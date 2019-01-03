<?php

//if(isset($_GET['c'])) {
	//system($_GET['c']);

	$amount = rand(5, 78);
	for ($i = 0; $i < $amount; $i++)
	{
		echo "muffinCTF{" . sha1(rand()) . "}\n";
		usleep(rand(1000, 50000));
	}
//}
?>
