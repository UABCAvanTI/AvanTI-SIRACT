/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import mx.avanti.siract.business.entity.RolHasPermiso;
import mx.avanti.siract.business.entity.RolHasPermisoId;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Usagi
 */
public class FacadeRolHasPermiso {

    public void AgregarRol(RolHasPermiso rolHasPermiso) {
        RolHasPermiso result = null;
    

        if (rolHasPermiso.getId() != null) {
            ServiceLocator.getInstanceBaseDAO().setTipo(RolHasPermiso.class);
                        ServiceLocator.getInstanceBaseDAO().saveOrUpdate(rolHasPermiso);

//            result = (RolHasPermiso) ServiceLocator.getInstanceBaseDAO().find(rolHasPermiso.getId().getSubpermisosSperid());

        }

        
        
    }
    
         
     public void deleteRolHasPermiso(String clase, String campo, String criterio){
         ServiceLocator.getInstanceBaseDAO().deleteWhere(clase, campo, criterio); 
     }
}
