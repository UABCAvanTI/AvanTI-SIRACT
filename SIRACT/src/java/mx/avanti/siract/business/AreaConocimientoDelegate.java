/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author balta
 */
public class AreaConocimientoDelegate implements Serializable {
    private List<Areaconocimiento> listaAreaConocimiento;
    
    public AreaConocimientoDelegate(){
        listaAreaConocimiento = new ArrayList<Areaconocimiento>();
    }
    
    public List<Areaconocimiento> getListaAreaConocimiento() {
        listaAreaConocimiento = ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().consultaAreaConocimiento();
        return listaAreaConocimiento;
    }

    public void setListaAreaConocimiento(List<Areaconocimiento> listaAreaConocimiento) {
        this.listaAreaConocimiento = listaAreaConocimiento;
    }
    
    public void agregarAreaConocimiento(Areaconocimiento areaconocimiento){
        ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().agregarAreaConocimiento(areaconocimiento);
    }
      public List<Areaconocimiento> consultarAreasConocimiento(String de,String campo,String criterio) {
        listaAreaConocimiento = ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().consultarAreasConocimiento(de, campo, criterio);
        return listaAreaConocimiento;
    }
    
      public Areaconocimiento buscarAreaConocimiento(Areaconocimiento areaconocimiento){
       Areaconocimiento resultado;
       resultado=ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().buscarAreaConocimiento(areaconocimiento);
       return resultado;
   }
      
      //Sustituido el 04-08-2015
//     public void eliminarAreaConocimiento(Areaconocimiento areaconocimiento){
//        ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().eliminarAreaConocimiento(areaconocimiento);
//    }
    public Areaconocimiento findAreaConocimientoById(int id){
        return ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().getAreaConocimiento(id);
    }
      
          public List<Areaconocimiento> getAreaByUnidad(String unidadClave){
              List<Areaconocimiento> resultado= ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().getAreasByUnidad(unidadClave);
                
              return resultado;
    }
          
          
          /////////////////Parte enviada por Luis Luna


     public void eliminarAreaConocimiento(Areaconocimiento areaconocimiento){
         if(areaconocimiento==null||areaconocimiento.getAcoid()==null){
             System.out.println("NO HAY ID PARA AREA CONOCIMIENTO");
         }
         areaconocimiento=ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().buscarAreaConocimiento(areaconocimiento);
        ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().eliminarAreaConocimiento(areaconocimiento);
    }

     //Metodos enviados por marco para resolver problemas de metodos faltantes
         public List<Areaconocimiento> getAreaMismoPlan(int idPlan){
        return ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().getAreaMismoPlan(idPlan);
    } 
}
