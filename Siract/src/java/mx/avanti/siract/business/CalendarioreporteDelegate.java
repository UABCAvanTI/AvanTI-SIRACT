/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.List;
import mx.avanti.siract.business.entity.Calendarioreporte;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Y
 */
public class CalendarioreporteDelegate implements Serializable{
    
    public Calendarioreporte calendarioFechaActual(){
        return ServiceFacadeLocator.getFacadeCalendarioReporte().calendarioreporteFechaActual();
    }
    
     public Calendarioreporte siguienteReporte(){
        return ServiceFacadeLocator.getFacadeCalendarioReporte().siguienteReporte();
    }
     
      Calendarioreporte calendarioreporte;
    private List<Calendarioreporte> calendariosReporte;
    private List<Calendarioreporte> con_cre;
    
    public List<Calendarioreporte> getCalendariosReporte(){
        calendariosReporte=ServiceFacadeLocator.getFacadeCalendarioReporte().findAll();
        return calendariosReporte;
    }
    
    public Calendarioreporte find(int id){
        calendarioreporte=ServiceFacadeLocator.getFacadeCalendarioReporte().find(id);
        return calendarioreporte;
    }
    
    public void deleteCalendarioreporte(Calendarioreporte c){
        ServiceFacadeLocator.getFacadeCalendarioReporte().eliminarCalendarioReporte(c);
    }
    
    public List<Calendarioreporte> getCon_cre(int conid){
        if (conid!=0){
            con_cre=ServiceFacadeLocator.getFacadeConfiguracion().findCREbyCON(conid);            
        } else{
            con_cre.clear();
        }
        return con_cre;
    }
    
    public void saveCalendarioReporte(Calendarioreporte calendarioreporte){
        ServiceFacadeLocator.getFacadeCalendarioReporte().saveCalendarioReporte(calendarioreporte);
    }

    
    
}
