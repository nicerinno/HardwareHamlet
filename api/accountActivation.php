<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 07/01/2019
 * Time: 15:27
 */

include_once (__DIR__ . "/model/Users.php");
include_once "db.php";

header("Content-Type: application/json");

//$data = [];
$conn = connDB();
if(isset($_GET['username']) && isset($_GET['validation_code'])){
    $username = $_GET['username'];
    $validation_code = $_GET['validation_code'];

    $sqlCheck = "SELECT * FROM users WHERE username='$username' AND validation_code='$validation_code'";
    $runCheck = $conn->query($sqlCheck);

    if($runCheck->num_rows > 0){
        $updateStatus = "UPDATE users SET active = true WHERE username = '$username' AND validation_code='$validation_code'";
        $setStatus = $conn->query($updateStatus);
    }
}
?>
<h1>ACCOUNT ACTIVATED</h1>
