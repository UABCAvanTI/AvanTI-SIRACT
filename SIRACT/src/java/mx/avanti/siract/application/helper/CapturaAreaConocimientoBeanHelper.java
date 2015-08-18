/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.List;
import mx.avanti.siract.business.AreaConocimientoDelegate;
import mx.avanti.siract.business.PlanEstudioDelegate;
import mx.avanti.siract.business.ProgramaEducativoDelegate;
import mx.avanti.siract.business.UnidadAcademicaDelegate;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Programaeducativo;

/**
 *
 * @author balta
 */
public class CapturaAreaConocimientoBeanHelper implements Serializable{
    private AreaConocimientoDelegate areaConocimientoDelegate;
    private Areaconocimiento areaConocimiento;
    private Areaconocimiento selecAreaconocimiento;
    private Planestudio planestudio;
    private List<Planestudio> listaPlanEstudio;
    
    private UnidadAcademicaDelegate unidadacadelegate;
    
    private ProgramaEducativoDelegate programaeducativodelegate;    
    private Programaeducativo programaeducativo;
    private List<Programaeducativo> listaFiltradaPed;
    
    private PlanEstudioDelegate planEstudioDelegate;
    private List<Areaconocimiento> listaFiltrada;
    private List<Areaconocimiento> listaFiltrada2;
    private List<Areaconocimiento> listaFiltrada3;
    private List<Areaconocimiento> listaFiltrada4;    
    private List<Areaconocimiento> listaSeleccionAcon;
    private boolean blnArea;
    private boolean bandera;

    
    private String mensaje="";
    
    private List<String> listaFiltrada2S;
    
    public CapturaAreaConocimientoBeanHelper(){
        try{
            this.areaConocimientoDelegate = new AreaConocimientoDelegate();
            planEstudioDelegate= new PlanEstudioDelegate();
            programaeducativodelegate=new ProgramaEducativoDelegate();

        } catch(Exception ex){
            ex.printStackTrace();
        }
        areaConocimiento = new Areaconocimiento();
        selecAreaconocimiento = new Areaconocimiento();
        planestudio =new Planestudio();
        programaeducativo=new Programaeducativo();
    }

    public List<Planestudio> getListaPlanEstudio() {
        return listaPlanEstudio;
    }

    public void setListaPlanEstudio(List<Planestudio> listaPlanEstudio) {
        this.listaPlanEstudio = listaPlanEstudio;
    }
    
    public List<String> getListaFiltrada2S() {
        return listaFiltrada2S;
    }

    public void setListaFiltrada2S(List<String> listaFiltrada2S) {
        this.listaFiltrada2S = listaFiltrada2S;
    }
    
    public ProgramaEducativoDelegate getProgramaeducativodelegate() {
        return programaeducativodelegate;
    }

    public void setProgramaeducativodelegate(ProgramaEducativoDelegate programaeducativodelegate) {
        this.programaeducativodelegate = programaeducativodelegate;
    }
    
    public UnidadAcademicaDelegate getUnidadacadelegate() {
        return unidadacadelegate;
    }

    public void setUnidadacadelegate(UnidadAcademicaDelegate unidadacadelegate) {
        this.unidadacadelegate = unidadacadelegate;
    }

    public Programaeducativo getProgramaeducativo() {
        return programaeducativo;
    }

    public void setProgramaeducativo(Programaeducativo programaeducativo) {
        this.programaeducativo = programaeducativo;
    }

    public AreaConocimientoDelegate getAreaConocimientoDelegate() {
        areaConocimiento.setPlanestudio(planestudio);
        return areaConocimientoDelegate;
    }
    
    public List<Programaeducativo> getListaFiltradaPed() {
        return listaFiltradaPed;
    }

    public void setListaFiltradaPed(List<Programaeducativo> listaFiltradaPed) {
        this.listaFiltradaPed = listaFiltradaPed;
    }

    public List<Areaconocimiento> getListaFiltrada() {
        return listaFiltrada;
    }
    
    public void setListaFiltrada2(List<Areaconocimiento> listaFiltrada2) {
        this.listaFiltrada2 = listaFiltrada2;
    }

