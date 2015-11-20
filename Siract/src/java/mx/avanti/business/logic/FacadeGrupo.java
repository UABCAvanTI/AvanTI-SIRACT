/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.Grupo;
import mx.avanti.siract.business.entity.UnidadaprendizajeImparteProfesor;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Manuel Papa
 */
public class FacadeGrupo {
      public void agregarGrupo(Grupo grupo){
        Grupo resultado = null;
        if(grupo.getGpoid()!= null){
            ServiceLocator.getInstanceBaseDAO().setTipo(Grupo.class);
            resultado = (Grupo) ServiceLocator.getInstanceBaseDAO().find(grupo.getGpoid());
        }
        if(resultado != null){
            grupo.setGpoid(resultado.getGpoid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(grupo);
        } else{
            ServiceLocator.getInstanceBaseDAO().save(grupo);
        }
    }
    
    public List<Grupo> consultaGrupo(){
        List<Grupo> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Grupo.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
        return resultado;
    }
       public Grupo buscarGrupo(int id){
        Grupo resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Grupo.class);
        resultado = (Grupo)ServiceLocator.getInstanceBaseDAO().find2("Grupo",id);
        return resultado;
    }
    
    public List<Grupo> buscarGrupos(String de, String campo, String criterio){
       List<Grupo> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Grupo.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhere("Grupo",de,campo,criterio);
        return resultado;
    }
        public List<Grupo> buscarGruposdeUnidad(String de,String campo,String criterio,String de2,String campo2,String criterio2,String order){
       List<Grupo> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Grupo.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhere("Grupo",de,campo,criterio);
        return resultado;
    }
        
               public Grupo getGrupo(int idGrupo){
        ServiceLocator.getInstanceBaseDAO().setTipo(Grupo.class);
        return (Grupo)ServiceLocator.getInstanceBaseDAO().find(idGrupo);
    }
               
               


    public void eliminarGrupo(Grupo grupo) {
        ServiceLocator.getInstanceBaseDAO().setTipo(Grupo.class);
        ServiceLocator.getInstanceBaseDAO().delete(grupo);
    }

 

  

    //CRITERIA
    public List<Grupo> finByPlan(int idPlan) {
        List<Grupo> result = null;
        result = ServiceLocator.getInstanceGrupoDAO().findByCriteria(idPlan);
        return result;
    }  
    
        public List<Grupo> getGpoMismoPlan(int idGrupo) {
        List<Grupo> listaGpo = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Grupo.class);
        listaGpo = ServiceLocator.getInstanceBaseDAO().findFromWhere("planestudio", "pesid", String.valueOf(idGrupo));
        return listaGpo;
    }
    
    public List<Grupo> getGpoAsignado(int idGrupo) {
        List<Grupo> listaGpo = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(UnidadaprendizajeImparteProfesor.class);
        listaGpo = ServiceLocator.getInstanceBaseDAO().findFromWhereB("grupo", "gpoid", String.valueOf(idGrupo),"uipid");
        return listaGpo;
    } 
     public List<Grupo> getGruposByUnidadAprendisaje(int uapid) {
        List<Grupo> result = null;
        result = ServiceLocator.getInstanceGrupoDAO().findByUnidadAprendisaje(uapid);
        return result;
    }
    
    public List<Grupo> getGruposByUnidadAprendisajeClave(int uapclave) {
        List<Grupo> result = null;
        result = ServiceLocator.getInstanceGrupoDAO().findByUnidadAprendisajeClave(uapclave);
        return result;
    }
    
     public List<Grupo> getGruposByProfesorUnidadAprendisajeClave(int numempleProfesor, int uapclave) {
        List<Grupo> result = null;
        result = ServiceLocator.getInstanceGrupoDAO().findByProfesorUnidadAprendisajeClave(numempleProfesor,uapclave);
        return result;
    }
               
}
