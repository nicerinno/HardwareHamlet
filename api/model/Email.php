<?php
/**
 * Created by PhpStorm.
 * Users: R_Rod
 * Date: 21/12/2018
 * Time: 01:07
 */
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

require_once(__DIR__ . '/PHPMailer/PHPMailer/src/Exception.php');
require_once(__DIR__ . '/PHPMailer/PHPMailer/src/PHPMailer.php');
require_once(__DIR__ . '/PHPMailer/PHPMailer/src/SMTP.php');
class Email
{
    private $userEmail, $username, $validationCode;

    /**
     * Email constructor.
     * @param $userEmail
     * @param $validationCode
     */
    public function __construct($userEmail, $username, $validationCode)
    {
        $this->userEmail = $userEmail;
        $this->validationCode = $validationCode;
        $this->username = $username;
    }

    public function sendEmail(){
        $mail = new PHPMailer(false);
        $mail->isSMTP();

//Enable SMTP debugging
// 0 = off (for production use)
// 1 = client messages
// 2 = client and server messages
       $mail->SMTPDebug = 0;
//Set the hostname of the mail server
        $mail->Host = 'smtp.gmail.com';
// use
 //$mail->Host = gethostbyname('smtp.gmail.com');
// if your network does not support SMTP over IPv6
//Set the SMTP port number - 587 for authenticated TLS, a.k.a. RFC4409 SMTP submission
        $mail->Port = 587;
//Set the encryption system to use - ssl (deprecated) or tls
        $mail->SMTPSecure = 'tls';
//Whether to use SMTP authentication
        $mail->SMTPAuth = true;

        $mail->Username = "hardwarehamlet.mail@gmail.com";
        $mail->Password = "hardwarehamlet.arm";

        try {
            $mail->setFrom('hardwarehamlet.mail@gmail.com', 'HardwareHamlet');
        } catch (Exception $e) {
        }
        $mail->addReplyTo('hardwarehamlet.mail@gmail.com', 'HardwareHamlet');
        $mail->addAddress($this->userEmail , $this->username);
        $mail->isHTML(true);
        $mail->WordWrap = 50;
        $mail->Subject = 'Welcome to HardwareHamlet! Activate your account ' . $this->username . '!';
        $mail->Body= "
<h2>Welcome to HardwareHamlet!</h2>
<p>We're very pleased to welcome you to our hamlet. But in order to continue, you need to enter the validation code below when requested: </p>
<h3>" . $this->validationCode . "</h3>
<p>We'll see you there. Cheers!</p>
<h4>The HardwareHamlet Team.</h4>";
        $mail->AltBody = 'Welcome to Hardware Hamlet! In order to officialy join our hamlet, 
        you need to confirm your account by entering this code when requested: ' . $this->validationCode . ". Cheers! The HardwareHamlet Team.";
//        try{
//            $mail->send();
//            echo 'Message sent successully';
//        } catch(Exception $e){
//            echo 'Message could not be sent. Error: ' . $mail->ErrorInfo;
//        }

        try {
            if (!$mail->send()) {
                echo "Mailer Error: " . $mail->ErrorInfo;
            } else {
                echo "Message sent!";
            }
        } catch (Exception $e) {
        }

    }

}