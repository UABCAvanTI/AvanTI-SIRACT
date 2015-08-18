package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.List;
import mx.avanti.siract.business.CampusDelegate;
import mx.avanti.siract.business.UnidadAcademicaDelegate;
import mx.avanti.siract.business.entity.Campus; 
import mx.avanti.siract.business.entity.Unidadacademica;

public class UnidadAcademicaBeanHelper implements Serializable{
    private UnidadAcademicaDelegate unidadAcademicaDelegate;
    private CampusDelegate campusDelegate;
    private Unidadacademica unidadacademica;
    private Unidadacademica selecUnidadAcademica;
    private Campus campus;
    private Campus campus2;
    private List<Unidadacademica> listaFiltrada;
    private List<Unidadacademica> listaFiltrada2;
    private List<Unidadacademica> listaFiltrada3;
    private List<Unidadacademica> listaSeleccionUA;
    private boolean banCiclo;
    private String Mensaje;
    boolean ban = true;
    private int idCampusFiltrado=0;

    public List<Unidadacademica> getListaFiltrada3() {
        return listaFiltrada3;
    }

    public void setListaFiltrada3(List<Unidadacademica> listaFiltrada3) {
        this.listaFiltrada3 = listaFiltrada3;
    }

    public List<Unidadacademica> getListaFiltrada2() {
        return listaFiltrada2;
    }

    public void setListaFiltrada2(List<Unidadacademica> listaFiltrada2) {
        this.listaFiltrada2 = listaFiltrada2;
    }

    public List<Unidadacademica> getListaSeleccionUA() {
        return listaSeleccionUA;
    }

    public void setListaSeleccionUA(List<Unidadacademica> listaSeleccionUA) {
        this.listaSeleccionUA = listaSeleccionUA;
    }

    public Campus getCampus2() {
        return campus2;
    }

    public void setCampus2(Campus campus2) {
        this.campus2 = campus2;
    }

    public CampusDelegate getCampusDelegate() {
        return campusDelegate;
    }

    public void setCampusDelegate(CampusDelegate campusDelegate) {
        this.campusDelegate = campusDelegate;
    }

    public UnidadAcademicaBeanHelper(){
        try{
            this.unidadAcademicaDelegate = new UnidadAcademicaDelegate();
            this.campusDelegate = new CampusDelegate();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        unidadacademica = new Unidadacademica();
        selecUnidadAcademica = new Unidadacademica();
        campus = new Campus();
        campus2 = new Campus();
//        campusDelegate.setListaCampus(null);
    }
    
    public UnidadAcademicaDelegate getUnidadAcademicaDelegate() {
        unidadacademica.setCampus(campus);
        return unidadAcademicaDelegate;
    }

    public List<Unidadacademica> getListaFiltrada() {
        return listaFiltrada;
    }
    
    public Unidadacademica getUnidadacademica() {
        return unidadacademica;
    }

    public void setUnidadacademica(Unidadacademica unidadacademica) {
        this.unidadacademica = unidadacademica;
    }
    
    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }
    
    public Unidadacademica getSelecUnidadAcademica() {
        return selecUnidadAcademica;
    }

    public void setSelecUnidadAcademica(Unidadacademica selecUnidadAcademica) {
        this.selecUnidadAcademica = selecUnidadAcademica;
    }

