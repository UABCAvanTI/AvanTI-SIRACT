/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Manuel Papa
 */
public class FacadeProgramaEducativo {
    
    
    
      public void agregarProgramaEducativo(Programaeducativo puesto){
        Programaeducativo resultado = null;
        if(puesto.getPedid()!= null){
            ServiceLocator.getInstanceBaseDAO().setTipo(Programaeducativo.class);
            resultado = (Programaeducativo) ServiceLocator.getInstanceBaseDAO().find(puesto.getPedid());
        }
        if(resultado != null){
            puesto.setPedid(resultado.getPedid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(puesto);
        } else{
            ServiceLocator.getInstanceBaseDAO().save(puesto);
        }
    }
    
    public List<Programaeducativo> consultaProgramaEducativo(){
        List<Programaeducativo> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Programaeducativo.class);
        resultado = ServiceLocator.
                getInstanceBaseDAO().findAll();
        return resultado;
    }
    
      public List<Programaeducativo> buscarProgramaEducativo(String de,String campo, String criterio){
        List<Programaeducativo> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Programaeducativo.class);
        resultado = ServiceLocator.
                getInstanceBaseDAO().findFromWhere("Programaeducativo","profesors", "proid", criterio);
        return resultado;
    }


      
      
      //////////
     public void eliminarProgramaEducativo(Programaeducativo programaeducativo){
        ServiceLocator.getInstanceBaseDAO().setTipo(Programaeducativo.class);
        ServiceLocator.getInstanceBaseDAO().delete(programaeducativo);
    }
     public Programaeducativo getProgramaEducativo(int id){
        ServiceLocator.getInstanceBaseDAO().setTipo(Programaeducativo.class);
        Programaeducativo obj =(Programaeducativo) ServiceLocator.getInstanceBaseDAO().find(id);
        return obj;
    }


    
    public List<Programaeducativo> findAll(){
        List<Programaeducativo> result = null;
        result = ServiceLocator.getInstanceProgramaeducativo().findAllProgramaeducativos();
        return result;
    }
    
    public List<Programaeducativo> findByUnidadAcademica(int idUnidad){
        List<Programaeducativo> result = null;
        result = ServiceLocator.getInstanceProgramaeducativo().findByCriteria(idUnidad);
        return result;
    }

    
    public List<Programaeducativo> consultaPE(int proid){
        List<Programaeducativo> listaPE = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Programaeducativo.class);
        listaPE = ServiceLocator.getInstanceBaseDAO().findFromWhere("profesors", "proid", String.valueOf(proid));
        return listaPE;
    }
  
    public List<Programaeducativo> consultaPEUAC(int uacid){
        List<Programaeducativo> listaPE = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Programaeducativo.class);
        listaPE = ServiceLocator.getInstanceBaseDAO().findFromWhereB("unidadacademica", "uacid", String.valueOf(uacid),"pednombre");
        return listaPE;
    }
    
    public List<Programaeducativo> consultaPEDeAreaConocimiento(String idAco){
        List<Programaeducativo> listaPE = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Programaeducativo.class);
        listaPE = ServiceLocator.getInstanceBaseDAO().findFromWhere("Programaeducativo","planestudios c join c.areaconocimientos", "acoid", String.valueOf(idAco)+" order by a.pednombre");
        return listaPE;
    }
    
      public Programaeducativo getPEdeResponsable(int profResponsableId){
        List<Programaeducativo> listaPE = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Programaeducativo.class);
        listaPE = ServiceLocator.getInstanceBaseDAO().findFromWhere("Programaeducativo","profesor", "proid", String.valueOf(profResponsableId));
        return listaPE.get(0);
    }
      
       public Programaeducativo getPEdeUA(int uaId){
        List<Programaeducativo> listaPE = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Programaeducativo.class);
        listaPE = ServiceLocator.getInstanceBaseDAO().findFromWhere("Programaeducativo","profesor", "proid", String.valueOf(uaId));
        return listaPE.get(0);
    }
        public Programaeducativo getPEdeUnidadAprendizaje(int uaId){
        List<Programaeducativo> listaPE = new ArrayList();
        ServiceLocator.getInstanceBaseDAO().setTipo(Programaeducativo.class);
        listaPE = ServiceLocator.getInstanceBaseDAO().findFromWhere("Programaeducativo","planestudios c join c.areaconocimientos d join d.unidadaprendizajes", "uapclave", String.valueOf(uaId));
        return listaPE.get(0);
    }
        
        
        //Metodo que enviara mensaje si la unidad de aprendizaje se encuentra
        //en mas de un Programa educativo
    public List<Programaeducativo>  obtenerProgramasEducativosDeUA(int uaId){
        List<Programaeducativo> listaPE = new ArrayList();
        ServiceLocator.getInstanceBaseDAO().setTipo(Programaeducativo.class);
        listaPE = ServiceLocator.getInstanceBaseDAO().findFromWhere("Programaeducativo","planestudios c join c.areaconocimientos d join d.unidadaprendizajes", "uapclave", String.valueOf(uaId));
        return listaPE;    
    }

        
    
    
    //PARTE OMITIDA DE PROGRAMA EDUCATIVO DESPUES DE INTEGRAR CATALOGOPROFESOR
    
    
