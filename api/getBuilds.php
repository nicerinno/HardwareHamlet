<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 26/12/2018
 * Time: 01:35
 */
include_once (__DIR__ . "/model/Builds.php");

include_once "db.php";

header("Content-Type: application/json");

$buildArray = array();
$conn = connDB();

if(isset($_GET['recent']) && isset($_GET['build_type_id']) && isset($_GET['search'])){

    $search = $_GET['search'];
    $order = $_GET['recent'];
    $type_id = $_GET['build_type_id'];

    if($search=='none' && $type_id=='0'){
        $sql1 = "SELECT * FROM builds ORDER BY regist_date $order";
        $result1 = $conn->query($sql1);
        endConnDB($conn);

        if ($result1->num_rows > 0) {
            while ($buildRow = $result1->fetch_assoc()) {
                array_push($buildArray, $newBuild = new Builds($buildRow['build_id'],$buildRow['user_id'],$buildRow['build_type_id'],$buildRow['build_name'],$buildRow['description'], $buildRow['cpu_description'],$buildRow['gpu_description'],$buildRow['ram_description'],$buildRow['price'],$buildRow['regist_date']));
            }
        }
    } else if($search=='none'){
        $sql2 = "SELECT * FROM builds WHERE build_type_id = '$type_id' ORDER BY regist_date $order";
        $result2 = $conn->query($sql2);
        endConnDB($conn);

        if ($result2->num_rows > 0) {
            while ($buildRow = $result2->fetch_assoc()) {
                array_push($buildArray, $newBuild = new Builds($buildRow['build_id'],$buildRow['user_id'],$buildRow['build_type_id'],$buildRow['build_name'],$buildRow['description'], $buildRow['cpu_description'],$buildRow['gpu_description'],$buildRow['ram_description'],$buildRow['price'],$buildRow['likes'],$buildRow['regist_date']));
            }
        }
    } else if($type_id=='0'){
        $sql3 = "SELECT * FROM builds WHERE name REGEXP '$search' ORDER BY regist_date $order";
        $result3 = $conn->query($sql3);
        endConnDB($conn);

        if ($result3->num_rows > 0) {
            while ($buildRow = $result3->fetch_assoc()) {
                array_push($buildArray, $newBuild = new Builds($buildRow['build_id'],$buildRow['user_id'],$buildRow['build_type_id'],$buildRow['build_name'],$buildRow['description'], $buildRow['cpu_description'],$buildRow['gpu_description'],$buildRow['ram_description'],$buildRow['price'],$buildRow['likes'],$buildRow['regist_date']));
            }
        }
    } else{
        $sql4 = "SELECT * FROM builds WHERE name REGEXP '$search' AND build_type_id = '$type_id' ORDER BY regist_date $order";
        $result4 = $conn->query($sql4);
        endConnDB($conn);

        if ($result4->num_rows > 0) {
            while ($buildRow = $result4->fetch_assoc()) {
                array_push($buildArray, $newBuild = new Builds($buildRow['build_id'],$buildRow['user_id'],$buildRow['build_type_id'],$buildRow['build_name'],$buildRow['description'], $buildRow['cpu_description'],$buildRow['gpu_description'],$buildRow['ram_description'],$buildRow['price'],$buildRow['likes'],$buildRow['regist_date']));
            }
        }
    }

}else if(isset($_GET['build_id'])){

    $component_id = $_GET['build_id'];

    $sql1 = "SELECT * FROM builds WHERE build_id='$build_id'";
    $result1 = $conn->query($sql1);
    endConnDB($conn);

    if ($result1->num_rows > 0) {
        while ($buildRow = $result1->fetch_assoc()) {
            array_push($buildArray, $newBuild = new Builds($buildRow['build_id'],$buildRow['user_id'],$buildRow['build_type_id'],$buildRow['build_name'],$buildRow['description'], $buildRow['cpu_description'],$buildRow['gpu_description'],$buildRow['ram_description'],$buildRow['price'],$buildRow['likes'],$buildRow['regist_date']));
        }
    }

}else if(isset($_GET['build_type_id'])){

    $build_type_id = $_GET['build_type_id'];

    $sql1 = "SELECT * FROM builds WHERE build_type_id='$build_type_id'";
    $result1 = $conn->query($sql1);
    endConnDB($conn);

    if ($result1->num_rows > 0) {
        while ($buildRow = $result1->fetch_assoc()) {
            array_push($buildArray, $newBuild = new Builds($buildRow['build_id'],$buildRow['user_id'],$buildRow['build_type_id'],$buildRow['build_name'],$buildRow['description'], $buildRow['cpu_description'],$buildRow['gpu_description'],$buildRow['ram_description'],$buildRow['price'],$buildRow['likes'],$buildRow['regist_date']));
        }
    }
    //cenas

}else{
    $sql1 = "SELECT * FROM builds ORDER BY regist_date desc";
    $result1 = $conn->query($sql1);
    endConnDB($conn);

    if ($result1->num_rows > 0) {
        while ($buildRow = $result1->fetch_assoc()) {
            array_push($buildArray, $newBuild = new Builds($buildRow['build_id'],$buildRow['user_id'],$buildRow['build_type_id'],$buildRow['build_name'],$buildRow['description'], $buildRow['cpu_description'],$buildRow['gpu_description'],$buildRow['ram_description'],$buildRow['price'],$buildRow['likes'],$buildRow['regist_date']));
        }
    }

}
echo json_encode($buildArray);