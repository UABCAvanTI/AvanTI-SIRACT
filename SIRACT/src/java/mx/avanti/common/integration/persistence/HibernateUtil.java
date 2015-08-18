package mx.avanti.common.integration.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author Cesar Favela
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;
    private static final ThreadLocal threadSession = new ThreadLocal();
    private static final ThreadLocal threadTransaccion = new ThreadLocal();
    
    static{
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    
    public static Session getSession(){
        Session s = (Session) threadSession.get();
        try{
            if(s == null){
                s = sessionFactory.openSession();
                threadSession.set(s);
            }
        }catch(HibernateException ex){
            ex.printStackTrace();
        }
        return s;
    }
    
    public static void closeSession(){
        try{
            Session s =(Session) threadSession.get();
            threadSession.set(null);
            if(s != null && s.isOpen()){
                s.flush();
                s.close();
            }
        }catch(HibernateException ex){
            ex.printStackTrace();
        }
    }
    
    public static void beingTransaccion(){
        Transaction tx = (Transaction) threadTransaccion.get();
        try{
            if(tx == null){
                tx = getSession().beginTransaction();
                threadTransaccion.set(tx);
            }
        }catch(HibernateException ex){
            ex.printStackTrace();
        }
    }
        
        public static void commitTransaction(){
            Transaction tx = (Transaction) threadTransaccion.get();
            try{
                if(tx != null && !tx.wasCommitted() && !tx.wasRolledBack()){
                    tx.commit();
                    threadTransaccion.set(null);
                }
            }catch(HibernateException ex){
                rollbackTransaction();
                ex.printStackTrace();
            }
        }

    public static void rollbackTransaction() {
        Transaction tx = (Transaction) threadTransaccion.get();
            try{
                threadTransaccion.set(null);
                if(tx != null && !tx.wasCommitted() && !tx.wasRolledBack()){
                    tx.rollback();
                }
            }catch(HibernateException ex){
                ex.printStackTrace();
            }
    }    
}
