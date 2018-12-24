<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 21/12/2018
 * Time: 00:16
 */

include_once "User.php";
include_once "Email.php";
header("Content-Type: application/json");

$input = json_decode(file_get_contents('php://input'));

if(!empty($input->username) && !empty($input->email) && !empty($input->password)){

    //getting the variables from the input
    $username = $input->username;
    $email = $input->email;
    $password = $input->password;

    $user = new User($email,$username,$password);

    //checking if there is another user with the username the user typed
    $check = $user->checkUserAvailability();
$data = [];
    if($check->num_rows == 0){
        $user = new User($email,$username,$password);
        $regist = $user->userRegistration();
        if($regist){
            $data = ["request-type" => "register", "result" => "successfull"];
            $email = new Email($email,$username,$user->validation_code);
        } else{
            //json response body success
            $data = ["request-type" => "register", "result" => "failure " . $username . " " . $email . " " . $password];
        }
    } else {
        $data = ["request-type" => "register", "result" => "Username already exists"];
    }
}
echo json_encode($data);