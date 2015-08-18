/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.ui;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.avanti.siract.application.helper.PlanEstudioBeanHelper;
import mx.avanti.siract.application.helper.ProgramaEducativoBeanHelper;
import mx.avanti.siract.application.helper.AsignarGrupoUnidadAprendizajeProfesorBeanHelper;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import mx.avanti.siract.business.entity.Grupo;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.business.entity.UnidadaprendizajeImparteProfesor;

/**
 *
 * @author Gig@B COMPUTADORAS
 */
@ManagedBean
@ViewScoped
public class AsignarGrupoUnidadAprendizajeProfesorBeanUI implements Serializable {

    private AsignarGrupoUnidadAprendizajeProfesorBeanHelper asignarGrupoUnidadAprendizajeProfesorBeanHelper;
    private AsignarGrupoUnidadAprendizajeProfesorBeanHelper AGUAPHelper;
    private List<UnidadaprendizajeImparteProfesor> listaFiltrada;
    private PlanEstudioBeanHelper planEstudioBeanHelper;
    private ProgramaEducativoBeanHelper programaEducativoBeanHelper;

    private List<Programaeducativo> listaPE;
    private List<Unidadaprendizaje> listaUA;
    private List<Areaconocimiento> listaAC;
    private List<Planestudio> listaPlan;
    private List<Profesor> listaProfesor;
    private List<Grupo> listaGrupo;

    private String header;
    private String deshabilitar;
    private String busqueda = "";
    private String mensajeConfirm;
    private String deshabilitarBoton;
    private String deshabilitarSubgrupo;
    private String mensajeVal;

    public AsignarGrupoUnidadAprendizajeProfesorBeanUI() {
        init();
    }

    private void init() {
        asignarGrupoUnidadAprendizajeProfesorBeanHelper = new AsignarGrupoUnidadAprendizajeProfesorBeanHelper();
        AGUAPHelper = new AsignarGrupoUnidadAprendizajeProfesorBeanHelper();
        planEstudioBeanHelper = new PlanEstudioBeanHelper();
        programaEducativoBeanHelper = new ProgramaEducativoBeanHelper();
    }
    /* getters y setters */

    public List<UnidadaprendizajeImparteProfesor> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<UnidadaprendizajeImparteProfesor> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public AsignarGrupoUnidadAprendizajeProfesorBeanHelper getAsignarGrupoUnidadAprendizajeProfesorBeanHelper() {
        return asignarGrupoUnidadAprendizajeProfesorBeanHelper;
    }

    public void setAsignarGrupoUnidadAprendizajeProfesorBeanHelper(AsignarGrupoUnidadAprendizajeProfesorBeanHelper asignarGrupoUnidadAprendizajeProfesorBeanHelper) {
        this.asignarGrupoUnidadAprendizajeProfesorBeanHelper = asignarGrupoUnidadAprendizajeProfesorBeanHelper;
    }

    //getters y setter de Marco
    public AsignarGrupoUnidadAprendizajeProfesorBeanHelper getAGUAPHelper() {
        return AGUAPHelper;
    }

    public void setAGUAPHelper(AsignarGrupoUnidadAprendizajeProfesorBeanHelper AGUAPHelper) {
        this.AGUAPHelper = AGUAPHelper;
    }

    public PlanEstudioBeanHelper getPlanEstudioBeanHelper() {
        return planEstudioBeanHelper;
    }

    public void setPlanEstudioBeanHelper(PlanEstudioBeanHelper planEstudioBeanHelper) {
        this.planEstudioBeanHelper = planEstudioBeanHelper;
    }

    public ProgramaEducativoBeanHelper getProgramaEducativoBeanHelper() {
        return programaEducativoBeanHelper;
    }

    public void setProgramaEducativoBeanHelper(ProgramaEducativoBeanHelper programaEducativoBeanHelper) {
        this.programaEducativoBeanHelper = programaEducativoBeanHelper;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
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
        if (deshabilitar.equals("true") && deshabilitarSubgrupo.equals("true")) {
            mensajeConfirm = "¿Está seguro de eliminar el registro?";
        } else {
            mensajeConfirm = "¿Está seguro de guardar los cambios?";
        }
        RequestContext.getCurrentInstance().update("confirmdlgId");
    }

    public String getDeshabilitarBoton() {
        return deshabilitarBoton;
    }

