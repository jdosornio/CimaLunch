/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.negocio.facade;

/**
 *
 * @author Jesus Donaldo
 */
public class FACADEServiceLocator {
    private static BaseFACADE baseFACADE;
    private static UsuarioFACADE usuarioFACADE;
    private static NegocioFACADE negocioFACADE;
    private static ComentarioPlatilloFACADE comentarioPlatilloFACADE;
    private static OrdenFACADE ordenFACADE;
    private static PlatilloFACADE platilloFACADE;
    
    public static BaseFACADE getBaseInstance() {
        
        if(baseFACADE == null) {
            baseFACADE = new BaseFACADE();
        }
        
        return baseFACADE;
    }
    
    public static UsuarioFACADE getUsuarioInstance() {
        
        if(usuarioFACADE == null) {
            usuarioFACADE = new UsuarioFACADE();
        }
        
        return usuarioFACADE;
    }
    
    public static NegocioFACADE getNegocioInstance() {
        
        if(negocioFACADE == null) {
            negocioFACADE = new NegocioFACADE();
        }
        
        return negocioFACADE;
    }
    
    public static ComentarioPlatilloFACADE getComentarioPlatilloInstance() {
        
        if(comentarioPlatilloFACADE == null) {
            comentarioPlatilloFACADE = new ComentarioPlatilloFACADE();
        }
        
        return comentarioPlatilloFACADE;
    }
    
    public static OrdenFACADE getOrdenInstance() {
        
        if(ordenFACADE == null) {
            ordenFACADE = new OrdenFACADE();
        }
        
        return ordenFACADE;
    }
    
    public static PlatilloFACADE getPlatilloFACADE() {
        
        if(platilloFACADE == null) {
            platilloFACADE = new PlatilloFACADE();
        }
        
        return platilloFACADE;
    }
}