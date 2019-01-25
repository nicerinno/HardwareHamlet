<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 25/01/2019
 * Time: 12:26
 */

include_once (__DIR__ . "/model/Users.php");
header("Content-Type: application/json");
include_once "db.php";
$data= array();
if(isset($_GET['user_id'])){
    $user_id = $_GET['user_id'];
    $conn = connDB();

    $sql = "SELECT * FROM users WHERE user_id = '$user_id' AND active=true";
    $runSql = $conn->query($sql);

    if($runSql->num_rows > 0){
        $data = ["request_type" => "check active", "result" => "active"];
    }else{
        $data = ["request_type" => "check active", "result" => "false"];
    }

}
return json_encode($data);