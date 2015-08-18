/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import mx.avanti.siract.business.AreaConocimientoDelegate;
import mx.avanti.siract.business.CatalogoCTDelegate;
import mx.avanti.siract.business.PracticasCampoDelegate;
import mx.avanti.siract.business.PracticasLaboratorioDelegate;
import mx.avanti.siract.business.PracticasTallerDelegate;
import mx.avanti.siract.business.ProfesorDelegate;
import mx.avanti.siract.business.ProgramaEducativoDelegate;
import mx.avanti.siract.business.SubtemaunidadDelegate;
import mx.avanti.siract.business.TemaunidadDelegate;
import mx.avanti.siract.business.UnidadAcademicaDelegate;
import mx.avanti.siract.business.UnidadAprendizajeDelegate;
import mx.avanti.siract.business.UnidadDelegate;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Practicalaboratorio;
import mx.avanti.siract.business.entity.Practicascampo;
import mx.avanti.siract.business.entity.Practicataller;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Subtemaunidad;
import mx.avanti.siract.business.entity.Temaunidad;
import mx.avanti.siract.business.entity.Unidad;
import mx.avanti.siract.business.entity.Unidadacademica;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Moises
 */
public class CatalogoCTBeanHelper implements Serializable {

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    private CatalogoCTDelegate catalogoCTDelegate;
    private UnidadDelegate unidadDelegate;
    private TemaunidadDelegate temaunidadDelegate;
    private SubtemaunidadDelegate subtemaunidadDelegate;
    private PracticasLaboratorioDelegate practicasLabDelegate;
    private PracticasTallerDelegate practicasTallerDelegate;
    private PracticasCampoDelegate practicasCampoDelegate;

    private List<Planestudio> planesestudio;

    //Objetos para poder obtener el PUESTO de: responsable de PE
    private ProfesorDelegate profesorDelegate;
    private List<Profesor> profesores;
    private Profesor profe;
//    private Responsableprogsramaeducativo responsablePE = null; !!!!!!!!!!!!!!!!!!!!!!!

    //Objeto para guardar la unidad de aprendizaje
    private Unidadaprendizaje unidadaprendizaje;

    //Objeto para guardar la unidad 
    private Unidad unidad;
    //Objeto para guardar la Temaunidad
    private Temaunidad temaunidad;

    //Atributos del árbol
    private TreeNode root;
//    TreeNode nivel1;
//    TreeNode nivel2;
//    TreeNode nivel3;

    List<Unidad> unidades;
    List<Practicalaboratorio> practicasLab;
    List<Practicataller> practicasTall;
    List<Practicascampo> practicasCampo;
    

    

    public CatalogoCTBeanHelper() {
        init();
    }

    private void init() {
        catalogoCTDelegate = new CatalogoCTDelegate();
        unidadDelegate = new UnidadDelegate();
        temaunidadDelegate = new TemaunidadDelegate();
        subtemaunidadDelegate = new SubtemaunidadDelegate();
        profesorDelegate = new ProfesorDelegate();
        profesores = profesorDelegate.getProfesores();
        profe = profesores.get(0);
        System.out.println("id profe: " + profe.getProid());
//        responsablePE = getResponsablePE();
        practicasLabDelegate = new PracticasLaboratorioDelegate();
        practicasTallerDelegate = new PracticasTallerDelegate();
        practicasCampoDelegate = new PracticasCampoDelegate();
        //Atributos del árbol
        root = new DefaultTreeNode("raiz", null);
    }

    public void setPlanesestudio(List<Planestudio> planesestudio) {
        this.planesestudio = planesestudio;

    }

    public void getResponsablePE() {
      //  responsablePE = catalogoCTDelegate.getResponsablePE(profe.getProid());
        //return responsablePE;
    }

    public List<Planestudio> getPlanesestudio() {
        //planesestudio = catalogoCTDelegate.getListaPlanesEstudio(responsablePE.getProgramaeducativo().getPedid());
        return planesestudio;
    }

    public Profesor getProfe() {
        return profe;
    }
    
        public Unidadaprendizaje getUnidadaprendizajeById(int id) {
        unidadaprendizaje = catalogoCTDelegate.getUnidadaprendizajeById(id);
        return unidadaprendizaje;
    }

