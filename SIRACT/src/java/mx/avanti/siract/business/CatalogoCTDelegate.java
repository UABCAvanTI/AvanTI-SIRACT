/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.List;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Practicalaboratorio;
import mx.avanti.siract.business.entity.Practicascampo;
import mx.avanti.siract.business.entity.Practicataller;
//import mx.avanti.siract.business.entity.Puesto;
//import mx.avanti.siract.business.entity.Responsableprogramaeducativo;
import mx.avanti.siract.business.entity.Subtemaunidad;
import mx.avanti.siract.business.entity.Temaunidad;
import mx.avanti.siract.business.entity.Unidad;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Moises
 */
public class CatalogoCTDelegate implements Serializable{
    private List<Planestudio> planesestudio;
//    private Puesto puestoProfesor;
//    private Responsableprogramaeducativo responsablePE;
    private Unidadaprendizaje unidadaprendizaje;
    private Unidad unidad;
    private Temaunidad temaunidad;
    
    //Método que funciona con BD 2.8.1
    /*public Puesto getPuestoProfesor(String idProfesor){
        puestoProfesor = ServiceFacadeLocator.getFacadePuesto().obtenerPuestoProfesor(idProfesor);
        return puestoProfesor;
    }*/
    
    //    public Responsableprogramaeducativo getResponsablePE(int idProfesor){
    //        responsablePE = ServiceFacadeLocator.getFacadeResponsableprogramaeducativo().obtenerResponsableprogramaeducativoProfesor(idProfesor);
    //        return responsablePE;
    //    }
    //    
    public List<Planestudio> getListaPlanesEstudio(int idPuesto){
        planesestudio = ServiceFacadeLocator.getInstanceFacadePlanEstudio().buscarPlanEstudio(idPuesto);
        return planesestudio;
    }
       
        public Unidadaprendizaje getUnidadaprendizajeById(int id){
        unidadaprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().find(id);
        return unidadaprendizaje;
    }
    
    public Unidadaprendizaje getUnidadaprendizajeById(int id,String acoId){
        unidadaprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().find(id,acoId);
        return unidadaprendizaje;
    }
    
    public Unidad getUnidadById(int id){
        unidad = ServiceFacadeLocator.getfacadeUnidad().find(id);
        return unidad;
    }
    
    public Temaunidad getTemaunidadById(int id){
        temaunidad = ServiceFacadeLocator.getfacadeTemaUnidad().find(id);
        return temaunidad;
    }
    
    public void eliminarUnidad(Unidad unidad){
        ServiceFacadeLocator.getfacadeUnidad().eliminarUnidad(unidad);
    }
    
    public void eliminarTemaUnidad(Temaunidad temaunidad){
        ServiceFacadeLocator.getfacadeTemaUnidad().eliminarTemaunidad(temaunidad);
    }
    
    public void eliminarSubtemaunidad(Subtemaunidad subtemaunidad){
        ServiceFacadeLocator.getFacadeSubtemaunidad().eliminarSubtemaunidad(subtemaunidad);
    }
    
    public void eliminarPracticalaboratorio(Practicalaboratorio pl){
        ServiceFacadeLocator.getfacadePracticasLab().eliminarPracticasLab(pl);
    }
    
    public void eliminarPracticataller(Practicataller pt){
        ServiceFacadeLocator.getfacadePracticasTall().eliminarPracticasTall(pt);
    }
    
    public void eliminarPracticacampo(Practicascampo pc){
        ServiceFacadeLocator.getFacadePracticasCampo().eliminarPracticasCampo(pc);
    }
    public List<Planestudio> getListaPlanesEstudioByUnidadAcademica(int idUa){
        planesestudio = ServiceFacadeLocator.getInstanceFacadePlanEstudio().buscarPlanEstudioByUnidadAcademica(idUa);
        return planesestudio;
    }
    
    
    public List<Planestudio> getListaPlanesEstudioByUnidadAprendizaje(int idUa){
        planesestudio = ServiceFacadeLocator.getInstanceFacadePlanEstudio().buscarPlanEstudioByUnidadAprendizae(idUa);
        return planesestudio;
    }
    public List<Planestudio> getListaPlanesEstudioByProgramaEducativo(String idPE){
        planesestudio = ServiceFacadeLocator.getInstanceFacadePlanEstudio().buscarPlanEstudioByProgramaEducativo(idPE);
        return planesestudio;
    }
    
    public List<Planestudio> getListaPlanesEstudioByAreaConocimiento(String idAco){
        planesestudio = ServiceFacadeLocator.getInstanceFacadePlanEstudio().buscarPlanEstudioDeAreaConocimiento(idAco);
        return planesestudio;
    }
    
}
