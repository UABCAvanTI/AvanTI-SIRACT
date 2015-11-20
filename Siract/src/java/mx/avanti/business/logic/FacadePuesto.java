/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;

import java.util.List;
//import mx.avanti.siract.business.entity.Puesto;
import mx.avanti.siract.business.entity.Unidad;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author balta
 */
public class FacadePuesto {
//    public void agregarPuesto(Puesto puesto){
//        Puesto resultado = null;
//        if(puesto == null){
//            System.out.println("Puesto nulo");
//        }
//        if(puesto.getPueid() != null){
//            ServiceLocator.getInstanceBaseDAO().setTipo(Puesto.class);
//            resultado = (Puesto) ServiceLocator.getInstanceBaseDAO().find(puesto.getPueid());
//        }
//        if(resultado != null){
//            puesto.setPueid(resultado.getPueid());
//            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(puesto);
//        } else{
//            ServiceLocator.getInstanceBaseDAO().save(puesto);
//        }
//    }
//    
//    public List<Puesto> consultaPuesto(){
//        List<Puesto> resultado = null;
//           ServiceLocator.getInstanceBaseDAO().setTipo(Puesto.class);
//        resultado = ServiceLocator.
//                getInstanceBaseDAO().findAll();
//        return resultado;
//    }
//    
//    public void eliminarPuesto(Puesto puesto){
//        ServiceLocator.getInstanceBaseDAO().delete(puesto);
//    }
//        public List<Puesto> ConsultaPuesto(int proid) {
//        List<Puesto> listaPP = null;
//        ServiceLocator.getInstanceBaseDAO().setTipo(Puesto.class);
//        listaPP = ServiceLocator.getInstanceBaseDAO().findFromWhere("profesores", "proid", String.valueOf(proid));// Duda aqui que se manda
//        return listaPP;
//    }
//    
//        
//       
//    
//    public List<Puesto> consultaPuestos(){
//        List<Puesto> resultado = null;
//        ServiceLocator.getInstanceBaseDAO().setTipo(Puesto.class);
//        resultado = ServiceLocator.getInstanceBaseDAO().findAll();
//        return resultado;
//    }
//
//    
//    public Puesto obtenerPuestoProfesor(String idProfe){
//        List<Puesto> resultado = null;
//        Puesto r = null;
//        ServiceLocator.getInstanceBaseDAO().setTipo(Puesto.class);
//        resultado = ServiceLocator.getInstanceBaseDAO().findPuestoFromId("profesorTienePuestos", "profesor.proid",idProfe);
//        
//        //Esto es para que regrese solo el primer resultado (en caso de que solo sea un puesto)
//        if(resultado==null||resultado.isEmpty()){
//         return null;   
//        }
//        r = resultado.get(0);
//        System.out.println("Puestooo:" + r.getPuepuesto());
//        return r;
//    }
//    
//    
        
}
