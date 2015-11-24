package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import mx.avanti.siract.business.AreaAdministrativaDelegate;
import mx.avanti.siract.business.AreaConocimientoDelegate;
import mx.avanti.siract.business.AsignarGrupoUnidadAprendizajeProfesorDelegate;
import mx.avanti.siract.business.CoordinadorAreaAdministrativaDelegate;
import mx.avanti.siract.business.GrupoDelegate;
import mx.avanti.siract.business.PlanEstudioDelegate;
import mx.avanti.siract.business.ProfesorDelegate;
import mx.avanti.siract.business.ProgramaEducativoDelegate;
import mx.avanti.siract.business.UnidadAcademicaDelegate;
import mx.avanti.siract.business.UnidadAprendizajeDelegate;
import mx.avanti.siract.business.UsuarioDelegate;
import mx.avanti.siract.business.entity.Areaadministrativa;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Coordinadorareaadministrativa;
import mx.avanti.siract.business.entity.CoordinadorareaadministrativaId;
import mx.avanti.siract.business.entity.Grupo;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Unidadacademica;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.business.entity.UnidadaprendizajeImparteProfesor;
import mx.avanti.siract.business.entity.Usuario;
import mx.avanti.siract.integration.persistence.BaseDAO;
import mx.avanti.siract.ui.AsignarAreaAdministrativaBeanUI;
import mx.avanti.siract.ui.LoginBean;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

public class AsignarAreaAdministrativaBeanHelper implements Serializable {

    private LoginBean loginBeanUI;
    
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
   
    //Objetos de Marco
    private Usuario usuario;
   
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

    public AsignarAreaAdministrativaBeanHelper() {
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
        AGUAP = new UnidadaprendizajeImparteProfesor(new Unidadaprendizaje(), new Profesor() ,new Grupo());
    }
    
    
    public LoginBean getLoginBeanUI() {
        return loginBeanUI;
    }

