/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Grupo;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Manuel Papa
 */
public class FacadePlanEstudio {
      public void agregarPlanEstudio(Planestudio planEstudio){
        Planestudio resultado = null;
        if(planEstudio.getPesid()!= null){
            ServiceLocator.getInstanceBaseDAO().setTipo(Planestudio.class);
            resultado = (Planestudio) ServiceLocator.getInstanceBaseDAO().find(planEstudio.getPesid());
        }
        if(resultado != null){
            planEstudio.setPesid(resultado.getPesid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(planEstudio);
        } else{
            ServiceLocator.getInstanceBaseDAO().save(planEstudio);
        }
    }
    
    public List<Planestudio> consultaPlanestudio(){
        List<Planestudio> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Planestudio.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
        return resultado;
    }
    
    
    
    public Planestudio getPlanEstudio(int idPlanEstudio){
        ServiceLocator.getInstanceBaseDAO().setTipo(Planestudio.class);
        return (Planestudio)ServiceLocator.getInstanceBaseDAO().find(idPlanEstudio);
    }    
       public void eliminarPlanEstudio(Planestudio planestudio){
        ServiceLocator.getInstanceBaseDAO().setTipo(Planestudio.class);
        ServiceLocator.getInstanceBaseDAO().delete(planestudio);
    }
       
  
    /**************************************Metodos requeridos por generador de rportes***************************************************/
//       public List<Planestudio> findAll(){
//        List<Planestudio> result = null;
//        result = ServiceLocator.getInstancePlanestudio().findAllPlanestudios();
//        return result;
//    }
       
    
    public List<Planestudio> findByProgramaeducativo(int idPrograma){
        List<Planestudio> result = null;
        result = ServiceLocator.getInstancePlanestudio().findByCriteria(idPrograma);
        return result;
    }
    /****************************************************************************************************************************************/
 
  
    
    public List<Planestudio> findAll(){
        List<Planestudio> result = null;
        result = ServiceLocator.getInstancePlanestudio().findAllPlanestudios();
        return result;
    } 
    
    public List<Planestudio> buscarPlanEstudio(String idPuesto){
        List<Planestudio> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Planestudio.class);
        resultado = ServiceLocator.
                getInstanceBaseDAO().findPlanesWherePuesto("responsableprogramaeducativos","pedid" , "pueid", idPuesto);
        return resultado;
    }
    
        public List<Planestudio> buscarPlanEstudio(int idPE){
        List<Planestudio> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Planestudio.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findPlanesWherePE(idPE);
        return resultado;
    }
   public List<Planestudio> buscarPlanEstudioByUnidadAcademica(int idUa){
        List<Planestudio> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Planestudio.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhereB("programaeducativo.unidadacademica", "uacid",String.valueOf(idUa),"pesvigenciaPlan");
        return resultado;
    }
   
       
   public List<Planestudio> buscarPlanEstudioByUnidadAprendizae(int idUa){
        List<Planestudio> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Planestudio.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhere("Planestudio", "areaconocimientos c join c.unidadaprendizajes","uapclave",String.valueOf(idUa));
        return resultado;
    }
   
   public List<Planestudio> buscarPlanEstudioByProgramaEducativo(String idPE){
        List<Planestudio> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Planestudio.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhereB("programaeducativo", "pedid",String.valueOf(idPE),"pesvigenciaPlan");
        return resultado;
    }
   
   
   
   public List<Planestudio> buscarPlanEstudioDeAreaConocimiento(String idAco){
        List<Planestudio> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Planestudio.class);
        resultado = ServiceLocator.
                getInstanceBaseDAO().findFromWhere("Planestudio","areaconocimientos", "acoid", idAco);
        return resultado;
    }
   
   //Metodos enviados por Alan Martinez para catalogo plan de estudo
       
    public  List<Planestudio> getProfMismoPE(int pedid){
        List<Planestudio> listaPE = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Planestudio.class);
        listaPE = ServiceLocator.getInstanceBaseDAO().findFromWhere("unidadacademicas", "uacid", String.valueOf(pedid));
        return listaPE;
    }
     
//    public List<Planestudio> consultaProfPE(int peid){
//        List<Planestudio> listaPE = null;
//        ServiceLocator.getInstanceBaseDAO().setTipo(Planestudio.class);
//        listaPE = ServiceLocator.getInstanceBaseDAO().findFromWhere("programaeducativos", "pedid", String.valueOf(peid));
//        return listaPE;
//    }
    
    public List<Planestudio> getPlanMismoPE(int pedid) {
        List<Planestudio> listaPlan = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Planestudio.class);
        listaPlan = ServiceLocator.getInstanceBaseDAO().findFromWhere("programaeducativos", "pedid", String.valueOf(pedid));
        return listaPlan;
    }
    
     public List<Planestudio> getPlanAsignadoGrupo(int pedid) {
        List<Planestudio> listaPlan = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Grupo.class);
        listaPlan = ServiceLocator.getInstanceBaseDAO().findFromWhereB("planestudio","pesid", String.valueOf(pedid),"gpoid");
        return listaPlan;
    }
        
        
    public List<Planestudio> getPlanAsignadoAreaConocimiento(int pedid) {
        List<Planestudio> listaPlan = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
        listaPlan = ServiceLocator.getInstanceBaseDAO().findFromWhereB("planestudio","pesid", String.valueOf(pedid),"acoid");
        return listaPlan;
    }
    
       public List<Planestudio> findByProgramaeducativoClave(int clavePrograma){
        List<Planestudio> result = null;
        result = ServiceLocator.getInstancePlanestudio().findByCriteriaClave(clavePrograma);
        return result;
    }
    
}