    public void setListaFiltrada(List<Unidadacademica> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public List<Unidadacademica> filtrado(String busqueda) {
        String CambioBus = busqueda.toLowerCase();
        String CambioObjNom = "";
        String campusFiltro="";
        
        if(idCampusFiltrado>0){
            listaFiltrada.clear();
            if(idCampusFiltrado==1){
                campusFiltro="mexicali";
            }else{
                if(idCampusFiltrado==2){
                    campusFiltro="tijuana";
                }else{
                    if(idCampusFiltrado==3){
                        campusFiltro="ensenada";
                    }
                }
            }
            listaFiltrada2 = unidadAcademicaDelegate.getListaUnidadAcademica();
            for(Unidadacademica uac : listaFiltrada2) {
            uac.setCampus(campusDelegate.findCampusById(uac.getCampus().getCamid()));
            }
            for (Unidadacademica ua : listaFiltrada2) {
                if(busqueda.trim().length()>0){                      
                    String CambioObjNum = Integer.toString(ua.getUacclave());
                    if (CambioObjNum.startsWith(busqueda)
                            && campusFiltro.equals(ua.getCampus().getCamnombre().toLowerCase())){
                        listaFiltrada.add(ua);
                    }else{
                            CambioObjNom = ua.getUacnombre().toLowerCase();
                            if(CambioObjNom.startsWith(busqueda)
                                    &&campusFiltro.equals(ua.getCampus().getCamnombre().toLowerCase())){
                                listaFiltrada.add(ua);
                            }
                    }
//                    if(campusFiltro.equals(ua.getCampus().getCamnombre().toLowerCase())
//                            ){
//                        listaFiltrada.add(ua);
//                    }
                }else{
                    if(campusFiltro.equals(ua.getCampus().getCamnombre().toLowerCase())){
                        listaFiltrada.add(ua);
                    }
                }
            }
//            listaFiltrada3=listaFiltrada;
//            System.out.println("tamaño lista tres: "+listaFiltrada3.size());
//            if (busqueda.trim().length() > 0) {
//                System.out.println("entro al if___");
//                listaFiltrada.clear();
//            System.out.println("tamaño lista tres: "+listaFiltrada3.size());
//            for (Unidadacademica ua : listaFiltrada3) {
//                 System.out.println("entro al for___");
//                String CambioObjNum = Integer.toString(ua.getUacclave());
//                System.out.println("clave: "+CambioObjNum+"parametro: "+busqueda);
//                if (CambioObjNum.startsWith(busqueda)) {
//                    System.out.println("entro if del for");
//                    listaFiltrada.add(ua);
//                }else{
//                        CambioObjNom = ua.getUacnombre().toLowerCase();
//                        if(CambioObjNom.startsWith(busqueda)){
//                            listaFiltrada.add(ua);
//                        }}
//            }
//            }else{
////                listaFiltrada = listaFiltrada;
//            }
        }else{
                
        listaFiltrada2 = unidadAcademicaDelegate.getListaUnidadAcademica();

        for(Unidadacademica uac : listaFiltrada2){
            uac.setCampus(campusDelegate.findCampusById(uac.getCampus().getCamid()));
            }
        
        if (busqueda.trim().length() > 0) {
            listaFiltrada.clear();
            
            for (Unidadacademica ua : listaFiltrada2){

                String CambioObjNum = Integer.toString(ua.getUacclave());
                if (CambioObjNum.startsWith(busqueda)){
                    listaFiltrada.add(ua);
                }else{
                        CambioObjNom = ua.getUacnombre().toLowerCase();
                        if(CambioObjNom.startsWith(busqueda)){
                            listaFiltrada.add(ua);
                        }
                }
            }
        }else{
            listaFiltrada = listaFiltrada2;
        }
        }
        return listaFiltrada;
    }
    
//    public List<Unidadacademica> filtrado(String busqueda) {
//        String CambioBus = busqueda.toLowerCase();
//        String CambioObjNom = "";
//        String campusFiltro="";
//        
//        if(idCampusFiltrado>0){
//            listaFiltrada.clear();
//            if(idCampusFiltrado==1){
//                campusFiltro="mexicali";
//            }else{
//                if(idCampusFiltrado==2){
//                    campusFiltro="tijuana";
//                }else{
//                    if(idCampusFiltrado==3){
//                        campusFiltro="ensenada";
//                    }}}
//            listaFiltrada2 = unidadAcademicaDelegate.getListaUnidadAcademica();
//            for(Unidadacademica uac : listaFiltrada2) {
//            uac.setCampus(campusDelegate.findCampusById(uac.getCampus().getCamid()));
//            }
//            for (Unidadacademica ua : listaFiltrada2) {
//                if(campusFiltro.equals(ua.getCampus().getCamnombre().toLowerCase())){
//                    listaFiltrada.add(ua);
//                }
//            }
//            listaFiltrada3=listaFiltrada;
//            System.out.println("tamaño lista tres: "+listaFiltrada3.size());
//            if (busqueda.trim().length() > 0) {
//                System.out.println("entro al if___");
//                listaFiltrada.clear();
//            for (Unidadacademica ua : listaFiltrada3) {
//                 System.out.println("entro al for___");
//                String CambioObjNum = Integer.toString(ua.getUacclave());
//                System.out.println("clave: "+CambioObjNum+"parametro: "+busqueda);
//                if (CambioObjNum.startsWith(busqueda)) {
//                    System.out.println("entro if del for");
//                    listaFiltrada.add(ua);
//                }else{
//                        CambioObjNom = ua.getUacnombre().toLowerCase();
//                        if(CambioObjNom.startsWith(busqueda)){
//                            listaFiltrada.add(ua);
//                        }}
//            }
//            }else{
////                listaFiltrada = listaFiltrada;
//            }
//        }else{
//                
//        listaFiltrada2 = unidadAcademicaDelegate.getListaUnidadAcademica();
//
//        for(Unidadacademica uac : listaFiltrada2){
//            uac.setCampus(campusDelegate.findCampusById(uac.getCampus().getCamid()));
//            }
//        
//        if (busqueda.trim().length() > 0) {
//            listaFiltrada.clear();
//            
//            for (Unidadacademica ua : listaFiltrada2){
//
//                String CambioObjNum = Integer.toString(ua.getUacclave());
//                if (CambioObjNum.startsWith(busqueda)){
//                    listaFiltrada.add(ua);
//                }else{
//                        CambioObjNom = ua.getUacnombre().toLowerCase();
//                        if(CambioObjNom.startsWith(busqueda)){
//                            listaFiltrada.add(ua);
//                        }}}
//        }else{
//            listaFiltrada = listaFiltrada2;
//        }
//        }
//        return listaFiltrada;
//    }
    
    public List<Unidadacademica> filtrado2(int busqueda) {
        String CambioObjNom = "";
        idCampusFiltrado=busqueda;
        listaFiltrada2 = unidadAcademicaDelegate.getListaUnidadAcademica();

        for (Unidadacademica uac : listaFiltrada2) {
            uac.setCampus(campusDelegate.findCampusById(uac.getCampus().getCamid()));
            }        
        if (busqueda > 0) {
            listaFiltrada.clear();
            
            for (Unidadacademica ua : listaFiltrada2) {
                    if(ua.getCampus().getCamid()==busqueda){
                        listaFiltrada.add(ua);
                    }
            }
        }else{
            listaFiltrada = listaFiltrada2;
        }
        return listaFiltrada;
    }
    
     public void seleccionarRegistro() {
        for (Unidadacademica ua : unidadAcademicaDelegate.getListaUnidadAcademica()) {
            if (ua.getUacid().equals(selecUnidadAcademica.getUacid())) {
                unidadacademica = ua;
                campus=campusDelegate.findCampusById(ua.getCampus().getCamid());
            }}}
    
    public boolean Validar(String ua2) {
        banCiclo = true;

        Mensaje = "";
        for (Unidadacademica uac : unidadAcademicaDelegate.getListaUnidadAcademica()) {
            String clave=Integer.toString(uac.getUacclave()); 
            if (clave.endsWith(ua2)) {
                ban = false;
                break;
            } else {
                ban = true;
            }}
        return ban;
    }
    
    public void eliminarDeLista(int id) {
        for (Unidadacademica uniac : listaSeleccionUA) {
            if (uniac.getUacid().equals(id)) {
                int index = listaSeleccionUA.indexOf(uniac);
                listaSeleccionUA.remove(index);
                break;
            }
        }
    }
}