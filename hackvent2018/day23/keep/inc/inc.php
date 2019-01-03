<?php

  # needs to include the needed pages
  if(isset($_GET['page'])) {
	if ( ($_GET['page'] == "../html/home.html")
	  || ($_GET['page'] == "../html/register.html")
	  || ($_GET['page'] == "../html/login.html")
	  || ($_GET['page'] == "../html/userinfo.html")
	  || ($_GET['page'] == "../html/dinner.html") )
	{
    		include($_GET['page']);
	}
  }

?>
