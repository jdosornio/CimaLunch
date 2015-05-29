/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.servicio.websocket;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import mx.uabc.mxl.sistemas.negocio.uc.ConsultarPlatillosUC;
import mx.uabc.mxl.sistemas.negocio.uc.LoginUC;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Jesus Donaldo
 */
@ApplicationScoped
@ServerEndpoint("/actions")
public class WebSocketServer {

    private static final String ACTION = "action";
    
    private static final String LOGIN = "login";
    private static final String USER = "user";
    private static final String PASS = "pass";
    
    private static final String GET_ALL_NEGOCIOS = "getAllNegocios";
    
    @Inject
    private SessionHandler sessionHandler;
    @Inject
    private LoginUC loginUC;
    @Inject
    private ConsultarPlatillosUC consultarPlatillosUC;
    
    @OnOpen
    public void open(Session session) {
        sessionHandler.addSession(session);
        System.out.println("Session open!");
    }
    
    @OnClose
    public void close(Session session) {
        sessionHandler.removeSession(session);
        System.out.println("Session remove!");
    }
    
    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(WebSocketServer.class.getName()).log(Level.SEVERE, null, error);
        System.out.println("Session Error");
    }
    
    @OnMessage
    public void handleMessage(String message, Session session) {
        try {
            //Actions
            JSONObject actionData = new JSONObject(message);
            
            String action = actionData.getString(ACTION);
            
            //Actions switch
            switch(action) {
                //Sign in with user and pass
                case LOGIN:
                    String response = loginUC.signIn(actionData.getString(USER),
                            actionData.getString(PASS));
                    
                    //Send response
                    sessionHandler.sendToSession(session, response);
                    break;
                
                case GET_ALL_NEGOCIOS:
                    response = consultarPlatillosUC.getAllNegocios();
                    
                    //Send response
                    if(response != null) {
                        sessionHandler.sendToSession(session, response);
                    }
                    break;
            }
        } catch (JSONException ex) {
            Logger.getLogger(WebSocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("message recieved: " + message);
    }
}