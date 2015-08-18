/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.integration.persistence;
// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import mx.avanti.common.integration.persistence.HibernateUtil;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Calendarioreporte;
import mx.avanti.siract.business.entity.Cicloescolar;
import mx.avanti.siract.business.entity.Configuracion;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Reporteavancecontenidotematico;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.business.entity.UnidadaprendizajeImparteProfesor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
// </editor-fold> 

/**
 *
 * @author Ricardo
 */

public class EsperadosDAO {
    // <editor-fold defaultstate="collapsed" desc="Variables">
    private Session session;
    private Criteria criteriaRC;
    private Criteria criteriaES;
    private Criteria criteriaAUX;
    private Criteria criteriaAUX2;
    
    private int UnidadAcademica;
    private ArrayList<String> AreaC;
    private ArrayList<String> PlanE;
    private ArrayList<String> CicloE;
    private ArrayList<Integer> ProgramaE;
    private ArrayList<String> Uaprendizaje;
    
    private List<Reporteavancecontenidotematico> listaRC;
    private List<UnidadaprendizajeImparteProfesor> listaES;
    private List<Areaconocimiento> listaAC;
    private List<Unidadaprendizaje> listaUA;
    private List<Profesor> listaPR;
    private List<Programaeducativo> listaPRO;
    private String semaforoProgEd;
    // </editor-fold> 
    
    // <editor-fold desc="Metodos">
    
