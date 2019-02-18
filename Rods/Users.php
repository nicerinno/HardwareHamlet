<?php
/**
 * Created by PhpStorm.
 * User: Mário
 * Date: 22/01/2019
 * Time: 18:45
 */

include_once "connection.php";

include_once (__DIR__ . "/model/PHPMailer/PHPMailer/src/PHPMailer.php");
include_once (__DIR__ . "/model/PHPMailer/PHPMailer/src/Exception.php");
include_once (__DIR__ . "/model/PHPMailer/PHPMailer/src/SMTP.php");
//hope it works
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

//server escola
//require '/var/www/html/api/composer/vendor/autoload.php';
require_once (__DIR__ . "/composer/vendor/autoload.php");

class Users
{
    public  $title_id, $usertype_id, $medal_id, $email, $username, $password, $description, $reputation, $active, $validation_code, $regist_date, $conn;


    public function __construct($title_id, $usertype_id, $medal_id, $email, $username, $password, $description, $reputation, $active, $validation_code, $regist_date)
    {

        $this->title_id = $title_id;
        $this->usertype_id = $usertype_id;
        $this->medal_id = $medal_id;
        $this->email = $email;
        $this->username = $username;
        $this->password = $password;
        $this->description = $description;
        $this->reputation = $reputation;
        $this->active = $active;
        $this->validation_code = $validation_code;
        $this->regist_date = $regist_date;
        $this->conn = connDB();
    }

    public function endConnDB ()
    {
        $this->conn->close();
    }


    public function register()
    {
        $data ="";
        $sql = "SELECT * FROM users WHERE username='$this->username'";
        $check = $this->conn->query($sql);
        $mt = explode(' ', microtime());
        $time_milis = ((int)$mt[1]) * 1000 + ((int)round($mt[0] * 1000));
        //checking if there is another user with the username the user typed
        $data = [];
        if($check->num_rows == 0){

            $sql2 = "SELECT * FROM users WHERE email='$this->email'";
            $checkEmail = $this->conn->query($sql2);
            if($checkEmail->num_rows == 0){
                //password hashing
                $passwordHash = password_hash($this->password,PASSWORD_BCRYPT);
                //defining the length of the validation code
                $length = 6;

                //creating the validation code wich will be sent by email i hope
                $validation_code = substr(str_shuffle("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"), 0, $length);
                $sql3 = "INSERT INTO users (title_id, usertype_id, medal_id, email, username, password, description, reputation, active, validation_code, regist_date) 
VALUES('1', '1', '1', '$this->email', '$this->username', '$passwordHash', 'No description yet.', '0', false, '$validation_code','$time_milis')";
                $regist = $this->conn->query($sql3);
                if($regist){
                    try{
                        $mail = new PHPMailer(true);
                        $mail->isSMTP();

                        $mail->SMTPOptions = array(
                            'ssl' => array(
                                'verify_peer' => false,
                                'verify_peer_name' => false,
                                'allow_self_signed' => true
                            )
                        );
//Enable SMTP debugging
// 0 = off (for production use)
// 1 = client messages
// 2 = client and server messages
                        $mail->SMTPDebug = 0;
//Set the hostname of the mail server
                        $mail->Host = 'tls://smtp.gmail.com:587';
// use
                        $mail->Username = "hardwarehamlet.mail@gmail.com";
                        $mail->Password = "hardwarehamlet.arm";
                        //$mail->Host = gethostbyname('smtp.gmail.com');
// if your network does not support SMTP over IPv6
//Set the SMTP port number - 587 for authenticated TLS, a.k.a. RFC4409 SMTP submission
                        $mail->Port = 587;
//Set the encryption system to use - ssl (deprecated) or tls
                        $mail->SMTPSecure = 'tls';
//Whether to use SMTP authentication
                        $mail->SMTPAuth = true;
                        $mail->SMTPAutoTLS = false;

                        $mail->setFrom('hardwarehamlet.mail@gmail.com', 'HardwareHamlet');
                        $mail->addCC('hardwarehamlet.mail@gmail.com');
                        $mail->addBCC('hardwarehamlet.mail@gmail.com');
                        $mail->addReplyTo('hardwarehamlet.mail@gmail.com', 'HardwareHamlet');
                        $mail->addAddress($this->email , $this->username);
                        $mail->isHTML(true);
                        $mail->WordWrap = 50;
                        $mail->Subject = 'Welcome to HardwareHamlet! Activate your account ' . $this->username . '!';
                        $mail->msgHTML("
<h2>Bem vindo ao HardwareHamlet!</h2>
<p>É com grande entusiasmo que te damos as boas vindas à nossa comunidade! Para que possas usufruir dos nossos serviços em toda a sua extensão,
confirma o teu introduzindo este código quando solicitado:</p>
<h3>$validation_code</h3>
<p>Vemo-nos lá!</p>
<h4>A Equipa do HardwareHamlet</h4>");
                        $mail->AltBody = 'Welcome to Hardware Hamlet! In order to officialy join our hamlet, 
        you need to confirm your account by entering this code when requested: ' . $validation_code . ". Cheers! The HardwareHamlet Team.";
                        if($mail->send()){
                            $data = "Registo efectuado com sucesso. Confirma o teu registo no seu email.";
                        }else $data = "Algo correu mal";
                    }
                    catch(Exception $e){
                        $data = "Algo correu mal";
                    }
                } else{
                    //json response body failure
                    $data = "Algo correu mal";
                }
            }else{
                $data = "Este email já existe";
            }
        } else {
            $data = "Este username já existe";
        }

        return $data;
    }

    public function create_session()
    {
        session_start();
        $_SESSION['email']= $this->email;
    }

    public function check_session()
    {
        if(empty($_SESSION))
            return false;
        else return true;
    }

    public function close_session()
    {
        if(empty($_SESSION))
            return false;
        else
        {
            session_destroy();
            return true;
        }
    }

    public function getIdUserByUsername(){
        $sql = "SELECT user_id FROM users WHERE email = '$this->email'";
        $runSql = $this->conn->query($sql);
        $this->endConnDB();

        $user_id = $runSql->fetch_assoc();

        return $user_id['user_id'];
    }


    public function passVerification()
    {
        $sql2 = "SELECT password FROM users WHERE username = '$this->username' or email='$this->email'";
        $result = $this->conn->query($sql2);
        $this->endConnDB();
        $hashedP = $result->fetch_assoc();
        $verify = password_verify($this->password,$hashedP['password']);

        if($verify)
        {
            $this->create_session();
        }
        return $verify;
    }

    function query_user_rank(){
        $sql = "SELECT * FROM users order by reputation desc limit 5";
        $result = $this->conn->query($sql);
        endConnDB($this->conn);
        return $result;

    }

    function query_title_by_id()
    {
        $sql = "SELECT name FROM titles WHERE title_id = $this->title_id";
        $result = $this->conn->query($sql);
        $result2 = $result->fetch_assoc();
        endConnDB($this->conn);
        return $result2['name'];

    }

    public function validateAccount(){
        $sql = "SELECT validation_code FROM users WHERE email = '$this->email'";
        $runSql = $this->conn->query($sql);
        $this->close_session();

        $validation_code1 = $runSql->fetch_assoc();

        if($validation_code1 == $this->validation_code){
            $sql5 = "UPDATE users SET active = 1 WHERE email = '$this->email'";
            $this->conn->query($sql5);

            $this->create_session();
            $this->endConnDB();
            return "Conta validada com sucesso";
        }else{
            return "Código errado. Tente novamente";
        }
    }

}