/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.ui;

import static com.sun.faces.context.flash.ELFlash.getFlash;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.avanti.siract.application.helper.CapturaPuestoBeanHelper;
import org.primefaces.context.RequestContext;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author balta
 */
@ManagedBean
@ViewScoped
//@SessionScoped
public class CapturaPuestoBeanUI implements Serializable{
////    private Puesto puesto;
//    private String busqueda;
//    private List<Puesto> listaFiltrada;
//    private String header;
//    private String deshabilitar;
//    private String deshabilitarAceptar;
//    private String band="true";
//    private CapturaPuestoBeanHelper puestoBeanHelper = null;
//    private String message;
//    private String deshabiliatarBoton = "true";
//    private String titleMod = "Seleccione un registro de la tabla";
//    private String titleElim = "Seleccione un registro de la tabla";
//    private boolean bandera;
//
//    public boolean isBandera() {
//        return bandera;
//    }
//
//    public void setBandera(boolean bandera) {
//        this.bandera = bandera;
//    }
//    
//    public String getDeshabiliatarBoton() {
//        return deshabiliatarBoton;
//    }
//
//    public void setDeshabiliatarBoton(String deshabiliatarBoton) {
//        this.deshabiliatarBoton = deshabiliatarBoton;
//    }
//
//    public String getTitleMod() {
//        return titleMod;
//    }
//
//    public void setTitleMod(String titleMod) {
//        this.titleMod = titleMod;
//    }
//
//    public String getTitleElim() {
//        return titleElim;
//    }
//
//    public void setTitleElim(String titleElim) {
//        this.titleElim = titleElim;
//    }
//    
//    
//    FacesContext context = FacesContext.getCurrentInstance();
//    
//    public String getMessage() {
//        return message;
//    }
// 
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public String getBusqueda() {
//        return busqueda;
//    }
//
//    public void setBusqueda(String busqueda) {
//        this.busqueda = busqueda;
//    }
//    
//    public CapturaPuestoBeanUI(){
//        init();
//    }
//    
//    public List<Puesto> getListaFiltrada() {
//        return listaFiltrada;
//    }
//    
//     public String getDeshabilitarAceptar() {
//        return deshabilitarAceptar;
//    }
//
//    public void setDeshabilitarAceptar(String deshabilitarAceptar) {
//        this.deshabilitarAceptar = deshabilitarAceptar;
//    }
//
//    public void setListaFiltrada(List<Puesto> listaFiltrada) {
//        this.listaFiltrada = listaFiltrada;
//    }
//    
//    private void init(){
//        puestoBeanHelper = new CapturaPuestoBeanHelper();
//        busqueda="";
//    }
//    
//    public void filtrado(){
//        listaFiltrada = puestoBeanHelper.filtrado("Nombre", busqueda);        
//    }
//
//    public CapturaPuestoBeanHelper getPuestoBeanHelper() {
//        return puestoBeanHelper;
//    }
//
////    public void setPuestoBeanHelper(CapturaPuestoBeanHelper puestoBeanHelper) {
////        this.puestoBeanHelper = puestoBeanHelper;
////    }
//    
//    public String getHeader(){
//        return header; 
//    }
//    
//    public void setHeader(final String header){
//        this.header = header;
//    }
//    
//    public void changeHeader(String titulo){
//        setHeader(titulo);
//    }
//    
//     public String getDeshabilitar() {
//        return deshabilitar;
//    }
//
//    public void setDeshabilitar(String deshabilitar) {
//        this.deshabilitar = deshabilitar;
//    }
//     
//    public void modificar(){
//        dlgCabecera(3);
//        try{
//            if(puestoBeanHelper.getSelectedPuesto().getPueid()>0){
//        puestoBeanHelper.setPuesto(puestoBeanHelper.getSelectedPuesto());
//        deshabilitarAceptar = "false";
//            }
//        }catch(NullPointerException e){
//           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");         
//            RequestContext.getCurrentInstance().showMessageInDialog(message);
//            deshabilitarAceptar = "true";
//        }
//    }
//    public void eliminar(){
//        dlgCabecera(2);
//    // puestoBeanHelper.getPuestoDelegate().eliminarPuesto(puestoBeanHelper.getSelectedPuesto());
//          try{
//            if(puestoBeanHelper.getSelectedPuesto().getPueid()>0){
//                puestoBeanHelper.setPuesto(puestoBeanHelper.getSelectedPuesto());
//                
//                deshabilitarAceptar = "false";
//            }
//        }catch(NullPointerException e){
//          FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");         
//            RequestContext.getCurrentInstance().showMessageInDialog(message);
//            deshabilitarAceptar = "true";
//        }
//    }
//    
//    public void nuevo(){
//        dlgCabecera(1);
//        puestoBeanHelper.setPuesto(new Puesto());
//        puestoBeanHelper.setSelectedPuesto(new Puesto());
//    }
//    
//          
//       public String onClickSubmit(){
//        String resultado="";
//        
//        if(deshabilitar.equals("false")){
//            
//            if(validacion()){
//                if(header.equals("Modificar Puesto")){
//                    FacesContext context = FacesContext.getCurrentInstance();
//                context.addMessage(null, new FacesMessage("Guardando", "Se guardo correctamente"));
//
//                puestoBeanHelper.getPuestoDelegate().agregarPuesto(puestoBeanHelper.getPuesto());
//                puestoBeanHelper.setPuesto(new Puesto());
//                puestoBeanHelper.setSelectedPuesto(new Puesto());  
//                RequestContext.getCurrentInstance().execute("confdlgMod.hide()");        
//                RequestContext.getCurrentInstance().execute("dlg.hide()");
//                        }
//                else if(header.equals("Agregar Puesto")){
//                    if(ValidarRegistro()==true){
//                    FacesContext context = FacesContext.getCurrentInstance();
//                    context.addMessage(null, new FacesMessage("Guardando", "Se guardo correctamente"));
//
//                    puestoBeanHelper.getPuestoDelegate().agregarPuesto(puestoBeanHelper.getPuesto());
//                    puestoBeanHelper.setPuesto(new Puesto());
//                    puestoBeanHelper.setSelectedPuesto(new Puesto());  
//                    }
//                     else {
//
//                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El puesto ya existe");
//                        RequestContext.getCurrentInstance().showMessageInDialog(message);
//                    }
//                }
//            }
//
//        }else{
//            RequestContext.getCurrentInstance().execute("confdlgElim.show()");
//        }
//        filtrado();
//        return resultado;
//    }
//       
//     public boolean validacion(){
//        if(puestoBeanHelper.getPuesto().getPuepuesto().isEmpty())
//        {            
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Favor de llenar todos los campos vacios");         
//            RequestContext.getCurrentInstance().showMessageInDialog(message);
//            return false;
//        }else
//            return true;
//        
//    }
//    
//    
//    
//    public String dlgCabecera(int i){
//        if(i == 1){
//            header = "Agregar Puesto";
//            deshabilitar = "false";
//        }
//        if(i == 2){
//            header = "Eliminar Puesto";
//            deshabilitar = "true";
//        }
//        if(i == 3){
//            header = "Modificar Puesto";
//            deshabilitar = "false";
//        }        
//        return header;
//    }
//    
//   
//    
//    
//    public void eliminacionConfirmada(){
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage("Eliminando", "Se elimino correctamente"));
//            
//        puestoBeanHelper.getPuestoDelegate().eliminarPuesto(puestoBeanHelper.getSelectedPuesto());
//        puestoBeanHelper.setPuesto(new Puesto());
//        puestoBeanHelper.setSelectedPuesto(new Puesto());
//        RequestContext.getCurrentInstance().execute("confdlgElim.hide()");        
//        RequestContext.getCurrentInstance().execute("dlg.hide()");
//        filtrado();
//        //RequestContext.getCurrentInstance().execute("dlg.hide()");
//        
//        
//    }
//    
//    
//    public void modificacionConfirmada(){
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage("Guardando", "Se guardo correctamente"));
//
//        puestoBeanHelper.getPuestoDelegate().agregarPuesto(puestoBeanHelper.getPuesto());
//        puestoBeanHelper.setPuesto(new Puesto());
//        puestoBeanHelper.setSelectedPuesto(new Puesto());  
//        RequestContext.getCurrentInstance().execute("confdlgMod.hide()");        
//        RequestContext.getCurrentInstance().execute("dlg.hide()");
//        filtrado();
//    }
//    
//    public boolean mostrarSeleccionPuesto(){
//        return puestoBeanHelper.getListaSeleccionada() != null && puestoBeanHelper.getListaSeleccionada().size()>1;
//    }
//    
//    public void mostrarBotones(){
//        try{
//            if(puestoBeanHelper.getSelectedPuesto().getPueid()>0){
//                deshabiliatarBoton = "false";
//                titleElim = "Eliminar";
//                titleMod = "Modificar";
//            }
//        }catch(NullPointerException e){}
//    }
//    
//    public void esconderBoton(){
//        deshabiliatarBoton = "true";
//        titleElim = "Seleccione un registro de la tabla";
//        titleMod = "Seleccione un registro de la tabla";
//    }
//    
//    public void limpiarSeleccion(){
//        puestoBeanHelper.setListaSeleccionada(null);
//        puestoBeanHelper.setSelectedPuesto(new Puesto());
//        mostrarSeleccionPuesto();
//        puestoBeanHelper.setPuesto(new Puesto());
//        puestoBeanHelper.setSelectedPuesto(new Puesto());            
//        botonesModElim();
//    }  
//    
//    public String botonesModElim(){
//        if(puestoBeanHelper.getListaFiltrada()== null || puestoBeanHelper.getListaFiltrada().size() < 1)
//            return "true";
//        else
//            return "false";
//    }
//
//    public String getBand() {
//        return band;
//    }
//
//    public void setBand(String band) {
//        this.band = band;
//    }
//    
//    public void ocultarB(){
//        band="true";
//    }
//    
//    public void mostrarB(){
//        band="false";
//    }
//    
//    public void ocultarB2(){
//        if(header.equalsIgnoreCase("Agregar Puesto")||(header.equalsIgnoreCase("Modificar Puesto"))){
//        band="true";
//        }
//    }
//    
//    public String valor(){
//        return band;
//    }
//    
//     public void onRowUnselect(UnselectEvent event){
//        puestoBeanHelper.setSelectedPuesto(new Puesto());
//        puestoBeanHelper.setSelectedPuesto((Puesto)event.getObject());
//    }
//     
//     public boolean ValidarRegistro() {//Metodo que devuelve un valor true o false dependiendo si el registro ingresado ya existe
//
//        String Puesto = puestoBeanHelper.getPuesto().getPuepuesto();
//
//        bandera = puestoBeanHelper.Validar(Puesto);
//     
//        return bandera;
//    }

    
}
