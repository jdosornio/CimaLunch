/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var socket = new WebSocket("ws://localhost:50337/CimaLunch/actions");
//array for storing the negocios
var negocios;
var negocioSeleccionado;
var categoriaSeleccionada;

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
        $('#negociosList').append('<li><a href="#" onclick="activarCategorias(\'' + negociosList[i].id + '\')"><img src="data:image/png;base64,' +
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
        attrs: JSON.stringify(["usuario", "tipo"])
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

function activarCategorias(nombreNegocio) {
    $('#boton-categorias').removeClass('disabled');
    negocioSeleccionado = nombreNegocio;
}

function activarProductos(nombreCategoria) {
    $('#boton-productos').removeClass('disabled');
    categoriaSeleccionada = nombreCategoria;
    alert(negocioSeleccionado + " " + categoriaSeleccionada); //Mensaje para mostrar que si se tiene el id y la categoria
    
    //mostrar los productos en la lista
}