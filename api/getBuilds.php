<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 26/12/2018
 * Time: 01:35
 */
include_once (__DIR__ . "/model/Build.php");
include_once (__DIR__ . "/model/Component.php");
include_once "db.php";

header("Content-Type: application/json");

$buildArray = array();
$componentsArray = array();
$conn = connDB();

if(isset($_GET['build_type_id'])){
    $type = $_GET['build_type_id'];

    $sql = "SELECT * FROM builds WHERE build_type_id='$type'";
    $result = $conn->query($sql);



    endConnDB($conn);
    if ($result->num_rows > 0) {
        while ($buildRow = $result->fetch_assoc()) {
            array_push($buildArray, $newBuild = new Build($buildRow['build_id'],$buildRow['user_id'],$buildRow['build_type_id'],$buildRow['build_name'],$buildRow['description'], $buildRow['cpu_description'],$buildRow['gpu_description'],$buildRow['ram_description'],$buildRow['price'],$buildRow['likes']));
        }
    }

} else if(isset($_GET['build_name'])){
    $name = $_GET['build_name'];

    $sql = "SELECT * FROM builds WHERE build_name='$name'";
    $result = $conn->query($sql);
    endConnDB($conn);

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            array_push($buildArray, $newBuild = new Build($row['build_id'],$row['user_id'],$row['build_type_id'],$row['build_name'],$row['description'], $row['cpu_description'],$row['gpu_description'],$row['ram_description'],$row['price'],$row['likes']));
        }

    }
} else{
    $sql = "SELECT * FROM builds";
    $result = $conn->query($sql);
    endConnDB($conn);
    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            array_push($buildArray, $newBuild = new Build($row['build_id'],$row['user_id'],$row['build_type_id'],$row['build_name'],$row['description'], $row['cpu_description'],$row['gpu_description'],$row['ram_description'],$row['price'],$row['likes']));
        }
    }
}

echo json_encode($buildArray);