/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.List;
import mx.avanti.siract.business.AreaConocimientoDelegate;
import mx.avanti.siract.business.PlanEstudioDelegate;
import mx.avanti.siract.business.ProfesorDelegate;
import mx.avanti.siract.business.ProgramaEducativoDelegate;
import mx.avanti.siract.business.UnidadAcademicaDelegate;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Unidadacademica;
import mx.avanti.siract.business.entity.Usuario;

/**
 *
 * @author balta
 */
public class CapturaAreaConocimientoBeanHelper implements Serializable{
    private AreaConocimientoDelegate areaConocimientoDelegate;
    private Areaconocimiento areaConocimiento;
    private Areaconocimiento selecAreaconocimiento;
    private Planestudio planestudio;
    private List<Planestudio> listaPlanEstudio;
    
    private UnidadAcademicaDelegate unidadacadelegate;
    
    private ProgramaEducativoDelegate programaeducativodelegate;    
    private Programaeducativo programaeducativo;
    private List<Programaeducativo> listaFiltradaPed;
    private List<Programaeducativo> listaProgramaEducativo;
    
    private PlanEstudioDelegate planEstudioDelegate;
    private List<Areaconocimiento> listaFiltrada;
    private List<Areaconocimiento> listaFiltrada2;
    private List<Areaconocimiento> listaFiltrada3;
    private List<Areaconocimiento> listaFiltrada4;    
    private List<Areaconocimiento> listaSeleccionAcon;
    private boolean blnArea;
    private boolean bandera;

    private Usuario usuario;
    private Profesor profesor;
    private List<Unidadacademica> listaUnidadAcademica;
    private String rolSeleccionado;
    private ProfesorDelegate profesorDelegate;
    
    private String mensaje="";
    
    private List<String> listaFiltrada2S;
    
    public CapturaAreaConocimientoBeanHelper(){
        try{
            this.areaConocimientoDelegate = new AreaConocimientoDelegate();
            planEstudioDelegate= new PlanEstudioDelegate();
            programaeducativodelegate=new ProgramaEducativoDelegate();
            profesorDelegate = new ProfesorDelegate();
            unidadacadelegate = new UnidadAcademicaDelegate();

        } catch(Exception ex){
            ex.printStackTrace();
        }
        areaConocimiento = new Areaconocimiento();
        selecAreaconocimiento = new Areaconocimiento();
        planestudio =new Planestudio();
        programaeducativo=new Programaeducativo();
    }

    public List<Programaeducativo> getListaProgramaEducativo() {
        return listaProgramaEducativo;
    }

