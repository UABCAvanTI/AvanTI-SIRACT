/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Subtemaunidad;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Y
 */
public class SubtemaunidadDelegate {
    
    private List<Subtemaunidad> listsubtemas;
    public SubtemaunidadDelegate(){
        listsubtemas=new ArrayList<Subtemaunidad> ();
    }
    
    public List<Subtemaunidad> getTemasUnidad(Subtemaunidad s) {
        return listsubtemas;
    }

    public void setListaTemas(List<Subtemaunidad> listaTemas) {
        this.listsubtemas = listaTemas;
    }

    public void setListaUnidad(List<Subtemaunidad> listaUnidad){
        this.listsubtemas=listaUnidad;
    }
    public List<Subtemaunidad> listaTemasUnidadDe(String de,String campo,String criterio,String order){
        listsubtemas = ServiceFacadeLocator.getFacadeSubtemaunidad().consultaSubtemasUnidad(de, campo, criterio, order);
        return listsubtemas;
    }
    public void agregarUnidad(Subtemaunidad subtemaunidad){
        ServiceFacadeLocator.getFacadeSubtemaunidad().agregarSubtemaunidad(subtemaunidad);
    }
    public void eliminarUnidad(Subtemaunidad subtemaunidad){
        ServiceFacadeLocator.getFacadeSubtemaunidad().eliminarSubtemaunidad(subtemaunidad);
    }
    
      public void agregarSubtemaunidad(Subtemaunidad subtemaunidad){
        ServiceFacadeLocator.getFacadeSubtemaunidad().agregarSubtemaunidad(subtemaunidad);
    }
}
