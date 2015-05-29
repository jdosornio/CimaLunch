/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var socket = new WebSocket("ws://localhost:8080/CimaLunch/actions");
socket.onmessage = onMessage;
socket.onopen = onOpen;

function onMessage(event) {
    var response = JSON.parse(event.data);
    
    //Remove all the innecessary data
    var list = response.list[0];
    
    //Get the true data list
    var negociosList = list[Object.keys(list)[0]];
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