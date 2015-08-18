/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.List;
import mx.avanti.siract.business.PlanEstudioDelegate;
import mx.avanti.siract.business.ProfesorDelegate;
import mx.avanti.siract.business.ProgramaEducativoDelegate;
import mx.avanti.siract.business.UnidadAcademicaDelegate;
import mx.avanti.siract.business.UsuarioDelegate;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Unidadacademica;
import mx.avanti.siract.business.entity.Usuario;

/**
 *
 * @author Gig@B COMPUTADORAS
 */
public class PlanEstudioBeanHelper implements Serializable {

    private PlanEstudioDelegate planEstudioDelegate;
    private ProgramaEducativoDelegate programaEducativoDelegate;
    private Planestudio planEstudio;
    private Planestudio planEstudio2;
    private Planestudio selecPlanEstudio;
    private Programaeducativo programaeducativo;
    private Programaeducativo programaeducativo2;
    private List<Planestudio> listaFiltrada;
    private List<Planestudio> listaFiltrada2;
    private Usuario usuario2;
    private List<Programaeducativo> listaProgramaEducativo;
    private List<Planestudio> listaSeleccionPlanEstudio;
    private List<Unidadacademica> listaUnidadAcademica;
    private UnidadAcademicaDelegate unidadAcademicaDelegate;
    private Profesor profesor2;
    private ProfesorDelegate profesorDeleagate;
    private Usuario usuario;
    private List<Programaeducativo> listaProgEduc;
    private UsuarioDelegate usuarioDelegate;
    Unidadacademica auxUA = null; 

    public ProfesorDelegate getProfesorDeleagate() {
        return profesorDeleagate;
    }

    public void setProfesorDeleagate(ProfesorDelegate profesorDeleagate) {
        this.profesorDeleagate = profesorDeleagate;
    }
    
    

    public Programaeducativo getProgramaeducativo2() {
        return programaeducativo2;
    }

    public void setProgramaeducativo2(Programaeducativo programaeducativo2) {
        this.programaeducativo2 = programaeducativo2;
    }
    
    

    public UsuarioDelegate getUsuarioDelegate() {
        return usuarioDelegate;
    }

