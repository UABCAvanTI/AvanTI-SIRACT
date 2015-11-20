/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.business.logic;

import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.CalendarioreporteTieneAlerta;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Y
 */
public class FacadeCalendarioreporteTieneAlerta {
    public List<CalendarioreporteTieneAlerta> getCalendariosFechaActual(String idCalendarioreporte){
        ServiceLocator.getInstanceBaseDAO().setTipo(CalendarioreporteTieneAlerta.class);
        return ServiceLocator.getInstanceBaseDAO().findFromWhere("CalendarioreporteTieneAlerta","calendarioreporte", "creid", idCalendarioreporte);
    }
    
     public void saveCalendarioreporteTieneAlerta(CalendarioreporteTieneAlerta calendarioAlerta){
        CalendarioreporteTieneAlerta result=null;
        //Busca si no existe el registro
        if (calendarioAlerta.getAlerta().getAleid()!=0&&calendarioAlerta.getCalendarioreporte().getCreid()!=0){
            //Si ninguno de los IDs es 0
            ServiceLocator.getInstanceBaseDAO().setTipo(CalendarioreporteTieneAlerta.class);
            result=(CalendarioreporteTieneAlerta) ServiceLocator.getInstanceBaseDAO()
                    .findCreAle(calendarioAlerta.getCalendarioreporte().getCreid(),
                            calendarioAlerta.getAlerta().getAleid());
        }
        if (result!=null){//Si existe, manda a modificar
            calendarioAlerta.getCalendarioreporte().setCreid(result.getCalendarioreporte().getCreid());
            ServiceLocator.getInstanceBaseDAO().setTipo(CalendarioreporteTieneAlerta.class);
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(calendarioAlerta);
        } else{//Si no, manda a insertar
            ServiceLocator.getInstanceBaseDAO().setTipo(CalendarioreporteTieneAlerta.class);
            ServiceLocator.getInstanceBaseDAO().save(calendarioAlerta);
        }
    }
    public List<CalendarioreporteTieneAlerta> findAll(){
        List<CalendarioreporteTieneAlerta> listaCalAlerta=null;
        ServiceLocator.getInstanceBaseDAO().setTipo(CalendarioreporteTieneAlerta.class);
        listaCalAlerta=ServiceLocator.getInstanceBaseDAO().findAll();
        return listaCalAlerta;
    }
    public CalendarioreporteTieneAlerta find(String tipo){
        ServiceLocator.getInstanceBaseDAO().setTipo(CalendarioreporteTieneAlerta.class);
        CalendarioreporteTieneAlerta calendarioAlerta=(CalendarioreporteTieneAlerta) ServiceLocator.getInstanceBaseDAO().findAlerta(tipo);
        return calendarioAlerta;
    }
    
    public CalendarioreporteTieneAlerta find(int creid, int aleid){//Busca una relación CRE/ALE específica por medio de IDs
        ServiceLocator.getInstanceBaseDAO().setTipo(CalendarioreporteTieneAlerta.class);
        List<CalendarioreporteTieneAlerta> listaCreAle=findByCRE(creid);
        CalendarioreporteTieneAlerta calendarioAlerta=listaCreAle.get(aleid-2);
        return calendarioAlerta;
    }
    public void deleteCalendarioAlerta(CalendarioreporteTieneAlerta calendarioAlerta){
        ServiceLocator.getInstanceBaseDAO().setTipo(CalendarioreporteTieneAlerta.class);
        ServiceLocator.getInstanceBaseDAO().delete(calendarioAlerta);
    }
    public List<CalendarioreporteTieneAlerta> findByCRE(int creid){
        //Busca relaciones Calendario/Alerta en base a un Calendario
        List<CalendarioreporteTieneAlerta> listaCreAle=null;
        ServiceLocator.getInstanceBaseDAO().setTipo(CalendarioreporteTieneAlerta.class);
        listaCreAle=new ArrayList(ServiceLocator.getInstanceBaseDAO().findCreAle(creid));
        return listaCreAle;
    }
    
}
