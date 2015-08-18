/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import mx.avanti.siract.business.CicloEscolarDelegate;
import mx.avanti.siract.business.entity.Cicloescolar;

/**
 *
 * @author Manuel
 */
public class CapturaCicloEscolarBeanHelper implements Serializable {
    private CicloEscolarDelegate CicloescolarDelegate;
    private Cicloescolar cicloescolar;
    private Cicloescolar selecCiclo;
    private List<Cicloescolar> listaFiltrada;
    private List<Cicloescolar> listaCicloSeleccionada;
    private boolean blnciclo;
    private String mensaje;
    

//    private CicloEscolarDelegate cicloEscolarDelegate;
//    private Cicloescolar cicloescolar;
//    private Cicloescolar selCicloEscolar;
//    private List<Cicloescolar> listaFiltrada;
//    private List<Cicloescolar> ListaCicloSelect;
//
//    public List<Cicloescolar> getListaCicloSelect() {
//        return ListaCicloSelect;
//    }
//
//    public void setListaCicloSelect(List<Cicloescolar> ListaCicloSelect) {
//        this.ListaCicloSelect = ListaCicloSelect;
//    }
//    private boolean banCiclo;
//    private String Mensaje;
//    boolean ban = true;
//    boolean banFormato = true;
//
//    public CapturaCicloEscolarBeanHelper() {
//        try {
//            this.cicloEscolarDelegate = new CicloEscolarDelegate();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        cicloescolar = new Cicloescolar();
//        selCicloEscolar = new Cicloescolar();
//    }
//
//    public List<Cicloescolar> getlistaFiltrada() {
//        return listaFiltrada;
//    }
//
//    public void setlistaFiltrada(List<Cicloescolar> listaFiltrada) {
//        this.listaFiltrada = listaFiltrada;
//    }
//
//    public CicloEscolarDelegate getCicloEscolarDelegate() {
//        return cicloEscolarDelegate;
//    }
//
//    public void setCicloEscolarDelegate(CicloEscolarDelegate cicloEscolarDelegate) {
//        this.cicloEscolarDelegate = cicloEscolarDelegate;
//    }
//
//    public Cicloescolar getCicloescolar() {
//        return cicloescolar;
//    }
//
//    public void setCicloescolar(Cicloescolar cicloescolar) {
//        this.cicloescolar = cicloescolar;
//    }
//
//    public Cicloescolar getSelCicloEscolar() {
//        return selCicloEscolar;
//    }
//
//    public void setSelCicloEscolar(Cicloescolar selCicloEscolar) {
//        this.selCicloEscolar = selCicloEscolar;
//    }
//
//    public List<Cicloescolar> filtrado(String campo, String buscar) {
//        listaFiltrada = cicloEscolarDelegate.getListaCicloEscolar();
//
//        if (buscar.trim().length() > 0) {
//            listaFiltrada.clear();
//            for (Cicloescolar ciclo : listaFiltrada) {
//                String CambioObjNum = ciclo.getCescicloEscolar();
//                //String CambioObjNum1=ciclo.getCescicloEscolar();
//                if (CambioObjNum.startsWith(buscar)) {
//                    listaFiltrada.add(ciclo);
//                }
//            }
//        }
//        //Collections.sort(ListaCiclo,Collections.reverseOrder());
//        return listaFiltrada;
//    }
//
//    //Metodo para validar datos repetidos
//    public boolean Validar(String CicloEscolar) {
//        banCiclo = true;
//
//        Mensaje = "";
//        for (Cicloescolar ciclo : cicloEscolarDelegate.getListaCicloEscolar()) {
//            if (ciclo.getCescicloEscolar().endsWith(CicloEscolar)) {
//                ban = false;
//                break;
//            } else {
//                ban = true;
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
//        return ban;
//    }
//
//    public void seleccionarRegistro() {
//        for (Cicloescolar ciclo : cicloEscolarDelegate.getListaCicloEscolar()) {
//            if (ciclo.getCescicloEscolar().equals(selCicloEscolar.getCescicloEscolar())) {
//                cicloescolar = ciclo;
//
//            }
//        }
//    }
//        public void eliminarDeLista(int id){
//        for(Cicloescolar ciclo:ListaCicloSelect){
//            if(ciclo.getCescicloEscolar().equals(id)){
//                int index=ListaCicloSelect.indexOf(ciclo);
//                ListaCicloSelect.remove(index);
//                break;
//            }
//        }
////        for(Cicloescolar ciclo:ListaCicloSelect){
////            System.out.println(ciclo.getCescicloEscolar());
////        }
//        
//    }
////    public boolean formatoCiclo(){//Metodo para establecer si el dato ingresado contenga el formato correcto que lleva ciclo escolar
////        String cadena = cicloescolar.getCescicloEscolar();
////        boolean ban1 = true;
////
////        for(int x = 0; x<4;x++){
////           
////           if(cadena.charAt(x)=='-'){
////               ban1=true;
////               break; 
////           } else{
////               ban1=false;
////           }
////        }
////        if(cadena.charAt(4)=='-' && ban1!=true && cadena.charAt(5)!='-'){
////           banFormato=true; 
////        }
////        else{
////             banFormato=false; 
////        }
////        
////        
////        return banFormato;
////    }
//
    public CapturaCicloEscolarBeanHelper(){
        try{
            this.CicloescolarDelegate = new CicloEscolarDelegate();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        cicloescolar = new Cicloescolar();
        selecCiclo = new Cicloescolar();
    }
           /*metodo para la busqueda en tiempo real del Ciclo escolar escrito en el filtro*/
    public List<Cicloescolar> filtrado(String campo, String busqueda) {
//        listaFiltrada = CicloescolarDelegate.getListaCicloEscolar();
//
//        if (buscar.trim().length() > 0) {
//            listaFiltrada.clear();
//            for (Cicloescolar ciclo : listaFiltrada) {
//                String CambioObjCiclo = Integer.toString(ciclo.getCesid());
//               
//                //String CambioObjNum1=ciclo.getCescicloEscolar();
//                if (CambioObjCiclo.startsWith(buscar)) {
//                    listaFiltrada.add(ciclo);
//                }
//            }
//        }
//        //Collections.sort(ListaCiclo,Collections.reverseOrder());
//        return listaFiltrada;
         listaFiltrada = CicloescolarDelegate.getListaCicloEscolar();

        if (busqueda.trim().length() > 0) {
            listaFiltrada.clear();
            for (Cicloescolar ciclo : CicloescolarDelegate.getListaCicloEscolar()) {
                
                String CambioObjNum = ciclo.getCescicloEscolar();
                //String CambioObjNum1=ciclo.getCescicloEscolar();
                if (CambioObjNum.startsWith(busqueda)) {
                    listaFiltrada.add(ciclo);
                }
            }
        }
        //Collections.sort(ListaCiclo,Collections.reverseOrder());
        return listaFiltrada;
    }
    public void seleccionarRegistro(){
        for(Cicloescolar ciclo : CicloescolarDelegate.getListaCicloEscolar()){
            if(ciclo.getCesid().equals(selecCiclo.getCesid())){
                cicloescolar = ciclo;

            }
        }
    }
     public boolean validacionciclo() { 
         boolean ban=true;
         String string = cicloescolar.getCescicloEscolar();
         String[] parts = string.split("-");
         String part1 = parts[0]; 
         String part2 = parts[1];
         
         int parte1Int = Integer.parseInt(part1);
         int parte2Int = Integer.parseInt(part2);
        int year = Calendar.getInstance().get(Calendar.YEAR);   
        System.out.println("holis"+part1+year+" : "+part2);
        if(parte1Int>2000 && parte1Int<=year){
            if(parte2Int>0 && parte2Int<6 && parte2Int!=3){
                 ban=true;
            }else{
                ban=false;
            }
            
         }else{
             ban=false;
         }
        return ban;
     
     }
        public void eliminarDeLista(int id){
        for(Cicloescolar ciclo:listaCicloSeleccionada){
            if(ciclo.getCesid().equals(id)){
                int index = listaCicloSeleccionada.indexOf(ciclo);
                listaCicloSeleccionada.remove(index);
                break;
            }
        }
    }
public String validarRepetidos() {
        blnciclo = true;

        mensaje = "";
        for (Cicloescolar ciclo : CicloescolarDelegate.getListaCicloEscolar()) {
            if (ciclo.getCescicloEscolar().equals(cicloescolar.getCescicloEscolar())) {
                mensaje = mensaje + "[Ciclo Escolar]";
                blnciclo= false;
                break;
            } 
            //Compara el campo de numero de empleado y al mismo tiempo una bandera para que 
            //en caso de que encuentre un registro identico no lo vuelva a comparar(esto aplica en los 
            //casos de que se repita un apellido o un nombre)
//            if (ciclo.getCescicloEscolar()== CicloEscolar && banCiclo == true) {
//
//                Mensaje = Mensaje + " [Numero de Empleado ]";
//                banCiclo = false;
//            }

        }
        return mensaje;
    } 

    public CicloEscolarDelegate getCicloescolarDelegate() {
        return CicloescolarDelegate;
    }

    public void setCicloescolarDelegate(CicloEscolarDelegate CicloescolarDelegate) {
        this.CicloescolarDelegate = CicloescolarDelegate;
    }

    public Cicloescolar getCicloescolar() {
        return cicloescolar;
    }

    public void setCicloescolar(Cicloescolar cicloescolar) {
        this.cicloescolar = cicloescolar;
    }

    public Cicloescolar getSelecCiclo() {
        return selecCiclo;
    }

    public void setSelecCiclo(Cicloescolar selecCiclo) {
        this.selecCiclo = selecCiclo;
    }

    public List<Cicloescolar> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Cicloescolar> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public List<Cicloescolar> getListaCicloSeleccionada() {
        return listaCicloSeleccionada;
    }

    public void setListaCicloSeleccionada(List<Cicloescolar> listaCicloSeleccionada) {
        this.listaCicloSeleccionada = listaCicloSeleccionada;
    }

    public boolean isBlnciclo() {
        return blnciclo;
    }

    public void setBlnciclo(boolean blnciclo) {
        this.blnciclo = blnciclo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


}
