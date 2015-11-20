/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import mx.avanti.siract.business.entity.RolHasPermiso;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Usagi
 */
public class RolHasPermisoDelegate {
     public void saveRolHasPermiso(RolHasPermiso rolHasPermiso) {
        ServiceFacadeLocator.getInstanceFacadeRolHasPermiso().AgregarRol(rolHasPermiso);
    }
         
     public void deleteRolHasPermiso(String clase, String campo, String criterio){
          ServiceFacadeLocator.getInstanceFacadeRolHasPermiso().deleteRolHasPermiso(clase, campo, criterio);
     } 
}
