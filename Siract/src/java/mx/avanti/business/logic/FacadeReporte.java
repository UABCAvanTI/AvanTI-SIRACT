/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.Reporte;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Y
 */
public class FacadeReporte {

    public void agregarReporte(Reporte reporte) {
        Reporte resultado = null;
        if (reporte.getId() != null) {
            ServiceLocator.getInstanceBaseDAO().setTipo(Reporte.class);
            //ESTA PARTE CAUSA CONFLICTOS AL GUARDAR, DEBIDO AL TIPO DE ID DEL OBJHETO REPORTE
//            resultado = (Reporte) ServiceLocator.getInstanceBaseDAO().find(reporte.getId().getRepid());
        }
        if (resultado != null) {
            reporte.setId(resultado.getId());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(reporte);
        } else {
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(reporte);
        }
    }

    public List<Reporte> consultaReporte() {
        List<Reporte> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Reporte.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
        return resultado;
    }

    public List<Reporte> consultaFFWDUnidadesAprendisaje(String de, String campo, String criterio, String de2, String campo2, String criterio2, String order) {
        List<Reporte> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Reporte.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhereDoble(de, campo, criterio, de2, campo2, criterio2, order);
        return resultado;
    }

    public Reporte consultaCheckBox(int id_nodo, String tipo_nodo, int id_reporte) {
        Reporte resultado = null;
        if (id_nodo != 0) {
            ServiceLocator.getInstanceBaseDAO().setTipo(Reporte.class);
            String deReporte = "";
            String clase = "Reporte";
             if (id_reporte == 0) {
                return null;
            }
//            if (id_reporte != 0) {
                deReporte = "reporteavancecontenidotematico.racid =" + id_reporte;
//            }
            
            //ESTA PARTE CAUSA CONFLICTOS AL GUARDAR, DEBIDO AL TIPO DE ID DEL OBJHETO REPORTE
            switch (tipo_nodo) {
                case "Unidad":
                    //SE AÑADE AND CUANDO HAY CONDICIONES ANTES DEL REPORTE
//                    if (id_reporte != 0) {
//                        deReporte = " AND " + deReporte;
//                    }
                    resultado = (Reporte) ServiceLocator.getInstanceBaseDAO().findMultipleIDExtra(id_nodo, "id.tunid = 0 AND id.sutid=0 AND " + deReporte, "id.uniid", "", clase);
                    break;
                case "Tema":
//                    if (id_reporte != 0) {
//                        deReporte = " AND " + deReporte;
//                    }
                    resultado = (Reporte) ServiceLocator.getInstanceBaseDAO().findMultipleIDExtra(id_nodo, "id.sutid = 0 AND " + deReporte, "id.tunid", "", clase);
                    break;
                case "Subtema":
                    resultado = (Reporte) ServiceLocator.getInstanceBaseDAO().findMultipleIDExtra(id_nodo, deReporte, "id.sutid", "", clase);
                    break;
                case "practicaTaller":
                    resultado = (Reporte) ServiceLocator.getInstanceBaseDAO().findMultipleIDExtra(id_nodo, deReporte, "id.prtid", "", clase);

                    break;
                case "practicaLaboratorio":
                    resultado = (Reporte) ServiceLocator.getInstanceBaseDAO().findMultipleIDExtra(id_nodo, deReporte, "id.prlid", "", clase);

                    break;
                case "PracticaClase":
                    break;
                      case "PracticaCampo":
                    resultado = (Reporte) ServiceLocator.getInstanceBaseDAO().findMultipleIDExtra(id_nodo, deReporte, "id.prcid", "", clase);
                    break;
                    
                    
            }
        }
        return resultado;
   
    }
    
     public Reporte consultaCheckBoxReportesAnteriores(int id_nodo, String tipo_nodo,int reporte_actual, int id_uaip) {
        Reporte resultado = null;
        if (id_nodo != 0) {
            ServiceLocator.getInstanceBaseDAO().setTipo(Reporte.class);
            String deReporte = "";
            String clase = "Reporte";
             if (id_uaip == 0) {
                return null;
            }
//            if (id_reporte != 0) {
             System.out.println("reporteavancecontenidotematico.racid!="+reporte_actual);
                deReporte = "reporteavancecontenidotematico.racid!="+reporte_actual+" AND reporteavancecontenidotematico.unidadaprendizajeImparteProfesor.uipid =" + id_uaip;
//            }
            
            //ESTA PARTE CAUSA CONFLICTOS AL GUARDAR, DEBIDO AL TIPO DE ID DEL OBJHETO REPORTE
            switch (tipo_nodo) {
                case "Unidad":
                    //SE AÑADE AND CUANDO HAY CONDICIONES ANTES DEL REPORTE
//                    if (id_reporte != 0) {
//                        deReporte = " AND " + deReporte;
//                    }
                    resultado = (Reporte) ServiceLocator.getInstanceBaseDAO().findMultipleIDExtra(id_nodo, "id.tunid = 0 AND id.sutid=0 AND " + deReporte, "id.uniid", "", clase);
                    break;
                case "Tema":
//                    if (id_reporte != 0) {
//                        deReporte = " AND " + deReporte;
//                    }
                    resultado = (Reporte) ServiceLocator.getInstanceBaseDAO().findMultipleIDExtra(id_nodo, "id.sutid = 0 AND " + deReporte, "id.tunid", "", clase);
                    break;
                case "Subtema":
                    resultado = (Reporte) ServiceLocator.getInstanceBaseDAO().findMultipleIDExtra(id_nodo, deReporte, "id.sutid", "", clase);
                    break;
                case "practicaTaller":
                    resultado = (Reporte) ServiceLocator.getInstanceBaseDAO().findMultipleIDExtra(id_nodo, deReporte, "id.prtid", "", clase);

                    break;
                case "practicaLaboratorio":
                    resultado = (Reporte) ServiceLocator.getInstanceBaseDAO().findMultipleIDExtra(id_nodo, deReporte, "id.prlid", "", clase);

                    break;
                case "PracticaClase":
                    break;
                      case "PracticaCampo":
                    resultado = (Reporte) ServiceLocator.getInstanceBaseDAO().findMultipleIDExtra(id_nodo, deReporte, "id.prcid", "", clase);
                    break;
                    
                    
            }
        }
        return resultado;
   
    }

    public void eliminaReportesDeReporteDeAvanceContenidoTematico(int id_ReporteAvance) {
        ServiceLocator.getInstanceBaseDAO().setTipo(Reporte.class);
        ServiceLocator.getInstanceBaseDAO().deleteWhere("Reporte","repobservacion=\'\' AND reporteavancecontenidotematico.racid", String.valueOf(id_ReporteAvance));
        ServiceLocator.getInstanceBaseDAO().updateWhere("Reporte","impartido", "0", "reporteavancecontenidotematico.racid", String.valueOf(id_ReporteAvance));
    }
}
