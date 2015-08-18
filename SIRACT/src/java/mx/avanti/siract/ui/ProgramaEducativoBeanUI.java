/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.ui;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.avanti.siract.application.helper.ProgramaEducativoBeanHelper;
import mx.avanti.siract.application.helper.UnidadAcademicaBeanHelper;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Unidadacademica;
import mx.avanti.siract.common.integration.ServiceLocator;
import org.primefaces.context.RequestContext;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author balta
 */
@ManagedBean
@ViewScoped
public class ProgramaEducativoBeanUI implements Serializable {

    private ProgramaEducativoBeanHelper programaEducativoBeanHelper = null;
    private UnidadAcademicaBeanHelper unidadAcademicaBeanHelper = null;

    private List<Programaeducativo> listaFiltrada;
    private List<Unidadacademica> listaUnidad;

    private String header;
    private String deshabilitar;
    private String deshabilitarAceptar;

    private String cabecera;
    private String textoBoton;
    private String busqueda = "";
    private boolean selecvisible;
    private String mensajeRep;

    private String mensajeConfirm="";

    private String deshabilitarBoton = "true";
    private String titleElim = "Seleccione un registro de la tabla";
    private String titleMod = "Seleccione un registro de la tabla";  
    
    private String renderUA = "false";
    
//Propiedad utilizada para acceder a los datos de LoginBean_2    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    public ProgramaEducativoBeanUI() {
        init();
    }

    private void init() {
        programaEducativoBeanHelper = new ProgramaEducativoBeanHelper();
        unidadAcademicaBeanHelper = new UnidadAcademicaBeanHelper();

    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public String getRenderUA() {
        return renderUA;
    }

    public void setRenderUA(String renderUA) {
        this.renderUA = renderUA;
    }
    
    
    
    //Post constructor necesario para usar ManagedProperty
    @PostConstruct
    public void postConstructor() {
        programaEducativoBeanHelper.setRolSeleccionado(loginBean.getSeleccionado());
        programaEducativoBeanHelper.setUsuario(loginBean.getUsuario());
        System.out.println("rol desde el BeanUI: " + loginBean.getSeleccionado());
        System.out.println("id del usuario desde login " + loginBean.getUsuario().getUsuid());
        
        if(programaEducativoBeanHelper.getRolSeleccionado().equalsIgnoreCase("Administrador")){
            renderUA="true";
        }else{
            renderUA="false";
        }
       
    }


    public String getMensajeConfirm() {
        return mensajeConfirm;
    }

    //ya
    public void setMensajeConfirm(String mensajeConfirm) {
//        if (deshabilitar.equals("true")) {
//            mensajeConfirm = "¿Está seguro de eliminar el registro?";
//        } else {
//            mensajeConfirm = "¿Está seguro de guardar los cambios?";
//        }
//        RequestContext.getCurrentInstance().update("confirmdlg");
        this.mensajeConfirm = mensajeConfirm;
    }

    //ya
    public String dlgCabecera(int i) {
        if (i == 1) {
            header = "Agregar Programa Educativo";
            deshabilitar = "false";
        }
        if (i == 2) {
            header = "Eliminar Programa Educativo";
            deshabilitar = "true";
        }
        if (i == 3) {
            header = "Modificar Programa Educativo";
            deshabilitar = "false";
        }
        return header;
    }

    //ya
    public void nuevo() {
        limpiarSeleccion();
        dlgCabecera(1);
        programaEducativoBeanHelper.setProgramaEducativo(new Programaeducativo());
        programaEducativoBeanHelper.setSelecProgramaEducativo(new Programaeducativo());
//        deshabilitarAceptar = "false";
    }

    public void modificar() {
        dlgCabecera(3);
        try {
            if (programaEducativoBeanHelper.getListaSeleccionPe().size() == 1) {
                programaEducativoBeanHelper.setProgramaEducativo(programaEducativoBeanHelper.getListaSeleccionPe().get(0));
                programaEducativoBeanHelper.setUnidadacademica(programaEducativoBeanHelper.getListaSeleccionPe().get(0).getUnidadacademica());
            } else {
                if (programaEducativoBeanHelper.getListaSeleccionPe().size() < 1) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                } else {
                    programaEducativoBeanHelper.setProgramaEducativo(programaEducativoBeanHelper.getListaSeleccionPe().get(0));
                    programaEducativoBeanHelper.setUnidadacademica(programaEducativoBeanHelper.getListaSeleccionPe().get(0).getUnidadacademica());
                }
            }
        } catch (NullPointerException e) {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
//            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void eliminar() {
        dlgCabecera(2);
        // cargarUsuario();
        try {
            if (programaEducativoBeanHelper.getListaSeleccionPe().size() == 1) {
                programaEducativoBeanHelper.setProgramaEducativo(programaEducativoBeanHelper.getListaSeleccionPe().get(0));
                programaEducativoBeanHelper.setUnidadacademica(programaEducativoBeanHelper.getListaSeleccionPe().get(0).getUnidadacademica());
                //selecvisible=true;
            } else {
                if (programaEducativoBeanHelper.getListaSeleccionPe().size() < 1) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                } else {
                    programaEducativoBeanHelper.setProgramaEducativo(programaEducativoBeanHelper.getListaSeleccionPe().get(0));
                    programaEducativoBeanHelper.setUnidadacademica(programaEducativoBeanHelper.getListaSeleccionPe().get(0).getUnidadacademica());

                    //  profesorBeanHelper.setUsuario2(profesorBeanHelper.getListaSeleccionProfesores().get(0).getUsuario());
                }
            }
//           
        } catch (NullPointerException e) {
//            programaEducativoBeanHelper.setProgramaEducativo(new Programaeducativo());
//            // profesorBeanHelper.setUsuario2(new Usuario());
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se seleccion&oacute; ning&uacute;n registro");
//            RequestContext.getCurrentInstance().showMessageInDialog(message);

        }
    }

    //ya
    public boolean validacion() {
        if (programaEducativoBeanHelper.getProgramaEducativo().getPedclave() == 0
                || programaEducativoBeanHelper.getProgramaEducativo().getPednombre().isEmpty()
                //||String.valueOf(programaEducativoBeanHelper.getProgramaEducativo().getPedclave()).isEmpty()
                || programaEducativoBeanHelper.getUnidadacademica().getUacid() == 0) {
            return true;
        } else {
            return false;
        }

    }

    //ya
    public void eliminacionConfirmada() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Eliminando", "Se eliminó correctamente"));

        programaEducativoBeanHelper.getProgramaEducativoDelegate().eliminarProgramaEducativo(programaEducativoBeanHelper.getSelecProgramaEducativo());
        programaEducativoBeanHelper.setProgramaEducativo(new Programaeducativo());
        programaEducativoBeanHelper.setSelecProgramaEducativo(new Programaeducativo());
        RequestContext.getCurrentInstance().execute("confdlgElim.hide()");

        if (programaEducativoBeanHelper.getListaSeleccionPe().size() == 1) {
            programaEducativoBeanHelper.setProgramaEducativo(programaEducativoBeanHelper.getListaSeleccionPe().get(0));
            programaEducativoBeanHelper.setUnidadacademica(programaEducativoBeanHelper.getListaSeleccionPe().get(0).getUnidadacademica());
        }
        filtrado();
    }

    public void modificacionConfirmada() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Guardando", "Se guardó correctamente"));

        programaEducativoBeanHelper.getProgramaEducativoDelegate().agregarProgramaEducativo(programaEducativoBeanHelper.getProgramaEducativo());
        programaEducativoBeanHelper.setProgramaEducativo(new Programaeducativo());
        programaEducativoBeanHelper.setSelecProgramaEducativo(new Programaeducativo());
        RequestContext.getCurrentInstance().execute("confdlgMod.hide()");
