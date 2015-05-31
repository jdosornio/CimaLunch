/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.persistencia.dao;

import java.util.List;
import mx.uabc.mxl.sistemas.persistencia.dto.OrdenDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloOrdenadoDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.UsuarioDTO;
import mx.uabc.mxl.sistemas.persistencia.util.HibernateUtil;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jesus Donaldo
 */
public class OrdenDAO extends BaseDAO<OrdenDTO> {
    
    public boolean hasOrdered(PlatilloDTO platillo, UsuarioDTO alumno) {
        boolean ok = true;
        
        try {
            HibernateUtil.openSession();
            HibernateUtil.beginTransaction();
            //Get a list of platillos ordenados by the alumno if any
            //else means the alumno hasn't ordered yet, so then we have to return
            //false
            
            List<PlatilloOrdenadoDTO> platillosOrdenados = 
                    HibernateUtil.getSession()
                            .createCriteria(PlatilloOrdenadoDTO.class, "po")
                            .createAlias("po.orden", "orden")
                            .add(Restrictions.and(
                                    Restrictions.eq("po.platillo", platillo),
                                    Restrictions.eq("orden.alumno", alumno),
                                    Restrictions.eq("orden.realizada", true)
                            )).list();
            
            //If nothing returned means that the alumno has no orders of that
            //platillo
            if(platillosOrdenados == null || platillosOrdenados.isEmpty()) {
                ok = false;
            }
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            ok = false;
            System.out.println(e);
        } finally {
            HibernateUtil.closeSession();
        }

        return ok;
    }
}