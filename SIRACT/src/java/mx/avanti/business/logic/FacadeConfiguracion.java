/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.business.logic;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import mx.avanti.siract.business.entity.Calendarioreporte;
import mx.avanti.siract.business.entity.Configuracion;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Owner
 */
public class FacadeConfiguracion {

    public void saveConfiguracion(Configuracion configuracion) {
        Configuracion result = null;

        if (configuracion.getConid() != null) {
            ServiceLocator.getInstanceBaseDAO().setTipo(Configuracion.class);
            result = (Configuracion) ServiceLocator.getInstanceBaseDAO().find(configuracion.getConid());
        }

        if (result != null) {
            configuracion.setConid(result.getConid());
            ServiceLocator.getInstanceBaseDAO().setTipo(Configuracion.class);
            ServiceLocator.getInstanceBaseDAO().saveOrUpdate(configuracion);
        } else {
          ServiceLocator.getInstanceBaseDAO().setTipo(Configuracion.class);
            ServiceLocator.getInstanceBaseDAO().save(configuracion);
        }
    }
    
      public Configuracion buscarConfiguracion(int id){
        Configuracion resultado = null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Configuracion.class);
        resultado = (Configuracion)ServiceLocator.getInstanceBaseDAO().findFromWhereB("cicloescolar","cesid",String.valueOf(id),"conid").get(0);
        return resultado;
    }

    public List<Configuracion> findAll() {
        List<Configuracion> listaConfiguracion = null;
     ServiceLocator.getInstanceBaseDAO().setTipo(Configuracion.class);
        listaConfiguracion = ServiceLocator.getInstanceBaseDAO().findAll();
        return listaConfiguracion;
    }
    public Configuracion bConfPorCiclo(int idCiclo){
        Configuracion configuracion=null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Configuracion.class);
        configuracion=(Configuracion) ServiceLocator.getInstanceBaseDAO().bConfigPorCiclo(idCiclo);
        return configuracion;
    }
    //Juan Carlos Fernández
    public List<Calendarioreporte> findCREbyCON(int conid){
        String queryCalendarios="from Configuracion as c join c.calendarioreportes as cr where c.conid="+conid;
        System.out.println(queryCalendarios);
        List<Calendarioreporte> listaCalendario = null;
        Configuracion con=null;
        ServiceLocator.getInstanceBaseDAO().setTipo(Configuracion.class);
        con=(Configuracion) ServiceLocator.getInstanceBaseDAO().find(conid);
        listaCalendario=new ArrayList<Calendarioreporte>();
        List<Object> listaObjetos=ServiceLocator.getInstanceBaseDAO().executeQuery(queryCalendarios);
        Iterator itr=listaObjetos.iterator();
        while(itr.hasNext()){
            Object[] obj=(Object[])itr.next();
            listaCalendario.add((Calendarioreporte)obj[1]);
        }
        for(int x=0; x<listaCalendario.size(); x++)
            System.out.println(listaCalendario.get(x).getCrefechaCorte());
        listaCalendario.sort(comparadorFechasCorte);//Ordena por fechas de corte
        return listaCalendario;
    }

    //Objeto Comparator para ordenar la lista de Calendarioreporte por fecha de corte
    public static Comparator<Calendarioreporte> comparadorFechasCorte = (Calendarioreporte cre1, Calendarioreporte cre2) -> {
        if (cre1.getCrefechaCorte().before(cre2.getCrefechaCorte())){//Compara las dos fechas de corte
            return -1;//Ordena antes
        }else{
            if(cre1.getCrefechaCorte().equals(cre2.getCrefechaCorte())){//Si dos fechas de corte son iguales...
                if(cre1.getCrefechaLimite().before(cre2.getCrefechaLimite())){//Compara las fecha de límite
                    return -1;//Ordena antes
                }else{
                    return 1;//Ordena después
                }
            }else{
                return 1;//Ordena después
            }
        }
    };
    
        public Configuracion find(int id){
        ServiceLocator.getInstanceBaseDAO().setTipo(Configuracion.class);
        Configuracion c=(Configuracion)ServiceLocator.getInstanceBaseDAO().find(id);
        return c;
    }
    
}
