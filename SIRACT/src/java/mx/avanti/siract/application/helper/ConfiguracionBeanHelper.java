/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import mx.avanti.siract.business.AlertaDelegate;
import mx.avanti.siract.business.CalendarioreporteDelegate;
import mx.avanti.siract.business.CalendarioreporteTieneAlertaDelegate;
import mx.avanti.siract.business.CicloEscolarDelegate;
import mx.avanti.siract.business.ConfiguracionDelegate;
import mx.avanti.siract.business.MensajeDelegate;
import mx.avanti.siract.business.entity.Alerta;
import mx.avanti.siract.business.entity.Calendarioreporte;
import mx.avanti.siract.business.entity.CalendarioreporteTieneAlerta;
import mx.avanti.siract.business.entity.CalendarioreporteTieneAlertaId;
import mx.avanti.siract.business.entity.Cicloescolar;
import mx.avanti.siract.business.entity.Configuracion;
import mx.avanti.siract.business.entity.Mensaje;
import mx.avanti.siract.integration.persistence.BaseDAO;
/**
 *
 * @author Samanta Rdgz
 */
public class ConfiguracionBeanHelper implements Serializable{
    private Configuracion configuracion;
    private Cicloescolar cicloescolar;
    private Mensaje mensaje;
    private Alerta alertaConf;//S
    private Alerta alerta;//JC
    private Calendarioreporte calendario;
    private Calendarioreporte seleccionCalendario;
    private Configuracion seleccionConfiguracion;
    private List <Cicloescolar> ciclosEscolares;
    private List <Calendarioreporte> calendarioreportes;
    private List <Mensaje> mensajes;
    private List <Alerta> alertas;
    //-------------------------
    private AlertaDelegate alertaDelegate;
    private CalendarioreporteDelegate calendarioReporteDelegate;
    private CalendarioreporteTieneAlertaDelegate calendarioReporteTieneAlertaDelegate;
    private CicloEscolarDelegate cicloEscolarDelegate;
    private ConfiguracionDelegate configuracionDelegate;
    private MensajeDelegate mensajeDelegate;
    
    public ConfiguracionBeanHelper(){
        try{
            this.mensajeDelegate=new MensajeDelegate();
            this.alertaDelegate=new AlertaDelegate();
            this.cicloEscolarDelegate=new CicloEscolarDelegate();
            this.configuracionDelegate=new ConfiguracionDelegate();
            this.calendarioReporteDelegate=new CalendarioreporteDelegate();
            this.calendarioReporteTieneAlertaDelegate=new CalendarioreporteTieneAlertaDelegate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        cicloescolar=new Cicloescolar();
        configuracion=new Configuracion();
        configuracion.setAlerta(new Alerta());
        configuracion.setCicloescolar(new Cicloescolar());
        mensaje=new Mensaje();
        calendario=new Calendarioreporte();
        seleccionCalendario=new Calendarioreporte();
        seleccionConfiguracion=new Configuracion();
        alerta=new Alerta();
        ciclosEscolares=findAllCiclosEscolares();
        mensajes=findAllMensajes();
        alertas=findAllAlertas();
    }
    public Configuracion getConfiguracion(){
        return configuracion;
    }
    public void setConfiguracion(Configuracion configuracion){
        this.configuracion=configuracion;
    }
    public Cicloescolar getCicloescolar(){
        return cicloescolar;
    }
    public void setCicloescolar(Cicloescolar cicloescolar){
        this.cicloescolar=cicloescolar;
    }
    public Mensaje getMensaje(){
        return mensaje;
    }
    public void setMensaje(Mensaje mensaje){
        this.mensaje=mensaje;
    }
    public Alerta getAlerta(){
        return alerta;
    }
    public void setAlerta(Alerta alerta){
        this.alerta=alerta;
    }
    public Alerta getAlertaCorte(){
        return alertaConf;
    }
    public void setAlertaCorte(Alerta alertaCorte){
        this.alertaConf=alertaCorte;
    }
    public Calendarioreporte getCalendario(){
        return calendario;
    }
    public void setCalendario(Calendarioreporte calendario){
        this.calendario=calendario;
    }
    public Calendarioreporte getSeleccionCalendario(){
        return seleccionCalendario;
    }
    public Configuracion getSeleccionConfiguracion(){
        return seleccionConfiguracion;
    }
    public void setSeleccionCalendario(Calendarioreporte seleccionCalendario){
        this.seleccionCalendario=seleccionCalendario;
    }
    public List<Cicloescolar> getCiclosEscolares() {
        return ciclosEscolares;
    }
    public void setCiclosEscolares(List<Cicloescolar> ciclosEscolares) {
        this.ciclosEscolares = ciclosEscolares;
    }
    public List<Calendarioreporte> getCalendarioreportes() {
        return calendarioreportes;
    }
    public void setCalendarioreportes(List<Calendarioreporte> calendarioreportes) {
        this.calendarioreportes = calendarioreportes;
    }
    public List<Mensaje> getMensajes() {
        return mensajes;
    }
    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }
    public List<Alerta> getAlertas() {
        return alertas;
    }
    public void setAlertas(List<Alerta> alertas) {
        this.alertas = alertas;
    }
    
