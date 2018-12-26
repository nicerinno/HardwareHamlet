<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 26/12/2018
 * Time: 01:35
 */
include_once (__DIR__ . "/model/Build.php");
include_once "db.php";

header("Content-Type: application/json");

$buildArray = array();
$conn = connDB();

if(isset($_GET['build_type_id'])){
    $type = $_GET['component_type_id'];

    $sql = "SELECT * FROM builds WHERE build_type_id='$type'";
    $result = $conn->query($sql);
    endConnDB($conn);

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            array_push($buildArray, $newBuild = new Build($row['build_id'],$row['user_id'],$row['build_type_id'],$row['name'],$row['description'], $row['cpu_description'],$row['gpu_description'],$row['ram_description'],$row['price'],$row['likes']));
        }
    }

} else if($_GET['name']){
    $name = $_GET['name'];

    $sql = "SELECT * FROM builds WHERE name='$name'";
    $result = $conn->query($sql);
    endConnDB($conn);

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            array_push($buildArray, $newBuild = new Build($row['build_id'],$row['user_id'],$row['build_type_id'],$row['name'],$row['description'], $row['cpu_description'],$row['gpu_description'],$row['ram_description'],$row['price'],$row['likes']));
        }

    }
} else{
    $sql = "SELECT * FROM builds";
    $result = $conn->query($sql);
    endConnDB($conn);
    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            array_push($buildArray, $newBuild = new Build($row['build_id'],$row['user_id'],$row['build_type_id'],$row['name'],$row['description'], $row['cpu_description'],$row['gpu_description'],$row['ram_description'],$row['price'],$row['likes']));
        }
    }
}

echo json_encode($buildArray);