/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.negocio.websocket;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Jesus Donaldo
 */
@ApplicationScoped
@ServerEndpoint("/actions")
public class WebSocketServer {
    
    @OnOpen
    public void open(Session session) {
        
    }
    
    @OnClose
    public void close(Session session) {
        
    }
    
    @OnError
    public void onError(Throwable error) {
    }
    
    @OnMessage
    public void handleMessage(String message, Session session) {
    }
}