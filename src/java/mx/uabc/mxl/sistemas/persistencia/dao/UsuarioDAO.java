/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.persistencia.dao;

import mx.uabc.mxl.sistemas.persistencia.dto.UsuarioDTO;
import mx.uabc.mxl.sistemas.persistencia.util.HibernateUtil;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jesus Donaldo
 */
public class UsuarioDAO extends BaseDAO<UsuarioDTO> {
    
    public UsuarioDTO getByUsuario(String usuario) {
        UsuarioDTO user = null;
        
        try {
            HibernateUtil.openSession();
            HibernateUtil.beginTransaction();
            //Transaction....
            user = (UsuarioDTO) HibernateUtil.getSession()
                    .createCriteria(UsuarioDTO.class)
                    .add(Restrictions.eq("usuario", usuario)).uniqueResult();
            
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            user = null;
            System.out.println(e);
        } finally {
            HibernateUtil.closeSession();
        }

        return user;
    }
}