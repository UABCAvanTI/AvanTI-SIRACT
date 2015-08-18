/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.ui;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.avanti.siract.application.helper.PlanEstudioBeanHelper;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Programaeducativo;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Alan-PC
 */
@ManagedBean
@ViewScoped
public class PlanEstudioBeanUI implements Serializable{
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    
    private PlanEstudioBeanHelper planEstudioBeanHelper = null;
    private List<Planestudio> listaFiltrada;
    private String busqueda=""; 
    private String cabecera;
    private String textoBoton;
    private String MensajeVal = "";
    private String mensajeConfirmacion="";
    private int x=0;

    public String getMensajeConfirmacion() {
        return mensajeConfirmacion;
    }

    public void setMensajeConfirmacion(String mensajeConfirmacion) {
        this.mensajeConfirmacion = mensajeConfirmacion;
    }

    public String getMensajeVal() {
        return MensajeVal;
    }

    public void setMensajeVal(String MensajeVal) {
        this.MensajeVal = MensajeVal;
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
    private String deshabilitar = "";

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
    
    public PlanEstudioBeanUI(){
        init();
    }
    
    private void init(){
        planEstudioBeanHelper = new PlanEstudioBeanHelper();
    }

    public List<Planestudio> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Planestudio> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public PlanEstudioBeanHelper getPlanEstudioBeanHelper() {
        return planEstudioBeanHelper;
    }

    public void setPlanEstudioBeanHelper(PlanEstudioBeanHelper planEstudioBeanHelper) {
        this.planEstudioBeanHelper = planEstudioBeanHelper;
    }
    
    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    
     @PostConstruct
    public void postConstructor() {
//        profesorBeanHelper.setListaRol(loginBean.Obtenerrol(loginBean.getUsuario().getUsuid()));
        planEstudioBeanHelper.setRolSeleccionado(loginBean.getSeleccionado());
        planEstudioBeanHelper.setUsuario(loginBean.getUsuario());
        System.out.println("rol desde el BeanUI: " + loginBean.getSeleccionado());
        System.out.println("id del usuario desde login " + loginBean.getUsuario().getUsuid());
    }
   
    public String header(int i) {
        if (i == 1) {
            cabecera = "Agregar Plan de Estudio";
            textoBoton = "Aceptar";
            deshabilitar = "false";
        }
        if (i == 2) {
            cabecera = "Eliminar Plan de Estudio";
            textoBoton = "Aceptar";
            deshabilitar = "true";
        }
        if (i == 3) {
            cabecera = "Modificar Plan de Estudio";
            textoBoton = "Aceptar";
            deshabilitar = "false";
        }
        return cabecera;
    }

    
    public void nuevo() {    

        header(1);
        planEstudioBeanHelper.setPlanEstudio(new Planestudio());
        planEstudioBeanHelper.setProgramaeducativo(new Programaeducativo());
        }

    public void modificar() {
        header(3);
     
        try {
            if (planEstudioBeanHelper.getListaSeleccionPlanEstudio().size() == 1) {
                planEstudioBeanHelper.setPlanEstudio(planEstudioBeanHelper.getListaSeleccionPlanEstudio().get(0));
                planEstudioBeanHelper.setProgramaeducativo(planEstudioBeanHelper.getListaSeleccionPlanEstudio().get(0).getProgramaeducativo());
            } else {
                if (planEstudioBeanHelper.getListaSeleccionPlanEstudio().size() < 1) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                } else {
                    planEstudioBeanHelper.setPlanEstudio(new Planestudio());
                    planEstudioBeanHelper.setProgramaeducativo(new Programaeducativo());
                    planEstudioBeanHelper.setPlanEstudio(planEstudioBeanHelper.getListaSeleccionPlanEstudio().get(0));
                    planEstudioBeanHelper.setProgramaeducativo(planEstudioBeanHelper.getListaSeleccionPlanEstudio().get(0).getProgramaeducativo());
                }
            }
        } catch (NullPointerException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void eliminar() {
        header(2);
       
        try {
            if (planEstudioBeanHelper.getListaSeleccionPlanEstudio().size() == 1) {
                planEstudioBeanHelper.setPlanEstudio(planEstudioBeanHelper.getListaSeleccionPlanEstudio().get(0));
                planEstudioBeanHelper.setProgramaeducativo(planEstudioBeanHelper.getListaSeleccionPlanEstudio().get(0).getProgramaeducativo());
            } else {
                if (planEstudioBeanHelper.getListaSeleccionPlanEstudio().size() < 1) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                } else {
                    planEstudioBeanHelper.setPlanEstudio(planEstudioBeanHelper.getListaSeleccionPlanEstudio().get(0));
                    planEstudioBeanHelper.setProgramaeducativo(planEstudioBeanHelper.getListaSeleccionPlanEstudio().get(0).getProgramaeducativo());
                }
            }
//           
        } catch (NullPointerException e) {
            planEstudioBeanHelper.setPlanEstudio(new Planestudio());            
            planEstudioBeanHelper.setProgramaeducativo(new Programaeducativo());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono; ningún registro");
            RequestContext.getCurrentInstance().showMessageInDialog(message);

        }
    }
//metodo para eliminar un registro de la base de datos

    public void Confirmacion() {
        if (deshabilitar.equals("true")) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Eliminando", "Se elimino; correctamente"));

            planEstudioBeanHelper.eliminarDeLista(planEstudioBeanHelper.getPlanEstudio().getPesid());
            planEstudioBeanHelper.getPlanEstudioDelegate().eliminarPlanEstudio(planEstudioBeanHelper.getPlanEstudio());
            planEstudioBeanHelper.setSelecPlanEstudio(new Planestudio());
            planEstudioBeanHelper.setPlanEstudio(new Planestudio());
            //profesorBeanHelper.setProfesor(profesor.setProid(0));
            
            //profesor.setProid(0);
            planEstudioBeanHelper.setProgramaeducativo(new Programaeducativo());
            RequestContext.getCurrentInstance().execute("confirmacion.hide();");

            if (planEstudioBeanHelper.getListaSeleccionPlanEstudio().size() == 1) {
                planEstudioBeanHelper.setPlanEstudio(planEstudioBeanHelper.getListaSeleccionPlanEstudio().get(0));
                planEstudioBeanHelper.setProgramaeducativo(planEstudioBeanHelper.getListaSeleccionPlanEstudio().get(0).getProgramaeducativo());
            }
        } else {
            planEstudioBeanHelper.getPlanEstudioDelegate().agregarPlanEstudio(planEstudioBeanHelper.getPlanEstudio());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Modificación", "Se guardo; correctamente"));
            RequestContext.getCurrentInstance().execute("confirmacion.hide();");
        }
        filtrado();
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro eliminado correctamente");
//        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public String onClickSubmit() {
        
        //System.out.println("Mensaje Confirmacion antes : "+mensajeConfirmacion);
        //setMensajeConfirmacion();
        //System.out.println("Mensaje Confirmacion despues : "+mensajeConfirmacion);
        if (deshabilitar.equals("true")) {
            setMensajeConfirmacion();
            RequestContext.getCurrentInstance().execute("confirmacion.show()");
        } else {
            if (planEstudioBeanHelper.getProgramaeducativo().getPedid() == 0
                    || planEstudioBeanHelper.getPlanEstudio().getPesvigenciaPlan().isEmpty()
                    || planEstudioBeanHelper.getPlanEstudio().getPescreditosObligatorios() == 0
                    || planEstudioBeanHelper.getPlanEstudio().getPescreditosOptativos() == 0
                    || planEstudioBeanHelper.getPlanEstudio().getPestotalCreditos() == 0
                    ) {

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Favor de llenar todos los campos");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                
            } else {
                MensajeVal = planEstudioBeanHelper.Validar(cabecera);//Se manda a llamar el metodo que nos devolvera un mensaje sobre los campos repetidos
                if (MensajeVal.isEmpty()) {//En caso de que la variable este vacia se le asignara una palabra para represente que no exten campos vacios
                    MensajeVal = "nada";
                }  
                   // MensajeVal = "nada";
                if (MensajeVal.equals("nada")) {
//                    HashSet setPlanEstudio = new HashSet();
//                    for(String puestosProfesor : listaSeleccionPuestos){
//                        for (Puesto puesto : listaPuestos) {
//                            if (puesto.getPuepuesto().equalsIgnoreCase(puestosProfesor)) {
//                                System.out.println("ID's de los puestos que se van a asignar:" + puesto.getPueid());
//                                setPuestos.add(puesto);
//                            }
//                        }
//                    }                     

                    if (cabecera.equals("Agregar Plan de Estudio")) {
                        HashSet setPE = new HashSet();

                        System.out.println("Antes de enviar mensaje de se guardo");
                       
                      //  profesorBeanHelper.getProfesor().setProgramaeducativos(setPE);
                      //  profesorBeanHelper.asignarUA();
                        planEstudioBeanHelper.getPlanEstudioDelegate().agregarPlanEstudio(planEstudioBeanHelper.getPlanEstudio());
//                        profesorBeanHelper.asignarPuestos(setPuestos);
                        planEstudioBeanHelper.setSelecPlanEstudio(new Planestudio());
                        planEstudioBeanHelper.setPlanEstudio(new Planestudio());
                        planEstudioBeanHelper.setProgramaeducativo(new Programaeducativo());

                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Alta", "Se guardo; correctamente"));
                        System.out.println("Despues de enviar mensaje de se guardo");
                    } else {
                        setMensajeConfirmacion();
                        if(x > 1){
                            RequestContext.getCurrentInstance().execute("confirmacion.show()");
                        }
                        else{
                     //   System.out.println("usuario id " + profesorBeanHelper.getUsuario2().getUsuid());
                        HashSet setPE = new HashSet();

                      /*  for (String PEDelProfesor : obtenerListaPE) {
                            for (Programaeducativo listPEAll : listaPE) {
                                if (listPEAll.getPednombre().equalsIgnoreCase(PEDelProfesor)) {
                                    System.out.println(listPEAll.getPednombre());
                                    setPE.add(listPEAll);
                                }
                            }
                        }*/
                        
                        planEstudioBeanHelper.getPlanEstudioDelegate().agregarPlanEstudio(planEstudioBeanHelper.getPlanEstudio());     
                        planEstudioBeanHelper.seleccionarRegistro();
                        planEstudioBeanHelper.setListaSeleccionPlanEstudio(planEstudioBeanHelper.getListaSeleccionPlanEstudio());
                        
                        //profesorBeanHelper.getProfesor().setProgramaeducativos(setPE);
                        //profesorBeanHelper.getProfesorDeleagate().agregarProfesor(profesorBeanHelper.getProfesor());
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Modificación", "Se guardo; correctamente"));

                        planEstudioBeanHelper.seleccionarRegistro();
                        RequestContext.getCurrentInstance().update("frmProfesor:seleccionados");
                        }
//                        RequestContext.getCurrentInstance().execute("confirmacion.show()");
                    }
//            profesorBeanHelper.getProfesorDeleagate().agregarProfesor(profesorBeanHelper.getProfesor());    
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "datos guardados correctamente");         
//            RequestContext.getCurrentInstance().showMessageInDialog(message);   
//                    filtrado1();
                } else {

                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El Plan de Estudio ligado al Programa Educativo ya existe" );
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
            }
        }
        filtrado();
        return "";
    }
    public void filtrado(){
        listaFiltrada = planEstudioBeanHelper.filtrado("Nombre", busqueda);
    }
    
