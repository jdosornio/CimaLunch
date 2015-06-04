/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var socket = new WebSocket("ws://localhost:8080/CimaLunch/actions");
var idUsuario;
var idNegocio;
var ordenes;
var imagenBytes;

socket.onmessage = onMessage;
socket.onopen = onOpen;

function onMessage(event) {
    //Get the data type and the data
    var response = event.data.split(":DELIM>");
    //Get the data type
    switch (response[0]) {
        //Returned all negocios

        case "newOrder":
            recibirNotificacion(response[1]);
            break;

    }

}

function onOpen() {
//Send get all negocios request
    var requestData = {
        action: "getAllNegocios"
    };
    socket.send(JSON.stringify(requestData));
}

function signOut() {

//To remove all attributes so the user can no longer enter the page
    var sessionData = {
        action: "delete",
        attrs: JSON.stringify(["usuario", "tipo", "idUsuario", "nombreUsuario"])
    };
    //Send data to http session
    $.post("SessionServlet", sessionData, function () {
        //Once deleted, return to index
        location.replace("index.jsp");
    });
}

$(document).ready(function () {
//for closing the socket before reload page
    $(window).on('beforeunload', function () {
        socket.close();
    });
    //for getting the data of the charola
    $.get("SessionServlet", {action: "get", attrs: JSON.stringify(["idUsuario"])}, function (response) {
        //Success, get id usuario
        idUsuario = response[0];

        var requestData = {
            action: "getNegocioAdmin",
            idAdmin: idUsuario
        };

        socket.send(JSON.stringify(requestData));
    });
    //Get id negocio and ordenes
});
