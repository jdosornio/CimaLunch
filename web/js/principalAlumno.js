/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var socket = new WebSocket("ws://localhost:8080/CimaLunch/actions");
//array for storing the negocios
var negocios;
//array for storing platillos
var platillos;

var negocioSeleccionado;
var productoSeleccionado;

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

    $('#productosList').html("");
    for (var i = 0; i < platillosList.length; i++) {
        var indice = i;
        $('#productosList').append('<li><a href="#" onclick="mostrarProducto(\'' + indice + '\')"><span class="item-info">'
                + platillosList[i].nombre + ' $' + platillosList[i].precio + '</span></a></li>');
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

function activarCategorias(indiceNegocio) {
    $('#boton-categorias').removeClass('disabled');
    negocioSeleccionado = indiceNegocio;
    document.getElementById('logoDireccion').setAttribute('src', 'data:image/png;base64,' + negocios[negocioSeleccionado].imagen);
}

function activarProductos(nombreCategoria) {
    $('#boton-productos').removeClass('disabled');

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

function mostrarProducto(indiceProducto) {
    productoSeleccionado = indiceProducto;
    var idUsuario;
    

    //Get idUsuario value in the session
    $.get("SessionServlet", {action: "get", attrs: JSON.stringify(["idUsuario"])}, function(response) {
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

function mostrarInfoPlatillo(infoPlatillo) {
    
    var platillo = JSON.parse(infoPlatillo);
    
    $('#nombreProducto').html(platillos[productoSeleccionado].nombre);
    $('#descripcionProducto').html(' <strong>Descripción: </strong>' + platillos[productoSeleccionado].descripcion);
    $('#calificacion').val(platillo.opinion);
    document.getElementById("precio").setAttribute('value', '$' + platillos[productoSeleccionado].precio);
    $('#tiempo').val(platillos[productoSeleccionado].tiempoPreparacion);
    
    //Show image
    $('#imagenPlatillo').attr('src', 'data:image/png;base64,' + platillo.imagen);
    
    //Test
    alert("Platillo ordenado: " + platillo.ordenado + "\n");
    
    var comentarios = platillo.comentarios;
    //Test comentarios
    for(var i = 0; i < comentarios.length; i++) {
        alert("Nombre: " + comentarios[i].alumno +  "\nFecha: " + comentarios[i].fecha + "\nCalificación: " + 
                comentarios[i].calificacion + "\nComentario: " + 
                comentarios[i].comentario);
    }
    
}