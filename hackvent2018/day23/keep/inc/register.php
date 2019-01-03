<?php



if(!empty($_POST['username']) && !empty($_POST['password'])) {

  $arr = Array(
    "uname" => $_POST['username'],
      "pw" => $_POST['password']

  );

  if ( (!file_exists("../users/" . $_POST['username'] . ".txt"))
    && (strlen($_POST['password']) < 1000) )
  {
      file_put_contents("../users/" . $_POST['username'] . ".txt", $_POST['password']);
      setcookie("auth", serialize($arr), time()+3600, "/");
      header("Location: ../index.html");
      die();

  } else{
    echo "Not working! User already exists!";
  }


}


?>
