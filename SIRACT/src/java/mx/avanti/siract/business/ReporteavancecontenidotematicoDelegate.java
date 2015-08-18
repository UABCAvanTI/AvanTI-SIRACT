/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mx.avanti.siract.application.helper.ReporteAux;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Calendarioreporte;
import mx.avanti.siract.business.entity.CalendarioreporteTieneAlerta;
import mx.avanti.siract.business.entity.Configuracion;
import mx.avanti.siract.business.entity.Reporteavancecontenidotematico;
import mx.avanti.siract.business.entity.UnidadaprendizajeImparteProfesor;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Y
 */
public class ReporteavancecontenidotematicoDelegate implements Serializable {

    private List<Reporteavancecontenidotematico> listaReporteavancecontenidotematico;

    public ReporteavancecontenidotematicoDelegate() {
        listaReporteavancecontenidotematico = new ArrayList<Reporteavancecontenidotematico>();
    }

    public List<Reporteavancecontenidotematico> getListaReporteavancecontenidotematico() {
        listaReporteavancecontenidotematico = ServiceFacadeLocator.getFacadeReporteavancecontenidotematico().consultaReporteavancecontenidotematico();
        return listaReporteavancecontenidotematico;
    }

    public List<Reporteavancecontenidotematico> getListaUnidadAprendisajeFFWD(String de, String campo, String criterio, String de2, String campo2, String criterio2, String order) {
        listaReporteavancecontenidotematico = ServiceFacadeLocator.getFacadeReporteavancecontenidotematico().consultaFFWDUnidadesAprendisaje(de, campo, criterio, de2, campo2, criterio2, order);
        return listaReporteavancecontenidotematico;
    }

    public void setListaReporteavancecontenidotematico(List<Reporteavancecontenidotematico> listaReporteavancecontenidotematico) {
        this.listaReporteavancecontenidotematico = listaReporteavancecontenidotematico;
    }

    public void agregarReporteavancecontenidotematico(Reporteavancecontenidotematico reporteavancecontenidotematico) {
        ServiceFacadeLocator.getFacadeReporteavancecontenidotematico()
                .agregarReporteavancecontenidotematico(reporteavancecontenidotematico);
    }

    public Reporteavancecontenidotematico buscarReporteavancecontenidotematico(int id_unidadAprendizaje, int id_profesor, int id_grupo,String subGrupo) {
        Reporteavancecontenidotematico resultado = null;
        resultado = ServiceFacadeLocator.getFacadeReporteavancecontenidotematico().
                buscarReporteavancecontenidotematico(id_unidadAprendizaje, id_profesor, id_grupo,subGrupo);
        return resultado;
    }
     public Reporteavancecontenidotematico buscarReporteavancecontenidotematicoNumero(int id_unidadAprendizaje,String num,int id_profesor, int id_grupo,String subGrupo) {
        Reporteavancecontenidotematico resultado = null;
        resultado = ServiceFacadeLocator.getFacadeReporteavancecontenidotematico().buscarReporteavancecontenidotematicoNumero(id_unidadAprendizaje,num, id_profesor, id_grupo,subGrupo);
        return resultado;
    }
       public Reporteavancecontenidotematico buscarReporteavancecontenidotematicoUltimo(int id_unidadAprendizaje, int id_profesor, int id_grupo,String tipo,String subGrupo) {
        Reporteavancecontenidotematico resultado = null;
        resultado = ServiceFacadeLocator.getFacadeReporteavancecontenidotematico().
                buscarReporteavancecontenidotematicoUltimo(id_unidadAprendizaje, id_profesor, id_grupo,tipo,subGrupo);
        return resultado;
    }

    public Reporteavancecontenidotematico buscarReporteavancecontenidotematicoIncompleto(int id_unidadAprendizaje, int id_profesor, int id_grupo) {
        Reporteavancecontenidotematico resultado = null;
        resultado = ServiceFacadeLocator.getFacadeReporteavancecontenidotematico().
                buscarReporteavancecontenidotematicoIncompleto(id_unidadAprendizaje, id_profesor, id_grupo);
        return resultado;
    }
    
    
 
    private List<Reporteavancecontenidotematico> listReporteAvanceContTem;
    private List<Areaconocimiento> listAreaCon;
    private List<Calendarioreporte> listCalendarioreporte;
    private List<Configuracion> listConfiguracion;
    private List<CalendarioreporteTieneAlerta> listCalendarioreporteTieneAlerta;
    private List<UnidadaprendizajeImparteProfesor> listUnidadaprendizajeImparteProfesor;
    
    public List<Reporteavancecontenidotematico> getReporteAvanceContenidoTematico(ReporteAux reporte) {
        listReporteAvanceContTem = ServiceFacadeLocator.getFacadeReporteavancecontenidotematico().consultaReporteAvanceContenidoTematico(reporte);
        return listReporteAvanceContTem;
    }
    
    public List<Areaconocimiento> getAreaConocimiento(int uapclave) {
        listAreaCon = ServiceFacadeLocator.getFacadeReporteavancecontenidotematico().consultaAreaConocimiento(uapclave);
        return listAreaCon;
    }
    
    public List<Calendarioreporte> getCalendarioreporte(Date confechainiciosemestre) {
        listCalendarioreporte = ServiceFacadeLocator.getFacadeReporteavancecontenidotematico().consultaCalendarioreporte(confechainiciosemestre);
        return listCalendarioreporte;
    }
    
    public List<Configuracion> getConfiguracion(ReporteAux reporte) {
        listConfiguracion = ServiceFacadeLocator.getFacadeReporteavancecontenidotematico().consultaConfiguracion(reporte);
        return listConfiguracion;
    }
    
    public List<CalendarioreporteTieneAlerta> getCalendarioreporteTieneAlerta(ReporteAux reporte) {
        listCalendarioreporteTieneAlerta = ServiceFacadeLocator.getFacadeReporteavancecontenidotematico().consultaCalendarioreporteTieneAlerta(reporte);
        return listCalendarioreporteTieneAlerta;
    }
    
    public List<UnidadaprendizajeImparteProfesor> getUnidadaprendizajeImparteProfesor(ReporteAux reporte) {
        listUnidadaprendizajeImparteProfesor = ServiceFacadeLocator.getFacadeReporteavancecontenidotematico().consultaUnidadaprendizajeImparteProfesor(reporte);
        return listUnidadaprendizajeImparteProfesor;
    }
}
