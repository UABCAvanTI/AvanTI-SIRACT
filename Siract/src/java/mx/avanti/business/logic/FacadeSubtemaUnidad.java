/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.Subtemaunidad;
import mx.avanti.siract.business.entity.Temaunidad;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Y
 */
public class FacadeSubtemaUnidad {
    
    public void agregarSubtemaunidad(Subtemaunidad subtemaunidad){
        Subtemaunidad resultado = null;
        if(subtemaunidad.getSutid()!= null){
            ServiceLocator.getInstanceBaseDAO().setTipo(Subtemaunidad.class);
            resultado = (Subtemaunidad) ServiceLocator.getInstanceBaseDAO().find(subtemaunidad.getSutid());
        }
        if(resultado != null){
            subtemaunidad.setSutid(resultado.getSutid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(subtemaunidad);
        } else{
            ServiceLocator.getInstanceBaseDAO().save(subtemaunidad);
        }
    }
    
    public void agregarSubtemaunidadCatalogo(int numero, Temaunidad tema){
        float porcentaje = (float) 0.0;
        float horas = (float) 0.0;
        for(int i=0;i<numero;i++){
            Subtemaunidad subtemaunidad = new Subtemaunidad(tema,"0","Subtema " + (i+1),porcentaje,horas);
            ServiceLocator.getInstanceBaseDAO().save(subtemaunidad);
        }
    }
    
    public List<Subtemaunidad> consultaSubtemasUnidad(String de,String campo,String criterio,String order){
        List<Subtemaunidad> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Subtemaunidad.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhereBExtra(de, campo, criterio,"Length("+order+"), "+order+" ASC","Subtemaunidad");
        return resultado;
    }
      public List<Subtemaunidad> obtenerUnidadesDeTema(String de,String campo,String criterio,String order){
        List<Subtemaunidad> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Subtemaunidad.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhereB(de, campo, criterio, order);
        return resultado;
    }
    
       
    
    public void eliminarSubtemaunidad(Subtemaunidad subtemaunidad){
        ServiceLocator.getInstanceBaseDAO().delete(subtemaunidad);
    }

    
}
