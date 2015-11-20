/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Areaadministrativa;
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
public class ConsultaDelegate implements Serializable{
    private List<Unidadacademica> unidadesacademicas;
    private List<Programaeducativo> programaseducativos;
    private List<Areaadministrativa> areaadministrativas;
    private List<Planestudio> planesestudios;
    private List<Areaconocimiento> areasconocimiento;
    private List<Unidadaprendizaje> unidadaprendizaje;
    private List<UnidadaprendizajeImparteProfesor> listUnidadAprendisajeProfesor;
    private List<Reporteavancecontenidotematico> listReporteAvanceContenido;
    private List<Grupo> listGrupo;
    private List<Profesor> listProfesor;
    
    public ConsultaDelegate(){
        unidadesacademicas = new ArrayList<Unidadacademica>();
        programaseducativos = new ArrayList<Programaeducativo>();
        areaadministrativas = new ArrayList<Areaadministrativa>();
        planesestudios = new ArrayList<Planestudio>();
        areasconocimiento = new ArrayList<Areaconocimiento>();
        unidadaprendizaje = new ArrayList<Unidadaprendizaje>();
        listUnidadAprendisajeProfesor = new ArrayList<UnidadaprendizajeImparteProfesor>();
        listReporteAvanceContenido = new ArrayList<Reporteavancecontenidotematico>();
        listGrupo = new ArrayList<Grupo>();
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
    
    
    public List<Programaeducativo> getProgramaeducativoByUnidadacademica(int idUnidad){
        programaseducativos = ServiceFacadeLocator.getInstanceFacadeProgramaeducativo().findByUnidadAcademica(idUnidad);
        return programaseducativos;
    }
    
    public List<Programaeducativo> getProgramaeducativoByUnidadacademicaClave(int claveUnidad){
        programaseducativos = ServiceFacadeLocator.getInstanceFacadeProgramaeducativo().findByUnidadAcademicaClave(claveUnidad);
        return programaseducativos;
    }
    
    public List<Areaadministrativa> getAreaadministrativaByProgramaeducativoClave(int pedclave){
        areaadministrativas = ServiceFacadeLocator.getInstanceFacadeAreaAdministrativa().findByProgramaEducativoClave(pedclave);
        return areaadministrativas;
    }
    
    public List<Planestudio> getPlanesByPrograma(int idPrograma){
        planesestudios = ServiceFacadeLocator.getInstanceFacadePlanEstudio().findByProgramaeducativo(idPrograma);
        return planesestudios;
    }
    
    public List<Planestudio> getPlanesByProgramaClave(int idPrograma){
        planesestudios = ServiceFacadeLocator.getInstanceFacadePlanEstudio().findByProgramaeducativoClave(idPrograma);
        return planesestudios;
    }
    
    public List<Areaconocimiento> getAreasByPlan(int idPlan){
        areasconocimiento = ServiceFacadeLocator.getInstanceFacadeAreaConocimiento().findByPlanestudio(idPlan);
        return areasconocimiento;
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
    
    public List<Unidadaprendizaje> getUnidadByArea(int idArea){
        unidadaprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findByAreaconocimiento(idArea);
        return unidadaprendizaje;
    }
    
    public List<Unidadaprendizaje> getUnidadByAreaClave(int acoclave){
        unidadaprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findByAreaconocimientoClave(acoclave);
        return unidadaprendizaje;
    }
    
    public List<Unidadaprendizaje> getUnidadByAreaAndEtapa(int idArea, String etapa){
        unidadaprendizaje = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findByAreaconocimiento2(idArea,etapa);
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