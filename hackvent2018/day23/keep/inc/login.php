<?php

if(!empty($_POST['username']) && !empty($_POST['password'])) {

    $arr = Array(
        "uname" => $_POST['username'],
        "pw" => $_POST['password']

    );

    //if no cookie yet
    setcookie("auth", serialize($arr),  time()+3600, "/");

    if(file_exists("../users/" . $_POST['username'] . ".txt")) {
        $adminName = $_POST['username'];
        $adminPassword = file_get_contents("../users/" . $_POST['username'] . ".txt");

        //$x = serialize(array("admin", "changeme"));

        //echo $x;

        $data = unserialize($_COOKIE['auth'], FALSE);
        //$data = unserialize($x);
        //print_r($data);

        if ($data['uname'] == $adminName && $data['pw'] == $adminPassword) {
            $admin = true;
            echo "logged in";

            echo "\n\nYour Username is: ";
            @eval ("echo " . $data["uname"] . ";");
            echo "\n\nYour Password is: ";
            @eval ("echo " . $data["pw"] . ";");


            //System("echo 'hello " + $data['uname'] + "' 2>&1",$ret);
            //$last_line = passthru("echo 'hello {$data['uname']} ' 2>&1", $retval); //python
            //echo $last_line;
            //eval("\$name = \$data['uname'];");

            /*echo "Hello ";
            eval('echo $data["uname"];');
            echo "Your Password Is: ";*/

            //eval("$x =  $adminPassword;");
            //eval("$x = $adminPassword;");
            //echo $x;

            //allow for input

            //session
            //eval("echo $adminPassword");
            //eval("\$oldPw = \$adminPassword;");
            //echo $oldPw;

            //print("\nHello " . $name);
            //echo $ret;
        } else {
            $admin = false;
            echo "bad password";
            //remove cookie
        }
    }


}


/*
class Example1
{
    public $cache_file;

    function __construct()
    {
        // some PHP code...
    }

    function __destruct()
    {
        $file = "/var/www/cache/tmp/{$this->cache_file}";
        if (file_exists($file)) @unlink($file);
    }
}

// some PHP code...

$user_data = unserialize($_GET['data']);

// some PHP code...
*/
?>
