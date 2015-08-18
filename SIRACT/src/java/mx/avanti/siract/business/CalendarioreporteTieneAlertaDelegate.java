/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.List;
import mx.avanti.siract.business.entity.CalendarioreporteTieneAlerta;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Y
 */
public class CalendarioreporteTieneAlertaDelegate implements Serializable {
    
     private List<CalendarioreporteTieneAlerta> crta;
    
    public List<CalendarioreporteTieneAlerta> getCalendarioReporteTieneAlerta(){
        crta=ServiceFacadeLocator.getFacadeCalendarioreporteTieneAlerta().findAll();
        return crta;
    }
    
    public CalendarioreporteTieneAlerta getCalendarioReporteTieneAlertas(int creid, int aleid){
        CalendarioreporteTieneAlerta cta=ServiceFacadeLocator.getFacadeCalendarioreporteTieneAlerta().find(creid, aleid);
        return cta;
    }
    
    public void saveCalendarioreporteTieneAlerta(CalendarioreporteTieneAlerta cta){
        ServiceFacadeLocator.getFacadeCalendarioreporteTieneAlerta().saveCalendarioreporteTieneAlerta(cta);
    }
    
    public List<CalendarioreporteTieneAlerta> getCalendariosFechaActual(String idCalendarioreporte){
        return ServiceFacadeLocator.getFacadeCalendarioreporteTieneAlerta().getCalendariosFechaActual(idCalendarioreporte);
    }
    
    
}
