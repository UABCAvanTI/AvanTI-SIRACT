/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;


import java.util.List;
import mx.avanti.siract.business.entity.Cicloescolar;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Samanta Rdgz
 */
public class FacadeCicloEscolarConfig {
    public void saveCicloEscolar(Cicloescolar cicloescolar){
        Cicloescolar result = null;

        if(cicloescolar.getCesid() != null){
            ServiceLocator.getInstanceBaseDAO().setTipo(Cicloescolar.class);
            result = (Cicloescolar) ServiceLocator.getInstanceBaseDAO().find(cicloescolar.getCesid());
        }
        
        if(result != null){
            cicloescolar.setCesid(result.getCesid());
                    ServiceLocator.getInstanceBaseDAO().setTipo(Cicloescolar.class);
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(cicloescolar);
        }else{
                    ServiceLocator.getInstanceBaseDAO().setTipo(Cicloescolar.class);
            ServiceLocator.getInstanceBaseDAO().save(cicloescolar);
        }
    }
    public List<Cicloescolar> findAll() {
        List<Cicloescolar> listaCiclo = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Cicloescolar.class);
        listaCiclo = ServiceLocator.getInstanceBaseDAO().findAll();
        return listaCiclo;
        
    }
}
