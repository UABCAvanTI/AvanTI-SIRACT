package mx.avanti.siract.integration.persistence;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//import mx.avanti.common.integration.persistence.HibernateUtil;

import mx.avanti.common.integration.persistence.HibernateUtil;
import mx.avanti.siract.business.entity.Catalogoreportes;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Graciela
 */
public class CatalogoreportesDAO{
    
    public void addCatalogoReportes(Catalogoreportes catalogoreportes) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(catalogoreportes);
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
    
    public void updateCatalogoreportes(Catalogoreportes catalogoreportes) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.saveOrUpdate(catalogoreportes);
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
    
    public void deleteCatalogoreportes(int idPer) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Catalogoreportes catalogoreportes = (Catalogoreportes) session.load(Catalogoreportes.class, new Integer(idPer));
            session.delete(catalogoreportes);
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
    
    
    public List<Catalogoreportes> findAllCatalogoreportess(){
        List<Catalogoreportes> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        
        try{
            result = (List<Catalogoreportes>) HibernateUtil.getSession().createQuery("from Catalogoreportes").list();
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
        
    public Catalogoreportes findByCatalogoreportesId(int idPer) {
        Catalogoreportes direccion = null;
        
        try {
            HibernateUtil.getSession();
            HibernateUtil.beingTransaccion();
            direccion = (Catalogoreportes) HibernateUtil.getSession().createQuery("from Catalogoreportes as catalogoreportes where catalogoreportes.ctrid = :id").setString("id", String.valueOf(idPer)).uniqueResult();
        } catch (Exception x) {
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return direccion;
    }
    
    public void findByCriteria(String tipoCriteria){
        
        if(tipoCriteria=="Entregados"){
            
        }
    }
    
        /*public List findByCriteria(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Criteria criteria = session.createCriteria(Persona.class, "persona");
        criteria.createAlias("persona.direccion", "direccion");//Inner Join by default
        
        criteria.add(Restrictions.eq("nombre", "Abraham Canett"));
        criteria.add(Restrictions.eq("direccion.calle", "mazapil"));
        criteria.add(Restrictions.eq("direccion.numero", 1884));
        criteria.setMaxResults(10);
        List listaPersonas = criteria.list();
        session.close();
        
        return listaPersonas;
    }*/
}
