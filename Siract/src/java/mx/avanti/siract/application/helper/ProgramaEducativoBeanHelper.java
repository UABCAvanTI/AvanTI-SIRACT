/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.List;
import mx.avanti.siract.business.ProfesorDelegate;
import mx.avanti.siract.business.ProgramaEducativoDelegate;
import mx.avanti.siract.business.UnidadAcademicaDelegate;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Unidadacademica;
import mx.avanti.siract.business.entity.Usuario;

/**
 *
 * @author balta
 */
public class ProgramaEducativoBeanHelper implements Serializable {

    private ProgramaEducativoDelegate programaEducativoDelegate;
    private ProfesorDelegate profesorDeleagate;
    private Programaeducativo programaEducativo;
    private Programaeducativo selecProgramaEducativo;
    private Unidadacademica unidadacademica;
    private Unidadacademica filtroUAC;
    private List<Programaeducativo> listaFiltrada;
    private List<Programaeducativo> listaSeleccionPe;
    private UnidadAcademicaDelegate unidadAcademicaDelegate;
    private boolean banPe;
    private String mensaje;
    private String rolSeleccionado;

    private Usuario usuario;
    private String ocultarLista;
    private Profesor profesor;
    private List<Unidadacademica> listaUnidadAcademica;
    private List<Programaeducativo> listaFiltrada2;
    
