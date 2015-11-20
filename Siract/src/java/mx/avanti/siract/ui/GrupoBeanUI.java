package mx.avanti.siract.ui;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.avanti.siract.application.helper.GrupoBeanHelper;
import mx.avanti.siract.application.helper.PlanEstudioBeanHelper;
import mx.avanti.siract.application.helper.ProgramaEducativoBeanHelper;
import mx.avanti.siract.business.entity.Grupo;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Rol;
import org.primefaces.context.RequestContext;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Gig@B COMPUTADORAS
 */
@ManagedBean
@ViewScoped
public class GrupoBeanUI implements Serializable {

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    private GrupoBeanHelper grupoBeanHelper = null;
    private PlanEstudioBeanHelper planEstudioBeanHelper = null;
    private ProgramaEducativoBeanHelper programaEducativoBeanHelper = null;

    /* variable para controlar los botones de eliminar y modificar */
    private String deshabilitarBoton = "true";
    private String titleMod = "Seleccione un registro de la tabla";
    private String titleElim = "Seleccione un registro de la tabla";

    /*variables para controlar los dialog */
    private String header;
    private String deshabilitar;
    private String deshabilitarAceptar;

    private String mensajeConfirm;
    private String botonAceptar;

    /* variables para la busqueda por filtro y la lista de resultados */
    private String busqueda = "";
    private List<Grupo> listaFiltrada;

    private List<Planestudio> listaPlanestudio;
    private List<Programaeducativo> listaProgramaEducativo;

    /* variable para obtener el mensaje de los datos repetidos */
    private String mensajeRep;
    private String mensajeVacio = "";

    @PostConstruct
    public void postConstructor() {
        grupoBeanHelper.setRolSeleccionado(loginBean.getSeleccionado());
        grupoBeanHelper.setUsuario(loginBean.getLogueado());
        filtrado();
    }

    public GrupoBeanUI() {
        init();
    }

    private void init() {
        grupoBeanHelper = new GrupoBeanHelper();
        planEstudioBeanHelper = new PlanEstudioBeanHelper();
        programaEducativoBeanHelper = new ProgramaEducativoBeanHelper();
//        deshabilitar = "false";
    }

    public GrupoBeanHelper getGrupoBeanHelper() {
        return grupoBeanHelper;
    }

    public PlanEstudioBeanHelper getPlanEstudioBeanHelper() {
        return planEstudioBeanHelper;
    }

    public void setPlanEstudioBeanHelper(PlanEstudioBeanHelper planEstudioBeanHelper) {
        this.planEstudioBeanHelper = planEstudioBeanHelper;
    }

    public void setGrupoBeanHelper(GrupoBeanHelper grupoBeanHelper) {
        this.grupoBeanHelper = grupoBeanHelper;
    }

    public ProgramaEducativoBeanHelper getProgramaEducativoBeanHelper() {
        return programaEducativoBeanHelper;
    }

