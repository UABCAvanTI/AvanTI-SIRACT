/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business.services;

import mx.avanti.business.logic.FacadeAlerta;
import mx.avanti.business.logic.FacadeAreaAdministrativa;
import mx.avanti.business.logic.FacadeAreaConocimiento;
import mx.avanti.business.logic.FacadeCalendarioReporte;
import mx.avanti.business.logic.FacadeCalendarioreporteTieneAlerta;
import mx.avanti.business.logic.FacadeCampus;
import mx.avanti.business.logic.FacadeCatalogoReportes;
import mx.avanti.business.logic.FacadeCicloEscolar;
import mx.avanti.business.logic.FacadeCicloEscolarConfig;
import mx.avanti.business.logic.FacadeConfiguracion;
import mx.avanti.business.logic.FacadeCoordinadorAreaAdministrativa;
import mx.avanti.business.logic.FacadeEsperados;
import mx.avanti.business.logic.FacadeGrupo;
import mx.avanti.business.logic.FacadeMensaje;
import mx.avanti.business.logic.FacadePermiso;
import mx.avanti.business.logic.FacadePlanEstudio;
import mx.avanti.business.logic.FacadePracticasLaboratorio;
import mx.avanti.business.logic.FacadePracticasTaller;
import mx.avanti.business.logic.FacadePracticascampo;
import mx.avanti.business.logic.FacadeProfesor;
import mx.avanti.business.logic.FacadeProfesorTienePuesto;
import mx.avanti.business.logic.FacadeProgramaEducativo;
import mx.avanti.business.logic.FacadePuesto;
import mx.avanti.business.logic.FacadeReporte;
import mx.avanti.business.logic.FacadeReporteavancecontenidotematico;
import mx.avanti.business.logic.FacadeResponsableprogramaeducativo;
import mx.avanti.business.logic.FacadeRol;
import mx.avanti.business.logic.FacadeRolHasPermiso;
import mx.avanti.business.logic.FacadeSubPermiso;
import mx.avanti.business.logic.FacadeSubtemaUnidad;
import mx.avanti.business.logic.FacadeTemaUnidad;
import mx.avanti.business.logic.FacadeUnidad;
import mx.avanti.business.logic.FacadeUnidadAcademica;
import mx.avanti.business.logic.FacadeUnidadAprendizaje;
import mx.avanti.business.logic.FacadeUnidadaprendisajeimparteprofesor;
import mx.avanti.business.logic.FacadeUsuario;
import mx.avanti.business.logic.SubTemaUnidadFacade;
import mx.avanti.siract.business.RolHasPermisoDelegate;


/**
 *
 * @author Cesar Favela
 */
public class ServiceFacadeLocator {

    private static FacadeUsuario facadeUsuario;
    private static FacadeRol facadeRol;
    private static FacadePermiso facadePermiso;
    private static FacadeSubPermiso facadeSubPermiso;
    private static FacadeRol facaderol;
    private static FacadeProfesor facadeprofesor;
    private static FacadeRolHasPermiso facaderolhaspermiso;
    private static FacadeProgramaEducativo facadeProgramaEducativo;
    private static FacadeCatalogoReportes facadeCatalogoreportes;
    private static FacadePuesto facadePuesto;
    private static FacadeUnidadAprendizaje facadeUnidadAprendizaje;
    private static FacadeAreaConocimiento facadeAreaConocimiento;
    private static FacadeCicloEscolar facadeCicloEscolar;
    private static FacadeCampus facadeCampus;
    private static FacadeConfiguracion facadeConfiguracion;
    private static FacadeMensaje facadeMensaje;
    private static FacadeAlerta facadeAlerta;
    private static FacadeCalendarioReporte facadeCalendarioReporte;
    private static FacadeCicloEscolarConfig facadeCicloEscolarConfig;
    private static FacadeUnidad facadeUnidad;
    private static FacadeTemaUnidad facadeTemaUnidad;
    private static FacadePracticasLaboratorio facadePracticasLab;
    private static FacadePracticasTaller facadePracticasTall;
    private static FacadeProfesor facadeProfesor;
    private static FacadeGrupo facadeGrupo;
    private static FacadeSubtemaUnidad facadeSubtemaunidad;
    private static FacadeReporte facadeReporte;
    private static FacadeReporteavancecontenidotematico facadeReporteavancecontenidotematico;
    private static FacadeUnidadaprendisajeimparteprofesor facadeUnidadaprendizajeImparteProfesor;
    private static FacadeCalendarioreporteTieneAlerta facadeCalendarioreporteTieneAlerta;
    private static FacadePracticascampo facadePracticascampo;
    private static FacadePlanEstudio facadePlanEstudio;
    private static FacadeUnidadAcademica facadeUnidadAcademica;
    private static FacadeProfesorTienePuesto facadeProfesorTienePuesto;
    private static FacadeResponsableprogramaeducativo facadeResponsableprogramaeducativo;
    private static FacadeEsperados facadeEsperados;

    
    public static FacadeCatalogoReportes getFacadeCatalogoreportes(){
        if(facadeCatalogoreportes == null){
            facadeCatalogoreportes = new FacadeCatalogoReportes();
            return facadeCatalogoreportes;
        }else{
            return facadeCatalogoreportes;
        }
    }
//    private static FacadeSubTemaUnidad subTemaUnidad;
    public static FacadeProgramaEducativo getInstanceFacadeProgramaeducativo() {
        if (facadeProgramaEducativo == null) {
            facadeProgramaEducativo = new FacadeProgramaEducativo();
            return facadeProgramaEducativo;
        } else {
            return facadeProgramaEducativo;
        }

    }

