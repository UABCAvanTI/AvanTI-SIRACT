/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.integration.persistence;

import java.util.List;
import mx.avanti.common.integration.persistence.HibernateUtil;
import mx.avanti.siract.business.entity.Profesor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author balta
 */
public class ProfesorDAO {
    
    public List findByCriteria(int idProgramaeducativo){
       Session session = HibernateUtil.getSessionFactory().openSession();
       
       Criteria criteria = session.createCriteria(Profesor.class, "profesor");
       criteria.createAlias("profesor.programaeducativo", "programaeducativo");//Inner Join by default
       
       criteria.add(Restrictions.eq("profesor.proid", idProgramaeducativo));
       criteria.setMaxResults(10);
       List listaProfesor = criteria.list();
       session.close();
       
       return listaProfesor;
    }
    public List findByUnidadAprendisajeClave(int upclave){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Criteria criteria = session.createCriteria(Profesor.class, "profesor").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);;
        criteria.createAlias("profesor.unidadaprendizajeImparteProfesors", "unidadaprendizajeImparteProfesors");//Inner Join by default
        criteria.createAlias("unidadaprendizajeImparteProfesors.unidadaprendizaje", "unidadaprendizaje");//Inner Join by default
        
           

        criteria.add(Restrictions.eq("unidadaprendizaje.uapclave",upclave));
        
        
        List listaProfesores = criteria.list();
        session.close();
        
        return listaProfesores;
    }
    
}
