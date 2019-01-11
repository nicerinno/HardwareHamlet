<?php

function connDB ()
{

    $servername = "localhost";
    $username = "hhadmin";
//    $password = "rs22352";
   $username = "root";
//    $password = "";
    $password = "XfuH4ft1TRtdwfki";
    $dbname = "HardwareHamlet";

    // Create connection
    $conn = new mysqli($servername, $username, $password, $dbname);
    $conn->set_charset('utf8mb4');
    // Check connection
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }
    else {
        return $conn;
    }

}

function endConnDB ($conn)
{
    $conn->close();
}

?>