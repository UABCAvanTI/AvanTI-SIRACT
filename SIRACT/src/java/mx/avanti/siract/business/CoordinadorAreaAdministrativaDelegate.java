package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.List;
import mx.avanti.siract.business.entity.Coordinadorareaadministrativa;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

public class CoordinadorAreaAdministrativaDelegate implements Serializable{
    public void asignarAreaAdministrativa(Coordinadorareaadministrativa c){
        ServiceFacadeLocator.getInstanceFacadeCoordinadorAreaAdministrativa().asignarAreaAdministrativa(c);
    }
    
    public void eliminarAsignacion(Coordinadorareaadministrativa c){
        ServiceFacadeLocator.getInstanceFacadeCoordinadorAreaAdministrativa().eliminarAsignacion(c);
    }
    
    public List<Coordinadorareaadministrativa> consultarAreaAdministrativa(){
        List<Coordinadorareaadministrativa> resultado = null;
        resultado = ServiceFacadeLocator.getInstanceFacadeCoordinadorAreaAdministrativa().consultarAreaAdministrativa();
        return resultado;
    }
}