    public static FacadeCatalogoReportes getFacadeCatalagoReportes() {
        if (facadeCatalogoreportes == null) {
            facadeCatalogoreportes = new FacadeCatalogoReportes();
            return facadeCatalogoreportes;
        } else {
            return facadeCatalogoreportes;
        }
    }

    public static FacadeUsuario getFacadeUsuario() {
        if (facadeUsuario == null) {
            facadeUsuario = new FacadeUsuario();
            return facadeUsuario;
        } else {
            return facadeUsuario;
        }
    }

    public static FacadeRol getFacadeRol() {
        if (facadeRol == null) {
            facadeRol = new FacadeRol();
            return facadeRol;
        } else {
            return facadeRol;
        }
    }

    public static FacadePermiso getFacadePermiso() {
        if (facadePermiso == null) {
            facadePermiso = new FacadePermiso();
            return facadePermiso;
        } else {
            return facadePermiso;
        }
    }

    public static FacadePuesto getInstanceFacadePuesto() {
        if (facadePuesto == null) {
            facadePuesto = new FacadePuesto();
            return facadePuesto;
        } else {
            return facadePuesto;
        }
    }

    public static FacadeUnidadAprendizaje getInstanceFacadeUnidadAprendizaje() {
        if (facadeUnidadAprendizaje == null) {
            facadeUnidadAprendizaje = new FacadeUnidadAprendizaje();
            return facadeUnidadAprendizaje;
        } else {
            return facadeUnidadAprendizaje;
        }
    }

    public static FacadeAreaConocimiento getInstanceFacadeAreaConocimiento() {
        if (facadeAreaConocimiento == null) {
            facadeAreaConocimiento = new FacadeAreaConocimiento();
            return facadeAreaConocimiento;
        } else {
            return facadeAreaConocimiento;
        }
    }

    public static FacadeCicloEscolar getFacadeCicloEscolar() {
        if (facadeCicloEscolar == null) {
            facadeCicloEscolar = new FacadeCicloEscolar();
            return facadeCicloEscolar;
        } else {
            return facadeCicloEscolar;
        }
    }

    public static FacadeCampus getInstanceFacadeCampus() {
        if (facadeCampus == null) {
            facadeCampus = new FacadeCampus();
            return facadeCampus;
        } else {
            return facadeCampus;
        }
    }

    public static FacadeConfiguracion getFacadeConfiguracion() {
        if (facadeConfiguracion == null) {
            facadeConfiguracion = new FacadeConfiguracion();
            return facadeConfiguracion;
        } else {
            return facadeConfiguracion;
        }
    }

    public static FacadeMensaje getFacadeMensaje() {
        if (facadeMensaje == null) {
            facadeMensaje = new FacadeMensaje();
            return facadeMensaje;
        } else {
            return facadeMensaje;
        }
    }

