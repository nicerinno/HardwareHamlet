<?php

include_once "connection.php";

?>

<?php

    $email = $_POST['email'];
    $password = $_POST['password'];
    $sql = "SELECT * FROM users WHERE email = '$email' AND password='$password'";
    $row = mysql_num_rows($sql);
    if( $row > 0){
        session_start();
        $_SESSION['email'] = $_POST['email'];
        $_SESSION['password'] = $_POST['password'];
        echo "autenticado";

    }





