package mx.avanti.siract.ui;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.avanti.siract.application.helper.ConfiguracionBeanHelper;
import mx.avanti.siract.business.entity.Alerta;
import mx.avanti.siract.business.entity.Calendarioreporte;
import mx.avanti.siract.business.entity.CalendarioreporteTieneAlerta;
import mx.avanti.siract.business.entity.Cicloescolar;
import mx.avanti.siract.business.entity.Configuracion;
import mx.avanti.siract.business.entity.Mensaje;
import mx.avanti.siract.integration.persistence.BaseDAO;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
public class ConfiguracionBeanUI implements Serializable{

    private ConfiguracionBeanHelper configuracionBeanHelper=null;
    //Estos booleanos determinan el estado de los botones de la interfaz
    private boolean botonDel=true, botonMod=true, ns=true, envio=true, botonAdd=true, botonAceptar=true, seleccionCal=true;

    //-------------------------------------------------------------------------------------------------------------Inicia componente sin lazy
    Date fechaCorte=new Date();
    Date fechaLimite=new Date();
    
    int diasCorte=7, diasLimite=7, diasAtraso=1;
    Mensaje mensaje=new Mensaje();
    //
    Date fechaCorteM=new Date();
    Date fechaLimiteM=new Date();
    int diasCorteM=7, diasLimiteM=7, diasAtrasoM=1;
    //
    Date fechaMinSemestre=new Date();
    Date fechaMaxSemestre=new Date();
    Date fechaMinCR=new Date();
    boolean habilitarFechaLimite=true;
    boolean habilitarFechaLimiteM=true;
    
    int ssss;

    public int getSsss() {
        return ssss;
    }

    public void setSsss(int ssss) {
        this.ssss = ssss;
    }
    
    
    
