/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.avanti.siract.application.helper.LoginBeanHelper;
import mx.avanti.siract.application.helper.UsuarioBeanHelper;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Rol;
import mx.avanti.siract.business.entity.Usuario;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author kitto
 */
@ManagedBean
@ViewScoped
public class UsuariosBeanUI implements Serializable {

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    
    private UsuarioBeanHelper usuarioBeanHelper = new UsuarioBeanHelper();
    
    private Usuario usuario = new Usuario();
    private Usuario logueado=new Usuario();
    private Usuario selectedUsuario;
    private List<Usuario> selectedUsuarios;
    private List<String> obtenerRoles;//aqui se guardan los roles que seleccione en la alta al usuario
    private List<Rol> obtenerListaRoles;
    private String boton = "true";
    private String busqueda;
    private LoginBeanHelper logBeanHelp = new LoginBeanHelper();
    private List<Usuario> listaFiltrada = usuarioBeanHelper.getUsuarioDelegate().getUsuario();
    private boolean multiSeleccion = true;
    private boolean eliminarono = false;
    private boolean eliminaa = false;
    private int idUsu = -1;
    private String deshabilitar;
    private String header;
    private String dialogo;
    private String usuariose;
    
    
    public UsuariosBeanUI() {
        
        if (this.usuario == null) {
            this.usuario = new Usuario();
        }
        selectedUsuario = new Usuario();
        init();
    }

    @PostConstruct
    private void init() {
        usuarioBeanHelper = new UsuarioBeanHelper();
        busqueda = "";
    }
    //////Limpiar variables
    public void refrescarForma2() {
         
        
        if (selectedUsuarios.size() == 1 && header.equals("Modificar Usuario")) {
            ///boton = "true";
            setSelectedUsuarios(null);

            System.out.println("llegue a esa zona");
            RequestContext.getCurrentInstance().execute("dlg.hide();");
            usuario=new Usuario();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Se guardó correctamente.", ""));

        } else {
            if (selectedUsuarios.size() == 1 && header.equals("Eliminar Usuario")) {
                boton = "true";
                RequestContext.getCurrentInstance().execute("dlg.hide();");

            }
        }

    }

    public void refrescarForma() {
        
        selectedUsuario = new Usuario();
        selectedUsuario.setUsuid(0);
        usuario = new Usuario();
        setSelectedUsuarios(null);
        obtenerRoles = null;
        System.out.println("U setter null");
        boton = "true";
        busqueda = "";
        List<Rol> list = null;
        list = loginBean.Obtenerrol(loginBean.getLogueado().getUsuid());
        String seleccionado=loginBean.getSeleccionado();
        System.out.println(seleccionado+"ÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑ");
        String catalogo="Administración de cuenta de usuario";
        loginBean.TienePermiso(list, seleccionado, catalogo);

        System.out.println("id usuario: " + usuario.getUsuid());
        System.out.println("id usuario: " + usuario.getUsuusuario());
    }
    
    public void filtrado() {
        System.out.println("Si entra<<<<<<<<<<<<<<<");
        listaFiltrada = usuarioBeanHelper.filtrado(busqueda);
        if (listaFiltrada.isEmpty()) {
            listaFiltrada = new ArrayList();
        }
    }
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
    
    public String cabecera(int i) {
        if (i == 1) {
            header = "Agregar Usuario";
            deshabilitar = "false";
        }
        if (i == 2) {
            header = "Eliminar Usuario";
            deshabilitar = "true";
        }
        if (i == 3) {
            header = "Modificar Usuario";
            deshabilitar = "false";
        }
        return header;
    }
    
    public void nuevo() {
        System.out.println("id usuario: "+usuario.getUsuid());
        System.out.println("id usuario: "+usuario.getUsuusuario());
        multiSeleccion = false;
        idUsu = -1;
//        capturaUsuarioBeanHelper.setUsuario(u);
        usuario = new Usuario();
        obtenerRoles = new ArrayList();
        selectedUsuarios = new ArrayList();
        selectedUsuario = new Usuario();
        deshabilitar = "false";
        // logBeanHelp.getUsuDel().saveUsuario(u);
        cabecera(1);
    }

    public String eliminar() {

        System.out.println("id usuario: "+usuario.getUsuid());
        System.out.println("id usuario: "+usuario.getUsuusuario());
        if (selectedUsuarios.size() > 1) {
            multiSeleccion = true;
           usuario=selectedUsuarios.get(0);
        } else {
            multiSeleccion = false;
        }
        
        String result3 = "UsuarioEliminado";
        cabecera(2);
        dialogo = "confirmation.show()";
        setEliminarono(false);
        eliminarono = false;
        setEliminaa(false);
        eliminaa = false;
        setBusqueda("");
        return result3;
    }

