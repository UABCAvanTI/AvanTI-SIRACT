/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Campus;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Manuel Papa
 */
public class CampusDelegate implements Serializable{
    private List<Campus> listaCampus;
     
    public CampusDelegate(){
        listaCampus = new ArrayList<Campus>();
    }

    public List<Campus> getListaCampus() {
        listaCampus = ServiceFacadeLocator.getInstanceFacadeCampus().consultaCampus();
        return listaCampus;
    }

    public void setListaCampus(List<Campus> listaCampus) {
        this.listaCampus = listaCampus;
    }
    
    public void agregarCampus(Campus campus){
        ServiceFacadeLocator.getInstanceFacadeCampus().agregarCampus(campus);
    }
    
    public void eliminarCampus(Campus campus){
        ServiceFacadeLocator.getInstanceFacadeCampus().eliminarCampus(campus);
    }
      public Campus findCampusById(int id){
        return ServiceFacadeLocator.getInstanceFacadeCampus().getCampus(id);
    }
}
