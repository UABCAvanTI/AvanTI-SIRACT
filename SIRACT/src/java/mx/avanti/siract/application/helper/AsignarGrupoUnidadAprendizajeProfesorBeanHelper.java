/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mx.avanti.siract.business.AsignarGrupoUnidadAprendizajeProfesorDelegate;
import mx.avanti.siract.business.GrupoDelegate;
import mx.avanti.siract.business.ProfesorDelegate;
import mx.avanti.siract.business.PlanEstudioDelegate;
import mx.avanti.siract.business.UnidadAcademicaDelegate;
import mx.avanti.siract.business.AreaConocimientoDelegate;
import mx.avanti.siract.business.ProgramaEducativoDelegate;
import mx.avanti.siract.business.UnidadAprendizajeDelegate;
import mx.avanti.siract.business.entity.Grupo;
import mx.avanti.siract.business.entity.Usuario;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Unidadacademica;
import mx.avanti.siract.business.entity.Unidadaprendizaje;


import mx.avanti.siract.business.entity.UnidadaprendizajeImparteProfesor;

/**
 *
 * @author Gig@B COMPUTADORAS
 */
public class AsignarGrupoUnidadAprendizajeProfesorBeanHelper implements Serializable {

    private ProfesorDelegate profesorDelegate;
    private GrupoDelegate grupoDelegate;
    private UnidadAprendizajeDelegate unidadAprendizajeDelegate;
    private List<UnidadaprendizajeImparteProfesor> listaFiltrada;

    private AsignarGrupoUnidadAprendizajeProfesorDelegate asignarGrupoUnidadAprendizajeProfesorDelegate;

    private Profesor selecProfesor;
    private Unidadaprendizaje unidadApren;
    private Grupo grupo;
    private Profesor[] profesoresSeleccionados;
    private Unidadaprendizaje[] unidadSeleccionada;
    private Grupo[] grupoSeleccionado;
    private UnidadaprendizajeImparteProfesor imparteProfesor;
    private UnidadaprendizajeImparteProfesor selImparteProfesor;
    //Criteria
//    private Unidadaprendizaje unidadAprenCriteria;
//    private Grupo grupoCriteria;
//    private Planestudio planEstudioCriteria;
//    private Profesor profesorCriteria;
//    private Programaeducativo programaEduCriteria;
//    private Areaconocimiento areaConocimientoCriteria;

    //Objetos de Marco
    private Usuario usuario;
    private Profesor profesor;    
    private Planestudio planEstudio;
    private Areaconocimiento areaConocimiento;
    private Programaeducativo programaEducativo;
    private UnidadaprendizajeImparteProfesor AGUAP;
    private UnidadaprendizajeImparteProfesor selecAGUAP;
    
    private List<Programaeducativo> listaProgEduc;
    private List<Programaeducativo> listaProgramaEducativo;    
    private List<Planestudio> listaPlanEstudio;
    private List<Areaconocimiento> listaAreaConocimiento;
    private List<Unidadaprendizaje> listaUnidadAprendizaje;
    private List<Unidadacademica> listaUnidadAcademica;
    private List<Grupo> listaGrupo;

    private AsignarGrupoUnidadAprendizajeProfesorDelegate AGUAPDelegate;
    private ProgramaEducativoDelegate programaEducativoDelegate;
    private PlanEstudioDelegate planEstudioDelegate;
    private AreaConocimientoDelegate areaConocimientoDelegate;
    private UnidadAcademicaDelegate unidadAcademicaDelegate;

    private List<UnidadaprendizajeImparteProfesor> listaAGUAPSeleccionada;

    private boolean bandUA;
    private boolean bandProf;
    private boolean bandGpo;
    private boolean bandTipo;
    private boolean bandSubgpo;

    private String mensaje;
    private String rolSeleccionado;