    public Date getFechaCorte() {
        return fechaCorte;
    }
    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }
    public Date getFechaLimite() {
        return fechaLimite;
    }
    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
    public int getDiasCorte() {
        return diasCorte;
    }
    public void setDiasCorte(int diasCorte) {
        this.diasCorte = diasCorte;
    }
    public int getDiasLimite() {
        return diasLimite;
    }
    public void setDiasLimite(int diasLimite) {
        this.diasLimite = diasLimite;
    }
    public int getDiasAtraso() {
        return diasAtraso;
    }
    public void setDiasAtraso(int diasAtraso) {
        this.diasAtraso = diasAtraso;
    }
    public Mensaje getMensaje() {
        return mensaje;
    }
    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }
    public Date getFechaCorteM() {
        return fechaCorteM;
    }
    public void setFechaCorteM(Date fechaCorteM) {
        this.fechaCorteM = fechaCorteM;
    }
    public Date getFechaLimiteM() {
        return fechaLimiteM;
    }
    public void setFechaLimiteM(Date fechaLimiteM) {
        this.fechaLimiteM = fechaLimiteM;
    }
    public int getDiasCorteM() {
        return diasCorteM;
    }
    public void setDiasCorteM(int diasCorteM) {
        this.diasCorteM = diasCorteM;
    }
    public int getDiasLimiteM() {
        return diasLimiteM;
    }
    public void setDiasLimiteM(int diasLimiteM) {
        this.diasLimiteM = diasLimiteM;
    }
    public int getDiasAtrasoM() {
        return diasAtrasoM;
    }
    public void setDiasAtrasoM(int diasAtrasoM) {
        this.diasAtrasoM = diasAtrasoM;
    }
    public Date getFechaMinSemestre() {
        return fechaMinSemestre;
    }
    public void setFechaMinSemestre(Date fechaMinSemestre) {
        this.fechaMinSemestre = fechaMinSemestre;
    }
    public Date getFechaMaxSemestre() {
        return fechaMaxSemestre;
    }
    public void setFechaMaxSemestre(Date fechaMaxSemestre) {
        this.fechaMaxSemestre = fechaMaxSemestre;
    }

    public Date getFechaMinCR() {
        return fechaMinCR;
    }

    public void setFechaMinCR(Date fechaMinCR) {
        this.fechaMinCR = fechaMinCR;
    }

    public boolean isHabilitarFechaLimite() {
        return habilitarFechaLimite;
    }

    public void setHabilitarFechaLimite(boolean habilitarFechaLimite) {
        this.habilitarFechaLimite = habilitarFechaLimite;
    }
    public boolean isHabilitarFechaLimiteM() {
        return habilitarFechaLimiteM;
    }

    public void setHabilitarFechaLimiteM(boolean habilitarFechaLimiteM) {
        this.habilitarFechaLimiteM = habilitarFechaLimiteM;
    }
    
    
    //-------------------------------------------------------------------Metodos listeners para la vista
    public void changeCicloEscolarListener(int cesID){
        System.out.println(cesID);
        if(cesID==0){
            ns=true;
            envio=true;
            botonAdd=true;
            botonDel=true;
            botonMod=true;
            configuracionBeanHelper.setConfiguracion(new Configuracion(new Date(), 16));
            List <Calendarioreporte> cs=new ArrayList<Calendarioreporte>();
            configuracionBeanHelper.setCalendarioreportes(cs);
            System.out.println("asdasdsad 00");   
            System.out.println(ns+" "+envio+" "+botonAdd);
        }else{
            ns=false;
            envio=false;
            botonAdd=false;
            configuracionBeanHelper.findConfiguracion(cesID);
        }
        changeCalendarioReporte();
        //

        //
        String ciclo=configuracionBeanHelper.getCicloescolar().getCescicloEscolar();
        int tipo=Integer.parseInt(ciclo.substring(5));
        int year=Integer.parseInt(configuracionBeanHelper.getCicloescolar().getCescicloEscolar().substring(0, 4));
        System.out.println(tipo+" "+year);
        fechaMinSemestre.setYear(year-1900);
        fechaMaxSemestre.setYear(year-1900);
        if(tipo==1){
                fechaMinSemestre.setMonth(1);
                fechaMaxSemestre.setMonth(5);
        }
        if(tipo==2){
                fechaMinSemestre.setMonth(7);
                fechaMaxSemestre.setMonth(11);
        }
        if(tipo==3){
                fechaMinSemestre.setMonth(11);
                fechaMaxSemestre.setMonth(0);
        }
        if(tipo==5){
                fechaMinSemestre.setMonth(5);
                fechaMaxSemestre.setMonth(6);
        }
        fechaMinSemestre.setDate(1);
        fechaMaxSemestre.setDate(30);
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY");
        System.out.println(sdf.format(fechaMinSemestre)+" "+sdf.format(fechaMaxSemestre));
    }
    public void changeCalendarioReporte(){
        try{
            if(configuracionBeanHelper.getConfiguracion().getConid()==null){
                configuracionBeanHelper.findAllCalendariosReportes(1);
            }else{
                configuracionBeanHelper.findAllCalendariosReportes(configuracionBeanHelper.getConfiguracion().getConid());
            }
        }catch(Exception e){
            
        }
    }
    public void guardarCalendarioReporte(){
        SimpleDateFormat fmt=new SimpleDateFormat("yyyyMMdd");
        if(fmt.format(fechaCorte).equals(fmt.format(new Date()))||fmt.format(fechaCorte).equals(fmt.format(fechaLimite))||fmt.format(fechaLimite).equals(fmt.format(new Date()))){
            growler("Las fechas son iguales o es el día de hoy ", "Revise las fechas");
        }else{
            boolean bolTraslape;
            bolTraslape=configuracionBeanHelper.guardarCalendarioReporte(fechaCorte, fechaLimite, diasCorte, diasLimite, diasAtraso);
            if(bolTraslape){
                growler("Se guardó correctamente ", " ");
            }else{
               growler("Se encontró traslape ", "Revise las fechas");
            }
        }
        setHabilitarFechaLimite(true);
    }
    public void modificarCalendarioReporte(){
        SimpleDateFormat fmt=new SimpleDateFormat("yyyyMMdd");
        if(fmt.format(fechaCorteM).equals(fmt.format(new Date()))||fmt.format(fechaCorteM).equals(fmt.format(fechaLimiteM))||fmt.format(fechaLimiteM).equals(fmt.format(new Date()))){
            growler("Las fechas son iguales o es el día de hoy", "Revise las fechas");
        }else{
            if(fechaCorteM.after(fechaLimiteM)){
                growler("La fecha de corte es mayor a limite", "Revise las fechas");
            }else{
                boolean bolTraslape;
                bolTraslape=configuracionBeanHelper.modificarCalendarioReporteOK(calendarioSeleccionado ,fechaCorteM, fechaLimiteM, diasCorteM, diasLimiteM, diasAtrasoM);
                if(bolTraslape){
                    growler("Se guardó correctamente ", " ");
                }else{
                    growler("Se encontro traslape ", "Revise las fechas");
                }
            }
        }
        setHabilitarFechaLimiteM(true);
    }
    Calendarioreporte calendarioSeleccionado=new Calendarioreporte();
    public void eliminarCalendarioReporte(){
        //configuracionBeanHelper.eliminarCalendarioReporte();
        configuracionBeanHelper.eliminarCalendarioReporte(calendarioSeleccionado);
        calendarioSeleccionado=new Calendarioreporte();
        growler("Se eliminó correctamente ", " ");
    }
    public void modificarCalendarioReporteListener(){
        Calendarioreporte c=new Calendarioreporte();
        //c=configuracionBeanHelper.modificacionCalendarioReporte();
        c=configuracionBeanHelper.modificacionCalendarioReporte(calendarioSeleccionado);
        setFechaCorteM(c.getCrefechaCorte());
        setFechaLimiteM(c.getCrefechaLimite());
        List<CalendarioreporteTieneAlerta> ctas=new ArrayList<CalendarioreporteTieneAlerta>();
        ctas.addAll(c.getCalendarioreporteTieneAlertas());
        for(int i=0; i<ctas.size(); i++){
            System.out.println("alertaid modificacion asdasd= "+ctas.get(i).getAlerta().getAleid());
            if(ctas.get(i).getAlerta().getAleid()==2)
                setDiasCorteM(ctas.get(i).getCaldias());
            if(ctas.get(i).getAlerta().getAleid()==3)
                setDiasAtrasoM(ctas.get(i).getCaldias());
            if(ctas.get(i).getAlerta().getAleid()==4)
                setDiasLimiteM(ctas.get(i).getCaldias());
        }
        ctas=new ArrayList<>();
        System.out.println("CorteM-"+getDiasCorteM());
        System.out.println("AtrasoM-"+getDiasAtrasoM());
        System.out.println("LimiteM-"+getDiasLimiteM());
    }
    public void changeMensajeListener(int menID){
        if(menID!=0){
            Alerta alerta=new Alerta();
            BaseDAO baseDAO=new BaseDAO();
            baseDAO.setTipo(Mensaje.class);
            mensaje=(Mensaje)baseDAO.find(menID);
            baseDAO.setTipo(Alerta.class);
            alerta=(Alerta)baseDAO.find(menID);
            mensaje.setAlerta(alerta);
        }else{
            setMensaje(new Mensaje(new Alerta(), " "));
            System.out.println("Selecciona mensaje asdasdasdasdasdasdasdasd");
        }
    }
    public void guardarMensaje(){
        if(mensaje.getMenid()!=0){
            if(mensaje.getMenmensaje()==null||mensaje.getMenmensaje()==" "||mensaje.getMenmensaje().isEmpty()){
                growler("Configuración de Mensaje Vacía", " ");
            }else{
                configuracionBeanHelper.guardarMensaje(mensaje);
                growler("Configuración de Mensaje Guardada", " ");
            }
        }
    }
    public void guardarAlertas(){
        configuracionBeanHelper.guardarAlertas();
        growler("Configuración de Alerta Guardada", " ");
    }
    public void agregarNuevoCR(){
        System.out.println(getDiasAtraso());
        setFechaCorte(new Date());
        setFechaLimite(new Date());
        setDiasCorte(7);
        setDiasLimite(7);
        setDiasAtraso(1);
        System.out.println(getDiasAtraso());
    }
    public void guardarConfiguracion(){
        SimpleDateFormat fmt=new SimpleDateFormat("yyyyMMdd");
        System.out.println(configuracionBeanHelper.getCicloescolar().getCesid());
        System.out.println(configuracionBeanHelper.getConfiguracion().getConnumeroSemanas());
        int asd=0;
        if(configuracionBeanHelper.getConfiguracion().getConnumeroSemanas()<=0){
            growler("Número de semanas no puede ser negativo", " ");
        }
        if((fmt.format(configuracionBeanHelper.getConfiguracion().getConfechaInicioSemestre()).equals(fmt.format(new Date())))){
            growler("El fecha de envío no puede ser el dia de hoy", " ");
        }else{
            configuracionBeanHelper.guardarConfiguracion();
            growler("Configuración General Guardada", " ");
        }
        
    }
    //
    public void onRowSelect(SelectEvent event){
        Calendarioreporte calendarioreporte=new Calendarioreporte();
        calendarioreporte=(Calendarioreporte)event.getObject();
        botonDel=false;
        botonMod=false;
        System.out.println(calendarioreporte.getCreid());
        calendarioSeleccionado=calendarioreporte;
    }
    
    public void onCloseDialogMensajes(){
        System.out.println("Cerraste mensajes :D");
        setMensaje(new Mensaje(new Alerta(), ""));
        Mensaje m=new Mensaje();
        m.setAlerta(new Alerta());
        m.setMenid(0);
        m.setMenmensaje(" ");
        configuracionBeanHelper.setMensaje(m);
    }
    //-------------------------------------------------------------------------------------------------------------Termina componente sin lazy
    
    public ConfiguracionBeanUI(){
        init();
    }

    private void init(){
        configuracionBeanHelper=new ConfiguracionBeanHelper();
    }

