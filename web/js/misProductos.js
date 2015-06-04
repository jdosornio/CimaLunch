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
var categoriaActual;
var idProductoActual;
var platillos;

socket.onmessage = onMessage;
socket.onopen = onOpen;

function onMessage(event) {
    //Get the data type and the data
    var response = event.data.split(":DELIM>");
    //Get the data type
    switch (response[0]) {
        //Returned all negocios
        case "addPlatilloOk":
            alert("Platillo registrado correctamente");
            break;

        case "addPlatilloError":
            alert("Error al registrar el platillo");
            break;

        case "updatePlatilloOk":
            alert("platillo actualizado correctamente");
            getAllPlatillos(categoriaActual);
            break;

        case "updatePlatilloError":
            alert("Error al actualizar el platillo");
            break;

        case "deletePlatilloOk":
            alert("Producto eliminado correctamente!");
            getAllPlatillos(categoriaActual);
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
    var requestData = {
        action: "getNegocioAdmin",
        idAdmin: idUsuario
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

    var nombre = $('#nombreNuevo').val();
    var precio = $('#precioNuevo').val();
    var tiempo = $('#tiempoNuevo').val();
    var descripcion = $('#descripcionNueva').val();
    var categoria = $('#categoriaNueva').val();

    if (nombre !== "" && precio !== "" && tiempo !== "" && descripcion !== "") {

        //Esos datos se envian para guardar el producto, es solo una prueba
        //Try if the image is saved
        var platillo = {
            idNegocio: +4,
            imagen: arrayBufferToBase64(imagenBytes),
            nombre: nombre,
            precio: precio,
            tiempoPreparacion: +tiempo,
            categoria: categoria,
            descripcion: descripcion
        };

        var requestData = {
            action: "addPlatillo",
            platillo: platillo
        };

        socket.send(JSON.stringify(requestData));

    }
}

//Para actualizar platillo, seria casi lo mismo...
function updatePlatillo() {
    alert("update");
    var nombre = $('#nombreMod').val();
    var precio = $('#precioMod').val();
    var tiempo = $('#tiempoMod').val();
    var descripcion = $('#descripcionMod').val();
    var categoria = $('#categoriaMod').val();

    if (nombre !== "" && precio !== "" && tiempo !== "" && descripcion !== "") {
        alert(nombre + precio + tiempo + descripcion + categoria);
        //Esos datos se envian para actualizar el producto, es solo una prueba
        //Try if the image is saved
        var platillo = {
            //Aquí va el id del platillo
            idPlatillo: idProductoActual,
            idNegocio: +4,
            imagen: arrayBufferToBase64(imagenBytes),
            nombre: nombre,
            precio: precio,
            tiempoPreparacion: +tiempo,
            categoria: categoria,
            descripcion: descripcion
        };

        var requestData = {
            action: "updatePlatillo",
            platillo: platillo
        };

        socket.send(JSON.stringify(requestData));

    }
}

function deletePlatillo() {
    var requestData = {
        action: "deletePlatillo",
        idPlatillo: idProductoActual
    };

    socket.send(JSON.stringify(requestData));
}

function getAllPlatillos(nombreCategoria) {
    categoriaActual = nombreCategoria;

    $('#categoria').html("");
    $('#categoria').append(nombreCategoria + "s");

    var requestData = {
        action: "getPlatillosByCategoria",
        idNegocio: 4,
        categoria: nombreCategoria
    };

    socket.send(JSON.stringify(requestData));
}

function showPlatillos(responseData) {

    var platillosList = JSON.parse(responseData);
    platillos = platillosList;

    $('#tablaProductos').html("");
    $('#tablaProductos').append('<tr>' +
            '<th>id</th>' +
            '<th>Nombre</th>' +
            '<th>Descripcion</th>' +
            '<th>Precio</th>' +
            '<th>Tiempo de preparacion</th>' +
            '<th></th>' +
            '<th></th>' +
            '</tr>');

    for (var i = 0; i < platillosList.length; i++) {
        $('#tablaProductos').append('<tr>' +
                '<td>' + platillosList[i].id + '</td>' +
                '<td>' + platillosList[i].nombre + '</td>' +
                '<td>' + platillosList[i].descripcion + '</td>' +
                '<td>$' + platillosList[i].precio + '</td>' +
                '<td>' + platillosList[0].tiempoPreparacion + ' minutos</td>' +
                '<td><button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#modalEliminar" onclick="getIdPlatillo(\'' + platillosList[i].id + '\')">Eliminar</button></td>' +
                '<td><button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#modalModificar" onclick="mostrarProducto(\'' + i + '\')">Modificar</button></td>' +
                '</tr>');
    }

}

function getIdPlatillo(idPlatillo) {
    idProductoActual = idPlatillo;
}

function mostrarProducto(indiceProducto) {
    productoSeleccionado = indiceProducto;
    idProductoActual = platillos[productoSeleccionado].id;

    //Get remaining product info
    var requestData = {
        action: "getInfoPlatillo",
        idPlatillo: platillos[productoSeleccionado].id,
        idAlumno: +4 //En realidad no traerá ningún comentario pero sirve para lo mismo
    };
    socket.send(JSON.stringify(requestData));
}

function mostrarInfoPlatillo(infoPlatillo) {
    var platillo = JSON.parse(infoPlatillo);

    $('#nombreMod').val(platillos[productoSeleccionado].nombre);
    $('#precioMod').val(platillos[productoSeleccionado].precio);
    $('#tiempoMod').val(platillos[productoSeleccionado].tiempoPreparacion);
    $('#descripcionMod').val(platillos[productoSeleccionado].descripcion);
    $('#categoriaMod').val(platillos[productoSeleccionado].categoria);
    imagenBytes = platillo.imagen;

}