    public AsignarGrupoUnidadAprendizajeProfesorBeanHelper() {
        try {
            this.asignarGrupoUnidadAprendizajeProfesorDelegate = new AsignarGrupoUnidadAprendizajeProfesorDelegate();
            this.AGUAPDelegate = new AsignarGrupoUnidadAprendizajeProfesorDelegate();
            profesorDelegate = new ProfesorDelegate();
            grupoDelegate = new GrupoDelegate();
            unidadAprendizajeDelegate = new UnidadAprendizajeDelegate();
            programaEducativoDelegate = new ProgramaEducativoDelegate();
            planEstudioDelegate = new PlanEstudioDelegate();
            areaConocimientoDelegate = new AreaConocimientoDelegate();
            unidadAcademicaDelegate = new UnidadAcademicaDelegate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }        
        grupo = new Grupo();
        usuario = new Usuario();
        profesor = new Profesor();        
        selecProfesor = new Profesor();
        planEstudio = new Planestudio();
        unidadApren = new Unidadaprendizaje();
        areaConocimiento = new Areaconocimiento();
        programaEducativo = new Programaeducativo();
        selecAGUAP = new UnidadaprendizajeImparteProfesor();
        imparteProfesor = new UnidadaprendizajeImparteProfesor();
        AGUAP = new UnidadaprendizajeImparteProfesor(new Unidadaprendizaje(), new Profesor(), new Grupo());
        

        //criteria
//        planEstudioCriteria = new Planestudio();
//        planEstudioCriteria.setPesid(0);
//        areaConocimientoCriteria = new Areaconocimiento();
//        areaConocimientoCriteria.setAcoid(0);
//        programaEduCriteria = new Programaeducativo();
//        programaEduCriteria.setPedid(0);
//        profesorCriteria = new Profesor();
//        profesorCriteria.setProid(0);
//        grupoCriteria = new Grupo();
//        grupoCriteria.setGpoid(0);
//        unidadAprenCriteria = new Unidadaprendizaje();
//        unidadAprenCriteria.setUapid(0);

    }

//    public AsignarGrupoUnidadAprendizajeProfesorDelegate getAsignarGrupoUnidadAprendizajeProfesorDelegate() {
//        imparteProfesor.setUnidadaprendizaje(unidadApren);
//        imparteProfesor.setGrupo(grupo);
//        imparteProfesor.setProfesor(selecProfesor);
//        return asignarGrupoUnidadAprendizajeProfesorDelegate;
//    }
    public Profesor getSelecProfesor() {
        return selecProfesor;
    }

    public void setSelecProfesor(Profesor selecProfesor) {
        this.selecProfesor = selecProfesor;
    }

    public Unidadaprendizaje getUnidadApren() {
        return unidadApren;
    }

