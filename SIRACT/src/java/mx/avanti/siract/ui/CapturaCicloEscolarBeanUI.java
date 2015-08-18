/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.ui;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.avanti.siract.application.helper.CapturaCicloEscolarBeanHelper;
import mx.avanti.siract.business.entity.Cicloescolar;
import org.primefaces.context.RequestContext;
import org.primefaces.event.UnselectEvent;


/**
 *
 * @author manuel
 */
@ManagedBean
@ViewScoped
public class CapturaCicloEscolarBeanUI implements Serializable {

//    private CapturaCicloEscolarBeanHelper escolarBeanHelper = null;
//    private List<Cicloescolar> ListaFiltrada;
//    private String buscar ;
//    private String header;
//    private String deshabilitar;
//    private boolean Bandera;
//    private boolean BanderaFormato;
//    private String cabecera;
//    private String textoBoton;
//    private String deshabilitarTitle = "true";
//    private String mensajeMod = "Seleccione un registro de la tabla";
//    private String mensajeBor = "Seleccione un registro de la tabla";
//
//
//    public String getMensajeMod() {
//        return mensajeMod;
//    }
//
//    public void setMensajeMod(String mensajeMod) {
//        this.mensajeMod = mensajeMod;
//    }
//
//    public String getMensajeBor() {
//        return mensajeBor;
//    }
//
//    public void setMensajeBor(String mensajeBor) {
//        this.mensajeBor = mensajeBor;
//    }
//
//    public String getDeshabilitarTitle() {
//        return deshabilitarTitle;
//    }
//
//    public void setDeshabilitarTitle(String deshabilitarTitle) {
//        this.deshabilitarTitle = deshabilitarTitle;
//    }
//
//    public boolean isBandera() {
//        return Bandera;
//    }
//
//    public void setBandera(boolean Bandera) {
//        this.Bandera = Bandera;
//    }
//
//    public boolean isBanderaFormato() {
//        return BanderaFormato;
//    }
//
//    public void setBanderaFormato(boolean BanderaFormato) {
//        this.BanderaFormato = BanderaFormato;
//    }
//
//    public String getHeader() {
//        return header;
//    }
//
//    public String getCabecera() {
//        return cabecera;
//    }
//
//    public void setCabecera(String cabecera) {
//        this.cabecera = cabecera;
//    }
//
//    public String getTextoBoton() {
//        return textoBoton;
//    }
//
//    public void setTextoBoton(String textoBoton) {
//        this.textoBoton = textoBoton;
//    }
//
//    public void setHeader(String header) {
//        this.header = header;
//    }
//
//    public String getDeshabilitar() {
//        return deshabilitar;
//    }
//
//    public void setDeshabilitar(String deshabilitar) {
//        this.deshabilitar = deshabilitar;
//    }
//
//    public List<Cicloescolar> getListaFiltrada() {
//        return ListaFiltrada;
//    }
//
//    public void setListaFiltrada(List<Cicloescolar> ListaFiltrada) {
//        this.ListaFiltrada = ListaFiltrada;
//    }
//
//    public String getBuscar() {
//        return buscar;
//    }
//
//    public void setBuscar(String buscar) {
//        this.buscar = buscar;
//    }
//
//    public CapturaCicloEscolarBeanUI() {
//        init();
//    }
//
//    private void init() {
//        escolarBeanHelper = new CapturaCicloEscolarBeanHelper();
//        buscar="";
//    }
//
//    public CapturaCicloEscolarBeanHelper getEscolarBeanHelper() {
//        return escolarBeanHelper;
//    }
//
//    public void setEscolarBeanHelper(CapturaCicloEscolarBeanHelper escolarBeanHelper) {
//        this.escolarBeanHelper = escolarBeanHelper;
//    }
//
//    public void modificar() {
//        header(3);//valor que se le enviara al metodo header para determinar el tipo de mensaje y la modificacion de la propiedad diseable
//        try {
//            if (escolarBeanHelper.getSelCicloEscolar().getCesid() == 1) {
//                //escolarBeanHelper.setProfesor(profesorBeanHelper.getSelecProfesor());
//                escolarBeanHelper.setCicloescolar(escolarBeanHelper.getSelCicloEscolar());
//
//            }
//        } catch (NullPointerException e) {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
//            RequestContext.getCurrentInstance().showMessageInDialog(message);
//        }
//    }
//
//    public void eliminar() {
//        header(2);//valor que se le enviara al metodo header para determinar el tipo de mensaje y la modificacion de la propiedad diseable
////        escolarBeanHelper.getCicloEscolarDelegate().eliminarCicloEscolar(escolarBeanHelper.getSelCicloEscolar());
//        try {
//            if (escolarBeanHelper.getSelCicloEscolar().getCesid() > 0) {
//                escolarBeanHelper.setCicloescolar(escolarBeanHelper.getSelCicloEscolar());
//
//            }
//        } catch (NullPointerException e) {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
//            RequestContext.getCurrentInstance().showMessageInDialog(message);
//        }
//    }
//
//    public void nuevo() {
//        limpiarSeleccion();
//        header(1);//valor que se le enviara al metodo header para determinar el tipo de mensaje y la modificacion de la propiedad diseable
//        escolarBeanHelper.setCicloescolar(new Cicloescolar());
//        escolarBeanHelper.setSelCicloEscolar(new Cicloescolar());
//
//    }
//
////    public String onClickSubmit(){
////        String resultado = "";
////       escolarBeanHelper.getCicloEscolarDelegate().agregarCicloEscolar(escolarBeanHelper.getCicloescolar());
////        return resultado;
////    }
////    public boolean validacion(){
////        if(escolarBeanHelper.getCicloescolar().ge){
////        }
////    }
//    public String onClickSubmit() {
//        if (deshabilitar.equals("true")) {
//            RequestContext.getCurrentInstance().execute("confirmacion.show();");
//        } else {
//            if (escolarBeanHelper.getCicloescolar().getCescicloEscolar().isEmpty()) {
//                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Llenar campo Ciclo Escolar");
//                RequestContext.getCurrentInstance().showMessageInDialog(message);
//            } else {
////                if (formatoCiclo() == true) {//Compara si el valor es verdadero en el metodo de formatoCiclo si es falso el registro no tiene el formato correcto
//                
//
//                    if (ValidarRegistro() == true) {
//                        escolarBeanHelper.getCicloEscolarDelegate().agregarCicloEscolar(escolarBeanHelper.getCicloescolar());
//                        escolarBeanHelper.setSelCicloEscolar(new Cicloescolar());
//                        escolarBeanHelper.setCicloescolar(new Cicloescolar());
//
////                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Datos guardados correctamente");
////                        RequestContext.getCurrentInstance().showMessageInDialog(message);
//                        FacesContext context = FacesContext.getCurrentInstance();
//                        context.addMessage(null, new FacesMessage("Guardando", "Se guardó correctamente"));
//                        RequestContext.getCurrentInstance().execute("confirmacion.hide();");
//
//                             //  Collections.sort(ListaCiclo,Collections.reverseOrder());
//
////                    mensajeMod= "Seleccione un registro de la tabla"; 
////                     mensajeBor= "Seleccione un registro de la tabla"; 
////                    deshabilitarTitle="true";
//
//                    } else {
//
//                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El Ciclo Escolar ya existe");
//                        RequestContext.getCurrentInstance().showMessageInDialog(message);
//                    }
////                } else {
////                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Formato no valido");
////                    RequestContext.getCurrentInstance().showMessageInDialog(message);
////                }
//            }
//        }
//
//        filtrado();
//        MensajeTitle();
//        return "";
//    }
//
//    public void filtrado() {//Metodo que devolvera la lista con mayor similitud al valor que contenga la variable buscar
//        ListaFiltrada = escolarBeanHelper.filtrado("Nombre", buscar);
//    }
//
//    public String ocultar() {
//        String mostrar = "dlg.hide();";
//        try {
//            //if(profesorBeanHelper.getSelecProfesor().getPronumeroEmpleado()>0){
//            if (escolarBeanHelper.getSelCicloEscolar().getCesid() > 0) {
//                mostrar = "dlg.show();";
//            } else {
//                mostrar = "dlg.hide();";
//            }
//        } catch (NullPointerException e) {
//
//        }
//        return mostrar;
//    }
////Metodo para validar el tipo de mensaje que se mostrara en la cabecera
//
//    public String header(int i) {
//        if (i == 1) {
//            cabecera = "Agregar Ciclo escolar";
//            textoBoton = "Aceptar";
//            deshabilitar = "false";
//        }
//        if (i == 2) {
//            cabecera = "Eliminar Ciclo escolar";
//            textoBoton = "Aceptar";
//            deshabilitar = "true";
//        }
//        if (i == 3) {
//            cabecera = "Modificar Ciclo escolar";
//            textoBoton = "Aceptar";
//            deshabilitar = "false";
//        }
//        return cabecera;
//    }
//
//    public void ConfirmarEliminacion() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage("Eliminando", "Se eliminó correctamente"));
//        
////        escolarBeanHelper.eliminarDeLista(escolarBeanHelper.getCicloescolar().getCesid());
//        escolarBeanHelper.getCicloEscolarDelegate().eliminarCicloEscolar(escolarBeanHelper.getCicloescolar());
//        escolarBeanHelper.setSelCicloEscolar(new Cicloescolar());
//        escolarBeanHelper.setCicloescolar(new Cicloescolar());
//        RequestContext.getCurrentInstance().execute("confirmacion.hide();");
//        if(escolarBeanHelper.getListaCicloSelect().size()==1){
//            escolarBeanHelper.setCicloescolar(escolarBeanHelper.getListaCicloSelect().get(0));
//         
//        }
////        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro eliminado correctamente");
////        RequestContext.getCurrentInstance().showMessageInDialog(message);
//        filtrado();
//    }
//
//    public boolean ValidarRegistro() {//Metodo que devuelve un valor true o false dependiendo si el registro ingresado ya existe
//
//        String CicloEscolar = escolarBeanHelper.getCicloescolar().getCescicloEscolar();
//
//        Bandera = escolarBeanHelper.Validar(CicloEscolar);
//
//        return Bandera;
//    }
//
//    //metodo para activar los botones de eliminar y modificar despues de seleccionar algun registro
//    public void MensajeTitle() {//detemrina el mensaje que se mostrara en la propiedad title en los botones de eliminar y modificar
//        try {
//            if (escolarBeanHelper.getSelCicloEscolar().getCesid() > 0) {
//
//                deshabilitarTitle = "false";
//                mensajeMod = "Modificar";
//                mensajeBor = "Borrar";
//            }
//
//        } catch (NullPointerException e) {
//        }
//    }
//
//    //metodo para desactivar los botones despues de ejecutar alguna modificacion o eliminacion
//    public void EsconderBotones() {//detemrina el mensaje que se mostrara en la propiedad title en los botones de eliminar y modificar
//        deshabilitarTitle = "true";
//        mensajeMod = "Seleccione un registro de la tabla";
//        mensajeBor = "Seleccione un registro de la tabla";
//    }
//
//    public boolean mostrarSeleccionCicloEscolar(){
//         return escolarBeanHelper.getListaCicloSelect()!= null && escolarBeanHelper.getListaCicloSelect().size() >1;
//    }
//      public void limpiarSeleccion(){
//        escolarBeanHelper.setListaCicloSelect(null);
//
//        escolarBeanHelper.setCicloescolar(new Cicloescolar());
//        mostrarSeleccionCicloEscolar();
//    }
//       public String tooltip(int i){
//        if(escolarBeanHelper.getListaCicloSelect()==null || escolarBeanHelper.getListaCicloSelect().size()<1){
//            return "Seleccione un registro de la tabla";            
//        }else{
//            if(i==2){
//                return "Eliminar";
//            }
//            if(i==3){
//                return "Modificar";
//            }
//        }
//        return "nada";
//    }
//           public boolean deshabilitarMenu(){
//        if(escolarBeanHelper.getListaCicloSelect()==null || escolarBeanHelper.getListaCicloSelect().size()<1){
//            return true;            
//        }
//        return false;
//    }
////    public boolean formatoCiclo() {//Devuelve un va
////        return BanderaFormato = escolarBeanHelper.formatoCiclo();
////    }
    private CapturaCicloEscolarBeanHelper CicloEscolarBeanHelper = null;
    
