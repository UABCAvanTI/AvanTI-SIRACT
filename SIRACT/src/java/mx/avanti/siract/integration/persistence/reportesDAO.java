/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.integration.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import mx.avanti.common.integration.persistence.HibernateUtil;
import mx.avanti.siract.application.helper.ReporteAux;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Calendarioreporte;
import mx.avanti.siract.business.entity.CalendarioreporteTieneAlerta;
import mx.avanti.siract.business.entity.Configuracion;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Reporteavancecontenidotematico;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.business.entity.UnidadaprendizajeImparteProfesor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Ricardo
 */
public class reportesDAO {

    //atributos para iniciar sesion de criteria y el objeto donde
    //se guardará el criteria
    Session session;
    Criteria criteria;    
       
        
       
    public reportesDAO() {
              
    }    
         
    //inicia la sesión del criteria  
    public void initSession(){
        session = HibernateUtil.getSessionFactory().openSession(); 
    }
    
    //criteria basico mas utilizado para Generador de reportes, donde se
    //agregan los alias o inner join de una tabla a otra siguiendo la ruta
    //del modelo de la base de datos de uno a uno
    public void initCriteria(){
        criteria = session.createCriteria(Reporteavancecontenidotematico.class, "reporteavancecontenidotematico");
        criteria.createAlias("reporteavancecontenidotematico.unidadaprendizajeImparteProfesor", "unidadaprendizajeImparteProfesor");//Inner Join by default
        criteria.createAlias("unidadaprendizajeImparteProfesor.unidadaprendizaje", "unidadaprendizaje");//Inner Join by default
        criteria.createAlias("unidadaprendizajeImparteProfesor.profesor", "profesor");//Inner Join by default    
        criteria.createAlias("unidadaprendizajeImparteProfesor.grupo", "grupo");//Inner Join by default
        criteria.createAlias("grupo.planestudio", "planestudio");//Inner Join by default
        criteria.createAlias("unidadaprendizaje.cicloescolar", "cicloescolar");//Inner Join by default
        criteria.createAlias("unidadaprendizaje.areaconocimiento", "areaconocimiento");//Inner Join by default
        criteria.createAlias("areaconocimiento.planestudio", "planestudio2");//Inner Join by default     
        criteria.createAlias("planestudio.programaeducativo", "programaeducativo");//Inner Join by default      
        criteria.createAlias("programaeducativo.unidadacademica", "unidadacademica");//Inner Join by default      
    }
    
    public void initCriteria4(){
        criteria = session.createCriteria(Reporteavancecontenidotematico.class, "reporteavancecontenidotematico");
        criteria.createAlias("reporteavancecontenidotematico.unidadaprendizajeImparteProfesor", "unidadaprendizajeImparteProfesor");//Inner Join by default
        criteria.createAlias("unidadaprendizajeImparteProfesor.unidadaprendizaje", "unidadaprendizaje");//Inner Join by default
        criteria.createAlias("unidadaprendizajeImparteProfesor.profesor", "profesor");//Inner Join by default    
        criteria.createAlias("unidadaprendizajeImparteProfesor.grupo", "grupo");//Inner Join by default
        criteria.createAlias("unidadaprendizaje.cicloescolar", "cicloescolar");//Inner Join by default
        criteria.createAlias("grupo.planestudio", "planestudio");//Inner Join by default
        criteria.createAlias("planestudio.programaeducativo", "programaeducativo");//Inner Join by default      
        //criteria.createAlias("profesor.", "");//Inner Join by default        
        //criteria.createAlias("unidadaprendizaje.areaconocimiento", "areaconocimiento");//Inner Join by default
        //criteria.createAlias("areaconocimiento.planestudio", "planestudio2");//Inner Join by default             
        criteria.createAlias("programaeducativo.unidadacademica", "unidadacademica");//Inner Join by default      
    }
      
    //query para unir con Join Configuración con CalendarioReporte con el
    //valor unico de confechaInicioSemestre para evitar la excepcion con los lazy
    //necesario para cual numero de ract le corresponde que fecha los tres: 
    //A Tiempo, En fecha limite y Despues de fecha limite
    public List findFromWhereCalendReporte(Date confechaInicioSemestre){
        //query anterior con id de configuración
        //String queryCalendarios="from Configuracion as c join c.calendarioreportes as cr where c.conid="+conid;
        String queryCalendarios="from Configuracion as c join c.calendarioreportes as cr where c.confechaInicioSemestre='"+confechaInicioSemestre+"'";
        System.out.println(" ");
        System.out.println(queryCalendarios);
        List<Calendarioreporte> listaCalendario = null;
        Configuracion con=null;
        //ServiceLocator.getInstanceBaseDAO().setTipo(Configuracion.class);
        //con=(Configuracion) ServiceLocator.getInstanceBaseDAO().find(conid);
        listaCalendario=new ArrayList<Calendarioreporte>();
        List<Object> listaObjetos=executeQuery(queryCalendarios);
        Iterator itr=listaObjetos.iterator();
        while(itr.hasNext()){
            Object[] obj=(Object[])itr.next();
            listaCalendario.add((Calendarioreporte)obj[1]);
        }
        
        System.out.println(" ");
        
        for(int x=0; x<listaCalendario.size(); x++){
            System.out.println(listaCalendario.get(x).getCrefechaCorte());
        }            
        //listaCalendario.sort(comparadorFechasCorte);//Ordena por fechas de corte
        return listaCalendario;
    }
    
    //query para unir con Join Configuración con CalendarioReporte con el
    //valor unico de confechaInicioSemestre para evitar la excepcion con los lazy
    //necesario para cual numero de ract le corresponde que fecha los tres: 
    //A Tiempo, En fecha limite y Despues de fecha limite
    public List findFromWhereAreaConT(int uapclave){
        //query anterior con id de configuración
        //String queryCalendarios="from Configuracion as c join c.calendarioreportes as cr where c.conid="+conid;
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Areaconocimiento.class, "areaconocimiento");
        //criteria.createAlias("areaconocimiento.unidadaprendizajes", "unidadaprendizajes");
        //si se tiene la clave de unidad academica diferente de cero(no vacio)
        //se pone la restricción de que sea igual a esta clave
        if(uapclave!=0){
          //criteria.add(Restrictions.eq("unidadaprendizajes.uapclave", uapclave));  
        }                     
        List listaUAp = criteria.list();
        List<Areaconocimiento> listaAreaCon = null;
        

        
        
        session.close();
        
        
        