    public static FacadeAlerta getFacadeAlerta() {
        if (facadeAlerta == null) {
            facadeAlerta = new FacadeAlerta();
            return facadeAlerta;
        } else {
            return facadeAlerta;
        }
    }

    public static FacadeCalendarioReporte getFacadeCalendarioReporte() {
        if (facadeCalendarioReporte == null) {
            facadeCalendarioReporte = new FacadeCalendarioReporte();
            return facadeCalendarioReporte;
        } else {
            return facadeCalendarioReporte;
        }
    }

    public static FacadeCicloEscolarConfig getfacadeCicloEscolarConfig() {
        if (facadeCicloEscolarConfig == null) {
            facadeCicloEscolarConfig = new FacadeCicloEscolarConfig();
            return facadeCicloEscolarConfig;
        } else {
            return facadeCicloEscolarConfig;
        }
    }

    public static FacadePracticasLaboratorio getfacadePracticasLab() {
        if (facadePracticasLab == null) {
            facadePracticasLab = new FacadePracticasLaboratorio();
            return facadePracticasLab;
        } else {
            return facadePracticasLab;
        }
    }

    public static FacadePracticasTaller getfacadePracticasTall() {
        if (facadePracticasTall == null) {
            facadePracticasTall = new FacadePracticasTaller();
            return facadePracticasTall;
        } else {
            return facadePracticasTall;
        }
    }

    public static FacadeTemaUnidad getfacadeTemaUnidad() {
        if (facadeTemaUnidad == null) {
            facadeTemaUnidad = new FacadeTemaUnidad();
            return facadeTemaUnidad;
        } else {
            return facadeTemaUnidad;
        }
    }

    public static FacadeUnidad getfacadeUnidad() {
        if (facadeUnidad == null) {
            facadeUnidad = new FacadeUnidad();
            return facadeUnidad;
        } else {
            return facadeUnidad;
        }
    }

    public static FacadeGrupo getFacadeGrupo() {
        if (facadeGrupo == null) {
            facadeGrupo = new FacadeGrupo();
            return facadeGrupo;
        } else {
            return facadeGrupo;
        }
    }

    public static FacadeProfesor getFacadeProfesor() {
        if (facadeProfesor == null) {
            facadeProfesor = new FacadeProfesor();
            return facadeProfesor;
        } else {
            return facadeProfesor;
        }
    }

    public static FacadeReporteavancecontenidotematico getFacadeReporteavancecontenidotematico() {
        if (facadeReporteavancecontenidotematico == null) {
            facadeReporteavancecontenidotematico = new FacadeReporteavancecontenidotematico();
            return facadeReporteavancecontenidotematico;
        } else {
            return facadeReporteavancecontenidotematico;
        }
    }

    public static FacadeReporte getFacadeReporte() {
        if (facadeReporte == null) {
            facadeReporte = new FacadeReporte();
            return facadeReporte;
        } else {
            return facadeReporte;
        }
    }

    public static FacadeUnidadaprendisajeimparteprofesor getFacadeUnidadaprendisajeimparteprofesor() {
        if (facadeUnidadaprendizajeImparteProfesor == null) {
            facadeUnidadaprendizajeImparteProfesor = new FacadeUnidadaprendisajeimparteprofesor();
            return facadeUnidadaprendizajeImparteProfesor;
        } else {
            return facadeUnidadaprendizajeImparteProfesor;
        }
    }

    public static FacadeCalendarioreporteTieneAlerta getFacadeCalendarioreporteTieneAlerta() {
        if (facadeCalendarioreporteTieneAlerta == null) {
            facadeCalendarioreporteTieneAlerta = new FacadeCalendarioreporteTieneAlerta();
            return facadeCalendarioreporteTieneAlerta;
        } else {
            return facadeCalendarioreporteTieneAlerta;
        }
    }

    public static FacadePracticascampo getFacadePracticasCampo() {
        if (facadePracticascampo == null) {
            facadePracticascampo = new FacadePracticascampo();
            return facadePracticascampo;
        } else {
            return facadePracticascampo;
        }
    }

    /**
     * *****************************************************************************************************************************
     */
    public static FacadeSubPermiso getFacadeSubPermiso() {
        if (facadeSubPermiso == null) {
            facadeSubPermiso = new FacadeSubPermiso();
            return facadeSubPermiso;
        } else {
            return facadeSubPermiso;
        }
    }

