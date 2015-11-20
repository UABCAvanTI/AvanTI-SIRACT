/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Manuel Papa
 */
public class FacadePlanEstudio1 {
      public void agregarPlanEstudio(Planestudio planEstudio){
        Planestudio resultado = null;
        if(planEstudio.getPesid()!= null){
            ServiceLocator.getInstanceBaseDAO().setTipo(Planestudio.class);
            resultado = (Planestudio) ServiceLocator.getInstanceBaseDAO().find(planEstudio.getPesid());
        }
        if(resultado != null){
            planEstudio.setPesid(resultado.getPesid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(planEstudio);
        } else{
            ServiceLocator.getInstanceBaseDAO().save(planEstudio);
        }
    }
    
    public List<Planestudio> consultaPlanestudio(){
        List<Planestudio> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Planestudio.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
        return resultado;
    }
    
    public void eliminarPlanEstudio(Planestudio planestudio){
        ServiceLocator.getInstanceBaseDAO().setTipo(Planestudio.class);
        ServiceLocator.getInstanceBaseDAO().delete(planestudio);
    }
    /**************************************Metodos requeridos por generador de rportes***************************************************/
//       public List<Planestudio> findAll(){
//        List<Planestudio> result = null;
//        result = ServiceLocator.getInstancePlanestudio().findAllPlanestudios();
//        return result;
//    }
       
    
//    public List<Planestudio> findByProgramaeducativo(int idPrograma){
//        List<Planestudio> result = null;
//        result = ServiceLocator.getInstancePlanestudio().findByCriteria(idPrograma);
//        return result;
//    }
    /****************************************************************************************************************************************/
    
    
    public Planestudio getPlanEstudio(int idPlanEstudio){
        ServiceLocator.getInstanceBaseDAO().setTipo(Planestudio.class);
        return (Planestudio)ServiceLocator.getInstanceBaseDAO().find(idPlanEstudio);
    }    
}
