/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//22/02/2015
package mx.avanti.siract.business;

import mx.avanti.siract.application.helper.*;
import java.io.Serializable;
import java.util.List;
import mx.avanti.siract.business.GrupoDelegate;
import mx.avanti.siract.business.PlanEstudioDelegate;
import mx.avanti.siract.business.entity.Grupo;
import mx.avanti.siract.business.entity.Planestudio;

/**
 *
 * @author Gig@B COMPUTADORAS
 */
public class GrupoBeanHelper implements Serializable{
    private GrupoDelegate grupoDelegate;
    private Grupo grupo;
    private Grupo selecGrupo;
    private Planestudio planestudio;
    private List<Grupo> listaFiltrada;
    private List<Grupo> listaGpoSeleccionada;
    private PlanEstudioDelegate planEstudioDelegate;
    private boolean blnGrupo;
    private String mensaje;
    
    public GrupoBeanHelper(){
        try{
            this.grupoDelegate = new GrupoDelegate();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        grupo = new Grupo();
        selecGrupo = new Grupo();
        planestudio = new Planestudio();
        planEstudioDelegate = new PlanEstudioDelegate();
    }

    public GrupoDelegate getGrupoDelegate() {
        grupo.setPlanestudio(planestudio);
        return grupoDelegate;
    }   
    
    /*metodo para la busqueda en tiempo real del grupo escrito en el filtro*/
    public List<Grupo> filtrado(String campo, String busqueda){
        //int cambioObjGrupo;
        
        listaFiltrada = grupoDelegate.getListaGrupo();
        for(Grupo gpo:listaFiltrada){
            gpo.setPlanestudio(planEstudioDelegate.findByPlanEstudioId(gpo.getPlanestudio().getPesid()));
        }        
        
        if(busqueda.trim().length()>0){
            listaFiltrada.clear();
            for(Grupo gpo : grupoDelegate.getListaGrupo()){
                gpo.setPlanestudio(planEstudioDelegate.findByPlanEstudioId(gpo.getPlanestudio().getPesid()));

                String cambioObjGrupo = Integer.toString(gpo.getGponumero());
                if(cambioObjGrupo.startsWith(busqueda))
                    listaFiltrada.add(gpo);
            }
        }
        
        return listaFiltrada;
    }
    
    public void seleccionarRegistro(){
        for(Grupo gpo : grupoDelegate.getListaGrupo()){
            if(gpo.getGpoid().equals(selecGrupo.getGpoid())){
                grupo = gpo;                
                planestudio = planEstudioDelegate.findByPlanEstudioId(gpo.getPlanestudio().getPesid());
            }
        }
    }
    
    public void eliminarDeLista(int id){
        for(Grupo gpo:listaGpoSeleccionada){
            if(gpo.getGpoid().equals(id)){
                int index = listaGpoSeleccionada.indexOf(gpo);
                listaGpoSeleccionada.remove(index);
                break;
            }
        }
    }
    
    public String validarRepetidos(){
        blnGrupo = true;
        mensaje = "";
        for(Grupo gpo : grupoDelegate.getListaGrupo()){
            if(gpo.getGponumero() == grupo.getGponumero() && blnGrupo == true
                    && !gpo.getGpoid().equals(grupo.getGpoid())){
                mensaje = mensaje + "[Grupo]";
                blnGrupo = false;
            }
        }
        return mensaje;
    }

//    public void setGrupoDelegate(GrupoDelegate grupoDelegate) {
//        this.grupoDelegate = grupoDelegate;
//    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Grupo getSelecGrupo() {
        return selecGrupo;
    }

    public void setSelecGrupo(Grupo selecGrupo) {
        this.selecGrupo = selecGrupo;
    }

    public Planestudio getPlanestudio() {
        return planestudio;
    }

    public void setPlanestudio(Planestudio planestudio) {
        this.planestudio = planestudio;
    }

    public void setListaFiltrada(List<Grupo> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public List<Grupo> getListaGpoSeleccionada() {
        return listaGpoSeleccionada;
    }

    public void setListaGpoSeleccionada(List<Grupo> listaGpoSeleccionada) {
        this.listaGpoSeleccionada = listaGpoSeleccionada;
    }     

    public PlanEstudioDelegate getPlanEstudioDelegate() {
        return planEstudioDelegate;
    }

    public void setPlanEstudioDelegate(PlanEstudioDelegate planEstudioDelegate) {
        this.planEstudioDelegate = planEstudioDelegate;
    }
    
    
}
