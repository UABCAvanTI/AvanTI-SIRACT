/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author balta
 */
public class FacadeUnidadAprendizaje {
    public void agregarUnidadAprendizaje(Unidadaprendizaje unidadAprendizaje){
        Unidadaprendizaje resultado = null;
        if(unidadAprendizaje.getUapid() != null){
            ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
            resultado = (Unidadaprendizaje) ServiceLocator.getInstanceBaseDAO().find(unidadAprendizaje.getUapid());
        }
        if(resultado != null){
            unidadAprendizaje.setUapid(resultado.getUapid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(unidadAprendizaje);
        } else{
            ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
            ServiceLocator.getInstanceBaseDAO().save(unidadAprendizaje);
        }
    }
    
    public List<Unidadaprendizaje> consultaUnidadAprendizaje(){
        List<Unidadaprendizaje> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
        return resultado;
    }
        public List<Unidadaprendizaje> consultaFFWTUnidadesAprendisaje(String de,String de2,String campo2,String criterio2,String de3,String campo3,String criterio3,String order){
        List<Unidadaprendizaje> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        
//                listaUnidadesAprendisaje = unidadAprendisajeDelegate.getListaUnidadAprendisajeFFWD("unidadaprendizajeImparteProfesors", "profesor.proid", profesor.getProid().toString(), "areaconocimiento.planestudio.programaeducativo", "pedid", areaConocimientoSeleccionada, "uapnombre");

        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhere("Unidadaprendizaje","areaconocimientos ac join a.unidadaprendizajeImparteProfesors","profesor.proid='"+criterio2+"' AND ac.planestudio.programaeducativo.pedid ",criterio3);
        return resultado;
    }
        
                  public Unidadaprendizaje getUnidadAprendazaje(int idUnidadAprendazaje){
          ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
          return (Unidadaprendizaje)ServiceLocator.getInstanceBaseDAO().find(idUnidadAprendazaje);
      }
                   
    
    public void eliminarUnidadAprendizaje(Unidadaprendizaje unidadAprendizaje){
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        ServiceLocator.getInstanceBaseDAO().delete(unidadAprendizaje);
    }
    
    /***********************************************Metodos requeridos por generador de reportes***********************************************************/
//       public List<Unidadaprendizaje> findAll(){
//        List<Unidadaprendizaje> result = null;
//        result = ServiceLocator.getInstanceUnidadaprendizaje().findAllUnidadaprendizajes();
//        return result;
//    }
//    
//    public List<Unidadaprendizaje> findByAreaconocimiento(int idPlan){
//        List<Unidadaprendizaje> result = null;
//        result = ServiceLocator.getInstanceUnidadaprendizaje().findByCriteria(idPlan);
//        return result;
//    }
    /*************************************************************************************************************************************************/

    public void add(Unidadaprendizaje unidadAprendizaje){
        Unidadaprendizaje resultado = null;
        if(unidadAprendizaje.getUapid() != null){
            ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
            resultado = (Unidadaprendizaje) ServiceLocator.getInstanceBaseDAO().find(unidadAprendizaje.getUapid());
        }
        if(resultado != null){
            unidadAprendizaje.setUapid(resultado.getUapid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(unidadAprendizaje);
        } else{
            ServiceLocator.getInstanceBaseDAO().save(unidadAprendizaje);
        }
    }
    
    public List<Unidadaprendizaje> findAll(){
        List<Unidadaprendizaje> result = null;
        result = ServiceLocator.getInstanceUnidadaprendizaje().findAllUnidadaprendizajes();
        return result;
    }
    
    public Unidadaprendizaje find(int id){
        Unidadaprendizaje result = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        result = (Unidadaprendizaje) ServiceLocator.getInstanceBaseDAO().findUap(id);
        return result;
    }
    
      public Unidadaprendizaje find(int id,String acoId){
        Unidadaprendizaje result = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        result = (Unidadaprendizaje) ServiceLocator.getInstanceBaseDAO().findUap(id,acoId);
        return result;
    }
      
    public List<Unidadaprendizaje> findByAreaconocimiento(int idArea){
        List<Unidadaprendizaje> result = null;
        result = ServiceLocator.getInstanceUnidadaprendizaje().findByCriteria(idArea);
        return result;
    }
    
    public List<Unidadaprendizaje> findByUnidadAcademica(int idUnidadAcademica){
        List<Unidadaprendizaje> result = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        result =ServiceLocator.getInstanceBaseDAO().findFromWhere("areaconocimientos","planestudio.programaeducativo.unidadacademica.uacid",String.valueOf(idUnidadAcademica));
        return result;
    }
    
    public List<Unidadaprendizaje> findByResponsable(int idUnidadAcademica){
        List<Unidadaprendizaje> result = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        result =ServiceLocator.getInstanceBaseDAO().findFromWhere("coordinadorareaadministrativas","profesor.usuario.usuid",String.valueOf(idUnidadAcademica));
        return result;
    }
    
     public List<Unidadaprendizaje> findByAreaConocimiento(String idArea){
        List<Unidadaprendizaje> result = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        result =ServiceLocator.getInstanceBaseDAO().findFromWhere("areaconocimientos","acoid",String.valueOf(idArea));
        return result;
    }
      public List<Unidadaprendizaje> findByAreaConocimientoAndPE(String idArea,String idPE){
        List<Unidadaprendizaje> result = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        result =ServiceLocator.getInstanceBaseDAO().findFromWhere("areaconocimientos","acoid",String.valueOf(idArea)+" AND "
                +"b.planestudio.programaeducativo.pedid='"+idPE+"'");
        return result;
    }
        public List<Unidadaprendizaje> findByPlanEstudio(String idPlan){
        List<Unidadaprendizaje> result = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        result =ServiceLocator.getInstanceBaseDAO().findFromWhere("areaconocimientos","planestudio.pesid",idPlan);
        return result;
    }
        
         public List<Unidadaprendizaje> findByPlanEstudioAndEtapa(String idPlan,String etapa){
        List<Unidadaprendizaje> result = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        result =ServiceLocator.getInstanceBaseDAO().findFromWhere("areaconocimientos","planestudio.pesid",idPlan+" AND "
                +"a.uapetapaFormacion='"+etapa+"'");
        return result;
    }
      
      public List<Unidadaprendizaje> findByEtapaAndPE(String etapa,String idPE){
        List<Unidadaprendizaje> result = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        result =ServiceLocator.getInstanceBaseDAO().findFromWhere("areaconocimientos","planestudio.programaeducativo.pedid",String.valueOf(idPE)+" AND "
                +"a.uapetapaFormacion='"+etapa+"'");
        return result;
    }
     
    public List<Unidadaprendizaje> findByPE(String idPE){
        List<Unidadaprendizaje> result = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        result =ServiceLocator.getInstanceBaseDAO().findFromWhere("areaconocimientos","planestudio.programaeducativo.pedid",String.valueOf(idPE));
        return result;
    }
    
    
    
    
    public List<Unidadaprendizaje> findByAreaconocimiento2(int idArea,String etapa){
        List<Unidadaprendizaje> result = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
       result = ServiceLocator.getInstanceBaseDAO().findFromWhere("Unidadaprendizaje","areaconocimientos","acoid",String.valueOf(idArea)+" AND a.uapetapaFormacion='"+etapa+"'");
//        result = ServiceLocator.getInstanceUnidadaprendizaje().findByCriteria2(idArea, etapa);
        return result;
    }
    
    public List<Unidadaprendizaje> findByAreaconocimientoAndEtapaAndPE(int idArea,String etapa,String idPE){
        List<Unidadaprendizaje> result = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
       result = ServiceLocator.getInstanceBaseDAO().findFromWhere("Unidadaprendizaje","areaconocimientos","acoid",String.valueOf(idArea)+" AND a.uapetapaFormacion='"+etapa+"' AND b.planestudio.programaeducativo.pedid='"+idPE+"'");
//        result = ServiceLocator.getInstanceUnidadaprendizaje().findByCriteria2(idArea, etapa);
        return result;
    }
    
      public List<Unidadaprendizaje> findByEtapa(String etapa){
        List<Unidadaprendizaje> result = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
       result = ServiceLocator.getInstanceBaseDAO().findWhere("Unidadaprendizaje a join a.areaconocimientos b","uapetapaFormacion",etapa,"uapnombre");
//        result = ServiceLocator.getInstanceUnidadaprendizaje().findByCriteria2(idArea, etapa);
        return result;
    }
    
    
    
    //Componente RACT
    public List<Unidadaprendizaje> consultaFFWDUnidadesAprendizaje(String de,String campo,String criterio,String de2,String campo2,String criterio2,String order){
        List<Unidadaprendizaje> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findFromWhereDoble(de, campo, criterio, de2, campo2, criterio2, order);
        return resultado;
    }
      
       public List<Unidadaprendizaje> findUAByClave(String clave){
        List<Unidadaprendizaje> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findWhereExtra("uapclave","'"+ clave+"'","Unidadaprendizaje","uapnombre");
        return resultado;
    }
       
           public List<Unidadaprendizaje> getUAMismaArea(int idArea) {
        List<Unidadaprendizaje> listaUA = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        listaUA = ServiceLocator.getInstanceBaseDAO().findFromWhere("areaconocimientos", "acoid", String.valueOf(idArea));
        return listaUA;
    }
           public List<Unidadaprendizaje> findByAreaconocimientoClave(int acoclave){
        List<Unidadaprendizaje> result = null;
        result = ServiceLocator.getInstanceUnidadaprendizaje().findByCriteriaClave(acoclave);
        return result;
    }
           
            public List<Unidadaprendizaje> findByRPE(String proId){
        List<Unidadaprendizaje> result = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        result =ServiceLocator.getInstanceBaseDAO().findFromWhere("areaconocimientos c join c.planestudio.programaeducativo.profesors_1","proid",String.valueOf(proId));
        return result;
    }
            public List<Unidadaprendizaje> findByAreaConocimientoRPE(String idArea, String proId){
        List<Unidadaprendizaje> result = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        result =ServiceLocator.getInstanceBaseDAO().findWhere("Unidadaprendizaje b join b.areaconocimientos a join a.planestudio.programaeducativo.profesors_1 c","acoid",idArea+"' and c.proid='"+proId,"uapnombre");
        return result;
    }

            public List<Unidadaprendizaje> findByEtapaRPE(String etapa, String proId){
        List<Unidadaprendizaje> result = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
       result = ServiceLocator.getInstanceBaseDAO().findWhere("Unidadaprendizaje a join a.areaconocimientos b join b.planestudio.programaeducativo.profesors_1 c","uapetapaFormacion",etapa+"' and c.proid='"+proId,"uapnombre");
//        result = ServiceLocator.getInstanceUnidadaprendizaje().findByCriteria2(idArea, etapa);
        return result;
}
             //Metodos Nuevos
            public List<Unidadaprendizaje> findByEtapaCAA(String etapa, String proId){
        List<Unidadaprendizaje> result = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
       result = ServiceLocator.getInstanceBaseDAO().findWhere("Unidadaprendizaje a join a.coordinadorareaadministrativas c","uapetapaFormacion",etapa+"' and c.profesor.proid='"+proId,"uapnombre");
//        result = ServiceLocator.getInstanceUnidadaprendizaje().findByCriteria2(idArea, etapa);
        return result;
    }
            
             public List<Unidadaprendizaje> findByAreaConocimientoCAA(String idArea, String proId){
        List<Unidadaprendizaje> result = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        result =ServiceLocator.getInstanceBaseDAO().findWhere("Unidadaprendizaje b join b.areaconocimientos a join b.coordinadorareaadministrativas c","acoid",idArea+"' and c.profesor.proid='"+proId,"uapnombre");
        return result;
    }
             
        public List<Unidadaprendizaje> findByEtapaAndPECAA(String etapa,String idPE,String proId){
        List<Unidadaprendizaje> result = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        result =ServiceLocator.getInstanceBaseDAO().findFromWhere("coordinadorareaadministrativas c join a.areaconocimientos","planestudio.programaeducativo.pedid","'"+String.valueOf(idPE)
                +"' AND "
                +"a.uapetapaFormacion='"+etapa+"' AND "
                +"c.profesor.proid='"+proId+"'");
        return result;
    }
          public List<Unidadaprendizaje> findByAreaConocimientoAndCAA(String idArea,String idPE,String proId){
        List<Unidadaprendizaje> result = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        result =ServiceLocator.getInstanceBaseDAO().findFromWhere("coordinadorareaadministrativas c join a.areaconocimientos","acoid",String.valueOf(idArea)+" AND "
                +"b.planestudio.programaeducativo.pedid='"+idPE+"' AND "
                +"c.profesor.proid='"+proId+"'");
        return result;
    }        
      public List<Unidadaprendizaje> findByCAA(String proId){
        List<Unidadaprendizaje> result = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        result =ServiceLocator.getInstanceBaseDAO().findFromWhere("coordinadorareaadministrativas","profesor.proid",proId);
        return result;
    }
      
      public List<Unidadaprendizaje> findByAreaconocimientoCAA(int idArea,String etapa,String proId){
        List<Unidadaprendizaje> result = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
       result = ServiceLocator.getInstanceBaseDAO().findFromWhere("Unidadaprendizaje","coordinadorareaadministrativas c join a.areaconocimientos","acoid",String.valueOf(idArea)+
               " AND a.uapetapaFormacion='"+etapa+"' AND "
              +"c.profesor.proid='"+proId+"'");
//        result = ServiceLocator.getInstanceUnidadaprendizaje().findByCriteria2(idArea, etapa);
        return result;
    }
      
      public List<Unidadaprendizaje> findByAreaconocimientoAndEtapaAndPECAA(int idArea,String etapa,String idPE,String proId){
        List<Unidadaprendizaje> result = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
       result = ServiceLocator.getInstanceBaseDAO().findFromWhere("Unidadaprendizaje","coordinadorareaadministrativas c join a.areaconocimientos","acoid",String.valueOf(idArea)+
               " AND a.uapetapaFormacion='"+etapa+
               "' AND b.planestudio.programaeducativo.pedid='"+idPE+"' AND "+
               "c.profesor.proid='"+proId+"'");
//        result = ServiceLocator.getInstanceUnidadaprendizaje().findByCriteria2(idArea, etapa);
        return result;
    }
      
      public List<Unidadaprendizaje> findByPlanEstudioCAA(String idPlan,String proId){
        List<Unidadaprendizaje> result = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        result =ServiceLocator.getInstanceBaseDAO().findFromWhere("coordinadorareaadministrativas c join a.areaconocimientos","planestudio.pesid='"+idPlan+"'"+
                 " AND c.profesor.proid",
                proId);
        return result;
    }
      
      public List<Unidadaprendizaje> findByPECAA(String idPE,String proId){
        List<Unidadaprendizaje> result = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Unidadaprendizaje.class);
        result =ServiceLocator.getInstanceBaseDAO().findFromWhere("coordinadorareaadministrativas c join a.areaconocimientos","planestudio.programaeducativo.pedid",String.valueOf(idPE)+" AND c.profesor.proid='"+proId+"'" );
        return result;
    }
}