    public Unidadaprendizaje getUnidadaprendizajeById(int id,String acoId) {
        unidadaprendizaje = catalogoCTDelegate.getUnidadaprendizajeById(id,acoId);
        return unidadaprendizaje;
    }

    public Unidad getUnidadById(int id) {
        unidad = catalogoCTDelegate.getUnidadById(id);
        return unidad;
    }

    public Temaunidad getTemaunidadById(int id) {
        temaunidad = catalogoCTDelegate.getTemaunidadById(id);
        return temaunidad;
    }

    public CatalogoCTDelegate getCatalogoCTDelegate() {
        return catalogoCTDelegate;
    }

    public List<Unidad> getUnidadesByUA(String idUA) {
        return unidadDelegate.consultaUnidades("unidadaprendizaje.uapid", idUA);
    }

    /*
     * ************************* Métodos para el árbol **********************************
     */
    
    
    public TreeNode getNodos(String seleccionado, String tipo) {
        System.out.println("TipoGetNodos" + tipo + seleccionado);
        if (tipo.trim().equalsIgnoreCase("C")) {
            System.out.println("Clase");
            return traerUnidades(seleccionado);
        } else {
            if (tipo.trim().equalsIgnoreCase("L")) {
                System.out.println("lab");

                return traerPracticasLaboratorio(seleccionado);
            } else {
                if (tipo.trim().equalsIgnoreCase("T")) {
                    System.out.println("Taller");

                    return traerPracticasTaller(seleccionado);
                }else{
                    if(tipo.trim().equalsIgnoreCase("PC")){
                        System.out.println("Practicas campo");
                        
                        return traerPracticasCampo(seleccionado);
                    }
                }
            }
        }
        return root;

    }

