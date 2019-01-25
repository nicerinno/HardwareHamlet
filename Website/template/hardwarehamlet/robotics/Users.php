<?php
/**
 * Created by PhpStorm.
 * User: Mário
 * Date: 22/01/2019
 * Time: 18:45
 */

include_once "connection.php";

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
        $sql = "INSERT INTO users(title_id,usertype_id, medal_id, email, username, password, description, reputation, active, validation_code, regist_date) VALUES
('1', '1', '1', '$this->email', '$this->username', '$this->password', 'No description yet.', '0', '0','$this->validation_code', '$this->regist_date')";
        $result = $this->conn->query($sql);

        $this->endConnDB();
        var_dump($result);
        return $result;


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

    function  query_user_rank(){
        $sql = "SELECT * FROM users order by reputation desc limit 5";
        $result = $this->conn->query($sql);
        endConnDB($this->conn);
        return $result;

    }

    function  query_title_by_id()
    {
        $sql = "SELECT name FROM titles WHERE title_id = $this->title_id";
        $result = $this->conn->query($sql);
        $result2 = $result->fetch_assoc();
        endConnDB($this->conn);
        return $result2['name'];

    }










}