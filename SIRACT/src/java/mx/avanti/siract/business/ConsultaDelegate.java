/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Grupo;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Reporteavancecontenidotematico;
import mx.avanti.siract.business.entity.Temaunidad;
import mx.avanti.siract.business.entity.Unidadacademica;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.business.entity.UnidadaprendizajeImparteProfesor;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Moises
 */
public class ConsultaDelegate implements Serializable {

    private List<Unidadacademica> unidadesacademicas;
    private List<Programaeducativo> programaseducativos;
    private List<Planestudio> planesestudios;
    private List<Areaconocimiento> areasconocimiento;
    private List<Unidadaprendizaje> unidadaprendizaje;
    private List<Temaunidad> temaunidad;

    public ConsultaDelegate() {
        unidadesacademicas = new ArrayList<Unidadacademica>();
        programaseducativos = new ArrayList<Programaeducativo>();
        planesestudios = new ArrayList<Planestudio>();
        areasconocimiento = new ArrayList<Areaconocimiento>();
        unidadaprendizaje = new ArrayList<Unidadaprendizaje>();
        temaunidad = new ArrayList<Temaunidad>();
    }

    public List<Unidadacademica> getUnidadesacademicas() {
        unidadesacademicas = ServiceFacadeLocator.getInstanceFacadeUnidadAcademica().findAll();
        return unidadesacademicas;
    }

    public List<Programaeducativo> getProgramaseducativos() {
        programaseducativos = ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().findAll();
        return programaseducativos;
    }

    public List<Planestudio> getPlanesesestudios() {
        planesestudios = ServiceFacadeLocator.getInstanceFacadePlanEstudio().findAll();
        return planesestudios;
    }

    public List<Areaconocimiento> getAreasconocimiento() {
        areasconocimiento = ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().findAll();
        return areasconocimiento;
    }

    public List<Unidadaprendizaje> getUnidadesaprendizaje() {
        unidadaprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findAll();
        return unidadaprendizaje;
    }

    public List<Programaeducativo> getProgramaeducativoByUnidadacademica(int idUnidad) {
        programaseducativos = ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().findByUnidadAcademica(idUnidad);
        return programaseducativos;
    }

    public List<Planestudio> getPlanesByPrograma(int idPrograma) {
        planesestudios = ServiceFacadeLocator.getInstanceFacadePlanEstudio().findByProgramaeducativo(idPrograma);
        return planesestudios;
    }

    public List<Areaconocimiento> getAreasByPlan(int idPlan) {
        areasconocimiento = ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().findByPlanestudio(idPlan);
        return areasconocimiento;
    }
    
       public List<Areaconocimiento> getAreasByPlanYProgramaeducativo(int idPlan,String idProgramaEducativo) {
        areasconocimiento = ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().getAreasByPlanYProgramaeducativo(idPlan,idProgramaEducativo);
         return areasconocimiento;
    }
        public List<Areaconocimiento> getAreasByProgramaeducativo(String idProgramaEducativo) {
        areasconocimiento = ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().getAreasByProgramaeducativo(idProgramaEducativo);
         return areasconocimiento;
    }

    public List<Unidadaprendizaje> getUnidadByArea(int idArea) {
        unidadaprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findByAreaconocimiento(idArea);
        return unidadaprendizaje;
    }
    
    public List<Unidadacademica> findUnidadAcademica(int idUsuario) {
        List<Unidadacademica> unidad=new ArrayList<Unidadacademica>();
        unidad = ServiceFacadeLocator.getInstanceFacadeUnidadAcademica().findUAByUsuario(idUsuario);
        return unidad; 
    }
    
 
    
    public List<Unidadaprendizaje> getUnidadByUnidadAcademica(int idUsuario) {
        List<Unidadaprendizaje> nuevaLista = new ArrayList();

        Unidadaprendizaje auxiliar;
        String nombre;
        
        unidadaprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findByUnidadAcademica(idUsuario);
        for (Iterator<Unidadaprendizaje> it = unidadaprendizaje.iterator(); it.hasNext();) {
            Unidadaprendizaje unidadaprendizaje2 = it.next();
           // System.out.println("++++++++++++LA UNIDAD DE APRENDIZAJE ES HORAS COMPLETAS"+unidadaprendizaje2.getUaphorasCompletas());
            if (unidadaprendizaje2.getUaphorasClase() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- C";
                auxiliar = auxiliar = new Unidadaprendizaje(
                                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid()); 
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasLaboratorio() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- L";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasTaller() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- T";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());

                nuevaLista.add(auxiliar);
            }
        }

