/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.business;

import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Practicalaboratorio;
import mx.avanti.siract.business.entity.Practicataller;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Maria luisa
 */
public class PracticasTallerDelegate {
     private List<Practicataller> listaPracticasTall;
    
    public PracticasTallerDelegate(){
        listaPracticasTall=new ArrayList<Practicataller> ();
    }
    


    public void setListaPracticasTall(List<Practicataller> listaPracticasTall){
        this.listaPracticasTall=listaPracticasTall;
    }
    public List<Practicataller> getListaPracticasTall(String de,String campo,String criterio,String order){
        listaPracticasTall = ServiceFacadeLocator.getfacadePracticasTall().consultaPracticasTallFromWhere(de, campo, criterio, order);
        return listaPracticasTall;
    }
    public List<Practicataller> getListaPracticasTallFromWhere(String de,String campo,String criterio,String order){
        listaPracticasTall = ServiceFacadeLocator.getfacadePracticasTall().consultaPracticasTallFromWhere(de, campo, criterio, order);
        
        return listaPracticasTall;
    }
    public void agregarPracticaTall(Practicataller practicasTall){
        ServiceFacadeLocator.getfacadePracticasTall().agregarPracticasTall(practicasTall);
   
    }
    public void eliminarPracticaTall(Practicataller practicasTall){
        ServiceFacadeLocator.getfacadePracticasTall().eliminarPracticasTall(practicasTall);
    
    }
    
 
    public List<Practicataller> consultarPracticas(String campo, String criterio){
        listaPracticasTall = ServiceFacadeLocator.getfacadePracticasTall().consultaPracticataller(campo, criterio);
        return listaPracticasTall;
    }
    
    public void agregarPracticaTallerCatalogo(int numPracticasTaller, Unidadaprendizaje ua){
        ServiceFacadeLocator.getfacadePracticasTall().agregarPracticasTaller(numPracticasTaller, ua);
   
    }
    
      public List<Practicataller> findAll(){
        listaPracticasTall = ServiceFacadeLocator.getfacadePracticasTall().findAll();
        return listaPracticasTall;
    }
}
