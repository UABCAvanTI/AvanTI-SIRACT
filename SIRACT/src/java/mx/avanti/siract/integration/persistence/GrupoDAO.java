/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.integration.persistence;

import java.util.List;
import mx.avanti.common.integration.persistence.HibernateUtil;
import mx.avanti.siract.business.entity.Grupo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Gig@B COMPUTADORAS
 */
public class GrupoDAO {
     public List findByCriteria(int idPlanestudio){
       Session session = HibernateUtil.getSessionFactory().openSession();
       
       Criteria criteria = session.createCriteria(Grupo.class, "grupo");
       criteria.createAlias("grupo.planestudio", "planestudio");//Inner Join by default
       
       criteria.add(Restrictions.eq("planestudio.pesid", idPlanestudio));
       criteria.setMaxResults(10);
       List listaGrupo = criteria.list();
       session.close();
       
       return listaGrupo;
    }
     
     public List<Grupo> findByUnidadAprendisaje(int uapid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Grupo.class, "grupo");
        
        criteria.createAlias("grupo.unidadaprendizajeImparteProfesors", "unidadaprendizajeImparteProfesors");//Inner Join by default
        criteria.createAlias("unidadaprendizajeImparteProfesors.unidadaprendizaje", "unidadaprendizaje");//Inner Join by default
        
        criteria.add(Restrictions.eq("punidadaprendizaje.uapid", uapid));
        List listaGrupos = criteria.list();
        session.close();
        
        return listaGrupos;
    }
    
    public List findByUnidadAprendisajeClave(int uapclave) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Grupo.class, "grupo");
        
        criteria.createAlias("grupo.unidadaprendizajeImparteProfesors", "unidadaprendizajeImparteProfesors");//Inner Join by default
        criteria.createAlias("unidadaprendizajeImparteProfesors.unidadaprendizaje", "unidadaprendizaje");//Inner Join by default
        
        criteria.add(Restrictions.eq("unidadaprendizaje.uapclave", uapclave));
        List listaGrupos = criteria.list();
        session.close();
        
        return listaGrupos;
    }
    
    public List findByProfesorUnidadAprendisajeClave(int numempleProfesor, int uapclave) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Grupo.class, "grupo").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        
        criteria.createAlias("grupo.unidadaprendizajeImparteProfesors", "unidadaprendizajeImparteProfesors");//Inner Join by default
        criteria.createAlias("unidadaprendizajeImparteProfesors.unidadaprendizaje", "unidadaprendizaje");//Inner Join by default
        criteria.createAlias("unidadaprendizajeImparteProfesors.profesor", "profesor");//Inner Join by default
        
        criteria.add(Restrictions.eq("profesor.pronumeroEmpleado", numempleProfesor));
        criteria.add(Restrictions.eq("unidadaprendizaje.uapclave", uapclave));
        List listaGrupos = criteria.list();
        session.close();
        
        return listaGrupos;
    }
    
}
