/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.ProfesorDelegate;
import mx.avanti.siract.business.ProgramaEducativoDelegate;
import mx.avanti.siract.business.RolDelegate;
import mx.avanti.siract.business.UnidadAcademicaDelegate;
import mx.avanti.siract.business.UsuarioDelegate;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Rol;
import mx.avanti.siract.business.entity.Unidadacademica;
import mx.avanti.siract.business.entity.Usuario;

/**
 *
 * @author Paco
 */
public class AsignarRPEBeanHelper implements Serializable{

    private ProgramaEducativoDelegate programaEducativoDelegate;
    private ProfesorDelegate profesorDelegate;
    private UnidadAcademicaDelegate unidadAcademicaDelegate; 
    private RolDelegate rolDelegate;
    private UsuarioDelegate usuarioDelegate;
     
    private List<Profesor> listaFiltrada;
    private List<Profesor> listaSeleccion;
    private List<Profesor> listaProfesoresTabla;
    private List<Programaeducativo> listaPE;
    
    private List<Usuario> listaUsuarios;    
    private List<Rol> rolRPE;
    private List<Unidadacademica> listaUnidadAcademica;
    
    private ArrayList <Profesor> listaProfesordlg;
    
    private Profesor profesor;
    private Profesor profesorSeleccionado;
    private Programaeducativo programaEducativo;
    
    private Usuario usuarioLogeado;
    private Profesor profesorLog;
    private String rolSeleccionado;
    
    private int idProfMod;
    private int idPEMod;
    public AsignarRPEBeanHelper() {
        programaEducativoDelegate = new ProgramaEducativoDelegate();
        profesorDelegate = new ProfesorDelegate();
        unidadAcademicaDelegate = new UnidadAcademicaDelegate();
        rolDelegate = new RolDelegate();
        usuarioDelegate = new UsuarioDelegate();
        
        profesor = new Profesor();
        programaEducativo = new Programaeducativo();
        
        listaProfesordlg = new ArrayList<Profesor>();
    }
    
    

    //<editor-fold defaultstate="collapsed" desc="getter y setter">
    
  
    public Profesor getProfesorSeleccionado() {
        return profesorSeleccionado;
    }

    public void setProfesorSeleccionado(Profesor profesorSeleccionado) {
        this.profesorSeleccionado = profesorSeleccionado;
    }

    public int getIdProfMod() {
        return idProfMod;
    }

    public void setIdProfMod(int idProfMod) {
        this.idProfMod = idProfMod;
    }

    public int getIdPEMod() {
        return idPEMod;
    }