        return nuevaLista;
        
    }
    
public List<Unidadaprendizaje> getUnidadByAreaAndEtapaAndPE(int idArea, String etapa,String idPE) {
        List<Unidadaprendizaje> nuevaLista = new ArrayList();

        Unidadaprendizaje auxiliar;
        String nombre;
        unidadaprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findByAreaconocimientoAndEtapaAndPE(idArea, etapa,idPE);

        for (Iterator<Unidadaprendizaje> it = unidadaprendizaje.iterator(); it.hasNext();) {
            Unidadaprendizaje unidadaprendizaje2 = it.next();
           // System.out.println("++++++++++++LA UNIDAD DE APRENDIZAJE ES HORAS COMPLETAS"+unidadaprendizaje2.getUaphorasCompletas());
            if (unidadaprendizaje2.getUaphorasClase() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- C";
                auxiliar = auxiliar = new Unidadaprendizaje(
                                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid()); 
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasLaboratorio() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- L";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasTaller() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- T";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());

                nuevaLista.add(auxiliar);
            }
        }

        return nuevaLista;
       // return ServiceFacadeLocator.getFacadeUnidadaprendizaje().findByAreaconocimiento2(idArea, etapa);
    }

    public List<Unidadaprendizaje> getUnidadByAreaAndEtapa(int idArea, String etapa) {
        List<Unidadaprendizaje> nuevaLista = new ArrayList();

        Unidadaprendizaje auxiliar;
        String nombre;
        unidadaprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findByAreaconocimiento2(idArea, etapa);

        for (Iterator<Unidadaprendizaje> it = unidadaprendizaje.iterator(); it.hasNext();) {
            Unidadaprendizaje unidadaprendizaje2 = it.next();
           // System.out.println("++++++++++++LA UNIDAD DE APRENDIZAJE ES HORAS COMPLETAS"+unidadaprendizaje2.getUaphorasCompletas());
            if (unidadaprendizaje2.getUaphorasClase() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- C";
                auxiliar = auxiliar = new Unidadaprendizaje(
                                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid()); 
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasLaboratorio() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- L";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasTaller() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- T";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());

                nuevaLista.add(auxiliar);
            }
        }

        return nuevaLista;
       // return ServiceFacadeLocator.getFacadeUnidadaprendizaje().findByAreaconocimiento2(idArea, etapa);
    }

    public List<Temaunidad> getTemaunidadByUnidad(int idUnidad) {
        temaunidad = ServiceFacadeLocator.getfacadeTemaUnidad().findByUnidad(idUnidad);
        return temaunidad;
    }
    
    public List<Areaconocimiento> getAreasByUnidadAcademica(int idUa) {
        areasconocimiento = ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().getAreasByUnidadAcademica(String.valueOf(idUa));
         return areasconocimiento;
    }
    
    public List<Unidadaprendizaje> getUnidadByResponsable(int idUsuario) {
        List<Unidadaprendizaje> nuevaLista = new ArrayList();

        Unidadaprendizaje auxiliar;
        String nombre;
        
        unidadaprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findByResponsable(idUsuario);
        for (Iterator<Unidadaprendizaje> it = unidadaprendizaje.iterator(); it.hasNext();) {
            Unidadaprendizaje unidadaprendizaje2 = it.next();
           // System.out.println("++++++++++++LA UNIDAD DE APRENDIZAJE ES HORAS COMPLETAS"+unidadaprendizaje2.getUaphorasCompletas());
            if (unidadaprendizaje2.getUaphorasClase() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- C";
                auxiliar = auxiliar = new Unidadaprendizaje(
                                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid()); 
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasLaboratorio() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- L";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasTaller() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- T";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());

                nuevaLista.add(auxiliar);
            }
        }

        return nuevaLista;
    }
    
    public List<Unidadaprendizaje> getUnidadByAreaConocimiento(int idArea) {
        List<Unidadaprendizaje> nuevaLista = new ArrayList();

        Unidadaprendizaje auxiliar;
        String nombre;
        
        unidadaprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findByAreaConocimiento(String.valueOf(idArea));
        for (Iterator<Unidadaprendizaje> it = unidadaprendizaje.iterator(); it.hasNext();) {
            Unidadaprendizaje unidadaprendizaje2 = it.next();
           // System.out.println("++++++++++++LA UNIDAD DE APRENDIZAJE ES HORAS COMPLETAS"+unidadaprendizaje2.getUaphorasCompletas());
            if (unidadaprendizaje2.getUaphorasClase() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- C";
                auxiliar = auxiliar = new Unidadaprendizaje(
                                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid()); 
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasLaboratorio() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- L";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasTaller() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- T";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());

                nuevaLista.add(auxiliar);
            }
        }

        return nuevaLista;
    }
    
    //Obtener unidades de aprendizaje por area de conocimieto y programa educativo
    public List<Unidadaprendizaje> getUnidadByAreaConocimientoAndPE(int idArea ,String idPE) {
        List<Unidadaprendizaje> nuevaLista = new ArrayList();

        Unidadaprendizaje auxiliar;
        String nombre;
        
        unidadaprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findByAreaConocimientoAndPE(String.valueOf(idArea),idPE);
        for (Iterator<Unidadaprendizaje> it = unidadaprendizaje.iterator(); it.hasNext();) {
            Unidadaprendizaje unidadaprendizaje2 = it.next();
           // System.out.println("++++++++++++LA UNIDAD DE APRENDIZAJE ES HORAS COMPLETAS"+unidadaprendizaje2.getUaphorasCompletas());
            if (unidadaprendizaje2.getUaphorasClase() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- C";
                auxiliar = auxiliar = new Unidadaprendizaje(
                                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid()); 
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasLaboratorio() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- L";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasTaller() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- T";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());

                nuevaLista.add(auxiliar);
            }
        }

        return nuevaLista;
    }
    
    //Obtener unidades por etapa y programa educativo
     public List<Unidadaprendizaje> getUnidadByEtapaAndPE(String etapa ,String idPE) {
        List<Unidadaprendizaje> nuevaLista = new ArrayList();

        Unidadaprendizaje auxiliar;
        String nombre;
        
        unidadaprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findByEtapaAndPE(etapa,idPE);
        for (Iterator<Unidadaprendizaje> it = unidadaprendizaje.iterator(); it.hasNext();) {
            Unidadaprendizaje unidadaprendizaje2 = it.next();
           // System.out.println("++++++++++++LA UNIDAD DE APRENDIZAJE ES HORAS COMPLETAS"+unidadaprendizaje2.getUaphorasCompletas());
            if (unidadaprendizaje2.getUaphorasClase() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- C";
                auxiliar = auxiliar = new Unidadaprendizaje(
                                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid()); 
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasLaboratorio() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- L";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasTaller() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- T";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());

                nuevaLista.add(auxiliar);
            }
        }

        return nuevaLista;
    }
    
    //Obtener unidades de aprendizaje por programa educativo (PE)
    public List<Unidadaprendizaje> getUnidadByPE(String idPE) {
        List<Unidadaprendizaje> nuevaLista = new ArrayList();

        Unidadaprendizaje auxiliar;
        String nombre;
        
        unidadaprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findByPE(idPE);
        for (Iterator<Unidadaprendizaje> it = unidadaprendizaje.iterator(); it.hasNext();) {
            Unidadaprendizaje unidadaprendizaje2 = it.next();
           // System.out.println("++++++++++++LA UNIDAD DE APRENDIZAJE ES HORAS COMPLETAS"+unidadaprendizaje2.getUaphorasCompletas());
            if (unidadaprendizaje2.getUaphorasClase() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- C";
                auxiliar = auxiliar = new Unidadaprendizaje(
                                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid()); 
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasLaboratorio() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- L";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasTaller() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- T";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());

                nuevaLista.add(auxiliar);
            }
        }

        return nuevaLista;
        
    }
    
    //Obtener unidad de aprendizaje por etapa
    public List<Unidadaprendizaje> getUnidadByEtapa(String etapa) {
        List<Unidadaprendizaje> nuevaLista = new ArrayList();

        Unidadaprendizaje auxiliar;
        String nombre;
        
        unidadaprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findByEtapa(etapa);
        for (Iterator<Unidadaprendizaje> it = unidadaprendizaje.iterator(); it.hasNext();) {
            Unidadaprendizaje unidadaprendizaje2 = it.next();
           // System.out.println("++++++++++++LA UNIDAD DE APRENDIZAJE ES HORAS COMPLETAS"+unidadaprendizaje2.getUaphorasCompletas());
            if (unidadaprendizaje2.getUaphorasClase() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- C";
                auxiliar = auxiliar = new Unidadaprendizaje(
                                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid()); 
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasLaboratorio() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- L";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasTaller() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- T";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());

                nuevaLista.add(auxiliar);
            }
        }

        return nuevaLista;
    }
    
    //Obtener unidades por plan de estudio
     public List<Unidadaprendizaje> getUnidadByPlanEstudio(int idPlan) {
        List<Unidadaprendizaje> nuevaLista = new ArrayList();

        Unidadaprendizaje auxiliar;
        String nombre;
        
        unidadaprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findByPlanEstudio(String.valueOf(idPlan));
        for (Iterator<Unidadaprendizaje> it = unidadaprendizaje.iterator(); it.hasNext();) {
            Unidadaprendizaje unidadaprendizaje2 = it.next();
           // System.out.println("++++++++++++LA UNIDAD DE APRENDIZAJE ES HORAS COMPLETAS"+unidadaprendizaje2.getUaphorasCompletas());
            if (unidadaprendizaje2.getUaphorasClase() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- C";
                auxiliar = auxiliar = new Unidadaprendizaje(
                                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid()); 
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasLaboratorio() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- L";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasTaller() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- T";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());

                nuevaLista.add(auxiliar);
            }
        }

        return nuevaLista;
    }
     
     //Obtener unidades por plan de estudio y etapa
    public List<Unidadaprendizaje> getUnidadByPlanEstudioAndEtapa(int idPlan,String etapa) {
        List<Unidadaprendizaje> nuevaLista = new ArrayList();

        Unidadaprendizaje auxiliar;
        String nombre;
        
        unidadaprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findByPlanEstudioAndEtapa(String.valueOf(idPlan),etapa);
        for (Iterator<Unidadaprendizaje> it = unidadaprendizaje.iterator(); it.hasNext();) {
            Unidadaprendizaje unidadaprendizaje2 = it.next();
           // System.out.println("++++++++++++LA UNIDAD DE APRENDIZAJE ES HORAS COMPLETAS"+unidadaprendizaje2.getUaphorasCompletas());
            if (unidadaprendizaje2.getUaphorasClase() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- C";
                auxiliar = auxiliar = new Unidadaprendizaje(
                                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                                auxiliar.setUapid(unidadaprendizaje2.getUapid()); 
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasLaboratorio() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- L";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());
                nuevaLista.add(auxiliar);
            }
            
            if (unidadaprendizaje2.getUaphorasTaller() != 0) {
                nombre = unidadaprendizaje2.getUapnombre() + " -- T";
//                auxiliar = new Unidadaprendizaje(unidadaprendizaje2.getCicloescolar(), unidadaprendizaje2.getAreaconocimiento(), unidadaprendizaje2.getUnidadaprendizaje(), unidadaprendizaje2.getUapclave(), nombre, unidadaprendizaje2.getUapetapaFormacion(),
//                        unidadaprendizaje2.getUapcreditos(), unidadaprendizaje2.getUaphorasClase(), unidadaprendizaje2.getUaphorasLaboratorio(), unidadaprendizaje2.getUaphorasTaller(),
//                        unidadaprendizaje2.getUaphorasClinica(), unidadaprendizaje2.getUaphorasExtraClase(), unidadaprendizaje2.getUaptipoCaracter(), unidadaprendizaje2.getPracticascampos(), unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(),
//                        unidadaprendizaje2.getPracticalaboratorios(), unidadaprendizaje2.getPracticatallers(), unidadaprendizaje2.getUnidadaprendizajes(), unidadaprendizaje2.getUnidads());
                auxiliar =  new Unidadaprendizaje(
                unidadaprendizaje2.getCicloescolar(), 
                                unidadaprendizaje2.getUnidadaprendizaje(),
                                unidadaprendizaje2.getUapclave(),
                                nombre,
                                unidadaprendizaje2.getUapetapaFormacion(),
                                unidadaprendizaje2.getUapcreditos(),
                                unidadaprendizaje2.getUaphorasClase(), 
                                unidadaprendizaje2.getUaphorasLaboratorio(),
                                unidadaprendizaje2.getUaphorasTaller(),
                                unidadaprendizaje2.getUaphorasClinica(),
                                unidadaprendizaje2.getUaphorasCampo(), 
                                unidadaprendizaje2.getUaphorasExtraClase(),
                                unidadaprendizaje2.getUaptipoCaracter(),true,true,true,true,
                                unidadaprendizaje2.getUnidadaprendizajeImparteProfesors(), 
                                unidadaprendizaje2.getPracticatallers(),
                                unidadaprendizaje2.getPracticascampos(),
                                unidadaprendizaje2.getPracticalaboratorios(),
                                unidadaprendizaje2.getPracticasclinicas(), 
                                 unidadaprendizaje2.getAreaconocimientos(),
                                unidadaprendizaje2.getUnidadaprendizajes(), 
                                unidadaprendizaje2.getCoordinadorareaadministrativas(),
                                unidadaprendizaje2.getUnidads());
                auxiliar.setUapid(unidadaprendizaje2.getUapid());

                nuevaLista.add(auxiliar);
            }
        }

        return nuevaLista;
    }
    
    //Parte enviada por reportes

    private List<UnidadaprendizajeImparteProfesor> listUnidadAprendisajeProfesor;
    private List<Reporteavancecontenidotematico> listReporteAvanceContenido;
    private List<Grupo> listGrupo;
    private List<Profesor> listProfesor;

    
    public List<Programaeducativo> getProgramaeducativoByUnidadacademicaClave(int claveUnidad){
        programaseducativos = ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().findByUnidadAcademicaClave(claveUnidad);
        return programaseducativos;
    }

    
    public List<Planestudio> getPlanesByProgramaClave(int idPrograma){
        planesestudios = ServiceFacadeLocator.getInstanceFacadePlanEstudio().findByProgramaeducativoClave(idPrograma);
        return planesestudios;
    }
    

    
    public List<Areaconocimiento> getAreasByPlanClave(int pedclave, String pesvigenciaPlan){
        areasconocimiento = ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().findByPlanestudioClave(pedclave,pesvigenciaPlan);
        return areasconocimiento;
    }
    
    
    public List<Areaconocimiento> getAreasByProgramaEducativoList(List<String> listaProgramas){
        
        List<Areaconocimiento> retListaAreas = null;
        List<Areaconocimiento> temp = null;
        
        for (String programa : listaProgramas) {
            temp = ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().findByProgramaEducativo(Integer.parseInt(programa));
            retListaAreas.addAll(temp);
        }        
        return retListaAreas;
    }

    
    public List<Unidadaprendizaje> getUnidadByAreaClave(int acoclave){
        unidadaprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findByAreaconocimientoClave(acoclave);
        return unidadaprendizaje;
    }
    


    public List<UnidadaprendizajeImparteProfesor> getUnidadesAprendisajeImparteProf(Integer uapid) {
        listUnidadAprendisajeProfesor = ServiceFacadeLocator.getFacadeReporteavancecontenidotematico().getimparteProfesorByUnidadAprendisaje(uapid);
        return  listUnidadAprendisajeProfesor;
    }

    public List<Reporteavancecontenidotematico> getResporteDeAvanceContenidoByUnidadImparte(Integer uipid) {
        listReporteAvanceContenido = ServiceFacadeLocator.getFacadeReporteavancecontenidotematico().getReportesAvanceByUinidadimparte(uipid);
        return listReporteAvanceContenido;
    }

    public List<Grupo> getGrupoByUnidad(int uaid) {
        listGrupo = ServiceFacadeLocator.getFacadeGrupo().getGruposByUnidadAprendisaje(uaid);
        return listGrupo;
    }
    
    public List<Grupo> getGrupoByUnidadClave(int uapclave) {
        listGrupo = ServiceFacadeLocator.getFacadeGrupo().getGruposByUnidadAprendisajeClave(uapclave);
        return listGrupo;
    }
    
    public List<Profesor> getProfesorByUnidadAprendisaje(int uapclave)       
    {
        listProfesor = ServiceFacadeLocator.getFacadeProfesor().getProfesorByUnidadAprendisajeClave(uapclave);
        return listProfesor;
    }
    
    public List<Profesor> getProfesorByUnidadAprendisajeClave(int uapclave)       
    {
        listProfesor = ServiceFacadeLocator.getFacadeProfesor().getProfesorByUnidadAprendisajeClave(uapclave);
        return listProfesor;
    }
    
    public List<Grupo> getGrupoByProfesorUnidadAprendisaje(int numempleProfesor ,int uapclave) {
        listGrupo = ServiceFacadeLocator.getFacadeGrupo().getGruposByProfesorUnidadAprendisajeClave(numempleProfesor,uapclave);
        return listGrupo;
    }
}
