<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 26/12/2018
 * Time: 00:48
 */

class Build implements JsonSerializable
{
    public $build_id;
    public $user_id;
    public $build_type_id;
    public $name;
    public $description;
    public $cpu_description;
    public $gpu_description;
    public $ram_description;
    public $price;
    public $likes;

    /**
     * Build constructor.
     * @param $build_id
     * @param $user_id
     * @param $buildtype_id
     * @param $name
     * @param $description
     * @param $cpu_description
     * @param $gpu_description
     * @param $ram_description
     * @param $price
     * @param $likes
     */
    public function __construct($build_id, $user_id, $build_type_id, $name, $description, $cpu_description, $gpu_description, $ram_description, $price, $likes)
    {
        $this->build_id = $build_id;
        $this->user_id = $user_id;
        $this->build_type_id = $build_type_id;
        $this->name = $name;
        $this->description = $description;
        $this->cpu_description = $cpu_description;
        $this->gpu_description = $gpu_description;
        $this->ram_description = $ram_description;
        $this->price = $price;
        $this->likes = $likes;
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

    public function jsonSerialize()
    {
        // TODO: Implement jsonSerialize() method.
        return
            [
                'build_id' => $this->getBuildId(),
                'user_id' => $this->getUserId(),
                'build_type_id' => $this->getBuildTypeId(),
                'name' => $this->getName(),
                'description' => $this->getDescription(),
                'cpu_description' => $this->getCpuDescription(),
                'gpu_description' => $this->getGpuDescription(),
                'ram_description' => $this->getRamDescription(),
                'price' => $this->getPrice(),
                'likes' => $this->getLikes()
            ];
    }


}