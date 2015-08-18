/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.Subpermisos;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author kitto
 */
public class FacadeSubPermiso {
    public void AgregarPermiso(Subpermisos per) {
        Subpermisos result = null;

        if (per.getSperid()!= null) {
            ServiceLocator.getInstanceBaseDAO().setTipo(Subpermisos.class);
            result = (Subpermisos) ServiceLocator.getInstanceBaseDAO().find(per.getSperid());
        }

        if (result != null) {
            per.setSperid(result.getSperid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(per);
        } else {
            ServiceLocator.getInstanceBaseDAO().save(per);
        }
    }

    public List<Subpermisos> ConsultaGralPermiso() {
        List<Subpermisos> listaPermiso = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Subpermisos.class);
        listaPermiso = ServiceLocator.getInstanceBaseDAO().findall("Subpermisos");
        return listaPermiso;
    }
    
    public List<Subpermisos> ConsultaPermiso(int userid) {
        List<Subpermisos> listaP = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Subpermisos.class);
        listaP = ServiceLocator.getInstanceBaseDAO().findFromWhere("rolHasPermisos", "rol.rolid", String.valueOf(userid));// Duda aqui que se manda
        return listaP;
    }
    public List<Subpermisos> ConsultaSubpermisos(int rolID, int permID) {
        List<Subpermisos> listaP = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Subpermisos.class);
        listaP = ServiceLocator.getInstanceBaseDAO().subperPermRol("rolHasPermisos", "permiso.perid", String.valueOf(permID),"rol.rolid", String.valueOf(rolID),"Subpermisos");
//                findFromWhere("rolHasPermisos", "permiso.perid", String.valueOf(permID));// Duda aqui que se manda
        return listaP;
    }
}
