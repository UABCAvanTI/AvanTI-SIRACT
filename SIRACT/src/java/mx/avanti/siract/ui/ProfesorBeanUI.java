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
import mx.avanti.siract.application.helper.ProfesorBeanHelper;
import mx.avanti.siract.application.helper.UsuarioBeanHelper;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Programaeducativo;
//import mx.avanti.siract.business.entity.Puesto;
import mx.avanti.siract.business.entity.Usuario;
import org.primefaces.context.RequestContext;

/**
 *
 * @author balta
 */
@ManagedBean
@ViewScoped
public class ProfesorBeanUI implements Serializable {

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    private ProfesorBeanHelper profesorBeanHelper = null;
    private UsuarioBeanHelper usuarioBeanHelper = null;
    private String cabecera;
    private String textoBoton;
    private String deshabilitar = "";
    private String campo;
    private String busqueda;
    private List<Profesor> listaFiltrada;
    private List<Usuario> listaUsuarios;
//    private List<Puesto> listaPuestos;
    private List<Programaeducativo> listaPE;
    private List<String> listaSeleccionPuestos;
    private List<String> obtenerListaPE;
    private Profesor profesor;
    private String MensajeVal = "";//variable que tendra el mensaje sobre los datos repetidos
    private String mensajeConfirmacion;

    public ProfesorBeanUI() {
        init();
    }

