/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mx.avanti.siract.business.CatalogoReportesDelegate;
import mx.avanti.siract.business.ConsultaDelegate;
import mx.avanti.siract.business.PlanEstudioDelegate;
import mx.avanti.siract.business.ProgramaEducativoDelegate;
import mx.avanti.siract.business.ReporteavancecontenidotematicoDelegate;
import mx.avanti.siract.business.UsuarioDelegate;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Calendarioreporte;
import mx.avanti.siract.business.entity.CalendarioreporteTieneAlerta;
import mx.avanti.siract.business.entity.Catalogoreportes;
import mx.avanti.siract.business.entity.Configuracion;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Reporteavancecontenidotematico;
import mx.avanti.siract.business.entity.Rol;
import mx.avanti.siract.business.entity.UnidadaprendizajeImparteProfesor;
import mx.avanti.siract.business.entity.Usuario;


/**
 *
 * @author Moises
 */
public class FiltrosBeanHelper implements Serializable{
    private ConsultaDelegate consultaDelegate;
    
    public FiltrosBeanHelper(){
        try{
            this.consultaDelegate = new ConsultaDelegate();

        } catch(Exception ex){
            ex.printStackTrace();
        }
                reporteAvanceContenidoTematicoDelegate = new ReporteavancecontenidotematicoDelegate();
        listaAux = new ArrayList<ReporteAvanceAux>();
        catalogoreportesDelegate = new CatalogoReportesDelegate();
        programaeducativoDelegate = new ProgramaEducativoDelegate();
        planEstudioDelegate = new PlanEstudioDelegate();
        usuarioDelegate = new UsuarioDelegate();
    }

    public ConsultaDelegate getConsultaDelegate() {
        return consultaDelegate;
    }
    
    //PARTE ENVIADA POR GENERADOR DE REPORTES
    
    
    
    private ReporteavancecontenidotematicoDelegate reporteAvanceContenidoTematicoDelegate;
    private CatalogoReportesDelegate catalogoreportesDelegate;
    private UsuarioDelegate usuarioDelegate;
    private List<Reporteavancecontenidotematico> listaReporte;
    ArrayList<ReporteAvanceAux> listaAux;
    private String op; 
    private String tipo;
    private int calnumeroReporte;
    private int numRact; 
    private String cescicloEscolar;
    private int acoclave;
    private int clavepe;
    private String pesvigencia;
    private int numProfUIPid;
    private Date fecha1;
    private int pronumeroEmpleado;
    private int gponumero;
    private int clave;
    private int uapclave;
    private int uacclave;
    public int creid;
    
    
    private void init(){
       reporteAvanceContenidoTematicoDelegate = new ReporteavancecontenidotematicoDelegate();
       listaAux = new ArrayList<ReporteAvanceAux>();
       catalogoreportesDelegate = new CatalogoReportesDelegate();
       op=null;
       tipo=null;
       calnumeroReporte=0;
       numRact=0;
       cescicloEscolar=null;
       acoclave=0;
       clavepe=0;
       pesvigencia=null;
       numProfUIPid=0;
       fecha1=null;
       pronumeroEmpleado=0;
       gponumero=0;
       clave=0;
       uapclave=0;
       uacclave=0;
       creid=0;
    }  

    public Usuario mostrarUsuario(Usuario usuario,Rol rolSeleccionado){
//        Usuario usuarioSelec=usuarioDelegate.getUsuario(usuario.getUsuid());
         Usuario usuarioSelec=usuarioDelegate.getSeleccionUsuario();
        return usuarioSelec;
    }
    
    public ReporteAux fijarAtributosReporte(ReporteAux reporteUI,String tipoReporte){
        ReporteAux reporteSet = new ReporteAux();
        
        if(tipoReporte.equalsIgnoreCase("ProgEd")){
            reporteSet = new ReporteAux();
            //los atributos de esta clase toman los mismos valores del
            //parametro recibido en este mismo metodo desde el beanUI
            setNumRact(reporteUI.getNumRact());
            setCescicloEscolar(reporteUI.getCescicloEscolar());            
            setClavepe(reporteUI.getClavepe());
            setPesvigencia(reporteUI.getPesvigencia());
            
            //atributos necesarios para entregados en el objeto que se manda al
            //delegate
            reporteSet.setNumRact(numRact);//aqui cambie para hacer consulta al ract1
            reporteSet.setCescicloEscolar(cescicloEscolar);            
            reporteSet.setClavepe(clavepe);
            reporteSet.setPesvigencia(pesvigencia);                        
            reporteSet.setClave(clavepe);            
        }
        if(tipoReporte.equalsIgnoreCase("AreaCon")){
            reporteSet = new ReporteAux();
            //los atributos de esta clase toman los mismos valores del
            //parametro recibido en este mismo metodo desde el beanUI
            setNumRact(reporteUI.getNumRact());
            setCescicloEscolar(reporteUI.getCescicloEscolar());
            setAcoclave(reporteUI.getAcoclave());
            setClavepe(reporteUI.getClavepe());
            setPesvigencia(reporteUI.getPesvigencia());
            
            //atributos necesarios para entregados en el objeto que se manda al
            //delegate
            reporteSet.setNumRact(numRact);//aqui cambie para hacer consulta al ract1
            reporteSet.setCescicloEscolar(cescicloEscolar);
            reporteSet.setAcoclave(acoclave);
            reporteSet.setClavepe(clavepe);
            reporteSet.setPesvigencia(pesvigencia);                       
            reporteSet.setClave(clavepe);            
        }
        if(tipoReporte.equalsIgnoreCase("UAGrupo")){
            reporteSet = new ReporteAux();
            //los atributos de esta clase toman los mismos valores del
            //parametro recibido en este mismo metodo desde el beanUI
            setNumRact(reporteUI.getNumRact());
            setCescicloEscolar(reporteUI.getCescicloEscolar());
            setAcoclave(reporteUI.getAcoclave());
            setClavepe(reporteUI.getClavepe());
            setPesvigencia(reporteUI.getPesvigencia());            
            setGponumero(reporteUI.getGponumero());
            setUapclave(reporteUI.getUapclave());

            //atributos necesarios para entregados en el objeto que se manda al
            //delegate
            reporteSet.setNumRact(numRact);//aqui cambie para hacer consulta al ract1
            reporteSet.setCescicloEscolar(cescicloEscolar);
            reporteSet.setAcoclave(acoclave);
            reporteSet.setClavepe(clavepe);
            reporteSet.setPesvigencia(pesvigencia);            
            reporteSet.setGponumero(gponumero);
            reporteSet.setClave(clavepe);
            reporteSet.setUapclave(uapclave);
        }
        if (tipoReporte.equalsIgnoreCase("ProfGrupo")) {
            reporteSet = new ReporteAux();
            //los atributos de esta clase toman los mismos valores del
            //parametro recibido en este mismo metodo desde el beanUI
            setNumRact(reporteUI.getNumRact());
            setCescicloEscolar(reporteUI.getCescicloEscolar());
            setAcoclave(reporteUI.getAcoclave());
            setClavepe(reporteUI.getClavepe());
            setPesvigencia(reporteUI.getPesvigencia());
            setPronumeroEmpleado(reporteUI.getPronumeroEmpleado());
            setGponumero(reporteUI.getGponumero());
            setUapclave(reporteUI.getUapclave());

            //atributos necesarios para entregados en el objeto que se manda al
            //delegate
            reporteSet.setNumRact(numRact);//aqui cambie para hacer consulta al ract1
            reporteSet.setCescicloEscolar(cescicloEscolar);
            reporteSet.setAcoclave(acoclave);
            reporteSet.setClavepe(clavepe);
            reporteSet.setPesvigencia(pesvigencia);
            reporteSet.setPronumeroEmpleado(pronumeroEmpleado);
            reporteSet.setGponumero(gponumero);
            reporteSet.setClave(clavepe);
            reporteSet.setUapclave(uapclave);
        }
        
        return reporteSet;
    }
     
    
    public String guardarConsulta(ReporteAux reporteUI,String tipoReporteUI,String CTRnombreUI){
          
        ReporteAux reporte = new ReporteAux();      
        
        //Opciones tipoReporteUI:
        
        //ATiempoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("ATiempoAreaCon")){
             
            reporte = new ReporteAux();
            
            reporte = fijarAtributosReporte(reporteUI,"AreaCon"); 
        }
        
        //ATiempoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoAreaConTodosRacts")){
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //ATiempoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("ATiempoProfGrupo")){
            reporte = new ReporteAux();
                    
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");        
        }
        
        //ATiempoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //ATiempoProgEd
        if(tipoReporteUI.equalsIgnoreCase("ATiempoProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //ATiempoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //ATiempoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("ATiempoUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //ATiempoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //ATiempoYNoYEnLimiteAreaCon
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteAreaCon")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //ATiempoYNoYEnLimiteAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteAreaConTodosRacts")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");        
        }
        
        //ATiempoYNoYEnLimiteProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProfGrupo")){
            reporte = new ReporteAux();
                
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //ATiempoYNoYEnLimiteProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //ATiempoYNoYEnLimiteProgEd
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //ATiempoYNoYEnLimiteProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //ATiempoYNoYEnLimiteUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //ATiempoYNoYEnLimiteUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteUAGrupoTodosRacts")){
            reporte = new ReporteAux();        

            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //PAGCAreaCon
        if(tipoReporteUI.equalsIgnoreCase("PAGCAreaCon")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //PAGCAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCAreaConTodosRacts")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //PAGCCompletoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoAreaConTodosRacts")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //PAGCCompletoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoAreaCon")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");        
        }
        
        //PAGCompletoEIncompletoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoAreaCon")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //PAGCompletoEIncompletoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //PAGCompletoEIncompletoProgEd
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //PAGCompletoEIncompletoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //PAGCompletoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //PAGCompletoProgEd
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //PAGCompletoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //PAGCompletosYNoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoAreaConTodosRacts")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //PAGCompletosYNoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //PAGCompletosYNoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoProgEdTodosRacts")){
            reporte = new ReporteAux();        
            
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //PAGCompletosYNoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //PAGCProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCProfGrupo")){
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //PAGCProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //PAGCCompletoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //PAGCProgEd
        if(tipoReporteUI.equalsIgnoreCase("PAGCProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //PAGCProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //PAGCCompletoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoProgEdTodosRacts")){
            reporte = new ReporteAux();                
            
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //PAGCUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //PAGCUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //PAGCCompletoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //enFechaLimiteTiempoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoAreaCon")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");        
        }
        
        //enFechaLimiteTiempoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoAreaConTodosRacts")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");        
        }
        
        //enFechaLimiteTiempoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //enFechaLimiteTiempoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //enFechaLimiteTiempoProgEd
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //enFechaLimiteTiempoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //enFechaLimiteTiempoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //enFechaLimiteTiempoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //entregadosAreaCon
        if(tipoReporteUI.equalsIgnoreCase("entregadosAreaCon")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //entregadosAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosAreaConTodosRacts")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //noEntregadosAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosAreaConTodosRacts")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //entregadosProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("entregadosProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //entregadosProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //noEntregadosProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //entregadosProgEd
        if(tipoReporteUI.equalsIgnoreCase("entregadosProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");        
        }
        
        //entregadosProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //noEntregadosProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //entregadosUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("entregadosUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //entregadosUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
       
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //noEntregadosUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
            
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //entregadosYNoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoAreaConTodosRacts")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //entregadosYNoEntregadosAreaCon
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosAreaCon")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //entregadosYNoEntregadosProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //entregadosYNoEntregadosProgEd
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //entregadosYNoEntregadosUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //entregadosYNoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");        
        }
        
        //entregadosYNoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //entregadosYNoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //fueraTiempoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoAreaCon")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");        
        }
        
        //fueraTiempoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoAreaConTodosRacts")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //fueraTiempoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //fueraTiempoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //fueraTiempoProgEd
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //fueraTiempoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //fueraTiempoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //fueraTiempoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //noEntregadosAreaCon
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosAreaCon")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");        
        }
        
        //noEntregadosProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //noEntregadosProgEd
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //noEntregadosUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        
     String CTRnombre=CTRnombreUI;
     String CTRConsultaQuery;
     
     String tipoReporte=tipoReporteUI;
     
     CTRConsultaQuery="tipoReporte:"+tipoReporte+"#"+"numRact:"+reporte.getNumRact()+"#"+
             "cescicloEscolar:"+reporte.getCescicloEscolar()+"#"+"acoclave:"+reporte.getAcoclave()+"#"+
             "clavepe:"+reporte.getClavepe()+"#"+"pesvigencia:"+reporte.getPesvigencia()+"#"+
             "pronumeroEmpleado:"+reporte.getPronumeroEmpleado()+"#"+"gponumero:"+reporte.getGponumero()+"#"+
             "clave:"+reporte.getClavepe()+"#"+"uapclave:"+reporte.getUapclave();
     
     Catalogoreportes catalogoreporte = new Catalogoreportes();
     
     Usuario auxUsuario = new Usuario();
     
     catalogoreporte.setCtrnombre(CTRnombre);
     catalogoreporte.setCtrconsultaQuery(CTRConsultaQuery);
     //catalogoreporte.setCtrconsultaObjeto(null);
     
     auxUsuario.setUsuid(1);
     
     catalogoreporte.setUsuario(auxUsuario);               
     
     catalogoreportesDelegate.agregarCatalogoreporte(catalogoreporte);
     
     return CTRConsultaQuery;
    }
    
    public String nombreTipoEjecutarQueryConsultaGuardada(int idCTR){
        String CTRConsultaQuery="";   
       
        Catalogoreportes auxCatalogoreporte=catalogoreportesDelegate.getCatalogoreporte(idCTR);
        
                
        ReporteAux reporteUI = new ReporteAux();
        
                
        CTRConsultaQuery=auxCatalogoreporte.getCtrconsultaQuery();
    
        String CTRConsultaQueryArray[] = CTRConsultaQuery.split("#");
    
            
        //muestra cada atributo correctamente guardado
    
        String tipoReporteSTRArray[]=CTRConsultaQueryArray[0].split(":");    
    
        //toma el valor para tipoReporteUI
        String tipoReporteUI = tipoReporteSTRArray[1];
        
        return tipoReporteUI;
    }
    
    public void modificarConsulta(ReporteAux reporteUI,String tipoReporteUI,String CTRnombreUI,Integer seleccionIdCTR){
     
        ReporteAux reporte = new ReporteAux();
        
        
        //Opciones tipoReporteUI:
        
        //ATiempoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("ATiempoAreaCon")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //ATiempoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoAreaConTodosRacts")){
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //ATiempoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("ATiempoProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //ATiempoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
            
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");                        
        }
        
        //ATiempoProgEd
        if(tipoReporteUI.equalsIgnoreCase("ATiempoProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");        
        }
        
        //ATiempoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");        
        }
        
        //ATiempoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("ATiempoUAGrupo")){
            reporte = new ReporteAux();        
            
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //ATiempoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //ATiempoYNoYEnLimiteAreaCon
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteAreaCon")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //ATiempoYNoYEnLimiteAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteAreaConTodosRacts")){
             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //ATiempoYNoYEnLimiteProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //ATiempoYNoYEnLimiteProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //ATiempoYNoYEnLimiteProgEd
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");        
        }
        
        //ATiempoYNoYEnLimiteProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //ATiempoYNoYEnLimiteUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //ATiempoYNoYEnLimiteUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //PAGCAreaCon
        if(tipoReporteUI.equalsIgnoreCase("PAGCAreaCon")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //PAGCAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCAreaConTodosRacts")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //PAGCCompletoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoAreaConTodosRacts")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //PAGCCompletoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoAreaCon")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //PAGCompletoEIncompletoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoAreaCon")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //PAGCompletoEIncompletoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //PAGCompletoEIncompletoProgEd
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //PAGCompletoEIncompletoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoUAGrupo")){
            reporte = new ReporteAux();        
            
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //PAGCompletoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //PAGCompletoProgEd
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //PAGCompletoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //PAGCompletosYNoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoAreaConTodosRacts")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //PAGCompletosYNoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //PAGCompletosYNoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //PAGCompletosYNoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //PAGCProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //PAGCProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //PAGCCompletoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");        
        }
        
        //PAGCProgEd
        if(tipoReporteUI.equalsIgnoreCase("PAGCProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //PAGCProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //PAGCCompletoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //PAGCUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //PAGCUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //PAGCCompletoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //enFechaLimiteTiempoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoAreaCon")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //enFechaLimiteTiempoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoAreaConTodosRacts")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //enFechaLimiteTiempoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //enFechaLimiteTiempoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //enFechaLimiteTiempoProgEd
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //enFechaLimiteTiempoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //enFechaLimiteTiempoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //enFechaLimiteTiempoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //entregadosAreaCon
        if(tipoReporteUI.equalsIgnoreCase("entregadosAreaCon")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");        
        }
        
        //entregadosAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosAreaConTodosRacts")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //noEntregadosAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosAreaConTodosRacts")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");        
        }
        
        //entregadosProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("entregadosProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //entregadosProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //noEntregadosProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //entregadosProgEd
        if(tipoReporteUI.equalsIgnoreCase("entregadosProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //entregadosProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //noEntregadosProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //entregadosUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("entregadosUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //entregadosUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //noEntregadosUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //entregadosYNoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoAreaConTodosRacts")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");        
        }
        
        //entregadosYNoEntregadosAreaCon
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosAreaCon")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");           
        }
        
        //entregadosYNoEntregadosProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");        
        }
        
        //entregadosYNoEntregadosProgEd
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");        
        }
        
        //entregadosYNoEntregadosUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //entregadosYNoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //entregadosYNoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");        
        }
        
        //entregadosYNoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //fueraTiempoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoAreaCon")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //fueraTiempoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoAreaConTodosRacts")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        }
        
        //fueraTiempoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //fueraTiempoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //fueraTiempoProgEd
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoProgEd")){
            reporte = new ReporteAux();        
            
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //fueraTiempoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        }
        
        //fueraTiempoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //fueraTiempoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        //noEntregadosAreaCon
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosAreaCon")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");        
        }
        
        //noEntregadosProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        }
        
        //noEntregadosProgEd
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");        
        }
        
        //noEntregadosUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        }
        
        
     String CTRnombre=CTRnombreUI;
     String CTRConsultaQuery;
     
     String tipoReporte=tipoReporteUI;
     
     CTRConsultaQuery="tipoReporte:"+tipoReporte+"#"+"numRact:"+reporte.getNumRact()+"#"+
             "cescicloEscolar:"+reporte.getCescicloEscolar()+"#"+"acoclave:"+reporte.getAcoclave()+"#"+
             "clavepe:"+reporte.getClavepe()+"#"+"pesvigencia:"+reporte.getPesvigencia()+"#"+
             "pronumeroEmpleado:"+reporte.getPronumeroEmpleado()+"#"+"gponumero:"+reporte.getGponumero()+"#"+
             "clave:"+reporte.getClavepe()+"#"+"uapclave:"+reporte.getUapclave();
      
        
    Catalogoreportes catalogoreporte = new Catalogoreportes();
     
     Usuario auxUsuario = new Usuario();
     
     catalogoreporte.setCtrnombre(CTRnombre);
     catalogoreporte.setCtrconsultaQuery(CTRConsultaQuery);
     
     auxUsuario.setUsuid(1);
     
     catalogoreporte.setUsuario(auxUsuario);   
       
     catalogoreporte.setCtrid(seleccionIdCTR);
     
     
     
     catalogoreportesDelegate.agregarCatalogoreporte(catalogoreporte);
          
