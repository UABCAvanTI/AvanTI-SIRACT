/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.integration.persistence;

import java.util.List;
import mx.avanti.common.integration.persistence.HibernateUtil;
import mx.avanti.siract.business.entity.Areaadministrativa;
import mx.avanti.siract.business.entity.Programaeducativo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Moises
 */
public class AreaadministrativaDAO extends BaseDAO{
    public void addAreaadministrativa(Areaadministrativa areaadministrativa) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(areaadministrativa);
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
    
    public void updateAreaadministrativa(Areaadministrativa areaadministrativa) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.saveOrUpdate(areaadministrativa);
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
    
    public void deleteAreaadministrativa(int aadid) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Areaadministrativa areaadministrativa = (Areaadministrativa) session.load(Areaadministrativa.class, new Integer(aadid));
            session.delete(areaadministrativa);
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
    
    
    public List<Areaadministrativa> findAllAreaadministrativas(){
        List<Areaadministrativa> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        
        try{
            result = (List<Areaadministrativa>) HibernateUtil.getSession().createQuery("from Areaadministrativa").list();
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
    
    public Areaadministrativa findByAreaadministrativaId(int aadid) {
        Areaadministrativa direccion = null;
        
        try {
            HibernateUtil.getSession();
            HibernateUtil.beingTransaccion();
            direccion = (Areaadministrativa) HibernateUtil.getSession().createQuery("from Areaadministrativa as areaadministrativa where areaadministrativa.aadid = :id").setString("id", String.valueOf(aadid)).uniqueResult();
        } catch (Exception x) {
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return direccion;
    }
    
    //Criteria para BaseDAO FUNCIONAL! por: Moises Canett - RAPE
    //NOTA: Se debe generar un metodo por cada clase, para generar sus criterias.
    public List findByCriteria(int idUnidadacademica){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Criteria criteria = session.createCriteria(Programaeducativo.class, "programaeducativo");
        criteria.createAlias("programaeducativo.unidadacademica", "unidadacademica");//Inner Join by default
        
        criteria.add(Restrictions.eq("unidadacademica.uacid", idUnidadacademica));
        List listaProgramas = criteria.list();
        session.close();
        
        return listaProgramas;
    }
    
    public List findByCriteriaClave(int pedclave){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Criteria criteria = session.createCriteria(Areaadministrativa.class, "areaadministrativa");
        criteria.createAlias("areaadministrativa.programaeducativo", "programaeducativo");//Inner Join by default
        
        criteria.add(Restrictions.eq("programaeducativo.pedclave", pedclave));
        List listaProgramas = criteria.list();
        session.close();
        
        return listaProgramas;
    }

    /*
    public Responsableprogramaeducativo findPesponsablePrograma(Integer pedid) {
        Responsableprogramaeducativo responsable = null;
        
        try {
            HibernateUtil.getSession();
            HibernateUtil.beingTransaccion();
            responsable = (Responsableprogramaeducativo) HibernateUtil.getSession().createQuery("from ResponsableProgramaEducativo where ResponsableProgramaEducativo.ProgramaEducativo_PEDid = :id").setString("id", String.valueOf(pedid)).uniqueResult();
        } catch (Exception x) {
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return responsable;
    }
    */
}