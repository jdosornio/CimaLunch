/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.persistencia.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Jesus Donaldo
 */
public class HibernateUtil {

    //Annotation based configuration
    private static SessionFactory sessionFactory;
    private static ThreadLocal<Session> session = new ThreadLocal<>();
    private static ThreadLocal<Transaction> transaction = new ThreadLocal<>();
    
    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            System.out.println("Hibernate Annotation Configuration loaded");
             
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                    applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Annotation serviceRegistry created");
             
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch(Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static Session getSession() {
        Session s = session.get();
        
        //Get session or create it if null
        if(s == null) {
            s = sessionFactory.openSession();
            session.set(s);
        }
        
        return s;
    }
    
    public static void openSession() {
        getSession();
        System.out.println("Open Session!");
    }
    
    public static void beginTransaction() {
        Transaction tx = transaction.get();
        Session s = session.get();
        
        //if transaction isnt there begin one
        if(tx == null && s != null && s.isOpen()) {
            tx = s.beginTransaction();
            transaction.set(tx);
            System.out.println("Begin Transaction!");
        }
    }
    
    public static void closeSession() {
        Session s = session.get();
        
        if(s != null && s.isOpen()) {
            s.close();
            transaction.set(null);
            session.set(null);
            System.out.println("Close session!");
        }
    }
    
    public static void rollbackTransaction() {
        Transaction tx = transaction.get();
        
        if(tx != null && !tx.wasRolledBack() && !tx.wasCommitted()) {
            tx.rollback();
            transaction.set(null);
            System.out.println("Rollback Transaction!");
        }
        
    }
    
    public static void commitTransaction() {
        Transaction tx = transaction.get();
        
        if(tx != null && !tx.wasRolledBack() && !tx.wasCommitted()) {
            tx.commit();
            transaction.set(null);
            System.out.println("Commit Transaction!");
        }
    }
    
}