//        //consulta general de la tabla Catalogoreportes
//        List<Catalogoreportes> listaCatalogoReportes = catalogoreportesDelegate.getCatalogoreportes();
//        
//        //este es el nombre del criteria del query guardado que se buscará
//        
//        int idCTR=0;
//           
//        //hace una búsqueda dentro de la tabla Catalogoreportes el 
//        //nombre que coincida y guarda su id del criteria de la misma tabla
//        for(Catalogoreportes catalogoRep :listaCatalogoReportes){
//            if(catalogoRep.getCtrnombre().equalsIgnoreCase(CTRnombre)){
//                idCTR = catalogoRep.getCtrid();
//                
//                catalogoreporte.setCtrid(idCTR);
//                catalogoreportesDelegate.agregarCatalogoreporte(catalogoreporte);
//            }
//        }     
     
    }
    
    public void eliminarConsulta(String tipoReporteCTR,String CTRnombre){
        
        List<Catalogoreportes> listaCatalogoReportes = catalogoreportesDelegate.getCatalogoreportes();
        
        Usuario auxUsuario = new Usuario();
          
     
        auxUsuario.setUsuid(1);
     
             
        //este es el nombre del criteria del query guardado que se buscará
        
        int idCTR=0;
           
        //hace una búsqueda dentro de la tabla Catalogoreportes el 
        //nombre que coincida y guarda su id del criteria de la misma tabla
        for(Catalogoreportes catalogoRep :listaCatalogoReportes){
            if(catalogoRep.getCtrnombre().equalsIgnoreCase(CTRnombre)){
                idCTR = catalogoRep.getCtrid();
                
                //catalogoreporte.setCtrid(idCTR);
                catalogoreportesDelegate.eliminarCatalogoreporte(idCTR);
            }
        }
                
    }
    
    public ArrayList<Catalogoreportes> todasConsultasGuardadas(){
        List<Catalogoreportes> listaCatalogoReportes = catalogoreportesDelegate.getCatalogoreportes();
        
        ArrayList<Catalogoreportes> listaCatalogoReportes2 = new ArrayList<Catalogoreportes>();
        
        String nombreAnterior="";
        //String nombreSiguiente="*";
        
        for(Catalogoreportes cr: listaCatalogoReportes){
            if(!(cr.getCtrnombre().equalsIgnoreCase(nombreAnterior))){
              listaCatalogoReportes2.add(cr);
            }
            nombreAnterior=cr.getCtrnombre();
        }
        
        return listaCatalogoReportes2;
    }
    
    public List<Catalogoreportes> todasConsultasGuardadasNormal(){
        List<Catalogoreportes> listaCatalogoReportes = catalogoreportesDelegate.getCatalogoreportes();        
                        
        return listaCatalogoReportes;
    }
    
    public ArrayList ejecutarQueryConsultaGuardada(ArrayList<Integer> idCTRlist){
        
        ReporteAux reporte = new ReporteAux();     
       
        //aqui empieza codigo test
        String CTRConsultaQuery;   
        
        ArrayList listaEjecutarQuery2 = new ArrayList();
        
        int pos=0;
        
        for(Integer idCTR2: idCTRlist){
        System.out.println("***********************************************");
        System.out.println("idCTRlist(ejecutar query consulta guardada beanHelper): "+idCTR2+" en la pos: "+pos);
        System.out.println("***********************************************");
        pos++;
        }
       
     for(Integer idCTR: idCTRlist){        
     
        Catalogoreportes auxCatalogoreporte=catalogoreportesDelegate.getCatalogoreporte(idCTR);
        
                
        ReporteAux reporteUI = new ReporteAux();
        
                
    CTRConsultaQuery=auxCatalogoreporte.getCtrconsultaQuery();
    
    String CTRConsultaQueryArray[] = CTRConsultaQuery.split("#");
    
            
        //muestra cada atributo correctamente guardado
    
    String tipoReporteSTRArray[]=CTRConsultaQueryArray[0].split(":");    
    
    //toma el valor para tipoReporteUI
    String tipoReporteUI = tipoReporteSTRArray[1];
    
        
    String numRactSTRArray[]=CTRConsultaQueryArray[1].split(":");    
        
    //toma el valor para numRact
    reporteUI.setNumRact(Integer.parseInt(numRactSTRArray[1]));
    
    
    String cescicloEscolarSTRArray[]=CTRConsultaQueryArray[2].split(":");    
        
    //toma el valor para cescicloEscolar
    reporteUI.setCescicloEscolar(cescicloEscolarSTRArray[1]);
    
    
    String acoclaveSTRArray[]=CTRConsultaQueryArray[3].split(":");    
        
    //toma el valor para acoclave
    reporteUI.setAcoclave(Integer.parseInt(acoclaveSTRArray[1]));
    
    
    String clavepeSTRArray[]=CTRConsultaQueryArray[4].split(":");    
        
    //toma el valor para clavepe
    reporteUI.setClavepe(Integer.parseInt(clavepeSTRArray[1]));
    
    
    String pesvigenciaSTRArray[]=CTRConsultaQueryArray[5].split(":");    
        
    //toma el valor para pesvigencia
    reporteUI.setPesvigencia(pesvigenciaSTRArray[1]);
    
    
    String pronumeroEmpleadoSTRArray[]=CTRConsultaQueryArray[6].split(":");    
        
    //toma el valor para pronumeroEmpleado
    reporteUI.setPronumeroEmpleado(Integer.parseInt(pronumeroEmpleadoSTRArray[1]));
    
    
    String gponumeroSTRArray[]=CTRConsultaQueryArray[7].split(":");    
       
    //toma el valor para pronumeroEmpleado
    reporteUI.setGponumero(Integer.parseInt(gponumeroSTRArray[1]));
    
    
    String claveSTRArray[]=CTRConsultaQueryArray[8].split(":");    
        
    //toma el valor para clave(clavepe)
    reporteUI.setClave(Integer.parseInt(claveSTRArray[1]));
    
    
    String uapclaveSTRArray[]=CTRConsultaQueryArray[9].split(":");    
        
    //toma el valor para uapclave
    reporteUI.setUapclave(Integer.parseInt(uapclaveSTRArray[1]));
    
        System.out.println(" ");
    
//    CTRConsultaQuery="tipoReporte:"+tipoReporte+"#"+"numRact:"+reporte.getNumRact()+"#"+
//             "cescicloEscolar:"+reporte.getCescicloEscolar()+"#"+"acoclave:"+reporte.getAcoclave()+"#"+
//             "clavepe:"+reporte.getClavepe()+"#"+"pesvigencia:"+reporte.getPesvigencia()+"#"+
//             "pronumeroEmpleado:"+reporte.getPronumeroEmpleado()+"#"+"gponumero:"+reporte.getGponumero()+"#"+
//             "clave:"+reporte.getClavepe()+"#"+"uapclave:"+reporte.getUapclave();
            //hasta aqui codigo de test
    
        ArrayList listaEjecutarQuery = new ArrayList();
        
        //Opciones tipoReporteUI:
        
        //ATiempoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("ATiempoAreaCon")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        
        listaEjecutarQuery=ATiempoAreaCon(reporte);
        }
        
        //ATiempoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoAreaConTodosRacts")){
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        
        listaEjecutarQuery=ATiempoAreaConTodosRacts(reporte);
        }
        
        //fueraTiempoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoAreaConTodosRacts")){
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");        
        
        listaEjecutarQuery=FueraTiempoAreaConTodosRacts(reporte);
        }
        
        //ATiempoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("ATiempoProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        
        listaEjecutarQuery=ATiempoProfGrupo(reporte);
        }
        
        //ATiempoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");        
        
        listaEjecutarQuery=ATiempoProfGrupoTodosRacts(reporte);
        }
        
        //fueraTiempoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        
        listaEjecutarQuery=FueraTiempoProfGrupoTodosRacts(reporte);
        }
        
        //ATiempoProgEd
        if(tipoReporteUI.equalsIgnoreCase("ATiempoProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        
        listaEjecutarQuery=ATiempoProgEd(reporte);
        }
        
        //ATiempoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        
        listaEjecutarQuery=ATiempoProgEdTodosRacts(reporte);
        }
        
        //fueraTiempoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        
        listaEjecutarQuery=FueraTiempoProgEdTodosRacts(reporte);
        }
        
        //ATiempoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("ATiempoUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=ATiempoUAGrupo(reporte);
        }
        
        //ATiempoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=ATiempoUAGrupoTodosRacts(reporte);
        }
        
        //fueraTiempoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=FueraTiempoUAGrupoTodosRacts(reporte);
        }
        
        //ATiempoYNoYEnLimiteAreaCon
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteAreaCon")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        
        listaEjecutarQuery=ATiempoYNoYEnLimiteAreaCon(reporte);
        }
        
        //ATiempoYNoYEnLimiteAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteAreaConTodosRacts")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        
        listaEjecutarQuery=ATiempoYNoYEnLimiteAreaConTodosRacts(reporte);
        }
        
        //ATiempoYNoYEnLimiteProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        
        listaEjecutarQuery=ATiempoYNoYEnLimiteProfGrupo(reporte);
        }
        
        //ATiempoYNoYEnLimiteProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        
        listaEjecutarQuery=ATiempoYNoYEnLimiteProfGrupoTodosRacts(reporte);
        }
        
        //ATiempoYNoYEnLimiteProgEd
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");        
        
        listaEjecutarQuery=ATiempoYNoYEnLimiteProgEd(reporte);
        }
        
        //ATiempoYNoYEnLimiteProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        
        listaEjecutarQuery=ATiempoYNoYEnLimiteProgEdTodosRacts(reporte);
        }
        
        //ATiempoYNoYEnLimiteUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=ATiempoYNoYEnLimiteUAGrupo(reporte);
        }
        
        //ATiempoYNoYEnLimiteUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=ATiempoYNoYEnLimiteUAGrupoTodosRacts(reporte);
        }
        
        //PAGCAreaCon
        if(tipoReporteUI.equalsIgnoreCase("PAGCAreaCon")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        
        listaEjecutarQuery=PAGCAreaCon(reporte);
        }
        
        //PAGCAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCAreaConTodosRacts")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");        
        
        listaEjecutarQuery=PAGCAreaConTodosRacts(reporte);
        }
        
        //PAGCCompletoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoAreaConTodosRacts")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        
        listaEjecutarQuery=PAGCCompletoAreaConTodosRacts(reporte);
        }
        
        //PAGCCompletoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoAreaCon")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");        
        
        listaEjecutarQuery=PAGCCompletoAreaCon(reporte);
        }
        
        //PAGCompletoEIncompletoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoAreaCon")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        
        listaEjecutarQuery=PAGCCompletoEIncompletoAreaCon(reporte);
        }
        
        //PAGCompletoEIncompletoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");           
        
        listaEjecutarQuery=PAGCCompletoEIncompletoProfGrupo(reporte);
        }
        
        //PAGCompletoEIncompletoProgEd
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        
        listaEjecutarQuery=PAGCCompletoEIncompletoProgEd(reporte);
        }
        
        //PAGCompletoEIncompletoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=PAGCCompletoEIncompletoUAGrupo(reporte);
        }
        
        //PAGCompletoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        
        listaEjecutarQuery=PAGCCompletoProfGrupo(reporte);
        }
        
        //PAGCompletoProgEd
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        
        listaEjecutarQuery=PAGCCompletoProgEd(reporte);
        }
        
        //PAGCompletoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=PAGCCompletoUAGrupo(reporte);
        }
        
        //PAGCompletosYNoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoAreaConTodosRacts")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        
        listaEjecutarQuery=PAGCCompletosYNoAreaConTodosRacts(reporte);
        }
        
        //PAGCompletosYNoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        
        listaEjecutarQuery=PAGCCompletosYNoProfGrupoTodosRacts(reporte);
        }
        
        //PAGCompletosYNoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");        
        
        listaEjecutarQuery=PAGCCompletosYNoProgEdTodosRacts(reporte);
        }
        
        //PAGCompletosYNoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=PAGCCompletosYNoUAGrupoTodosRacts(reporte);
        }
        
        //PAGCProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        
        listaEjecutarQuery=PAGCProfGrupo(reporte);
        }
        
        //PAGCProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCProfGrupoTodosRacts")){
            reporte = new ReporteAux();                
            
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        
        listaEjecutarQuery=PAGCProfGrupoTodosRacts(reporte);
        }
        
        //PAGCCompletoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        
        listaEjecutarQuery=PAGCCompletoProfGrupoTodosRacts(reporte);
        }
        
        //PAGCProgEd
        if(tipoReporteUI.equalsIgnoreCase("PAGCProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        
        listaEjecutarQuery=PAGCProgEd(reporte);
        }
        
        //PAGCProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        
        listaEjecutarQuery=PAGCProgEdTodosRacts(reporte);
        }
        
        //PAGCCompletoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        
        listaEjecutarQuery=PAGCCompletoProgEdTodosRacts(reporte);
        }
        
        //PAGCUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=PAGCUAGrupo(reporte);
        }
        
        //PAGCUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=PAGCUAGrupoTodosRacts(reporte);
        }
        
        //PAGCCompletoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=PAGCCompletoUAGrupoTodosRacts(reporte);
        }
        
        //enFechaLimiteTiempoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoAreaCon")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        
        listaEjecutarQuery=EnFechaLimiteTiempoAreaCon(reporte);
        }
        
        //enFechaLimiteTiempoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoAreaConTodosRacts")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        
        listaEjecutarQuery=EnFechaLimiteTiempoAreaConTodosRacts(reporte);
        }
        
        //enFechaLimiteTiempoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        
        listaEjecutarQuery=EnFechaLimiteTiempoProfGrupo(reporte);
        }
        
        //enFechaLimiteTiempoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        
        listaEjecutarQuery=EnFechaLimiteTiempoProfGrupoTodosRacts(reporte);
        }
        
        //enFechaLimiteTiempoProgEd
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        
        listaEjecutarQuery=EnFechaLimiteTiempoProgEd(reporte);
        }
        
        //enFechaLimiteTiempoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        
        listaEjecutarQuery=EnFechaLimiteTiempoProgEdTodosRacts(reporte);
        }
        
        //enFechaLimiteTiempoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=EnFechaLimiteTiempoUAGrupo(reporte);
        }
        
        //enFechaLimiteTiempoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=EnFechaLimiteTiempoUAGrupoTodosRacts(reporte);
        }
        
        //entregadosAreaCon
        if(tipoReporteUI.equalsIgnoreCase("entregadosAreaCon")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
                
        listaEjecutarQuery=entregadosAreaCon(reporte);
        }
        
        //entregadosAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosAreaConTodosRacts")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        
        listaEjecutarQuery=entregadosAreaConTodosRacts(reporte);
        }
        
        //entregadosProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("entregadosProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        
        listaEjecutarQuery=entregadosProfGrupo(reporte);
        }
        
        //entregadosProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        
        listaEjecutarQuery=entregadosProfGrupoTodosRacts(reporte);
        }
        
        //entregadosProgEd
        if(tipoReporteUI.equalsIgnoreCase("entregadosProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        
        listaEjecutarQuery=entregadosProgEd(reporte);
        }
        
        //entregadosProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        
        listaEjecutarQuery=entregadosProgEdTodosRacts(reporte);
        }
        
        //entregadosUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("entregadosUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=entregadosUAGrupo(reporte);
        }
        
        //entregadosUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=entregadosUAGrupoTodosRacts(reporte);
        }
        
        //entregadosYNoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoAreaConTodosRacts")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        
        listaEjecutarQuery=entregadosYNoAreaConTodosRacts(reporte);
        }
        
        //entregadosYNoEntregadosAreaCon
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosAreaCon")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        
        listaEjecutarQuery=entregadosYNoEntregadosAreaCon(reporte);
        }
        
        //entregadosYNoEntregadosProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        
        listaEjecutarQuery=entregadosYNoEntregadosProfGrupo(reporte);
        }
        
        //entregadosYNoEntregadosProgEd
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        
        listaEjecutarQuery=entregadosYNoEntregadosProgEd(reporte);
        }
        
        //entregadosYNoEntregadosUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=entregadosYNoEntregadosUAGrupo(reporte);
        }
        
        //entregadosYNoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        
        listaEjecutarQuery=entregadosYNoProfGrupoTodosRacts(reporte);
        }
        
        //entregadosYNoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        
        listaEjecutarQuery=entregadosYNoProgEdTodosRacts(reporte);
        }
        
        //entregadosYNoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=entregadosYNoUAGrupoTodosRacts(reporte);
        }
        
        //fueraTiempoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoAreaCon")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        
        listaEjecutarQuery=FueraTiempoAreaCon(reporte);
        }
        
        //fueraTiempoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoProfGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        
        listaEjecutarQuery=FueraTiempoProfGrupo(reporte);
        }
        
        //fueraTiempoProgEd
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        
        listaEjecutarQuery=FueraTiempoProgEd(reporte);
        }
        
        //fueraTiempoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=FueraTiempoUAGrupo(reporte);
        }
        
        //noEntregadosAreaCon
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosAreaCon")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        
        listaEjecutarQuery=noEntregadosAreaCon(reporte);
        }
        
        //noEntregadosAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosAreaConTodosRacts")){             
            reporte = new ReporteAux();
        
            reporte = fijarAtributosReporte(reporteUI,"AreaCon");            
        
        listaEjecutarQuery=noEntregadosAreaConTodosRacts(reporte);
        }
        
        //noEntregadosProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosProfGrupo")){
            reporte = new ReporteAux();        
            
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        
        listaEjecutarQuery=noEntregadosProfGrupo(reporte);
        }
        
        //noEntregadosProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosProfGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");            
        
        listaEjecutarQuery=noEntregadosProfGrupoTodosRacts(reporte);
        }
        
        //noEntregadosProgEd
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosProgEd")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        
        listaEjecutarQuery=noEntregadosProgEd(reporte);
        }
        
        //noEntregadosProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosProgEdTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"ProgEd");            
        
        listaEjecutarQuery=noEntregadosProgEdTodosRacts(reporte);
        }
        
        //noEntregadosUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosUAGrupo")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=noEntregadosUAGrupo(reporte);
        }
        
        //noEntregadosUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosUAGrupoTodosRacts")){
            reporte = new ReporteAux();        
        
            reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        listaEjecutarQuery=noEntregadosUAGrupoTodosRacts(reporte);
        }
        
        
        for(Object ejectQuery: listaEjecutarQuery){
            listaEjecutarQuery2.add(ejectQuery);
        }
     }
     
     return listaEjecutarQuery2;
             
    }
    
    public ArrayList<ReporteAux> prepararAtribGuardarConsultaProgEd(ReporteAux reporteUI,List<String> selectCicloEscolarList,List<String> selectProgramEducativo){
        //selectCicloEscolarList.size();
        
        Programaeducativo proged = new Programaeducativo();
        
        List<Planestudio> planestList = null;        
                
        ReporteAux reporte = new ReporteAux();
        
        ArrayList<ReporteAux> listaReporte = new ArrayList<ReporteAux>(); 
        
        for(String selectCicloEs: selectCicloEscolarList){
             for(String selectPEid: selectProgramEducativo){            
            int claveproged=Integer.parseInt(selectPEid.trim());
            
            proged = new Programaeducativo();
            
            //nota aqui comente
            //proged=programaeducativoDelegate.getProgramaeducativo(idproged);
    
            planestList=planEstudioDelegate.getByProgramaeducativo(claveproged);
            
            reporte = new ReporteAux();
            
            //los atributos de esta clase toman los mismos valores del
            //parametro recibido en este mismo metodo desde el beanUI
            setNumRact(reporteUI.getNumRact());                  
            //setCescicloEscolar(reporteUI.getCescicloEscolar());             
            setCescicloEscolar(selectCicloEs);             
            //setClavepe(proged.getPedclave());                  
            setClavepe(claveproged); 
            if(!(planestList.isEmpty())){
            setPesvigencia(planestList.get(0).getPesvigenciaPlan());
            }
//            int idproged=Integer.parseInt(selectPEid.trim());
//            
//            proged = new Programaeducativo();
//            
//            proged=programaeducativoDelegate.getProgramaeducativo(idproged);
//    
//            planestList=planEstudioDelegate.getByProgramaeducativo(idproged);
//            
//            reporte = new ReporteAux();
//            
//            //los atributos de esta clase toman los mismos valores del
//            //parametro recibido en este mismo metodo desde el beanUI
//            setNumRact(reporteUI.getNumRact());                  
//            setCescicloEscolar(reporteUI.getCescicloEscolar());             
//            setClavepe(proged.getPedclave());                  
//            setPesvigencia(planestList.get(0).getPesvigenciaPlan());
            
            //atributos necesarios para entregados en el objeto que se manda al
            //delegate
            reporte.setNumRact(numRact);//aqui cambie para hacer consulta al ract1
            reporte.setCescicloEscolar(cescicloEscolar);        
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setClave(clavepe);        
            
            listaReporte.add(reporte);
         }
        }
        
        return listaReporte;
    }
    
    public ArrayList<ReporteAux> prepararAtribGuardarConsultaAreaCon(ReporteAux reporteUI,List<String> selectCicloEscolarList,List<String> selectPlanesEstudio,List<String> selectAreaConocimiento){
        //selectCicloEscolarList.size();
        
        Programaeducativo proged = new Programaeducativo();
        
        List<Planestudio> planestList = null;
        
        //ArrayList<ReporteAvanceAux> pelist = new ArrayList<ReporteAvanceAux>();
        
        //ArrayList<ReporteAvanceAux> pelist2 = new ArrayList<ReporteAvanceAux>();
        
        ArrayList<ReporteAux> listaReporte = new ArrayList<ReporteAux>(); 
        
        ReporteAux reporte = new ReporteAux();
        
        //for(String selectPEid: selectProgramEducativo){            
          for(String selectCicloEs: selectCicloEscolarList){
              for(String selectPlanEs: selectPlanesEstudio){
                  for(String selectAreaCon: selectAreaConocimiento){
            
            //int claveproged=Integer.parseInt(selectPEid.trim());
            
            proged = new Programaeducativo();
            
            //nota aqui comente
            //proged=programaeducativoDelegate.getProgramaeducativo(idproged);
    
            //planestList=planEstudioDelegate.getByProgramaeducativo(reporteUI.getClavepe());
            
            int claveacon=Integer.parseInt(selectAreaCon);
            
            reporte = new ReporteAux();
            
            //los atributos de esta clase toman los mismos valores del
            //parametro recibido en este mismo metodo desde el beanUI
            setNumRact(reporteUI.getNumRact());                  
            //setCescicloEscolar(reporteUI.getCescicloEscolar());             
            setCescicloEscolar(selectCicloEs);             
            //setClavepe(proged.getPedclave());                  
            setClavepe(reporteUI.getClavepe());                  
            setPesvigencia(selectPlanEs);
            setAcoclave(claveacon);
            
            //atributos necesarios para entregados en el objeto que se manda al
            //delegate
            reporte.setNumRact(numRact);//aqui cambie para hacer consulta al ract1
            reporte.setCescicloEscolar(cescicloEscolar);        
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setClave(clavepe);        
            reporte.setAcoclave(acoclave);                
            
            listaReporte.add(reporte);
            }
           }
          }
        
        return listaReporte;
    }
    
    public ArrayList<ReporteAux> prepararAtribGuardarConsultaUAprend(ReporteAux reporteUI,List<String> selectCicloEscolarList,List<String> selectPlanesEstudio,List<String> selectAreaConocimiento,List<String> selectUnidadAprendisaje,List<String> selectGrupo){
        //selectCicloEscolarList.size();
        
        Programaeducativo proged = new Programaeducativo();
        
        List<Planestudio> planestList = null;
        
        //ArrayList<ReporteAvanceAux> pelist = new ArrayList<ReporteAvanceAux>();
        
        //ArrayList<ReporteAvanceAux> pelist2 = new ArrayList<ReporteAvanceAux>();
        
        ArrayList<ReporteAux> listaReporte = new ArrayList<ReporteAux>(); 
        
        ReporteAux reporte = new ReporteAux();
        
        //for(String selectPEid: selectProgramEducativo){            
          for(String selectCicloEs: selectCicloEscolarList){
              for(String selectPlanEs: selectPlanesEstudio){                                       
                for(String selectAreaCon: selectAreaConocimiento){                 
                    for(String selectUAp: selectUnidadAprendisaje){
                      for(String selectGr: selectGrupo){       
                              
                          
                      
            
            //int claveproged=Integer.parseInt(selectPEid.trim());
            
            proged = new Programaeducativo();
            
            //nota aqui comente
            //proged=programaeducativoDelegate.getProgramaeducativo(idproged);
    
            //planestList=planEstudioDelegate.getByProgramaeducativo(reporteUI.getClavepe());
            
            int claveacon=Integer.parseInt(selectAreaCon);
            int claveuaprend=Integer.parseInt(selectUAp);
            int clavegrupo=Integer.parseInt(selectGr);
            
            reporte = new ReporteAux();
            
            //los atributos de esta clase toman los mismos valores del
            //parametro recibido en este mismo metodo desde el beanUI
            setNumRact(reporteUI.getNumRact());                  
            //setCescicloEscolar(reporteUI.getCescicloEscolar());             
            setCescicloEscolar(selectCicloEs);             
            //setClavepe(proged.getPedclave());                  
            setClavepe(reporteUI.getClavepe());                  
            setPesvigencia(selectPlanEs);
            setAcoclave(claveacon);
            setUapclave(claveuaprend);
            setGponumero(clavegrupo);
            
            //atributos necesarios para entregados en el objeto que se manda al
            //delegate
            reporte.setNumRact(numRact);//aqui cambie para hacer consulta al ract1
            reporte.setCescicloEscolar(cescicloEscolar);        
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setClave(clavepe);        
            reporte.setAcoclave(acoclave);        
            reporte.setUapclave(uapclave);
            reporte.setGponumero(gponumero);        
            
            listaReporte.add(reporte);
              }
             }
            }
           }
          }
        
        return listaReporte;
    }
    
    public ArrayList<ReporteAux> prepararAtribGuardarConsultaProfesor(ReporteAux reporteUI,List<String> selectCicloEscolarList,List<String> selectPlanesEstudio,List<String> selectAreaConocimiento,List<String> selectUnidadAprendisaje,List<String> selectGrupo,List<String> selectProfesor){
        //selectCicloEscolarList.size();
        
        Programaeducativo proged = new Programaeducativo();
        
        List<Planestudio> planestList = null;
        
        //ArrayList<ReporteAvanceAux> pelist = new ArrayList<ReporteAvanceAux>();
        
        //ArrayList<ReporteAvanceAux> pelist2 = new ArrayList<ReporteAvanceAux>();
        
        ArrayList<ReporteAux> listaReporte = new ArrayList<ReporteAux>(); 
        
        ReporteAux reporte = new ReporteAux();
        
        //for(String selectPEid: selectProgramEducativo){            
          for(String selectCicloEs: selectCicloEscolarList){
              for(String selectPlanEs: selectPlanesEstudio){                                       
                for(String selectAreaCon: selectAreaConocimiento){                 
                    for(String selectUAp: selectUnidadAprendisaje){
    //                  for(String selectGr: selectGrupo){
                          for(String selectProf: selectProfesor){
                              
                          
                      
            
            //int claveproged=Integer.parseInt(selectPEid.trim());
            
            proged = new Programaeducativo();
            
            //nota aqui comente
            //proged=programaeducativoDelegate.getProgramaeducativo(idproged);
    
            //planestList=planEstudioDelegate.getByProgramaeducativo(reporteUI.getClavepe());
            
            int claveacon=Integer.parseInt(selectAreaCon);
            int claveuaprend=Integer.parseInt(selectUAp);
      //      int clavegrupo=Integer.parseInt(selectGr);
            int claveprofesor=Integer.parseInt(selectProf);
            
            reporte = new ReporteAux();
            
            //los atributos de esta clase toman los mismos valores del
            //parametro recibido en este mismo metodo desde el beanUI
            setNumRact(reporteUI.getNumRact());                  
            //setCescicloEscolar(reporteUI.getCescicloEscolar());             
            setCescicloEscolar(selectCicloEs);             
            //setClavepe(proged.getPedclave());                  
            setClavepe(reporteUI.getClavepe());                  
            setPesvigencia(selectPlanEs);
            setAcoclave(claveacon);
            setUapclave(claveuaprend);
    //        setGponumero(clavegrupo);
            setPronumeroEmpleado(claveprofesor);
            
            //atributos necesarios para entregados en el objeto que se manda al
            //delegate
            reporte.setNumRact(numRact);//aqui cambie para hacer consulta al ract1
            reporte.setCescicloEscolar(cescicloEscolar);        
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setClave(clavepe);        
            reporte.setAcoclave(acoclave);        
            reporte.setUapclave(uapclave);
   //         reporte.setGponumero(gponumero);   
            reporte.setPronumeroEmpleado(pronumeroEmpleado);
            
            listaReporte.add(reporte);
              }
   //          }
            }
           }
          }
         }
        
        return listaReporte;
    }
    
    public ArrayList selectionProgEd(ReporteAux reporteUI,String tipoReporteUI,List<String> selectProgramEducativo,List<String> selectCicloEscolarList){
        
        //selectCicloEscolarList.size();
        
        Programaeducativo proged = new Programaeducativo();
        
        List<Planestudio> planestList = null;
        
        ArrayList<ReporteAvanceAux> pelist = new ArrayList<ReporteAvanceAux>();
        
        ArrayList<ReporteAvanceAux> pelist2 = new ArrayList<ReporteAvanceAux>();
        
        ArrayList<ReporteAvanceAux> pelist6 = new ArrayList<ReporteAvanceAux>();
        
        ReporteAux reporte = new ReporteAux();
        
        for(String selectPEid: selectProgramEducativo){            
            for(String selectCicloEs: selectCicloEscolarList){
            
            int claveproged=Integer.parseInt(selectPEid.trim());
            
            proged = new Programaeducativo();
            
            //nota aqui comente
            //proged=programaeducativoDelegate.getProgramaeducativo(idproged);
    
            planestList=planEstudioDelegate.getByProgramaeducativo(claveproged);
            
            reporte = new ReporteAux();
            
            //los atributos de esta clase toman los mismos valores del
            //parametro recibido en este mismo metodo desde el beanUI
            setNumRact(reporteUI.getNumRact());                  
            //setCescicloEscolar(reporteUI.getCescicloEscolar());             
            setCescicloEscolar(selectCicloEs);             
            //setClavepe(proged.getPedclave());                  
            setClavepe(claveproged);                  
            if(!planestList.isEmpty()){
            setPesvigencia(planestList.get(0).getPesvigenciaPlan());
            }
            //atributos necesarios para entregados en el objeto que se manda al
            //delegate
            reporte.setNumRact(numRact);//aqui cambie para hacer consulta al ract1
            reporte.setCescicloEscolar(cescicloEscolar);        
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setClave(clavepe);        
            
            pelist = new ArrayList<ReporteAvanceAux>();
            
            //pe=pe;
            
            //Opciones tipoReporteUI:        
                
            //ATiempoProgEd
            if (tipoReporteUI.equalsIgnoreCase("ATiempoProgEd")) {
                pelist=ATiempoProgEd(reporte);
            }

            //ATiempoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("ATiempoProgEdTodosRacts")) {
                pelist=ATiempoProgEdTodosRacts(reporte);
            }

            //ATiempoYNoYEnLimiteProgEd
            if (tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProgEd")) {
                pelist=ATiempoYNoYEnLimiteProgEd(reporte);
            }

            //ATiempoYNoYEnLimiteProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProgEdTodosRacts")) {
                pelist=ATiempoYNoYEnLimiteProgEdTodosRacts(reporte);
            }

            //PAGCompletoEIncompletoProgEd
            if (tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoProgEd")) {
               pelist=PAGCCompletoEIncompletoProgEd(reporte);
            }

            //PAGCompletoProgEd
            if (tipoReporteUI.equalsIgnoreCase("PAGCompletoProgEd")) {
                pelist=PAGCCompletoProgEd(reporte);
            }

            //PAGCompletosYNoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoProgEdTodosRacts")) {
                pelist=PAGCCompletosYNoProgEdTodosRacts(reporte);
            }

            //PAGCProgEd
            if (tipoReporteUI.equalsIgnoreCase("PAGCProgEd")) {
               pelist=PAGCProgEd(reporte);
            }

            //PAGCProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("PAGCProgEdTodosRacts")) {
                pelist=PAGCProgEdTodosRacts(reporte);
            }

            //PAGCCompletoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("PAGCCompletoProgEdTodosRacts")) {
                pelist=PAGCCompletoProgEdTodosRacts(reporte);
            }

            //enFechaLimiteTiempoProgEd
            if (tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProgEd")) {
                pelist=EnFechaLimiteTiempoProgEd(reporte);
            }

            //enFechaLimiteTiempoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProgEdTodosRacts")) {
                pelist=EnFechaLimiteTiempoProgEdTodosRacts(reporte);
            }

            //entregadosProgEd
            if (tipoReporteUI.equalsIgnoreCase("entregadosProgEd")) {
                pelist=entregadosProgEd(reporte);
            }

            //entregadosProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("entregadosProgEdTodosRacts")) {                
                pelist=entregadosProgEdTodosRacts(reporte);                
            }

            //noEntregadosProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("noEntregadosProgEdTodosRacts")) {
                pelist=noEntregadosProgEdTodosRacts(reporte);
            }

            //entregadosYNoEntregadosProgEd
            if (tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosProgEd")) {
                pelist=entregadosYNoEntregadosProgEd(reporte);
            }

            //entregadosYNoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("entregadosYNoProgEdTodosRacts")) {
                pelist=entregadosYNoProgEdTodosRacts(reporte);
            }

            //fueraTiempoProgEd
            if (tipoReporteUI.equalsIgnoreCase("fueraTiempoProgEd")) {
                pelist=FueraTiempoProgEd(reporte);
            }

            //fueraTiempoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("fueraTiempoProgEdTodosRacts")) {
                pelist=FueraTiempoProgEdTodosRacts(reporte);
            }

            //noEntregadosProgEd
            if (tipoReporteUI.equalsIgnoreCase("noEntregadosProgEd")) {
                pelist=noEntregadosProgEd(reporte);
            }
            
            for(ReporteAvanceAux ra: pelist){                
                pelist2.add(ra);                
            }
        
            }
        }
        
        pelist6.addAll(pelist2);
         
         ReporteAvanceAux pelist7 = new ReporteAvanceAux();
         
         int cont=0;
          
         for(ReporteAvanceAux pelist4 :pelist2){
             cont=0;
             //idUipid.add(pelist4.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUipid());
             for(ReporteAvanceAux pelist5 :pelist2){
               if(pelist4.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()==pelist5.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()){
                cont++;                   
               }          
               if(cont>=2){
                   pelist7=pelist5;
                   pelist6.remove(pelist7);
                   break;
               }
             }
         }
        
        //programaeducativoDelegate.getProgramaeducativo(creid);
        return pelist6;
    }
    
    public ArrayList selectionAreaCon(ReporteAux reporteUI,String tipoReporteUI,List<String> selectCicloEscolarList,List<String> selectPlanesEstudio,List<String> selectAreaConocimiento){
        
        //selectCicloEscolarList.size();
        
        Programaeducativo proged = new Programaeducativo();
        
        List<Planestudio> planestList = null;
        
        ArrayList<ReporteAvanceAux> pelist = new ArrayList<ReporteAvanceAux>();
        
        ArrayList<ReporteAvanceAux> pelist2 = new ArrayList<ReporteAvanceAux>();
        
        ArrayList<ReporteAvanceAux> pelist6 = new ArrayList<ReporteAvanceAux>();
        
        ReporteAux reporte = new ReporteAux();
        
        //for(String selectPEid: selectProgramEducativo){            
          for(String selectCicloEs: selectCicloEscolarList){
              for(String selectPlanEs: selectPlanesEstudio){
                  for(String selectAreaCon: selectAreaConocimiento){
            
            //int claveproged=Integer.parseInt(selectPEid.trim());
            
            proged = new Programaeducativo();
            
            //nota aqui comente
            //proged=programaeducativoDelegate.getProgramaeducativo(idproged);
    
            //planestList=planEstudioDelegate.getByProgramaeducativo(reporteUI.getClavepe());
            
            int claveacon=Integer.parseInt(selectAreaCon);
            
            reporte = new ReporteAux();
            
            //los atributos de esta clase toman los mismos valores del
            //parametro recibido en este mismo metodo desde el beanUI
            setNumRact(reporteUI.getNumRact());                  
            //setCescicloEscolar(reporteUI.getCescicloEscolar());             
            setCescicloEscolar(selectCicloEs);             
            //setClavepe(proged.getPedclave());                  
            setClavepe(reporteUI.getClavepe());                  
            setPesvigencia(selectPlanEs);
            setAcoclave(claveacon);
            
            //atributos necesarios para entregados en el objeto que se manda al
            //delegate
            reporte.setNumRact(numRact);//aqui cambie para hacer consulta al ract1
            reporte.setCescicloEscolar(cescicloEscolar);        
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setClave(clavepe);        
            reporte.setAcoclave(acoclave);        
            
            pelist = new ArrayList<ReporteAvanceAux>();
            
            //pe=pe;
            
            //Opciones tipoReporteUI:        
                
            //ATiempoProgEd
            if (tipoReporteUI.equalsIgnoreCase("ATiempoAreaCon")) {
                pelist=ATiempoAreaCon(reporte);
            }

            //ATiempoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("ATiempoAreaConTodosRacts")) {
                pelist=ATiempoAreaConTodosRacts(reporte);
            }

            //ATiempoYNoYEnLimiteProgEd
            if (tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteAreaCon")) {
                pelist=ATiempoYNoYEnLimiteAreaCon(reporte);
            }

            //ATiempoYNoYEnLimiteProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteAreaConTodosRacts")) {
                pelist=ATiempoYNoYEnLimiteAreaConTodosRacts(reporte);
            }

            //PAGCompletoEIncompletoProgEd
            if (tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoAreaCon")) {
               pelist=PAGCCompletoEIncompletoAreaCon(reporte);
            }

            //PAGCompletoProgEd
            if (tipoReporteUI.equalsIgnoreCase("PAGCCompletoAreaCon")) {
                pelist=PAGCCompletoAreaCon(reporte);
            }

            //PAGCompletosYNoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoAreaConTodosRacts")) {
                pelist=PAGCCompletosYNoAreaConTodosRacts(reporte);
            }

            //PAGCProgEd
            if (tipoReporteUI.equalsIgnoreCase("PAGCAreaCon")) {
               pelist=PAGCAreaCon(reporte);
            }

            //PAGCProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("PAGCAreaConTodosRacts")) {
                pelist=PAGCAreaConTodosRacts(reporte);
            }

            //PAGCCompletoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("PAGCCompletoAreaConTodosRacts")) {
                pelist=PAGCCompletoAreaConTodosRacts(reporte);
            }

            //enFechaLimiteTiempoProgEd
            if (tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoAreaCon")) {
                pelist=EnFechaLimiteTiempoAreaCon(reporte);
            }

            //enFechaLimiteTiempoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoAreaConTodosRacts")) {
                pelist=EnFechaLimiteTiempoAreaConTodosRacts(reporte);
            }

            //entregadosProgEd
            if (tipoReporteUI.equalsIgnoreCase("entregadosAreaCon")) {
                pelist=entregadosAreaCon(reporte);
            }

            //entregadosProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("entregadosAreaConTodosRacts")) {                
                pelist=entregadosAreaConTodosRacts(reporte);                
            }

            //noEntregadosProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("noEntregadosAreaConTodosRacts")) {
                pelist=noEntregadosAreaConTodosRacts(reporte);
            }

            //entregadosYNoEntregadosProgEd
            if (tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosAreaCon")) {
                pelist=entregadosYNoEntregadosAreaCon(reporte);
            }

            //entregadosYNoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("entregadosYNoAreaConTodosRacts")) {
                pelist=entregadosYNoAreaConTodosRacts(reporte);
            }

            //fueraTiempoProgEd
            if (tipoReporteUI.equalsIgnoreCase("fueraTiempoAreaCon")) {
                pelist=FueraTiempoAreaCon(reporte);
            }

            //fueraTiempoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("fueraTiempoAreaConTodosRacts")) {
                pelist=FueraTiempoAreaConTodosRacts(reporte);
            }

            //noEntregadosProgEd
            if (tipoReporteUI.equalsIgnoreCase("noEntregadosAreaCon")) {
                pelist=noEntregadosAreaCon(reporte);
            }
            
            for(ReporteAvanceAux ra: pelist){                
                pelist2.add(ra);                
            }
          }
         }
        }
        
        pelist6.addAll(pelist2);
         
         ReporteAvanceAux pelist7 = new ReporteAvanceAux();
         
         int cont=0;
          
         for(ReporteAvanceAux pelist4 :pelist2){
             cont=0;
             //idUipid.add(pelist4.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUipid());
             for(ReporteAvanceAux pelist5 :pelist2){
               if(pelist4.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()==pelist5.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()){
                cont++;                   
               }          
               if(cont>=2){
                   pelist7=pelist5; 
                   pelist6.remove(pelist7);
                   break;
               }
             }
         }  
          
        //programaeducativoDelegate.getProgramaeducativo(creid);
        return pelist6;
    }
    
    public ArrayList selectionUAGrupo(ReporteAux reporteUI,String tipoReporteUI,List<String> selectCicloEscolarList,List<String> selectPlanesEstudio,List<String> selectAreaConocimiento,List<String> selectUnidadAprendisaje,List<String> selectGrupo){
        
        //selectCicloEscolarList.size();
        
        Programaeducativo proged = new Programaeducativo();
        
        List<Planestudio> planestList = null;
        
        ArrayList<ReporteAvanceAux> pelist = new ArrayList<ReporteAvanceAux>();
        
        ArrayList<ReporteAvanceAux> pelist2 = new ArrayList<ReporteAvanceAux>();
        
        ArrayList<ReporteAvanceAux> pelist6 = new ArrayList<ReporteAvanceAux>();
        
        ReporteAux reporte = new ReporteAux();
        
        //for(String selectPEid: selectProgramEducativo){            
          for(String selectCicloEs: selectCicloEscolarList){
              for(String selectPlanEs: selectPlanesEstudio){                                       
                for(String selectAreaCon: selectAreaConocimiento){                 
                    for(String selectUAp: selectUnidadAprendisaje){
                      for(String selectGr: selectGrupo){       
                              
                          
                      
            
            //int claveproged=Integer.parseInt(selectPEid.trim());
            
            proged = new Programaeducativo();
            
            //nota aqui comente
            //proged=programaeducativoDelegate.getProgramaeducativo(idproged);
    
            //planestList=planEstudioDelegate.getByProgramaeducativo(reporteUI.getClavepe());
            
            int claveacon=Integer.parseInt(selectAreaCon);
            int claveuaprend=Integer.parseInt(selectUAp);
            int clavegrupo=Integer.parseInt(selectGr);
            
            reporte = new ReporteAux();
            
            //los atributos de esta clase toman los mismos valores del
            //parametro recibido en este mismo metodo desde el beanUI
            setNumRact(reporteUI.getNumRact());                  
            //setCescicloEscolar(reporteUI.getCescicloEscolar());             
            setCescicloEscolar(selectCicloEs);             
            //setClavepe(proged.getPedclave());                  
            setClavepe(reporteUI.getClavepe());                  
            setPesvigencia(selectPlanEs);
            setAcoclave(claveacon);
            setUapclave(claveuaprend);
            setGponumero(clavegrupo);
            
            //atributos necesarios para entregados en el objeto que se manda al
            //delegate
            reporte.setNumRact(numRact);//aqui cambie para hacer consulta al ract1
            reporte.setCescicloEscolar(cescicloEscolar);        
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setClave(clavepe);        
            reporte.setAcoclave(acoclave);        
            reporte.setUapclave(uapclave);
            reporte.setGponumero(gponumero);        
            
            pelist = new ArrayList<ReporteAvanceAux>();
            
            //pe=pe;
            
            //Opciones tipoReporteUI:        
                
            //ATiempoProgEd
            if (tipoReporteUI.equalsIgnoreCase("ATiempoUAGrupo")) {
                pelist=ATiempoUAGrupo(reporte);
            }

            //ATiempoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("ATiempoUAGrupoTodosRacts")) {
                pelist=ATiempoUAGrupoTodosRacts(reporte);
            }

            //ATiempoYNoYEnLimiteProgEd
            if (tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteUAGrupo")) {
                pelist=ATiempoYNoYEnLimiteUAGrupo(reporte);
            }

            //ATiempoYNoYEnLimiteProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteUAGrupoTodosRacts")) {
                pelist=ATiempoYNoYEnLimiteUAGrupoTodosRacts(reporte);
            }

            //PAGCompletoEIncompletoProgEd
            if (tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoUAGrupo")) {
               pelist=PAGCCompletoEIncompletoUAGrupo(reporte);
            }

            //PAGCompletoProgEd
            if (tipoReporteUI.equalsIgnoreCase("PAGCCompletoUAGrupo")) {
                pelist=PAGCCompletoUAGrupo(reporte);
            }

            //PAGCompletosYNoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoUAGrupoTodosRacts")) {
                pelist=PAGCCompletosYNoUAGrupoTodosRacts(reporte);
            }

            //PAGCProgEd
            if (tipoReporteUI.equalsIgnoreCase("PAGCUAGrupo")) {
               pelist=PAGCUAGrupo(reporte);
            }

            //PAGCProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("PAGCUAGrupoTodosRacts")) {
                pelist=PAGCUAGrupoTodosRacts(reporte);
            }

            //PAGCCompletoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("PAGCCompletoUAGrupoTodosRacts")) {
                pelist=PAGCCompletoUAGrupoTodosRacts(reporte);
            }

            //enFechaLimiteTiempoProgEd
            if (tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoUAGrupo")) {
                pelist=EnFechaLimiteTiempoUAGrupo(reporte);
            }

            //enFechaLimiteTiempoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoUAGrupoTodosRacts")) {
                pelist=EnFechaLimiteTiempoUAGrupoTodosRacts(reporte);
            }

            //entregadosProgEd
            if (tipoReporteUI.equalsIgnoreCase("entregadosUAGrupo")) {
                pelist=entregadosUAGrupo(reporte);
            }

            //entregadosProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("entregadosUAGrupoTodosRacts")) {                
                pelist=entregadosUAGrupoTodosRacts(reporte);                
            }

            //noEntregadosProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("noEntregadosUAGrupoTodosRacts")) {
                pelist=noEntregadosUAGrupoTodosRacts(reporte);
            }

            //entregadosYNoEntregadosProgEd
            if (tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosUAGrupo")) {
                pelist=entregadosYNoEntregadosUAGrupo(reporte);
            }

            //entregadosYNoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("entregadosYNoUAGrupoTodosRacts")) {
                pelist=entregadosYNoUAGrupoTodosRacts(reporte);
            }

            //fueraTiempoProgEd
            if (tipoReporteUI.equalsIgnoreCase("fueraTiempoUAGrupo")) {
                pelist=FueraTiempoUAGrupo(reporte);
            }

            //fueraTiempoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("fueraTiempoUAGrupoTodosRacts")) {
                pelist=FueraTiempoUAGrupoTodosRacts(reporte);
            }

            //noEntregadosProgEd
            if (tipoReporteUI.equalsIgnoreCase("noEntregadosUAGrupo")) {
                pelist=noEntregadosUAGrupo(reporte);
            }
            
            for(ReporteAvanceAux ra: pelist){                
                pelist2.add(ra);                
            }
            
            }
           }
          }
         }
        }
    
         //ArrayList<Integer> idUipid = new ArrayList<Integer>();
         //ArrayList<Integer> idUipid2 = new ArrayList<Integer>();
         
         pelist6.addAll(pelist2);
         
         ReporteAvanceAux pelist7 = new ReporteAvanceAux();
         
         int cont=0;
          
         for(ReporteAvanceAux pelist4 :pelist2){
             cont=0;
             //idUipid.add(pelist4.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUipid());
             for(ReporteAvanceAux pelist5 :pelist2){
               if(pelist4.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()==pelist5.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()){
                cont++;                   
               }          
               if(cont>=2){
                   pelist7=pelist5;
                   pelist6.remove(pelist7);
                   break;
               }
             }
         }
          
        //programaeducativoDelegate.getProgramaeducativo(creid);
        return pelist6;
    }
    
    public ArrayList selectionProfGrupo(ReporteAux reporteUI,String tipoReporteUI,List<String> selectCicloEscolarList,List<String> selectPlanesEstudio,List<String> selectAreaConocimiento,List<String> selectUnidadAprendisaje,List<String> selectGrupo,List<String> selectProfesor){
        
        //selectCicloEscolarList.size();
        
        Programaeducativo proged = new Programaeducativo();
        
        List<Planestudio> planestList = null;
        
        ArrayList<ReporteAvanceAux> pelist = new ArrayList<ReporteAvanceAux>();
        
        ArrayList<ReporteAvanceAux> pelist2 = new ArrayList<ReporteAvanceAux>();
        
        ArrayList<ReporteAvanceAux> pelist6 = new ArrayList<ReporteAvanceAux>();
        
        ReporteAux reporte = new ReporteAux();
        
        //for(String selectPEid: selectProgramEducativo){            
          for(String selectCicloEs: selectCicloEscolarList){
              for(String selectPlanEs: selectPlanesEstudio){                                       
                for(String selectAreaCon: selectAreaConocimiento){                 
                    for(String selectUAp: selectUnidadAprendisaje){
                      //for(String selectGr: selectGrupo){       
                          for(String selectProf: selectProfesor){       
                              
                          
                              System.out.println("***********entro select Profesor en BeanHelper********");         
            
            //int claveproged=Integer.parseInt(selectPEid.trim());
            
            proged = new Programaeducativo();
            
            //nota aqui comente
            //proged=programaeducativoDelegate.getProgramaeducativo(idproged);
    
            //planestList=planEstudioDelegate.getByProgramaeducativo(reporteUI.getClavepe());
            
            int claveacon=Integer.parseInt(selectAreaCon);
            int claveuaprend=Integer.parseInt(selectUAp);
   //         int clavegrupo=Integer.parseInt(selectGr);
            int claveprofesor=Integer.parseInt(selectProf);
            
            reporte = new ReporteAux();
            
            //los atributos de esta clase toman los mismos valores del
            //parametro recibido en este mismo metodo desde el beanUI
            setNumRact(reporteUI.getNumRact());                  
            //setCescicloEscolar(reporteUI.getCescicloEscolar());             
            setCescicloEscolar(selectCicloEs);             
            //setClavepe(proged.getPedclave());                  
            setClavepe(reporteUI.getClavepe());                  
            setPesvigencia(selectPlanEs);
            setAcoclave(claveacon);
            setUapclave(claveuaprend);
   //         setGponumero(clavegrupo);
            setPronumeroEmpleado(claveprofesor);
            
            //atributos necesarios para entregados en el objeto que se manda al
            //delegate
            reporte.setNumRact(numRact);//aqui cambie para hacer consulta al ract1
            reporte.setCescicloEscolar(cescicloEscolar);        
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setClave(clavepe);        
            reporte.setAcoclave(acoclave);        
            reporte.setUapclave(uapclave);
            reporte.setGponumero(gponumero); 
            reporte.setPronumeroEmpleado(pronumeroEmpleado);
            
            pelist = new ArrayList<ReporteAvanceAux>();
            
            //pe=pe;
            
            //Opciones tipoReporteUI:        
                
            //ATiempoProgEd
            if (tipoReporteUI.equalsIgnoreCase("ATiempoProfGrupo")) {
                pelist=ATiempoProfGrupo(reporte);
            }

            //ATiempoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("ATiempoProfGrupoTodosRacts")) {
                pelist=ATiempoProfGrupoTodosRacts(reporte);
            }

            //ATiempoYNoYEnLimiteProgEd
            if (tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProfGrupo")) {
                pelist=ATiempoYNoYEnLimiteProfGrupo(reporte);
            }

            //ATiempoYNoYEnLimiteProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProfGrupoTodosRacts")) {
                pelist=ATiempoYNoYEnLimiteProfGrupoTodosRacts(reporte);
            }

            //PAGCompletoEIncompletoProgEd
            if (tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoProfGrupo")) {
               pelist=PAGCCompletoEIncompletoProfGrupo(reporte);
            }

            //PAGCompletoProgEd
            if (tipoReporteUI.equalsIgnoreCase("PAGCCompletoProfGrupo")) {
                pelist=PAGCCompletoProfGrupo(reporte);
            }

            //PAGCompletosYNoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoProfGrupoTodosRacts")) {
                pelist=PAGCCompletosYNoProfGrupoTodosRacts(reporte);
            }

            //PAGCProgEd
            if (tipoReporteUI.equalsIgnoreCase("PAGCProfGrupo")) {
               pelist=PAGCProfGrupo(reporte);
            }

            //PAGCProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("PAGCProfGrupoTodosRacts")) {
                pelist=PAGCProfGrupoTodosRacts(reporte);
            }

            //PAGCCompletoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("PAGCCompletoProfGrupoTodosRacts")) {
                pelist=PAGCCompletoProfGrupoTodosRacts(reporte);
            }

            //enFechaLimiteTiempoProgEd
            if (tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProfGrupo")) {
                pelist=EnFechaLimiteTiempoProfGrupo(reporte);
            }

            //enFechaLimiteTiempoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProfGrupoTodosRacts")) {
                pelist=EnFechaLimiteTiempoProfGrupoTodosRacts(reporte);
            }

            //entregadosProgEd
            if (tipoReporteUI.equalsIgnoreCase("entregadosProfGrupo")) {
                pelist=entregadosProfGrupo(reporte);
            }

            //entregadosProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("entregadosProfGrupoTodosRacts")) {                
                pelist=entregadosProfGrupoTodosRacts(reporte);                
            }

            //noEntregadosProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("noEntregadosProfGrupoTodosRacts")) {
                pelist=noEntregadosProfGrupoTodosRacts(reporte);
            }

            //entregadosYNoEntregadosProgEd
            if (tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosProfGrupo")) {
                pelist=entregadosYNoEntregadosProfGrupo(reporte);
            }

            //entregadosYNoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("entregadosYNoProfGrupoTodosRacts")) {
                pelist=entregadosYNoProfGrupoTodosRacts(reporte);
            }

            //fueraTiempoProgEd
            if (tipoReporteUI.equalsIgnoreCase("fueraTiempoProfGrupo")) {
                pelist=FueraTiempoProfGrupo(reporte);
            }

            //fueraTiempoProgEdTodosRacts
            if (tipoReporteUI.equalsIgnoreCase("fueraTiempoProfGrupoTodosRacts")) {
                pelist=FueraTiempoProfGrupoTodosRacts(reporte);
            }

            //noEntregadosProgEd
            if (tipoReporteUI.equalsIgnoreCase("noEntregadosProfGrupo")) {
                pelist=noEntregadosProfGrupo(reporte);
            }
            
            for(ReporteAvanceAux ra: pelist){                
                pelist2.add(ra);                
            }
            
            }
           }
          }
         }
        }
     //  }
    
         //ArrayList<Integer> idUipid = new ArrayList<Integer>();
         //ArrayList<Integer> idUipid2 = new ArrayList<Integer>();
         
         pelist6.addAll(pelist2);
         
         ReporteAvanceAux pelist7 = new ReporteAvanceAux();
         
         int cont=0;
          
         for(ReporteAvanceAux pelist4 :pelist2){
             cont=0;
             //idUipid.add(pelist4.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUipid());
             for(ReporteAvanceAux pelist5 :pelist2){
               if(pelist4.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()==pelist5.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()){
                cont++;                   
               }          
               if(cont>=2){
                   pelist7=pelist5;
                   pelist6.remove(pelist7);
                   break;
               }
             }
         }
          
        //programaeducativoDelegate.getProgramaeducativo(creid);
        return pelist6;
    }
    
    public ArrayList ordenarTodosRactsEntregadosYNo(ArrayList<ReporteAvanceAux> listaAuxRact1,ArrayList<ReporteAvanceAux> listaAuxRact2,ArrayList<ReporteAvanceAux> listaAuxRact3){
        //posiciones de la lista por cada una de las listas de ract(por que no es for
        //normal sino foreach y se necesita la posicion para actualizar(modificar o update) 
        //el valor en la posición correcta
        int posRact1=0;
        int posRact2=0;
        int posRact3=0;
        
        //ciclos y condiciones para tomar en cuenta los racts desde el primero aunque no haya 
        //entregado los demas es decir que cualquiera de los tres este vacio empezando a 
        //comparar desde el primero o si no comparar segundo o tercero y los otros no entregados
        //aqui abajo modifique Jesus Ruelas        
        if((listaAuxRact2.isEmpty())&&(listaAuxRact3.isEmpty())&&!(listaAuxRact1.isEmpty())){
            for(ReporteAvanceAux repRact1: listaAuxRact1 ){
                 repRact1.setAreaConocimiento(repRact1.getAreaConocimiento());                 
                
                 repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact2(0);
                 repRact1.setPorcentAvanceRact3(0);
                 
                 repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                 repRact1.setStatusRact2("No entrego");
                 repRact1.setStatusRact3("No entrego");
                 
                 repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact2(null);
                 repRact1.setFechaElaboracRact3(null);
                 
                 repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                 repRact1.setFechaLimiteRact2(null);
                 repRact1.setFechaLimiteRact3(null);
                 
                 repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact2("Incompleto");
                 repRact1.setTipoReporteSelecRact3("Incompleto");
                 
                 listaAuxRact1.set(posRact1, repRact1);                 
                                  
                 posRact1++;
            }
        }else{
            
            if((listaAuxRact2.isEmpty())&&!(listaAuxRact1.isEmpty())){
             for(ReporteAvanceAux repRact1: listaAuxRact1 ){
                  repRact1.setAreaConocimiento(repRact1.getAreaConocimiento());                 
                 
                  repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                  repRact1.setPorcentAvanceRact2(0);
                  //repRact1.setPorcentAvanceRact3(0);
                  
                  repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                  repRact1.setStatusRact2("No entrego");
                  //repRact1.setStatusRact3("No entrego");
                  
                  repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                  repRact1.setFechaElaboracRact2(null);
                  //repRact1.setFechaElaboracRact3(null);
                  
                  repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                  repRact1.setFechaLimiteRact2(null);
                  //repRact1.setFechaLimiteRact3(repRact1.fechaLimite);
                  
                  repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                  repRact1.setTipoReporteSelecRact2("Incompleto");
                  //repRact1.setTipoReporteSelecRact3("Incompleto");
                  
                  listaAuxRact1.set(posRact1, repRact1);                  
                                    
                  posRact1++;
             }
        }else{
                if((listaAuxRact3.isEmpty())&&!(listaAuxRact1.isEmpty())){
                   for(ReporteAvanceAux repRact1: listaAuxRact1 ){
                      repRact1.setAreaConocimiento(repRact1.getAreaConocimiento());
                       
                      repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                      //repRact1.setPorcentAvanceRact2(0);
                      repRact1.setPorcentAvanceRact3(0);
                  
                      repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                      //repRact1.setStatusRact2("No entrego");
                      repRact1.setStatusRact3("No entrego");
                  
                      repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                      //repRact1.setFechaElaboracRact2(null);
                      repRact1.setFechaElaboracRact3(null);
                  
                      repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                      //repRact1.setFechaLimiteRact2(repRact1.fechaLimite);
                      repRact1.setFechaLimiteRact3(null);
                  
                      repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                      //repRact1.setTipoReporteSelecRact2("Incompleto");
                      repRact1.setTipoReporteSelecRact3("Incompleto");
                  
                      listaAuxRact1.set(posRact1, repRact1);                  
                                        
                      posRact1++;
            }
          
        }
      }
     }
        
        posRact1=0;
        posRact2=0;
        posRact3=0; 
        //aqui arriba modifique Jesus Ruelas
        
        /*
        
        //ciclos y condiciones para tomar en cuenta los racts desde el primero aunque no haya 
        //entregado los demas es decir que cualquiera de los tres este vacio empezando a 
        //comparar desde el primero o si no comparar segundo o tercero y los otros no entregados
        //aqui abajo modifique Jesus Ruelas        
        if((listaAuxRact1.isEmpty())&&(listaAuxRact3.isEmpty())&&!(listaAuxRact2.isEmpty())){
            for(ReporteAvanceAux repRact2: listaAuxRact2 ){
                 repRact2.setPorcentAvanceRact2(repRact2.reporteAvance.getRacavanceGlobal());
                 repRact2.setPorcentAvanceRact1(0);
                 repRact2.setPorcentAvanceRact3(0);
                 
                 repRact2.setStatusRact2(repRact2.reporteAvance.getRacstatus());
                 repRact2.setStatusRact1("No entrego");
                 repRact2.setStatusRact3("No entrego");
                 
                 repRact2.setFechaElaboracRact2(repRact2.reporteAvance.getRacfechaElaboracion());
                 repRact2.setFechaElaboracRact1(null);
                 repRact2.setFechaElaboracRact3(null);
                 
                 repRact2.setFechaLimiteRact2(repRact2.fechaLimite);
                 repRact2.setFechaLimiteRact1(null);
                 repRact2.setFechaLimiteRact3(null);
                 
                 repRact2.setTipoReporteSelecRact2(repRact2.tipoReporteSelec);
                 repRact2.setTipoReporteSelecRact1("Incompleto");
                 repRact2.setTipoReporteSelecRact3("Incompleto");
                 
                 listaAuxRact1.set(posRact1, repRact2);                 
                                  
                 posRact1++;
            }
        }else{
            
            if((listaAuxRact1.isEmpty())&&!(listaAuxRact2.isEmpty())){
             for(ReporteAvanceAux repRact2: listaAuxRact2 ){
                  repRact2.setPorcentAvanceRact2(repRact2.reporteAvance.getRacavanceGlobal());
                  repRact2.setPorcentAvanceRact1(0);
                  //repRact1.setPorcentAvanceRact3(0);
                  
                  repRact2.setStatusRact2(repRact2.reporteAvance.getRacstatus());
                  repRact2.setStatusRact1("No entrego");
                  //repRact1.setStatusRact3("No entrego");
                  
                  repRact2.setFechaElaboracRact2(repRact2.reporteAvance.getRacfechaElaboracion());
                  repRact2.setFechaElaboracRact1(null);
                  //repRact1.setFechaElaboracRact3(null);
                  
                  repRact2.setFechaLimiteRact2(repRact2.fechaLimite);
                  repRact2.setFechaLimiteRact1(null);
                  //repRact1.setFechaLimiteRact3(repRact1.fechaLimite);
                  
                  repRact2.setTipoReporteSelecRact2(repRact2.tipoReporteSelec);
                  repRact2.setTipoReporteSelecRact1("Incompleto");
                  //repRact1.setTipoReporteSelecRact3("Incompleto");
                  
                  listaAuxRact1.set(posRact1, repRact2);                  
                                    
                  posRact1++;
             }
        }else{
                if((listaAuxRact3.isEmpty())&&!(listaAuxRact2.isEmpty())){
                   for(ReporteAvanceAux repRact2: listaAuxRact2 ){
                      repRact2.setPorcentAvanceRact2(repRact2.reporteAvance.getRacavanceGlobal());
                      //repRact1.setPorcentAvanceRact2(0);
                      repRact2.setPorcentAvanceRact3(0);
                  
                      repRact2.setStatusRact2(repRact2.reporteAvance.getRacstatus());
                      //repRact1.setStatusRact2("No entrego");
                      repRact2.setStatusRact3("No entrego");
                  
                      repRact2.setFechaElaboracRact2(repRact2.reporteAvance.getRacfechaElaboracion());
                      //repRact1.setFechaElaboracRact2(null);
                      repRact2.setFechaElaboracRact3(null);
                  
                      repRact2.setFechaLimiteRact2(repRact2.fechaLimite);
                      //repRact1.setFechaLimiteRact2(repRact1.fechaLimite);
                      repRact2.setFechaLimiteRact3(null);
                  
                      repRact2.setTipoReporteSelecRact2(repRact2.tipoReporteSelec);
                      //repRact1.setTipoReporteSelecRact2("Incompleto");
                      repRact2.setTipoReporteSelecRact3("Incompleto");
                  
                      listaAuxRact1.set(posRact1, repRact2);                  
                                        
                      posRact1++;
            }
          
        }
      }
     }
        
        posRact1=0;
        posRact2=0;
        posRact3=0; 
        //aqui arriba modifique Jesus Ruelas

        //ciclos y condiciones para tomar en cuenta los racts desde el primero aunque no haya 
        //entregado los demas es decir que cualquiera de los tres este vacio empezando a 
        //comparar desde el primero o si no comparar segundo o tercero y los otros no entregados
        //aqui abajo modifique Jesus Ruelas        
        if((listaAuxRact1.isEmpty())&&(listaAuxRact2.isEmpty())&&!(listaAuxRact3.isEmpty())){
            for(ReporteAvanceAux repRact3: listaAuxRact3 ){
                 repRact3.setPorcentAvanceRact3(repRact3.reporteAvance.getRacavanceGlobal());
                 repRact3.setPorcentAvanceRact1(0);
                 repRact3.setPorcentAvanceRact2(0);
                 
                 repRact3.setStatusRact3(repRact3.reporteAvance.getRacstatus());
                 repRact3.setStatusRact1("No entrego");
                 repRact3.setStatusRact2("No entrego");
                 
                 repRact3.setFechaElaboracRact3(repRact3.reporteAvance.getRacfechaElaboracion());
                 repRact3.setFechaElaboracRact1(null);
                 repRact3.setFechaElaboracRact2(null);
                 
                 repRact3.setFechaLimiteRact3(repRact3.fechaLimite);
                 repRact3.setFechaLimiteRact1(null);
                 repRact3.setFechaLimiteRact2(null);
                 
                 repRact3.setTipoReporteSelecRact3(repRact3.tipoReporteSelec);
                 repRact3.setTipoReporteSelecRact1("Incompleto");
                 repRact3.setTipoReporteSelecRact2("Incompleto");
                 
                 listaAuxRact1.set(posRact1, repRact3);                 
                                  
                 posRact1++;
            }
        }else{
            
            if((listaAuxRact1.isEmpty())&&!(listaAuxRact3.isEmpty())){
             for(ReporteAvanceAux repRact3: listaAuxRact3 ){
                  repRact3.setPorcentAvanceRact3(repRact3.reporteAvance.getRacavanceGlobal());
                  repRact3.setPorcentAvanceRact1(0);
                  //repRact1.setPorcentAvanceRact3(0);
                  
                  repRact3.setStatusRact3(repRact3.reporteAvance.getRacstatus());
                  repRact3.setStatusRact1("No entrego");
                  //repRact1.setStatusRact3("No entrego");
                  
                  repRact3.setFechaElaboracRact3(repRact3.reporteAvance.getRacfechaElaboracion());
                  repRact3.setFechaElaboracRact1(null);
                  //repRact1.setFechaElaboracRact3(null);
                  
                  repRact3.setFechaLimiteRact3(repRact3.fechaLimite);
                  repRact3.setFechaLimiteRact1(null);
                  //repRact1.setFechaLimiteRact3(repRact1.fechaLimite);
                  
                  repRact3.setTipoReporteSelecRact3(repRact3.tipoReporteSelec);
                  repRact3.setTipoReporteSelecRact1("Incompleto");
                  //repRact1.setTipoReporteSelecRact3("Incompleto");
                  
                  listaAuxRact1.set(posRact1, repRact3);                  
                                    
                  posRact1++;
             }
        }else{
                if((listaAuxRact2.isEmpty())&&!(listaAuxRact3.isEmpty())){
                   for(ReporteAvanceAux repRact3: listaAuxRact3 ){
                      repRact3.setPorcentAvanceRact3(repRact3.reporteAvance.getRacavanceGlobal());
                      //repRact1.setPorcentAvanceRact2(0);
                      repRact3.setPorcentAvanceRact2(0);
                  
                      repRact3.setStatusRact3(repRact3.reporteAvance.getRacstatus());
                      //repRact1.setStatusRact2("No entrego");
                      repRact3.setStatusRact2("No entrego");
                  
                      repRact3.setFechaElaboracRact3(repRact3.reporteAvance.getRacfechaElaboracion());
                      //repRact1.setFechaElaboracRact2(null);
                      repRact3.setFechaElaboracRact2(null);
                  
                      repRact3.setFechaLimiteRact3(repRact3.fechaLimite);
                      //repRact1.setFechaLimiteRact2(repRact1.fechaLimite);
                      repRact3.setFechaLimiteRact2(null);
                  
                      repRact3.setTipoReporteSelecRact3(repRact3.tipoReporteSelec);
                      //repRact1.setTipoReporteSelecRact2("Incompleto");
                      repRact3.setTipoReporteSelecRact2("Incompleto");
                  
                      listaAuxRact1.set(posRact1, repRact3);                  
                                        
                      posRact1++;
            }
          
        }
      }
     }
        */
        
        posRact1=0;
        posRact2=0;
        posRact3=0; 
        //aqui arriba modifique Jesus Ruelas
        
        boolean banderaRact3=false;
        
        //Para comparar Ract1 con Ract2 y agregar el los valores del ract2 al del ract1 en una
        //sola linea de la lista
        for(ReporteAvanceAux repRact1: listaAuxRact1 ){
           for(ReporteAvanceAux repRact2: listaAuxRact2 ){
             if(repRact1.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()==repRact2.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()){
                 
                 //si son iguales el id de unidad de aprendizajeimparteprofesor agrega ract2 al ract1
                 //en una sola linea y toma mas valores para la lista que ya no es de un solo ract
                 
                 repRact1.setAreaConocimiento(repRact1.getAreaConocimiento());
                 
                 repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact2(repRact2.reporteAvance.getRacavanceGlobal());
                 
                 repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                 repRact1.setStatusRact2(repRact2.reporteAvance.getRacstatus());
                 
                 repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact2(repRact2.reporteAvance.getRacfechaElaboracion());
                 
                 repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                 repRact1.setFechaLimiteRact2(repRact2.fechaLimite);
                 
                 repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact2(repRact2.tipoReporteSelec);
                 
                 listaAuxRact1.set(posRact1, repRact1);
                 
                 banderaRact3=true;
                 
                 break;
             }
             else{
                 
                 //si no son iguales el id de unidad de aprendizajeimparteprofesor agrega ract2 al ract1
                 //en una sola linea y toma mas valores vacios para la lista que ya no es de un solo ract
                 
                 repRact1.setAreaConocimiento(repRact1.getAreaConocimiento());
                 
                 repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());                 
                 repRact1.setPorcentAvanceRact2(0);
                 
                 repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                 repRact1.setStatusRact2("No entrego");
                 
                 repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact2(null);
                 
                 repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                 repRact1.setFechaLimiteRact2(repRact2.fechaLimite);
                 
                 repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact2("No entregado");
                 
                 listaAuxRact1.set(posRact1, repRact1);                  
                 
                 banderaRact3=true;
                 
             }
             posRact2++;
           } 
           posRact1++;
        }
        
        posRact1=0;
        posRact2=0;
        posRact3=0;                 
        
        //Para comparar Ract1 con Ract3 y agregar los valores del ract3 a los del ract1(que ya tiene lo del
        //ract2) para mostrarlo en una sola línea de la lista
        for(ReporteAvanceAux repRact1: listaAuxRact1 ){
           for(ReporteAvanceAux repRact3: listaAuxRact3 ){
             if(repRact1.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()==repRact3.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()){
                 
                 //si son iguales el id de unidad de aprendizajeimparteprofesor agrega ract3 al ract1
                 //en una sola linea y toma mas valores para la lista que ya no es de un solo ract
                 
                 repRact1.setAreaConocimiento(repRact1.getAreaConocimiento());
                 
                 repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact3(repRact3.reporteAvance.getRacavanceGlobal());
                 
                 repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                 repRact1.setStatusRact3(repRact3.reporteAvance.getRacstatus());
                 
                 repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact3(repRact3.reporteAvance.getRacfechaElaboracion());
                 
                 repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                 repRact1.setFechaLimiteRact3(repRact3.fechaLimite);
                 
                 repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact3(repRact3.tipoReporteSelec);
                 
                 listaAuxRact1.set(posRact1, repRact1);                 
                 
                 banderaRact3=true;
                 
                 break;
             }
             else{
                 
                 //si no son iguales el id de unidad de aprendizajeimparteprofesor agrega ract3 al ract1
                 //en una sola linea y toma mas valores vacios para la lista que ya no es de un solo ract
                 
                 repRact1.setAreaConocimiento(repRact1.getAreaConocimiento());
                 
                 repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact3(0);
                 
                 repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                 repRact1.setStatusRact3("No entrego");
                 
                 repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact3(null);
                 
                 repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                 repRact1.setFechaLimiteRact3(repRact3.fechaLimite);
                 
                 repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact3("No entregado");
                 
                 listaAuxRact1.set(posRact1, repRact1); 
                 
                 banderaRact3=true;
                 
             }
             posRact3++;
           } 
           posRact1++;
        }
        
        posRact1=0;
        posRact2=0;
        posRact3=0;  

        ArrayList<ReporteAvanceAux> listaAuxRact1b=new ArrayList<ReporteAvanceAux>();
        
        ArrayList<Integer> listaUipid1 = new ArrayList<Integer>();
        ArrayList<Integer> listaUipid2 = new ArrayList<Integer>();
        ArrayList<Integer> listaUipid3 = new ArrayList<Integer>();
        ArrayList<Integer> listaUipidN = new ArrayList<Integer>();
        
        for(ReporteAvanceAux repRact1: listaAuxRact1){
            listaUipid1.add(repRact1.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
        }
        
        for(ReporteAvanceAux repRact2: listaAuxRact2){
            listaUipid2.add(repRact2.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
        }
        
        for(ReporteAvanceAux repRact3: listaAuxRact3){
            listaUipid3.add(repRact3.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
        }
        
        //ReporteAvanceAux repRact1a = new ReporteAvanceAux();
        //ReporteAvanceAux repRact2a = new ReporteAvanceAux();
        
        for (Integer uipid2b : listaUipid2) {
            for (Integer uipid3b : listaUipid3) {
                if (listaUipid1.contains(uipid2b)) {

                } else {
                    if (listaUipid2.contains(uipid3b)&&!(listaUipid1.contains(uipid3b))) {

                        //} else {
                        for (ReporteAvanceAux repRact2 : listaAuxRact2) {
                            for (ReporteAvanceAux repRact3 : listaAuxRact3) {

                                if ((repRact2.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid() == uipid3b) && (repRact3.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid() == uipid3b)) {
                                    repRact2.setAreaConocimiento(repRact2.getAreaConocimiento());

                                    repRact2.setPorcentAvanceRact2(repRact2.reporteAvance.getRacavanceGlobal());
                                    repRact2.setPorcentAvanceRact3(repRact3.reporteAvance.getRacavanceGlobal());
                                    //repRact2.setPorcentAvanceRact1(0);

                                    repRact2.setStatusRact2(repRact2.reporteAvance.getRacstatus());
                                    repRact2.setStatusRact3(repRact3.reporteAvance.getRacstatus());
                                    //repRact2.setStatusRact1("No entrego");

                                    repRact2.setFechaElaboracRact2(repRact2.reporteAvance.getRacfechaElaboracion());
                                    repRact2.setFechaElaboracRact3(repRact3.reporteAvance.getRacfechaElaboracion());
                                    //repRact2.setFechaElaboracRact1(null);

                                    repRact2.setFechaLimiteRact2(repRact2.fechaLimite);
                                    repRact2.setFechaLimiteRact3(repRact3.fechaLimite);
                                    //repRact2.setFechaLimiteRact1(null);

                                    repRact2.setTipoReporteSelecRact2(repRact2.tipoReporteSelec);
                                    repRact2.setTipoReporteSelecRact3(repRact3.tipoReporteSelec);
                        //repRact2.setTipoReporteSelecRact1("No entregado");

                                    //listaAuxRact1.set(posRact1, repRact1a);                  
                                    //listaAuxRact1.set(listaAuxRact1.size(), repRact2);
                                    
                                    for (ReporteAvanceAux repRactn : listaAuxRact1b) {
                                        listaUipidN.add(repRactn.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
                                    }                           
                                    
                                    if(listaUipidN.contains(uipid3b)){
                                        
                                    }else{
                                    listaAuxRact1b.add(repRact2);
                                    }
                                    
                                    listaUipidN.clear();
                                    
                                    break;
                                }
                                
                            }
                        }
                    }
                }
                //break;
            }
        }
        
        listaAuxRact1.addAll(listaAuxRact1b);
        
        listaAuxRact1b.clear();
        
        listaUipid1.clear();
        
        for(ReporteAvanceAux repRact1: listaAuxRact1){
            listaUipid1.add(repRact1.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
        }
        
        
        for (Integer uipid2 : listaUipid2) {
            if (listaUipid1.contains(uipid2)) {

            } else {
                for (ReporteAvanceAux repRact2 : listaAuxRact2) {
                    
                    if (repRact2.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid() == uipid2) {
                        repRact2.setAreaConocimiento(repRact2.getAreaConocimiento());
                        
                        repRact2.setPorcentAvanceRact2(repRact2.reporteAvance.getRacavanceGlobal());
                        //repRact2.setPorcentAvanceRact1(0);

                        repRact2.setStatusRact2(repRact2.reporteAvance.getRacstatus());
                        //repRact2.setStatusRact1("No entrego");

                        repRact2.setFechaElaboracRact2(repRact2.reporteAvance.getRacfechaElaboracion());
                        //repRact2.setFechaElaboracRact1(null);

                        repRact2.setFechaLimiteRact2(repRact2.fechaLimite);
                        //repRact2.setFechaLimiteRact1(null);

                        repRact2.setTipoReporteSelecRact2(repRact2.tipoReporteSelec);
                        //repRact2.setTipoReporteSelecRact1("No entregado");

                        //listaAuxRact1.set(posRact1, repRact1a);                  
                        //listaAuxRact1.set(listaAuxRact1.size(), repRact2);
                        listaAuxRact1b.add(repRact2);
                    }
                }
            }
        }
        
        for (Integer uipid3 : listaUipid3) {
            if (listaUipid1.contains(uipid3)) {

            } else {
                for (ReporteAvanceAux repRact3 : listaAuxRact3) {
                    if (repRact3.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid() == uipid3) {
                        repRact3.setAreaConocimiento(repRact3.getAreaConocimiento());
                        
                        repRact3.setPorcentAvanceRact3(repRact3.reporteAvance.getRacavanceGlobal());
                        //repRact2.setPorcentAvanceRact1(0);

                        repRact3.setStatusRact3(repRact3.reporteAvance.getRacstatus());
                        //repRact2.setStatusRact1("No entrego");

                        repRact3.setFechaElaboracRact3(repRact3.reporteAvance.getRacfechaElaboracion());
                        //repRact2.setFechaElaboracRact1(null);

                        repRact3.setFechaLimiteRact3(repRact3.fechaLimite);
                        //repRact2.setFechaLimiteRact1(null);

                        repRact3.setTipoReporteSelecRact3(repRact3.tipoReporteSelec);
                        //repRact2.setTipoReporteSelecRact1("No entregado");

                        //listaAuxRact1.set(posRact1, repRact1a);                  
                        //listaAuxRact1.set(listaAuxRact1.size(), repRact2);
                        listaAuxRact1b.add(repRact3);
                    }
                }
            }
        }
        
        /*
        //Para comparar Ract1 con Ract2 y agregar el los valores del ract2 al del ract1 en una
        //sola linea de la lista
        for(ReporteAvanceAux repRact2: listaAuxRact2 ){
           for(ReporteAvanceAux repRact1: listaAuxRact1 ){
             if(repRact2.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()==repRact1.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()){
                 
                 /*
                 //si son iguales el id de unidad de aprendizajeimparteprofesor agrega ract2 al ract1
                 //en una sola linea y toma mas valores para la lista que ya no es de un solo ract
                 
                 repRact1.setPorcentAvanceRact2(repRact2.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                 
                 repRact1.setStatusRact2(repRact2.reporteAvance.getRacstatus());
                 repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                 
                 repRact1.setFechaElaboracRact2(repRact2.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                 
                 repRact1.setFechaLimiteRact2(repRact2.fechaLimite);
                 repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                 
                 repRact1.setTipoReporteSelecRact2(repRact2.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                 
                 listaAuxRact1.set(posRact1, repRact1);
                 
                 banderaRact3=true;
                  */ 
        /*
                 break;
                  
             }
             else{
                 
                 //si no son iguales el id de unidad de aprendizajeimparteprofesor agrega ract2 al ract1
                 //en una sola linea y toma mas valores vacios para la lista que ya no es de un solo ract
                 
                 repRact1.setPorcentAvanceRact2(repRact2.reporteAvance.getRacavanceGlobal());
                 //repRact1.setPorcentAvanceRact1(0);
                 
                 repRact1.setStatusRact2(repRact2.reporteAvance.getRacstatus());
                 //repRact1.setStatusRact1("No entrego");
                 
                 repRact1.setFechaElaboracRact2(repRact2.reporteAvance.getRacfechaElaboracion());
                 //repRact1.setFechaElaboracRact1(null);
                 
                 repRact1.setFechaLimiteRact2(repRact2.fechaLimite);
                 //repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                 
                 repRact1.setTipoReporteSelecRact2(repRact2.tipoReporteSelec);
                 //repRact1.setTipoReporteSelecRact1("No entregado");
                 
                 //listaAuxRact1.set(posRact1, repRact1a);                  
                 //listaAuxRact1.set(listaAuxRact1.size(), repRact2);
                 listaAuxRact1b.add(repRact1);                  
                 
                 banderaRact3=true;
                 
             }
             posRact1++;
           } 
           posRact2++;
           posRact1=0;
        }
        
        posRact1=0;
        posRact2=0;
        posRact3=0;  
        

        
        //Para comparar Ract1 con Ract3 y agregar los valores del ract3 a los del ract1(que ya tiene lo del
        //ract2) para mostrarlo en una sola línea de la lista
        for(ReporteAvanceAux repRact3: listaAuxRact3 ){
           for(ReporteAvanceAux repRact1: listaAuxRact1 ){
             if(repRact3.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()==repRact1.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()){
                 /*
                 //si son iguales el id de unidad de aprendizajeimparteprofesor agrega ract3 al ract1
                 //en una sola linea y toma mas valores para la lista que ya no es de un solo ract
                 
                 repRact1.setPorcentAvanceRact3(repRact3.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                 
                 repRact1.setStatusRact3(repRact3.reporteAvance.getRacstatus());
                 repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                 
                 repRact1.setFechaElaboracRact3(repRact3.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                 
                 repRact1.setFechaLimiteRact3(repRact3.fechaLimite);
                 repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                 
                 repRact1.setTipoReporteSelecRact3(repRact3.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                 
                 listaAuxRact1.set(posRact1, repRact1);                 
                 
                 banderaRact3=true;
                 */
        /*
                 break;
                 
             }
             else{
                 
                 //si no son iguales el id de unidad de aprendizajeimparteprofesor agrega ract3 al ract1
                 //en una sola linea y toma mas valores vacios para la lista que ya no es de un solo ract
                 
                 repRact1.setPorcentAvanceRact3(repRact3.reporteAvance.getRacavanceGlobal());
                 //repRact1.setPorcentAvanceRact1(0);
                 
                 repRact1.setStatusRact3(repRact3.reporteAvance.getRacstatus());
                 //repRact1.setStatusRact1("No entrego");
                 
                 repRact1.setFechaElaboracRact3(repRact3.reporteAvance.getRacfechaElaboracion());
                 //repRact1.setFechaElaboracRact1(null);
                 
                 repRact1.setFechaLimiteRact3(repRact3.fechaLimite);
                 //repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                 
                 repRact1.setTipoReporteSelecRact3(repRact3.tipoReporteSelec);
                 //repRact1.setTipoReporteSelecRact1("No entregado");
                 
                 //listaAuxRact1.set(posRact1, repRact1);
                 //listaAuxRact1.set(listaAuxRact1.size(), repRact3);
                 listaAuxRact1b.add(repRact1);                  
                 
                 banderaRact3=true;
                 
             }
             posRact1++;
           } 
           posRact3++;
           posRact1=0;
        }
        
        posRact1=0;
        posRact2=0;
        posRact3=0;
        
        listaAuxRact1.addAll(listaAuxRact1b);
       
        
        /*
        //Para comparar Ract1 con Ract3 y agregar los valores del ract3 a los del ract1(que ya tiene lo del
        //ract2) para mostrarlo en una sola línea de la lista
        for(ReporteAvanceAux repRact2: listaAuxRact2 ){
           for(ReporteAvanceAux repRact3: listaAuxRact3 ){
             if(repRact2.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()==repRact3.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()){
                 
                 //si son iguales el id de unidad de aprendizajeimparteprofesor agrega ract3 al ract1
                 //en una sola linea y toma mas valores para la lista que ya no es de un solo ract
                 
                 repRact2.setPorcentAvanceRact2(repRact2.reporteAvance.getRacavanceGlobal());
                 repRact2.setPorcentAvanceRact3(repRact3.reporteAvance.getRacavanceGlobal());
                 
                 repRact2.setStatusRact2(repRact2.reporteAvance.getRacstatus());
                 repRact2.setStatusRact3(repRact3.reporteAvance.getRacstatus());
                 
                 repRact2.setFechaElaboracRact2(repRact2.reporteAvance.getRacfechaElaboracion());
                 repRact2.setFechaElaboracRact3(repRact3.reporteAvance.getRacfechaElaboracion());
                 
                 repRact2.setFechaLimiteRact2(repRact2.fechaLimite);
                 repRact2.setFechaLimiteRact3(repRact3.fechaLimite);
                 
                 repRact2.setTipoReporteSelecRact2(repRact2.tipoReporteSelec);
                 repRact2.setTipoReporteSelecRact3(repRact3.tipoReporteSelec);
                 
                 listaAuxRact1.add(repRact2);                 
                 
                 banderaRact3=true;
                 
                 break;
             }
             else{
                 
                 //si no son iguales el id de unidad de aprendizajeimparteprofesor agrega ract3 al ract1
                 //en una sola linea y toma mas valores vacios para la lista que ya no es de un solo ract
                 
                 repRact2.setPorcentAvanceRact2(repRact2.reporteAvance.getRacavanceGlobal());
                 repRact2.setPorcentAvanceRact3(0);
                 
                 repRact2.setStatusRact2(repRact2.reporteAvance.getRacstatus());
                 repRact2.setStatusRact3("No entrego");
                 
                 repRact2.setFechaElaboracRact2(repRact2.reporteAvance.getRacfechaElaboracion());
                 repRact2.setFechaElaboracRact3(null);
                 
                 repRact2.setFechaLimiteRact2(repRact2.fechaLimite);
                 repRact2.setFechaLimiteRact3(repRact3.fechaLimite);
                 
                 repRact2.setTipoReporteSelecRact2(repRact2.tipoReporteSelec);
                 repRact2.setTipoReporteSelecRact3("No entregado");
                 
                 listaAuxRact1.add(repRact2); 
                 
                 banderaRact3=true;
                 
             }
             posRact3++;
           } 
           posRact2++;
        }
        */
        
        /*
        
        if(!banderaRact3){
            
                 ReporteAvanceAux repRact3=new ReporteAvanceAux();
            
                 repRact3.setPorcentAvanceRact3(repRact3.reporteAvance.getRacavanceGlobal());
                 repRact3.setPorcentAvanceRact1(0);
                 repRact3.setPorcentAvanceRact2(0);
                 
                 repRact3.setStatusRact3(repRact3.reporteAvance.getRacstatus());
                 repRact3.setStatusRact1("No entrego");
                 repRact3.setStatusRact2("No entrego");
                 
                 repRact3.setFechaElaboracRact3(repRact3.reporteAvance.getRacfechaElaboracion());
                 repRact3.setFechaElaboracRact1(null);
                 repRact3.setFechaElaboracRact2(null);
                 
                 repRact3.setFechaLimiteRact3(repRact3.fechaLimite);
                 repRact3.setFechaLimiteRact1(null);
                 repRact3.setFechaLimiteRact2(null);
                 
                 repRact3.setTipoReporteSelecRact3(repRact3.tipoReporteSelec);
                 repRact3.setTipoReporteSelecRact1("No entregado");
                 repRact3.setTipoReporteSelecRact2("No entregado");
                 
                 listaAuxRact1.set(listaAuxRact1.size()+1, repRact3); 
        }
          */      
        
        listaAuxRact1.addAll(listaAuxRact1b);
        
        return listaAuxRact1;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo de
    //solamente entregados 
    public ArrayList entregadosProgEdTodosRacts(ReporteAux reporteUI){
        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los entregados por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();                
            
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para entregados
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                                            
                               
        reporte = fijarAtributosReporte(reporteUI,"ProgEd");        
        
        //atributos necesarios para entregados en el objeto que se manda al
        //delegate
        reporte.setOp("CompararProgEduc");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=entregadosProgEd(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=entregadosProgEd(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=entregadosProgEd(reporte);
        
        listaAux=ordenarTodosRactsEntregadosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);        
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo de
    //solamente no entregados 
    public ArrayList noEntregadosProgEdTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los entregados por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para entregados
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                                        
                           
        reporte = fijarAtributosReporte(reporteUI,"ProgEd");        
        
        //atributos necesarios para entregados en el objeto que se manda al
        //delegate
        reporte.setOp("CompararProgEduc");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=noEntregadosProgEd(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=noEntregadosProgEd(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=noEntregadosProgEd(reporte);
        
        listaAux=ordenarTodosRactsEntregadosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);        
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo de
    //la union de entregados y no entregados
    public ArrayList entregadosYNoProgEdTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los entregados y no entregados por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para entregados y no entregados
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;            
        
        reporte = fijarAtributosReporte(reporteUI,"ProgEd");        
               
        //atributos necesarios para entregados y no entregados en el objeto que se manda al
        //delegate
        reporte.setOp("CompararProgEduc");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=entregadosYNoProgEd(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=entregadosYNoProgEd(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=entregadosYNoProgEd(reporte);
        
        listaAux=ordenarTodosRactsEntregadosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);        
                
        return listaAux;
    }
    
    //metodo auxiliar para obtener por cada ract(*no en una sola linea) 
    //de todos los registros en ract(no por unico profesor, unidad
    //de aprendizaje y grupo)
    //para Programa educativo de la union de entregados y no entregados
    public ArrayList entregadosYNoProgEd(ReporteAux reporteUI){
        
        init();        
        
        //listas donde se guardaran los entregados, no entregados y la lista temporal 
        //donde esta la union de estas dos al finalizar el metodo
        ArrayList<ReporteAvanceAux> listaAuxEntregadosProgEd;
        ArrayList<ReporteAvanceAux> listaAuxNoEntregadosProgEd; 
        ArrayList<ReporteAvanceAux> listaAux2=new ArrayList<ReporteAvanceAux>();
        
        ReporteAvanceAux reporteAvanceAux;
                
        //llama a los metodos de esta misma clase para entregados y no entregados
        //mandandole el parametro de objeto recibido que tiene todos los atributos
        //necesarios para la consulta al delegate y dao
        listaAuxEntregadosProgEd=entregadosProgEd(reporteUI);
        listaAuxNoEntregadosProgEd=noEntregadosProgEd(reporteUI);
        
        //recorre la lista de entregados y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxEntregadosProgEd){            
                       
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Entregado");            
            listaAux2.add(reporteAvanceAux);
        }
        
        //recorre la lista de no entregados y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxNoEntregadosProgEd){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("No entregado");            
            listaAux2.add(reporteAvanceAux);
        }
        
        return listaAux2;
    }

    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area de conocimiento de
    //solamente entregados 
    public ArrayList entregadosAreaConTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los entregados por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para entregados
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;        
        
        reporte = fijarAtributosReporte(reporteUI,"AreaCon");        
        
        //atributos necesarios para entregados en el objeto que se manda al
        //delegate
        reporte.setOp("CompararAreaCon");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=entregadosAreaCon(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=entregadosAreaCon(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=entregadosAreaCon(reporte);
        
        listaAux=ordenarTodosRactsEntregadosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area de conocimiento de
    //solamente no entregados 
    public ArrayList noEntregadosAreaConTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los entregados por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para entregados
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"AreaCon");        
        
        //atributos necesarios para entregados en el objeto que se manda al
        //delegate
        reporte.setOp("CompararAreaCon");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=noEntregadosAreaCon(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=noEntregadosAreaCon(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=noEntregadosAreaCon(reporte);
        
        listaAux=ordenarTodosRactsEntregadosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area de conocimiento de
    //la union entregados y no entregados
    public ArrayList entregadosYNoAreaConTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los entregados y no entregados por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para entregados y no entregados
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"AreaCon");
        
        //atributos necesarios para entregados y no entregados en el objeto que se manda al
        //delegate
        reporte.setOp("CompararAreaCon");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=entregadosYNoAreaCon(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=entregadosYNoAreaCon(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=entregadosYNoAreaCon(reporte);
        
        listaAux=ordenarTodosRactsEntregadosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);       
                
        return listaAux;
    }
    
    //metodo auxiliar para obtener por cada ract(*no en una sola linea) 
    //de todos los registros en ract(no por unico profesor, unidad
    //de aprendizaje y grupo)
    //para Area de conocimiento de la union de entregados y no entregados
    public ArrayList entregadosYNoAreaCon(ReporteAux reporteUI){
                
        init();        
                
        //listas donde se guardaran los entregados, no entregados y la lista temporal 
        //donde esta la union de estas dos al finalizar el metodo
        ArrayList<ReporteAvanceAux> listaAuxEntregadosAreaCon;
        ArrayList<ReporteAvanceAux> listaAuxNoEntregadosAreaCon; 
        ArrayList<ReporteAvanceAux> listaAux2=new ArrayList<ReporteAvanceAux>();
        
        ReporteAvanceAux reporteAvanceAux;
                
        //llama a los metodos de esta misma clase para entregados y no entregados
        //mandandole el parametro de objeto recibido que tiene todos los atributos
        //necesarios para la consulta al delegate y dao
        listaAuxEntregadosAreaCon=entregadosAreaCon(reporteUI);
        listaAuxNoEntregadosAreaCon=noEntregadosAreaCon(reporteUI);
        
        //recorre la lista de entregados y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxEntregadosAreaCon){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Entregado");            
            listaAux2.add(reporteAvanceAux);
        }
        
        //recorre la lista de no entregados y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxNoEntregadosAreaCon){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("No entregado");            
            listaAux2.add(reporteAvanceAux);
        }
        
        return listaAux2;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad de aprendizaje con Grupo
    //de solamente entregados
    public ArrayList entregadosUAGrupoTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los entregados por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para entregados
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                       
        
        reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        //atributos necesarios para entregados en el objeto que se manda al
        //delegate
        reporte.setOp("CompararUAGrupo");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=entregadosUAGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=entregadosUAGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=entregadosUAGrupo(reporte);
        
        listaAux=ordenarTodosRactsEntregadosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad de aprendizaje con Grupo
    //de solamente no entregados
    public ArrayList noEntregadosUAGrupoTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los entregados por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para entregados
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                                        
        
        reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        //atributos necesarios para entregados en el objeto que se manda al
        //delegate
        reporte.setOp("CompararUAGrupo");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=noEntregadosUAGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=noEntregadosUAGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=noEntregadosUAGrupo(reporte);
        
        listaAux=ordenarTodosRactsEntregadosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad de aprendizaje con Grupo
    //de la union entregados y no entregados    
    public ArrayList entregadosYNoUAGrupoTodosRacts(ReporteAux reporteUI){
        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los entregados y no entregados por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para entregados y no entregados
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                
        
        reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        //atributos necesarios para entregados y no entregados en el objeto que se manda al
        //delegate
        reporte.setOp("CompararUAGrupo");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=entregadosYNoUAGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=entregadosYNoUAGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=entregadosYNoUAGrupo(reporte);
        
        listaAux=ordenarTodosRactsEntregadosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo auxiliar para obtener por cada ract(*no en una sola linea) 
    //de todos los registros en ract(no por unico profesor, unidad
    //de aprendizaje y grupo)
    //para Unidad de aprendizaje con Grupo de la union de entregados y no entregados
    public ArrayList entregadosYNoUAGrupo(ReporteAux reporteUI){
                
        init();        
                
        //listas donde se guardaran los entregados, no entregados y la lista temporal 
        //donde esta la union de estas dos al finalizar el metodo
        ArrayList<ReporteAvanceAux> listaAuxEntregadosUAGrupo;
        ArrayList<ReporteAvanceAux> listaAuxNoEntregadosUAGrupo; 
        ArrayList<ReporteAvanceAux> listaAux2=new ArrayList<ReporteAvanceAux>();
        
        ReporteAvanceAux reporteAvanceAux;
                
        //llama a los metodos de esta misma clase para entregados y no entregados
        //mandandole el parametro de objeto recibido que tiene todos los atributos
        //necesarios para la consulta al delegate y dao
        listaAuxEntregadosUAGrupo=entregadosUAGrupo(reporteUI);
        listaAuxNoEntregadosUAGrupo=noEntregadosUAGrupo(reporteUI);
        
        //recorre la lista de entregados y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxEntregadosUAGrupo){            
                       
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Entregado");            
            listaAux2.add(reporteAvanceAux);
        }
        
        //recorre la lista de no entregados y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxNoEntregadosUAGrupo){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("No entregado");            
            listaAux2.add(reporteAvanceAux);
        }
        
        return listaAux2;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor con Grupo
    //de solamente entregados    
    public ArrayList entregadosProfGrupoTodosRacts(ReporteAux reporteUI){
        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los entregados por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para entregados
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                
        
        reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");
        
        //atributos necesarios para entregados en el objeto que se manda al
        //delegate
        reporte.setOp("CompararProfGrupo");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=entregadosProfGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=entregadosProfGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=entregadosProfGrupo(reporte);
        
        listaAux=ordenarTodosRactsEntregadosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);        
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor con Grupo
    //de solamente no entregados    
    public ArrayList noEntregadosProfGrupoTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los entregados por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para entregados
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                
        
        reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");
        
        //atributos necesarios para entregados en el objeto que se manda al
        //delegate
        reporte.setOp("CompararProfGrupo");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=noEntregadosProfGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=noEntregadosProfGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=noEntregadosProfGrupo(reporte);
        
        listaAux=ordenarTodosRactsEntregadosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor con Grupo
    //de la union de entregados y no entregados
    public ArrayList entregadosYNoProfGrupoTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los entregados y no entregados por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para entregados y no entregados
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        

        reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");
        
        //atributos necesarios para entregados y no entregados en el objeto que se manda al
        //delegate
        reporte.setOp("CompararProfGrupo");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=entregadosYNoProfGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=entregadosYNoProfGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=entregadosYNoProfGrupo(reporte);
        
        listaAux=ordenarTodosRactsEntregadosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo auxiliar para obtener por cada ract(*no en una sola linea) 
    //de todos los registros en ract(no por unico profesor, unidad
    //de aprendizaje y grupo)
    //para Profesor con Grupo de la union de entregados y no entregados
    public ArrayList entregadosYNoProfGrupo(ReporteAux reporteUI){
                
        init();       
                
        //listas donde se guardaran los entregados, no entregados y la lista temporal 
        //donde esta la union de estas dos al finalizar el metodo
        ArrayList<ReporteAvanceAux> listaAuxEntregadosProfGrupo;
        ArrayList<ReporteAvanceAux> listaAuxNoEntregadosProfGrupo; 
        ArrayList<ReporteAvanceAux> listaAux2=new ArrayList<ReporteAvanceAux>();
        
        ReporteAvanceAux reporteAvanceAux;
                
        //llama a los metodos de esta misma clase para entregados y no entregados
        //mandandole el parametro de objeto recibido que tiene todos los atributos
        //necesarios para la consulta al delegate y dao
        listaAuxEntregadosProfGrupo=entregadosProfGrupo(reporteUI);
        listaAuxNoEntregadosProfGrupo=noEntregadosProfGrupo(reporteUI);
        
        //recorre la lista de entregados y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxEntregadosProfGrupo){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Entregado");            
            listaAux2.add(reporteAvanceAux);
        }
        
        //recorre la lista de no entregados y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxNoEntregadosProfGrupo){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("No entregado");            
            listaAux2.add(reporteAvanceAux);
        }
        
        return listaAux2;
    }
    
    public ArrayList ordenarTodosRactsPAGCCompletosYNo(ArrayList<ReporteAvanceAux> listaAuxRact1,ArrayList<ReporteAvanceAux> listaAuxRact2,ArrayList<ReporteAvanceAux> listaAuxRact3){
        //posiciones de la lista por cada una de las listas de ract(por que no es for
        //normal sino foreach y se necesita la posicion para actualizar(modificar o update) 
        //el valor en la posición correcta
        int posRact1=0;
        int posRact2=0;
        int posRact3=0;
        
        //ciclos y condiciones para tomar en cuenta los racts desde el primero aunque no haya 
        //entregado los demas es decir que cualquiera de los tres este vacio empezando a 
        //comparar desde el primero o si no comparar segundo o tercero y los otros no entregados
        //aqui abajo modifique Jesus Ruelas        
        if((listaAuxRact2.isEmpty())&&(listaAuxRact3.isEmpty())&&!(listaAuxRact1.isEmpty())){
            for(ReporteAvanceAux repRact1: listaAuxRact1 ){
                 repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact2(0);
                 repRact1.setPorcentAvanceRact3(0);
                 
                 repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                 repRact1.setStatusRact2("No entrego");
                 repRact1.setStatusRact3("No entrego");
                 
                 repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact2(null);
                 repRact1.setFechaElaboracRact3(null);
                 
                 repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                 repRact1.setFechaLimiteRact2(null);
                 repRact1.setFechaLimiteRact3(null);
                 
                 repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact2("Incompleto");
                 repRact1.setTipoReporteSelecRact3("Incompleto");
                 
                 listaAuxRact1.set(posRact1, repRact1);                 
                                  
                 posRact1++;
            }
        }else{
            
            if((listaAuxRact2.isEmpty())&&!(listaAuxRact1.isEmpty())){
             for(ReporteAvanceAux repRact1: listaAuxRact1 ){
                  repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                  repRact1.setPorcentAvanceRact2(0);
                  //repRact1.setPorcentAvanceRact3(0);
                  
                  repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                  repRact1.setStatusRact2("No entrego");
                  //repRact1.setStatusRact3("No entrego");
                  
                  repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                  repRact1.setFechaElaboracRact2(null);
                  //repRact1.setFechaElaboracRact3(null);
                  
                  repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                  repRact1.setFechaLimiteRact2(null);
                  //repRact1.setFechaLimiteRact3(repRact1.fechaLimite);
                  
                  repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                  repRact1.setTipoReporteSelecRact2("Incompleto");
                  //repRact1.setTipoReporteSelecRact3("Incompleto");
                  
                  listaAuxRact1.set(posRact1, repRact1);                  
                                   
                  posRact1++;
             }
        }else{
                if((listaAuxRact3.isEmpty())&&!(listaAuxRact1.isEmpty())){
                   for(ReporteAvanceAux repRact1: listaAuxRact1 ){
                      repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                      //repRact1.setPorcentAvanceRact2(0);
                      repRact1.setPorcentAvanceRact3(0);
                  
                      repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                      //repRact1.setStatusRact2("No entrego");
                      repRact1.setStatusRact3("No entrego");
                  
                      repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                      //repRact1.setFechaElaboracRact2(null);
                      repRact1.setFechaElaboracRact3(null);
                  
                      repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                      //repRact1.setFechaLimiteRact2(repRact1.fechaLimite);
                      repRact1.setFechaLimiteRact3(null);
                  
                      repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                      //repRact1.setTipoReporteSelecRact2("Incompleto");
                      repRact1.setTipoReporteSelecRact3("Incompleto");
                  
                      listaAuxRact1.set(posRact1, repRact1);                  
                                   
                      posRact1++;
            }
          
        }
      }
     }

        posRact1=0;
        posRact2=0;
        posRact3=0; 
        //aqui arriba modifique Jesus Ruelas
        
        /*
        //ciclos y condiciones para tomar en cuenta los racts desde el primero aunque no haya 
        //entregado los demas es decir que cualquiera de los tres este vacio empezando a 
        //comparar desde el primero o si no comparar segundo o tercero y los otros no entregados
        //aqui abajo modifique Jesus Ruelas        
        if((listaAuxRact1.isEmpty())&&(listaAuxRact3.isEmpty())&&!(listaAuxRact2.isEmpty())){
            for(ReporteAvanceAux repRact1: listaAuxRact2 ){
                 repRact1.setPorcentAvanceRact2(repRact1.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact1(0);
                 repRact1.setPorcentAvanceRact3(0);
                 
                 repRact1.setStatusRact2(repRact1.reporteAvance.getRacstatus());
                 repRact1.setStatusRact1("No entrego");
                 repRact1.setStatusRact3("No entrego");
                 
                 repRact1.setFechaElaboracRact2(repRact1.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact1(null);
                 repRact1.setFechaElaboracRact3(null);
                 
                 repRact1.setFechaLimiteRact2(repRact1.fechaLimite);
                 repRact1.setFechaLimiteRact1(null);
                 repRact1.setFechaLimiteRact3(null);
                 
                 repRact1.setTipoReporteSelecRact2(repRact1.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact1("Incompleto");
                 repRact1.setTipoReporteSelecRact3("Incompleto");
                 
                 listaAuxRact1.set(posRact1, repRact1);                 
                                  
                 posRact1++;
            }
        }else{
            
            if((listaAuxRact1.isEmpty())&&!(listaAuxRact2.isEmpty())){
             for(ReporteAvanceAux repRact1: listaAuxRact2 ){
                  repRact1.setPorcentAvanceRact2(repRact1.reporteAvance.getRacavanceGlobal());
                  repRact1.setPorcentAvanceRact1(0);
                  //repRact1.setPorcentAvanceRact3(0);
                  
                  repRact1.setStatusRact2(repRact1.reporteAvance.getRacstatus());
                  repRact1.setStatusRact1("No entrego");
                  //repRact1.setStatusRact3("No entrego");
                  
                  repRact1.setFechaElaboracRact2(repRact1.reporteAvance.getRacfechaElaboracion());
                  repRact1.setFechaElaboracRact1(null);
                  //repRact1.setFechaElaboracRact3(null);
                  
                  repRact1.setFechaLimiteRact2(repRact1.fechaLimite);
                  repRact1.setFechaLimiteRact1(null);
                  //repRact1.setFechaLimiteRact3(repRact1.fechaLimite);
                  
                  repRact1.setTipoReporteSelecRact2(repRact1.tipoReporteSelec);
                  repRact1.setTipoReporteSelecRact1("Incompleto");
                  //repRact1.setTipoReporteSelecRact3("Incompleto");
                  
                  listaAuxRact1.set(posRact1, repRact1);                  
                                    
                  posRact1++;
             }
        }else{
                if((listaAuxRact3.isEmpty())&&!(listaAuxRact2.isEmpty())){
                   for(ReporteAvanceAux repRact1: listaAuxRact2 ){
                      repRact1.setPorcentAvanceRact2(repRact1.reporteAvance.getRacavanceGlobal());
                      //repRact1.setPorcentAvanceRact2(0);
                      repRact1.setPorcentAvanceRact3(0);
                  
                      repRact1.setStatusRact2(repRact1.reporteAvance.getRacstatus());
                      //repRact1.setStatusRact2("No entrego");
                      repRact1.setStatusRact3("No entrego");
                  
                      repRact1.setFechaElaboracRact2(repRact1.reporteAvance.getRacfechaElaboracion());
                      //repRact1.setFechaElaboracRact2(null);
                      repRact1.setFechaElaboracRact3(null);
                  
                      repRact1.setFechaLimiteRact2(repRact1.fechaLimite);
                      //repRact1.setFechaLimiteRact2(repRact1.fechaLimite);
                      repRact1.setFechaLimiteRact3(null);
                  
                      repRact1.setTipoReporteSelecRact2(repRact1.tipoReporteSelec);
                      //repRact1.setTipoReporteSelecRact2("Incompleto");
                      repRact1.setTipoReporteSelecRact3("Incompleto");
                  
                      listaAuxRact1.set(posRact1, repRact1);                  
                                        
                      posRact1++;
            }
          
        }
      }
     }
        
        posRact1=0;
        posRact2=0;
        posRact3=0; 
        //aqui arriba modifique Jesus Ruelas

        //ciclos y condiciones para tomar en cuenta los racts desde el primero aunque no haya 
        //entregado los demas es decir que cualquiera de los tres este vacio empezando a 
        //comparar desde el primero o si no comparar segundo o tercero y los otros no entregados
        //aqui abajo modifique Jesus Ruelas        
        if((listaAuxRact1.isEmpty())&&(listaAuxRact2.isEmpty())&&!(listaAuxRact3.isEmpty())){
            for(ReporteAvanceAux repRact1: listaAuxRact3 ){
                 repRact1.setPorcentAvanceRact3(repRact1.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact1(0);
                 repRact1.setPorcentAvanceRact2(0);
                 
                 repRact1.setStatusRact3(repRact1.reporteAvance.getRacstatus());
                 repRact1.setStatusRact1("No entrego");
                 repRact1.setStatusRact2("No entrego");
                 
                 repRact1.setFechaElaboracRact3(repRact1.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact1(null);
                 repRact1.setFechaElaboracRact2(null);
                 
                 repRact1.setFechaLimiteRact3(repRact1.fechaLimite);
                 repRact1.setFechaLimiteRact1(null);
                 repRact1.setFechaLimiteRact2(null);
                 
                 repRact1.setTipoReporteSelecRact3(repRact1.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact1("Incompleto");
                 repRact1.setTipoReporteSelecRact2("Incompleto");
                 
                 listaAuxRact1.set(posRact1, repRact1);                 
                                  
                 posRact1++;
            }
        }else{
            
            if((listaAuxRact1.isEmpty())&&!(listaAuxRact3.isEmpty())){
             for(ReporteAvanceAux repRact1: listaAuxRact3 ){
                  repRact1.setPorcentAvanceRact3(repRact1.reporteAvance.getRacavanceGlobal());
                  repRact1.setPorcentAvanceRact1(0);
                  //repRact1.setPorcentAvanceRact3(0);
                  
                  repRact1.setStatusRact3(repRact1.reporteAvance.getRacstatus());
                  repRact1.setStatusRact1("No entrego");
                  //repRact1.setStatusRact3("No entrego");
                  
                  repRact1.setFechaElaboracRact3(repRact1.reporteAvance.getRacfechaElaboracion());
                  repRact1.setFechaElaboracRact1(null);
                  //repRact1.setFechaElaboracRact3(null);
                  
                  repRact1.setFechaLimiteRact3(repRact1.fechaLimite);
                  repRact1.setFechaLimiteRact1(null);
                  //repRact1.setFechaLimiteRact3(repRact1.fechaLimite);
                  
                  repRact1.setTipoReporteSelecRact3(repRact1.tipoReporteSelec);
                  repRact1.setTipoReporteSelecRact1("Incompleto");
                  //repRact1.setTipoReporteSelecRact3("Incompleto");
                  
                  listaAuxRact1.set(posRact1, repRact1);                  
                                    
                  posRact1++;
             }
        }else{
                if((listaAuxRact2.isEmpty())&&!(listaAuxRact3.isEmpty())){
                   for(ReporteAvanceAux repRact1: listaAuxRact3 ){
                      repRact1.setPorcentAvanceRact3(repRact1.reporteAvance.getRacavanceGlobal());
                      //repRact1.setPorcentAvanceRact2(0);
                      repRact1.setPorcentAvanceRact2(0);
                  
                      repRact1.setStatusRact3(repRact1.reporteAvance.getRacstatus());
                      //repRact1.setStatusRact2("No entrego");
                      repRact1.setStatusRact2("No entrego");
                  
                      repRact1.setFechaElaboracRact3(repRact1.reporteAvance.getRacfechaElaboracion());
                      //repRact1.setFechaElaboracRact2(null);
                      repRact1.setFechaElaboracRact2(null);
                  
                      repRact1.setFechaLimiteRact3(repRact1.fechaLimite);
                      //repRact1.setFechaLimiteRact2(repRact1.fechaLimite);
                      repRact1.setFechaLimiteRact2(null);
                  
                      repRact1.setTipoReporteSelecRact3(repRact1.tipoReporteSelec);
                      //repRact1.setTipoReporteSelecRact2("Incompleto");
                      repRact1.setTipoReporteSelecRact2("Incompleto");
                  
                      listaAuxRact1.set(posRact1, repRact1);                  
                                        
                      posRact1++;
            }
          
        }
      }
    }
        */
        
        posRact1=0;
        posRact2=0;
        posRact3=0; 
        //aqui arriba modifique Jesus Ruelas
        
        //Para comparar Ract1 con Ract2 y agregar el los valores del ract2 al del ract1 en una
        //sola linea de la lista
        for(ReporteAvanceAux repRact1: listaAuxRact1 ){
           for(ReporteAvanceAux repRact2: listaAuxRact2 ){
             if(repRact1.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()==repRact2.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()){

                 //si son iguales el id de unidad de aprendizajeimparteprofesor agrega ract2 al ract1
                 //en una sola linea y toma mas valores para la lista que ya no es de un solo ract
                 
                 repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact2(repRact2.reporteAvance.getRacavanceGlobal());
                 
                 repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                 repRact1.setStatusRact2(repRact2.reporteAvance.getRacstatus());
                 
                 repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact2(repRact2.reporteAvance.getRacfechaElaboracion());
                 
                 repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                 repRact1.setFechaLimiteRact2(repRact2.fechaLimite);
                 
                 repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact2(repRact2.tipoReporteSelec);
                 
                 listaAuxRact1.set(posRact1, repRact1);                 
                 
                 break;
             }
             else{
                 //si no son iguales el id de unidad de aprendizajeimparteprofesor agrega ract2 al ract1
                 //en una sola linea y toma mas valores unicos para la lista que ya no es de un solo ract
                 
                 repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact2(0);
                 
                 repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                 repRact1.setStatusRact2("No entrego");
                 
                 repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact2(null);
                 
                 repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                 repRact1.setFechaLimiteRact2(repRact2.fechaLimite);
                 
                 repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact2("Incompleto");
                 
                 listaAuxRact1.set(posRact1, repRact1);                  
                 
             }
             posRact2++;
           } 
           posRact1++;
        }
        
        posRact1=0;
        posRact2=0;
        posRact3=0;         
                
        //Para comparar Ract1 con Ract3 y agregar el los valores del ract3 al del ract1 en una
        //sola linea de la lista
        for(ReporteAvanceAux repRact1: listaAuxRact1 ){
           for(ReporteAvanceAux repRact3: listaAuxRact3 ){
             if(repRact1.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()==repRact3.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()){
                 
                 //si son iguales el id de unidad de aprendizajeimparteprofesor agrega ract3 al ract1
                 //en una sola linea y toma mas valores para la lista que ya no es de un solo ract
                 
                 repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact3(repRact3.reporteAvance.getRacavanceGlobal());
                 
                 repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                 repRact1.setStatusRact3(repRact3.reporteAvance.getRacstatus());
                 
                 repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact3(repRact3.reporteAvance.getRacfechaElaboracion());
                 
                 repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                 repRact1.setFechaLimiteRact3(repRact3.fechaLimite);
                 
                 repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact3(repRact3.tipoReporteSelec);
                 
                 listaAuxRact1.set(posRact1, repRact1);                 
                 
                 break;
             }
             else{
                 //si no son iguales el id de unidad de aprendizajeimparteprofesor agrega ract3 al ract1
                 //en una sola linea y toma mas valores unicos para la lista que ya no es de un solo ract
                 
                 repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact3(0);
                 
                 repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                 repRact1.setStatusRact3("No entrego");
                 
                 repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact3(null);
                 
                 repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                 repRact1.setFechaLimiteRact3(repRact3.fechaLimite);
                 
                 repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact3("Incompleto");
                 
                 listaAuxRact1.set(posRact1, repRact1); 
                 
             }
             posRact3++;
           } 
           posRact1++;
        }
        
        posRact1=0;
        posRact2=0;
        posRact3=0;  

        ArrayList<ReporteAvanceAux> listaAuxRact1b=new ArrayList<ReporteAvanceAux>();
        
        ArrayList<Integer> listaUipid1 = new ArrayList<Integer>();
        ArrayList<Integer> listaUipid2 = new ArrayList<Integer>();
        ArrayList<Integer> listaUipid3 = new ArrayList<Integer>();
        ArrayList<Integer> listaUipidN = new ArrayList<Integer>();
        
        for(ReporteAvanceAux repRact1: listaAuxRact1){
            listaUipid1.add(repRact1.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
        }
        
        for(ReporteAvanceAux repRact2: listaAuxRact2){
            listaUipid2.add(repRact2.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
        }
        
        for(ReporteAvanceAux repRact3: listaAuxRact3){
            listaUipid3.add(repRact3.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
        }
        
        //ReporteAvanceAux repRact1a = new ReporteAvanceAux();
        //ReporteAvanceAux repRact2a = new ReporteAvanceAux();
        
        for (Integer uipid2b : listaUipid2) {
            for (Integer uipid3b : listaUipid3) {
                if (listaUipid1.contains(uipid2b)) {

                } else {
                    if (listaUipid2.contains(uipid3b)&&!(listaUipid1.contains(uipid3b))) {

                        //} else {
                        for (ReporteAvanceAux repRact2 : listaAuxRact2) {
                            for (ReporteAvanceAux repRact3 : listaAuxRact3) {

                                if ((repRact2.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid() == uipid3b) && (repRact3.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid() == uipid3b)) {
                                    repRact2.setAreaConocimiento(repRact2.getAreaConocimiento());

                                    repRact2.setPorcentAvanceRact2(repRact2.reporteAvance.getRacavanceGlobal());
                                    repRact2.setPorcentAvanceRact3(repRact3.reporteAvance.getRacavanceGlobal());
                                    //repRact2.setPorcentAvanceRact1(0);

                                    repRact2.setStatusRact2(repRact2.reporteAvance.getRacstatus());
                                    repRact2.setStatusRact3(repRact3.reporteAvance.getRacstatus());
                                    //repRact2.setStatusRact1("No entrego");

                                    repRact2.setFechaElaboracRact2(repRact2.reporteAvance.getRacfechaElaboracion());
                                    repRact2.setFechaElaboracRact3(repRact3.reporteAvance.getRacfechaElaboracion());
                                    //repRact2.setFechaElaboracRact1(null);

                                    repRact2.setFechaLimiteRact2(repRact2.fechaLimite);
                                    repRact2.setFechaLimiteRact3(repRact3.fechaLimite);
                                    //repRact2.setFechaLimiteRact1(null);

                                    repRact2.setTipoReporteSelecRact2(repRact2.tipoReporteSelec);
                                    repRact2.setTipoReporteSelecRact3(repRact3.tipoReporteSelec);
                        //repRact2.setTipoReporteSelecRact1("No entregado");

                                    //listaAuxRact1.set(posRact1, repRact1a);                  
                                    //listaAuxRact1.set(listaAuxRact1.size(), repRact2);
                                    
                                    for (ReporteAvanceAux repRactn : listaAuxRact1b) {
                                        listaUipidN.add(repRactn.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
                                    }                           
                                    
                                    if(listaUipidN.contains(uipid3b)){
                                        
                                    }else{
                                    listaAuxRact1b.add(repRact2);
                                    }
                                    
                                    listaUipidN.clear();
                                    
                                    break;
                                }
                                
                            }
                        }
                    }
                }
                //break;
            }
        }
        
        listaAuxRact1.addAll(listaAuxRact1b);
        
        listaAuxRact1b.clear();
        
        listaUipid1.clear();
        
        for(ReporteAvanceAux repRact1: listaAuxRact1){
            listaUipid1.add(repRact1.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
        }
        
        for (Integer uipid2 : listaUipid2) {
            if (listaUipid1.contains(uipid2)) {

            } else {
                for (ReporteAvanceAux repRact2 : listaAuxRact2) {
                    
                    if (repRact2.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid() == uipid2) {
                        repRact2.setAreaConocimiento(repRact2.getAreaConocimiento());
                        
                        repRact2.setPorcentAvanceRact2(repRact2.reporteAvance.getRacavanceGlobal());
                        //repRact2.setPorcentAvanceRact1(0);

                        repRact2.setStatusRact2(repRact2.reporteAvance.getRacstatus());
                        //repRact2.setStatusRact1("No entrego");

                        repRact2.setFechaElaboracRact2(repRact2.reporteAvance.getRacfechaElaboracion());
                        //repRact2.setFechaElaboracRact1(null);

                        repRact2.setFechaLimiteRact2(repRact2.fechaLimite);
                        //repRact2.setFechaLimiteRact1(null);

                        repRact2.setTipoReporteSelecRact2(repRact2.tipoReporteSelec);
                        //repRact2.setTipoReporteSelecRact1("No entregado");

                        //listaAuxRact1.set(posRact1, repRact1a);                  
                        //listaAuxRact1.set(listaAuxRact1.size(), repRact2);
                        listaAuxRact1b.add(repRact2);
                    }
                }
            }
        }
        
        for (Integer uipid3 : listaUipid3) {
            if (listaUipid1.contains(uipid3)) {

            } else {
                for (ReporteAvanceAux repRact3 : listaAuxRact3) {
                    if (repRact3.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid() == uipid3) {
                        repRact3.setAreaConocimiento(repRact3.getAreaConocimiento());
                        
                        repRact3.setPorcentAvanceRact3(repRact3.reporteAvance.getRacavanceGlobal());
                        //repRact2.setPorcentAvanceRact1(0);

                        repRact3.setStatusRact3(repRact3.reporteAvance.getRacstatus());
                        //repRact2.setStatusRact1("No entrego");

                        repRact3.setFechaElaboracRact3(repRact3.reporteAvance.getRacfechaElaboracion());
                        //repRact2.setFechaElaboracRact1(null);

                        repRact3.setFechaLimiteRact3(repRact3.fechaLimite);
                        //repRact2.setFechaLimiteRact1(null);

                        repRact3.setTipoReporteSelecRact3(repRact3.tipoReporteSelec);
                        //repRact2.setTipoReporteSelecRact1("No entregado");

                        //listaAuxRact1.set(posRact1, repRact1a);                  
                        //listaAuxRact1.set(listaAuxRact1.size(), repRact2);
                        listaAuxRact1b.add(repRact3);
                    }
                }
            }
        }
        
        listaAuxRact1.addAll(listaAuxRact1b);
        
        return listaAuxRact1;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo
    //de solamente Porcentaje de Avance General *Incompleto*
    public ArrayList PAGCProgEdTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Porcentaje de Avance General Incompleto por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Porcentaje de Avance General Incompleto
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"ProgEd");
        
        //atributos necesarios para Porcentaje de Avance General Incompleto en el objeto que se manda al
        //delegate
        reporte.setOp("CompararProgEduc");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=PAGCProgEd(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=PAGCProgEd(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=PAGCProgEd(reporte);
        
        listaAux=ordenarTodosRactsPAGCCompletosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);        
                
        return listaAux;
    }

    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo
    //de solamente Porcentaje de Avance General *Completo*
    public ArrayList PAGCCompletoProgEdTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Porcentaje de Avance General Incompleto por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Porcentaje de Avance General Incompleto
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"ProgEd");
        
        //atributos necesarios para Porcentaje de Avance General Incompleto en el objeto que se manda al
        //delegate
        reporte.setOp("CompararProgEduc");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=PAGCCompletoProgEd(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=PAGCCompletoProgEd(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=PAGCCompletoProgEd(reporte);
        
        listaAux=ordenarTodosRactsPAGCCompletosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);        
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo
    //de ambos Porcentaje de Avance General Completo e *Incompleto*
    public ArrayList PAGCCompletosYNoProgEdTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Porcentaje de Avance General Completo e Incompleto 
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Porcentaje de Avance General Completo e
        //Incompleto
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"ProgEd");
        
        //atributos necesarios para Porcentaje de Avance General Completo e Incompleto en el objeto 
        //que se manda al delegate
        reporte.setOp("CompararProgEduc");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=PAGCCompletosYNoProgEd(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=PAGCCompletosYNoProgEd(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=PAGCCompletosYNoProgEd(reporte);
        
        listaAux=ordenarTodosRactsPAGCCompletosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3); 
                
        return listaAux;
    }
    
    //metodo auxiliar para obtener por cada ract(*no en una sola linea) 
    //de todos los registros en ract(no por unico profesor, unidad
    //de aprendizaje y grupo)
    //para Programa educativo de ambos Porcentaje Avance General 
    //Completo e *Incompleto*
    public ArrayList PAGCCompletosYNoProgEd(ReporteAux reporteUI){
                
        init();        
                
        //listas donde se guardaran los Porcentaje Avance General Completo y los Icompletos y la lista temporal 
        //donde esta la union de estas dos al finalizar el metodo
        ArrayList<ReporteAvanceAux> listaAuxPAGCCompletoProgEd;
        ArrayList<ReporteAvanceAux> listaAuxPAGCProgEd; 
        ArrayList<ReporteAvanceAux> listaAux2=new ArrayList<ReporteAvanceAux>();
        
        ReporteAvanceAux reporteAvanceAux;
                
        //llama a los metodos de esta misma clase para Porcentaje Avance General Completo y los Icompletos
        //mandandole el parametro de objeto recibido que tiene todos los atributos
        //necesarios para la consulta al delegate y dao
        listaAuxPAGCCompletoProgEd=PAGCCompletoProgEd(reporteUI);
        listaAuxPAGCProgEd=PAGCProgEd(reporteUI);
        
        //recorre la lista de Porcentaje Avance General Completo y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxPAGCCompletoProgEd){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Completo");            
            listaAux2.add(reporteAvanceAux);
        }
        
        //recorre la lista de Porcentaje Avance General Icompleto y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxPAGCProgEd){           
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Incompleto");            
            listaAux2.add(reporteAvanceAux);
        }
        
        return listaAux2;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area de conocimiento
    //de solamente Porcentaje de Avance General *Incompleto*
    public ArrayList PAGCAreaConTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Porcentaje de Avance General Incompleto 
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Porcentaje de Avance General Incompleto        
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"AreaCon");
                        
        //atributos necesarios para Porcentaje de Avance General Incompleto en el objeto que se manda al
        //delegate
        reporte.setOp("CompararAreaCon");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=PAGCAreaCon(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=PAGCAreaCon(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=PAGCAreaCon(reporte);
        
        listaAux=ordenarTodosRactsPAGCCompletosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area de conocimiento
    //de solamente Porcentaje de Avance General *Completo*
    public ArrayList PAGCCompletoAreaConTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Porcentaje de Avance General Incompleto 
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Porcentaje de Avance General Incompleto        
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"AreaCon");
                        
        //atributos necesarios para Porcentaje de Avance General Incompleto en el objeto que se manda al
        //delegate
        reporte.setOp("CompararAreaCon");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=PAGCCompletoAreaCon(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=PAGCCompletoAreaCon(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=PAGCCompletoAreaCon(reporte);
        
        listaAux=ordenarTodosRactsPAGCCompletosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area de conocimiento
    //para ambos Porcentaje de Avance General Completo e *Incompleto*
    public ArrayList PAGCCompletosYNoAreaConTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Porcentaje de Avance General Completo e Incompleto 
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Porcentaje de Avance General Completo e
        //Incompleto        
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"AreaCon");
                        
        //atributos necesarios para Porcentaje de Avance General Completo e Incompleto en el objeto que se manda al
        //delegate
        reporte.setOp("CompararAreaCon");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=PAGCCompletosYNoAreaCon(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=PAGCCompletosYNoAreaCon(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=PAGCCompletosYNoAreaCon(reporte);
        
        listaAux=ordenarTodosRactsPAGCCompletosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo auxiliar para obtener por cada ract(*no en una sola linea) 
    //de todos los registros en ract(no por unico profesor, unidad
    //de aprendizaje y grupo)
    //para Area de conocimiento de ambos Porcentaje Avance General 
    //Completo e *Incompleto*
    public ArrayList PAGCCompletosYNoAreaCon(ReporteAux reporteUI){
                
        init();        
                
        //listas donde se guardaran los Porcentaje Avance General Completo y los Icompletos y la lista temporal 
        //donde esta la union de estas dos al finalizar el metodo
        ArrayList<ReporteAvanceAux> listaAuxPAGCCompletoAreaCon;
        ArrayList<ReporteAvanceAux> listaAuxPAGCAreaCon; 
        ArrayList<ReporteAvanceAux> listaAux2=new ArrayList<ReporteAvanceAux>();
        
        ReporteAvanceAux reporteAvanceAux;
                
        //llama a los metodos de esta misma clase para Porcentaje Avance General Completo y los Icompletos
        //mandandole el parametro de objeto recibido que tiene todos los atributos
        //necesarios para la consulta al delegate y dao
        listaAuxPAGCCompletoAreaCon=PAGCCompletoAreaCon(reporteUI);
        listaAuxPAGCAreaCon=PAGCAreaCon(reporteUI);
        
        //recorre la lista de Porcentaje Avance General Completo y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxPAGCCompletoAreaCon){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Completo");            
            listaAux2.add(reporteAvanceAux);
        }
        
        //recorre la lista de Porcentaje Avance General Incompleto y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxPAGCAreaCon){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Incompleto");            
            listaAux2.add(reporteAvanceAux);
        }
        
        return listaAux2;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad de aprendizaje con Grupo
    //para solamente Porcentaje de Avance General *Incompleto*
    public ArrayList PAGCUAGrupoTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Porcentaje de Avance General Incompleto 
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Porcentaje de Avance General Incompleto        
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        //atributos necesarios para Porcentaje de Avance General Incompleto en el objeto que se manda al
        //delegate
        reporte.setOp("CompararUAGrupo");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=PAGCUAGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=PAGCUAGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=PAGCUAGrupo(reporte);
        
        listaAux=ordenarTodosRactsPAGCCompletosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);        
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad de aprendizaje con Grupo
    //para solamente Porcentaje de Avance General *Completo*
    public ArrayList PAGCCompletoUAGrupoTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Porcentaje de Avance General Incompleto 
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Porcentaje de Avance General Incompleto        
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
                
        reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        //atributos necesarios para Porcentaje de Avance General Incompleto en el objeto que se manda al
        //delegate
        reporte.setOp("CompararUAGrupo");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
        
        listaAuxRact1=PAGCCompletoUAGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=PAGCCompletoUAGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=PAGCCompletoUAGrupo(reporte);
        
        listaAux=ordenarTodosRactsPAGCCompletosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad de aprendizaje con Grupo
    //para ambos Porcentaje de Avance General Completo e *Incompleto*
    public ArrayList PAGCCompletosYNoUAGrupoTodosRacts(ReporteAux reporteUI){
        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Porcentaje de Avance General Completo e Incompleto 
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Porcentaje de Avance General Completo
        //e Incompleto        
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        //atributos necesarios para Porcentaje de Avance General Completo e Incompleto en el objeto que se manda al
        //delegate
        reporte.setOp("CompararUAGrupo");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para consultar ract1
        
        listaAuxRact1=PAGCCompletosYNoUAGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=PAGCCompletosYNoUAGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact3=PAGCCompletosYNoUAGrupo(reporte);

        listaAux=ordenarTodosRactsPAGCCompletosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);        
                
        return listaAux;
    }
    
    //metodo auxiliar para obtener por cada ract(*no en una sola linea) 
    //de todos los registros en ract(no por unico profesor, unidad
    //de aprendizaje y grupo)
    //para Unidad de aprendizaje con Grupo de ambos Porcentaje Avance General 
    //Completo e *Incompleto*
    public ArrayList PAGCCompletosYNoUAGrupo(ReporteAux reporteUI){
                
        init();        
                
        //listas donde se guardaran los Porcentaje Avance General Completo y los Icompletos y la lista temporal 
        //donde esta la union de estas dos al finalizar el metodo
        ArrayList<ReporteAvanceAux> listaAuxPAGCCompletoUAGrupo;
        ArrayList<ReporteAvanceAux> listaAuxPAGCUAGrupo; 
        ArrayList<ReporteAvanceAux> listaAux2=new ArrayList<ReporteAvanceAux>();
        
        ReporteAvanceAux reporteAvanceAux;
                
        //llama a los metodos de esta misma clase para Porcentaje Avance General Completo y los Icompletos
        //mandandole el parametro de objeto recibido que tiene todos los atributos
        //necesarios para la consulta al delegate y dao
        listaAuxPAGCCompletoUAGrupo=PAGCCompletoUAGrupo(reporteUI);
        listaAuxPAGCUAGrupo=PAGCUAGrupo(reporteUI);
        
        //recorre la lista de Porcentaje Avance General Completo y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxPAGCCompletoUAGrupo){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Completo");            
            listaAux2.add(reporteAvanceAux);
        }
        
        //recorre la lista de Porcentaje Avance General Incompleto y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxPAGCUAGrupo){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Incompleto");            
            listaAux2.add(reporteAvanceAux);
        }
        
        return listaAux2;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor con Grupo
    //para solamente Porcentaje de Avance General *Incompleto*
    public ArrayList PAGCProfGrupoTodosRacts(ReporteAux reporteUI){
        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Porcentaje de Avance General Incompleto 
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Porcentaje de Avance General Incompleto                
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");
        
        //atributos necesarios para Porcentaje de Avance General Incompleto en el objeto que se manda al
        //delegate
        reporte.setOp("CompararProfGrupo");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para consultar ract1
        
        listaAuxRact1=PAGCProfGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=PAGCProfGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=PAGCProfGrupo(reporte);
        
        listaAux=ordenarTodosRactsPAGCCompletosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);        
               
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor con Grupo
    //para solamente Porcentaje de Avance General *Completo*
    public ArrayList PAGCCompletoProfGrupoTodosRacts(ReporteAux reporteUI){
        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Porcentaje de Avance General Incompleto 
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Porcentaje de Avance General Incompleto                
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");
        
        //atributos necesarios para Porcentaje de Avance General Incompleto en el objeto que se manda al
        //delegate
        reporte.setOp("CompararProfGrupo");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para consultar ract1
        
        listaAuxRact1=PAGCCompletoProfGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=PAGCCompletoProfGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=PAGCCompletoProfGrupo(reporte);
        
        listaAux=ordenarTodosRactsPAGCCompletosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);
               
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor con Grupo
    //para ambos Porcentaje de Avance General Completo e *Incompleto*
    public ArrayList PAGCCompletosYNoProfGrupoTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Porcentaje de Avance General Completo e Incompleto 
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
        
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Porcentaje de Avance General Completo 
        //e Incompleto                
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");
        
        //atributos necesarios para Porcentaje de Avance General Completo e Incompleto en el objeto que se manda al
        //delegate
        reporte.setOp("CompararProfGrupo");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para consultar ract1
        
        listaAuxRact1=PAGCCompletosYNoProfGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=PAGCCompletosYNoProfGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=PAGCCompletosYNoProfGrupo(reporte);
        
        listaAux=ordenarTodosRactsPAGCCompletosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo auxiliar para obtener por cada ract(*no en una sola linea) 
    //de todos los registros en ract(no por unico profesor, unidad
    //de aprendizaje y grupo)
    //para Profesor con Grupo de ambos Porcentaje Avance General 
    //Completo e *Incompleto*
    public ArrayList PAGCCompletosYNoProfGrupo(ReporteAux reporteUI){
                
        init();        
                
        //listas donde se guardaran los Porcentaje Avance General Completo y los Icompletos y la lista temporal 
        //donde esta la union de estas dos al finalizar el metodo
        ArrayList<ReporteAvanceAux> listaAuxPAGCCompletoProfGrupo;
        ArrayList<ReporteAvanceAux> listaAuxPAGCProfGrupo; 
        ArrayList<ReporteAvanceAux> listaAux2=new ArrayList<ReporteAvanceAux>();
        
        ReporteAvanceAux reporteAvanceAux;
                
        //llama a los metodos de esta misma clase para Porcentaje Avance General Completo y los Icompletos
        //mandandole el parametro de objeto recibido que tiene todos los atributos
        //necesarios para la consulta al delegate y dao
        listaAuxPAGCCompletoProfGrupo=PAGCCompletoProfGrupo(reporteUI);
        listaAuxPAGCProfGrupo=PAGCProfGrupo(reporteUI);
        
        //recorre la lista de Porcentaje Avance General Completo y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxPAGCCompletoProfGrupo){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Completo");            
            listaAux2.add(reporteAvanceAux);
        }
        
        //recorre la lista de Porcentaje Avance General Incompleto y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxPAGCProfGrupo){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Incompleto");            
            listaAux2.add(reporteAvanceAux);
        }
        
        return listaAux2;
    }
    
    public ArrayList ordenarTodosRactsATiempoYNoYEnLimite(ArrayList<ReporteAvanceAux> listaAuxRact1,ArrayList<ReporteAvanceAux> listaAuxRact2,ArrayList<ReporteAvanceAux> listaAuxRact3){
        //posiciones de la lista por cada una de las listas de ract(por que no es for
        //normal sino foreach y se necesita la posicion para actualizar(modificar o update) 
        //el valor en la posición correcta
        int posRact1=0;
        int posRact2=0;
        int posRact3=0;
        
        //ciclos y condiciones para tomar en cuenta los racts desde el primero aunque no haya 
        //entregado los demas es decir que cualquiera de los tres este vacio empezando a 
        //comparar desde el primero o si no comparar segundo o tercero y los otros no entregados
        //aqui abajo modifique Jesus Ruelas        
        if((listaAuxRact2.isEmpty())&&(listaAuxRact3.isEmpty())&&!(listaAuxRact1.isEmpty())){
            for(ReporteAvanceAux repRact1: listaAuxRact1 ){
                 repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact2(0);
                 repRact1.setPorcentAvanceRact3(0);
                 
                 repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                 repRact1.setStatusRact2("No entrego");
                 repRact1.setStatusRact3("No entrego");
                 
                 repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact2(null);
                 repRact1.setFechaElaboracRact3(null);
                 
                 repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                 repRact1.setFechaLimiteRact2(null);
                 repRact1.setFechaLimiteRact3(null);
                 
                 repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact2("Incompleto");
                 repRact1.setTipoReporteSelecRact3("Incompleto");
                 
                 listaAuxRact1.set(posRact1, repRact1);                 
                                  
                 posRact1++;
            }
        }else{
            
            if((listaAuxRact2.isEmpty())&&!(listaAuxRact1.isEmpty())){
             for(ReporteAvanceAux repRact1: listaAuxRact1 ){
                  repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                  repRact1.setPorcentAvanceRact2(0);
                  //repRact1.setPorcentAvanceRact3(0);
                  
                  repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                  repRact1.setStatusRact2("No entrego");
                  //repRact1.setStatusRact3("No entrego");
                  
                  repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                  repRact1.setFechaElaboracRact2(null);
                  //repRact1.setFechaElaboracRact3(null);
                  
                  repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                  repRact1.setFechaLimiteRact2(null);
                  //repRact1.setFechaLimiteRact3(repRact1.fechaLimite);
                  
                  repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                  repRact1.setTipoReporteSelecRact2("Incompleto");
                  //repRact1.setTipoReporteSelecRact3("Incompleto");
                  
                  listaAuxRact1.set(posRact1, repRact1);                  
                                   
                  posRact1++;
             }
        }else{
                if((listaAuxRact3.isEmpty())&&!(listaAuxRact1.isEmpty())){
                   for(ReporteAvanceAux repRact1: listaAuxRact1 ){
                      repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                      //repRact1.setPorcentAvanceRact2(0);
                      repRact1.setPorcentAvanceRact3(0);
                  
                      repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                      //repRact1.setStatusRact2("No entrego");
                      repRact1.setStatusRact3("No entrego");
                  
                      repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                      //repRact1.setFechaElaboracRact2(null);
                      repRact1.setFechaElaboracRact3(null);
                  
                      repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                      //repRact1.setFechaLimiteRact2(repRact1.fechaLimite);
                      repRact1.setFechaLimiteRact3(null);
                  
                      repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                      //repRact1.setTipoReporteSelecRact2("Incompleto");
                      repRact1.setTipoReporteSelecRact3("Incompleto");
                  
                      listaAuxRact1.set(posRact1, repRact1);                  
                                   
                      posRact1++;
            }
          
        }
      }
     }

        posRact1=0;
        posRact2=0;
        posRact3=0; 
        //aqui arriba modifique Jesus Ruelas
        
        /*
        //ciclos y condiciones para tomar en cuenta los racts desde el primero aunque no haya 
        //entregado los demas es decir que cualquiera de los tres este vacio empezando a 
        //comparar desde el primero o si no comparar segundo o tercero y los otros no entregados
        //aqui abajo modifique Jesus Ruelas        
        if((listaAuxRact1.isEmpty())&&(listaAuxRact3.isEmpty())&&!(listaAuxRact2.isEmpty())){
            for(ReporteAvanceAux repRact1: listaAuxRact2 ){
                 repRact1.setPorcentAvanceRact2(repRact1.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact1(0);
                 repRact1.setPorcentAvanceRact3(0);
                 
                 repRact1.setStatusRact2(repRact1.reporteAvance.getRacstatus());
                 repRact1.setStatusRact1("No entrego");
                 repRact1.setStatusRact3("No entrego");
                 
                 repRact1.setFechaElaboracRact2(repRact1.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact1(null);
                 repRact1.setFechaElaboracRact3(null);
                 
                 repRact1.setFechaLimiteRact2(repRact1.fechaLimite);
                 repRact1.setFechaLimiteRact1(null);
                 repRact1.setFechaLimiteRact3(null);
                 
                 repRact1.setTipoReporteSelecRact2(repRact1.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact1("Incompleto");
                 repRact1.setTipoReporteSelecRact3("Incompleto");
                 
                 listaAuxRact1.set(posRact1, repRact1);                 
                                  
                 posRact1++;
            }
        }else{
            
            if((listaAuxRact1.isEmpty())&&!(listaAuxRact2.isEmpty())){
             for(ReporteAvanceAux repRact1: listaAuxRact2 ){
                  repRact1.setPorcentAvanceRact2(repRact1.reporteAvance.getRacavanceGlobal());
                  repRact1.setPorcentAvanceRact1(0);
                  //repRact1.setPorcentAvanceRact3(0);
                  
                  repRact1.setStatusRact2(repRact1.reporteAvance.getRacstatus());
                  repRact1.setStatusRact1("No entrego");
                  //repRact1.setStatusRact3("No entrego");
                  
                  repRact1.setFechaElaboracRact2(repRact1.reporteAvance.getRacfechaElaboracion());
                  repRact1.setFechaElaboracRact1(null);
                  //repRact1.setFechaElaboracRact3(null);
                  
                  repRact1.setFechaLimiteRact2(repRact1.fechaLimite);
                  repRact1.setFechaLimiteRact1(null);
                  //repRact1.setFechaLimiteRact3(repRact1.fechaLimite);
                  
                  repRact1.setTipoReporteSelecRact2(repRact1.tipoReporteSelec);
                  repRact1.setTipoReporteSelecRact1("Incompleto");
                  //repRact1.setTipoReporteSelecRact3("Incompleto");
                  
                  listaAuxRact1.set(posRact1, repRact1);                  
                                    
                  posRact1++;
             }
        }else{
                if((listaAuxRact3.isEmpty())&&!(listaAuxRact2.isEmpty())){
                   for(ReporteAvanceAux repRact1: listaAuxRact2 ){
                      repRact1.setPorcentAvanceRact2(repRact1.reporteAvance.getRacavanceGlobal());
                      //repRact1.setPorcentAvanceRact2(0);
                      repRact1.setPorcentAvanceRact3(0);
                  
                      repRact1.setStatusRact2(repRact1.reporteAvance.getRacstatus());
                      //repRact1.setStatusRact2("No entrego");
                      repRact1.setStatusRact3("No entrego");
                  
                      repRact1.setFechaElaboracRact2(repRact1.reporteAvance.getRacfechaElaboracion());
                      //repRact1.setFechaElaboracRact2(null);
                      repRact1.setFechaElaboracRact3(null);
                  
                      repRact1.setFechaLimiteRact2(repRact1.fechaLimite);
                      //repRact1.setFechaLimiteRact2(repRact1.fechaLimite);
                      repRact1.setFechaLimiteRact3(null);
                  
                      repRact1.setTipoReporteSelecRact2(repRact1.tipoReporteSelec);
                      //repRact1.setTipoReporteSelecRact2("Incompleto");
                      repRact1.setTipoReporteSelecRact3("Incompleto");
                  
                      listaAuxRact1.set(posRact1, repRact1);                  
                                        
                      posRact1++;
            }
          
        }
      }
     }
        
        posRact1=0;
        posRact2=0;
        posRact3=0; 
        //aqui arriba modifique Jesus Ruelas

        //ciclos y condiciones para tomar en cuenta los racts desde el primero aunque no haya 
        //entregado los demas es decir que cualquiera de los tres este vacio empezando a 
        //comparar desde el primero o si no comparar segundo o tercero y los otros no entregados
        //aqui abajo modifique Jesus Ruelas        
        if((listaAuxRact1.isEmpty())&&(listaAuxRact2.isEmpty())&&!(listaAuxRact3.isEmpty())){
            for(ReporteAvanceAux repRact1: listaAuxRact3 ){
                 repRact1.setPorcentAvanceRact3(repRact1.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact1(0);
                 repRact1.setPorcentAvanceRact2(0);
                 
                 repRact1.setStatusRact3(repRact1.reporteAvance.getRacstatus());
                 repRact1.setStatusRact1("No entrego");
                 repRact1.setStatusRact2("No entrego");
                 
                 repRact1.setFechaElaboracRact3(repRact1.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact1(null);
                 repRact1.setFechaElaboracRact2(null);
                 
                 repRact1.setFechaLimiteRact3(repRact1.fechaLimite);
                 repRact1.setFechaLimiteRact1(null);
                 repRact1.setFechaLimiteRact2(null);
                 
                 repRact1.setTipoReporteSelecRact3(repRact1.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact1("Incompleto");
                 repRact1.setTipoReporteSelecRact2("Incompleto");
                 
                 listaAuxRact1.set(posRact1, repRact1);                 
                                  
                 posRact1++;
            }
        }else{
            
            if((listaAuxRact1.isEmpty())&&!(listaAuxRact3.isEmpty())){
             for(ReporteAvanceAux repRact1: listaAuxRact3 ){
                  repRact1.setPorcentAvanceRact3(repRact1.reporteAvance.getRacavanceGlobal());
                  repRact1.setPorcentAvanceRact1(0);
                  //repRact1.setPorcentAvanceRact3(0);
                  
                  repRact1.setStatusRact3(repRact1.reporteAvance.getRacstatus());
                  repRact1.setStatusRact1("No entrego");
                  //repRact1.setStatusRact3("No entrego");
                  
                  repRact1.setFechaElaboracRact3(repRact1.reporteAvance.getRacfechaElaboracion());
                  repRact1.setFechaElaboracRact1(null);
                  //repRact1.setFechaElaboracRact3(null);
                  
                  repRact1.setFechaLimiteRact3(repRact1.fechaLimite);
                  repRact1.setFechaLimiteRact1(null);
                  //repRact1.setFechaLimiteRact3(repRact1.fechaLimite);
                  
                  repRact1.setTipoReporteSelecRact3(repRact1.tipoReporteSelec);
                  repRact1.setTipoReporteSelecRact1("Incompleto");
                  //repRact1.setTipoReporteSelecRact3("Incompleto");
                  
                  listaAuxRact1.set(posRact1, repRact1);                  
                                    
                  posRact1++;
             }
        }else{
                if((listaAuxRact2.isEmpty())&&!(listaAuxRact3.isEmpty())){
                   for(ReporteAvanceAux repRact1: listaAuxRact3 ){
                      repRact1.setPorcentAvanceRact3(repRact1.reporteAvance.getRacavanceGlobal());
                      //repRact1.setPorcentAvanceRact2(0);
                      repRact1.setPorcentAvanceRact2(0);
                  
                      repRact1.setStatusRact3(repRact1.reporteAvance.getRacstatus());
                      //repRact1.setStatusRact2("No entrego");
                      repRact1.setStatusRact2("No entrego");
                  
                      repRact1.setFechaElaboracRact3(repRact1.reporteAvance.getRacfechaElaboracion());
                      //repRact1.setFechaElaboracRact2(null);
                      repRact1.setFechaElaboracRact2(null);
                  
                      repRact1.setFechaLimiteRact3(repRact1.fechaLimite);
                      //repRact1.setFechaLimiteRact2(repRact1.fechaLimite);
                      repRact1.setFechaLimiteRact2(null);
                  
                      repRact1.setTipoReporteSelecRact3(repRact1.tipoReporteSelec);
                      //repRact1.setTipoReporteSelecRact2("Incompleto");
                      repRact1.setTipoReporteSelecRact2("Incompleto");
                  
                      listaAuxRact1.set(posRact1, repRact1);                  
                                        
                      posRact1++;
            }
          
        }
      }
     }
        */
        posRact1=0;
        posRact2=0;
        posRact3=0; 
        //aqui arriba modifique Jesus Ruelas
        
        //Para comparar Ract1 con Ract2 y agregar el los valores del ract2 al del ract1 en una
        //sola linea de la lista
        for(ReporteAvanceAux repRact1: listaAuxRact1 ){
           for(ReporteAvanceAux repRact2: listaAuxRact2 ){
             if(repRact1.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()==repRact2.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()){                 
                 //si son iguales el id de unidad de aprendizajeimparteprofesor agrega ract2 al ract1
                 //en una sola linea y toma mas valores para la lista que ya no es de un solo ract                 
                 
                 repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact2(repRact2.reporteAvance.getRacavanceGlobal());
                 
                 repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                 repRact1.setStatusRact2(repRact2.reporteAvance.getRacstatus());
                 
                 repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact2(repRact2.reporteAvance.getRacfechaElaboracion());
                 
                 repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                 repRact1.setFechaLimiteRact2(repRact2.fechaLimite);
                 
                 repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact2(repRact2.tipoReporteSelec);
                 
                 listaAuxRact1.set(posRact1, repRact1);                 
                 
                 break;
             }
             else{
                 //si no son iguales el id de unidad de aprendizajeimparteprofesor agrega ract2 al ract1
                 //en una sola linea y toma mas valores vacio para la lista que ya no es de un solo ract                 
                 
                 repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact2(0);
                 
                 repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                 repRact1.setStatusRact2("No entrego");
                 
                 repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact2(null);
                 
                 repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                 repRact1.setFechaLimiteRact2(repRact2.fechaLimite);
                 
                 repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact2("No entregado");
                 
                 listaAuxRact1.set(posRact1, repRact1);                  
                 
             }
             posRact2++;
           } 
           posRact1++;
        }
        
        posRact1=0;
        posRact2=0;
        posRact3=0;         
                
        //Para comparar Ract1 con Ract3 y agregar el los valores del ract3 al del ract1 en una
        //sola linea de la lista
        for(ReporteAvanceAux repRact1: listaAuxRact1 ){
           for(ReporteAvanceAux repRact3: listaAuxRact3 ){
             if(repRact1.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()==repRact3.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid()){
                 //si son iguales el id de unidad de aprendizajeimparteprofesor agrega ract3 al ract1
                 //en una sola linea y toma mas valores para la lista que ya no es de un solo ract                 
                 
                 repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact3(repRact3.reporteAvance.getRacavanceGlobal());
                 
                 repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                 repRact1.setStatusRact3(repRact3.reporteAvance.getRacstatus());
                 
                 repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact3(repRact3.reporteAvance.getRacfechaElaboracion());
                 
                 repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                 repRact1.setFechaLimiteRact3(repRact3.fechaLimite);
                 
                 repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact3(repRact3.tipoReporteSelec);
                 
                 listaAuxRact1.set(posRact1, repRact1);                 
                 
                 break;
             }
             else{
                 //si no son iguales el id de unidad de aprendizajeimparteprofesor agrega ract3 al ract1
                 //en una sola linea y toma mas valores vacio para la lista que ya no es de un solo ract                 
                 
                 repRact1.setPorcentAvanceRact1(repRact1.reporteAvance.getRacavanceGlobal());
                 repRact1.setPorcentAvanceRact3(0);
                 
                 repRact1.setStatusRact1(repRact1.reporteAvance.getRacstatus());
                 repRact1.setStatusRact3("No entrego");
                 
                 repRact1.setFechaElaboracRact1(repRact1.reporteAvance.getRacfechaElaboracion());
                 repRact1.setFechaElaboracRact3(null);
                 
                 repRact1.setFechaLimiteRact1(repRact1.fechaLimite);
                 repRact1.setFechaLimiteRact3(repRact3.fechaLimite);
                 
                 repRact1.setTipoReporteSelecRact1(repRact1.tipoReporteSelec);
                 repRact1.setTipoReporteSelecRact3("No entregado");
                 
                 listaAuxRact1.set(posRact1, repRact1); 
                 
             }
             posRact3++;
           } 
           posRact1++;
        }
    
        posRact1=0;
        posRact2=0;
        posRact3=0;  

        ArrayList<ReporteAvanceAux> listaAuxRact1b=new ArrayList<ReporteAvanceAux>();
        
        ArrayList<Integer> listaUipid1 = new ArrayList<Integer>();
        ArrayList<Integer> listaUipid2 = new ArrayList<Integer>();
        ArrayList<Integer> listaUipid3 = new ArrayList<Integer>();
        ArrayList<Integer> listaUipidN = new ArrayList<Integer>();
        
        for(ReporteAvanceAux repRact1: listaAuxRact1){
            listaUipid1.add(repRact1.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
        }
        
        for(ReporteAvanceAux repRact2: listaAuxRact2){
            listaUipid2.add(repRact2.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
        }
        
        for(ReporteAvanceAux repRact3: listaAuxRact3){
            listaUipid3.add(repRact3.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
        }
        
        //ReporteAvanceAux repRact1a = new ReporteAvanceAux();
        //ReporteAvanceAux repRact2a = new ReporteAvanceAux();
        
        for (Integer uipid2b : listaUipid2) {
            for (Integer uipid3b : listaUipid3) {
                if (listaUipid1.contains(uipid2b)) {

                } else {
                    if (listaUipid2.contains(uipid3b)&&!(listaUipid1.contains(uipid3b))) {

                        //} else {
                        for (ReporteAvanceAux repRact2 : listaAuxRact2) {
                            for (ReporteAvanceAux repRact3 : listaAuxRact3) {

                                if ((repRact2.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid() == uipid3b) && (repRact3.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid() == uipid3b)) {
                                    repRact2.setAreaConocimiento(repRact2.getAreaConocimiento());

                                    repRact2.setPorcentAvanceRact2(repRact2.reporteAvance.getRacavanceGlobal());
                                    repRact2.setPorcentAvanceRact3(repRact3.reporteAvance.getRacavanceGlobal());
                                    //repRact2.setPorcentAvanceRact1(0);

                                    repRact2.setStatusRact2(repRact2.reporteAvance.getRacstatus());
                                    repRact2.setStatusRact3(repRact3.reporteAvance.getRacstatus());
                                    //repRact2.setStatusRact1("No entrego");

                                    repRact2.setFechaElaboracRact2(repRact2.reporteAvance.getRacfechaElaboracion());
                                    repRact2.setFechaElaboracRact3(repRact3.reporteAvance.getRacfechaElaboracion());
                                    //repRact2.setFechaElaboracRact1(null);

                                    repRact2.setFechaLimiteRact2(repRact2.fechaLimite);
                                    repRact2.setFechaLimiteRact3(repRact3.fechaLimite);
                                    //repRact2.setFechaLimiteRact1(null);

                                    repRact2.setTipoReporteSelecRact2(repRact2.tipoReporteSelec);
                                    repRact2.setTipoReporteSelecRact3(repRact3.tipoReporteSelec);
                        //repRact2.setTipoReporteSelecRact1("No entregado");

                                    //listaAuxRact1.set(posRact1, repRact1a);                  
                                    //listaAuxRact1.set(listaAuxRact1.size(), repRact2);
                                    
                                    for (ReporteAvanceAux repRactn : listaAuxRact1b) {
                                        listaUipidN.add(repRactn.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
                                    }                           
                                    
                                    if(listaUipidN.contains(uipid3b)){
                                        
                                    }else{
                                    listaAuxRact1b.add(repRact2);
                                    }
                                    
                                    listaUipidN.clear();
                                    
                                    break;
                                }
                                
                            }
                        }
                    }
                }
                //break;
            }
        }
        
        listaAuxRact1.addAll(listaAuxRact1b);
        
        listaAuxRact1b.clear();
        
        listaUipid1.clear();
        
        for(ReporteAvanceAux repRact1: listaAuxRact1){
            listaUipid1.add(repRact1.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
        }
        
        for (Integer uipid2 : listaUipid2) {
            if (listaUipid1.contains(uipid2)) {

            } else {
                for (ReporteAvanceAux repRact2 : listaAuxRact2) {
                    
                    if (repRact2.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid() == uipid2) {
                        repRact2.setAreaConocimiento(repRact2.getAreaConocimiento());
                        
                        repRact2.setPorcentAvanceRact2(repRact2.reporteAvance.getRacavanceGlobal());
                        //repRact2.setPorcentAvanceRact1(0);

                        repRact2.setStatusRact2(repRact2.reporteAvance.getRacstatus());
                        //repRact2.setStatusRact1("No entrego");

                        repRact2.setFechaElaboracRact2(repRact2.reporteAvance.getRacfechaElaboracion());
                        //repRact2.setFechaElaboracRact1(null);

                        repRact2.setFechaLimiteRact2(repRact2.fechaLimite);
                        //repRact2.setFechaLimiteRact1(null);

                        repRact2.setTipoReporteSelecRact2(repRact2.tipoReporteSelec);
                        //repRact2.setTipoReporteSelecRact1("No entregado");

                        //listaAuxRact1.set(posRact1, repRact1a);                  
                        //listaAuxRact1.set(listaAuxRact1.size(), repRact2);
                        listaAuxRact1b.add(repRact2);
                    }
                }
            }
        }
        
        for (Integer uipid3 : listaUipid3) {
            if (listaUipid1.contains(uipid3)) {

            } else {
                for (ReporteAvanceAux repRact3 : listaAuxRact3) {
                    if (repRact3.reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid() == uipid3) {
                        repRact3.setAreaConocimiento(repRact3.getAreaConocimiento());
                        
                        repRact3.setPorcentAvanceRact3(repRact3.reporteAvance.getRacavanceGlobal());
                        //repRact2.setPorcentAvanceRact1(0);

                        repRact3.setStatusRact3(repRact3.reporteAvance.getRacstatus());
                        //repRact2.setStatusRact1("No entrego");

                        repRact3.setFechaElaboracRact3(repRact3.reporteAvance.getRacfechaElaboracion());
                        //repRact2.setFechaElaboracRact1(null);

                        repRact3.setFechaLimiteRact3(repRact3.fechaLimite);
                        //repRact2.setFechaLimiteRact1(null);

                        repRact3.setTipoReporteSelecRact3(repRact3.tipoReporteSelec);
                        //repRact2.setTipoReporteSelecRact1("No entregado");

                        //listaAuxRact1.set(posRact1, repRact1a);                  
                        //listaAuxRact1.set(listaAuxRact1.size(), repRact2);
                        listaAuxRact1b.add(repRact3);
                    }
                }
            }
        }
        
        listaAuxRact1.addAll(listaAuxRact1b); 
        
        return listaAuxRact1;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo
    //para solamente A Tiempo(comparando la fecha limite)
    public ArrayList ATiempoProgEdTodosRacts(ReporteAux reporteUI){
        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Entregados A Tiempo  
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Entregados A Tiempo         
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"ProgEd");
        
        //atributos necesarios para Entregados A Tiempo en el objeto que se manda al
        //delegate
        reporte.setOp("CompararProfGrupo");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para consultar ract1
               
        listaAuxRact1=ATiempoProgEd(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=ATiempoProgEd(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=ATiempoProgEd(reporte);
        
        listaAux=ordenarTodosRactsATiempoYNoYEnLimite(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo
    //para solamente Fuera Tiempo(comparando la fecha limite)
    public ArrayList FueraTiempoProgEdTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Entregados A Tiempo  
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Entregados A Tiempo         
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"ProgEd");
        
        //atributos necesarios para Entregados A Tiempo en el objeto que se manda al
        //delegate
        reporte.setOp("CompararProfGrupo");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para consultar ract1
               
        listaAuxRact1=FueraTiempoProgEd(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=FueraTiempoProgEd(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=FueraTiempoProgEd(reporte);
        
        listaAux=ordenarTodosRactsATiempoYNoYEnLimite(listaAuxRact1,listaAuxRact2,listaAuxRact3);
        //listaAux=ordenarTodosRactsEntregadosYNo(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo
    //para solamente En Fecha Limite Tiempo(comparando la fecha limite)
    public ArrayList EnFechaLimiteTiempoProgEdTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Entregados A Tiempo  
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Entregados A Tiempo         
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"ProgEd");
        
        //atributos necesarios para Entregados A Tiempo en el objeto que se manda al
        //delegate
        reporte.setOp("CompararProfGrupo");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para consultar ract1
                
        listaAuxRact1=EnFechaLimiteTiempoProgEd(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=EnFechaLimiteTiempoProgEd(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=EnFechaLimiteTiempoProgEd(reporte);
        
        listaAux=ordenarTodosRactsATiempoYNoYEnLimite(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo
    //para todos A Tiempo, En Fecha Limite y Despues de Fecha Limite(comparando la fecha limite)
    public ArrayList ATiempoYNoYEnLimiteProgEdTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Entregados A Tiempo, Despues de fecha limite y en fecha limite  
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Entregados A Tiempo,
        //Despues de fecha limte y en fecha limite
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
       
        reporte = fijarAtributosReporte(reporteUI,"ProgEd");
        
        //atributos necesarios para Entregados A Tiempo, Despues de fecha limite y en fecha limite
        //en el objeto que se manda al delegate
        reporte.setOp("CompararProfGrupo");
        reporte.setTipo("Enviado");
        reporte.setNumRact(1);//aqui cambie para consultar ract1
        
        listaAuxRact1=ATiempoYNoYEnLimiteProgEd(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=ATiempoYNoYEnLimiteProgEd(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=ATiempoYNoYEnLimiteProgEd(reporte);
        
        listaAux=ordenarTodosRactsATiempoYNoYEnLimite(listaAuxRact1,listaAuxRact2,listaAuxRact3);
               
        return listaAux;
    }
    
    //metodo auxiliar para obtener por cada ract(*no en una sola linea) 
    //de todos los registros en ract(no por unico profesor, unidad
    //de aprendizaje y grupo)
    //para Programa educativo de todos A Tiempo, En Fecha Limite, 
    // Despues de Fecha Limite(comparando la fecha Limite)
    public ArrayList ATiempoYNoYEnLimiteProgEd(ReporteAux reporteUI){        
        init();
        
        //listas donde se guardaran los Entregados A Tiempo, Despues de fecha limite y en fecha limite
        //y la lista temporal donde esta la union de estas tres al finalizar el metodo
        ArrayList<ReporteAvanceAux> listaAuxATiempo;
        //ArrayList<ReporteAvanceAux> listaAuxEnFechaLimiteTiempo;        
        ArrayList<ReporteAvanceAux> listaAuxFueraTiempo;
        ArrayList<ReporteAvanceAux> listaAux2=new ArrayList<ReporteAvanceAux>();
        
        ReporteAvanceAux reporteAvanceAux;
        
        //llama a los metodos de esta misma clase para Entregados A Tiempo, 
        //Despues de fecha limite y en fecha limite
        //mandandole el parametro de objeto recibido que tiene todos los atributos
        //necesarios para la consulta al delegate y dao
        listaAuxATiempo=ATiempoProgEd(reporteUI);
        //listaAuxEnFechaLimiteTiempo=EnFechaLimiteTiempoProgEd(reporteUI);
        listaAuxFueraTiempo=FueraTiempoProgEd(reporteUI);
                
        //recorre la lista de Entregados A Tiempo y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxATiempo){            
                   
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("A tiempo");            
            listaAux2.add(reporteAvanceAux);
        }        
        
//        //recorre la lista de Entregados En Fecha Limite y lo une a la lista auxiliar que debe
//        //tener la union de ambos
//        for(ReporteAvanceAux ra: listaAuxEnFechaLimiteTiempo){            
//                        
//            reporteAvanceAux=ra;
//            reporteAvanceAux.setTipoReporteSelec("En fecha Limite");            
//            listaAux2.add(reporteAvanceAux);
//        }
        
        //recorre la lista de Entregados Despues de fecha Limite y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxFueraTiempo){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Despues fecha limite");            
            listaAux2.add(reporteAvanceAux);
        }
        
        return listaAux2;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area de conocimiento
    //para solo A Tiempo(comparando la fecha limite)
    public ArrayList ATiempoAreaConTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Entregados A Tiempo  
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Entregados A Tiempo        
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"AreaCon");
        
        //atributos necesarios para Entregados A Tiempo
        //en el objeto que se manda al delegate
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("ATiempo");
        reporte.setNumRact(1);//aqui cambie para consultar ract1
           
        listaAuxRact1=ATiempoAreaCon(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=ATiempoAreaCon(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=ATiempoAreaCon(reporte);
        
        listaAux=ordenarTodosRactsATiempoYNoYEnLimite(listaAuxRact1,listaAuxRact2,listaAuxRact3);        
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area de conocimiento
    //para solo Fuera Tiempo(comparando la fecha limite)
    public ArrayList FueraTiempoAreaConTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Entregados A Tiempo  
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Entregados A Tiempo        
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"AreaCon");
        
        //atributos necesarios para Entregados A Tiempo
        //en el objeto que se manda al delegate
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("ATiempo");
        reporte.setNumRact(1);//aqui cambie para consultar ract1
           
        listaAuxRact1=FueraTiempoAreaCon(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=FueraTiempoAreaCon(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=FueraTiempoAreaCon(reporte);
        
        listaAux=ordenarTodosRactsATiempoYNoYEnLimite(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area de conocimiento
    //para solo En Fecha Limite Tiempo(comparando la fecha limite)
    public ArrayList EnFechaLimiteTiempoAreaConTodosRacts(ReporteAux reporteUI){
        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Entregados A Tiempo  
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Entregados A Tiempo        
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"AreaCon");
        
        //atributos necesarios para Entregados A Tiempo
        //en el objeto que se manda al delegate
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("ATiempo");
        reporte.setNumRact(1);//aqui cambie para consultar ract1
            
        listaAuxRact1=EnFechaLimiteTiempoAreaCon(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=EnFechaLimiteTiempoAreaCon(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=EnFechaLimiteTiempoAreaCon(reporte);
        
        listaAux=ordenarTodosRactsATiempoYNoYEnLimite(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area de conocimiento
    //para todos A Tiempo, En Fecha Limite y Despues de Fecha Limite(comparando la fecha limite)
    public ArrayList ATiempoYNoYEnLimiteAreaConTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Entregados A Tiempo, Despues de fecha limite y en fecha limite  
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Entregados A Tiempo,
        //Despues de fecha limite y en fecha limite
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
                
        reporte = fijarAtributosReporte(reporteUI,"AreaCon");
        
        //atributos necesarios para Entregados A Tiempo, 
        //Despues de fecha limite y en fecha limite
        //en el objeto que se manda al delegate
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("ATiempo");
        reporte.setNumRact(1);//aqui cambie para consultar ract1
       
        listaAuxRact1=ATiempoYNoYEnLimiteAreaCon(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=ATiempoYNoYEnLimiteAreaCon(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=ATiempoYNoYEnLimiteAreaCon(reporte);
        
        listaAux=ordenarTodosRactsATiempoYNoYEnLimite(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo auxiliar para obtener por cada ract(*no en una sola linea) 
    //de todos los registros en ract(no por unico profesor, unidad
    //de aprendizaje y grupo)
    //para Area de conocimiento de todos A Tiempo, En Fecha Limite, 
    // Despues de Fecha Limite(comparando la fecha Limite)
    public ArrayList ATiempoYNoYEnLimiteAreaCon(ReporteAux reporteUI){        
                
        init();
        
        //listas donde se guardaran los Entregados A Tiempo, Despues de fecha limite y en fecha limite
        //y la lista temporal donde esta la union de estas tres al finalizar el metodo
        ArrayList<ReporteAvanceAux> listaAuxATiempo;
        ArrayList<ReporteAvanceAux> listaAuxEnFechaLimiteTiempo;        
        ArrayList<ReporteAvanceAux> listaAuxFueraTiempo;
        ArrayList<ReporteAvanceAux> listaAux2=new ArrayList<ReporteAvanceAux>();
        
        ReporteAvanceAux reporteAvanceAux;
        
        //llama a los metodos de esta misma clase para Entregados A Tiempo, 
        //Despues de fecha limite y en fecha limite
        //mandandole el parametro de objeto recibido que tiene todos los atributos
        //necesarios para la consulta al delegate y dao
        listaAuxATiempo=ATiempoAreaCon(reporteUI);
        listaAuxEnFechaLimiteTiempo=EnFechaLimiteTiempoAreaCon(reporteUI);
        listaAuxFueraTiempo=FueraTiempoAreaCon(reporteUI);
        
        //recorre la lista de Entregados A Tiempo y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxATiempo){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("A tiempo");            
            listaAux2.add(reporteAvanceAux);
        }        
        
        //recorre la lista de Entregados En fecha limite y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxEnFechaLimiteTiempo){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("En fecha Limite");            
            listaAux2.add(reporteAvanceAux);
        }
        
        //recorre la lista de Entregados Despues fecha limite y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxFueraTiempo){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Despues fecha limite");            
            listaAux2.add(reporteAvanceAux);
        }
        
        return listaAux2;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad de aprendizaje con Grupo
    //para todos A Tiempo(comparando la fecha limite)
    public ArrayList ATiempoUAGrupoTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Entregados A Tiempo  
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Entregados A Tiempo        
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        //atributos necesarios para Entregados A Tiempo        
        //en el objeto que se manda al delegate
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("ATiempo");
        reporte.setNumRact(1);//aqui cambie para consultar el ract1
            
        listaAuxRact1=ATiempoUAGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=ATiempoUAGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=ATiempoUAGrupo(reporte);
        
        listaAux=ordenarTodosRactsATiempoYNoYEnLimite(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad de aprendizaje con Grupo
    //para todos Fuera Tiempo(comparando la fecha limite)
    public ArrayList FueraTiempoUAGrupoTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Entregados A Tiempo  
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Entregados A Tiempo        
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        //atributos necesarios para Entregados A Tiempo        
        //en el objeto que se manda al delegate
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("ATiempo");
        reporte.setNumRact(1);//aqui cambie para consultar el ract1
            
        listaAuxRact1=FueraTiempoUAGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=FueraTiempoUAGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=FueraTiempoUAGrupo(reporte);
        
        listaAux=ordenarTodosRactsATiempoYNoYEnLimite(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad de aprendizaje con Grupo
    //para todos En Fecha Limite Tiempo(comparando la fecha limite)
    public ArrayList EnFechaLimiteTiempoUAGrupoTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Entregados A Tiempo  
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Entregados A Tiempo        
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                       
        
        reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        //atributos necesarios para Entregados A Tiempo        
        //en el objeto que se manda al delegate
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("ATiempo");
        reporte.setNumRact(1);//aqui cambie para consultar el ract1
            
        listaAuxRact1=EnFechaLimiteTiempoUAGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=EnFechaLimiteTiempoUAGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=EnFechaLimiteTiempoUAGrupo(reporte);
        
        listaAux=ordenarTodosRactsATiempoYNoYEnLimite(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad de aprendizaje con Grupo
    //para todos A Tiempo, En Fecha Limite y Despues de Fecha Limite(comparando la fecha limite)
    public ArrayList ATiempoYNoYEnLimiteUAGrupoTodosRacts(ReporteAux reporteUI){        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Entregados A Tiempo, Despues de fecha limite y en fecha limite  
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Entregados A Tiempo,
        //Despues fecha limite y en fecha limite
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;  
        
        reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        //atributos necesarios para Entregados A Tiempo      
        //Despues fecha limite y en fecha limite
        //en el objeto que se manda al delegate
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("ATiempo");
        reporte.setNumRact(1);//aqui cambie para consultar ract1
        
        listaAuxRact1=ATiempoYNoYEnLimiteUAGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=ATiempoYNoYEnLimiteUAGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=ATiempoYNoYEnLimiteUAGrupo(reporte);
        
        listaAux=ordenarTodosRactsATiempoYNoYEnLimite(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo auxiliar para obtener por cada ract(*no en una sola linea) 
    //de todos los registros en ract(no por unico profesor, unidad
    //de aprendizaje y grupo)
    //para Unidad de aprendizaje con Grupo de todos A Tiempo, En Fecha Limite, 
    // Despues de Fecha Limite(comparando la fecha Limite)
    public ArrayList ATiempoYNoYEnLimiteUAGrupo(ReporteAux reporteUI){                        
        init();
        
        //listas donde se guardaran los Entregados A Tiempo, Despues de fecha limite y en fecha limite
        //y la lista temporal donde esta la union de estas tres al finalizar el metodo
        ArrayList<ReporteAvanceAux> listaAuxATiempo;
        ArrayList<ReporteAvanceAux> listaAuxEnFechaLimiteTiempo;        
        ArrayList<ReporteAvanceAux> listaAuxFueraTiempo;
        ArrayList<ReporteAvanceAux> listaAux2=new ArrayList<ReporteAvanceAux>();
        
        ReporteAvanceAux reporteAvanceAux;
        
        //llama a los metodos de esta misma clase para Entregados A Tiempo, 
        //Despues de fecha limite y en fecha limite
        //mandandole el parametro de objeto recibido que tiene todos los atributos
        //necesarios para la consulta al delegate y dao
        listaAuxATiempo=ATiempoUAGrupo(reporteUI);
        listaAuxEnFechaLimiteTiempo=EnFechaLimiteTiempoUAGrupo(reporteUI);
        listaAuxFueraTiempo=FueraTiempoUAGrupo(reporteUI);
                
        //recorre la lista de Entregados A Tiempo y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxATiempo){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("A tiempo");            
            listaAux2.add(reporteAvanceAux);
        }        
        
        //recorre la lista de Entregados En Fecha Limite y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxEnFechaLimiteTiempo){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("En fecha Limite");            
            listaAux2.add(reporteAvanceAux);
        }
        
        //recorre la lista de Entregados Despues fecha limite y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxFueraTiempo){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Despues fecha limite");            
            listaAux2.add(reporteAvanceAux);
        }
        
        return listaAux2;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor con Grupo
    //para todos A Tiempo(comparando la fecha limite)
    public ArrayList ATiempoProfGrupoTodosRacts(ReporteAux reporteUI){
        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Entregados A Tiempo  
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
        
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Entregados A Tiempo        
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");
        
        //atributos necesarios para Entregados A Tiempo        
        //en el objeto que se manda al delegate
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("ATiempo");
        reporte.setNumRact(1);//aqui cambie para consultar el ract1
            
        listaAuxRact1=ATiempoProfGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=ATiempoProfGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=ATiempoProfGrupo(reporte);
        
        listaAux=ordenarTodosRactsATiempoYNoYEnLimite(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor con Grupo
    //para todos Fuera Tiempo(comparando la fecha limite)
    public ArrayList FueraTiempoProfGrupoTodosRacts(ReporteAux reporteUI){
        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Entregados A Tiempo  
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
        
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Entregados A Tiempo        
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");
        
        //atributos necesarios para Entregados A Tiempo        
        //en el objeto que se manda al delegate
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("ATiempo");
        reporte.setNumRact(1);//aqui cambie para consultar el ract1
            
        listaAuxRact1=FueraTiempoProfGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=FueraTiempoProfGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=FueraTiempoProfGrupo(reporte);
        
        listaAux=ordenarTodosRactsATiempoYNoYEnLimite(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor con Grupo
    //para todos En Fecha Limite Tiempo(comparando la fecha limite)
    public ArrayList EnFechaLimiteTiempoProfGrupoTodosRacts(ReporteAux reporteUI){
        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Entregados A Tiempo  
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
        
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Entregados A Tiempo        
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        

        reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");
        
        //atributos necesarios para Entregados A Tiempo        
        //en el objeto que se manda al delegate
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("ATiempo");
        reporte.setNumRact(1);//aqui cambie para consultar el ract1
            
        listaAuxRact1=EnFechaLimiteTiempoProfGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=EnFechaLimiteTiempoProfGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=EnFechaLimiteTiempoProfGrupo(reporte);
        
        listaAux=ordenarTodosRactsATiempoYNoYEnLimite(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor con Grupo
    //para todos A Tiempo, En Fecha Limite y Despues de Fecha Limite(comparando la fecha limite)
    public ArrayList ATiempoYNoYEnLimiteProfGrupoTodosRacts(ReporteAux reporteUI){
        
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
    
        //listas donde se guardaran los Entregados A Tiempo, Despues fecha limite y en fecha limite  
        //por el numero de ract que indica
        ArrayList<ReporteAvanceAux> listaAuxRact1=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact2=new ArrayList<ReporteAvanceAux>();
        ArrayList<ReporteAvanceAux> listaAuxRact3=new ArrayList<ReporteAvanceAux>();
                
        //objeto necesario para hacer la consulta del delegate en todas
        //las capas hacia el dao, con los atributos necesarios para Entregados A Tiempo,        
        //Despues fecha limite y en fecha limite
        ReporteAux reporte=new ReporteAux();
    
        int cont=0;                        
        
        reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");
        
        //atributos necesarios para Entregados A Tiempo,
        //Despues fecha limite y en fecha limite
        //en el objeto que se manda al delegate
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("ATiempo");
        reporte.setNumRact(1);//aqui cambie para consultar ract1
        
        listaAuxRact1=ATiempoYNoYEnLimiteProfGrupo(reporte);
        
        reporte.setNumRact(2);//aqui cambie para sobreescribir y ahora hacer consulta al ract2
        
        listaAuxRact2=ATiempoYNoYEnLimiteProfGrupo(reporte);
        
        reporte.setNumRact(3);//aqui cambie para sobreescribir y ahora hacer consulta al ract3
        
        listaAuxRact3=ATiempoYNoYEnLimiteProfGrupo(reporte);
        
        listaAux=ordenarTodosRactsATiempoYNoYEnLimite(listaAuxRact1,listaAuxRact2,listaAuxRact3);
                
        return listaAux;
    }

    //metodo auxiliar para obtener por cada ract(*no en una sola linea) 
    //de todos los registros en ract(no por unico profesor, unidad
    //de aprendizaje y grupo)
    //para Profesor con Grupo de todos A Tiempo, En Fecha Limite, 
    // Despues de Fecha Limite(comparando la fecha Limite)
    public ArrayList ATiempoYNoYEnLimiteProfGrupo(ReporteAux reporteUI){        
                
        init();
        
        //listas donde se guardaran los Entregados A Tiempo, Despues de fecha limite y en fecha limite
        //y la lista temporal donde esta la union de estas tres al finalizar el metodo
        ArrayList<ReporteAvanceAux> listaAuxATiempo;
        ArrayList<ReporteAvanceAux> listaAuxEnFechaLimiteTiempo;        
        ArrayList<ReporteAvanceAux> listaAuxFueraTiempo;
        ArrayList<ReporteAvanceAux> listaAux2=new ArrayList<ReporteAvanceAux>();
        
        ReporteAvanceAux reporteAvanceAux;
        
        //llama a los metodos de esta misma clase para Entregados A Tiempo, 
        //Despues de fecha limite y en fecha limite
        //mandandole el parametro de objeto recibido que tiene todos los atributos
        //necesarios para la consulta al delegate y dao
        listaAuxATiempo=ATiempoProfGrupo(reporteUI);
        listaAuxEnFechaLimiteTiempo=EnFechaLimiteTiempoProfGrupo(reporteUI);
        listaAuxFueraTiempo=FueraTiempoProfGrupo(reporteUI);
                
        //recorre la lista de Entregados A Tiempo y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxATiempo){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("A tiempo");            
            listaAux2.add(reporteAvanceAux);
        }       
        
        //recorre la lista de Entregados En fecha Limite y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxEnFechaLimiteTiempo){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("En fecha Limite");            
            listaAux2.add(reporteAvanceAux); 
        }
        
        //recorre la lista de Entregado Despues fecha Limite y lo une a la lista auxiliar que debe
        //tener la union de ambos
        for(ReporteAvanceAux ra: listaAuxFueraTiempo){            
                       
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Despues fecha limite");            
            listaAux2.add(reporteAvanceAux);
        }
        
        return listaAux2;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para los entregados por Programa educativo segun los atributos de consulta
    //de la especificación(Todo esto por un solo numero de Ract)
    public ArrayList entregadosProgEd(ReporteAux reporteUI){
     
        init();  //este se ocupa siempre para refrescar los atributos y siempre sean nuevos y que requieres
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de entregados
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte = new ReporteAux();
    
        int cont=0;                       
        
        reporte = fijarAtributosReporte(reporteUI,"ProgEd"); 
       
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("CompararProgEduc");
        reporte.setTipo("Enviado");
        
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);
                
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
                
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;        
                
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
        
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          
            System.out.println("Filtrosbeanhelper");
            System.out.println("usamos la clave de unidad aprendisaje");
            System.out.println(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            
            areaCon = reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
//            raAux=new ReporteAvanceAux(reporteAvance,cont,cont2,auxPAvance);
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance);
            listaAux.add(raAux);
            
            cont=0;
            auxPAvance=0;
            //break;
            
       }         
               
        return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para los entregados por Area de conocimiento segun los atributos de consulta
    //de la especificación(Todo esto por un solo numero de Ract)
    public ArrayList entregadosAreaCon(ReporteAux reporteUI){
        
        init();
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de entregados
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        int cont=0;      
    
        reporte = fijarAtributosReporte(reporteUI,"AreaCon");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("CompararAreaCon");
        reporte.setTipo("Enviado");
        
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);      
                
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
                
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;        
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
        
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
//            raAux=new ReporteAvanceAux(reporteAvance,cont,cont2,auxPAvance);
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance);
            listaAux.add(raAux);
            }
            
            cont=0;
            auxPAvance=0;
       } 
        
      return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para los entregados por Unidad de aprendizaje con Grupo segun los atributos de consulta
    //de la especificación(Todo esto por un solo numero de Ract)
    public ArrayList entregadosUAGrupo(ReporteAux reporteUI){
        
        init();
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de entregados
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        int cont=0;        
        
        reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("CompararUAGrupo");
        reporte.setTipo("Enviado");
        
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);
        
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
                
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;               
        
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
//            raAux=new ReporteAvanceAux(reporteAvance,cont,cont2,auxPAvance);
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance);
            listaAux.add(raAux);
            }
            
            cont=0;
            auxPAvance=0;
       }
        
        return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para los entregados por Profesor con Grupo segun los atributos de consulta
    //de la especificación(Todo esto por un solo numero de Ract)
    public ArrayList entregadosProfGrupo(ReporteAux reporteUI){
        
        init();
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de entregados
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        int cont=0;        

        reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("CompararProfGrupo");
        reporte.setTipo("Enviado");
                
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);        
                
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
                
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;        
        
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
//            raAux=new ReporteAvanceAux(reporteAvance,cont,cont2,auxPAvance);
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance);
            listaAux.add(raAux);
            }
            
            cont=0;
            auxPAvance=0;
       } 
        
       return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para los no entregados por Programa educativo segun los atributos de consulta
    //de la especificación(Todo esto por un solo numero de Ract)
    public ArrayList noEntregadosProgEd(ReporteAux reporteUI){
        
        init();
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de no entregados
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        int cont=0;        
        
        reporte = fijarAtributosReporte(reporteUI,"ProgEd");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("CompararProgEduc");
        reporte.setTipo("Parcial");

        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);
      
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
                
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;        
                
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;

        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
//            raAux=new ReporteAvanceAux(reporteAvance,cont,cont2,auxPAvance);
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance);
            listaAux.add(raAux);
            
            cont=0;
            auxPAvance=0;
       } 
        
        
        //nuevo no entregado vacio
        
        //esta parte es para la consulta de no entregado vacio es decir
        //que tiene registro en unidadaprendizajeimparteprofesor pero no
        //tiene registro(o ningun ract) en reporteavancecontenidotematico
        //se le mandan los atributos necesarios para la consulta de criteria
        //al delegate y de ahi al dao igual con objeto nuevo de reporte
        //para inicializar con ceros o nulos los atributos que no se necesitan
        reporte=new ReporteAux();
        
        //atributos necesarios para la consulta de no entregado vacio
        reporte.setOp("NoEntregadoVacio");
        //reporte.setNumRact(numRact);
        reporte.setCescicloEscolar(cescicloEscolar);
        reporte.setClavepe(clavepe);
        reporte.setPesvigencia(pesvigencia);
        reporte.setClave(clavepe);
        
        //llama al metodo del delegate y al dao para la consulta de no entregado vacio
        List<UnidadaprendizajeImparteProfesor> lista11=reporteAvanceContenidoTematicoDelegate.getUnidadaprendizajeImparteProfesor(reporte); 
       
        List<Reporteavancecontenidotematico> lista12;            
              
        Reporteavancecontenidotematico ravanceAux2=new Reporteavancecontenidotematico();
        
        //recorre la lista de no entregado vacio y la compara con la que
        //si incluye reporteavancecontenidotematico para saber que el ract no esta presente
        for(UnidadaprendizajeImparteProfesor uaip: lista11){
            
            reporte=new ReporteAux();
            
            reporte.setOp("EnviadosYParciales");
            reporte.setNumProfUIPid(uaip.getUipid());
            reporte.setNumRact(numRact);
            
            lista12=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);
            
            //si no encuentra el id de unidadaprendizajeimparteprofesor agrega la
            //materia y profesor lo convierte de tipo reporteavancecontenidotematico
            //con valors vacios o nulos para que si tenga un registro y si presente
            //por lo menos estos datos y se tenga contemplado en la consulta
            if(lista12.isEmpty()){
                
                ravanceAux2=new Reporteavancecontenidotematico();
                                                
                ravanceAux2.setRacnumero(""+numRact);
                ravanceAux2.setRacstatus(null);
                ravanceAux2.setRacfechaElaboracion(null);
                ravanceAux2.setRacavanceGlobal(0);
                ravanceAux2.setUnidadaprendizajeImparteProfesor(uaip);
                
                areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(uaip.getUnidadaprendizaje().getUapclave());
                //areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(uaip.getUnidadaprendizaje().getUapclave());                
                
                if(areaCon.isEmpty()){
                raAux=new ReporteAvanceAux(ravanceAux2,null,0,0,0);
                listaAux.add(raAux);
                }else{
                    raAux=new ReporteAvanceAux(ravanceAux2,areaCon.get(0),0,0,0);
                    listaAux.add(raAux);
                }
                
            }                
            
        }        
        
        return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para los no entregados por Area de conocimiento segun los atributos de consulta
    //de la especificación(Todo esto por un solo numero de Ract)
    public ArrayList noEntregadosAreaCon(ReporteAux reporteUI){
        
        init();
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de no entregados
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        int cont=0;        
            
        reporte = fijarAtributosReporte(reporteUI,"AreaCon");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("CompararAreaCon");
        reporte.setTipo("Parcial");
        
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);      
                
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
                
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;        
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
                
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
//            raAux=new ReporteAvanceAux(reporteAvance,cont,cont2,auxPAvance);
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance);
            listaAux.add(raAux);
            }
            
            cont=0;
            auxPAvance=0;
       } 
        
         //nuevo no entregado vacio
        
        //esta parte es para la consulta de no entregado vacio es decir
        //que tiene registro en unidadaprendizajeimparteprofesor pero no
        //tiene registro(o ningun ract) en reporteavancecontenidotematico
        //se le mandan los atributos necesarios para la consulta de criteria
        //al delegate y de ahi al dao igual con objeto nuevo de reporte
        //para inicializar con ceros o nulos los atributos que no se necesitan
        reporte=new ReporteAux();
            
        //atributos necesarios para la consulta de no entregado vacio
        reporte.setOp("NoEntregadoVacio");
        //reporte.setNumRact(numRact);
        reporte.setCescicloEscolar(cescicloEscolar);
        reporte.setAcoclave(acoclave);
        reporte.setClavepe(clavepe);
        reporte.setPesvigencia(pesvigencia);
        reporte.setClave(clavepe);
    
        //llama al metodo del delegate y al dao para la consulta de no entregado vacio
        List<UnidadaprendizajeImparteProfesor> lista11=reporteAvanceContenidoTematicoDelegate.getUnidadaprendizajeImparteProfesor(reporte);
       
        List<Reporteavancecontenidotematico> lista12;            
              
        Reporteavancecontenidotematico ravanceAux2=new Reporteavancecontenidotematico();
        
        //recorre la lista de no entregado vacio y la compara con la que
        //si incluye reporteavancecontenidotematico para saber que el ract no esta presente
        for(UnidadaprendizajeImparteProfesor uaip: lista11){
            
            reporte=new ReporteAux();
            
            reporte.setOp("EnviadosYParciales");
            reporte.setNumProfUIPid(uaip.getUipid());
            reporte.setNumRact(numRact);
            
            lista12=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);
            
            //si no encuentra el id de unidadaprendizajeimparteprofesor agrega la
            //materia y profesor lo convierte de tipo reporteavancecontenidotematico
            //con valors vacios o nulos para que si tenga un registro y si presente
            //por lo menos estos datos y se tenga contemplado en la consulta
            if(lista12.isEmpty()){
                
                ravanceAux2=new Reporteavancecontenidotematico();
                                                
                ravanceAux2.setRacnumero(""+numRact);
                ravanceAux2.setRacstatus(null);
                ravanceAux2.setRacfechaElaboracion(null);
                ravanceAux2.setRacavanceGlobal(0);
                ravanceAux2.setUnidadaprendizajeImparteProfesor(uaip);
                
                areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(uaip.getUnidadaprendizaje().getUapclave());
                //areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(uaip.getUnidadaprendizaje().getUapclave());
                
                //if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
                if(areaCon.isEmpty()){
                raAux=new ReporteAvanceAux(ravanceAux2,null,0,0,0);
                listaAux.add(raAux);
                }else{
                    if(areaCon.get(0).getAcoclave() == acoclave || acoclave == 0){
                    raAux=new ReporteAvanceAux(ravanceAux2,areaCon.get(0),0,0,0);
                    listaAux.add(raAux);
                    }                    
                }
                
                //}
            }               
            
        }        
                     
        return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para los no entregados por Unidad de aprendizaje con Grupo segun los atributos de consulta
    //de la especificación(Todo esto por un solo numero de Ract)
    public ArrayList noEntregadosUAGrupo(ReporteAux reporteUI){
        
        init();
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de no entregados
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        int cont=0;        
        
        reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("CompararUAGrupo");
        reporte.setTipo("Parcial");
        
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);
        
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
                
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;               
        
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
//            raAux=new ReporteAvanceAux(reporteAvance,cont,cont2,auxPAvance);
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance);
            listaAux.add(raAux);
            }
            
            cont=0;
            auxPAvance=0;
       } 
        
         //nuevo no entregado vacio
        
        //esta parte es para la consulta de no entregado vacio es decir
        //que tiene registro en unidadaprendizajeimparteprofesor pero no
        //tiene registro(o ningun ract) en reporteavancecontenidotematico
        //se le mandan los atributos necesarios para la consulta de criteria
        //al delegate y de ahi al dao igual con objeto nuevo de reporte
        //para inicializar con ceros o nulos los atributos que no se necesitan
        reporte=new ReporteAux();
        
        //atributos necesarios para la consulta de no entregado vacio
        reporte.setOp("NoEntregadoVacio");
        //reporte.setNumRact(numRact);
        reporte.setCescicloEscolar(cescicloEscolar);
        reporte.setAcoclave(acoclave);
        reporte.setClavepe(clavepe);
        reporte.setPesvigencia(pesvigencia);
        reporte.setGponumero(gponumero);
        reporte.setClave(clavepe);
        reporte.setUapclave(uapclave);
        
        //llama al metodo del delegate y al dao para la consulta de no entregado vacio
        List<UnidadaprendizajeImparteProfesor> lista11=reporteAvanceContenidoTematicoDelegate.getUnidadaprendizajeImparteProfesor(reporte);
       
        List<Reporteavancecontenidotematico> lista12;            
              
        Reporteavancecontenidotematico ravanceAux2=new Reporteavancecontenidotematico();
        
        //recorre la lista de no entregado vacio y la compara con la que
        //si incluye reporteavancecontenidotematico para saber que el ract no esta presente
        for(UnidadaprendizajeImparteProfesor uaip: lista11){
            
            reporte=new ReporteAux();
            
            reporte.setOp("EnviadosYParciales");
            reporte.setNumProfUIPid(uaip.getUipid());
            reporte.setNumRact(numRact);
            
            lista12=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);
     
            //si no encuentra el id de unidadaprendizajeimparteprofesor agrega la
            //materia y profesor lo convierte de tipo reporteavancecontenidotematico
            //con valors vacios o nulos para que si tenga un registro y si presente
            //por lo menos estos datos y se tenga contemplado en la consulta
            if(lista12.isEmpty()){
                
                ravanceAux2=new Reporteavancecontenidotematico();
                                                
                ravanceAux2.setRacnumero(""+numRact);
                ravanceAux2.setRacstatus(null);
                ravanceAux2.setRacfechaElaboracion(null);
                ravanceAux2.setRacavanceGlobal(0);
                ravanceAux2.setUnidadaprendizajeImparteProfesor(uaip);
                
                areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(uaip.getUnidadaprendizaje().getUapclave());
                //areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(uaip.getUnidadaprendizaje().getUapclave());
                
                //if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
                if(areaCon.isEmpty()){
                raAux=new ReporteAvanceAux(ravanceAux2,null,0,0,0);
                listaAux.add(raAux);
                }else{
                    if(areaCon.get(0).getAcoclave() == acoclave || acoclave == 0){
                    raAux=new ReporteAvanceAux(ravanceAux2,areaCon.get(0),0,0,0);
                    listaAux.add(raAux);
                    }                    
                }
                //}
            }                
            
        }                
             
        return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para los no entregados por Profesor con Grupo segun los atributos de consulta
    //de la especificación(Todo esto por un solo numero de Ract)
    public ArrayList noEntregadosProfGrupo(ReporteAux reporteUI){
        
        init();
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de no entregados
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        int cont=0;        

        reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("CompararProfGrupo");
        reporte.setTipo("Parcial");
                
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);        
             
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
                
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
                
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
//            raAux=new ReporteAvanceAux(reporteAvance,cont,cont2,auxPAvance);
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance);
            listaAux.add(raAux);
            }
            
            cont=0;
            auxPAvance=0;
       }
        
         //nuevo no entregado vacio
        
        //esta parte es para la consulta de no entregado vacio es decir
        //que tiene registro en unidadaprendizajeimparteprofesor pero no
        //tiene registro(o ningun ract) en reporteavancecontenidotematico
        //se le mandan los atributos necesarios para la consulta de criteria
        //al delegate y de ahi al dao igual con objeto nuevo de reporte
        //para inicializar con ceros o nulos los atributos que no se necesitan
        reporte=new ReporteAux();

        //atributos necesarios para la consulta de no entregado vacio
        reporte.setOp("NoEntregadoVacio");
        //reporte.setNumRact(numRact);
        reporte.setCescicloEscolar(cescicloEscolar);
        reporte.setAcoclave(acoclave);
        reporte.setClavepe(clavepe);
        reporte.setPesvigencia(pesvigencia);
        reporte.setPronumeroEmpleado(pronumeroEmpleado);
        reporte.setGponumero(gponumero);
        reporte.setClave(clavepe);
        reporte.setUapclave(uapclave);        
                
        //llama al metodo del delegate y al dao para la consulta de no entregado vacio
        List<UnidadaprendizajeImparteProfesor> lista11=reporteAvanceContenidoTematicoDelegate.getUnidadaprendizajeImparteProfesor(reporte);
       
        List<Reporteavancecontenidotematico> lista12;            
              
        Reporteavancecontenidotematico ravanceAux2=new Reporteavancecontenidotematico();
        
        //recorre la lista de no entregado vacio y la compara con la que
        //si incluye reporteavancecontenidotematico para saber que el ract no esta presente
        for(UnidadaprendizajeImparteProfesor uaip: lista11){
            
            reporte=new ReporteAux();
            
            reporte.setOp("EnviadosYParciales");
            reporte.setNumProfUIPid(uaip.getUipid());
            reporte.setNumRact(numRact);
            
            lista12=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);
            
            //si no encuentra el id de unidadaprendizajeimparteprofesor agrega la
            //materia y profesor lo convierte de tipo reporteavancecontenidotematico
            //con valors vacios o nulos para que si tenga un registro y si presente
            //por lo menos estos datos y se tenga contemplado en la consulta
            if(lista12.isEmpty()){
                
                ravanceAux2=new Reporteavancecontenidotematico();
                                                
                ravanceAux2.setRacnumero(""+numRact);
                ravanceAux2.setRacstatus(null);
                ravanceAux2.setRacfechaElaboracion(null);
                ravanceAux2.setRacavanceGlobal(0);
                ravanceAux2.setUnidadaprendizajeImparteProfesor(uaip);
                
                areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(uaip.getUnidadaprendizaje().getUapclave());
                //areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(uaip.getUnidadaprendizaje().getUapclave());
                
                //if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
                if(areaCon.isEmpty()){
                raAux=new ReporteAvanceAux(ravanceAux2,null,0,0,0);
                listaAux.add(raAux);
                }else{
                    if(areaCon.get(0).getAcoclave() == acoclave || acoclave == 0){
                    raAux=new ReporteAvanceAux(ravanceAux2,areaCon.get(0),0,0,0);
                    listaAux.add(raAux);
                    }                    
                }
                //}
            }                            
            
        }                
              
        return listaAux;
    }
    
    //metodo que une los entregados y no entregados por un solo numero de
    //ract *no por una sola linea los tres racts de un mismo id de 
    //unidadaprendizajeimparteprofesor
    public ArrayList entregadosYNoEntregadosProgEd(ReporteAux reporteUI){
        //objeto que esta inicializado a ceros o nulos los atributos
        //que no necesita y posteriormente se guardan con los set los
        //atributos que se van a necesitar
        ReporteAux reporte=new ReporteAux(); 
        
        init();
        
        reporte = fijarAtributosReporte(reporteUI,"ProgEd");
        
        ArrayList<ReporteAvanceAux> listaAuxEntregadosProgEd;
        ArrayList<ReporteAvanceAux> listaAuxNoEntregadosProgEd;  
        ArrayList<ReporteAvanceAux> listaAux2 = new ArrayList<ReporteAvanceAux>();
        
        ReporteAvanceAux reporteAvanceAux;
        
        //hace dos listas llamando los metodos de entregados en una
        //y no entregados en otra
        listaAuxEntregadosProgEd=entregadosProgEd(reporteUI);
        listaAuxNoEntregadosProgEd=noEntregadosProgEd(reporteUI);
        
        //agrega a la lista principal los entregados
        for(ReporteAvanceAux ra: listaAuxEntregadosProgEd){            
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Entregado");            
            listaAux2.add(reporteAvanceAux);            
        }
        
        //agrega a la misma lista principal los no entregados
        for(ReporteAvanceAux ra: listaAuxNoEntregadosProgEd){            
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("No entregado");            
            listaAux2.add(reporteAvanceAux);            
        }
        
        return listaAux2;
    }
    
    //metodo que une los entregados y no entregados por un solo numero de
    //ract *no por una sola linea los tres racts de un mismo id de 
    //unidadaprendizajeimparteprofesor
    public ArrayList entregadosYNoEntregadosAreaCon(ReporteAux reporteUI){        
        //objeto que esta inicializado a ceros o nulos los atributos
        //que no necesita y posteriormente se guardan con los set los
        //atributos que se van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        init();
        
        reporte = fijarAtributosReporte(reporteUI,"AreaCon");
        
        ArrayList<ReporteAvanceAux> listaAuxEntregadosAreaCon;
        ArrayList<ReporteAvanceAux> listaAuxNoEntregadosAreaCon;
        ArrayList<ReporteAvanceAux> listaAux2=new ArrayList<ReporteAvanceAux>();
        
        ReporteAvanceAux reporteAvanceAux;
                
        //hace dos listas llamando los metodos de entregados en una
        //y no entregados en otra
        listaAuxEntregadosAreaCon=entregadosAreaCon(reporteUI);
        listaAuxNoEntregadosAreaCon=noEntregadosAreaCon(reporteUI);
        
        //agrega a la lista principal los entregados
        for(ReporteAvanceAux ra: listaAuxEntregadosAreaCon){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Entregado");            
            listaAux2.add(reporteAvanceAux);
            
        }
        
        //agrega a la misma lista principal los no entregados
        for(ReporteAvanceAux ra: listaAuxNoEntregadosAreaCon){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("No entregado");            
            listaAux2.add(reporteAvanceAux);
            
        }
        
        return listaAux2;
    }
    
    //metodo que une los entregados y no entregados por un solo numero de
    //ract *no por una sola linea los tres racts de un mismo id de 
    //unidadaprendizajeimparteprofesor
    public ArrayList entregadosYNoEntregadosUAGrupo(ReporteAux reporteUI){
        //objeto que esta inicializado a ceros o nulos los atributos
        //que no necesita y posteriormente se guardan con los set los
        //atributos que se van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        init();
        
        reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        //atributos necesarios para entregados y no entregados en el objeto que se manda al
        //delegate
        reporte.setNumRact(1);//aqui cambie para hacer consulta al ract1
                       
        ArrayList<ReporteAvanceAux> listaAuxEntregadosUAGrupo;
        ArrayList<ReporteAvanceAux> listaAuxNoEntregadosUAGrupo;        
        ArrayList<ReporteAvanceAux> listaAux2=new ArrayList<ReporteAvanceAux>();
        
        ReporteAvanceAux reporteAvanceAux;
        
        //hace dos listas llamando los metodos de entregados en una
        //y no entregados en otra
        listaAuxEntregadosUAGrupo=entregadosUAGrupo(reporteUI);
        listaAuxNoEntregadosUAGrupo=noEntregadosUAGrupo(reporteUI);
        
        //agrega a la lista principal los entregados
        for(ReporteAvanceAux ra: listaAuxEntregadosUAGrupo){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Entregado");            
            listaAux2.add(reporteAvanceAux);
            
        }
        
        //agrega a la misma lista principal los no entregados
        for(ReporteAvanceAux ra: listaAuxNoEntregadosUAGrupo){
                                    
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("No entregado");            
            listaAux2.add(reporteAvanceAux);
            
        }
        
        return listaAux2;
    }

    //metodo que une los entregados y no entregados por un solo numero de
    //ract *no por una sola linea los tres racts de un mismo id de 
    //unidadaprendizajeimparteprofesor
    public ArrayList entregadosYNoEntregadosProfGrupo(ReporteAux reporteUI){
        //objeto que esta inicializado a ceros o nulos los atributos
        //que no necesita y posteriormente se guardan con los set los
        //atributos que se van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        init();
        
        reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");
                
        ArrayList<ReporteAvanceAux> listaAuxEntregadosProfGrupo;
        ArrayList<ReporteAvanceAux> listaAuxNoEntregadosProfGrupo;        
        
        ReporteAvanceAux reporteAvanceAux;
                
        //hace dos listas llamando los metodos de entregados en una
        //y no entregados en otra
        listaAuxEntregadosProfGrupo=entregadosProfGrupo(reporteUI);
        listaAuxNoEntregadosProfGrupo=noEntregadosProfGrupo(reporteUI);
        ArrayList<ReporteAvanceAux> listaAux2=new ArrayList<ReporteAvanceAux>();
        
        //agrega a la lista principal los entregados
        for(ReporteAvanceAux ra: listaAuxEntregadosProfGrupo){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Entregado");            
            listaAux2.add(reporteAvanceAux);
            
        }
        
        //agrega a la lista principal los entregados
        for(ReporteAvanceAux ra: listaAuxNoEntregadosProfGrupo){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("No entregado");            
            listaAux2.add(reporteAvanceAux);
            
        }
        
        return listaAux2;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para Porcentaje Avance General *Incompleto* por Programa educativo
    //segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList PAGCProgEd(ReporteAux reporteUI){
        
        init();
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Porcentaje de
        //Avance General Incompleto
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        int cont=0;         
        
        reporte = fijarAtributosReporte(reporteUI,"ProgEd");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("CompararProgEduc");
        reporte.setTipo("EnviadoYParcial");
        
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);

        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
        
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;        
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
                
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)

            //en esta parte se recorre individualmente cada ract(3ro, 2do o 1ero)
            // y segun el id de unidadaprendizajeimparteprofesor encuentra el porcentaje
            //que le corresponde para despues comparar cada numero de ract por el mismo
            //profesor con misma unidadaprendizaje y grupo para saber y calcular despues
            //el mayor porcentaje alcanzado de este            
            auxNumRact=3;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setClave(clavepe);
            
            List<Reporteavancecontenidotematico> lista6=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);            
            
            //se calcula el porcentaje ahora ract2 por id unidadaprendizajeimparteprofesor            
            auxNumRact=2;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setClave(clavepe);
            
            List<Reporteavancecontenidotematico> lista7=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);            
            
            //se calcula el porcentaje ahora ract1 por id unidadaprendizajeimparteprofesor
            auxNumRact=1;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setClave(clavepe);
            
            List<Reporteavancecontenidotematico> lista8=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);            
                        
            //compara la lista6 que contiene el porcentaje del ract3 junto con
            //compara la lista7 que contiene el porcentaje del ract2 junto con
            //compara la lista8 que contiene el porcentaje del ract1
            //solo toma en auxPAvance el valor del procentaje mayor por id
            // de unidadprendizajeimparteprofesor
            if(lista6.size()>0){
                auxPAvance=lista6.get(0).getRacavanceGlobal();
            }else{
                if(lista7.size()>0){
                    auxPAvance=lista7.get(0).getRacavanceGlobal();
                }else{
                    if(lista8.size()>0){
                        auxPAvance=lista8.get(0).getRacavanceGlobal();
                    }
                }
            }
            
           // System.out.println("  Ciclo escolar:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getCicloescolar().getCescicloEscolar()+"   Clave de programa educativo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getPlanestudio().getProgramaeducativo().getPedclave()+"   Programa educativo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getPlanestudio().getProgramaeducativo().getPednombre()+"   Area de conocimiento:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getAconombre()+"   Plan de estudios:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getPlanestudio().getPesvigenciaPlan()+"   Clave Unidad de aprendizaje:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave()+"   Unidad de aprendizaje:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapnombre()+"   Profesor:   Numero de empleado:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getPronumeroEmpleado()+"   Nombre:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getPronombre()+"   Apellido Paterno:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoPaterno()+"   Apellido Materno:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoMaterno()+"   Grupo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getGrupo().getGponumero()+"   Grupo Tipo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUiptipoSubgrupo()+"   Grupo Subgrupo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUipsubgrupo()+"  Total entregados por Profesor, Unidad de aprendizaje y Grupo : "+cont+" Total entregados global: "+cont2+"  Porcentaje global completado por Profesor, Unidad de aprendizaje y Grupo:   "+auxPAvance+"   Porcentaje avance ract seleccionado   "+reporteAvance.getRacavanceGlobal());            
            
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*
//            raAux=new ReporteAvanceAux(reporteAvance,cont,cont2,auxPAvance);
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance);
            
            //solo guarda los valores en la lista cuando no alcanzaron el 100% en el
            //mayor ract que son los Porcentaje Avance Global Incompleto
            if(auxPAvance!=100){
            listaAux.add(raAux);
            }
            
            cont=0;
            auxPAvance=0;
       }        
              
        return listaAux;
    }

    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para Porcentaje Avance General *Incompleto* por Area de conocimiento
    //segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList PAGCAreaCon(ReporteAux reporteUI){        
        init();
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Porcentaje de
        //Avance General Incompleto
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        int cont=0;        
          
        reporte = fijarAtributosReporte(reporteUI,"AreaCon");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("CompararAreaCon");
        reporte.setTipo("EnviadoYParcial");
       
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);      
        
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
                
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
                
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
            
            //en esta parte se recorre individualmente cada ract(3ro, 2do o 1ero)
            // y segun el id de unidadaprendizajeimparteprofesor encuentra el porcentaje
            //que le corresponde para despues comparar cada numero de ract por el mismo
            //profesor con misma unidadaprendizaje y grupo para saber y calcular despues
            //el mayor porcentaje alcanzado de este            
            auxNumRact=3;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setAcoclave(acoclave);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setClave(clavepe);
            
            List<Reporteavancecontenidotematico> lista6=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);            
                        
            //se calcula el porcentaje ahora ract2 por id unidadaprendizajeimparteprofesor
            auxNumRact=2;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setAcoclave(acoclave);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setClave(clavepe);
            
            List<Reporteavancecontenidotematico> lista7=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);                       
                     
            //se calcula el porcentaje ahora ract1 por id unidadaprendizajeimparteprofesor
            auxNumRact=1;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setAcoclave(acoclave);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setClave(clavepe);
            
            List<Reporteavancecontenidotematico> lista8=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);            

            //compara la lista6 que contiene el porcentaje del ract3 junto con
            //compara la lista7 que contiene el porcentaje del ract2 junto con
            //compara la lista8 que contiene el porcentaje del ract1
            //solo toma en auxPAvance el valor del procentaje mayor por id
            // de unidadprendizajeimparteprofesor
            if(lista6.size()>0){
                auxPAvance=lista6.get(0).getRacavanceGlobal();
            }else{
                if(lista7.size()>0){
                    auxPAvance=lista7.get(0).getRacavanceGlobal();
                }else{
                    if(lista8.size()>0){
                        auxPAvance=lista8.get(0).getRacavanceGlobal();
                    }
                }
            }            
                       
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont,cont2,auxPAvance);
            
            //solo guarda los valores en la lista cuando no alcanzaron el 100% en el
            //mayor ract que son los Porcentaje Avance Global Incompleto
            if(auxPAvance!=100){
            listaAux.add(raAux);
            }
            }
            
            cont=0;
            auxPAvance=0;
       }         
              
        return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para Porcentaje Avance General *Incompleto* por Unidad de aprendizaje con Grupo
    //segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList PAGCUAGrupo(ReporteAux reporteUI){
        
        init();
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Porcentaje de
        //Avance General Incompleto
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        int cont=0;        
      
        reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("CompararUAGrupo");
        reporte.setTipo("EnviadoYParcial");
        
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);
        
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
                
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;              
        
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
            
            //en esta parte se recorre individualmente cada ract(3ro, 2do o 1ero)
            // y segun el id de unidadaprendizajeimparteprofesor encuentra el porcentaje
            //que le corresponde para despues comparar cada numero de ract por el mismo
            //profesor con misma unidadaprendizaje y grupo para saber y calcular despues
            //el mayor porcentaje alcanzado de este            
            auxNumRact=3;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setAcoclave(acoclave);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setGponumero(gponumero);
            reporte.setClave(clavepe);
            reporte.setUapclave(uapclave);
        
            List<Reporteavancecontenidotematico> lista6=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);           
                                 
            //se calcula el porcentaje ahora ract2 por id unidadaprendizajeimparteprofesor
            auxNumRact=2;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setAcoclave(acoclave);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setGponumero(gponumero);
            reporte.setClave(clavepe);
            reporte.setUapclave(uapclave);
        
            List<Reporteavancecontenidotematico> lista7=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);          
                                    
            //se calcula el porcentaje ahora ract1 por id unidadaprendizajeimparteprofesor
            auxNumRact=1;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setAcoclave(acoclave);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setGponumero(gponumero);
            reporte.setClave(clavepe);
            reporte.setUapclave(uapclave);
        
            List<Reporteavancecontenidotematico> lista8=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);           
                                    
            //compara la lista6 que contiene el porcentaje del ract3 junto con
            //compara la lista7 que contiene el porcentaje del ract2 junto con
            //compara la lista8 que contiene el porcentaje del ract1
            //solo toma en auxPAvance el valor del procentaje mayor por id
            // de unidadprendizajeimparteprofesor
            if(lista6.size()>0){
                auxPAvance=lista6.get(0).getRacavanceGlobal();
            }else{
                if(lista7.size()>0){
                    auxPAvance=lista7.get(0).getRacavanceGlobal();
                }else{
                    if(lista8.size()>0){
                        auxPAvance=lista8.get(0).getRacavanceGlobal();
                    }
                }
            }
            