    public void modificar() {
        ////Usuario usuario = new Usuario();

        System.out.println("id usuario: "+usuario.getUsuid());
        System.out.println("id usuario: "+usuario.getUsuusuario());
        if (selectedUsuarios.size() > 1) {
            multiSeleccion = true;
            usuario=selectedUsuarios.get(0);
        } else {
            multiSeleccion = false;
        }

        if (selectedUsuarios.size() == 1) {
            usuario = selectedUsuarios.get(0);
            System.out.println(usuario.getUsuid() + "<><><><>>><>>>>>><><>><>>");
            idUsu = usuario.getUsuid();
        }
        cabecera(3);
        dialogo = "confirmation.show()";
        idUsu = usuario.getUsuid();
        List<Usuario> l;
        l = selectedUsuarios;
        setBusqueda("");

    }
    
    

    
    
    

    //Se obtienen los roles del helper, si U no es nulo va a y consulta especificamente del objeto guardad
    //en u pero si u es nulo consulta todos los roles guardados en la base de datos.
    public List<String> getObtenerRoles() {
        usuarioBeanHelper = new UsuarioBeanHelper(usuario);

        if (usuario != null && usuario.getUsuid() != null) {
            obtenerRoles = usuarioBeanHelper.getListarol(usuario);
            return obtenerRoles;
        } else {

            for (Rol roles : usuarioBeanHelper.consultaroles()) {
                obtenerRoles.add(roles.getRoltipo());
            }
            return obtenerRoles;
        }
    }

    ////////////Manejo de tabla
    public String onRowSelect(SelectEvent event) {

        boton = "false";
        usuario = new Usuario();
        usuario = selectedUsuario;
        usuario = (Usuario) event.getObject();
        usuariose = usuario.getUsuusuario();

        System.out.println(usuario.getUsuid() + "______________________-modificar");
        System.out.println(usuario.getUsuid() + "______________________-modificar");

        setUsuariose(usuariose);

        return usuariose;
    }

    public void onRowUnselect(UnselectEvent event) {
        
        if (selectedUsuarios.size() > 0) {
            boton = "false";
            System.out.println("+++++++++++++");
        } else {
            boton = "true";
            System.out.println("------------");
        }
        usuario = new Usuario();
        if(selectedUsuarios.size()==1){
            usuario=selectedUsuarios.get(0);
            System.out.println(" fui igual a 1 ");
            System.out.println(usuario.getUsuusuario() + "______________________-modificar");
        System.out.println(usuario.getUsuid() + "______________________-modificar");
        }
        
    }

    public String onToggleselect(ToggleSelectEvent event) {
        
        if (boton == "false" && selectedUsuarios.size() == 0) {
            boton = "true";
            return boton;
        }
        if (boton == "true" && selectedUsuarios.size() > 0) {
            boton = "false";
            return boton;
        }
        return boton;

    }
    
    ////llenado de tabla con profesor
    public String profDeUsu(int usuProf) {
        Profesor usProfesor = usuarioBeanHelper.getProfDel().profIDUs(usuProf);
        if (usProfesor != null) {
            return usProfesor.getPronumeroEmpleado() + " - " + usProfesor.getPronombre() + " " + usProfesor.getProapellidoPaterno() + " " + usProfesor.getProapellidoMaterno() + ".";

        } else {
            return "No tiene un profesor asignado.";
        }
    }
    ////roles
    public String rolesStrList(Usuario usuRol) {
        String rolsUsuEsp = "";
        System.out.println("El usuario: " + usuRol.getUsuusuario());
        rolsUsuEsp = "";
        List<String> lista = null;
        lista = usuarioBeanHelper.getListarol(usuRol);
        if (lista.isEmpty()) {
            return "No tiene roles asignados.";
        } else {
            for (int i = 0; i < lista.size(); i++) {
                if (i == (lista.size()) - 1) {
                    System.out.println("Profesor: " + usuRol.getUsuusuario() + " Roles: " + lista.get(i) + "<<<<<<<<");
                    rolsUsuEsp += lista.get(i) + ".";
                } else {
                    System.out.println("Profesor: " + usuRol.getUsuusuario() + " Roles: " + lista.get(i) + "<<<<<<<<");
                    rolsUsuEsp += lista.get(i) + ", ";
                }
            }
            return rolsUsuEsp;

        }

    }
    
