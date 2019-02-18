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
    public $component_type_id, $user_id, $brand, $name, $descripion, $price, $flg_available, $icon_url, $regist_date, $conn;

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

        $result = $this->conn->query($sql);

        endConnDB($this->conn);

        return $result;
    }

    public function query_components()
    {
        $sql = "SELECT  components.component_id, components.name, components.price, components.icon_url FROM components";
        $result = $this->conn->query($sql);
        endConnDB($this->conn);
        return $result;
    }

    public function query_component_type($componentType)
    {
        $sql = "SELECT components.name, components.price, components.icon_url FROM components WHERE components.component_type_id=" . $componentType;
        $result = $this->conn->query($sql);
        endConnDB($this->conn);
        return $result;
    }

    function query_component_id($id)
    {
        $sql = "SELECT * FROM components WHERE component_id='$id'";
        $result = $this->conn->query($sql);

        $fetch =  $result->fetch_assoc();
        endConnDB($this->conn);
        return $fetch;
    }

    function query_top_components()
    {
        $data = [];
        $sql = "select component_id, count(component_id) as count FROM build_components GROUP BY component_id order by count(component_id) desc limit 5";
        $query = $this->conn->query($sql);
        if ($query->num_rows > 0) {
//            while ($row = $query->fetch_assoc()) {
//                $id = $row['component_id'];
//                $sql2 = "SELECT * FROM components WHERE component_id = '$id'";
//                $component = $this->conn->query($sql2)->fetch_assoc();
//                $newComponent = new Components( $component['component_type_id'], $component['user_id'], $component['brand']
//                    , $component['name'], $component['description'], $component['price'], $component['flg_available'], $component['icon_url'], $component['regist_date']);
//
//                array_push($data, $newComponent);
//
//
//            }
            endConnDB($this->conn);
return $query;
        }


    }
}