    //-----------------------------------------------------------------------Metodos componente sin lazy
    public List<Cicloescolar> findAllCiclosEscolares(){ // ok
        ciclosEscolares=cicloEscolarDelegate.getListaCicloEscolar();
        List<Cicloescolar> csord=new ArrayList<Cicloescolar>();
        System.out.println(ciclosEscolares.get(1).getCescicloEscolar().substring(0, 4));
        int v1=0, v2=0;
        Cicloescolar auxCES=new Cicloescolar();
        for(int i=0; i<ciclosEscolares.size(); i++)
            for(int k=0; k<ciclosEscolares.size()-1; k++){
                v1=Integer.parseInt(ciclosEscolares.get(k).getCescicloEscolar().substring(0, 4)+""+Integer.parseInt(ciclosEscolares.get(k).getCescicloEscolar().substring(5)));
                v2=Integer.parseInt(ciclosEscolares.get(k+1).getCescicloEscolar().substring(0, 4)+""+Integer.parseInt(ciclosEscolares.get(k+1).getCescicloEscolar().substring(5)));
               // System.out.println(v1+" "+v2);
                if(v2>v1){
                    auxCES=ciclosEscolares.get(k+1);
                    ciclosEscolares.set(k+1, ciclosEscolares.get(k));
                    ciclosEscolares.set(k, auxCES);
                }
            }
//        for(int i=0; i<ciclosEscolares.size(); i++){
//            System.out.println(ciclosEscolares.get(i).getCesid()+" "+ciclosEscolares.get(i).getCescicloEscolar());
//        }
        return ciclosEscolares;
    }
    
    public Configuracion findConfiguracion(int id){ // ok
        cicloescolar=cicloEscolarDelegate.find(id);
        if(configuracionDelegate.bConfPorCiclo(cicloescolar.getCesid())==null){
            configuracion=new Configuracion();
            configuracion.setAlerta(alertaDelegate.findAlerta("General"));
            configuracion.setCicloescolar(cicloescolar);
            configuracion.setConid(cicloescolar.getCesid());
            configuracion.setConnumeroSemanas(16);
            configuracion.setConfechaInicioSemestre(new Date());
        }else{
            configuracion=new Configuracion();
            configuracion=configuracionDelegate.bConfPorCiclo(id);
        }
        calendarioreportes=new ArrayList<Calendarioreporte>();
        return configuracion;
    }
    
    public List<Calendarioreporte> findAllCalendariosReportes(int id){ // ok
        if(configuracion.getConid()==0||configuracion.getConid()==null||configuracion==null){
            System.out.println("configuracion vacia");
            configuracion=new Configuracion();
            configuracion.setAlerta(alertaDelegate.findAlerta("General"));
            configuracion.setCicloescolar(cicloescolar);
        }else{
            calendarioreportes=new ArrayList<Calendarioreporte>();
            calendarioreportes=calendarioReporteDelegate.getCon_cre(id);
        }
        return calendarioreportes;
    }
    
