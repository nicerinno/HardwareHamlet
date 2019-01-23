<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 03/01/2019
 * Time: 17:40
 */

include_once (__DIR__ . "/model/Build_Type.php");
include_once "db.php";


$conn = connDB();
if(isset($_GET['build_type_id'])){
    $id = $_GET['build_type_id'];
    $data = 0;

    $sql = "SELECT * FROM build_type WHERE build_type_id = '$id'";
    $query = $conn->query($sql);

    if($query->num_rows > 0){
        $row = $query->fetch_assoc();
        $data = new Build_Type($row['build_type_id'],$row['name']);

    }
}else {
    $data = [];
    $sql = "SELECT * FROM build_type";
    $query = $conn->query($sql);

    if($query->num_rows > 0){
        while($row = $query->fetch_assoc()){
            array_push($data, new Build_Type($row['build_type_id'],$row['name']));
        }
    }
}
echo json_encode($data);

$conn->close();
