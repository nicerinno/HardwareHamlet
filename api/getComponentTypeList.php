<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 11/01/2019
 * Time: 23:37
 */
include_once (__DIR__ . "/model/Component_Type.php");
include_once "db.php";


$conn = connDB();
if(isset($_GET['component_type_id'])){
    $id = $_GET['component_type_id'];
    $data = 0;

    $sql = "SELECT * FROM component_type WHERE component_type_id = '$id'";
    $query = $conn->query($sql);

    if($query->num_rows > 0){
        $row = $query->fetch_assoc();
        $data = new Build_Type($row['component_type_id'],$row['name']);
    }
}else {
    $data = [];
    $sql = "SELECT * FROM component_type";
    $query = $conn->query($sql);

    if($query->num_rows > 0){
        while($row = $query->fetch_assoc()){
            array_push($data, new Component_Type($row['component_type_id'],$row['name']));
        }
    }
}
echo json_encode($data);

$conn->close();
