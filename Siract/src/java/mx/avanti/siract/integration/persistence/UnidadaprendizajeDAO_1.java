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
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Graciela
 */
public class UnidadaprendizajeDAO_1 {
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

    
        public List findByCriteria(int idAreaconocimiento){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Criteria criteria = session.createCriteria(Unidadaprendizaje.class, "unidadaprendizaje");
        criteria.createAlias("unidadaprendizaje.areaconocimiento", "areaconocimiento");//Inner Join by default
        
        criteria.add(Restrictions.eq("areaconocimiento.acoid", idAreaconocimiento));
        criteria.setMaxResults(10);
        List listaUnidadesAprendizaje = criteria.list();
        session.close();
        
        return listaUnidadesAprendizaje;
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
}
