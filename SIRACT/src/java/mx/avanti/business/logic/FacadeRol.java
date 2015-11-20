/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.common.integration.ServiceLocator;
import mx.avanti.siract.business.entity.Rol;

/**
 *
 * @author Manzanas45
 */
public class FacadeRol {

    public void AgregarRol(Rol rol) {
        Rol result = null;

        if (rol.getRolid() != null) {
            ServiceLocator.getInstanceBaseDAO().setTipo(Rol.class);
            result = (Rol) ServiceLocator.getInstanceBaseDAO().find(rol.getRolid());
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + result.getRoltipo());
        }

        if (result != null) {
//            rol.setRolid(result.getRolid());
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>..Voy a modificar: " + rol.getRoltipo());
        } else {
           ServiceLocator.getInstanceBaseDAO().save(rol);
        }
    }

    public void modificarRol(Rol rol) {
        ServiceLocator.getInstanceBaseDAO().update(rol.getRolid(), rol.getRoltipo(), rol.getRolprioridad());
    }

    public List<Rol> ConsultaGralRol() {
        List<Rol> listaRol = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Rol.class);
        listaRol = ServiceLocator.getInstanceBaseDAO().findAll();
        return listaRol;
    }

    public void eliminarRol(Rol rol) {
        if (rol != null && rol.getRolid() != null) {
            System.out.println("RolFacade>>>>>>>>>>>>>>>>>>>>>>>" + rol.getRolid());
            ServiceLocator.getInstanceBaseDAO().delete(rol);
        }
    }

    public List<Rol> ConsultaRol(int userid) {
        List<Rol> listaRU = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Rol.class);
        listaRU = ServiceLocator.getInstanceBaseDAO().findFromWhere("usuarios", "usuid", String.valueOf(userid));// Duda aqui que se manda
        return listaRU;
    }

    public Rol getRolUnico(int rolid) {
        ServiceLocator.getInstanceBaseDAO().setTipo(Rol.class);
        return (Rol) ServiceLocator.getInstanceBaseDAO().find(rolid);
    }

//    public List<Rol> ConsultaRol2(String userid, String usuario, String usuid) {
//        
//        List<Rol> listaRU = null;
//        ServiceLocator.getInstanceBaseDAO().setTipo(Rol.class);
//        listaRU = ServiceLocator.getInstanceBaseDAO().findFromWhere("usuarios", "usuid", String.valueOf(userid));// Duda aqui que se manda
//        return listaRU;
//    }
}