    public void setProgramaEducativoBeanHelper(ProgramaEducativoBeanHelper programaEducativoBeanHelper) {
        this.programaEducativoBeanHelper = programaEducativoBeanHelper;
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

    public String getDeshabilitarAceptar() {
        return deshabilitarAceptar;
    }

    public void setDeshabilitarAceptar(String deshabilitarAceptar) {
        this.deshabilitarAceptar = deshabilitarAceptar;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public List<Grupo> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Grupo> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public List<Planestudio> getListaPlanestudio() {
        return listaPlanestudio;
    }

    public void setListaPlanestudio(List<Planestudio> listaPlanestudio) {
        this.listaPlanestudio = listaPlanestudio;
    }

    public List<Programaeducativo> getListaProgramaEducativo() {
        return listaProgramaEducativo;
    }

    public void setListaProgramaEducativo(List<Programaeducativo> listaProgramaEducativo) {
        this.listaProgramaEducativo = listaProgramaEducativo;
    }

    public String getDeshabilitarBoton() {
        return deshabilitarBoton;
    }

    public void setDeshabilitarBoton(String deshabilitarBoton) {
        this.deshabilitarBoton = deshabilitarBoton;
    }

    public String getTitleMod() {
        return titleMod;
    }

    public void setTitleMod(String titleMod) {
        this.titleMod = titleMod;
    }

    public String getTitleElim() {
        return titleElim;
    }

    public void setTitleElim(String titleElim) {
        this.titleElim = titleElim;
    }

    public String getMensajeConfirm() {
        return mensajeConfirm;
    }

    public void setMensajeConfirm(String mensajeConfirm) {
        this.mensajeConfirm = mensajeConfirm;
    }

    public String getBotonAceptar() {
        return botonAceptar;
    }

    public void setBotonAceptar(String botonAceptar) {
        this.botonAceptar = botonAceptar;
    }

    public String getMensajeRep() {
        return mensajeRep;
    }

    public void setMensajeRep(String mensajeRep) {
        this.mensajeRep = mensajeRep;
    }

    public String getMensajeVacio() {
        return mensajeVacio;
    }

    public void setMensajeVacio(String mensajeVacio) {
        this.mensajeVacio = mensajeVacio;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public void setMensajeConfirm() {

//        List<AsignarGrupoUnidadAprendizajeProfesorBeanUI> listaAGUAP =
        if (deshabilitar.equals("true")) {
            if (grupoBeanHelper.getGrupoDelegate().getGrupoAsignado(grupoBeanHelper.getGrupo().getGpoid()).size() > 0) {
                mensajeConfirm = "El grupo se encuentra asignado a una  unidad de aprendizaje y profesor.";
                botonAceptar = "false";
            } else {
                mensajeConfirm = "¿Está seguro de eliminar el registro?";
                botonAceptar = "true";
            }
        } else if (header.equals("Modificar Grupo") && grupoBeanHelper.getGrupoDelegate().getGrupoAsignado(grupoBeanHelper.getGrupo().getGpoid()).size() > 0) {
            mensajeConfirm = "El grupo se encuentra asignado a una  unidad de aprendizaje y profesor. ¿Está seguro de guardar los cambios?";
        }
        RequestContext.getCurrentInstance().update("confirmdlgId");
    }

    /* metodo para cambiar el titulo de la cabecera del dialog y deshabilitar campos */
    public String dlgCabecera(int i) {
        if (i == 1) {
            header = "Agregar Grupo";
            deshabilitar = "false";
        }
        if (i == 2) {
            header = "Eliminar Grupo";
            deshabilitar = "true";
        }
        if (i == 3) {
            header = "Modificar Grupo";
            deshabilitar = "false";
        }
        return header;
    }

    /* se agrego la linea del metodo dlgCabecera */
    public void nuevo() {
        limpiarSeleccion();
        dlgCabecera(1);

//        deshabilitar = "true";
        grupoBeanHelper.setGrupo(new Grupo());
        grupoBeanHelper.setPlanestudio(new Planestudio());
        grupoBeanHelper.setProgramaEducativo(new Programaeducativo());
//        grupoBeanHelper.setSelecGrupo(new Grupo());
//        deshabilitarAceptar = "false";
        cargarPlanEstudio();
    }

    public void modificar() {
        cargarPlanEstudio();
        dlgCabecera(3);
        try {
            if (grupoBeanHelper.getListaGpoSeleccionada().size() == 1) {
                grupoBeanHelper.setGrupo(grupoBeanHelper.getListaGpoSeleccionada().get(0));
                grupoBeanHelper.setPlanestudio(grupoBeanHelper.getListaGpoSeleccionada().get(0).getPlanestudio());
                grupoBeanHelper.setProgramaEducativo(programaEducativoBeanHelper.getProgramaEducativoDelegate().findProgramaEducativoById(grupoBeanHelper.getPlanestudio().getProgramaeducativo().getPedid()));
                dialogFiltrarPlan();
//                grupoBeanHelper.setProgramaEducativo();
//                deshabilitarAceptar = "false";
            } else {
                if (grupoBeanHelper.getListaGpoSeleccionada().size() < 1) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                } else {
                    grupoBeanHelper.setGrupo(grupoBeanHelper.getListaGpoSeleccionada().get(0));
                    grupoBeanHelper.setPlanestudio(grupoBeanHelper.getListaGpoSeleccionada().get(0).getPlanestudio());
                    grupoBeanHelper.setProgramaEducativo(programaEducativoBeanHelper.getProgramaEducativoDelegate().findProgramaEducativoById(grupoBeanHelper.getPlanestudio().getProgramaeducativo().getPedid()));
                    dialogFiltrarPlan();
                }
            }
        } catch (NullPointerException e) {
            //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");         
            //RequestContext.getCurrentInstance().showMessageInDialog(message);
//            deshabilitarAceptar = "true";
        }
    }

    public void eliminar() {
        cargarPlanEstudio();

        dlgCabecera(2);
        try {
            if (grupoBeanHelper.getListaGpoSeleccionada().size() == 1) {
                grupoBeanHelper.setGrupo(grupoBeanHelper.getListaGpoSeleccionada().get(0));
                grupoBeanHelper.setPlanestudio(grupoBeanHelper.getListaGpoSeleccionada().get(0).getPlanestudio());
                grupoBeanHelper.setProgramaEducativo(programaEducativoBeanHelper.getProgramaEducativoDelegate().findProgramaEducativoById(grupoBeanHelper.getPlanestudio().getProgramaeducativo().getPedid()));
//                deshabilitarAceptar = "false";
            } else {
                if (grupoBeanHelper.getListaGpoSeleccionada().size() < 1) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                } else {
                    grupoBeanHelper.setGrupo(grupoBeanHelper.getListaGpoSeleccionada().get(0));
                    grupoBeanHelper.setPlanestudio(grupoBeanHelper.getListaGpoSeleccionada().get(0).getPlanestudio());
                    grupoBeanHelper.setProgramaEducativo(programaEducativoBeanHelper.getProgramaEducativoDelegate().findProgramaEducativoById(grupoBeanHelper.getPlanestudio().getProgramaeducativo().getPedid()));
                }
            }
        } catch (NullPointerException e) {
            //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");         
            //RequestContext.getCurrentInstance().showMessageInDialog(message);
//            deshabilitarAceptar = "true";
        }
    }
    /*se termina de agregar la linea del metodo dlgCabecera */

    /* metodo para validar si algun campos esta vacio y muestre un mensaje */
    public String validacion() {
        mensajeVacio = "";
        if (grupoBeanHelper.getGrupo().getGponumero() == 0 || String.valueOf(grupoBeanHelper.getGrupo().getGponumero()).isEmpty()){
            mensajeVacio = mensajeVacio + "Numero de grupo,";
        }
        
        if(grupoBeanHelper.getPlanestudio().getPesid() == 0) {
           mensajeVacio = mensajeVacio + " Plan de estudio,";
        }
        
//        if(grupoBeanHelper.getProgramaEducativo().getPedid() == 0){
//            mensajeVacio = mensajeVacio + " Programa educativo";
//        }
        
        return mensajeVacio;
//        } else {
//            return false;
//        }

    }

    /* metodo para realizar la eliminacion si el usuario lo confirma en el confirmDialog */
    public void eliminacionConfirmada() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Eliminando", "Se eliminó correctamente"));

        grupoBeanHelper.getGrupoDelegate().eliminarGrupo(grupoBeanHelper.getSelecGrupo());
        grupoBeanHelper.setGrupo(new Grupo());
        grupoBeanHelper.setSelecGrupo(new Grupo());
        RequestContext.getCurrentInstance().execute("confdlgElim.hide()");

        if (grupoBeanHelper.getListaGpoSeleccionada().size() == 1) {
            grupoBeanHelper.setGrupo(grupoBeanHelper.getListaGpoSeleccionada().get(0));
            grupoBeanHelper.setPlanestudio(grupoBeanHelper.getListaGpoSeleccionada().get(0).getPlanestudio());

        }
        filtrado();
    }