//        RequestContext.getCurrentInstance().execute("dlg.hide()");
        filtrado();
    }

    //ya
    public void confirmacionAceptada() {
        if (deshabilitar.equals("true")) {
            if(renderCancelar.equals("true")){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Eliminando", "Se eliminó correctamente"));

            programaEducativoBeanHelper.eliminarDeLista(programaEducativoBeanHelper.getProgramaEducativo().getPedid());
            programaEducativoBeanHelper.getProgramaEducativoDelegate().eliminarProgramaEducativo(programaEducativoBeanHelper.getProgramaEducativo());
            programaEducativoBeanHelper.setSelecProgramaEducativo(new Programaeducativo());
            programaEducativoBeanHelper.setProgramaEducativo(new Programaeducativo());
            programaEducativoBeanHelper.setSelecProgramaEducativo(new Programaeducativo());
            //profesorBeanHelper.setProfesor(profesor.setProid(0));

            //profesor.setProid(0);
            RequestContext.getCurrentInstance().execute("confirmdlg.hide();");

            if (programaEducativoBeanHelper.getListaSeleccionPe().size() >= 1) {
                programaEducativoBeanHelper.setProgramaEducativo(programaEducativoBeanHelper.getListaSeleccionPe().get(0));
                programaEducativoBeanHelper.setUnidadacademica(programaEducativoBeanHelper.getListaSeleccionPe().get(0).getUnidadacademica());

                RequestContext.getCurrentInstance().execute("dlg.show();");
            }
//        } else {
//            programaEducativoBeanHelper.getProgramaEducativoDelegate().agregarProgramaEducativo(programaEducativoBeanHelper.getProgramaEducativo());
//            FacesContext context = FacesContext.getCurrentInstance();
//            context.addMessage(null, new FacesMessage("Modificaci&#243;n", "Se guard&#243; correctamente"));
//            RequestContext.getCurrentInstance().execute("confirmacion.hide();");
            }else{                
                RequestContext.getCurrentInstance().execute("confirmdlg.hide();");
                limpiarSeleccion();
            }
        }else{            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Modificando", "Se guardó correctamente"));
            programaEducativoBeanHelper.getProgramaEducativoDelegate().agregarProgramaEducativo(programaEducativoBeanHelper.getProgramaEducativo());
            programaEducativoBeanHelper.seleccionarRegistro();
            programaEducativoBeanHelper.setListaSeleccionPe(programaEducativoBeanHelper.getListaSeleccionPe());
            RequestContext.getCurrentInstance().execute("dlg.show();");
                RequestContext.getCurrentInstance().execute("confirmdlg.hide();");
        }
        filtrado();

    }

    //private String mensajeConfirmdlg;
    public String onClickSubmit() {
        String resultado = "";
        //setMensajeConfirm();
        if (deshabilitar.equals("true")) {
            if(getPEAsignado(programaEducativoBeanHelper.getProgramaEducativo().getPedid()).size()<1){
                System.out.println("NO ESTA ASIGNADO");
                mensajeConfirm="¿Está seguro de eliminar el registro?";
                renderCancelar="true";
            }else{
                System.out.println("ESTA ASIGNADO");
                mensajeConfirm="El Programa Educativo tiene uno o mas planes de estudio asignados.";
                renderCancelar="false";
            }
            RequestContext.getCurrentInstance().update("confirmdlg");
            RequestContext.getCurrentInstance().execute("confirmdlg.show()");
        } else {
            if(programaEducativoBeanHelper.getRolSeleccionado().equalsIgnoreCase("director")
                    ||programaEducativoBeanHelper.getRolSeleccionado().equalsIgnoreCase("subdirector")){
                programaEducativoBeanHelper.setUnidadacademica(programaEducativoBeanHelper.UAbyProf());
            }
            
            System.out.println("\n\n\n"+programaEducativoBeanHelper.getUnidadacademica().getUacnombre()+"\n\n\n\n");
            
            if (validacion() == true) {
                System.out.println("entre aqui 1");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Favor de llenar todos los campos vacios");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            } else {
                mensajeRep = programaEducativoBeanHelper.validarRepetidos();
                if (mensajeRep.isEmpty()) {
                    mensajeRep = "vacio";
                }
                if (mensajeRep.equals("vacio")) {

                    if (header.equals("Agregar Programa Educativo")) {
                        //System.out.println("kakakakakak");
                        programaEducativoBeanHelper.getProgramaEducativo().setUnidadacademica(programaEducativoBeanHelper.getUnidadacademica());

                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Guardado", "Se guardó correctamente"));

                        programaEducativoBeanHelper.getProgramaEducativoDelegate().agregarProgramaEducativo(programaEducativoBeanHelper.getProgramaEducativo());
                        programaEducativoBeanHelper.setProgramaEducativo(new Programaeducativo());
                        programaEducativoBeanHelper.setSelecProgramaEducativo(new Programaeducativo());
                        programaEducativoBeanHelper.setUnidadacademica(new Unidadacademica());
                    } else {
                        if (header.equals("Modificar Programa Educativo")) {
                            if(getPEAsignado(programaEducativoBeanHelper.getProgramaEducativo().getPedid()).size()>0){
                                System.out.println("NO ESTA ASIGNADO");
                                mensajeConfirm="Este Programa Educativo tiene asignado uno o mas Planes de Estudio.\n¿Está seguro de modificar el registro?";
                                renderCancelar="true";
                                RequestContext.getCurrentInstance().update("confirmdlg");
                                RequestContext.getCurrentInstance().execute("confirmdlg.show()");
                            }else{
                            FacesContext context = FacesContext.getCurrentInstance();
                            context.addMessage(null, new FacesMessage("Modificando", "Se guardó correctamente"));

                            programaEducativoBeanHelper.getProgramaEducativoDelegate().agregarProgramaEducativo(programaEducativoBeanHelper.getProgramaEducativo());
                            programaEducativoBeanHelper.seleccionarRegistro();
                            programaEducativoBeanHelper.setListaSeleccionPe(programaEducativoBeanHelper.getListaSeleccionPe());
                            RequestContext.getCurrentInstance().execute("dlg.show();");
                            }
                        }
                    }

                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Campos repetidos en:" + mensajeRep);
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }

            }
        }
        filtrado();
        mostrarBotones();
        return resultado;
    }

    //ya
    public void filtrado() {
        listaFiltrada = programaEducativoBeanHelper.filtrado(busqueda);
    }

    //ya
    public void mostrarBotones() {
        try {
            if (programaEducativoBeanHelper.getSelecProgramaEducativo().getPedid() > 0) {
                deshabilitarBoton = "false";
                titleElim = "Eliminar";
                titleMod = "Modificar";
            }
        } catch (NullPointerException e) {
        }
    }

    //ya
    public void esconderBotones() {
        deshabilitarBoton = "true";
        titleElim = "Seleccione un registro de la tabla";
        titleMod = "Seleccione un registro de la tabla";
    }

    //ya
    public String botonesSelect() {
        if (programaEducativoBeanHelper.getSelecProgramaEducativo().getPedid() > 0) {
            //habilitarBoton = "false";
            return "false";
        } else {
            //habilitarBoton = "true";
            return "true";
        }
    }

    //ya