    public ProgramaEducativoBeanHelper() {
        try {
            this.programaEducativoDelegate = new ProgramaEducativoDelegate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        programaEducativo = new Programaeducativo();
        selecProgramaEducativo = new Programaeducativo();
        unidadacademica = new Unidadacademica();
        filtroUAC = new Unidadacademica();
        filtroUAC.setUacid(0);
        unidadAcademicaDelegate = new UnidadAcademicaDelegate();
        profesorDeleagate = new ProfesorDelegate();

    }

    public Unidadacademica getFiltroUAC() {
        return filtroUAC;
    }

    public void setFiltroUAC(Unidadacademica filtroUAC) {
        this.filtroUAC = filtroUAC;
    }

    public ProgramaEducativoDelegate getProgramaEducativoDelegate() {
        programaEducativo.setUnidadacademica(unidadacademica);
        return programaEducativoDelegate;
    }

    public ProfesorDelegate getProfesorDeleagate() {
        return profesorDeleagate;
    }

    public void setProfesorDeleagate(ProfesorDelegate profesorDeleagate) {
        this.profesorDeleagate = profesorDeleagate;
    }

    public String getRolSeleccionado() {
        return rolSeleccionado;
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

    public String getOcultarLista() {
        return ocultarLista;
    }

    public void setOcultarLista(String ocultarLista) {
        this.ocultarLista = ocultarLista;
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

    public List<Programaeducativo> getListaFiltrada2() {
        return listaFiltrada2;
    }

    public void setListaFiltrada2(List<Programaeducativo> listaFiltrada2) {
        this.listaFiltrada2 = listaFiltrada2;
    }

//
//          public List<Programaeducativo> filtrado(String campo, String busqueda) {
//
//         listaFiltrada = programaEducativoDelegate.getListaProgramaEducativo();
//
//            for (Programaeducativo ped : listaFiltrada) {
//            ped.setUnidadacademica(unidadAcademicaDelegate.findUnidadAcademicaById(ped.getUnidadacademica().getUacid()));
//        }
//        if (busqueda.trim().length() > 0) {
//            listaFiltrada.clear();
//            for (Programaeducativo pro : programaEducativoDelegate.getListaProgramaEducativo()) {
//                
//                String CambioObjNum = pro.getPednombre();
//                //String CambioObjNum1=ciclo.getCescicloEscolar();
//                if (CambioObjNum.startsWith(busqueda)) {
//                    listaFiltrada.add(pro);
//                }
//            }
//        }
//        //Collections.sort(ListaCiclo,Collections.reverseOrder());
//        return listaFiltrada;
//    }
    public List<Programaeducativo> filtrado(String busqueda) {
        getUsuarioTienePE();
        if (busqueda.trim().length() > 0) {
            listaFiltrada2.clear();
            for (Programaeducativo pro : listaFiltrada) {
                pro.setUnidadacademica(unidadAcademicaDelegate.findUnidadAcademicaById(pro.getUnidadacademica().getUacid()));

                String cambioObjPE = Integer.toString(pro.getPedclave());
                if (cambioObjPE.startsWith(busqueda)) {
                    listaFiltrada2.add(pro);
                } else {
                    cambioObjPE = pro.getPednombre().toLowerCase();
                    if (cambioObjPE.startsWith(busqueda.toLowerCase())) {
                        listaFiltrada2.add(pro);
                    }
                }
            }
        } else {
            listaFiltrada2 = listaFiltrada; //programaEducativoDelegate.getListaProgramaEducativo();
        }
        for (Programaeducativo pro : listaFiltrada2) {
            pro.setUnidadacademica(unidadAcademicaDelegate.findUnidadAcademicaById(pro.getUnidadacademica().getUacid()));
        }

        return listaFiltrada2;
    }

//    public void filtrarUAC() {
//        List<Programaeducativo> listaFiltrada3;
//        listaFiltrada3=programaEducativoDelegate.getListaProgramaeducativo();
//         try {
//            listaFiltrada.clear();
//        } catch (NullPointerException e) {
//            System.out.println("Null Pointer");
//            listaFiltrada = programaEducativoDelegate.getListaProgramaeducativo();
//            listaFiltrada.clear();
//        }
//         
////        if(getProgramaEducativo().getPedid() == null){
////            listaFiltrada = profesorDeleagate.getListaProfesor();            
////        }
//         System.out.println("SOUT NO. 1");
//         System.out.println("Programa educativo es: " + getProgramaeducativo2().getPedid());
//         
//         if(getProgramaeducativo2().getPedid()==null){
//             programaeducativo2.setPedid(0);
//         }
//        System.out.println("Programa educativo es: " + getProgramaeducativo2().getPedid());
//        if (getProgramaeducativo2().getPedid() == 0) {
//            //listaFiltrada = profesorDeleagate.getListaProfesor();
//            //System.out.println("SOUT NO. 2");
//            profesor2 = profesorDeleagate.findProfesorFromUser(usuario.getUsuid());
//            //System.out.println("SOUT NO. 3");
//            System.out.println(profesor2.getPronombre() + profesor2.getProid());
//            //System.out.println("Prueba");
//                       
//            System.out.println("UA encontrado: " + unidadAcademicaDelegate.getProfUAC(profesor2.getProid()).get(0).getUacnombre());
//            
//            for (Programaeducativo pe : listaProgramaEducativo) {
//                for (Planestudio plan : listaFiltrada3) {
//
////                    System.out.println("mostrando los planes de estudi en FiltrarPlanPorPE \n");
////                    System.out.println(plan.getPesvigenciaPlan());
//                    if (pe.getPedid() == plan.getProgramaeducativo().getPedid()) {
//                        listaFiltrada.add(plan);
//                    }
//                }
//            }//                
////                auxUA = unidadAcademicaDelegate.getProfUAC(profesor2.getProid()).get(0);
////            if (auxUA == null || auxUA.getUacid() == null) {
////                System.out.println("NO SE ENCONTRO UNIDAD ACADEMICA PARA");
////            }
////            listaFiltrada = planEstudioDelegate.getPlanMismoPE(auxUA.getUacid());
//
//        } else {
//            listaFiltrada = planEstudioDelegate.buscarPlanEstudio(programaeducativo2.getPedid());
//        }    
//    }
    public void seleccionarRegistro() {
        for (Programaeducativo pro : programaEducativoDelegate.getListaProgramaEducativo()) {
            if (pro.getPedid().equals(selecProgramaEducativo.getPedid())) {
                programaEducativo = pro;
                unidadacademica = unidadAcademicaDelegate.findUnidadAcademicaById(pro.getUnidadacademica().getUacid());
            }
        }
    }

    public void eliminarDeLista(int id) {
        for (Programaeducativo pro : listaSeleccionPe) {
            if (pro.getPedid().equals(id)) {
                int index = listaSeleccionPe.indexOf(pro);
                listaSeleccionPe.remove(index);
                break;
            }
        }
//        for (Programaeducativo pro : listaSeleccionPe) {
//            System.out.println(pro.getPednombre());
//        }

    }

    public String validarRepetidos() {
        banPe = true;
        mensaje = "";
        for (Programaeducativo pro : programaEducativoDelegate.getListaProgramaEducativo()) {
            if (pro.getPedclave() == programaEducativo.getPedclave()
                    && !pro.getPedid().equals(programaEducativo.getPedid())) {
                mensaje = mensaje + "[Clave] ";
                banPe = false;
            }
            if (pro.getPednombre() == programaEducativo.getPednombre()
                    && !pro.getPedid().equals(programaEducativo.getPedid())) {
                mensaje = mensaje + "[Nombre] ";
                banPe = false;
            }

        }
        return mensaje;
    }

//    public void setProgramaEducativoDelegate(ProgramaEducativoDelegate programaEducativoDelegate) {
//        this.programaEducativoDelegate = programaEducativoDelegate;
//    }
//    public List<Programaeducativo> filtrado(String busqueda){  
//        String CambioBus =busqueda.toLowerCase();
//        String CambioObjNom="";
//
//        listaFiltrada = programaEducativoDelegate.getListaProgramaEducativo();
//        
//         for (Programaeducativo ped : listaFiltrada) {
//             ped.setUnidadacademica(unidadAcademicaDelegate.findUnidadAcademicaById(ped.getUnidadacademica().getUacid()));
//        }
//        
//        return listaFiltrada;
//    }
    //get y set
    public void setProgramaEducativoDelegate(ProgramaEducativoDelegate programaEducativoDelegate) {
        this.programaEducativoDelegate = programaEducativoDelegate;
    }

    public UnidadAcademicaDelegate getUnidadAcademicaDelegate() {
        return unidadAcademicaDelegate;
    }

    public void setUnidadAcademicaDelegate(UnidadAcademicaDelegate unidadAcademicaDelegate) {
        this.unidadAcademicaDelegate = unidadAcademicaDelegate;
    }

    public Programaeducativo getProgramaEducativo() {
        return programaEducativo;
    }

    public void setProgramaEducativo(Programaeducativo programaEducativo) {
        this.programaEducativo = programaEducativo;
    }

    public Programaeducativo getSelecProgramaEducativo() {
        return selecProgramaEducativo;
    }

    public void setSelecProgramaEducativo(Programaeducativo selecProgramaEducativo) {
        this.selecProgramaEducativo = selecProgramaEducativo;
    }

    public Unidadacademica getUnidadacademica() {
        return unidadacademica;
    }

    public void setUnidadacademica(Unidadacademica unidadacademica) {
        this.unidadacademica = unidadacademica;
    }

    public List<Programaeducativo> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Programaeducativo> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public List<Programaeducativo> getListaSeleccionPe() {
        return listaSeleccionPe;
    }

    public void setListaSeleccionPe(List<Programaeducativo> listaSeleccionPe) {
        this.listaSeleccionPe = listaSeleccionPe;
    }

    public boolean isBanPe() {
        return banPe;
    }

    public void setBanPe(boolean banPe) {
        this.banPe = banPe;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void getUsuarioTienePE() {
        System.out.println(rolSeleccionado);
        System.out.println("profesorFromUser: " + profesorDeleagate.findProfesorFromUser(usuario.getUsuid()).getProid());
//        listaFiltrada = programaEducativoDelegate.getListaProgramaEducativo();
//        listaFiltrada2 = programaEducativoDelegate.getListaProgramaEducativo();

        try {
            listaFiltrada.clear();
            listaFiltrada2.clear();
        } catch (NullPointerException e) {

        }
        if (rolSeleccionado.equalsIgnoreCase("Director") || rolSeleccionado.equalsIgnoreCase("Subdirector")
                || rolSeleccionado.equalsIgnoreCase("RESPONSABLE DE PROGRAMA EDUCATIVO")|| rolSeleccionado.equalsIgnoreCase("Coordinador de Área de Conocimiento")
                || rolSeleccionado.equalsIgnoreCase("Coordinador de Formación Básica")) {
            ocultarLista = "true";
            profesor = profesorDeleagate.findProfesorFromUser(usuario.getUsuid());
            //a partir de aqui aparece NullPointer
            listaUnidadAcademica = unidadAcademicaDelegate.getProfUAC(profesor.getProid()); // guardar en objeto lista de unidadAcademica
            for (Unidadacademica uac : listaUnidadAcademica) {
                listaFiltrada = programaEducativoDelegate.getPEUAC(uac.getUacid());
//                for (Programaeducativo pe : listaFiltrada) {
//                    if (uac.getUacid().equals(pe.getUnidadacademica().getUacid())) {
//                        //aqui da NullPointer
//                        listaFiltrada2.add(pe);
//                    }
//                }
            }
        } else {
            if (rolSeleccionado.equalsIgnoreCase("Administrador")) {
                profesor = profesorDeleagate.findProfesorFromUser(usuario.getUsuid());
                listaUnidadAcademica = unidadAcademicaDelegate.getListaUnidadAcademica();
                if(filtroUAC.getUacid()!=0){
                listaFiltrada = programaEducativoDelegate.getPEUAC(filtroUAC.getUacid());
                }else{
                listaFiltrada = programaEducativoDelegate.getListaProgramaEducativo();
                }
            } else {
                ocultarLista = "false";
                listaFiltrada = programaEducativoDelegate.getListaProgramaEducativo();
            }
        }
    }
    
    public Unidadacademica UAbyProf(){
        return unidadAcademicaDelegate.getProfUAC(profesor.getProid()).get(0) ;
    }

}