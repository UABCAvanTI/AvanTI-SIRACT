/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Campus;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author balta
 */
public class FacadeCampus {
    public void agregarCampus(Campus campus){
        Campus resultado = null;
        if(campus == null){
            System.out.println("Campus nulo");
        }
        if(campus.getCamid() != null){
            ServiceLocator.getInstanceBaseDAO().setTipo(Campus.class);
            resultado = (Campus) ServiceLocator.getInstanceBaseDAO().find(campus.getCamid());
        }
        if(resultado != null){
            campus.setCamid(resultado.getCamid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(campus);
        } else{
            ServiceLocator.getInstanceBaseDAO().save(campus);
        }
    }
    
    public List<Campus> consultaCampus(){
        List<Campus> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Campus.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
        return resultado;
    }
    
    public void eliminarCampus(Campus campus){
        ServiceLocator.getInstanceBaseDAO().delete(campus);
    }
        public Campus getCampus(int id){
        ServiceLocator.getInstanceBaseDAO().setTipo(Campus.class);
        return (Campus) ServiceLocator.getInstanceBaseDAO().find(id);
    }
}
