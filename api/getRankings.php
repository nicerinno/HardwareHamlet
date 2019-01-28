<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 22/01/2019
 * Time: 16:55
 */
include_once (__DIR__ . "/model/Users.php");
include_once (__DIR__ . "/model/Component.php");
include_once (__DIR__ . "/model/Builds.php");
include_once (__DIR__ . "/model/Build_Components.php");
include_once (__DIR__ . "/model/Comments.php");

header("Content-Type: application/json");

include_once "db.php";
$data = [];
$conn = connDB();
if(isset($_GET['ranking'])){
    $typeOfRanking = $_GET['ranking'];

    if($typeOfRanking == "users"){
        $sql = "SELECT * FROM users order by reputation desc limit 5";
        $query = $conn->query($sql);

        if($query->num_rows > 0){
            while($row = $query->fetch_assoc()){
                $newUser = new Users($row['user_id'],$row['title_id'],$row['usertype_id'],$row['medal_id']
                    ,$row['email'],$row['username'],$row['password'], $row['description'],$row['reputation'],$row['active']
                    ,$row['validation_code'],$row['regist_date']);

                array_push($data, $newUser);
            }
        }
    }else if($typeOfRanking == "components"){
        $sql = "select component_id, count(component_id) as count FROM build_components GROUP BY component_id order by count(component_id) desc limit 5";
        $query = $conn->query($sql);
        if($query->num_rows > 0){
            while($row = $query->fetch_assoc()) {
                $id = $row['component_id'];
                $sql2 = "SELECT * FROM components WHERE component_id = '$id'";
                $component = $conn->query($sql2)->fetch_assoc();
                $newComponent = new Component($component['component_id'], $component['component_type_id'], $component['user_id'], $component['brand']
                    , $component['name'], $component['description'], $component['price'], $component['flg_available'], $component['icon_url'], $component['regist_date']);

                array_push($data, $newComponent);
            }
        }
    }else if($typeOfRanking == "builds"){
        $sql = "SELECT * FROM builds order by likes desc limit 5";
        $query = $conn->query($sql);

        if($query->num_rows > 0){
            while($row = $query->fetch_assoc()) {
                $newBuild = new Builds($row['build_id'], $row['user_id'], $row['build_type_id'], $row['build_name']
                    , $row['description'], $row['price'], $row['likes'], $row['regist_date']);

                array_push($data, $newBuild);
            }
        }
    }

    endConnDB($conn);
}

echo json_encode($data);