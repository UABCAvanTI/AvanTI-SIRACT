/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Cicloescolar;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Manuel
 */
public class CicloEscolarDelegate implements Serializable{
    private List<Cicloescolar> listaCicloEscolar;
    
    public CicloEscolarDelegate(){
        listaCicloEscolar = new ArrayList<Cicloescolar>();
    }

        public Cicloescolar find(int id){
        Cicloescolar c=ServiceFacadeLocator.getFacadeCicloEscolar().find(id);
        return c;
    }
      
    public List<Cicloescolar> getListaCicloEscolar() {
        listaCicloEscolar = ServiceFacadeLocator.getFacadeCicloEscolar().findAll();
        return listaCicloEscolar;
    }

    public void setListaCicloEscolar(List<Cicloescolar> listaCicloEscolar) {
        this.listaCicloEscolar = listaCicloEscolar;
    }
    
    public void agregarCicloEscolar(Cicloescolar cicloescolar){
        ServiceFacadeLocator.getFacadeCicloEscolar().agregarCicloEscolar(cicloescolar);
    }
    
    public void eliminarCicloEscolar(Cicloescolar cicloEscolar){
        ServiceFacadeLocator.getFacadeCicloEscolar().eliminarCicloEscolar(cicloEscolar);
    }
    
        public Cicloescolar cicloescolarActual(){
            Cicloescolar cicloEscolarActual = ServiceFacadeLocator.getFacadeCicloEscolar().cicloEscolarActual();
            return cicloEscolarActual;
    }
        
        
 
        public Cicloescolar findCicloEscolarById(int id){
        return ServiceFacadeLocator.getFacadeCicloEscolar().getCicloEscolar(id);
    }

        
}
