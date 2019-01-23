<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 31/12/2018
 * Time: 14:21
 */

include_once (__DIR__ . "/model/Builds.php");
include_once "db.php";

header("Content-Type: application/json");

$conn = connDB();
$data = array();
if(isset($_GET['build_id']) && isset($_GET['user_id'])){
    $user_id = $_GET['user_id'];
    $build_id = $_GET['build_id'];

    $checkIfActive = "SELECT * FROM users WHERE user_id=(SELECT user_id FROM builds WHERE build_id = '$build_id') AND active=true";
    $runCheckActive = $conn->query($checkIfActive);

    if($runCheckActive->num_rows > 0){
        $sql2 = "INSERT INTO likes (build_id,user_id) values ($build_id,$user_id)";
        $registLike = $conn->query($sql2);

        if($registLike){
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
                $id = $medal['medal_id'];

                //if yes, update the medal id of the user
                if($queryItPls->num_rows > 0){
                    $setNewMedal = "UPDATE users SET medal_id = '$id' WHERE user_id = (SELECT user_id FROM builds WHERE build_id = '$build_id')";
                    $query = $conn->query($setNewMedal);
                }
                //json responde body success
                $data = array("request_type" => "post like", "result" => "successfull");
            } else{
                //json response body failure
                $data = array("request_type" => "post like", "result" => "failure");
            }
        }else {
            $sql2 = "INSERT INTO likes (build_id,user_id) values ($build_id,$user_id)";
            $registLike = $conn->query($sql2);
            $sql = "UPDATE builds SET likes = likes + - WHERE build_id = '$build_id'";
            $regist = $conn->query($sql);
            if ($regist) {
                $data = array("request_type" => "post like", "result" => "already liked");

                //TODO: TRATAR DO removing likes
            }

        }
    }else{
        //json response body failure
        $data = array("request_type" => "build registration", "result" => "User not active");
    }
    endConnDB($conn);

}
echo json_encode($data);
