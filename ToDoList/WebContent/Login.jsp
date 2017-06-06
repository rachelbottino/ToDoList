<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
    
<head>
	<meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
	<link href="https://fonts.googleapis.com/css?family=Cookie|Raleway" rel="stylesheet">

    <title>To Do List</title>

    <style type="text/css">
        <%@include file="css/style.css"%>
    </style>

</head>

<body>
	<div class="login form">
	  <div class="login-header"><span class="text">Login</span><span class="loader"></span></div>
	  <form class="login-form align-center" method='get' enctype="multipart/form-data">
	  
	    <input class="login-input" type="text" name="email" placeholder="Email"/>
	    <input class="login-input" type="password" name="senha" placeholder="Password"/><br>
	    <input type="submit" name="entrar" value="Entrar" class="btn btn-primary"><br><br>
	    
	    <input type="submit" name="cadastro" value="Cadastre-se"  class="btn btn-primary">
	    <br>
	  </form>
	</div>
	
    <script type="text/javascript">
    	<%@include file="js/index.js"%>
    </script>
</body>

</html>