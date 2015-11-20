/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.business;

import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Y
 */
public class ProgramaEducativoDelegate {
    
    
    
    private List<Programaeducativo> listaProgramaeducativo;
    
    public ProgramaEducativoDelegate(){
        listaProgramaeducativo = new ArrayList<Programaeducativo>();
    }

    public List<Programaeducativo> getListaProgramaeducativo() {
        listaProgramaeducativo = ServiceFacadeLocator.getInstanceFacadeProgramaeducativo().consultaProgramaEducativo();
        return listaProgramaeducativo;
    }
        public List<Programaeducativo> getListaUnidadAprendisajeFFWD(String de,String campo,String criterio) {
        listaProgramaeducativo = ServiceFacadeLocator.getInstanceFacadeProgramaeducativo().buscarProgramaEducativo(de, campo, criterio);
        return listaProgramaeducativo;
    }

    public void setListaProgramaeducativo(List<Programaeducativo> listaProgramaeducativo) {
        this.listaProgramaeducativo = listaProgramaeducativo;
    }
    
  
     private List<Programaeducativo> listaProgramaEducativo;


    public List<Programaeducativo> getListaProgramaEducativo() {
        listaProgramaEducativo = ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().consultaProgramaEducativo();
        
        return listaProgramaEducativo;
    }

    public void setListaProgramaEducativo(List<Programaeducativo> listaProgramaEducativo) {
        this.listaProgramaEducativo = listaProgramaEducativo;
    }
    
    public void agregarProgramaEducativo(Programaeducativo programaeducativo){
        ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().agregarProgramaEducativo(programaeducativo);
    }
    
    public void eliminarProgramaEducativo(Programaeducativo programaeducativo){
        ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().eliminarProgramaEducativo(programaeducativo);
    }
    public Programaeducativo findProgramaEducativoById(int id){
        return ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().getProgramaEducativo(id);
    }

    public void getProgramaEducativoDeResponsable(int id){
        
    
    }
    
    
    public List<Programaeducativo> getPEProf(int proid){
        listaProgramaEducativo = ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().consultaPE(proid);
        return listaProgramaEducativo;
    }
    
    public List<Programaeducativo> getPEUAC(int uacid){
        System.out.println("ID DE PROGRAMAEDUCATIVO "+uacid);
        listaProgramaEducativo = ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().consultaPEUAC(uacid);
        return listaProgramaEducativo;
    }
    
    public List<Programaeducativo> getPEDeAreaConocimiento(String idAco){
        System.out.println("ID DE Area Conocimiento "+idAco);
        listaProgramaEducativo = ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().consultaPEDeAreaConocimiento(idAco);
        return listaProgramaEducativo;
    }
    
       public Programaeducativo getPEdeResponsable(int profResponsableId){
        Programaeducativo programaeducativo = ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().getPEdeResponsable(profResponsableId);
        return programaeducativo;
    }
       
        public Programaeducativo getPEdeUnAp(int uaId){
        Programaeducativo programaeducativo = ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().getPEdeUnidadAprendizaje(uaId);
        return programaeducativo;
    }
    
//    private List<Programaeducativo> listaProgramaeducativo;
//    
//    public ProgramaeducativoDelegate(){
//        listaProgramaeducativo = new ArrayList<Programaeducativo>();
//    }
//
//    public List<Programaeducativo> getListaProgramaeducativo() {
//        listaProgramaeducativo = ServiceFacadeLocator.getInstanceFacadeProgramaeducativo().consultaProgramaEducativo();
//        return listaProgramaeducativo;
//    }
//        public List<Programaeducativo> getListaUnidadAprendisajeFFWD(String de,String campo,String criterio) {
//        listaProgramaeducativo = ServiceFacadeLocator.getInstanceFacadeProgramaeducativo().buscarProgramaEducativo(de, campo, criterio);
//        return listaProgramaeducativo;
//    }
//
//    public void setListaProgramaeducativo(List<Programaeducativo> listaProgramaeducativo) {
//        this.listaProgramaeducativo = listaProgramaeducativo;
//    }
//    
//  
//     private List<Programaeducativo> listaProgramaEducativo;
//
//
//    public List<Programaeducativo> getListaProgramaEducativo() {
//        listaProgramaEducativo = ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().consultaProgramaEducativo();
//        
//        return listaProgramaEducativo;
//    }
//
//    public void setListaProgramaEducativo(List<Programaeducativo> listaProgramaEducativo) {
//        this.listaProgramaEducativo = listaProgramaEducativo;
//    }
//    
//    public void agregarProgramaEducativo(Programaeducativo programaeducativo){
//        ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().agregarProgramaEducativo(programaeducativo);
//    }
//    
//    public void eliminarProgramaEducativo(Programaeducativo programaeducativo){
//        ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().eliminarProgramaEducativo(programaeducativo);
//    }
//    public Programaeducativo findProgramaEducativoById(int id){
//        return ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().getProgramaEducativo(id);
//    }
//
//    public List<Programaeducativo> getPEProf(int proid){
//        listaProgramaEducativo = ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().consultaPE(proid);
//        return listaProgramaEducativo;
//    }

//  public List<Responsableprogramaeducativo> getListarRPE(){
//        List<Responsableprogramaeducativo> listaRPE=new ArrayList();
//      listaRPE = ServiceFacadeLocator.getInstanceFacadeProgramaeducativo().consultaRPE();
//        return listaRPE;
//    } 

        //Metodo que enviara mensaje si la unidad de aprendizaje se encuentra
        //en mas de un Programa educativo
    public List<Programaeducativo>  obtenerProgramasEducativosDeUA(int uaId){
        return ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().obtenerProgramasEducativosDeUA(uaId);
    }

    
    //Responsable de PE
//    public Programaeducativo getResponsablePE(int proid){
//        Programaeducativo pe = ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().getResponsablePE(proid);
//        return pe;
//    }
        public List<Programaeducativo> getResponsablePE(int proid){
        List<Programaeducativo> pe = ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().getResponsablePE(proid);
        return pe;
    }

    public List<Programaeducativo> getPEperteneceProf(int proid){
        List<Programaeducativo> pe = ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().getPEperteneceProf(proid);
        return pe;
    }
    
     //metodos Nuevos
    public List<Programaeducativo> getPEdeCoordinadorAreaAdmin(int profResponsableId){
        List<Programaeducativo> programaeducativo = ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().getPEdeCoordinadorAreaAdmin(profResponsableId);
        return programaeducativo;
    }


}
