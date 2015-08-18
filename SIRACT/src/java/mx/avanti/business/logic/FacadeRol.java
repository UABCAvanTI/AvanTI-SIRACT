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

        if (rol.getRolid()!= null) {
            ServiceLocator.getInstanceBaseDAO().setTipo(Rol.class);
            result = (Rol) ServiceLocator.getInstanceBaseDAO().find(rol.getRolid());
        }

        if (result != null) {
            rol.setRolid(result.getRolid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(rol);
        } else {
            ServiceLocator.getInstanceBaseDAO().save(rol);
        }
    }

    public List<Rol> ConsultaGralRol() {
        List<Rol> listaRol = null;
         ServiceLocator.getInstanceBaseDAO().setTipo(Rol.class);
        listaRol = ServiceLocator.getInstanceBaseDAO().findAll();
        return listaRol;
    }
    
    public void eliminarRol(Rol rol){
        if(rol!=null && rol.getRolid()!=null){
        System.out.println("RolFacade>>>>>>>>>>>>>>>>>>>>>>>"+rol.getRolid());
        ServiceLocator.getInstanceBaseDAO().deleteWhere("Rol","ROLid", String.valueOf(rol.getRolid()));
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
        return (Rol)ServiceLocator.getInstanceBaseDAO().find(rolid);
    }
  

//    public List<Rol> ConsultaRol2(String userid, String usuario, String usuid) {
//        
//        List<Rol> listaRU = null;
//        ServiceLocator.getInstanceBaseDAO().setTipo(Rol.class);
//        listaRU = ServiceLocator.getInstanceBaseDAO().findFromWhere("usuarios", "usuid", String.valueOf(userid));// Duda aqui que se manda
//        return listaRU;
//    }
}
