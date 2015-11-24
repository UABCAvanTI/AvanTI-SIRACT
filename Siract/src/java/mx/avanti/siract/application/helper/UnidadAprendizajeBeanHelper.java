/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.avanti.siract.business.AreaConocimientoDelegate;
import mx.avanti.siract.business.CampusDelegate;
import mx.avanti.siract.business.CicloEscolarDelegate;
import mx.avanti.siract.business.PlanEstudioDelegate;
import mx.avanti.siract.business.ProgramaEducativoDelegate;
import mx.avanti.siract.business.UnidadAcademicaDelegate;
import mx.avanti.siract.business.UnidadAprendizajeDelegate;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Cicloescolar;
import mx.avanti.siract.business.entity.Unidadaprendizaje;

/**
 *
 * @author balta
 */
//Se remplazara con lo que trabaje Sumico
public class UnidadAprendizajeBeanHelper implements Serializable {

//<editor-fold defaultstate="collapsed" desc="Declaracion de objetos de clase">
    private UnidadAprendizajeDelegate UADelegate;
//    private AreaConocimientoDelegate AreaConDelegate;
    private PlanEstudioDelegate planEstudioDelegate;
    private AreaConocimientoDelegate areaConocimientoDelegate;
    private ProgramaEducativoDelegate programaEducativoDelegate;

//</editor-fold>    
//<editor-fold defaultstate="collapsed" desc="Declaracion de objetos tipo lista">
    private List<Unidadaprendizaje> listaFiltrada;
    private List<Unidadaprendizaje> listaUASeleccionada;

    private List<String> listaAreaCon;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Declaracion de objetos entidad">
    private Unidadaprendizaje unidadAprendizaje;
    private Programaeducativo programaEducativo;
    private Planestudio planEstudio;
    private Areaconocimiento areaConocimiento;
    private Cicloescolar cicloEscolar;

    private Unidadaprendizaje selecUA;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Getters y Setters de objetos de clase">
    public UnidadAprendizajeDelegate getUADelegate() {
        return UADelegate;
    }

    public void setUADelegate(UnidadAprendizajeDelegate UADelegate) {
        this.UADelegate = UADelegate;
    }

    public PlanEstudioDelegate getPlanEstudioDelegate() {
        return planEstudioDelegate;
    }

    public void setPlanEstudioDelegate(PlanEstudioDelegate planEstudioDelegate) {
        this.planEstudioDelegate = planEstudioDelegate;
    }

    public AreaConocimientoDelegate getAreaConocimientoDelegate() {
        return areaConocimientoDelegate;
    }

    public void setAreaConocimientoDelegate(AreaConocimientoDelegate areaConocimientoDelegate) {
        this.areaConocimientoDelegate = areaConocimientoDelegate;
    }

    public ProgramaEducativoDelegate getProgramaEducativoDelegate() {
        return programaEducativoDelegate;
    }

