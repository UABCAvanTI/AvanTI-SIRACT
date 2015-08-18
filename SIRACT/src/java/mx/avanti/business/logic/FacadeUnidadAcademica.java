/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.Unidadacademica;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Manuel Papa
 */
public class FacadeUnidadAcademica {
    
    
    
    
    
    
    
    public List<Unidadacademica> findAll(){
        List<Unidadacademica> result = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Unidadacademica.class);
        result = ServiceLocator.getInstanceUnidadacademica().findAllUnidadacademicas();
        return result;
        
    }
      public void agregarUnidadAcademica(Unidadacademica unidadAcademica){
        Unidadacademica resultado = null;
         ServiceLocator.getInstanceBaseDAO().setTipo(Unidadacademica.class);
        if(unidadAcademica.getUacid()!= null){
            resultado = (Unidadacademica) ServiceLocator.getInstanceBaseDAO().find(unidadAcademica.getUacid());
        }
        if(resultado != null){
            unidadAcademica.setUacid(resultado.getUacid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(unidadAcademica);
        } else{
            ServiceLocator.getInstanceBaseDAO().save(unidadAcademica);
        }
    }
    
    public List<Unidadacademica> consultaUnidadAcademica(){
        List<Unidadacademica> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadacademica.class);
        resultado = ServiceLocator.
                getInstanceBaseDAO().findAll();
        return resultado;
    }
    
     public void eliminarUnidadAcademica(Unidadacademica unidadAcademica){
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadacademica.class);
        ServiceLocator.getInstanceBaseDAO().delete(unidadAcademica);
    }
        public Unidadacademica getUnidadAcademica(int id){
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadacademica.class);
        Unidadacademica obj =(Unidadacademica) ServiceLocator.getInstanceBaseDAO().find(id);
        return obj;
    }
     
    public List<Unidadacademica> consultaProfUAC(int uacid){
        List<Unidadacademica> listaUAC = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadacademica.class);
        listaUAC = ServiceLocator.getInstanceBaseDAO().findFromWhere("profesors", "proid","'"+ String.valueOf(uacid)+"'");
        return listaUAC;
    }        
    
    public List<Unidadacademica> findUAByUsuario(int usuaroId){
        List<Unidadacademica> listaUAC = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadacademica.class);
        listaUAC = ServiceLocator.getInstanceBaseDAO().findFromWhere("profesors", "usuario.usuid",String.valueOf(usuaroId));
        return listaUAC;
    }   
    
    //PARTE OMITIDA DESPUES DE INTEGRAR CATALOGOPROFESOR
    
    
//    
//    public List<Unidadacademica> findAll(){
//        List<Unidadacademica> result = null;
//        result = ServiceLocator.getInstanceUnidadacademica().findAllUnidadacademicas();
//        return result;
//        
//    }
//      public void agregarUnidadAcademica(Unidadacademica unidadAcademica){
//        Unidadacademica resultado = null;
//        if(unidadAcademica.getUacid()!= null){
//            ServiceLocator.getInstanceBaseDAO().setTipo(Unidadacademica.class);
//            resultado = (Unidadacademica) ServiceLocator.getInstanceBaseDAO().find(unidadAcademica.getUacid());
//        }
//        if(resultado != null){
//            unidadAcademica.setUacid(resultado.getUacid());
//            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(unidadAcademica);
//        } else{
//            ServiceLocator.getInstanceBaseDAO().save(unidadAcademica);
//        }
//    }
//    
//    public List<Unidadacademica> consultaUnidadAcademica(){
//        List<Unidadacademica> resultado = null;
//        resultado = ServiceLocator.
//                getInstanceBaseDAO().findAll();
//        return resultado;
//    }
//    
//     public void eliminarUnidadAcademica(Unidadacademica unidadAcademica){
//        ServiceLocator.getInstanceBaseDAO().delete(unidadAcademica);
//    }
//        public Unidadacademica getUnidadAcademica(int id){
//        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadacademica.class);
//        Unidadacademica obj =(Unidadacademica) ServiceLocator.getInstanceBaseDAO().find(id);
//        return obj;
//    }
     
     
}
