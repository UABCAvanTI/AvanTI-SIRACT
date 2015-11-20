package mx.avanti.siract.ui;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.avanti.siract.application.helper.UnidadAcademicaBeanHelper;
import mx.avanti.siract.business.entity.Unidadacademica;
import mx.avanti.siract.business.entity.Campus;
import mx.avanti.siract.business.entity.Rol;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class UnidadAcademicaBeanUI implements Serializable{
    private UnidadAcademicaBeanHelper unidadAcademicaBeanHelper = null;
    private List<Unidadacademica> listaFiltrada;
    private String busqueda="";
    private String header;
    private String boton;
    private String deshabilitar;
    private String message;
    private String deshabilitarBoton = "true";
    private String mensajeEliminar;
    private boolean Bandera;
    private String renderCancelar;
    String uatipo="";
    int x=0;

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    
    public String getRenderCancelar() {
        return renderCancelar;
    }

    public void setRenderCancelar(String renderCancelar) {
        this.renderCancelar = renderCancelar;
    }

    public String getMensajeEliminar() {
        return mensajeEliminar;
    }

    public void setMensajeEliminar(String mensajeEliminar) {
        this.mensajeEliminar = mensajeEliminar;
    }

    public String getUatipo() {
        return uatipo;
    }

    public void setUatipo(String uatipo) {
        this.uatipo = uatipo;
    }
    
    public boolean isBandera() {
        return Bandera;
    }

    public void setBandera(boolean Bandera) {
        this.Bandera = Bandera;
    }
    
    public String getDeshabilitarBoton() {
        return deshabilitarBoton;
    }

    public void setDeshabilitarBoton(String deshabilitarBoton) {
        this.deshabilitarBoton = deshabilitarBoton;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getHeader() {
        return header;
    }

    public void setHeader(final String header) {
        this.header = header;
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
    
    public UnidadAcademicaBeanUI(){
        init();
    }
    
    private void init(){
        unidadAcademicaBeanHelper = new UnidadAcademicaBeanHelper();
        x=0;
    }
    
    public UnidadAcademicaBeanHelper getUnidadAcademicaBeanHelper() {
        return unidadAcademicaBeanHelper;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public List<Unidadacademica> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Unidadacademica> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public void filtrado() {
        List<Rol> list = null;
        list = loginBean.Obtenerrol(loginBean.getLogueado().getUsuid());
        String seleccionado=loginBean.getSeleccionado();
        System.out.println(seleccionado+"ÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑ");
        String catalogo="Administración de unidad académica";
        loginBean.TienePermiso(list, seleccionado, catalogo);
        listaFiltrada = unidadAcademicaBeanHelper.filtrado(busqueda);
        
    }
    
    public void filtrado2() {
        listaFiltrada = unidadAcademicaBeanHelper.filtrado2(unidadAcademicaBeanHelper.getCampus().getCamid());
    }
    
    public void nuevo(){
        limpiar();
        cabecera(1);
        unidadAcademicaBeanHelper.setUnidadacademica(new Unidadacademica());
        unidadAcademicaBeanHelper.setCampus(new Campus());
    }
    
    public void modificar(){
          cabecera(3);
        try{
            if(unidadAcademicaBeanHelper.getListaSeleccionUA().size()==1){
                unidadAcademicaBeanHelper.setUnidadacademica(unidadAcademicaBeanHelper.getListaSeleccionUA().get(0));
                unidadAcademicaBeanHelper.setCampus(unidadAcademicaBeanHelper.getListaSeleccionUA().get(0).getCampus());
            }else {
                if (unidadAcademicaBeanHelper.getListaSeleccionUA().size() < 1) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                } else {
                    unidadAcademicaBeanHelper.setUnidadacademica(new Unidadacademica());
                    unidadAcademicaBeanHelper.setCampus(new Campus());
                    unidadAcademicaBeanHelper.setUnidadacademica(unidadAcademicaBeanHelper.getListaSeleccionUA().get(0));
                    unidadAcademicaBeanHelper.setCampus(unidadAcademicaBeanHelper.getListaSeleccionUA().get(0).getCampus());
                }
            }
        }catch(NullPointerException e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");         
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }}
    
    public void eliminar(){
         cabecera(2);
        try{
             if(unidadAcademicaBeanHelper.getListaSeleccionUA().size()==1){
                unidadAcademicaBeanHelper.setUnidadacademica(unidadAcademicaBeanHelper.getListaSeleccionUA().get(0));
                unidadAcademicaBeanHelper.setCampus(unidadAcademicaBeanHelper.getListaSeleccionUA().get(0).getCampus());
            }else {
                if (unidadAcademicaBeanHelper.getListaSeleccionUA().size() < 1) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                } else {
                    unidadAcademicaBeanHelper.setUnidadacademica(unidadAcademicaBeanHelper.getListaSeleccionUA().get(0));
                unidadAcademicaBeanHelper.setCampus(unidadAcademicaBeanHelper.getListaSeleccionUA().get(0).getCampus());
                }             
             }
        }catch(NullPointerException e){
            unidadAcademicaBeanHelper.setUnidadacademica(new Unidadacademica());
            unidadAcademicaBeanHelper.setCampus(new Campus());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");         
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
    
    public String onClickSubmit(){
        setMensajeConfirmacion();
         if(deshabilitar.equals("false")){
            if(validacionVacio()){
                if(header.equals("Modificar Unidad Académica")){
                    RequestContext.getCurrentInstance().execute("conDlgElim.show();");
                }
                else if(header.equals("Agregar Unidad Académica")){
                    if (validarRepetido() == true){
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("","Se guardó correctamente"));
                    
                    unidadAcademicaBeanHelper.getUnidadAcademicaDelegate().agregarUnidadAcademica(unidadAcademicaBeanHelper.getUnidadacademica());
                    unidadAcademicaBeanHelper.setSelecUnidadAcademica(new Unidadacademica());
                    unidadAcademicaBeanHelper.setUnidadacademica(new Unidadacademica());
                    RequestContext.getCurrentInstance().execute("dlg.show();");                    
                 }else{
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", "La unidad Académica ya existe");
                        RequestContext.getCurrentInstance().showMessageInDialog(message);
                    }}
            }
        }else{
            RequestContext.getCurrentInstance().update("uacap");            
//            RequestContext.getCurrentInstance().update("conDlgElim"); 
            RequestContext.getCurrentInstance().execute("conDlgElim.show();");
        }   
       filtrado();
       return "";
    }
    
    public String cabecera(int i){
        if(i==1){
            setHeader("Agregar Unidad Académica");
            deshabilitar="false";
        }else{
            if(i==2){
            setHeader("Eliminar Unidad Académica");
            deshabilitar="true";
        }else{
            if(i==3){
            header="Modificar Unidad Académica";
            deshabilitar="false";
        }}}
        return header;
    }
     
    public boolean validacionVacio(){
        if(unidadAcademicaBeanHelper.getCampus().getCamid()==0
                || unidadAcademicaBeanHelper.getUnidadacademica().getUacclave()==0
                || unidadAcademicaBeanHelper.getUnidadacademica().getUacnombre().isEmpty()){
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Llenar campo(s) faltantes");
             RequestContext.getCurrentInstance().showMessageInDialog(message);
             return false;
         }else{
             return true;
         }}
    
     public void eliminConfir(){
         if(header.equals("Eliminar Unidad Académica")){
             if(renderCancelar.equals("true")){  
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("","Se eliminó correctamente"));   
        unidadAcademicaBeanHelper.eliminarDeLista(unidadAcademicaBeanHelper.getUnidadacademica().getUacid());
        unidadAcademicaBeanHelper.getUnidadAcademicaDelegate().eliminarUnidadAcademica(unidadAcademicaBeanHelper.getUnidadacademica()); 
        unidadAcademicaBeanHelper.setSelecUnidadAcademica(new Unidadacademica());
        unidadAcademicaBeanHelper.setUnidadacademica(new Unidadacademica()); 
        unidadAcademicaBeanHelper.setCampus(new Campus());
        RequestContext.getCurrentInstance().execute("conDlgElim.hide();");
               
        if(unidadAcademicaBeanHelper.getListaSeleccionUA().size()>=1){
            unidadAcademicaBeanHelper.setUnidadacademica(unidadAcademicaBeanHelper.getListaSeleccionUA().get(0));
            unidadAcademicaBeanHelper.setCampus(unidadAcademicaBeanHelper.getListaSeleccionUA().get(0).getCampus());
             RequestContext.getCurrentInstance().execute("dlg.show();");
            
        }
            
         }else{                
                RequestContext.getCurrentInstance().execute("conDlgElim.hide();");
             RequestContext.getCurrentInstance().execute("dlg.hide();");
                limpiar();
                mostrarSeleccionUA();
                botones();
            }
         }else{
             if(header.equals("Modificar Unidad Académica")){
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("","Se guardó correctamente"));         
        
                    unidadAcademicaBeanHelper.getUnidadAcademicaDelegate().agregarUnidadAcademica(unidadAcademicaBeanHelper.getUnidadacademica());
//                    unidadAcademicaBeanHelper.setUnidadacademica(new Unidadacademica());
//                    unidadAcademicaBeanHelper.setSelecUnidadAcademica(new Unidadacademica());
                    RequestContext.getCurrentInstance().execute("conDlgElim.hide();");
                    RequestContext.getCurrentInstance().execute("dlg.show();");
             }
         }
        filtrado();
     }
     
     public void habilitarBotons(){
         deshabilitarBoton = "false";
     }
     
      public void deshabilitarBotons(){
          if(unidadAcademicaBeanHelper.getListaSeleccionUA().size()<1){
              deshabilitarBoton = "true";
              limpiar();
          }
      }
      
      public boolean botones(){
          if (unidadAcademicaBeanHelper.getListaSeleccionUA() == null || unidadAcademicaBeanHelper.getListaSeleccionUA().size() < 1) {
            return true;
        }
        return false;
      }
      
      public boolean validarRepetido(){
        String unidadacademica = Integer.toString(unidadAcademicaBeanHelper.getUnidadacademica().getUacclave());
        Bandera = unidadAcademicaBeanHelper.Validar(unidadacademica);
        return Bandera;
      }
      
      public void limpiar(){
          unidadAcademicaBeanHelper.setUnidadacademica(new Unidadacademica()); 
          unidadAcademicaBeanHelper.setCampus(new Campus());
          unidadAcademicaBeanHelper.setSelecUnidadAcademica(new Unidadacademica());
          unidadAcademicaBeanHelper.setListaSeleccionUA(null);
          mostrarSeleccionUA();
          botones();
//          filtrado();
//          mostrarSeleccionUA();
//          botones();
      }
      
      public boolean mostrarSeleccionUA() {
        if (unidadAcademicaBeanHelper.getListaSeleccionUA() != null && unidadAcademicaBeanHelper.getListaSeleccionUA().size() > 1) {
            return true;
        } else {
            return false;
        }
    }
      
      public void setMensajeConfirmacion(){
          if (deshabilitar.equals("true")){
              if(unidadAcademicaBeanHelper.getUnidadAcademicaDelegate().getUAasignado(unidadAcademicaBeanHelper.getUnidadacademica().getUacid()).size()>0){
             mensajeEliminar="La Unidad Académica está asignada a un programa educativo";
             renderCancelar="false";
         }else{
             mensajeEliminar="¿Estás seguro de eliminar el registro?";
             renderCancelar="true";
         }
          }else{
              if(header.equals("Modificar Unidad Académica")){
                  renderCancelar="true";
                  if(unidadAcademicaBeanHelper.getUnidadAcademicaDelegate().getUAasignado(unidadAcademicaBeanHelper.getUnidadacademica().getUacid()).size()>0){
                      mensajeEliminar="La Unidad Académica está asignada a un programa educativo";
                  }else{
                      mensajeEliminar="¿Está seguro de guardar los cambios?";
                  }
              }
          }
          RequestContext.getCurrentInstance().update("confirmid");
      }
}
