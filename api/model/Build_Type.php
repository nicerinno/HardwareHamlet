<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 03/01/2019
 * Time: 17:28
 */

class Build_Type implements JsonSerializable
{
    private $build_type_id;
    private $name;

    /**
     * Build_Type constructor.
     * @param $build_type_id
     * @param $name
     */
    public function __construct($build_type_id, $name)
    {
        $this->build_type_id = $build_type_id;
        $this->name = $name;
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
     * Specify data which should be serialized to JSON
     * @link https://php.net/manual/en/jsonserializable.jsonserialize.php
     * @return mixed data which can be serialized by <b>json_encode</b>,
     * which is a value of any type other than a resource.
     * @since 5.4.0
     */
    public function jsonSerialize()
    {
        // TODO: Implement jsonSerialize() method.

        return[
            "build_type_id" => $this->getBuildTypeId(),
            "name" => $this->getName()
        ];
    }
}