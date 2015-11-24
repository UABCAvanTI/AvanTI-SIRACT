/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.ui;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.avanti.siract.application.helper.UnidadAprendizajeBeanHelper;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Planestudio;

import org.primefaces.context.RequestContext;
import javax.faces.context.FacesContext;
import mx.avanti.siract.business.entity.Cicloescolar;

/**
 *
 * @author balta
 */
@ManagedBean
@ViewScoped
public class UnidadAprendizajeBeanUI implements Serializable {
//<editor-fold defaultstate="collapsed" desc="declaracion de objetos de clase">

    private UnidadAprendizajeBeanHelper UABeanHelper;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="declaracion de objetos tipo lista">    
    private List<Unidadaprendizaje> listaFiltrada;
    private List<Programaeducativo> listaPE;
    private List<Planestudio> listaPlan;
    private List<Areaconocimiento> listaAC;
    private List<Unidadaprendizaje> listaUA;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="declaracion de objetos String">
    private String busqueda = "";
    private String cabecera;
    private String deshabilitar;
    private String mensajeConfirm;
    private String renderCancelar;
//</editor-fold>

    int horasClase = 0;
    int horasTaller = 0;
    int horasLaboratorio = 0;
    int horasCampo = 0;
    int horasClinica = 0;
    int horasExtra = 0;

//<editor-fold defaultstate="collapsed" desc="Getters y Setters de objetos de clase">
    public UnidadAprendizajeBeanHelper getUABeanHelper() {
        return UABeanHelper;
    }

