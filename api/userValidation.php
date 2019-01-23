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

    $sqlUser = "SELECT * FROM users WHERE username = '$username' or email='$username'";
    $queryUser = $conn->query($sqlUser);

    if($queryUser->num_rows > 0){
        $sqlUserAct = "SELECT * FROM users WHERE active = true AND username = '$username' or email='$username'";
        $queryUserAct = $conn->query($sqlUserAct);

        if($queryUserAct->num_rows > 0){
            $sqlP = "SELECT password FROM users WHERE username = '$username' or email='$username'";
            $queryPass = $conn->query($sqlP);
            $hashedP = $queryPass->fetch_assoc();

            $verify = password_verify($password,$hashedP['password']);

            if($verify){
                $data = ["request_type" => "user validation", "result" => "successful"];
            }else {
                $data = ["request_type" => "user validation", "result" => "incorrect password"];
            }
        }else if($queryUserAct->num_rows == 0){
            $data = ["request_type" => "user validation", "result" => "user inactive"];
        }
    } else {
        $data = ["request_type" => "user validation", "result" => "username does not exist"];
    }
}
echo json_encode($data);