    public TreeNode traerUnidades(String idUA) {
        
        double hTotUni=0;
        double hTotTem=0;
        unidades = unidadDelegate.consultaUnidades("unidadaprendizaje.uapclave", idUA);
        TemaunidadDelegate temaunidaDelegate = new TemaunidadDelegate();
        SubtemaunidadDelegate subtemaunidadDelegate = new SubtemaunidadDelegate();
        TreeNode nivel1 = new DefaultTreeNode();
        TreeNode nivel2;
        TreeNode nivel3;
        List<Temaunidad> temasUnidad;
        List<Subtemaunidad> subtemasTemaUnidad;
        root = new DefaultTreeNode(new NodoMultiClass(null), null);
        for (Iterator<Unidad> it = unidades.iterator(); it.hasNext();) {
            Unidad unidadA = it.next();
            nivel1 = new DefaultTreeNode(new NodoMultiClass(unidadA), root);
            temasUnidad = temaunidaDelegate.getListaTemaunidadsFromWhere("unidad", "uniid", unidadA.getUniid().toString(), "tunnumero");            
            hTotUni= hTotUni + unidadA.getUnihoras();
            sethTotUni(hTotUni);
            for (Iterator<Temaunidad> it1 = temasUnidad.iterator(); it1.hasNext();) {
                Temaunidad temaunidadA = it1.next();
                nivel2 = new DefaultTreeNode(new NodoMultiClass(temaunidadA), nivel1);
                subtemasTemaUnidad = subtemaunidadDelegate.listaTemasUnidadDe("temaunidad", "tunid", temaunidadA.getTunid().toString(), "sutnumero");
                hTotTem = hTotTem + temaunidadA.getTunhoras();
                for (Iterator<Subtemaunidad> it2 = subtemasTemaUnidad.iterator(); it2.hasNext();) {
                    Subtemaunidad subtemaunidadA = it2.next();
                    nivel3 = new DefaultTreeNode(new NodoMultiClass(subtemaunidadA), nivel2);
                }
            }
        }
        return root;
    }
    //sumico inicio
    //se valida la suma de subtemas , se recibe el id del tema el id unidad y la clave de la unidad de aprendisaje
public double sumaHorasSubTUni(int idTema, int idUni , String idUA)
{
        double hTotSubTem=0;
        unidades = unidadDelegate.consultaUnidades("unidadaprendizaje.uapclave", idUA);
        TemaunidadDelegate temaunidaDelegate = new TemaunidadDelegate();
        SubtemaunidadDelegate subtemaunidadDelegate = new SubtemaunidadDelegate();
        TreeNode nivel1 = new DefaultTreeNode();
        TreeNode nivel2;
        TreeNode nivel3;
        List<Temaunidad> temasUnidad;
        List<Subtemaunidad> subtemasTemaUnidad;
       
        root = new DefaultTreeNode(new NodoMultiClass(null), null);
        for (Iterator<Unidad> it = unidades.iterator(); it.hasNext();) 
        {
            Unidad unidadA = it.next();
            nivel1 = new DefaultTreeNode(new NodoMultiClass(unidadA), root);
            temasUnidad = temaunidaDelegate.getListaTemaunidadsFromWhere("unidad", "uniid", unidadA.getUniid().toString(), "tunnumero");
            //sumico inicio
                   if(idUni == unidadA.getUniid())
                   {
                       for (Iterator<Temaunidad> it1 = temasUnidad.iterator(); it1.hasNext();) 
                       {
                           Temaunidad temaunidadA = it1.next();
                           nivel2 = new DefaultTreeNode(new NodoMultiClass(temaunidadA), nivel1);
                           subtemasTemaUnidad = subtemaunidadDelegate.listaTemasUnidadDe("temaunidad", "tunid", temaunidadA.getTunid().toString(), "sutnumero");
                           if(idTema == temaunidadA.getTunid())
                           {
                               for (Iterator<Subtemaunidad> it2 = subtemasTemaUnidad.iterator(); it2.hasNext();) 
                               {
                                   Subtemaunidad subtemaunidadA = it2.next();
                                   nivel3 = new DefaultTreeNode(new NodoMultiClass(subtemaunidadA), nivel2);
                                   hTotSubTem = hTotSubTem + subtemaunidadA.getSuthoras();
                    }
                }
            }
        }
    }
    return hTotSubTem;
}
    //se valida la suma de subtemas , se recibe el id del tema el id unidad y la clave de la unidad de aprendisaje
public double sumaHorasTemUni(int idUni , String idUA)
{
        double hTotTem=0;
        unidades = unidadDelegate.consultaUnidades("unidadaprendizaje.uapclave", idUA);
        TemaunidadDelegate temaunidaDelegate = new TemaunidadDelegate();
        SubtemaunidadDelegate subtemaunidadDelegate = new SubtemaunidadDelegate();
        TreeNode nivel1 = new DefaultTreeNode();
        TreeNode nivel2;
        TreeNode nivel3;
        List<Temaunidad> temasUnidad;
        List<Subtemaunidad> subtemasTemaUnidad;
       
        root = new DefaultTreeNode(new NodoMultiClass(null), null);
        for (Iterator<Unidad> it = unidades.iterator(); it.hasNext();) 
        {
            Unidad unidadA = it.next();
            nivel1 = new DefaultTreeNode(new NodoMultiClass(unidadA), root);
            temasUnidad = temaunidaDelegate.getListaTemaunidadsFromWhere("unidad", "uniid", unidadA.getUniid().toString(), "tunnumero");
            //sumico inicio
                   if(idUni == unidadA.getUniid())
                   {
                       for (Iterator<Temaunidad> it1 = temasUnidad.iterator(); it1.hasNext();) 
                       {
                           Temaunidad temaunidadA = it1.next();
                           nivel2 = new DefaultTreeNode(new NodoMultiClass(temaunidadA), nivel1);
                           subtemasTemaUnidad = subtemaunidadDelegate.listaTemasUnidadDe("temaunidad", "tunid", temaunidadA.getTunid().toString(), "sutnumero");
                            hTotTem = hTotTem + temaunidadA.getTunhoras();
                            sethTotTem(hTotTem);
                       }       
                   }  
        }
    return hTotSubTem;
}
    //sumico fin

    public TreeNode traerPracticasLaboratorio(String idUA) {
        double hTotPrac=0;
        practicasLab = practicasLabDelegate.consultarPracticas("unidadaprendizaje.uapclave", idUA);
        TreeNode nivel1 = new DefaultTreeNode();

        root = new DefaultTreeNode(new NodoMultiClass(null), null);

        for (Iterator<Practicalaboratorio> it = practicasLab.iterator(); it.hasNext();) {
            Practicalaboratorio practica = it.next();
            nivel1 = new DefaultTreeNode(new NodoMultiClass(practica), root);
            hTotPrac= hTotPrac + practica.getPrlhoras();
            sethTotPractL(hTotPrac);
        }
        return root;
    }

