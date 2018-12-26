<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 24/12/2018
 * Time: 23:27
 */
include_once (__DIR__ . "/model/Component.php");
include_once "db.php";

header("Content-Type: application/json");

$input = json_decode(file_get_contents('php://input'));
$conn = connDB();
$data = [];
if(!empty($input->component_type_id) && !empty($input->user_id) && !empty($input->brand)
    && !empty($input->name) && !empty($input->description) && !empty($input->price)
    && !empty($input->flg_available) && !empty($input->icon_url)){

//    $component = new Component('','$input->component_type_id','$input->user_id','$input->brand','$input->name','$input->description','$input->price','$input->flg_available','$input->icon_url');
    $sql = "INSERT INTO components VALUES('','$input->component_type_id','$input->user_id','$input->brand','$input->name','$input->description','$input->price','$input->flg_available','$input->icon_url')";
    $regist = $conn->query($sql);
    if($regist){
        $data = ["request-type" => "component registration", "result" => "successfull"];
        $component = new Component('',$input->component_type_id,$input->user_id,$input->brand,$input->name,$input->description,$input->price,$input->flg_available,$input->icon_url);
    } else{
        //json response body success
        $data = ["request-type" => "component registration", "result" => "failure " ];
    }
    endConnDB($conn);
}
echo json_encode($data);