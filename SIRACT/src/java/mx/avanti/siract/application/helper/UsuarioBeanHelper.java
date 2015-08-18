/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.List;
import mx.avanti.siract.business.UsuarioDelegate;
import mx.avanti.siract.business.entity.Usuario;

/**
 *
 * @author balta
 */
public class UsuarioBeanHelper implements Serializable{
    private UsuarioDelegate usuarioDelegate;
    private Usuario usuario;
    private Usuario seleccionUsuario;
    private List<Usuario> filtroUsuario;
    
    public UsuarioBeanHelper(){
        try{
            this.usuarioDelegate = new UsuarioDelegate();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        usuario = new Usuario();
        seleccionUsuario = new Usuario();
//        usuarioDelegate = new UsuarioDelegate();
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
}
