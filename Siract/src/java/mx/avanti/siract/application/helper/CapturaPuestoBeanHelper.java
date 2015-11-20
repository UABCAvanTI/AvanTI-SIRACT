/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import static com.sun.faces.context.flash.ELFlash.getFlash;
import java.io.Serializable;
import java.util.List;
import mx.avanti.siract.business.PuestoDelegate;
//import mx.avanti.siract.business.entity.Puesto;

/**
 *
 * @author balta
 */

//Ya no hay captura de puesto


public class CapturaPuestoBeanHelper implements Serializable{
//    private PuestoDelegate puestoDelegate;
//    private Puesto puesto;
//    private Puesto selectedPuesto;
//    private List<Puesto> listaFiltrada;
//    private boolean banderaPuesto;
//    private String mensaje;
//
//    public boolean isBanderaPuesto() {
//        return banderaPuesto;
//    }
//
//    public void setBanderaPuesto(boolean banderaPuesto) {
//        this.banderaPuesto = banderaPuesto;
//    }
//
//    public List<Puesto> getListaFiltrada() {
//        return listaFiltrada;
//    }
//    private List<Puesto> listaSeleccionada;
//
//    public List<Puesto> getListaSeleccionada() {
//        return listaSeleccionada;
//    }
//
//    public void setListaSeleccionada(List<Puesto> listaSeleccionada) {
//        this.listaSeleccionada = listaSeleccionada;
//    }
//    
//    public CapturaPuestoBeanHelper(){
//        try{
//            this.puestoDelegate = new PuestoDelegate();
//        } catch(Exception ex){
//            ex.printStackTrace();
//        }
//        puesto = new Puesto();
//        selectedPuesto = new Puesto();
//    }
//    
//    public PuestoDelegate getPuestoDelegate() {
//        return puestoDelegate;
//    }
//
////    public void setPuestoDelegate(PuestoDelegate puestoDelegate) {
////        this.puestoDelegate = puestoDelegate;
////    }
//    
//    public Puesto getPuesto() {
//        return puesto;
//    }
//
//    public void setPuesto(Puesto puesto) {
//        this.puesto = puesto;
//    }
//
//    public Puesto getSelectedPuesto() {
//        return selectedPuesto;
//    }
//
//    public void setSelectedPuesto(Puesto selectedPuesto) {
//        this.selectedPuesto = selectedPuesto;
//    }
//    
//    public void setListaFiltrada(List<Puesto> listaFiltrada) {
//        this.listaFiltrada = listaFiltrada;
//    }
//    
//    
//     public List<Puesto> filtrado(String campo, String busqueda){  
//        String CambioBus =busqueda.toLowerCase();
//        String CambioObjNom="";
//
//        listaFiltrada = puestoDelegate.getListaPuesto();
//        
//        if(busqueda.trim().length()>0){
//            listaFiltrada.clear();
//            for(Puesto pues : puestoDelegate.getListaPuesto()){
//                
//                    CambioObjNom=pues.getPuepuesto().toLowerCase();
//                    CambioObjNom=CambioObjNom.replace("á","a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
//                    if(CambioObjNom.startsWith(CambioBus)){
//                        listaFiltrada.add(pues);
//                    }}}
//        return listaFiltrada;
//    }
//    
//    public void SeleccionarRegistro(){
//        for(Puesto pues : puestoDelegate.getListaPuesto()){
//            if(pues.getPueid().equals(selectedPuesto.getPueid())){
//                puesto = pues;                
//            }
//        }
//    }
//    
//    public boolean Validar(String Puesto) {
//       banderaPuesto = true;
//
//        mensaje = "";
//        for (Puesto puesto : puestoDelegate.getListaPuesto()) {
//            if (puesto.getPuepuesto().endsWith(Puesto)) {
//                banderaPuesto = false;
//                break;
//            } else {
//                banderaPuesto = true;
//            }
//            //Compara el campo de numero de empleado y al mismo tiempo una bandera para que 
//            //en caso de que encuentre un registro identico no lo vuelva a comparar(esto aplica en los 
//            //casos de que se repita un apellido o un nombre)
////            if (ciclo.getCescicloEscolar()== CicloEscolar && banCiclo == true) {
////
////                Mensaje = Mensaje + " [Numero de Empleado ]";
////                banCiclo = false;
////            }
//
//        }
//        return banderaPuesto;
//    }
//    
}
