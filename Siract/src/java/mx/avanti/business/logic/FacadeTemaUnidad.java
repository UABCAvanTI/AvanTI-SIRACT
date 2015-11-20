/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.Temaunidad;
import mx.avanti.siract.business.entity.Unidad;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Manuel Papa
 */
public class FacadeTemaUnidad {

    public void agregarTemaunidad(Temaunidad temaunidad) {
        Temaunidad resultado = null;
        if (temaunidad.getTunid() != null) {
            ServiceLocator.getInstanceBaseDAO().setTipo(Temaunidad.class);
            resultado = (Temaunidad) ServiceLocator.getInstanceBaseDAO().find(temaunidad.getTunid());
        }
        if (resultado != null) {
            temaunidad.setTunid(resultado.getTunid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(temaunidad);
        } else {
            ServiceLocator.getInstanceBaseDAO().save(temaunidad);
        }
    }

    public List<Temaunidad> consultaTemaunidadFromWhere(String de, String campo, String criterio, String order) {
        List<Temaunidad> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Temaunidad.class);

        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhereBExtra(de, campo, criterio,"Length("+order+"), "+order+" ASC","Temaunidad");
        return resultado;
    }

    public void eliminarTemaunidad(Temaunidad temaunidad) {
        ServiceLocator.getInstanceBaseDAO().delete(temaunidad);
    }

    
    
    public void agregarTemaunidadCatalogo(int numero, Unidad uni){
        Float horas = (float)0.0;
        float porcentaje = (float) 0.0;
        for(int i=0;i<numero;i++){
            Temaunidad temaunidad = new Temaunidad(uni,"0","Tema " + (i+1),porcentaje,horas,true,null);
            ServiceLocator.getInstanceBaseDAO().save(temaunidad);
        }
    }
    
    public Temaunidad find(int id){
        Temaunidad result = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Temaunidad.class);
        result = (Temaunidad) ServiceLocator.getInstanceBaseDAO().find(id);
        return result;
    }
    
    public List<Temaunidad> findByUnidad(int idUnidad){
        List<Temaunidad> result = null;
        result = ServiceLocator.getInstanceTemaunidad().findByCriteria(idUnidad);
        return result;
    }


}