//    public boolean mostrarSeleccionPe() {
//        if (programaEducativoBeanHelper.getListaSeleccionPe() != null && programaEducativoBeanHelper.getListaSeleccionPe().size() > 1) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    public boolean mostrarSeleccionPe() {
        return programaEducativoBeanHelper.getListaSeleccionPe() != null && programaEducativoBeanHelper.getListaSeleccionPe().size() > 1;
    }

    //ya
    public void limpiarSeleccion() {
        System.out.println("LIMPIANDO SELECCION##");
        programaEducativoBeanHelper.setListaSeleccionPe(null);
        programaEducativoBeanHelper.setProgramaEducativo(new Programaeducativo());
        programaEducativoBeanHelper.setSelecProgramaEducativo(new Programaeducativo());
        programaEducativoBeanHelper.setUnidadacademica(new Unidadacademica());
        mostrarSeleccionPe();
        botonesModElim();
    }

    //ay
    public String botonesModElim() {
        System.out.println("entre aqui");
        if (programaEducativoBeanHelper.getListaSeleccionPe() == null
                || programaEducativoBeanHelper.getListaSeleccionPe().size() < 1) {

            return "true";
        } else {

            return "false";
        }
    }

    //ya
    public String tooltip(int i) {
        if (programaEducativoBeanHelper.getListaSeleccionPe() == null || programaEducativoBeanHelper.getListaSeleccionPe().size() < 1) {
            return "Seleccione un registro de la tabla";
        } else {
            if (i == 1) {
                return "Eliminar";
            }
            if (i == 2) {
                return "Modificar";
            }
        }
        return "";
    }
    //ya

    public List<Unidadacademica> cargarUnidadAcademica() {
        listaUnidad = programaEducativoBeanHelper.getUnidadAcademicaDelegate().getListaUnidadAcademica();
        return listaUnidad;
    }
