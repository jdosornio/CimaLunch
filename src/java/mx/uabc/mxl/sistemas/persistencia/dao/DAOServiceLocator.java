/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.persistencia.dao;

/**
 *
 * @author Jesus Donaldo
 */
public class DAOServiceLocator {
    private static BaseDAO baseDAO;
    private static UsuarioDAO usuarioDAO;
    private static NegocioDAO negocioDAO;
    
    public static BaseDAO getBaseInstance() {
        
        if(baseDAO == null) {
            baseDAO = new BaseDAO();
        }
        
        return baseDAO;
    }
    
    public static UsuarioDAO getUsuarioInstance() {
        
        if(usuarioDAO == null) {
            usuarioDAO = new UsuarioDAO();
        }
        
        return usuarioDAO;
    }
    
    public static NegocioDAO getNegocioInstance() {
        
        if(negocioDAO == null) {
            negocioDAO = new NegocioDAO();
        }
        
        return negocioDAO;
    }
}