        /*String queryAreaCon="from Areaconocimiento as a join a.unidadaprendizajes as ua where ua.uapclave='"+uapclave+"'";
        System.out.println(" ");
        System.out.println(queryAreaCon);
        List<Areaconocimiento> listaAreaCon = null;*/
        //Configuracion con=null;
        //ServiceLocator.getInstanceBaseDAO().setTipo(Configuracion.class);
        //con=(Configuracion) ServiceLocator.getInstanceBaseDAO().find(conid);
        //listaAreaCon=new ArrayList<Areaconocimiento>();
//        List<Object> listaObjetos=executeQuery(queryCalendarios);
       /* List<Object> listaObjetos=executeQuery(queryAreaCon);
        Iterator itr=listaObjetos.iterator();
        while(itr.hasNext()){
            Object[] obj=(Object[])itr.next();
            listaAreaCon.add((Areaconocimiento)obj[0]);
        }*/
        
        
//        for(int x=0; x<listaCalendario.size(); x++){
//            System.out.println(listaCalendario.get(x).getCrefechaCorte());
//        }            
        //listaCalendario.sort(comparadorFechasCorte);//Ordena por fechas de corte
        return listaUAp;
//        return listaObjetos;
    }
    
    public List findFromWhereAreaCon(int uapclave){
        //query anterior con id de configuración
        //String queryCalendarios="from Configuracion as c join c.calendarioreportes as cr where c.conid="+conid;
        String queryAreaCon="from Areaconocimiento as a join a.unidadaprendizajes as ua where ua.uapclave='"+uapclave+"'";
        System.out.println(" ");
        System.out.println(queryAreaCon);
        List<Areaconocimiento> listaAreaCon = null;
        //Configuracion con=null;
        //ServiceLocator.getInstanceBaseDAO().setTipo(Configuracion.class);
        //con=(Configuracion) ServiceLocator.getInstanceBaseDAO().find(conid);
        listaAreaCon=new ArrayList<Areaconocimiento>();
//        List<Object> listaObjetos=executeQuery(queryCalendarios);
        List<Object> listaObjetos=executeQuery(queryAreaCon);
        Iterator itr=listaObjetos.iterator();
        while(itr.hasNext()){
            Object[] obj=(Object[])itr.next();
            listaAreaCon.add((Areaconocimiento)obj[0]);
        }
        
        System.out.println(" ");
        
//        for(int x=0; x<listaCalendario.size(); x++){
//            System.out.println(listaCalendario.get(x).getCrefechaCorte());
//        }            
        //listaCalendario.sort(comparadorFechasCorte);//Ordena por fechas de corte
        return listaAreaCon;
//        return listaObjetos;
    }
    
    //metodo para ejecutar el query de Join de Configuración con
    //CalendarioReporte o puede ser usado para ejecutar cualquier
    //query a la base de datos
    public List executeQuery(String query){
        List result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        
        try{
            //result = (List) HibernateUtil.getSession().createQuery("select c from Configuracion c join c.alerta a join c.calendarioreportes").list();
            result = (List) HibernateUtil.getSession().createQuery(query).list();
            System.out.println(" ");
            System.out.println(query);
            System.out.println(" ");
            
            if(result == null){
                System.out.println("nulo");
            }
        } catch(Exception x){
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
    
    //metodo generico para hacer dos join que no utilizamos
    public List findFromWhereAnterior(String de,String campo,String criterio) {
        List result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        
        try{
            result = (List) HibernateUtil.getSession().createQuery("select c from Configuracion c join c.alerta a join c.calendarioreportes").list();
            
            if(result == null){
                System.out.println("nulo");
            }
        } catch(Exception x){
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
     
    //metodo de criteria para obtener los programas educativos que corresponden
    //a la misma UnidadAcademica por su misma clave
    public List findByCriteriaDetalladoProgEdUnidadAcademica(int uacclave){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Programaeducativo.class, "programaeducativo");
        criteria.createAlias("programaeducativo.unidadacademica", "unidadacademica");
        //si se tiene la clave de unidad academica diferente de cero(no vacio)
        //se pone la restricción de que sea igual a esta clave
        if(uacclave!=0){
          criteria.add(Restrictions.eq("unidadacademica.uacclave", uacclave));  
        }                     
        List listaUAp = criteria.list();
        session.close();
        return listaUAp;
    }
    
    //metodo principal para hacer todos los tipos de criteria con la
    //misma estructura filtrando primero por el op=opcion y luego por
    //el tipo que ya estan definidos en este método el objeto que recibe
    //y identifican la consulta o criteria a realizar desde el metodo
    //que lo llama guardando el resultado en una lista de cada opcion
    //que es el resultado o return de este método
    public List findByCriteria(ReporteAux reporte){
        List listaUAp = null;
        initSession();
        initCriteria();
        //--------------------------Entregados y no entregados general--------------------------------//
        //parte del metodo que busca los entregados y no entrados en cada caso//
        if(reporte.op.equals("EntregadosYNo")){
            if(reporte.tipo.equalsIgnoreCase("Enviado")){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            }
            if(reporte.tipo.equalsIgnoreCase("Parcial")){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            if(reporte.tipo.equalsIgnoreCase("EnviadoYParcial")){
                //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            //los siguientes condiciones con atributos sirven para filtrar con la
            //restricción si es cero o nulo lo ignora si no busca agregar la restricción
            //indicada para el filtro correspondiente o que correspondan los resultados
            //al atributo o valor indicado como restricción o igualdad de la consulta 
            if(reporte.uacclave==0){
                
            }else{
               criteria.add(Restrictions.eq("unidadacademica.uacclave", reporte.uacclave)); 
            }
            if(reporte.clavepe==0){
                
            }else{
               criteria.add(Restrictions.eq("programaeducativo.pedclave", reporte.clavepe)); 
            }
            listaUAp = criteria.list();
        }
        //-----------------------Calendario de reportes----------------------------------------------//
        if(reporte.op.equals("CalendReport")){    
            criteria = session.createCriteria(Calendarioreporte.class, "calendarioreporte");
            listaUAp = criteria.list();
        }
        //-----------------------Calendario de reporte tiene alerta----------------------------------------------//
        if(reporte.op.equals("CalendReportTieneAlerta")){    
            criteria = session.createCriteria(CalendarioreporteTieneAlerta.class, "calendarioreportetienealerta");
            criteria.createAlias("calendarioreportetienealerta.calendarioreporte", "calendarioreporte");
            if (reporte.creid == 0) {

            } else {
                criteria.add(Restrictions.eq("calendarioreporte.creid", reporte.creid));
            }
            if (reporte.calnumeroReporte == 0) {

            } else {
                criteria.add(Restrictions.eq("calendarioreportetienealerta.calnumeroReporte", reporte.calnumeroReporte));
            }
            listaUAp = criteria.list();
        }
        //------------------------Area con todos------------------------------------------------------//
        if(reporte.op.equals("AreaConTodos")){
            criteria = session.createCriteria(Areaconocimiento.class, "areaconocimiento");
            criteria.createAlias("areaconocimiento.planestudio", "planestudio");
            criteria.createAlias("planestudio.programaeducativo", "programaeducativo");
            if (reporte.clavepe == 0) {

            } else {
                criteria.add(Restrictions.eq("programaeducativo.pedclave", reporte.clavepe));
            }            
            listaUAp = criteria.list();
        }
        //------------------------ConfiguracionTodos------------------------------------------------------//
        if(reporte.op.equals("ConfiguracionTodos")){
            criteria = session.createCriteria(Configuracion.class, "configuracion");
            criteria.createAlias("configuracion.alerta", "alerta");
            criteria.createAlias("configuracion.cicloescolar", "cicloescolar");
                       
            listaUAp = criteria.list();
        }
        //------------------------CalendarioReporteTieneAlerta------------------------------------------------------//
        if(reporte.op.equals("CalendarioReporteTieneAlertaTodos")){
            criteria = session.createCriteria(CalendarioreporteTieneAlerta.class, "calendarioreportetienealerta");
            criteria.createAlias("calendarioreportetienealerta.calendarioreporte", "calendarioreporte");
            criteria.createAlias("calendarioreportetienealerta.alerta", "alerta");
                       
            listaUAp = criteria.list();
        }
        //--------------------Enviados y parciales-----------------------------------------------------------------------------//
        if (reporte.op.equals("EnviadosYParciales")) {
            criteria = session.createCriteria(Reporteavancecontenidotematico.class, "reporteavancecontenidotematico");
            //criteria.createAlias("reporte.reporteavancecontenidotematico", "reporteavancecontenidotematico");//Inner Join by default
            //criteria.createAlias("reporteavancecontenidotematico.unidadaprendizajeImparteProfesor", "unidadaprendizajeImparteProfesor",JoinType.RIGHT_OUTER_JOIN);//Inner Join by default
            criteria.createAlias("reporteavancecontenidotematico.unidadaprendizajeImparteProfesor", "unidadaprendizajeImparteProfesor");//Inner Join by default
            criteria.createAlias("unidadaprendizajeImparteProfesor.unidadaprendizaje", "unidadaprendizaje");//Inner Join by default
            criteria.createAlias("unidadaprendizajeImparteProfesor.profesor", "profesor");//Inner Join by default                        
            //criteria.setFetchMode("programaeducativo.profesors", FetchMode.JOIN);
            criteria.createAlias("unidadaprendizajeImparteProfesor.grupo", "grupo");//Inner Join by default
            criteria.createAlias("grupo.planestudio", "planestudio");//Inner Join by default
            criteria.createAlias("unidadaprendizaje.cicloescolar", "cicloescolar");//Inner Join by default
            criteria.createAlias("unidadaprendizaje.areaconocimiento", "areaconocimiento");//Inner Join by default
            //criteria.setMaxResults(10);
            criteria.createAlias("areaconocimiento.planestudio", "planestudio2");//Inner Join by default     
            criteria.createAlias("planestudio.programaeducativo", "programaeducativo");//Inner Join by default        

            //criteria.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);
      //      criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
        //criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", UIPid));      

            
            //criteria.add(Restrictions);
            if(reporte.clave==0){
                
            }else{
              criteria.add(Restrictions.eq("programaeducativo.pedclave", reporte.clave));                
            }
            if(reporte.acoclave==0){
                
            }else{
            criteria.add(Restrictions.eq("areaconocimiento.acoclave",reporte.acoclave));         
            }
            if(reporte.uapclave==0){
                //
            }else{
            criteria.add(Restrictions.eq("unidadaprendizaje.uapclave",reporte.uapclave));                
            }
            if(reporte.gponumero==0){
                
            }else{
            criteria.add(Restrictions.eq("grupo.gponumero",reporte.gponumero));
            }
            if(reporte.pronumeroEmpleado==0){
                
            }else{
            criteria.add(Restrictions.eq("profesor.pronumeroEmpleado", reporte.pronumeroEmpleado));        
            }
            if(reporte.pesvigencia == null || reporte.pesvigencia.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("planestudio.pesvigenciaPlan", reporte.pesvigencia));
            }
            if(reporte.cescicloEscolar == null || reporte.cescicloEscolar.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("cicloescolar.cescicloEscolar", reporte.cescicloEscolar));
            }

            if (reporte.numRact == 1) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero", "1"));
            }
            if (reporte.numRact == 2) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero", "2"));
            }
            if (reporte.numRact == 3) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero", "3"));
            }

            if (reporte.numProfUIPid == 0) {

            } else {
                criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", reporte.numProfUIPid));
            }

            listaUAp = criteria.list();

            //session.close();

            //return listaUAp;
        }
        //--------------------No entregados vacio-----------------------------------------------------------------------------//
        if (reporte.op.equals("NoEntregadoVacio")) {
            criteria = session.createCriteria(UnidadaprendizajeImparteProfesor.class, "unidadaprendizajeImparteProfesor");
            //criteria.createAlias("reporte.reporteavancecontenidotematico", "reporteavancecontenidotematico");//Inner Join by default
            //criteria.createAlias("reporteavancecontenidotematico.unidadaprendizajeImparteProfesor", "unidadaprendizajeImparteProfesor");//Inner Join by default
            criteria.createAlias("unidadaprendizajeImparteProfesor.unidadaprendizaje", "unidadaprendizaje");//Inner Join by default
            criteria.createAlias("unidadaprendizajeImparteProfesor.profesor", "profesor");//Inner Join by default                        
            //criteria.setFetchMode("programaeducativo.profesors", FetchMode.JOIN);
            criteria.createAlias("unidadaprendizajeImparteProfesor.grupo", "grupo");//Inner Join by default
            criteria.createAlias("grupo.planestudio", "planestudio");//Inner Join by default
            criteria.createAlias("unidadaprendizaje.cicloescolar", "cicloescolar");//Inner Join by default
            criteria.createAlias("unidadaprendizaje.areaconocimiento", "areaconocimiento");//Inner Join by default
            //criteria.setMaxResults(10);
            criteria.createAlias("areaconocimiento.planestudio", "planestudio2");//Inner Join by default     
            criteria.createAlias("planestudio.programaeducativo", "programaeducativo");//Inner Join by default        

            //criteria.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);
      //      criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
        //criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", UIPid));      

            
            //criteria.add(Restrictions);
            if(reporte.clave==0){
                
            }else{
              criteria.add(Restrictions.eq("programaeducativo.pedclave", reporte.clave));                
            }
            if(reporte.acoclave==0){
                
            }else{
            criteria.add(Restrictions.eq("areaconocimiento.acoclave",reporte.acoclave));         
            }
            if(reporte.uapclave==0){
                //
            }else{
            criteria.add(Restrictions.eq("unidadaprendizaje.uapclave",reporte.uapclave));                            
            }
            if(reporte.gponumero==0){
                
            }else{
            criteria.add(Restrictions.eq("grupo.gponumero",reporte.gponumero));
            }
            if(reporte.pronumeroEmpleado==0){
                
            }else{
            criteria.add(Restrictions.eq("profesor.pronumeroEmpleado", reporte.pronumeroEmpleado));        
            }
            if(reporte.pesvigencia == null || reporte.pesvigencia.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("planestudio.pesvigenciaPlan", reporte.pesvigencia));
            }
            if(reporte.cescicloEscolar == null || reporte.cescicloEscolar.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("cicloescolar.cescicloEscolar", reporte.cescicloEscolar));
            }

            if (reporte.numRact == 1) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero", "1"));
            }
            if (reporte.numRact == 2) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero", "2"));
            }
            if (reporte.numRact == 3) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero", "3"));
            }

            if (reporte.numProfUIPid == 0) {

            } else {
                criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", reporte.numProfUIPid));
            }

            listaUAp = criteria.list();

            //session.close();

            //return listaUAp;
        }
        //--------------------ATiempoYNo-----------------------------------------------------------------------------//
        if (reporte.op.equals("ATiempoYNo")) {
            criteria = session.createCriteria(Reporteavancecontenidotematico.class, "reporteavancecontenidotematico");
            //criteria.createAlias("reporte.reporteavancecontenidotematico", "reporteavancecontenidotematico");//Inner Join by default
            criteria.createAlias("reporteavancecontenidotematico.unidadaprendizajeImparteProfesor", "unidadaprendizajeImparteProfesor");//Inner Join by default
            criteria.createAlias("unidadaprendizajeImparteProfesor.unidadaprendizaje", "unidadaprendizaje");//Inner Join by default
            criteria.createAlias("unidadaprendizajeImparteProfesor.profesor", "profesor");//Inner Join by default                        
            //criteria.setFetchMode("programaeducativo.profesors", FetchMode.JOIN);
            criteria.createAlias("unidadaprendizajeImparteProfesor.grupo", "grupo");//Inner Join by default
            criteria.createAlias("grupo.planestudio", "planestudio");//Inner Join by default
            criteria.createAlias("unidadaprendizaje.cicloescolar", "cicloescolar");//Inner Join by default
            criteria.createAlias("unidadaprendizaje.areaconocimiento", "areaconocimiento");//Inner Join by default
            //criteria.setMaxResults(10);
            criteria.createAlias("areaconocimiento.planestudio", "planestudio2");//Inner Join by default     
            criteria.createAlias("planestudio.programaeducativo", "programaeducativo");//Inner Join by default        

            //criteria.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);
//quite la siguiente linea para que obtenga resultados de Enviados y Parciales
//         criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
        //criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", UIPid));      

            if (reporte.tipo.equalsIgnoreCase("ATiempo")) {
                criteria.add(Restrictions.le("reporteavancecontenidotematico.racfechaElaboracion", reporte.fecha1));                
            }
            if (reporte.tipo.equalsIgnoreCase("FueraTiempo")) {
                criteria.add(Restrictions.gt("reporteavancecontenidotematico.racfechaElaboracion", reporte.fecha1));
            }
            if (reporte.tipo.equalsIgnoreCase("EnFechaLimite")) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racfechaElaboracion", reporte.fecha1));
            }

            //criteria.add(Restrictions);
            if(reporte.clave==0){
                
            }else{
              criteria.add(Restrictions.eq("programaeducativo.pedclave", reporte.clave));                
            }
            if(reporte.acoclave==0){
                
            }else{
            criteria.add(Restrictions.eq("areaconocimiento.acoclave",reporte.acoclave));         
            }
            if(reporte.uapclave==0){
                //
            }else{
            criteria.add(Restrictions.eq("unidadaprendizaje.uapclave",reporte.uapclave));                            
            }
            if(reporte.gponumero==0){
                
            }else{
            criteria.add(Restrictions.eq("grupo.gponumero", reporte.gponumero));
            }
            if(reporte.pronumeroEmpleado==0){
                
            }else{
            criteria.add(Restrictions.eq("profesor.pronumeroEmpleado", reporte.pronumeroEmpleado));        
            }
            if(reporte.pesvigencia == null || reporte.pesvigencia.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("planestudio.pesvigenciaPlan", reporte.pesvigencia));
            }
            if(reporte.cescicloEscolar == null || reporte.cescicloEscolar.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("cicloescolar.cescicloEscolar", reporte.cescicloEscolar));
            }
            if (reporte.numRact == 1) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero", "1"));
            }
            if (reporte.numRact == 2) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero", "2"));
            }
            if (reporte.numRact == 3) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero", "3"));
            }

            if (reporte.numProfUIPid == 0) {

            } else {
                criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", reporte.numProfUIPid));
            }

            listaUAp = criteria.list();

            //session.close();

            //return listaUAp;
        }
        //------------------------ConfigSet-----------------------------------------------------------//
        if(reporte.op.equals("ConfigSet")){
            criteria = session.createCriteria(Configuracion.class, "configuracion");
            criteria.createAlias("configuracion.cicloescolar", "cicloescolar");//Inner Join by default
            criteria.createAlias("configuracion.alerta", "alerta");//Inner Join by default
            criteria.createAlias("configuracion.calendarioreportes", "calendarioreportes");//Inner Join by default
            criteria.createAlias("alerta.calendarioreporteTieneAlertas", "calendarioreporteTieneAlerta");//Inner Join by default
            //criteria.add(Restrictions.eq("calendarioreporteTieneAlerta.calnumeroReporte",reporte.calnumeroReporte));   
            listaUAp = criteria.list();
        }
        //----------------------CompararAreaCon------------------------------------------------------//
        if(reporte.op.equals("CompararAreaCon")){
            if(reporte.tipo.equalsIgnoreCase("ATiempo")||reporte.tipo.equalsIgnoreCase("FueraTiempo")||reporte.tipo.equalsIgnoreCase("Enviado")){
            criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            }
            if(reporte.tipo.equalsIgnoreCase("Parcial")){
            criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            if(reporte.tipo.equalsIgnoreCase("EnviadoYParcial")){
            //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            if(reporte.tipo.equalsIgnoreCase("ATiempo")){
                criteria.add(Restrictions.le("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }
            if(reporte.tipo.equalsIgnoreCase("FueraTiempo")){
                criteria.add(Restrictions.gt("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }    
            if(reporte.clavepe == 0){
                
            }else{
            criteria.add(Restrictions.eq("programaeducativo.pedclave",reporte.clavepe));
            }
            if(reporte.pesvigencia == null || reporte.pesvigencia.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("planestudio.pesvigenciaPlan", reporte.pesvigencia));
            }
            if(reporte.cescicloEscolar == null || reporte.cescicloEscolar.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("cicloescolar.cescicloEscolar",reporte.cescicloEscolar));
            }
            if(reporte.acoclave == 0){
                
            }else{
            criteria.add(Restrictions.eq("areaconocimiento.acoclave",reporte.acoclave));
            }
            if(reporte.numRact==1){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","1"));
            }
            if(reporte.numRact==2){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","2"));
            }
            if(reporte.numRact==3){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","3"));
            }
            if(reporte.numProfUIPid==0){}else{
                criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", reporte.numProfUIPid));  
            }
            listaUAp = criteria.list();
        }
        //-----------------------------ProgEducatTodos------------------------------------------------//
        if(reporte.op.equals("ProgEducatTodos")){
            criteria = session.createCriteria(Planestudio.class, "planestudio");
            criteria.createAlias("planestudio.programaeducativo", "programaeducativo");//Inner Join by default
            listaUAp = criteria.list();
        }
        //-----------------------------PAGC------------------------------------------------//
        if(reporte.op.equals("PAGC")){
            criteria = session.createCriteria(Unidadaprendizaje.class, "unidadaprendizaje");
            criteria.createAlias("unidadaprendizaje.cicloescolar", "cicloescolar");//Inner Join by default
            criteria.createAlias("unidadaprendizaje.areaconocimiento", "areaconocimiento");//Inner Join by default
            criteria.createAlias("areaconocimiento.planestudio", "planestudio");//Inner Join by default     
            criteria.createAlias("planestudio.programaeducativo", "programaeducativo");//Inner Join by default         
            criteria.createAlias("programaeducativo.unidadacademica", "unidadacademica");//Inner Join by default         
           
            listaUAp = criteria.list();
        }       
        //-----------------------------CompararProfGrupo----------------------------------------------//
        if(reporte.op.equals("CompararProfGrupo")){
            if(reporte.tipo.equalsIgnoreCase("ATiempo")||reporte.tipo.equalsIgnoreCase("FueraTiempo")||reporte.tipo.equalsIgnoreCase("Enviado")){
            criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            }
            if(reporte.tipo.equalsIgnoreCase("Parcial")){
            criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            if(reporte.tipo.equalsIgnoreCase("EnviadoYParcial")){
            //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            if(reporte.tipo.equalsIgnoreCase("ATiempo")){
                criteria.add(Restrictions.le("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }
            if(reporte.tipo.equalsIgnoreCase("FueraTiempo")){
                criteria.add(Restrictions.gt("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }
            if(reporte.clavepe == 0){
                
            }else{    
            criteria.add(Restrictions.eq("programaeducativo.pedclave",reporte.clavepe));
            }
            if(reporte.pesvigencia == null || reporte.pesvigencia.equalsIgnoreCase("")){
                
            }else{ 
            criteria.add(Restrictions.eq("planestudio.pesvigenciaPlan", reporte.pesvigencia));
            }
            if(reporte.cescicloEscolar == null || reporte.cescicloEscolar.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("cicloescolar.cescicloEscolar",reporte.cescicloEscolar));
            }
            if(reporte.acoclave == 0){
                
            }else{
            criteria.add(Restrictions.eq("areaconocimiento.acoclave",reporte.acoclave));
            }
            if(reporte.uapclave==0){
                //
            }else{
            criteria.add(Restrictions.eq("unidadaprendizaje.uapclave",reporte.uapclave));                            
            }
            if(reporte.pronumeroEmpleado == 0){
                
            }else{
            criteria.add(Restrictions.eq("profesor.pronumeroEmpleado", reporte.pronumeroEmpleado));
            }
            if(reporte.gponumero == 0){
                
            }else{
            criteria.add(Restrictions.eq("grupo.gponumero", reporte.gponumero)); 
            }
            if(reporte.numRact==1){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","1"));
            }
            if(reporte.numRact==2){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","2"));
            }
            if(reporte.numRact==3){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","3"));
            }  
            if(reporte.numProfUIPid==0){}else{
                criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", reporte.numProfUIPid));  
            }
            listaUAp = criteria.list();
        }
        //------------------------------------CompararProgramaEducativo------------------------------------------//
        if(reporte.op.equals("CompararProgEduc")){
            if(reporte.tipo.equalsIgnoreCase("ATiempo")||reporte.tipo.equalsIgnoreCase("FueraTiempo")||reporte.tipo.equalsIgnoreCase("Enviado")){
            criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            }
            if(reporte.tipo.equalsIgnoreCase("Parcial")){
            criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            if(reporte.tipo.equalsIgnoreCase("EnviadoYParcial")){
            //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            if(reporte.tipo.equalsIgnoreCase("ATiempo")){
                criteria.add(Restrictions.le("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }
            if(reporte.tipo.equalsIgnoreCase("FueraTiempo")){
                criteria.add(Restrictions.gt("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }
            if(reporte.clavepe == 0){
                
            }else{
            criteria.add(Restrictions.eq("programaeducativo.pedclave",reporte.clavepe));
            }
            if(reporte.pesvigencia == null || reporte.pesvigencia.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("planestudio.pesvigenciaPlan", reporte.pesvigencia));
            }
            if(reporte.cescicloEscolar == null || reporte.cescicloEscolar.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("cicloescolar.cescicloEscolar",reporte.cescicloEscolar));
            }
            if(reporte.numRact==1){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","1"));
            }
            if(reporte.numRact==2){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","2"));
            }
            if(reporte.numRact==3){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","3"));
            }
            if(reporte.numProfUIPid==0){}else{
              criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", reporte.numProfUIPid));  
            }
            listaUAp = criteria.list();
        }
        //------------------------------------PorcentAvanceSolo------------------------------------------//
        if(reporte.op.equals("PorcentAvanceSolo")){
            //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            if(reporte.tipo.equalsIgnoreCase("ATiempo")){
                criteria.add(Restrictions.le("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }
            if(reporte.tipo.equalsIgnoreCase("FueraTiempo")){
                criteria.add(Restrictions.gt("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }
            if(reporte.clave == 0){
                
            }else{
            criteria.add(Restrictions.eq("programaeducativo.pedclave",reporte.clave));
            }
            if(reporte.pesvigencia == null || reporte.pesvigencia.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("planestudio.pesvigenciaPlan", reporte.pesvigencia));
            }
            if(reporte.cescicloEscolar == null || reporte.cescicloEscolar.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("cicloescolar.cescicloEscolar",reporte.cescicloEscolar));
            }
            if(reporte.numRact==1){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","1"));
            }
            if(reporte.numRact==2){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","2"));
            }
            if(reporte.numRact==3){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","3"));
            }
            if(reporte.numProfUIPid==0){}else{
              criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", reporte.numProfUIPid));  
            }
            listaUAp = criteria.list();
        }
         //------------------------------------CompararUAGrupo------------------------------------------//      
        if(reporte.op.equals("CompararUAGrupo")){
            if(reporte.tipo.equalsIgnoreCase("ATiempo")||reporte.tipo.equalsIgnoreCase("FueraTiempo")||reporte.tipo.equalsIgnoreCase("Enviado")){
            criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            }
            if(reporte.tipo.equalsIgnoreCase("Parcial")){
            criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            if(reporte.tipo.equalsIgnoreCase("EnviadoYParcial")){
            //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            if(reporte.tipo.equalsIgnoreCase("ATiempo")){
                criteria.add(Restrictions.le("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }
            if(reporte.tipo.equalsIgnoreCase("FueraTiempo")){
                criteria.add(Restrictions.gt("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }
            if(reporte.clavepe == 0){
                
            }else{
            criteria.add(Restrictions.eq("programaeducativo.pedclave",reporte.clavepe));
            }
            if(reporte.pesvigencia == null || reporte.pesvigencia.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("planestudio.pesvigenciaPlan", reporte.pesvigencia));
            }
            if(reporte.cescicloEscolar == null || reporte.cescicloEscolar.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("cicloescolar.cescicloEscolar",reporte.cescicloEscolar));
            }
            if(reporte.acoclave == 0){
                
            }else{
            criteria.add(Restrictions.eq("areaconocimiento.acoclave",reporte.acoclave));
            }
            if(reporte.uapclave == 0){
                //
            }else{
            criteria.add(Restrictions.eq("unidadaprendizaje.uapclave",reporte.uapclave));            
            }
            if(reporte.gponumero == 0){
                
            }else{
            criteria.add(Restrictions.eq("grupo.gponumero", reporte.gponumero));
            }
            if(reporte.numRact==1){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","1"));
            }
            if(reporte.numRact==2){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","2"));
            }
            if(reporte.numRact==3){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","3"));
            }
            if(reporte.numProfUIPid==0){}else{
                criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", reporte.numProfUIPid));  
            }
            listaUAp = criteria.list();
        }
        session.close();
        return listaUAp;
    }
    
    public List<UnidadaprendizajeImparteProfesor> findByUnidadAprendisaje(Integer uapid) {
        
        Session sessionTH = HibernateUtil.getSessionFactory().openSession();        
        Criteria criteriaTH = sessionTH.createCriteria(UnidadaprendizajeImparteProfesor.class, "UnidadaprendizajeImparteProfesor");
        criteriaTH.createAlias("UnidadaprendizajeImparteProfesor.profesor", "profesor");//Inner Join by default
        criteriaTH.createAlias("UnidadaprendizajeImparteProfesor.grupo", "grupo");//Inner Join by default
        
        // kitar cuando ya este
        criteriaTH.add(Restrictions.eq("UnidadaprendizajeImparteProfesor.uipid", uapid));
        //criteria.setMaxResults(10);
        List<UnidadaprendizajeImparteProfesor> listUnidadImparteProf = criteriaTH.list();
        sessionTH.close();
        
        return listUnidadImparteProf;
    }
    
    public List<Reporteavancecontenidotematico> findByUnidadImparte(Integer uipid){
        
        Session sessionTH = HibernateUtil.getSessionFactory().openSession();        
        Criteria criteriaTH = sessionTH.createCriteria(Reporteavancecontenidotematico.class, "Reporteavancecontenidotematico");
        criteriaTH.createAlias("Reporteavancecontenidotematico.unidadaprendizajeImparteProfesor", "unidadaprendizajeImparteProfesor");//Inner Join by default
                
        criteriaTH.add(Restrictions.eq("Reporteavancecontenidotematico.unidadaprendizajeImparteProfesor.uipid", uipid));
        List<Reporteavancecontenidotematico> listUnidadImparteProf;
        listUnidadImparteProf = criteriaTH.list();
        sessionTH.close();
        
        return listUnidadImparteProf;
        
    }
    
    //metodo principal para hacer todos los tipos de criteria con la
    //misma estructura filtrando primero por el op=opcion y luego por
    //el tipo que ya estan definidos en este método el objeto que recibe
    //y identifican la consulta o criteria a realizar desde el metodo
    //que lo llama guardando el resultado en una lista de cada opcion
    //que es el resultado o return de este método
    public List findByCriteria4(ReporteAux reporte){
        List listaUAp = null;
        initSession();
        initCriteria4();
        //--------------------------Entregados y no entregados general--------------------------------//
        //parte del metodo que busca los entregados y no entrados en cada caso//
        if(reporte.op.equals("EntregadosYNo")){
            if(reporte.tipo.equalsIgnoreCase("Enviado")){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            }
            if(reporte.tipo.equalsIgnoreCase("Parcial")){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            if(reporte.tipo.equalsIgnoreCase("EnviadoYParcial")){
                //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            //los siguientes condiciones con atributos sirven para filtrar con la
            //restricción si es cero o nulo lo ignora si no busca agregar la restricción
            //indicada para el filtro correspondiente o que correspondan los resultados
            //al atributo o valor indicado como restricción o igualdad de la consulta 
            if(reporte.uacclave==0){
                
            }else{
               criteria.add(Restrictions.eq("unidadacademica.uacclave", reporte.uacclave)); 
            }
            if(reporte.clavepe==0){
                
            }else{
               criteria.add(Restrictions.eq("programaeducativo.pedclave", reporte.clavepe)); 
            }
            listaUAp = criteria.list();
        }
        //-----------------------Calendario de reportes----------------------------------------------//
        if(reporte.op.equals("CalendReport")){    
            criteria = session.createCriteria(Calendarioreporte.class, "calendarioreporte");
            listaUAp = criteria.list();
        }
        //-----------------------Calendario de reporte tiene alerta----------------------------------------------//
        if(reporte.op.equals("CalendReportTieneAlerta")){    
            criteria = session.createCriteria(CalendarioreporteTieneAlerta.class, "calendarioreportetienealerta");
            criteria.createAlias("calendarioreportetienealerta.calendarioreporte", "calendarioreporte");
            if (reporte.creid == 0) {

            } else {
                criteria.add(Restrictions.eq("calendarioreporte.creid", reporte.creid));
            }
            if (reporte.calnumeroReporte == 0) {

            } else {
                criteria.add(Restrictions.eq("calendarioreportetienealerta.calnumeroReporte", reporte.calnumeroReporte));
            }
            listaUAp = criteria.list();
        }
        //------------------------Area con todos------------------------------------------------------//
        if(reporte.op.equals("AreaConTodos")){
            criteria = session.createCriteria(Areaconocimiento.class, "areaconocimiento");
            criteria.createAlias("areaconocimiento.planestudio", "planestudio");
            criteria.createAlias("planestudio.programaeducativo", "programaeducativo");
            if (reporte.clavepe == 0) {

            } else {
                criteria.add(Restrictions.eq("programaeducativo.pedclave", reporte.clavepe));
            }            
            listaUAp = criteria.list();
        }
        //------------------------ConfiguracionTodos------------------------------------------------------//
        if(reporte.op.equals("ConfiguracionTodos")){
            criteria = session.createCriteria(Configuracion.class, "configuracion");
            criteria.createAlias("configuracion.alerta", "alerta");
            criteria.createAlias("configuracion.cicloescolar", "cicloescolar");
                       
            listaUAp = criteria.list();
        }
        //------------------------CalendarioReporteTieneAlerta------------------------------------------------------//
        if(reporte.op.equals("CalendarioReporteTieneAlertaTodos")){
            criteria = session.createCriteria(CalendarioreporteTieneAlerta.class, "calendarioreportetienealerta");
            criteria.createAlias("calendarioreportetienealerta.calendarioreporte", "calendarioreporte");
            criteria.createAlias("calendarioreportetienealerta.alerta", "alerta");
                       
            listaUAp = criteria.list();
        }
        //--------------------Enviados y parciales-----------------------------------------------------------------------------//
        if (reporte.op.equals("EnviadosYParciales")) {
            criteria = session.createCriteria(Reporteavancecontenidotematico.class, "reporteavancecontenidotematico");
            //criteria.createAlias("reporte.reporteavancecontenidotematico", "reporteavancecontenidotematico");//Inner Join by default
            //criteria.createAlias("reporteavancecontenidotematico.unidadaprendizajeImparteProfesor", "unidadaprendizajeImparteProfesor",JoinType.RIGHT_OUTER_JOIN);//Inner Join by default
            criteria.createAlias("reporteavancecontenidotematico.unidadaprendizajeImparteProfesor", "unidadaprendizajeImparteProfesor");//Inner Join by default
            criteria.createAlias("unidadaprendizajeImparteProfesor.unidadaprendizaje", "unidadaprendizaje");//Inner Join by default
            criteria.createAlias("unidadaprendizajeImparteProfesor.profesor", "profesor");//Inner Join by default                        
            //criteria.setFetchMode("programaeducativo.profesors", FetchMode.JOIN);
            criteria.createAlias("unidadaprendizajeImparteProfesor.grupo", "grupo");//Inner Join by default
            criteria.createAlias("grupo.planestudio", "planestudio");//Inner Join by default
            criteria.createAlias("unidadaprendizaje.cicloescolar", "cicloescolar");//Inner Join by default
//            criteria.createAlias("unidadaprendizaje.areaconocimiento", "areaconocimiento");//Inner Join by default
//            //criteria.setMaxResults(10);
//            criteria.createAlias("areaconocimiento.planestudio", "planestudio2");//Inner Join by default     
            criteria.createAlias("planestudio.programaeducativo", "programaeducativo");//Inner Join by default        

            //criteria.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);
      //      criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
        //criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", UIPid));      

            
            //criteria.add(Restrictions);
            if(reporte.clave==0){
                
            }else{
              criteria.add(Restrictions.eq("programaeducativo.pedclave", reporte.clave));                
            }
//            if(reporte.acoclave==0){
//                
//            }else{
//            criteria.add(Restrictions.eq("areaconocimiento.acoclave",reporte.acoclave));         
//            }
            if(reporte.uapclave==0){
                //
            }else{
            criteria.add(Restrictions.eq("unidadaprendizaje.uapclave",reporte.uapclave));                
            }
            if(reporte.gponumero==0){
                
            }else{
            criteria.add(Restrictions.eq("grupo.gponumero",reporte.gponumero));
            }
            if(reporte.pronumeroEmpleado==0){
                
            }else{
            criteria.add(Restrictions.eq("profesor.pronumeroEmpleado", reporte.pronumeroEmpleado));        
            }
            if(reporte.pesvigencia == null || reporte.pesvigencia.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("planestudio.pesvigenciaPlan", reporte.pesvigencia));
            }
            if(reporte.cescicloEscolar == null || reporte.cescicloEscolar.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("cicloescolar.cescicloEscolar", reporte.cescicloEscolar));
            }

            if (reporte.numRact == 1) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero", "1"));
            }
            if (reporte.numRact == 2) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero", "2"));
            }
            if (reporte.numRact == 3) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero", "3"));
            }

            if (reporte.numProfUIPid == 0) {

            } else {
                criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", reporte.numProfUIPid));
            }

            listaUAp = criteria.list();

            //session.close();

            //return listaUAp;
        }
        //--------------------No entregados vacio-----------------------------------------------------------------------------//
        if (reporte.op.equals("NoEntregadoVacio")) {
            criteria = session.createCriteria(UnidadaprendizajeImparteProfesor.class, "unidadaprendizajeImparteProfesor");
            //criteria.createAlias("reporte.reporteavancecontenidotematico", "reporteavancecontenidotematico");//Inner Join by default
            //criteria.createAlias("reporteavancecontenidotematico.unidadaprendizajeImparteProfesor", "unidadaprendizajeImparteProfesor");//Inner Join by default
            criteria.createAlias("unidadaprendizajeImparteProfesor.unidadaprendizaje", "unidadaprendizaje");//Inner Join by default
            criteria.createAlias("unidadaprendizajeImparteProfesor.profesor", "profesor");//Inner Join by default                        
            //criteria.setFetchMode("programaeducativo.profesors", FetchMode.JOIN);
            criteria.createAlias("unidadaprendizajeImparteProfesor.grupo", "grupo");//Inner Join by default
            criteria.createAlias("grupo.planestudio", "planestudio");//Inner Join by default
            criteria.createAlias("unidadaprendizaje.cicloescolar", "cicloescolar");//Inner Join by default
//            criteria.createAlias("unidadaprendizaje.areaconocimiento", "areaconocimiento");//Inner Join by default
//            //criteria.setMaxResults(10);
//            criteria.createAlias("areaconocimiento.planestudio", "planestudio2");//Inner Join by default     
            criteria.createAlias("planestudio.programaeducativo", "programaeducativo");//Inner Join by default        

            //criteria.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);
      //      criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
        //criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", UIPid));      

            
            //criteria.add(Restrictions);
            if(reporte.clave==0){
                
            }else{
              criteria.add(Restrictions.eq("programaeducativo.pedclave", reporte.clave));                
            }
//            if(reporte.acoclave==0){
//                
//            }else{
//            criteria.add(Restrictions.eq("areaconocimiento.acoclave",reporte.acoclave));         
//            }
            if(reporte.uapclave==0){
                //
            }else{
            criteria.add(Restrictions.eq("unidadaprendizaje.uapclave",reporte.uapclave));                            
            }
            if(reporte.gponumero==0){
                
            }else{
            criteria.add(Restrictions.eq("grupo.gponumero",reporte.gponumero));
            }
            if(reporte.pronumeroEmpleado==0){
                
            }else{
            criteria.add(Restrictions.eq("profesor.pronumeroEmpleado", reporte.pronumeroEmpleado));        
            }
            if(reporte.pesvigencia == null || reporte.pesvigencia.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("planestudio.pesvigenciaPlan", reporte.pesvigencia));
            }
            if(reporte.cescicloEscolar == null || reporte.cescicloEscolar.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("cicloescolar.cescicloEscolar", reporte.cescicloEscolar));
            }

            if (reporte.numRact == 1) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero", "1"));
            }
            if (reporte.numRact == 2) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero", "2"));
            }
            if (reporte.numRact == 3) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero", "3"));
            }

            if (reporte.numProfUIPid == 0) {

            } else {
                criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", reporte.numProfUIPid));
            }

            listaUAp = criteria.list();

            //session.close();

            //return listaUAp;
        }
        //--------------------ATiempoYNo-----------------------------------------------------------------------------//
        if (reporte.op.equals("ATiempoYNo")) {
            criteria = session.createCriteria(Reporteavancecontenidotematico.class, "reporteavancecontenidotematico");
            //criteria.createAlias("reporte.reporteavancecontenidotematico", "reporteavancecontenidotematico");//Inner Join by default
            criteria.createAlias("reporteavancecontenidotematico.unidadaprendizajeImparteProfesor", "unidadaprendizajeImparteProfesor");//Inner Join by default
            criteria.createAlias("unidadaprendizajeImparteProfesor.unidadaprendizaje", "unidadaprendizaje");//Inner Join by default
            criteria.createAlias("unidadaprendizajeImparteProfesor.profesor", "profesor");//Inner Join by default                        
            //criteria.setFetchMode("programaeducativo.profesors", FetchMode.JOIN);
            criteria.createAlias("unidadaprendizajeImparteProfesor.grupo", "grupo");//Inner Join by default
            criteria.createAlias("grupo.planestudio", "planestudio");//Inner Join by default
            criteria.createAlias("unidadaprendizaje.cicloescolar", "cicloescolar");//Inner Join by default
//            criteria.createAlias("unidadaprendizaje.areaconocimiento", "areaconocimiento");//Inner Join by default
//            //criteria.setMaxResults(10);
//            criteria.createAlias("areaconocimiento.planestudio", "planestudio2");//Inner Join by default     
            criteria.createAlias("planestudio.programaeducativo", "programaeducativo");//Inner Join by default        

            //criteria.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);
//quite la siguiente linea para que obtenga resultados de Enviados y Parciales
//         criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
        //criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", UIPid));      

            if (reporte.tipo.equalsIgnoreCase("ATiempo")) {
                criteria.add(Restrictions.le("reporteavancecontenidotematico.racfechaElaboracion", reporte.fecha1));                
            }
            if (reporte.tipo.equalsIgnoreCase("FueraTiempo")) {
                criteria.add(Restrictions.gt("reporteavancecontenidotematico.racfechaElaboracion", reporte.fecha1));
            }
            if (reporte.tipo.equalsIgnoreCase("EnFechaLimite")) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racfechaElaboracion", reporte.fecha1));
            }

            //criteria.add(Restrictions);
            if(reporte.clave==0){
                
            }else{
              criteria.add(Restrictions.eq("programaeducativo.pedclave", reporte.clave));                
            }
//            if(reporte.acoclave==0){
//                
//            }else{
//            criteria.add(Restrictions.eq("areaconocimiento.acoclave",reporte.acoclave));         
//            }
            if(reporte.uapclave==0){
                //
            }else{
            criteria.add(Restrictions.eq("unidadaprendizaje.uapclave",reporte.uapclave));                            
            }
            if(reporte.gponumero==0){
                
            }else{
            criteria.add(Restrictions.eq("grupo.gponumero", reporte.gponumero));
            }
            if(reporte.pronumeroEmpleado==0){
                
            }else{
            criteria.add(Restrictions.eq("profesor.pronumeroEmpleado", reporte.pronumeroEmpleado));        
            }
            if(reporte.pesvigencia == null || reporte.pesvigencia.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("planestudio.pesvigenciaPlan", reporte.pesvigencia));
            }
            if(reporte.cescicloEscolar == null || reporte.cescicloEscolar.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("cicloescolar.cescicloEscolar", reporte.cescicloEscolar));
            }
            if (reporte.numRact == 1) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero", "1"));
            }
            if (reporte.numRact == 2) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero", "2"));
            }
            if (reporte.numRact == 3) {
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero", "3"));
            }

            if (reporte.numProfUIPid == 0) {

            } else {
                criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", reporte.numProfUIPid));
            }

            listaUAp = criteria.list();

            //session.close();

            //return listaUAp;
        }
        //------------------------ConfigSet-----------------------------------------------------------//
        if(reporte.op.equals("ConfigSet")){
            criteria = session.createCriteria(Configuracion.class, "configuracion");
            criteria.createAlias("configuracion.cicloescolar", "cicloescolar");//Inner Join by default
            criteria.createAlias("configuracion.alerta", "alerta");//Inner Join by default
        //criteria.createAlias("configuracion.calendarioreportes", "calendarioreportes");//Inner Join by default
        //criteria.createAlias("alerta.calendarioreporteTieneAlertas", "calendarioreporteTieneAlerta");//Inner Join by default
            //criteria.add(Restrictions.eq("calendarioreporteTieneAlerta.calnumeroReporte",reporte.calnumeroReporte));   
            listaUAp = criteria.list();
        }
        //----------------------CompararAreaCon------------------------------------------------------//
        if(reporte.op.equals("CompararAreaCon")){
            if(reporte.tipo.equalsIgnoreCase("ATiempo")||reporte.tipo.equalsIgnoreCase("FueraTiempo")||reporte.tipo.equalsIgnoreCase("Enviado")){
            criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            }
            if(reporte.tipo.equalsIgnoreCase("Parcial")){
            criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            if(reporte.tipo.equalsIgnoreCase("EnviadoYParcial")){
            //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            if(reporte.tipo.equalsIgnoreCase("ATiempo")){
                criteria.add(Restrictions.le("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }
            if(reporte.tipo.equalsIgnoreCase("FueraTiempo")){
                criteria.add(Restrictions.gt("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }    
            if(reporte.clavepe == 0){
                
            }else{
            criteria.add(Restrictions.eq("programaeducativo.pedclave",reporte.clavepe));
            }
            if(reporte.pesvigencia == null || reporte.pesvigencia.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("planestudio.pesvigenciaPlan", reporte.pesvigencia));
            }
            if(reporte.cescicloEscolar == null || reporte.cescicloEscolar.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("cicloescolar.cescicloEscolar",reporte.cescicloEscolar));
            }
//            if(reporte.acoclave == 0){
//                
//            }else{
//            criteria.add(Restrictions.eq("areaconocimiento.acoclave",reporte.acoclave));
//            }
            if(reporte.numRact==1){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","1"));
            }
            if(reporte.numRact==2){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","2"));
            }
            if(reporte.numRact==3){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","3"));
            }
            if(reporte.numProfUIPid==0){}else{
                criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", reporte.numProfUIPid));  
            }
            listaUAp = criteria.list();
        }
        //-----------------------------ProgEducatTodos------------------------------------------------//
        if(reporte.op.equals("ProgEducatTodos")){
            criteria = session.createCriteria(Planestudio.class, "planestudio");
            criteria.createAlias("planestudio.programaeducativo", "programaeducativo");//Inner Join by default
            listaUAp = criteria.list();
        }
        //-----------------------------PAGC------------------------------------------------//
        if(reporte.op.equals("PAGC")){
            criteria = session.createCriteria(UnidadaprendizajeImparteProfesor.class, "unidadaprendizajeimparteprofesor");
            criteria.createAlias("unidadaprendizajeimparteprofesor.unidadaprendizaje", "unidadaprendizaje");//Inner Join by default
            criteria.createAlias("unidadaprendizajeimparteprofesor.grupo", "grupo");//Inner Join by default
            criteria.createAlias("unidadaprendizajeimparteprofesor.profesor", "profesor");//Inner Join by default
            criteria.createAlias("grupo.planestudio", "planestudio");//Inner Join by default
            criteria.createAlias("unidadaprendizaje.cicloescolar", "cicloescolar");//Inner Join by default
//            criteria.createAlias("unidadaprendizaje.areaconocimiento", "areaconocimiento");//Inner Join by default
//            criteria.createAlias("areaconocimiento.planestudio", "planestudio");//Inner Join by default     
            criteria.createAlias("planestudio.programaeducativo", "programaeducativo");//Inner Join by default         
            criteria.createAlias("programaeducativo.unidadacademica", "unidadacademica");//Inner Join by default         
           
            listaUAp = criteria.list();
        }       
        //-----------------------------CompararProfGrupo----------------------------------------------//
        if(reporte.op.equals("CompararProfGrupo")){
            if(reporte.tipo.equalsIgnoreCase("ATiempo")||reporte.tipo.equalsIgnoreCase("FueraTiempo")||reporte.tipo.equalsIgnoreCase("Enviado")){
            criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            }
            if(reporte.tipo.equalsIgnoreCase("Parcial")){
            criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            if(reporte.tipo.equalsIgnoreCase("EnviadoYParcial")){
            //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            if(reporte.tipo.equalsIgnoreCase("ATiempo")){
                criteria.add(Restrictions.le("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }
            if(reporte.tipo.equalsIgnoreCase("FueraTiempo")){
                criteria.add(Restrictions.gt("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }
            if(reporte.clavepe == 0){
                
            }else{    
            criteria.add(Restrictions.eq("programaeducativo.pedclave",reporte.clavepe));
            }
            if(reporte.pesvigencia == null || reporte.pesvigencia.equalsIgnoreCase("")){
                
            }else{ 
            criteria.add(Restrictions.eq("planestudio.pesvigenciaPlan", reporte.pesvigencia));
            }
            if(reporte.cescicloEscolar == null || reporte.cescicloEscolar.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("cicloescolar.cescicloEscolar",reporte.cescicloEscolar));
            }
//            if(reporte.acoclave == 0){
//                
//            }else{
//            criteria.add(Restrictions.eq("areaconocimiento.acoclave",reporte.acoclave));
//            }
            if(reporte.uapclave==0){
                //
            }else{
            criteria.add(Restrictions.eq("unidadaprendizaje.uapclave",reporte.uapclave));                            
            }
            if(reporte.pronumeroEmpleado == 0){
                
            }else{
            criteria.add(Restrictions.eq("profesor.pronumeroEmpleado", reporte.pronumeroEmpleado));
            }
            if(reporte.gponumero == 0){
                
            }else{
            criteria.add(Restrictions.eq("grupo.gponumero", reporte.gponumero)); 
            }
            if(reporte.numRact==1){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","1"));
            }
            if(reporte.numRact==2){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","2"));
            }
            if(reporte.numRact==3){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","3"));
            }  
            if(reporte.numProfUIPid==0){}else{
                criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", reporte.numProfUIPid));  
            }
            listaUAp = criteria.list();
        }
        //------------------------------------CompararProgramaEducativo------------------------------------------//
        if(reporte.op.equals("CompararProgEduc")){
            if(reporte.tipo.equalsIgnoreCase("ATiempo")||reporte.tipo.equalsIgnoreCase("FueraTiempo")||reporte.tipo.equalsIgnoreCase("Enviado")){
            criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            }
            if(reporte.tipo.equalsIgnoreCase("Parcial")){
            criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            if(reporte.tipo.equalsIgnoreCase("EnviadoYParcial")){
            //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            if(reporte.tipo.equalsIgnoreCase("ATiempo")){
                criteria.add(Restrictions.le("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }
            if(reporte.tipo.equalsIgnoreCase("FueraTiempo")){
                criteria.add(Restrictions.gt("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }
            if(reporte.clavepe == 0){
                
            }else{
            criteria.add(Restrictions.eq("programaeducativo.pedclave",reporte.clavepe));
            }
            if(reporte.pesvigencia == null || reporte.pesvigencia.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("planestudio.pesvigenciaPlan", reporte.pesvigencia));
            }
            if(reporte.cescicloEscolar == null || reporte.cescicloEscolar.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("cicloescolar.cescicloEscolar",reporte.cescicloEscolar));
            }
            if(reporte.numRact==1){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","1"));
            }
            if(reporte.numRact==2){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","2"));
            }
            if(reporte.numRact==3){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","3"));
            }
            if(reporte.numProfUIPid==0){}else{
              criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", reporte.numProfUIPid));  
            }
            listaUAp = criteria.list();
        }
        //------------------------------------PorcentAvanceSolo------------------------------------------//
        if(reporte.op.equals("PorcentAvanceSolo")){
            //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            if(reporte.tipo.equalsIgnoreCase("ATiempo")){
                criteria.add(Restrictions.le("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }
            if(reporte.tipo.equalsIgnoreCase("FueraTiempo")){
                criteria.add(Restrictions.gt("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }
            if(reporte.clave == 0){
                
            }else{
            criteria.add(Restrictions.eq("programaeducativo.pedclave",reporte.clave));
            }
            if(reporte.pesvigencia == null || reporte.pesvigencia.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("planestudio.pesvigenciaPlan", reporte.pesvigencia));
            }
            if(reporte.cescicloEscolar == null || reporte.cescicloEscolar.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("cicloescolar.cescicloEscolar",reporte.cescicloEscolar));
            }
            if(reporte.numRact==1){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","1"));
            }
            if(reporte.numRact==2){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","2"));
            }
            if(reporte.numRact==3){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","3"));
            }
            if(reporte.numProfUIPid==0){}else{
              criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", reporte.numProfUIPid));  
            }
            listaUAp = criteria.list();
        }
         //------------------------------------CompararUAGrupo------------------------------------------//      
        if(reporte.op.equals("CompararUAGrupo")){
            if(reporte.tipo.equalsIgnoreCase("ATiempo")||reporte.tipo.equalsIgnoreCase("FueraTiempo")||reporte.tipo.equalsIgnoreCase("Enviado")){
            criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            }
            if(reporte.tipo.equalsIgnoreCase("Parcial")){
            criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            if(reporte.tipo.equalsIgnoreCase("EnviadoYParcial")){
            //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Enviado"));
            //criteria.add(Restrictions.eq("reporteavancecontenidotematico.racstatus", "Parcial"));
            }
            if(reporte.tipo.equalsIgnoreCase("ATiempo")){
                criteria.add(Restrictions.le("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }
            if(reporte.tipo.equalsIgnoreCase("FueraTiempo")){
                criteria.add(Restrictions.gt("reporteavancecontenidotematico.racfechaElaboracion",reporte.fecha1));
            }
            if(reporte.clavepe == 0){
                
            }else{
            criteria.add(Restrictions.eq("programaeducativo.pedclave",reporte.clavepe));
            }
            if(reporte.pesvigencia == null || reporte.pesvigencia.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("planestudio.pesvigenciaPlan", reporte.pesvigencia));
            }
            if(reporte.cescicloEscolar == null || reporte.cescicloEscolar.equalsIgnoreCase("")){
                
            }else{
            criteria.add(Restrictions.eq("cicloescolar.cescicloEscolar",reporte.cescicloEscolar));
            }
//            if(reporte.acoclave == 0){
//                
//            }else{
//            criteria.add(Restrictions.eq("areaconocimiento.acoclave",reporte.acoclave));
//            }
            if(reporte.uapclave == 0){
                //
            }else{
            criteria.add(Restrictions.eq("unidadaprendizaje.uapclave",reporte.uapclave));            
            }
            if(reporte.gponumero == 0){
                
            }else{
            criteria.add(Restrictions.eq("grupo.gponumero", reporte.gponumero));
            }
            if(reporte.numRact==1){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","1"));
            }
            if(reporte.numRact==2){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","2"));
            }
            if(reporte.numRact==3){
                criteria.add(Restrictions.eq("reporteavancecontenidotematico.racnumero","3"));
            }
            if(reporte.numProfUIPid==0){}else{
                criteria.add(Restrictions.eq("unidadaprendizajeImparteProfesor.uipid", reporte.numProfUIPid));  
            }
            listaUAp = criteria.list();
        }
        session.close();
        return listaUAp;
    }
}