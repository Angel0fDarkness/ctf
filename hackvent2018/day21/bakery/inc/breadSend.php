<?php

# need to ping addresses
if(isset($_GET['ip'])) {
	if (filter_var($_GET['ip'], FILTER_VALIDATE_IP))
	{
		echo "is IP";
		system('ping -c 1 ' . $_GET['ip']);
	} else {
		$amount = rand(5, 78);
		for ($i = 0; $i < $amount; $i++)
		{
			echo "muffinCTF{" . sha1(rand()) . "}\n";
 			usleep(rand(1000, 50000));
		}
	}
  die();
}

?>
