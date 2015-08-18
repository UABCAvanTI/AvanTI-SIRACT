/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Alerta;
import mx.avanti.siract.business.entity.Calendarioreporte;
import mx.avanti.siract.business.entity.Cicloescolar;
import mx.avanti.siract.business.entity.Configuracion;
import mx.avanti.siract.business.entity.Mensaje;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Owner
 */
public class DelegateConfiguracionSistema implements Serializable {

    private List<Configuracion> configuracion;
    private List<Alerta> alerta;
    private List<Mensaje> mensaje;
    private List<Calendarioreporte> reporte;
    private List<Cicloescolar> cicloEscolar;

    public DelegateConfiguracionSistema() {
        configuracion = new ArrayList<Configuracion>();
        alerta = new ArrayList<Alerta>();
        mensaje= new ArrayList<Mensaje>();
        reporte=new ArrayList<Calendarioreporte>();
        cicloEscolar=new ArrayList<Cicloescolar>();

    }

    public List<Configuracion> getConfiguracion() {
        configuracion = ServiceFacadeLocator.getFacadeConfiguracion().findAll();
        return configuracion;
    }
    

    public void saveConfiguracion(Configuracion configuracion) {
        ServiceFacadeLocator.getfacadeCicloEscolarConfig().saveCicloEscolar(configuracion.getCicloescolar());
        ServiceFacadeLocator.getFacadeAlerta().saveAlerta(configuracion.getAlerta());
        ServiceFacadeLocator.getFacadeConfiguracion().saveConfiguracion(configuracion);
    }
    
    public Configuracion buscarConfiguracion(int id){
         return ServiceFacadeLocator.getFacadeConfiguracion().buscarConfiguracion(id);
    }
    
    
    
    public List<Alerta> getAlerta() {
        alerta = ServiceFacadeLocator.getFacadeAlerta().findAll();
        return alerta;
    }

    public void saveAlerta(Alerta alerta) {
//        ServiceFacadeLocator.getFacadeMensaje().saveMensaje(alerta.getMensaje());
        ServiceFacadeLocator.getFacadeAlerta().saveAlerta(alerta);
    }
    
    public List<Mensaje> getMensaje(){
        mensaje=ServiceFacadeLocator.getFacadeMensaje().findAll();
        return mensaje;
    }
    public void saveMensaje(Mensaje mensaje){
        ServiceFacadeLocator.getFacadeMensaje().saveMensaje(mensaje);
    }
    
    
    public List<Calendarioreporte> getCalendarioreporte(){
        reporte=ServiceFacadeLocator.getFacadeCalendarioReporte().findAll();
        return reporte;
    }
    
     public void saveCaledarioReporte(Calendarioreporte calendarioreporte){
        ServiceFacadeLocator.getFacadeCalendarioReporte().saveCalendarioReporte(calendarioreporte);
    }
    //balta
    
     public void setCalendarioReporte(List<Calendarioreporte> reporte) {
        this.reporte = reporte;
    }
    
    public void agregarCalendarioReporte(Calendarioreporte calendarioreporte){
        ServiceFacadeLocator.getFacadeCalendarioReporte().saveCalendarioReporte(calendarioreporte);
       
    }
    
    public void eliminarCalendarioReporte(Calendarioreporte caldarioreporte){
        ServiceFacadeLocator.getFacadeCalendarioReporte().eliminarCalendarioReporte(caldarioreporte);
    }
    
    
    //balta
    
   
    public List<Cicloescolar> getCicloEscolar() {
        cicloEscolar=ServiceFacadeLocator.getfacadeCicloEscolarConfig().findAll();
        return cicloEscolar;
      
    }
    
    public void saveCicloEscolar(Cicloescolar cicloEscolar){
        ServiceFacadeLocator.getfacadeCicloEscolarConfig().saveCicloEscolar(cicloEscolar);
        
    }
}
