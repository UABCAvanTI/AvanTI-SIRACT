/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.business;

import java.util.List;
import mx.avanti.siract.business.entity.Alerta;
import mx.avanti.siract.business.entity.Mensaje;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Eduardo
 */
public class MensajeDelegate {
    private List<Mensaje> mensajes;
    
    public List<Mensaje> getMensajes(){
        mensajes=ServiceFacadeLocator.getFacadeMensaje().findAll();
        Mensaje m=new Mensaje();
        List<Alerta> alertas=ServiceFacadeLocator.getFacadeAlerta().findAll();
        for(int x=0; x<mensajes.size(); x++){
            m=mensajes.get(x);
            m.setAlerta(alertas.get(x));
            mensajes.set(x, m);
        }
        return mensajes;
    }
    
    public void saveMensaje(Mensaje mensaje){
        ServiceFacadeLocator.getFacadeMensaje().saveMensaje(mensaje);
    }
}