    public void setUsuarioDelegate(UsuarioDelegate usuarioDelegate) {
        this.usuarioDelegate = usuarioDelegate;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(Usuario usuario) {
        this.usuario2 = usuario;
    }

    public List<Unidadacademica> getListaUnidadAcademica() {
        return listaUnidadAcademica;
    }

    public void setListaUnidadAcademica(List<Unidadacademica> listaUnidadAcademica) {
        this.listaUnidadAcademica = listaUnidadAcademica;
    }

    public Planestudio getPlanEstudio2() {
        return planEstudio2;
    }

    public void setPlanEstudio2(Planestudio planEstudio2) {
        this.planEstudio2 = planEstudio2;
    }

    public List<Planestudio> getListaSeleccionPlanEstudio() {
        return listaSeleccionPlanEstudio;
    }

    public List<Planestudio> getListaFiltrada2() {
        return listaFiltrada2;
    }

    public void setListaFiltrada2(List<Planestudio> listaFiltrada2) {
        this.listaFiltrada2 = listaFiltrada2;
    }

    public void setListaSeleccionPlanEstudio(List<Planestudio> listaSeleccionPlanEstudio) {
        this.listaSeleccionPlanEstudio = listaSeleccionPlanEstudio;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public boolean isBanPlanEstudio() {
        return banPlanEstudio;
    }

    public void setBanPlanEstudio(boolean banPlanEstudio) {
        this.banPlanEstudio = banPlanEstudio;
    }


    private boolean banPlanEstudio;
    private boolean banProgramaEducativo;
    private String Mensaje;
    private String rolSeleccionado;
    private String ocultarLista;

    public PlanEstudioBeanHelper() {
        try {
            this.planEstudioDelegate = new PlanEstudioDelegate();
            this.programaEducativoDelegate = new ProgramaEducativoDelegate();
            this.profesorDeleagate = new ProfesorDelegate();
            this.unidadAcademicaDelegate = new UnidadAcademicaDelegate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        planEstudio = new Planestudio();
        selecPlanEstudio = new Planestudio();
        programaeducativo = new Programaeducativo();
        programaeducativo2 = new Programaeducativo();
        programaeducativo.setPedid(0);
    }

    public PlanEstudioDelegate getPlanEstudioDelegate() {
        planEstudio.setProgramaeducativo(programaeducativo);
        return planEstudioDelegate;
    }

//    public void setPlanEstudioDelegate(PlanEstudioDelegate planEstudioDelegate) {
//        this.planEstudioDelegate = planEstudioDelegate;
//    }
    public String getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(String rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public String getOcultarLista() {
        return ocultarLista;
    }

    public void setOcultarLista(String ocultarLista) {
        this.ocultarLista = ocultarLista;
    }

    public Planestudio getPlanEstudio() {
        return planEstudio;
    }

    public void setPlanEstudio(Planestudio planEstudio) {
        this.planEstudio = planEstudio;
    }

    public Planestudio getSelecPlanEstudio() {
        return selecPlanEstudio;
    }

    public void setSelecPlanEstudio(Planestudio selecPlanEstudio) {
        this.selecPlanEstudio = selecPlanEstudio;
    }

    public UnidadAcademicaDelegate getUnidadAcademicaDelegate() {
        return unidadAcademicaDelegate;
    }

    public void setUnidadAcademicaDelegate(UnidadAcademicaDelegate unidadAcademicaDelegate) {
        this.unidadAcademicaDelegate = unidadAcademicaDelegate;
    }

    
    
    public Programaeducativo getProgramaeducativo() {
        return programaeducativo;
    }

    public void setProgramaeducativo(Programaeducativo programaeducativo) {
        this.programaeducativo = programaeducativo;
    }

    public List<Planestudio> filtrado(String campo, String busqueda) {
        //int cambioObjGrupo;
        getUsuarioTienePE();        
        filtrarPE();
        //listaFiltrada = planEstudioDelegate.getListaPlanEstudio();
        for (Planestudio planE : listaFiltrada) {
            planE.setProgramaeducativo(programaEducativoDelegate.findProgramaEducativoById(planE.getProgramaeducativo().getPedid()));
        }

        if (busqueda.trim().length() > 0) {
            listaFiltrada.clear();
            for (Planestudio planE : planEstudioDelegate.getListaPlanEstudio()) {
                planE.setProgramaeducativo(programaEducativoDelegate.findProgramaEducativoById(planE.getProgramaeducativo().getPedid()));

                String cambioObjPlanE = planE.getPesvigenciaPlan();
                if (cambioObjPlanE.startsWith(busqueda)) {
                    listaFiltrada.add(planE);
                }
            }
        }

        return listaFiltrada;
    }

    public ProgramaEducativoDelegate getProgramaEducativoDelegate() {
        return programaEducativoDelegate;
    }

    public void setProgramaEducativoDelegate(ProgramaEducativoDelegate programaEducativoDelegate) {
        this.programaEducativoDelegate = programaEducativoDelegate;
    }

    public List<Planestudio> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Planestudio> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public List<Programaeducativo> getListaProgramaEducativo() {
        return listaProgramaEducativo;
    }

    public void setListaProgramaEducativo(List<Programaeducativo> listaProgramaEducativo) {
        this.listaProgramaEducativo = listaProgramaEducativo;
    }

    public Profesor getProfesor2() {
        return profesor2;
    }

    public void setProfesor2(Profesor profesor2) {
        this.profesor2 = profesor2;
    }

    public String Validar(String cabecera) {
        banProgramaEducativo = true;
        banPlanEstudio = true;
        Mensaje = "";
        System.out.println("Sout 1");
        for (Planestudio planE : planEstudioDelegate.getListaPlanEstudio()) {
            //Compara el campo de numero de empleado y al mismo tiempo una bandera para que 
            //en caso de que encuentre un registro identico no lo vuelva a comparar(esto aplica en los 
            //casos de que se repita un apellido o un nombre)
          //  System.out.println("Sout 2");
//            System.out.println("Vigen del Plan 1: " + planE.getPesvigenciaPlan());
//            System.out.println("Vigencia del Plan 2: " + planEstudio.getPesvigenciaPlan());
//            System.out.println("Programa Educativo 1: " + planE.getProgramaeducativo().getPedid());
//            System.out.println("Programa Educativo 2: " + programaeducativo.getPedid());
//            
                      
            if (cabecera.equals("Agregar Plan de Estudio") && planE.getProgramaeducativo().getPedid() == programaeducativo.getPedid() && planE.getPesvigenciaPlan().equalsIgnoreCase(planEstudio.getPesvigenciaPlan())) {
                System.out.println("Sout 3");
                Mensaje = Mensaje + " [ Programa Educativo ]";
                banProgramaEducativo = false;
            }
        }
        return Mensaje;
    }

    public void seleccionarRegistro() {
        for (Planestudio planE : planEstudioDelegate.getListaPlanEstudio()) {
            if (planE.getPesid().equals(selecPlanEstudio.getPesid())) {
                planEstudio = planE;
                programaeducativo = programaEducativoDelegate.findProgramaEducativoById(planE.getProgramaeducativo().getPedid());
            }
        }
    }

    public void eliminarDeLista(int id) {
        for (Planestudio planE : listaSeleccionPlanEstudio) {
            if (planE.getPesid().equals(id)) {
                int index = listaSeleccionPlanEstudio.indexOf(planE);
                listaSeleccionPlanEstudio.remove(index);
                break;
            }
        }
        for (Planestudio planE : listaSeleccionPlanEstudio) {
            System.out.println(planE.getPesvigenciaPlan());
        }

    }

    public void filtrarPE() {
        List<Planestudio> listaFiltrada3;
        listaFiltrada3=planEstudioDelegate.getListaPlanEstudio();
         try {
            listaFiltrada.clear();
        } catch (NullPointerException e) {
            System.out.println("Null Pointer");
            listaFiltrada = planEstudioDelegate.getListaPlanEstudio();
            listaFiltrada.clear();
        }
         
//        if(getProgramaEducativo().getPedid() == null){
//            listaFiltrada = profesorDeleagate.getListaProfesor();            
//        }
         System.out.println("SOUT NO. 1");
         System.out.println("Programa educativo es: " + getProgramaeducativo2().getPedid());
         
         if(getProgramaeducativo2().getPedid()==null){
             programaeducativo2.setPedid(0);
         }
        System.out.println("Programa educativo es: " + getProgramaeducativo2().getPedid());
        if (getProgramaeducativo2().getPedid() == 0) {
            //listaFiltrada = profesorDeleagate.getListaProfesor();
            //System.out.println("SOUT NO. 2");
            profesor2 = profesorDeleagate.findProfesorFromUser(usuario.getUsuid());
            //System.out.println("SOUT NO. 3");
            System.out.println(profesor2.getPronombre() + profesor2.getProid());
            //System.out.println("Prueba");
                       
            System.out.println("UA encontrado: " + unidadAcademicaDelegate.getProfUAC(profesor2.getProid()).get(0).getUacnombre());
            
            for (Programaeducativo pe : listaProgramaEducativo) {
                for (Planestudio plan : listaFiltrada3) {

//                    System.out.println("mostrando los planes de estudi en FiltrarPlanPorPE \n");
//                    System.out.println(plan.getPesvigenciaPlan());
                    if (pe.getPedid() == plan.getProgramaeducativo().getPedid()) {
                        listaFiltrada.add(plan);
                    }
                }
            }//                
//                auxUA = unidadAcademicaDelegate.getProfUAC(profesor2.getProid()).get(0);
//            if (auxUA == null || auxUA.getUacid() == null) {
//                System.out.println("NO SE ENCONTRO UNIDAD ACADEMICA PARA");
//            }
//            listaFiltrada = planEstudioDelegate.getPlanMismoPE(auxUA.getUacid());

        } else {
            listaFiltrada = planEstudioDelegate.buscarPlanEstudio(programaeducativo2.getPedid());
        }    
    }
    

    public void getUsuarioTienePE() {
        System.out.println("Rol - Plan Estudio" + rolSeleccionado);
        //   System.out.println("profesorFromUser: " + profesorDeleagate.findProfesorFromUser(usuario.getUsuid()).getProid());
        listaProgramaEducativo = programaEducativoDelegate.getListaProgramaEducativo();
        try {
            listaProgramaEducativo.clear();
        } catch (NullPointerException e) {

        }
        //System.out.println("Role menu: " + loginBeanUI.getRoles().size());
//        System.out.println("Map roles: " + loginBeanUI.getRolMenu());   
//        System.out.println("Rol: " + loginBeanUI.getRol());
//        List<Rol> list = null;
//        System.out.println("Roles: " + loginBeanUI.getRoles().get(0));

        //System.out.println("variable R: " + loginBeanUI.() + "\n variable Rol: " + loginBeanUI.getRol());
//        //if(getLoginBeanUI().getRoles() ==  getUsuario().getUsuusuario()){
        //listaUsuario = usuarioDelegate.getListaUsuario();}
        //rolMenu esta vacio
        if (rolSeleccionado.equalsIgnoreCase("Director") || rolSeleccionado.equalsIgnoreCase("Subdirector") || rolSeleccionado.equalsIgnoreCase("Administrador")) {
            ocultarLista = "true";
            System.out.println("sout 1");
            profesor2 = profesorDeleagate.findProfesorFromUser(usuario.getUsuid());
            System.out.println("sout 2");
            //a partir de aqui aparece NullPointer
            System.out.println("info del PRO2: " + profesor2.getProid());
            listaUnidadAcademica = unidadAcademicaDelegate.getProfUAC(profesor2.getProid()); // guardar en objeto lista de unidadAcademica
            System.out.println("sout 3");
            listaProgEduc = programaEducativoDelegate.getListaProgramaEducativo();
            System.out.println("sout 4");
            for (Unidadacademica uac : listaUnidadAcademica) {
                System.out.println("sout 5");
                for (Programaeducativo pe : listaProgEduc) {
                    System.out.println("sout 6");
                    if (uac.getUacid() == pe.getUnidadacademica().getUacid()) {
                        System.out.println("sout 7");
                        //aqui da NullPointer
                        listaProgramaEducativo.add(pe);
                        System.out.println("sout exitoso");
                    }
                }
//                System.out.println("sout 4");
//                    System.out.println("sout 5");
//                        listaProgramaEducativo = programaEducativoDelegate.getPEUAC(uac.getUacid());
//                        System.out.println("sout 6");
            }

        } else if (rolSeleccionado.equalsIgnoreCase("Responsable de Programa Educativo")) {
            profesor2 = profesorDeleagate.findProfesorFromUser(usuario.getUsuid());
//            if(profesor2.getProid() == responsableProgramaEducativo.getProfesor().getProid()){
//                listaProgramaEducativo.add(programaEducativoDelegate.findProgramaEducativoById(responsableProgramaEducativo.getProgramaeducativo().getPedid()));
//            }
        } else {
            //ocultarLista = "false";
        }

    }

}
