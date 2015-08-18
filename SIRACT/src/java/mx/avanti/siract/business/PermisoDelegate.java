/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Permiso;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Manzanas45
 */
public class PermisoDelegate implements Serializable{   
    private List<Permiso> permiso;
    private List<Permiso> listapermiso;
    public PermisoDelegate() {
        permiso = new ArrayList<Permiso>();
        permiso = ServiceFacadeLocator.getFacadePermiso().ConsultaGralPermiso();
         
    }
    
    public List<Permiso> getPermiso() {
        permiso = ServiceFacadeLocator.getFacadePermiso().ConsultaGralPermiso();
        return permiso;
        
    }
    public List<Permiso> getPermisoUser(int userid) {
        permiso=ServiceFacadeLocator.getFacadePermiso().ConsultaPermiso(userid);
        return permiso;
    }

    public void setPermiso(List<Permiso> permiso) {
        this.permiso = permiso;
    }
    
    public void savePermiso(Permiso permiso) {
        ServiceFacadeLocator.getFacadePermiso().AgregarPermiso(permiso);
    }
    public void consultarpermiso(){
        permiso = ServiceFacadeLocator.getFacadePermiso().ConsultaGralPermiso();
    }
//    public List<Permiso> getPermisoRol(int rolID){
//        permiso=ServiceFacadeLocator.getFacadePermiso().ConsultaPermiso(rolID);
//    }
}
