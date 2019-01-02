<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 31/12/2018
 * Time: 14:21
 */

include_once (__DIR__ . "/model/Build.php");
include_once "db.php";

header("Content-Type: application/json");

$conn = connDB();
$data = [];
if(isset($_GET['build_id'])){

    $build_id = $_GET['build_id'];

    $sql = "UPDATE builds SET likes = likes + 1 WHERE build_id = '$build_id'";
    $regist = $conn->query($sql);
    if($regist){
        //checking if the user is eligible for a new medal
        //getting the amount of likes that a user has in every builds
        $getNumbOfLikes = "SELECT SUM(likes) from builds WHERE user_id = (SELECT user_id FROM builds WHERE build_id = '$build_id')";
        $likesQ = $conn->query($getNumbOfLikes)->fetch_assoc();
        $likes = $likesQ['SUM(likes)'];

        //checking if there is a medal with the same amount of likes
        $checkForMedal = "SELECT medal_id FROM medals WHERE amount_likes = '$likes'";
        $queryItPls = $conn->query($checkForMedal);
        $medal = $queryItPls->fetch_assoc();
        $id = $queryItPls['medal_id'];

        //if yes, update the medal id of the user
        if($queryItPls->num_rows > 0){
            $setNewMedal = "UPDATE users SET medal_id = '$id' WHERE user_id = (SELECT user_id FROM builds WHERE build_id = '$build_id')";
            $query = $conn->query($setNewMedal);
        }
        //json responde body success
        $data = ["request-type" => "post like", "result" => "successfull"];
    } else{
        //json response body failure
        $data = ["request-type" => "post like", "result" => "failure." ];
    }

    endConnDB($conn);

}
echo json_encode($data);
