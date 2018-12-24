<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 20/12/2018
 * Time: 23:38
 */

include_once "db.php";

class User
{
    public $email,$username,$password,$connm,$validation_code;

    /**
     * User constructor.
     * @param $email
     * @param $username
     * @param $password
     * @param $description
     */
    public function __construct($email, $username, $password)
    {
        $this->email = $email;
        $this->username = $username;
        $this->password = $password;

        $this->conn = connDB();
    }

    public function getValidationCode(){


        return  $this->validation_code;
    }

    public function userRegistration(){
        //password hashing
        $passwordHash = password_hash($this->password,PASSWORD_BCRYPT);
        //defining the length of the validation code
        $length = 6;

        //creating the validation code wich will be sent by email
        $this->validation_code = substr(str_shuffle("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"), 0, $length);
        $sql = "INSERT INTO users VALUES('',1,1,1,'$this->email','$this->username','$passwordHash','empty',1,0,'$this->validation_code')";
        $result = $this->conn->query($sql);
        endConnDB($this->conn);
        return $result;

    }



    public function checkUserAvailability(){
        $sql = "SELECT * FROM users WHERE username='$this->username'";
        $result = $this->conn->query($sql);
        endConnDB($this->conn);
        return $result;
    }

}