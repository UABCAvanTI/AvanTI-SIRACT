/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.business;

import java.util.List;
import mx.avanti.siract.business.entity.Alerta;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Eduardo
 */
public class AlertaDelegate {
    private List<Alerta> alertas;
    private Alerta alerta;
    
    public List<Alerta> getAlertas(){
        alertas=ServiceFacadeLocator.getFacadeAlerta().findAll();
        return alertas;
    }
    
    public void saveAlertas(List<Alerta> alertas){
        for (int x=0; x<alertas.size(); x++){
            ServiceFacadeLocator.getFacadeAlerta().saveAlerta(alertas.get(x));
        }
    }
    
    public Alerta findAlerta(String tipo){
        alerta=ServiceFacadeLocator.getFacadeAlerta().find(tipo);
        return alerta;
    }
}
