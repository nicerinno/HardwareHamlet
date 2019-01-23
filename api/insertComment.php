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
if(isset($_GET['build_id']) && isset($_GET['content']) && isset($_GET['user_id']) && isset($_GET['regist_date'])){
    $build_id = $_GET['build_id'];
    $content = $_GET['content'];
    $user_id = $_GET['user_id'];
    $regist_date = $_GET['regist_date'];


        $sql = "INSERT INTO comments(build_id, content, user_id, regist_date) VALUES('$build_id','$content','$user_id','$regist_date')";
        $regist = $conn->query($sql);
        if($regist){
            $data = ["request_type" => "comment registration", "result" => "successful"];
        } else{
            //json response body success
            $data = ["request_type" => "comment registration", "result" => "failure " ];
        }

    endConnDB($conn);
}
echo json_encode($data);