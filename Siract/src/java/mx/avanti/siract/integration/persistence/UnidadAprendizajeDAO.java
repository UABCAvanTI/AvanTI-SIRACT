/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.integration.persistence;

import java.util.List;
import mx.avanti.common.integration.persistence.HibernateUtil;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Gig@B COMPUTADORAS
 */
public class UnidadAprendizajeDAO {
     public List findByCriteria(int idAreaConocimiento){
         System.out.println("BUSCANDO UNIDADES PARA AREA: "+idAreaConocimiento);
       Session session = HibernateUtil.getSessionFactory().openSession();
       
       Criteria criteria = session.createCriteria(Unidadaprendizaje.class, "unidadAprendizaje");
       criteria.createAlias("unidadAprendizaje.areaconocimiento", "areaconocimiento");//Inner Join by default
       
       criteria.add(Restrictions.eq("areaconocimiento.acoid", idAreaConocimiento));
       criteria.addOrder(Order.asc("uapclave"));
       //criteria.setMaxResults(10);
       List listaUnidadAprendizaje = criteria.list();
       session.close();
       
       return listaUnidadAprendizaje;
    }
     
     
    public void addCatalogoReportes(Unidadaprendizaje unidadaprendizaje) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(unidadaprendizaje);
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
    
    public void updateUnidadaprendizaje(Unidadaprendizaje unidadaprendizaje) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.saveOrUpdate(unidadaprendizaje);
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
    
    public void deleteUnidadaprendizaje(int idPer) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Unidadaprendizaje unidadaprendizaje = (Unidadaprendizaje) session.load(Unidadaprendizaje.class, new Integer(idPer));
            session.delete(unidadaprendizaje);
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
    
    
    public List<Unidadaprendizaje> findAllUnidadaprendizajes(){
        List<Unidadaprendizaje> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        
        try{
            result = (List<Unidadaprendizaje>) HibernateUtil.getSession().createQuery("from Unidadaprendizaje").list();
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
    
    public List findByUnidadAcademica(int idAreaConocimiento){
         System.out.println("BUSCANDO UNIDADES PARA AREA: "+idAreaConocimiento);
       Session session = HibernateUtil.getSessionFactory().openSession();
       
       Criteria criteria = session.createCriteria(Unidadaprendizaje.class, "unidadAprendizaje");
       criteria.createAlias("unidadAprendizaje.areaconocimiento", "areaconocimiento");//Inner Join by default
       
       criteria.add(Restrictions.eq("areaconocimiento.acoid", idAreaConocimiento));
       criteria.addOrder(Order.asc("uapclave"));
       //criteria.setMaxResults(10);
       List listaUnidadAprendizaje = criteria.list();
       session.close();
       
       return listaUnidadAprendizaje;
    }
    
    
    public Unidadaprendizaje findByUnidadaprendizajeId(int idPer) {
        Unidadaprendizaje direccion = null;
        
        try {
            HibernateUtil.getSession();
            HibernateUtil.beingTransaccion();
            direccion = (Unidadaprendizaje) HibernateUtil.getSession().createQuery("from Unidadaprendizaje as unidadaprendizaje where unidadaprendizaje.idUnidadaprendizaje = :id").setString("id", String.valueOf(idPer)).uniqueResult();
        } catch (Exception x) {
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return direccion;
    }
       
        public List findByCriteria2(int idAreaconocimiento, String etapa){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Criteria criteria = session.createCriteria(Unidadaprendizaje.class, "unidadaprendizaje");
        criteria.createAlias("unidadaprendizaje.areaconocimiento", "areaconocimiento");//Inner Join by default
        
        criteria.add(Restrictions.eq("areaconocimiento.acoid", idAreaconocimiento));
        criteria.add(Restrictions.eq("uapetapaFormacion", etapa));
        criteria.setMaxResults(10);
        List listaUnidadesAprendizaje = criteria.list();
        session.close();
        
        return listaUnidadesAprendizaje;
    }
    public List findByCriteriaClave(int acoclave){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Criteria criteria = session.createCriteria(Unidadaprendizaje.class, "unidadaprendizaje");
        criteria.createAlias("unidadaprendizaje.areaconocimientos", "areaconocimientos");//Inner Join by default
        
        criteria.add(Restrictions.eq("areaconocimientos.acoclave", acoclave));
        List listaUnidadesAprendizaje = criteria.list();
        session.close();
        System.out.println("Entro a findByCriteria***");
        return listaUnidadesAprendizaje;
            
    }
}
