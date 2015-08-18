/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.integration.persistence;

import java.util.List;
import mx.avanti.common.integration.persistence.HibernateUtil;
import mx.avanti.siract.business.entity.Areaconocimiento;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author balta
 */
public class AreaconocimientoDAO {
    
    public List findByCriteria(int idPlanestudio){
       Session session = HibernateUtil.getSessionFactory().openSession();
       
       Criteria criteria = session.createCriteria(Areaconocimiento.class, "areaconocimiento");
       criteria.createAlias("areaconocimiento.planestudio", "planestudio");//Inner Join by default
       
       criteria.add(Restrictions.eq("planestudio.pesid", idPlanestudio));
       List listaAreas = criteria.list();
       session.close();
       
       return listaAreas;
   }
    
    public void addCatalogoReportes(Areaconocimiento areaconocimiento) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(areaconocimiento);
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
    
    public void updateAreaconocimiento(Areaconocimiento areaconocimiento) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.saveOrUpdate(areaconocimiento);
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
    
    public void deleteAreaconocimiento(int idPer) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Areaconocimiento areaconocimiento = (Areaconocimiento) session.load(Areaconocimiento.class, new Integer(idPer));
            session.delete(areaconocimiento);
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
    
    
    public List<Areaconocimiento> findAllAreaconocimientos(){
        List<Areaconocimiento> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        
        try{
            result = (List<Areaconocimiento>) HibernateUtil.getSession().createQuery("from Areaconocimiento").list();
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
    
    public Areaconocimiento findByAreaconocimientoId(int idPer) {
        Areaconocimiento direccion = null;
        
        try {
            HibernateUtil.getSession();
            HibernateUtil.beingTransaccion();
            direccion = (Areaconocimiento) HibernateUtil.getSession().createQuery("from Areaconocimiento as areaconocimiento where areaconocimiento.idAreaconocimiento = :id").setString("id", String.valueOf(idPer)).uniqueResult();
        } catch (Exception x) {
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return direccion;
    }

    public List findByCriteriaClave(int progEduClave, String proEduVigencia){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Criteria criteria = session.createCriteria(Areaconocimiento.class, "areaconocimiento");
        criteria.createAlias("areaconocimiento.planestudio", "planestudio");//Inner Join by default
        criteria.createAlias("planestudio.programaeducativo", "programaeducativo");//Inner Join by default
        
        criteria.add(Restrictions.eq("planestudio.pesvigenciaPlan", proEduVigencia));
        criteria.add(Restrictions.eq("programaeducativo.pedclave", progEduClave));
        List listaAreas = criteria.list();
        session.close();
        
        return listaAreas;
    }

    public List<Areaconocimiento> findByProgramaEducativo(int idPrograma) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Areaconocimiento.class, "areaconocimiento");
        
        criteria.createAlias("areaconocimiento.programaeducativo", "programaeducativo");//Inner Join by default
        criteria.add(Restrictions.eq("programaeducativo.PEDid", idPrograma));
        List listaAreas = criteria.list();
        session.close();
        
        return listaAreas;
    }
}
