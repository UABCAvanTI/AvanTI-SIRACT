package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.List;
import mx.avanti.siract.business.entity.Areaadministrativa;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

public class AreaAdministrativaDelegate implements Serializable{
    public void agregarAreaAdministrativa(Areaadministrativa c){
        ServiceFacadeLocator.getInstanceFacadeAreaAdministrativa().agregarAreaAdministrativa(c);
    }
    
    public void eliminarAreaAdministrativa(Areaadministrativa c){
        ServiceFacadeLocator.getInstanceFacadeAreaAdministrativa().eliminarAreaAdministrativa(c);
    }
    
    public List<Areaadministrativa> consultarAreaAdministrativa(){
        List<Areaadministrativa> resultado = null;
        resultado = ServiceFacadeLocator.getInstanceFacadeAreaAdministrativa().consultarAreaAdministrativa();
        return resultado;
    }
    
    public Areaadministrativa findAreaAdById(int id){
        return ServiceFacadeLocator.getInstanceFacadeAreaAdministrativa().getAreaAd(id);
    }
    
     public List<Areaadministrativa> getAreaAdbyPE(int pedid){
        return ServiceFacadeLocator.getInstanceFacadeAreaAdministrativa().consultaAreaAdPE(pedid);
    }
     
     public List<Areaadministrativa> getAreaAsignada(int idArea){
         return ServiceFacadeLocator.getInstanceFacadeAreaAdministrativa().getAreaAsignada(idArea);
     }
     
    public void asignarAreaAdministrativa(Areaadministrativa c){
        ServiceFacadeLocator.getInstanceFacadeAreaAdministrativa().agregarAreaAdministrativa(c);
    }
    
    public void eliminarAsignacion(Areaadministrativa c){
        ServiceFacadeLocator.getInstanceFacadeAreaAdministrativa().eliminarAreaAdministrativa(c);
    }

}
