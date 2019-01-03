<?php

if(isset($_POST['name']) && isset($_POST['food'])) {

    $arr = array($_POST['name'], $_POST['food']);
    $data = serialize($arr);
    echo $data;
}

if(isset($_GET['invite'])) {
	$invite = $_GET['invite'];
	$regex = "^/muffinCTF\{[a-z0-9]{40}\}$/"
        if (preg_match($regex, $invite, $matches) !== FALSE)
        {
                if (count($matches) > 0)
                {
    			$invite_content = $invite;
    			$invite_name = md5($invite_content);
    			file_put_contents('../invitation/'.$invite_name, $invite_content);
		}
	}
    die();
}
