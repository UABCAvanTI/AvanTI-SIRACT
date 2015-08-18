/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.business;

import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.UnidadaprendizajeImparteProfesor;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Y
 */
public class UnidadaprendizajeImparteProfesorDelegate {
    
    private List<UnidadaprendizajeImparteProfesor> listaUnidad;
    
    public UnidadaprendizajeImparteProfesorDelegate(){
        listaUnidad=new ArrayList<UnidadaprendizajeImparteProfesor> ();
    }


    public List<UnidadaprendizajeImparteProfesor> getListaUnidadFFWT(String de,String de2,String campo2,String criterio2,String de3,String campo3,String criterio3,String order){
        listaUnidad = new ArrayList(
                ServiceFacadeLocator.getFacadeUnidadaprendisajeimparteprofesor().
                        consultaFFWTUnidadaprendizajeImparteProfesor(de, de2, campo2, criterio2, de3, campo3, criterio3, order)
                );
        return listaUnidad;
    }
    
             public List<UnidadaprendizajeImparteProfesor> consultaFFW(String de, String campo, String criterio, String order){
        List<UnidadaprendizajeImparteProfesor> resultado = null;
        resultado =   ServiceFacadeLocator.getFacadeUnidadaprendisajeimparteprofesor().consultaFFW(de, campo, criterio, order);
        return resultado;
    }
    
      public UnidadaprendizajeImparteProfesor buscarUnidadAprendizajeImparteProfesor(int id_unidadAprendizaje, int id_profesor, int id_grupo,String tipo_grupo,String subGrupo) {
        UnidadaprendizajeImparteProfesor resultado = null;
          System.out.println("BUSCANDO UNIDADAPRENDIZAJEIMPARTEPROFESOR");
        resultado = ServiceFacadeLocator.getFacadeUnidadaprendisajeimparteprofesor().
                buscarUnidadAprendizajeImparteProfesor(id_unidadAprendizaje, id_profesor, id_grupo,tipo_grupo,subGrupo);
        return resultado;
    }
 
}
