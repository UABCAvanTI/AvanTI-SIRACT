/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import mx.avanti.siract.application.helper.AreaAdministrativaBeanHelper;
import mx.avanti.siract.business.entity.Areaadministrativa;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Rol;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Paco
 */
@ManagedBean
@ViewScoped
public class AreaAdministrativaBeanUI implements Serializable{
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    
    AreaAdministrativaBeanHelper areaAdministrativaHelper;
    AreaAdministrativaBeanHelper areaAdministrativaHelper2;

    private List<Areaadministrativa> listaFiltrada;
    private String busqueda;
    private String cabecera;
    private String deshabilitar;
    private String MensajeVal;
    private String mensajeConfirmacion;    
    private String renderCancelar;
    
    public AreaAdministrativaBeanUI() {
        this.areaAdministrativaHelper = new AreaAdministrativaBeanHelper();
        this.areaAdministrativaHelper2 = new AreaAdministrativaBeanHelper();
        cabecera = "";
        deshabilitar="";
        busqueda="";
    }

    @PostConstruct
    public void postConstructor() {
//        profesorBeanHelper.setListaRol(loginBean.Obtenerrol(loginBean.getUsuario().getUsuid()));
//        profesorBeanHelper.setRolSeleccionado(loginBean.getSeleccionado());
//        profesorBeanHelper.setUsuario(loginBean.getUsuario());
        areaAdministrativaHelper.setRol(loginBean.getSeleccionado());
        areaAdministrativaHelper.setUsuariolog(loginBean.getLogueado());
        System.out.println("rol desde el BeanUI: " + loginBean.getSeleccionado());
        System.out.println("id del usuario desde login " + loginBean.getLogueado().getUsuid());
    }
    
    //<editor-fold defaultstate="collapsed" desc="getter setter">
    public AreaAdministrativaBeanHelper getAreaAdministrativaHelper() {
        return areaAdministrativaHelper;
    }

    public void setAreaAdministrativaHelper(AreaAdministrativaBeanHelper areaAdministrativaHelper) {
        this.areaAdministrativaHelper = areaAdministrativaHelper;
    }
    
    
    public AreaAdministrativaBeanHelper getAreaAdministrativaHelper2() {
        return areaAdministrativaHelper2;
    }

    public void setAreaAdministrativaHelper2(AreaAdministrativaBeanHelper areaAdministrativaHelper2) {
        this.areaAdministrativaHelper2 = areaAdministrativaHelper2;
    }

    public List<Areaadministrativa> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Areaadministrativa> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

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

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public String getMensajeConfirmacion() {
        return mensajeConfirmacion;
    }

    public void setMensajeConfirmacion(String mensajeConfirmacion) {
        this.mensajeConfirmacion = mensajeConfirmacion;
    }

    public String getRenderCancelar() {
        return renderCancelar;
    }