//ya

    public void onRowUnselect(UnselectEvent event) {
        programaEducativoBeanHelper.setSelecProgramaEducativo(new Programaeducativo());
        programaEducativoBeanHelper.setSelecProgramaEducativo((Programaeducativo) event.getObject());
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

    public boolean deshabilitarMenu() {
        if (programaEducativoBeanHelper.getListaSeleccionPe() == null || programaEducativoBeanHelper.getListaSeleccionPe().size() < 1) {
            return true;
        }
        return false;
    }

    //get y set
    public ProgramaEducativoBeanHelper getProgramaEducativoBeanHelper() {
        return programaEducativoBeanHelper;
    }

    public void setProgramaEducativoBeanHelper(ProgramaEducativoBeanHelper programaEducativoBeanHelper) {
        this.programaEducativoBeanHelper = programaEducativoBeanHelper;
    }

    public UnidadAcademicaBeanHelper getUnidadAcademicaBeanHelper() {
        return unidadAcademicaBeanHelper;
    }

    public void setUnidadAcademicaBeanHelper(UnidadAcademicaBeanHelper unidadAcademicaBeanHelper) {
        this.unidadAcademicaBeanHelper = unidadAcademicaBeanHelper;
    }

    public List<Programaeducativo> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Programaeducativo> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public List<Unidadacademica> getListaUnidad() {
        return listaUnidad;
    }

    public void setListaUnidad(List<Unidadacademica> listaUnidad) {
        this.listaUnidad = listaUnidad;
    }

    public String getCabecera() {
        return cabecera;
    }

    public void setCabecera(String cabecera) {
        this.cabecera = cabecera;
    }

    public String getTextoBoton() {
        return textoBoton;
    }

    public void setTextoBoton(String textoBoton) {
        this.textoBoton = textoBoton;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public boolean isSelecvisible() {
        return selecvisible;
    }

    public void setSelecvisible(boolean selecvisible) {
        this.selecvisible = selecvisible;
    }

    public String getMensajeRep() {
        return mensajeRep;
    }

    public void setMensajeRep(String mensajeRep) {
        this.mensajeRep = mensajeRep;
    }

    public String getDeshabilitarBoton() {
        return deshabilitarBoton;
    }

    public void setDeshabilitarBoton(String deshabilitarBoton) {
        this.deshabilitarBoton = deshabilitarBoton;
    }

    public String getTitleElim() {
        return titleElim;
    }

    public void setTitleElim(String titleElim) {
        this.titleElim = titleElim;
    }

    public String getTitleMod() {
        return titleMod;
    }

    public void setTitleMod(String titleMod) {
        this.titleMod = titleMod;
    }

    public String getDeshabilitarAceptar() {
        return deshabilitarAceptar;
    }

    public void setDeshabilitarAceptar(String deshabilitarAceptar) {
        this.deshabilitarAceptar = deshabilitarAceptar;
    }
 
    private String renderCancelar;

    public String getRenderCancelar() {
        return renderCancelar;
    }

    public void setRenderCancelar(String renderCancelar) {
        this.renderCancelar = renderCancelar;
    }
    
    
    
    public List<Programaeducativo> getPEAsignado(int idPE) {
        List<Programaeducativo> listaPEasignado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Planestudio.class);
        listaPEasignado = ServiceLocator.getInstanceBaseDAO().findFromWhereB("programaeducativo", "pedid", String.valueOf(idPE),"pesid");
        return listaPEasignado;
    }
}
