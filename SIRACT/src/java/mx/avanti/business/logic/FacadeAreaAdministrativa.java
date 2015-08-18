package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.Areaadministrativa;
import mx.avanti.siract.business.entity.Coordinadorareaadministrativa;
import mx.avanti.siract.common.integration.ServiceLocator;

public class FacadeAreaAdministrativa {
    public void agregarAreaAdministrativa(Areaadministrativa areaad){
        Areaadministrativa resultado = null;
        if(areaad.getAadid() != null){
            ServiceLocator.getInstanceBaseDAO().setTipo(Areaadministrativa.class);
            resultado = (Areaadministrativa)ServiceLocator.getInstanceBaseDAO().find(areaad.getAadid());
        }
        if(resultado != null){   
            ServiceLocator.getInstanceBaseDAO().setTipo(Areaadministrativa.class);            
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(areaad);
        }else{
            ServiceLocator.getInstanceBaseDAO().setTipo(Areaadministrativa.class);
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(areaad);
        }
    }
    
   public List<Areaadministrativa> consultarAreaAdministrativa(){
       List<Areaadministrativa> resultado = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Areaadministrativa.class);
       resultado = ServiceLocator.getInstanceBaseDAO().findAll();
       return resultado;
   }
   
   public void eliminarAreaAdministrativa(Areaadministrativa c){
       ServiceLocator.getInstanceBaseDAO().setTipo(Areaadministrativa.class);
       ServiceLocator.getInstanceBaseDAO().delete(c);
   }
   
   public Areaadministrativa getAreaAd(int idAreaAd){
        ServiceLocator.getInstanceBaseDAO().setTipo(Areaadministrativa.class);
        return (Areaadministrativa)ServiceLocator.getInstanceBaseDAO().find(idAreaAd);
    }
   
   public List<Areaadministrativa> consultaAreaAdPE(int peid){
        List<Areaadministrativa> listaPE = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Areaadministrativa.class);
        listaPE = ServiceLocator.getInstanceBaseDAO().findFromWhere("programaeducativo", "pedid", String.valueOf(peid));
        return listaPE;
    }   
   public List<Areaadministrativa> getAreaAsignada(int idArea) {
        List<Areaadministrativa> listaGpo = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Coordinadorareaadministrativa.class);
        listaGpo = ServiceLocator.getInstanceBaseDAO().findFromWhereB("areaadministrativa", "aadid", String.valueOf(idArea),"areaadministrativa.aadid");
        return listaGpo;
    }  
}
