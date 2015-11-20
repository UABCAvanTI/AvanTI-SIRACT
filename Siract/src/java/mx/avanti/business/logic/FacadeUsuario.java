/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mx.avanti.siract.business.RolDelegate;
import mx.avanti.siract.common.integration.ServiceLocator;
import mx.avanti.siract.business.entity.Usuario;

/**
 *
 * @author Javier Ivan Villagomez Martinez, Cesar Favela
 */
public class FacadeUsuario {

    RolDelegate roldelegate;

    public void AgregarUsuario(Usuario usuario) {
        Usuario result = null;
//        t, contra;
//        contra = usuario.getUsucontrasenia();
//        
//        usuario.setUsucontrasenia(contraEncrypt);

        /*List<Rol> listarol=new ArrayList<Rol>();
         List<Rol> rols=new ArrayList<Rol>();
         List<Rol> rols2=new ArrayList<Rol>();
         List<Rol> recive=new ArrayList<Rol>();
        
        
         System.out.println(usuario.getUsuusuario());
         System.out.println(usuario.getUsucontrasenia());
         for (int x=0;x<usuario.getRols().size();x++) {
         System.out.println(usuario.getRols().size());
         }
        
        
         listarol = (List<Rol>) usuario.getRols();
         rols=roldelegate.getRol();
         for(int x=0;x<rols.size();x++){
         if(rols.get(x).getRolid()==listarol.get(x).getRolid()){
         rols2.add(rols.get(x));
         }
         }
       
         usuario.setUsuusuario(usuario.getUsuusuario());
         usuario.setUsucontrasenia(contra);
        
         recive=rols2;
         for(int x=0;x<recive.size();x++){
         usuario.getRols().add(recive.get(x));
         }*/
        /*------------------Funciones creadas para la insercion en la tabla rol_tiene_usuario--------------------------------------------------------------*/
        /* List<Rol> listaroles = null;
         ServiceLocator.getInstanceBaseDAO().setTipo(Rol.class);
         listaroles = ServiceLocator.getInstanceBaseDAO().findall();
         List<Rol> listaroles2=new ArrayList<Rol>();
         listaroles2.add(listaroles.get(0));
         listaroles2.add(listaroles.get(1));
         Rol rol1=listaroles.get(0);
         Rol rol2=listaroles.get(1);
         System.out.println(rol1.getRoltipo());
         System.out.println(rol2.getRoltipo());*/
        /*Set<Rol> rols = new HashSet<Rol>(0);
         List<Rol> listaroles2 = (List<Rol>) usuario.getRols();
         for (Iterator<Rol> it = listaroles2.iterator(); it.hasNext();) {
         Rol rol = it.next();
         rols.add(rol); 
         System.out.println(rol.getRoltipo());
         }
         usuario.setRols(rols);  
         ServiceLocator.getInstanceBaseDAO().setTipo(Rol.class);*/
            //listaroles2 = ServiceLocator.getInstanceBaseDAO().findall();
        //listaroles2.listIterator();  
        /*------------------------------------------------------------------------------------------------------------------------------------------------*/
        if (usuario.getUsuid() != null) {

            ServiceLocator.getInstanceBaseDAO().setTipo(Usuario.class);
            result = (Usuario) ServiceLocator.getInstanceBaseDAO().find(usuario.getUsuid());
        }

        if (result != null) {
            usuario.setUsuid(result.getUsuid());
            ServiceLocator.getInstanceBaseDAO().setTipo(Usuario.class);
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(usuario);
        } else {

            ServiceLocator.getInstanceBaseDAO().setTipo(Usuario.class);
            ServiceLocator.getInstanceBaseDAO().save(usuario);

        }
    }

    public List<Usuario> ConsultaGralUsuario() {
        List<Usuario> listaUsuario = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Usuario.class);
        listaUsuario = ServiceLocator.getInstanceBaseDAO().findAll();
        return listaUsuario;
    }

    public void eliminarUsuario(Usuario usuario) {
        ServiceLocator.getInstanceBaseDAO().delete(usuario);
    }

    public Usuario usuUnic(int usID) {
        Usuario us = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Usuario.class);
        us = (Usuario)ServiceLocator.getInstanceBaseDAO().find(usID); 
        return us;
    }
    
    public Usuario login(Usuario usuario){
        return ServiceLocator.getInstanceBaseDAO().login(usuario);
    }
    
     public Usuario getUsuario(int id){
        ServiceLocator.getInstanceBaseDAO().setTipo(Usuario.class);
        return (Usuario) ServiceLocator.getInstanceBaseDAO().find(id);
    }
     
        public List<Usuario> consultaUsuario(){
        List<Usuario> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Usuario.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
        return resultado;
    }

        
        
        
    public void agregarUsuario(Usuario usuario){
        Usuario resultado = null;
        if(usuario == null){
            
        }
        if(usuario.getUsuid() != null){
            ServiceLocator.getInstanceBaseDAO().setTipo(Usuario.class);
            resultado = (Usuario) ServiceLocator.getInstanceBaseDAO().find(usuario.getUsuid());
        }
        if(resultado != null){
            usuario.setUsuid(resultado.getUsuid());
            ServiceLocator.getInstanceBaseDAO().setTipo(Usuario.class);
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(usuario);
        } else{
            ServiceLocator.getInstanceBaseDAO().setTipo(Usuario.class);
            ServiceLocator.getInstanceBaseDAO().save(usuario);
        }
    }
    
    public List<String> getListaUsuarios(){
        List<Usuario> list = consultaUsuario();
        List<String> resultado = new ArrayList();
        for(Iterator<Usuario> it = list.iterator(); it.hasNext();){
          Usuario usuario = it.next();
          resultado.add(usuario.getUsuusuario());
        }
        return resultado;
    }           
    
    //paco
    public List<Usuario> getUsuariobyRol(int rolid){
        List<Usuario> listaUsuario = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Usuario.class);
        listaUsuario = ServiceLocator.getInstanceBaseDAO().findFromWhere("rols","rolid", String.valueOf(rolid));
                
        return listaUsuario;
    }
    
}
