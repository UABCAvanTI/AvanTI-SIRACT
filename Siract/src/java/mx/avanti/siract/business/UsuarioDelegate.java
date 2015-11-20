/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Rol;
import mx.avanti.siract.business.entity.Usuario;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author AvanTI-2014
 */
public class UsuarioDelegate implements Serializable{

    private List<Usuario> usuario;
    private List<Rol> rolesPorUsuario;
    private Usuario usuarioUnico;
    

    
    public UsuarioDelegate() {
        usuario = new ArrayList<Usuario>();
        usuario = ServiceFacadeLocator.getFacadeUsuario().ConsultaGralUsuario();
    }   

    public List<Usuario> getUsuario() {
        usuario = ServiceFacadeLocator.getFacadeUsuario().ConsultaGralUsuario();
        return usuario;
    }
    
    public List<Rol> getRolesPorUsuario(){
        List<Rol> roles; 
        for (Usuario usu : usuario) {
               roles = ServiceFacadeLocator.getFacadeRol().ConsultaRol(usu.getUsuid());
               for(Rol rol:roles){
                   rolesPorUsuario.add(rol);
               }
               
            }
        return rolesPorUsuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }

    public void saveUsuario(Usuario usuario) {
        
        //ServiceFacadeLocator.getFacadeUsuario().AgregarUsuario(usuario);
        ServiceFacadeLocator.getFacadeUsuario().AgregarUsuario(usuario);
    }
    
    public void eliminarUsuario(Usuario usuario){
        ServiceFacadeLocator.getFacadeUsuario().eliminarUsuario(usuario);
    }
    
    public Usuario usuUnic(int usID){
        return ServiceFacadeLocator.getFacadeUsuario().usuUnic(usID);
    }
    
    public Usuario login(Usuario usuario){
        return ServiceFacadeLocator.getFacadeUsuario().login(usuario);
    }
    
    
    
     private List<Usuario> listaUsuario;
    private Usuario seleccionUsuario;

    public List<Usuario> getListaUsuario() {
        listaUsuario = ServiceFacadeLocator.getFacadeUsuario().consultaUsuario();
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
    
    public void agregarUSuario(Usuario usuario){
        ServiceFacadeLocator.getFacadeUsuario().agregarUsuario(usuario);
    }
    
    public List<String> getUsuarioString(){
        return ServiceFacadeLocator.getFacadeUsuario().getListaUsuarios();
    }

    public Usuario getSeleccionUsuario() {
        return seleccionUsuario;
    }

    public void setSeleccionUsuario(Usuario seleccionUsuario) {
        this.seleccionUsuario = seleccionUsuario;
    }
    
    public void seleccionar(){
        System.out.println("value: "+seleccionUsuario.getUsuid());
    }
    
    public Usuario findUsuarioById(int id){
        return ServiceFacadeLocator.getFacadeUsuario().getUsuario(id);
    }

  
    public List<Usuario> findUsuarioByRol(int rolid){
        return ServiceFacadeLocator.getFacadeUsuario().getUsuariobyRol(rolid);
    }


}
