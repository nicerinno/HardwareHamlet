<?php
/**
 * Created by PhpStorm.
 * Users: R_Rod
 * Date: 20/12/2018
 * Time: 22:53
 */


class Component implements JsonSerializable
{
    public $component_id;
    public $component_type_id;
    public $user_id;
    public $brand;
    public $name;
    public $description;
    public $price;
    public $flg_available;
    public $icon_url;

    /**
     * Component constructor.
     * @param $component_id
     * @param $component_type_id
     * @param $user_id
     * @param $brand
     * @param $name
     * @param $description
     * @param $price
     * @param $flg_available
     * @param $icon_url
     */
    public function __construct($component_id, $component_type_id, $user_id, $brand, $name, $description, $price, $flg_available, $icon_url)
    {
        $this->component_id = $component_id;
        $this->component_type_id = $component_type_id;
        $this->user_id = $user_id;
        $this->brand = $brand;
        $this->name = $name;
        $this->description = $description;
        $this->price = $price;
        $this->flg_available = $flg_available;
        $this->icon_url = $icon_url;
    }

    /**
     * @return mixed
     */
    public function getComponentId()
    {
        return $this->component_id;
    }

    /**
     * @return mixed
     */
    public function getComponentTypeId()
    {
        return $this->component_type_id;
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
    public function getBrand()
    {
        return $this->brand;
    }

    /**
     * @return mixed
     */
    public function getName()
    {
        return $this->name;
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
    public function getPrice()
    {
        return $this->price;
    }

    /**
     * @return mixed
     */
    public function getFlgAvailable()
    {
        return $this->flg_available;
    }

    /**
     * @return mixed
     */
    public function getIconUrl()
    {

        return $this->icon_url;
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
        // TODO: Implement jsonSerialize() method.
        return
            [
                'component_id' => $this->getComponentId(),
                'component_type_id' => $this->getComponentTypeId(),
                'user_id' => $this->getUserId(),
                'brand' => $this->getBrand(),
                'name' => $this->getName(),
                'description' => $this->getDescription(),
                'price' => $this->getPrice(),
                'flg_available' => $this->getFlgAvailable(),
                'icon_url' => $this->getIconUrl()
            ];
    }
}