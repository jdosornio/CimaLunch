<%-- 
    Document   : principalAdministrador
    Created on : May 28, 2015, 9:27:56 PM
    Author     : FernandoEnrique
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" class="no-js">
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
        <link href="css/estiloPrincipalAdministrador.css" rel="stylesheet">
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
                            <li><a href="misProductos.jsp"> Mis Productos </a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a onclick="signOut()" href="#">Cerrar Sesión</a></li>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div><!--/.container-fluid -->
            </nav>

            <!--/ Ordenes activas -->
            <section id="ordenesActivas" class="ordenes col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <h2>Mis Ordenes</h2>
                <article>
                    <!-- Contenido de las ordenes activas -->
                </article>
            </section>

        </div>

        <script src="js/jquery.js"></script>
        <script src="js/notificacionesAdmin.js"></script>
        <script src="js/principalAdministrador.js"></script>
        <script src="js/bootstrap.js"></script>

        <!--<input type='file' onchange='openFile(event)'>-->
    </body>
</html>
