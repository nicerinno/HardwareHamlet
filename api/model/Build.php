<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 26/12/2018
 * Time: 00:48
 */
include_once "C:\xampp\htdocs\hhapi\db.php";
class Build implements JsonSerializable
{
    private $build_id;
    private $user_id;
    private $build_type_id;
    private $build_name;
    private $description;
    private $cpu_description;
    private $gpu_description;
    private $ram_description;
    private $price;
    private $likes;
    private $regist_date;

    /**
     * Build constructor.
     * @param $build_id
     * @param $user_id
     * @param $build_type_id
     * @param $build_name
     * @param $description
     * @param $cpu_description
     * @param $gpu_description
     * @param $ram_description
     * @param $price
     * @param $likes
     * @param $regist_date
     */
    public function __construct($build_id, $user_id, $build_type_id, $build_name, $description, $cpu_description, $gpu_description, $ram_description, $price, $likes, $regist_date)
    {
        $this->build_id = $build_id;
        $this->user_id = $user_id;
        $this->build_type_id = $build_type_id;
        $this->build_name = $build_name;
        $this->description = $description;
        $this->cpu_description = $cpu_description;
        $this->gpu_description = $gpu_description;
        $this->ram_description = $ram_description;
        $this->price = $price;
        $this->likes = $likes;
        $this->regist_date = $regist_date;
    }

    /**
     * @return mixed
     */
    public function getBuildId()
    {
        return $this->build_id;
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
    public function getBuildTypeId()
    {
        return $this->build_type_id;
    }

    /**
     * @return mixed
     */
    public function getBuildName()
    {
        return $this->build_name;
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
    public function getCpuDescription()
    {
        return $this->cpu_description;
    }

    /**
     * @return mixed
     */
    public function getGpuDescription()
    {
        return $this->gpu_description;
    }

    /**
     * @return mixed
     */
    public function getRamDescription()
    {
        return $this->ram_description;
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
    public function getLikes()
    {
        return $this->likes;
    }

    /**
     * @return mixed
     */
    public function getRegistDate()
    {
        return $this->regist_date;
    }




    public function jsonSerialize()
    {
        return
            array(
                'build_id' => $this->getBuildId(),
                'username' => $this->getUserId(),
                'build_type_name' => $this->getBuildTypeId(),
                'build_name' => $this->getBuildName(),
                'description' => $this->getDescription(),
                'cpu_description' => $this->getCpuDescription(),
                'gpu_description' => $this->getGpuDescription(),
                'ram_description' => $this->getRamDescription(),
                'price' => $this->getPrice(),
                'likes' => $this->getLikes(),
                'regist_date' => $this->getRegistDate()
            );
    }

    public function getComponentsFromBuild($buildId){
        $componentsArray = array();
        $conn = connDB();
        $components = "SELECT * FROM components WHERE component_id IN (SELECT component_id FROM build_components WHERE build_id =" . $buildId .  ")";
        $componentResult = $conn->query($components);
        if($componentResult->num_rows >0){
            while($row = $componentResult->fetch_assoc()){
                array_push($componentsArray, $newComponent = new Component($row['component_id'],$row['component_type_id'],$row['user_id'],$row['brand'],$row['name'],$row['description'],$row['price'],$row['flg_available'],$row['icon_url'],$row['regist_date']));
            }
        }

        return $componentsArray;
    }
}