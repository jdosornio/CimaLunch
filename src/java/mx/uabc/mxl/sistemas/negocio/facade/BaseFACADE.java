/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.negocio.facade;

import java.util.List;
import mx.uabc.mxl.sistemas.persistencia.dao.DAOServiceLocator;

/**
 *
 * @author Jesus Donaldo
 * @param <T>
 */
public class BaseFACADE<T> {
    
    public boolean saveEntity(T entity) {
        return DAOServiceLocator.getBaseInstance().saveOrUpdate(entity);
    }
    
    public boolean updateEntity(T entity) {
        return DAOServiceLocator.getBaseInstance().saveOrUpdate(entity);
    }
    
    public T getEntity(int entityId, Class<T> clazz) {
        return (T) DAOServiceLocator.getBaseInstance().get(entityId, clazz);
    }
    
    public List<T> getAllEntities(Class<T> clazz) {
        return DAOServiceLocator.getBaseInstance().getAll(clazz);
    }
    
    public boolean deleteEntity(T entity) {
        return DAOServiceLocator.getBaseInstance().delete(entity);
    }
}