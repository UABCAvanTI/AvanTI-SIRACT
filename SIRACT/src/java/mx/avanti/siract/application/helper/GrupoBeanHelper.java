/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//22/02/2015
package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.GrupoDelegate;
import mx.avanti.siract.business.PlanEstudioDelegate;
import mx.avanti.siract.business.ProfesorDelegate;
import mx.avanti.siract.business.ProgramaEducativoDelegate;
import mx.avanti.siract.business.UnidadAcademicaDelegate;
import mx.avanti.siract.business.entity.Grupo;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Unidadacademica;
import mx.avanti.siract.business.entity.Usuario;

/**
 *
 * @author Gig@B COMPUTADORAS
 */
public class GrupoBeanHelper implements Serializable {

    private Grupo grupo;
    private Programaeducativo programaEducativo;
    private Grupo selecGrupo;
    private Planestudio planestudio;
    private Usuario usuario;
    private Profesor profesor;
    private Unidadacademica auxUA = null;

    private GrupoDelegate grupoDelegate;
    private ProfesorDelegate profesorDelegate;
    private UnidadAcademicaDelegate unidadAcademicaDelegate;
    private ProgramaEducativoDelegate programaEducativoDelegate;

    private List<Grupo> listaFiltrada;
    private List<Grupo> listaFiltrada2;
    private List<Grupo> listaGpoSeleccionada;
    private List<Planestudio> listaPlanEstudio;
    private List<Unidadacademica> listaUnidadAcademica;
    private List<Programaeducativo> listaProgramaEducativo;
    private List<Programaeducativo> listaProgEduc;

    private PlanEstudioDelegate planEstudioDelegate;

    private boolean blnGrupo;
    private boolean blnPlan;

    private String mensaje;
    private String rolSeleccionado;
    private String mostrarListaPlan = "true";
    private String listaPE;
    private String renderPlan;
    private String renderPE;

