/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var socket = new WebSocket("ws://localhost:50337/CimaLunch/actions");
//array for storing the negocios
var negocios;
//array for storing platillos
var platillos;
var negocioSeleccionado;
var productoSeleccionado;
var categoriaSeleccionada;
var charola = new Array(), cont = 0;
var imagenActual;
var idUsuario;

var precioCharola;
var tiempoCharola;

$('.cont').append(" " + cont + " ");

socket.onmessage = onMessage;
socket.onopen = onOpen;
function onMessage(event) {
    //Get the data type and the data
    var response = event.data.split(":DELIM>");
    //Get the data type
    switch (response[0]) {
        //Returned all negocios
        case "allNegocios":
            showNegocios(response[1]);
            break;
        case "platillosByCategoria":
            showPlatillos(response[1]);
            break;
        case "infoPlatillo":
            mostrarInfoPlatillo(response[1]);
            break;
        case "ordenesAlumno":
            mostrarOrdenesAlumno(response[1]);
            break;
        case "noComentario":
            $('#estadoComentario').append("Ya has comentado este producto anteriormente.");
            break;
        case "siComentario":
            alert("Comentario realizado exitosamente.");
            break;
        case "placeOrderError":
            alert("No se pudo guardar la orden");
            break;
        case "placeOrderOk":
            //notificarNegocio();
            limpiarCharola();
            mostrarTodaLaCharola();
            break;
            //Quitar...
        default:
            alert(response[1]);
    }

}

function showNegocios(responseData) {
//Get data array
    var negociosList = JSON.parse(responseData);
    negocios = negociosList;
    //show data in page
    for (var i = 0; i < negociosList.length; i++) {
//Show in html
//to display an image from bytes you need to use data:image/png;base64, and
//the byte array as a string
        var indice = i;
        $('#negociosList').append('<li><a href="#" onclick="activarCategorias(\'' + indice + '\')"><img src="data:image/png;base64,' +
                negociosList[i].imagen + '"> ' + negociosList[i].nombre + '</a></li>');
        //Apend delimiter if not last element
        if (i + 1 !== negociosList.length) {
            $('#negociosList').append('<li class="divider"></li>');
        }

//        alert("Id: " + negociosList[i].id + "\nNombre: " + negociosList[i].nombre +
//                "\nSlogan: " + negociosList[i].slogan);

//Show negocio image
//        loadImage(
//                negociosList[i].imagen,
//                function (img) {
//                    //There was an error
//                    if (img.type === "error") {
//                        console.log("Error loading image ");
//                    } else {
//                        document.body.appendChild(img);
//                    }
//                }
//        );

//        var image = document.createElement('img');
//        image.src = 'data:image/png;base64,' + negociosList[i].imagen;
//        document.body.appendChild(image);
    }
}

