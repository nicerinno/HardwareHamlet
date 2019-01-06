<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 04/01/2019
 * Time: 12:51
 */

include_once(__DIR__ . "/model/Users.php");
include_once "db.php";

$conn = connDB();

$conn = connDB();

if(isset($_GET['user_id'])){
    $id = $_GET['user_id'];
    $data = 0;

    $sql = "SELECT * FROM users WHERE user_id = '$id'";
    $query = $conn->query($sql);

    if($query->num_rows > 0){
        $row = $query->fetch_assoc();
        $data = new Users($row['user_id'],$row['title_id'],$row['usertype_id'],$row['medal_id'],$row['email'],$row['username'],$row['password'], $row['description'],$row['reputation'],$row['active'],$row['validation_code'],$row['regist_date']);
    }
}else {
    $data = array();

    $sql = "SELECT * FROM users";
    $query = $conn->query($sql);

    if($query->num_rows > 0){
        while($row = $query->fetch_assoc()){
            array_push($data, new Users($row['user_id'],$row['title_id'],$row['usertype_id'],$row['medal_id'],$row['email'],$row['username'],$row['password'], $row['description'],$row['reputation'],$row['active'],$row['validation_code'],$row['regist_date']));
        }
    }
}
$conn->close();
echo json_encode($data);