    public void setProgramaEducativoDelegate(ProgramaEducativoDelegate programaEducativoDelegate) {
        this.programaEducativoDelegate = programaEducativoDelegate;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Getters y Setters de objetos tipo lista">
    public List<Unidadaprendizaje> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Unidadaprendizaje> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public List<Unidadaprendizaje> getListaUASeleccionada() {
        return listaUASeleccionada;
    }

    public void setListaUASeleccionada(List<Unidadaprendizaje> listaUASeleccionada) {
        this.listaUASeleccionada = listaUASeleccionada;
    }

    public List<String> getListaAreaCon() {
        return listaAreaCon;
    }

    public void setListaAreaCon(List<String> listaAreaCon) {
        this.listaAreaCon = listaAreaCon;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Getters y Setters de objetos entidad">
    public Unidadaprendizaje getUnidadAprendizaje() {
        return unidadAprendizaje;
    }

    public void setUnidadAprendizaje(Unidadaprendizaje unidadAprendizaje) {
        this.unidadAprendizaje = unidadAprendizaje;
    }

    public Programaeducativo getProgramaEducativo() {
        return programaEducativo;
    }

    public void setProgramaEducativo(Programaeducativo programaEducativo) {
        this.programaEducativo = programaEducativo;
    }

    public Planestudio getPlanEstudio() {
        return planEstudio;
    }

    public void setPlanEstudio(Planestudio planEstudio) {
        this.planEstudio = planEstudio;
    }

    public Areaconocimiento getAreaConocimiento() {
        return areaConocimiento;
    }

    public void setAreaConocimiento(Areaconocimiento areaConocimiento) {
        this.areaConocimiento = areaConocimiento;
    }

    public Unidadaprendizaje getSelecUA() {
        return selecUA;
    }

    public void setSelecUA(Unidadaprendizaje selecUA) {
        this.selecUA = selecUA;
    }

    public Cicloescolar getCicloEscolar() {
        return cicloEscolar;
    }

    public void setCicloEscolar(Cicloescolar cicloEscolar) {
        this.cicloEscolar = cicloEscolar;
    }
//</editor-fold>

    public UnidadAprendizajeBeanHelper() {
        try {
            this.UADelegate = new UnidadAprendizajeDelegate();
            planEstudioDelegate = new PlanEstudioDelegate();
            areaConocimientoDelegate = new AreaConocimientoDelegate();
            programaEducativoDelegate = new ProgramaEducativoDelegate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        unidadAprendizaje = new Unidadaprendizaje();
        programaEducativo = new Programaeducativo();
        planEstudio = new Planestudio();
        areaConocimiento = new Areaconocimiento();
        cicloEscolar = new Cicloescolar();
        selecUA = new Unidadaprendizaje();
    }

//<editor-fold defaultstate="collapsed" desc="metodos">
    public List<Unidadaprendizaje> filtrado(String campo, String busqueda) {
        listaFiltrada = UADelegate.getListaUnidadAprendizaje();
        String cambiobus = busqueda.toLowerCase();
        String cambioObj = "";
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(UnidadAprendizajeBeanHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (busqueda.trim().length() > 0) {
            listaFiltrada.clear();
            for (Unidadaprendizaje ua : UADelegate.getListaUnidadAprendizaje()) {
                cambioObj = ua.getUapnombre().toLowerCase();
                if (cambioObj.startsWith(cambiobus)) {
                    listaFiltrada.add(ua);
                } else {
                    String cambioObjNum = Integer.toString(ua.getUapclave());
                    if (cambioObjNum.startsWith(busqueda)) {
                        listaFiltrada.add(ua);
                    }
                }
            }
        }

        return listaFiltrada;
    }

    public List<String> getListaAreaConocimiento(Unidadaprendizaje ua) {
        List<Areaconocimiento> lista = null;
        System.out.println("entrando al metodo" + ua.getUapid());
        listaAreaCon = new ArrayList();
        try {
            if (ua.getUapid() > 0) {
                System.out.println("entro a la validacion");
                lista = areaConocimientoDelegate.getAreaPorUA(ua.getUapid());
                System.out.println("tamaño de la lista");
                System.out.println(lista.size());
                for (Areaconocimiento ac : lista) {
                    listaAreaCon.add(ac.getAconombre());
                }
            } else {
                //lista = 
            }

        } catch (NullPointerException e) {

        }
        System.out.println("tamaño final " + listaAreaCon.size());
        return listaAreaCon;
    }

    public void seleccionarRegistro() {
        for (Unidadaprendizaje ua : UADelegate.getListaUnidadAprendizaje()) {
            if (ua.getUapid().equals(selecUA.getUapid())) {
                unidadAprendizaje = ua;
                setCicloEscolar(listaUASeleccionada.get(0).getCicloescolar());
                setAreaConocimiento(getAreaConocimientoDelegate().getAreaPorUA(getUnidadAprendizaje().getUapid()).get(0));
                setPlanEstudio(getPlanEstudioDelegate().findByPlanEstudioId(getAreaConocimiento().getPlanestudio().getPesid()));
                setProgramaEducativo(getProgramaEducativoDelegate().findProgramaEducativoById(getPlanEstudio().getProgramaeducativo().getPedid()));
//                setCicloEscolar(UABeanHelper.getListaUASeleccionada().get(0).getCicloescolar());

            }
        }
    }

    public void eliminarDeLista(int id) {
        for (Unidadaprendizaje UniApen : listaUASeleccionada) {
            if (UniApen.getUapid().equals(id)) {
                int index = listaUASeleccionada.indexOf(UniApen);
                listaUASeleccionada.remove(index);
                break;
            }
        }
    }

//    public List<Areaconocimiento> consultaAreaCon(){
//        List<Areaconocimiento> ac;
//        List<Areaconocimiento> ac2 = new ArrayList<Areaconocimiento>();
//        ac = AreaConDelegate.getListaAreaConocimiento();
//        for(int i = 0; i < )
//    }
//</editor-fold>
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
