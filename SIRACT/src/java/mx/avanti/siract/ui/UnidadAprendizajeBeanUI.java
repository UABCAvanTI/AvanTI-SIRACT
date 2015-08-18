/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.ui;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.avanti.siract.application.helper.UnidadAprendizajeBeanHelper;
import mx.avanti.siract.business.entity.Unidadaprendizaje;

/**
 *
 * @author balta
 */
@ManagedBean
@ViewScoped
public class UnidadAprendizajeBeanUI implements Serializable{
//    private UnidadAprendizajeBeanHelper unidadAprendizajeBeanHelper = null;
//    List<Unidadaprendizaje> listaFiltrada;
//    
//    public UnidadAprendizajeBeanUI(){
//        init();
//    }
//    
//    private void init(){
//        unidadAprendizajeBeanHelper = new UnidadAprendizajeBeanHelper();
//    }
//
//    public List<Unidadaprendizaje> getListaFiltrada() {
//        return listaFiltrada;
//    }
//
//    public void setListaFiltrada(List<Unidadaprendizaje> listaFiltrada) {
//        this.listaFiltrada = listaFiltrada;
//    }
//
//    public UnidadAprendizajeBeanHelper getUnidadAprendizajeBeanHelper() {
//        return unidadAprendizajeBeanHelper;
//    }
//    
//    public void nuevo(){
//        unidadAprendizajeBeanHelper.setUnidadaprendizaje(new Unidadaprendizaje());
//    }
//    
//    public void modificar(){
//        unidadAprendizajeBeanHelper.setUnidadaprendizaje(unidadAprendizajeBeanHelper.getSelecUnidadaprendizaje());
//    }
//    
//    public void eliminar(){
//        unidadAprendizajeBeanHelper.getUnidadAprendizajeDelegate().eliminarUnidadAprendizaje(unidadAprendizajeBeanHelper.getSelecUnidadaprendizaje());
//    }
//    
//    public String onClickSubmit(){
//        String resultado="";
//        unidadAprendizajeBeanHelper.getUnidadAprendizajeDelegate().agregarUnidadAprendizaje(unidadAprendizajeBeanHelper.getUnidadaprendizaje());
//        return resultado;
//    }
//    
//    public void filtrado(){
//        listaFiltrada = unidadAprendizajeBeanHelper.filtro("");
//    }
}
