/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.servicio.session;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

/**
 *
 * @author Jesus Donaldo
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {

    private final static String ACTION = "action";
    private final static String SAVE = "save";
    private final static String DELETE = "delete";
    private final static String ATTRS = "attrs";
    private final static String VALUES = "values";
    private final static String GET = "get";
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        //Get user session in browser
        HttpSession session = request.getSession();

        //Get the action between save or delete an attribute
        String action = request.getParameter(ACTION);
        
        switch (action) {
            case SAVE:
                try {
                    //Get the array of attributes and values
                    JSONArray attrs = new JSONArray(request.getParameter(ATTRS));
                    JSONArray values = new JSONArray(request.getParameter(VALUES));

                    //Set every value of every attribute
                    for (int i = 0; i < attrs.length(); i++) {
                        System.out.println("Atribute: " + attrs.getString(i));
                        System.out.println("Value: " + values.getString(i));

                        session.setAttribute(attrs.getString(i),
                                values.getString(i));
                    }

                } catch (JSONException ex) {
                    Logger.getLogger(SessionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case DELETE:
                try {
                    JSONArray attrs = new JSONArray(request.getParameter(ATTRS));

                    //Delete all attributes
                    for (int i = 0; i < attrs.length(); i++) {
                        session.removeAttribute(attrs.getString(i));
                    }
                } catch (JSONException ex) {
                    Logger.getLogger(SessionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        //Prepare to return data
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        response.setContentType("application/json");
        
        //Get user session in browser
        HttpSession session = request.getSession();

        //Get the action between save or delete an attribute
        String action = request.getParameter(ACTION);

        switch (action) {
            case GET:
                try {
                    //Get the array of attributes
                    JSONArray attrs = new JSONArray(request.getParameter(ATTRS));
                    JSONArray values = new JSONArray();
                    
                    //Get every value of every attribute and put it in the response
                    for (int i = 0; i < attrs.length(); i++) {
                        System.out.println("Atribute: " + attrs.getString(i));

                        values.put(session.getAttribute(attrs.getString(i)));
                        
                        System.out.println("Values: " + session
                                .getAttribute(attrs.getString(i)));
                    }

                    values.put(3);
                    //Send response
                    response.getWriter().print(values.toString());
                    
                } catch (JSONException ex) {
                    Logger.getLogger(SessionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }
}