//----Getters y Setters----//
    public ConfiguracionBeanHelper getConfiguracionBeanHelper(){
        return configuracionBeanHelper;
    }
    public void setConfiguracionBeanHelper(ConfiguracionBeanHelper configuracionBeanHelper){
        this.configuracionBeanHelper=configuracionBeanHelper;
    }
    //Getters para los booleanos de la interfaz
    public boolean isBotonDel(){
        return botonDel;
    }
    public boolean isBotonMod(){
        return botonMod;
    }
    public boolean isNs(){
        return ns;
    }
    public boolean isEnvio(){
        return envio;
    }
    public boolean isBotonAdd(){
        return botonAdd;
    }
    public boolean isBotonAceptar(){
        return botonAceptar;
    }
    public boolean isSeleccionCal(){
        return seleccionCal;
    }
    
//----Listeners----//
    public void habilitador(boolean estado){
        //Botones de Eliminar y Modificar
        botonDel=estado;
        botonMod=estado;
    }
    public void habilitarAceptar(boolean estado){
        //Afecta el estado del botón de Aceptar
        botonAceptar=estado;
    }    
    //Método manejador de growls
    public void growler(String tituloGrowl, String mensajeGrowl){
        FacesContext contexto=FacesContext.getCurrentInstance();
        contexto.addMessage(null, new FacesMessage(tituloGrowl, mensajeGrowl));
    }
    
//Alertas
    EnvioAlertaThread envioAlertaThread=new EnvioAlertaThread();
    public void ejecutarAlertas(boolean estado){
       // envioAlertaThread.revisarAlertas(estado);
        envioAlertaThread.estatusAlertas(estado);
    }
}
