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
        $sql = "SELECT * FROM components order by price desc limit 5";
        $query = $conn->query($sql);

        if($query->num_rows > 0){
            while($row = $query->fetch_assoc()) {
                $newComponent = new Component($row['component_id'], $row['component_type_id'], $row['user_id'], $row['brand']
                    , $row['name'], $row['description'], $row['price'], $row['flg_available'], $row['icon_url'], $row['regist_date']);

                array_push($data, $newComponent);
            }
        }
    }

    endConnDB($conn);
}

echo json_encode($data);