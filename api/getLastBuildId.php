<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 02/01/2019
 * Time: 18:39
 */

include_once "db.php";

header("Content-Type: application/json");
$conn = connDB();

$getLastId = "SELECT MAX(build_id) FROM builds";
$executeLastId = $conn->query($getLastId);
$lastId = $executeLastId->fetch_assoc();

$conn->close();

$data = ["request_type" => "last build id", "result" => $lastId['MAX(build_id)']];

echo json_encode($data);