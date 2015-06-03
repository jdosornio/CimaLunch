/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var socket = new WebSocket("ws://localhost:8080/CimaLunch/actions");
socket.onmessage = onMessage;

function onMessage(event) {
    var response = event.data;

    if (response.indexOf("loginOk") !== -1) {
        //Show new page... send user to http session

        //Get usuario in 1 and tipo in 2
        var usuario = response.split("/");

        var sessionData = {
            action: "save",
            attrs: JSON.stringify(["usuario", "tipo", "idUsuario", "nombreUsuario"]),
            values: JSON.stringify([usuario[1], usuario[2], usuario[3], usuario[4]])
        };

        //Send data to http session
        $.post("SessionServlet", sessionData, function () {
            //Once saved depending on the type go to the right page...

            if (usuario[2] === "ALUMNO") {
                location.replace("principalAlumno.jsp");
            }
            else if (usuario[2] === "ADMIN_LOCAL") {
                //window.location.replace = "principalAdmin.jsp"
            }
        });
    }
    else if (response === "loginFail") {
        //Show error message
        alert("Usuario o contraseña incorrectos");
    }
}

function login() {
    var user = $('#inputUser').val();
    var pass = $('#inputPassword').val();

    var loginData = {
        action: "login",
        user: user,
        pass: pass
    };

    socket.send(JSON.stringify(loginData));
}

$(document).ready(function () {
    //for closing the socket before reload page
    $(window).on('beforeunload', function () {
        socket.close();
    });

    //On form submit
    $('#loginForm').submit(function () {
        login();
        return false;
    });
});