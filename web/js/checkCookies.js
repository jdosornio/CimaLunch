/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function getUrlParts(url) {
    var a = document.createElement('a');
    a.href = url;

    return {
        href: a.href,
        host: a.host,
        hostname: a.hostname,
        port: a.port,
        pathname: a.pathname,
        protocol: a.protocol,
        hash: a.hash,
        search: a.search
    };
}

$("#inputUser").prop('disabled', false);
$("#inputPassword").prop('disabled', false);
$("#signIn").prop('disabled', false);

//If cookies are not enabled
if (!navigator.cookieEnabled) {
    //Get page of the current url without the domain
    var url = getUrlParts(location.href).pathname.replace("/CimaLunch/", "");

    //If empty or index.jsp current page = index.jsp
    if (!(url === "" || url === "index.jsp")) {
        //if not in index.jsp go to it
        location.replace("index.jsp");
    }
    else {
        //if current page = index.jsp show message or alert
        document.getElementsByClassName("checkbox")[0].style.display = 'block';
        $(".checkbox").append("<p>Las cookies no están habilitadas, no puedes" +
                " acceder a la página.</p>");

        //disable login
        $("#inputUser").prop('disabled', true);
        $("#inputPassword").prop('disabled', true);
        $("#signIn").prop('disabled', true);
    }
}
