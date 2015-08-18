/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.PracticasLaboratorioDelegate;
import mx.avanti.siract.business.entity.Practicalaboratorio;
import mx.avanti.siract.business.entity.Temaunidad;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Maria luisa
 */
public class FacadePracticasLaboratorio {
     public void agregarPracticasLab(Practicalaboratorio practicaLab){
        Practicalaboratorio resultado = null;
        if(practicaLab.getPrlid() !=null){
                
            ServiceLocator.getInstanceBaseDAO().setTipo(Practicalaboratorio.class);
            resultado = (Practicalaboratorio) ServiceLocator.getInstanceBaseDAO().find(practicaLab.getPrlid());
        }
        if(resultado != null){
            practicaLab.setPrlid(resultado.getPrlid());
            
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(practicaLab);
        } else{
            ServiceLocator.getInstanceBaseDAO().save(practicaLab);
        }
    }
    
    public List<Practicalaboratorio> consultaPracticasLabFromWhere(String de,String campo,String criterio,String order){
        List<Practicalaboratorio> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Practicalaboratorio.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhereBExtra(de, campo, criterio, "Length("+order+"), "+order+" ASC","Practicalaboratorio");
        return resultado;
    }
    
       
    
    public void eliminarPracticasLab(Practicalaboratorio practicasLab){
        ServiceLocator.getInstanceBaseDAO().delete(practicasLab);
    }

    public void agregarPracticasLab(int numero, Unidadaprendizaje ua) {
        ServiceLocator.getInstanceBaseDAO().setTipo(Practicalaboratorio.class);
        float horas = (float) 0.0;
        float porcentaje = (float) 0.0;
        for(int i=0;i<numero;i++){
            Practicalaboratorio practicalaboratorio;
            practicalaboratorio = new Practicalaboratorio(ua,0,"Practica " + (i+1),porcentaje,horas);
            ServiceLocator.getInstanceBaseDAO().save(practicalaboratorio);
        }
    }


    public List<Practicalaboratorio> consultaPracticalaboratorio(String campo, String criterio) {
        List<Practicalaboratorio> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Practicalaboratorio.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findWhereExtra(campo, criterio, "Practicalaboratorio","prlnumero");
        return resultado;
    }
    
       public List<Practicalaboratorio> findAll() {
        List<Practicalaboratorio> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Practicalaboratorio.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
        return resultado;
    }
}
