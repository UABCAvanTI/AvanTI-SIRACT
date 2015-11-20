/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.application.helper;

import java.util.List;
import mx.avanti.siract.business.AlertaDelegate;
import mx.avanti.siract.business.CalendarioreporteDelegate;
import mx.avanti.siract.business.CalendarioreporteTieneAlertaDelegate;
import mx.avanti.siract.business.CicloEscolarDelegate;
import mx.avanti.siract.business.MensajeDelegate;
import mx.avanti.siract.business.ProfesorDelegate;
import mx.avanti.siract.business.UsuarioDelegate;
import mx.avanti.siract.business.entity.Alerta;
import mx.avanti.siract.business.entity.Calendarioreporte;
import mx.avanti.siract.business.entity.CalendarioreporteTieneAlerta;
import mx.avanti.siract.business.entity.Cicloescolar;
import mx.avanti.siract.business.entity.Mensaje;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Usuario;

/**
 *
 * @author Eduardo
 */
public class EnvioAlertasHelper {
    private AlertaDelegate alertaDelegate;
    private CalendarioreporteDelegate calendarioReporteDelegate;
    private CalendarioreporteTieneAlertaDelegate calendarioReporteTieneAlertaDelegate;
    private CicloEscolarDelegate cicloEscolarDelegate;
    private MensajeDelegate mensajeDelegate;
    private ProfesorDelegate profesorDelegate;
    private UsuarioDelegate usuarioDelegate;

    public EnvioAlertasHelper() {
        this.alertaDelegate = new AlertaDelegate();
        this.calendarioReporteDelegate = new CalendarioreporteDelegate();
        this.calendarioReporteTieneAlertaDelegate = new CalendarioreporteTieneAlertaDelegate();
        this.cicloEscolarDelegate = new CicloEscolarDelegate();
        this.mensajeDelegate = new MensajeDelegate();
        this.profesorDelegate = new ProfesorDelegate();
        this.usuarioDelegate = new UsuarioDelegate();
    }
    
    public AlertaDelegate getAlertaDelegate() {
        return alertaDelegate;
    }

    public void setAlertaDelegate(AlertaDelegate alertaDelegate) {
        this.alertaDelegate = alertaDelegate;
    }

    public CalendarioreporteDelegate getCalendarioReporteDelegate() {
        return calendarioReporteDelegate;
    }

    public void setCalendarioReporteDelegate(CalendarioreporteDelegate calendarioReporteDelegate) {
        this.calendarioReporteDelegate = calendarioReporteDelegate;
    }

    public CalendarioreporteTieneAlertaDelegate getCalendarioReporteTieneAlertaDelegate() {
        return calendarioReporteTieneAlertaDelegate;
    }

    public void setCalendarioReporteTieneAlertaDelegate(CalendarioreporteTieneAlertaDelegate calendarioReporteTieneAlertaDelegate) {
        this.calendarioReporteTieneAlertaDelegate = calendarioReporteTieneAlertaDelegate;
    }

    public CicloEscolarDelegate getCicloEscolarDelegate() {
        return cicloEscolarDelegate;
    }

    public void setCicloEscolarDelegate(CicloEscolarDelegate cicloEscolarDelegate) {
        this.cicloEscolarDelegate = cicloEscolarDelegate;
    }

    public MensajeDelegate getMensajeDelegate() {
        return mensajeDelegate;
    }

    public void setMensajeDelegate(MensajeDelegate mensajeDelegate) {
        this.mensajeDelegate = mensajeDelegate;
    }

    public ProfesorDelegate getProfesorDelegate() {
        return profesorDelegate;
    }

    public void setProfesorDelegate(ProfesorDelegate profesorDelegate) {
        this.profesorDelegate = profesorDelegate;
    }

    public UsuarioDelegate getUsuarioDelegate() {
        return usuarioDelegate;
    }

    public void setUsuarioDelegate(UsuarioDelegate usuarioDelegate) {
        this.usuarioDelegate = usuarioDelegate;
    }
    
    public List<Alerta> getAlertas(){
        List<Alerta> alertas=alertaDelegate.getAlertas();
        return alertas;
    }
    
    public List<Calendarioreporte> getCalendarioreportes(){
        List<Calendarioreporte> calendarioreportes=calendarioReporteDelegate.getCalendariosReporte();
        return calendarioreportes;
    }
    
    public List<CalendarioreporteTieneAlerta> getCalendarioreporteTieneAlertas(){
        List<CalendarioreporteTieneAlerta> calendarioreporteTieneAlertas=calendarioReporteTieneAlertaDelegate.getCalendarioReporteTieneAlerta();
        return calendarioreporteTieneAlertas;
    }
    
    public List<Cicloescolar> getCicloEscolar(){
        List<Cicloescolar> cicloescolars=cicloEscolarDelegate.getListaCicloEscolar();
        return cicloescolars;
    }
    
    public List<Mensaje> getMensaje(){
        List<Mensaje> mensajes=mensajeDelegate.getMensajes();
        return mensajes;
    }
    
    public List<Profesor> getProfesor(){
        List<Profesor> profesors=profesorDelegate.getProfesores();
        return profesors;
    }
    
    public List<Usuario> getUsuario(){
        List<Usuario> usuarios=usuarioDelegate.getUsuario();
        return usuarios;
    }
}
