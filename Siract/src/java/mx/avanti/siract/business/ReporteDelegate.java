/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Reporte;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Y
 */
public class ReporteDelegate implements Serializable {

    private List<Reporte> listaReporte;

    public ReporteDelegate() {
        listaReporte = new ArrayList<Reporte>();
    }

    public List<Reporte> getListaReporte() {
        listaReporte = ServiceFacadeLocator.getFacadeReporte().consultaReporte();
        return listaReporte;
    }

    public List<Reporte> getListaUnidadAprendisajeFFWD(String de, String campo, String criterio, String de2, String campo2, String criterio2, String order) {
        listaReporte = ServiceFacadeLocator.getFacadeReporte().consultaFFWDUnidadesAprendisaje(de, campo, criterio, de2, campo2, criterio2, order);
        return listaReporte;
    }

    public void setListaReporte(List<Reporte> listaReporte) {
        this.listaReporte = listaReporte;
    }

    public void agregarReporte(Reporte reporte) {
        ServiceFacadeLocator.getFacadeReporte().agregarReporte(reporte);
    }

    public Reporte consultaCheckBox(int id_nodo, String tipo_nodo, int id_reporte) {
        return ServiceFacadeLocator.getFacadeReporte().consultaCheckBox(id_nodo, tipo_nodo, id_reporte);
    }
    
    public Reporte consultaCheckBoxReportesAnteriores(int id_nodo, String tipo_nodo,int reporte_actual,int id_uaip) {
        return ServiceFacadeLocator.getFacadeReporte().consultaCheckBoxReportesAnteriores(id_nodo, tipo_nodo,reporte_actual, id_uaip);
    }

    public void eliminaReportesDeReporteDeAvanceContenidoTematico(int id_ReporteAvance) {
        ServiceFacadeLocator.getFacadeReporte().eliminaReportesDeReporteDeAvanceContenidoTematico(id_ReporteAvance);
    }

}