    public void setDeshabilitarBoton(String deshabilitarBoton) {
        this.deshabilitarBoton = deshabilitarBoton;
    }

    public String getDeshabilitarSubgrupo() {
        return deshabilitarSubgrupo;
    }

    public void setDeshabilitarSubgrupo(String deshabilitarSubgrupo) {
        this.deshabilitarSubgrupo = deshabilitarSubgrupo;
    }

    public String getMensajeVal() {
        return mensajeVal;
    }

    public void setMensajeVal(String mensajeVal) {
        this.mensajeVal = mensajeVal;
    }

    public List<Unidadaprendizaje> getListaUA() {
        return listaUA;
    }

    public List<Programaeducativo> getListaPE() {
        return listaPE;
    }

    public void setListaPE(List<Programaeducativo> listaPE) {
        this.listaPE = listaPE;
    }

    public void setListaUA(List<Unidadaprendizaje> listaUA) {
        this.listaUA = listaUA;
    }

    public List<Profesor> getListaProfesor() {
        return listaProfesor;
    }

    public void setListaProfesor(List<Profesor> listaProfesor) {
        this.listaProfesor = listaProfesor;
    }

    public List<Grupo> getListaGrupo() {
        return listaGrupo;
    }

    public void setListaGrupo(List<Grupo> listaGrupo) {
        this.listaGrupo = listaGrupo;
    }

    public List<Areaconocimiento> getListaAC() {
        return listaAC;
    }

    public void setListaAC(List<Areaconocimiento> listaAC) {
        this.listaAC = listaAC;
    }

    public List<Planestudio> getListaPlan() {
        return listaPlan;
    }

    public void setListaPlan(List<Planestudio> listaPlan) {
        this.listaPlan = listaPlan;
    }


    /* metodos */
    public void nuevo() {
        limpiarSeleccion();
        dlgCabecera(1);
        AGUAPHelper.setAGUAP(new UnidadaprendizajeImparteProfesor());
        AGUAPHelper.setProgramaEducativo(new Programaeducativo());
        AGUAPHelper.setPlanEstudio(new Planestudio());
        AGUAPHelper.setAreaConocimiento(new Areaconocimiento());
        AGUAPHelper.setUnidadApren(new Unidadaprendizaje());
        AGUAPHelper.setProfesor(new Profesor());
        AGUAPHelper.setGrupo(new Grupo());
//        AGUAPHelper.setSelecAGUAP(new UnidadaprendizajeImparteProfesor());
//        asignarGrupoUnidadAprendizajeProfesorBeanHelper.setImparteProfesor(new UnidadaprendizajeImparteProfesor());
        cargarPE();
        cargarPlan();
        cargarAC();
        cargarUA();
        cargarGrupo();
        cargarProfesor();
    }

