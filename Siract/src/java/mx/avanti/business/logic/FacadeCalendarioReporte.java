/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.Calendarioreporte;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Owner
 */
public class FacadeCalendarioReporte {
    public void saveCalendarioReporte(Calendarioreporte calendarioreporte){
        Calendarioreporte result = null;
        
        if(calendarioreporte == null){
            System.out.println("Puesto nulo");
        }

        if(calendarioreporte.getCreid() != null){
           ServiceLocator.getInstanceBaseDAO().setTipo(Calendarioreporte.class);
            result = (Calendarioreporte) ServiceLocator.getInstanceBaseDAO().find(calendarioreporte.getCreid());
        }
        
        if(result != null){
            calendarioreporte.setCreid(result.getCreid());
            ServiceLocator.getInstanceBaseDAO().setTipo(Calendarioreporte.class);
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(calendarioreporte);
        }else{
            ServiceLocator.getInstanceBaseDAO().setTipo(Calendarioreporte.class);
            ServiceLocator.getInstanceBaseDAO().save(calendarioreporte);
        }
    }
    public List<Calendarioreporte> findAll() {
        List<Calendarioreporte> listaCalendario = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Calendarioreporte.class);
        listaCalendario = ServiceLocator.getInstanceBaseDAO().findAll();
        return listaCalendario;
    }
        public Calendarioreporte find(int id){
        ServiceLocator.getInstanceBaseDAO().setTipo(Calendarioreporte.class);
        Calendarioreporte c= (Calendarioreporte) ServiceLocator.getInstanceBaseDAO().find(id);
        return c;
    }
    public void eliminarCalendarioReporte(Calendarioreporte calendarioreporte){
        ServiceLocator.getInstanceBaseDAO().setTipo(Calendarioreporte.class);
        ServiceLocator.getInstanceBaseDAO().delete(calendarioreporte);
    }
    
    public Calendarioreporte calendarioreporteFechaActual(){
         ServiceLocator.getInstanceBaseDAO().setTipo(Calendarioreporte.class);
        return (Calendarioreporte) ServiceLocator.getInstanceBaseDAO().reporteFechaActual();
    }
    
        public Calendarioreporte siguienteReporte(){
        return (Calendarioreporte) ServiceLocator.getInstanceBaseDAO().siguienteReporte();
    }
    
}
