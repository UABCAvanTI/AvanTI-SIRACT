/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.integration.persistence;

import mx.avanti.common.integration.persistence.HibernateUtil;
import mx.avanti.siract.business.entity.Unidadacademica;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Moises
 */
public class UnidadacademicaDAO extends BaseDAO{
    public void addUnidadacademica(Unidadacademica unidadacademica) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(unidadacademica);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    public void updateUnidadacademica(Unidadacademica unidadacademica) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.saveOrUpdate(unidadacademica);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    public void deleteUnidadacademica(int idPer) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Unidadacademica unidadacademica = (Unidadacademica) session.load(Unidadacademica.class, new Integer(idPer));
            session.delete(unidadacademica);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    
    public List<Unidadacademica> findAllUnidadacademicas(){
        List<Unidadacademica> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        
        try{
            result = (List<Unidadacademica>) HibernateUtil.getSession().createQuery("from Unidadacademica").list();
            if(result == null){
                System.out.println("nulo");
            }
        }catch(Exception x){
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }finally{
            HibernateUtil.closeSession();
        }
        return result;
    }
    
    public Unidadacademica findByUnidadacademicaId(int idPer) {
        Unidadacademica direccion = null;
        
        try {
            HibernateUtil.getSession();
            HibernateUtil.beingTransaccion();
            direccion = (Unidadacademica) HibernateUtil.getSession().createQuery("from Unidadacademica as unidadacademica where unidadacademica.uacid = :id").setString("id", String.valueOf(idPer)).uniqueResult();
        } catch (Exception x) {
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return direccion;
    }
    
    //No se utiliza por ahora.
    public List findByCriteria(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Criteria criteria = session.createCriteria(Unidadacademica.class, "unidadacademica");
        criteria.createAlias("unidadacademica.campus", "campus");//Inner Join by default
        
        criteria.add(Restrictions.eq("campus.camnombre", "Mexicali"));
        criteria.setMaxResults(10);
        List listaUnidades = criteria.list();
        session.close();
        
        return listaUnidades;
    }
}