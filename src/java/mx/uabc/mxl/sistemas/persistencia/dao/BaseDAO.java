/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.persistencia.dao;

import java.util.List;
import mx.uabc.mxl.sistemas.persistencia.util.HibernateUtil;

/**
 *
 * @author Jesus Donaldo
 * @param <T>
 */
public class BaseDAO<T> {
    
    public boolean saveOrUpdate(T entity) {
        
        boolean ok = true;
        
        try {
            HibernateUtil.openSession();
            HibernateUtil.beginTransaction();
            //Transaction....
            HibernateUtil.getSession().saveOrUpdate(entity);
            
            HibernateUtil.commitTransaction();
            
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            ok = false;
            System.out.println(e);
        } finally {
            HibernateUtil.closeSession();
        }

        return ok;
    }
    
    public boolean save(T entity) {
        
        boolean ok = true;
        
        try {
            HibernateUtil.openSession();
            HibernateUtil.beginTransaction();
            //Transaction....
            HibernateUtil.getSession().save(entity);
            
            HibernateUtil.commitTransaction();
            
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            ok = false;
            System.out.println(e);
        } finally {
            HibernateUtil.closeSession();
        }

        return ok;
    }
    
    public T get(int id, Class<T> clazz) {
        
        T entity = null;
        
        try {
            HibernateUtil.openSession();
            HibernateUtil.beginTransaction();
            //Transaction....
            entity = (T) HibernateUtil.getSession().get(clazz, id);
            
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            entity = null;
            System.out.println(e);
        } finally {
            HibernateUtil.closeSession();
        }

        return entity;
    }
    
    public List<T> getAll(Class<T> clazz) {
        
        List<T> entities;
        
        try {
            HibernateUtil.openSession();
            HibernateUtil.beginTransaction();
            //Transaction....
            entities = HibernateUtil.getSession().createCriteria(clazz).list();
            
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            entities = null;
            System.out.println(e);
        } finally {
            HibernateUtil.closeSession();
        }

        return entities;
    }
    
    public boolean delete(T entity) {
        
        boolean ok = true;
        
        try {
            HibernateUtil.openSession();
            HibernateUtil.beginTransaction();
            //Transaction....
            HibernateUtil.getSession().delete(entity);
            
            HibernateUtil.commitTransaction();
            
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