    public void selectMultipleUsuario() {

        selectedUsuario = usuarioBeanHelper.getUsuarioDelegate().usuUnic(selectedUsuario.getUsuid());
        idUsu = selectedUsuario.getUsuid();
        usuario = selectedUsuario;
        System.out.println("Usuario selected en el select " + usuario.getUsuid());
        

    }
    
    //////Submit
    public void onClickSubmit() {
        String result = "UsuarioGuardado";
        
        HashSet setRols = new HashSet();
        if (deshabilitar.equals("true")) {
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

            if (isEliminarono() == false) {
                RequestContext.getCurrentInstance().execute("confirmdlg.show();");

            }

            if (isEliminarono()) {
                eliminaa = false;
                setEliminarono(false);
                FacesContext context = FacesContext.getCurrentInstance();
                eliminarComfirm();

                if (selectedUsuarios.size() == 0) {
                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    RequestContext.getCurrentInstance().execute("dlg.hide();");

                }

            }

        } //modificar/alta
        else {
            try {

                String mensaj = "";
                if (usuario.getUsuusuario().isEmpty() || usuario.getUsucontrasenia().isEmpty()) {
                    if (usuario.getUsucontrasenia().isEmpty()) {
                        mensaj = "Contraseña";
                    }
                    if (usuario.getUsuusuario().isEmpty()) {
                        mensaj = "Usuario";
                    }
                    if (usuario.getUsuusuario().isEmpty() && usuario.getUsucontrasenia().isEmpty()) {
                        mensaj = "Usuario y contraseña";
                    }

                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Llenar campo " + mensaj, ""));

                } else {

                    setRols = new HashSet();
                    usuarioBeanHelper = new UsuarioBeanHelper();

                    for (String rolesDelUsuario : obtenerRoles) {

                        for (Rol listRolAll : obtenerListaRoles) {
                            if (listRolAll.getRoltipo().equalsIgnoreCase(rolesDelUsuario)) {
                                System.out.println("ID's de los roles que se van a asignar:" + listRolAll.getRolid());
                                setRols.add(listRolAll);
                            }
                        }
                    }

                    if (idUsu != -1) {
                        System.out.println("OnClick se activó");
                        Usuario usMod = usuarioBeanHelper.getUsuarioDelegate().usuUnic(idUsu);
                        System.out.println("NuevoUsuario: " + usMod.getUsuusuario());
                        usMod.setRols(setRols);
                        usMod.setUsuusuario(usuario.getUsuusuario());
                        if (!usuario.getUsucontrasenia().equalsIgnoreCase(usMod.getUsucontrasenia())) {
                            usMod.setUsucontrasenia(DigestUtils.md5Hex(usuario.getUsucontrasenia()));

                        }
                        usuarioBeanHelper.getUsuarioDelegate().saveUsuario(usMod);
                        FacesContext context = FacesContext.getCurrentInstance();
                        
                        
                        context.addMessage(null, new FacesMessage("Se guardó correctamente.", ""));
                        
                        
                        List<Usuario> L;
                        L = selectedUsuarios;
                        System.out.println(usMod.getUsuusuario() + "_______________________________" + usMod.getUsuid());
                        for (int user = 0; user < selectedUsuarios.size(); user++) {
                            if (selectedUsuarios.get(user).getUsuid() == usMod.getUsuid()) {
                                System.out.println("se cumplio la condicion");
                                selectedUsuarios.remove(user);
                                selectedUsuarios.add(usMod);
                            }

                        }

                        context = FacesContext.getCurrentInstance();
                        

                        System.out.println(selectedUsuarios.size() + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                        if (selectedUsuarios.size() == 0) {
                            RequestContext.getCurrentInstance().execute("dlg.hide();");

                        }
                        
                    } else {
                        usuario.setRols(setRols);
                        usuario.setUsucontrasenia(DigestUtils.md5Hex(usuario.getUsucontrasenia()));

                        usuarioBeanHelper.getUsuarioDelegate().saveUsuario(usuario);
                        
                        usuario = new Usuario();
                        FacesContext context = FacesContext.getCurrentInstance();
                        
                        
                        context.addMessage(null, new FacesMessage("Se guardó correctamente.", ""));

                    }
                    idUsu = -1;
                }

            } catch (org.hibernate.AssertionFailure e) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Usuario ya existente");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                usuario = new Usuario();
            }
        }
        listaFiltrada = usuarioBeanHelper.getUsuarioDelegate().getUsuario();

        /////if(selectedUsuarios.size()==1)setSelectedUsuarios(null);
        ////
    }
    
    
    ////////////// Altas Bajas Modificaciones
    
