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
import javax.faces.context.FacesContext;
import mx.avanti.siract.application.helper.CapturaAreaConocimientoBeanHelper;
import mx.avanti.siract.business.PlanEstudioDelegate;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Programaeducativo;
import org.primefaces.context.RequestContext;
import org.primefaces.event.UnselectEvent;
/**
 *
 * @author balta
 */
@ManagedBean
@ViewScoped
public class AreaConocimientoBeanUI implements Serializable{
    
    
    private CapturaAreaConocimientoBeanHelper areaBeanHelper = null;        
    private List<Areaconocimiento> listaFiltrada; 
    private List<Planestudio> listaPlanEstudio;  
    //private PlanEstudioDelegate planEstudiDelegate;    
    private String busqueda="";  
    private String cabecera;
    private String textoBoton;
    private String deshabilitar = "";
    private String mensajeConfirm;
    private String mensajeRep;

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
    private String deshabilitarBoton="true";
    private String titleMod = "Seleccione un registro de la tabla";    
    private String titleElim = "Seleccione un registro de la tabla";
       		    
    public AreaConocimientoBeanUI(){
        init();
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

    public String getDeshabilitar() {
        return deshabilitar;
    }

    public void setDeshabilitar(String deshabilitar) {
        this.deshabilitar = deshabilitar;
    }
        
    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
    
    public void init(){
        areaBeanHelper = new CapturaAreaConocimientoBeanHelper();
    }
    public List<Areaconocimiento> getListaFiltrada() {
	return listaFiltrada;
    }

    public void setListaFiltrada(List<Areaconocimiento> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public void filtrado() {
        listaFiltrada = areaBeanHelper.filtrado("Nombre", busqueda);
    }
    
    public void filtrado2() {
//        listaFiltrada = areaBeanHelper.filtrado2("Nombre", areaBeanHelper.getProgramaeducativo().getPedid());
    }
    
    public void planfiltrado() {
        listaFiltrada = areaBeanHelper.filtrado("Nombre", busqueda);
    }

    public CapturaAreaConocimientoBeanHelper getAreaBeanHelper() {
        return areaBeanHelper;
    }

    public void setAreaBeanHelper(CapturaAreaConocimientoBeanHelper areaBeanHelper) {
        this.areaBeanHelper = areaBeanHelper;
    }

    public List<Planestudio> getListaPlanEstudio() {
        return listaPlanEstudio;
    }

    public void setListaPlanEstudio(List<Planestudio> listaPlanEstudio) {
        this.listaPlanEstudio = listaPlanEstudio;
    }
    
    public void modificar(){
        header1(3);
        cargarPlan();        
        try{
            if(areaBeanHelper.getListaSeleccionAcon().size() == 1){
                areaBeanHelper.setAreaConocimiento(areaBeanHelper.getListaSeleccionAcon().get(0));    
                areaBeanHelper.setProgramaeducativo(areaBeanHelper.getListaSeleccionAcon().get(0).getPlanestudio().getProgramaeducativo());
                areaBeanHelper.filtrarPlanPorPE();
                areaBeanHelper.setPlanestudio(areaBeanHelper.getListaSeleccionAcon().get(0).getPlanestudio());
            } else {
                areaBeanHelper.setAreaConocimiento(new Areaconocimiento());
                areaBeanHelper.setProgramaeducativo(new Programaeducativo());
                areaBeanHelper.setPlanestudio(new Planestudio());
                                
                areaBeanHelper.setAreaConocimiento(areaBeanHelper.getListaSeleccionAcon().get(0));
                areaBeanHelper.setProgramaeducativo(areaBeanHelper.getListaSeleccionAcon().get(0).getPlanestudio().getProgramaeducativo());
                areaBeanHelper.filtrarPlanPorPE();
                areaBeanHelper.setPlanestudio(areaBeanHelper.getListaSeleccionAcon().get(0).getPlanestudio());                
                
            }
        }catch(NullPointerException e){
            
        }

    }
    

    public void eliminar(){
        header1(2);
        cargarPlan();
        
        try{
            if(areaBeanHelper.getListaSeleccionAcon().size() == 1){
                areaBeanHelper.setAreaConocimiento(areaBeanHelper.getListaSeleccionAcon().get(0));    
                areaBeanHelper.setProgramaeducativo(areaBeanHelper.getListaSeleccionAcon().get(0).getPlanestudio().getProgramaeducativo());
                areaBeanHelper.filtrarPlanPorPE();
                areaBeanHelper.setPlanestudio(areaBeanHelper.getListaSeleccionAcon().get(0).getPlanestudio());
            } else {
                areaBeanHelper.setAreaConocimiento(new Areaconocimiento());
                areaBeanHelper.setProgramaeducativo(new Programaeducativo());
                areaBeanHelper.setPlanestudio(new Planestudio());
                                
                areaBeanHelper.setAreaConocimiento(areaBeanHelper.getListaSeleccionAcon().get(0));
                areaBeanHelper.setProgramaeducativo(areaBeanHelper.getListaSeleccionAcon().get(0).getPlanestudio().getProgramaeducativo());
                areaBeanHelper.filtrarPlanPorPE();
                areaBeanHelper.setPlanestudio(areaBeanHelper.getListaSeleccionAcon().get(0).getPlanestudio());                
            }
        }catch(NullPointerException e){
            
        }
    }
    
    public void eliminacionConfirmada(){
        cargarPlan();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Eliminando", "Se eliminó correctamente"));            
        if(areaBeanHelper.getListaSeleccionAcon().size() == 1){
                areaBeanHelper.setSelecAreaconocimiento(areaBeanHelper.getListaSeleccionAcon().get(0));
                areaBeanHelper.setPlanestudio(areaBeanHelper.getListaSeleccionAcon().get(0).getPlanestudio());
        }
        areaBeanHelper.getAreaConocimientoDelegate().eliminarAreaConocimiento(areaBeanHelper.getSelecAreaconocimiento());
        areaBeanHelper.setAreaConocimiento(new Areaconocimiento());
        areaBeanHelper.setSelecAreaconocimiento(new Areaconocimiento());
        RequestContext.getCurrentInstance().execute("confdlgElim.hide()");
        RequestContext.getCurrentInstance().execute("dlg.hide()");
  
        
        filtrado();                       
    }
    
    public void nuevo(){
        limpiarSeleccion();        
        header1(1);
        areaBeanHelper.setAreaConocimiento(new Areaconocimiento());        
        areaBeanHelper.setSelecAreaconocimiento(new Areaconocimiento());
        areaBeanHelper.getPlanestudio().setProgramaeducativo(new Programaeducativo());
    }
    
//    public void nuevo() {
//        //limpiarSeleccion();
//        header(1);
//        areaBeanHelper.setAreaConocimiento(new Areaconocimiento());
//    }
//    public void eliminar() {
//        header(2);
//    }
//    public void modificar() {
//        header(3);
//    }
    public void setMensajeConfirm(){
        if(deshabilitar.equals("true")){
            mensajeConfirm = "¿Está seguro de eliminar el registro?";
        }else{
            mensajeConfirm = "¿Está seguro de guardar los cambios?";
        }
        RequestContext.getCurrentInstance().update("confirmdlg");
    }  
    public boolean validacion(){
        if(areaBeanHelper.getAreaConocimiento().getAconombre().equalsIgnoreCase("")
                || areaBeanHelper.getPlanestudio().getPesid() == 0){  
            
            return true;
        }
        else
            return false;
        
    }
    
    public String onClickSubmit(){
        String resultado="";
        setMensajeConfirm();
        
        if(deshabilitar.equals("true")){ 
            RequestContext.getCurrentInstance().execute("confdlgElim.show()");                                              
        }else{
            if(validacion()){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Favor de llenar todos los campos vacios");         
                RequestContext.getCurrentInstance().showMessageInDialog(message);                
            }
            else{
                //mensajeRep = areaBeanHelper.validarRepetidos();
                mensajeRep="";
                if(mensajeRep.isEmpty()){
                    mensajeRep = "vacio";
                }
                if(mensajeRep.equals("vacio")){
                    
                    if(cabecera.equals("Agregar Área de conocimiento")){                        
                        
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Agregando", "Se guardó correctamente"));
                        RequestContext.getCurrentInstance().execute("dlg.hide();");
                        areaBeanHelper.getAreaConocimientoDelegate().agregarAreaConocimiento(areaBeanHelper.getAreaConocimiento());
                        areaBeanHelper.setAreaConocimiento(new Areaconocimiento());
                        areaBeanHelper.setSelecAreaconocimiento(new Areaconocimiento());  
                        areaBeanHelper.setPlanestudio(new Planestudio());
                    }else{ 
                        if(cabecera.equals("Modificar Área de conocimiento")){                        
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Modificando", "Se guardó correctamente"));

                        areaBeanHelper.getAreaConocimientoDelegate().agregarAreaConocimiento(areaBeanHelper.getAreaConocimiento());
                        areaBeanHelper.seleccionarRegistro();
                        areaBeanHelper.setListaSeleccionAcon(areaBeanHelper.getListaSeleccionAcon());                       
                        RequestContext.getCurrentInstance().execute("dlg.show();");
                        }                         
                    }
                }else{
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Campos repetidos en: " + mensajeRep);
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
            
        }
        }            
        filtrado();
        mostrarBotones();
        return resultado;
    }    
     public void mostrarBotones(){
        try{
            if(areaBeanHelper.getSelecAreaconocimiento().getAcoid() > 0){
                deshabilitarBoton = "false";
                titleElim = "Eliminar";
                titleMod = "Modificar";
            }
        }catch(NullPointerException e){}
    }
    
    public String tooltip(int i) {
        if (areaBeanHelper.getListaSeleccionAcon() == null || areaBeanHelper.getListaSeleccionAcon().size() < 1) {
            return "Seleccione un registro de la tabla";
        } 
        else {
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
        if (areaBeanHelper.getListaSeleccionAcon() == null || areaBeanHelper.getListaSeleccionAcon().size() < 1) {
            return true;
        }
        return false;
    }
    public String header1(int i) {
        if (i == 1) {
            setCabecera("Agregar Área de conocimiento");    
            deshabilitar = "false";
        }
        if (i == 2) {
            setCabecera("Eliminar Área de conocimiento");            
            deshabilitar = "true";
        }
        if (i == 3) {
            setCabecera("Modificar Área de conocimiento");            
            deshabilitar = "false";
        }
        return cabecera;
    }
    
    public boolean mostrarSeleccionArea() {
        if (areaBeanHelper.getListaSeleccionAcon()!= null && areaBeanHelper.getListaSeleccionAcon().size() > 1) {
            return true;
        } else {
            return false;
        }
    }
    
//    public void eliminar() {
//        header(2);
//        cargarUsuario();
//        try {
//            if (areaBeanHelper.getListaSeleccionAcon().size() == 1) {
//                areaBeanHelper.setAreaConocimiento(areaBeanHelper.getListaSeleccionAcon().get(0));
//                profesorBeanHelper.setUsuario2(profesorBeanHelper.getListaSeleccionProfesores().get(0).getUsuario());
//            } else {
//                if (profesorBeanHelper.getListaSeleccionProfesores().size() < 1) {
//                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
//                    RequestContext.getCurrentInstance().showMessageInDialog(message);
//                } else {
//                    profesorBeanHelper.setProfesor(profesorBeanHelper.getListaSeleccionProfesores().get(0));
//                    profesorBeanHelper.setUsuario2(profesorBeanHelper.getListaSeleccionProfesores().get(0).getUsuario());
//                }
//            }
////           
//        } catch (NullPointerException e) {
//            profesorBeanHelper.setProfesor(new Profesor());            
//            profesorBeanHelper.setUsuario2(new Usuario());
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se seleccion&oacute; ning&uacute;n registro");
//            RequestContext.getCurrentInstance().showMessageInDialog(message);
//
//        }
//    }

    public void onRowUnselect(UnselectEvent event){
        areaBeanHelper.setSelecAreaconocimiento(new Areaconocimiento());
        areaBeanHelper.setSelecAreaconocimiento((Areaconocimiento)event.getObject());
    }
    public String botonesModElim(){
        if(areaBeanHelper.getListaSeleccionAcon() == null)
            return "true";
        else
            return "false";
    }    
    public void limpiarSeleccion(){
        areaBeanHelper.setListaSeleccionAcon(null);
        areaBeanHelper.setAreaConocimiento(new Areaconocimiento());
        areaBeanHelper.setSelecAreaconocimiento(new Areaconocimiento());     
        areaBeanHelper.setPlanestudio(new Planestudio());
        areaBeanHelper.setProgramaeducativo(new Programaeducativo());
        mostrarSeleccionArea();
        botonesModElim();         
    }
    
    public List<Planestudio> cargarPlan(){
        listaPlanEstudio = areaBeanHelper.getPlanEstudioDelegate().getListaPlanEstudio();
        return listaPlanEstudio;
    }       
}