    /* metodo para realizar las modificaciones si el usuario lo confirma en el confirmDialog*/
    public void modificacionConfirmada() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Guardando", "Se guardó correctamente"));

        grupoBeanHelper.getGrupoDelegate().agregarGrupo(grupoBeanHelper.getGrupo());
        grupoBeanHelper.setGrupo(new Grupo());
        grupoBeanHelper.setSelecGrupo(new Grupo());
        RequestContext.getCurrentInstance().execute("confdlgMod.hide()");
//        RequestContext.getCurrentInstance().execute("dlg.hide()");
        filtrado();
    }

    public void confirmacionAceptada() {
        //deshabilitarAceptar = "false";
        if (deshabilitar.equals("true")) {
            if (botonAceptar.equals("true")) {
                esconderBotones();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("", "Se eliminó correctamente"));

                grupoBeanHelper.eliminarDeLista(grupoBeanHelper.getGrupo().getGpoid());
                grupoBeanHelper.getGrupoDelegate().eliminarGrupo(grupoBeanHelper.getGrupo());
                grupoBeanHelper.setSelecGrupo(new Grupo());
                grupoBeanHelper.setGrupo(new Grupo());
//                grupoBeanHelper.setPlanestudio(new Planestudio());
//                grupoBeanHelper.setProgramaEducativo(new Programaeducativo());
//                grupoBeanHelper.setSelecGrupo(new Grupo());

                RequestContext.getCurrentInstance().execute("confirmdlg.hide();");

                if (grupoBeanHelper.getListaGpoSeleccionada().size() >= 1) {
                    grupoBeanHelper.setGrupo(grupoBeanHelper.getListaGpoSeleccionada().get(0));
                    grupoBeanHelper.setPlanestudio(grupoBeanHelper.getListaGpoSeleccionada().get(0).getPlanestudio());
                    grupoBeanHelper.setProgramaEducativo(programaEducativoBeanHelper.getProgramaEducativoDelegate().findProgramaEducativoById(grupoBeanHelper.getPlanestudio().getProgramaeducativo().getPedid()));
                    RequestContext.getCurrentInstance().execute("dlg.show();");
                    //deshabilitarAceptar = "true";
                }
            } else {
                RequestContext.getCurrentInstance().execute("confirmdlg.hide();");
                limpiarSeleccion();
            }

        } else {

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("", "Se guardó correctamente"));

            grupoBeanHelper.getGrupoDelegate().agregarGrupo(grupoBeanHelper.getGrupo());
            grupoBeanHelper.seleccionarRegistro();
            grupoBeanHelper.setListaGpoSeleccionada(grupoBeanHelper.getListaGpoSeleccionada());

            RequestContext.getCurrentInstance().execute("dlg.show();");

        }
        filtrado();
    }

    /* 
     se verifica que si los campos estan habilitados se realizara la validacion
     de si estan vacios, de estarlo no se realizara un registro y mostrara un mensaje
     en caso contrario dara la alta.
     si estan deshabilitados se realizara una baja
     */
    public String onClickSubmit() {

        String resultado = "";

        setMensajeConfirm();
        RequestContext.getCurrentInstance().update("confirmdlg");

        if (deshabilitar.equals("true")) {
            //System.out.println("point here");
            //boolean res = validacion();                    }else if(header.equals("Eliminar Grupo")){
            RequestContext.getCurrentInstance().execute("confirmdlg.show()");
        } else {
            if (!validacion().isEmpty()) {
                //System.out.println("point here 2");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Favor de llenar " + mensajeVacio);
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            } else {

                mensajeRep = grupoBeanHelper.validarRepetidos();

                if (mensajeRep.isEmpty()) {
                    mensajeRep = "vacio";

                }
                if (mensajeRep.equals("vacio")) {

                    if (header.equals("Agregar Grupo")) {

                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("", "Se guardó correctamente"));

                        grupoBeanHelper.getGrupoDelegate().agregarGrupo(grupoBeanHelper.getGrupo());
                        grupoBeanHelper.setGrupo(new Grupo());
                        grupoBeanHelper.setSelecGrupo(new Grupo());
                        grupoBeanHelper.setPlanestudio(new Planestudio());
                        grupoBeanHelper.getProgramaEducativo().setPedid(0);
                        grupoBeanHelper.getPlanestudio().setPesid(0);
                    } else if (header.equals("Modificar Grupo")) {
                        if (grupoBeanHelper.getGrupoDelegate().getGrupoAsignado(grupoBeanHelper.getGrupo().getGpoid()).size() > 0) {
                            botonAceptar = "true";
                            RequestContext.getCurrentInstance().update("confirmdlg");
                            RequestContext.getCurrentInstance().execute("confirmdlg.show()");
                        } else {
                            FacesContext context = FacesContext.getCurrentInstance();
                            context.addMessage(null, new FacesMessage("", "Se guardó correctamente"));

                            grupoBeanHelper.getGrupoDelegate().agregarGrupo(grupoBeanHelper.getGrupo());
                            grupoBeanHelper.seleccionarRegistro();
                            grupoBeanHelper.setListaGpoSeleccionada(grupoBeanHelper.getListaGpoSeleccionada());

                            RequestContext.getCurrentInstance().execute("dlg.show();");
                        }
                    }
                    filtrado();
                    RequestContext.getCurrentInstance().update(":frmGrupo:idSelecPE");
                    RequestContext.getCurrentInstance().update(":frmGrupo:idSelectPlan");
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El: " + mensajeRep + " ya existe");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }

            }
        }

        mostrarBotones();
        return resultado;
    }
    /* metodo para llamar al metodo de filtrado en el Helper */

    public void filtrado() {
        List<Rol> list = null;
        list = loginBean.Obtenerrol(loginBean.getLogueado().getUsuid());
        String seleccionado=loginBean.getSeleccionado();
        System.out.println(seleccionado+"ÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑ");
        String catalogo="Administración grupo";
        loginBean.TienePermiso(list, seleccionado, catalogo);
        listaFiltrada = grupoBeanHelper.filtrado("Nombre", busqueda);
    }

    /* metodo para activar los botones de eliminar y modificar despues de seleccionar algun registro */
    public void mostrarBotones() {
        try {
            if (grupoBeanHelper.getSelecGrupo().getGpoid() > 0) {
                deshabilitarBoton = "false";
                titleElim = "Eliminar";
                titleMod = "Modificar";
            }
        } catch (NullPointerException e) {
        }
    }

    /* metodo para desactivar los botones despues de ejecutar una modificacion o eliminacion */
    public void esconderBotones() {
        deshabilitarBoton = "true";
        titleElim = "Seleccione un registro de la tabla";
        titleMod = "Seleccione un registro de la tabla";
    }

    public String botonesSelect() {
        if (grupoBeanHelper.getSelecGrupo().getGpoid() > 0) {
            //habilitarBoton = "false";
            return "false";
        } else {
            //habilitarBoton = "true";
            return "true";
        }
    }

    public boolean mostrarSeleccionGrupo() {
        return grupoBeanHelper.getListaGpoSeleccionada() != null && grupoBeanHelper.getListaGpoSeleccionada().size() > 1;
    }

    public void limpiarSeleccion() {
        grupoBeanHelper.setListaGpoSeleccionada(null);
        grupoBeanHelper.setGrupo(new Grupo());
        grupoBeanHelper.setSelecGrupo(new Grupo());
        grupoBeanHelper.setPlanestudio(new Planestudio());
        mostrarSeleccionGrupo();
        botonesModElim();
    }

    public String botonesModElim() {
        if (grupoBeanHelper.getListaGpoSeleccionada() == null || grupoBeanHelper.getListaGpoSeleccionada().size() < 1) {
            return "true";
        } else {
            return "false";
        }
    }

    public String toolTip(int i) {
        if (grupoBeanHelper.getListaGpoSeleccionada() == null || grupoBeanHelper.getListaGpoSeleccionada().size() < 1) {
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

    public List<Planestudio> cargarPlanEstudio() {
        listaPlanestudio = grupoBeanHelper.getPlanEstudioDelegate().getListaPlanEstudio();
        return listaPlanestudio;
    }

//    public List<Programaeducativo> cargarProgramaEducativo(){
//        
//    }
//    public List<Planestudio> cargarPlanEstudio(){
//        listaPlanestudio = grupoBeanHelper.getGrupoDelegate()
//    }
    public void onRowUnselect(UnselectEvent event) {
        grupoBeanHelper.setSelecGrupo(new Grupo());
        grupoBeanHelper.setSelecGrupo((Grupo) event.getObject());
    }

    public void dialogFiltrarPlan() {
        if (grupoBeanHelper.getProgramaEducativo().getPedid() != 0) {
            listaPlanestudio = planEstudioBeanHelper.getPlanEstudioDelegate().buscarPlanEstudio(grupoBeanHelper.getProgramaEducativo().getPedid());
//            deshabilitar = "false";
        } else {
//            deshabilitar = "true";
            grupoBeanHelper.getPlanestudio().setPesid(0);
//            planestudio.setPesid(0);
            List<Planestudio> listaPlan = grupoBeanHelper.getPlanEstudioDelegate().getListaPlanEstudio();
            List<Planestudio> listaPlan2 = null;
//            List<Grupo> listaGpo = grupoBeanHelper.getGrupoDelegate().getListaGrupo();

            try {
                listaPlanestudio.clear();
                listaPlan2.clear();
            } catch (NullPointerException e) {
                listaPlanestudio = grupoBeanHelper.getPlanEstudioDelegate().getListaPlanEstudio();
                listaPlanestudio.clear();

                listaPlan2 = grupoBeanHelper.getPlanEstudioDelegate().getListaPlanEstudio();
                listaPlan2.clear();
            }

            for (Programaeducativo pe : grupoBeanHelper.getListaProgramaEducativo()) {
                for (Planestudio plan : listaPlan) {

//                    System.out.println("mostrando los planes de estudi en FiltrarPlanPorPE \n");
//                    System.out.println(plan.getPesvigenciaPlan());
                    if (pe.getPedid() == plan.getProgramaeducativo().getPedid()) {
                        listaPlanestudio.add(plan);
                    }
                }
            }
        }
    }

    public String listaPEDeGrupo(Planestudio plan) {
        System.out.println(plan.getPesvigenciaPlan());
        System.out.println(plan.getProgramaeducativo().getPedid());
        String ProgEducGpo = "";
        String lista = null;
        lista = grupoBeanHelper.getListaPE(plan);
        if (lista.isEmpty()) {
            return "----";
        } else {
//            for(int i = 0; i < lista.size(); i++){
//                if(i == (lista.size()) - 1){
//                    ProgEducGpo += lista.get(i) + ".";
//                } else {
//                    ProgEducGpo += lista.get(i) + ".";
//                }
//            }
            return lista;
        }
    }
}