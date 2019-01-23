<?php
/**
 * Created by PhpStorm.
 * User: R_Rod
 * Date: 12/01/2019
 * Time: 02:15
 */
$string = "{
	\"build\":{
		\"build_id\" : \"4\",
		\"user_id\" : \"2\",
		\"build_type_id\" : \"5\",
		\"build_name\" :\"asdasdsaddsa\",
		\"description\" : \"adssadsa\",
		\"cpu_description\" :\"saddas\",
		\"gpu_description\" :\"saddas\",
		\"ram_description\" :\"saddas\",
		\"price\" : \"0\",
		\"likes\" : \"0\",
		\"regist_date\" : \"0\"
		
	},
	\"components\" :[ {
		\"component_id\" : \"1\",
		\"quantity\" : \"1\"
	}]
}";

$input = json_decode($string);
$build = $input->components;
//$build_id = $build->build_id;
//var_dump($input);