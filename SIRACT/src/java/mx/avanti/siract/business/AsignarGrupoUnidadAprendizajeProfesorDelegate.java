/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Grupo;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.UnidadaprendizajeImparteProfesor;
import mx.avanti.siract.business.entity.Reporteavancecontenidotematico;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Gig@B COMPUTADORAS
 */
public class AsignarGrupoUnidadAprendizajeProfesorDelegate implements Serializable {

    private List<Grupo> listaGrupo;
    private List<Unidadaprendizaje> listaUnidadAprendizaje;
    private List<Profesor> listaProfesor;
    private List<Planestudio> listaPlanEstudio;
    private List<Areaconocimiento> listaAreaConocimiento;
    private List<Programaeducativo> listaProgramaEducativo;
    private List<UnidadaprendizajeImparteProfesor> listaimparteProfesors;

    public AsignarGrupoUnidadAprendizajeProfesorDelegate() {
        listaGrupo = new ArrayList<Grupo>();
        listaProfesor = new ArrayList<Profesor>();
        listaUnidadAprendizaje = new ArrayList<Unidadaprendizaje>();
        listaimparteProfesors = new ArrayList<UnidadaprendizajeImparteProfesor>();
    }

    public List<Profesor> getListaProfesor() {
        listaProfesor = ServiceFacadeLocator.getInstanceFacadeProfesor().consultaProfesor();
        return listaProfesor;
    }

    public void setListaProfesor(List<Profesor> listaProfesor) {
        this.listaProfesor = listaProfesor;
    }

    public void agregarProfesor(Profesor profesor) {
        ServiceFacadeLocator.getInstanceFacadeProfesor().agregarProfesor(profesor);
    }

    public void eliminarProfesor(Profesor profesor) {
        ServiceFacadeLocator.getInstanceFacadeProfesor().eliminarProfesor(profesor);
    }

    public List<Grupo> getListaGrupo() {
        listaGrupo = ServiceFacadeLocator.getFacadeGrupo().consultaGrupo();
        return listaGrupo;
    }

    public void setListaGrupo(List<Grupo> listaGrupo) {
        this.listaGrupo = listaGrupo;
    }

    public List<Areaconocimiento> getListaAreaConocimiento() {
        listaAreaConocimiento = ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().consultaAreaConocimiento();
        return listaAreaConocimiento;
    }

    public void setListaAreaConocimiento(List<Areaconocimiento> listaAreaConocimiento) {
        this.listaAreaConocimiento = listaAreaConocimiento;
    }

    public void agregarGrupo(Grupo grupo) {
        ServiceFacadeLocator.getFacadeGrupo().agregarGrupo(grupo);
    }

    public void eliminarGrupo(Grupo grupo) {
        ServiceFacadeLocator.getFacadeGrupo().eliminarGrupo(grupo);
    }

    public List<Unidadaprendizaje> getListaUnidadAprendizaje() {
        listaUnidadAprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().consultaUnidadAprendizaje();
        return listaUnidadAprendizaje;
    }

    public void setListaUnidadAprendizaje(List<Unidadaprendizaje> listaUnidadAprendizaje) {
        this.listaUnidadAprendizaje = listaUnidadAprendizaje;
    }

    public void agregarUnidadAprendizaje(Unidadaprendizaje unidadAprendizaje) {
        ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().agregarUnidadAprendizaje(unidadAprendizaje);
    }

    public void eliminarUnidadAprendizaje(Unidadaprendizaje unidadAprendizaje) {
        ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().eliminarUnidadAprendizaje(unidadAprendizaje);
    }

    public List<Planestudio> getListaPlanEstudio() {
        listaPlanEstudio = ServiceFacadeLocator.getInstanceFacadePlanEstudio().consultaPlanestudio();
        return listaPlanEstudio;
    }

    public void setListaPlanEstudio(List<Planestudio> listaPlanEstudio) {
        this.listaPlanEstudio = listaPlanEstudio;
    }

    //CRITERIAS 
    public List<Profesor> getProfesoresByPrograma(int idPrograma) {
        listaProfesor = ServiceFacadeLocator.getInstanceFacadeProfesor().findProfesor(idPrograma);
        return listaProfesor;
    }

//     public List<Unidadaprendizaje> getUnidadAprendizajeByAreaConocimiento(int idAreaCono){
//          listaUnidadAprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().finByAreaConocimiento(idAreaCono);
//          return listaUnidadAprendizaje;
//      }
    public List<Planestudio> getPlanesByPrograma(int idPrograma) {
        listaPlanEstudio = ServiceFacadeLocator.getInstanceFacadePlanEstudio().findByProgramaeducativo(idPrograma);
        return listaPlanEstudio;
    }

//        public List<Areaconocimiento> getAreasByPlan(int idPlan){
//       listaAreaConocimiento = ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().findByPlanestudio(idPlan);
//       return listaAreaConocimiento;
//   }
    public List<Grupo> getGruposByPlan(int idPlan) {
        listaGrupo = ServiceFacadeLocator.getFacadeGrupo().finByPlan(idPlan);
        return listaGrupo;
    }

    public List<Profesor> getProfesor(int idPrograma) {
        listaProfesor = ServiceFacadeLocator.getInstanceFacadeProfesor().findProfesor(idPrograma);
        return listaProfesor;
    }

    public List<UnidadaprendizajeImparteProfesor> getListaimparteProfesors() {
        listaimparteProfesors = ServiceFacadeLocator.getFacadeUnidadaprendisajeimparteprofesor().consultaUniAprenImparteProfe();
        return listaimparteProfesors;
    }

    public void setListaimparteProfesors(List<UnidadaprendizajeImparteProfesor> listaimparteProfesors) {
        this.listaimparteProfesors = listaimparteProfesors;
    }

    public void agregarUniAprenImparteProfe(UnidadaprendizajeImparteProfesor imparteProfesor) {
        ServiceFacadeLocator.getFacadeUnidadaprendisajeimparteprofesor().agregarUniAprenImparteProfe(imparteProfesor);
    }

    public void eliminarUniAprenImparteProfe(UnidadaprendizajeImparteProfesor imaparteProfesor) {
        ServiceFacadeLocator.getFacadeUnidadaprendisajeimparteprofesor().eliminarUniAprenImparteProfe(imaparteProfesor);
    }

    public List<Programaeducativo> getListaProgramaEducativo() {
        listaProgramaEducativo = ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().consultaProgramaEducativo();
        return listaProgramaEducativo;
    }

    public void setListaProgramaEducativo(List<Programaeducativo> listaProgramaEducativo) {
        this.listaProgramaEducativo = listaProgramaEducativo;
    }

    public List<UnidadaprendizajeImparteProfesor> getAsignacionPorGPO(int idGrupo) {
        return ServiceFacadeLocator.getFacadeUnidadaprendisajeimparteprofesor().getAsignacionPorGPO(idGrupo);
    }
    
//    public List<UnidadaprendizajeImparteProfesor> getReporteUAIP(int idUAIP){
//        return ServiceFacadeLocator.getFacadeReporteavancecontenidotematico().getimparteProfesorByUnidadAprendisaje(idUAIP);
//    }
    
    public List<Reporteavancecontenidotematico> getReporteUAIP(int idUAIP){
        return ServiceFacadeLocator.getFacadeReporteavancecontenidotematico().getReportesAvanceByUinidadimparte(idUAIP);
    }

}
