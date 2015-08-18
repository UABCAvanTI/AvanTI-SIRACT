/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;


import java.util.List;
import mx.avanti.siract.business.entity.Alerta;

import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Owner
 */
public class FacadeAlerta {

    public void saveAlerta(Alerta alerta){
        Alerta result = null;

        //
        //ANTES ERA NULL, LO CAMBIE A CERO POR QUE MARCABA UN ERROR
        //
        if(alerta.getAleid() != 0){
           ServiceLocator.getInstanceBaseDAO().setTipo(Alerta.class);
           
            result = (Alerta) ServiceLocator.getInstanceBaseDAO().find(alerta.getAleid());
        }
        
        if(result != null){
            alerta.setAleid(result.getAleid());
            ServiceLocator.getInstanceBaseDAO().setTipo(Alerta.class);
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(alerta);
        }else{
            ServiceLocator.getInstanceBaseDAO().setTipo(Alerta.class);
            ServiceLocator.getInstanceBaseDAO().save(alerta);
        }
    }
    public List<Alerta> findAll() {
        List<Alerta> listaAlerta = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Alerta.class);
        listaAlerta = ServiceLocator.getInstanceBaseDAO().findAll();
        return listaAlerta;
    }
    
    
    
//     public void saveAlerta(Alerta alerta){
//        Alerta result=null;
//
//        if (alerta.getAleid()!=0){
//            ServiceLocator.getInstanceBaseDAO().setTipo(Alerta.class);
//            result=(Alerta) ServiceLocator.getInstanceBaseDAO().find(alerta.getAleid());
//        }
//        if (result!=null){
//            alerta.setAleid(result.getAleid());
//            ServiceLocator.getInstanceBaseDAO().setTipo(Alerta.class);
//            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(alerta);
//        } else{
//            ServiceLocator.getInstanceBaseDAO().setTipo(Alerta.class);
//            ServiceLocator.getInstanceBaseDAO().save(alerta);
//        }
//    }
//    public List<Alerta> findAll(){
//        List<Alerta> listaAlerta=null;
//        ServiceLocator.getInstanceBaseDAO().setTipo(Alerta.class);
//        listaAlerta=ServiceLocator.getInstanceBaseDAO().findAll();
//        return listaAlerta;
//    }
    public Alerta find(String tipo){
        ServiceLocator.getInstanceBaseDAO().setTipo(Alerta.class);
        Alerta alerta=(Alerta) ServiceLocator.getInstanceBaseDAO().findAlerta(tipo);
        return alerta;
    }
    //Juan Carlos Fernández
    public Alerta find(int id){
        ServiceLocator.getInstanceBaseDAO().setTipo(Alerta.class);
        Alerta ale = (Alerta) ServiceLocator.getInstanceBaseDAO().find(id);
        return ale;
    }
    
    
} 
