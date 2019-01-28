<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 23/01/2019
 * Time: 19:21
 */
include_once (__DIR__ . "/model/Builds.php");
include_once "db.php";

header("Content-Type: application/json");

$conn = connDB();
$data = array();
if(isset($_GET['build_id']) && isset($_GET['user_id'])){
    $user_id = $_GET['user_id'];
    $build_id = $_GET['build_id'];

    $checkIfLike = "SELECT * FROM likes WHERE user_id='$user_id' AND build_id='$build_id'";
    $runCheckLike = $conn->query($checkIfLike);
    if($runCheckLike->num_rows > 0) {
        $data = array("request_type" => "check like", "result" => "true");
    }else{
        $data = array("request_type" => "check like", "result" => "false");
    }

    endConnDB($conn);

}
echo json_encode($data);