    public boolean guardarCalendarioReporte(Date fechaCorte, Date fechaLimite, int diasCorte, int diasLimite, int diasAtraso){ // ok
        List<Calendarioreporte> cs=calendarioreportes;
        boolean bandera=true;
        for(int i=0; i<cs.size(); i++){
            if(((fechaCorte.equals(cs.get(i).getCrefechaCorte())||fechaCorte.after(cs.get(i).getCrefechaCorte()))&&(fechaCorte.equals(cs.get(i).getCrefechaLimite())||fechaCorte.before(cs.get(i).getCrefechaLimite())))||(fechaLimite.equals(cs.get(i).getCrefechaCorte())||fechaLimite.after(cs.get(i).getCrefechaCorte()))&&(fechaLimite.equals(cs.get(i).getCrefechaLimite())||fechaLimite.before(cs.get(i).getCrefechaLimite()))){
                bandera=false;
                System.out.println(bandera+" ----corte comparacion");
                break;
            }
            if(fechaCorte.before(cs.get(i).getCrefechaCorte())&&fechaLimite.after(cs.get(i).getCrefechaLimite())){
                bandera=false;
                break;
            }
            if(fechaCorte.equals(configuracion.getConfechaInicioSemestre())||fechaLimite.equals(configuracion.getConfechaInicioSemestre())){
                bandera=false;
                break;
            }
            System.out.println("fecha corte= "+fechaCorte.getTime()+" - fecha limite= "+fechaLimite.getTime());
            System.out.println("Fecha Corte BD= "+cs.get(i).getCrefechaCorte().getTime()+" - Fecha Limite BD= "+cs.get(i).getCrefechaLimite().getTime());
        }
        System.out.println(bandera+" ----SAlio del ciclo de traslapes");
        if(bandera){
            Calendarioreporte c=new Calendarioreporte();
            HashSet setCalendarios=new HashSet();
            CalendarioreporteTieneAlerta calendarioreporteTieneAlertaC=new CalendarioreporteTieneAlerta();
            CalendarioreporteTieneAlerta calendarioreporteTieneAlertaL=new CalendarioreporteTieneAlerta();
            CalendarioreporteTieneAlerta calendarioreporteTieneAlertaA=new CalendarioreporteTieneAlerta();

            c.setCreid(0);
            c.setCrefechaCorte(fechaCorte);
            c.setCrefechaLimite(fechaLimite);

            calendarioReporteDelegate.saveCalendarioReporte(c);

            //
            System.out.println(c.getCreid()+" assssssssssssssssssssssssssssssssssssssssssssss");
            calendarioreporteTieneAlertaC.setCalendarioreporte(c);
            calendarioreporteTieneAlertaC.setAlerta(alertaDelegate.findAlerta("Pre-corte"));
            calendarioreporteTieneAlertaC.setCaldias(diasCorte);
            calendarioreporteTieneAlertaC.setCalnumeroReporte(calendarioreportes.size()+1);
            CalendarioreporteTieneAlertaId calendarioreporteTieneAlertaIdC=new CalendarioreporteTieneAlertaId(c.getCreid(), alertaDelegate.findAlerta("Pre-corte").getAleid());
            calendarioreporteTieneAlertaC.setId(calendarioreporteTieneAlertaIdC);
            calendarioReporteTieneAlertaDelegate.saveCalendarioreporteTieneAlerta(calendarioreporteTieneAlertaC);
            
            calendarioreporteTieneAlertaL.setCalendarioreporte(c);
            calendarioreporteTieneAlertaL.setAlerta(alertaDelegate.findAlerta("Pre-limite"));
            calendarioreporteTieneAlertaL.setCaldias(diasLimite);
            calendarioreporteTieneAlertaL.setCalnumeroReporte(calendarioreportes.size()+1);
            CalendarioreporteTieneAlertaId calendarioreporteTieneAlertaIdL=new CalendarioreporteTieneAlertaId(c.getCreid(), alertaDelegate.findAlerta("Pre-limite").getAleid());
            calendarioreporteTieneAlertaL.setId(calendarioreporteTieneAlertaIdL);
            calendarioReporteTieneAlertaDelegate.saveCalendarioreporteTieneAlerta(calendarioreporteTieneAlertaL);

            calendarioreporteTieneAlertaA.setCalendarioreporte(c);
            calendarioreporteTieneAlertaA.setAlerta(alertaDelegate.findAlerta("Atraso"));
            calendarioreporteTieneAlertaA.setCaldias(diasAtraso);
            calendarioreporteTieneAlertaA.setCalnumeroReporte(calendarioreportes.size()+1);
            CalendarioreporteTieneAlertaId calendarioreporteTieneAlertaIdA=new CalendarioreporteTieneAlertaId(c.getCreid(), alertaDelegate.findAlerta("Atraso").getAleid());
            calendarioreporteTieneAlertaA.setId(calendarioreporteTieneAlertaIdA);
            calendarioReporteTieneAlertaDelegate.saveCalendarioreporteTieneAlerta(calendarioreporteTieneAlertaA);
            //

            for(int x=0; x<calendarioreportes.size(); x++)
                setCalendarios.add(calendarioreportes.get(x));
            setCalendarios.add(c);
            configuracion.setCalendarioreportes(setCalendarios);
            configuracionDelegate.saveConfiguracion(configuracion);
            findAllCalendariosReportes(configuracion.getConid());
            c=new Calendarioreporte();
            return true;
        }else{
            return false;
        }
    }
    
