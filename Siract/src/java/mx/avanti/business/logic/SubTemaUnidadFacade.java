/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.List;
import java.util.concurrent.Delayed;
import mx.avanti.siract.business.entity.Subtemaunidad;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Y
 */
public class SubTemaUnidadFacade {

    public void agregarSubtemaunidad(Subtemaunidad subtemaunidad) {
        Subtemaunidad resultado = null;
        if (subtemaunidad.getSutid() != null) {
            ServiceLocator.getInstanceBaseDAO().setTipo(Subtemaunidad.class);
            resultado = (Subtemaunidad) ServiceLocator.getInstanceBaseDAO().find(subtemaunidad.getSutid());
        }
        if (resultado != null) {
            subtemaunidad.setSutid(resultado.getSutid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(subtemaunidad);
        } else {
            ServiceLocator.getInstanceBaseDAO().save(subtemaunidad);
        }
    }

    public List<Subtemaunidad> consultaSubtemasUnidad(String de, String campo, String criterio, String order){
        List<Subtemaunidad> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Subtemaunidad.class);

        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhereBExtra(de, campo, criterio, order+" ASC","Subtemaunidad");
        return resultado;
    }

    public List<Subtemaunidad> obtenerUnidadesDeTema(String de, String campo, String criterio, String order) {
        List<Subtemaunidad> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Subtemaunidad.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhereB(de, campo, criterio, order);
        return resultado;
    }

    public void eliminarSubtemaunidad(Subtemaunidad subtemaunidad) {
        ServiceLocator.getInstanceBaseDAO().delete(subtemaunidad);
    }
}
