/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.Practicascampo;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Y
 */
public class FacadePracticascampo {
        public List<Practicascampo> consultaPracticasCampoFromWhere(String de,String campo,String criterio,String order){
        List<Practicascampo> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Practicascampo.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhereBExtra(de, campo, criterio, "length("+order+"),"+order+" ASC","Practicascampo");
        return resultado;
    }
        
         public void agregarPracticasCampo(Practicascampo practicaCampo){
        Practicascampo resultado = null;
        if(practicaCampo.getPrcid()!=null){
                
            ServiceLocator.getInstanceBaseDAO().setTipo(Practicascampo.class);
            resultado = (Practicascampo) ServiceLocator.getInstanceBaseDAO().find(practicaCampo.getPrcid());
        }
        if(resultado != null){
            practicaCampo.setPrcid(resultado.getPrcid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(practicaCampo);
        } else{
            ServiceLocator.getInstanceBaseDAO().save(practicaCampo);
        }
    }
    
    public void eliminarPracticasCampo(Practicascampo practicasCampo){
        ServiceLocator.getInstanceBaseDAO().delete(practicasCampo);
    }
    
    public List<Practicascampo> consultaPracticascampo(String campo, String criterio) {
        List<Practicascampo> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Practicascampo.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findWhereExtra(campo, criterio,"Practicascampo","prcnumero");
        return resultado;
    }
        
}
