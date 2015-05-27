/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.servicio.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jesus Donaldo
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        //Get user session in browser
        HttpSession session = request.getSession();
        
        //Get the action between save or delete an attribute
        String action = request.getParameter("action");
        
        switch(action) {
            case "save":
                session.setAttribute(request.getParameter("attr"),
                        request.getParameter("value"));
                break;
            case "delete":
                session.removeAttribute(request.getParameter("attr"));
                break;
        }
    }
}