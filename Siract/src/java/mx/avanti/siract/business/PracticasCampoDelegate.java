/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.business;

import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Practicascampo;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Y
 */
public class PracticasCampoDelegate {
    
         private List<Practicascampo> listaPracticasCampo;
    
    public PracticasCampoDelegate(){
        listaPracticasCampo=new ArrayList<Practicascampo> ();
    }
    
        public List<Practicascampo> getListaPracticasCampo(String de,String campo,String criterio,String order){
        listaPracticasCampo = ServiceFacadeLocator.getFacadePracticasCampo().consultaPracticasCampoFromWhere(de, campo, criterio, order);
        return listaPracticasCampo;
    }
        

    public void setListaPracticasCampo(List<Practicascampo> listaPracticasCampo){
        this.listaPracticasCampo=listaPracticasCampo;
    }

    public List<Practicascampo> getListaPracticasCampoFromWhere(String de,String campo,String criterio,String order){
        listaPracticasCampo = ServiceFacadeLocator.getFacadePracticasCampo().consultaPracticasCampoFromWhere(de, campo, criterio, order);
        
        return listaPracticasCampo;
    }
    public void agregarPracticaCampo(Practicascampo practicasCampo){
        ServiceFacadeLocator.getFacadePracticasCampo().agregarPracticasCampo(practicasCampo);
   
    }
    public void eliminarPracticaCampo(Practicascampo practicasCampo){
        ServiceFacadeLocator.getFacadePracticasCampo().eliminarPracticasCampo(practicasCampo);
    
    }
    public List<Practicascampo> consultarPracticas(String campo, String criterio){
        listaPracticasCampo = ServiceFacadeLocator.getFacadePracticasCampo().consultaPracticascampo(campo, criterio);
        return listaPracticasCampo;
    }
    
        
        
}