    public void setUABeanHelper(UnidadAprendizajeBeanHelper UABeanHelper) {
        this.UABeanHelper = UABeanHelper;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Getters y Setters de objetos tipo lista">
    public List<Unidadaprendizaje> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Unidadaprendizaje> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public List<Programaeducativo> getListaPE() {
        return listaPE;
    }

    public void setListaPE(List<Programaeducativo> listaPE) {
        this.listaPE = listaPE;
    }

    public List<Planestudio> getListaPlan() {
        return listaPlan;
    }

    public void setListaPlan(List<Planestudio> listaPlan) {
        this.listaPlan = listaPlan;
    }

    public List<Areaconocimiento> getListaAC() {
        return listaAC;
    }

    public void setListaAC(List<Areaconocimiento> listaAC) {
        this.listaAC = listaAC;
    }

    public List<Unidadaprendizaje> getListaUA() {
        return listaUA;
    }

    public void setListaUA(List<Unidadaprendizaje> listaUA) {
        this.listaUA = listaUA;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Getters y Setters de variables">
    public String getCabecera() {
        return cabecera;
    }

    public void setCabecera(String cabecera) {
        this.cabecera = cabecera;
    }

    public String getDeshabilitar() {
        return deshabilitar;
    }

    public void setDeshabilitar(String deshabilitar) {
        this.deshabilitar = deshabilitar;
    }

    public String getMensajeConfirm() {
        return mensajeConfirm;
    }

    public void setMensajeConfirm() {
        if (deshabilitar.equals("true")) {
            mensajeConfirm = "¿Está seguro de elminar el registro?";
            renderCancelar = "true";
        } else {
            mensajeConfirm = "¿Está seguro de guardas los cambios?";
//            renderCancelar = "true";
        }
        RequestContext.getCurrentInstance().update("confirmdlgId");
    }

    public String getRenderCancelar() {
        return renderCancelar;
    }

    public void setRenderCancelar(String renderCancelar) {
        this.renderCancelar = renderCancelar;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }    
//</editor-fold>


//<editor-fold defaultstate="collapsed" desc="constructor">
    public UnidadAprendizajeBeanUI() {
        init();
    }

    private void init() {
        UABeanHelper = new UnidadAprendizajeBeanHelper();
    }
//</editor-fold>

    /* metodos */
    public void nuevo() {

        cabecera(1);
        UABeanHelper.setUnidadAprendizaje(new Unidadaprendizaje());
        UABeanHelper.setProgramaEducativo(new Programaeducativo());
        UABeanHelper.setPlanEstudio(new Planestudio());
        UABeanHelper.setAreaConocimiento(new Areaconocimiento());
        UABeanHelper.setCicloEscolar(new Cicloescolar());
        cargarListas();

    }

    public void modificar() {
        cabecera(3);
        cargarListas();

        try {
            if (UABeanHelper.getListaUASeleccionada().size() == 1) {
                UABeanHelper.setUnidadAprendizaje(UABeanHelper.getListaUASeleccionada().get(0));
                UABeanHelper.setCicloEscolar(UABeanHelper.getListaUASeleccionada().get(0).getCicloescolar());
                UABeanHelper.setAreaConocimiento(UABeanHelper.getAreaConocimientoDelegate().getAreaPorUA(UABeanHelper.getUnidadAprendizaje().getUapid()).get(0));
                UABeanHelper.setPlanEstudio(UABeanHelper.getPlanEstudioDelegate().findByPlanEstudioId(UABeanHelper.getAreaConocimiento().getPlanestudio().getPesid()));
                UABeanHelper.setProgramaEducativo(UABeanHelper.getProgramaEducativoDelegate().findProgramaEducativoById(UABeanHelper.getPlanEstudio().getProgramaeducativo().getPedid()));
            } else {
                UABeanHelper.setUnidadAprendizaje(UABeanHelper.getListaUASeleccionada().get(0));
                UABeanHelper.setCicloEscolar(UABeanHelper.getListaUASeleccionada().get(0).getCicloescolar());
                UABeanHelper.setAreaConocimiento(UABeanHelper.getAreaConocimientoDelegate().getAreaPorUA(UABeanHelper.getUnidadAprendizaje().getUapid()).get(0));
                UABeanHelper.setPlanEstudio(UABeanHelper.getPlanEstudioDelegate().findByPlanEstudioId(UABeanHelper.getAreaConocimiento().getPlanestudio().getPesid()));
                UABeanHelper.setProgramaEducativo(UABeanHelper.getProgramaEducativoDelegate().findProgramaEducativoById(UABeanHelper.getPlanEstudio().getProgramaeducativo().getPedid()));
            }
        } catch (NullPointerException e) {
            limpiarSeleccion();
        }

    }
//    

    public void eliminar() {
        cabecera(2);
        cargarListas();

        try {
            if (UABeanHelper.getListaUASeleccionada().size() == 1) {
                UABeanHelper.setUnidadAprendizaje(UABeanHelper.getListaUASeleccionada().get(0));
                UABeanHelper.setCicloEscolar(UABeanHelper.getListaUASeleccionada().get(0).getCicloescolar());
                UABeanHelper.setAreaConocimiento(UABeanHelper.getAreaConocimientoDelegate().getAreaPorUA(UABeanHelper.getUnidadAprendizaje().getUapid()).get(0));
                UABeanHelper.setPlanEstudio(UABeanHelper.getPlanEstudioDelegate().findByPlanEstudioId(UABeanHelper.getAreaConocimiento().getPlanestudio().getPesid()));
                UABeanHelper.setProgramaEducativo(UABeanHelper.getProgramaEducativoDelegate().findProgramaEducativoById(UABeanHelper.getPlanEstudio().getProgramaeducativo().getPedid()));

//                UABeanHelper.setAreaConocimiento(UABeanHelper.getListaUASeleccionada().get(0).getAreaconocimientos());
            } else {
                UABeanHelper.setUnidadAprendizaje(UABeanHelper.getListaUASeleccionada().get(0));
                UABeanHelper.setCicloEscolar(UABeanHelper.getListaUASeleccionada().get(0).getCicloescolar());
                UABeanHelper.setAreaConocimiento(UABeanHelper.getAreaConocimientoDelegate().getAreaPorUA(UABeanHelper.getUnidadAprendizaje().getUapid()).get(0));
                UABeanHelper.setPlanEstudio(UABeanHelper.getPlanEstudioDelegate().findByPlanEstudioId(UABeanHelper.getAreaConocimiento().getPlanestudio().getPesid()));
                UABeanHelper.setProgramaEducativo(UABeanHelper.getProgramaEducativoDelegate().findProgramaEducativoById(UABeanHelper.getPlanEstudio().getProgramaeducativo().getPedid()));

            }
        } catch (NullPointerException e) {
            limpiarSeleccion();
        }
//        UABeanHelper.getUADelegate().eliminarUnidadAprendizaje(UABeanHelper.ge());
    }
//    

    public String onClickSubmit() {
        setMensajeConfirm();
        if (deshabilitar.equals("true")) {
            RequestContext.getCurrentInstance().execute("confirmdlg.show()");
        } else {
            if (validarCamposVacios()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Favor de llenar todos los campos vacios");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            } else {
                if (cabecera.equals("Agregar Unidad de Aprendizaje")) {
                    HashSet setAC = new HashSet();
                    setAC.add(UABeanHelper.getAreaConocimiento());
                    UABeanHelper.getUnidadAprendizaje().setAreaconocimientos(setAC);
                    UABeanHelper.getUADelegate().agregarUnidadAprendizaje(UABeanHelper.getUnidadAprendizaje());
                    
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("", "Se guardó correctamente"));                    
                    
                } else if (cabecera.equals("Modificar Unidad de Aprendizaje")) {
                    HashSet setAC = new HashSet();
                    setAC.add(UABeanHelper.getAreaConocimiento());
                    UABeanHelper.getUnidadAprendizaje().setAreaconocimientos(setAC);
                    UABeanHelper.getUADelegate().agregarUnidadAprendizaje(UABeanHelper.getUnidadAprendizaje());                    
                    
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("", "Se guardó correctamente"));                    
                }
            }
        }
        filtrado();
        return "";

    }

