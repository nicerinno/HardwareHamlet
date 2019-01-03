<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 03/01/2019
 * Time: 17:57
 */

class Medals implements JsonSerializable
{
    private $medal_id;
    private $name;
    private $icon;
    private $amount_likes;

    /**
     * Medals constructor.
     * @param $medal_id
     * @param $name
     * @param $icon
     * @param $amount_likes
     */
    public function __construct($medal_id, $name, $icon, $amount_likes)
    {
        $this->medal_id = $medal_id;
        $this->name = $name;
        $this->icon = $icon;
        $this->amount_likes = $amount_likes;
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
    public function getName()
    {
        return $this->name;
    }

    /**
     * @return mixed
     */
    public function getIcon()
    {
        return $this->icon;
    }

    /**
     * @return mixed
     */
    public function getAmountLikes()
    {
        return $this->amount_likes;
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
            "medal_id" => $this->getMedalId(),
            "name" => $this->getName(),
            "icon" => $this->getIcon(),
            "amount_likes" => $this->getAmountLikes()

        ];
    }
}