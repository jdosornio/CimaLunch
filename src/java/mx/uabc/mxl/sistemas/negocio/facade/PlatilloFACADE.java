/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.negocio.facade;

import mx.uabc.mxl.sistemas.persistencia.dao.DAOServiceLocator;
import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloDTO;

/**
 *
 * @author Jesus Donaldo
 */
public class PlatilloFACADE extends BaseFACADE<PlatilloDTO> {
    
    public byte[] getImagen(PlatilloDTO platillo) {
        return DAOServiceLocator.getPlatilloInstance().getImagen(platillo);
    }
}