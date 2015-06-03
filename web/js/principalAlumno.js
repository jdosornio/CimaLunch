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
    var idUsuario;
    //Get idUsuario value in the session
    $.get("SessionServlet", {action: "get", attrs: JSON.stringify(["idUsuario"])}, function (response) {
        //Success, get id usuario
        idUsuario = response[0];
        //Get remaining product info
        var requestData = {
            action: "getInfoPlatillo",
            idPlatillo: platillos[productoSeleccionado].id,
            idAlumno: idUsuario
        };
        socket.send(JSON.stringify(requestData));
    });
}

/**
 * Mostrar la informacion del platillo en el modal de detalles.
 * @param {type} infoPlatillo
 * @returns {undefined}
 */
function mostrarInfoPlatillo(infoPlatillo) {

    var platillo = JSON.parse(infoPlatillo);
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
            '<button class="btn btn-primary" type="submit" onclick="agregarACharola(\'' + productoSeleccionado + '\')">Añadir a la charola</button>' +
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
    }
    $('.modal-body').append(
            '</ul>' +
            '</div>' +
            '<form>' +
            '<label for="comentario">Dejanos tu comentario:</label>' +
            '<br>' +
            '<label for="calificacion">Calificación:</label>' +
            '<select>' +
            '<option>1</option>' +
            '<option>2</option>' +
            '<option>3</option>' +
            '<option>4</option>' +
            '<option>5</option>' +
            ' </select>' +
            '<br><br>' +
            '<textarea name="comentario" form="usrform" rows="3" cols="30"></textarea>' +
            '<br><br>' +
            ' <button class="btn btn-sm btn-primary">Enviar</button>' +
            '</form>'
            );
}

function agregarACharola(indice) {
    var cantidad = $('#cantidad').val();
    charola[cont] = platillos[indice];
    cont = +cont + +cantidad;
    $('.cont').html("");
    $('.cont').append(" " + cont + " ");
    actualizarCharola();
}

function actualizarCharola(){
        $('.dropdown-cart').append('<li>' +
            ' <span class="item">' +
            ' <span class="item-left">' +
            ' <img src="http://lorempixel.com/50/50/" alt="" />' +
            ' <span class="item-info">' +
            ' <span>Platillo 1</span>' +
            '<span>$19.50</span>' +
            '</span>' +
            '  </span>' +
            '<span class="item-right">' +
            ' <button class="btn btn-xs btn-danger pull-right">x</button>' +
            ' </span>' +
            '</span>' +
            ' </li>');
}