/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.negocio.facade;

import java.util.List;
import mx.uabc.mxl.sistemas.persistencia.dao.DAOServiceLocator;
import mx.uabc.mxl.sistemas.persistencia.dto.NegocioDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.OrdenDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloOrdenadoDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.UsuarioDTO;

/**
 *
 * @author Jesus Donaldo
 */
public class OrdenFACADE extends BaseFACADE<OrdenDTO> {
    
    public boolean hasOrdered(PlatilloDTO platillo, UsuarioDTO alumno) {
        return DAOServiceLocator.getOrdenInstance().hasOrdered(platillo, alumno);
    }
    
    public List<OrdenDTO> getOrdenesAlumno(UsuarioDTO alumno) {
        return DAOServiceLocator.getOrdenInstance().getOrdenesAlumno(alumno);
    }
    
    public List<PlatilloOrdenadoDTO> getPlatillosOrdenByNegocio(OrdenDTO orden,
            NegocioDTO negocio) {
        return DAOServiceLocator.getOrdenInstance()
                .getPlatillosOrdenByNegocio(orden, negocio);
    }
}