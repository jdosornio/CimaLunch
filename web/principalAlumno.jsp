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
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="css/estiloPrincipalAlumno.css" rel="stylesheet">
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

            <aside class="negocios col-xs-12 col-sm-12 col-md-4 col-lg-3">
                <ul>
                    <li class="dropdown col-xs-4 col-sm-4 col-md-12 col-lg-12">
                        <button class="dropdown-toggle btn btn-default navbar-btn btn-lg" data-toggle="dropdown" role="button"> 
                            <span class="glyphicon glyphicon-lock"> Negocio</span> <span class="caret"></span></button>
                        <ul class="dropdown-menu dropdown-cart" role="menu">
                            <li><a href="#"><img src="imagenes/logo1.jpg"> Deli</a></li>
                            <li class="divider"></li>
                            <li><a href="#"><img src="imagenes/logo2.jpg"> Javis</a></li>
                            <li class="divider"></li>
                            <li><a href="#"><img src="imagenes/logo3.jpg"> Pizza Mia</a></li>
                        </ul>
                    </li>
                    <li class="dropdown col-xs-4 col-sm-4 col-md-12 col-lg-12">
                        <button class="dropdown-toggle btn btn-default navbar-btn btn-lg disabled" data-toggle="dropdown" role="button"> 
                            <span class="glyphicon glyphicon-glass"> Categoria</span> <span class="caret"></span></button>
                        <ul class="dropdown-menu dropdown-cart" role="menu">
                            <li><a href="#"><img src="imagenes/comida.jpg"> Platillos</a></li>
                            <li class="divider"></li>
                            <li><a href="#"><img src="imagenes/bebidas.jpg"> Bebidas</a></li>
                            <li class="divider"></li>
                            <li><a href="#"><img src="imagenes/botana.png"> Botanas</a></li>
                        </ul>
                    </li>
                    <li class="dropdown col-xs-4 col-sm-4 col-md-12 col-lg-12">
                        <button class="dropdown-toggle btn btn-default navbar-btn btn-lg disabled" data-toggle="dropdown" role="button"> 
                            <span class="glyphicon glyphicon-cutlery"> Producto</span> <span class="caret"></span></button>
                        <ul class="dropdown-menu dropdown-cart" role="menu">
                            <li><a href="#"><span class="item-info">Platillo 1 $35.00</span></a></li>
                            <li><a href="#"><span class="item-info">Platillo 2 $29.00</span></a></li>
                        </ul>
                    </li>
                </ul>
            </aside>

            <!-- Div del producto -->
            <div class="producto col-xs-12 col-sm-12 col-md-7 col-lg-8">
                <div class="direccion col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <img src="imagenes/logo1.jpg">
                    <span>Comida</span>
                </div>
                <h2>Platillo 1</h2>
                <div class="imagenArticulo col-xs-12 col-sm-12 col-md-6 col-lg-6">
                    <img src="imagenes/platillo1.jpg">
                </div>
                <div class="detallesArticulo col-sm-12 col-md-6 col-lg-6">
                    <p><strong>Descripción:</strong> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore magna aliqua.</p>
                    <label for="calificacion">Opinion de los clientes:</label>
                    <input type="text" value="4/5" readonly="readonly">
                    <br><br>
                    <label for="precio">Precio:</label>
                    <input type="text" value="$35.00" readonly="readonly">
                    <br><br>
                    <label for="tiempo">Tiempo de preparación:</label>
                    <input type="text" value="15" readonly="readonly">
                    <span>minuto(s).</span>
                    <br><br>
                    <label for="cantidad">Cantidad:</label>
                    <input type="number" max="99" min="1" value="1">
                    <br><br>
                    <button class="btn btn-sm btn-primary">Añadir al carrito</button>
                </div>
                <div style="clear:both;"></div>
                <div class="comentarios">
                    <div class="comentariosClientes">
                        <h4>Comentarios de otros clientes:</h4>
                        <br><br>
                        <ul>
                            <li>
                                <span>Nombre del cliente.</span>
                                <span>Calificación: 5/5</span>
                                <br>
                                <span>Fecha del comentario</span>
                                <br>
                                <span>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                    tempor incididunt ut labore et dolore magna aliqua.</span>
                            </li>
                            <br>
                            <li>
                                <span>Nombre del cliente.</span>
                                <span>Calificación: 5/5</span>
                                <br>
                                <span>Fecha del comentario</span>
                                <br>
                                <span>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                    tempor incididunt ut labore et dolore magna aliqua.</span>
                            </li>
                            <br>
                            <li>
                                <span>Nombre del cliente.</span>
                                <span>Calificación: 5/5</span>
                                <br>
                                <span>Fecha del comentario</span>
                                <br>
                                <span>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                    tempor incididunt ut labore et dolore magna aliqua.</span>
                            </li>
                        </ul>
                    </div>
                </div>
                <form>
                    <label for="comentario">Dejanos tu comentario:</label>
                    <br>
                    <label for="calificacion">Calificación:</label>
                    <select>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                    <br><br>
                    <textarea name="comentario" form="usrform" rows="3" cols="30"></textarea>
                    <br><br>
                    <button class="btn btn-sm btn-primary">Enviar</button>
                </form>             
            </div>

            <footer class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <span>@CimaLunch</span>
                <br>
                Fernando Enrique Avendaño Hernández
                <br>
                Jesús Donaldo Osornio Hernández
            </footer>

        </div> <!-- /container -->

        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/principalAlumno.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->

    </body>
</html>