    public void setRenderCancelar(String renderCancelar) {
        this.renderCancelar = renderCancelar;
    }
    
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="ABM">
    public void nuevo() {
        header(1);
        limpiarSeleccion();
        areaAdministrativaHelper.setAreaAdministrativa(new Areaadministrativa(new Programaeducativo(), ""));
        //areaAdministrativaHelper.setProgramaEducativo(new Programaeducativo());
        areaAdministrativaHelper.cargarListaPEdlg();
    }
    public void eliminar() {
        header(2);
        areaAdministrativaHelper.cargarListaPEdlg();
        try {
            if (areaAdministrativaHelper.getListaSeleccion().size() == 1) {
                areaAdministrativaHelper.setAreaAdministrativa(areaAdministrativaHelper.getListaSeleccion().get(0));
                //areaAdministrativaHelper.setProgramaEducativo(areaAdministrativaHelper.getListaSeleccion().get(0).getProgramaeducativo());
                
            } else {
//                if (areaAdministrativaHelper.getListaSeleccion().size() < 1) {
//                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
//                    RequestContext.getCurrentInstance().showMessageInDialog(message);
//                }
//                else {
                
                    areaAdministrativaHelper.setAreaAdministrativa(areaAdministrativaHelper.getListaSeleccion().get(0));
                    //areaAdministrativaHelper.setProgramaEducativo(areaAdministrativaHelper.getListaSeleccion().get(0).getProgramaeducativo());
                
//                }
            }
//           
        } catch (NullPointerException e) {
                    areaAdministrativaHelper.setAreaAdministrativa(new Areaadministrativa(new Programaeducativo(), ""));
                    //areaAdministrativaHelper.setProgramaEducativo(new Programaeducativo());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se seleccion&oacute; ning&uacute;n registro");
            RequestContext.getCurrentInstance().showMessageInDialog(message);

        }
    }
        public void modificar() {
        header(3);
        areaAdministrativaHelper.cargarListaPEdlg();
        try {
            if (areaAdministrativaHelper.getListaSeleccion().size() == 1) {
                areaAdministrativaHelper.setAreaAdministrativa(areaAdministrativaHelper.getListaSeleccion().get(0));
                //areaAdministrativaHelper.setProgramaEducativo(areaAdministrativaHelper.getListaSeleccion().get(0).getProgramaeducativo());
                System.out.println("seleccionpedid: "+areaAdministrativaHelper.getListaSeleccion().get(0).getProgramaeducativo().getPedid());
                System.out.println("pedid: " +areaAdministrativaHelper.getProgramaEducativo().getPedid());
                
            } else {
//                if (areaAdministrativaHelper.getListaSeleccion().size() < 1) {
//                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
//                    RequestContext.getCurrentInstance().showMessageInDialog(message);
//                } else {
                    areaAdministrativaHelper.setAreaAdministrativa(new Areaadministrativa(new Programaeducativo(), ""));
                    //areaAdministrativaHelper.setProgramaEducativo(new Programaeducativo());
                
                    areaAdministrativaHelper.setAreaAdministrativa(areaAdministrativaHelper.getListaSeleccion().get(0));
                    areaAdministrativaHelper.setProgramaEducativo(areaAdministrativaHelper.getListaSeleccion().get(0).getProgramaeducativo());                    
                    //areaAdministrativaHelper.setProgramaEducativo(areaAdministrativaHelper.getListaSeleccion().get(0).getProgramaeducativo());
                
                //}
            }
        } catch (NullPointerException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

      public String onClickSubmit() {        
        setMensajeConfirmacion();
        if (deshabilitar.equals("true")) {
            RequestContext.getCurrentInstance().execute("confirmacion.show()");
        } 
        else {
            if (areaAdministrativaHelper.getAreaAdministrativa().getAadnombre().isEmpty()
                    && areaAdministrativaHelper.getAreaAdministrativa().getProgramaeducativo().getPedid() == 0) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Capturar campo(s) vacío(s) Programa Educativo y Nombre ");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
            else{
                if(areaAdministrativaHelper.getAreaAdministrativa().getAadnombre().isEmpty()){
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Capturar campo(s) vacío(s) Nombre ");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
                else{
                    if(areaAdministrativaHelper.getAreaAdministrativa().getProgramaeducativo().getPedid() == 0){
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Capturar campo(s) vacío(s) Programa educativo ");
                        RequestContext.getCurrentInstance().showMessageInDialog(message);
                    }
                    else {
                    MensajeVal = areaAdministrativaHelper.Validar();//Se manda a llamar el metodo que nos devolvera un mensaje sobre los campos repetidos
                    if (MensajeVal.isEmpty()) {//En caso de que la variable este vacia se le asignara una palabra para represente que no exten campos vacios
                        MensajeVal = "nada";
                    }
                    if (MensajeVal.equals("nada")) {
                        if (cabecera.equals("Agregar área administrativa")) {
                            //areaAdministrativaHelper.getAreaAdministrativa().setProgramaeducativo(areaAdministrativaHelper.getProgramaEducativo());
                            areaAdministrativaHelper.getAreaAdministrativaDelegate().agregarAreaAdministrativa(areaAdministrativaHelper.getAreaAdministrativa());

                            areaAdministrativaHelper.setAreaAdministrativa(new Areaadministrativa(new Programaeducativo(), ""));
                            //areaAdministrativaHelper.setProgramaEducativo(new Programaeducativo());                        
                            areaAdministrativaHelper.cargarListaPEdlg();
                            FacesContext context = FacesContext.getCurrentInstance();
                            context.addMessage(null, new FacesMessage("Se guardó correctamente",""));
                        } 
                        else {
    //                        if (profesorBeanHelper.getProfesorDeleagate().getProfAsignado(profesorBeanHelper.getProfesor().getProid()).size() > 0) {
    //                            RequestContext.getCurrentInstance().execute("confirmacion.show()");
    //                        } else {        
                                FacesContext context = FacesContext.getCurrentInstance();
                                context.addMessage(null, new FacesMessage("Se guardó correctamente",""));
                                //areaAdministrativaHelper.getAreaAdministrativa().setProgramaeducativo(areaAdministrativaHelper.getProgramaEducativo());
                                areaAdministrativaHelper.getAreaAdministrativaDelegate().agregarAreaAdministrativa(areaAdministrativaHelper.getAreaAdministrativa());
                                areaAdministrativaHelper.seleccionarRegistro();
                                areaAdministrativaHelper.setListaSeleccion(areaAdministrativaHelper.getListaSeleccion());             
                                busqueda="";
                                //areaAdministrativaHelper.cargarListaPEdlg();                                                        
                                //RequestContext.getCurrentInstance().execute("dlg.show();");                       
    //                        }
                        }
                } else {

                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", MensajeVal);
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
            }
            }
            }
        }
        filtrado();
        busqueda="";
        return "";
    }
      
      public void Confirmacion() {
        if (deshabilitar.equals("true")) {            
            if(renderCancelar.equals("true")){
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Se eliminó correctamente", ""));
//                System.out.println(areaAdministrativaHelper.getListaSeleccion().size());
            areaAdministrativaHelper.eliminarDeLista(areaAdministrativaHelper.getAreaAdministrativa().getAadid());
            areaAdministrativaHelper.getAreaAdministrativaDelegate().eliminarAreaAdministrativa(areaAdministrativaHelper.getAreaAdministrativa());
            areaAdministrativaHelper.setSeleccionAreaad(new Areaadministrativa(new Programaeducativo(), ""));
            areaAdministrativaHelper.setAreaAdministrativa(new Areaadministrativa(new Programaeducativo(), ""));
            //areaAdministrativaHelper.setProgramaEducativo(new Programaeducativo());
            
//                System.out.println(areaAdministrativaHelper.getListaSeleccion().size());
            RequestContext.getCurrentInstance().update(":formAreaAd:seleccionados");

            RequestContext.getCurrentInstance().execute("confirmacion.hide();");            

                System.out.println("\n\n"+areaAdministrativaHelper.getListaSeleccion().size());
            if (areaAdministrativaHelper.getListaSeleccion().size() >= 1) {
                areaAdministrativaHelper.setAreaAdministrativa(areaAdministrativaHelper.getListaSeleccion().get(0));
                RequestContext.getCurrentInstance().execute("dlg.show();");                
            }
            else{                
                RequestContext.getCurrentInstance().execute("dlg.hide();");                
            }            

            }else{
                RequestContext.getCurrentInstance().execute("confirmacion.hide();");
                limpiarSeleccion();
                RequestContext.getCurrentInstance().execute("dlg.hide();");
            }
            
        } else {
            areaAdministrativaHelper.getAreaAdministrativaDelegate().agregarAreaAdministrativa(areaAdministrativaHelper.getAreaAdministrativa());
                        
            RequestContext.getCurrentInstance().execute("confirmacion.hide();");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Se guardó correctamente",""));
            RequestContext.getCurrentInstance().execute("dlg.show();");        
        }
        
        filtrado();
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro eliminado correctamente");
//        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

//</editor-fold>
      //<editor-fold defaultstate="collapsed" desc="Cambios Interfaz">
      
    public String header(int i) {
        if (i == 1) {
            cabecera = "Agregar área administrativa";
            deshabilitar = "false";
        }
        if (i == 2) {
            cabecera = "Eliminar área administrativa";
            deshabilitar = "true";
        }
        if (i == 3) {
            cabecera = "Modificar área administrativa";
            deshabilitar = "false";
        }
        return cabecera;
    }
    
    public String tooltip(int i) {
        if (areaAdministrativaHelper.getListaSeleccion()== null || areaAdministrativaHelper.getListaSeleccion().size() < 1) {
            return "Seleccione un registro de la tabla";
        } else {
            if (i == 2) {
                return "Eliminar";
            }
            if (i == 3) {
                return "Modificar";
            }
        }
        return "nada";
    }
    public boolean deshabilitarMenu() {
        if (areaAdministrativaHelper.getListaSeleccion()== null || getAreaAdministrativaHelper().getListaSeleccion().size() < 1) {
            return true;
        }
        return false;
    }
    
    public boolean mostrarSeleccionAreasAD() {
        if (areaAdministrativaHelper.getListaSeleccion()!= null && areaAdministrativaHelper.getListaSeleccion().size() > 1) {
            return true;
        } else {
            return false;
        }
    }
    public void setMensajeConfirmacion() {
        if (deshabilitar.equals("true")) {
            if (areaAdministrativaHelper.getAreaAdministrativaDelegate().getAreaAsignada(areaAdministrativaHelper.getAreaAdministrativa().getAadid()).size() > 0) {
                mensajeConfirmacion = "El área tiene un coordinador y unidades de aprendizaje asignadas";
                renderCancelar = "false";
            } else {
                mensajeConfirmacion = "¿Está seguro de eliminar el registro?";
                renderCancelar = "true";
            }
        } else{
            if (cabecera.equals("Modificar Área Administrativa") && areaAdministrativaHelper.getAreaAdministrativaDelegate().getAreaAsignada(areaAdministrativaHelper.getAreaAdministrativa().getAadid()).size() > 0) {
            mensajeConfirmacion = "El area tiene un coordinador y unidades de aprendizaje asugnadas. ¿Está seguro de guardar los cambios?";
            renderCancelar = "true";
            }
        }
        //mensajeConfirmacion = "¿Esta seguro que desea eliminar el registro?";
        RequestContext.getCurrentInstance().update("confirmacionId");
    }
    
    public void limpiarSeleccion() {
        areaAdministrativaHelper.setListaSeleccion(null);
        areaAdministrativaHelper.setAreaAdministrativa(new Areaadministrativa(new Programaeducativo(), ""));
        //areaAdministrativaHelper.setProgramaEducativo(new Programaeducativo());
        mostrarSeleccionAreasAD();
    }
//</editor-fold>
    public void filtrado() {
        List<Rol> list = null;
        list = loginBean.Obtenerrol(loginBean.getLogueado().getUsuid());
        String seleccionado=loginBean.getSeleccionado();
        System.out.println(seleccionado+"ÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑ");
        String catalogo="Administración de área administrativa";
        loginBean.TienePermiso(list, seleccionado, catalogo);
        //listaFiltrada = areaAdministrativaHelper.filtrado("",busqueda);
        //System.out.println("-----------------------------"+busqueda);
        String busqProg="";    
        areaAdministrativaHelper.getUsuarioTienePE();
        try{
            busqProg=areaAdministrativaHelper.buscarNomProedu(areaAdministrativaHelper.getProgramaEducativo().getPedid());            
        }catch(Exception e){
            
        }
        if(busqProg.equalsIgnoreCase("")){
            listaFiltrada = areaAdministrativaHelper.filtrado("Nombre", busqueda);            
        }
        else{            
                if(busqueda.equalsIgnoreCase("")){
                    listaFiltrada = areaAdministrativaHelper.filtrado("Progedu", busqProg);                    
                }
                else{
                    String busqProgNom=busqueda+"--"+busqProg;
                    listaFiltrada = areaAdministrativaHelper.filtrado("ProgeduNom", busqProgNom);                    
                }            
        }        
    }
}