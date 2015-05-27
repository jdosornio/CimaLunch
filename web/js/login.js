/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var socket = new WebSocket("ws://localhost:8080/CimaLunch/actions");
socket.onmessage = onMessage;

function onMessage(event) {
    var response = event.data;
    
    if(response.indexOf("loginOk") !== -1) {
        //Show new page... send user to http session
        
        //Get usuario
        var usuario = response.split("/");
        
        var sessionData = {
            action: "save",
            attr: "user",
            value: usuario[1]
        };
        
        //Send data to http session
        $.post("SessionServlet", sessionData, function() {
            //Once saved change to the next page...
            window.location = "index.html";
        });
    }
    else if(response === "loginFail") {
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