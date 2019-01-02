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
    $sql = "INSERT INTO comments(build_id, content, user_id) VALUES('$input->build_id','$input->content','$input->user_id')";
    $regist = $conn->query($sql);
    if($regist){
        $data = ["request-type" => "comment registration", "result" => "successfull"];
        $comment = new Comments("",$input->build_id,$input->content,$input->user_id);
    } else{
        //json response body success
        $data = ["request-type" => "comment registration", "result" => "failure " ];
    }
    endConnDB($conn);
}
echo json_encode($data);