    public void setIdPEMod(int idPEMod) {
        this.idPEMod = idPEMod;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Programaeducativo getProgramaEducativo() {
        return programaEducativo;
    }

    public void setProgramaEducativo(Programaeducativo programaEducativo) {
        this.programaEducativo = programaEducativo;
    }

    public List<Profesor> getListaSeleccion() {
        return listaSeleccion;
    }

    public void setListaSeleccion(List<Profesor> listaSeleccion) {
        this.listaSeleccion = listaSeleccion;
    }

    public Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    public String getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(String rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public List<Profesor> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Profesor> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public List<Profesor> getListaProfesoresTabla() {
        return listaProfesoresTabla;
    }

    public void setListaProfesoresTabla(List<Profesor> listaProfesoresTabla) {
        this.listaProfesoresTabla = listaProfesoresTabla;
    }

    public ArrayList<Profesor> getListaProfesordlg() {
        return listaProfesordlg;
    }

    public void setListaProfesordlg(ArrayList<Profesor> listaProfesordlg) {
        this.listaProfesordlg = listaProfesordlg;
    }

    public List<Programaeducativo> getListaPE() {
        return listaPE;
    }

    public void setListaPE(List<Programaeducativo> listaPE) {
        this.listaPE = listaPE;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public RolDelegate getRolDelegate() {
        return rolDelegate;
    }

    public void setRolDelegate(RolDelegate rolDelegate) {
        this.rolDelegate = rolDelegate;
    }

    public UsuarioDelegate getUsuarioDelegate() {
        return usuarioDelegate;
    }

    public void setUsuarioDelegate(UsuarioDelegate usuarioDelegate) {
        this.usuarioDelegate = usuarioDelegate;
    }

    public List<Rol> getRolRPE() {
        return rolRPE;
    }

    public void setRolRPE(List<Rol> rolRPE) {
        this.rolRPE = rolRPE;
    }


    public ProgramaEducativoDelegate getProgramaEducativoDelegate() {
        return programaEducativoDelegate;
    }

    public void setProgramaEducativoDelegate(ProgramaEducativoDelegate programaEducativoDelegate) {
        this.programaEducativoDelegate = programaEducativoDelegate;
    }

    public ProfesorDelegate getProfesorDelegate() {
        return profesorDelegate;
    }

    public void setProfesorDelegate(ProfesorDelegate profesorDelegate) {
        this.profesorDelegate = profesorDelegate;
    }

    public UnidadAcademicaDelegate getUnidadAcademicaDelegate() {
        return unidadAcademicaDelegate;
    }

    public void setUnidadAcademicaDelegate(UnidadAcademicaDelegate unidadAcademicaDelegate) {
        this.unidadAcademicaDelegate = unidadAcademicaDelegate;
    }
    
    
//</editor-fold>
    
    public List<Profesor> filtrado(String busqueda) {

        System.out.println("\n\n\n");
        getUsuarioTienePE();
        usuariosRPE();
        rpeByProf();
        listaFiltrada = listaProfesoresTabla;
        return listaFiltrada;
    }

    public void usuariosRPE(){
        rolRPE = rolDelegate.getRol();
        for(Rol rol : rolRPE){ 
            if(rol.getRoltipo().equalsIgnoreCase("Responsable de Programa Educativo")){
                listaUsuarios = usuarioDelegate.findUsuarioByRol(rol.getRolid());
                break;
            }
        }
        listaProfesordlg.clear();
        for(Usuario usu : listaUsuarios){
            listaProfesordlg.addAll(profesorDelegate.getProfbyUsuario(usu.getUsuid()));
        }
//        for(Profesor prof : listaProfesor){
//            System.out.println(prof.getPronombre());
//        }
    }
    public void rpeByProf(){
        listaProfesoresTabla = profesorDelegate.getListaProfesor();
        listaProfesoresTabla.clear();
        for(Programaeducativo pe : listaPE){
            listaProfesoresTabla.addAll(profesorDelegate.getRPEbyPE(pe.getPedid()));
        }
        for(Profesor prof : listaProfesoresTabla){
            System.out.println(prof.getPronombre());
        }
    }
    public String peByRPE(int rpeid){
        return programaEducativoDelegate.getResponsablePE(rpeid).get(0).getPednombre();
    }
    public Programaeducativo peByRPEobj(int rpeid){
        return programaEducativoDelegate.getResponsablePE(rpeid).get(0);
    }
    public void getUsuarioTienePE() {
        listaPE = programaEducativoDelegate.getListaProgramaEducativo();
        try {
            listaPE.clear();
        } catch (NullPointerException e) {

        }
        if (rolSeleccionado.equalsIgnoreCase("Director") || rolSeleccionado.equalsIgnoreCase("Subdirector") || rolSeleccionado.equalsIgnoreCase("Administrador")) {
            profesorLog = profesorDelegate.findProfesorFromUser(usuarioLogeado.getUsuid());
            //a partir de aqui aparece NullPointer
            listaUnidadAcademica = unidadAcademicaDelegate.getProfUAC(profesorLog.getProid()); // guardar en objeto lista de unidadAcademica
            //listaProgEduc = programaEducativoDelegate.getListaProgramaEducativo();
            for (Unidadacademica uac : listaUnidadAcademica) {
                for (Programaeducativo pe : programaEducativoDelegate.getListaProgramaEducativo()) {
                    if (uac.getUacid().equals( pe.getUnidadacademica().getUacid())) {
                        //aqui da NullPointer
                        listaPE.add(pe);
                    }
                }
            }

        }

    }
    
    public String Validar() {
        String Mensaje = "";
        
        programaEducativo = programaEducativoDelegate.findProgramaEducativoById(programaEducativo.getPedid());
        if((idPEMod==0 && idProfMod==0)||(idPEMod!=programaEducativo.getPedid() && idProfMod!=profesor.getProid())){
        if(profesorDelegate.getRPEbyPE(programaEducativo.getPedid())!=null
                && profesorDelegate.getRPEbyPE(programaEducativo.getPedid()).size()>0){
            Mensaje = "Este programa educativo ya tiene un responsable que lo coordina";
            return Mensaje;
        }else{
            if(programaEducativoDelegate.getResponsablePE(profesor.getProid())!=null
                && programaEducativoDelegate.getResponsablePE(profesor.getProid()).size()>0){
                System.out.println(programaEducativoDelegate.getResponsablePE(profesor.getProid()).size());
                Mensaje = "Este profesor ya es responsable de un programa educativo";
                return Mensaje;
            }
        }   

        boolean ban =false;
        for(Programaeducativo pe : programaEducativoDelegate.getPEperteneceProf(profesor.getProid())){
            if(pe.getPedid().equals(programaEducativo.getPedid())){
                ban = true;
            }
        }
        if(!ban){
            Mensaje = "Este profesor no pertenece a este Programa educativo";
            return Mensaje;
        }
    }
        if(idPEMod!=programaEducativo.getPedid() && idProfMod==profesor.getProid()){
            if(profesorDelegate.getRPEbyPE(programaEducativo.getPedid())!=null
                && profesorDelegate.getRPEbyPE(programaEducativo.getPedid()).size()>0){
            Mensaje = "Este programa educativo ya tiene un responsable que lo coordina";
            return Mensaje;
        } 
        //programaEducativo = programaEducativoDelegate.findProgramaEducativoById(programaEducativo.getPedid());

        boolean ban =false;
        for(Programaeducativo pe : programaEducativoDelegate.getPEperteneceProf(profesor.getProid())){
            if(pe.getPedid().equals(programaEducativo.getPedid())){
                ban = true;
            }
        }
        if(!ban){
            Mensaje = "Este profesor no pertenece a este Programa educativo";
            return Mensaje;
        }
        }
        
        if(idPEMod==programaEducativo.getPedid() && idProfMod!=profesor.getProid()){
            
            if(programaEducativoDelegate.getResponsablePE(profesor.getProid())!=null
                && programaEducativoDelegate.getResponsablePE(profesor.getProid()).size()>0){
                System.out.println(programaEducativoDelegate.getResponsablePE(profesor.getProid()).size());
                Mensaje = "Este profesor ya es responsable de un programa educativo";
                return Mensaje;
            }           
        boolean ban =false;
        for(Programaeducativo pe : programaEducativoDelegate.getPEperteneceProf(profesor.getProid())){
            if(pe.getPedid().equals(programaEducativo.getPedid())){
                ban = true;
            }
        }
        if(!ban){
            Mensaje = "Este profesor no pertenece a este Programa educativo";
            return Mensaje;
        }
        }
        return Mensaje;
    }
    
    public void eliminarDeLista(int id) {
        for (Profesor prof : listaSeleccion) {
            if (prof.getProid().equals(id)) {
                int index = listaSeleccion.indexOf(prof);
                listaSeleccion.remove(index);
                break;
            }
        }

    }
}