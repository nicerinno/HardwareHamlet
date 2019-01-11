<?php
/**
 * Created by PhpStorm.
 * Users: R_Rod
 * Date: 20/12/2018
 * Time: 22:53
 */

include_once (__DIR__ . "/model/Component.php");
include_once "db.php";

header("Content-Type: application/json");

$component = array();
$conn = connDB();

if(isset($_GET['price_order']) && isset($_GET['component_type_id']) && isset($_GET['search'])){

    $search = $_GET['search'];
    $order = $_GET['price_order'];
    $type_id = $_GET['component_type_id'];

    if($search=='none' && $type_id=='0'){
        $sql1 = "SELECT * FROM components ORDER BY price $order";
        $result1 = $conn->query($sql1);
        endConnDB($conn);

        if ($result1->num_rows > 0) {
            while ($row = $result1->fetch_assoc()) {
                array_push($component, $newComponent = new Component($row['component_id'],$row['component_type_id'],$row['user_id'],$row['brand'], $row['name'],$row['description'],$row['price'],$row['flg_available'],$row['icon_url'],$row['regist_date']));
            }
        }
    } else if($search=='none'){
        $sql2 = "SELECT * FROM components WHERE component_type_id = '$type_id' ORDER BY price $order";
        $result2 = $conn->query($sql2);
        endConnDB($conn);

        if ($result2->num_rows > 0) {
            while ($row = $result2->fetch_assoc()) {
                array_push($component, $newComponent = new Component($row['component_id'],$row['component_type_id'],$row['user_id'],$row['brand'], $row['name'],$row['description'],$row['price'],$row['flg_available'],$row['icon_url'],$row['regist_date']));
            }
        }
    } else if($type_id=='0'){
        $sql3 = "SELECT * FROM components WHERE concat(brand,' ', name) REGEXP '$search' ORDER BY price $order";
        $result3 = $conn->query($sql3);
        endConnDB($conn);

        if ($result3->num_rows > 0) {
            while ($row = $result3->fetch_assoc()) {
                array_push($component, $newComponent = new Component($row['component_id'],$row['component_type_id'],$row['user_id'],$row['brand'], $row['name'],$row['description'],$row['price'],$row['flg_available'],$row['icon_url'],$row['regist_date']));
            }
        }
    } else{
        $sql4 = "SELECT * FROM components WHERE concat(brand,' ', name) REGEXP '$search' AND component_type_id = '$type_id' ORDER BY price $order";
        $result4 = $conn->query($sql4);
        endConnDB($conn);

        if ($result4->num_rows > 0) {
            while ($row = $result4->fetch_assoc()) {
                array_push($component, $newComponent = new Component($row['component_id'],$row['component_type_id'],$row['user_id'],$row['brand'], $row['name'],$row['description'],$row['price'],$row['flg_available'],$row['icon_url'],$row['regist_date']));
            }
        }
    }
}else if(isset($_GET['component_id'])){

    $component_id = $_GET['component_id'];

    $sql1 = "SELECT * FROM components WHERE component_id='$component_id'";
    $result1 = $conn->query($sql1);
    endConnDB($conn);

    if ($result1->num_rows > 0) {
        while ($row = $result1->fetch_assoc()) {
            array_push($component, $newComponent = new Component($row['component_id'],$row['component_type_id'],$row['user_id'],$row['brand'], $row['name'],$row['description'],$row['price'],$row['flg_available'],$row['icon_url'],$row['regist_date']));
        }
    }
}else if(isset($_GET['build_id'])){

    $build_id = $_GET['build_id'];

    $components = "SELECT * FROM components WHERE component_id IN (SELECT component_id FROM build_components WHERE build_id =" . $buildId .  ")";
    $componentResult = $conn->query($components);
    endConnDB($conn);
    if($componentResult->num_rows >0){
        while($row = $componentResult->fetch_assoc()){
            array_push($componentsArray, $newComponent = new Component($row['component_id'],$row['component_type_id'],$row['user_id'],$row['brand'],$row['name'],$row['description'],$row['price'],$row['flg_available'],$row['icon_url'],$row['regist_date']));
        }
    }
}else{
    $sql1 = "SELECT * FROM components ORDER BY price desc";
    $result1 = $conn->query($sql1);
    endConnDB($conn);

    if ($result1->num_rows > 0) {
        while ($row = $result1->fetch_assoc()) {
            array_push($component, $newComponent = new Component($row['component_id'],$row['component_type_id'],$row['user_id'],$row['brand'], $row['name'],$row['description'],$row['price'],$row['flg_available'],$row['icon_url'],$row['regist_date']));
        }
    }
}

echo json_encode($component);