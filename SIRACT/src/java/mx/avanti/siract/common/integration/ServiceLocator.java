/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.common.integration;


import mx.avanti.siract.integration.persistence.AreaconocimientoDAO;
import mx.avanti.siract.integration.persistence.BaseDAO;
import mx.avanti.siract.integration.persistence.CatalogoreportesDAO;
import mx.avanti.siract.integration.persistence.EsperadosDAO;
import mx.avanti.siract.integration.persistence.GrupoDAO;
import mx.avanti.siract.integration.persistence.PlanestudioDAO;
import mx.avanti.siract.integration.persistence.ProfesorDAO;
import mx.avanti.siract.integration.persistence.ProgramaeducativoDAO;
import mx.avanti.siract.integration.persistence.TemaunidadDAO;
import mx.avanti.siract.integration.persistence.UnidadAprendizajeDAO;
import mx.avanti.siract.integration.persistence.UnidadDAO;
import mx.avanti.siract.integration.persistence.UnidadacademicaDAO;
import mx.avanti.siract.integration.persistence.reportesDAO;



/**
 *
 * @author Cesar Favela
 */

public class ServiceLocator {
   private static BaseDAO baseDAO;
    private static PlanestudioDAO planestudioDAO;
    private static AreaconocimientoDAO areaconocimientoDAO;
    private static ProfesorDAO profesorDAO;
    private static GrupoDAO grupoDAO;
    private static UnidadAprendizajeDAO unidadAprendizajeDAO;
       
    private static UnidadacademicaDAO unidadacademicaDAO;
    private static ProgramaeducativoDAO programaeducativoDAO;
    private static TemaunidadDAO temaunidadDAO;
    private static UnidadDAO unidadDAO;
    private static CatalogoreportesDAO catalogoreportesDAO;
        private static reportesDAO reportesDAO;
    private static EsperadosDAO esperadosDAO;

    
    /*public static BaseDAO getInstanceBaseDAO(Class t){
        if(baseDAO == null){
            baseDAO = new BaseDAO(t);
            return baseDAO;
        } else{
            return baseDAO;
        }
    }*/
      
    public static BaseDAO getInstanceBaseDAO(){
        if(baseDAO == null){
            baseDAO = new BaseDAO();
            return baseDAO;
        } else{
            return baseDAO;
        }
    }
    
    /****************************GENERADOR DE REPORTES******************************************/
//
//    private static GrupoDAO grupoDAO;
//    
//
//       
//        public static GrupoDAO getInstanceGrupoDAO(){
//        if(grupoDAO == null){
//            grupoDAO = new GrupoDAO();
//            return grupoDAO;
//        }else{
//            return grupoDAO;
//        }
//    }
//    
    
        
    /***********************************************************************************************************************/
    
    public static PlanestudioDAO getInstancePlanestudio(){
       if(planestudioDAO == null){
           planestudioDAO = new PlanestudioDAO();
           return planestudioDAO;
       }else{
           return planestudioDAO;
       }
   }
    
    public static AreaconocimientoDAO getInstanceAreaconocimiento(){
       if(areaconocimientoDAO == null){
           areaconocimientoDAO = new AreaconocimientoDAO();
           return areaconocimientoDAO;
       }else{
           return areaconocimientoDAO;
       }
   }
    
    public static ProfesorDAO getInstanceProfesor(){
       if(profesorDAO == null){
           profesorDAO = new ProfesorDAO();
           return profesorDAO;
       }else{
           return profesorDAO;
       }
   }
    
    
    public static GrupoDAO getInstanceGrupoDAO(){
        if(grupoDAO == null){
            grupoDAO = new GrupoDAO();
            return grupoDAO;
        } else{
            return grupoDAO;
        }
    }
   
   public static UnidadAprendizajeDAO getInstanceUnidadAprendizajeDAO(){
        if(unidadAprendizajeDAO == null){
            unidadAprendizajeDAO = new UnidadAprendizajeDAO();
            return unidadAprendizajeDAO;
        } else{
            return unidadAprendizajeDAO;
        }
    }

    public static UnidadacademicaDAO getInstanceUnidadacademica(){
        if(unidadacademicaDAO == null){
            unidadacademicaDAO = new UnidadacademicaDAO();
            return unidadacademicaDAO;
        }else{
            return unidadacademicaDAO;
        }
    }
    
    public static ProgramaeducativoDAO getInstanceProgramaeducativo(){
        if(programaeducativoDAO == null){
            programaeducativoDAO = new ProgramaeducativoDAO();
            return programaeducativoDAO;
        }else{
            return programaeducativoDAO;
        }
    }
    
    public static UnidadAprendizajeDAO getInstanceUnidadaprendizaje(){
        if(unidadAprendizajeDAO == null){
            unidadAprendizajeDAO = new UnidadAprendizajeDAO();
            return unidadAprendizajeDAO;
        }else{
            return unidadAprendizajeDAO;
        }
    }
    
    public static TemaunidadDAO getInstanceTemaunidad(){
        if(temaunidadDAO == null){
            temaunidadDAO = new TemaunidadDAO();
            return temaunidadDAO;
        }else{
            return temaunidadDAO;
        }
    }
    
    public static UnidadDAO getInstanceUnidad(){
        if(unidadDAO == null){
            unidadDAO = new UnidadDAO();
            return unidadDAO;
        }else{
            return unidadDAO;
        }
    }
      public static CatalogoreportesDAO getInstanceCatalogoreportes(){
        if(catalogoreportesDAO == null){
            catalogoreportesDAO = new CatalogoreportesDAO();
            return catalogoreportesDAO;
        }else{
            return catalogoreportesDAO;
        }
    }
    
     public static reportesDAO getInstanceReportes(){
        if(reportesDAO == null){
            reportesDAO = new reportesDAO();
            return reportesDAO;
        }else{
            return reportesDAO;
        }
    }
     public static EsperadosDAO getInstanceEsperados(){
        if(esperadosDAO == null){
            esperadosDAO = new EsperadosDAO();
            return esperadosDAO;
        }else{
            return esperadosDAO;
        }
    }  
}
