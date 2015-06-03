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
import mx.uabc.mxl.sistemas.negocio.uc.ConsultarOrdenesUC;
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
    private static final String GET_PLATILLOS_BY_CATEGORIA
            = "getPlatillosByCategoria";
    private static final String SET_COMENTARIO = "setComentario";
    private static final String GET_INFO_PLATILLO = "getInfoPlatillo";

    private static final String GET_ORDENES_ALUMNO = "getOrdenesAlumno";

    private static final String ID_NEGOCIO = "idNegocio";
    private static final String CATEGORIA = "categoria";

    private static final String ID_PLATILLO = "idPlatillo";
    private static final String ID_ALUMNO = "idAlumno";

    private static final String COMENTARIO = "comentario";
    private static final String CALIFICACION = "calificacion";

    @Inject
    private SessionHandler sessionHandler;
    @Inject
    private LoginUC loginUC;
    @Inject
    private ConsultarPlatillosUC consultarPlatillosUC;
    @Inject
    private ConsultarOrdenesUC consultarOrdenesUC;

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
            switch (action) {
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
                    if (response != null) {
                        sessionHandler.sendToSession(session, response);
                    }
                    break;

                case GET_PLATILLOS_BY_CATEGORIA:
                    response = consultarPlatillosUC.getPlatillosByCategoria(
                            actionData.getInt(ID_NEGOCIO),
                            actionData.getString(CATEGORIA).toUpperCase());

                    if (response != null) {
                        sessionHandler.sendToSession(session, response);
                    }
                    break;

                case GET_INFO_PLATILLO:
                    response = consultarPlatillosUC
                            .getPlatilloInfo(actionData.getInt(ID_PLATILLO),
                                    actionData.getInt(ID_ALUMNO));

                    if (response != null) {
                        sessionHandler.sendToSession(session, response);
                    }
                    break;

                case GET_ORDENES_ALUMNO:
                    response = consultarOrdenesUC
                            .getOrdenesAlumno(actionData.getInt(ID_ALUMNO));

                    if (response != null) {
                        sessionHandler.sendToSession(session, response);
                    }
                    break;

                case SET_COMENTARIO:
                    boolean flag = consultarPlatillosUC.setComentario(
                            actionData.getString(COMENTARIO),
                            actionData.getInt(CALIFICACION),
                            actionData.getInt(ID_PLATILLO),
                            actionData.getInt(ID_ALUMNO));
                    if (flag == false) {
                        sessionHandler.sendToSession(session, "noComentario");
                    }else{
                        sessionHandler.sendToSession(session, "siComentario");
                    }
                    break;
            }
        } catch (JSONException ex) {
            Logger.getLogger(WebSocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("message recieved: " + message);
    }
}
