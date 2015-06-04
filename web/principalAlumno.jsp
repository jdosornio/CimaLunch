<%-- 
    Document   : principalAlumno
    Created on : 27/05/2015, 08:37:05 PM
    Author     : Jesus Donaldo
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" class="no-js"><head>
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
        <link href="css/estiloPrincipalAlumno.css" rel="stylesheet">
        <script src="js/modernizr-2.8.3.min.js"></script>
    </head>

    <body>

        <%
            String nombreUsuario = null;
            if (session.getAttribute("usuario") == null) {
                //go to index
                response.sendRedirect("index.jsp");
            } else {
                nombreUsuario = String.valueOf(session.getAttribute("nombreUsuario"));
            }
        %>

        <div class="container">
            <!-- Barra de navegacion -->
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand">Cima<span>Lunch</span></a>
                    </div>
                    <div style="height: 0.8px;" aria-expanded="false" id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li><a>Bienvenido <%="<strong>" + nombreUsuario + "</strong>"%>!</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" onclick="getOrdenesAlumno()" aria-expanded="false">Mis Ordenes <span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu" id="listaOrdenes">
                                    <!-- Ordenes del alumno -->
                                </ul>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <button class="dropdown-toggle btn btn-default navbar-btn" data-toggle="dropdown" role="button" onclick="mostrarTodaLaCharola()"> 
                                    <span class="glyphicon glyphicon-inbox"></span><span class="cont"> <!-- Numero de articulos en el carrito --> </span>
                                    <span class="caret"></span></button>
                                <ul id="charola" class="dropdown-menu dropdown-cart" role="menu">
                                    <!-- Contenido de la charola -->
                                </ul>
                            </li>
                            <li><a href="#" onclick="signOut()">Cerrar Sesión</a></li>
                        </ul>
                    </div><!--Barra de navegacion -->
                </div><!--Contenedor de barra -->
            </nav>

            <aside class="negocios col-xs-12 col-sm-12 col-md-4 col-lg-3">
                <ul>
                    <li class="dropdown col-xs-4 col-sm-4 col-md-12 col-lg-12">
                        <button class="dropdown-toggle btn btn-default navbar-btn btn-lg boton-negocios" data-toggle="dropdown" role="button"> 
                            <span class="glyphicon glyphicon-lock"> Negocio</span> <span class="caret"></span></button>
                        <ul id="negociosList" class="dropdown-menu dropdown-cart" role="menu">
                            <!--Dropdown de los Locales -->
                        </ul>
                    </li>
                </ul>
            </aside>

            <!-- Div del producto -->
            <div class="producto col-xs-12 col-sm-12 col-md-7 col-lg-8 hidden">
                <div class="direccion col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <img id="logoNegocio">
                    <span id="sloganNegocio"></span>
                </div>
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="#comidas" data-toggle="tab" onclick="activarProductos('Comida')">Comidas</a></li >
                    <li role ="presentation"><a href="#bebidas" data-toggle="tab" onclick="activarProductos('Bebida')">Bebidas</a></li>
                    <li role="presentation"><a href="#dulces" data-toggle="tab" onclick="activarProductos('Dulce')">Dulces</a></li>
                    <li role="presentation"><a href="#otros" data-toggle="tab" onclick="activarProductos('Otro')">Otros</a></li>
                </ul>
                <!-- Tabbed Pane categorias -->
                <div id="my-tab-content" class="tab-content">
                    <div class="tab-pane active" id="comidas">
                        <ul id="listaProductosComida">
                            <!-- Productos de categoria comida -->
                        </ul>
                    </div>
                    <div class="tab-pane" id="bebidas">
                        <ul id="listaProductosBebida">
                            <!-- Productos de categoria bebida -->
                        </ul>
                    </div>
                    <div class="tab-pane" id="dulces">
                        <ul id="listaProductosDulce">
                            <!-- Productos de categoria dulce -->
                        </ul>
                    </div>
                    <div class="tab-pane" id="otros">
                        <ul id="listaProductosOtro">
                            <!-- Productos de categoria otro -->
                        </ul>
                    </div>
                </div>

                <!--Detalles de los productos -->
                <div class="modal fade" id="modalDetalles" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h3 class="modal-title" id="myModalLabel">Detalles</h3>
                            </div>
                            <div class="modal-body modalInfo">
                                <!--Detalles del producto seleccionado -->
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <footer class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <span>@CimaLunch</span>
                <br>
                Fernando Enrique Avendaño Hernández
                <br>
                Jesús Donaldo Osornio Hernández
            </footer><!-- Footer -->

        </div> <!-- Contenedor del body -->

        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/principalAlumno.js"></script>
        <script src="js/notificacionesAlumno.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->

    </body>
</html>