//            System.out.println("  Ciclo escolar:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getCicloescolar().getCescicloEscolar()+"   Clave de programa educativo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getPlanestudio().getProgramaeducativo().getPedclave()+"   Programa educativo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getPlanestudio().getProgramaeducativo().getPednombre()+"   Area de conocimiento:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getAconombre()+"   Plan de estudios:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getPlanestudio().getPesvigenciaPlan()+"   Clave Unidad de aprendizaje:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave()+"   Unidad de aprendizaje:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapnombre()+"   Profesor:   Numero de empleado:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getPronumeroEmpleado()+"   Nombre:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getPronombre()+"   Apellido Paterno:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoPaterno()+"   Apellido Materno:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoMaterno()+"   Grupo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getGrupo().getGponumero()+"   Grupo Tipo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUiptipoSubgrupo()+"   Grupo Subgrupo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUipsubgrupo()+"  Total entregados por Profesor, Unidad de aprendizaje y Grupo : "+cont+" Total entregados global: "+cont2+"  Porcentaje global completado por Profesor, Unidad de aprendizaje y Grupo:   "+auxPAvance+"   Porcentaje avance ract seleccionado   "+reporteAvance.getRacavanceGlobal());            
            
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*
//            raAux=new ReporteAvanceAux(reporteAvance,cont,cont2,auxPAvance);
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance);
            
            //solo guarda los valores en la lista cuando no alcanzaron el 100% en el
            //mayor ract que son los Porcentaje Avance Global Incompleto
            if(auxPAvance!=100){
            listaAux.add(raAux);
            }
            }
            
            cont=0;
            auxPAvance=0;
       }         
             
        return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para Porcentaje Avance General *Incompleto* por Profesor con Grupo
    //segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList PAGCProfGrupo(ReporteAux reporteUI){
        
        init();
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Porcentaje de
        //Avance General Incompleto
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        int cont=0;        
        
        reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("CompararProfGrupo");
        reporte.setTipo("EnviadoYParcial");
        
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);        
        
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
                
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
                
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
            
            //en esta parte se recorre individualmente cada ract(3ro, 2do o 1ero)
            // y segun el id de unidadaprendizajeimparteprofesor encuentra el porcentaje
            //que le corresponde para despues comparar cada numero de ract por el mismo
            //profesor con misma unidadaprendizaje y grupo para saber y calcular despues
            //el mayor porcentaje alcanzado de este            
            auxNumRact=3;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setAcoclave(acoclave);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setPronumeroEmpleado(pronumeroEmpleado);
            reporte.setGponumero(gponumero); 
            reporte.setClave(clavepe);
            reporte.setUapclave(uapclave);
        
            List<Reporteavancecontenidotematico> lista6=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);
                                    
            //se calcula el porcentaje ahora ract2 por id unidadaprendizajeimparteprofesor
            auxNumRact=2;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setAcoclave(acoclave);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setPronumeroEmpleado(pronumeroEmpleado);
            reporte.setGponumero(gponumero); 
            reporte.setClave(clavepe);
            reporte.setUapclave(uapclave);
        
            List<Reporteavancecontenidotematico> lista7=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);
                                    
            //se calcula el porcentaje ahora ract1 por id unidadaprendizajeimparteprofesor
            auxNumRact=1;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setAcoclave(acoclave);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setPronumeroEmpleado(pronumeroEmpleado);
            reporte.setGponumero(gponumero); 
            reporte.setClave(clavepe);
            reporte.setUapclave(uapclave);
        
            List<Reporteavancecontenidotematico> lista8=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);

            //compara la lista6 que contiene el porcentaje del ract3 junto con
            //compara la lista7 que contiene el porcentaje del ract2 junto con
            //compara la lista8 que contiene el porcentaje del ract1
            //solo toma en auxPAvance el valor del procentaje mayor por id
            // de unidadprendizajeimparteprofesor
            if(lista6.size()>0){
                auxPAvance=lista6.get(0).getRacavanceGlobal();
            }else{
                if(lista7.size()>0){
                    auxPAvance=lista7.get(0).getRacavanceGlobal();
                }else{
                    if(lista8.size()>0){
                        auxPAvance=lista8.get(0).getRacavanceGlobal();
                    }
                }
            }
            
           // System.out.println("  Ciclo escolar:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getCicloescolar().getCescicloEscolar()+"   Clave de programa educativo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getPlanestudio().getProgramaeducativo().getPedclave()+"   Programa educativo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getPlanestudio().getProgramaeducativo().getPednombre()+"   Area de conocimiento:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getAconombre()+"   Plan de estudios:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getPlanestudio().getPesvigenciaPlan()+"   Clave Unidad de aprendizaje:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave()+"   Unidad de aprendizaje:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapnombre()+"   Profesor:   Numero de empleado:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getPronumeroEmpleado()+"   Nombre:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getPronombre()+"   Apellido Paterno:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoPaterno()+"   Apellido Materno:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoMaterno()+"   Grupo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getGrupo().getGponumero()+"   Grupo Tipo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUiptipoSubgrupo()+"   Grupo Subgrupo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUipsubgrupo()+"  Total entregados por Profesor, Unidad de aprendizaje y Grupo : "+cont+" Total entregados global: "+cont2+"  Porcentaje global completado por Profesor, Unidad de aprendizaje y Grupo:   "+auxPAvance+"   Porcentaje avance ract seleccionado   "+reporteAvance.getRacavanceGlobal());            
            
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*
//            raAux=new ReporteAvanceAux(reporteAvance,cont,cont2,auxPAvance);
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance);
            
            //solo guarda los valores en la lista cuando no alcanzaron el 100% en el
            //mayor ract que son los Porcentaje Avance Global Incompleto
            if(auxPAvance!=100){
            listaAux.add(raAux);
            }
            }
            
            cont=0;
            auxPAvance=0;
       }         
              
        return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para Porcentaje Avance General Completo por Programa educativo
    //segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList PAGCCompletoProgEd(ReporteAux reporteUI){        
        init();
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Porcentaje de
        //Avance General Completo
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        int cont=0;         
      
        reporte = fijarAtributosReporte(reporteUI,"ProgEd");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("CompararProgEduc");
        reporte.setTipo("EnviadoYParcial");
        
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);
        
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
                
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;        
                
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
                
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)

            //en esta parte se recorre individualmente cada ract(3ro, 2do o 1ero)
            // y segun el id de unidadaprendizajeimparteprofesor encuentra el porcentaje
            //que le corresponde para despues comparar cada numero de ract por el mismo
            //profesor con misma unidadaprendizaje y grupo para saber y calcular despues
            //el mayor porcentaje alcanzado de este            
            auxNumRact=3;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setClave(clavepe);
            
            List<Reporteavancecontenidotematico> lista6=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);            
       
            //se calcula el porcentaje ahora ract2 por id unidadaprendizajeimparteprofesor
            auxNumRact=2;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setClave(clavepe);
            
            List<Reporteavancecontenidotematico> lista7=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);            
                        
            //se calcula el porcentaje ahora ract1 por id unidadaprendizajeimparteprofesor
            auxNumRact=1;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setClave(clavepe);
            
            List<Reporteavancecontenidotematico> lista8=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);            

            //compara la lista6 que contiene el porcentaje del ract3 junto con
            //compara la lista7 que contiene el porcentaje del ract2 junto con
            //compara la lista8 que contiene el porcentaje del ract1
            //solo toma en auxPAvance el valor del procentaje mayor por id
            // de unidadprendizajeimparteprofesor
            if(lista6.size()>0){
                auxPAvance=lista6.get(0).getRacavanceGlobal();
            }else{
                if(lista7.size()>0){
                    auxPAvance=lista7.get(0).getRacavanceGlobal();
                }else{
                    if(lista8.size()>0){
                        auxPAvance=lista8.get(0).getRacavanceGlobal();
                    }
                }
            }           
                      
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*
//            raAux=new ReporteAvanceAux(reporteAvance,cont,cont2,auxPAvance);
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance);
            
            //solo guarda los valores en la lista cuando alcanzaron el 100% en el
            //mayor ract que son los Porcentaje Avance Global Completo
            if(auxPAvance==100){
            listaAux.add(raAux);
            }
            
            cont=0;
            auxPAvance=0;
       }         
              
        return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para Porcentaje Avance General Completo por Area de conocimiento
    //segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList PAGCCompletoAreaCon(ReporteAux reporteUI){
        
        init();
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Porcentaje de
        //Avance General Completo
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        int cont=0;        
        
        reporte = fijarAtributosReporte(reporteUI,"AreaCon");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("CompararAreaCon");
        reporte.setTipo("EnviadoYParcial");
        
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);      
        
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
               
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
               
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
            
            //en esta parte se recorre individualmente cada ract(3ro, 2do o 1ero)
            // y segun el id de unidadaprendizajeimparteprofesor encuentra el porcentaje
            //que le corresponde para despues comparar cada numero de ract por el mismo
            //profesor con misma unidadaprendizaje y grupo para saber y calcular despues
            //el mayor porcentaje alcanzado de este            
            auxNumRact=3;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setAcoclave(acoclave);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setClave(clavepe);
            
            List<Reporteavancecontenidotematico> lista6=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);            
                        
            //se calcula el porcentaje ahora ract2 por id unidadaprendizajeimparteprofesor
            auxNumRact=2;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setAcoclave(acoclave);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setClave(clavepe);
            
            List<Reporteavancecontenidotematico> lista7=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);            
                                    
            //se calcula el porcentaje ahora ract1 por id unidadaprendizajeimparteprofesor
            auxNumRact=1;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setAcoclave(acoclave);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setClave(clavepe);
            
            List<Reporteavancecontenidotematico> lista8=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);            
            
            //compara la lista6 que contiene el porcentaje del ract3 junto con
            //compara la lista7 que contiene el porcentaje del ract2 junto con
            //compara la lista8 que contiene el porcentaje del ract1
            //solo toma en auxPAvance el valor del procentaje mayor por id
            // de unidadprendizajeimparteprofesor
            if(lista6.size()>0){
                auxPAvance=lista6.get(0).getRacavanceGlobal();
            }else{
                if(lista7.size()>0){
                    auxPAvance=lista7.get(0).getRacavanceGlobal();
                }else{
                    if(lista8.size()>0){
                        auxPAvance=lista8.get(0).getRacavanceGlobal();
                    }
                }
            }            
                   
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*
//            raAux=new ReporteAvanceAux(reporteAvance,cont,cont2,auxPAvance);
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance);
            
            //solo guarda los valores en la lista cuando alcanzaron el 100% en el
            //mayor ract que son los Porcentaje Avance Global Completo
            if(auxPAvance==100){
            listaAux.add(raAux);
            }
            }
            
            cont=0;
            auxPAvance=0;
       }        
              
        return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para Porcentaje Avance General Completo por Unidad de aprendizaje con Grupo
    //segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList PAGCCompletoUAGrupo(ReporteAux reporteUI){
        
        init();
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Porcentaje de
        //Avance General Completo
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        int cont=0;        
        
        reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("CompararUAGrupo");
        reporte.setTipo("EnviadoYParcial");
        
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);
        
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
                
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;               
        
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
            
            //en esta parte se recorre individualmente cada ract(3ro, 2do o 1ero)
            // y segun el id de unidadaprendizajeimparteprofesor encuentra el porcentaje
            //que le corresponde para despues comparar cada numero de ract por el mismo
            //profesor con misma unidadaprendizaje y grupo para saber y calcular despues
            //el mayor porcentaje alcanzado de este            
            auxNumRact=3;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setAcoclave(acoclave);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setGponumero(gponumero);
            reporte.setClave(clavepe);
            reporte.setUapclave(uapclave);
        
            List<Reporteavancecontenidotematico> lista6=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);           
                                    
            //se calcula el porcentaje ahora ract2 por id unidadaprendizajeimparteprofesor
            auxNumRact=2;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setAcoclave(acoclave);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setGponumero(gponumero);
            reporte.setClave(clavepe);
            reporte.setUapclave(uapclave);
        
            List<Reporteavancecontenidotematico> lista7=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);           
                                    
            //se calcula el porcentaje ahora ract1 por id unidadaprendizajeimparteprofesor
            auxNumRact=1;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setAcoclave(acoclave);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setGponumero(gponumero);
            reporte.setClave(clavepe);
            reporte.setUapclave(uapclave);
        
            List<Reporteavancecontenidotematico> lista8=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);           

            //compara la lista6 que contiene el porcentaje del ract3 junto con
            //compara la lista7 que contiene el porcentaje del ract2 junto con
            //compara la lista8 que contiene el porcentaje del ract1
            //solo toma en auxPAvance el valor del procentaje mayor por id
            // de unidadprendizajeimparteprofesor
            if(lista6.size()>0){
                auxPAvance=lista6.get(0).getRacavanceGlobal();
            }else{
                if(lista7.size()>0){
                    auxPAvance=lista7.get(0).getRacavanceGlobal();
                }else{
                    if(lista8.size()>0){
                        auxPAvance=lista8.get(0).getRacavanceGlobal();
                    }
                }
            }
            
            //System.out.println("  Ciclo escolar:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getCicloescolar().getCescicloEscolar()+"   Clave de programa educativo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getPlanestudio().getProgramaeducativo().getPedclave()+"   Programa educativo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getPlanestudio().getProgramaeducativo().getPednombre()+"   Area de conocimiento:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getAconombre()+"   Plan de estudios:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getPlanestudio().getPesvigenciaPlan()+"   Clave Unidad de aprendizaje:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave()+"   Unidad de aprendizaje:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapnombre()+"   Profesor:   Numero de empleado:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getPronumeroEmpleado()+"   Nombre:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getPronombre()+"   Apellido Paterno:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoPaterno()+"   Apellido Materno:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoMaterno()+"   Grupo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getGrupo().getGponumero()+"   Grupo Tipo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUiptipoSubgrupo()+"   Grupo Subgrupo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUipsubgrupo()+"  Total entregados por Profesor, Unidad de aprendizaje y Grupo : "+cont+" Total entregados global: "+cont2+"  Porcentaje global completado por Profesor, Unidad de aprendizaje y Grupo:   "+auxPAvance+"   Porcentaje avance ract seleccionado   "+reporteAvance.getRacavanceGlobal());            
            
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*
//            raAux=new ReporteAvanceAux(reporteAvance,cont,cont2,auxPAvance);
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance);
            
            //solo guarda los valores en la lista cuando alcanzaron el 100% en el
            //mayor ract que son los Porcentaje Avance Global Completo
            if(auxPAvance==100){
            listaAux.add(raAux);
            }
            }
            
            cont=0;
            auxPAvance=0;
       }         
              
        return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para Porcentaje Avance General Completo por Profesor con Grupo
    //segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList PAGCCompletoProfGrupo(ReporteAux reporteUI){
        
        init();
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Porcentaje de
        //Avance General Completo
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        int cont=0;        
        
        reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("CompararProfGrupo");
        reporte.setTipo("EnviadoYParcial");
        
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);        
        
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
             
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
             
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
            
            //en esta parte se recorre individualmente cada ract(3ro, 2do o 1ero)
            // y segun el id de unidadaprendizajeimparteprofesor encuentra el porcentaje
            //que le corresponde para despues comparar cada numero de ract por el mismo
            //profesor con misma unidadaprendizaje y grupo para saber y calcular despues
            //el mayor porcentaje alcanzado de este            
            auxNumRact=3;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setAcoclave(acoclave);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setPronumeroEmpleado(pronumeroEmpleado);
            reporte.setGponumero(gponumero); 
            reporte.setClave(clavepe);
            reporte.setUapclave(uapclave);
        
            List<Reporteavancecontenidotematico> lista6=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);
        
            //se calcula el porcentaje ahora ract2 por id unidadaprendizajeimparteprofesor
            auxNumRact=2;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setAcoclave(acoclave);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setPronumeroEmpleado(pronumeroEmpleado);
            reporte.setGponumero(gponumero); 
            reporte.setClave(clavepe);
            reporte.setUapclave(uapclave);
        
            List<Reporteavancecontenidotematico> lista7=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);
                                    
            //se calcula el porcentaje ahora ract1 por id unidadaprendizajeimparteprofesor
            auxNumRact=1;
            
            reporte=new ReporteAux();
            
            reporte.setOp("PorcentAvanceSolo");
            reporte.setTipo("EnviadoYParcial");
            reporte.setNumRact(auxNumRact);
            reporte.setCescicloEscolar(cescicloEscolar);
            reporte.setAcoclave(acoclave);
            reporte.setClavepe(clavepe);
            reporte.setPesvigencia(pesvigencia);
            reporte.setNumProfUIPid(reporteAvance.getUnidadaprendizajeImparteProfesor().getUipid());
            reporte.setPronumeroEmpleado(pronumeroEmpleado);
            reporte.setGponumero(gponumero); 
            reporte.setClave(clavepe);
            reporte.setUapclave(uapclave);
        
            List<Reporteavancecontenidotematico> lista8=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);

            //compara la lista6 que contiene el porcentaje del ract3 junto con
            //compara la lista7 que contiene el porcentaje del ract2 junto con
            //compara la lista8 que contiene el porcentaje del ract1
            //solo toma en auxPAvance el valor del procentaje mayor por id
            // de unidadprendizajeimparteprofesor
            if(lista6.size()>0){
                auxPAvance=lista6.get(0).getRacavanceGlobal();
            }else{
                if(lista7.size()>0){
                    auxPAvance=lista7.get(0).getRacavanceGlobal();
                }else{
                    if(lista8.size()>0){
                        auxPAvance=lista8.get(0).getRacavanceGlobal();
                    }
                }
            }
            
          // System.out.println("  Ciclo escolar:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getCicloescolar().getCescicloEscolar()+"   Clave de programa educativo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getPlanestudio().getProgramaeducativo().getPedclave()+"   Programa educativo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getPlanestudio().getProgramaeducativo().getPednombre()+"   Area de conocimiento:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getAconombre()+"   Plan de estudios:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getAreaconocimiento().getPlanestudio().getPesvigenciaPlan()+"   Clave Unidad de aprendizaje:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave()+"   Unidad de aprendizaje:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapnombre()+"   Profesor:   Numero de empleado:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getPronumeroEmpleado()+"   Nombre:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getPronombre()+"   Apellido Paterno:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoPaterno()+"   Apellido Materno:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoMaterno()+"   Grupo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getGrupo().getGponumero()+"   Grupo Tipo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUiptipoSubgrupo()+"   Grupo Subgrupo:   "+reporteAvance.getUnidadaprendizajeImparteProfesor().getUipsubgrupo()+"  Total entregados por Profesor, Unidad de aprendizaje y Grupo : "+cont+" Total entregados global: "+cont2+"  Porcentaje global completado por Profesor, Unidad de aprendizaje y Grupo:   "+auxPAvance+"   Porcentaje avance ract seleccionado   "+reporteAvance.getRacavanceGlobal());            
            
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*
//            raAux=new ReporteAvanceAux(reporteAvance,cont,cont2,auxPAvance);
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance);
        
            //solo guarda los valores en la lista cuando alcanzaron el 100% en el
            //mayor ract que son los Porcentaje Avance Global Completo
            if(auxPAvance==100){
            listaAux.add(raAux);
            }
            }
            
            cont=0;
            auxPAvance=0;
       }         
             
        return listaAux;
    }
    
    //este metodo une los Porcentaje Avance Global Completo con Incompleto
    //por un solo numero de ract, *no en una sola linea los tres ract por 
    //id de unidadaprendizajeimparteprofesor
    public ArrayList PAGCCompletoEIncompletoProgEd(ReporteAux reporteUI){
        
        ReporteAux reporte=new ReporteAux();
        
        init();
        
        reporte = fijarAtributosReporte(reporteUI,"ProgEd");
        
        ArrayList<ReporteAvanceAux> listaAuxPAGCCompleto;
        ArrayList<ReporteAvanceAux> listaAuxPAGCIncompleto;        
        ArrayList<ReporteAvanceAux> listaAux2=new ArrayList<ReporteAvanceAux>();
        
        ReporteAvanceAux reporteAvanceAux;
                
        //hace las consultas para Porcentaje Avance Global Completo
        //en una lista y Porcentaje Avance Global Incompleto(*PAGC*) en
        //otra lista
        listaAuxPAGCCompleto=PAGCCompletoProgEd(reporteUI);
        listaAuxPAGCIncompleto=PAGCProgEd(reporteUI);
        
        //une en la lista principal los Porcentaje Avance Global Completo
        for(ReporteAvanceAux ra: listaAuxPAGCCompleto){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Completo");            
            listaAux2.add(reporteAvanceAux);
            
        }
        
        //une en la misma lista principal los Porcentaje Avance 
        //Global Incompleto(*PAGC*)
        for(ReporteAvanceAux ra: listaAuxPAGCIncompleto){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Incompleto");            
            listaAux2.add(reporteAvanceAux);
            
        }
        
        return listaAux2;
    }
    
    //este metodo une los Porcentaje Avance Global Completo con Incompleto
    //por un solo numero de ract, *no en una sola linea los tres ract por 
    //id de unidadaprendizajeimparteprofesor
    public ArrayList PAGCCompletoEIncompletoAreaCon(ReporteAux reporteUI){
        
        ReporteAux reporte=new ReporteAux();
        
        init();
        
        reporte = fijarAtributosReporte(reporteUI,"AreaCon");
        
        ArrayList<ReporteAvanceAux> listaAuxPAGCCompleto;
        ArrayList<ReporteAvanceAux> listaAuxPAGCIncompleto;        
        ArrayList<ReporteAvanceAux> listaAux2=new ArrayList<ReporteAvanceAux>();
        
        ReporteAvanceAux reporteAvanceAux;
                
        //hace las consultas para Porcentaje Avance Global Completo
        //en una lista y Porcentaje Avance Global Incompleto(*PAGC*) en
        //otra lista
        listaAuxPAGCCompleto=PAGCCompletoAreaCon(reporteUI);
        listaAuxPAGCIncompleto=PAGCAreaCon(reporteUI);
                
        //une en la lista principal los Porcentaje Avance Global Completo
        for(ReporteAvanceAux ra: listaAuxPAGCCompleto){
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Completo");            
            listaAux2.add(reporteAvanceAux);
            
        }
        
        //une en la misma lista principal los Porcentaje Avance 
        //Global Incompleto(*PAGC*)
        for(ReporteAvanceAux ra: listaAuxPAGCIncompleto){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Incompleto");            
            listaAux2.add(reporteAvanceAux);
            
        }
        
        return listaAux2;
    }
    
    //este metodo une los Porcentaje Avance Global Completo con Incompleto
    //por un solo numero de ract, *no en una sola linea los tres ract por 
    //id de unidadaprendizajeimparteprofesor
    public ArrayList PAGCCompletoEIncompletoUAGrupo(ReporteAux reporteUI){
        
        ReporteAux reporte=new ReporteAux();
        
        init();
        
        reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
        
        ArrayList<ReporteAvanceAux> listaAuxPAGCCompleto;
        ArrayList<ReporteAvanceAux> listaAuxPAGCIncompleto;        
        ArrayList<ReporteAvanceAux> listaAux2=new ArrayList<ReporteAvanceAux>();
        
        ReporteAvanceAux reporteAvanceAux;
                
        //hace las consultas para Porcentaje Avance Global Completo
        //en una lista y Porcentaje Avance Global Incompleto(*PAGC*) en
        //otra lista
        listaAuxPAGCCompleto=PAGCCompletoUAGrupo(reporteUI);
        listaAuxPAGCIncompleto=PAGCUAGrupo(reporteUI);
        
        //une en la lista principal los Porcentaje Avance Global Completo
        for(ReporteAvanceAux ra: listaAuxPAGCCompleto){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Completo");            
            listaAux2.add(reporteAvanceAux);
            
        }
        
        //une en la misma lista principal los Porcentaje Avance 
        //Global Incompleto(*PAGC*)
        for(ReporteAvanceAux ra: listaAuxPAGCIncompleto){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Incompleto");            
            listaAux2.add(reporteAvanceAux);
            
        }
        return listaAux2;
    }
    
    //este metodo une los Porcentaje Avance Global Completo con Incompleto
    //por un solo numero de ract, *no en una sola linea los tres ract por 
    //id de unidadaprendizajeimparteprofesor
    public ArrayList PAGCCompletoEIncompletoProfGrupo(ReporteAux reporteUI){
        
        ReporteAux reporte=new ReporteAux();
        
        init();
        
        reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");
       
        ArrayList<ReporteAvanceAux> listaAuxPAGCCompleto;
        ArrayList<ReporteAvanceAux> listaAuxPAGCIncompleto;        
        ArrayList<ReporteAvanceAux> listaAux2=new ArrayList<ReporteAvanceAux>();
        
        ReporteAvanceAux reporteAvanceAux;
                
        //hace las consultas para Porcentaje Avance Global Completo
        //en una lista y Porcentaje Avance Global Incompleto(*PAGC*) en
        //otra lista
        listaAuxPAGCCompleto=PAGCCompletoProfGrupo(reporteUI);
        listaAuxPAGCIncompleto=PAGCProfGrupo(reporteUI);
        
        //une en la lista principal los Porcentaje Avance Global Completo
        for(ReporteAvanceAux ra: listaAuxPAGCCompleto){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Completo");            
            listaAux2.add(reporteAvanceAux);
            
        }
        
        //une en la misma lista principal los Porcentaje Avance 
        //Global Incompleto(*PAGC*)
        for(ReporteAvanceAux ra: listaAuxPAGCIncompleto){            
                        
            reporteAvanceAux=ra;
            reporteAvanceAux.setTipoReporteSelec("Incompleto");            
            listaAux2.add(reporteAvanceAux);
            
        }
        
        return listaAux2;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para A Tiempo(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Programa educativo segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList ATiempoProgEd(ReporteAux reporteUI){
        
        init();
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Entregados 
        //A Tiempo
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        int cont=0;        
        
        //toma esta clase solo los atributos que necesita que son
        //los que posteriormente le pasa al objeto reporte que necesita
        //inicializarse para que los atributos que no necesita sean ceros
        //o nulos y los ignore el metodo del dao
        setNumRact(reporteUI.getNumRact());                  
        setCescicloEscolar(reporteUI.getCescicloEscolar());          
                
        reporte=new ReporteAux();        
        
        reporte.setOp("ConfigSet");
        
        //tipo de consulta del delegate al dao obtiene las tablas desde configuración
        List<Configuracion> lista6=reporteAvanceContenidoTematicoDelegate.getConfiguracion(reporte);
        
         //lista para unir id de configuracion con el id correspondiente de calendario reporte
        List<Calendarioreporte> listaCalendario;                        
        
        Date fechaCorte=null;
        Date fechaLimite=null;
        
        //lista para unir id de calendarioreporte con el id correspondiente de calendarioreportetienealerta
        List<CalendarioreporteTieneAlerta> lista7;        
         
        //recorre la lista de configuracion y compara el ciclo escolar del objeto reporteUI que viene del
        //beanUI con el ciclo escolar que le corresponde a la consulta o criteria de configuración
        for(Configuracion con: lista6){
            if (con.getCicloescolar().getCescicloEscolar().equalsIgnoreCase(cescicloEscolar) ) {
               //busca el id de configuración en una lista con criteria o consulta de calendarioreporte
               listaCalendario=reporteAvanceContenidoTematicoDelegate.getCalendarioreporte(con.getConfechaInicioSemestre());       
                      
                 for (Calendarioreporte cr : listaCalendario) {
                    
                    reporte=new ReporteAux();
                    
                    reporte.setOp("CalendReportTieneAlerta");
                    
                    reporte.setCreid(cr.getCreid());
                    reporte.setCalnumeroReporte(numRact);
                    
                    //busca por numero de ract en calendarioreportetienealerta el numero correspondiente
                    //de id de la lista o criteria consultada de calendario reporte
                    //*todas estas uniones son para no usar los set de las tablas por medio de un
                    //query de join(en estas tres partes coincidan por el mismo id que las une)*
                    lista7 = reporteAvanceContenidoTematicoDelegate.getCalendarioreporteTieneAlerta(reporte);

                    if(lista7.size()>0){
                        
                    //toma la fecha correspondiente de todas las uniones correspondientes al
                    //numero ract con el ciclo escolar y plan de estudios correcto
                    fechaCorte = lista7.get(0).getCalendarioreporte().getCrefechaCorte();
                    fechaLimite = lista7.get(0).getCalendarioreporte().getCrefechaLimite();
                    
                    }
                }   
            }
        }        
                        
        reporte = new ReporteAux();               
      
        reporte = fijarAtributosReporte(reporteUI,"ProgEd");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("ATiempo");
        reporte.setFecha1(fechaLimite);
    
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);                
             
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
        
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;        
                
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
        
        listaAux=new ArrayList<ReporteAvanceAux>();
        int cont3;
        int cont4;
        
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance,fechaCorte,fechaLimite);
            listaAux.add(raAux);
            
            cont=0;
            auxPAvance=0;
       } 
        
       return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para A Tiempo(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Area de conocimiento segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList ATiempoAreaCon(ReporteAux reporteUI){
        
        init();
        
        int cont=0;
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Entregados 
        //A Tiempo
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();        
        
        //toma esta clase solo los atributos que necesita que son
        //los que posteriormente le pasa al objeto reporte que necesita
        //inicializarse para que los atributos que no necesita sean ceros
        //o nulos y los ignore el metodo del dao
        setNumRact(reporteUI.getNumRact());                  
        setCescicloEscolar(reporteUI.getCescicloEscolar());  
                
        reporte=new ReporteAux();
        
        reporte.setOp("ConfigSet");
        
        //tipo de consulta del delegate al dao obtiene las tablas desde configuración
        List<Configuracion> lista10=reporteAvanceContenidoTematicoDelegate.getConfiguracion(reporte);
        
        //lista para unir id de configuracion con el id correspondiente de calendario reporte
        List<Calendarioreporte> listaCalendario;
        
        Date fechaCorte=null;
        Date fechaLimite=null;
        
        //lista para unir id de calendarioreporte con el id correspondiente de calendarioreportetienealerta
        List<CalendarioreporteTieneAlerta> lista7;
        
        //recorre la lista de configuracion y compara el ciclo escolar del objeto reporteUI que viene del
        //beanUI con el ciclo escolar que le corresponde a la consulta o criteria de configuración
        for(Configuracion con: lista10){
            if (con.getCicloescolar().getCescicloEscolar().equalsIgnoreCase(cescicloEscolar) ) {
                //busca el id de configuración en una lista con criteria o consulta de calendarioreporte
                listaCalendario=reporteAvanceContenidoTematicoDelegate.getCalendarioreporte(con.getConfechaInicioSemestre());
       
               
                for (Calendarioreporte cr : listaCalendario) {
                    
                    reporte=new ReporteAux();
                    
                    reporte.setOp("CalendReportTieneAlerta");
                    
                    reporte.setCreid(cr.getCreid());
                    reporte.setCalnumeroReporte(numRact);
               
                    //busca por numero de ract en calendarioreportetienealerta el numero correspondiente
                    //de id de la lista o criteria consultada de calendario reporte
                    //*todas estas uniones son para no usar los set de las tablas por medio de un
                    //query de join(en estas tres partes coincidan por el mismo id que las une)*
                    lista7 =reporteAvanceContenidoTematicoDelegate.getCalendarioreporteTieneAlerta(reporte);

                    if(lista7.size()>0){
                        
                    //toma la fecha correspondiente de todas las uniones correspondientes al
                    //numero ract con el ciclo escolar y plan de estudios correcto
                    fechaCorte = lista7.get(0).getCalendarioreporte().getCrefechaCorte();
                    fechaLimite = lista7.get(0).getCalendarioreporte().getCrefechaLimite();
                    
                    }
                }   
            }
        }
        
        reporte = new ReporteAux();
        
        reporte = fijarAtributosReporte(reporteUI,"AreaCon");
                
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("ATiempo");
        reporte.setFecha1(fechaLimite);

        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);        
        
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
        
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
        listaAux=new ArrayList<ReporteAvanceAux>();
        
        int cont3;
        int cont4;
        
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance,fechaCorte,fechaLimite);
            listaAux.add(raAux);
            }
            
            cont=0;
            auxPAvance=0;
       } 
        
        return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para A Tiempo(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Unidad de aprendizaje con Grupo segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList ATiempoUAGrupo(ReporteAux reporteUI){
        
        init();
        
        int cont=0;        
        
        //toma esta clase solo los atributos que necesita que son
        //los que posteriormente le pasa al objeto reporte que necesita
        //inicializarse para que los atributos que no necesita sean ceros
        //o nulos y los ignore el metodo del dao
        setNumRact(reporteUI.getNumRact());                  
        setCescicloEscolar(reporteUI.getCescicloEscolar());  
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Entregados 
        //A Tiempo
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        reporte.setOp("ConfigSet");
        
        //tipo de consulta del delegate al dao obtiene las tablas desde configuración
        List<Configuracion> lista11=reporteAvanceContenidoTematicoDelegate.getConfiguracion(reporte);        
        
        //lista para unir id de configuracion con el id correspondiente de calendario reporte
        List<Calendarioreporte> listaCalendario;        
        
        Date fechaCorte=null;
        Date fechaLimite=null;
        
        //lista para unir id de calendarioreporte con el id correspondiente de calendarioreportetienealerta
        List<CalendarioreporteTieneAlerta> lista7;         
        
        //recorre la lista de configuracion y compara el ciclo escolar del objeto reporteUI que viene del
        //beanUI con el ciclo escolar que le corresponde a la consulta o criteria de configuración
        for(Configuracion con: lista11){
            if (con.getCicloescolar().getCescicloEscolar().equalsIgnoreCase(cescicloEscolar) ) {
                //busca el id de configuración en una lista con criteria o consulta de calendarioreporte
                listaCalendario=reporteAvanceContenidoTematicoDelegate.getCalendarioreporte(con.getConfechaInicioSemestre());
       
                for (Calendarioreporte cr : listaCalendario) {
                    
                    reporte=new ReporteAux();
                    
                    reporte.setOp("CalendReportTieneAlerta");
                    
                    reporte.setCreid(cr.getCreid());
                    reporte.setCalnumeroReporte(numRact);

                    //busca por numero de ract en calendarioreportetienealerta el numero correspondiente
                    //de id de la lista o criteria consultada de calendario reporte
                    //*todas estas uniones son para no usar los set de las tablas por medio de un
                    //query de join(en estas tres partes coincidan por el mismo id que las une)*
                    lista7 =reporteAvanceContenidoTematicoDelegate.getCalendarioreporteTieneAlerta(reporte);

                    if(lista7.size()>0){
                        
                    //toma la fecha correspondiente de todas las uniones correspondientes al
                    //numero ract con el ciclo escolar y plan de estudios correcto
                    fechaCorte = lista7.get(0).getCalendarioreporte().getCrefechaCorte();
                    fechaLimite = lista7.get(0).getCalendarioreporte().getCrefechaLimite();                                        
                    
                    }
                }   
            }
        }
        
        reporte = new ReporteAux();  
        
        reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
      
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("ATiempo");
        reporte.setFecha1(fechaLimite);
        
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);        
        
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();

        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;        
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
        listaAux=new ArrayList<ReporteAvanceAux>();
        int cont3;
        int cont4;
        
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance,fechaCorte,fechaLimite);
            listaAux.add(raAux);
            }
            
            cont=0;
            auxPAvance=0;
       }         
             
        return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para A Tiempo(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Profesor con Grupo  segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList ATiempoProfGrupo(ReporteAux reporteUI){
        
        init();
        
        int cont=0;        
        
        //toma esta clase solo los atributos que necesita que son
        //los que posteriormente le pasa al objeto reporte que necesita
        //inicializarse para que los atributos que no necesita sean ceros
        //o nulos y los ignore el metodo del dao
        setNumRact(reporteUI.getNumRact());                  
        setCescicloEscolar(reporteUI.getCescicloEscolar());  
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Entregados 
        //A Tiempo
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        reporte.setOp("ConfigSet");
        
        //tipo de consulta del delegate al dao obtiene las tablas desde configuración
        List<Configuracion> lista11=reporteAvanceContenidoTematicoDelegate.getConfiguracion(reporte);
        
        //lista para unir id de configuracion con el id correspondiente de calendario reporte
        List<Calendarioreporte> listaCalendario;        
                
        Date fechaCorte=null;
        Date fechaLimite=null;
        
        //lista para unir id de calendarioreporte con el id correspondiente de calendarioreportetienealerta
        List<CalendarioreporteTieneAlerta> lista7;

        //recorre la lista de configuracion y compara el ciclo escolar del objeto reporteUI que viene del
        //beanUI con el ciclo escolar que le corresponde a la consulta o criteria de configuración
        for(Configuracion con: lista11){
            if (con.getCicloescolar().getCescicloEscolar().equalsIgnoreCase(cescicloEscolar) ) {
                //busca el id de configuración en una lista con criteria o consulta de calendarioreporte
                listaCalendario=reporteAvanceContenidoTematicoDelegate.getCalendarioreporte(con.getConfechaInicioSemestre());
       
                for (Calendarioreporte cr : listaCalendario) {
                    
                    reporte=new ReporteAux();
                    
                    reporte.setOp("CalendReportTieneAlerta");
        
                    reporte.setCreid(cr.getCreid());
                    reporte.setCalnumeroReporte(numRact);
       
                    //busca por numero de ract en calendarioreportetienealerta el numero correspondiente
                    //de id de la lista o criteria consultada de calendario reporte
                    //*todas estas uniones son para no usar los set de las tablas por medio de un
                    //query de join(en estas tres partes coincidan por el mismo id que las une)*
                    lista7 =reporteAvanceContenidoTematicoDelegate.getCalendarioreporteTieneAlerta(reporte);

                    if(lista7.size()>0){
                        
                    //toma la fecha correspondiente de todas las uniones correspondientes al
                    //numero ract con el ciclo escolar y plan de estudios correcto
                    fechaCorte = lista7.get(0).getCalendarioreporte().getCrefechaCorte();
                    fechaLimite = lista7.get(0).getCalendarioreporte().getCrefechaLimite();                    
                    
                    }
                }   
            }
        }
        
        reporte = new ReporteAux();
        
        reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("ATiempo");
        reporte.setFecha1(fechaLimite);
        
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);        

        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
        
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;        
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
        listaAux=new ArrayList<ReporteAvanceAux>();
        int cont3;
        int cont4;
        
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance,fechaCorte,fechaLimite);
            listaAux.add(raAux);
            }
            
            cont=0;
            auxPAvance=0;
       }         
              
        return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para "Despues de fecha límite"(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Programa educativo segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList FueraTiempoProgEd(ReporteAux reporteUI){
        
        init();
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Entregados 
        //Despues de fecha limite
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        int cont=0;        
        
        //toma esta clase solo los atributos que necesita que son
        //los que posteriormente le pasa al objeto reporte que necesita
        //inicializarse para que los atributos que no necesita sean ceros
        //o nulos y los ignore el metodo del dao
        setNumRact(reporteUI.getNumRact());                  
        setCescicloEscolar(reporteUI.getCescicloEscolar());          
        
        System.out.println(" numract: "+numRact+"  cescicloEscolar: "+cescicloEscolar);
        
        reporte=new ReporteAux();
        
        reporte.setOp("ConfigSet");
        
        //tipo de consulta del delegate al dao obtiene las tablas desde configuración
        List<Configuracion> lista6=reporteAvanceContenidoTematicoDelegate.getConfiguracion(reporte);        
        
        //lista para unir id de configuracion con el id correspondiente de calendario reporte
        List<Calendarioreporte> listaCalendario;                        
        
        Date fechaCorte=null;
        Date fechaLimite=null;
        
        System.out.println("************* Inicio 1 ************");   
        
        //lista para unir id de calendarioreporte con el id correspondiente de calendarioreportetienealerta
        List<CalendarioreporteTieneAlerta> lista7;        
                        
        //recorre la lista de configuracion y compara el ciclo escolar del objeto reporteUI que viene del
        //beanUI con el ciclo escolar que le corresponde a la consulta o criteria de configuración
        for(Configuracion con: lista6){
            System.out.println("************* Inicio 1.1 ************");
            if (con.getCicloescolar().getCescicloEscolar().equalsIgnoreCase(cescicloEscolar) ) {
                //busca el id de configuración en una lista con criteria o consulta de calendarioreporte
                listaCalendario=reporteAvanceContenidoTematicoDelegate.getCalendarioreporte(con.getConfechaInicioSemestre());       
                   System.out.println("************* Inicio 2 ************");   
                for (Calendarioreporte cr : listaCalendario) {
                    
                    reporte=new ReporteAux();
                    
                    reporte.setOp("CalendReportTieneAlerta");
                    
                    reporte.setCreid(cr.getCreid());
                    reporte.setCalnumeroReporte(numRact);

                    //busca por numero de ract en calendarioreportetienealerta el numero correspondiente
                    //de id de la lista o criteria consultada de calendario reporte
                    //*todas estas uniones son para no usar los set de las tablas por medio de un
                    //query de join(en estas tres partes coincidan por el mismo id que las une)*
                    lista7 = reporteAvanceContenidoTematicoDelegate.getCalendarioreporteTieneAlerta(reporte);

                    System.out.println("************* Inicio 3 ************");   
                    
                    if(lista7.size()>0){
                        
                    //toma la fecha correspondiente de todas las uniones correspondientes al
                    //numero ract con el ciclo escolar y plan de estudios correcto    
                    fechaCorte = lista7.get(0).getCalendarioreporte().getCrefechaCorte();
                    fechaLimite = lista7.get(0).getCalendarioreporte().getCrefechaLimite();
                    
                    System.out.println("************* Inicio 4 ************");   
                    
                    }
                }   
            }
        }        
                        
        reporte = new ReporteAux();  
        
        reporte = fijarAtributosReporte(reporteUI,"ProgEd");
            
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("FueraTiempo");
        reporte.setFecha1(fechaLimite);
       
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);                
    
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
        
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;        
                
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
        listaAux=new ArrayList<ReporteAvanceAux>();
        int cont3;
        int cont4;
        
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance,fechaCorte,fechaLimite);
            listaAux.add(raAux);
            
            System.out.println("************* Inicio 5 ************");   
            
            cont=0;
            auxPAvance=0;
       } 
        
       return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para "Despues de fecha límite"(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Area de conocimiento segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList FueraTiempoAreaCon(ReporteAux reporteUI){        
        init();
        
        int cont=0;
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Entregados 
        //Despues de fecha limite
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();        
        
        //toma esta clase solo los atributos que necesita que son
        //los que posteriormente le pasa al objeto reporte que necesita
        //inicializarse para que los atributos que no necesita sean ceros
        //o nulos y los ignore el metodo del dao
        setNumRact(reporteUI.getNumRact());                  
        setCescicloEscolar(reporteUI.getCescicloEscolar());  
                
        reporte=new ReporteAux();
        
        reporte.setOp("ConfigSet");
        
        //tipo de consulta del delegate al dao obtiene las tablas desde configuración
        List<Configuracion> lista10=reporteAvanceContenidoTematicoDelegate.getConfiguracion(reporte);
        
        //lista para unir id de configuracion con el id correspondiente de calendario reporte
        List<Calendarioreporte> listaCalendario;
        
        Date fechaCorte=null;
        Date fechaLimite=null;
        
        //lista para unir id de calendarioreporte con el id correspondiente de calendarioreportetienealerta
        List<CalendarioreporteTieneAlerta> lista7;
        
        //recorre la lista de configuracion y compara el ciclo escolar del objeto reporteUI que viene del
        //beanUI con el ciclo escolar que le corresponde a la consulta o criteria de configuración
        for(Configuracion con: lista10){
            if (con.getCicloescolar().getCescicloEscolar().equalsIgnoreCase(cescicloEscolar) ) {
                //busca el id de configuración en una lista con criteria o consulta de calendarioreporte
                listaCalendario=reporteAvanceContenidoTematicoDelegate.getCalendarioreporte(con.getConfechaInicioSemestre());       
               
                for (Calendarioreporte cr : listaCalendario) {
                    
                    reporte=new ReporteAux();
                    
                    reporte.setOp("CalendReportTieneAlerta");
                    
                    reporte.setCreid(cr.getCreid());
                    reporte.setCalnumeroReporte(numRact);
               
                    //busca por numero de ract en calendarioreportetienealerta el numero correspondiente
                    //de id de la lista o criteria consultada de calendario reporte
                    //*todas estas uniones son para no usar los set de las tablas por medio de un
                    //query de join(en estas tres partes coincidan por el mismo id que las une)*
                    lista7 =reporteAvanceContenidoTematicoDelegate.getCalendarioreporteTieneAlerta(reporte);

                    if(lista7.size()>0){
        
                    //toma la fecha correspondiente de todas las uniones correspondientes al
                    //numero ract con el ciclo escolar y plan de estudios correcto    
                    fechaCorte = lista7.get(0).getCalendarioreporte().getCrefechaCorte();
                    fechaLimite = lista7.get(0).getCalendarioreporte().getCrefechaLimite();
                    
                    }
                }   
            }
        }                              
        
        reporte = new ReporteAux();
        
        reporte = fijarAtributosReporte(reporteUI,"AreaCon");
                
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("FueraTiempo");
        reporte.setFecha1(fechaLimite);
        
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);                
               
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
        
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;        
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
        listaAux=new ArrayList<ReporteAvanceAux>();
        
        int cont3;
        int cont4;
        
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance,fechaCorte,fechaLimite);
            listaAux.add(raAux);
            }
            
            cont=0;
            auxPAvance=0;
       } 
        
        return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para "Despues de fecha límite"(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Unidad de aprendizaje con Grupo segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList FueraTiempoUAGrupo(ReporteAux reporteUI){        
        init();
        
        int cont=0;        
        
        //toma esta clase solo los atributos que necesita que son
        //los que posteriormente le pasa al objeto reporte que necesita
        //inicializarse para que los atributos que no necesita sean ceros
        //o nulos y los ignore el metodo del dao
        setNumRact(reporteUI.getNumRact());                  
        setCescicloEscolar(reporteUI.getCescicloEscolar());  
      
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Entregados 
        //Despues de fecha limite
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        reporte.setOp("ConfigSet");
        
        //tipo de consulta del delegate al dao obtiene las tablas desde configuración
        List<Configuracion> lista11=reporteAvanceContenidoTematicoDelegate.getConfiguracion(reporte);        
        
        //lista para unir id de configuracion con el id correspondiente de calendario reporte
        List<Calendarioreporte> listaCalendario;        
        
        Date fechaCorte=null;
        Date fechaLimite=null;
        
        //lista para unir id de calendarioreporte con el id correspondiente de calendarioreportetienealerta
        List<CalendarioreporteTieneAlerta> lista7;         
        
        //recorre la lista de configuracion y compara el ciclo escolar del objeto reporteUI que viene del
        //beanUI con el ciclo escolar que le corresponde a la consulta o criteria de configuración
        for(Configuracion con: lista11){
            if (con.getCicloescolar().getCescicloEscolar().equalsIgnoreCase(cescicloEscolar) ) {
                //busca el id de configuración en una lista con criteria o consulta de calendarioreporte
                listaCalendario=reporteAvanceContenidoTematicoDelegate.getCalendarioreporte(con.getConfechaInicioSemestre());
       
                for (Calendarioreporte cr : listaCalendario) {
                    
                    reporte=new ReporteAux();
                    
                    reporte.setOp("CalendReportTieneAlerta");
                    
                    reporte.setCreid(cr.getCreid());
                    reporte.setCalnumeroReporte(numRact);

                    //busca por numero de ract en calendarioreportetienealerta el numero correspondiente
                    //de id de la lista o criteria consultada de calendario reporte
                    //*todas estas uniones son para no usar los set de las tablas por medio de un
                    //query de join(en estas tres partes coincidan por el mismo id que las une)*
                    lista7 =reporteAvanceContenidoTematicoDelegate.getCalendarioreporteTieneAlerta(reporte);

                    if(lista7.size()>0){
                        
                    //toma la fecha correspondiente de todas las uniones correspondientes al
                    //numero ract con el ciclo escolar y plan de estudios correcto    
                    fechaCorte = lista7.get(0).getCalendarioreporte().getCrefechaCorte();
                    fechaLimite = lista7.get(0).getCalendarioreporte().getCrefechaLimite();                    
                                        
                    }
                }   
            }
        }        
                
        reporte = new ReporteAux();        
        
        reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
      
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("FueraTiempo");
        reporte.setFecha1(fechaLimite);
        
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);                
               
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();

        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;        
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
        listaAux=new ArrayList<ReporteAvanceAux>();
        int cont3;
        int cont4;
        
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance,fechaCorte,fechaLimite);
            listaAux.add(raAux);
            }
            
            cont=0;
            auxPAvance=0;
       }         
              
        return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para "Despues de fecha límite"(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Profesor con Grupo segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList FueraTiempoProfGrupo(ReporteAux reporteUI){
        
        init();
        
        int cont=0;        
        
        //toma esta clase solo los atributos que necesita que son
        //los que posteriormente le pasa al objeto reporte que necesita
        //inicializarse para que los atributos que no necesita sean ceros
        //o nulos y los ignore el metodo del dao
        setNumRact(reporteUI.getNumRact());                  
        setCescicloEscolar(reporteUI.getCescicloEscolar());  
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Entregados 
        //Despues de fecha limite
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        reporte.setOp("ConfigSet");
        
        //tipo de consulta del delegate al dao obtiene las tablas desde configuración
        List<Configuracion> lista11=reporteAvanceContenidoTematicoDelegate.getConfiguracion(reporte);        
        
        //lista para unir id de configuracion con el id correspondiente de calendario reporte
        List<Calendarioreporte> listaCalendario;        
                
        Date fechaCorte=null;
        Date fechaLimite=null;
        
        //lista para unir id de calendarioreporte con el id correspondiente de calendarioreportetienealerta
        List<CalendarioreporteTieneAlerta> lista7;
        
        //recorre la lista de configuracion y compara el ciclo escolar del objeto reporteUI que viene del
        //beanUI con el ciclo escolar que le corresponde a la consulta o criteria de configuración
        for(Configuracion con: lista11){
            if (con.getCicloescolar().getCescicloEscolar().equalsIgnoreCase(cescicloEscolar) ) {
                //busca el id de configuración en una lista con criteria o consulta de calendarioreporte
                listaCalendario=reporteAvanceContenidoTematicoDelegate.getCalendarioreporte(con.getConfechaInicioSemestre());
       
                for (Calendarioreporte cr : listaCalendario) {
                    
                    reporte=new ReporteAux();
                    
                    reporte.setOp("CalendReportTieneAlerta");
        
                    reporte.setCreid(cr.getCreid());
                    reporte.setCalnumeroReporte(numRact);
       
                    //busca por numero de ract en calendarioreportetienealerta el numero correspondiente
                    //de id de la lista o criteria consultada de calendario reporte
                    //*todas estas uniones son para no usar los set de las tablas por medio de un
                    //query de join(en estas tres partes coincidan por el mismo id que las une)*
                    lista7 =reporteAvanceContenidoTematicoDelegate.getCalendarioreporteTieneAlerta(reporte);

                    if(lista7.size()>0){
                        
                    //toma la fecha correspondiente de todas las uniones correspondientes al
                    //numero ract con el ciclo escolar y plan de estudios correcto    
                    fechaCorte = lista7.get(0).getCalendarioreporte().getCrefechaCorte();
                    fechaLimite = lista7.get(0).getCalendarioreporte().getCrefechaLimite();                    
                    
                    }
                }   
            }
        }
        
        reporte = new ReporteAux();
        
        reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("FueraTiempo");
        reporte.setFecha1(fechaLimite);
        
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);        
                       
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
        
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;        
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
        listaAux=new ArrayList<ReporteAvanceAux>();
        int cont3;
        int cont4;
        
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance,fechaCorte,fechaLimite);
            listaAux.add(raAux);
            }
            
            cont=0;
            auxPAvance=0;
       }         
        
        return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para "En fecha límite"(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Programa educativo segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList EnFechaLimiteTiempoProgEd(ReporteAux reporteUI){
        
        init();
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Entregados 
        //En fecha limite
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        int cont=0;        
        
        //toma esta clase solo los atributos que necesita que son
        //los que posteriormente le pasa al objeto reporte que necesita
        //inicializarse para que los atributos que no necesita sean ceros
        //o nulos y los ignore el metodo del dao
        setNumRact(reporteUI.getNumRact());                  
        setCescicloEscolar(reporteUI.getCescicloEscolar());          
                
        reporte=new ReporteAux();
        
        reporte.setOp("ConfigSet");
        
        //tipo de consulta del delegate al dao obtiene las tablas desde configuración
        List<Configuracion> lista6=reporteAvanceContenidoTematicoDelegate.getConfiguracion(reporte);        
         
        //lista para unir id de configuracion con el id correspondiente de calendario reporte
        List<Calendarioreporte> listaCalendario;                        
        
        Date fechaCorte=null;
        Date fechaLimite=null;
        
        //lista para unir id de calendarioreporte con el id correspondiente de calendarioreportetienealerta
        List<CalendarioreporteTieneAlerta> lista7;        
                        
        //recorre la lista de configuracion y compara el ciclo escolar del objeto reporteUI que viene del
        //beanUI con el ciclo escolar que le corresponde a la consulta o criteria de configuración
        for(Configuracion con: lista6){
            if (con.getCicloescolar().getCescicloEscolar().equalsIgnoreCase(cescicloEscolar) ) {
                //busca el id de configuración en una lista con criteria o consulta de calendarioreporte
                listaCalendario=reporteAvanceContenidoTematicoDelegate.getCalendarioreporte(con.getConfechaInicioSemestre());       
                                     
                for (Calendarioreporte cr : listaCalendario) {
                    
                    reporte=new ReporteAux();
                    
                    reporte.setOp("CalendReportTieneAlerta");
                    
                    reporte.setCreid(cr.getCreid());
                    reporte.setCalnumeroReporte(numRact);
            
                    //busca por numero de ract en calendarioreportetienealerta el numero correspondiente
                    //de id de la lista o criteria consultada de calendario reporte
                    //*todas estas uniones son para no usar los set de las tablas por medio de un
                    //query de join(en estas tres partes coincidan por el mismo id que las une)*
                    lista7 = reporteAvanceContenidoTematicoDelegate.getCalendarioreporteTieneAlerta(reporte);

                    if(lista7.size()>0){
                        
                    //toma la fecha correspondiente de todas las uniones correspondientes al
                    //numero ract con el ciclo escolar y plan de estudios correcto    
                    fechaCorte = lista7.get(0).getCalendarioreporte().getCrefechaCorte();
                    fechaLimite = lista7.get(0).getCalendarioreporte().getCrefechaLimite();
                    
                    }
                }   
            }
        }        
                        
        reporte = new ReporteAux();         
        
        reporte = fijarAtributosReporte(reporteUI,"ProgEd");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("EnFechaLimite");
        reporte.setFecha1(fechaLimite);
        
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);        
        
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
        
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;        
                
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
        listaAux=new ArrayList<ReporteAvanceAux>();
        int cont3;
        int cont4;
        
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance,fechaCorte,fechaLimite);
            listaAux.add(raAux);
            
            cont=0;
            auxPAvance=0;
       } 
        
       return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para "En fecha límite"(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Area de conocimiento segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList EnFechaLimiteTiempoAreaCon(ReporteAux reporteUI){
        
        init();
        
        int cont=0;
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Entregados 
        //En fecha limite
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();        
       
        //toma esta clase solo los atributos que necesita que son
        //los que posteriormente le pasa al objeto reporte que necesita
        //inicializarse para que los atributos que no necesita sean ceros
        //o nulos y los ignore el metodo del dao
        setNumRact(reporteUI.getNumRact());                  
        setCescicloEscolar(reporteUI.getCescicloEscolar());  
                
        reporte=new ReporteAux();
        
        reporte.setOp("ConfigSet");
        
        //tipo de consulta del delegate al dao obtiene las tablas desde configuración
        List<Configuracion> lista10=reporteAvanceContenidoTematicoDelegate.getConfiguracion(reporte);        
        
        //lista para unir id de configuracion con el id correspondiente de calendario reporte
        List<Calendarioreporte> listaCalendario;
        
        Date fechaCorte=null;
        Date fechaLimite=null;
        
        //lista para unir id de calendarioreporte con el id correspondiente de calendarioreportetienealerta
        List<CalendarioreporteTieneAlerta> lista7;
        
        //recorre la lista de configuracion y compara el ciclo escolar del objeto reporteUI que viene del
        //beanUI con el ciclo escolar que le corresponde a la consulta o criteria de configuración
        for(Configuracion con: lista10){
            if (con.getCicloescolar().getCescicloEscolar().equalsIgnoreCase(cescicloEscolar) ) {
                //busca el id de configuración en una lista con criteria o consulta de calendarioreporte
                listaCalendario=reporteAvanceContenidoTematicoDelegate.getCalendarioreporte(con.getConfechaInicioSemestre());       
               
                for (Calendarioreporte cr : listaCalendario) {
                    
                    reporte=new ReporteAux();
                    
                    reporte.setOp("CalendReportTieneAlerta");
                    
                    reporte.setCreid(cr.getCreid());
                    reporte.setCalnumeroReporte(numRact);
        
                    //busca por numero de ract en calendarioreportetienealerta el numero correspondiente
                    //de id de la lista o criteria consultada de calendario reporte
                    //*todas estas uniones son para no usar los set de las tablas por medio de un
                    //query de join(en estas tres partes coincidan por el mismo id que las une)*
                    lista7 =reporteAvanceContenidoTematicoDelegate.getCalendarioreporteTieneAlerta(reporte);

                    if(lista7.size()>0){
                        
                    //toma la fecha correspondiente de todas las uniones correspondientes al
                    //numero ract con el ciclo escolar y plan de estudios correcto    
                    fechaCorte = lista7.get(0).getCalendarioreporte().getCrefechaCorte();
                    fechaLimite = lista7.get(0).getCalendarioreporte().getCrefechaLimite();
                    
                    }
                }   
            }
        }                              
        
        reporte = new ReporteAux();
        
        reporte = fijarAtributosReporte(reporteUI,"AreaCon");
                
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("EnFechaLimite");
        reporte.setFecha1(fechaLimite);

        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);                
               
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
        
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;        
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
        listaAux=new ArrayList<ReporteAvanceAux>();
        
        int cont3;
        int cont4;
        
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance,fechaCorte,fechaLimite);
            listaAux.add(raAux);
            }
            
            cont=0;
            auxPAvance=0;
       } 
        
        return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para "En fecha límite"(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Unidad de aprendizaje con Grupo segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList EnFechaLimiteTiempoUAGrupo(ReporteAux reporteUI){
        
        init();
        
        int cont=0;        
        
        //toma esta clase solo los atributos que necesita que son
        //los que posteriormente le pasa al objeto reporte que necesita
        //inicializarse para que los atributos que no necesita sean ceros
        //o nulos y los ignore el metodo del dao
        setNumRact(reporteUI.getNumRact());                  
        setCescicloEscolar(reporteUI.getCescicloEscolar());  
        
        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Entregados 
        //En fecha limite
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        reporte.setOp("ConfigSet");
        
        //tipo de consulta del delegate al dao obtiene las tablas desde configuración
        List<Configuracion> lista11=reporteAvanceContenidoTematicoDelegate.getConfiguracion(reporte);        
        
        //lista para unir id de configuracion con el id correspondiente de calendario reporte
        List<Calendarioreporte> listaCalendario;        
        
        Date fechaCorte=null;
        Date fechaLimite=null;
        
        //lista para unir id de calendarioreporte con el id correspondiente de calendarioreportetienealerta
        List<CalendarioreporteTieneAlerta> lista7;         
        
        //recorre la lista de configuracion y compara el ciclo escolar del objeto reporteUI que viene del
        //beanUI con el ciclo escolar que le corresponde a la consulta o criteria de configuración
        for(Configuracion con: lista11){
            if (con.getCicloescolar().getCescicloEscolar().equalsIgnoreCase(cescicloEscolar) ) {
                //busca el id de configuración en una lista con criteria o consulta de calendarioreporte
                listaCalendario=reporteAvanceContenidoTematicoDelegate.getCalendarioreporte(con.getConfechaInicioSemestre());
       
                for (Calendarioreporte cr : listaCalendario) {
                    
                    reporte=new ReporteAux();
                    
                    reporte.setOp("CalendReportTieneAlerta");
                    
                    reporte.setCreid(cr.getCreid());
                    reporte.setCalnumeroReporte(numRact);

                    //busca por numero de ract en calendarioreportetienealerta el numero correspondiente
                    //de id de la lista o criteria consultada de calendario reporte
                    //*todas estas uniones son para no usar los set de las tablas por medio de un
                    //query de join(en estas tres partes coincidan por el mismo id que las une)*
                    lista7 =reporteAvanceContenidoTematicoDelegate.getCalendarioreporteTieneAlerta(reporte);

                    if(lista7.size()>0){
                        
                    //toma la fecha correspondiente de todas las uniones correspondientes al
                    //numero ract con el ciclo escolar y plan de estudios correcto    
                    fechaCorte = lista7.get(0).getCalendarioreporte().getCrefechaCorte();
                    fechaLimite = lista7.get(0).getCalendarioreporte().getCrefechaLimite();                    
                                        
                    }
                }   
            }
        }        
                
        reporte = new ReporteAux();
        
        reporte = fijarAtributosReporte(reporteUI,"UAGrupo");
      
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("EnFechaLimite");
        reporte.setFecha1(fechaLimite);
        
        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);                
               
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();

        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;        
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
        listaAux=new ArrayList<ReporteAvanceAux>();
        int cont3;
        int cont4;
        
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance,fechaCorte,fechaLimite);
            listaAux.add(raAux);
            }
            
            cont=0;
            auxPAvance=0;
       }         
        
        return listaAux;
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para "En fecha límite"(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Profesor con Grupo segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public ArrayList EnFechaLimiteTiempoProfGrupo(ReporteAux reporteUI){
        
        init();
        
        int cont=0;        
        
        //toma esta clase solo los atributos que necesita que son
        //los que posteriormente le pasa al objeto reporte que necesita
        //inicializarse para que los atributos que no necesita sean ceros
        //o nulos y los ignore el metodo del dao
        setNumRact(reporteUI.getNumRact());                  
        setCescicloEscolar(reporteUI.getCescicloEscolar());  

        //objeto que se pasa al delegate y este por las capas al dao
        //que contiene los atributos necesarios para la consulta de Entregados 
        //En fecha limite
        //con el criteri(restricciones), necesita inicializarse asi los ceros y
        //nulos de los atributos que no van a necesitar
        ReporteAux reporte=new ReporteAux();
        
        reporte.setOp("ConfigSet");
        
        //tipo de consulta del delegate al dao obtiene las tablas desde configuración
        List<Configuracion> lista11=reporteAvanceContenidoTematicoDelegate.getConfiguracion(reporte);        
        
        //lista para unir id de configuracion con el id correspondiente de calendario reporte
        List<Calendarioreporte> listaCalendario;        
                
        Date fechaCorte=null;
        Date fechaLimite=null;
        
        //lista para unir id de calendarioreporte con el id correspondiente de calendarioreportetienealerta
        List<CalendarioreporteTieneAlerta> lista7;
        
        //recorre la lista de configuracion y compara el ciclo escolar del objeto reporteUI que viene del
        //beanUI con el ciclo escolar que le corresponde a la consulta o criteria de configuración
        for(Configuracion con: lista11){
            if (con.getCicloescolar().getCescicloEscolar().equalsIgnoreCase(cescicloEscolar) ) {
                //busca el id de configuración en una lista con criteria o consulta de calendarioreporte
                listaCalendario=reporteAvanceContenidoTematicoDelegate.getCalendarioreporte(con.getConfechaInicioSemestre());
       
                for (Calendarioreporte cr : listaCalendario) {
                    
                    reporte=new ReporteAux();
                    
                    reporte.setOp("CalendReportTieneAlerta");
        
                    reporte.setCreid(cr.getCreid());
                    reporte.setCalnumeroReporte(numRact);
       
                    //busca por numero de ract en calendarioreportetienealerta el numero correspondiente
                    //de id de la lista o criteria consultada de calendario reporte
                    //*todas estas uniones son para no usar los set de las tablas por medio de un
                    //query de join(en estas tres partes coincidan por el mismo id que las une)*
                    lista7 =reporteAvanceContenidoTematicoDelegate.getCalendarioreporteTieneAlerta(reporte);

                    if(lista7.size()>0){
        
                    //toma la fecha correspondiente de todas las uniones correspondientes al
                    //numero ract con el ciclo escolar y plan de estudios correcto    
                    fechaCorte = lista7.get(0).getCalendarioreporte().getCrefechaCorte();
                    fechaLimite = lista7.get(0).getCalendarioreporte().getCrefechaLimite();                    
                    
                    }
                }   
            }
        }
        
        reporte = new ReporteAux();
        
        reporte = fijarAtributosReporte(reporteUI,"ProfGrupo");
        
        //se pasan al objeto reporte los atributos que necesita segun 
        //tipo consulta de criteria en el dao
        reporte.setOp("ATiempoYNo");
        reporte.setTipo("EnFechaLimite");
        reporte.setFecha1(fechaLimite);

        //llama el metodo desde el delegate hacia el dao para que realice la consulta
        //de criteria segun los atributos que se le paso(restricciones) y este regresa
        //una lista de tipo Reporteavancecontenidotematico que une 11 tablas con los 
        //valores para presentar en el reporte en excel se obtienen estos valores con
        //gets de una tabla a otra siguiendo el camino desde el primera a la enesima
        //que estan unidas por este join
        List<Reporteavancecontenidotematico> lista3=reporteAvanceContenidoTematicoDelegate.getReporteAvanceContenidoTematico(reporte);                
               
        //recupera cuantos valores(tuplas) encontro en la lista anterior
        int cont2=lista3.size();
        
        float auxPAvance=0;
        int auxUipid;
        int auxNumRact;        
        
        //esta objeto raAux sirve para obtener en un ArrayList que devuelve al final
        //este metodo donde ademas de la lista normal de esta consulta principal
        //anterior se guardan otras variable o datos importantes por tupla o una
        //sola linea para presentarlo asi en la vista o excel
        ReporteAvanceAux raAux;
        listaAux=new ArrayList<ReporteAvanceAux>();
        int cont3;
        int cont4;
        
        List<Areaconocimiento> areaCon;
                
        //este ciclo cuenta cuantos valores unicos hay por el id de
        //unidadaprendizajeimparteprofesor(union de 3 id: profesor,
        //unidad de aprendizaje y grupo) es la misma consulta y lista 
        //al delegate anterior pero cuenta esta restriccion cuantos 
        //entregados hay por cada id unidadaprendizajeimparteprofesor
        for(Reporteavancecontenidotematico reporteAvance : lista3){          

            areaCon=reporteAvanceContenidoTematicoDelegate.getAreaConocimiento(reporteAvance.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave());
            //une en un ArrayList, la lista reporteAvance, cont2: cuantos valores de la
            //consulta normal, cont: cuantos valores del id unidadaprendizajeimparteprofesor
            //y *auxPAvance: cuanto fue el mayor porcentaje entregado en el mayor ract*(aqui no se usa)
            if(reporte.getAcoclave() == areaCon.get(0).getAcoclave() || reporte.getAcoclave() == 0){
            raAux=new ReporteAvanceAux(reporteAvance,areaCon.get(0),cont2,auxPAvance,fechaCorte,fechaLimite);
            listaAux.add(raAux);
            }
            
            cont=0;
            auxPAvance=0;
       }         
        
        return listaAux;
    }
    
    public ArrayList<ReporteAvanceAux> getListaAux() {
        return listaAux;
    }

    public void setListaAux(ArrayList<ReporteAvanceAux> listaAux) {
        this.listaAux = listaAux;
    }
    
    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCalnumeroReporte() {
        return calnumeroReporte;
    }

    public void setCalnumeroReporte(int calnumeroReporte) {
        this.calnumeroReporte = calnumeroReporte;
    }

    public int getNumRact() {
        return numRact;
    }

    public void setNumRact(int numRact) {
        this.numRact = numRact;
    }

    public String getCescicloEscolar() {
        return cescicloEscolar;
    }

    public void setCescicloEscolar(String cescicloEscolar) {
        this.cescicloEscolar = cescicloEscolar;
    }

    public int getAcoclave() {
        return acoclave;
    }

    public void setAcoclave(int acoclave) {
        this.acoclave = acoclave;
    }

    public int getClavepe() {
        return clavepe;
    }

    public void setClavepe(int clavepe) {
        this.clavepe = clavepe;
    }

    public String getPesvigencia() {
        return pesvigencia;
    }

    public void setPesvigencia(String pesvigencia) {
        this.pesvigencia = pesvigencia;
    }

    public int getNumProfUIPid() {
        return numProfUIPid;
    }

    public void setNumProfUIPid(int numProfUIPid) {
        this.numProfUIPid = numProfUIPid;
    }

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public int getPronumeroEmpleado() {
        return pronumeroEmpleado;
    }

    public void setPronumeroEmpleado(int pronumeroEmpleado) {
        this.pronumeroEmpleado = pronumeroEmpleado;
    }

    public int getGponumero() {
        return gponumero;
    }

    public void setGponumero(int gponumero) {
        this.gponumero = gponumero;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public int getUapclave() {
        return uapclave;
    }

    public void setUapclave(int uapclave) {
        this.uapclave = uapclave;
    }

    public int getUacclave() {
        return uacclave;
    }

    public void setUacclave(int uacclave) {
        this.uacclave = uacclave;
    }

    public int getCreid() {
        return creid;
    }

    public void setCreid(int creid) {
        this.creid = creid;
    }
            
    public ReporteavancecontenidotematicoDelegate getReporteAvanceContenidoTematicoDelegate() {
        return reporteAvanceContenidoTematicoDelegate;
    }

    public void setReporteAvanceContenidoTematicoDelegate(ReporteavancecontenidotematicoDelegate reporteAvanceContenidoTematicoDelegate) {
        this.reporteAvanceContenidoTematicoDelegate = reporteAvanceContenidoTematicoDelegate;
    }
    
    //agregue aqui jesus ruelas
    private ProgramaEducativoDelegate programaeducativoDelegate;
    private PlanEstudioDelegate planEstudioDelegate;

    public ProgramaEducativoDelegate getProgramaeducativoDelegate() {
        return programaeducativoDelegate;
    }

    public void setProgramaeducativoDelegate(ProgramaEducativoDelegate programaeducativoDelegate) {
        this.programaeducativoDelegate = programaeducativoDelegate;
    }
    
    public PlanEstudioDelegate getPlanEstudioDelegate() {
        return planEstudioDelegate;
    }

    public void setPlanEstudioDelegate(PlanEstudioDelegate planEstudioDelegate) {
        this.planEstudioDelegate = planEstudioDelegate;
    } 
    //agregue aqui jesus ruelas
    
}