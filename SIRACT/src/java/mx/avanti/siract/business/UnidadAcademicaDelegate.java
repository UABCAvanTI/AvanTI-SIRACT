/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Unidadacademica;
import mx.avanti.siract.business.services.ServiceFacadeLocator;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author balta
 */
public class UnidadAcademicaDelegate implements Serializable{
    
    
    private List<Unidadacademica> listaUnidadAcademica;
    
    public UnidadAcademicaDelegate(){
        listaUnidadAcademica = new ArrayList<Unidadacademica>();
    }

    public List<Unidadacademica> getListaUnidadAcademica() {
        listaUnidadAcademica = ServiceFacadeLocator.getInstanceFacadeUnidadAcademica().consultaUnidadAcademica();
        return listaUnidadAcademica;
    }

    public void setListaUnidadAprendizaje(List<Unidadacademica> listaUnidadAcademica) {
        this.listaUnidadAcademica = listaUnidadAcademica;
    }
    
    public void agregarUnidadAcademica(Unidadacademica unidadAcademica){
        ServiceFacadeLocator.getInstanceFacadeUnidadAcademica().agregarUnidadAcademica(unidadAcademica);
    }
    
    public void eliminarUnidadAcademica(Unidadacademica unidadacademica){
        ServiceFacadeLocator.getInstanceFacadeUnidadAcademica().eliminarUnidadAcademica(unidadacademica);
    }
    public Unidadacademica findUnidadAcademicaById(int id){
        return ServiceFacadeLocator.getInstanceFacadeUnidadAcademica().getUnidadAcademica(id);
    }
    
    public List<Unidadacademica> getProfUAC(int uacid){
        return ServiceFacadeLocator.getInstanceFacadeUnidadAcademica().consultaProfUAC(uacid);
    }       
    
    public List<Unidadacademica> getUAasignado(Integer uacid){
        List<Unidadacademica> listaUAC;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadacademica.class);
        listaUAC = ServiceLocator.getInstanceBaseDAO().findFromWhereB("unidadacademica","uacid",String.valueOf(uacid),"pedid");
        return listaUAC;
    }  


    //PARTE OMITIDA DESPUES DE INTEGRAR CTALOGO PROFESOR
//    private List<Unidadacademica> listaUnidadAcademica;
//    
//    public UnidadAcademicaDelegate(){
//        listaUnidadAcademica = new ArrayList<Unidadacademica>();
//    }
//
//    public List<Unidadacademica> getListaUnidadAcademica() {
//        listaUnidadAcademica = ServiceFacadeLocator.getInstanceFacadeUnidadAcademica().consultaUnidadAcademica();
//        return listaUnidadAcademica;
//    }
//
//    public void setListaUnidadAprendizaje(List<Unidadacademica> listaUnidadAcademica) {
//        this.listaUnidadAcademica = listaUnidadAcademica;
//    }
//    
//    public void agregarUnidadAcademica(Unidadacademica unidadAcademica){
//        ServiceFacadeLocator.getInstanceFacadeUnidadAcademica().agregarUnidadAcademica(unidadAcademica);
//    }
//    
//    public void eliminarUnidadAcademica(Unidadacademica unidadacademica){
//        ServiceFacadeLocator.getInstanceFacadeUnidadAcademica().eliminarUnidadAcademica(unidadacademica);
//    }
//    public Unidadacademica findUnidadAcademicaById(int id){
//        return ServiceFacadeLocator.getInstanceFacadeUnidadAcademica().getUnidadAcademica(id);
//    }
//    
  
}