    /* variable para controlar los botones de eliminar y modificar */
    private String deshabilitarBoton="true";
    private String titleMod = "Seleccione un registro de la tabla";    
    private String titleElim = "Seleccione un registro de la tabla";
    
    /*variables para controlar los dialog */
    private String header;
    private String deshabilitar;
    private String deshabilitarAceptar;

    public boolean isValciclo() {
        return valciclo;
    }

    public void setValciclo(boolean valciclo) {
        this.valciclo = valciclo;
    }
    
    private boolean valciclo;

    public boolean isSelecvisible() {
        return selecvisible;
    }

    public void setSelecvisible(boolean selecvisible) {
        this.selecvisible = selecvisible;
    }
    private boolean selecvisible;
    
    private String mensajeConfirm;
    
    /* variables para la busqueda por filtro y la lista de resultados */
    private String busqueda="";    
    private List<Cicloescolar> listaFiltrada;  
    
    
    /* variable para obtener el mensaje de los datos repetidos */
    private String mensajeRep;
       public CapturaCicloEscolarBeanUI(){
        init();
    }

    public CapturaCicloEscolarBeanHelper getCicloEscolarBeanHelper() {
        return CicloEscolarBeanHelper;
    }

    public void setCicloEscolarBeanHelper(CapturaCicloEscolarBeanHelper CicloEscolarBeanHelper) {
        this.CicloEscolarBeanHelper = CicloEscolarBeanHelper;
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

    public String getMensajeConfirm() {
        return mensajeConfirm;
    }

    public void setMensajeConfirm(String mensajeConfirm) {
        this.mensajeConfirm = mensajeConfirm;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public List<Cicloescolar> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Cicloescolar> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public String getMensajeRep() {
        return mensajeRep;
    }

    public void setMensajeRep(String mensajeRep) {
        this.mensajeRep = mensajeRep;
    }
    private void init(){
        CicloEscolarBeanHelper = new CapturaCicloEscolarBeanHelper();
    }
    public void setMensajeConfirm(){
        if(deshabilitar.equals("true")){
            mensajeConfirm = "¿Está seguro de eliminar el registro?";
        }else{
            mensajeConfirm = "¿Está seguro de guardar los cambios?";
        }
        RequestContext.getCurrentInstance().update("confirmdlg");
    }
    public String dlgCabecera(int i){
        if(i == 1){
            header = "Agregar Ciclo escolar";
            deshabilitar = "false";
        }
        if(i == 2){
            header = "Eliminar Ciclo escolar";
            deshabilitar = "true";
        }
        if(i == 3){
            header = "Modificar Ciclo escolar";
            deshabilitar = "false";
        }        
        return header;
    }
    public void nuevo(){
        limpiarSeleccion();
        dlgCabecera(1);
        CicloEscolarBeanHelper.setCicloescolar(new Cicloescolar());
        CicloEscolarBeanHelper.setSelecCiclo(new Cicloescolar());
//        deshabilitarAceptar = "false";
    }
    public void limpiarSeleccion(){
        CicloEscolarBeanHelper.setListaCicloSeleccionada(null);
        CicloEscolarBeanHelper.setCicloescolar(new Cicloescolar());
        CicloEscolarBeanHelper.setSelecCiclo(new Cicloescolar());           
        mostrarSeleccionCiclo();        
        botonesModElim();
    }  
    public String botonesModElim(){
        if(CicloEscolarBeanHelper.getListaCicloSeleccionada()== null || CicloEscolarBeanHelper.getListaCicloSeleccionada().size() < 1)
            return "true";
        else
            return "false";
    }
    public boolean mostrarSeleccionCiclo() {
        return CicloEscolarBeanHelper.getListaCicloSeleccionada()!= null && CicloEscolarBeanHelper.getListaCicloSeleccionada().size() > 1;
    } 
    public void modificar(){
        
        dlgCabecera(3);
        try{
            if(CicloEscolarBeanHelper.getListaCicloSeleccionada().size() == 1){
                CicloEscolarBeanHelper.setCicloescolar(CicloEscolarBeanHelper.getListaCicloSeleccionada().get(0));   

//                deshabilitarAceptar = "false";
            } else{
                if(CicloEscolarBeanHelper.getListaCicloSeleccionada().size() < 1){
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");         
                    RequestContext.getCurrentInstance().showMessageInDialog(message);                
                }else{
//                    CicloEscolarBeanHelper.setCicloescolar(new Cicloescolar());
                    CicloEscolarBeanHelper.setCicloescolar(CicloEscolarBeanHelper.getListaCicloSeleccionada().get(0));
                    
                }
            }
        }catch(NullPointerException e){
            //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");         
            //RequestContext.getCurrentInstance().showMessageInDialog(message);
//            deshabilitarAceptar = "true";
        }     
    }
        public void eliminar(){
        dlgCabecera(2);
        try{
            if(CicloEscolarBeanHelper.getListaCicloSeleccionada().size() == 1){
                CicloEscolarBeanHelper.setCicloescolar(CicloEscolarBeanHelper.getListaCicloSeleccionada().get(0));   
                selecvisible=true;
//                deshabilitarAceptar = "false";
            } else{
                if(CicloEscolarBeanHelper.getListaCicloSeleccionada().size() < 1){
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");         
                    RequestContext.getCurrentInstance().showMessageInDialog(message);                
                }else{
                    CicloEscolarBeanHelper.setCicloescolar(CicloEscolarBeanHelper.getListaCicloSeleccionada().get(0));

                }
            }
        }catch(NullPointerException e){
            //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");         
            //RequestContext.getCurrentInstance().showMessageInDialog(message);
//            deshabilitarAceptar = "true";
        }
    }
    public boolean validacion(){
        if(CicloEscolarBeanHelper.getCicloescolar().getCescicloEscolar().isEmpty()){
            return true;
        }else{
            return false;
        }

        
    }
    public void eliminacionConfirmada(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Eliminando", "Se eliminó correctamente"));            
        
        CicloEscolarBeanHelper.getCicloescolarDelegate().eliminarCicloEscolar(CicloEscolarBeanHelper.getSelecCiclo());
        CicloEscolarBeanHelper.setCicloescolar(new Cicloescolar());
        CicloEscolarBeanHelper.setSelecCiclo(new Cicloescolar());
        RequestContext.getCurrentInstance().execute("confdlgElim.hide()");
        
        if(CicloEscolarBeanHelper.getListaCicloSeleccionada().size() == 1){
            CicloEscolarBeanHelper.setCicloescolar(CicloEscolarBeanHelper.getListaCicloSeleccionada().get(0));
        }
        filtrado();                       
    }
    public void filtrado(){
        listaFiltrada = CicloEscolarBeanHelper.filtrado("Nombre", busqueda);
    }
    public boolean validacionCiclo(){
        
        return CicloEscolarBeanHelper.validacionciclo();
    }
    public void modificacionConfirmada(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Guardando", "Se guardó correctamente"));
        
        CicloEscolarBeanHelper.getCicloescolarDelegate().agregarCicloEscolar(CicloEscolarBeanHelper.getCicloescolar());
        CicloEscolarBeanHelper.setCicloescolar(new Cicloescolar());
        CicloEscolarBeanHelper.setSelecCiclo(new Cicloescolar());  
        RequestContext.getCurrentInstance().execute("confdlgMod.hide()");        
//        RequestContext.getCurrentInstance().execute("dlg.hide()");
        filtrado();
    }
    public void confirmacionAceptada(){
        //deshabilitarAceptar = "false";
        if(deshabilitar.equals("true")){
          
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Eliminando", "Se eliminó correctamente"));            
            
            CicloEscolarBeanHelper.eliminarDeLista(CicloEscolarBeanHelper.getCicloescolar().getCesid());
            CicloEscolarBeanHelper.getCicloescolarDelegate().eliminarCicloEscolar(CicloEscolarBeanHelper.getCicloescolar());
            CicloEscolarBeanHelper.setSelecCiclo(new Cicloescolar());
            CicloEscolarBeanHelper.setCicloescolar(new Cicloescolar());
            RequestContext.getCurrentInstance().execute("confirmdlg.hide();");            
            
            if(CicloEscolarBeanHelper.getListaCicloSeleccionada().size()>=1){
                CicloEscolarBeanHelper.setCicloescolar(CicloEscolarBeanHelper.getListaCicloSeleccionada().get(0));
                 RequestContext.getCurrentInstance().execute("dlg.show();");
                
                //deshabilitarAceptar = "true";
            }
        }
        filtrado();
    }
    public String onClickSubmit() {
        String resultado = "";
        setMensajeConfirm();

        if (deshabilitar.equals("true")) {
            //System.out.println("point here");
            //boolean res = validacion();                    }else if(header.equals("Eliminar Grupo")){
            RequestContext.getCurrentInstance().execute("confirmdlg.show()");
        } else {
            if (validacion()) {
                //System.out.println("point here 2");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Favor de llenar todos los campos vacios");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            } else {

                mensajeRep = CicloEscolarBeanHelper.validarRepetidos();

                if (mensajeRep.isEmpty()) {
                    mensajeRep = "vacio";

                }
                if (mensajeRep.equals("vacio")) {
                    if (validacionCiclo()==true) {

                        if (header.equals("Agregar Ciclo escolar")) {

                            FacesContext context = FacesContext.getCurrentInstance();
                            context.addMessage(null, new FacesMessage("Guardado", "Se guardó correctamente"));

                            CicloEscolarBeanHelper.getCicloescolarDelegate().agregarCicloEscolar(CicloEscolarBeanHelper.getCicloescolar());
                            CicloEscolarBeanHelper.setCicloescolar(new Cicloescolar());
                            CicloEscolarBeanHelper.setSelecCiclo(new Cicloescolar());
                        } else if (header.equals("Modificar Ciclo escolar")) {
                            FacesContext context = FacesContext.getCurrentInstance();
                            context.addMessage(null, new FacesMessage("Modificando", "Se guardó correctamente"));

                            CicloEscolarBeanHelper.getCicloescolarDelegate().agregarCicloEscolar(CicloEscolarBeanHelper.getCicloescolar());
                            CicloEscolarBeanHelper.setSelecCiclo(new Cicloescolar());
                            CicloEscolarBeanHelper.setCicloescolar(CicloEscolarBeanHelper.getListaCicloSeleccionada().get(0));

                        }

                    }
                    else{
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Ciclo escolar no valido");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
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
    public void mostrarBotones(){
        try{
            if(CicloEscolarBeanHelper.getSelecCiclo().getCesid()> 0){
                deshabilitarBoton = "false";
                titleElim = "Eliminar";
                titleMod = "Modificar";
            }
        }catch(NullPointerException e){}
    }
   public void esconderBotones(){
        deshabilitarBoton = "true";
        titleElim = "Seleccione un registro de la tabla";
        titleMod = "Seleccione un registro de la tabla";
    }
    
    public String botonesSelect(){
        if(CicloEscolarBeanHelper.getSelecCiclo().getCesid()>0){
            //habilitarBoton = "false";
            return "false";
        }
        else{
            //habilitarBoton = "true";
            return "true";
        }
    }
    public boolean mostrarSeleccionGrupo() {
        return CicloEscolarBeanHelper.getListaCicloSeleccionada()!= null && CicloEscolarBeanHelper.getListaCicloSeleccionada().size() > 1;
    }    
    public String toolTip(int i){
        if(CicloEscolarBeanHelper.getListaCicloSeleccionada()== null || CicloEscolarBeanHelper.getListaCicloSeleccionada().size() < 1)
            return "Seleccione un registro de la tabla";
        else{
            if(i == 1)
                return "Eliminar";
            else if(i == 2)
                return "Modificar";
        }
        return "";
    } 
     
    public void onRowUnselect(UnselectEvent event){
        CicloEscolarBeanHelper.setSelecCiclo(new Cicloescolar());
        CicloEscolarBeanHelper.setSelecCiclo((Cicloescolar)event.getObject());
    }
    

    

}
