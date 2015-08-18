/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.Cicloescolar;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Manuel 
 */
public class FacadeCicloEscolar {
      public void agregarCicloEscolar(Cicloescolar cicloEscolar){
        Cicloescolar resultado = null;
        if(cicloEscolar.getCesid()!= null){
            ServiceLocator.getInstanceBaseDAO().setTipo(Cicloescolar.class);
            resultado = (Cicloescolar) ServiceLocator.getInstanceBaseDAO().find(cicloEscolar.getCesid());
        }
        if(resultado != null){
            cicloEscolar.setCesid(resultado.getCesid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(cicloEscolar);
        } else{
            ServiceLocator.getInstanceBaseDAO().save(cicloEscolar);
        }
    }
    
    public List<Cicloescolar> consultaCicloescolar(){
        List<Cicloescolar> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Cicloescolar.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
        return resultado;
    }
    
    public void eliminarCicloEscolar(Cicloescolar cicloEscolar ){
        ServiceLocator.getInstanceBaseDAO().delete(cicloEscolar);
    }
    
    public Cicloescolar cicloEscolarActual(){
        ServiceLocator.getInstanceBaseDAO().setTipo(Cicloescolar.class);
        return ((Cicloescolar)ServiceLocator.getInstanceBaseDAO().cicloEscolarActual());
    }
     public Cicloescolar find(int id){
        ServiceLocator.getInstanceBaseDAO().setTipo(Cicloescolar.class);
        Cicloescolar c= (Cicloescolar) ServiceLocator.getInstanceBaseDAO().find(id);
        return c;
    }
     
    public void saveCicloEscolar(Cicloescolar cicloescolar){
        Cicloescolar result = null;

        if(cicloescolar.getCesid() != null){
            ServiceLocator.getInstanceBaseDAO().setTipo(Cicloescolar.class);
            result = (Cicloescolar) ServiceLocator.getInstanceBaseDAO().find(cicloescolar.getCesid());
        }
        
        if(result != null){
            cicloescolar.setCesid(result.getCesid());
                    ServiceLocator.getInstanceBaseDAO().setTipo(Cicloescolar.class);
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(cicloescolar);
        }else{
                    ServiceLocator.getInstanceBaseDAO().setTipo(Cicloescolar.class);
            ServiceLocator.getInstanceBaseDAO().save(cicloescolar);
        }
    }
    public List<Cicloescolar> findAll() {
        List<Cicloescolar> listaCiclo = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Cicloescolar.class);
        listaCiclo = ServiceLocator.getInstanceBaseDAO().findAll();
        return listaCiclo;
    }
    
        public Cicloescolar getCicloEscolar(int idCicloEscolar){
        ServiceLocator.getInstanceBaseDAO().setTipo(Cicloescolar.class);
        return (Cicloescolar)ServiceLocator.getInstanceBaseDAO().find(idCicloEscolar);
    }

     
}
