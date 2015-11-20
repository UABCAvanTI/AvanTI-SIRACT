/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.business.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mx.avanti.siract.application.helper.ReporteAux;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Calendarioreporte;
import mx.avanti.siract.business.entity.CalendarioreporteTieneAlerta;
import mx.avanti.siract.business.entity.Configuracion;
import mx.avanti.siract.business.entity.Coordinadorareaadministrativa;
import mx.avanti.siract.business.entity.Reporteavancecontenidotematico;
import mx.avanti.siract.business.entity.UnidadaprendizajeImparteProfesor;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Y
 */
public class FacadeReporteavancecontenidotematico {
     public void agregarReporteavancecontenidotematico(Reporteavancecontenidotematico reporteavancecontenidotematico){
        Reporteavancecontenidotematico resultado = null;
        if(reporteavancecontenidotematico.getRacid() != null){
            ServiceLocator.getInstanceBaseDAO().setTipo(Reporteavancecontenidotematico.class);
            resultado = (Reporteavancecontenidotematico) ServiceLocator.getInstanceBaseDAO().find(reporteavancecontenidotematico.getRacid());
            System.out.println("METODO AGREGAR REPORTE");
        }
        if(resultado != null){
            reporteavancecontenidotematico.setRacid(resultado.getRacid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(reporteavancecontenidotematico);
        } else{
            ServiceLocator.getInstanceBaseDAO().save(reporteavancecontenidotematico);
        }
        
    }
    
    public List<Reporteavancecontenidotematico> consultaReporteavancecontenidotematico(){
        List<Reporteavancecontenidotematico> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Reporteavancecontenidotematico.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
        return resultado;
    }
        public List<Reporteavancecontenidotematico> consultaFFWDUnidadesAprendisaje(String de,String campo,String criterio,String de2,String campo2,String criterio2,String order){
        List<Reporteavancecontenidotematico> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Reporteavancecontenidotematico.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhereDoble(de, campo, criterio, de2, campo2, criterio2, order);
        return resultado;
    }
        
        public Reporteavancecontenidotematico buscarReporteavancecontenidotematico(int id_unidadAprendizaje,int id_profesor, int id_grupo,String subGrupo){
                  Reporteavancecontenidotematico resultado = null;                 
                    ServiceLocator.getInstanceBaseDAO().setTipo(Reporteavancecontenidotematico.class);
                    String criterio="unidadaprendizajeImparteProfesor.profesor.proid = "+id_profesor+" AND ";
//                    criterio+="unidadaprendizajeImparteProfesor.grupo.gpoid = "+id_grupo+" AND ";
                    criterio+="unidadaprendizajeImparteProfesor.unidadaprendizaje.uapclave = "+id_unidadAprendizaje+" AND ";
                    criterio+="unidadaprendizajeImparteProfesor.uipsubgrupo = "+subGrupo+"";
            resultado = (Reporteavancecontenidotematico) ServiceLocator.getInstanceBaseDAO().findMultipleIDExtra(id_grupo,criterio,"unidadaprendizajeImparteProfesor.grupo.gponumero","order by racnumero desc, racid desc","Reporteavancecontenidotematico");
            return resultado;
      }
        
        
         public Reporteavancecontenidotematico buscarReporteavancecontenidotematicoNumero(int id_unidadAprendizaje,String numero,int id_profesor, int id_grupo,String subGrupo){
                  Reporteavancecontenidotematico resultado = null;                 
                    ServiceLocator.getInstanceBaseDAO().setTipo(Reporteavancecontenidotematico.class);
                    String criterio="unidadaprendizajeImparteProfesor.profesor.proid = "+id_profesor+" AND ";
//                    criterio+="unidadaprendizajeImparteProfesor.grupo.gpoid = "+id_grupo+" AND ";
                    criterio+="unidadaprendizajeImparteProfesor.unidadaprendizaje.uapclave = "+id_unidadAprendizaje+" AND ";
                    criterio+="unidadaprendizajeImparteProfesor.uipsubgrupo = "+subGrupo+" AND ";
                    criterio+="racnumero='"+numero+"'";
                    System.out.println("NUMERO : "+numero);
            resultado = (Reporteavancecontenidotematico) ServiceLocator.getInstanceBaseDAO().findMultipleIDExtra(id_grupo,criterio,"unidadaprendizajeImparteProfesor.grupo.gponumero","order by racnumero desc","Reporteavancecontenidotematico");
            return resultado;
      }
         
            public Reporteavancecontenidotematico buscarReporteavancecontenidotematicoUltimo(int id_unidadAprendizaje,int id_profesor, int id_grupo,String tipo,String subGrupo){
                  Reporteavancecontenidotematico resultado = null;                 
                    ServiceLocator.getInstanceBaseDAO().setTipo(Reporteavancecontenidotematico.class);
                    String criterio="unidadaprendizajeImparteProfesor.profesor.proid = "+id_profesor+" AND ";
//                    criterio+="unidadaprendizajeImparteProfesor.grupo.gpoid = "+id_grupo+" AND ";
                    criterio+="unidadaprendizajeImparteProfesor.unidadaprendizaje.uapclave = "+id_unidadAprendizaje+" AND ";
                    criterio+="unidadaprendizajeImparteProfesor.uiptipoSubgrupo = '"+tipo+"' AND ";
                    criterio+="unidadaprendizajeImparteProfesor.uipsubgrupo = "+subGrupo;
            resultado = (Reporteavancecontenidotematico) ServiceLocator.getInstanceBaseDAO().findMultipleIDExtra(id_grupo,criterio,"unidadaprendizajeImparteProfesor.grupo.gponumero","order by racnumero desc","Reporteavancecontenidotematico");
            return resultado;
      }
        
        public Reporteavancecontenidotematico buscarReporteavancecontenidotematicoIncompleto(int id_unidadAprendizaje,int id_profesor, int id_grupo){
                  Reporteavancecontenidotematico resultado = null;                 
                    ServiceLocator.getInstanceBaseDAO().setTipo(Reporteavancecontenidotematico.class);
                    String criterio="unidadaprendizajeImparteProfesor.profesor.proid = "+id_profesor+" AND ";
//                    criterio+="unidadaprendizajeImparteProfesor.grupo.gpoid = "+id_grupo+" AND ";
                    criterio+="unidadaprendizajeImparteProfesor.unidadaprendizaje.uapclave = "+id_unidadAprendizaje+" AND a.racstatus = 'parcial'";
            resultado = (Reporteavancecontenidotematico) ServiceLocator.getInstanceBaseDAO().findMultipleIDExtra(id_grupo,criterio,"unidadaprendizajeImparteProfesor.grupo.gponumero","","Reporteavancecontenidotematico");
            if(resultado==null){
                System.out.println("RESULTADO VACIO");
            }
            return resultado;
      }
        
        
    public List<Reporteavancecontenidotematico> consultaReporteAvanceContenidoTematico(ReporteAux reporte){
        List<Reporteavancecontenidotematico> resultado=null;
        resultado = ServiceLocator.getInstanceReportes().findByCriteria4(reporte);
        return resultado;
    }
    
    public List<Areaconocimiento> consultaAreaConocimiento(int uapclave){
        List<Areaconocimiento> resultado=null;
        resultado = ServiceLocator.getInstanceReportes().findFromWhereAreaCon(uapclave);
        return resultado;
    }
    
//    public List<Areaconocimiento> consultaAreaConocimiento(ReporteAux reporte){
//        List<Areaconocimiento> resultado=null;
//        resultado = ServiceLocator.getInstanceReportes().findByCriteria4(reporte);
//        return resultado;
//    }
    
    public List<Calendarioreporte> consultaCalendarioreporte(Date confechainiciosemestre){
        List<Calendarioreporte> resultado=null;
        resultado = ServiceLocator.getInstanceReportes().findFromWhereCalendReporte(confechainiciosemestre);
        return resultado;
    }
    
    public List<Configuracion> consultaConfiguracion(ReporteAux reporte){
        List<Configuracion> resultado=null;
        resultado = ServiceLocator.getInstanceReportes().findByCriteria4(reporte);
        return resultado;
    }
    
    public List<CalendarioreporteTieneAlerta> consultaCalendarioreporteTieneAlerta(ReporteAux reporte){
        List<CalendarioreporteTieneAlerta> resultado=null;
        resultado = ServiceLocator.getInstanceReportes().findByCriteria4(reporte);
        return resultado;
    }
    
    public List<UnidadaprendizajeImparteProfesor> consultaUnidadaprendizajeImparteProfesor(ReporteAux reporte){
        List<UnidadaprendizajeImparteProfesor> resultado=null;
        resultado = ServiceLocator.getInstanceReportes().findByCriteria4(reporte);
        return resultado;
    }

    public List<UnidadaprendizajeImparteProfesor> getimparteProfesorByUnidadAprendisaje(Integer uapid) {
        List<UnidadaprendizajeImparteProfesor> resultado= new ArrayList<UnidadaprendizajeImparteProfesor>();
        resultado = ServiceLocator.getInstanceReportes().findByUnidadAprendisaje(uapid);
        return resultado;
    }

    public List<Reporteavancecontenidotematico> getReportesAvanceByUinidadimparte(Integer uipid) {
        List<Reporteavancecontenidotematico> resultado = new ArrayList<Reporteavancecontenidotematico>();
        resultado = ServiceLocator.getInstanceReportes().findByUnidadImparte(uipid);
        return resultado;
    }
    


    
    public List<Coordinadorareaadministrativa> consultaCoordAreaAdminProfUAprend(int uapclave){
        List<Coordinadorareaadministrativa> resultado=null;
        resultado = ServiceLocator.getInstanceReportes().findByCriteriaDetalladoCordAreaAdminProfUAprend(uapclave);
        return resultado;
    }
       public List<Coordinadorareaadministrativa> consultaCoordAreaAdminProfUAprend(int uapclave,int pedclave,int aadid){
        List<Coordinadorareaadministrativa> resultado=null;
        resultado = ServiceLocator.getInstanceReportes().findByCriteriaDetalladoCordAreaAdminProfUAprend(uapclave,pedclave,aadid);
        return resultado;
    }
 
}
