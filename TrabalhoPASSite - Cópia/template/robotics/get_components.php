<?php
/**
 * Created by PhpStorm.
 * User: Mário
 * Date: 18/01/2019
 * Time: 12:41
 */

include_once "Components.php";

$nc = new Components("","","","","","","","","");


if(!isset($_POST['id'])) {
    $result = $nc->query_components();
}
else {
    $type = $_POST['id'];
    if ($type == 0)
        $result = $nc->query_components();
    else
        $result = $nc->query_component_type($type);
}

$rows = [];
if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc())
        array_push($rows, $row);
}

echo json_encode($rows);