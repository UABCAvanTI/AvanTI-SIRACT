/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.List;
import mx.avanti.siract.business.CampusDelegate;
import mx.avanti.siract.business.entity.Campus;
/**
 *
 * @author Manuel Papa
 */
public class CampusBeanHelper implements Serializable{
    private CampusDelegate campusDelegate;
    private Campus campus;
    private Campus selectedCampus;
    private List<Campus> listaFiltrada;
    private List<Campus> listaCampus;

    public List<Campus> getListaCampus() {
        return listaCampus;
    }

    public void setListaCampus(List<Campus> listaCampus) {
        this.listaCampus = listaCampus;
    }
    
    public CampusBeanHelper(){
        try{
            this.campusDelegate = new CampusDelegate();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        campus = new Campus();
        selectedCampus = new Campus();
    }
    
    public CampusDelegate getCampusDelegate() {
        return campusDelegate;
    }
    
    public Campus getCampus(){
        return campus;
    } 
     
    public void setCampus(Campus campus) {
        this.campus = campus;
    }
    
     public Campus getSelectedCampus() {
        return selectedCampus;
    }
     
     public void setSelectedCampus(Campus selectedCampus) {
        this.selectedCampus = selectedCampus;
    }

//    public List<Campus> getListaFiltrada() {
//        return listaFiltrada;
//    }

    public void setListaFiltrada(List<Campus> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

//    public void setCampusDelegate(CampusDelegate campusDelegate) {
//        this.campusDelegate = campusDelegate;
//    }
    
//    public Campus getCampus() {
//        Campus aux = new Campus();
//        aux = (Campus)getFlash().get("selectCampus");
//        try{
//            if(aux.getPueid() == null){
//                
//            } else{
//                campus = aux;
//            }
//        } catch(Exception ex){
//            System.out.println(""+ex);
//        }
//        return campus;
//    }
    
    public List<Campus> filtrado(String campo, String busqueda){  
        String CambioBus =busqueda.toLowerCase();
        String CambioObjNom="";

        listaFiltrada = campusDelegate.getListaCampus();
        
        if(busqueda.trim().length()>0){
            listaFiltrada.clear();
            for(Campus cam : campusDelegate.getListaCampus()){
                
                    CambioObjNom=cam.getCamnombre().toLowerCase();
                    CambioObjNom=CambioObjNom.replace("á","a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
                    if(CambioObjNom.startsWith(CambioBus)){
                        listaFiltrada.add(cam);
                    }}}
        return listaFiltrada;
    }
    
    public void seleccionarRegistro() {
        for (Campus cam : campusDelegate.getListaCampus()) {
            if (cam.getCamid().equals(selectedCampus.getCamid())) {
                campus = cam;
            }}}
}
