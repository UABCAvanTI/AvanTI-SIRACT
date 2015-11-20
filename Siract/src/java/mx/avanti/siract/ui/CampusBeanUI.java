package mx.avanti.siract.ui;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.avanti.siract.application.helper.CampusBeanHelper;
import mx.avanti.siract.business.entity.Campus;
import mx.avanti.siract.business.entity.Rol;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class CampusBeanUI implements Serializable{
    private CampusBeanHelper campusBeanHelper = null;
    private List<Campus> listaFiltrada;
    private String busqueda="";
    private String header;
    private String boton;
    private String deshabilitar;
    private String message;
    private String deshabilitarBoton = "true";
    private boolean Bandera;
    private String mensajeEliminar;
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    public String getMensajeEliminar() {
        return mensajeEliminar;
    }

    public void setMensajeEliminar(String mensajeEliminar) {
        this.mensajeEliminar = mensajeEliminar;
    }

    public boolean isBandera() {
        return Bandera;
    }

    public void setBandera(boolean Bandera) {
        this.Bandera = Bandera;
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

    public void setDeshabilitarBoton(String deshabilitarTitle) {
        this.deshabilitarBoton = deshabilitarTitle;
    }
    
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
    
    
    //Post constructor necesario para usar ManagedProperty
//    @PostConstruct
//    public void postConstructor() {
//        programaEducativoBeanHelper.setRolSeleccionado(loginBean.getSeleccionado());
//        programaEducativoBeanHelper.setUsuario(loginBean.getLogueado());
//        System.out.println("rol desde el BeanUI: " + loginBean.getSeleccionado());
//        System.out.println("id del usuario desde login " + loginBean.getLogueado().getUsuid());
//        
//        if(programaEducativoBeanHelper.getRolSeleccionado().equalsIgnoreCase("Administrador")){
//            renderUA="true";
//        }else{
//            renderUA="false";
//        }
//       
//    }
    
    public void filtrado(){
        List<Rol> list = null;
        list = loginBean.Obtenerrol(loginBean.getLogueado().getUsuid());
        String seleccionado=loginBean.getSeleccionado();
        System.out.println(seleccionado+"ÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑ");
        String catalogo="Administración de campus";
        loginBean.TienePermiso(list, seleccionado, catalogo);
        listaFiltrada = campusBeanHelper.filtrado("Nombre", busqueda);        
    }
    
    public void nuevo(){
        cabecera(1);
        Campus c=new Campus();
        campusBeanHelper.setCampus(c);
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
        }}
    
    public void eliminar(){ 
        cabecera(2); 
        try{
            if(campusBeanHelper.getSelectedCampus().getCamid()>0){
                campusBeanHelper.setCampus(campusBeanHelper.getSelectedCampus());
            }
        }catch(NullPointerException e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");         
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }}
    
    public String onClickSubmit(){
         setMensajeConfirmacion();
        if(deshabilitar.equals("false")){
            if(validacionVacio()){
                if(header.equals("Modificar Campus")){
                    RequestContext.getCurrentInstance().execute("conDlgElim.show();");
//                    FacesContext context = FacesContext.getCurrentInstance();
//                    context.addMessage(null, new FacesMessage("","Se guardó correctamente"));         
//        
//                    campusBeanHelper.getCampusDelegate().agregarCampus(campusBeanHelper.getCampus());
//                    campusBeanHelper.setCampus(new Campus());
//                    campusBeanHelper.setSelectedCampus(new Campus());
//                    RequestContext.getCurrentInstance().execute("dlgCapturaCampus.hide()");
                }
                else if(header.equals("Agregar Campus")){
                    if (validarRepetido() == true){
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("","Se guardó correctamente"));
                    
                    campusBeanHelper.getCampusDelegate().agregarCampus(campusBeanHelper.getCampus());
                    campusBeanHelper.setSelectedCampus(new Campus());
                    campusBeanHelper.setCampus(new Campus()); 
                 }else{

                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "El Campus ya existe");
                        RequestContext.getCurrentInstance().showMessageInDialog(message);
                    }
                }}
        }else{
            RequestContext.getCurrentInstance().execute("conDlgElim.show();");
        }   
       filtrado();
       return "";
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
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Llenar campo(s) faltantes");
             RequestContext.getCurrentInstance().showMessageInDialog(message);
             return false;
         }else{
             return true;
         }}
     
     public void eliminConfir(){
         if(header.equals("Eliminar Campus")){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("","Se eliminó correctamente"));         
        
        campusBeanHelper.getCampusDelegate().eliminarCampus(campusBeanHelper.getSelectedCampus()); 
        campusBeanHelper.setSelectedCampus(new Campus());
        campusBeanHelper.setCampus(new Campus());  
        RequestContext.getCurrentInstance().execute("conDlgElim.hide();");
        RequestContext.getCurrentInstance().execute("dlgCapturaCampus.hide();");
         }else{
             if(header.equals("Modificar Campus")){
                 FacesContext context = FacesContext.getCurrentInstance();
                 context.addMessage(null, new FacesMessage("","Se guardó correctamente")); 
                 campusBeanHelper.getCampusDelegate().agregarCampus(campusBeanHelper.getCampus());
                 RequestContext.getCurrentInstance().execute("conDlgElim.hide();");
             }
         }
        filtrado();
     }
     
     public void habilitarBotons(){
         deshabilitarBoton = "false";
     }
     
      public void deshabilitarBotons(){
          deshabilitarBoton = "true";
          limpiar();
      }
      
      public boolean validarRepetido(){
         String campus = campusBeanHelper.getCampus().getCamnombre();
        Bandera = campusBeanHelper.Validar(campus);
        return Bandera;
      }
      
      public void limpiar(){
          campusBeanHelper.setSelectedCampus(new Campus());
      }
      
       public void setMensajeConfirmacion(){
          if (deshabilitar.equals("true")){
             mensajeEliminar="¿Estás seguro de eliminar el registro?";
          }else{
              if(header.equals("Modificar Campus")){
                      mensajeEliminar="¿Estás seguro de modificar el registro?";
              }
          }
          RequestContext.getCurrentInstance().update("confirmid");
      }
}