/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.List;
import mx.avanti.siract.business.AreaConocimientoDelegate;
import mx.avanti.siract.business.CampusDelegate;
import mx.avanti.siract.business.CicloEscolarDelegate;
import mx.avanti.siract.business.PlanEstudioDelegate;
import mx.avanti.siract.business.ProgramaEducativoDelegate;
import mx.avanti.siract.business.UnidadAcademicaDelegate;
import mx.avanti.siract.business.UnidadAprendizajeDelegate;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Cicloescolar;
import mx.avanti.siract.business.entity.Unidadaprendizaje;

/**
 *
 * @author balta
 */

//Se remplazara con lo que trabaje Sumico
public class UnidadAprendizajeBeanHelper implements Serializable {
//
//    private UnidadAprendizajeDelegate unidadAprendizajeDelegate;
//    private AreaConocimientoDelegate areaConocimientoDelegate;
//    private CicloEscolarDelegate cicloEscolarDelegate;
//    List<Unidadaprendizaje> listaFiltrada;
//
//    private Unidadaprendizaje unidadaprendizaje;
//    private Unidadaprendizaje selecUnidadaprendizaje;
//    private Areaconocimiento areaconocimiento;
//    private Cicloescolar cicloescolar;
//
//    public UnidadAprendizajeBeanHelper() {
//        try {
//            this.unidadAprendizajeDelegate = new UnidadAprendizajeDelegate();
//            this.areaConocimientoDelegate = new AreaConocimientoDelegate();
//            this.cicloEscolarDelegate = new CicloEscolarDelegate();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        unidadaprendizaje = new Unidadaprendizaje();
//        selecUnidadaprendizaje = new Unidadaprendizaje();
//        areaconocimiento = new Areaconocimiento();
//        cicloescolar = new Cicloescolar();
//    }
//
//    public UnidadAprendizajeDelegate getUnidadAprendizajeDelegate() {
//        unidadaprendizaje.setAreaconocimiento(areaconocimiento);
//        unidadaprendizaje.setCicloescolar(cicloescolar);
//        return unidadAprendizajeDelegate;
//    }
//
//    public void setUnidadAprendizajeDelegate(UnidadAprendizajeDelegate unidadAprendizajeDelegate) {
//        this.unidadAprendizajeDelegate = unidadAprendizajeDelegate;
//    }
//
//    public Unidadaprendizaje getUnidadaprendizaje() {
//        return unidadaprendizaje;
//    }
//
//    public void setUnidadaprendizaje(Unidadaprendizaje unidadaprendizaje) {
//        this.unidadaprendizaje = unidadaprendizaje;
//    }
//
//    public Unidadaprendizaje getSelecUnidadaprendizaje() {
//        return selecUnidadaprendizaje;
//    }
//
//    public void setSelecUnidadaprendizaje(Unidadaprendizaje selecUnidadaprendizaje) {
//        this.selecUnidadaprendizaje = selecUnidadaprendizaje;
//    }
//
//    public Areaconocimiento getAreaconocimiento() {
//        return areaconocimiento;
//    }
//
//    public void setAreaconocimiento(Areaconocimiento areaconocimiento) {
//        this.areaconocimiento = areaconocimiento;
//    }
//
//    public Cicloescolar getCicloescolar() {
//        return cicloescolar;
//    }
//
//    public void setCicloescolar(Cicloescolar cicloescolar) {
//        this.cicloescolar = cicloescolar;
//    }
//
//    public AreaConocimientoDelegate getAreaConocimientoDelegate() {
//        return areaConocimientoDelegate;
//    }
//
//    public void setAreaConocimientoDelegate(AreaConocimientoDelegate areaConocimientoDelegate) {
//        this.areaConocimientoDelegate = areaConocimientoDelegate;
//    }
//
//    public CicloEscolarDelegate getCicloEscolarDelegate() {
//        return cicloEscolarDelegate;
//    }
//
//    public void setCicloEscolarDelegate(CicloEscolarDelegate cicloEscolarDelegate) {
//        this.cicloEscolarDelegate = cicloEscolarDelegate;
//    }
//
//    public void setListaFiltrada(List<Unidadaprendizaje> listaFiltrada) {
//        this.listaFiltrada = listaFiltrada;
//    }
//
//    public List<Unidadaprendizaje> filtro(String busqueda) {
//        String cambioBus = busqueda.toLowerCase();
//        String cambioObj = "";
//
//        listaFiltrada = unidadAprendizajeDelegate.getListaUnidadAprendizaje();
//        for (Unidadaprendizaje ua : listaFiltrada) {
//            ua.setAreaconocimiento(areaConocimientoDelegate.findAreaConocimientoById(ua.getAreaconocimiento().getAcoid()));
//            ua.setCicloescolar(cicloEscolarDelegate.findCicloEscolarById(ua.getCicloescolar().getCesid()));
//            ua.setUnidadaprendizaje(unidadAprendizajeDelegate.findUAById(ua.getUapid()));
//        }
//        cargarPlanEstudio();
//        return listaFiltrada;
//    }
//
//    public void cargarPlanEstudio() {
//        PlanEstudioDelegate planEstudioDelegate = new PlanEstudioDelegate();
//        for (Unidadaprendizaje ua : listaFiltrada) {
//            ua.getAreaconocimiento().setPlanestudio(planEstudioDelegate.findByPlanEstudioId(
//                    ua.getAreaconocimiento().getPlanestudio().getPesid()));
//        }
//        cargarProgEd();
//    }
//    public void cargarProgEd() {
//        ProgramaEducativoDelegate progEdDelegate = new ProgramaEducativoDelegate();
//        for (Unidadaprendizaje ua : listaFiltrada) {
//            ua.getAreaconocimiento().getPlanestudio().setProgramaeducativo(
//                    progEdDelegate.findProgramaEducativoById(ua.getAreaconocimiento().
//                            getPlanestudio().getProgramaeducativo().getPedid()));
//        }
//        cargarUnidadAcademica();
//    }
//    public void cargarUnidadAcademica() {
//        UnidadAcademicaDelegate unidadAcademicaDelegate = new UnidadAcademicaDelegate();
//        for (Unidadaprendizaje ua : listaFiltrada) {
//            ua.getAreaconocimiento().getPlanestudio().getProgramaeducativo().setUnidadacademica(
//                    unidadAcademicaDelegate.findUnidadAcademicaById(
//                            ua.getAreaconocimiento().getPlanestudio().getProgramaeducativo().getUnidadacademica().getUacid()));
//        }
//        cargarCampus();
//    }
//    public void cargarCampus() {
//        CampusDelegate campusDelegate = new CampusDelegate();
//        for (Unidadaprendizaje ua : listaFiltrada) {
//            ua.getAreaconocimiento().getPlanestudio().getProgramaeducativo().getUnidadacademica().setCampus(
//                    campusDelegate.findCampusById(
//                            ua.getAreaconocimiento().getPlanestudio().getProgramaeducativo().getUnidadacademica().getCampus().getCamid()));
//        }
//    }
}