    public void modificar() {
        dlgCabecera(3);
        cargarUA();
        cargarGrupo();
        cargarProfesor();
        cargarPlan();
        cargarPE();
        cargarAC();
        try {
            if (AGUAPHelper.getListaAGUAPSeleccionada().size() == 1) {
                System.out.println("grupo en lista corto: " + AGUAPHelper.getListaAGUAPSeleccionada().get(0).getGrupo().getGponumero());
//                System.out.println("grupo en lista largo: " + asignarGrupoUnidadAprendizajeProfesorBeanHelper.getListaAGUAPSeleccionada().get(0).getGrupo().getGponumero());

                AGUAPHelper.setAGUAP(AGUAPHelper.getListaAGUAPSeleccionada().get(0));
                AGUAPHelper.setGrupo(AGUAPHelper.getListaAGUAPSeleccionada().get(0).getGrupo());
                AGUAPHelper.setProfesor(AGUAPHelper.getListaAGUAPSeleccionada().get(0).getProfesor());
                AGUAPHelper.setUnidadApren(AGUAPHelper.getListaAGUAPSeleccionada().get(0).getUnidadaprendizaje());

//            System.out.println("este es el ID de plan de estudio almacenado en grupo, todo consultado desde AGUAP");
//            System.out.println(AGUAPHelper.getGrupo().getPlanestudio().getPesid());                
                AGUAPHelper.setPlanEstudio(planEstudioBeanHelper.getPlanEstudioDelegate().findByPlanEstudioId(AGUAPHelper.getGrupo().getPlanestudio().getPesid()));
                AGUAPHelper.setProgramaEducativo(programaEducativoBeanHelper.getProgramaEducativoDelegate().findProgramaEducativoById(AGUAPHelper.getPlanEstudio().getProgramaeducativo().getPedid()));
                AGUAPHelper.setAreaConocimiento(AGUAPHelper.getAreaConocimientoDelegate().getAreaMismoPlan(AGUAPHelper.getPlanEstudio().getPesid()).get(0));
                filtrarListas();
//                filtrarPlanYProfPorPE();
//                filtrarAreaYGpoPorPlan();
//                filtrarUAPorArea();

            } else {
                if (AGUAPHelper.getListaAGUAPSeleccionada().size() < 1) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                } else {
                    AGUAPHelper.setAGUAP(AGUAPHelper.getListaAGUAPSeleccionada().get(0));
                    AGUAPHelper.setGrupo(AGUAPHelper.getListaAGUAPSeleccionada().get(0).getGrupo());
                    AGUAPHelper.setProfesor(AGUAPHelper.getListaAGUAPSeleccionada().get(0).getProfesor());
                    AGUAPHelper.setUnidadApren(AGUAPHelper.getListaAGUAPSeleccionada().get(0).getUnidadaprendizaje());

                    AGUAPHelper.setPlanEstudio(planEstudioBeanHelper.getPlanEstudioDelegate().findByPlanEstudioId(AGUAPHelper.getGrupo().getPlanestudio().getPesid()));
                    AGUAPHelper.setProgramaEducativo(programaEducativoBeanHelper.getProgramaEducativoDelegate().findProgramaEducativoById(AGUAPHelper.getPlanEstudio().getProgramaeducativo().getPedid()));
                    AGUAPHelper.setAreaConocimiento(AGUAPHelper.getAreaConocimientoDelegate().getAreaMismoPlan(AGUAPHelper.getPlanEstudio().getPesid()).get(0));
                    filtrarListas();
//                    filtrarPlanYProfPorPE();
//                    filtrarAreaYGpoPorPlan();
//                    filtrarUAPorArea();
                }
            }
        } catch (NullPointerException e) {
            System.out.println("\n\n\n joijiuhhojooijij");
            AGUAPHelper.setAGUAP(new UnidadaprendizajeImparteProfesor());
            AGUAPHelper.setUnidadApren(new Unidadaprendizaje());
            AGUAPHelper.setProfesor(new Profesor());
            AGUAPHelper.setGrupo(new Grupo());
        }
//        asignarGrupoUnidadAprendizajeProfesorBeanHelper.setImparteProfesor(asignarGrupoUnidadAprendizajeProfesorBeanHelper.getSelImparteProfesor());
    }

    public void eliminar() {
        dlgCabecera(2);
        cargarUA();
        cargarGrupo();
        cargarProfesor();
        cargarPlan();
        cargarPE();
        cargarAC();
        try {
            if (AGUAPHelper.getListaAGUAPSeleccionada().size() == 1) {
                AGUAPHelper.setAGUAP(AGUAPHelper.getListaAGUAPSeleccionada().get(0));
                AGUAPHelper.setGrupo(AGUAPHelper.getListaAGUAPSeleccionada().get(0).getGrupo());
                AGUAPHelper.setProfesor(AGUAPHelper.getListaAGUAPSeleccionada().get(0).getProfesor());
                AGUAPHelper.setUnidadApren(AGUAPHelper.getListaAGUAPSeleccionada().get(0).getUnidadaprendizaje());

                AGUAPHelper.setPlanEstudio(planEstudioBeanHelper.getPlanEstudioDelegate().findByPlanEstudioId(AGUAPHelper.getGrupo().getPlanestudio().getPesid()));
                AGUAPHelper.setProgramaEducativo(programaEducativoBeanHelper.getProgramaEducativoDelegate().findProgramaEducativoById(AGUAPHelper.getPlanEstudio().getProgramaeducativo().getPedid()));
                AGUAPHelper.setAreaConocimiento(AGUAPHelper.getAreaConocimientoDelegate().getAreaMismoPlan(AGUAPHelper.getPlanEstudio().getPesid()).get(0));

                filtrarPlanYProfPorPE();
                filtrarAreaYGpoPorPlan();
                filtrarUAPorArea();
            } else {
                if (AGUAPHelper.getListaAGUAPSeleccionada().size() < 1) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                } else {

                    AGUAPHelper.setAGUAP(AGUAPHelper.getListaAGUAPSeleccionada().get(0));
                    AGUAPHelper.setGrupo(AGUAPHelper.getListaAGUAPSeleccionada().get(0).getGrupo());
                    AGUAPHelper.setProfesor(AGUAPHelper.getListaAGUAPSeleccionada().get(0).getProfesor());
                    AGUAPHelper.setUnidadApren(AGUAPHelper.getListaAGUAPSeleccionada().get(0).getUnidadaprendizaje());

                    AGUAPHelper.setPlanEstudio(planEstudioBeanHelper.getPlanEstudioDelegate().findByPlanEstudioId(AGUAPHelper.getGrupo().getPlanestudio().getPesid()));
                    AGUAPHelper.setProgramaEducativo(programaEducativoBeanHelper.getProgramaEducativoDelegate().findProgramaEducativoById(AGUAPHelper.getPlanEstudio().getProgramaeducativo().getPedid()));
                    AGUAPHelper.setAreaConocimiento(AGUAPHelper.getAreaConocimientoDelegate().getAreaMismoPlan(AGUAPHelper.getPlanEstudio().getPesid()).get(0));

                    filtrarPlanYProfPorPE();
                    filtrarAreaYGpoPorPlan();
                    filtrarUAPorArea();
                }
            }
        } catch (NullPointerException e) {
            AGUAPHelper.setAGUAP(new UnidadaprendizajeImparteProfesor());
            AGUAPHelper.setUnidadApren(new Unidadaprendizaje());
            AGUAPHelper.setProfesor(new Profesor());
            AGUAPHelper.setGrupo(new Grupo());
        }
