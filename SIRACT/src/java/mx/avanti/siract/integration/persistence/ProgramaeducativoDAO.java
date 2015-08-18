/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.integration.persistence;

import mx.avanti.common.integration.persistence.HibernateUtil;
import mx.avanti.siract.business.entity.Programaeducativo;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Moises
 */
public class ProgramaeducativoDAO extends BaseDAO{
    public void addProgramaeducativo(Programaeducativo programaeducativo) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(programaeducativo);
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
    
    public void updateProgramaeducativo(Programaeducativo programaeducativo) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.saveOrUpdate(programaeducativo);
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
    
    public void deleteProgramaeducativo(int idPer) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Programaeducativo programaeducativo = (Programaeducativo) session.load(Programaeducativo.class, new Integer(idPer));
            session.delete(programaeducativo);
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
    
    
    public List<Programaeducativo> findAllProgramaeducativos(){
        List<Programaeducativo> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        
        try{
            result = (List<Programaeducativo>) HibernateUtil.getSession().createQuery("from Programaeducativo").list();
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
    
    public Programaeducativo findByProgramaeducativoId(int idPer) {
        Programaeducativo direccion = null;
        
        try {
            HibernateUtil.getSession();
            HibernateUtil.beingTransaccion();
            direccion = (Programaeducativo) HibernateUtil.getSession().createQuery("from Programaeducativo as programaeducativo where programaeducativo.pedid = :id").setString("id", String.valueOf(idPer)).uniqueResult();
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
        criteria.setMaxResults(10);
        List listaProgramas = criteria.list();
        session.close();
        
        return listaProgramas;
    }
    
    public List findByCriteriaClave(int claveUnidadacademica){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Criteria criteria = session.createCriteria(Programaeducativo.class, "programaeducativo");
        criteria.createAlias("programaeducativo.unidadacademica", "unidadacademica");//Inner Join by default
        
        criteria.add(Restrictions.eq("unidadacademica.uacclave", claveUnidadacademica));
        List listaProgramas = criteria.list();
        session.close();
        
        return listaProgramas;
    }
}