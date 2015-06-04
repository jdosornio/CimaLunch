/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.persistencia.dao;

import java.util.List;
import mx.uabc.mxl.sistemas.persistencia.dto.NegocioDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloDTO.Categoria;
import mx.uabc.mxl.sistemas.persistencia.dto.UsuarioDTO;
import mx.uabc.mxl.sistemas.persistencia.util.HibernateUtil;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jesus Donaldo
 */
public class NegocioDAO extends BaseDAO<NegocioDTO> {
    
    public List<PlatilloDTO> getPlatillosByCategoria(NegocioDTO negocio,
            Categoria categoria) {
        
        List<PlatilloDTO> platillos;
        
        try {
            HibernateUtil.openSession();
            HibernateUtil.beginTransaction();
            //Get all platillos of a negocio by categoria....
            platillos = HibernateUtil.getSession()
                    .createCriteria(PlatilloDTO.class)
                    .add(Restrictions.and(
                            Restrictions.eq("negocio", negocio),
                            Restrictions.eq("categoria", categoria)
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

    public List<NegocioDTO> getNegociosByAdmin(UsuarioDTO adminLocal) {
        
        List<NegocioDTO> negocios;
        
        try {
            HibernateUtil.openSession();
            HibernateUtil.beginTransaction();
            //Get all platillos of a negocio by categoria....
            negocios = HibernateUtil.getSession()
                    .createCriteria(NegocioDTO.class)
                    .add(Restrictions.eq("administrador", adminLocal)).list();
            
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            negocios = null;
            System.out.println(e);
        } finally {
            HibernateUtil.closeSession();
        }

        return negocios;
    }
}