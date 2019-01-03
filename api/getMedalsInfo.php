<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 03/01/2019
 * Time: 18:07
 */

include_once (__DIR__ . "/model/Medals.php");
include_once "db.php";

$conn = connDB();

if(isset($_GET['medal_id'])){
    $id = $_GET['medal_id'];
    $data = 0;

    $sql = "SELECT * FROM medals WHERE medal_id = '$id'";
    $query = $conn->query($sql);

    if($query->num_rows > 0){
        $row = $query->fetch_assoc();
        $data = new Medals($row['medal_id'],$row['name'],$row['icon'],$row['amount_likes']);
    }
}else {
    $data = array();

    $sql = "SELECT * FROM medals";
    $query = $conn->query($sql);

    if($query->num_rows > 0){
        while($row = $query->fetch_assoc()){
            array_push($data, new Medals($row['medal_id'],$row['name'],$row['icon'],$row['amount_likes']));
        }
    }
}
$conn->close();
echo json_encode($data);