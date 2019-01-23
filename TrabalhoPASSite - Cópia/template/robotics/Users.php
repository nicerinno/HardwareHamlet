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
    public $title_id, $user_type_id, $medal_id, $email, $username, $password, $description, $reputation, $active, $validation_code, $regist_date, $conn;


    public function __construct($title_id, $user_type_id, $medal_id, $email, $username, $password, $description, $reputation, $active, $validation_code, $regist_date)
    {
        $this->title_id = $title_id;
        $this->user_type_id = $user_type_id;
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




}