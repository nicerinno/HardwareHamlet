<?php
/**
 * Created by PhpStorm.
 * User: Mário
 * Date: 20/01/2019
 * Time: 01:08
 */
include_once "connection.php";

class Components
{
    public  $component_type_id, $user_id, $brand, $name, $descripion, $price, $flg_available, $icon_url, $regist_date, $conn;

    /**
     * Components constructor.
     * @param $component_type_id
     * @param $user_id
     * @param $brand
     * @param $name
     * @param $descripion
     * @param $price
     * @param $flg_available
     * @param $icon_url
     * @param $regist_date
     */
    public function __construct($component_type_id, $user_id, $brand, $name, $descripion, $price, $flg_available, $icon_url, $regist_date)
    {
        $this->component_type_id = $component_type_id;
        $this->user_id = $user_id;
        $this->brand = $brand;
        $this->name = $name;
        $this->descripion = $descripion;
        $this->price = $price;
        $this->flg_available = $flg_available;
        $this->icon_url = $icon_url;
        $this->regist_date = $regist_date;
        $this->conn = connDB();
    }

    public function addComponent()
    {
        $sql = "INSERT INTO components(component_type_id, user_id, brand, name, description, price, flg_available, icon_url, regist_date) VALUES ('$this->component_type_id',
'$this->user_id', '$this->brand','$this->name','$this->descripion','$this->price','$this->flg_available','$this->icon_url','$this->regist_date')";
        var_dump($sql);
        $result = $this->conn->query($sql);
        var_dump($result);
        endConnDB($this->conn);

        return $result;
    }

    public function query_components()
    {
        $sql = "SELECT components.name, components.price, components.icon_url FROM components";
        $result = $this->conn->query($sql);
        endConnDB($this->conn);
        return $result;
    }

    public function query_component_type($componentType)
    {
        $sql = "SELECT components.name, components.price, components.icon_url FROM components WHERE components.component_type_id=".$componentType;
        $result = $this->conn->query($sql);
        endConnDB($this->conn);
        return $result;
    }


}