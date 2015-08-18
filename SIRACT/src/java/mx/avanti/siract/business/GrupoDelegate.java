/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.business;

import java.util.ArrayList;
import java.util.List;
import mx.avanti.siract.business.entity.Grupo;
import mx.avanti.siract.business.entity.Temaunidad;
import mx.avanti.siract.business.entity.Unidad;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Y
 */
public class GrupoDelegate {
    private List<Grupo> listaGrupo;
           Grupo resultado;

    
    public GrupoDelegate(){
        listaGrupo=new ArrayList<Grupo> ();
    }
    
     public Grupo buscarGrupo(int id){
        Grupo resultado = null;
        resultado = (Grupo)ServiceFacadeLocator.getFacadeGrupo().buscarGrupo(id);
        return resultado;
    }
    
    public List<Grupo> getListaGrupo() {
        listaGrupo = ServiceFacadeLocator.getFacadeGrupo().consultaGrupo();
        return listaGrupo;
    }

    public void setListaGrupo(List<Grupo> listaGrupo) {
        this.listaGrupo = listaGrupo;
    }

   public List<Grupo> buscarGrupos(String de, String campo, String criterio){
       List<Grupo> res;
       res=ServiceFacadeLocator.getFacadeGrupo().buscarGrupos(de, campo, criterio);
       return res;
   }
        public Grupo findGrupoById(int id){
        return ServiceFacadeLocator.getFacadeGrupo().getGrupo(id);
    }

    
    public void agregarGrupo(Grupo grupo){
        ServiceFacadeLocator.getFacadeGrupo().agregarGrupo(grupo);
    }
    
    public void eliminarGrupo(Grupo grupo){
        ServiceFacadeLocator.getFacadeGrupo().eliminarGrupo(grupo);
    }
    
        public List<Grupo> getGpoMismoPlan(int idplan) {
        return ServiceFacadeLocator.getFacadeGrupo().getGpoMismoPlan(idplan);
    }
    
    public List<Grupo> getGrupoAsignado(int idGrupo){
        return ServiceFacadeLocator.getFacadeGrupo().getGpoAsignado(idGrupo);
    }
}
