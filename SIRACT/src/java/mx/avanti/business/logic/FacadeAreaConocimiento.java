/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.List;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Manuel Papa
 */
public class FacadeAreaConocimiento {
      public void agregarAreaConocimiento(Areaconocimiento puesto){
        Areaconocimiento resultado = null;
        if(puesto.getAcoid()!= null){
               ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
       
            resultado = (Areaconocimiento) ServiceLocator.getInstanceBaseDAO().find(puesto.getAcoid());
        }
        if(resultado != null){
            puesto.setAcoid(resultado.getAcoid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(puesto);
        } else{
            ServiceLocator.getInstanceBaseDAO().save(puesto);
        }
    }
    
    public List<Areaconocimiento> consultaAreaConocimiento(){
        List<Areaconocimiento> resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
        return resultado;
    }
        public List<Areaconocimiento> consultarAreasConocimiento(String de,String campo,String criterio) {
            List<Areaconocimiento> listaAreaConocimiento = null;
           ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
        listaAreaConocimiento = ServiceLocator.getInstanceBaseDAO().findFromWhere("Areaconocimiento",de,campo, criterio);
        return listaAreaConocimiento;
    }
    
     public Areaconocimiento buscarAreaConocimiento(Areaconocimiento grupo){
        Areaconocimiento resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
        resultado = (Areaconocimiento)ServiceLocator.getInstanceBaseDAO().find(grupo.getAcoid());
        return resultado;
    }
     
     
    
    public void eliminarAreaConocimiento(Areaconocimiento areaconocimiento){
        ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
        ServiceLocator.getInstanceBaseDAO().delete(areaconocimiento);
    }
    /*********************************************Metodos requeriod por generador de reportes***************************************************************/
//      public List<Areaconocimiento> findAll(){
//        List<Areaconocimiento> result = null;
//        result = ServiceLocator.getInstanceAreaconocimiento().findAllAreaconocimientos();
//        return result;
//    }
    
//    public List<Areaconocimiento> findByPlanestudio(int idPlan){
//        List<Areaconocimiento> result = null;
//        result = ServiceLocator.getInstanceAreaconocimiento().findByCriteria(idPlan);
//        return result;
//    }
    
    /************************************************************************************************************************************************/

         public Areaconocimiento getAreaConocimiento(int idAreaConocimiento){
        ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
        return (Areaconocimiento)ServiceLocator.getInstanceBaseDAO().find(idAreaConocimiento);
    }   

     
    public List<Areaconocimiento> findAll(){
        List<Areaconocimiento> result = null;
        result = ServiceLocator.getInstanceAreaconocimiento().findAllAreaconocimientos();
        return result;
    }
    
    public List<Areaconocimiento> findByPlanestudio(int idPlan){
        List<Areaconocimiento> result = null;
        result = ServiceLocator.getInstanceAreaconocimiento().findByCriteria(idPlan);
        return result;
    }
    
    public List<Areaconocimiento> getAreasByPlanYProgramaeducativo(int idPlan,String idProgramaEducativo){
         List<Areaconocimiento> result = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
        result = ServiceLocator.getInstanceBaseDAO().findFromWhereB("planestudio", "pesid", "'"+idPlan+"' AND a.planestudio.programaeducativo.pedid ='"+idProgramaEducativo+"'", "aconombre");
        return result;
    }
    
    public List<Areaconocimiento> getAreasByProgramaeducativo(String idProgramaEducativo){
         List<Areaconocimiento> result = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
        result = ServiceLocator.getInstanceBaseDAO().findFromWhereB("planestudio", "programaeducativo.pedid ","'"+idProgramaEducativo+"'", "aconombre");
        return result;
    }
    
    public void add(Areaconocimiento puesto){
        Areaconocimiento resultado = null;
        if(puesto.getAcoid()!= null){
               ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
       
            resultado = (Areaconocimiento) ServiceLocator.getInstanceBaseDAO().find(puesto.getAcoid());
        }
        if(resultado != null){
            puesto.setAcoid(resultado.getAcoid());
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(puesto);
        } else{
            ServiceLocator.getInstanceBaseDAO().save(puesto);
        }
        
        
    }
    
    
        public List<Areaconocimiento> getAreasByUnidad(String unidadClave){
         List<Areaconocimiento> result = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
        result = ServiceLocator.getInstanceBaseDAO().findFromWhere("Areaconocimiento", "unidadaprendizajes","uapclave",unidadClave);
        return result;
    }
             public List<Areaconocimiento> getAreasByUnidadAcademica(String idUa){
         List<Areaconocimiento> result = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
        result = ServiceLocator.getInstanceBaseDAO().findFromWhereB("planestudio.programaeducativo.unidadacademica", "uacid",idUa,"aconombre");
        return result;
    }
    
//    public List<Areaconocimiento> consultarAreasConocimiento(String de,String campo,String criterio) {
//            List<Areaconocimiento> listaAreaConocimiento = null;
//           ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
//        listaAreaConocimiento = ServiceLocator.getInstanceBaseDAO().findFromWhere(de,campo, criterio);
//        return listaAreaConocimiento;
//    }

//Metodos enviados por marco para resolver problemas de metodos faltantes
                 public List<Areaconocimiento> getAreaMismoPlan(int idplan) {
        List<Areaconocimiento> listaArea = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
        listaArea = ServiceLocator.getInstanceBaseDAO().findFromWhere("planestudio", "pesid", String.valueOf(idplan));
        return listaArea;
    }

       public List<Areaconocimiento> findByPlanestudioClave(int pedclave, String pesvigenciaPlan){
        List<Areaconocimiento> result = null;
        result = ServiceLocator.getInstanceAreaconocimiento().findByCriteriaClave(pedclave, pesvigenciaPlan);
        return result;
    }
       
          public List<Areaconocimiento> findByProgramaEducativo(int idPrograma) {
        List<Areaconocimiento> result = null;
        result = ServiceLocator.getInstanceAreaconocimiento().findByProgramaEducativo(idPrograma);
        return result;
    }
          
              public List<Areaconocimiento> getAreaPorUA(int uaid) {
        List<Areaconocimiento> listaArea = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
        listaArea = ServiceLocator.getInstanceBaseDAO().findFromWhere("unidadaprendizajes", "uapid", String.valueOf(uaid));
        return listaArea;
    }
              
              public List<Areaconocimiento> getAreasByPlanYProgramaeducativoRPE(int idPlan,String idProgramaEducativo,String proId){
         List<Areaconocimiento> result = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
        result = ServiceLocator.getInstanceBaseDAO().findFromWhere("Areaconocimiento","planestudio.programaeducativo.profesors_1", "proid='"+proId+"' AND a.planestudio","'"+idPlan+"' ORDER BY aconombre");
        return result;
    }
    
    public List<Areaconocimiento> getAreasByProgramaeducativoRPE(String idProgramaEducativo,String proId){
         List<Areaconocimiento> result = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
        result = ServiceLocator.getInstanceBaseDAO().findFromWhere("Areaconocimiento","planestudio.programaeducativo.profesors_1", "proid='"+proId+"' AND a.planestudio.programaeducativo.pedid ","'"+idProgramaEducativo+"' ORDER BY aconombre");
        return result;
    }
     public List<Areaconocimiento> getAreasByRPE(String proId){
         List<Areaconocimiento> result = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
        result = ServiceLocator.getInstanceBaseDAO().findFromWhere("Areaconocimiento","planestudio.programaeducativo.profesors_1", "proid","'"+proId+"' ORDER BY aconombre");
        return result;
}
     
     //Metodos Nuevos
              public List<Areaconocimiento> getAreasByCoordinadorAreaAdmin(String idProgramaEducativo,String proId){
         List<Areaconocimiento> result = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
        result = ServiceLocator.getInstanceBaseDAO().findFromWhere("planestudio","unidadaprendizajes c join c.coordinadorareaadministrativas" ,"profesor.proid="+proId+" AND a.programaeducativo.pedid ","'"+idProgramaEducativo+"' order by aconombre");
        return result;
    }
              public List<Areaconocimiento> getAreasByPlanYProgramaeducativoCAA(int idPlan,String idProgramaEducativo,String proId){
         List<Areaconocimiento> result = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);

        result = ServiceLocator.getInstanceBaseDAO().findFromWhere("Areaconocimiento","planestudio.programaeducativo.areaadministrativas d join d.coordinadorareaadministrativas c join a.planestudio.programaeducativo.profesors_1",
                "proid='"+proId+"' AND a.planestudio='"+idPlan+"' AND "+
                        "c.profesor.proid","'"+proId+"' ORDER BY aconombre");
        return result;
    }
              public List<Areaconocimiento> getAreasByProgramaeducativoCAA(String idProgramaEducativo,String proId){
         List<Areaconocimiento> result = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
        result = ServiceLocator.getInstanceBaseDAO().findFromWhere("Areaconocimiento","planestudio.programaeducativo.areaadministrativas d join d.coordinadorareaadministrativas","profesor.proid='"+proId+"' AND "+
                 "a.planestudio.programaeducativo.pedid ","'"+idProgramaEducativo+"' ORDER BY aconombre");
        return result;
    }
              public List<Areaconocimiento> getAreasByCAA(String proId){
         List<Areaconocimiento> result = null;
       ServiceLocator.getInstanceBaseDAO().setTipo(Areaconocimiento.class);
        result = ServiceLocator.getInstanceBaseDAO().findFromWhere("Areaconocimiento","planestudio.programaeducativo.areaadministrativas d join d.coordinadorareaadministrativas", "profesor.proid","'"+proId+"' ORDER BY aconombre");
        return result;
    }
}
