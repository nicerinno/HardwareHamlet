<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 26/12/2018
 * Time: 00:48
 */

class Builds implements JsonSerializable
{
    private $build_id;
    private $user_id;
    private $build_type_id;
    private $build_name;
    private $description;
    private $price;
    private $likes;
    private $regist_date;

    /**
     * Builds constructor.
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
    public function __construct($build_id, $user_id, $build_type_id, $build_name, $description, $price, $likes, $regist_date)
    {
        $this->build_id = $build_id;
        $this->user_id = $user_id;
        $this->build_type_id = $build_type_id;
        $this->build_name = $build_name;
        $this->description = $description;
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

                'build' => array(
                    'build_id' => $this->getBuildId(),
                    'user_id' => $this->getUserId(),
                    'build_type_id' => $this->getBuildTypeId(),
                    'build_name' => $this->getBuildName(),
                    'description' => $this->getDescription(),
                    'price' => $this->getPrice(),
                    'likes' => $this->getLikes(),
                    'regist_date' => $this->getRegistDate()
                ),
                'components' => $this->getComponentsFromBuild($this->getBuildId()),
                'comments' => $this->getComments($this->getBuildId())
            );
    }

    public function getComponentsFromBuild($buildId){
        $componentsArray = array();
        $conn = connDB();
        $components = "SELECT * FROM build_components WHERE build_id = $buildId";
        $componentResult = $conn->query($components);
        if($componentResult->num_rows >0){
            while($row = $componentResult->fetch_assoc()){
                array_push($componentsArray, $newComponent = new Build_Components($row['build_id'],$row['component_id'],$row['quantity']));
            }
        }
        endConnDB($conn);
        return $componentsArray;
    }

    public function getComments($build_id){
        $commentsArray = array();
        $conn = connDB();
        $sqlComments = "SELECT * FROM comments WHERE build_id = $build_id";
        $sqlCommentsResult = $conn->query($sqlComments);
        if($sqlCommentsResult->num_rows > 0){
            while($row = $sqlCommentsResult->fetch_assoc()){
                array_push($commentsArray, $newComment = new Comments($row['comment_id'],$build_id,$row['content'],$row['user_id'],$row['regist_date']));
            }
        }
        endConnDB($conn);
        return $commentsArray;
    }

}