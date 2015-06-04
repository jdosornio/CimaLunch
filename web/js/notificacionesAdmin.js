/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Agregar script a todas las paginas del Admin para que siempre escuche las notificaciones
function recibirNotificacion(response) {
    var orden = JSON.parse(response);

    alert("IdOrden: " + orden.idOrden + "\nFecha: " + orden.fecha + "\nNombre Alumno: " +
            orden.nombreAlumno + "\nPrecio total: " + orden.precioTotal);
    
    var platillos = orden.platillos;
    
    for(var i = 0; i < platillos.length; i++) {
        alert("Nombre del Platillo: " + platillos[i].nombrePlatillo + "\nCantidad: " +
                platillos[i].cantidad);
    }
}