    public String tooltip(int i) {
        if (planEstudioBeanHelper.getListaSeleccionPlanEstudio() == null || planEstudioBeanHelper.getListaSeleccionPlanEstudio().size() < 1) {
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
        if (planEstudioBeanHelper.getListaSeleccionPlanEstudio()== null || planEstudioBeanHelper.getListaSeleccionPlanEstudio().size() < 1) {
            return true;
        }
        return false;
    }
        
        public boolean mostrarSeleccionPlanEstudio() {
        if (planEstudioBeanHelper.getListaSeleccionPlanEstudio() != null && planEstudioBeanHelper.getListaSeleccionPlanEstudio().size() > 1) {
            return true;
        } else {
            return false;
        }
    }
        
    public void limpiarSeleccion() {
        planEstudioBeanHelper.setListaSeleccionPlanEstudio(null);
        planEstudioBeanHelper.setPlanEstudio(new Planestudio());
        planEstudioBeanHelper.setProgramaeducativo(new Programaeducativo());
        mostrarSeleccionPlanEstudio();
    }
    
    public void horasCompletas(){
        int credOptativos = planEstudioBeanHelper.getPlanEstudio().getPescreditosOptativos();
        int credObligatorios = planEstudioBeanHelper.getPlanEstudio().getPescreditosObligatorios();
        int horasCompletas= credOptativos + credObligatorios;
        planEstudioBeanHelper.getPlanEstudio().setPestotalCreditos(horasCompletas);
    }
    
    public void setMensajeConfirmacion(){    
     boolean asignado=false;
     
        if(deshabilitar.equals("true")){
            if(planEstudioBeanHelper.getPlanEstudioDelegate().getPlanAsignadoGrupo(planEstudioBeanHelper.getPlanEstudio().getPesid()).size() > 0){
                asignado=true;
                
                if(planEstudioBeanHelper.getPlanEstudioDelegate().getPlanAsignadoAreaConocimiento(planEstudioBeanHelper.getPlanEstudio().getPesid()).size() > 0){
                    mensajeConfirmacion="El plan de estudio ya esta asignado a un grupo y a una area de conocimiento ¿Desea continuar?";
                    x=10;
                }
                else{
                    mensajeConfirmacion="El plan de estudio ya esta asignado a un grupo ¿Deseas continuar?";
                    x=10;
                }
            }
            else{
              if(planEstudioBeanHelper.getPlanEstudioDelegate().getPlanAsignadoAreaConocimiento(planEstudioBeanHelper.getPlanEstudio().getPesid()).size() > 0){
                    mensajeConfirmacion="El plan de estudio ya esta asignado a un area de conocimiento ¿Desea Continnuar?";
                    x=10;
              }
              else{
                   mensajeConfirmacion="Esta seguro de eliminar este registro";
                   x=0;
              }                
            }
        }
        else{
            if(planEstudioBeanHelper.getPlanEstudioDelegate().getPlanAsignadoGrupo(planEstudioBeanHelper.getPlanEstudio().getPesid()).size() > 0){
                asignado=true;
                
               if(planEstudioBeanHelper.getPlanEstudioDelegate().getPlanAsignadoAreaConocimiento(planEstudioBeanHelper.getPlanEstudio().getPesid()).size() > 0){
                   mensajeConfirmacion="El plan de estudio ya esta asignado a un grupo y a un area de conocimiento ¿Desea continnuar?";
                   x=10;
               }
               else{
                   mensajeConfirmacion="El plan de estudio ya esta asignado a un grupo ¿Deseas continuar?"; 
                   x=10;
               }                
            }
            else{
               if(planEstudioBeanHelper.getPlanEstudioDelegate().getPlanAsignadoAreaConocimiento(planEstudioBeanHelper.getPlanEstudio().getPesid()).size() > 0){
                    mensajeConfirmacion="El plan de estudio ya esta asignado a un area de conocimiento ¿Desea Continnuar?";
                    x=10;
               }
               else{
                   mensajeConfirmacion="Esta seguro de eliminar este registro";
                   x=0;
               }
            }       
        }
        
       
           if(planEstudioBeanHelper.getPlanEstudioDelegate().getPlanAsignadoAreaConocimiento(planEstudioBeanHelper.getPlanEstudio().getPesid()).size() > 0){
                if(asignado==true){
                    mensajeConfirmacion="El plan de estudio ya esta asignado a un grupo y a un area de conocimiento ¿Desea Continnuar?";
                    x=10;
                }
                else{
                    mensajeConfirmacion="El plan de estudio ya esta asignado a un area de conocimiento ¿Desea continuar?";
                    x=10;
                }
        }
       
        RequestContext.getCurrentInstance().update("confirmacionId");
}
    
    public void validacionVigenciaPlan(){
        String validacion;
        String vigencia = Character.toString(planEstudioBeanHelper.getPlanEstudio().getPesvigenciaPlan().charAt(5));
        try{
            
            System.out.println("Vigencia : "+vigencia);
            System.out.println("Plan Estudio 1 : "+planEstudioBeanHelper.getPlanEstudio().getPesvigenciaPlan().charAt(5));
            if(vigencia.equals("1") || vigencia.equals("2")){
                System.out.println("Plan Estudio 2 : "+planEstudioBeanHelper.getPlanEstudio().getPesvigenciaPlan());
            }           
            else{
                System.out.println("Plan Estudio original: " + planEstudioBeanHelper.getPlanEstudio().getPesvigenciaPlan());
            StringBuilder sb = new StringBuilder(planEstudioBeanHelper.getPlanEstudio().getPesvigenciaPlan());
            sb.deleteCharAt(5); 
            validacion=sb.toString();
                System.out.println("Valor de validacion : "+sb);
            planEstudioBeanHelper.getPlanEstudio().setPesvigenciaPlan(validacion);
                System.out.println("Plan Estudio Modificado: " + planEstudioBeanHelper.getPlanEstudio().getPesvigenciaPlan());
           
            }
        }catch(Exception e){
            
        }
//        RequestContext.getCurrentInstance().update(":frmProfesor:cap:profrfc");
//        RequestContext.getCurrentInstance().execute("event.preventDefault();\n" +
//"                                            return false;\n" +
//"                                            ");
    }
  
}
