/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.persistencia.dao;

import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloDTO;
import mx.uabc.mxl.sistemas.persistencia.util.HibernateUtil;

/**
 *
 * @author Jesus Donaldo
 */
public class PlatilloDAO extends BaseDAO<PlatilloDTO>{
    
    public byte[] getImagen(PlatilloDTO platillo) {
        
        byte[] imagen = null;
        
        try {
            HibernateUtil.openSession();
            HibernateUtil.beginTransaction();
            //Get the imagen
            PlatilloDTO objPlatillo = (PlatilloDTO) HibernateUtil.getSession()
                    .get(PlatilloDTO.class, platillo.getId());
            
            imagen = objPlatillo.getImagen();
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            imagen = null;
            System.out.println(e);
        } finally {
            HibernateUtil.closeSession();
        }

        return imagen;
    }
}