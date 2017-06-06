<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>To Do List</title>

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>
    <link href="https://fonts.googleapis.com/css?family=Cookie|Raleway" rel="stylesheet">
    
    <!-- Theme CSS -->
   	<style type="text/css">
        <%@include file="css/agency.css"%>
        <%@include file="vendor/bootstrap/css/bootstrap.min.css"%>
    </style>

</head>
<body id="page-top" class="index">

    <!-- Navigation -->
    <nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top">ToDoList</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a class="page-scroll" href="Login.jsp">Sair</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

    <!-- Criar nova categoria -->
    <section id="nova_categoria" class="bg-light-gray">
        <div class="container text-center">
            <h2 class="section-heading">Criar Nova Categoria</h2>
            <form method='get' enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control" size="50" name="nome_categoria" placeholder="Nome da categoria" required>
            </div>
            <div class="form-group">
                  <input list="postit" class="form-control" name="postit_color" placeholder="Post It" required>
                  <datalist id="postit">
                        <option value="Rosa">
                        <option value="Verde">
                        <option value="Amarelo">
                        <option value="Laranja">
                        <option value="Roxo">
                  </datalist>
            </div>
            <input type="submit" class="btn btn-primary-1" data-dismiss="modal" name="nova_categoria" value="Nova Categoria"></input>
            </form>                    
        </div>
    </section>

    <footer>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <span class="copyright">Copyright &copy; To Do List 2017</span>
                </div>
                <div class="col-md-4">
                </div>
                <div class="col-md-4">
                </div>
            </div>
        </div>
    </footer>
    
    <script type="text/javascript">
    	<%@include file="js/agency.min.js"%>
    	<%@include file="vendor/jquery/jquery.min.js"%>
    	<%@include file="vendor/bootstrap/js/bootstrap.min.js"%>
    	<%@include file="js/jqBootstrapValidation.js"%>
    	<%@include file="js/contact_me.js"%>  
    </script>

    </body>
</html>