<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>
<head>
  <title>Port</title>
  <link rel="stylesheet" type="text/css" href="css/semantic.min.css">
  <script src="js/jquery.min.js"></script>
  <script src="js/semantic.min.js"></script>
</head>
<body>
<div class="ui visible left demo vertical inverted sidebar labeled icon menu">
  <a class="item" onclick="$('#viewport').load('html/about.html');">
    <i class="home icon"></i>
    Home
  </a>
  <a class="item" onclick="$('#viewport').load('html/ships.html');">
    <i class="ship icon"></i>
    Shipstorage
  </a>
  <a class="item" onclick="$('#viewport').load('html/search.html');">
    <i class="search icon"></i>
    Portname
  </a>
</div>
<div id="viewport" style="margin-left:120px; margin-top:20px;"></div>
<script>
    $('#viewport').load('html/about.html');
</script>
</body>
</html>