function showPlatillos(responseData) {
    var platillosList = JSON.parse(responseData);
    platillos = platillosList;
    //test platillo info
//    alert("Id: " + platillosList[0].id + "\nNombre: " + platillosList[0].nombre +
//            "\nDescripción: " + platillosList[0].desscripcion + "\nCategoria: " +
//            platillosList[0].categoria + "\nPrecio: " + platillosList[0].precio +
//            "\nTiempo de Preparación: " + platillosList[0].tiempoPreparacion);
    if (categoriaSeleccionada === "Comida") {
        $('#listaProductosComida').html("");
        for (var i = 0; i < platillosList.length; i++) {
            $('#listaProductosComida').append('<li class="productoItem">' +
                    '<div class="productoItemIzq">' +
                    '<span>' + platillosList[i].nombre + '</span><br>' +
                    '<span>' + platillosList[i].descripcion +
                    ' <a href="#" data-toggle="modal" data-target="#modalDetalles" onclick="mostrarProducto(' + i + ')"> Detalles</a>' +
                    '</span>' +
                    '</div>' +
                    '<div class="productoItemDer">' +
                    '<span> $' + platillosList[i].precio + '</span>' +
                    ' </div>' +
                    '</li>' +
                    '<div style="clear: both;"></div>');
        }
    }
    if (categoriaSeleccionada === "Bebida") {
        $('#listaProductosBebida').html("");
        for (var i = 0; i < platillosList.length; i++) {
            $('#listaProductosBebida').append('<li class="productoItem">' +
                    '<div class="productoItemIzq">' +
                    '<span>' + platillosList[i].nombre + '</span><br>' +
                    '<span>' + platillosList[i].descripcion +
                    ' <a href="#" data-toggle="modal" data-target="#modalDetalles" onclick="mostrarProducto(' + i + ')"> Detalles</a>' +
                    '</span>' +
                    '</div>' +
                    '<div class="productoItemDer">' +
                    '<span> $' + platillosList[i].precio + '</span>' +
                    ' </div>' +
                    '</li>' +
                    '<div style="clear: both;"></div>');
        }
    }
    if (categoriaSeleccionada === "Dulce") {
        $('#listaProductosDulce').html("");
        for (var i = 0; i < platillosList.length; i++) {
            $('#listaProductosDulce').append('<li class="productoItem">' +
                    '<div class="productoItemIzq">' +
                    '<span>' + platillosList[i].nombre + '</span><br>' +
                    '<span>' + platillosList[i].descripcion +
                    ' <a href="#" data-toggle="modal" data-target="#modalDetalles" onclick="mostrarProducto(' + i + ')"> Detalles</a>' +
                    '</span>' +
                    '</div>' +
                    '<div class="productoItemDer">' +
                    '<span> $' + platillosList[i].precio + '</span>' +
                    ' </div>' +
                    '</li>' +
                    '<div style="clear: both;"></div>');
        }
    }
    if (categoriaSeleccionada === "Otro") {
        $('#listaProductosOtro').html("");
        for (var i = 0; i < platillosList.length; i++) {
            $('#listaProductosOtro').append('<li class="productoItem">' +
                    '<div class="productoItemIzq">' +
                    '<span>' + platillosList[i].nombre + '</span><br>' +
                    '<span>' + platillosList[i].descripcion +
                    ' <a href="#" data-toggle="modal" data-target="#modalDetalles" onclick="mostrarProducto(' + i + ')"> Detalles</a>' +
                    '</span>' +
                    '</div>' +
                    '<div class="productoItemDer">' +
                    '<span> $' + platillosList[i].precio + '</span>' +
                    ' </div>' +
                    '</li>' +
                    '<div style="clear: both;"></div>');
        }
    }
}
//Get the data needed as fast as the socket is open
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
    $.get("SessionServlet", {action: "get", attrs: JSON.stringify(["charola", "idUsuario"])}, function (response) {
        //Success, get id usuario
        charola = response[0];
        idUsuario = response[1];
    });
});
/**
 * Mostrar las categorias de productos activar el div de productos
 * @param {type} indiceNegocio
 * @returns {undefined}
 */
function activarCategorias(indiceNegocio) {
    $('#boton-categorias').removeClass('disabled');
    negocioSeleccionado = indiceNegocio;
    document.getElementById('logoNegocio').setAttribute('src', 'data:image/png;base64,' + negocios[negocioSeleccionado].imagen);
    $('#sloganNegocio').html(negocios[negocioSeleccionado].slogan);
    $('.producto').removeClass('hidden');
    activarProductos("Comida");
}

/**
 * Mostrar los productos de una categoria
 * @param {type} nombreCategoria
 * @returns {undefined}
 */
function activarProductos(nombreCategoria) {
    $('#boton-productos').removeClass('disabled');
    categoriaSeleccionada = nombreCategoria;
    //mostrar los productos en la lista
    var requestData = {
        action: "getPlatillosByCategoria",
        idNegocio: negocios[negocioSeleccionado].id,
        categoria: nombreCategoria
    };
    socket.send(JSON.stringify(requestData));
    $('#categoriaDireccion').html("");
    $('#categoriaDireccion').append(nombreCategoria);
}

/**
 * Mostrar detalles del producto envia el indice del producto
 * @param {type} indiceProducto
 * @returns {undefined}
 */
function mostrarProducto(indiceProducto) {
    productoSeleccionado = indiceProducto;

    //Get remaining product info
    var requestData = {
        action: "getInfoPlatillo",
        idPlatillo: platillos[productoSeleccionado].id,
        idAlumno: idUsuario
    };
    socket.send(JSON.stringify(requestData));
}

/**
 * Mostrar la informacion del platillo en el modal de detalles.
 * @param {type} infoPlatillo
 * @returns {undefined}
 */
