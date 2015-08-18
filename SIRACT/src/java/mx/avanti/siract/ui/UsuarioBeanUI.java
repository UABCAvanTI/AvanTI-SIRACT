/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.avanti.siract.application.helper.UsuarioBeanHelper;
import mx.avanti.siract.business.entity.Usuario;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Manzanas45
 */
@ManagedBean
@ViewScoped
public class UsuarioBeanUI implements Serializable{
    private UsuarioBeanHelper usuarioBeanHelper = null;
    
    public UsuarioBeanUI(){
        init();
    }
    
    private void init(){
        usuarioBeanHelper = new UsuarioBeanHelper();
    }

    public UsuarioBeanHelper getUsuarioBeanHelper() {
        return usuarioBeanHelper;
    }

//    public void setUsuarioBeanHelper(CapturaUsuarioBeanHelper usuarioBeanHelper) {
//        this.usuarioBeanHelper = usuarioBeanHelper;
//    }
    
    public void nuevo(){
        usuarioBeanHelper.setUsuario(new Usuario());
    }
    
    public void modificar(){
        usuarioBeanHelper.setUsuario(usuarioBeanHelper.getSeleccionUsuario());
    }
    
    public void eliminar(){
        usuarioBeanHelper.getUsuarioDelegate().eliminarUsuario(usuarioBeanHelper.getSeleccionUsuario());
    }
    
    public String onClickSubmit(){
        String resultado="";
        usuarioBeanHelper.getUsuarioDelegate().agregarUSuario(usuarioBeanHelper.getUsuario());
        return resultado;
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
    
    public void eliminarUsuario(Usuario usuario){
        ServiceFacadeLocator.getFacadeUsuario().eliminarUsuario(usuario);
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
    
    
}