    public void setLoginBeanUI(LoginBean loginBeanUI) {
        this.loginBeanUI = loginBeanUI;
    }
    
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
//        System.out.println("/////////////////////////"+programaEducativo.getPednombre());
        return programaEducativo;
    }

    public void setProgramaEducativo(Programaeducativo programaEducativo) {
        this.programaEducativo = programaEducativo;
    }

    public List<Programaeducativo> getListaProgEduc() {
        return listaProgEduc;
    }

    public void setListaProgEduc(List<Programaeducativo> listaProgEduc) {
        this.listaProgEduc = listaProgEduc;
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
    
    public List<Coordinadorareaadministrativa> filtrado(String busqueda) {
        List <Coordinadorareaadministrativa> listaFiltrada2 = careaAdministrativaDelegate.consultarAreaAdministrativa();
        listaFiltrada2.clear();
        
        String cambioBus = busqueda.toLowerCase();
        String cambioObj = "";

        cargarTodo();
        if(programaEducativo.getPedid() != null && !programaEducativo.getPedid().equals(0)){
            List<Areaadministrativa> areaadministrativas=areaAdministrativaDelegate.getAreaAdbyPE(programaEducativo.getPedid());
                Areaadministrativa a=new Areaadministrativa();
                cs=careaAdministrativaDelegate.consultarAreaAdministrativa();
                List<Coordinadorareaadministrativa> coordinadorareaadministrativas=new ArrayList<Coordinadorareaadministrativa>();
                for(Areaadministrativa a1 : areaadministrativas){
                    for(int i=0; i<cs.size(); i++){
                        if(cs.get(i).getAreaadministrativa().getAadid()==a1.getAadid()){
                            a=areaAdministrativaDelegate.findAreaAdById(cs.get(i).getAreaadministrativa().getAadid());
                            a.setProgramaeducativo(programaEducativoDelegate.findProgramaEducativoById(a.getProgramaeducativo().getPedid()));
                            cs.get(i).setAreaadministrativa(a);
                            cs.get(i).setProfesor(profesorDelegate.findProfesorById(cs.get(i).getProfesor().getProid()));
                            cs.get(i).setUnidadaprendizaje(unidadAprendizajeDelegate.findUAById(cs.get(i).getUnidadaprendizaje().getUapid()));
                            cs.set(i, cs.get(i));
                            coordinadorareaadministrativas.add(cs.get(i));
                            a=new Areaadministrativa();
                        }else{
                            a=new Areaadministrativa();
                        }
                    }
                }
                setCs(coordinadorareaadministrativas);
        }
        if (busqueda.trim().length() > 0) {
            listaFiltrada2.clear();
            for (Coordinadorareaadministrativa caa : cs) {
                caa.setAreaadministrativa(areaAdministrativaDelegate.findAreaAdById(caa.getAreaadministrativa().getAadid()));
                caa.getAreaadministrativa().setProgramaeducativo(programaEducativoDelegate.findProgramaEducativoById(caa.getAreaadministrativa().getProgramaeducativo().getPedid()));
                caa.setProfesor(profesorDelegate.findProfesorById(caa.getProfesor().getProid()));
                caa.setUnidadaprendizaje(unidadAprendizajeDelegate.findUAById(caa.getUnidadaprendizaje().getUapid()));

                cambioObj = caa.getAreaadministrativa().getAadnombre().toLowerCase();
                if (cambioObj.startsWith(cambioBus)) {
                    listaFiltrada2.add(caa);
                } else {
                    cambioObj = caa.getProfesor().getPronombre().toLowerCase();
                    if (cambioObj.startsWith(cambioBus)) {
                        listaFiltrada2.add(caa);
                    }
                    else{
                        cambioObj = Integer.toString(caa.getProfesor().getPronumeroEmpleado());
                        if (cambioObj.startsWith(cambioBus)) {
                            listaFiltrada2.add(caa);
                        }else{
                            cambioObj = caa.getUnidadaprendizaje().getUapnombre().toLowerCase();
                            if(cambioObj.startsWith(cambioBus)){
                                listaFiltrada2.add(caa);
                            }else{
                                cambioObj = Integer.toString(caa.getUnidadaprendizaje().getUapclave());
                                if(cambioObj.startsWith(cambioBus)){
                                    listaFiltrada2.add(caa);
                                }
                            }
                        }
                    }
                }

            }
            cs = listaFiltrada2;
        }
//        else{
//            cargarTodo();
//        }

        return listaFiltrada2;
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

//    public void eliminarDeLista(int id) {
//        for (UnidadaprendizajeImparteProfesor aguap : listaAGUAPSeleccionada) {
//            if (aguap.getUipid().equals(id)) {
//                int index = listaAGUAPSeleccionada.indexOf(aguap);
//                listaAGUAPSeleccionada.remove(index);
//                break;
//            }
//        }
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
        System.out.println(profesor.getPronombre());
        System.out.println(listaProgEduc.get(0).getPednombre());
    }    
    
    public void filtrarPlanPorPE(){
        listaPlanEstudio = planEstudioDelegate.getPlanMismoPE(programaEducativo.getPedid());
    }
    
    public void filtrarAreaYGpoPorPlan(){
        listaAreaConocimiento = areaConocimientoDelegate.getAreaMismoPlan(planEstudio.getPesid());
        listaGrupo = grupoDelegate.getGpoMismoPlan(planEstudio.getPesid());
    }
    
    public void filtrarUAPorArea(){
        listaUnidadAprendizaje = unidadAprendizajeDelegate.getUAMismaArea(areaConocimiento.getAcoid());
    }
//---------------------------------------------------------------------------------------------------------
    int profID=0;
    int uapID=0;
    
    //
    List<Coordinadorareaadministrativa> cs=new ArrayList<Coordinadorareaadministrativa>();
    private List<Profesor> listaProfesor=new ArrayList<Profesor>();
    private List<Unidadaprendizaje> listaUnidades=new ArrayList<Unidadaprendizaje>();
    private List<Unidadaprendizaje> listaUASel=new ArrayList<Unidadaprendizaje>();
    List<String>areas=new ArrayList<String>();
    Areaadministrativa area=new Areaadministrativa();;
    private Profesor profesor=new Profesor();    
    //
    
    Coordinadorareaadministrativa caaSeleccionada;
    List<String>unidadesAp=new ArrayList<String>();
    
    public int getProfID() {
        return profID;
    }

    public void setProfID(int profID) {
        this.profID = profID;
    }

    public int getUapID() {
        return uapID;
    }

    public void setUapID(int uapID) {
        this.uapID = uapID;
    }

    
//---------
    public List<Coordinadorareaadministrativa> getCs() {
        return cs;
    }

    public void setCs(List<Coordinadorareaadministrativa> cs) {
        this.cs = cs;
    }

    public List<Profesor> getListaProfesor() {
        return listaProfesor;
    }

    public void setListaProfesor(List<Profesor> listaProfesor) {
        this.listaProfesor = listaProfesor;
    }

    public List<Unidadaprendizaje> getListaUnidades() {
        return listaUnidades;
    }

    public void setListaUnidades(List<Unidadaprendizaje> listaUnidades) {
        this.listaUnidades = listaUnidades;
    }

    public List<Unidadaprendizaje> getListaUASel() {
        return listaUASel;
    }

    public void setListaUASel(List<Unidadaprendizaje> listaUASel) {
        this.listaUASel = listaUASel;
    }
    
   public List<String> getAreas() {
        return areas;
    }

    public void setAreas(List<String> areas) {
        this.areas = areas;
    }

    public Areaadministrativa getArea() {
        return area;
    }

    public void setArea(Areaadministrativa area) {
        this.area = area;
    }
    
    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
//----------
    public Coordinadorareaadministrativa getCaaSeleccionada() {
        return caaSeleccionada;
    }

    public void setCaaSeleccionado(Coordinadorareaadministrativa caaSeleccionada) {
        this.caaSeleccionada = caaSeleccionada;
    }

    

    public List<String> getUnidadesAp() {
        return unidadesAp;
    }

    public void setUnidadesAp(List<String> unidadesAp) {
        this.unidadesAp = unidadesAp;
    }
    
    
    
    BaseDAO baseDAO=new BaseDAO();
    //-----------------------------bueno :v
    CoordinadorAreaAdministrativaDelegate careaAdministrativaDelegate=new CoordinadorAreaAdministrativaDelegate();
    AreaAdministrativaDelegate areaAdministrativaDelegate=new AreaAdministrativaDelegate();
    //
    List<Areaadministrativa> areaadministrativas=new ArrayList<Areaadministrativa>();
    List<Programaeducativo> programaeducativos=new ArrayList<Programaeducativo>();
    List<Planestudio> planestudios=new ArrayList<Planestudio>();
    List<Unidadaprendizaje> unidadaprendizajes=new ArrayList<Unidadaprendizaje>();
    List<Profesor> profesors=new ArrayList<Profesor>();
    //
    Programaeducativo pedSeleccion=new Programaeducativo();
    Planestudio pesSeleccion=new Planestudio();

    public List<Areaadministrativa> getAreaadministrativas() {
        return areaadministrativas;
    }

    public void setAreaadministrativas(List<Areaadministrativa> areaadministrativas) {
        this.areaadministrativas = areaadministrativas;
    }

    public List<Programaeducativo> getProgramaeducativos() {
        return programaeducativos;
    }

    public void setProgramaeducativos(List<Programaeducativo> programaeducativos) {
        this.programaeducativos = programaeducativos;
    }

    public List<Planestudio> getPlanestudios() {
        return planestudios;
    }

    public void setPlanestudios(List<Planestudio> planestudios) {
        this.planestudios = planestudios;
    }

    public Programaeducativo getPedSeleccion() {
        return pedSeleccion;
    }

    public void setPedSeleccion(Programaeducativo pedSeleccion) {
        this.pedSeleccion = pedSeleccion;
    }

    public Planestudio getPesSeleccion() {
        return pesSeleccion;
    }

    public void setPesSeleccion(Planestudio pesSeleccion) {
        this.pesSeleccion = pesSeleccion;
    }

    public List<Unidadaprendizaje> getUnidadaprendizajes() {
        return unidadaprendizajes;
    }

    public void setUnidadaprendizajes(List<Unidadaprendizaje> unidadaprendizajes) {
        this.unidadaprendizajes = unidadaprendizajes;
    }

    public List<Profesor> getProfesors() {
        return profesors;
    }

    public void setProfesors(List<Profesor> profesors) {
        this.profesors = profesors;
    }

    public CoordinadorAreaAdministrativaDelegate getCareaAdministrativaDelegate() {
        return careaAdministrativaDelegate;
    }

    public void setCareaAdministrativaDelegate(CoordinadorAreaAdministrativaDelegate careaAdministrativaDelegate) {
        this.careaAdministrativaDelegate = careaAdministrativaDelegate;
    }
    
    
    
    
    public void cargarPED(){
        programaeducativos = programaEducativoDelegate.getListaProgramaEducativo();
    }
    
    public void cargarPES(){
        planestudios=planEstudioDelegate.getListaPlanEstudio();
    }
    
    public void cargarUnidades(){
        unidadaprendizajes=unidadAprendizajeDelegate.getListaUnidadAprendizaje();
    }
    
    public void cargarProfesores(){
        profesors=profesorDelegate.getListaProfesor();
    }
    
    public void cargarTodo(){
        cargarPED();
        cargarPES();
        cargarProfesores();
        cargarUnidades();
        consultarAreasAdministrativas();
        //consultarAreas();
    }
    
//    public void consultarAreasAdministrativas(){ // este 
//        Areaadministrativa a=new Areaadministrativa();
//        cs=careaAdministrativaDelegate.consultarAreaAdministrativa();
//        for(int i=0; i<cs.size(); i++){
//            a=areaAdministrativaDelegate.findAreaAdById(cs.get(i).getAreaadministrativa().getAadid());
//            a.setProgramaeducativo(programaEducativoDelegate.findProgramaEducativoById(a.getProgramaeducativo().getPedid()));
//            cs.get(i).setAreaadministrativa(a);
//            cs.get(i).setProfesor(profesorDelegate.findProfesorById(cs.get(i).getProfesor().getProid()));
//            cs.get(i).setUnidadaprendizaje(unidadAprendizajeDelegate.findUAById(cs.get(i).getUnidadaprendizaje().getUapid()));
//            cs.set(i, cs.get(i));
//            a=new Areaadministrativa();
//        }
//    }
    
    public void consultarAreasAdministrativas(){ // este 
        if(rolSeleccionado.equalsIgnoreCase("Director")||rolSeleccionado.equalsIgnoreCase("Subdirector")||rolSeleccionado.equalsIgnoreCase("Administrador")){
            System.out.println("Soy director, sub o admin");
            Areaadministrativa a=new Areaadministrativa();
            cs=careaAdministrativaDelegate.consultarAreaAdministrativa();
            for(int i=0; i<cs.size(); i++){
                a=areaAdministrativaDelegate.findAreaAdById(cs.get(i).getAreaadministrativa().getAadid());
                a.setProgramaeducativo(programaEducativoDelegate.findProgramaEducativoById(a.getProgramaeducativo().getPedid()));
                cs.get(i).setAreaadministrativa(a);
                cs.get(i).setProfesor(profesorDelegate.findProfesorById(cs.get(i).getProfesor().getProid()));
                cs.get(i).setUnidadaprendizaje(unidadAprendizajeDelegate.findUAById(cs.get(i).getUnidadaprendizaje().getUapid()));
                cs.set(i, cs.get(i));
                a=new Areaadministrativa();
            }
        }else{
            if(rolSeleccionado.equalsIgnoreCase("Responsable de Programa Educativo")){
                System.out.println("soy RPE :P");
                Profesor profesorLog = profesorDelegate.findProfesorFromUser(usuario.getUsuid());
                System.out.println(programaEducativo.getPednombre()+"-----------qqqqq-----------------------------------------------"+programaEducativo.getPedid());
                System.out.println(profesorLog.getPronombre()+" "+profesorLog.getProid());
                //programaEducativo = programaEducativoDelegate.getResponsablePE(profesorLog.getProid()).get(0);
                //setProgramaEducativo(programaEducativoDelegate.getPEdeResponsable(profesorLog.getProid()));
                setProgramaEducativo(programaEducativoDelegate.getResponsablePE(profesorLog.getProid()).get(0));
                List<Areaadministrativa> areaadministrativas=areaAdministrativaDelegate.getAreaAdbyPE(programaEducativo.getPedid());
                Areaadministrativa a=new Areaadministrativa();
                cs=careaAdministrativaDelegate.consultarAreaAdministrativa();
                List<Coordinadorareaadministrativa> coordinadorareaadministrativas=new ArrayList<Coordinadorareaadministrativa>();
                for(Areaadministrativa a1 : areaadministrativas){
                    for(int i=0; i<cs.size(); i++){
                        if(cs.get(i).getAreaadministrativa().getAadid()==a1.getAadid()){
                            a=areaAdministrativaDelegate.findAreaAdById(cs.get(i).getAreaadministrativa().getAadid());
                            a.setProgramaeducativo(programaEducativoDelegate.findProgramaEducativoById(a.getProgramaeducativo().getPedid()));
                            cs.get(i).setAreaadministrativa(a);
                            cs.get(i).setProfesor(profesorDelegate.findProfesorById(cs.get(i).getProfesor().getProid()));
                            cs.get(i).setUnidadaprendizaje(unidadAprendizajeDelegate.findUAById(cs.get(i).getUnidadaprendizaje().getUapid()));
                            cs.set(i, cs.get(i));
                            coordinadorareaadministrativas.add(cs.get(i));
                            a=new Areaadministrativa();
                        }else{
                            a=new Areaadministrativa();
                        }
                    }
                }
                setCs(coordinadorareaadministrativas);
                setProgramaEducativo(programaEducativoDelegate.getResponsablePE(profesorLog.getProid()).get(0));
                System.out.println(programaEducativo.getPednombre()+"---------------aaa-------------------------------------------"+programaEducativo.getPedid());
            }else{
                
            if(rolSeleccionado.equalsIgnoreCase("Coordinador de Área de Conocimiento")){
                  System.out.println("soy coordinador area :P");
                Profesor profesorLog = profesorDelegate.findProfesorFromUser(usuario.getUsuid());
                System.out.println(programaEducativo.getPednombre()+"-----------qqqqq-----------------------------------------------"+programaEducativo.getPedid());
                System.out.println(profesorLog.getPronombre()+" "+profesorLog.getProid());
                //programaEducativo = programaEducativoDelegate.getResponsablePE(profesorLog.getProid()).get(0);
                //setProgramaEducativo(programaEducativoDelegate.getPEdeResponsable(profesorLog.getProid()));
                setProgramaEducativo(programaEducativoDelegate.getPEdeCoordinadorAreaAdmin(profesorLog.getProid()).get(0));
                List<Areaadministrativa> areaadministrativas=areaAdministrativaDelegate.getAreaAdbyPE(programaEducativo.getPedid());
                Areaadministrativa a=new Areaadministrativa();
                cs=careaAdministrativaDelegate.consultarAreaAdministrativa();
                List<Coordinadorareaadministrativa> coordinadorareaadministrativas=new ArrayList<Coordinadorareaadministrativa>();
                for(Areaadministrativa a1 : areaadministrativas){
                    for(int i=0; i<cs.size(); i++){
                        if(cs.get(i).getAreaadministrativa().getAadid()==a1.getAadid()){
                            a=areaAdministrativaDelegate.findAreaAdById(cs.get(i).getAreaadministrativa().getAadid());
                            a.setProgramaeducativo(programaEducativoDelegate.findProgramaEducativoById(a.getProgramaeducativo().getPedid()));
                            cs.get(i).setAreaadministrativa(a);
                            cs.get(i).setProfesor(profesorDelegate.findProfesorById(cs.get(i).getProfesor().getProid()));
                            cs.get(i).setUnidadaprendizaje(unidadAprendizajeDelegate.findUAById(cs.get(i).getUnidadaprendizaje().getUapid()));
                            cs.set(i, cs.get(i));
                            coordinadorareaadministrativas.add(cs.get(i));
                            a=new Areaadministrativa();
                        }else{
                            a=new Areaadministrativa();
                        }
                    }
                }
                setCs(coordinadorareaadministrativas);
                setProgramaEducativo(programaEducativoDelegate.getResponsablePE(profesorLog.getProid()).get(0));
                System.out.println(programaEducativo.getPednombre()+"---------------aaa-------------------------------------------"+programaEducativo.getPedid());
                    
                }
            }
        }
    }
    
    //--------------------------------
    public void consultarACA(){ // Trae los registros de la tabla area administrativa -- ok
        cs=careaAdministrativaDelegate.consultarAreaAdministrativa();
        areaadministrativas=areaAdministrativaDelegate.consultarAreaAdministrativa();
        for(int q=0; q<areaadministrativas.size(); q++)
            System.out.println(areaadministrativas.get(q).getAadnombre());
        for(int i=0; i<cs.size(); i++){
            cs.get(i).setProfesor(profesorDelegate.findProfesorById(cs.get(i).getProfesor().getProid()));
            cs.get(i).setUnidadaprendizaje(unidadAprendizajeDelegate.findUAById(cs.get(i).getUnidadaprendizaje().getUapid()));
            cs.get(i).setAreaadministrativa(areaAdministrativaDelegate.findAreaAdById(cs.get(i).getAreaadministrativa().getAadid()));
            cs.set(i, cs.get(i));
            System.out.println(cs.get(i).getUnidadaprendizaje().getUapnombre()+" "+cs.get(i).getProfesor().getPronombre());
        }
        //
        consultarProfesores();
        consultarUnidadesAp();
        consultarAreas();
    }
    
    public void consultarProfesores(){ //Trae los profesores de la bd -- ok
        listaProfesor=profesorDelegate.getProfesores();
    }
    
    public void consultarUnidadesAp(){ //Trae las UAP de la bd -- ok
        listaUnidades=unidadAprendizajeDelegate.getListaUnidadAprendizaje(); 
    }
    //AsignarAreaAdministrativaBeanUI aaabui=new AsignarAreaAdministrativaBeanUI();
    public void onClickSubmit() { //Guarda las asignaciones --  ok
        if(AsignarAreaAdministrativaBeanUI.header.equalsIgnoreCase("Asignar Coordinación Área Administrativa")){
        Coordinadorareaadministrativa c=new Coordinadorareaadministrativa();
        Unidadaprendizaje u=new Unidadaprendizaje();
        CoordinadorareaadministrativaId ci;
        System.out.println(unidadesAp.size());
        
        for(int q=0; q<unidadesAp.size(); q++){//asi :v
            try{
            System.out.println(q);
            u=unidadAprendizajeDelegate.findUAById(Integer.parseInt(unidadesAp.get(q)));
            ci=new CoordinadorareaadministrativaId(profesor.getProid(), u.getUapid());
            c.setId(ci);
            c.setAreaadministrativa(getArea());
            careaAdministrativaDelegate.asignarAreaAdministrativa(c);
            }catch(Exception e){
                e.printStackTrace();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Este profesor ya esta asignado al mismo grupo");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
            ci=new CoordinadorareaadministrativaId();
            c=new Coordinadorareaadministrativa();
            u=new Unidadaprendizaje();
        }
        setArea(new Areaadministrativa());
        unidadesAp=new ArrayList<String>();
        setProfesor(new Profesor());
        consultarAreasAdministrativas();
        }else if (AsignarAreaAdministrativaBeanUI.header.equalsIgnoreCase("Modificar Asignación Área Administrativa")) {
            try{
                System.out.println("Llegue a modificar");
            Coordinadorareaadministrativa c=getCaaSeleccionada();
            CoordinadorareaadministrativaId ci=getCaaSeleccionada().getId();
            System.out.println(getCaaSeleccionada().getProfesor().getPronombre()+"-----------------------");
            careaAdministrativaDelegate.eliminarAsignacion(getCaaSeleccionada());//quitar si no sirve
            ci.setProfesorProid(getProfesor().getProid());
            c.setId(ci);
            setCaaSeleccionado(c);
            careaAdministrativaDelegate.asignarAreaAdministrativa(getCaaSeleccionada());
            System.out.println(getCaaSeleccionada().getProfesor().getPronombre()+"-----------------------");
            setProfesor(new Profesor());
            c=new Coordinadorareaadministrativa();
            consultarAreasAdministrativas();
            
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }    
    
    public void guardar(){
        Coordinadorareaadministrativa c=new Coordinadorareaadministrativa();
        CoordinadorareaadministrativaId ci=new CoordinadorareaadministrativaId(19, 599);
        areaadministrativas=areaAdministrativaDelegate.consultarAreaAdministrativa();
        c.setAreaadministrativa(areaadministrativas.get(0));
        c.setId(ci);
        careaAdministrativaDelegate.asignarAreaAdministrativa(c);
    }
    
    public void seleccion(SelectEvent event){
        Coordinadorareaadministrativa c=new Coordinadorareaadministrativa();
        c=(Coordinadorareaadministrativa)event.getObject();
        c.setAreaadministrativa(areaAdministrativaDelegate.findAreaAdById(c.getAreaadministrativa().getAadid()));
        c.setProfesor(profesorDelegate.findProfesorById(c.getProfesor().getProid()));
        c.setUnidadaprendizaje(unidadAprendizajeDelegate.findUAById(c.getUnidadaprendizaje().getUapid()));
        setCaaSeleccionado(c);
    }
    
    public void eliminarCAA(){
        Coordinadorareaadministrativa c=getCaaSeleccionada();
        careaAdministrativaDelegate.eliminarAsignacion(c);
        c=new Coordinadorareaadministrativa();
        setCaaSeleccionado(new Coordinadorareaadministrativa());
        consultarAreasAdministrativas();
    }
    
    public void consultarAreas(){ // ok -- pero usa el basedao aqui -- trae el nombre de las areas de la bd
        List<Areaadministrativa> as=areaAdministrativaDelegate.consultarAreaAdministrativa();
        areaadministrativas=new ArrayList<Areaadministrativa>();
        for(int i=0; i<as.size(); i++){
            if(as.get(i).getProgramaeducativo().getPedid()==getProgramaEducativo().getPedid())
                areaadministrativas.add(as.get(i));                
        }
    }
    
    //---------------------------------------
    String uapSel="";

    public String getUapSel() {
        return uapSel;
    }

    public void setUapSel(String uapSel) {
        this.uapSel = uapSel;
    }

    
//    public void unidadesDelArea(){
//        uapSel=" ";
//        baseDAO.setTipo(Unidadaprendizaje.class);
//        List<Unidadaprendizaje> list=baseDAO.findFromWhere("coordinadorareaadministrativas", "areaadministrativa.aadid", getAaSeleccionada().getAadid().toString());
//        for(int y=0; y<list.size(); y++)
//            uapSel=" "+uapSel+"-"+list.get(y).getUapnombre();
//        setUapSel(uapSel);
//        System.out.println(uapSel);
//        coordinadorDelArea();
//    }
    //---------------------prueba
    Profesor coordinador=new Profesor();

    public Profesor getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(Profesor coordinador) {
        this.coordinador = coordinador;
    }
    
    
//    public void coordinadorDelArea(){
//        baseDAO.setTipo(Profesor.class);
//        List<Profesor> list=baseDAO.findFromWhere("coordinadorareaadministrativas", "areaadministrativa.aadid", getAaSeleccionada().getAadid().toString());
//        setCoordinador(list.get(0));
//    }
    
    
    //---------------------------------------------
    UsuarioDelegate usuarioDelegate=new UsuarioDelegate();
    public void cargarPEDbyUsuario(){
        //Usuario u=getUsuario();
        Usuario u=usuarioDelegate.findUsuarioById(56);
        Profesor p=profesorDelegate.findProfesorFromUser(u.getUsuid());
        Programaeducativo ped=programaEducativoDelegate.findProgramaEducativoById(13);
        List<Planestudio> planestudios=planEstudioDelegate.getPlanMismoPE(ped.getPedid());
        List<Planestudio> pes=planEstudioDelegate.getListaPlanEstudio();
        //List<Areaconocimiento> aac=areaConocimientoDelegate.getAreaMismoPlan(pes.get(0).getPesid());
        //List<Unidadaprendizaje> uap=unidadAprendizajeDelegate.getUAMismaArea(aac.get(0).getAcoid());
        planestudios=planEstudioDelegate.getListaPlanEstudio();
        for(int i=0; i<pes.size(); i++){
            if(pes.get(i).getProgramaeducativo().getPedid()==ped.getPedid())
                planestudios.add(pes.get(i));
        }
        
        System.out.println(u.getUsuusuario());
        System.out.println(p.getPronombre());
        System.out.println(ped.getPednombre());
        for(int i=0; i<planestudios.size(); i++)
            System.out.println(planestudios.get(i).getPesvigenciaPlan());
        //for(int i=0; i<aac.size(); i++)
        //    System.out.println(aac.get(i).getAconombre());
        //for(int i=0; i<uap.size(); i++)
        //    System.out.println(uap.get(i).getUapnombre());
    }
}