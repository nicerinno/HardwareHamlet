<?php

function connDB ()
{
    $servername = "localhost";
    $username = "root";
    $password = "";
        //$password = "XfuH4ft1TRtdwfki";
    $dbname = "hardwarehamlet";

    // Create connection
    $conn = new mysqli($servername, $username, $password, $dbname);
    $conn->set_charset('utf8mb4');
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