    /*public TreeNode traerPracticasTaller(String idUA) {
        practicasTall = practicasTallerDelegate.consultarPracticas("unidadaprendizaje.uapclave", idUA);

        TreeNode nivel1 = new DefaultTreeNode();

        root = new DefaultTreeNode(new NodoMultiClass(null), null);

        for (Iterator<Practicataller> it = practicasTall.iterator(); it.hasNext();) {
            Practicataller practica = it.next();
            nivel1 = new DefaultTreeNode(new NodoMultiClass(practica), root);
        }
        return root;
    }*/
    
    //Método sumico
    public TreeNode traerPracticasTaller(String idUA) {
        hTotPracT = 0.0;//se necesita para limpiar la variable
        practicasTall = practicasTallerDelegate.consultarPracticas("unidadaprendizaje.uapclave", idUA);

        TreeNode nivel1 = new DefaultTreeNode();

        root = new DefaultTreeNode(new NodoMultiClass(null), null);

        for (Iterator<Practicataller> it = practicasTall.iterator(); it.hasNext();) {
            Practicataller practica = it.next();
            nivel1 = new DefaultTreeNode(new NodoMultiClass(practica), root);
            hTotPracT = hTotPracT + practica.getPrthoras();
            sethTotPracT(hTotPracT);
        }
        return root;
    }
    
    private TreeNode traerPracticasCampo(String idUA) {
        practicasCampo = practicasCampoDelegate.consultarPracticas("unidadaprendizaje.uapclave", idUA);

        TreeNode nivel1 = new DefaultTreeNode();

        root = new DefaultTreeNode(new NodoMultiClass(null), null);

        for (Iterator<Practicascampo> it = practicasCampo.iterator(); it.hasNext();) {
            Practicascampo practica = it.next();
            nivel1 = new DefaultTreeNode(new NodoMultiClass(practica), root);
        }
        return root;
    }
    //Inicia metodo para la convercion de horas( Gibran)
public double convercion(String buscar){
        
        double datoConvertido=0;
        buscar=buscar.replace("_", "0");
        String[] parts = buscar.split(":");
        String part1 = parts[0]; 
        String part2 = parts[1];
        double parte1Int = Integer.parseInt(part1);
        double parte2Int = Integer.parseInt(part2);
        datoConvertido=(100*parte2Int)/60;
        datoConvertido=datoConvertido/100;
        datoConvertido= parte1Int+datoConvertido;
        System.out.println(datoConvertido);
        //horas.setPrthoras(datoConvertido);
        
        BigDecimal bigdatoConvertido = new BigDecimal(datoConvertido);
         bigdatoConvertido = bigdatoConvertido.setScale(2,RoundingMode.HALF_UP);
         datoConvertido = bigdatoConvertido.doubleValue();
        return datoConvertido;
    }//temrina  metodo
public boolean minMayor(String buscar){
        
        boolean ban=false;
        buscar=buscar.replace("_","0");
        String[] parts = buscar.split(":");
        String part1 = parts[0]; 
        String part2 = parts[1];
        int parte1Int = Integer.parseInt(part1);
        int parte2Int = Integer.parseInt(part2);
        if(parte2Int>59){
            ban=true;
        }else{
            ban=false;
        }

        //horas.setPrthoras(datoConvertido);
        return ban;
    }//temrina metodo

    public void setTemaunidad(Temaunidad temaunidad) {
        this.temaunidad = temaunidad;
    }

    public void setPracticasLab(List<Practicalaboratorio> practicasLab) {
        this.practicasLab = practicasLab;
    }

    public void setPracticasTall(List<Practicataller> practicasTall) {
        this.practicasTall = practicasTall;
    }

    public void setPracticasCampo(List<Practicascampo> practicasCampo) {
        this.practicasCampo = practicasCampo;
    }


    //Agregar unidades
    public void agregarUnidadCatalogo(Unidad nuevaUnidad) {
//        unidadDelegate.agregarUnidadCatalogo(numero, ua);
        unidadDelegate.agregarUnidad(nuevaUnidad);
    }

    public void agregarTemaCatalogo(Temaunidad temaunidad) {
//        temaunidadDelegate.agregarTemaunidadCatalogo(numTemas, uni);
        temaunidadDelegate.agregarTemaunidad(temaunidad);
    }

