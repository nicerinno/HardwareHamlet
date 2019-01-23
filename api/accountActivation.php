<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 07/01/2019
 * Time: 15:27
 */

include_once (__DIR__ . "/model/Users.php");
include_once "db.php";
//include_once "accountActivated.html";

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
        echo "<!DOCTYPE html>
<html lang=\"en\">
<head>
    <meta charset=\"UTF-8\">
    <title>Title</title>
</head>
<body>
<h1>CONTA ACTIVADA</h1>
<p>Parabéns! Activaste a tua conta com sucesso. Serás redireccionado/a para a nossa página principal em alguns segundos.</p>
<p>Caso demore muito tempo, podes clicar <a href='accountActivated.html'>aqui</a></p>
</body>
</html>
";
        redirect();
    }
}

function redirect(){
sleep(5);
header('Location: accountActivated.html');
}
?>