function mostrarInfoPlatillo(infoPlatillo) {

    var platillo = JSON.parse(infoPlatillo);
    imagenActual = platillo.imagen;
    var comento = +0;
    $('.modal-body').html("");
    $('.modal-body').append('<h4>' + platillos[productoSeleccionado].nombre + '</h4>' +
            '<div class="detallesIzq col-xs-12 col-sm-12 col-md-6 col-lg-6">' +
            '<img src="data:image/png;base64,' + platillo.imagen + '">' +
            '</div>' +
            '<div class="detallesDer detallesArticulo col-xs-12 col-sm-12 col-md-6 col-lg-6">' +
            '<span>Descripción: ' + platillos[productoSeleccionado].descripcion + '</span>' +
            '<br>' +
            '<span>Precio: $' + platillos[productoSeleccionado].precio + ' pesos.</span>' +
            '<br>' +
            '<span>Tiempo de preparacion: ' + platillos[productoSeleccionado].tiempoPreparacion + ' minutos.</span>' +
            '<br>' +
            '<label for="cantidad">Cantidad:</label>' +
            '<input id="cantidad" type="number" max="99" min="1" value="1">' +
            '<button class="btn btn-primary" type="submit" data-dismiss="modal" onclick="agregarACharola(\'' + productoSeleccionado + '\')">Añadir a la charola</button>' +
            '</div>' +
            '<div style="clear: both;"></div> ' +
            '<div class="comentariosClientes">' +
            '<h4>Comentarios de otros clientes:</h4>' +
            '<ul>');
    var comentarios = platillo.comentarios;
    for (var i = 0; i < comentarios.length; i++) {
        $('.modal-body').append(
                '<li>' +
                '<span>Nombre: ' + comentarios[i].alumno + '</span>' +
                ' <br>' +
                '<span>Calificación: ' + comentarios[i].calificacion + '</span>' +
                ' <br>' +
                ' <span>Fecha: ' + comentarios[i].fecha + '</span>' +
                '<br>' +
                '<span>Comentario: ' + comentarios[i].comentario + '</span>' +
                ' </li>' +
                ' <br>');
        //Para saber si el alumno actual ya comento el platillo
        if (+idUsuario === +comentarios[i].idAlumno) {
            comento = +1;
        }
    }
    $('.modal-body').append(
            '</ul>' +
            '</div>');
    if (comento === +0) {
        $('.modal-body').append(
                '<form>' +
                '<label for="comentario">Dejanos tu comentario:</label>' +
                '<br>' +
                '<label for="calificacion">Calificación:</label>' +
                '<select id="calificacion">' +
                '<option>1</option>' +
                '<option>2</option>' +
                '<option>3</option>' +
                '<option>4</option>' +
                '<option>5</option>' +
                ' </select>' +
                '<br><br>' +
                '<span>Comentario:</span><br>' +
                '<textarea id="comentario" name="comentario" rows="3" cols="30" required></textarea>' +
                '<br><br>' +
                ' <button type="submit" class="btn btn-sm btn-primary" data-dismiss="modal" onClick="grabarComentario()">Enviar</button>' +
                '<br>' +
                '<span id="estadoComentario"></span>' +
                '</form>');
    }
}

function agregarACharola(indice) {
    var cantidad = $('#cantidad').val();
    //charola[cont] = platillos[indice]; 
    cont = +cont + +cantidad;
    $('.cont').html("");
    $('.cont').append(" " + cont + " ");
    //actualizarCharola();

    var platillo = platillos[indice];
    //Seleccionar la cantidad...
    platillo.cantidad = cantidad;
    platillo.imagen = imagenActual;
    platillo.tiempo = platillos[indice].tiempoPreparacion;

    //Send producto to session
    var sessionData = {
        action: "addToCharola",
        platillo: JSON.stringify(platillo)
    };

    //Send data to http session
    $.post("SessionServlet", sessionData, function () {
        //Hacer algo cuando se guarde el producto en la sesion
        $.get("SessionServlet", {action: "get", attrs: JSON.stringify(["charola"])}, function (response) {
            //Success, get id usuario
            charola = response[0];
            mostrarTodaLaCharola();
        });
    });
}

function removerDeCharola(indice) {
    //Prueba
    var sessionData = {
        action: "removeFromCharola",
        id: charola[indice].id
    };

    //Send data to http session
    $.post("SessionServlet", sessionData, function () {
        //Hacer algo cuando se guarde el producto en la sesion
        $.get("SessionServlet", {action: "get", attrs: JSON.stringify(["charola"])}, function (response) {
            //Success, get id usuario
            charola = response[0];
            mostrarTodaLaCharola();
        });
    });
}

