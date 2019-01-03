<?php

include "EvalMath.php";
include "Stack.php";


# calculations need to be possible, examples:
# (23+64)
# 123*3
# 321-122
if(isset($_GET['prize'])) {
	$regex = "/^[0-9.,\/*+\-() ]+$/";
	if (preg_match($regex, $_GET['prize'], $matches) !== FALSE)
	{
		if (count($matches) > 0)
		{
			$m = new EvalMath;
			$m->suppress_errors = true;
			$result = $m->evaluate($_GET['prize']);
			echo number_format($result * 1.20, 2);
			die();
		}
	}

	$amount = rand(5, 78);
	for ($i = 0; $i < $amount; $i++)
	{
		echo "muffinCTF{" . sha1(rand()) . "}\n";
		usleep(rand(1000, 50000));
	}
	die();
}


// flag deployment
if(isset($_GET['bread'])) {
	$bread_content = $_GET['bread'];
	$regex = "/^muffinCTF\{[a-z0-9]{40}\}$/";
	if (preg_match($regex, $bread_content, $matches) !== FALSE)
	{
		if (count($matches) > 0)
		{
			$bread_name = md5($bread_content);
			file_put_contents('../breads/'.$bread_name, $bread_content);
		}
	}

	die();
}

?>
