<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 30/12/2018
 * Time: 20:10
 */

include_once (__DIR__ . "/model/Email.php");
include_once "db.php";

header("Content-Type: application/json");

$input = json_decode(file_get_contents('php://input'));
$conn = connDB();
if(!empty($input->comment_id)){
    $sql = "DELETE FROM comments WHERE comment_id = $input->comment_id";
    $regist = $conn->query($sql);
    if($regist){
        $data = ["request_type" => "comment delete", "result" => "successfull"];
        $comment = new Comments("",$input->build_id,$input->content,$input->user_id);
    } else{
        //json response body success
        $data = ["request_type" => "comment delete", "result" => "failure " ];
    }
    endConnDB($conn);
}
echo json_encode($data);