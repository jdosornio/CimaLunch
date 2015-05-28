<%-- 
    Document   : principalAlumno
    Created on : 27/05/2015, 08:37:05 PM
    Author     : Jesus Donaldo
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es"><head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>CimaLunch - Pagina principal</title>
        
        <script src="js/checkCookies.js"></script>
        
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="css/navbar.css" rel="stylesheet">
        <link href="css/estilo_1.css" rel="stylesheet">
    </head>

    <body>

        <%
            if (session.getAttribute("usuario") == null) {
                //go to index
                response.sendRedirect("index.jsp");
            }
        %>
        
        <div class="container">
            <!-- Static navbar -->
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand">CimaLunch</a>
                    </div>
                    <div style="height: 0.8px;" aria-expanded="false" id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li><a>Bienvenido Usuario!</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Mis Ordenes <span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li role="presentation" class="dropdown-header">
                                        <span>Orden 2</span>
                                    </li>
                                    <li><a href="#">Platillo 1 (Deli) </a></li>
                                    <li><a href="#">Bebida 1 (Deli) <span class="glyphicon glyphicon-ok"></span></a></li>
                                    <li class="divider"></li>
                                    <li role="presentation" class="dropdown-header">
                                        <span>Orden 1</span>
                                    </li>
                                    <li><a href="#">Platillo 1 (Deli) <span class="glyphicon glyphicon-ok"></span></a></li>
                                    <li><a href="#">Bebida 1 (Javis) <span class="glyphicon glyphicon-ok"></span></a></li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <button class="dropdown-toggle btn btn-default navbar-btn" data-toggle="dropdown" role="button"> 
                                    <span class="glyphicon glyphicon-shopping-cart"></span> 2 <span class="caret"></span></button>
                                <ul class="dropdown-menu dropdown-cart" role="menu">
                                    <li>
                                        <span class="item">
                                            <span class="item-left">
                                                <img src="http://lorempixel.com/50/50/" alt="" />
                                                <span class="item-info">
                                                    <span>Platillo 1</span>
                                                    <span>$19.50</span>
                                                </span>
                                            </span>
                                            <span class="item-right">
                                                <button class="btn btn-xs btn-danger pull-right">x</button>
                                            </span>
                                        </span>
                                    </li>
                                    <li>
                                        <span class="item">
                                            <span class="item-left">
                                                <img src="http://lorempixel.com/50/50/" alt="" />
                                                <span class="item-info">
                                                    <span>Bebida 1</span>
                                                    <span>$13.00</span>
                                                </span>
                                            </span>
                                            <span class="item-right">
                                                <button class="btn btn-xs btn-danger pull-right">x</button>
                                            </span>
                                        </span>
                                    </li>
                                    <li class="divider"></li>
                                    <li role="presentation" class="dropdown-header">Total: $32.50</li>
                                    <li class="divider"></li>
                                    <li><a class="text-center" href="">Confirmar Orden</a></li>
                                </ul>
                            </li>
                            <li><a href="#" onclick="signOut()">Cerrar Sesión</a></li>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div><!--/.container-fluid -->
            </nav>

            <aside class="negocios col-xs-12 col-sm-12 col-md-4 col-lg-4">
                <ul>
                    <li class="dropdown col-xs-4 col-sm-4 col-md-12 col-lg-12">
                        <button class="dropdown-toggle btn btn-default navbar-btn btn-lg" data-toggle="dropdown" role="button"> 
                            <span class="glyphicon glyphicon-lock">Negocio</span> <span class="caret"></span></button>
                        <ul class="dropdown-menu dropdown-cart" role="menu">
                            <li><img src="imagenes/logo1.jpg"><a href="#">Deli</a></li>
                            <li class="divider"></li>
                            <li><a href="#">Javis</a></li>
                        </ul>
                    </li>
                    <li class="dropdown col-xs-4 col-sm-4 col-md-12 col-lg-12">
                        <button class="dropdown-toggle btn btn-default navbar-btn btn-lg" data-toggle="dropdown" role="button"> 
                            <span class="glyphicon glyphicon-glass">Categoria</span> <span class="caret"></span></button>
                        <ul class="dropdown-menu dropdown-cart" role="menu">
                            <li role="presentation" class="dropdown-header">
                                <span>Orden 2</span>
                            </li>
                            <li><a href="#">Platillo 1 (Deli) </a></li>
                            <li><a href="#">Bebida 1 (Deli) <span class="glyphicon glyphicon-ok"></span></a></li>
                            <li class="divider"></li>
                            <li role="presentation" class="dropdown-header">
                                <span>Orden 1</span>
                            </li>
                            <li><a href="#">Platillo 1 (Deli) <span class="glyphicon glyphicon-ok"></span></a></li>
                            <li><a href="#">Bebida 1 (Javis) <span class="glyphicon glyphicon-ok"></span></a></li>
                        </ul>
                    </li>
                    <li class="dropdown col-xs-4 col-sm-4 col-md-12 col-lg-12">
                        <button class="dropdown-toggle btn btn-default navbar-btn btn-lg" data-toggle="dropdown" role="button"> 
                            <span class="glyphicon glyphicon-glass">Producto</span> <span class="caret"></span></button>
                        <ul class="dropdown-menu dropdown-cart" role="menu">
                            <li role="presentation" class="dropdown-header">
                                <span>Orden 2</span>
                            </li>
                            <li><a href="#">Platillo 1 (Deli) </a></li>
                            <li><a href="#">Bebida 1 (Deli) <span class="glyphicon glyphicon-ok"></span></a></li>
                            <li class="divider"></li>
                            <li role="presentation" class="dropdown-header">
                                <span>Orden 1</span>
                            </li>
                            <li><a href="#">Platillo 1 (Deli) <span class="glyphicon glyphicon-ok"></span></a></li>
                            <li><a href="#">Bebida 1 (Javis) <span class="glyphicon glyphicon-ok"></span></a></li>
                        </ul>
                    </li>
                </ul>
            </aside>

            <!-- Main component for a primary marketing message or call to action -->
            <div class="producto col-xs-12 col-sm-12 col-md-8 col-lg-8">
                <h1>Producto</h1>
                <p>This example is a quick exercise to illustrate how the 
                    default, static navbar and fixed to top navbar work. It includes the 
                    responsive CSS and HTML, so it also adapts to your viewport and device.</p>
                <p>
                    <a class="btn btn-lg btn-primary" href="http://getbootstrap.com/components/#navbar" role="button">View navbar docs »</a>
                </p>
            </div>

        </div> <!-- /container -->

        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/principalAlumno.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->

    </body>
</html>