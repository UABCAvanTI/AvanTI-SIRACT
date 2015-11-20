/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Catalogoreportes;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Cesar Favela
 */
public class CatalogoReportesDelegate {
    private List<Catalogoreportes> catalogoreportes;
    
    public CatalogoReportesDelegate(){
        selectedCatalogoreporte = new Catalogoreportes();
        catalogoreportes = new ArrayList<Catalogoreportes>();
       
    }
    
    public List<Catalogoreportes> getCatalagoReportes() {
        
         catalogoreportes = ServiceFacadeLocator.getFacadeCatalagoReportes().findallCatalaogoReportes();
         return catalogoreportes;
    }

    public void setCatalagoReporteslist(List<Catalogoreportes> catalogoreportes) {
        this.catalogoreportes=catalogoreportes;
    }
    
    public void setCatalagoReportes(Catalogoreportes catalogoreportes){
        ServiceFacadeLocator.getFacadeCatalagoReportes().saveCatalogoReportes(catalogoreportes);
        //ServiceFacadeLocator.getFacadeArchivo().saveArchivo(persona.getArchivo());
        //ServiceFacadeLocator.getFacadePersona().savePersona(persona);
    }
    
    //ENVIADO POR GENERADOR DE REPORTES
    private Catalogoreportes catalogoreporte;
    private Catalogoreportes selectedCatalogoreporte = null;
    int selectedid;    
    
 
    public void agregarCatalogoreporte(Catalogoreportes catalogoreporte){
        ServiceFacadeLocator.getFacadeCatalagoReportes().agregarCatalogoReportes(catalogoreporte);
    }
    
    public void eliminarCatalogoreporte(int ctrid){
        ServiceFacadeLocator.getFacadeCatalogoreportes().eliminarCatalogoreporte(ctrid);
    }
    
    public List<Catalogoreportes> getCatalogoreportes() {
        catalogoreportes = ServiceFacadeLocator.getFacadeCatalogoreportes().
                consultaCatalogoreportes();
        return catalogoreportes;
    }
    
    public Catalogoreportes getCatalogoreporte(int ctrid){
        catalogoreporte = ServiceFacadeLocator.getFacadeCatalogoreportes().
                consultaCatalogoreporte(ctrid);                
        return catalogoreporte;
    }
    
}
