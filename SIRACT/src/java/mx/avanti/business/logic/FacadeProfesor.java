/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Unidad;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Usagi
 */
public class FacadeProfesor {
    
    public Profesor findProfesorFromUser(int idUsuario) {
        Profesor resultado = new Profesor();
        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
        List<Profesor> lista = ServiceLocator.getInstanceBaseDAO().findFromWhereB2("usuario", "usuid", String.valueOf(idUsuario), "proid");
        if(lista.isEmpty()){
             return null;
        }else{
            return lista.get(0);
        }
    }
    
            public void agregarProfesor(Profesor profesor){
        Profesor resultado = null;
        if(profesor.getProid() != null){
            ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
            resultado = (Profesor) ServiceLocator.getInstanceBaseDAO().find(profesor.getProid());
        }
        if(resultado != null){
            profesor.setProid(resultado.getProid());
            ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(profesor);
        } else{
            ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(profesor);
        }
    }
    
     public void agregarUnidad(Profesor profesor){
        Unidad resultado = null;
        if(profesor.getProid()!= null){
            ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
            resultado = (Unidad) ServiceLocator.getInstanceBaseDAO().find(profesor.getProid());
        }
        if(resultado != null){
            profesor.setProid(resultado.getUniid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(profesor);
        } else{
            ServiceLocator.getInstanceBaseDAO().save(profesor);
        }
    }
    
    public List<Profesor> consultaProfesores(){
        List<Profesor> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
        return resultado;
    }
     public List<Profesor> consultaProfesor(){
        List<Profesor> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
        return resultado;
    }
    
    
    //Metodo para conseguir un profesor correspondiente a un usuario
//    public Profesor findProfesorFromUser(int idUsuario){
//        Profesor resultado;
//        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
//        resultado =(Profesor) ServiceLocator.getInstanceBaseDAO().findFromWhereB("usuario", "usuid", String.valueOf(idUsuario), "proid").get(0);
//       return resultado;
//    }
    
    public void eliminarUnidad(Profesor unidad){
        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
        ServiceLocator.getInstanceBaseDAO().delete(unidad);
    }
        public Profesor getProfesor(int idProfesor){
        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
        return (Profesor)ServiceLocator.getInstanceBaseDAO().find(idProfesor);
    }
           
   

    public void eliminarProfesor(Profesor profesor){
        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
        ServiceLocator.getInstanceBaseDAO().delete(profesor);
    }
   

    
    
    /***********************************METODOS NECESARIOS PARA GENERADOR DE REPORTES******************************************/
//        public List<Profesor> findAll(){
//        List<Profesor> result = null;
//        result = ServiceLocator.getInstanceProfesorDAO().findAllProfesor();
//        return result;
//    }
//    
//   public List<Profesor> findByProfe(int idProfe){
//        List<Profesor> result = null;
//        result = (List<Profesor>) ServiceLocator.getInstanceProfesorDAO().findByCriteria(idProfe);
//        return result;
//    }
    /*************************************************************************************************************************/
   
   //CRITERIA
//    public List<Profesor> finByProfesor(int idPrograma){
//        List<Profesor> result = null;
//        result = ServiceLocator.getInstanceProfesorDAO().findByCriteria(idPrograma);
//        return result;
//    }
    
    public List<Profesor> findProfesor(int idPrograma){
        List<Profesor> result= null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
        result = ServiceLocator.getInstanceBaseDAO().findFromWhere("programaeducativos", "pedid", String.valueOf(idPrograma));
        return result;
    }
        
    
    public List<Profesor> consultaProfPE(int peid){
        List<Profesor> listaPE = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
        listaPE = ServiceLocator.getInstanceBaseDAO().findFromWhere("programaeducativos_1", "pedid", String.valueOf(peid));
        return listaPE;
    }    
    
    public  List<Profesor> getProfMismoPE(int pedid){
        List<Profesor> listaPE = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
        listaPE = ServiceLocator.getInstanceBaseDAO().findFromWhere("unidadacademicas", "uacid", String.valueOf(pedid));
        return listaPE;
    }
    
    public  List<Profesor> consultaProfPorPe(int pedid){
        List<Profesor> listaPE = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
        listaPE = ServiceLocator.getInstanceBaseDAO().findFromWhere("profesors", "proid", String.valueOf(pedid));
        return listaPE;
    }    
        
    
    
    ///PARTE DE PROFESOR OMITIDA DESPUES DE INTEGRAR CATALOGO DE PROFESOR

//    public Profesor findProfesorFromUser(int idUsuario) {
//        Profesor resultado = new Profesor();
//        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
//        List<Profesor> lista = ServiceLocator.getInstanceBaseDAO().findFromWhereB2("usuario", "usuid", String.valueOf(idUsuario), "proid");
//        if(lista.isEmpty()){
//             return null;
//        }else{
//            return lista.get(0);
//        }
//    }
//    
//            public void agregarProfesor(Profesor profesor){
//        Profesor resultado = null;
//        if(profesor.getProid() != null){
//            ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
//            resultado = (Profesor) ServiceLocator.getInstanceBaseDAO().find(profesor.getProid());
//        }
//        if(resultado != null){
//            profesor.setProid(resultado.getProid());
//            ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
//            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(profesor);
//        } else{
//            ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
//            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(profesor);
//        }
//    }
//    
//     public void agregarUnidad(Profesor profesor){
//        Unidad resultado = null;
//        if(profesor.getProid()!= null){
//            ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
//            resultado = (Unidad) ServiceLocator.getInstanceBaseDAO().find(profesor.getProid());
//        }
//        if(resultado != null){
//            profesor.setProid(resultado.getUniid());
//            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(profesor);
//        } else{
//            ServiceLocator.getInstanceBaseDAO().save(profesor);
//        }
//    }
//    
//    public List<Profesor> consultaProfesores(){
//        List<Profesor> resultado = null;
//        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
//        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
//        return resultado;
//    }
//     public List<Profesor> consultaProfesor(){
//        List<Profesor> resultado = null;
//        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
//        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
//        return resultado;
//    }
//    
//    
//    //Metodo para conseguir un profesor correspondiente a un usuario
////    public Profesor findProfesorFromUser(int idUsuario){
////        Profesor resultado;
////        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
////        resultado =(Profesor) ServiceLocator.getInstanceBaseDAO().findFromWhereB("usuario", "usuid", String.valueOf(idUsuario), "proid").get(0);
////       return resultado;
////    }
//    
//    public void eliminarUnidad(Profesor unidad){
//        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
//        ServiceLocator.getInstanceBaseDAO().delete(unidad);
//    }
//        public Profesor getProfesor(int idProfesor){
//        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
//        return (Profesor)ServiceLocator.getInstanceBaseDAO().find(idProfesor);
//    }
//           
//   
//
//    public void eliminarProfesor(Profesor profesor){
//        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
//        ServiceLocator.getInstanceBaseDAO().delete(profesor);
//    }
//   
//
//    
//    
//    /***********************************METODOS NECESARIOS PARA GENERADOR DE REPORTES******************************************/
////        public List<Profesor> findAll(){
////        List<Profesor> result = null;
////        result = ServiceLocator.getInstanceProfesorDAO().findAllProfesor();
////        return result;
////    }
////    
////   public List<Profesor> findByProfe(int idProfe){
////        List<Profesor> result = null;
////        result = (List<Profesor>) ServiceLocator.getInstanceProfesorDAO().findByCriteria(idProfe);
////        return result;
////    }
//    /*************************************************************************************************************************/
//   
//   //CRITERIA
////    public List<Profesor> finByProfesor(int idPrograma){
////        List<Profesor> result = null;
////        result = ServiceLocator.getInstanceProfesorDAO().findByCriteria(idPrograma);
////        return result;
////    }
//    
//    public List<Profesor> findProfesor(int idPrograma){
//        List<Profesor> result= null;
//        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
//        result = ServiceLocator.getInstanceBaseDAO().findFromWhere("programaeducativos", "pedid", String.valueOf(idPrograma));
//        return result;
//    }
//        
//    
//    public List<Profesor> consultaProfPE(int peid){
//        List<Profesor> listaPE = null;
//        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
//        listaPE = ServiceLocator.getInstanceBaseDAO().findFromWhere("programaeducativos", "pedid", String.valueOf(peid));
//        return listaPE;
//    }    
        
        public List<Profesor> getProfAsignado(int idProfesor) {
        List<Profesor> listaGpo = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Profesor.class);
        listaGpo = ServiceLocator.getInstanceBaseDAO().findFromWhereB("profesor", "proid", String.valueOf(idProfesor),"uipid");
        return listaGpo;
    }  
        
        
        

    
    public List<Profesor> getProfesorByUnidadAprendisajeClave(int uapclave) {
        List<Profesor> result = null;
        result = ServiceLocator.getInstanceProfesor().findByUnidadAprendisajeClave(uapclave);
        return result; 
    }
}
