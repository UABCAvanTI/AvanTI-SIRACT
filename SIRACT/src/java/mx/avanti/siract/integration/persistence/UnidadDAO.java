/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.integration.persistence;

import java.util.List;
import mx.avanti.common.integration.persistence.HibernateUtil;
import mx.avanti.siract.business.entity.Unidad;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Moises
 */
public class UnidadDAO {
    public void addUnidad(Unidad unidad) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(unidad);
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
    
    public void updateUnidad(Unidad unidad) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.saveOrUpdate(unidad);
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
    
    public void deleteUnidad(int idUni) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Unidad unidad = (Unidad) session.load(Unidad.class, new Integer(idUni));
            session.delete(unidad);
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
    
    
    public List<Unidad> findAllUnidads(){
        List<Unidad> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        
        try{
            result = (List<Unidad>) HibernateUtil.getSession().createQuery("from Unidad").list();
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
    
    public Unidad findByUnidadId(int idUni) {
        Unidad direccion = null;
        
        try {
            HibernateUtil.getSession();
            HibernateUtil.beingTransaccion();
            direccion = (Unidad) HibernateUtil.getSession().createQuery("from Unidad as unidad where unidad.uniid = :id").setString("id", String.valueOf(idUni)).uniqueResult();
        } catch (Exception x) {
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return direccion;
    }

    
        public Unidad findByCriteria(int idUnidad){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Criteria criteria = session.createCriteria(Unidad.class);
//        criteria.createAlias("unidad.unidad", "unidad");//Inner Join by default
        
        criteria.add(Restrictions.eq("uniid", String.valueOf(idUnidad)));
        criteria.setMaxResults(10);
        Unidad u = (Unidad) criteria.uniqueResult();
        session.close();
        
        return u;
    }
}
