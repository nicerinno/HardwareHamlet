<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 03/01/2019
 * Time: 17:58
 */

class Titles implements JsonSerializable
{
    private $title_id;
    private $name;
    private $rep_amount;
    private $color;

    /**
     * Titles constructor.
     * @param $title_id
     * @param $name
     * @param $rep_amount
     * @param $color
     */
    public function __construct($title_id, $name, $rep_amount, $color)
    {
        $this->title_id = $title_id;
        $this->name = $name;
        $this->rep_amount = $rep_amount;
        $this->color = $color;
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
    public function getName()
    {
        return $this->name;
    }

    /**
     * @return mixed
     */
    public function getRepAmount()
    {
        return $this->rep_amount;
    }

    /**
     * @return mixed
     */
    public function getColor()
    {
        return $this->color;
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
            "title_id" => $this->getTitleId(),
            "name" => $this->getName(),
            "rep_amount" => $this->getRepAmount(),
            "color" => $this->getColor()
        ];
    }
}