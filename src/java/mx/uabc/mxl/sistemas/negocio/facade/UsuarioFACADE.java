/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.negocio.facade;

import mx.uabc.mxl.sistemas.persistencia.dao.DAOServiceLocator;
import mx.uabc.mxl.sistemas.persistencia.dto.UsuarioDTO;

/**
 *
 * @author Jesus Donaldo
 */
public class UsuarioFACADE extends BaseFACADE<UsuarioDTO> {
    
    public UsuarioDTO getByUsuario(String usuario) {
        return DAOServiceLocator.getUsuarioInstance().getByUsuario(usuario);
    }
}