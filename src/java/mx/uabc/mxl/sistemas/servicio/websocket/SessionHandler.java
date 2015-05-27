/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.servicio.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

/**
 *
 * @author Jesus Donaldo
 */
@ApplicationScoped
public class SessionHandler {
    private final Set<Session> sessions = Collections
            .synchronizedSet(new HashSet<>());
    
    public void addSession(Session session) {
        sessions.add(session);
    }
    
    public void removeSession(Session session) {
        sessions.remove(session);
    }
    
    public void sendToAllSessions(String message) {
        
        synchronized(sessions) {
            Iterator<Session> it = sessions.iterator();
            while(it.hasNext()) {
                sendToSession(it.next(), message);
            }
        }
    }
    
    public void sendToSession(Session session, String message) {
        
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(SessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}