//        asignarGrupoUnidadAprendizajeProfesorBeanHelper.getAsignarGrupoUnidadAprendizajeProfesorDelegate().eliminarUniAprenImparteProfe(asignarGrupoUnidadAprendizajeProfesorBeanHelper.getSelImparteProfesor());
//        filtro();
    }

//    public boolean validacion() {
//        if (AGUAPHelper.getAGUAP().getUipsubgrupo().isEmpty()) {
//            return true;
//        }
//        return false;
//    }
    //falta mucho por terminar no hay que llamar a este metodo aun
    public String onClickSubmit() {
        String resultado = "";
        setMensajeConfirm();

        if (deshabilitar.equals("true") && deshabilitarSubgrupo.equals("true")) {
            RequestContext.getCurrentInstance().execute("confirmdlg.show()");
        } else {
            if (validarCamposVacios()) {
//                System.out.println("validar subgrupo" + AGUAPHelper.getAGUAP().getUipsubgrupo());
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Favor de llenar todos los campos vacios");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            } else {

                mensajeVal = AGUAPHelper.validarRepetidos();
                System.out.println("BEANUI mensajeVal = " + mensajeVal);

                if (mensajeVal.isEmpty()) {
                    mensajeVal = "vacio";
                }

                if (mensajeVal.equals("vacio")) {

                    if (header.equals("Agregar Grupo, Unidad Aprendizaje y Profesor")) {
                        if (!AGUAPHelper.getAGUAP().getUipsubgrupo().equals("0") && AGUAPHelper.getAGUAP().getUiptipoSubgrupo().equals("C")) {
                            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Este subgrupo no coincide con su tipo");
                            RequestContext.getCurrentInstance().showMessageInDialog(message);

                        } else {

                            FacesContext context = FacesContext.getCurrentInstance();
                            context.addMessage(null, new FacesMessage("Agregando", "Se guardó correctamente"));

                            System.out.println("ID de UA " + AGUAPHelper.getUnidadApren().getUapid());
                            System.out.println("ID de Profesor " + AGUAPHelper.getProfesor().getProid());
                            System.out.println("ID de grupo " + AGUAPHelper.getGrupo().getGpoid());
                            System.out.println("Tipo " + AGUAPHelper.getAGUAP().getUiptipoSubgrupo());
                            System.out.println("Subgrupo " + AGUAPHelper.getAGUAP().getUipsubgrupo());

                            AGUAPHelper.getAGUAPDelegate().agregarUniAprenImparteProfe(AGUAPHelper.getAGUAP());
                            AGUAPHelper.setAGUAP(new UnidadaprendizajeImparteProfesor());
                            AGUAPHelper.setSelecAGUAP(new UnidadaprendizajeImparteProfesor());
                            AGUAPHelper.setGrupo(new Grupo());
                            AGUAPHelper.setProfesor(new Profesor());
                            AGUAPHelper.setUnidadApren(new Unidadaprendizaje());
                            AGUAPHelper.setProgramaEducativo(new Programaeducativo());
                            AGUAPHelper.setPlanEstudio((new Planestudio()));
                            AGUAPHelper.setAreaConocimiento(new Areaconocimiento());
                        }

                    } else if (header.equals("Modificar Grupo, Unidad Aprendizaje y Profesor")) {
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Modificando", "Se guardó correctamente"));

                        AGUAPHelper.getAGUAPDelegate().agregarUniAprenImparteProfe(AGUAPHelper.getAGUAP());
                        AGUAPHelper.seleccionarRegistro();
                        AGUAPHelper.setListaAGUAPSeleccionada(AGUAPHelper.getListaAGUAPSeleccionada());

                        RequestContext.getCurrentInstance().execute("dlg.show()");
                    }
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Este profesor ya esta asignado al mismo grupo");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
            }
        }
        //asignarGrupoUnidadAprendizajeProfesorBeanHelper.getAsignarGrupoUnidadAprendizajeProfesorDelegate().agregarUniAprenImparteProfe(asignarGrupoUnidadAprendizajeProfesorBeanHelper.getImparteProfesor());
        filtro();
        return resultado;
    }

    //aun falta por terminar
    public void confirmacionAceptada() {
        if (deshabilitar.equals("true")) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Eliminando", "se eliminó correctamente"));

            AGUAPHelper.eliminarDeLista(AGUAPHelper.getAGUAP().getUipid());
            AGUAPHelper.getAGUAPDelegate().eliminarUniAprenImparteProfe(AGUAPHelper.getAGUAP());
            AGUAPHelper.setSelecAGUAP(new UnidadaprendizajeImparteProfesor());
            AGUAPHelper.setAGUAP(new UnidadaprendizajeImparteProfesor());
            //AGUAPHelper.setSelecAGUAP(new UnidadaprendizajeImparteProfesor());
            RequestContext.getCurrentInstance().execute("confirmdlg.hide()");

            if (AGUAPHelper.getListaAGUAPSeleccionada().size() >= 1) {
                AGUAPHelper.setAGUAP(AGUAPHelper.getListaAGUAPSeleccionada().get(0));
                AGUAPHelper.setGrupo(AGUAPHelper.getListaAGUAPSeleccionada().get(0).getGrupo());
                AGUAPHelper.setProfesor(AGUAPHelper.getListaAGUAPSeleccionada().get(0).getProfesor());
                AGUAPHelper.setUnidadApren(AGUAPHelper.getListaAGUAPSeleccionada().get(0).getUnidadaprendizaje());
                RequestContext.getCurrentInstance().execute("dlg.show();");

            }
            filtro();
        }
    }

    public void filtro() {
        listaFiltrada = AGUAPHelper.filtrado("Nombre", busqueda);
    }

    public String toolTip(int i) {
        if (AGUAPHelper.getListaAGUAPSeleccionada() == null || AGUAPHelper.getListaAGUAPSeleccionada().size() < 1) {
            return "Seleccione un registro de la tabla";
        } else {
            if (i == 1) {
                return "Eliminar";
            } else if (i == 2) {
                return "Modificar";
            }
        }
        return "";
    }

    public String botonesModElim() {
        if (AGUAPHelper.getListaAGUAPSeleccionada() == null || AGUAPHelper.getListaAGUAPSeleccionada().size() < 1) {
            return "true";
        } else {
            return "false";
        }
    }

    public String dlgCabecera(int i) {
        if (i == 1) {
            header = "Agregar Grupo, Unidad Aprendizaje y Profesor";
            deshabilitar = "false";
            deshabilitarSubgrupo = "false";
        }
        if (i == 2) {
            header = "Eliminar Grupo, Unidad Aprendizaje y Profesor";
            deshabilitar = "true";
            deshabilitarSubgrupo = "true";
        }
        if (i == 3) {
            header = "Modificar Grupo, Unidad Aprendizaje y Profesor";
            deshabilitar = "false";
            deshabilitarSubgrupo = "false";
        }
        return header;
    }

    public List<Programaeducativo> cargarPE() {
        listaPE = AGUAPHelper.getAGUAPDelegate().getListaProgramaEducativo();
        return listaPE;
    }

    public List<Planestudio> cargarPlan() {
        listaPlan = AGUAPHelper.getAGUAPDelegate().getListaPlanEstudio();
        return listaPlan;
    }

    public List<Areaconocimiento> cargarAC() {
        listaAC = AGUAPHelper.getAGUAPDelegate().getListaAreaConocimiento();
        return listaAC;
    }

    public List<Unidadaprendizaje> cargarUA() {
        listaUA = AGUAPHelper.getUnidadAprendizajeDelegate().getListaUnidadAprendizaje();
        return listaUA;
    }

    public List<Profesor> cargarProfesor() {
        listaProfesor = AGUAPHelper.getAGUAPDelegate().getListaProfesor();
        return listaProfesor;
    }

    public List<Grupo> cargarGrupo() {
        listaGrupo = AGUAPHelper.getAGUAPDelegate().getListaGrupo();
        return listaGrupo;
    }

    public boolean mostrarSeleccionAGUAP() {
        return AGUAPHelper.getListaAGUAPSeleccionada() != null && AGUAPHelper.getListaAGUAPSeleccionada().size() > 1;
    }

    public void filtrarPlanYProfPorPE() {
        listaPlan = AGUAPHelper.getPlanEstudioDelegate().buscarPlanEstudio(AGUAPHelper.getProgramaEducativo().getPedid());
        listaProfesor = AGUAPHelper.getProfesorDelegate().getProfPE(AGUAPHelper.getProgramaEducativo().getPedid());
    }

    public void filtrarAreaYGpoPorPlan() {
        listaAC = AGUAPHelper.getAreaConocimientoDelegate().getAreaMismoPlan(AGUAPHelper.getPlanEstudio().getPesid());
        listaGrupo = AGUAPHelper.getGrupoDelegate().getGpoMismoPlan(AGUAPHelper.getPlanEstudio().getPesid());
    }

    public void filtrarUAPorArea() {
        listaUA = AGUAPHelper.getUnidadAprendizajeDelegate().getUAMismaArea(AGUAPHelper.getAreaConocimiento().getAcoid());
    }

    public void cargaDeListas() {
        cargarPE();
        cargarPlan();
        cargarAC();
        cargarUA();
        cargarProfesor();
    }

    public void filtrarListas() {
        filtrarPlanYProfPorPE();
        filtrarAreaYGpoPorPlan();
        filtrarUAPorArea();
    }

    public void limpiarSeleccion() {
        AGUAPHelper.setListaAGUAPSeleccionada(null);
        AGUAPHelper.setAGUAP(new UnidadaprendizajeImparteProfesor());
        AGUAPHelper.setUnidadApren(new Unidadaprendizaje());
        AGUAPHelper.setProfesor(new Profesor());
        AGUAPHelper.setGrupo(new Grupo());
        mostrarSeleccionAGUAP();
        botonesModElim();
    }

    public void tipoTieneSubgrupo() {
        if (AGUAPHelper.getAGUAP().getUiptipoSubgrupo().equals("C")) {
            AGUAPHelper.getAGUAP().setUipsubgrupo("0");
            deshabilitarSubgrupo = "true";
        } else {
            AGUAPHelper.getAGUAP().setUipsubgrupo("");
            deshabilitarSubgrupo = "false";
        }
    }

    public void subgrupoTieneTipo() {
        if (AGUAPHelper.getAGUAP().getUipsubgrupo().equals("0")) {
            AGUAPHelper.getAGUAP().setUiptipoSubgrupo("C");
        }
    }

    public boolean validarCamposVacios() {
        if (AGUAPHelper.getUnidadApren().getUapid() == 0
                || AGUAPHelper.getProfesor().getProid() == 0
                || AGUAPHelper.getGrupo().getGpoid() == 0
                || AGUAPHelper.getAGUAP().getUipsubgrupo().isEmpty()
                || AGUAPHelper.getAGUAP().getUiptipoSubgrupo().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
