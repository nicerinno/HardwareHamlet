<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 31/12/2018
 * Time: 15:20
 */

class Build_Components implements JsonSerializable
{
    public $build_id;
    public $component_id;
    public $quantity;

    /**
     * Build_Components constructor.
     * @param $build_id
     * @param $component_id
     * @param $quantity
     */
    public function __construct($build_id, $component_id, $quantity)
    {
        $this->build_id = $build_id;
        $this->component_id = $component_id;
        $this->quantity = $quantity;
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
    public function getComponentId()
    {
        return $this->component_id;
    }

    /**
     * @return mixed
     */
    public function getQuantity()
    {
        return $this->quantity;
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
        return [
            'build_id' => $this->getBuildId(),
            'component_id' => $this->getComponentId(),
            'quantity' => $this->getQuantity()
        ];
    }
}