    public static FacadeRol getInstanceFacadeRol() {
        if (facaderol == null) {
            facaderol = new FacadeRol();
            return facaderol;
        } else {
            return facaderol;
        }
    }

    public static FacadeProfesor getInstanceFacadeProfesor() {
        if (facadeprofesor == null) {
            facadeprofesor = new FacadeProfesor();
            return facadeprofesor;
        } else {
            return facadeprofesor;
        }
    }

    public static FacadePlanEstudio getInstanceFacadePlanEstudio() {
        if (facadePlanEstudio == null) {
            facadePlanEstudio = new FacadePlanEstudio();
            return facadePlanEstudio;
        } else {
            return facadePlanEstudio;
        }
    }

    public static FacadeRolHasPermiso getInstanceFacadeRolHasPermiso() {
        if (facaderolhaspermiso == null) {
            facaderolhaspermiso = new FacadeRolHasPermiso();
            return facaderolhaspermiso;
        } else {
            return facaderolhaspermiso;
        }
    }

    public static FacadeUnidadAcademica getInstanceFacadeUnidadAcademica() {
        if (facadeUnidadAcademica == null) {
            facadeUnidadAcademica = new FacadeUnidadAcademica();
            return facadeUnidadAcademica;
        } else {
            return facadeUnidadAcademica;
        }
    }

    public static FacadeProgramaEducativo getInstanceFacadeProgramaEducativo() {
        if (facadeProgramaEducativo == null) {
            facadeProgramaEducativo = new FacadeProgramaEducativo();
            return facadeProgramaEducativo;
        } else {
            return facadeProgramaEducativo;
        }
    }

    public static FacadeProfesorTienePuesto getInstanceFacadeProfesorTienePuesto() {
        if (facadeProfesorTienePuesto == null) {
            facadeProfesorTienePuesto = new FacadeProfesorTienePuesto();
            return facadeProfesorTienePuesto;
        } else {
            return facadeProfesorTienePuesto;
        }
    }
    
     public static FacadeSubtemaUnidad getFacadeSubtemaunidad(){
        if(facadeSubtemaunidad == null){
            facadeSubtemaunidad = new FacadeSubtemaUnidad();
            return facadeSubtemaunidad;
        }else{
            return facadeSubtemaunidad;
        }
    }
     
       public static FacadeResponsableprogramaeducativo getFacadeResponsableprogramaeducativo(){
      if(facadeResponsableprogramaeducativo == null){
          facadeResponsableprogramaeducativo = new FacadeResponsableprogramaeducativo();
          return facadeResponsableprogramaeducativo;
      }else{
          return facadeResponsableprogramaeducativo;
      }
  }
     
     
//    public static SubTemaUnidadFacade getSubtemaunidadFacade() {
//        if (facadeSubtemaunidad == null) {
//            facadeSubtemaunidad = new SubTemaUnidadFacade();
//            return facadeSubtemaunidad;
//        } else {
//            return facadeSubtemaunidad;
//        }
//    }
    public static FacadeEsperados getFacadeEsperados(){
        if(facadeEsperados == null){
            facadeEsperados = new FacadeEsperados();
            return facadeEsperados;
        }else{
            return facadeEsperados;
        }
    }

    public static FacadeAreaAdministrativa facadeAreaadministrativa;
    public static FacadeAreaAdministrativa getInstanceFacadeAreaAdministrativa(){
        if(facadeAreaadministrativa == null){
            facadeAreaadministrativa = new FacadeAreaAdministrativa();
            return facadeAreaadministrativa;
        }else{
            return facadeAreaadministrativa;
        }
        
    }
public static FacadeCoordinadorAreaAdministrativa facadeCoordinadorAreaAdministrativa;
          public static FacadeCoordinadorAreaAdministrativa getInstanceFacadeCoordinadorAreaAdministrativa(){
          if(facadeCoordinadorAreaAdministrativa == null){
              facadeCoordinadorAreaAdministrativa = new FacadeCoordinadorAreaAdministrativa();
              return facadeCoordinadorAreaAdministrativa;
          }else{
              return facadeCoordinadorAreaAdministrativa;
          }
      }
}
