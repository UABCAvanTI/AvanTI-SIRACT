/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import mx.avanti.siract.application.helper.AsignarRPEBeanHelper;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Rol;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Paco
 */
@ManagedBean
@ViewScoped
public class AsignarRPEBeanUI implements Serializable{

    private List<Profesor> listaFiltrada;
    private AsignarRPEBeanHelper asignarRPEHelper;
    
    private Profesor profMod;
    
    private String busqueda;
    private String cabecera;
    private String deshabilitar;
    private String MensajeVal;
    private String mensajeConfirmacion;
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    
    public AsignarRPEBeanUI() {
        asignarRPEHelper = new AsignarRPEBeanHelper();
        
        busqueda="";
        cabecera = "";
        deshabilitar = "";
    }
    @PostConstruct
    public void postConstructor() {
        asignarRPEHelper.setRolSeleccionado(loginBean.getSeleccionado());
        asignarRPEHelper.setUsuarioLogeado(loginBean.getLogueado());
        System.out.println("rol desde el BeanUI: " + loginBean.getSeleccionado());
        System.out.println("id del usuario desde login " + loginBean.getLogueado().getUsuid());        
        
    }

    //<editor-fold defaultstate="collapsed" desc="getter setter">
    
    public String getMensajeConfirmacion() {
        return mensajeConfirmacion;
    }