    private void init() {
        profesorBeanHelper = new ProfesorBeanHelper();
        usuarioBeanHelper = new UsuarioBeanHelper();
        busqueda = "";
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public List<String> getListaSeleccionPuestos() {
        return listaSeleccionPuestos;
    }

    public void setListaSeleccionPuestos(List<String> listaSeleccionPuestos) {
        this.listaSeleccionPuestos = listaSeleccionPuestos;
    }

    public List<String> getObtenerListaPE() {
        return obtenerListaPE;
    }

    public void setObtenerListaPE(List<String> obtenerListaPE) {
        this.obtenerListaPE = obtenerListaPE;
    }

    public String getMensajeVal() {
        return MensajeVal;
    }

    public void setMensajeVal(String MensajeVal) {
        this.MensajeVal = MensajeVal;
    }

    public UsuarioBeanHelper getUsuarioBeanHelper() {
        return usuarioBeanHelper;
    }

    public void setUsuarioBeanHelper(UsuarioBeanHelper usuarioBeanHelper) {
        this.usuarioBeanHelper = usuarioBeanHelper;
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

    public ProfesorBeanHelper getProfesorBeanHelper() {
        return profesorBeanHelper;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public List<Profesor> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Profesor> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

//    public List<Puesto> getListaPuestos() {
//        listaPuestos = profesorBeanHelper.getListaPuesto();
//        return listaPuestos;
//    }
    public List<Programaeducativo> getListaPE() {
        return listaPE;
    }

    public void setListaPE(List<Programaeducativo> listaPE) {
        this.listaPE = listaPE;
    }

//    public void setListaPuestos(List<Puesto> listaPuestos) {
//        this.listaPuestos = listaPuestos;
//    }
    @PostConstruct
    public void postConstructor() {
//        profesorBeanHelper.setListaRol(loginBean.Obtenerrol(loginBean.getUsuario().getUsuid()));
        profesorBeanHelper.setRolSeleccionado(loginBean.getSeleccionado());
        profesorBeanHelper.setUsuario(loginBean.getUsuario());
        System.out.println("rol desde el BeanUI: " + loginBean.getSeleccionado());
        System.out.println("id del usuario desde login " + loginBean.getUsuario().getUsuid());
    }

    public void filtrado() {
        listaFiltrada = profesorBeanHelper.filtrado("Nombre", busqueda);
//        RequestContext.getCurrentInstance().update(":frmProfesor:seleccionados");
//        RequestContext.getCurrentInstance().update("frmProfesor:seleccionados");
    }

    public void nuevo() {
        limpiarSeleccion();
        header(1);
        profesorBeanHelper.setProfesor(new Profesor());
        profesorBeanHelper.setUsuario2(new Usuario());
//        profesorBeanHelper.setProgramaEducativo(new Programaeducativo());
        listaSeleccionPuestos = null;
        obtenerListaPE.clear();
//        profesorBeanHelper.getListaProfesores().clear();
//        profesorBeanHelper.setSelecProfesor(new Profesor());
        cargarUsuario();
        cargarPE();
    }

    public void modificar() {
//        obtenerListaPE = null;
        obtenerListaPE.clear();
        header(3);
        cargarUsuario();
        cargarPE();
        mostrarPEDeProfesor();
        try {
            if (profesorBeanHelper.getListaSeleccionProfesores().size() == 1) {
                profesorBeanHelper.setProfesor(profesorBeanHelper.getListaSeleccionProfesores().get(0));
                profesorBeanHelper.setUsuario2(profesorBeanHelper.getListaSeleccionProfesores().get(0).getUsuario());
                profesorBeanHelper.getPEModProf(profesorBeanHelper.getListaSeleccionProfesores().get(0));
                for (Programaeducativo pe : profesorBeanHelper.getListaPEMod()) {
                    obtenerListaPE.add(pe.getPednombre());
                }
                //profesorBeanHelper.setProgramaEducativo(profesorBeanHelper.getListaSeleccionProfesores());
            } else {
                if (profesorBeanHelper.getListaSeleccionProfesores().size() < 1) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                } else {
                    profesorBeanHelper.setProfesor(new Profesor());
                    profesorBeanHelper.setUsuario2(new Usuario());
                    profesorBeanHelper.setProfesor(profesorBeanHelper.getListaSeleccionProfesores().get(0));
                    profesorBeanHelper.setUsuario2(profesorBeanHelper.getListaSeleccionProfesores().get(0).getUsuario());
//                    profesorBeanHelper.setProgramaEducativo(profesorBeanHelper.getProgramaEducativoDelegate().findProgramaEducativoById(profesorBeanHelper.getListaSeleccionProfesores().get(0).getProid()));                    
                    profesorBeanHelper.getPEModProf(profesorBeanHelper.getListaSeleccionProfesores().get(0));
                    for (Programaeducativo pe : profesorBeanHelper.getListaPEMod()) {
                        obtenerListaPE.add(pe.getPednombre());
                    }
                }
            }
        } catch (NullPointerException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void eliminar() {
        header(2);
        cargarUsuario();
        try {
            if (profesorBeanHelper.getListaSeleccionProfesores().size() == 1) {
                profesorBeanHelper.setProfesor(profesorBeanHelper.getListaSeleccionProfesores().get(0));
                profesorBeanHelper.setUsuario2(profesorBeanHelper.getListaSeleccionProfesores().get(0).getUsuario());
            } else {
                if (profesorBeanHelper.getListaSeleccionProfesores().size() < 1) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono ningun registro");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                } else {
                    profesorBeanHelper.setProfesor(profesorBeanHelper.getListaSeleccionProfesores().get(0));
                    profesorBeanHelper.setUsuario2(profesorBeanHelper.getListaSeleccionProfesores().get(0).getUsuario());
                }
            }
//           
        } catch (NullPointerException e) {
            profesorBeanHelper.setProfesor(new Profesor());
            profesorBeanHelper.setUsuario2(new Usuario());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se seleccion&oacute; ning&uacute;n registro");
            RequestContext.getCurrentInstance().showMessageInDialog(message);

        }
    }
//metodo para eliminar un registro de la base de datos

    public void Confirmacion() {
        if (deshabilitar.equals("true")) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Eliminando", "Se eliminó correctamente"));

            profesorBeanHelper.eliminarDeLista(profesorBeanHelper.getProfesor().getProid());
            profesorBeanHelper.getProfesorDeleagate().eliminarProfesor(profesorBeanHelper.getProfesor());
            profesorBeanHelper.setSelecProfesor(new Profesor());
            profesorBeanHelper.setProfesor(new Profesor());
            //profesorBeanHelper.setProfesor(profesor.setProid(0));

            //profesor.setProid(0);
            profesorBeanHelper.setUsuario2(new Usuario());
            RequestContext.getCurrentInstance().execute("confirmacion.hide();");

            if (profesorBeanHelper.getListaSeleccionProfesores().size() >= 1) {
                profesorBeanHelper.setProfesor(profesorBeanHelper.getListaSeleccionProfesores().get(0));
                profesorBeanHelper.setUsuario2(profesorBeanHelper.getListaSeleccionProfesores().get(0).getUsuario());
            }
        } else {
            HashSet setPE = new HashSet();

            for (String PEDelProfesor : obtenerListaPE) {
                for (Programaeducativo listPEAll : listaPE) {
                    if (listPEAll.getPednombre().equalsIgnoreCase(PEDelProfesor)) {
                        System.out.println(listPEAll.getPednombre());
                        setPE.add(listPEAll);
                    }
                }
            }

            profesorBeanHelper.getProfesor().setProgramaeducativos_1(setPE);
            profesorBeanHelper.getProfesorDeleagate().agregarProfesor(profesorBeanHelper.getProfesor());

            profesorBeanHelper.getPEModProf(profesorBeanHelper.getListaSeleccionProfesores().get(0));
            obtenerListaPE.clear();
            for (Programaeducativo pe : profesorBeanHelper.getListaPEMod()) {
                obtenerListaPE.add(pe.getPednombre());
            }
            RequestContext.getCurrentInstance().execute("confirmacion.hide();");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Modificación", "Se guardó correctamente"));

        }
        filtrado();
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro eliminado correctamente");
//        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public String onClickSubmit() {
//        profesorBeanHelper.getUsuario2().getUsuid();
        setMensajeConfirmacion();
        if (deshabilitar.equals("true")) {
            RequestContext.getCurrentInstance().execute("confirmacion.show()");
        } else {
            if (profesorBeanHelper.getProfesor().getPronumeroEmpleado() == 0
                    || profesorBeanHelper.getProfesor().getPronumeroEmpleado() < 1
                    || profesorBeanHelper.getProfesor().getProrfc().isEmpty()
                    || profesorBeanHelper.getProfesor().getPronombre().isEmpty()
                    || profesorBeanHelper.getProfesor().getProapellidoPaterno().isEmpty()
                    || profesorBeanHelper.getProfesor().getProapellidoMaterno().isEmpty()
                    || profesorBeanHelper.getUsuario2().getUsuid() == 0
                    || obtenerListaPE.isEmpty()) {

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Favor de llenar todos los campos");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            } else {
                MensajeVal = profesorBeanHelper.Validar();//Se manda a llamar el metodo que nos devolvera un mensaje sobre los campos repetidos
                if (MensajeVal.isEmpty()) {//En caso de que la variable este vacia se le asignara una palabra para represente que no exten campos vacios
                    MensajeVal = "nada";
                }
                if (MensajeVal.equals("nada")) {

                    if (cabecera.equals("Agregar Profesor")) {
                        HashSet setPE = new HashSet();

                        for (String PEDelProfesor : obtenerListaPE) {
                            for (Programaeducativo listPEAll : listaPE) {
                                if (listPEAll.getPednombre().equalsIgnoreCase(PEDelProfesor)) {
                                    setPE.add(listPEAll);
                                }
                            }
                        }

                        profesorBeanHelper.asignarUA();
                        profesorBeanHelper.getProfesor().setProgramaeducativos_1(setPE);
                        profesorBeanHelper.getProfesorDeleagate().agregarProfesor(profesorBeanHelper.getProfesor());

                        profesorBeanHelper.setSelecProfesor(new Profesor());
                        profesorBeanHelper.setProfesor(new Profesor());
                        profesorBeanHelper.setUsuario2(new Usuario());

                        obtenerListaPE.clear();

                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Alta", "Se guardó correctamente"));
                    } else {
                        if (profesorBeanHelper.getProfesorDeleagate().getProfAsignado(profesorBeanHelper.getProfesor().getProid()).size() > 0) {
                            RequestContext.getCurrentInstance().execute("confirmacion.show()");
                        } else {
                            System.out.println("usuario id " + profesorBeanHelper.getUsuario2().getUsuid());
                            HashSet setPE = new HashSet();

                            for (String PEDelProfesor : obtenerListaPE) {
                                for (Programaeducativo listPEAll : listaPE) {
                                    if (listPEAll.getPednombre().equalsIgnoreCase(PEDelProfesor)) {
                                        System.out.println(listPEAll.getPednombre());
                                        setPE.add(listPEAll);
                                    }
                                }
                            }

                            profesorBeanHelper.getProfesor().setProgramaeducativos_1(setPE);
                            profesorBeanHelper.getProfesorDeleagate().agregarProfesor(profesorBeanHelper.getProfesor());

                            profesorBeanHelper.getPEModProf(profesorBeanHelper.getListaSeleccionProfesores().get(0));
                            obtenerListaPE.clear();
                            for (Programaeducativo pe : profesorBeanHelper.getListaPEMod()) {
                                obtenerListaPE.add(pe.getPednombre());
                            }

                            FacesContext context = FacesContext.getCurrentInstance();
                            context.addMessage(null, new FacesMessage("Modificación", "Se guardó correctamente"));
                        }
//                        profesorBeanHelper.seleccionarRegistro();                        

//                        RequestContext.getCurrentInstance().update(":frmProfesor:cap");                        
//                        RequestContext.getCurrentInstance().update("frmProfesor:seleccionados");
//                        RequestContext.getCurrentInstance().execute("confirmacion.show()");
                    }
//            profesorBeanHelper.getProfesorDeleagate().agregarProfesor(profesorBeanHelper.getProfesor());    
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "datos guardados correctamente");         
//            RequestContext.getCurrentInstance().showMessageInDialog(message);   
                } else {

                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Campos repetidos en:" + MensajeVal);
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
            }
        }
//        RequestContext.getCurrentInstance().update(":frmProfesor:seleccionados");
//        RequestContext.getCurrentInstance().update("frmProfesor:seleccionados");
        filtrado();
        return "";
    }
//                    profesorBeanHelper.getProfesorDeleagate().agregarProfesor(profesorBeanHelper.getProfesor());
//                    profesorBeanHelper.setSelecProfesor(new Profesor());
//                    profesorBeanHelper.setProfesor(new Profesor());  
//                    
//                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Datos guardados correctamente");         
//                    RequestContext.getCurrentInstance().showMessageInDialog(message);   
//                
////            profesorBeanHelper.getProfesorDeleagate().agregarProfesor(profesorBeanHelper.getProfesor());    
//            
////            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "datos guardados correctamente");         
////            RequestContext.getCurrentInstance().showMessageInDialog(message);   
//        }
//        }
//        
//        
//        filtrado();
//        return "";

    public boolean mostrarSeleccionProfesores() {
        if (profesorBeanHelper.getListaSeleccionProfesores() != null && profesorBeanHelper.getListaSeleccionProfesores().size() > 1) {
            return true;
        } else {
            return false;
        }
    }
//metodo para asignar texto a la cabecera e identificar si es eliminacion o no

    public String header(int i) {
        if (i == 1) {
            cabecera = "Agregar Profesor";
            textoBoton = "Aceptar";
            deshabilitar = "false";
        }
        if (i == 2) {
            cabecera = "Eliminar Profesor";
            textoBoton = "Aceptar";
            deshabilitar = "true";
        }
        if (i == 3) {
            cabecera = "Modificar Profesor";
            textoBoton = "Aceptar";
            deshabilitar = "false";
        }
        return cabecera;
    }
//metodo para cargar la lista de usuarios

    public List<Usuario> cargarUsuario() {
        listaUsuarios = profesorBeanHelper.getUsuarioDelegate().getListaUsuario();
//        if(!textoBoton.equals("Registrar")){         
//            listaUsuarios.clear();
//            listaUsuarios.add(profesorBeanHelper.getSelecProfesor().getUsuario());
//        }
        return listaUsuarios;
    }

    public List<Programaeducativo> cargarPE() {
        listaPE = profesorBeanHelper.getProgramaEducativoDelegate().getListaProgramaEducativo();
        return listaPE;
    }

//    public String PEDeProfesor(int peProfesor){
//        Programaeducativo peProf = profesorBeanHelper.getProgramaEducativoDelegate().findProgramaEducativoById(peProfesor);
//        if(peProf != null){
//            return peProf.getPednombre();
//        }else{
//            return "no tiene Programa Educativo asignado";
//        }
//    }
    public String listaPEDeProf(Profesor prof) {
        String ProgEducProf = "";
        List<String> lista = null;
        lista = profesorBeanHelper.getListaProgramaEducativo(prof);
        if (lista.isEmpty()) {
            return "No tiene Programa Educativo asignado.";
        } else {
            for (int i = 0; i < lista.size(); i++) {
                if (i == (lista.size()) - 1) {
                    ProgEducProf += lista.get(i) + ".";
                } else {
                    ProgEducProf += lista.get(i) + ", ";
                }
            }
            return ProgEducProf;
        }
    }

    //Inicia el metodo que nos devolvera una cadena con los posibles campos repetidos
    //Termina el metodo
    //Metodo que reinicia la lista de registros seleccionados
    public void limpiarSeleccion() {
        profesorBeanHelper.setListaSeleccionProfesores(null);
        profesorBeanHelper.setUsuario2(new Usuario());
        profesorBeanHelper.setProfesor(new Profesor());
        mostrarSeleccionProfesores();
    }

    public String tooltip(int i) {
        if (profesorBeanHelper.getListaSeleccionProfesores() == null || profesorBeanHelper.getListaSeleccionProfesores().size() < 1) {
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
        if (profesorBeanHelper.getListaSeleccionProfesores() == null || profesorBeanHelper.getListaSeleccionProfesores().size() < 1) {
            return true;
        }
        return false;
    }

    public String getMensajeConfirmacion() {
        return mensajeConfirmacion;
    }

    public void setMensajeConfirmacion() {
        if (deshabilitar.equals("true")) {
            if (profesorBeanHelper.getProfesorDeleagate().getProfAsignado(profesorBeanHelper.getProfesor().getProid()).size() > 0) {
                mensajeConfirmacion = "El profesor se encuentra asignado a una  unidad de aprendizaje y profesor.";
            } else {
                mensajeConfirmacion = "¿Está seguro de eliminar el registro?";
            }
        } else if (cabecera.equals("Modificar Profesor") && profesorBeanHelper.getProfesorDeleagate().getProfAsignado(profesorBeanHelper.getProfesor().getProid()).size() > 0) {
            mensajeConfirmacion = "El profesor se encuentra asignado a una  unidad de aprendizaje y profesor. ¿Está seguro de guardar los cambios?";

        }
        RequestContext.getCurrentInstance().update("confirmacionId");
    }

    public boolean mostrarListaPE() {
        if (deshabilitar.equalsIgnoreCase("true")) {
            return false;
        } else {
            return true;
        }
    }

    public void mostrarPEDeProfesor() {
        for (Profesor prof : profesorBeanHelper.getListaSeleccionProfesores()) {
            System.out.println("--------");

        }
    }

    public void actualizarListaPE() {
        obtenerListaPE.clear();
        for (Programaeducativo pe : profesorBeanHelper.getListaPEMod()) {
            obtenerListaPE.add(pe.getPednombre());
        }
    }
}
