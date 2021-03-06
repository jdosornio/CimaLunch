/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.persistencia.dao;

import java.util.List;
import mx.uabc.mxl.sistemas.persistencia.dto.NegocioDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.OrdenDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloOrdenadoDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.UsuarioDTO;
import mx.uabc.mxl.sistemas.persistencia.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
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
    
    public List<OrdenDTO> getOrdenesAlumno(UsuarioDTO alumno) {
        List<OrdenDTO> ordenes;
        
        try {
            HibernateUtil.openSession();
            HibernateUtil.beginTransaction();
            
            //Get ordenes of alumno that are already placed.
            ordenes = HibernateUtil.getSession()
                    .createCriteria(OrdenDTO.class)
                    .add(Restrictions.and(
                            Restrictions.eq("alumno", alumno),
                            Restrictions.eq("realizada", true)
                    ))
                    .addOrder(Order.desc("fecha"))
                    .setFetchMode("platillosOrdenados", FetchMode.JOIN)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            ordenes = null;
            System.out.println(e);
        } finally {
            HibernateUtil.closeSession();
        }

        return ordenes;
    }
    
    public List<PlatilloOrdenadoDTO> getPlatillosOrdenByNegocio(OrdenDTO orden,
            NegocioDTO negocio) {
        
        List<PlatilloOrdenadoDTO> platillos = null;
        
        try {
            HibernateUtil.openSession();
            HibernateUtil.beginTransaction();
            
            //Get platillos of an order by negocio
            platillos = HibernateUtil.getSession()
                    .createCriteria(PlatilloOrdenadoDTO.class, "po")
                    .createAlias("po.platillo.negocio", "negocio")
                    .add(Restrictions.and(
                            Restrictions.eq("po.orden", orden),
                            Restrictions.eq("negocio.nombre", negocio.getNombre())
                    )).list();
            
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            platillos = null;
            System.out.println(e);
        } finally {
            HibernateUtil.closeSession();
        }

        return platillos;
    }
    
    public List<OrdenDTO> getOrdenesByNegocio(NegocioDTO negocio) {
        
        List<OrdenDTO> ordenes = null;
        
        try {
            HibernateUtil.openSession();
            HibernateUtil.beginTransaction();
            
            //Get platillos of an order by negocio
            ordenes = HibernateUtil.getSession()
                    .createCriteria(OrdenDTO.class, "orden")
                    .createAlias("orden.platillosOrdenados", "platillos")
                    .createAlias("platillos.platillo", "platillo")
                    .add(Restrictions.and(
                            Restrictions.eq("platillo.negocio", negocio),
                            Restrictions.eq("platillos.status", PlatilloOrdenadoDTO.Status.PREPARACION)
                    ))
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            
            for(OrdenDTO orden : ordenes) {
                Hibernate.initialize(orden.getPlatillosOrdenados());
            }
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            ordenes = null;
            System.out.println(e);
        } finally {
            HibernateUtil.closeSession();
        }

        return ordenes;
    }
    
    public PlatilloOrdenadoDTO getPlatilloOrdenado(OrdenDTO orden, PlatilloDTO platillo) {
        PlatilloOrdenadoDTO platilloOrdenado;
        
        try {
            HibernateUtil.openSession();
            HibernateUtil.beginTransaction();
            
            //Get platillos of an order by negocio
            platilloOrdenado = (PlatilloOrdenadoDTO) HibernateUtil.getSession()
                    .createCriteria(PlatilloOrdenadoDTO.class)
                    .add(Restrictions.and(
                            Restrictions.eq("orden", orden),
                            Restrictions.eq("platillo", platillo)
                    )).uniqueResult();

        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            platilloOrdenado = null;
            System.out.println(e);
        } finally {
            HibernateUtil.closeSession();
        }

        return platilloOrdenado;
    }
}