    public void setListaFiltrada(List<Areaconocimiento> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    public PlanEstudioDelegate getPlanEstudioDelegate() {
        return planEstudioDelegate;
    }

    public void setPlanEstudioDelegate(PlanEstudioDelegate planEstudioDelegate) {
        this.planEstudioDelegate = planEstudioDelegate;
    }

    public Planestudio getPlanestudio() {
        return planestudio;
    }

    public void setPlanestudio(Planestudio planestudio) {
        this.planestudio = planestudio;
    }


    public Areaconocimiento getAreaConocimiento() {
        return areaConocimiento;
    }

    public void setAreaConocimiento(Areaconocimiento areaconocimiento) {
        this.areaConocimiento = areaconocimiento;
    }

    public Areaconocimiento getSelecAreaconocimiento() {
        return selecAreaconocimiento;
    }
        
    public List<Areaconocimiento> getListaFiltrada3() {
        return listaFiltrada3;
    }

    public void setListaFiltrada3(List<Areaconocimiento> listaFiltrada3) {
        this.listaFiltrada3 = listaFiltrada3;
    }

    public List<Areaconocimiento> getListaFiltrada4() {
        return listaFiltrada4;
    }

    public void setListaFiltrada4(List<Areaconocimiento> listaFiltrada4) {
        this.listaFiltrada4 = listaFiltrada4;
    }
    
    public List<Areaconocimiento> getListaSeleccionAcon() {
        return listaSeleccionAcon;
    }

    public void setListaSeleccionAcon(List<Areaconocimiento> listaSeleccionAcon) {
        this.listaSeleccionAcon = listaSeleccionAcon;
    }

    public void setSelecAreaconocimiento(Areaconocimiento selecAreaconocimiento) {
        this.selecAreaconocimiento = selecAreaconocimiento;
    }
    
    public Planestudio getSelecPlanEstudio() {
        return planestudio;
    }

    public void setSelecPlanEstudio(Planestudio planestudio) {
        this.planestudio = planestudio;
    }
    public List<Areaconocimiento> filtrado(String campo, String busqueda){
        String Cambus = busqueda.toLowerCase();
        String Nomarecon="";
        
        listaFiltrada = areaConocimientoDelegate.getListaAreaConocimiento();
        for(Areaconocimiento area:listaFiltrada){
            area.setPlanestudio(planEstudioDelegate.findByPlanEstudioId(area.getPlanestudio().getPesid()));            
            area.getPlanestudio().setProgramaeducativo(programaeducativodelegate.findProgramaEducativoById(area.getPlanestudio().getProgramaeducativo().getPedid()));
        }                
        
        if(busqueda.trim().length()>0){
            listaFiltrada2.clear();
            
            for(Areaconocimiento area : listaFiltrada){
                area.setPlanestudio(planEstudioDelegate.findByPlanEstudioId(area.getPlanestudio().getPesid()));
                area.getPlanestudio().setProgramaeducativo(programaeducativodelegate.findProgramaEducativoById(area.getPlanestudio().getProgramaeducativo().getPedid()));
                Nomarecon=area.getAconombre().toLowerCase();
                if (Nomarecon.startsWith(Cambus)) {
                    listaFiltrada2.add(area);
                }                
            }            
        }
        else{
            listaFiltrada2=listaFiltrada;
        }
        
        System.out.println("SUPUUUUUUUUUUUU::::::"+programaeducativo.getPedid());
        listaFiltrada2=filtrado2(listaFiltrada2,programaeducativo.getPedid());
        
        return listaFiltrada2;
    }
    
    public List<Areaconocimiento> filtrado2(List<Areaconocimiento> list, int busqueda){
        //String Cambus = busqueda.toLowerCase();     
        List<Areaconocimiento> list2;
//        filtrarPlanPorPE();
//        String Nomarecon="";
//        if(busqueda>0){
//                        
//            for(Areaconocimiento area : list){                
//                area.setPlanestudio(planEstudioDelegate.findByPlanEstudioId(area.getPlanestudio().getPesid()));
//                area.getPlanestudio().setProgramaeducativo(programaeducativodelegate.findProgramaEducativoById(area.getPlanestudio().getProgramaeducativo().getPedid()));                
//                if (area.getPlanestudio().getProgramaeducativo().getPedid()==busqueda) {
//                        list2.add(area);
//                }           
//            }            
//        }
//        else{
            list2=list;
        //}
                
        return list2;
    }
           
    public void seleccionarRegistro() {
        for (Areaconocimiento area : areaConocimientoDelegate.getListaAreaConocimiento()) {
            if (area.getAcoid().equals(selecAreaconocimiento.getAcoid())) {
                areaConocimiento = area;                
            }
        }
    }
    public String validarRepetidos(){
        blnArea = true;
        mensaje = "";
        for(Areaconocimiento area : areaConocimientoDelegate.getListaAreaConocimiento()){
            //if(blnArea == true && area.getAcoid().equals(areaConocimiento.getAcoid()) || area.getAconombre().equalsIgnoreCase(areaConocimiento.getAconombre()) ){            
            if(blnArea=true && area.getAconombre().equalsIgnoreCase(areaConocimiento.getAconombre()) && !area.getPlanestudio().getPesvigenciaPlan().equalsIgnoreCase(areaConocimiento.getPlanestudio().getPesvigenciaPlan())){
                
            }
            else{
                mensaje = mensaje + " El nombre del Área de conocimiento No se puede repetir dentro del mismo plan de estudio";
                blnArea = false;
                break;
            }
        }        
        return mensaje;
    }
    
    public void filtrarPlanPorPE() {
        //mostrarListaPlan = "false";       
        System.out.println("PUFFF"+programaeducativo.getPedid());
        listaPlanEstudio = planEstudioDelegate.getPlanMismoPE(programaeducativo.getPedid());
    }

}