/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.Practicalaboratorio;
import mx.avanti.siract.business.entity.Practicataller;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Maria luisa
 */
public class FacadePracticasTaller {
     public void agregarPracticasTall(Practicataller practicaTall){
        Practicataller resultado = null;
        if(practicaTall.getPrtid() !=null){
                
            ServiceLocator.getInstanceBaseDAO().setTipo(Practicataller.class);
            resultado = (Practicataller) ServiceLocator.getInstanceBaseDAO().find(practicaTall.getPrtid());
        }
        if(resultado != null){
            practicaTall.setPrtid(resultado.getPrtid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(practicaTall);
        } else{
            ServiceLocator.getInstanceBaseDAO().save(practicaTall);
        }
    }
    
    public List<Practicataller> consultaPracticasTallFromWhere(String de,String campo,String criterio,String order){
        List<Practicataller> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Practicataller.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhereBExtra(de, campo, criterio, "length("+order+"),"+order+" ASC","Practicataller");
        return resultado;
    }
    
       
    
    public void eliminarPracticasTall(Practicataller practicasTall){
        ServiceLocator.getInstanceBaseDAO().delete(practicasTall);
    }
 
     
     public void agregarPracticasTaller(int numero, Unidadaprendizaje ua) {
        ServiceLocator.getInstanceBaseDAO().setTipo(Practicataller.class);
        float horas = (float) 0.0;
        float porcentaje = (float) 0.0;
        for(int i=0;i<numero;i++){
            Practicataller practicataller;
            practicataller = new Practicataller(ua,0,"Practica " + (i+1),porcentaje,horas);
            ServiceLocator.getInstanceBaseDAO().save(practicataller);
        }
    }
    public List<Practicataller> consultaPracticataller(String campo, String criterio) {
        List<Practicataller> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Practicataller.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findWhereExtra(campo, criterio, "Practicataller","prtnumero");
        return resultado;
    }
    
        public List<Practicataller> findAll() {
        List<Practicataller> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Practicataller.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
        return resultado;
    }
    
}
