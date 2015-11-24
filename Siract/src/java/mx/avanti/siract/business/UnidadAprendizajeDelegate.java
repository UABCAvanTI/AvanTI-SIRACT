/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author balta
 */
public class UnidadAprendizajeDelegate implements Serializable{
    private List<Unidadaprendizaje> listaUnidadAprendizaje;
    
    public UnidadAprendizajeDelegate(){
        listaUnidadAprendizaje = new ArrayList<Unidadaprendizaje>();
    }

    public List<Unidadaprendizaje> getListaUnidadAprendizaje() {
        listaUnidadAprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().consultaUnidadAprendizaje();
        return listaUnidadAprendizaje;
    }
        public List<Unidadaprendizaje> getListaUnidadAprendisajeFFWT(String de,String de2,String campo2,String criterio2,String de3,String campo3,String criterio3,String order) {
        listaUnidadAprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().consultaFFWTUnidadesAprendisaje(de,de2, campo2, criterio2, de3, campo3, criterio3, order);
        return listaUnidadAprendizaje;
    }

    public void setListaUnidadAprendizaje(List<Unidadaprendizaje> listaUnidadAprendizaje) {
        this.listaUnidadAprendizaje = listaUnidadAprendizaje;
    }
//    
//    public void agregarUniadadAprenidzaje(Unidadaprendizaje unidadaprendizaje){
//        ServiceFacadeLocator.getInstanceFacadeAreaConocimiento()
//                .agregarAreaConocimiento(unidadaprendizaje.getAreaconocimiento());
//        ServiceFacadeLocator.getFacadeCicloEscolar()
//                .agregarCicloEscolar(unidadaprendizaje.getCicloescolar());
//        ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje()
//                .agregarUnidadAprendizaje(unidadaprendizaje);
//    }
    
            public Unidadaprendizaje findUAById(int id){
        return ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().getUnidadAprendazaje(id);
    }
            
      public Unidadaprendizaje findUAbyClave(String clave){
          List<Unidadaprendizaje> lista=ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findUAByClave(clave);
          if(lista!=null&&!lista.isEmpty()&&lista.size()>0){
        return lista.get(0);
          }else{
              return null;
          }
    }
            
            
   

        
    public void agregarUnidadAprendizaje(Unidadaprendizaje unidadaprendizaje){
        ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().agregarUnidadAprendizaje(unidadaprendizaje);
    }
    
    public void eliminarUnidadAprendizaje(Unidadaprendizaje unidadaprendizaje){
        ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().eliminarUnidadAprendizaje(unidadaprendizaje);
    }
    
        public List<Unidadaprendizaje> getUAMismaArea(int idArea) {
        return ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().getUAMismaArea(idArea);
    }
      
        /////////////////
    private List<Programaeducativo> listaProgramaEducativo;
    private List<Planestudio> listaPlanEstudio;
    private List<Areaconocimiento> listaAreaConocimiento;

  public List<Programaeducativo> getListaProgramaEducativo() {
        listaProgramaEducativo = ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().consultaProgramaEducativo();
        return listaProgramaEducativo;
    }
    public void setListaProgramaEducativo(List<Programaeducativo> listaProgramaEducativo) {
        this.listaProgramaEducativo = listaProgramaEducativo;
    }

    public List<Planestudio> getListaPlanEstudio() {
        listaPlanEstudio = ServiceFacadeLocator.getInstanceFacadePlanEstudio().consultaPlanestudio();
        return listaPlanEstudio;
    }

    public void setListaPlanEstudio(List<Planestudio> listaPlanEstudio) {
        this.listaPlanEstudio = listaPlanEstudio;
    }

    public List<Areaconocimiento> getListaAreaConocimiento() {
        listaAreaConocimiento = ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().consultaAreaConocimiento();
        return listaAreaConocimiento;
    }

    public void setListaAreaConocimiento(List<Areaconocimiento> listaAreaConocimiento) {
        this.listaAreaConocimiento = listaAreaConocimiento;
    }
    
}