    public void agregarSubtemaCatalogo(Subtemaunidad subtema) {
//        subtemaunidadDelegate.agregarSubtemaunidadCatalogo(numSubtemas, tema);
        subtemaunidadDelegate.agregarSubtemaunidad(subtema);
    }
    
    public void agregarPracticaLaboratorioCatalogo(Practicalaboratorio practicaL){
//        practicasLabDelegate.agregarPracticaLabCatalogo(numPracticasLaboratorio, ua);
        practicasLabDelegate.agregarPracticaLab(practicaL);
    }
    
    public void agregarPracticaTallerCatalogo(Practicataller practicaT){
//        practicasTallerDelegate.agregarPracticaTallerCatalogo(numPracticasTaller, ua);
        practicasTallerDelegate.agregarPracticaTall(practicaT);
    }

    public void agregarPracticaCampoCatalogo(Practicascampo practicaC){
        practicasCampoDelegate.agregarPracticaCampo(practicaC);
    }
    
    //sumico inicio
    private double hTotPracT=0;
    private double hTotPractL=0; 
    private double hTotUni=0;
    private double hTotTem=0;
    private double hTotSubTem=0; 

    public double gethTotSubTem() {
        return hTotSubTem;
    }

    public void sethTotSubTem(double hTotSubTem) {
        this.hTotSubTem = hTotSubTem;
    }

    public double gethTotPractL() {
        return hTotPractL;
    }

    public void sethTotPractL(double hTotPractL) {
        this.hTotPractL = hTotPractL;
    }

    public double gethTotTem() {
        return hTotTem;
    }

    public void sethTotTem(double hTotTem) {
        this.hTotTem = hTotTem;
    }

    public double gethTotUni() {
        return hTotUni;
    }

    public void sethTotUni(double hTotUni) {
        this.hTotUni = hTotUni;
    }

    public double gethTotPracT() {
        return hTotPracT;
    }

    public void sethTotPracT(double hTotPracT) {
        this.hTotPracT = hTotPracT;
    }
    
    //sumico fin
    
    //Metodo para traer programaes educativos de la unidad academica a la que 
    //pertenece el director y/o administrador.
    UnidadAcademicaDelegate unidadAcademicaDelegate=new UnidadAcademicaDelegate();
    ProgramaEducativoDelegate programaEducativoDelegate=new ProgramaEducativoDelegate();
    
    
    public List<Programaeducativo> programasEducativosDeUsuario(int usuarioId){
        List<Unidadacademica> unidadesAcademicas=new ArrayList<Unidadacademica>();
        List<Programaeducativo> programasEducativos=new ArrayList<Programaeducativo>();
        Profesor profesorTemporal=profesorDelegate.findProfesorFromUser(usuarioId);
        unidadesAcademicas=unidadAcademicaDelegate.getProfUAC(profesorTemporal.getProid());
        for(Unidadacademica unidadAcademicaTemporal:unidadesAcademicas){
            List<Programaeducativo> aux=programaEducativoDelegate.getPEUAC(unidadAcademicaTemporal.getUacid());
            programasEducativos.addAll(aux);
        }
        return programasEducativos;
    }
    
     public List<Programaeducativo> programasEducativosDeAreaConocimiento(String idAco){
        List<Programaeducativo> programasEducativos=programaEducativoDelegate.getPEDeAreaConocimiento(idAco);
        return programasEducativos;
    }
    
     public List<Programaeducativo> programasEducativosDeUnidadAcademica(int uaId){
        List<Programaeducativo> programaeducativo=new ArrayList();        
        Profesor profesorTemporal=profesorDelegate.findProfesorFromUser(uaId);
        return programaeducativo;
    }
    
    
    public Programaeducativo programaEducativoDeResponsable(int usuarioId){
         List<Unidadacademica> unidadesAcademicas=new ArrayList<Unidadacademica>();
        Programaeducativo programaeducativo;        
        Profesor profesorTemporal=profesorDelegate.findProfesorFromUser(usuarioId);
       programaeducativo=programaEducativoDelegate.getPEdeResponsable(profesorTemporal.getProid());
        
        
        return programaeducativo;
    }
    
    //Metodos para actualizar la base de datos en casos de horas completas o no para
    //UnidadAprendizaje Unidad Temaunidad
    UnidadAprendizajeDelegate unidadAprendizajeDelegate=new UnidadAprendizajeDelegate();
    
