/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.negocio.uc;

import javax.enterprise.context.ApplicationScoped;
import mx.uabc.mxl.sistemas.negocio.facade.FACADEServiceLocator;
import mx.uabc.mxl.sistemas.persistencia.dto.UsuarioDTO;

/**
 *
 * @author Jesus Donaldo
 */
@ApplicationScoped
public class LoginUC {
    private static final String LOGIN_OK = "loginOk";
    private static final String LOGIN_FAIL = "loginFail";
    
    public String signIn(String usuario, String pass) {
        String response = LOGIN_FAIL;
        
        //Get user object if exists
        UsuarioDTO user = FACADEServiceLocator.getUsuarioInstance()
                .getByUsuario(usuario);
        
        //User exists, now to check if the pass is correct
        if(user != null) {
            if(user.getPass().equals(pass)) {
                //If ok return usuario and tipo and ok message
                response = LOGIN_OK + "/" + usuario + "/" + 
                        user.getTipo().toString();
            }
        }
        
        return response;
    }
    
}