//      public void agregarProgramaEducativo(Programaeducativo puesto){
//        Programaeducativo resultado = null;
//        if(puesto.getPedid()!= null){
//            ServiceLocator.getInstanceBaseDAO().setTipo(Programaeducativo.class);
//            resultado = (Programaeducativo) ServiceLocator.getInstanceBaseDAO().find(puesto.getPedid());
//        }
//        if(resultado != null){
//            puesto.setPedid(resultado.getPedid());
//            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(puesto);
//        } else{
//            ServiceLocator.getInstanceBaseDAO().save(puesto);
//        }
//    }
//    
//    public List<Programaeducativo> consultaProgramaEducativo(){
//        List<Programaeducativo> resultado = null;
//        ServiceLocator.getInstanceBaseDAO().setTipo(Programaeducativo.class);
//        resultado = ServiceLocator.
//                getInstanceBaseDAO().findAll();
//        return resultado;
//    }
//    
//      public List<Programaeducativo> buscarProgramaEducativo(String de,String campo, String criterio){
//        List<Programaeducativo> resultado = null;
//        ServiceLocator.getInstanceBaseDAO().setTipo(Programaeducativo.class);
//        resultado = ServiceLocator.
//                getInstanceBaseDAO().findFromWhere("Programaeducativo","profesors", "proid", criterio);
//        return resultado;
//    }
//
//
//      
//      
//      //////////
//     public void eliminarProgramaEducativo(Programaeducativo programaeducativo){
//        ServiceLocator.getInstanceBaseDAO().setTipo(Programaeducativo.class);
//        ServiceLocator.getInstanceBaseDAO().delete(programaeducativo);
//    }
//     public Programaeducativo getProgramaEducativo(int id){
//        ServiceLocator.getInstanceBaseDAO().setTipo(Programaeducativo.class);
//        Programaeducativo obj =(Programaeducativo) ServiceLocator.getInstanceBaseDAO().find(id);
//        return obj;
//    }
//
//
//    
//    public List<Programaeducativo> findAll(){
//        List<Programaeducativo> result = null;
//        result = ServiceLocator.getInstanceProgramaeducativo().findAllProgramaeducativos();
//        return result;
//    }
//    
//    public List<Programaeducativo> findByUnidadAcademica(int idUnidad){
//        List<Programaeducativo> result = null;
//        result = ServiceLocator.getInstanceProgramaeducativo().findByCriteria(idUnidad);
//        return result;
//    }
//
//    
//    public List<Programaeducativo> consultaPE(int proid){
//        List<Programaeducativo> listaPE = null;
//        ServiceLocator.getInstanceBaseDAO().setTipo(Programaeducativo.class);
//        listaPE = ServiceLocator.getInstanceBaseDAO().findFromWhere("profesors", "proid", String.valueOf(proid));
//        return listaPE;
//    }
  
      
      //Ya no existe a entidad responsableprogramaeducativo, ahora se maneja como 
      //propiedad en profesor y programa educativo
//    public List<Responsableprogramaeducativo> consultaRPE(){
//        List<Responsableprogramaeducativo> resultado = null;
//        ServiceLocator.getInstanceBaseDAO().setTipo(Responsableprogramaeducativo.class);
//        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
//        return resultado;
//    }  

//Responsable de PE
     public Programaeducativo getResponsablePE(int proid){
        List<Programaeducativo> listaPE = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Programaeducativo.class);
        listaPE = ServiceLocator.getInstanceBaseDAO().findFromWhere("profesors", "proid", String.valueOf(proid));
        return listaPE.get(0);
    }
     
      public List<Programaeducativo> findByUnidadAcademicaClave(int claveUnidad){
        List<Programaeducativo> result = null;
        result = ServiceLocator.getInstanceProgramaeducativo().findByCriteriaClave(claveUnidad);
        return result;
    }


}
