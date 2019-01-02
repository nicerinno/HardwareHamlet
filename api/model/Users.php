<?php
/**
 * Created by PhpStorm.
 * Users: R_Rod
 * Date: 20/12/2018
 * Time: 23:38
 */



class Users implements JsonSerializable
{
    public $user_id;
    public $title_id;
    public $usertype_id;
    public $medal_id;
    public $email;
    public $username;
    private $password;
    public $description;
    public $reputation;
    public $active;
    public $validation_code;

    /**
     * Users constructor.
     * @param $user_id
     * @param $title_id
     * @param $usertype_id
     * @param $medal_id
     * @param $email
     * @param $username
     * @param $password
     * @param $description
     * @param $reputation
     * @param $active
     * @param $validation_code
     */
    public function __construct($user_id, $title_id, $usertype_id, $medal_id, $email, $username, $password, $description, $reputation, $active, $validation_code)
    {
        $this->user_id = $user_id;
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
    }

    /**
     * @return mixed
     */
    public function getUserId()
    {
        return $this->user_id;
    }

    /**
     * @return mixed
     */
    public function getTitleId()
    {
        return $this->title_id;
    }

    /**
     * @return mixed
     */
    public function getUsertypeId()
    {
        return $this->usertype_id;
    }

    /**
     * @return mixed
     */
    public function getMedalId()
    {
        return $this->medal_id;
    }

    /**
     * @return mixed
     */
    public function getEmail()
    {
        return $this->email;
    }

    /**
     * @return mixed
     */
    public function getUsername()
    {
        return $this->username;
    }

    /**
     * @return mixed
     */
    private function getPassword()
    {
        return $this->password;
    }

    /**
     * @return mixed
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @return mixed
     */
    public function getReputation()
    {
        return $this->reputation;
    }

    /**
     * @return mixed
     */
    public function getActive()
    {
        return $this->active;
    }

    /**
     * @return mixed
     */
    public function getValidationCode()
    {
        return $this->validation_code;
    }


    /**
     * Specify data which should be serialized to JSON
     * @link https://php.net/manual/en/jsonserializable.jsonserialize.php
     * @return mixed data which can be serialized by <b>json_encode</b>,
     * which is a value of any type other than a resource.
     * @since 5.4.0
     */
    public function jsonSerialize()
    {
        return [
            [
                'user_id' => $this->getUserId(),
                'title_id' => $this->getTitleId(),
                'usertype_id' => $this->getUsertypeId(),
                'medal_id' => $this->getMedalId(),
                'email' => $this->getEmail(),
                'username' => $this->getUsername(),
                'password' => $this->getPassword(),
                'description' => $this->getDescription(),
                'reputation' => $this->getReputation(),
                'active' => $this->getActive(),
                'validation_code' => $this->getValidationCode()
            ]

        ];
    }
}