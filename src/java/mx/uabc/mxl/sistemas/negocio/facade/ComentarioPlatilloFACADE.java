/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.negocio.facade;

import java.util.List;
import mx.uabc.mxl.sistemas.persistencia.dao.DAOServiceLocator;
import mx.uabc.mxl.sistemas.persistencia.dto.ComentarioPlatilloDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloDTO;

/**
 *
 * @author Jesus Donaldo
 */
public class ComentarioPlatilloFACADE extends BaseFACADE<ComentarioPlatilloDTO>{
    
    public double getCalificacionPromedio(PlatilloDTO platillo) {
        double calificacion = 0;
        
        Object result = DAOServiceLocator.getComentarioPlatilloInstance()
                .getCalificacionPromedio(platillo);
        
        //If != null then transform to double
        if(result != null) {
            calificacion = (double) result;
        }
        
        return calificacion;
    }
    
    public List<ComentarioPlatilloDTO> getComentarios(PlatilloDTO platillo) {
        return DAOServiceLocator.getComentarioPlatilloInstance()
                .getComentarios(platillo);
    }
}