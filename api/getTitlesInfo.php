<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 03/01/2019
 * Time: 18:08
 */

include_once (__DIR__ . "/model/Titles.php");
include_once "db.php";


$conn = connDB();

if(isset($_GET['title_id'])){
    $id = $_GET['title_id'];
    $data = 0;

    $sql = "SELECT * FROM titles WHERE title_id = '$id'";
    $query = $conn->query($sql);

    if($query->num_rows > 0){
        $row = $query->fetch_assoc();
        $data = new Titles($row['title_id'],$row['name'],$row['rep_amount'],$row['color']);
    }
}else {
    $data = array();

    $sql = "SELECT * FROM titles";
    $query = $conn->query($sql);

    if($query->num_rows > 0){
        while($row = $query->fetch_assoc()){
            array_push($data, new Titles($row['title_id'],$row['name'],$row['rep_amount'],$row['color']));
        }
    }
}
$conn->close();
echo json_encode($data);