    public void setUnidadApren(Unidadaprendizaje unidadApren) {
        this.unidadApren = unidadApren;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Profesor[] getProfesoresSeleccionados() {
        return profesoresSeleccionados;
    }

    public void setProfesoresSeleccionados(Profesor[] profesoresSeleccionados) {
        this.profesoresSeleccionados = profesoresSeleccionados;
    }

    public Unidadaprendizaje[] getUnidadSeleccionada() {
        return unidadSeleccionada;
    }

    public void setUnidadSeleccionada(Unidadaprendizaje[] unidadSeleccionada) {
        this.unidadSeleccionada = unidadSeleccionada;
    }

    public Grupo[] getGrupoSeleccionado() {
        return grupoSeleccionado;
    }

    public void setGrupoSeleccionado(Grupo[] grupoSeleccionado) {
        this.grupoSeleccionado = grupoSeleccionado;
    }

    public UnidadaprendizajeImparteProfesor getImparteProfesor() {
//        imparteProfesor.setUnidadaprendizaje(unidadApren);
//        imparteProfesor.setGrupo(grupo);
//        imparteProfesor.setProfesor(selecProfesor);
        return imparteProfesor;
    }

    public void setImparteProfesor(UnidadaprendizajeImparteProfesor imaparteProfesor) {
        this.imparteProfesor = imaparteProfesor;
    }

//    public Planestudio getPlanEstudioCriteria() {
//        return planEstudioCriteria;
//    }
//
//    public void setPlanEstudioCriteria(Planestudio planEstudioCriteria) {
//        this.planEstudioCriteria = planEstudioCriteria;
//    }

    public UnidadaprendizajeImparteProfesor getSelImparteProfesor() {
        return selImparteProfesor;
    }

    public void setSelImparteProfesor(UnidadaprendizajeImparteProfesor selImparteProfesor) {
        this.selImparteProfesor = selImparteProfesor;
    }

    //Getter y Setter de Marco
    public AsignarGrupoUnidadAprendizajeProfesorDelegate getAGUAPDelegate() {
        AGUAP.setUnidadaprendizaje(unidadApren);
        AGUAP.setGrupo(grupo);
        AGUAP.setProfesor(profesor);
//        imparteProfesor.setProfesor(selecProfesor);        
        return AGUAPDelegate;
    }

    public ProfesorDelegate getProfesorDelegate() {
        return profesorDelegate;
    }

    public void setProfesorDelegate(ProfesorDelegate profesorDelegate) {
        this.profesorDelegate = profesorDelegate;
    }

    public GrupoDelegate getGrupoDelegate() {
        return grupoDelegate;
    }

    public void setGrupoDelegate(GrupoDelegate grupoDelegate) {
        this.grupoDelegate = grupoDelegate;
    }

    public UnidadAprendizajeDelegate getUnidadAprendizajeDelegate() {
        return unidadAprendizajeDelegate;
    }

    public void setUnidadAprendizajeDelegate(UnidadAprendizajeDelegate unidadAprendizajeDelegate) {
        this.unidadAprendizajeDelegate = unidadAprendizajeDelegate;
    }

    public ProgramaEducativoDelegate getProgramaEducativoDelegate() {
        return programaEducativoDelegate;
    }

    public void setProgramaEducativoDelegate(ProgramaEducativoDelegate programaEducativoDelegate) {
        this.programaEducativoDelegate = programaEducativoDelegate;
    }

    public PlanEstudioDelegate getPlanEstudioDelegate() {
        return planEstudioDelegate;
    }

    public void setPlanEstudioDelegate(PlanEstudioDelegate planEstudioDelegate) {
        this.planEstudioDelegate = planEstudioDelegate;
    }

    public AreaConocimientoDelegate getAreaConocimientoDelegate() {
        return areaConocimientoDelegate;
    }

    public void setAreaConocimientoDelegate(AreaConocimientoDelegate areaConocimientoDelegate) {
        this.areaConocimientoDelegate = areaConocimientoDelegate;
    }

    public UnidadAcademicaDelegate getUnidadAcademicaDelegate() {
        return unidadAcademicaDelegate;
    }

    public void setUnidadAcademicaDelegate(UnidadAcademicaDelegate unidadAcademicaDelegate) {
        this.unidadAcademicaDelegate = unidadAcademicaDelegate;
    }

    public List<UnidadaprendizajeImparteProfesor> getListaAGUAPSeleccionada() {
        return listaAGUAPSeleccionada;
    }

    public void setListaAGUAPSeleccionada(List<UnidadaprendizajeImparteProfesor> listaAGUAPSeleccionada) {
        this.listaAGUAPSeleccionada = listaAGUAPSeleccionada;
    }

    public UnidadaprendizajeImparteProfesor getAGUAP() {
        return AGUAP;
    }

    public void setAGUAP(UnidadaprendizajeImparteProfesor AGUAP) {
        this.AGUAP = AGUAP;
    }

    public UnidadaprendizajeImparteProfesor getSelecAGUAP() {
        return selecAGUAP;
    }

    public void setSelecAGUAP(UnidadaprendizajeImparteProfesor selecAGUAP) {
        this.selecAGUAP = selecAGUAP;
    }

    public Programaeducativo getProgramaEducativo() {
        return programaEducativo;
    }

    public void setProgramaEducativo(Programaeducativo programaEducativo) {
        this.programaEducativo = programaEducativo;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Areaconocimiento getAreaConocimiento() {
        return areaConocimiento;
    }

    public void setAreaConocimiento(Areaconocimiento areaConocimiento) {
        this.areaConocimiento = areaConocimiento;
    }

    public Planestudio getPlanEstudio() {
        return planEstudio;
    }

    public void setPlanEstudio(Planestudio planEstudio) {
        this.planEstudio = planEstudio;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<Programaeducativo> getListaProgramaEducativo() {
        return listaProgramaEducativo;
    }

    public void setListaProgramaEducativo(List<Programaeducativo> listaProgramaEducativo) {
        this.listaProgramaEducativo = listaProgramaEducativo;
    }

    public List<Planestudio> getListaPlanEstudio() {
        return listaPlanEstudio;
    }

    public void setListaPlanEstudio(List<Planestudio> listaPlanEstudio) {
        this.listaPlanEstudio = listaPlanEstudio;
    }

    public List<Areaconocimiento> getListaAreaConocimiento() {
        return listaAreaConocimiento;
    }

    public void setListaAreaConocimiento(List<Areaconocimiento> listaAreaConocimiento) {
        this.listaAreaConocimiento = listaAreaConocimiento;
    }

    public List<Unidadaprendizaje> getListaUnidadAprendizaje() {
        return listaUnidadAprendizaje;
    }

    public void setListaUnidadAprendizaje(List<Unidadaprendizaje> listaUnidadAprendizaje) {
        this.listaUnidadAprendizaje = listaUnidadAprendizaje;
    }

    public List<Grupo> getListaGrupo() {
        return listaGrupo;
    }

    public void setListaGrupo(List<Grupo> listaGrupo) {
        this.listaGrupo = listaGrupo;
    }

    public void setRolSeleccionado(String rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

//    public Profesor[] asignar(){
//        Profesor prof[] = getProfesoresSeleccionados();
//        Grupo grup[] = getGrupoSeleccionado();
//        Unidadaprendizaje uniapren[] = getUnidadSeleccionada();
//        for(int x=0; x< prof.length;x++){
//            System.out.println("profesor: " + prof[x].getPronombre());
//            prof[x].getUnidadacademicas().addAll(Arrays.asList(uniapren));
////            for (int i=0;i< uniapren.length;i++){
////                uniapren[x].g
////            }
//        }
//        for (int x=0; x< prof.length;x++){
//            try{
//                asignarGrupoUnidadAprendizajeProfesorDelegate.agregarProfesor(prof[x]);
//            } catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//        return prof;
//        
//    }
//    
    //Criteria
//    public Unidadaprendizaje getUnidadAprenCriteria() {
//        return unidadAprenCriteria;
//    }
//
//    public void setUnidadAprenCriteria(Unidadaprendizaje unidadAprenCriteria) {
//        this.unidadAprenCriteria = unidadAprenCriteria;
//    }
//
//    public Grupo getGrupoCriteria() {
//        return grupoCriteria;
//    }
//
//    public void setGrupoCriteria(Grupo grupoCriteria) {
//        this.grupoCriteria = grupoCriteria;
//    }
//
//    public Planestudio getPlanEstudiCriteria() {
//        return planEstudioCriteria;
//    }
//
//    public void setPlanEstudiCriteria(Planestudio planEstudiCriteria) {
//        this.planEstudioCriteria = planEstudiCriteria;
//    }
//
//    public Profesor getProfesorCriteria() {
//        return profesorCriteria;
//    }
//
//    public void setProfesorCriteria(Profesor profesorCriteria) {
//        this.profesorCriteria = profesorCriteria;
//    }
//
//    public Programaeducativo getProgramaEduCriteria() {
//        return programaEduCriteria;
//    }
//
//    public void setProgramaEduCriteria(Programaeducativo programaEduCriteria) {
//        this.programaEduCriteria = programaEduCriteria;
//    }
//
//    public Areaconocimiento getAreaConocimientoCriteria() {
//        return areaConocimientoCriteria;
//    }
//
//    public void setAreaConocimientoCriteria(Areaconocimiento areaConocimientoCriteria) {
//        this.areaConocimientoCriteria = areaConocimientoCriteria;
//    }

//    public List<Planestudio> getPlanesByPrograma() {
//        if (programaEduCriteria.getPedid() != 0) {
//            planEstudioCriteria.setPesid(0);
//            areaConocimientoCriteria.setAcoid(0);
//            grupoCriteria.setGpoid(0);
//            return getAsignarGrupoUnidadAprendizajeProfesorDelegate().getPlanesByPrograma(programaEduCriteria.getPedid());
//        } else {
//            planEstudioCriteria.setPesid(0);
//            return new ArrayList<Planestudio>();
//        }
//    }
//    public List<Areaconocimiento> getAreasByPlan() {
//        if (planEstudioCriteria.getPesid() != 0) {
//            areaConocimientoCriteria.setAcoid(0);
//            return getAsignarGrupoUnidadAprendizajeProfesorDelegate().getAreasByPlan(planEstudioCriteria.getPesid());
//        } else {
//            areaConocimientoCriteria.setAcoid(0);
//            return new ArrayList<Areaconocimiento>();
//        }
//    }
//    public List<Grupo> getGruposByPlan() {
//        if (planEstudioCriteria.getPesid() != 0) {
//            grupoCriteria.setGpoid(0);
//            return getAsignarGrupoUnidadAprendizajeProfesorDelegate().getGruposByPlan(planEstudioCriteria.getPesid());
//        } else {
//            grupoCriteria.setGpoid(0);
//            return new ArrayList<Grupo>();
//        }
//    }
//    public List<Unidadaprendizaje> getUnidadesByAreaCono() {
//        if (areaConocimientoCriteria.getAcoid() != 0) {
//            unidadAprenCriteria.setUapid(0);
//            return getAsignarGrupoUnidadAprendizajeProfesorDelegate().getUnidadAprendizajeByAreaConocimiento(areaConocimientoCriteria.getAcoid());
//        } else {
//             unidadAprenCriteria.setUapid(0);
//            return new ArrayList<Unidadaprendizaje>();
//        }
//    }
//    public List<Profesor> getProfesorByProgram() {
//        if (programaEduCriteria.getPedid() != 0) {
//            profesorCriteria.setProid(0);
//            return getAsignarProfesorDeleagate().getProfesoresByPrograma(programaEduCriteria.getPedid());
//        } else {
//            profesorCriteria.setProid(0);
//            return new ArrayList<Profesor>();
//        }
//
//    }
//    public List<Profesor> getProfesor2() {
//        if (programaEduCriteria.getPedid() != 0) {
//            profesorCriteria.setProid(0);
//            return getAsignarGrupoUnidadAprendizajeProfesorDelegate().getProfesor(programaEduCriteria.getPedid());
//        } else {
//            profesorCriteria.setProid(0);
//            return new ArrayList<Profesor>();
//        }
//    }
    public List<UnidadaprendizajeImparteProfesor> filtrado(String campo, String busqueda) {
        String cambioBus = busqueda.toLowerCase();
        String cambioObj = "";

        listaFiltrada = asignarGrupoUnidadAprendizajeProfesorDelegate.getListaimparteProfesors();
        for (UnidadaprendizajeImparteProfesor uaip : listaFiltrada) {
            uaip.setGrupo(grupoDelegate.findGrupoById(uaip.getGrupo().getGpoid()));
            uaip.setProfesor(profesorDelegate.findProfesorById(uaip.getProfesor().getProid()));
            uaip.setUnidadaprendizaje(unidadAprendizajeDelegate.findUAById(uaip.getUnidadaprendizaje().getUapid()));
        }
        if (busqueda.trim().length() > 0) {
            listaFiltrada.clear();
            for (UnidadaprendizajeImparteProfesor uaip : asignarGrupoUnidadAprendizajeProfesorDelegate.getListaimparteProfesors()) {
                uaip.setGrupo(grupoDelegate.findGrupoById(uaip.getGrupo().getGpoid()));
                uaip.setProfesor(profesorDelegate.findProfesorById(uaip.getProfesor().getProid()));
                uaip.setUnidadaprendizaje(unidadAprendizajeDelegate.findUAById(uaip.getUnidadaprendizaje().getUapid()));

                cambioObj = uaip.getUiptipoSubgrupo().toLowerCase();
                if (cambioObj.startsWith(cambioBus)) {
                    listaFiltrada.add(uaip);
                } else {
                    cambioObj = uaip.getUipsubgrupo().toLowerCase();
                    if (cambioObj.startsWith(cambioBus)) {
                        listaFiltrada.add(uaip);
                    }
                }

            }
        }

        return listaFiltrada;
    }

    public void seleccionarRegistro() {
        for (UnidadaprendizajeImparteProfesor aguap : AGUAPDelegate.getListaimparteProfesors()) {
            if (aguap.getUipid().equals(selecAGUAP.getUipid())) {
                AGUAP = aguap;
                unidadApren = unidadAprendizajeDelegate.findUAById(aguap.getUnidadaprendizaje().getUapid());
                profesor = profesorDelegate.findProfesorById(aguap.getProfesor().getProid());
                grupo = grupoDelegate.findGrupoById(aguap.getGrupo().getGpoid());

                //aun falta por terminar
            }
        }
    }

    public void eliminarDeLista(int id) {
        for (UnidadaprendizajeImparteProfesor aguap : listaAGUAPSeleccionada) {
            if (aguap.getUipid().equals(id)) {
                int index = listaAGUAPSeleccionada.indexOf(aguap);
                listaAGUAPSeleccionada.remove(index);
                break;
            }
        }
    }

//    public String validarRepetidos(){
//        bandUA = true;
//        bandProf = true;
//        bandGpo = true;
//        bandTipo = true;
//        bandSubgpo = true;
//        mensaje = "";
//        
//        for(UnidadaprendizajeImparteProfesor aguap : asignarGrupoUnidadAprendizajeProfesorDelegate.getListaimparteProfesors()){            
//            if(aguap.getUnidadaprendizaje().getUapid().equals(unidadApren.getUapid()) && bandUA == true
//                    && !aguap.getUipid().equals(AGUAP.getUipid())){
//                mensaje = mensaje + "[UnidadAprendizaje]";
//                bandUA = false;
//            }
//            
//            if(aguap.getProfesor().getProid().equals(profesor.getProid()) && bandProf == true
//                    && !aguap.getProfesor().equals(AGUAP.getUipid())){
//                mensaje = mensaje + "[Profesor]";
//                bandProf = false;
//            }
//            
//            if(aguap.getGrupo().getGpoid().equals(grupo.getGpoid()) && bandGpo == true
//                    && !aguap.getGrupo().equals(AGUAP.getUipid())){
//                mensaje =  mensaje + "[Grupo]";
//                bandGpo = false;
//            }
//            
//            if(aguap.getUiptipoSubgrupo().equals(AGUAP.getUiptipoSubgrupo()) && bandTipo == true
//                    && !aguap.getUiptipoSubgrupo().equals(AGUAP.getUipid())){
//                mensaje = mensaje + "[Tipo]";
//                bandTipo = false;
//            }
//            
//            if(aguap.getUipsubgrupo().equals(AGUAP.getUipsubgrupo()) && bandSubgpo == true
//                    && !aguap.getUipsubgrupo().equals(AGUAP.getUipid())){
//                mensaje = mensaje + "[Subgrupo]";
//                bandSubgpo = false;
//            }
//        }
//        return mensaje;
//    }  
    
    public String validarRepetidos() {
        bandUA = true;
        bandProf = true;
        bandGpo = true;
        bandTipo = true;
        bandSubgpo = true;
        mensaje = "";

        for (UnidadaprendizajeImparteProfesor aguap : AGUAPDelegate.getListaimparteProfesors()) {
            
            //System.out.println("validarRep, ");
            
            if (aguap.getGrupo().getGpoid().equals(grupo.getGpoid()) && bandGpo == true
                     
                    && aguap.getUipsubgrupo().equals(AGUAP.getUipsubgrupo())
                    && bandSubgpo == true) {
//                mensaje = mensaje + "[Grupo]";
                bandGpo = false;
                System.out.println("primer if = " + unidadApren.getUapid());
                //System.out.println("primer if otro UA = " + );
//
//                if (aguap.getUnidadaprendizaje().getUapid().equals(unidadApren.getUapid()) && bandUA == true
//                        && !aguap.getUipid().equals(AGUAP.getUipid())) {
//                    bandUA = false;
//                    System.out.println("segundo if");
//
//                    if (aguap.getProfesor().getProid().equals(profesor.getProid()) && bandProf == true
//                            && !aguap.getUipid().equals(AGUAP.getUipid())) {
//                        bandProf = false;
//                        System.out.println("tercer if");
//                        
//                        if (aguap.getUiptipoSubgrupo().equals(AGUAP.getUiptipoSubgrupo()) && bandTipo == true) {
                            bandTipo = false;
                            System.out.println("cuarto IF");
//
//                            if (aguap.getUipsubgrupo().equals(AGUAP.getUipsubgrupo()) && bandSubgpo == true
//                                    && !aguap.getUipid().equals(AGUAP.getUipid())) {
                                mensaje = "Profesor ya asignado";
//                                bandSubgpo = false;       
//                                System.out.println("quinto IF");
//                                System.out.println("BEAN HELPER mensaje dentro del IF = " + mensaje);
//                            }
//                        }
//                    }
//                }
            }
        }
        System.out.println("BEAN HELPER mensaje = " + mensaje);
        return mensaje;
    }
    public void getUsuarioTienePE() {
        listaProgramaEducativo = programaEducativoDelegate.getListaProgramaEducativo();
        try {
            listaProgramaEducativo.clear();
        } catch (NullPointerException e) {
        }
        if (rolSeleccionado.equalsIgnoreCase("Director") || rolSeleccionado.equalsIgnoreCase("Subdirector") || rolSeleccionado.equalsIgnoreCase("Administrador")) {
            profesor = profesorDelegate.findProfesorFromUser(usuario.getUsuid());
            listaUnidadAcademica = unidadAcademicaDelegate.getProfUAC(profesor.getProid());
            listaProgEduc = programaEducativoDelegate.getListaProgramaEducativo();
            for (Unidadacademica uac : listaUnidadAcademica) {
                for (Programaeducativo pe : listaProgEduc) {
                    if (uac.getUacid() == pe.getUnidadacademica().getUacid()) {
                        listaProgramaEducativo.add(pe);
                    }
                }
            }
        }
    }    
    
//    public void filtrarPlanPorPE(){
//        listaPlanEstudio = planEstudioDelegate.buscarPlanEstudio(programaEducativo.getPedid());
//    }
//    
//    public void filtrarAreaYGpoPorPlan(){
//        listaAreaConocimiento = areaConocimientoDelegate.getAreaMismoPlan(planEstudio.getPesid());
//        listaGrupo = grupoDelegate.getGpoMismoPlan(planEstudio.getPesid());
//    }
//    
//    public void filtrarUAPorArea(){
//        listaUnidadAprendizaje = unidadAprendizajeDelegate.getUAMismaArea(areaConocimiento.getAcoid());
//    }

}
