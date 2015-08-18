/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;


import java.util.List;
import mx.avanti.siract.business.entity.Mensaje;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Owner
 */
public class FacadeMensaje {
    
    public void saveMensaje(Mensaje mensaje){
        Mensaje result = null;

        
        
        //
        //ANTES ERA NULL, LO CAMBIE A CERO POR QUE MARCABA UN ERROR
        //
        if(mensaje.getMenid() != 0){
        ServiceLocator.getInstanceBaseDAO().setTipo(Mensaje.class);
            result = (Mensaje) ServiceLocator.getInstanceBaseDAO().find(mensaje.getMenid());
        }
        
        if(result != null){
            mensaje.setMenid(result.getMenid());
           ServiceLocator.getInstanceBaseDAO().setTipo(Mensaje.class);
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(mensaje);
        }else{
         ServiceLocator.getInstanceBaseDAO().setTipo(Mensaje.class);
            ServiceLocator.getInstanceBaseDAO().save(mensaje);
        }
    }
    public List<Mensaje> findAll() {
        List<Mensaje> listaMensaje = null;
     ServiceLocator.getInstanceBaseDAO().setTipo(Mensaje.class);
        listaMensaje = ServiceLocator.getInstanceBaseDAO().findAll();
        return listaMensaje;
    }
}
