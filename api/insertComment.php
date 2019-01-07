<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 30/12/2018
 * Time: 19:45
 */
include_once (__DIR__ . "/model/Comments.php");
include_once "db.php";

header("Content-Type: application/json");

$input = json_decode(file_get_contents('php://input'));
$conn = connDB();
if(!empty($input->build_id) && !empty($input->content) && !empty($input->user_id)){
    $checkIfActive = "SELECT * FROM users WHERE user_id='$input->build_name' AND active=true";
    $runCheckActive = $conn->query($checkIfActive);

    if($runCheckActive->num_rows > 0){
        $sql = "INSERT INTO comments(build_id, content, user_id) VALUES('$input->build_id','$input->content','$input->user_id')";
        $regist = $conn->query($sql);
        if($regist){
            $data = ["request_type" => "comment registration", "result" => "successfull"];
            $comment = new Comments("",$input->build_id,$input->content,$input->user_id);
        } else{
            //json response body success
            $data = ["request_type" => "comment registration", "result" => "failure " ];
        }
    }else{
        //json response body failure
        $data = ["request_type" => "build registration", "result" => "User not active"];
    }

    endConnDB($conn);
}
echo json_encode($data);