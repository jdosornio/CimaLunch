/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function recibirNotificacion(idOrden, idPlatillo, idAlumno) {
    idOrden = parseInt(idOrden);
    idPlatillo = parseInt(idPlatillo);
    
    $.get("SessionServlet", {action: "get", attrs: JSON.stringify(["idUsuario"])}, function (response) {
        //Success, get id usuario
        var idUsuario = response[0];
        
        alert(idUsuario + " " + idAlumno);
        
        if(idUsuario === idAlumno) {
            //?? aqui deberia entrar....
            //Si el usuario actual es a quien va dirigido el mensaje....
            alert("IdOrden: " + idOrden + "\nIdPlatillo: " + idPlatillo + "\nIdAlumno: " + idAlumno);
        }
    });
}