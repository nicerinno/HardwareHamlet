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

if(isset($_GET['component_type_id'])){
    $type = $_GET['component_type_id'];

    $sql = "SELECT * FROM components WHERE component_type_id='$type'";
    $result = $conn->query($sql);
    endConnDB($conn);

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            array_push($component, $newComponent = new Component($row['component_id'],$row['component_type_id'],$row['user_id'],$row['brand'], $row['name'],$row['description'],$row['price'],$row['flg_available'],$row['icon_url']));
        }
    }

} else if($_GET['brand']){
    $brand = $_GET['brand'];

    $sql = "SELECT * FROM components WHERE brand='$brand'";
    $result = $conn->query($sql);
    endConnDB($conn);

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            array_push($component, $newComponent = new Component($row['component_id'],$row['component_type_id'],$row['user_id'],$row['brand'], $row['name'],$row['description'],$row['price'],$row['flg_available'],$row['icon_url']));
        }
    }

} else if($_GET['price-order']){
    $order = $_GET['price-order'];

    $sql = "SELECT * FROM components ORDER BY price $order";
    $result = $conn->query($sql);
    endConnDB($conn);

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            array_push($component, $newComponent = new Component($row['component_id'],$row['component_type_id'],$row['user_id'],$row['brand'], $row['name'],$row['description'],$row['price'],$row['flg_available'],$row['icon_url']));
        }
    }
}else if($_GET['flg_available']){
    $order = $_GET['flg_available'];

    $sql = "SELECT * FROM components WHERE flg_available = true";
    $result = $conn->query($sql);
    endConnDB($conn);

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            array_push($component, $newComponent = new Component($row['component_id'],$row['component_type_id'],$row['user_id'],$row['brand'], $row['name'],$row['description'],$row['price'],$row['flg_available'],$row['icon_url']));
        }
    }
} else if($_GET['price-order'] && $_GET['component_type_id']) {
    $type = $_GET['component_type_id'];
    $order = $_GET['price-order'];

    $sql = "SELECT * FROM components WHERE component_type_id='$type' ORDER BY price $order";
    $result = $conn->query($sql);
    endConnDB($conn);

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            array_push($component, $newComponent = new Component($row['component_id'],$row['component_type_id'],$row['user_id'],$row['brand'], $row['name'],$row['description'],$row['price'],$row['flg_available'],$row['icon_url']));
        }
    }
} else{
    $sql = "SELECT * FROM components";
    $result = $conn->query($sql);
    endConnDB($conn);
    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            array_push($component, $newComponent = new Component($row['component_id'],$row['component_type_id'],$row['user_id'],$row['brand'], $row['name'],$row['description'],$row['price'],$row['flg_available'],$row['icon_url']));
        }
    }
}

echo json_encode($component);