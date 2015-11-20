/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import mx.avanti.siract.business.PermisoDelegate;
import mx.avanti.siract.business.ProfesorDelegate;
import mx.avanti.siract.business.RolDelegate;
import mx.avanti.siract.business.SubPermisoDelegate;
import mx.avanti.siract.business.UsuarioDelegate;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Rol;
import mx.avanti.siract.business.entity.Usuario;

/**
 *
 * @author balta
 */
public class UsuarioBeanHelper implements Serializable{
    private UsuarioDelegate usuarioDelegate=new UsuarioDelegate();
    ////private Usuario usuario;
    private Usuario seleccionUsuario;
    private List<Usuario> listaFiltrada;
    private List<Usuario> filtroUsuario;
    ////private List<Rol> listaRol = new ArrayList();
    ////private RolDelegate rolDelegate;
  ////  private List<String> listarol;
   //// private ProfesorDelegate profDel = new ProfesorDelegate();
     List<Rol> listaRol = new ArrayList();
    Rol rol;
    Usuario usuario;
    RolDelegate rolDelegate;
    private List<String> listarol;
    List<Rol> rolesID = new ArrayList();
    UsuarioDelegate usuDel = new UsuarioDelegate();
    ProfesorDelegate profDel = new ProfesorDelegate();
    PermisoDelegate permisoDelegete = new PermisoDelegate();
    SubPermisoDelegate sp=new SubPermisoDelegate();
    
    public UsuarioBeanHelper(){
        init();
    }
    public UsuarioBeanHelper(Usuario u){
        init();
        usuario = u;

    }
    public void init() {
        rolDelegate = new RolDelegate();
        listarol = new ArrayList<String>();
    }

    public UsuarioDelegate getUsuarioDelegate() {
        return usuarioDelegate;
    }

//    public void setUsuarioDelegate(UsuarioDelegate usuarioDelegate) {
//        this.usuarioDelegate = usuarioDelegate;
//    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getSeleccionUsuario() {
        return seleccionUsuario;
    }

    public void setSeleccionUsuario(Usuario seleccionUsuario) {
        this.seleccionUsuario = seleccionUsuario;
    }
    
    public void seleccionar(){
        System.out.println("seleccion: "+seleccionUsuario.getUsuid());
    }
    
    public List<Usuario> filtrado(String busqueda) {
        busqueda = busqueda.toLowerCase();
       listaFiltrada = usuarioDelegate.getUsuario();

        if (busqueda.trim().length() > 0) {
            listaFiltrada.clear();
            for (Usuario us : usuarioDelegate.getUsuario()) {
                String usuarioT=us.getUsuusuario().toLowerCase();
                if (usuarioT.startsWith(busqueda)) {
                    if (!listaFiltrada.contains(us)) {
                        listaFiltrada.add(us);
                    }
                }
                for (Rol rolUsB : rolDelegate.getRolUser(us.getUsuid())) {
                    String rolTipo = rolUsB.getRoltipo().toLowerCase();
                    if (rolTipo.startsWith(busqueda)) {
//                        System.out.println("Rol para comparar" + rolUsB.getRoltipo());
                        if (!listaFiltrada.contains(us)) {
                            listaFiltrada.add(us);
                        }
                    }
                }
                Profesor prof = profDel.profIDUs(us.getUsuid());
                String[] nombreProf;
                String nombProf;
                if (prof != null) {
                    nombProf = prof.getPronombre() + " " + prof.getProapellidoPaterno() + " " + prof.getProapellidoMaterno();
                    nombProf = nombProf.toLowerCase();
                    nombreProf = nombProf.split(" ");
                    for (int i = 0; i < nombreProf.length; i++) {
                        if (nombreProf[i].startsWith(busqueda)) {
                            if (!listaFiltrada.contains(us)) {
                                listaFiltrada.add(us);
                            }
                        } else {
                            if (prof.getPronombre().startsWith(busqueda)) {
                                if (!listaFiltrada.contains(us)) {
                                    listaFiltrada.add(us);
                                }
                            }
                        }
                    }
                    if (String.valueOf(prof.getPronumeroEmpleado()).startsWith(busqueda)) {
                        if (!listaFiltrada.contains(us)) {
                            listaFiltrada.add(us);
                        }
                    }
                }

            }

        }
        return listaFiltrada;
    }
    
    public ProfesorDelegate getProfDel() {
        return profDel;
    }

    public void setProfDel(ProfesorDelegate profDel) {
        this.profDel = profDel;
    }
    
   
    public List<Rol> getListaRol() {
        listaRol = rolDelegate.getRol();
        //// getListaRolfindFromWhere("usuarios", "usuid", "1");
        return listaRol;
    }

    public void setListaRol(List<Rol> listaRol) {
        this.listaRol = listaRol;
    }
///////////////////// Copia de métodos de CapturaUsuarioBeanHelper/////////////////
//consulta los roles especificos para cada usuario

    public List<String> getListarol(Usuario u) {
        List<Rol> recibe = null;//Creacion de recibe 

        listarol = new ArrayList();
        if (u.getUsuid() > 0) {

            recibe = rolDelegate.getRolUser(u.getUsuid());
            for (Rol usu : recibe) {

                listarol.add(usu.getRoltipo());
            }

        } else {
            recibe = consultaroles();
            for (Rol usu : recibe) {

            }
        }
        return listarol;
    }

    public void setListarol(List<String> listarol) {
        this.listarol = listarol;
    }

    public Usuario GuardarUsuario() {
        List<Rol> recibe;//Creacion de recibe 
        recibe = consultaroles();//Tomamos el valor que contiene rols2
        usuario.setRols(new HashSet(recibe));
        return usuario;// y retornamos el objeto preparado para guardar

    }
//Todos los roles almacenados en la base de datos (?)

    public List<Rol> consultaroles() {//Ciclo para consultar los roles
        List<Rol> rols;//creacion de lista rols
        List<Rol> rols2 = new ArrayList<Rol>();//creacion de lista rols2
        rols = rolDelegate.getRol();//Consulta de roles en la tabla rol
        for (int i = 0; i < listarol.size(); i++) {  //recorriendo el tamaño para obtener el dato de rols en cada posicion      
            for (int x = 0; x < rols.size(); x++) {// recorriendo el tamaño de rols
                if (rols.get(x).getRoltipo().equals(listarol.get(i))) {//comparacion para saber cuales roles son los que se elijen para agregar 
                    rols2.add(rols.get(x));//se prepara el objeto rols2 con la informacion de los datos que se mandaron
                }
            }
        }
        return rols2;//regresado rols2
    }

    
    
}