    public void eliminarComfirm() {
        logueado = usuarioBeanHelper.getUsuarioDelegate().usuUnic(loginBean.getLogueado().getUsuid());
        List<Usuario> l = selectedUsuarios;
        System.out.println(l.size() + "tamaño de la lista ____________");
        
        if (l.size() == 1) {
            for (Usuario us : l) {

                usuario =usuarioBeanHelper.getUsuarioDelegate().usuUnic(us.getUsuid());
                idUsu = usuario.getUsuid();
                Usuario usMod = usuarioBeanHelper.getUsuarioDelegate().usuUnic(idUsu);
                System.out.println(usuario.getUsuusuario() + "_______________________");
                System.out.println(usMod.getUsuusuario() + "_______________________");
                if (logueado.getUsuusuario().equals(usMod.getUsuusuario())) {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("No puedes eliminar el usuario que esta logueado.", ""));
                } else {
                    System.out.println("Borrando usuario: " + usMod.getUsuusuario());
                    usuarioBeanHelper.getUsuarioDelegate().eliminarUsuario(usMod);
                    

                    usuario = null; //limpiar la variable cada vez que se modifica
                    usuario = new Usuario();// se crea una vacia para llenar posteriormente y evitar nullpointer

                    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxenestaparte se esta eliminando 1 usuario");
                    RequestContext.getCurrentInstance().execute("dlg.hide();");
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Se eliminó correctamente.", ""));
                    
                }

            }

        } else {
            usuario = usuarioBeanHelper.getUsuarioDelegate().usuUnic(usuario.getUsuid());
            idUsu = usuario.getUsuid();
            Usuario usMod = usuarioBeanHelper.getUsuarioDelegate().usuUnic(idUsu);
            System.out.println("Borrando usuario: " + usMod.getUsuusuario());
            usuarioBeanHelper.getUsuarioDelegate().eliminarUsuario(usMod);
           
            for (int user = 0; user < selectedUsuarios.size(); user++) {
                if (selectedUsuarios.get(user).getUsuid() == usMod.getUsuid()) {
                    selectedUsuarios.remove(user);
                    

                }
               
            }
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
             if(getSelectedUsuarios().size()==1){
                usuario=selectedUsuarios.get(0);
            }else{
            usuario = null; //limpiar la variable cada vez que se modifica
            usuario = new Usuario();// se crea una vacia para llenar posteriormente y evitar nullpointer
             }

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Se eliminó correctamente.", ""));

        }
        String ruta = "inicioP";

        if (selectedUsuarios.size() == 1) {
            boton = "true";
            ////usuario = new Usuario();
            setBoton(boton);
            getBoton();
        }

        ////usuario = new Usuario();

    }
    
    
    
    //////////////get and set
    public Usuario getUsuario() {
        System.out.println(usuario.getUsucontrasenia() + "contraseñaaaa");
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    
    
    public List<Usuario> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Usuario> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    
    public String getUsuariose() {

        return usuariose;
    }

    public void setUsuariose(String usuariose) {
        this.usuariose = usuariose;
    }
    
    
    public void setEliminaa(boolean eliminaa) {
        this.eliminaa = eliminaa;
    }

    ////banderas
    public void banderaeliminar() {
        eliminaa = true;
        onClickSubmit();
    }
    
    
    
    public boolean isEliminarono() {
        if (eliminaa == true) {

            eliminarono = true;
            setEliminarono(true);
        }

        return eliminarono;
    }

    public void setEliminarono(boolean eliminarono) {
        this.eliminarono = eliminarono;
    }
    
    
    
    public String getDialogo() {
        return dialogo;
    }

    public void setDialogo(String dialogo) {
        this.dialogo = dialogo;
    }


    

    public String getDeshabilitar() {
        return deshabilitar;
    }

    public void setDeshabilitar(String deshabilitar) {
        this.deshabilitar = deshabilitar;
    }
    

    public boolean isMultiSeleccion() {
        return multiSeleccion;
    }

    public void setMultiSeleccion(boolean multiSeleccion) {
        this.multiSeleccion = multiSeleccion;
    }
    public void setObtenerRoles(List<String> obtenerRoles) {
        this.obtenerRoles = obtenerRoles;
    }

    public List<Rol> getObtenerListaRoles() {
        obtenerListaRoles = usuarioBeanHelper.getListaRol();
        return obtenerListaRoles;
    }
    
    public String getBoton() {
        return boton;
    }

    public void setBoton(String boton) {
        this.boton = boton;
    }
    
    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public List<Usuario> getSelectedUsuarios() {
        return selectedUsuarios;
    }

    public void setSelectedUsuarios(List<Usuario> selectedUsuarios) {
        this.selectedUsuarios = selectedUsuarios;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
    
    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
}
