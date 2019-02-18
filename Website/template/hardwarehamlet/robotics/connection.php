<?php
/**
 * Created by PhpStorm.
 * User: Mário
 * Date: 10/01/2019
 * Time: 15:16
 */

function connDB ()
{
    $servername = "localhost";
    $username = "root";
    //$password = "XfuH4ft1TRtdwfki";
    $password = "";
    $dbname = "HardwareHamlet";

// Create connection
    $conn = new mysqli($servername, $username, $password, $dbname);
    $conn->set_charset("utf8mb4");

// Check connection
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }
    else {
        // echo "<br>","Ligado","<br>";
        return $conn;
    }
}

function endConnDB ($conn)
{
    $conn->close();
}



?>
