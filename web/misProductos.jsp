<%-- 
    Document   : misPlatillos
    Created on : May 28, 2015, 11:19:40 PM
    Author     : FernandoEnrique
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>CimaLunch - Pagina principal</title>
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="css/estiloMisProductos.css" rel="stylesheet">
    </head>

    <body>

        <%
            String nombreUsuario = null;
            if (session.getAttribute("usuario") == null) {
                //go to index
                response.sendRedirect("index.jsp");
            } else {
                nombreUsuario = String.valueOf(session.getAttribute("usuario"));
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
                            <li><a>Bienvenido <%="<strong>" + nombreUsuario + "</strong>"%>!</a></li>
                            <li><a href="principalAdministrador.jsp"> Inicio </a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="#">Cerrar Sesión</a></li>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div><!--/.container-fluid -->
            </nav>

            <section class="contenedor col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <h2>Mis Productos</h2>
                <div class="categoria col-xs-12 col-sm-12 col-md-4 col-lg-4">

                    <ul>
                        <li class="dropdown col-xs-4 col-sm-4 col-md-12 col-lg-12">
                            <button class="dropdown-toggle btn btn-default navbar-btn btn-lg" data-toggle="dropdown" role="button"> 
                                <span class="glyphicon glyphicon-glass"> Categoria</span> <span class="caret"></span></button>
                            <ul class="dropdown-menu dropdown-cart" role="menu">
                                <li><a href="#"><img src="imagenes/comida.jpg"> Platillos</a></li>
                                <li class="divider"></li>
                                <li><a href="#"><img src="imagenes/bebidas.jpg"> Bebidas</a></li>
                                <li class="divider"></li>
                                <li><a href="#"><img src="imagenes/botana.png"> Botanas</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <div class="productos col-xs-12 col-sm-12 col-md-7 col-lg-7">
                    <table class="table table-hover">
                        
                    </table>
                </div>
            </section>
        </div>

        <script src="js/misProductos.js"></script>
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.js"></script>
    </body>

</html>