    public void setMensajeConfirmacion(String mensajeConfirmacion) {
        this.mensajeConfirmacion = mensajeConfirmacion;
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

    public List<Profesor> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Profesor> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public AsignarRPEBeanHelper getAsignarRPEHelper() {
        return asignarRPEHelper;
    }

    public void setAsignarRPEHelper(AsignarRPEBeanHelper asignarRPEHelper) {
        this.asignarRPEHelper = asignarRPEHelper;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
    
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="operaciones">
     public void nuevo() {
        header(1);
        limpiarSeleccion();
                asignarRPEHelper.setIdProfMod(0);
                asignarRPEHelper.setIdPEMod(0);
        asignarRPEHelper.setProfesor(new Profesor());
        asignarRPEHelper.getProfesor().setProid(0);
        asignarRPEHelper.setProgramaEducativo(new Programaeducativo());
        asignarRPEHelper.getProgramaEducativo().setPedid(0);
    }
     public void eliminar(){
         header(2);
         asignarRPEHelper.getUsuarioTienePE();
         asignarRPEHelper.setProgramaEducativo(asignarRPEHelper.peByRPEobj(asignarRPEHelper.getProfesorSeleccionado().getProid()));
         asignarRPEHelper.setProfesor(asignarRPEHelper.getProfesorSeleccionado());
//         try {
//            if (asignarRPEHelper.getListaSeleccion().size() == 1) {
//                asignarRPEHelper.setProfesor(asignarRPEHelper.getListaSeleccion().get(0));
//                //areaAdministrativaHelper.setAreaAdministrativa(areaAdministrativaHelper.getListaSeleccion().get(0));
//                //areaAdministrativaHelper.setProgramaEducativo(areaAdministrativaHelper.getListaSeleccion().get(0).getProgramaeducativo());
//                
//            } else {
//                if (asignarRPEHelper.getListaSeleccion().size() < 1) {
//                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
//                    RequestContext.getCurrentInstance().showMessageInDialog(message);
//                } else {
//                
//                    //areaAdministrativaHelper.setAreaAdministrativa(areaAdministrativaHelper.getListaSeleccion().get(0));
//                    //areaAdministrativaHelper.setProgramaEducativo(areaAdministrativaHelper.getListaSeleccion().get(0).getProgramaeducativo());
//                
//                }
//            }
////           
//        } catch (NullPointerException e) {
//                    //areaAdministrativaHelper.setAreaAdministrativa(new Areaadministrativa(new Programaeducativo(), ""));
//                    //areaAdministrativaHelper.setProgramaEducativo(new Programaeducativo());
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se seleccion&oacute; ning&uacute;n registro");
//            RequestContext.getCurrentInstance().showMessageInDialog(message);
//
//        }
     }
     public void modificar(){
         header(3);
        asignarRPEHelper.getUsuarioTienePE();
         asignarRPEHelper.setProgramaEducativo(asignarRPEHelper.peByRPEobj(asignarRPEHelper.getProfesorSeleccionado().getProid()));
//                try {
//                    if (asignarRPEHelper.getListaSeleccion().size() == 1) {
                        asignarRPEHelper.setProfesor(asignarRPEHelper.getProfesorSeleccionado());
                        asignarRPEHelper.setIdProfMod(asignarRPEHelper.getProfesor().getProid());
                        asignarRPEHelper.setIdPEMod(asignarRPEHelper.getProgramaEducativo().getPedid());
                        System.out.println(asignarRPEHelper.getIdPEMod());
                        System.out.println(asignarRPEHelper.getIdProfMod());
                //areaAdministrativaHelper.setAreaAdministrativa(areaAdministrativaHelper.getListaSeleccion().get(0));
                //areaAdministrativaHelper.setProgramaEducativo(areaAdministrativaHelper.getListaSeleccion().get(0).getProgramaeducativo());
                
//            } else {
//                if (asignarRPEHelper.getListaSeleccion().size() < 1) {
//                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
//                    RequestContext.getCurrentInstance().showMessageInDialog(message);
//                } else {
//                
//                    //areaAdministrativaHelper.setAreaAdministrativa(areaAdministrativaHelper.getListaSeleccion().get(0));
//                    //areaAdministrativaHelper.setProgramaEducativo(areaAdministrativaHelper.getListaSeleccion().get(0).getProgramaeducativo());
//                
//                }
//            }
//            if (areaAdministrativaHelper.getListaSeleccion().size() == 1) {
//                areaAdministrativaHelper.setAreaAdministrativa(areaAdministrativaHelper.getListaSeleccion().get(0));
//                //areaAdministrativaHelper.setProgramaEducativo(areaAdministrativaHelper.getListaSeleccion().get(0).getProgramaeducativo());
//                System.out.println("seleccionpedid: "+areaAdministrativaHelper.getListaSeleccion().get(0).getProgramaeducativo().getPedid());
//                System.out.println("pedid: " +areaAdministrativaHelper.getProgramaEducativo().getPedid());
//                
//            } else {
//                if (areaAdministrativaHelper.getListaSeleccion().size() < 1) {
//                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
//                    RequestContext.getCurrentInstance().showMessageInDialog(message);
//                } else {
//                    areaAdministrativaHelper.setAreaAdministrativa(new Areaadministrativa(new Programaeducativo(), ""));
//                    //areaAdministrativaHelper.setProgramaEducativo(new Programaeducativo());
//                
//                    areaAdministrativaHelper.setAreaAdministrativa(areaAdministrativaHelper.getListaSeleccion().get(0));
//                    //areaAdministrativaHelper.setProgramaEducativo(areaAdministrativaHelper.getListaSeleccion().get(0).getProgramaeducativo());
//                
//                }
//            }
//        } catch (NullPointerException e) {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
//            RequestContext.getCurrentInstance().showMessageInDialog(message);
//        }
     }
     public String onClickSubmit() {
        setMensajeConfirmacion();
        if (deshabilitar.equals("true")) {
            RequestContext.getCurrentInstance().execute("confirmacion.show()");
        } else {
            if (asignarRPEHelper.getProfesor().getProid()==0
                    || asignarRPEHelper.getProgramaEducativo().getPedid() == 0) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Favor de llenar todos los campos");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            } else {
                System.out.println(asignarRPEHelper.getProfesor().getProid());
                System.out.println(asignarRPEHelper.getProgramaEducativo().getPedid());
                System.out.println(asignarRPEHelper.getIdPEMod());
                System.out.println(asignarRPEHelper.getIdProfMod());
                MensajeVal = asignarRPEHelper.Validar();//Se manda a llamar el metodo que nos devolvera un mensaje sobre los campos repetidos
                if (MensajeVal.isEmpty()) {//En caso de que la variable este vacia se le asignara una palabra para represente que no exten campos vacios
                    MensajeVal = "nada";
                }
                if (MensajeVal.equals("nada")) {
                    if (cabecera.equals("Agregar Asignación de Responsable")) {
                        HashSet setPE = new HashSet();
                        setPE.add(asignarRPEHelper.getProgramaEducativo());
                        
                        asignarRPEHelper.setProfesor(asignarRPEHelper.getProfesorDelegate().findProfesorById(asignarRPEHelper.getProfesor().getProid()));
                        asignarRPEHelper.getProfesor().setProgramaeducativos(setPE);
                        //areaAdministrativaHelper.getAreaAdministrativa().setProgramaeducativo(areaAdministrativaHelper.getProgramaEducativo());
                        asignarRPEHelper.getProfesorDelegate().agregarProfesor(asignarRPEHelper.getProfesor());
//                                getAreaAdministrativaDelegate().agregarAreaAdministrativa(
//                                        areaAdministrativaHelper.getAreaAdministrativa());
//                        
                        asignarRPEHelper.setProfesor(new Profesor());
                        asignarRPEHelper.getProfesor().setProid(0);
                        asignarRPEHelper.setProgramaEducativo(new Programaeducativo());
                        asignarRPEHelper.getProgramaEducativo().setPedid(0);
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("", "Se guardó correctamente"));
                    } else {
//                        HashSet setPE = new HashSet();
//                        setPE.add(asignarRPEHelper.getProgramaEducativo());
//                        
//                        asignarRPEHelper.setProfesor(asignarRPEHelper.getProfesorDelegate().findProfesorById(asignarRPEHelper.getProfesor().getProid()));
//                        asignarRPEHelper.getProfesor().setProgramaeducativos(setPE);
//                        asignarRPEHelper.getProfesorDelegate().agregarProfesor(asignarRPEHelper.getProfesor());
////                      
////                         areaAdministrativaHelper.getAreaAdministrativaDelegate().agregarAreaAdministrativa(areaAdministrativaHelper.getAreaAdministrativa());
//                           
//                        FacesContext context = FacesContext.getCurrentInstance();
//                        context.addMessage(null, new FacesMessage("Modificación", "Se guardó correctamente"));
////                        }
                        
            RequestContext.getCurrentInstance().execute("confirmacion.show()");
                    }
                } else {

                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", MensajeVal);
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
            }
        }
        filtro();
        return "";
    }
     public void Confirmacion() {
        if (deshabilitar.equals("true")) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("", "Se eliminó correctamente"));

                        
            asignarRPEHelper.setProfesor(asignarRPEHelper.getProfesorDelegate().findProfesorById(asignarRPEHelper.getProfesor().getProid()));
            asignarRPEHelper.getProfesor().setProgramaeducativos(new HashSet());
            asignarRPEHelper.getProfesorDelegate().agregarProfesor(asignarRPEHelper.getProfesor());

            RequestContext.getCurrentInstance().execute("confirmacion.hide();");

                RequestContext.getCurrentInstance().execute("dlg.hide();");
                limpiarSeleccion();
            
        } else {
            HashSet setPE = new HashSet();
            
            profMod = asignarRPEHelper.getProfesorDelegate().findProfesorById(asignarRPEHelper.getIdProfMod());
            profMod.setProgramaeducativos(new HashSet());
            asignarRPEHelper.getProfesorDelegate().agregarProfesor(profMod);
            
                        setPE.add(asignarRPEHelper.getProgramaEducativo());
                        
                        asignarRPEHelper.setProfesor(asignarRPEHelper.getProfesorDelegate().findProfesorById(asignarRPEHelper.getProfesor().getProid()));
                        asignarRPEHelper.getProfesor().setProgramaeducativos(setPE);
                        asignarRPEHelper.getProfesorDelegate().agregarProfesor(asignarRPEHelper.getProfesor());
//                      
//                         areaAdministrativaHelper.getAreaAdministrativaDelegate().agregarAreaAdministrativa(areaAdministrativaHelper.getAreaAdministrativa());
                           
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("", "Se guardó correctamente"));
            RequestContext.getCurrentInstance().execute("confirmacion.hide();");
            RequestContext.getCurrentInstance().execute("dlg.show();");
            asignarRPEHelper.setIdPEMod(asignarRPEHelper.getProgramaEducativo().getPedid());
            asignarRPEHelper.setIdProfMod(asignarRPEHelper.getProfesor().getProid());

        }
        filtro();
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro eliminado correctamente");
//        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
//</editor-fold>
     //<editor-fold defaultstate="collapsed" desc="metodos de vista">
           public void setMensajeConfirmacion() {
        if (deshabilitar.equals("true")) {
            
                mensajeConfirmacion = "¿Está seguro de eliminar el registro?";
            
        } else{
            if (cabecera.equals("Modificar Asignación de Responsable")) {
            mensajeConfirmacion = "¿Está seguro de guardar los cambios?";
            }
        }
        //mensajeConfirmacion = "¿Esta seguro que desea eliminar el registro?";
        RequestContext.getCurrentInstance().update("confirmacionId");
    }
    public String header(int i) {
        if (i == 1) {
            cabecera = "Agregar Asignación de Responsable";
            deshabilitar = "false";
        }
        if (i == 2) {
            cabecera = "Eliminar Asignación de Responsable";
            deshabilitar = "true";
        }
        if (i == 3) {
            cabecera = "Modificar Asignación de Responsable";
            deshabilitar = "false";
        }
        return cabecera;
    }
    
    public String tooltip(int i) {
        if (asignarRPEHelper.getProfesorSeleccionado()== null || asignarRPEHelper.getProfesorSeleccionado().getProid()==null) {
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
        if (asignarRPEHelper.getProfesorSeleccionado()== null || asignarRPEHelper.getProfesorSeleccionado().getProid() == null) {
            return true;
        }
        return false;
    }
//</editor-fold>
    public void filtro(){
        List<Rol> list = null;
        list = loginBean.Obtenerrol(loginBean.getLogueado().getUsuid());
        String seleccionado=loginBean.getSeleccionado();
        System.out.println(seleccionado+"ÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑ");
        String catalogo="Responsable de programa educativo";
        loginBean.TienePermiso(list, seleccionado, catalogo);
        listaFiltrada = asignarRPEHelper.filtrado(busqueda);
    }
    
    public void limpiarSeleccion() {
        asignarRPEHelper.setListaSeleccion(null);
        asignarRPEHelper.setProfesorSeleccionado(new Profesor());
        asignarRPEHelper.setProfesor(new Profesor());
        asignarRPEHelper.setProgramaEducativo(new Programaeducativo());
        asignarRPEHelper.getProfesor().setProid(0);
        asignarRPEHelper.getProgramaEducativo().setPedid(0);
        //areaAdministrativaHelper.setAreaAdministrativa(new Areaadministrativa(new Programaeducativo(), ""));
        //areaAdministrativaHelper.setProgramaEducativo(new Programaeducativo());
//        mostrarSeleccionAreasAD();
    }
    public boolean mostrarSeleccionRPE() {
        if (asignarRPEHelper.getListaSeleccion()!= null && asignarRPEHelper.getListaSeleccion().size() > 1) {
            return true;
        } else {
            return false;
        }
    }
}