    public void eliminarCalendarioReporte(Calendarioreporte cr){ //Correcto // ok
        Calendarioreporte c=new Calendarioreporte();
        c=null;
        int asd=0;
        while(c==null){
            System.out.println("estoy aqui . . . aun: "+cr.getCreid());
            c=calendarioReporteDelegate.find(cr.getCreid());
            asd++;
        if(asd==100)
            break;
        }
        System.out.println("sali");
        calendarioReporteDelegate.deleteCalendarioreporte(c);
        c=new Calendarioreporte();
        findAllCalendariosReportes(configuracion.getConid());
    }
    
    
    public Calendarioreporte modificacionCalendarioReporte(Calendarioreporte cr){//nuevo, bueno? // ok
        Calendarioreporte c=new Calendarioreporte();
        c=calendarioReporteDelegate.find(cr.getCreid());
        HashSet setCRTA=new HashSet();
        setCRTA.add(calendarioReporteTieneAlertaDelegate.getCalendarioReporteTieneAlertas(cr.getCreid(), alertaDelegate.findAlerta("Pre-corte").getAleid()));
        setCRTA.add(calendarioReporteTieneAlertaDelegate.getCalendarioReporteTieneAlertas(cr.getCreid(), alertaDelegate.findAlerta("Atraso").getAleid()));
        setCRTA.add(calendarioReporteTieneAlertaDelegate.getCalendarioReporteTieneAlertas(cr.getCreid(), alertaDelegate.findAlerta("Pre-limite").getAleid()));
        c.setCalendarioreporteTieneAlertas(setCRTA);
        return c;
    }

    public List<Mensaje> findAllMensajes(){ // ok
        mensajes=mensajeDelegate.getMensajes();
        return mensajes;
    }
    
    public void guardarMensaje(Mensaje mensaje){ // ok
        mensajeDelegate.saveMensaje(mensaje);
    }
    
    public List<Alerta> findAllAlertas(){ // ok
        alertas=alertaDelegate.getAlertas();
        System.out.println(alertas.size());
        return alertas;
    }
    
    public void guardarAlertas(){ // ok
        alertaDelegate.saveAlertas(alertas);
    }
    
    public void guardarConfiguracion(){ // ok
        configuracionDelegate.saveConfiguracion(configuracion);
        configuracion=configuracionDelegate.findConfiguracion(configuracion.getConid());
        
    }
    
