<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 31/12/2018
 * Time: 15:14
 */
//include_once (__DIR__ . "/model/Buildss.php");
//include_once (__DIR__ . "/model/Component.php");
include_once (__DIR__ . "/model/Build_Components.php");
include_once "db.php";

header("Content-Type: application/json");

$input = json_decode(file_get_contents('php://input'));

$build_components = array();

$conn = connDB();
$data = array();

if(!empty($input->build)){
    $build = $input->build;

    $checkIfActive = "SELECT * FROM users WHERE user_id='$build->user_id' AND active=true";
    $runCheckActive = $conn->query($checkIfActive);

    if($runCheckActive->num_rows > 0){
        $sql = "SELECT * FROM builds WHERE build_name = '$build->build_name'";
        $checkExisting = $conn->query($sql);

        if ($checkExisting->num_rows == 0){
            $insert = "INSERT INTO builds(build_id,user_id, build_type_id, build_name, description, cpu_description, gpu_description, ram_description, price, likes) VALUES('$build->build_id','$build->user_id','$build->build_type_id','$build->build_name', '$build->description', '$build->cpu_description', '$build->gpu_description', '$build->ram_description', '0' ,'0')";
            $query1 = $conn->query($insert);
            if($query1){
                $data = ["request_type" => "build registration", "result" => "success"];
            }else{
                $data = ["request_type" => "build registration", "result" => "failure"];
            }

        } else {
            //json response body failure
            $data = ["request_type" => "build registration", "result" => "A build with this name already exists"];
        }
    }else{
        //json response body failure
        $data = ["request_type" => "build registration", "result" => "User not active"];
    }


    $conn->close();
}else if(!empty($input->build_components)){
    $build_components = $input->components;
    $build_id = $build_components[0]->build_id;
        for($i = 0; $i < count($build_components); $i++){
            $component = $build_components[$i];
            $insertBuildComponent = "INSERT INTO build_components(build_id,component_id,quantity) VALUES ('$build_id' , '$component->component_id' , '$component->quantity')";
            $query2 = $conn->query($insertBuildComponent);
        }
        if($query2){
            //update the price of the build
            $updatePrice = "UPDATE builds SET price = (SELECT round ((SELECT SUM(price) FROM components WHERE component_id IN (SELECT component_id FROM build_components WHERE build_id = '$build_id')),2)) WHERE build_id= '$build_id'";
            $queryUpdatePrice = $conn->query($updatePrice);
            if($queryUpdatePrice){
                //json response body success
                $data = ["request_type" => "components registration", "result" => "successfull"];
            } else{
                //json response body failure
                $data = ["request_type" => "build registration", "result" => "Failure. Couldn't set the price"];
            }

        }else{
            //json response body failure
            $data = ["request_type" => "build registration", "result" => "Failure. Couldn't regist the component"];
        }
}

echo json_encode($data);
