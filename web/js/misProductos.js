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
        case "addPlatilloOk":
            alert("platillo registrado correctamente");
            break;

        case "addPlatilloError":
            alert("Error al registrar el platillo");
            break;

        case "updatePlatilloOk":
            alert("platillo actualizado correctamente");
            break;

        case "updatePlatilloError":
            alert("Error al actualizar el platillo");
            break;

        case "deletePlatilloOk":
            alert("platillo eliminado correctamente");
            break;

        case "deletePlatilloError":
            alert("Error al eliminar el platillo");
            break;

        case "idNegocio":
            idNegocio = parseInt(response[1]);
            break;

        case "platillosByCategoria":
            showPlatillos(response[1]);
            break;

        case "infoPlatillo":
            mostrarInfoPlatillo(response[1]);
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

//Este metodo es para abrir el archivo de imagen, depende de donde lo quieras poner
//guarda los datos en una variable para que puedan ser utilizados al guardar el platillo
function openFile(event) {
    var input = event.target;

    var reader = new FileReader();
    reader.onload = function () {
        var arrayBuffer = reader.result;

        console.log(arrayBuffer.byteLength);

        imagenBytes = arrayBuffer;

    };
    reader.readAsArrayBuffer(input.files[0]);
}
;

//Este metodo transforma los bytes de la imagen en cadena para que se puedan envviar
//por json
function arrayBufferToBase64(buffer) {
    var binary = '';
    var bytes = new Uint8Array(buffer);
    var len = bytes.byteLength;
    for (var i = 0; i < len; i++) {
        binary += String.fromCharCode(bytes[ i ]);
    }
    return window.btoa(binary);
}

//Para registrar platillo
function addPlatillo() {
    //Esos datos se envian para guardar el producto, es solo una prueba
    //Try if the image is saved
    var platillo = {
        idNegocio: idNegocio,
        imagen: arrayBufferToBase64(imagenBytes),
        nombre: "platillo1",
        precio: 30.50,
        tiempoPreparacion: 10,
        categoria: "COMIDA",
        descripcion: "un platillo muy bueno"
    };

    var requestData = {
        action: "addPlatillo",
        platillo: platillo
    };

    socket.send(JSON.stringify(requestData));
}

//Para actualizar platillo, seria casi lo mismo...
function updatePlatillo() {
    //Esos datos se envian para actualizar el producto, es solo una prueba
    //Try if the image is saved
    var platillo = {
        //Aquí va el id del platillo
        idPlatillo: 1,
        imagen: arrayBufferToBase64(imagenBytes),
        nombre: "platillo1",
        precio: 30.50,
        tiempoPreparacion: 10,
        categoria: "COMIDA",
        descripcion: "un platillo muy bueno"
    };

    var requestData = {
        action: "updatePlatillo",
        platillo: platillo
    };

    socket.send(JSON.stringify(requestData));
}

function getAllPlatillos(nombreCategoria) {

    var requestData = {
        action: "getPlatillosByCategoria",
        idNegocio: idNegocio,
        categoria: nombreCategoria
    };

    socket.send(JSON.stringify(requestData));
}

function showPlatillos(responseData) {
    var platillosList = JSON.parse(responseData);
    platillos = platillosList;
    //test platillo info
//    alert("Id: " + platillosList[0].id + "\nNombre: " + platillosList[0].nombre +
//            "\nDescripción: " + platillosList[0].desscripcion + "\nCategoria: " +
//            platillosList[0].categoria + "\nPrecio: " + platillosList[0].precio +
//            "\nTiempo de Preparación: " + platillosList[0].tiempoPreparacion);
}

function mostrarProducto(indiceProducto) {
    productoSeleccionado = indiceProducto;

    //Get remaining product info
    var requestData = {
        action: "getInfoPlatillo",
        idPlatillo: platillos[productoSeleccionado].id,
        idAlumno: idUsuario //En realidad no traerá ningún comentario pero sirve para lo mismo
    };
    socket.send(JSON.stringify(requestData));
}

function mostrarInfoPlatillo(infoPlatillo) {

    var platillo = JSON.parse(infoPlatillo);

    //mostrar datos....
}

function recibirNotificacion(response) {
    var orden = JSON.parse(response);

    alert(orden);
}
