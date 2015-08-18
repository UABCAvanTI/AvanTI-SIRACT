/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.Catalogoreportes;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Cesar Favela
 */
public class FacadeCatalogoReportes{
    public void saveCatalogoReportes(Catalogoreportes catalagoreportes){
        Catalogoreportes resultado = null;
        if(catalagoreportes.getCtrid() != null){
            ServiceLocator.getInstanceBaseDAO().setTipo(Catalogoreportes.class);
            resultado = (Catalogoreportes) ServiceLocator.getInstanceBaseDAO().find(catalagoreportes.getCtrid());
        }
        if(resultado != null){
            catalagoreportes.setCtrid(resultado.getCtrid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(catalagoreportes);
        } else{
            ServiceLocator.getInstanceBaseDAO().save(catalagoreportes);
        }
    }
    
    public List<Catalogoreportes> findallCatalaogoReportes(){
        List<Catalogoreportes> resultado = null;
        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
        return resultado;
    }
    
    //ENVIADO POR GENERADOR DE REPORTES
    
    
   public void agregarCatalogoReportes(Catalogoreportes catalogoreporte){
        Catalogoreportes resultado = null;
        if(catalogoreporte.getCtrid()!= null){ //debe normalmente ser null en vez de cero, al mapear hibernate int por integer
            resultado = (Catalogoreportes) ServiceLocator.getInstanceCatalogoreportes().
                    findByCatalogoreportesId(catalogoreporte.getCtrid());
        }
        if(resultado != null) {
            catalogoreporte.setCtrid(resultado.getCtrid());
            ServiceLocator.getInstanceCatalogoreportes().updateCatalogoreportes(catalogoreporte);
        }else{
            ServiceLocator.getInstanceCatalogoreportes().addCatalogoReportes(catalogoreporte);
        }
    }
    
    public List<Catalogoreportes> consultaCatalogoreportes(){
        List<Catalogoreportes> resultado=null;
        resultado = ServiceLocator.getInstanceCatalogoreportes().findAllCatalogoreportess();
        return resultado;
    }
    
    public void eliminarCatalogoreporte(int ctrid){
        ServiceLocator.getInstanceCatalogoreportes().
                deleteCatalogoreportes(ctrid);
    }
    
    public Catalogoreportes consultaCatalogoreporte(int ctrid){
        //List<Catalogoreportes> resultado=null;
        Catalogoreportes resultado=null;
        resultado = ServiceLocator.getInstanceCatalogoreportes().findByCatalogoreportesId(ctrid);
        return resultado;
    }
}