function mostrarTodaLaCharola() {
    precioCharola = 0;
    tiempoCharola = 0;
    var cont = 0;

    //Prueba
    $.get("SessionServlet", {action: "get", attrs: JSON.stringify(["charola"])}, function (response) {
        //Success, get id usuario
        $('#charola').html("");
        var charola = response[0];
        if (charola === null || charola.length === 0) {
            $('#charola').append('<span class="item-left"> No hay productos.</span><li class="divider"></li>' +
                    '<li role="presentation" class="dropdown-header">Total: $ ' + precioCharola
                    + ' pesos.  Tiempo estimado: ' + tiempoCharola + ' minutos.</li>');
        }
        for (var i = 0; i < charola.length; i++) {
            //  alert("Platillo: " + charola[i].nombre + " Cantidad: " + charola[i].cantidad);
            precioCharola = +precioCharola + +(charola[i].precio * charola[i].cantidad);
            tiempoCharola = +tiempoCharola + +(charola[i].tiempo * charola[i].cantidad);
            cont = +cont + +charola[i].cantidad;
            $('#charola').append('<li>' +
                    ' <span class="item">' +
                    ' <span class="item-left">' +
                    ' <img src="data:image/png;base64,' + charola[i].imagen + '">' +
                    ' <span class="item-info">' +
                    ' <span>' + charola[i].nombre + '</span>' +
                    '<span>$' + charola[i].precio + ' pesos.</span>' +
                    '</span>' +
                    '  </span>' +
                    '<span class="item-right">' +
                    '<span>Cant: ' + charola[i].cantidad + '  </span>' +
                    ' <button class="btn btn-xs btn-danger pull-right" onclick="removerDeCharola(\'' + i + '\')">X</button>' +
                    ' </span>' +
                    '</span>' +
                    ' </li>');
        }
        $('#charola').append('<li class="divider"></li>' +
                '<li role="presentation" class="dropdown-header">Total: $ ' + precioCharola
                + ' pesos.  Tiempo estimado: ' + tiempoCharola + ' minutos.</li>');
        if (cont > 0) {
            $('#charola').append(' <li class="divider"></li>' +
                    '<li><a class="text-center" href="#" onclick="confirmarOrden()">Confirmar Orden</a></li>');
        }
        $('.cont').html("");
        $('.cont').append(" " + cont + " ");
    });
    $('.cont').html("");
    $('.cont').append(" " + cont + " ");
}

function removerTodosDeCharola() {

    var sessionData = {
        action: "delete",
        attrs: JSON.stringify(["charola"])
    };
    //Send data to http session
    $.post("SessionServlet", sessionData, function () {
        //Once deleted, return to index
        $.get("SessionServlet", {action: "get", attrs: JSON.stringify(["charola"])}, function (response) {
            //Success, get id usuario
            charola = response[0];
        });
    });
}

function getOrdenesAlumno() {

    var requestData = {
        action: "getOrdenesAlumno",
        idAlumno: idUsuario
    };

    socket.send(JSON.stringify(requestData));
}

//Prueba
function mostrarOrdenesAlumno(responseData) {
    var ordenes = JSON.parse(responseData);

    $('#listaOrdenes').html("");

    for (var i = 0; i < ordenes.length - 1; i++) {
        $('#listaOrdenes').append('<li role="presentation" class="dropdown-header">' +
                '<span>Orden #' + ordenes[i].id + '</span>');

        var platillos = ordenes[i].platillosOrdenados;

        for (var j = 0; j < platillos.length; j++) {
            if (platillos[j].status === "LISTO") {
                $('#listaOrdenes').append('</li>' +
                        '<li><a href="#">' + platillos[j].nombre + '(' + platillos[j].negocio + ') ' +
                        '<span class="glyphicon glyphicon-ok"></span>');
            } else {
                $('#listaOrdenes').append('</li>' +
                        '<li><a href="#">' + platillos[j].nombre + '(' + platillos[j].negocio + ') ');
            }
            $('#listaOrdenes').append('</a></li>');
        }

        $('#listaOrdenes').append('<li class="divider"></li>');

//        alert("Id: " + ordenes[i].id);
//        //Las ordenes tienen mas datos como id, precioTotal, tiempoEstimado y fecha
//        //por si se requieren
//        var platillos = ordenes[i].platillosOrdenados;
//
//        for (var j = 0; j < platillos.length; j++) {
//            alert("Nombre: " + platillos[j].nombre + "\nNegocio: " + platillos[j].negocio +
//                    "\nStatus: " + platillos[j].status);
//            //Los platillos tienen mas datos como precio, tiempoPreparacion, 
//            //cantidad, por si se requieren
//    }
    }
}

function grabarComentario() {
    var comentario, calificacion, idPlatillo;

    comentario = $('#comentario').val();

    if (comentario === "") {
        alert("El campo comentario no puede estar vacio!.");
    } else {
        calificacion = $('#calificacion').val();
        idPlatillo = platillos[productoSeleccionado].id;

        var requestData = {
            action: "setComentario",
            comentario: comentario,
            calificacion: calificacion,
            idAlumno: idUsuario,
            idPlatillo: idPlatillo
        };

        socket.send(JSON.stringify(requestData));
    }

}

function confirmarOrden() {

    var requestData = {
        action: "placeOrder",
        idAlumno: idUsuario,
        charola: charola,
        precioTotal: precioCharola,
        tiempoEstimado: tiempoCharola
    };

    socket.send(JSON.stringify(requestData));
}

function limpiarCharola() {

    var sessionData = {
        action: "delete",
        attrs: JSON.stringify(["charola"])
    };
    //Send data to http session
    $.post("SessionServlet", sessionData, function () {
        //Deleted
    });
}
