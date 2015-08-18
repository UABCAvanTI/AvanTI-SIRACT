/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Samanta Rdgz
 */
public class AsignarProfesorDelegate implements Serializable{
    private List<Profesor> listaProfesor;
    private List<Profesor> listacriteriaProfesor;
    private List<Areaconocimiento> listaAreaConocimiento;
    private List<Planestudio> listaPlanEstudio;
    private List<Programaeducativo> listaProgramaEducativo;
    
    public AsignarProfesorDelegate(){
        listaProfesor = new ArrayList<Profesor>();
        listaAreaConocimiento = new ArrayList<Areaconocimiento>();
        listaPlanEstudio = new ArrayList<Planestudio>();
        listaProgramaEducativo = new ArrayList<Programaeducativo>();
    }

    public List<Profesor> getListaProfesor() {
        listaProfesor = ServiceFacadeLocator.getInstanceFacadeProfesor().consultaProfesor();
        return listaProfesor;
    }

    public void setListaProfesor(List<Profesor> listaProfesor) {
        this.listaProfesor = listaProfesor;
    }
    
    public void agregarProfesor(Profesor profesor){
        ServiceFacadeLocator.getInstanceFacadeProfesor().agregarProfesor(profesor);
    }
    
    public void eliminarProfesor(Profesor profesor){
        ServiceFacadeLocator.getInstanceFacadeProfesor().eliminarProfesor(profesor);
    }
    
    public List<Areaconocimiento> getListaAreaConocimiento() {
        listaAreaConocimiento = ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().consultaAreaConocimiento();
        return listaAreaConocimiento;
    }

    public void setListaAreaConocimiento(List<Areaconocimiento> listaAreaConocimiento) {
        this.listaAreaConocimiento = listaAreaConocimiento;
    }
    
    public void agregarAreaConocimiento(Areaconocimiento areaconocimiento){
        ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().agregarAreaConocimiento(areaconocimiento);
    }

     public void eliminarAreaConocimiento(Areaconocimiento areaconocimiento){
        ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().eliminarAreaConocimiento(areaconocimiento);
    }

    public List<Planestudio> getListaPlanEstudio() {
        return listaPlanEstudio;
    }

    public void setListaPlanEstudio(List<Planestudio> listaPlanEstudio) {
        this.listaPlanEstudio = listaPlanEstudio;
    }

    public List<Profesor> getListacriteriaProfesor() {
        return listacriteriaProfesor;
    }

    public void setListacriteriaProfesor(List<Profesor> listacriteriaProfesor) {
        this.listacriteriaProfesor = listacriteriaProfesor;
    }
    
     
     
     //CRITERIA
     public List<Planestudio> getPlanesByPrograma(int idPrograma){
       listaPlanEstudio = ServiceFacadeLocator.getInstanceFacadePlanEstudio().findByProgramaeducativo(idPrograma);
       return listaPlanEstudio;
   }
     
//      public List<Areaconocimiento> getAreasByPlan(int idPlan){
//       listaAreaConocimiento = ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().findByPlanestudio(idPlan);
//       return listaAreaConocimiento;
//   }
//     public List<Profesor> getProfesoresByPrograma(int idPrograma){
//       listacriteriaProfesor = ServiceFacadeLocator.getInstanceFacadeProfesor().finByProfesor(idPrograma);
//       return listacriteriaProfesor;
//   }
     
     public List<Profesor> getProfesor(int idPrograma){
         listacriteriaProfesor = ServiceFacadeLocator.getInstanceFacadeProfesor().findProfesor(idPrograma);
         return listacriteriaProfesor;
     }
}
