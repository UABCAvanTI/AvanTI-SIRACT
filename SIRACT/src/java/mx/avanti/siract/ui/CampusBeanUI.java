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
import mx.avanti.siract.application.helper.CampusBeanHelper;
import mx.avanti.siract.business.entity.Campus;
import org.primefaces.context.RequestContext;
/**
 *
 * @author Manuel Papa
 */
@ManagedBean
@ViewScoped
public class CampusBeanUI implements Serializable{
//    private Campus campus;
    private CampusBeanHelper campusBeanHelper = null;
    private String busqueda="";
    private List<Campus> listaFiltrada;
    private String header;
    private String boton;
    private String deshabilitar;
    private boolean band;
    private String message;
 
    
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getHeader(){
        return header; 
    }
    
    public void setHeader(final String header){
        this.header = header;
    }
    
    public void changeHeader(String titulo){
        setHeader(titulo);
    }

    public String getBoton() {
        return boton;
    }

    public void setBoton(String boton) {
        this.boton = boton;
    }

    public String getDeshabilitar() {
        return deshabilitar;
    }

    public void setDeshabilitar(String deshabilitar) {
        this.deshabilitar = deshabilitar;
    }
    
    public CampusBeanUI(){
        init();
    }
    
    private void init(){
        campusBeanHelper = new CampusBeanHelper();
    }
    
    public CampusBeanHelper getCampusBeanHelper() {
        return campusBeanHelper;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public List<Campus> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Campus> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public void filtrado(){
        listaFiltrada = campusBeanHelper.filtrado("Nombre", busqueda);        
    }

//    public void setCampusBeanHelper(CapturaCampusBeanHelper campusBeanHelper) {
//        this.campusBeanHelper = campusBeanHelper;
//    }
    public void nuevo(){
        cabecera(1);
        campusBeanHelper.setCampus(new Campus());
//        campusBeanHelper.setSelectedCampus(new Campus());
    } 
    
    public void modificar(){
         cabecera(3);
        try{
            if(campusBeanHelper.getSelectedCampus().getCamid()>0){
        campusBeanHelper.setCampus(campusBeanHelper.getSelectedCampus());
            }
        }catch(NullPointerException e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");         
            RequestContext.getCurrentInstance().showMessageInDialog(message);
//            context.addMessage(null, new FacesMessage("Successful",  "Your message: " + message) );
        }
    }
    
    public void eliminar(){ 
        cabecera(2); 
        try{
            if(campusBeanHelper.getSelectedCampus().getCamid()>0){
//                linea diferente a profesor
//        campusBeanHelper.getCampusDelegate().eliminarCampus(campusBeanHelper.getSelectedCampus());
                campusBeanHelper.setCampus(campusBeanHelper.getSelectedCampus());
            }
        }catch(NullPointerException e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");         
            RequestContext.getCurrentInstance().showMessageInDialog(message);
//            context.addMessage(null, new FacesMessage("Successful",  "Your message: " + message) );
        }
    }
    
    public String onClickSubmit(){
        System.out.println("Valores");
        
        if(deshabilitar.equals("false")){
            if(validacionVacio()){
                System.out.println("Valores 2");
                if(header.equals("Modificar Campus")){
                    System.out.println("Valores 3");
                    RequestContext.getCurrentInstance().execute("conDlgModif.show();");
                }
                else if(header.equals("Agregar Campus")){
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("adasd", "asdasdasdasd"));
                    
                    campusBeanHelper.getCampusDelegate().agregarCampus(campusBeanHelper.getCampus());
                    campusBeanHelper.setSelectedCampus(new Campus());
                    campusBeanHelper.setCampus(new Campus()); 
                }}
        }else{
            RequestContext.getCurrentInstance().execute("conDlgElim.show();");
        }   
       filtrado();
       return "";
    }
    
    public boolean mostrarSeleccionCampus() {
        return campusBeanHelper.getListaCampus() != null && campusBeanHelper.getListaCampus().size() > 1;
    }
    
     public String cabecera(int i){
        if(i==1){
            setHeader("Agregar Campus");
            deshabilitar="false";
        }else{
            if(i==2){
            setHeader("Eliminar Campus");
            deshabilitar="true";
        }else{
            if(i==3){
            header="Modificar Campus";
            deshabilitar="false";
        }}}
        return header;
    }
     
     public boolean validacionVacio(){
         if(campusBeanHelper.getCampus().getCamnombre().isEmpty()){
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Favor de llenar todos los campos vacios");
             RequestContext.getCurrentInstance().showMessageInDialog(message);
             return false;
         }else{
             return true;
         }}
     
     public void eliminConfir(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful",  "Your message: ") );         
        
        campusBeanHelper.getCampusDelegate().eliminarCampus(campusBeanHelper.getSelectedCampus()); 
        campusBeanHelper.setSelectedCampus(new Campus());
        campusBeanHelper.setCampus(new Campus());  
        RequestContext.getCurrentInstance().execute("conDlgElim.hide();");
        filtrado();
     }
     
     public void modifConfir(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful",  "Your message: ") );         
        
        campusBeanHelper.getCampusDelegate().agregarCampus(campusBeanHelper.getCampus());
        campusBeanHelper.setCampus(new Campus());
        campusBeanHelper.setSelectedCampus(new Campus());
        RequestContext.getCurrentInstance().execute("conDlgModif.hide();");
        filtrado();
     }
}