    public void setListaProgramaEducativo(List<Programaeducativo> listaProgramaEducativo) {
        this.listaProgramaEducativo = listaProgramaEducativo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Unidadacademica> getListaUnidadAcademica() {
        return listaUnidadAcademica;
    }

    public void setListaUnidadAcademica(List<Unidadacademica> listaUnidadAcademica) {
        this.listaUnidadAcademica = listaUnidadAcademica;
    }

    public String getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(String rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public ProfesorDelegate getProfesorDelegate() {
        return profesorDelegate;
    }

    public void setProfesorDelegate(ProfesorDelegate profesorDelegate) {
        this.profesorDelegate = profesorDelegate;
    }

    public List<Planestudio> getListaPlanEstudio() {
        return listaPlanEstudio;
    }

    public void setListaPlanEstudio(List<Planestudio> listaPlanEstudio) {
        this.listaPlanEstudio = listaPlanEstudio;
    }
    
    public List<String> getListaFiltrada2S() {
        return listaFiltrada2S;
    }

    public void setListaFiltrada2S(List<String> listaFiltrada2S) {
        this.listaFiltrada2S = listaFiltrada2S;
    }
    
    public ProgramaEducativoDelegate getProgramaeducativodelegate() {
        return programaeducativodelegate;
    }

    public void setProgramaeducativodelegate(ProgramaEducativoDelegate programaeducativodelegate) {
        this.programaeducativodelegate = programaeducativodelegate;
    }
    
    public UnidadAcademicaDelegate getUnidadacadelegate() {
        return unidadacadelegate;
    }

    public void setUnidadacadelegate(UnidadAcademicaDelegate unidadacadelegate) {
        this.unidadacadelegate = unidadacadelegate;
    }

    public Programaeducativo getProgramaeducativo() {
        return programaeducativo;
    }

    public void setProgramaeducativo(Programaeducativo programaeducativo) {
        this.programaeducativo = programaeducativo;
    }

    public AreaConocimientoDelegate getAreaConocimientoDelegate() {
        areaConocimiento.setPlanestudio(planestudio);
        return areaConocimientoDelegate;
    }
    
    public List<Programaeducativo> getListaFiltradaPed() {
        return listaFiltradaPed;
    }

    public void setListaFiltradaPed(List<Programaeducativo> listaFiltradaPed) {
        this.listaFiltradaPed = listaFiltradaPed;
    }

    public List<Areaconocimiento> getListaFiltrada() {
        return listaFiltrada;
    }
    
    public void setListaFiltrada2(List<Areaconocimiento> listaFiltrada2) {
        this.listaFiltrada2 = listaFiltrada2;
    }

    public void setListaFiltrada(List<Areaconocimiento> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    public PlanEstudioDelegate getPlanEstudioDelegate() {
        return planEstudioDelegate;
    }

    public void setPlanEstudioDelegate(PlanEstudioDelegate planEstudioDelegate) {
        this.planEstudioDelegate = planEstudioDelegate;
    }

    public Planestudio getPlanestudio() {
        return planestudio;
    }

    public void setPlanestudio(Planestudio planestudio) {
        this.planestudio = planestudio;
    }


    public Areaconocimiento getAreaConocimiento() {
        return areaConocimiento;
    }

    public void setAreaConocimiento(Areaconocimiento areaconocimiento) {
        this.areaConocimiento = areaconocimiento;
    }

    public Areaconocimiento getSelecAreaconocimiento() {
        return selecAreaconocimiento;
    }
        
    public List<Areaconocimiento> getListaFiltrada3() {
        return listaFiltrada3;
    }

    public void setListaFiltrada3(List<Areaconocimiento> listaFiltrada3) {
        this.listaFiltrada3 = listaFiltrada3;
    }

    public List<Areaconocimiento> getListaFiltrada4() {
        return listaFiltrada4;
    }

    public void setListaFiltrada4(List<Areaconocimiento> listaFiltrada4) {
        this.listaFiltrada4 = listaFiltrada4;
    }
    
    public List<Areaconocimiento> getListaSeleccionAcon() {
        return listaSeleccionAcon;
    }

    public void setListaSeleccionAcon(List<Areaconocimiento> listaSeleccionAcon) {
        this.listaSeleccionAcon = listaSeleccionAcon;
    }

    public void setSelecAreaconocimiento(Areaconocimiento selecAreaconocimiento) {
        this.selecAreaconocimiento = selecAreaconocimiento;
    }
    
    public Planestudio getSelecPlanEstudio() {
        return planestudio;
    }

    public void setSelecPlanEstudio(Planestudio planestudio) {
        this.planestudio = planestudio;
    }
    public List<Areaconocimiento> filtrado(String campo, String busqueda){
        String Cambus = busqueda.toLowerCase();
        String Nomarecon="";
        String progedu="";
        String plan="";
        
        listaFiltrada = areaConocimientoDelegate.getListaAreaConocimiento();
        for(Areaconocimiento area:listaFiltrada){
            try{
            area.setPlanestudio(planEstudioDelegate.findByPlanEstudioId(area.getPlanestudio().getPesid()));            
            area.getPlanestudio().setProgramaeducativo(programaeducativodelegate.findProgramaEducativoById(area.getPlanestudio().getProgramaeducativo().getPedid()));
            }
            catch(Exception e){
            }
        }                
        
        if(busqueda.trim().length()>0){
            try{
            listaFiltrada2.clear();
            }catch(NullPointerException e){
            listaFiltrada2 = areaConocimientoDelegate.getListaAreaConocimiento();
            listaFiltrada2.clear();
            }
            
            for(Areaconocimiento area : listaFiltrada){
                try{                                
                    area.setPlanestudio(planEstudioDelegate.findByPlanEstudioId(area.getPlanestudio().getPesid()));
                    area.getPlanestudio().setProgramaeducativo(programaeducativodelegate.findProgramaEducativoById(area.getPlanestudio().getProgramaeducativo().getPedid()));
                    Nomarecon=area.getAconombre().toLowerCase();
                    progedu=area.getPlanestudio().getProgramaeducativo().getPednombre();
                    plan=area.getPlanestudio().getPesvigenciaPlan();
                }catch(Exception e){
                    
                }
                if(campo.equalsIgnoreCase("nombre")){
                    if (Nomarecon.startsWith(Cambus)) {
                    listaFiltrada2.add(area);
                    }   
                }                               
                else{
                    if(campo.equalsIgnoreCase("Progedu")){
                        if (progedu.equalsIgnoreCase(Cambus)) {
                        listaFiltrada2.add(area);
                        }                
                    }
                    else{
                        if(campo.equalsIgnoreCase("ProgeduNom")){
                            String[] prognom;
                            prognom = Cambus.split("--");                                            
                            if (Nomarecon.startsWith(prognom[0]) && progedu.equalsIgnoreCase(prognom[1])) {
                                listaFiltrada2.add(area);
                            }
                        }
                        else{
                            if(campo.equalsIgnoreCase("ProgPlan")){
                                String[] progPlan;
                                progPlan = Cambus.split("--");                            
                                if (progedu.equalsIgnoreCase(progPlan[0]) && plan.equalsIgnoreCase(progPlan[1]) ) {
                                    listaFiltrada2.add(area);
                                }
                            }
                            else{
                                String[] todo;
                                todo = Cambus.split("--");
                                if (Nomarecon.startsWith(todo[0]) && progedu.equalsIgnoreCase(todo[1]) && plan.equalsIgnoreCase(todo[2])) {
                                    listaFiltrada2.add(area);
                                }
                            }
                        }
                    }
                }                
            }            
        }
        else{
            listaFiltrada2=listaFiltrada;
        }        
        return listaFiltrada2;
    }
    
            
    public void getUsuarioTienePE() {
        listaProgramaEducativo = programaeducativodelegate.getListaProgramaEducativo();
        try {
            listaProgramaEducativo.clear();
        } catch (NullPointerException e) {
        }
        if (rolSeleccionado.equalsIgnoreCase("Director") || rolSeleccionado.equalsIgnoreCase("Subdirector") || rolSeleccionado.equalsIgnoreCase("Administrador")) {
            profesor = profesorDelegate.findProfesorFromUser(usuario.getUsuid());
            listaUnidadAcademica = unidadacadelegate.getProfUAC(profesor.getProid());
            listaFiltradaPed = programaeducativodelegate.getListaProgramaEducativo();
            for (Unidadacademica uac : listaUnidadAcademica) {
                for (Programaeducativo pe : listaFiltradaPed) {
                    if (uac.getUacid() == pe.getUnidadacademica().getUacid()) {
                        listaProgramaEducativo.add(pe);
                    }
                }
            }
        } else{
            if(rolSeleccionado.equalsIgnoreCase("Responsable de Programa Educativo")
                    ||rolSeleccionado.equalsIgnoreCase("Coordinador de Formación Básica")){
            profesor = profesorDelegate.findProfesorFromUser(usuario.getUsuid());
            listaProgramaEducativo.add(programaeducativodelegate.findProgramaEducativoById(programaeducativodelegate.getResponsablePE(profesor.getProid()).get(0).getPedid()));
            programaeducativo = listaProgramaEducativo.get(0);
                System.out.println(programaeducativo.getPednombre()+"\n\n\n");
                filtrarPlanPorPE();
//            listaFiltrada = profesorDeleagate.getProfPE(listaProgramaEducativo.get(0).getPedid());
//            
//            for (Profesor prof : listaFiltrada) {
//                prof.setUsuario(usuarioDelegate.findUsuarioById(prof.getUsuario().getUsuid()));
//            }            
//            for(Profesor prof : profesor2)
//            listaProgramaEducativo = programaEducativoDelegate.getPEdeResponsable(profesor2.getProid());
//            if(profesor2.getProid() == responsableProgramaEducativo.getProfesor().getProid()){
//                listaProgramaEducativo.add(programaEducativoDelegate.findProgramaEducativoById(responsableProgramaEducativo.getProgramaeducativo().getPedid()));
//            }
        }else{
                if(rolSeleccionado.equalsIgnoreCase("Coordinador de Área de Conocimiento")){
                    profesor = profesorDelegate.findProfesorFromUser(usuario.getUsuid());
                    listaProgramaEducativo = programaeducativodelegate.getPEdeCoordinadorAreaAdmin(profesor.getProid());
                    programaeducativo = listaProgramaEducativo.get(0);
                    filtrarPlanPorPE();
                }
            }
        }
    }    
    public void seleccionarRegistro() {
        for (Areaconocimiento area : listaSeleccionAcon) {
            if (area.getAcoid().equals(selecAreaconocimiento.getAcoid())) {
                try{
                areaConocimiento = area;                                
                programaeducativo=area.getPlanestudio().getProgramaeducativo();
                filtrarPlanPorPE();
                planestudio=area.getPlanestudio();
                }catch(Exception e){
                    
                }
            }
        }
    }
    public String validarRepetidos(String todos){
        boolean band = true;
        mensaje = "";
        String[] todo=null;
        todo=todos.split("--");       
        todo[0]=todo[0].toLowerCase();
        for(Areaconocimiento area : areaConocimientoDelegate.getListaAreaConocimiento()){            
            try{
            area.setPlanestudio(planEstudioDelegate.findByPlanEstudioId(area.getPlanestudio().getPesid()));            
            area.getPlanestudio().setProgramaeducativo(programaeducativodelegate.findProgramaEducativoById(area.getPlanestudio().getProgramaeducativo().getPedid()));
            }
            catch(Exception e){
            }            
            if(!area.getAcoid().equals(areaConocimiento.getAcoid())){
                if(todo[0].equalsIgnoreCase(area.getAconombre())){                
                    if(todo[1].equalsIgnoreCase(area.getPlanestudio().getProgramaeducativo().getPednombre())){
                        if(todo[2].equalsIgnoreCase(area.getPlanestudio().getPesvigenciaPlan())){
                            mensaje = " el nombre del área de conocimiento, ya existe dentro del plan de estudio";
                        }                                            
                        band=false;                        
                    }                        
                }
            }                            
        }
        return mensaje;
    }
    
    public void filtrarPlanPorPE() {
        //mostrarListaPlan = "false";       
        System.out.println("PUFFF"+programaeducativo.getPedid());
        listaPlanEstudio = planEstudioDelegate.buscarPlanEstudio(programaeducativo.getPedid());
    }

    public String buscarNomProedu(int bus) {
        String nomprog="";        
        try{
        nomprog = programaeducativodelegate.findProgramaEducativoById(bus).getPednombre();
        }catch(Exception e){             
        }
        return nomprog;
    }
    
    public String buscarVigPlan(int bus) {
        String vigplan="";        
        try{
        vigplan = planEstudioDelegate.findByPlanEstudioId(bus).getPesvigenciaPlan();
        }catch(Exception e){             
        }
        return vigplan;
    }
    
      public void eliminarDeLista(int id) {
        for (Areaconocimiento area : listaSeleccionAcon) {
            if (area.getAcoid().equals(id)) {
                int index = listaSeleccionAcon.indexOf(area);
                listaSeleccionAcon.remove(index);                
                break;
            }
        }
    }
}