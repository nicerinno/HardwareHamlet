<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 06/01/2019
 * Time: 00:21
 */

include_once(__DIR__ . "/model/Users.php");
include_once "db.php";

header("Content-Type: application/json");

$input = json_decode(file_get_contents('php://input'));

$conn = connDB();
$data = array();

if(!empty($input->username) && !empty($input->password)){
    $username = $input->username;
    $password = $input->password;

    $sqlUser = "SELECT * FROM users WHERE username = '$username'";
    $queryUser = $conn->query($sqlUser);

    if($queryUser->num_rows > 0){

        $sqlP = "SELECT password FROM users WHERE username = '$username'";
        $queryPass = $conn->query($sqlP);
        $hashedP = $queryPass->fetch_assoc();

        $verify = password_verify($password,$hashedP['password']);

        if($verify){
            $data = ["request_type" => "user validation", "result" => "successful"];
        }else {
            $data = ["request_type" => "user validation", "result" => "incorrect password"];
        }
    } else {
        $data = ["request_type" => "user validation", "result" => "username does not exist"];
    }
}
echo json_encode($data);

