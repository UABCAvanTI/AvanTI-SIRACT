/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.Iterator;
import java.util.List;
import mx.avanti.siract.business.entity.Unidad;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Manuel Papa
 */
public class FacadeUnidad {
    
        public Unidad find(int id){
        Unidad result = null;
//        ServiceLocator.getInstanceBaseDAO().setTipo(Unidad.class);
        result = (Unidad) ServiceLocator.getInstanceUnidad().findByUnidadId(id);
        return result;
    }
    
    public void agregarUnidad(Unidad unidad){
        Unidad resultado = null;
        if(unidad.getUniid()!= null){
            ServiceLocator.getInstanceBaseDAO().setTipo(Unidad.class);
            resultado = (Unidad) ServiceLocator.getInstanceBaseDAO().find(unidad.getUniid());
        }
        if(resultado != null){
            unidad.setUniid(resultado.getUniid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(unidad);
        } else{
            ServiceLocator.getInstanceBaseDAO().save(unidad);
        }
    }
    
    public List<Unidad> consultaUnidad(String de,String campo,String criterio,String order){
        List<Unidad> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidad.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhereBExtra(de, campo, criterio,"Length("+order+"), "+order+" ASC","Unidad");
        return resultado;
    }
    
       
    
    public void eliminarUnidad(Unidad unidad){
        ServiceLocator.getInstanceBaseDAO().delete(unidad);
    }
        public List<Unidad> consultaUnidades(String campo,String criterio){
                List<Unidad> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidad.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findWhereExtra(campo, criterio,"Unidad","uninumero");
        return resultado;
    }
        
           public List<Unidad> findAll(){
                List<Unidad> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidad.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
        return resultado;
    }
    
}
