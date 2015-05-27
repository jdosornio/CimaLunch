<%-- 
    Document   : loginTest
    Created on : 27/05/2015, 10:56:22 AM
    Author     : Jesus Donaldo
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>CimaLuch - Login</title>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, 
              maximum-scale=1.0, minimum-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/estiloLogin.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">

      <form id="loginForm" class="form-signin">
        <h2 class="form-signin-heading">Iniciar sesión</h2>
        <br><br>
        <label for="inputUser" class="sr-only">Usuario</label>
        <input id="inputUser" class="form-control" placeholder="Usuario" required="" autofocus="" type="text">
        <br>
        <label for="inputPassword" class="sr-only">Contraseña</label>
        <input id="inputPassword" class="form-control" placeholder="Contrseña" required="" type="password">
        <div class="checkbox">
        </div><br>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Iniciar Sesión</button>
      </form>

    </div> <!-- /container -->
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/login.js"></script>
    </body>
</html>