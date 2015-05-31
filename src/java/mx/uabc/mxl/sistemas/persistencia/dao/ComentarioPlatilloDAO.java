/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.persistencia.dao;

import java.util.List;
import mx.uabc.mxl.sistemas.persistencia.dto.ComentarioPlatilloDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloDTO;
import mx.uabc.mxl.sistemas.persistencia.util.HibernateUtil;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jesus Donaldo
 */
public class ComentarioPlatilloDAO extends BaseDAO<ComentarioPlatilloDTO> {
    
    private static final String GET_AVG_CALIF = "SELECT AVG(cp.calificacion) "
            + "FROM ComentarioPlatilloDTO AS cp "
            + "WHERE cp.platillo = :platillo";
    
    public Object getCalificacionPromedio(PlatilloDTO platillo) {
        
        Object calificacion = null;
        
        try {
            HibernateUtil.openSession();
            HibernateUtil.beginTransaction();
            //Get the average calificacion of one platillo
            
            calificacion = HibernateUtil.getSession().createQuery(GET_AVG_CALIF)
                    .setEntity("platillo", platillo)
                    .uniqueResult();
            
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            calificacion = null;
            System.out.println(e);
        } finally {
            HibernateUtil.closeSession();
        }

        return calificacion;
    }
    
    public List<ComentarioPlatilloDTO> getComentarios(PlatilloDTO platillo) {
        
        List<ComentarioPlatilloDTO> comentarios;
        
        try {
            HibernateUtil.openSession();
            HibernateUtil.beginTransaction();
            //Get the average calificacion of one platillo
            
            comentarios = HibernateUtil.getSession()
                    .createCriteria(ComentarioPlatilloDTO.class)
                    .add(Restrictions.eq("platillo", platillo))
                    .addOrder(Order.asc("fecha")).list();
            
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            comentarios = null;
            System.out.println(e);
        } finally {
            HibernateUtil.closeSession();
        }

        return comentarios;
    }
    
}