/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Gig@B COMPUTADORAS
 */
public class PlanEstudioDelegate implements Serializable{
     private List<Planestudio> listaPlanEstudio;
    
    public PlanEstudioDelegate(){
        listaPlanEstudio = new ArrayList<Planestudio>();
    }

    public List<Planestudio> getListaPlanEstudio() {
        listaPlanEstudio = ServiceFacadeLocator.getInstanceFacadePlanEstudio().consultaPlanestudio();
        return listaPlanEstudio;
    }

    public void setListaPlanEstudio(List<Planestudio> listaPlanEstudio) {
        this.listaPlanEstudio = listaPlanEstudio;
    }
    
    public void agregarPlanEstudio(Planestudio planestudio){
        ServiceFacadeLocator.getInstanceFacadePlanEstudio().agregarPlanEstudio(planestudio);
    }
    
    public void eliminarPlanEstudio(Planestudio planestudio){
        ServiceFacadeLocator.getInstanceFacadePlanEstudio().eliminarPlanEstudio(planestudio);
    }
    
    public Planestudio findByPlanEstudioId(int id){
        return ServiceFacadeLocator.getInstanceFacadePlanEstudio().getPlanEstudio(id);
    }    
     public List<Planestudio> buscarPlanEstudio(int pedid){
        return ServiceFacadeLocator.getInstanceFacadePlanEstudio().buscarPlanEstudio(pedid);
    }
     
     //Metodos pasados por Alan para plan de estudios delegate
     
    public Profesor findProfesorFromUser(int idUsuario){
        Profesor profesor = ServiceFacadeLocator.getFacadeProfesor().findProfesorFromUser(idUsuario);
        return profesor;
    }   

    
     public List<Planestudio> getPlanEstudioPE(int pedid){
        return ServiceFacadeLocator.getInstanceFacadePlanEstudio().getProfMismoPE(pedid);
    }
     
      public List<Planestudio> getPlanMismoPE(int pedid){
        return ServiceFacadeLocator.getInstanceFacadePlanEstudio().getPlanMismoPE(pedid);
    }
    
    public List<Planestudio> getPlanAsignadoGrupo(int pedid){
        return ServiceFacadeLocator.getInstanceFacadePlanEstudio().getPlanAsignadoGrupo(pedid);
    }
    
    public List<Planestudio> getPlanAsignadoAreaConocimiento(int pedid){
        return ServiceFacadeLocator.getInstanceFacadePlanEstudio().getPlanAsignadoAreaConocimiento(pedid);
    }
   public List<Planestudio> getByProgramaeducativo(int pedclave){
        listaPlanEstudio = ServiceFacadeLocator.getInstanceFacadePlanEstudio().
                findByProgramaeducativo(pedclave);                
        return listaPlanEstudio;
    }
    
}