    public void filtrado() {
        listaFiltrada = UABeanHelper.filtrado("Nombre", busqueda);
//        System.out.println("");
    }

    public String consultaACDeUA(Unidadaprendizaje ua) {

        String AreaConUA = "";
        List<String> lista = null;
        lista = UABeanHelper.getListaAreaConocimiento(ua);
        System.out.println(lista.size());
        if (lista.isEmpty()) {
            return "no tiene areas de conocimiento asignada.";
        } else {
            for (int i = 0; i < lista.size(); i++) {
                if (i == (lista.size()) - 1) {
                    AreaConUA += lista.get(i) + ".";
                } else {
                    AreaConUA += lista.get(i) + ", ";
                }
            }
            return AreaConUA;
        }
    }

    public List<Programaeducativo> cargarPE() {
        listaPE = UABeanHelper.getUADelegate().getListaProgramaEducativo();
        return listaPE;
    }

    public List<Planestudio> cargarPlan() {
        listaPlan = UABeanHelper.getUADelegate().getListaPlanEstudio();
        return listaPlan;
    }

    public List<Areaconocimiento> cargarAC() {
        listaAC = UABeanHelper.getUADelegate().getListaAreaConocimiento();
        return listaAC;
    }

    public List<Unidadaprendizaje> cargarUA() {
        listaUA = UABeanHelper.getUADelegate().getListaUnidadAprendizaje();
        return listaUA;
    }

    public void cargarListas() {
        cargarUA();
        cargarPE();
        cargarPlan();
        cargarAC();
    }

    public void fitlrarPlanPorPE() {
        System.out.println("id del PE " + UABeanHelper.getProgramaEducativo().getPedid());
        System.out.println("asdasdasd " + UABeanHelper.getPlanEstudioDelegate().getListaPlanEstudio().size());
        listaPlan = UABeanHelper.getPlanEstudioDelegate().buscarPlanEstudio(UABeanHelper.getProgramaEducativo().getPedid());
    }

    public void filtrarAreaPorPlan() {
        listaAC = UABeanHelper.getAreaConocimientoDelegate().getAreaMismoPlan(UABeanHelper.getPlanEstudio().getPesid());
    }

    public void horasExtraClase() {
        if (UABeanHelper.getUnidadAprendizaje().getUaphorasClase() != null) {
            horasClase = UABeanHelper.getUnidadAprendizaje().getUaphorasClase();
            UABeanHelper.getUnidadAprendizaje().setUaphorasExtraClase(horasClase);

        } else {
            UABeanHelper.getUnidadAprendizaje().setUaphorasExtraClase(0);
        }
    }

    public void creditos() {
        if (UABeanHelper.getUnidadAprendizaje().getUaphorasClase() == null) {
            horasClase = 0;
        } else {
            horasClase = UABeanHelper.getUnidadAprendizaje().getUaphorasClase();
        }

        if (UABeanHelper.getUnidadAprendizaje().getUaphorasTaller() == null) {
            horasTaller = 0;
        } else {
            horasTaller = UABeanHelper.getUnidadAprendizaje().getUaphorasTaller();
        }

        if (UABeanHelper.getUnidadAprendizaje().getUaphorasLaboratorio() == null) {
            horasLaboratorio = 0;
        } else {
            horasLaboratorio = UABeanHelper.getUnidadAprendizaje().getUaphorasLaboratorio();
        }

        if (UABeanHelper.getUnidadAprendizaje().getUaphorasCampo() == null) {
            horasCampo = 0;
        } else {
            horasCampo = UABeanHelper.getUnidadAprendizaje().getUaphorasCampo();
        }

        if (UABeanHelper.getUnidadAprendizaje().getUaphorasClinica() == null) {
            horasClinica = 0;
        } else {
            horasClinica = UABeanHelper.getUnidadAprendizaje().getUaphorasClinica();
        }

        if (UABeanHelper.getUnidadAprendizaje().getUaphorasExtraClase() == null) {
            horasExtra = 0;
        } else {
            horasExtra = UABeanHelper.getUnidadAprendizaje().getUaphorasExtraClase();
        }

        int creditosTotales = horasClase + horasTaller + horasLaboratorio + horasCampo + horasClinica + horasExtra;

        UABeanHelper.getUnidadAprendizaje().setUapcreditos(creditosTotales);
    }