    public void horasCompletasUnidadAprendizaje(Unidadaprendizaje unidadAprendizaje,Boolean horasCompletas){
         System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$CAMBIANDO HORAS COMPLETAS PARA UNIDADAPRENDIZAJE");
         Unidadaprendizaje uaAuxiliar= unidadAprendizajeDelegate.findUAById(unidadAprendizaje.getUapid());
                
             System.out.println("SE RECIBIO "+uaAuxiliar.getUaphorasLaboratorioCompletas());
          uaAuxiliar.setUaphorasClaseCompletas(unidadAprendizaje.getUaphorasClaseCompletas());
          uaAuxiliar.setUaphorasLaboratorioCompletas(unidadAprendizaje.getUaphorasLaboratorioCompletas());
          uaAuxiliar.setUaphorasTallerCompletas(unidadAprendizaje.getUaphorasTallerCompletas());
          uaAuxiliar.setUaphorasCampoCompletas(unidadAprendizaje.getUaphorasCampoCompletas());
    System.out.println("SE CAMBIO "+uaAuxiliar.getUaphorasLaboratorioCompletas());
                 // System.out.println("POR "+unidadAprendizaje.getUaphorasCompletas());
        unidadAprendizajeDelegate.agregarUnidadAprendizaje(uaAuxiliar);
    }
    
    public void horasCompletasUnidad(String idUnidad){
        Unidad unidad;
        unidad=unidadDelegate.findUnidad(idUnidad);
        unidad.setUnihorasCompletas(!unidad.getUnihorasCompletas());

        unidadDelegate.agregarUnidad(unidad);
        
    }
    
    public void horasCompletastTemaunidad(String idTemaunidad){
        Temaunidad temaunidad;
        temaunidad=temaunidadDelegate.findTemaunidad(idTemaunidad);
        temaunidad.setTunhorasCompletas(!temaunidad.getTunhorasCompletas());
        temaunidadDelegate.agregarTemaunidad(temaunidad);
    }
    
     public List<Planestudio> getPlanesestudio(int idPE) {
        planesestudio = catalogoCTDelegate.getListaPlanesEstudio(idPE);
        return planesestudio;
    }
     
        public Programaeducativo programadeUnidadAprandizaje(int idUA){
        Programaeducativo resultado=null;
        resultado=programaEducativoDelegate.getPEdeUnAp(idUA);
        return resultado;
     }
          public Areaconocimiento areaConocimientodeUnidadAprandizaje(String idUA){
        List<Areaconocimiento> resultado=null;
        AreaConocimientoDelegate areaconocimientoDelegate=new AreaConocimientoDelegate();
        resultado=areaconocimientoDelegate.getAreaByUnidad(String.valueOf(idUA));
        return resultado.get(0);
     }
          public List<Planestudio> getPlanesestudioByUnidadAcademica(int idUa) {
        planesestudio = catalogoCTDelegate.getListaPlanesEstudioByUnidadAcademica(idUa);
        return planesestudio;
    }
              public List<Planestudio> getPlanesestudioByUnidadAprendizaje(String idUA) {
        planesestudio = catalogoCTDelegate.getListaPlanesEstudioByUnidadAprendizaje(Integer.parseInt(idUA));
        return planesestudio;
    }
          
          
               public List<Planestudio> getPlanesestudioByAreaConocimiento(String idAco) {
        planesestudio = catalogoCTDelegate.getListaPlanesEstudioByAreaConocimiento(idAco);
        return planesestudio;
    }
    public List<Planestudio> getPlanesestudioByProgramaEducativo(String idPE) {
        planesestudio = catalogoCTDelegate.getListaPlanesEstudioByProgramaEducativo(idPE);
        return planesestudio;
    }
          public Planestudio getPlanDeUnidadAprendizaje(int idUa){
              List<Planestudio> plan=null;
              plan=catalogoCTDelegate.getListaPlanesEstudioByUnidadAprendizaje(idUa);
              return plan.get(0);
          }
          
     public void unidadesAprendijzajeFiltradas(String where){
         
     }
     
//Metodo que enviara mensaje si la unidad de aprendizaje se encuentra
        //en mas de un Programa educativo
    public List<Programaeducativo>  obtenerProgramasEducativosDeUA(int uaId){
        return programaEducativoDelegate.obtenerProgramasEducativosDeUA(uaId);
    }


}
