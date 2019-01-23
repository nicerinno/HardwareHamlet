<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 30/12/2018
 * Time: 19:54
 */
include_once (__DIR__ . "/model/Comments.php");
include_once "db.php";

header("Content-Type: application/json");

$comments = array();
$conn = connDB();

if(isset($_GET['build_id'])){
    $build_id = $_GET['build_id'];

    $sql = "SELECT * FROM comments WHERE build_id='$build_id' ORDER BY comment_id DESC";
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            array_push($comments, $newComment = new Comments($row['comment_id'],$row['build_id'],$row['content'],$row['user_id'],$row['regist_date']));
        }
    }
    endConnDB($conn);
}
echo json_encode($comments);