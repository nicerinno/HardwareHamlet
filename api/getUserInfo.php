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
header("Content-Type: application/json");


if(isset($_GET['user_id'])){
    $id = $_GET['user_id'];
    $data = 0;

    $sql = "SELECT * FROM users WHERE user_id = '$id'";
    $query = $conn->query($sql);

    if($query->num_rows > 0){
        $row = $query->fetch_assoc();
        $data = new Users($row['user_id'],$row['title_id'],$row['usertype_id'],$row['medal_id'],$row['email'],$row['username'],$row['password'], $row['description'],$row['reputation'],$row['active'],$row['validation_code'],$row['regist_date']);
    }
}else if(isset($_GET['username'])){
    $id = $_GET['username'];
    $data = 0;

    $sql = "SELECT user_id FROM users WHERE username = '$id'";
    $query = $conn->query($sql);


    if($query->num_rows > 0) {
        $row = $query->fetch_assoc();
        $user_id = $row['user_id'];
        $data = ["request_type" => "user_id", "result" => "$user_id"];
    }
}
endConnDB($conn);
echo json_encode($data);