/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Usagi
 */
public class ProfesorDelegate implements Serializable{
    
    
    
     private List<Profesor> profesores;
    private List<Profesor> listaProfesor;

    public List<Profesor> getProfesores() {
        profesores = ServiceFacadeLocator.getFacadeProfesor().consultaProfesores();
        return profesores;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }
    
    public Profesor findProfesorFromUser(int idUsuario){
        Profesor profesor = ServiceFacadeLocator.getFacadeProfesor().findProfesorFromUser(idUsuario);
        return profesor;
    }
    
    public Profesor profIDUs(int idUsuario){
        return ServiceFacadeLocator.getInstanceFacadeProfesor().findProfesorFromUser(idUsuario);
    }
        public List<Profesor> getListaProfesor() {
        listaProfesor = ServiceFacadeLocator.getInstanceFacadeProfesor().consultaProfesor();
        return listaProfesor;
    }
        
          public Profesor findProfesorById(int id){
        return ServiceFacadeLocator.getInstanceFacadeProfesor().getProfesor(id);
    }
          
          
    
    public ProfesorDelegate(){
        listaProfesor = new ArrayList<Profesor>();
    }

    public void setListaProfesor(List<Profesor> listaProfesor) {
        this.listaProfesor = listaProfesor;
    }
    
    public void agregarProfesor(Profesor profesor){
        ServiceFacadeLocator.getInstanceFacadeProfesor().agregarProfesor(profesor);
    }
    
    public void eliminarProfesor(Profesor profesor){
        ServiceFacadeLocator.getInstanceFacadeProfesor().eliminarProfesor(profesor);
    }
    
    
     public List<Profesor> getProfPE(int pedid){
        return ServiceFacadeLocator.getInstanceFacadeProfesor().consultaProfPE(pedid);
    }

          
     public  List<Profesor> getProfMismoPE(int pedid){
        return ServiceFacadeLocator.getInstanceFacadeProfesor().getProfMismoPE(pedid);
    }
     
     public List<Profesor> getProfesorPorPE(int pedid){
         return ServiceFacadeLocator.getInstanceFacadeProfesor().consultaProfPorPe(pedid);
     }
    
     
     
     //PARTE OMITIDA DESPUES DE AGREGAR CATALOGO PROFESOR
    
//    
//     private List<Profesor> profesores;
//    private List<Profesor> listaProfesor;
//
//    public List<Profesor> getProfesores() {
//        profesores = ServiceFacadeLocator.getFacadeProfesor().consultaProfesores();
//        return profesores;
//    }
//
//    public void setProfesores(List<Profesor> profesores) {
//        this.profesores = profesores;
//    }
//    
//    public Profesor findProfesorFromUser(int idUsuario){
//        Profesor profesor = ServiceFacadeLocator.getFacadeProfesor().findProfesorFromUser(idUsuario);
//        return profesor;
//    }
//    
//    public Profesor profIDUs(int idUsuario){
//        return ServiceFacadeLocator.getInstanceFacadeProfesor().findProfesorFromUser(idUsuario);
//    }
//        public List<Profesor> getListaProfesor() {
//        listaProfesor = ServiceFacadeLocator.getInstanceFacadeProfesor().consultaProfesor();
//        return listaProfesor;
//    }
//        
//          public Profesor findProfesorById(int id){
//        return ServiceFacadeLocator.getInstanceFacadeProfesor().getProfesor(id);
//    }
//          
//          
//    
//    public ProfesorDelegate(){
//        listaProfesor = new ArrayList<Profesor>();
//    }
//
//    public void setListaProfesor(List<Profesor> listaProfesor) {
//        this.listaProfesor = listaProfesor;
//    }
//    
//    public void agregarProfesor(Profesor profesor){
//        ServiceFacadeLocator.getInstanceFacadeProfesor().agregarProfesor(profesor);
//    }
//    
//    public void eliminarProfesor(Profesor profesor){
//        ServiceFacadeLocator.getInstanceFacadeProfesor().eliminarProfesor(profesor);
//    }
//    
//    
//     public List<Profesor> getProfPE(int pedid){
//        return ServiceFacadeLocator.getInstanceFacadeProfesor().consultaProfPE(pedid);
//    }

     public List<Profesor> getProfAsignado(int idProf){
         return ServiceFacadeLocator.getInstanceFacadeProfesor().getProfAsignado(idProf);
     }
          
    
}
