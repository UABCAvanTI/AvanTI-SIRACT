/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.business;

import java.util.List;
import mx.avanti.siract.business.entity.Configuracion;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Eduardo
 */
public class ConfiguracionDelegate {    
    
   public Configuracion bConfPorCiclo(int id){
        Configuracion c=ServiceFacadeLocator.getFacadeConfiguracion().bConfPorCiclo(id);
        return c;
   }
   
   public void saveConfiguracion(Configuracion configuracion){
       ServiceFacadeLocator.getFacadeConfiguracion().saveConfiguracion(configuracion);
   }
   
   public Configuracion findConfiguracion(int id){
       Configuracion c=ServiceFacadeLocator.getFacadeConfiguracion().find(id);
       return c;
   }
}
