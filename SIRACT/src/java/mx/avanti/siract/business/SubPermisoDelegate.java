/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Subpermisos;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author kitto
 */
public class SubPermisoDelegate implements Serializable {
    private List<Subpermisos> permiso;
    private List<Subpermisos> listapermiso;
    public SubPermisoDelegate() {
        permiso = new ArrayList<Subpermisos>();
        permiso = ServiceFacadeLocator.getFacadeSubPermiso().ConsultaGralPermiso();
        ////permiso = ServiceFacadeLocator.getFacadePermiso().ConsultaGralPermiso();
         
    }
    
    public List<Subpermisos> getPermiso() {
        permiso = ServiceFacadeLocator.getFacadeSubPermiso().ConsultaGralPermiso();
        return permiso;
        
    }
    public List<Subpermisos> getPermisoUser(int userid) {
        permiso=ServiceFacadeLocator.getFacadeSubPermiso().ConsultaPermiso(userid);
        
        return permiso;
    }
     public List<Subpermisos> getPermisoID(int rolID,int permID) {
        permiso=ServiceFacadeLocator.getFacadeSubPermiso().ConsultaSubpermisos(rolID,permID);
        return permiso;
    }

    public void setPermiso(List<Subpermisos> permiso) {
        this.permiso = permiso;
    }
    
    public void savePermiso(Subpermisos subpermiso) {
        ServiceFacadeLocator.getFacadeSubPermiso().AgregarPermiso(subpermiso);
        
    }
    public void consultarpermiso(){
        permiso = ServiceFacadeLocator.getFacadeSubPermiso().ConsultaGralPermiso();
    }
}
