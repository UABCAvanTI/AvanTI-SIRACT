/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.business;

import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Practicalaboratorio;
import mx.avanti.siract.business.entity.Temaunidad;
import mx.avanti.siract.business.entity.Unidad;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Maria luisa
 */
public class PracticasLaboratorioDelegate {
    private List<Practicalaboratorio> listaPracticasLab;
    
    public PracticasLaboratorioDelegate(){
        listaPracticasLab=new ArrayList<Practicalaboratorio> ();
    }
    


    public void setListaPracticasLab(List<Practicalaboratorio> listaPracticasLab){
        this.listaPracticasLab=listaPracticasLab;
    }
    public List<Practicalaboratorio> getListaPracticasLab(String de,String campo,String criterio,String order){
        listaPracticasLab = ServiceFacadeLocator.getfacadePracticasLab().consultaPracticasLabFromWhere(de, campo, criterio, order);
        return listaPracticasLab;
    }
    public List<Practicalaboratorio> getListaPracticasLabFromWhere(String de,String campo,String criterio,String order){
        listaPracticasLab = ServiceFacadeLocator.getfacadePracticasLab().consultaPracticasLabFromWhere(de, campo, criterio, order);
        
        return listaPracticasLab;
    }
    public void agregarPracticaLab(Practicalaboratorio practicasLab){
        ServiceFacadeLocator.getfacadePracticasLab().agregarPracticasLab(practicasLab);
   
    }
    public void eliminarPracticaLab(Practicalaboratorio practicasLab){
        ServiceFacadeLocator.getfacadePracticasLab().eliminarPracticasLab(practicasLab);
    
    }


    public List<Practicalaboratorio> consultarPracticas(String campo, String criterio){
        listaPracticasLab = ServiceFacadeLocator.getfacadePracticasLab().consultaPracticalaboratorio(campo, criterio);
        return listaPracticasLab;
    }
    
 public List<Practicalaboratorio> findAll(){
        listaPracticasLab = ServiceFacadeLocator.getfacadePracticasLab().findAll();
        return listaPracticasLab;
    }
}
