/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.business;

import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Temaunidad;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Y
 */
public class TemaunidadDelegate {
    
    private List<Temaunidad> listaTemaunidads;
    
    public TemaunidadDelegate(){
        listaTemaunidads=new ArrayList<Temaunidad> ();
    }
    


    public void setListaTemaunidad(List<Temaunidad> listaTemaunidad){
        this.listaTemaunidads=listaTemaunidad;
    }
    public List<Temaunidad> getListaTemaunidadsFromWhere(String de,String campo,String criterio,String order){
        listaTemaunidads = ServiceFacadeLocator.getfacadeTemaUnidad().consultaTemaunidadFromWhere(de, campo, criterio, order);
        return listaTemaunidads;
    }
    
       public Temaunidad findTemaunidad(String idTemaunidad){
        Temaunidad temaunidad = ServiceFacadeLocator.getfacadeTemaUnidad().find(Integer.parseInt(idTemaunidad));
        return temaunidad;
    }
    
    public void agregarUnidad(Temaunidad temaunidad){
        ServiceFacadeLocator.getfacadeTemaUnidad().agregarTemaunidad(temaunidad);
    }
    public void eliminarUnidad(Temaunidad temaunidad){
        ServiceFacadeLocator.getfacadeTemaUnidad().eliminarTemaunidad(temaunidad);
    }
    
       public void agregarTemaunidad(Temaunidad temaunidad){
        ServiceFacadeLocator.getfacadeTemaUnidad().agregarTemaunidad(temaunidad);
    }
    
}
