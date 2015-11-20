/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Rol;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Manzanas45
 */
public class RolDelegate implements Serializable{
     private List<Rol> rol;
    private List<Rol> listarol;

     
     public RolDelegate() {
        rol = new ArrayList<Rol>();
        rol = ServiceFacadeLocator.getFacadeRol().ConsultaGralRol();
    }
     
     public List<Rol> getRol() {
        rol = ServiceFacadeLocator.getFacadeRol().ConsultaGralRol();
        for(int x=0;x<rol.size();x++){
            
        }
        return rol;
    }

    public void setRol(List<Rol> rol) {
        this.rol = rol;
    }
    
    public void saveRol(Rol rol) {
        ServiceFacadeLocator.getFacadeRol().AgregarRol(rol);
    }
    public void updateRol(Rol rol) {
        ServiceFacadeLocator.getFacadeRol().modificarRol(rol);
    }
    
    public void eliminarRol(Rol rol){ 
        ServiceFacadeLocator.getFacadeRol().eliminarRol(rol);
    }
    
    public List<Rol> getRolUser(int userid) {
        listarol=ServiceFacadeLocator.getInstanceFacadeRol().ConsultaRol(userid);
        return listarol;
    }
    public Rol getRolUnico(int rolid) {
        return ServiceFacadeLocator.getInstanceFacadeRol().getRolUnico(rolid);
    }
    
    
//    public List<Rol> getListaRolfindFromWhere(String de, String usuid, String usuario){
//        listarol=ServiceFacadeLocator.getFacadeRol().ConsultaRol2(de,usuid,usuario);
//        
//        return listarol;
//    }
    
    
}
