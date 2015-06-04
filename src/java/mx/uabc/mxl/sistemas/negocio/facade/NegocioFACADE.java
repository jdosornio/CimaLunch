/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.negocio.facade;

import java.util.List;
import mx.uabc.mxl.sistemas.persistencia.dao.DAOServiceLocator;
import mx.uabc.mxl.sistemas.persistencia.dto.NegocioDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloDTO.Categoria;
import mx.uabc.mxl.sistemas.persistencia.dto.UsuarioDTO;

/**
 *
 * @author Jesus Donaldo
 */
public class NegocioFACADE extends BaseFACADE<NegocioDTO> {
    
    public List<PlatilloDTO> getPlatillosByCategoria(NegocioDTO negocio,
            Categoria categoria) {
        
        return DAOServiceLocator.getNegocioInstance()
                .getPlatillosByCategoria(negocio, categoria);
    }
    
    public List<NegocioDTO> getNegociosByAdmin(UsuarioDTO adminLocal) {
        return DAOServiceLocator.getNegocioInstance().getNegociosByAdmin(adminLocal);
    }
}