<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 20/12/2018
 * Time: 22:53
 */

include_once "Component.php";
include_once "db.php";

header("Content-Type: application/json");



$component[] = Component::class;

$conn = connDB();

if(isset($_GET['component_type_id'])){
    $type = $_GET['component_type_id'];

    $sql = "SELECT * FROM components WHERE component_type_id='$type'";
    $result = $conn->query($sql);
    endConnDB($conn);

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            array_push($component, $row);
        }
    }

} else if($_GET['brand']){
    $brand = $_GET['brand'];

    $sql = "SELECT * FROM components WHERE brand='$brand'";
    $result = $conn->query($sql);
    endConnDB($conn);

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            array_push($component, $row);
        }
    }

} else if($_GET['price-order']){
    $order = $_GET['price-order'];

    $sql = "SELECT * FROM components ORDER BY price $order";
    $result = $conn->query($sql);
    endConnDB($conn);

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            array_push($component, $row);
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
            array_push($component, $row);
        }
    }
} else{
    $sql = "SELECT * FROM components";
    $result = $conn->query($sql);
    endConnDB($conn);
    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            array_push($component, $row);
        }
    }
}

echo json_encode($component);