    public void limpiarSeleccion() {
        UABeanHelper.setListaUASeleccionada(null);
        UABeanHelper.setUnidadAprendizaje(new Unidadaprendizaje());
        UABeanHelper.setProgramaEducativo(new Programaeducativo());
        UABeanHelper.setPlanEstudio(new Planestudio());
        UABeanHelper.setAreaConocimiento(new Areaconocimiento());
        UABeanHelper.setCicloEscolar(new Cicloescolar());
        mostrarSeleccionUA();
        deshabilitarMenu();
    }

    public String cabecera(int i) {
        if (i == 1) {
            cabecera = "Agregar Unidad de Aprendizaje";
            deshabilitar = "false";
        }
        if (i == 2) {
            cabecera = "Eliminar Unidad de Aprendizaje";
            deshabilitar = "true";
        }
        if (i == 3) {
            cabecera = "Modificar Unidad de Aprendizaje";
            deshabilitar = "false";
        }
        return cabecera;
    }

    public boolean deshabilitarMenu() {
        if (UABeanHelper.getListaUASeleccionada() == null || UABeanHelper.getListaUASeleccionada().size() < 1) {
            return true;
        }
        return false;
    }

    public boolean mostrarSeleccionUA() {
        return UABeanHelper.getListaUASeleccionada() != null && UABeanHelper.getListaUASeleccionada().size() > 1;
    }

    public void confirmacionAceptada() {
        if (deshabilitar.equals("true")) {
            if (renderCancelar.equals("true")) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("", "Se eliminó correctamente"));

                UABeanHelper.eliminarDeLista(UABeanHelper.getUnidadAprendizaje().getUapid());
                UABeanHelper.getUADelegate().eliminarUnidadAprendizaje(UABeanHelper.getUnidadAprendizaje());
                UABeanHelper.setSelecUA(new Unidadaprendizaje());
                UABeanHelper.setUnidadAprendizaje(new Unidadaprendizaje());
                RequestContext.getCurrentInstance().execute("confirmdlg.hide();");

                if (UABeanHelper.getListaUASeleccionada().size() >= 1) {
                    UABeanHelper.setUnidadAprendizaje(UABeanHelper.getListaUASeleccionada().get(0));
                    UABeanHelper.setCicloEscolar(UABeanHelper.getListaUASeleccionada().get(0).getCicloescolar());
                    UABeanHelper.setAreaConocimiento(UABeanHelper.getAreaConocimientoDelegate().getAreaPorUA(UABeanHelper.getUnidadAprendizaje().getUapid()).get(0));
                    UABeanHelper.setPlanEstudio(UABeanHelper.getPlanEstudioDelegate().findByPlanEstudioId(UABeanHelper.getAreaConocimiento().getPlanestudio().getPesid()));
                    UABeanHelper.setProgramaEducativo(UABeanHelper.getProgramaEducativoDelegate().findProgramaEducativoById(UABeanHelper.getPlanEstudio().getProgramaeducativo().getPedid()));
                }
            } else {
                RequestContext.getCurrentInstance().execute("confirmacion.hide();");
                limpiarSeleccion();
            }
        } else {
            HashSet setAC = new HashSet();

            setAC.add(UABeanHelper.getAreaConocimiento());
            UABeanHelper.getUnidadAprendizaje().setAreaconocimientos(setAC);
            UABeanHelper.getUADelegate().agregarUnidadAprendizaje(UABeanHelper.getUnidadAprendizaje());

            RequestContext.getCurrentInstance().execute("confirmdlg.hide();");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("", "Se guardó correctamente"));
        }
        filtrado();
    }

    public boolean validarCamposVacios() {
        if (UABeanHelper.getUnidadAprendizaje().getUapclave() == 0
                || UABeanHelper.getUnidadAprendizaje().getUapnombre().isEmpty()
                || UABeanHelper.getUnidadAprendizaje().getUapetapaFormacion().isEmpty()
                || UABeanHelper.getUnidadAprendizaje().getUaptipoCaracter().isEmpty()
                || UABeanHelper.getUnidadAprendizaje().getUaphorasClase() == null
                || UABeanHelper.getUnidadAprendizaje().getUaphorasTaller() == null
                || UABeanHelper.getUnidadAprendizaje().getUaphorasLaboratorio() == null
                || UABeanHelper.getUnidadAprendizaje().getUaphorasCampo() == null
                || UABeanHelper.getUnidadAprendizaje().getUaphorasClinica() == null
                || UABeanHelper.getAreaConocimiento().getAcoid() == 0) {
            return true;
        } else {
            return false;
        }
    }
//    

//    
}
