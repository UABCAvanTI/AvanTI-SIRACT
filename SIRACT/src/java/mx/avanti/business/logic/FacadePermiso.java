/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.common.integration.ServiceLocator;
import mx.avanti.siract.business.entity.Permiso;

/**
 *
 * @author Manzanas45
 */
public class FacadePermiso {
    public void AgregarPermiso(Permiso per) {
        Permiso result = null;

        if (per.getPerid()!= null) {
            ServiceLocator.getInstanceBaseDAO().setTipo(Permiso.class);
            result = (Permiso) ServiceLocator.getInstanceBaseDAO().find(per.getPerid());
        }

        if (result != null) {
            per.setPerid(result.getPerid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(per);
        } else {
            ServiceLocator.getInstanceBaseDAO().save(per);
        }
    }

    public List<Permiso> ConsultaGralPermiso() {
        List<Permiso> listaPermiso = null;
         ServiceLocator.getInstanceBaseDAO().setTipo(Permiso.class);
        listaPermiso = ServiceLocator.getInstanceBaseDAO().findall("Permiso");
        return listaPermiso;
    }
    
    public List<Permiso> ConsultaPermiso(int userid) {
        List<Permiso> listaP = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Permiso.class);
        listaP = ServiceLocator.getInstanceBaseDAO().findFromWhereUnic("rolHasPermisos", "rol.rolid", String.valueOf(userid),"Permiso");// Duda aqui que se manda
        return listaP;
    }
}
