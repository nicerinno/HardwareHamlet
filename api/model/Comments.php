<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 30/12/2018
 * Time: 19:50
 */


/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 30/12/2018
 * Time: 19:34
 */

class Comments implements JsonSerializable
{
    private $comment_id;
    private $build_id;
    private $content;
    private $user_id;

    /**
     * Comments constructor.
     * @param $comment_id
     * @param $build_id
     * @param $content
     * @param $user_id
     */
    public function __construct($comment_id, $build_id, $content, $user_id)
    {
        $this->comment_id = $comment_id;
        $this->build_id = $build_id;
        $this->content = $content;
        $this->user_id = $user_id;
    }

    /**
     * @return mixed
     */
    public function getCommentId()
    {
        return $this->comment_id;
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
    public function getContent()
    {
        return $this->content;
    }

    /**
     * @return mixed
     */
    public function getUserId()
    {
        return $this->user_id;
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
            'comment_id' => $this->getCommentId(),
            'user_id' => $this->getUserId(),
            'build_id' => $this->getBuildId(),
            'content' => $this->getContent()
        ];
    }
}