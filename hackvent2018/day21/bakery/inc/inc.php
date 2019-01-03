<?php

	function startsWith($haystack, $needle)
	{
		$length = strlen($needle);
		return (substr($haystack, 0, $length) == $needle);
	}

	# needs to include the needed pages
	if(isset($_GET['page'])) {
		$page = $_GET['page'];
		if (startsWith($page, "breads.php")
		||  startsWith($page, "breadSend.php")
		|| ($page === "../html/home.html")
		|| ($page === "../html/breads.html")
		|| ($page === "../html/breadSend.html"))
		{
			include($page);
		} else {
			$amount = rand(5, 78);
			for ($i = 0; $i < $amount; $i++)
			{
				echo "muffinCTF{" . sha1(rand()) . "}\n";
				usleep(rand(1000, 50000));
			}
		}
	}

?>