    public boolean modificarCalendarioReporteOK(Calendarioreporte cr, Date fechaCorte, Date fechaLimite, int diasCorte, int diasLimite, int diasAtraso){//nuevo, bueno?!
        List<Calendarioreporte> cs=calendarioreportes;
        boolean bandera=true;
        for(int i=0; i<cs.size(); i++){
            if(getSeleccionCalendario().getCreid()==cs.get(i).getCreid()){
                System.out.println("No me comparo conmigo mismo :D");
            }else{
                if(((fechaCorte.equals(cs.get(i).getCrefechaCorte())||fechaCorte.after(cs.get(i).getCrefechaCorte()))&&(fechaCorte.equals(cs.get(i).getCrefechaLimite())||fechaCorte.before(cs.get(i).getCrefechaLimite())))||(fechaLimite.equals(cs.get(i).getCrefechaCorte())||fechaLimite.after(cs.get(i).getCrefechaCorte()))&&(fechaLimite.equals(cs.get(i).getCrefechaLimite())||fechaLimite.before(cs.get(i).getCrefechaLimite()))){
                    bandera=false;
                    break;
                }
                if(fechaCorte.before(cs.get(i).getCrefechaCorte())&&fechaLimite.after(cs.get(i).getCrefechaLimite())){
                    bandera=false;
                    break;
                }
                if(fechaCorte.equals(configuracion.getConfechaInicioSemestre())||fechaLimite.equals(configuracion.getConfechaInicioSemestre())){
                    bandera=false;
                    break;
                }
            }
        }
        if(bandera){
            Calendarioreporte c=new Calendarioreporte();
            c=calendarioReporteDelegate.find(cr.getCreid());
            c.setCrefechaCorte(fechaCorte);
            c.setCrefechaLimite(fechaLimite);

            CalendarioreporteTieneAlerta calendarioreporteTieneAlertaC=new CalendarioreporteTieneAlerta();
            CalendarioreporteTieneAlerta calendarioreporteTieneAlertaL=new CalendarioreporteTieneAlerta();
            CalendarioreporteTieneAlerta calendarioreporteTieneAlertaA=new CalendarioreporteTieneAlerta();

            calendarioReporteDelegate.saveCalendarioReporte(c);

            //
            System.out.println(c.getCreid()+" assssssssssssssssssssssssssssssssssssssssssssss");
            calendarioreporteTieneAlertaC.setCalendarioreporte(c);
            calendarioreporteTieneAlertaC.setAlerta(alertaDelegate.findAlerta("Pre-corte"));
            calendarioreporteTieneAlertaC.setCaldias(diasCorte);
            calendarioreporteTieneAlertaC.setCalnumeroReporte(1);
            CalendarioreporteTieneAlertaId calendarioreporteTieneAlertaIdC=new CalendarioreporteTieneAlertaId(c.getCreid(), alertaDelegate.findAlerta("Pre-corte").getAleid());
            calendarioreporteTieneAlertaC.setId(calendarioreporteTieneAlertaIdC);
            calendarioReporteTieneAlertaDelegate.saveCalendarioreporteTieneAlerta(calendarioreporteTieneAlertaC);

            calendarioreporteTieneAlertaL.setCalendarioreporte(c);
            calendarioreporteTieneAlertaL.setAlerta(alertaDelegate.findAlerta("Pre-limite"));
            calendarioreporteTieneAlertaL.setCaldias(diasLimite);
            calendarioreporteTieneAlertaL.setCalnumeroReporte(1);
            CalendarioreporteTieneAlertaId calendarioreporteTieneAlertaIdL=new CalendarioreporteTieneAlertaId(c.getCreid(), alertaDelegate.findAlerta("Pre-limite").getAleid());
            calendarioreporteTieneAlertaL.setId(calendarioreporteTieneAlertaIdL);
            calendarioReporteTieneAlertaDelegate.saveCalendarioreporteTieneAlerta(calendarioreporteTieneAlertaL);

            calendarioreporteTieneAlertaA.setCalendarioreporte(c);
            calendarioreporteTieneAlertaA.setAlerta(alertaDelegate.findAlerta("Atraso"));
            calendarioreporteTieneAlertaA.setCaldias(diasAtraso);
            calendarioreporteTieneAlertaA.setCalnumeroReporte(1);
            CalendarioreporteTieneAlertaId calendarioreporteTieneAlertaIdA=new CalendarioreporteTieneAlertaId(c.getCreid(), alertaDelegate.findAlerta("Atraso").getAleid());
            calendarioreporteTieneAlertaA.setId(calendarioreporteTieneAlertaIdA);
            calendarioReporteTieneAlertaDelegate.saveCalendarioreporteTieneAlerta(calendarioreporteTieneAlertaA);
            //
            findAllCalendariosReportes(configuracion.getConid());
            return true;
        }else{
            return false;
        }
    }
}