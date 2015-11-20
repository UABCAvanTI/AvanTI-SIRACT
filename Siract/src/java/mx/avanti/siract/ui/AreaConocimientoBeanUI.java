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
import mx.avanti.siract.application.helper.CapturaAreaConocimientoBeanHelper;
import mx.avanti.siract.business.PlanEstudioDelegate;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Rol;
import mx.avanti.siract.business.entity.Unidadacademica;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.common.integration.ServiceLocator;
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
    private CapturaAreaConocimientoBeanHelper areaBeanHelper2 = null;
    private List<Areaconocimiento> listaFiltrada; 
    private List<Planestudio> listaPlanEstudio;  
    //private PlanEstudioDelegate planEstudiDelegate;    
    private String busqueda="";  
    private String cabecera;
    private String textoBoton;
    private String deshabilitar = "";
    private String mensajeConfirm;
    private String mensajeRep;
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    
        @PostConstruct
    public void postConstructor() {
        areaBeanHelper.setRolSeleccionado(loginBean.getSeleccionado());
        areaBeanHelper.setUsuario(loginBean.getLogueado());
        filtrado();
    }
    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
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
        areaBeanHelper2 = new CapturaAreaConocimientoBeanHelper();
    }
    public List<Areaconocimiento> getListaFiltrada() {
	return listaFiltrada;
    }

    public void setListaFiltrada(List<Areaconocimiento> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
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
    
    public CapturaAreaConocimientoBeanHelper getAreaBeanHelper2() {
        return areaBeanHelper2;
    }

    public void setAreaBeanHelper2(CapturaAreaConocimientoBeanHelper areaBeanHelper2) {
        this.areaBeanHelper2 = areaBeanHelper2;
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
                
                
                //for(int x=0; x<areaBeanHelper.getListaSeleccionAcon().size();x++){
                    //if(areaBeanHelper.getListaSeleccionAcon().get(x).getAcoid() == areaBeanHelper.getSelecAreaconocimiento().getAcoid()){
                        areaBeanHelper.setAreaConocimiento(areaBeanHelper.getListaSeleccionAcon().get(0));
                        areaBeanHelper.setProgramaeducativo(areaBeanHelper.getListaSeleccionAcon().get(0).getPlanestudio().getProgramaeducativo());
                        areaBeanHelper.filtrarPlanPorPE();
                        areaBeanHelper.setPlanestudio(areaBeanHelper.getListaSeleccionAcon().get(0).getPlanestudio());                
                    //}
                //}
                
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
//                areaBeanHelper.setAreaConocimiento(new Areaconocimiento());
//                areaBeanHelper.setProgramaeducativo(new Programaeducativo());
//                areaBeanHelper.setPlanestudio(new Planestudio());
                                
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
        
        areaBeanHelper.eliminarDeLista(areaBeanHelper.getAreaConocimiento().getAcoid());        
        //areaBeanHelper.getAreaConocimientoDelegate().eliminarAreaConocimiento(areaBeanHelper.getSelecAreaconocimiento());
        areaBeanHelper.getAreaConocimientoDelegate().eliminarAreaConocimiento(areaBeanHelper.getAreaConocimiento());
        areaBeanHelper.setAreaConocimiento(new Areaconocimiento());
        areaBeanHelper.setSelecAreaconocimiento(new Areaconocimiento());        
        RequestContext.getCurrentInstance().execute("confdlgElim.hide()");
        
        
        if(areaBeanHelper.getListaSeleccionAcon().size() >= 1){
                areaBeanHelper.setAreaConocimiento(areaBeanHelper.getListaSeleccionAcon().get(0));    
                areaBeanHelper.setProgramaeducativo(areaBeanHelper.getListaSeleccionAcon().get(0).getPlanestudio().getProgramaeducativo());
                areaBeanHelper.filtrarPlanPorPE();
                areaBeanHelper.setPlanestudio(areaBeanHelper.getListaSeleccionAcon().get(0).getPlanestudio());
                RequestContext.getCurrentInstance().execute("dlg.show();");
        }
        else{            
            RequestContext.getCurrentInstance().execute("dlg.hide();");
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("", "Se eliminó correctamente"));    
        //RequestContext.getCurrentInstance().execute("dlg.hide");
  
        
        filtrado();                       
    }
    
    public void nuevo(){
        limpiarSeleccion();        
        header1(1);
        areaBeanHelper.setAreaConocimiento(new Areaconocimiento());        
        areaBeanHelper.setSelecAreaconocimiento(new Areaconocimiento());
        areaBeanHelper.getPlanestudio().setProgramaeducativo(new Programaeducativo());
    }
    
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
    
    public String validacion2(){
        if(areaBeanHelper.getAreaConocimiento().getAconombre().equalsIgnoreCase("")
                && areaBeanHelper.getPlanestudio().getPesid() == 0 
                && areaBeanHelper.getProgramaeducativo().getPedid()==0){              
            return "Capturar campo(s) vacío(s) nombre, programa educativo y plan de estudios";
        }
        if(areaBeanHelper.getAreaConocimiento().getAconombre().equalsIgnoreCase("")&& areaBeanHelper.getProgramaeducativo().getPedid()==0){              
            return "Capturar campo(s) vacío(s) plan de estudios";
        }
        if(areaBeanHelper.getAreaConocimiento().getAconombre().equalsIgnoreCase("")&& areaBeanHelper.getPlanestudio().getPesid() == 0){
            return "Capturar campo(s) vacío(s) nombre y plan de estudios";
        }
        if(areaBeanHelper.getProgramaeducativo().getPedid()==0 && areaBeanHelper.getPlanestudio().getPesid() == 0){
            return "Capturar campo(s) vacío(s) programa educativo y plan de estudios";
        }
        if(areaBeanHelper.getAreaConocimiento().getAconombre().equalsIgnoreCase("")){
            return "Capturar campo(s) vacío(s) nombre";
        }
        if(areaBeanHelper.getPlanestudio().getPesid() == 0){
            return "Capturar campo(s) vacío(s) plan de estudios";
        }
        if(areaBeanHelper.getProgramaeducativo().getPedid()==0){
            return "Capturar campo(s) vacío(s) programa educativo";
        }
        
        return "nada";
    }
    
    public String onClickSubmit(){
        String resultado="";
        setMensajeConfirm();
        
        if(deshabilitar.equals("true")){ 
            
            if(getAreaUnidad(areaBeanHelper.getAreaConocimiento().getAcoid()).size()<1){                
                mensajeConfirm="¿Está seguro de eliminar el registro?";
                RequestContext.getCurrentInstance().update("confdlg");            
                RequestContext.getCurrentInstance().update("capdlg");            
                RequestContext.getCurrentInstance().execute("confdlgElim.show()");                                              
            }
            else{                
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Esta área de conocimiento esta asignada a una unidad de aprendizaje, no se puede eliminar");         
                RequestContext.getCurrentInstance().showMessageInDialog(message);                                
                RequestContext.getCurrentInstance().execute("dlg.hide();");                
            }
//            RequestContext.getCurrentInstance().update("confdlg");            
//            RequestContext.getCurrentInstance().update("capdlg");            
//            RequestContext.getCurrentInstance().execute("confdlgElim.show()");                                              
        }else{
            if(!validacion2().equalsIgnoreCase("nada")){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", validacion2());         
                RequestContext.getCurrentInstance().showMessageInDialog(message);                
                
                //RequestContext.getCurrentInstance().execute("dlg.hide();");
            }
            else{
                String nom=areaBeanHelper.getAreaConocimiento().getAconombre();
                String prog=areaBeanHelper.buscarNomProedu(areaBeanHelper.getProgramaeducativo().getPedid());                
                String plan=areaBeanHelper.buscarVigPlan(areaBeanHelper.getPlanestudio().getPesid());                
                String todo=nom+"--"+prog+"--"+plan;
                mensajeRep = areaBeanHelper.validarRepetidos(todo);                
                
                if(mensajeRep.isEmpty()){
                    mensajeRep = "vacio";
                }
                    
                if(mensajeRep.equals("vacio")){
                    
                    if(cabecera.equals("Agregar área de conocimiento")){                        
                        
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("", "Se guardó correctamente"));                        
                        //RequestContext.getCurrentInstance().execute("dlg.hide();");
                        areaBeanHelper.getAreaConocimientoDelegate().agregarAreaConocimiento(areaBeanHelper.getAreaConocimiento());
                        areaBeanHelper.setAreaConocimiento(new Areaconocimiento());
                        areaBeanHelper.setSelecAreaconocimiento(new Areaconocimiento());  
                        areaBeanHelper.setPlanestudio(new Planestudio());
                        areaBeanHelper.setProgramaeducativo(new Programaeducativo());
                        limpiarSeleccion();
                    }else{ 
                        if(cabecera.equals("Modificar área de conocimiento")){                        
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("", "Se guardó correctamente"));

                        areaBeanHelper.getAreaConocimientoDelegate().agregarAreaConocimiento(areaBeanHelper.getAreaConocimiento());
                        areaBeanHelper.seleccionarRegistro();
                        areaBeanHelper.setListaSeleccionAcon(areaBeanHelper.getListaSeleccionAcon());                       
                        RequestContext.getCurrentInstance().execute("dlg.show();");                       
                        
                        }                         
                    }
                }else{
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "Campos repetidos en " + mensajeRep);
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
            setCabecera("Agregar área de conocimiento");    
            deshabilitar = "false";
        }
        if (i == 2) {
            setCabecera("Eliminar área de conocimiento");            
            deshabilitar = "true";
        }
        if (i == 3) {
            setCabecera("Modificar área de conocimiento");            
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
        areaBeanHelper.getPlanestudio().setProgramaeducativo(new Programaeducativo());        
        areaBeanHelper.setProgramaeducativo(new Programaeducativo());
        filtrado();
        mostrarSeleccionArea();
        botonesModElim();         
    }
    
    public List<Planestudio> cargarPlan(){
        listaPlanEstudio = areaBeanHelper.getPlanEstudioDelegate().getListaPlanEstudio();
        return listaPlanEstudio;
    }           
   
    public List<Areaconocimiento> getAreaUnidad(int idACON) {
        List<Areaconocimiento> listaAreaUnidad = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);        
        //listaAreaUnidad = ServiceLocator.getInstanceBaseDAO().findFromWhere("areaconocimiento", "ACOid", String.valueOf(idACON));
        listaAreaUnidad = ServiceLocator.getInstanceBaseDAO().findFromWhere("areaconocimientos", "acoid", String.valueOf(idACON));
        return listaAreaUnidad;
    }
      
    public void filtrado() {
        
        List<Rol> list = null;
        list = loginBean.Obtenerrol(loginBean.getLogueado().getUsuid());
        String seleccionado=loginBean.getSeleccionado();
        System.out.println(seleccionado+"ÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑ");
        String catalogo="Administración de área de conocimiento";
        loginBean.TienePermiso(list, seleccionado, catalogo);
        
        String busqProg="";
        String busqProgPlan="";
        
        areaBeanHelper.getUsuarioTienePE();
        try{
            busqProg=areaBeanHelper.buscarNomProedu(areaBeanHelper.getProgramaeducativo().getPedid());                
            busqProgPlan=areaBeanHelper.buscarVigPlan(areaBeanHelper.getPlanestudio().getPesid());
        }catch(Exception e){
            
        }
        if(busqProg.equalsIgnoreCase("")){
            listaFiltrada = areaBeanHelper.filtrado("Nombre", busqueda);
        }
        else{
            if(busqProgPlan.equalsIgnoreCase("")){
                if(busqueda.equalsIgnoreCase("")){
                    listaFiltrada = areaBeanHelper.filtrado("Progedu", busqProg);
                }
                else{
                    String busqProgNom=busqueda+"--"+busqProg;
                    listaFiltrada = areaBeanHelper.filtrado("ProgeduNom", busqProgNom);
                }
                    
            }
            else{
                if(busqueda.equalsIgnoreCase("")){
                    String busqProgPlan2=busqProg+"--"+busqProgPlan;
                    listaFiltrada = areaBeanHelper.filtrado("ProgPlan", busqProgPlan2);
                }
                else{
                    String todo=busqueda+"--"+busqProg+"--"+busqProgPlan;
                    listaFiltrada = areaBeanHelper.filtrado("todo", todo);
                }
            }
        }        
    }
    
   
}