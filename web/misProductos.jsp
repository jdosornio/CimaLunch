<%-- 
    Document   : misPlatillos
    Created on : May 28, 2015, 11:19:40 PM
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
        <link href="css/estiloMisProductos.css" rel="stylesheet">
        <script src="js/modernizr-2.8.3.min.js"></script>
    </head>

    <body>

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
                            <li><a>Bienvenido !</a></li>
                            <li><a href="principalAdministrador.jsp"> Inicio </a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="#" onclick="signOut()">Cerrar Sesión</a></li>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div><!--/.container-fluid -->
            </nav>

            <section class="contenedor col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <h2>Mis Productos</h2>
                <button type="button" id="agregar" class="btn btn-md btn-primary" data-toggle="modal" data-target="#myModal">Agregar</button>
                <!--/ Modal Agregar -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title" id="myModalLabel">Agregar un producto</h4>
                            </div>
                            <div class="modal-body">
                                <form class="agregarProducto">
                                    <label for="categoria" class="sr-only">Categoria:</label>
                                    <select id="categoriaNueva" class="selectCategoria">
                                        <option>COMIDA</option>
                                        <option>BEBIDA</option>
                                        <option>DULCE</option>
                                        <option>OTRO</option>
                                    </select>
                                    <label for="nombre" class="sr-only control-label">Nombre:</label>
                                    <input id="nombreNuevo" name="nombre" type="text" class="form-control" placeholder="Nombre" required="">
                                    <label for="descripcion" class="sr-only">Descripcion:</label>
                                    <textarea id="descripcionNueva" name="descripcion" form="usrform" rows="3" cols="30" placeholder="Descripción"></textarea>
                                    <label for="precio" class="sr-only">Precio:</label>
                                    <input id="precioNuevo" name="precio" type="text" class="form-control" placeholder="Precio" required="">
                                    <label for="tiempo" class="sr-only">Tiempo de preparación:</label>
                                    <input id="tiempoNuevo" name="tiempo" type="text" class="form-control" placeholder="Tiempo" required="">
                                    <label for="imagen">File input</label>
                                    <input type="file" id="imagen" onchange="openFile(event)">
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                        <button class="btn btn-primary" type="submit" onclick="addPlatillo()">Agregar</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!--/ Modal Modificar -->
                <div class="modal fade" id="modalModificar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title" id="myModalLabel">Modificar un producto</h4>
                            </div>
                            <div class="modal-body">
                                <form class="agregarProducto">
                                    <label for="categoria" class="sr-only">Categoria:</label>
                                    <select id="categoriaMod" class="selectCategoria">
                                        <option>COMIDA</option>
                                        <option>BEBIDA</option>
                                        <option>DULCE</option>
                                        <option>OTRO</option>
                                    </select>
                                    <label for="nombre" class="sr-only control-label">Nombre:</label>
                                    <input id="nombreMod" name="nombre" type="text" class="form-control" placeholder="Nombre" required="">
                                    <label for="descripcion" class="sr-only">Descripcion:</label>
                                    <textarea id="descripcionMod" name="descripcion" form="usrform" rows="3" cols="30" placeholder="Descripción"></textarea>
                                    <label for="precio" class="sr-only">Precio:</label>
                                    <input id="precioMod" name="precio" type="text" class="form-control" placeholder="Precio" required="">
                                    <label for="tiempo" class="sr-only">Tiempo de preparación:</label>
                                    <input id="tiempoMod" name="tiempo" type="text" class="form-control" placeholder="Tiempo" required="">
                                    <label for="imagen">File input</label>
                                    <input type="file" id="imagenMod" onchange="openFile(event)">
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                        <button class="btn btn-primary" type="submit" onclick="updatePlatillo()" data-dismiss="modal">Modificar</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!--/ Modal Eliminar -->
                <div class="modal fade" id="modalEliminar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title" id="myModalLabel">Eliminar</h4>
                            </div>
                            <div class="modal-body">
                                <span>Estas seguro de que deseas eliminar este producto?</span>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                <button class="btn btn-primary" data-dismiss="modal" onclick="deletePlatillo()">Eliminar</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="categoria col-xs-11 col-sm-11 col-md-3 col-lg-3">
                    <ul>
                        <li class="dropdown col-xs-4 col-sm-4 col-md-12 col-lg-12">
                            <button class="dropdown-toggle btn btn-default navbar-btn btn-lg" data-toggle="dropdown" role="button"> 
                                <span class="glyphicon glyphicon-glass"> Categoria</span> <span class="caret"></span></button>
                            <ul class="dropdown-menu dropdown-cart" role="menu">
                                <li><a href="#" onclick="getAllPlatillos('Comida')"><img src="imagenes/comida.jpg"> Comidas</a></li>
                                <li class="divider"></li>
                                <li><a href="#" onclick="getAllPlatillos('Bebida')"><img src="imagenes/bebidas.jpg"> Bebidas</a></li>
                                <li class="divider"></li>
                                <li><a href="#" onclick="getAllPlatillos('Dulce')"><img src="imagenes/botana.png"> Dulces</a></li>
                                <li class="divider"></li>
                                <li><a href="#" onclick="getAllPlatillos('Otro')"><img src="imagenes/botana.png"> Otros</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <h3 id="categoria"><!-- Titulo de la categoria --></h3>
                <div class="productos col-xs-11 col-sm-11 col-md-8 col-lg-8">
                    <table class="table table-hover table-striped table-responsive" id="tablaProductos">
                        <!-- Lista de productos por categoria -->
                    </table>
                </div>
            </section>
        </div>

        <script src="js/misProductos.js"></script>
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.js"></script>
    </body>

</html>
