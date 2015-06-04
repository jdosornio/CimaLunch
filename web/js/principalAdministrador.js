/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var socket = new WebSocket("ws://localhost:50337/CimaLunch/actions");
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

        case "idNegocio":
            idNegocio = parseInt(response[1]);

            var requestData = {
                action: "getOrdenesNegocio",
                idNegocio: idNegocio
            };

            socket.send(JSON.stringify(requestData));
            break;

        case "ordenesNegocio":
            mostrarOrdenes(response[1]);
            break;
    }

}

function onOpen() {
//Send get all negocios request
    var requestData = {
        action: "getNegocioAdmin",
        idAdmin: 6
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
    });
    //Get id negocio and ordenes
});

function notificarAlumno(idOrden) {

    var idOrdenA = idOrden.split(',')[0];
    var idPlatilloA = idOrden.split(',')[1];

    var btn = '#' + idOrdenA + 'btn' + idPlatilloA;

    $(btn).removeClass();
    $(btn).addClass('glyphicon');
    $(btn).addClass('glyphicon-ok');

    //Para cuando seleccione que un platillo está listo
    var requestData = {
        action: "notifyOrderReady",
        idOrden: idOrdenA,
        idPlatillo: idPlatilloA
    };

    socket.send(JSON.stringify(requestData));

}

function mostrarOrdenes(data) {

    $('#ordenesActivas').html("");

    var ordenes = JSON.parse(data);

    $('#ordenesActivas').append('<h2>Mis Ordenes</h2>');

    for (var i = 0; i < ordenes.length; i++) {
        //Agregar ordenes....
        var ord = '<article class="orden col-xs-10 col-sm-5 col-md-3 col-lg-3 clearfix">' +
                '<h4>Orden #' + ordenes[i].id + '</h4>' +
                '<span><strong>Hecha por:</strong> ' + ordenes[i].nombreAlumno + ' a las ' + ordenes[i].fecha + '</span>' +
                '<ul>';

        var platillos = ordenes[i].platillosOrdenados;

        for (var j = 0; j < platillos.length; j++) {
            ord += '<li class="producto">' + '<span>' + platillos[j].nombre + ' X ' + platillos[j].cantidad + '</span>' +
                    '<button  onclick="notificarAlumno(\'' + ordenes[i].id + ',' + platillos[j].id + '\')" class="btn btn-sm btn-primary">' +
                    '<span id="' + ordenes[i].id + 'btn' + platillos[j].id + '" class="glyphicon glyphicon-time"></span>' +
                    '</button>' +
                    '</li>' +
                    '<div style="clear: both;"></div>';

        }
        ord += '</ul>' +
                '<h4 id="totalOrden">Total: $' + ordenes[i].precioTotal + '</h4>' +
                '</article>';

        $('#ordenesActivas').append(ord);
    }
}

function recibirNotificacion(idOrden, idPlatillo, idAlumno) {
    idOrden = parseInt(idOrden);
    idPlatillo = parseInt(idPlatillo);

    onOpen();

//    $.get("SessionServlet", {action: "get", attrs: JSON.stringify(["idUsuario"])}, function (response) {
//        //Success, get id usuario
//        var idUsuario = response[0];
//        
//        alert(idUsuario + " " + idAlumno);
//        
//        if(idUsuario === idAlumno) {
//            //?? aqui deberia entrar....
//            //Si el usuario actual es a quien va dirigido el mensaje....
//            alert("IdOrden: " + idOrden + "\nIdPlatillo: " + idPlatillo + "\nIdAlumno: " + idAlumno);
//        }
//    });
}