    public GrupoBeanHelper() {
        try {
            this.grupoDelegate = new GrupoDelegate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        mostrarListaPlan = "true";

        grupo = new Grupo();
        usuario = new Usuario();
        selecGrupo = new Grupo();
        planestudio = new Planestudio();
        planestudio.setPesid(0);

        programaEducativo = new Programaeducativo();
        programaEducativo.setPedid(0);

        profesorDelegate = new ProfesorDelegate();
        planEstudioDelegate = new PlanEstudioDelegate();
        unidadAcademicaDelegate = new UnidadAcademicaDelegate();
        programaEducativoDelegate = new ProgramaEducativoDelegate();
    }

    public GrupoDelegate getGrupoDelegate() {
        grupo.setPlanestudio(planestudio);
        return grupoDelegate;
    }

    /*metodo para la busqueda en tiempo real del grupo escrito en el filtro*/
    public List<Grupo> filtrado(String campo, String busqueda) {
        //int cambioObjGrupo;
//        if (programaEducativo.getPedid() == null || programaEducativo.getPedid() < 1) {
//            mostrarListaPlan = "true";
//        } else {
//            mostrarListaPlan = "false";
//        }
//        listaFiltrada = grupoDelegate.getListaGrupo();

        getUsuarioTienePE();

        filtrarPlanPorPE();
        filtrarGpoPorPlan();

//      
        if (busqueda.trim().length() > 0) {
            listaFiltrada2.clear();
            for (Grupo gpo : listaFiltrada) {
                gpo.setPlanestudio(planEstudioDelegate.findByPlanEstudioId(gpo.getPlanestudio().getPesid()));

                String cambioObjGrupo = Integer.toString(gpo.getGponumero());
                if (cambioObjGrupo.startsWith(busqueda)) {
                    listaFiltrada2.add(gpo);
                }
            }
        } else {
            listaFiltrada2 = listaFiltrada;
        }
        for (Grupo gpo : listaFiltrada) {
            gpo.setPlanestudio(planEstudioDelegate.findByPlanEstudioId(gpo.getPlanestudio().getPesid()));
        }
        return listaFiltrada2;
    }
    
    public void filtrarPlanPorPE() {
        try {
            listaPlanEstudio.clear();
//            listaFiltrada.clear();
        } catch (NullPointerException e) {
            listaPlanEstudio = planEstudioDelegate.getListaPlanEstudio();
        }

        if (programaEducativo.getPedid() == 0) {
            renderPE = "true";
            mostrarListaPlan = "true";
            planestudio.setPesid(0);
            List<Planestudio> listaPlan = planEstudioDelegate.getListaPlanEstudio();
            List<Grupo> listaGpo = grupoDelegate.getListaGrupo();

            try {
                listaFiltrada.clear();
            } catch (NullPointerException e) {
                listaFiltrada = grupoDelegate.getListaGrupo();
                listaFiltrada.clear();
            }

            for (Programaeducativo pe : listaProgramaEducativo) {
                for (Planestudio plan : listaPlan) {

//                    System.out.println("mostrando los planes de estudi en FiltrarPlanPorPE \n");
//                    System.out.println(plan.getPesvigenciaPlan());
                    if (pe.getPedid() == plan.getProgramaeducativo().getPedid()) {
                        listaPlanEstudio.add(plan);
                    }
                }
            }
            for (Planestudio plan : listaPlanEstudio) {
                for (Grupo grup : listaGpo) {
                    if (plan.getPesid() == grup.getPlanestudio().getPesid()) {
                        listaFiltrada.add(grup);
                    }
                }
            }

         
        } else {
            renderPE = "false";
            mostrarListaPlan = "false";
//            System.out.println(programaEducativo.getPednombre());
            listaPlanEstudio = planEstudioDelegate.buscarPlanEstudio(programaEducativo.getPedid());
        }
//            listaFiltrada = grupoDelegate.getListaGrupo();
        for (Grupo gpo : grupoDelegate.getListaGrupo()) {
            gpo.setPlanestudio(planEstudioDelegate.findByPlanEstudioId(gpo.getPlanestudio().getPesid()));
        }           
    }

    public void filtrarGpoPorPlan() {
        List<Grupo> listaGpo = grupoDelegate.getListaGrupo();

        try {
            listaFiltrada.clear();
        } catch (NullPointerException e) {

        }
        if (planestudio.getPesid() != 0) {
            renderPlan = "false";
//            System.out.println(planestudio.getPesid());
            listaFiltrada = grupoDelegate.getGpoMismoPlan(planestudio.getPesid());
//            for (Grupo grupo : listaFiltrada) {
//                System.out.println("asdasda" + grupo.getGponumero());
//            }
        } else if (planestudio.getPesid() == 0) {
            renderPlan = "true";
            for (Planestudio plan : listaPlanEstudio) {
                for (Grupo grup : grupoDelegate.getListaGrupo()) {
                    if (plan.getPesid() == grup.getPlanestudio().getPesid()) {
                        listaFiltrada.add(grup);
                    }
                }
            }
        }
        for (Grupo gpo : grupoDelegate.getListaGrupo()) {
            gpo.setPlanestudio(planEstudioDelegate.findByPlanEstudioId(gpo.getPlanestudio().getPesid()));
        }

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
    
    
    
    
    

    public void seleccionarRegistro() {
        for (Grupo gpo : grupoDelegate.getListaGrupo()) {
            if (gpo.getGpoid().equals(selecGrupo.getGpoid())) {
                grupo = gpo;
                planestudio = planEstudioDelegate.findByPlanEstudioId(gpo.getPlanestudio().getPesid());
                programaEducativo = programaEducativoDelegate.findProgramaEducativoById(getPlanestudio().getProgramaeducativo().getPedid());                        
            }
        }
    }

    public void eliminarDeLista(int id) {
        for (Grupo gpo : listaGpoSeleccionada) {
            if (gpo.getGpoid().equals(id)) {
                int index = listaGpoSeleccionada.indexOf(gpo);
                listaGpoSeleccionada.remove(index);
                break;
            }
        }
    }

    public String validarRepetidos() {
        blnGrupo = true;
        blnPlan = true;
        mensaje = "";
        for (Grupo gpo : grupoDelegate.getListaGrupo()) {
            
            if(gpo.getGponumero() == grupo.getGponumero() && blnGrupo == true
                        && !gpo.getGpoid().equals(grupo.getGpoid())){
                blnGrupo = false;
            
            
            if (gpo.getPlanestudio().getPesid().equals(planestudio.getPesid()) && blnPlan == true) {
                blnPlan = false;
                mensaje = mensaje + "[Grupo]";
//                    break;
                }
            }
        }
        return mensaje;
    }
        //    public void filtrarPE() {
    //        try {
    //            listaFiltrada.clear();
    //        } catch (NullPointerException e) {
    //
    //        }
    //        if (getProgramaEducativo().getPedid() == 0) {
    //            listaFiltrada = grupoDelegate.getListaGrupo();
    //
    //        } else {
    //            if (getPlanestudio().getPesid() == 0) {
    //
    //            } else {
    ////            profesor = profesorDelegate.findProfesorFromUser(usuario.getUsuid());
    ////            auxUA = unidadAcademicaDelegate.getProfUAC(profesor.getProid()).get(0);            
    ////            listaProgEduc = programaEducativoDelegate.getPEUAC(auxUA.getUacid());
    ////            for(Programaeducativo pe : listaProgEduc){
    ////                if(pe.getPedid() == planestudio.getProgramaeducativo().getPedid()){
    ////                    listaPlanEstudio = planEstudioDelegate.getPlanMismoPE(pe.getPedid());
    ////                }
    ////            }   
    //                listaPlanEstudio = planEstudioDelegate.getPlanMismoPE(getProgramaEducativo().getPedid());
    //                filtrarPlan();
    //            }
    //        }
    //    }

//    public void filtrarPlan() {
//        for (Planestudio plan : listaPlanEstudio) {
//            for (Grupo gpo : listaFiltrada) {
//                if (plan.getPesid() == getPlanestudio().getPesid()
//                        && plan.getPesid() == gpo.getPlanestudio().getPesid()) {
//                    listaFiltrada.add(gpo);
//                }
//            }
//        }
//    }



    public String getListaPE(Planestudio plan) {
//        System.out.println(plan.getPesid());
        Programaeducativo recibe = null;
//        listaPE = new ArrayList();
        try {
            if (plan.getPesid() > 0) {
//                recibe = programaEducativoDelegate.getPEPorPlanEstudio(plan.getPesid());
                recibe = programaEducativoDelegate.findProgramaEducativoById(plan.getProgramaeducativo().getPedid());
//                if(programaEducativoDelegate.findProgramaEducativoById(plan.getProgramaeducativo().getPedid()).getPedid() == plan.getProgramaeducativo().getPedid())
//                    recibe.add(programaEducativo);
//                for(Programaeducativo pe : recibe){
                System.out.println(recibe);
                listaPE = recibe.getPednombre();
//                }
            } else {
//                recibe = consultaPE();
            }
        } catch (NullPointerException e) {
        }
        return listaPE;
    }

//    public List<Programaeducativo> consultaPE() {
//        List<Programaeducativo> pe;
//        List<Programaeducativo> pe2 = new ArrayList<Programaeducativo>();
//        pe = programaEducativoDelegate.getListaProgramaEducativo();
//        for (int i = 0; i < listaPE.size(); i++) {
//            for (int x = 0; x < pe.size(); x++) {
//                if (pe.get(x).getPednombre().equals(listaPE.get(i))) {
//                    pe2.add(pe.get(x));
//                }
//            }
//        }
//        return pe2;
//    }
//    public void setGrupoDelegate(GrupoDelegate grupoDelegate) {
//        this.grupoDelegate = grupoDelegate;
//    }
    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Grupo getSelecGrupo() {
        return selecGrupo;
    }

    public void setSelecGrupo(Grupo selecGrupo) {
        this.selecGrupo = selecGrupo;
    }

    public Planestudio getPlanestudio() {
        return planestudio;
    }

    public void setPlanestudio(Planestudio planestudio) {
        this.planestudio = planestudio;
    }

    public void setListaFiltrada(List<Grupo> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public List<Grupo> getListaGpoSeleccionada() {
        return listaGpoSeleccionada;
    }

    public void setListaGpoSeleccionada(List<Grupo> listaGpoSeleccionada) {
        this.listaGpoSeleccionada = listaGpoSeleccionada;
    }

    public PlanEstudioDelegate getPlanEstudioDelegate() {
        return planEstudioDelegate;
    }

    public void setRolSeleccionado(String rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public void setPlanEstudioDelegate(PlanEstudioDelegate planEstudioDelegate) {
        this.planEstudioDelegate = planEstudioDelegate;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ProfesorDelegate getProfesorDelegate() {
        return profesorDelegate;
    }

    public List<Programaeducativo> getListaProgramaEducativo() {
        return listaProgramaEducativo;
    }

    public void setListaProgramaEducativo(List<Programaeducativo> listaProgramaEducativo) {
        this.listaProgramaEducativo = listaProgramaEducativo;
    }

    public List<Unidadacademica> getListaUnidadAcademica() {
        return listaUnidadAcademica;
    }

    public void setListaUnidadAcademica(List<Unidadacademica> listaUnidadAcademica) {
        this.listaUnidadAcademica = listaUnidadAcademica;
    }

    public void setProfesorDelegate(ProfesorDelegate profesorDelegate) {
        this.profesorDelegate = profesorDelegate;
    }

    public ProgramaEducativoDelegate getProgramaEducativoDelegate() {
        return programaEducativoDelegate;
    }

    public void setProgramaEducativoDelegate(ProgramaEducativoDelegate programaEducativoDelegate) {
        this.programaEducativoDelegate = programaEducativoDelegate;
    }

    public Programaeducativo getProgramaEducativo() {
        return programaEducativo;
    }

    public void setProgramaEducativo(Programaeducativo programaEducativo) {
        this.programaEducativo = programaEducativo;
    }

    public List<Planestudio> getListaPlanEstudio() {
        return listaPlanEstudio;
    }

    public void setListaPlanEstudio(List<Planestudio> listaPlanEstudio) {
        this.listaPlanEstudio = listaPlanEstudio;
    }

    public String getMostrarListaPlan() {
        return mostrarListaPlan;
    }

    public void setMostrarListaPlan(String mostrarListaPlan) {
        this.mostrarListaPlan = mostrarListaPlan;
    }

    public List<Grupo> getListaFiltrada2() {
        return listaFiltrada2;
    }

    public void setListaFiltrada2(List<Grupo> listaFiltrada2) {
        this.listaFiltrada2 = listaFiltrada2;
    }

    public String getListaPE() {
        return listaPE;
    }

    public void setListaPE(String listaPE) {
        this.listaPE = listaPE;
    }

    public String getRenderPlan() {
        return renderPlan;
    }

    public void setRenderPlan(String renderPlan) {
        this.renderPlan = renderPlan;
    }

    public String getRenderPE() {
        return renderPE;
    }

    public void setRenderPE(String renderPE) {
        this.renderPE = renderPE;
    }

}
