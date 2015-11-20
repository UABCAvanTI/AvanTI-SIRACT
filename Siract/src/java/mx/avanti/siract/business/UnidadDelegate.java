/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.business;

import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Temaunidad;
import mx.avanti.siract.business.entity.Unidad;
import mx.avanti.siract.business.services.ServiceFacadeLocator;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Y
 */
public class UnidadDelegate {
    private List<Unidad> listaUnidad;
    private List<Temaunidad> listaTemas;
    public UnidadDelegate(){
        listaUnidad=new ArrayList<Unidad> ();
    }
    
    public List<Temaunidad> getTemasUnidad(Unidad u) {
        return listaTemas;
    }

    public void setListaTemas(List<Temaunidad> listaTemas) {
        this.listaTemas = listaTemas;
    }

    public void setListaUnidad(List<Unidad> listaUnidad){
        this.listaUnidad=listaUnidad;
    }
    public List<Unidad> getListaUnidad(String de,String campo,String criterio,String order){
        listaUnidad = ServiceFacadeLocator.getfacadeUnidad().consultaUnidad(de, campo, criterio, order);
        return listaUnidad;
    }
    
       public Unidad findUnidad(String unidadId){
       
        return  ServiceFacadeLocator.getfacadeUnidad().find(Integer.parseInt(unidadId));
    }
    public void agregarUnidad(Unidad unidad){
        ServiceFacadeLocator.getfacadeUnidad().agregarUnidad(unidad);
    }
    public void eliminarUnidad(Unidad unidad){
        ServiceFacadeLocator.getfacadeUnidad().eliminarUnidad(unidad);
    }
        public List<Unidad> consultaUnidades(String campo, String criterio){
        return ServiceFacadeLocator.getfacadeUnidad().consultaUnidades(campo, criterio);
    }
    
      public List<Unidad> findAll(){
          return ServiceFacadeLocator.getfacadeUnidad().findAll();
    }
}
