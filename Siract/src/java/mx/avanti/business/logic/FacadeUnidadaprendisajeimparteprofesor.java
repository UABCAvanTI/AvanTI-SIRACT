/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.UnidadaprendizajeImparteProfesor;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Y
 */
public class FacadeUnidadaprendisajeimparteprofesor {
    
    
    public List<UnidadaprendizajeImparteProfesor> consultaUnidadaprendizajeImparteProfesor(){
        List<UnidadaprendizajeImparteProfesor> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(UnidadaprendizajeImparteProfesor.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
        return resultado;
    }
        public List<UnidadaprendizajeImparteProfesor> consultaFFWTUnidadaprendizajeImparteProfesor(String de,String de2,String campo2,String criterio2,String de3,String campo3,String criterio3,String order){
        List<UnidadaprendizajeImparteProfesor> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(UnidadaprendizajeImparteProfesor.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhereTriple(de, de2, campo2, criterio2, de3, campo3, criterio3, order);
        return resultado;
    }
        
           public List<UnidadaprendizajeImparteProfesor> consultaFFW(String de, String campo, String criterio, String order){
        List<UnidadaprendizajeImparteProfesor> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(UnidadaprendizajeImparteProfesor.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhere("UnidadaprendizajeImparteProfesor",de, campo, criterio);
        return resultado;
    }
        
        
              public UnidadaprendizajeImparteProfesor buscarUnidadAprendizajeImparteProfesor(int clave_unidadAprendizaje,int id_profesor, int clase_grupo,String tipo_grupo,String subGrupo){
                  UnidadaprendizajeImparteProfesor resultado = null;
                  System.out.println("--clave "+clave_unidadAprendizaje+" ; id_Pro "+id_profesor+";clase_grp "+clase_grupo);
                  
                    ServiceLocator.getInstanceBaseDAO().setTipo(UnidadaprendizajeImparteProfesor.class);
                    String criterio="profesor.proid = "+id_profesor+" AND ";
//                    criterio+="unidadaprendizajeImparteProfesor.grupo.gpoid = "+id_grupo+" AND ";
                    criterio+="unidadaprendizaje.uapclave = "+clave_unidadAprendizaje+" AND uiptipoSubgrupo = '"+tipo_grupo+"' AND " ;
                    criterio+="uipsubgrupo = "+subGrupo;
            resultado = (UnidadaprendizajeImparteProfesor) ServiceLocator.getInstanceBaseDAO().findMultipleID(clase_grupo,criterio,"grupo.gponumero","");
            return resultado;
      }
       


    public UnidadaprendizajeImparteProfesor buscarUnidadAprendizajeImparteProfesor(int id_unidadAprendizaje, int id_profesor, int id_grupo) {
        UnidadaprendizajeImparteProfesor resultado = null;
        System.out.println("--id_UNI " + id_unidadAprendizaje + " ; id_Pro " + id_profesor + ";id_grp " + id_grupo);

        ServiceLocator.getInstanceBaseDAO().setTipo(UnidadaprendizajeImparteProfesor.class);
        String criterio = "profesor.proid = " + id_profesor + " AND ";
//                    criterio+="unidadaprendizajeImparteProfesor.grupo.gpoid = "+id_grupo+" AND ";
        criterio += "unidadaprendizaje.uapclave = " + id_unidadAprendizaje + "";
        resultado = (UnidadaprendizajeImparteProfesor) ServiceLocator.getInstanceBaseDAO().findMultipleID(id_grupo, criterio, "grupo.gponumero", "");
        return resultado;
    }  
    
    
    public void agregarUniAprenImparteProfe(UnidadaprendizajeImparteProfesor imaparteProfesor){
        UnidadaprendizajeImparteProfesor resultado = null;
        if(imaparteProfesor.getUipid() != null){
            ServiceLocator.getInstanceBaseDAO().setTipo(UnidadaprendizajeImparteProfesor.class);
            resultado = (UnidadaprendizajeImparteProfesor) ServiceLocator.getInstanceBaseDAO().find(imaparteProfesor.getUipid());
        }
        if(resultado != null){
            imaparteProfesor.setUipid(resultado.getUipid());
            ServiceLocator.getInstanceBaseDAO().setTipo(UnidadaprendizajeImparteProfesor.class);
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(imaparteProfesor);
        } else{
            ServiceLocator.getInstanceBaseDAO().setTipo(UnidadaprendizajeImparteProfesor.class);
            ServiceLocator.getInstanceBaseDAO().save(imaparteProfesor);
        }
    }
    
    public List<UnidadaprendizajeImparteProfesor> consultaUniAprenImparteProfe(){
        List<UnidadaprendizajeImparteProfesor> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(UnidadaprendizajeImparteProfesor.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
        return resultado;
    }
    
    public void eliminarUniAprenImparteProfe(UnidadaprendizajeImparteProfesor imaparteProfesor){
        ServiceLocator.getInstanceBaseDAO().setTipo(UnidadaprendizajeImparteProfesor.class);
        ServiceLocator.getInstanceBaseDAO().delete(imaparteProfesor);
    }
    
    public List<UnidadaprendizajeImparteProfesor> getUAIPDeProfesor(int idProfesor) {
        List<UnidadaprendizajeImparteProfesor> listaGpo = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(UnidadaprendizajeImparteProfesor.class);
        listaGpo = ServiceLocator.getInstanceBaseDAO().findFromWhereB("profesor", "proid", String.valueOf(idProfesor),"uipid");
        return listaGpo;
    }  

    public List<UnidadaprendizajeImparteProfesor> getAsignacionPorGPO(int idGrupo) {
        List<UnidadaprendizajeImparteProfesor> listaGpo = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(UnidadaprendizajeImparteProfesor.class);
        listaGpo = ServiceLocator.getInstanceBaseDAO().findFromWhere("grupo", "gpoid", String.valueOf(idGrupo));
        return listaGpo;
    }
    
}
