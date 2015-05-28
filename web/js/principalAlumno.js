/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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