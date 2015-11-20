package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.Coordinadorareaadministrativa;
import mx.avanti.siract.common.integration.ServiceLocator;

public class FacadeCoordinadorAreaAdministrativa {
    public void asignarAreaAdministrativa(Coordinadorareaadministrativa c){
        ServiceLocator.getInstanceBaseDAO().setTipo(Coordinadorareaadministrativa.class);
        ServiceLocator.getInstanceBaseDAO().saveOrUpdate(c);
    }
    
   public List<Coordinadorareaadministrativa> consultarAreaAdministrativa(){
       List<Coordinadorareaadministrativa> resultado = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Coordinadorareaadministrativa.class);
       resultado = ServiceLocator.getInstanceBaseDAO().findAll();
       return resultado;
   }
   
   public void eliminarAsignacion(Coordinadorareaadministrativa c){
       ServiceLocator.getInstanceBaseDAO().setTipo(Coordinadorareaadministrativa.class);
       ServiceLocator.getInstanceBaseDAO().delete(c);
   }
}
