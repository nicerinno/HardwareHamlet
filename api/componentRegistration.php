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

//checking if the json input has all the parameteres
if(!empty($input->component_type_id) && !empty($input->user_id) && !empty($input->brand)
    && !empty($input->name) && !empty($input->description) && !empty($input->price)
    && !empty($input->flg_available) && !empty($input->icon_url)){

    $checkIfActive = "SELECT * FROM users WHERE user_id='$input->build_name' AND active=true";
    $runCheckActive = $conn->query($checkIfActive);

    if($runCheckActive->num_rows > 0){
//inserting the component in Database
        $sql = "INSERT INTO components VALUES('','$input->component_type_id','$input->user_id','$input->brand','$input->name','$input->description','$input->price','$input->flg_available','$input->icon_url')";
        $regist = $conn->query($sql);
        if($regist){

            $sql2 = "UPDATE users SET reputation = reputation + 25 WHERE user_id = '$input->user_id'";
            $setRep = $conn->query($sql2);

            //check if user is eligible for new Title
            //getting the amount of rep the user has
            $getRepNumber = "SELECT reputation FROM users WHERE user_id = '$input->user_id' ";
            $queryRep = $conn->query($getRepNumber)->fetch_assoc();
            $repAmount = $queryRep['reputation'];

            //checking if there is a title with that amount of rep
            $checkForTitle = "SELECT title_id FROM titles WHERE rep_amount = '$repAmount'";
            $queryCheck = $conn->query($checkForTitle);
            $titleCheck = $queryCheck->fetch_assoc();
            $title_id = $titleCheck['title_id'];

            //if the check query returns a row then update the title
            if($queryCheck->num_rows > 0){
                $setNewTitle = "UPDATE users SET title_id = '$title_id' WHERE user_id = '$input->user_id'";
                $queryNewTitel = $conn->query($setNewTitle);
            }

            //json response body success
            $data = ["request_type" => "component registration", "result" => "successfull"];
            $component = new Component('',$input->component_type_id,$input->user_id,$input->brand,$input->name,$input->description,$input->price,$input->flg_available,$input->icon_url);
        } else{
            //json response body failure
            $data = ["request_type" => "component registration", "result" => "failure " ];
        }
    }else{
        //json response body failure
        $data = ["request_type" => "build registration", "result" => "User not active"];
    }



    endConnDB($conn);
}
echo json_encode($data);