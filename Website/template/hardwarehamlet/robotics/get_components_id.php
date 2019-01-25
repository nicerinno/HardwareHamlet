<?php
/**
 * Created by PhpStorm.
 * User: Mário
 * Date: 23/01/2019
 * Time: 22:36
 */
include_once "Components.php";

$nc = new Components("","","","","","","","","");

if(!empty($_GET['id'])) {
    $id = $_GET['id'];
    $result = $nc->query_component_id($id);

    $rows = [];
    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc())
            array_push($rows, $row);

        echo json_encode($rows);
    }

}

?>