    // <editor-fold defaultstate="collapsed" desc="Criterias">
    public void initCriteria(int band){
        session = HibernateUtil.getSessionFactory().openSession();
        //---------------------------Criteria de consulta de Grupos para esperados-----------------------------
        criteriaES = session.createCriteria(UnidadaprendizajeImparteProfesor.class,"unidadaprendizajeImparteProfesor"); 
        //---------------------------Criteria de consulta Areas de Conocimiento-------------------------------
        criteriaAUX = null;
        criteriaAUX2 = null;
        if(band==1){
            criteriaAUX = session.createCriteria(Areaconocimiento.class,"areaconocimiento");   
            criteriaAUX.createAlias("areaconocimiento.planestudio", "planestudio");
            criteriaAUX.createAlias("planestudio.programaeducativo", "programaeducativo");
            criteriaAUX.createAlias("programaeducativo.unidadacademica", "unidadacademica");
            criteriaAUX.add(Restrictions.eq("unidadacademica.uacid", UnidadAcademica));//Filta los resultados por unidad academica
        }
        //---------------------------Criteria de consulta de Unidad Aprendizaje-------------------------------
        if(band==2){
            criteriaAUX = session.createCriteria(UnidadaprendizajeImparteProfesor.class,"unidadaprendizajeImparteProfesor");
            criteriaAUX.createAlias("unidadaprendizajeImparteProfesor.grupo", "grupo");
            criteriaAUX.createAlias("grupo.planestudio", "planestudio");
            criteriaAUX.createAlias("planestudio.programaeducativo", "programaeducativo");
            criteriaAUX.createAlias("programaeducativo.unidadacademica", "unidadacademica");
            criteriaAUX.add(Restrictions.eq("unidadacademica.uacid", UnidadAcademica));
            
            criteriaAUX2 = session.createCriteria(Programaeducativo.class,"programaeducativo");   
            criteriaAUX2.createAlias("programaeducativo.unidadacademica", "unidadacademica");
            criteriaAUX2.add(Restrictions.eq("unidadacademica.uacid", UnidadAcademica));
        }
        //---------------------------Criteria de consulta de Programa educativo-------------------------------
        if(band==3){
            criteriaAUX = session.createCriteria(Programaeducativo.class,"programaeducativo");   
            criteriaAUX.createAlias("programaeducativo.unidadacademica", "unidadacademica");
            criteriaAUX.add(Restrictions.eq("unidadacademica.uacid", UnidadAcademica));
        }
        //---------------------------Criteria de consulta de Profesor-------------------------------
        if(band==4){
            criteriaAUX = session.createCriteria(Profesor.class,"profesor");
            criteriaAUX.createAlias("profesor.unidadaprendizajeImparteProfesors", "unidadaprendizajeImparteProfesors");
            criteriaAUX.createAlias("unidadaprendizajeImparteProfesors.grupo", "grupo");
            criteriaAUX.createAlias("grupo.planestudio", "planestudio");
            criteriaAUX.createAlias("planestudio.programaeducativo", "programaeducativo");        
            criteriaAUX.createAlias("programaeducativo.unidadacademica", "unidadacademica");
            criteriaAUX.add(Restrictions.eq("unidadacademica.uacid", UnidadAcademica));
        }
        if(band==5){
            criteriaRC = session.createCriteria(Reporteavancecontenidotematico.class, "reporteavancecontenidotematico");
            criteriaRC.createAlias("reporteavancecontenidotematico.unidadaprendizajeImparteProfesor", "unidadaprendizajeImparteProfesor");
            criteriaRC.createAlias("unidadaprendizajeImparteProfesor.grupo", "grupo");
            criteriaRC.createAlias("grupo.planestudio", "planestudio");
            criteriaRC.createAlias("planestudio.programaeducativo", "programaeducativo");        
            criteriaRC.createAlias("programaeducativo.unidadacademica", "unidadacademica");
            criteriaRC.add(Restrictions.eq("unidadacademica.uacid", UnidadAcademica));
        }else{
            criteriaRC = session.createCriteria(Reporteavancecontenidotematico.class, "reporteavancecontenidotematico");
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Area de conocimiento"> 
    
    // <editor-fold defaultstate="collapsed" desc="Init para area de conocimiento">
    public void initAreaConocimiento(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE) {
        session = HibernateUtil.getSessionFactory().openSession();
        this.UnidadAcademica = UnidadAcademica;
        initCriteria(1);
        listaRC = criteriaRC.list();
        listaAC=criteriaAUX.list();
        listaES = criteriaES.list();
        this.AreaC = AC;
        this.PlanE = PE;
        this.CicloE = CE;
        this.ProgramaE = PrE;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Metodo retorna Array con el nombre de area de conocimiento y Racts entregados"> 
    public ArrayList<String> getEntregadosAreaConocimiento(){
        ArrayList<String> Racts = new ArrayList<String>();
        String nombre="";
        int entregados=0;
        int x;
        for (Areaconocimiento listaAC1 : listaAC) {
            if (AreaC.contains(listaAC1.getAconombre())) {
                nombre = listaAC1.getAconombre();
                String queryAreaCon="from Areaconocimiento as a join a.unidadaprendizajes as ua where a.aconombre='"+nombre+"'";
                ArrayList<Unidadaprendizaje> listaAreaCon = new ArrayList<Unidadaprendizaje>();
                reportesDAO rp = new reportesDAO();
                List<Object> listaObjetos=rp.executeQuery(queryAreaCon);
                Iterator itr=listaObjetos.iterator();
                while(itr.hasNext()){
                    Object[] obj=(Object[])itr.next();
                    listaAreaCon.add((Unidadaprendizaje)obj[0]);
                }
                for(x=0;x<listaRC.size();x++){
                    //--Condicion que verifica que solo se tomen en cuenta los datos con los parametros elegidos de AreaConocimiento,PlanEstudios,CicloEscolar y ProgramaEducativo-----------------
                    if(PlanE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesvigenciaPlan())&&
                       CicloE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getCicloescolar().getCescicloEscolar())&&
                       ProgramaE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getProgramaeducativo().getPedid())){
                        for (Unidadaprendizaje listaAreaCon1 : listaAreaCon) {
                            if(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapnombre().equalsIgnoreCase(listaAreaCon1.getUapnombre())){
                                if(listaRC.get(x).getRacstatus().equalsIgnoreCase("Enviado")){
                                    entregados++;
                                }
                            }
                        }  
                    }   
                }
            }
            Racts.add(nombre+"-"+Integer.toString(entregados));
            entregados = 0;
        }
        return Racts;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Metodo retorna Array con el nombre de area de conocimiento y Racts entregados por numero de ract">
    public ArrayList<String> getEntregadosAreaConocimiento(String numero){
        ArrayList<String> Racts = new ArrayList<String>();
        String nombre="";
        int entregados=0;
        int x;
        for (Areaconocimiento listaAC1 : listaAC) {
            if (AreaC.contains(listaAC1.getAconombre())) {
                nombre = listaAC1.getAconombre();
                String queryAreaCon="from Areaconocimiento as a join a.unidadaprendizajes as ua where a.aconombre='"+nombre+"'";
                ArrayList<Unidadaprendizaje> listaAreaCon = new ArrayList<Unidadaprendizaje>();
                reportesDAO rp = new reportesDAO();
                List<Object> listaObjetos=rp.executeQuery(queryAreaCon);
                Iterator itr=listaObjetos.iterator();
                while(itr.hasNext()){
                    Object[] obj=(Object[])itr.next();
                    listaAreaCon.add((Unidadaprendizaje)obj[0]);
                }
                for(x=0;x<listaRC.size();x++){
                    //--Condicion que verifica que solo se tomen en cuenta los datos con los parametros elegidos de AreaConocimiento,PlanEstudios,CicloEscolar y ProgramaEducativo-----------------
                    if(PlanE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesvigenciaPlan())&&
                       CicloE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getCicloescolar().getCescicloEscolar())&&
                       ProgramaE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getProgramaeducativo().getPedid())){
                        for (Unidadaprendizaje listaAreaCon1 : listaAreaCon) {
                            if(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapnombre().equalsIgnoreCase(listaAreaCon1.getUapnombre())){
                                if(listaRC.get(x).getRacstatus().equalsIgnoreCase("Enviado")&&listaRC.get(x).getRacnumero().equalsIgnoreCase(numero)){
                                    entregados++;
                                }
                            }
                        }  
                    }   
                }
            }
            Racts.add(nombre+"-"+Integer.toString(entregados));
            entregados = 0;
        }
        return Racts;
    }
    
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Metodo retorna Array con el nombre de area de conocimiento y Racts esperados"> 
    public ArrayList<String> getEsperadosAreaConocimiento(){
        ArrayList<String> Racts = new ArrayList<String>();
        String nombre="";
        int esperados=0;
        for (Areaconocimiento listaAC1 : listaAC) {
            if (AreaC.contains(listaAC1.getAconombre())) {
                nombre = listaAC1.getAconombre();
                String queryAreaCon="from Areaconocimiento as a join a.unidadaprendizajes as ua where a.aconombre='"+nombre+"'";
                ArrayList<Unidadaprendizaje> listaAreaCon = new ArrayList<Unidadaprendizaje>();
                reportesDAO rp = new reportesDAO();
                List<Object> listaObjetos=rp.executeQuery(queryAreaCon);
                Iterator itr=listaObjetos.iterator();
                while(itr.hasNext()){
                    Object[] obj=(Object[])itr.next();
                    listaAreaCon.add((Unidadaprendizaje)obj[0]);
                }
                for (UnidadaprendizajeImparteProfesor listaES1 : listaES) {
                //--Condicion que verifica que solo se tomen en cuenta los datos con los parametros elegidos de AreaConocimiento,PlanEstudios,CicloEscolar y ProgramaEducativo-----------------
                    if (PlanE.contains(listaES1.getGrupo().getPlanestudio().getPesvigenciaPlan()) && 
                    CicloE.contains(listaES1.getUnidadaprendizaje().getCicloescolar().getCescicloEscolar()) && 
                    ProgramaE.contains(listaES1.getGrupo().getPlanestudio().getProgramaeducativo().getPedid())) {
                //------------------------------------------------------------------------------------------------------------------------------------------    
                        for (Unidadaprendizaje listaAreaCon1 : listaAreaCon) {
                            if(listaES1.getUnidadaprendizaje().getUapnombre().equalsIgnoreCase(listaAreaCon1.getUapnombre())){
                                esperados = esperados + 3;
                            }
                        }
                    }     
                }
            }
            Racts.add(nombre+"-"+Integer.toString(esperados));
            esperados = 0;
        }
        return Racts;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Metodo retorna Array con el nombre de area de conocimiento y una relacion de Racts esperados y entregados">
    public ArrayList<String> getRactsAreaConocimiento() {
        ArrayList<String> Completo = new ArrayList<String>();
        ArrayList<String> entregados = getEntregadosAreaConocimiento();
        ArrayList<String> esperados = getEsperadosAreaConocimiento();
        int tamano1 = entregados.size();
        int tamano2 = esperados.size();
        if(tamano1==tamano2){
            for(int x=0;x<tamano1;x++){
                String[] AuxEntregados = entregados.get(x).split("-");
                String[] AuxEsperados = esperados.get(x).split("-");
                if(AuxEntregados[0].equalsIgnoreCase(AuxEsperados[0])){
                    Completo.add(AuxEsperados[0]+"-"+AuxEntregados[1]+"-"+AuxEsperados[1]);
                }else{
                    System.out.println("ERROR, Valores de area de conocimiento no coinciden");
                }
            }
        }else{
            System.out.println("ERROR, Longitud de valores entregados y esperados no coinciden");
        }
        return Completo;
    }
    // </editor-fold>
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Unidad de aprendizaje"> 
    
    // <editor-fold defaultstate="collapsed" desc="Init para unidad de aprendizaje"> 
    public  void initUnidadAprendizaje(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,ArrayList<String> aprendi) {
        session = HibernateUtil.getSessionFactory().openSession();
        this.AreaC = AC;
        this.PlanE = PE;
        this.CicloE = CE;
        this.ProgramaE = PrE;
        this.UnidadAcademica = UnidadAcademica;
        this.Uaprendizaje = aprendi;
        initCriteria(2);
        listaRC = criteriaRC.list();
        listaUA=criteriaAUX.list();
        listaES = criteriaES.list();
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Metodo retorna Array con el nombre de unidad de aprendizaje y Racts entregados"> 
    public ArrayList<String> getEntregadosUnidadAprendizaje(){
        ArrayList<String> Racts = new ArrayList<String>();
        int entregados = 0;
        int x;
        String nombre;
        for (Unidadaprendizaje listaUA1 : listaUA) {
            if (Uaprendizaje.contains(listaUA1.getUapnombre())) {
                nombre = listaUA1.getUapnombre(); 
                for(x=0;x<listaRC.size();x++){
                    if(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapnombre().equals(nombre)&&
                    PlanE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesvigenciaPlan())&&
                    CicloE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getCicloescolar().getCescicloEscolar())&&
                    ProgramaE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getProgramaeducativo().getPedid())){                    
                        String queryAreaCon="from Areaconocimiento as a join a.unidadaprendizajes as ua where ua.uapclave='"+listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave()+"'";
                        ArrayList<Areaconocimiento> listaAreaCon = new ArrayList<Areaconocimiento>();
                        reportesDAO rp = new reportesDAO();
                        List<Object> listaObjetos=rp.executeQuery(queryAreaCon);
                        Iterator itr=listaObjetos.iterator();
                        while(itr.hasNext()){
                            Object[] obj=(Object[])itr.next();
                            listaAreaCon.add((Areaconocimiento)obj[0]);
                        }    
                        for (Areaconocimiento listaAreaCon1 : listaAreaCon) {
                            if(AreaC.contains(listaAreaCon1.getAconombre()) && listaRC.get(x).getRacstatus().equalsIgnoreCase("Enviado")){
                                entregados++;
                            }
                        }
                    }   
                }
                Racts.add(nombre+"-"+Integer.toString(entregados));
                entregados=0;
            }
        }
        return Racts;   
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Metodo retorna Array con el nombre de unidad de aprendizaje y Racts entregados por numero de ract"> 
    public ArrayList<String> getEntregadosUnidadAprendizaje(String numero){
        ArrayList<String> Racts = new ArrayList<String>();
        int entregados = 0;
        int x;
        String nombre;
        for (Unidadaprendizaje listaUA1 : listaUA) {
            if (Uaprendizaje.contains(listaUA1.getUapnombre())) {
                nombre = listaUA1.getUapnombre(); 
                for(x=0;x<listaRC.size();x++){
                    if(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapnombre().equals(nombre)&&
                    PlanE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesvigenciaPlan())&&
                    CicloE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getCicloescolar().getCescicloEscolar())&&
                    ProgramaE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getProgramaeducativo().getPedid())){    
                        String queryAreaCon="from Areaconocimiento as a join a.unidadaprendizajes as ua where ua.uapclave='"+listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave()+"'";
                        ArrayList<Areaconocimiento> listaAreaCon = new ArrayList<Areaconocimiento>();
                        reportesDAO rp = new reportesDAO();
                        List<Object> listaObjetos=rp.executeQuery(queryAreaCon);
                        Iterator itr=listaObjetos.iterator();
                        while(itr.hasNext()){
                            Object[] obj=(Object[])itr.next();
                            listaAreaCon.add((Areaconocimiento)obj[0]);
                        }    
                        for (Areaconocimiento listaAreaCon1 : listaAreaCon) {
                            if(AreaC.contains(listaAreaCon1.getAconombre()) && listaRC.get(x).getRacstatus().equalsIgnoreCase("Enviado")&&listaRC.get(x).getRacnumero().equalsIgnoreCase(numero)){
                                entregados++;
                            }
                        }
                    } 
                }
                Racts.add(nombre+"-"+Integer.toString(entregados));
                entregados=0;
            }
        }
        return Racts;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Metodo retorna Array con el nombre de unidad de aprendizaje y Racts esperados"> 
    public ArrayList<String> getEsperadosUnidadAprendizaje(){
        ArrayList<String> Racts = new ArrayList<String>();
        int esperados=0;
        String nombre;
        for (Unidadaprendizaje listaUA1 : listaUA) {
            if (Uaprendizaje.contains(listaUA1.getUapnombre())) {
                nombre = listaUA1.getUapnombre();
                for (UnidadaprendizajeImparteProfesor listaES1 : listaES) {
                    if (listaES1.getUnidadaprendizaje().getUapnombre().equalsIgnoreCase(nombre) 
                    && PlanE.contains(listaES1.getGrupo().getPlanestudio().getPesvigenciaPlan()) 
                    && CicloE.contains(listaES1.getUnidadaprendizaje().getCicloescolar().getCescicloEscolar()) 
                    && ProgramaE.contains(listaES1.getGrupo().getPlanestudio().getProgramaeducativo().getPedid())){    
                        String queryAreaCon="from Areaconocimiento as a join a.unidadaprendizajes as ua where ua.uapclave='"+listaES1.getUnidadaprendizaje().getUapclave()+"'";
                        ArrayList<Areaconocimiento> listaAreaCon = new ArrayList<Areaconocimiento>();
                        reportesDAO rp = new reportesDAO();
                        List<Object> listaObjetos=rp.executeQuery(queryAreaCon);
                        Iterator itr=listaObjetos.iterator();
                        while(itr.hasNext()){
                            Object[] obj=(Object[])itr.next();
                            listaAreaCon.add((Areaconocimiento)obj[0]);
                        }    
                        for (Areaconocimiento listaAreaCon1 : listaAreaCon) {
                            if(AreaC.contains(listaAreaCon1.getAconombre())){
                                esperados = esperados + 3;
                            }
                        }
                    }
                }
                Racts.add(nombre+"-"+Integer.toString(esperados));
                esperados=0;
            }
        }
        return Racts;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Metodo retorna Array con el nombre de unidad de aprendizaje y una relacion de Racts esperados y entregados"> 
    public ArrayList<String> getRactsUnidadAprendizaje(){
        ArrayList<String> Completo = new ArrayList<String>();
        ArrayList<String> entregados = getEntregadosUnidadAprendizaje();
        ArrayList<String> esperados = getEsperadosUnidadAprendizaje();
        int tamano1 = entregados.size();
        int tamano2 = esperados.size();
        if(tamano1==tamano2){
            for(int x=0;x<tamano1;x++){
                String[] AuxEntregados = entregados.get(x).split("-");
                String[] AuxEsperados = esperados.get(x).split("-");
                if(AuxEntregados[0].equalsIgnoreCase(AuxEsperados[0])){
                    Completo.add(AuxEsperados[0]+"-"+AuxEntregados[1]+"-"+AuxEsperados[1]);
                }else{
                    System.out.println("ERROR, Valores de area de conocimiento no coinciden");
                }
            }
        }else{
            System.out.println("ERROR, Longitud de valores entregados y esperados no coinciden");
        }
        return Completo;
    }
    // </editor-fold>
    
    // </editor-fold>
    
    // <editor-fold desc="Programa educativo"> 
    
    // <editor-fold defaultstate="collapsed" desc="Init para programa educativo"> 
    public void initProgramaEducativo(int UnidadAcademica,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE) {
        session = HibernateUtil.getSessionFactory().openSession();
        this.PlanE = PE;
        this.CicloE = CE;
        this.ProgramaE = PrE;
        this.UnidadAcademica = UnidadAcademica;
        initCriteria(3);
        listaRC = criteriaRC.list();
        listaPRO=criteriaAUX.list();
        listaES = criteriaES.list();
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Entregados, no entregados y esperados">
    // <editor-fold defaultstate="collapsed" desc="Metodo retorna Array con el nombre de programa educativo y Racts entregados"> 
    public ArrayList<String> getEntregadosProgramaEducativo(){
        ArrayList<String> Racts = new ArrayList<String>();
        int entregados = 0;
        int x;
        String nombre;
        System.out.println("Size de listaPRO: "+listaPRO.size());
        for (Programaeducativo listaPRO1 : listaPRO) {
            System.out.println("Contenido de listaPRO: " + listaPRO1.getPednombre());
            if (ProgramaE.contains(listaPRO1.getPedclave())) {
                nombre = listaPRO1.getPednombre();
                for(x=0;x<listaRC.size();x++){
                    //--Condicion que verifica que solo se tomen en cuenta los datos con los parametros elegidos de AreaConocimiento,PlanEstudios,CicloEscolar y ProgramaEducativo-----------------
                    if(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getProgramaeducativo().getPednombre().equals(nombre)&&
                            PlanE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesvigenciaPlan())&&
                            CicloE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getCicloescolar().getCescicloEscolar())){
                        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                        if(listaRC.get(x).getRacstatus().equalsIgnoreCase("Enviado")){
                            entregados++;
                        }
                    }   
                }
                Racts.add(nombre+"-"+Integer.toString(entregados));
                entregados=0;
            }
        }
        return Racts;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Metodo retorna Array con el nombre de programa educativo y Racts entregados por numero de ract"> 
    public ArrayList<String> getEntregadosProgramaEducativo(String numero){
        ArrayList<String> Racts = new ArrayList<String>();
        int entregados = 0;
        String nombre;
        for (Programaeducativo listaPRO1 : listaPRO) {
            if (ProgramaE.contains(listaPRO1.getPedclave())) {
                nombre = listaPRO1.getPednombre();
                for(Reporteavancecontenidotematico listaRC1 : listaRC){
                    //--Condicion que verifica que solo se tomen en cuenta los datos con los parametros elegidos de AreaConocimiento,PlanEstudios,CicloEscolar y ProgramaEducativo-----------------
                    if(listaRC1.getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getProgramaeducativo().getPednombre().equals(nombre)&&
                            PlanE.contains(listaRC1.getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesvigenciaPlan())&&
                            CicloE.contains(listaRC1.getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getCicloescolar().getCescicloEscolar())){
                        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                        if(listaRC1.getRacstatus().equalsIgnoreCase("Enviado")&&listaRC1.getRacnumero().equalsIgnoreCase(numero)){
                            entregados++;
                        }
                    }   
                }
            Racts.add(nombre+"-"+Integer.toString(entregados));
            entregados=0;
            }
            
        }
        return Racts;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Metodo retorna Array con el nombre de programa educativo y Racts esperados"> 
    public ArrayList<String> getEsperadosProgramaEducativo(){
        ArrayList<String> Racts = new ArrayList<String>();
        int esperados=0;
        String nombre;
        for (Programaeducativo listaPRO1 : listaPRO) {
            if (ProgramaE.contains(listaPRO1.getPedclave())) {
                nombre = listaPRO1.getPednombre();
                for(int j=0;j<listaES.size();j++){
                    //--Condicion que verifica que solo se tomen en cuenta los datos con los parametros elegidos de AreaConocimiento,PlanEstudios,CicloEscolar y ProgramaEducativo-----------------
                    if(listaES.get(j).getGrupo().getPlanestudio().getProgramaeducativo().getPednombre().equalsIgnoreCase(nombre)&&
                            PlanE.contains(listaES.get(j).getGrupo().getPlanestudio().getPesvigenciaPlan())&&
                            CicloE.contains(listaES.get(j).getUnidadaprendizaje().getCicloescolar().getCescicloEscolar())){
                        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                        
                        if(j<=listaRC.size()){
                            esperados = esperados + 3;
                        }
                        
                    }
                }
                Racts.add(nombre+"-"+Integer.toString(esperados));
                esperados=0;
            }
        }
        return Racts;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Metodo retorna Array con el nombre de programa educativo y una relacion de Racts esperados y entregados"> 
    public ArrayList<String> getRactsProgramaEducativo() {
        ArrayList<String> Completo = new ArrayList<String>();
        ArrayList<String> entregados = getEntregadosProgramaEducativo();
        ArrayList<String> esperados = getEsperadosProgramaEducativo();
        int tamano1 = entregados.size();
        int tamano2 = esperados.size();
        if(tamano1==tamano2){
            for(int x=0;x<tamano1;x++){
                String[] AuxEntregados = entregados.get(x).split("-");
                String[] AuxEsperados = esperados.get(x).split("-");
                if(AuxEntregados[0].equalsIgnoreCase(AuxEsperados[0])){
                    Completo.add(AuxEsperados[0]+"-"+AuxEntregados[1]+"-"+AuxEsperados[1]);
                }else{
                    System.out.println("ERROR, Valores de area de conocimiento no coinciden");
                }
            }
        }else{
            System.out.println("ERROR, Longitud de valores entregados y esperados no coinciden");
        }
        return Completo;
    }
    // </editor-fold>
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="A tiempo, fuera de tiempo y en fecha limite">
    // <editor-fold defaultstate="collapsed" desc="Variables">
    private ArrayList<Date> fechasCorte;
    private ArrayList<Date> fechasLimite;
    // </editor-fold>
    
    public void obtenerFechas(String Ciclo){
        fechasCorte = new ArrayList<Date>();
        fechasLimite = new ArrayList<Date>();
        String obtenerID = "from Cicloescolar as c where c.cescicloEscolar ='"+Ciclo+"'";
        List<Cicloescolar> ListCiclo = (List) HibernateUtil.getSession().createQuery(obtenerID).list();
        List<Calendarioreporte> listaCalendario = null;
        String queryCiclo="from Configuracion as c where c.cicloescolar.cesid='"+ListCiclo.get(0).getCesid()+"'";
        List<Configuracion> Config = (List) HibernateUtil.getSession().createQuery(queryCiclo).list();
        System.out.println(Config.size()+"-"+Config.get(0).getConid());
        for(Configuracion config1:Config){
            String query="from Configuracion as c join c.calendarioreportes as cr where c.conid='"+config1.getConid()+"'";
            listaCalendario=new ArrayList<Calendarioreporte>();
            List<Object> listaObjetos = (List) HibernateUtil.getSession().createQuery(query).list();
            Iterator itr=listaObjetos.iterator();
            for(Object calendario1:listaObjetos){
                Object[] obj=(Object[])itr.next();
                listaCalendario.add((Calendarioreporte)obj[1]);
            }
            for(Calendarioreporte calendario1:listaCalendario){
                fechasCorte.add(calendario1.getCrefechaCorte());
                fechasLimite.add(calendario1.getCrefechaLimite());
            }
        }
    }
    
    public ArrayList<String> getAtiempoProgramaEducativo(String r1,String r2,String r3){
        obtenerFechas(CicloE.get(0));
        ArrayList<String> Racts = new ArrayList<String>();
        int entregados = 0;
        int x;
        String nombre;
        System.out.println("Size de listaPRO: "+listaPRO.size());
        for (Programaeducativo listaPRO1 : listaPRO) {
            System.out.println("Contenido de listaPRO: " + listaPRO1.getPednombre());
            if (ProgramaE.contains(listaPRO1.getPedclave())) {
                nombre = listaPRO1.getPednombre();
                for(x=0;x<listaRC.size();x++){
                    //--Condicion que verifica que solo se tomen en cuenta los datos con los parametros elegidos de AreaConocimiento,PlanEstudios,CicloEscolar y ProgramaEducativo-----------------
                    if(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getProgramaeducativo().getPednombre().equals(nombre)&&
                            PlanE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesvigenciaPlan())&&
                            CicloE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getCicloescolar().getCescicloEscolar())){
                        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                        if(listaRC.get(x).getRacstatus().equalsIgnoreCase("Enviado")){
                            if(listaRC.get(x).getRacnumero().equalsIgnoreCase(r1)&&listaRC.get(x).getRacfechaElaboracion().before(fechasLimite.get(0))){
                                entregados++;
                            }
                            if(listaRC.get(x).getRacnumero().equalsIgnoreCase(r2)&&listaRC.get(x).getRacfechaElaboracion().before(fechasLimite.get(1))){
                                entregados++;
                            }
                            if(listaRC.get(x).getRacnumero().equalsIgnoreCase(r3)&&listaRC.get(x).getRacfechaElaboracion().before(fechasLimite.get(2))){
                                entregados++;
                            }
                        }
                    }   
                }
                Racts.add(nombre+"-"+Integer.toString(entregados));
                entregados=0;
            }
        }
        return Racts;
    }
    
    public ArrayList<String> getEnLimiteProgramaEducativo(String r1,String r2,String r3){
        obtenerFechas(CicloE.get(0));
        ArrayList<String> Racts = new ArrayList<String>();
        int entregados = 0;
        int x;
        String nombre;
        System.out.println("Size de listaPRO: "+listaPRO.size());
        for (Programaeducativo listaPRO1 : listaPRO) {
            System.out.println("Contenido de listaPRO: " + listaPRO1.getPednombre());
            if (ProgramaE.contains(listaPRO1.getPedclave())) {
                nombre = listaPRO1.getPednombre();
                for(x=0;x<listaRC.size();x++){
                    //--Condicion que verifica que solo se tomen en cuenta los datos con los parametros elegidos de AreaConocimiento,PlanEstudios,CicloEscolar y ProgramaEducativo-----------------
                    if(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getProgramaeducativo().getPednombre().equals(nombre)&&
                            PlanE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor(). getGrupo().getPlanestudio().getPesvigenciaPlan())&&
                            CicloE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getCicloescolar().getCescicloEscolar())){
                        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                        if(listaRC.get(x).getRacstatus().equalsIgnoreCase("Enviado")){
                            if(listaRC.get(x).getRacnumero().equalsIgnoreCase(r1)&&listaRC.get(x).getRacfechaElaboracion().equals(fechasLimite.get(0))){
                                entregados++;
                            }
                            if(listaRC.get(x).getRacnumero().equalsIgnoreCase(r2)&&listaRC.get(x).getRacfechaElaboracion().equals(fechasLimite.get(1))){
                                entregados++;
                            }
                            if(listaRC.get(x).getRacnumero().equalsIgnoreCase(r3)&&listaRC.get(x).getRacfechaElaboracion().equals(fechasLimite.get(2))){
                                entregados++;
                            }
                        }
                    }   
                }
                Racts.add(nombre+"-"+Integer.toString(entregados));
                entregados=0;
            }
        }
        return Racts;
    }
    
    public ArrayList<String> getDespuesLimiteProgramaEducativo(String r1,String r2,String r3){
        ArrayList<String> Racts = new ArrayList<String>();
        int entregados = 0;
        int x;
        String nombre;
        System.out.println("Size de listaPRO: "+listaPRO.size());
        for (Programaeducativo listaPRO1 : listaPRO) {
            System.out.println("Contenido de listaPRO: " + listaPRO1.getPednombre());
            if (ProgramaE.contains(listaPRO1.getPedclave())) {
                nombre = listaPRO1.getPednombre();
                for(x=0;x<listaRC.size();x++){
                    //--Condicion que verifica que solo se tomen en cuenta los datos con los parametros elegidos de AreaConocimiento,PlanEstudios,CicloEscolar y ProgramaEducativo-----------------
                    if(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getProgramaeducativo().getPednombre().equals(nombre)&&
                            PlanE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesvigenciaPlan())&&
                            CicloE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getCicloescolar().getCescicloEscolar())){
                        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                        if(listaRC.get(x).getRacstatus().equalsIgnoreCase("Enviado")){
                            if(listaRC.get(x).getRacnumero().equalsIgnoreCase(r1)&&listaRC.get(x).getRacfechaElaboracion().after(fechasLimite.get(0))){
                                entregados++;
                            }
                            if(listaRC.get(x).getRacnumero().equalsIgnoreCase(r2)&&listaRC.get(x).getRacfechaElaboracion().after(fechasLimite.get(1))){
                                entregados++;
                            }
                            if(listaRC.get(x).getRacnumero().equalsIgnoreCase(r3)&&listaRC.get(x).getRacfechaElaboracion().after(fechasLimite.get(2))){
                                entregados++;
                            }
                        }
                    }   
                }
                Racts.add(nombre+"-"+Integer.toString(entregados));
                entregados=0;
            }
        }
        return Racts;
    }
    // </editor-fold> 
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Profesor">
    
    // <editor-fold defaultstate="collapsed" desc="Init de profesor">
    public void initProfesor(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,ArrayList<String> aprendi){
        session = HibernateUtil.getSessionFactory().openSession();
        this.AreaC = AC;
        this.PlanE = PE;
        this.CicloE = CE;
        this.ProgramaE = PrE;
        this.UnidadAcademica = UnidadAcademica;
        this.Uaprendizaje = aprendi;
        initCriteria(4);
        listaRC = criteriaRC.list();
        listaPR=criteriaAUX.list();
        listaES = criteriaES.list();
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Metodo retorna Array con el nombre de profesor y Racts entregados">
    public ArrayList<String> getEntregadosProfesor(){
        ArrayList<String> Racts = new ArrayList<String>();
        ArrayList<String> profesor = new ArrayList<String>();
        int entregados = 0;
        int x;
        String nombre;
        for (Profesor listaPR1 : listaPR) {
            nombre = listaPR1.getPronombre() + " " + listaPR1.getProapellidoPaterno() + " " + listaPR1.getProapellidoMaterno();
            for (x=0; x<listaRC.size(); x++) {
            //--Condicion que verifica que solo se tomen en cuenta los datos con los parametros elegidos de AreaConocimiento,PlanEstudios,CicloEscolar y ProgramaEducativo-----------------
                if (listaRC.get(x).getUnidadaprendizajeImparteProfesor().getProfesor().getProrfc().equalsIgnoreCase(listaPR1.getProrfc()) 
                && PlanE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesvigenciaPlan()) 
                && CicloE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getCicloescolar().getCescicloEscolar()) 
                && ProgramaE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getProgramaeducativo().getPedid())
                && Uaprendizaje.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapnombre())) {
            //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                    String queryAreaCon="from Areaconocimiento as a join a.unidadaprendizajes as ua where ua.uapclave='"+listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave()+"'";
                    ArrayList<Areaconocimiento> listaAreaCon = new ArrayList<Areaconocimiento>();
                    reportesDAO rp = new reportesDAO();
                    List<Object> listaObjetos=rp.executeQuery(queryAreaCon);
                    Iterator itr=listaObjetos.iterator();
                    while(itr.hasNext()){
                        Object[] obj=(Object[])itr.next();
                        listaAreaCon.add((Areaconocimiento)obj[0]);
                    }    
                    for (Areaconocimiento listaAreaCon1 : listaAreaCon) {
                        if(AreaC.contains(listaAreaCon1.getAconombre()) && listaRC.get(x).getRacstatus().equalsIgnoreCase("Enviado")){
                            entregados++;
                        }
                    }
                }
            }
            if(profesor.contains(nombre)==false){
                Racts.add(nombre+"-"+Integer.toString(entregados));
            }
            profesor.add(nombre);
            entregados=0;
        }
        return Racts;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Metodo retorna Array con el nombre de profesor y Racts entregados por numero de ract">
    public ArrayList<String> getEntregadosProfesor(String numero){
        ArrayList<String> Racts = new ArrayList<String>();
        ArrayList<String> profesor = new ArrayList<String>();
        int entregados = 0;
        int x;
        String nombre;
        for (Profesor listaPR1 : listaPR) {
            nombre = listaPR1.getPronombre() + " " + listaPR1.getProapellidoPaterno() + " " + listaPR1.getProapellidoMaterno();
            for (x=0; x<listaRC.size(); x++) {
            //--Condicion que verifica que solo se tomen en cuenta los datos con los parametros elegidos de AreaConocimiento,PlanEstudios,CicloEscolar y ProgramaEducativo-----------------
                if (listaRC.get(x).getUnidadaprendizajeImparteProfesor().getProfesor().getProrfc().equalsIgnoreCase(listaPR1.getProrfc()) 
                && PlanE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesvigenciaPlan()) 
                && CicloE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getCicloescolar().getCescicloEscolar()) 
                && ProgramaE.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getProgramaeducativo().getPedid()) 
                && Uaprendizaje.contains(listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapnombre())) {
            //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                    String queryAreaCon="from Areaconocimiento as a join a.unidadaprendizajes as ua where ua.uapclave='"+listaRC.get(x).getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave()+"'";
                    ArrayList<Areaconocimiento> listaAreaCon = new ArrayList<Areaconocimiento>();
                    reportesDAO rp = new reportesDAO();
                    List<Object> listaObjetos=rp.executeQuery(queryAreaCon);
                    Iterator itr=listaObjetos.iterator();
                    while(itr.hasNext()){
                        Object[] obj=(Object[])itr.next();
                        listaAreaCon.add((Areaconocimiento)obj[0]);
                    }    
                    for (Areaconocimiento listaAreaCon1 : listaAreaCon) {
                        if(AreaC.contains(listaAreaCon1.getAconombre()) && listaRC.get(x).getRacstatus().equalsIgnoreCase("Enviado")&&listaRC.get(x).getRacnumero().equalsIgnoreCase(numero)){
                            entregados++;
                        }
                    }
                }
            }
            if(profesor.contains(nombre)==false){
                Racts.add(nombre+"-"+Integer.toString(entregados));
            }
            profesor.add(nombre);
            entregados=0;
        }
        return Racts;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Metodo retorna Array con el nombre de profesor y Racts esperados">
    public ArrayList<String> getEsperadosProfesor(){
        ArrayList<String> Racts = new ArrayList<String>();
        ArrayList<String> profesor = new ArrayList<String>();
        int esperados = 0;
        String nombre;
        for (Profesor listaPR1 : listaPR) {
            nombre = listaPR1.getPronombre() + " " + listaPR1.getProapellidoPaterno() + " " + listaPR1.getProapellidoMaterno();
            for (UnidadaprendizajeImparteProfesor listaES1 : listaES) {
                //--Condicion que verifica que solo se tomen en cuenta los datos con los parametros elegidos de AreaConocimiento,PlanEstudios,CicloEscolar y ProgramaEducativo-----------------
                if (listaES1.getProfesor().getProrfc().equalsIgnoreCase(listaPR1.getProrfc()) 
                && PlanE.contains(listaES1.getGrupo().getPlanestudio().getPesvigenciaPlan()) 
                && CicloE.contains(listaES1.getUnidadaprendizaje().getCicloescolar().getCescicloEscolar()) 
                && ProgramaE.contains(listaES1.getGrupo().getPlanestudio().getProgramaeducativo().getPedid()) 
                && Uaprendizaje.contains(listaES1.getUnidadaprendizaje().getUapnombre())) {
                //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------        
                    String queryAreaCon="from Areaconocimiento as a join a.unidadaprendizajes as ua where ua.uapclave='"+listaES1.getUnidadaprendizaje().getUapclave()+"'";
                    ArrayList<Areaconocimiento> listaAreaCon = new ArrayList<Areaconocimiento>();
                    reportesDAO rp = new reportesDAO();
                    List<Object> listaObjetos=rp.executeQuery(queryAreaCon);
                    Iterator itr=listaObjetos.iterator();
                    while(itr.hasNext()){
                        Object[] obj=(Object[])itr.next();
                        listaAreaCon.add((Areaconocimiento)obj[0]);
                    }    
                    for (Areaconocimiento listaAreaCon1 : listaAreaCon) {
                        if(AreaC.contains(listaAreaCon1.getAconombre())){
                            esperados = esperados+3;
                        }
                    }
                }
            }
            if(profesor.contains(nombre)==false){
                Racts.add(nombre+"-"+Integer.toString(esperados));
            }
            profesor.add(nombre);
            esperados=0;
        }
        return Racts;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Metodo retorna Array con el nombre de profesor y una relacion de Racts esperados y entregados">
    public ArrayList<String> getRactsProfesor(){
        ArrayList<String> Completo = new ArrayList<String>();
        ArrayList<String> entregados = getEntregadosProfesor();
        ArrayList<String> esperados = getEsperadosProfesor();
        int tamano1 = entregados.size();
        int tamano2 = esperados.size();
        if(tamano1==tamano2){
            for(int x=0;x<tamano1;x++){
                String[] AuxEntregados = entregados.get(x).split("-");
                String[] AuxEsperados = esperados.get(x).split("-");
                if(AuxEntregados[0].equalsIgnoreCase(AuxEsperados[0])){
                    Completo.add(AuxEsperados[0]+"-"+AuxEntregados[1]+"-"+AuxEsperados[1]);
                }else{
                    System.out.println("ERROR, Valores de area de conocimiento no coinciden");
                }
            }
        }else{
            System.out.println("ERROR, Longitud de valores entregados y esperados no coinciden");
        }
        return Completo;
    }
    // </editor-fold>
    
    // </editor-fold>
    
    // <editor-fold desc="Semaforo" defaultstate="collapsed">
    public ArrayList<String> contarSemaforoProgEd(int ua){
        this.UnidadAcademica = ua;
        initCriteria(5);
        initCriteria(2);
        List<UnidadaprendizajeImparteProfesor> Reportes = criteriaAUX.list();
        List<Programaeducativo> Prog = criteriaAUX2.list();
        ArrayList<String> listaProgEd = new ArrayList<String>();
        for(Programaeducativo Prog1 : Prog){
            int TotalReportes = 0;
            int TotalEntregados = 0;
            double porcentaje = 0;
            String programa = Prog1.getPednombre();
            for (UnidadaprendizajeImparteProfesor Reporte : Reportes) {
                if(Reporte.getGrupo().getPlanestudio().getProgramaeducativo().getPednombre().equals(programa)){
                    TotalReportes = TotalReportes + 3;//esperados
                }
            }
            List<Reporteavancecontenidotematico> Entregados = criteriaRC.list();
            TotalEntregados = 0;
            for (Reporteavancecontenidotematico Entregado : Entregados) {
                if(Entregado.getRacstatus().equalsIgnoreCase("Enviado")&&
                   Entregado.getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getProgramaeducativo().getPednombre().equals(programa)){
                    TotalEntregados++;//entregados
                }
            }
            if(TotalReportes!=0){
                porcentaje = (TotalEntregados*100)/TotalReportes;
                listaProgEd.add(programa+"-"+porcentaje); 
            }
        }
        return listaProgEd;
    }
    
    public ArrayList<String> contarSemaforoProgEdValor(int ua){
        this.UnidadAcademica = ua;
        initCriteria(5);
        initCriteria(2);
        List<UnidadaprendizajeImparteProfesor> Reportes = criteriaAUX.list();
        List<Programaeducativo> Prog = criteriaAUX2.list();
        ArrayList<String> listaProgEd = new ArrayList<String>();
        for(Programaeducativo Prog1 : Prog){
            int TotalReportes = 0;
            int TotalEntregados = 0;
            double porcentaje = 0;
            String programa = Prog1.getPednombre();
            for (UnidadaprendizajeImparteProfesor Reporte : Reportes) {
                if(Reporte.getGrupo().getPlanestudio().getProgramaeducativo().getPednombre().equals(programa)){
                    TotalReportes = TotalReportes + 3;//esperados
                }
            }
            List<Reporteavancecontenidotematico> Entregados = criteriaRC.list();
            TotalEntregados = 0;
            int entRact1 = 0;
            int entRact2 = 0;
            int entRact3 = 0;
            for (Reporteavancecontenidotematico Entregado : Entregados) {
                if(Entregado.getRacstatus().equalsIgnoreCase("Enviado")&&
                   Entregado.getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getProgramaeducativo().getPednombre().equals(programa)){
                    TotalEntregados++;//entregados
                    //aqui modifique Jesus Ruelas
                    if(Entregado.getRacnumero().equalsIgnoreCase("1")){
                        entRact1++;
                    }
                    if(Entregado.getRacnumero().equalsIgnoreCase("2")){
                        entRact2++;
                    }
                    if(Entregado.getRacnumero().equalsIgnoreCase("3")){
                        entRact3++;
                    }
                    //aqui modifique Jesus Ruelas    
                }
            }
            if(TotalReportes!=0){
                //porcentaje = (TotalEntregados*100)/TotalReportes;
                //listaProgEd.add(programa+"-"+porcentaje); 
                listaProgEd.add(programa+"-"+TotalEntregados+"-"+TotalReportes+"-"+"1:"+entRact1+"-"+"2:"+entRact2+"-"+"3:"+entRact3); 
            }
        }
        return listaProgEd;
    }

    public double contarSemaforo(int ua){
        this.UnidadAcademica = ua;
        initCriteria(5);
        initCriteria(2);
        List<UnidadaprendizajeImparteProfesor> Reportes = criteriaAUX.list();
        int TotalReportes = 0;
        for (UnidadaprendizajeImparteProfesor Reporte : Reportes) {
                TotalReportes = TotalReportes + 3;
        }
        List<Reporteavancecontenidotematico> Entregados = criteriaRC.list();
        int TotalEntregados = 0;
        for (Reporteavancecontenidotematico Entregado : Entregados) {
            if(Entregado.getRacstatus().equalsIgnoreCase("Enviado")){
                TotalEntregados++;
            }
        } 
        double porcentaje = (TotalEntregados*100)/TotalReportes;
        return porcentaje;
    }
    
    public int contarProgEd(int ua){
        this.UnidadAcademica = ua;
        initCriteria(3);
        List<Programaeducativo> progEd = criteriaAUX.list();
        int longitud = progEd.size();
        return longitud;
    }
    // </editor-fold> 
    
    // </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="Main para pruebas">
    public static void main(String[] args) {
        ArrayList<String> Plan = new ArrayList<String>();
        ArrayList<Integer> Programa = new ArrayList<Integer>();
        ArrayList<String> Ciclo = new ArrayList<String>();
        //----------------------------------------------------------------------
        int UnidadA = 1;
        Plan.add("2009-2");
      //  Plan.add("2006-2");
      //  Plan.add("2008-2");
        Programa.add(14006);
        Programa.add(11012);
        Programa.add(14051);
        //Programa.add(2);
        Ciclo.add("2009-2");
        Ciclo.add("2009-2");
        //----------------------------------------------------------------------
        EsperadosDAO ob = new EsperadosDAO();
       // ob.obtenerFechas("2009-2")
        ob.initProgramaEducativo(UnidadA, Plan, Programa, Ciclo);
        ArrayList<String> mostrar = ob.getAtiempoProgramaEducativo("1", "", "");
        ArrayList<String> entregados = ob.getAtiempoProgramaEducativo("", "2", "");
        ArrayList<String> esperados = ob.getAtiempoProgramaEducativo("", "", "3");
        System.out.println("----------ProgramaEducativo-EntregadosenFecha-Esperados-----------");
        for (String mostrar1 : mostrar) {
            System.out.println(mostrar1);
        }
        System.out.println("-----------------En limite----------------------------");
        for (String entregado : entregados) {
            System.out.println(entregado);
        }
        System.out.println("-----------------Despues------------------------------");
        for (String esperado : esperados) {
            System.out.println(esperado);
        }
    }
    // </editor-fold> 
}   