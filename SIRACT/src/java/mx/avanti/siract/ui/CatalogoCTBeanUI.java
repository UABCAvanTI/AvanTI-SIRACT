/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.ui;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.avanti.siract.application.helper.CatalogoCTBeanHelper;
import mx.avanti.siract.application.helper.FiltrosBeanHelper;
import mx.avanti.siract.application.helper.NodoMultiClass;
import mx.avanti.siract.business.CTDelegate;
import mx.avanti.siract.business.ConsultaDelegate;
import mx.avanti.siract.business.UnidadAprendizajeDelegate;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Practicalaboratorio;
import mx.avanti.siract.business.entity.Practicascampo;
import mx.avanti.siract.business.entity.Practicataller;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Subtemaunidad;
import mx.avanti.siract.business.entity.Temaunidad;
import mx.avanti.siract.business.entity.Unidad;
import mx.avanti.siract.business.entity.Unidadacademica;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import org.eclipse.jdt.internal.compiler.lookup.TypeIds;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Moises
 */
@ManagedBean
@ViewScoped
public class CatalogoCTBeanUI implements Serializable {

    CTDelegate ctDelegate=new CTDelegate();
    private CatalogoCTBeanHelper catalogoCTBeanHelper;
    private FiltrosBeanHelper filtrosHelper;

    //Objetos a seleccionar
    private Planestudio selectedPlanestudio;
    private Areaconocimiento selectedAreaconocimiento;
    private int selectedEtapa = 0;
    private Unidadaprendizaje selectedUnidadaprendizaje;
    private String buscar = "00:00";

    //Objetos para horas
    private int hc = 0;
    private int ht = 0;
    private int hl = 0;
    private int calculoHoras = 0;

    //Listas a desplegar
    private List<Planestudio> planesestudio;

    //Objetos para id's
    private String seleccionarUA = "algo";
    private String seleccionarUnidad = "algo";
    private String seleccionarTemaunidad = "algo";

    //Propiedades para habilitar componentes
    private Boolean deshabilitarArea = true;
    private Boolean deshabilitarEtapa = true;
    private Boolean deshabilitarUA = true;

    /**
     * ***** Atributos del árbol ********
     */
    private TreeNode root;
    private TreeNode selectedNode;
    private String tipo = "algo";
    private String tipoG = "algo";
    private String clave = "algo";

    //Atributos del objeto para actualizar
    String numero = "";
    String nombre = "";
    float horas = (float) 0.0;
    float PA = (float) 0.0;

    //Listas de dialogos
    private List<Unidad> unidades;

    //Objetos de los dialogos
    private int numUnidades = 0;
    private int numTemas = 0;
    private int numSubtemas = 0;
    private int numPracticaslaboratorio = 0;
    private int numPracticastaller = 0;

    //*************** Objetos para dialogo agregar *****************//
    private Unidad selectedUnidad;
    private Unidad selectedUnidad2;
    private Temaunidad selectedTemaunidad;

    private Unidad nuevaUnidad;
    private Temaunidad nuevoTemaunidad;
    private Subtemaunidad nuevoSubtemaunidad;
    private Practicalaboratorio nuevaPracticaL;
    private Practicataller nuevaPracticaT;
    private Practicascampo nuevaPracticaC;

    private boolean deshabilitarPlan = false;
    
    
    private boolean isCoordinadorAreaAdmin=false;
    
    //Propiedad utilizada para acceder a los datos de LoginBean_2
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    //Metodo constructor
    public CatalogoCTBeanUI() {
        catalogoCTBeanHelper = new CatalogoCTBeanHelper();
        filtrosHelper = new FiltrosBeanHelper();

        selectedPlanestudio = new Planestudio();
        selectedPlanestudio.setPesid(0);
        selectedAreaconocimiento = new Areaconocimiento();
        selectedAreaconocimiento.setAcoid(0);
        selectedUnidadaprendizaje = new Unidadaprendizaje();
        selectedUnidadaprendizaje.setUapid(0);

        //Objetos de dialogo agregar
        selectedUnidad = new Unidad();
        selectedUnidad.setUniid(0);

        selectedUnidad2 = new Unidad();
        selectedUnidad2.setUniid(0);

        selectedTemaunidad = new Temaunidad();
        selectedTemaunidad.setTunid(0);

        //Atributos del arbol
        root = new DefaultTreeNode();

        //Objetos para agregar
        nuevaUnidad = new Unidad();
        nuevoTemaunidad = new Temaunidad();
        nuevoSubtemaunidad = new Subtemaunidad();
        nuevaPracticaL = new Practicalaboratorio();
        nuevaPracticaT = new Practicataller();
        nuevaPracticaC = new Practicascampo();
    }

    /////Parte encargada del filtro de programas educativos
    //Variable que decide si se presenta o no el filtro Programa Educativo
    boolean isAdmin = false;
    String programaESeleccionado = "0";//Variable encargada de guardar id de programa educativo seleccionado

    public String getProgramaESeleccionado() {
        return programaESeleccionado;
    }

    public void setProgramaESeleccionado(String programaESeleccionado) {
        this.programaESeleccionado = programaESeleccionado;
    }

    List<Programaeducativo> programasEducativos = new ArrayList<Programaeducativo>();

    public boolean isFiltroPE() {
        return isAdmin;
    }

    public void setFiltroPE(boolean filtroPE) {
        this.isAdmin = filtroPE;
    }

    public List<Programaeducativo> getProgramasEducativos() {
        return programasEducativos;
    }

    public void setProgramasEducativos(List<Programaeducativo> programasEducativos) {
        this.programasEducativos = programasEducativos;
    }

    
    //Variable que guarda el Id del profesor del usuario en Sesion
    int usuId=0;
    
    //Post constructor necesario para usar ManagedProperty
    @PostConstruct
    public void postConstructor() {

        if (loginBean == null && loginBean.getLogueado()!= null) {
        } else {
            usuId=loginBean.getLogueado().getUsuid();
            String rolSeleccionado = loginBean.getSeleccionado();

            //Roles que presentaran seleccion de Programa educativo
            String[] rolesAdministradores = {"Director", "Subdirector", "Administrador"};
            for (String stringTmp : rolesAdministradores) {
                if (rolSeleccionado.equals(stringTmp)) {
                    isAdmin = true;
                }
            }
            if (isAdmin) {

                programasEducativos = catalogoCTBeanHelper.programasEducativosDeUsuario(loginBean.getLogueado().getUsuid());
            }
            if (rolSeleccionado.equals("Responsable de Programa Educativo")) {
                Programaeducativo programaeducativoTemporal = catalogoCTBeanHelper.programaEducativoDeResponsable(loginBean.getLogueado().getUsuid());
                if(programaeducativoTemporal==null){
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "No ha sido asignado como responsable de algun Programa educativo <br/></center>");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                }else{
                programaESeleccionado = programaeducativoTemporal.getPedid().toString();
                programasEducativos.add(programaeducativoTemporal);
                }
                deshabilitarComponentes();
            }
            
            if (rolSeleccionado.equalsIgnoreCase("Coordinador de Área de Conocimiento")) {
                isCoordinadorAreaAdmin=true;
                List<Programaeducativo> programaeducativoTemporal = catalogoCTBeanHelper.programaEducativoDeCoordinadorAreaAdmin(loginBean.getLogueado().getUsuid());
                if(programaeducativoTemporal==null){
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "No ha sido asignado como Coordinador de un area administrativa<br/></center>");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                }else{
                 programasEducativos = catalogoCTBeanHelper.programaEducativoDeCoordinadorAreaAdmin(usuId);
                programasEducativos=programaeducativoTemporal;
                programaESeleccionado = programaeducativoTemporal.get(0).getPedid().toString();
                }
                deshabilitarComponentes();
            }

            
            

        }
        areasConocimiento = getAreasByPlan();
        unidadesAprendizaje=getUnidadByArea();
        planesestudio=obtenerPlanesEstudio();

    }

    ////Fin de filtro programas educativos
    //Getters y Setters
    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public CatalogoCTBeanHelper getCatalogoCTBeanHelper() {
        return catalogoCTBeanHelper;
    }

    public Planestudio getSelectedPlanestudio() {
        return selectedPlanestudio;
    }

    public void setSelectedPlanestudio(Planestudio selectedPlanestudio) {
        this.selectedPlanestudio = selectedPlanestudio;
    }

    public Areaconocimiento getSelectedAreaconocimiento() {
        return selectedAreaconocimiento;
    }

    public void setSelectedAreaconocimiento(Areaconocimiento selectedAreaconocimiento) {
        this.selectedAreaconocimiento = selectedAreaconocimiento;
    }

    public int getSelectedEtapa() {
        return selectedEtapa;
    }

    public void setSelectedEtapa(int SelectedEtapa) {
        this.selectedEtapa = SelectedEtapa;
    }

    public Unidadaprendizaje getSelectedUnidadaprendizaje() {
        return selectedUnidadaprendizaje;
    }

    public void setSelectedUnidadaprendizaje(Unidadaprendizaje selectedUnidadaprendizaje) {
        this.selectedUnidadaprendizaje = selectedUnidadaprendizaje;
    }

    public FiltrosBeanHelper getFiltros() {
        return filtrosHelper;
    }

    public String getSeleccionarUA() {
        return seleccionarUA;
    }

    public void setSeleccionarUA(String seleccionarUA) {
        if (!seleccionarUA.equalsIgnoreCase("algo") && !seleccionarUA.equalsIgnoreCase("0")) {
            String[] valores = seleccionarUA.split(" -- ");
            selectedUnidadaprendizaje = getUnidadaprendizaje(Integer.parseInt(valores[0]));
            System.out.println("Valores: " + valores[0]);
            this.clave = valores[0];
            this.tipoG = valores[2];
        } else {
            selectedUnidadaprendizaje = new Unidadaprendizaje();
            selectedUnidadaprendizaje.setUapid(0);
            selectedUnidadaprendizaje.setUapclave(0);
            this.clave = "algo";
            this.tipoG = "algo";
        }

        if (selectedUnidadaprendizaje == null || selectedUnidadaprendizaje.getUapid() == 0) {
//            selectedUnidadaprendizaje = new Unidadaprendizaje();
//            selectedUnidadaprendizaje.setUaphorasClase(0);
//            selectedUnidadaprendizaje.setUaphorasTaller(0);
//            selectedUnidadaprendizaje.setUaphorasLaboratorio(0);
            hc = 0;
            ht = 0;
            hl = 0;
            calculoHoras = 0;

        } else {
            hc = selectedUnidadaprendizaje.getUaphorasClase() * 16;
            ht = selectedUnidadaprendizaje.getUaphorasTaller() * 16;
            hl = selectedUnidadaprendizaje.getUaphorasLaboratorio() * 16;
            calculoHoras = (hc + ht + hl);

        }
        this.seleccionarUA = seleccionarUA;
    }

    public String getSeleccionarUnidad() {
        return seleccionarUnidad;
    }

    public void setSeleccionarUnidad(String seleccionarUnidad) {
        selectedUnidad = getUnidad(Integer.parseInt(seleccionarUnidad));
        if (selectedUnidad == null || selectedUnidad.getUniid() == 0) {
        } else {
        }
        this.seleccionarUnidad = seleccionarUnidad;
    }

    public String getSeleccionarTemaunidad() {
        return seleccionarTemaunidad;
    }

    public void setSeleccionarTemaunidad(String seleccionarTemaunidad) {
        selectedTemaunidad = getTemaunidad(Integer.parseInt(seleccionarTemaunidad));
        if (selectedTemaunidad == null || selectedTemaunidad.getTunid() == 0) {
        }
        this.seleccionarTemaunidad = seleccionarTemaunidad;
    }

    public int getHc() {
        return hc;
    }

    public int getHt() {
        return ht;
    }

    public int getHl() {
        return hl;
    }

    public int getCalculoHoras() {
        return calculoHoras;
    }

    public Boolean getDeshabilitarArea() {
        return deshabilitarArea;
    }

    public Boolean getDeshabilitarEtapa() {
        return deshabilitarEtapa;
    }

    public Boolean getDeshabilitarUA() {
        return deshabilitarUA;
    }

    public int getNumUnidades() {
        return numUnidades;
    }

    public void setNumUnidades(int numUnidades) {
        this.numUnidades = numUnidades;
    }

    public int getNumTemas() {
        return numTemas;
    }

    public void setNumTemas(int numTemas) {
        this.numTemas = numTemas;
    }

    public int getNumSubtemas() {
        return numSubtemas;
    }

    public void setNumSubtemas(int numSubtemas) {
        this.numSubtemas = numSubtemas;
    }

    public int getNumPracticaslaboratorio() {
        return numPracticaslaboratorio;
    }

    public void setNumPracticaslaboratorio(int numPracticaslaboratorio) {
        this.numPracticaslaboratorio = numPracticaslaboratorio;
    }

    public int getNumPracticastaller() {
        return numPracticastaller;
    }

    public void setNumPracticastaller(int numPracticastaller) {
        this.numPracticastaller = numPracticastaller;
    }

    public Unidad getSelectedUnidad() {
        return selectedUnidad;
    }

    public void setSelectedUnidad(Unidad unidad) {
        this.selectedUnidad = unidad;
    }

    public Unidad getSelectedUnidad2() {
        return selectedUnidad2;
    }

    public void setSelectedUnidad2(Unidad selectedUnidad2) {
        this.selectedUnidad2 = selectedUnidad2;
    }

    public Temaunidad getSelectedTemaunidad() {
        return selectedTemaunidad;
    }

    public void setSelectedTemaunidad(Temaunidad selectedTemaunidad) {
        this.selectedTemaunidad = selectedTemaunidad;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public String getTipo() {
        return tipo;
    }

    public void setUnidad(Unidad nuevaUnidad) {
        this.nuevaUnidad = nuevaUnidad;
    }

    public Unidad getNuevaUnidad() {

        return nuevaUnidad;

    }

    public Temaunidad getNuevoTemaunidad() {
        return nuevoTemaunidad;
    }

    public void setNuevoTemaunidad(Temaunidad nuevoTemaunidad) {
        this.nuevoTemaunidad = nuevoTemaunidad;
    }

    public Subtemaunidad getNuevoSubtemaunidad() {
        return nuevoSubtemaunidad;
    }

    public void setNuevoSubtemaunidad(Subtemaunidad nuevoSubtemaunidad) {
        this.nuevoSubtemaunidad = nuevoSubtemaunidad;
    }

    public Practicalaboratorio getNuevaPracticaL() {
        return nuevaPracticaL;
    }

    public void setNuevaPracticaL(Practicalaboratorio nuevaPracticaL) {
        this.nuevaPracticaL = nuevaPracticaL;
    }

    public Practicataller getNuevaPracticaT() {
        return nuevaPracticaT;
    }

    public void setNuevaPracticaT(Practicataller nuevaPracticaT) {
        this.nuevaPracticaT = nuevaPracticaT;
    }

    public Practicascampo getNuevaPracticaC() {
        return nuevaPracticaC;
    }

    public void setNuevaPracticaC(Practicascampo nuevaPracticaC) {
        this.nuevaPracticaC = nuevaPracticaC;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getHoras() {
        return horas;
    }

    public void setHoras(float horas) {
        this.horas = horas;
    }

    public float getPA() {
        return PA;
    }

    public void setPA(float PA) {
        this.PA = PA;
    }

    public List<Planestudio> obtenerPlanesEstudio(){
    if (isAdmin) {
            if (!seleccionarUA.equals("0")&&!seleccionarUA.equals("algo")) {
                planesestudio = catalogoCTBeanHelper.getPlanesestudioByUnidadAprendizaje(clave);
                if (planesestudio != null && planesestudio.size() >= 1) {
                    System.out.println("++++++++++++++++++++++++++PLANES DE ESTUDIO " + planesestudio.get(0).getPesid());
                    selectedPlanestudio.setPesid(planesestudio.get(0).getPesid());
                }
                return planesestudio;
            }
            if (!programaESeleccionado.equals("0")) {
                
                return catalogoCTBeanHelper.getPlanesestudio(Integer.parseInt(programaESeleccionado));
            }
            Unidadacademica aux = ctDelegate.findUnidadAcademica(loginBean.getLogueado().getUsuid()).get(0);
            return catalogoCTBeanHelper.getPlanesestudioByUnidadAcademica(aux.getUacid());
        }
        //ANTES
//        planesestudio = catalogoCTBeanHelper.getPlanesestudio();
//        return planesestudio;
        //AHORA
        if (!programaESeleccionado.equals("0")) {
            return catalogoCTBeanHelper.getPlanesestudio(Integer.parseInt(programaESeleccionado));
        } else {
            return new ArrayList<Planestudio>();
        }
    }
    //***** Getters de listas *****
    public List<Planestudio> getPlanesestudio() {
        return planesestudio;
    }

    private List<Areaconocimiento> areasConocimiento = new ArrayList();

    public List<Areaconocimiento> getAreasConocimiento() {
        return areasConocimiento;
    }

    public void setAreasConocimiento(List<Areaconocimiento> areasConocimiento) {
        this.areasConocimiento = areasConocimiento;
    }

    //Ahora tambien tomara en cuenta para la consulta el Programa Educativo seleccionado
    public List<Areaconocimiento> getAreasByPlan() {
        if (isAdmin) {
            if (selectedPlanestudio.getPesid() != 0) {
                selectedAreaconocimiento.setAcoid(0);
                selectedEtapa = 0;
//            selectedEtapa = 0; Causo problemas con el metodo deshabilitarComponentes()
                setSeleccionarUA("0");
                selectedUnidadaprendizaje.setUapid(0);
                return ctDelegate.getAreasByPlanYProgramaeducativoAdmin(selectedPlanestudio.getPesid(), programaESeleccionado);
            } else {
                if(isCoordinadorAreaAdmin){
                    return ctDelegate.getAreasByCoordinadorAreaAdmin(programaESeleccionado,usuId);
                }else{
                if (!programaESeleccionado.equals("0")) {
                    return ctDelegate.getAreasByProgramaeducativoAdmin(programaESeleccionado);
                }
                }
//            selectedAreaconocimiento.setAcoid(0);
//            selectedEtapa = 0;
//            return new ArrayList<Areaconocimiento>();
            }

            Unidadacademica uaAux = ctDelegate.findUnidadAcademica(loginBean.getLogueado().getUsuid()).get(0);
            return ctDelegate.getAreasByUnidadAcademica(uaAux.getUacid());
        }
        if (selectedPlanestudio.getPesid() != 0) {
            selectedAreaconocimiento.setAcoid(0);
            selectedEtapa = 0;
//            selectedEtapa = 0; Causo problemas con el metodo deshabilitarComponentes()
            setSeleccionarUA("0");
            selectedUnidadaprendizaje.setUapid(0);
            if(isCoordinadorAreaAdmin){
            return ctDelegate.getAreasByPlanYProgramaeducativoCAA(selectedPlanestudio.getPesid(), programaESeleccionado,usuId);
            }
            return ctDelegate.getAreasByPlanYProgramaeducativoRPE(selectedPlanestudio.getPesid(), programaESeleccionado,usuId);
        } else {
            if (!programaESeleccionado.equals("0")) {
                if(isCoordinadorAreaAdmin){
                return ctDelegate.getAreasByProgramaeducativoCAA(programaESeleccionado,usuId);
                }
                return ctDelegate.getAreasByProgramaeducativoRPE(programaESeleccionado,usuId);
            }else{
                if(isCoordinadorAreaAdmin){
                return ctDelegate.getAreasByCAA(usuId);
                }
                return ctDelegate.getAreasByRPE(usuId);
            }
        }
    }

    public List<Unidadaprendizaje> getUnidadesAprendizaje() {
        return unidadesAprendizaje;
    }

    public void setUnidadesAprendizaje(List<Unidadaprendizaje> unidadesAprendizaje) {
        this.unidadesAprendizaje = unidadesAprendizaje;
    }

    
    public List<Unidadaprendizaje> getUnidadByArea() {
        if (isAdmin && selectedEtapa == 0 && selectedAreaconocimiento.getAcoid() == 0 && programaESeleccionado.equals("0") && selectedPlanestudio.getPesid()==0) {
            Unidadacademica aux = ctDelegate.findUnidadAcademica(loginBean.getLogueado().getUsuid()).get(0);
            unidadesAprendizaje = ctDelegate.getUnidadByUnidadAcademica(aux.getUacid());
            return unidadesAprendizaje;
        } else {
            if (selectedEtapa == 0 && selectedAreaconocimiento.getAcoid() == 0 && programaESeleccionado.equals("0") && selectedPlanestudio.getPesid()==0) {
                if(isCoordinadorAreaAdmin){
                    System.out.println("ENTRANDO COMO COORDINADOR AA**************");
            unidadesAprendizaje = ctDelegate.getUnidadByCAA(usuId);
                }else{
            unidadesAprendizaje = ctDelegate.getUnidadByRPE(usuId);
                }
            return unidadesAprendizaje;
             }
            
            if (selectedAreaconocimiento.getAcoid() != 0 && selectedEtapa != 0 ) {
                setSeleccionarUA("0");
                selectedUnidadaprendizaje.setUapid(0);
                /*Este if es para introducir la etapa, se cambio a entero para poder utilizar la propiedad "Disabled"
                 en los componentes SelectOneMenu en la vista xhtml*/
                String etapa = "";
                if (selectedEtapa == 1) {
                    etapa = "Básica";
                }
                if (selectedEtapa == 2) {
                    etapa = "Disciplinaria";
                }
                if (selectedEtapa == 3) {
                    etapa = "Terminal";
                }
                if (programaESeleccionado.equals("0")) {
                    if(isCoordinadorAreaAdmin){
                    unidadesAprendizaje = ctDelegate.getUnidadByAreaAndEtapaCAA(selectedAreaconocimiento.getAcoid(), etapa,usuId);
                    }else{
                    unidadesAprendizaje = ctDelegate.getUnidadByAreaAndEtapa(selectedAreaconocimiento.getAcoid(), etapa);
                    }
                } else {
                    if(isCoordinadorAreaAdmin){
                    unidadesAprendizaje = ctDelegate.getUnidadByAreaAndEtapaAndPECAA(selectedAreaconocimiento.getAcoid(), etapa, programaESeleccionado,usuId);
                    }else{
                    unidadesAprendizaje = ctDelegate.getUnidadByAreaAndEtapaAndPE(selectedAreaconocimiento.getAcoid(), etapa, programaESeleccionado);
                    }
                }
                return unidadesAprendizaje;
            } else {
                setSeleccionarUA("0");
                selectedUnidadaprendizaje.setUapid(0);

                //condicion para validar si hay un programa educativo seleccionado 
                if (programaESeleccionado.equals("0")) {
                    if(isCoordinadorAreaAdmin){
                    unidadesAprendizaje = ctDelegate.getUnidadByCAA(usuId);
                    }else{
                    unidadesAprendizaje = ctDelegate.getUnidadByResponsable(loginBean.getLogueado().getUsuid());
                    }
               } else {
                    //Condicion para saber si fue seleccionado un programa educativo
                    if (!programaESeleccionado.equals("0")) {
                        if(isCoordinadorAreaAdmin){
                         unidadesAprendizaje = ctDelegate.getUnidadByPECAA(programaESeleccionado,usuId);
                        }else{
                          unidadesAprendizaje = ctDelegate.getUnidadByPE(programaESeleccionado);
                        }
                    } else {

                    }
                }
                //

                //Validacion para caso de elegir Programa educativo y area de conocimiento
                if (selectedAreaconocimiento.getAcoid() != 0 && !programaESeleccionado.equals("0")) {
                    if(isCoordinadorAreaAdmin){
                    unidadesAprendizaje = ctDelegate.getUnidadByAreaConocimientoAndCAA(selectedAreaconocimiento.getAcoid(), programaESeleccionado,usuId);
                    }else{
                    unidadesAprendizaje = ctDelegate.getUnidadByAreaConocimientoAndPE(selectedAreaconocimiento.getAcoid(), programaESeleccionado);
                    }
                }
                //Seleccion de programa educativo y etapa
                if (selectedEtapa != 0 && !programaESeleccionado.equals("0")) {
                    String etapa = "";
                    if (selectedEtapa == 1) {
                        etapa = "Básica";
                    }
                    if (selectedEtapa == 2) {
                        etapa = "Disciplinaria";
                    }
                    if (selectedEtapa == 3) {
                        etapa = "Terminal";
                    }
                    if(isCoordinadorAreaAdmin){
                        System.out.println("PROGRAMA Y ETAPA DE CAA!!!!!!!!!!!!!!!!!!!!!");
                    unidadesAprendizaje = ctDelegate.getUnidadByEtapaAndPECAA(etapa, programaESeleccionado,usuId);
                    }else{
                    unidadesAprendizaje = ctDelegate.getUnidadByEtapaAndPEAdmin(etapa, programaESeleccionado);
                    }
                    }

                //Validacion para caso de seleccionar solo area de conocimiento
                if (selectedAreaconocimiento.getAcoid() != 0 && programaESeleccionado.equals("0")) {
                    if(isAdmin){
                    unidadesAprendizaje = ctDelegate.getUnidadByAreaConocimientoAdmin(selectedAreaconocimiento.getAcoid());
                    }else{
                        if(isCoordinadorAreaAdmin){
                        unidadesAprendizaje = ctDelegate.getUnidadByAreaConocimientoCAA(selectedAreaconocimiento.getAcoid(),usuId);
                        }else{
                        unidadesAprendizaje = ctDelegate.getUnidadByAreaConocimientoRPE(selectedAreaconocimiento.getAcoid(),usuId);
                        }
                    }
                }
                //

                //Caso de solo seleccionar etapa
                if (selectedEtapa != 0 && selectedAreaconocimiento.getAcoid() == 0 && programaESeleccionado.equals("0")) {
                    String etapa = "";
                    if (selectedEtapa == 1) {
                        etapa = "Básica";
                    }
                    if (selectedEtapa == 2) {
                        etapa = "Disciplinaria";
                    }
                    if (selectedEtapa == 3) {
                        etapa = "Terminal";
                    }
                    
                    if(isAdmin){
                      unidadesAprendizaje = ctDelegate.getUnidadByEtapaAdmin(etapa);
                    }else{
                        if(isCoordinadorAreaAdmin){
                          unidadesAprendizaje = ctDelegate.getUnidadByEtapaCAA(etapa,String.valueOf(usuId)); 
                        }else{
                      unidadesAprendizaje = ctDelegate.getUnidadByEtapa(etapa,String.valueOf(usuId)); 
                        }
                        
                    }
                }
                //
                
                //Caso de seleccionar solo plan de estudio
                if (selectedPlanestudio!=null&&selectedPlanestudio.getPesid()!=0&& selectedAreaconocimiento.getAcoid() == 0&&selectedEtapa==0) {
                    if(isCoordinadorAreaAdmin){
                    unidadesAprendizaje = ctDelegate.getUnidadByPlanEstudioCAA(selectedPlanestudio.getPesid(),usuId);
                    }else{
                    unidadesAprendizaje = ctDelegate.getUnidadByPlanEstudio(selectedPlanestudio.getPesid());
                    }
                }
                
                //Caso de seleccionar solo plan de estudio y etapa
                if (selectedPlanestudio!=null&&selectedPlanestudio.getPesid()!=0&& selectedAreaconocimiento.getAcoid() == 0&&selectedEtapa!=0) {
                    String etapa = "";
                    if (selectedEtapa == 1) {
                        etapa = "Básica";
                    }
                    if (selectedEtapa == 2) {
                        etapa = "Disciplinaria";
                    }
                    if (selectedEtapa == 3) {
                        etapa = "Terminal";
                    }
                    unidadesAprendizaje = ctDelegate.getUnidadByPlanEstudioAndEtapa(selectedPlanestudio.getPesid(),etapa);
                }

                return unidadesAprendizaje;
            }
        }
    }

    public List<Temaunidad> getTemasByUnidad() {
        if (selectedUnidad2.getUniid() != 0) {
            //setSeleccionarTemaunidad("0");
            //selectedTemaunidad.setTunid(0);
            return ctDelegate.getTemaunidadByUnidad(selectedUnidad2.getUniid());
        } else {
            setSeleccionarTemaunidad("0");
            selectedTemaunidad.setTunid(0);
            return new ArrayList<Temaunidad>();
        }
    }

    public Unidadaprendizaje getUnidadaprendizaje(int id) {
        if (id == 0) {
            selectedUnidadaprendizaje.setUapclave(0);
            return selectedUnidadaprendizaje;
        } else {
            selectedUnidadaprendizaje = catalogoCTBeanHelper.getUnidadaprendizajeById(id, selectedAreaconocimiento.getAcoid().toString());
            return selectedUnidadaprendizaje;
        }
    }

    public boolean isDeshabilitarPlan() {
        return deshabilitarPlan;
    }

    public void setDeshabilitarPlan(boolean deshabilitarPlan) {
        this.deshabilitarPlan = deshabilitarPlan;
    }

    public void onSelectProgramaEducativo() {
        if (programaESeleccionado != "0") {

        }
    }

    //Metodo para deshabilitar componentes
    public void deshabilitarComponentes() {
        limpiarSeleccion();
        if (!programaESeleccionado.equals("0")) {
            deshabilitarPlan = false;
            estiloHorasC = "color:black;";
            estiloHorasL = "color:black;";
            estiloHorasT = "color:black;";
        } else {
            if (programaESeleccionado.equals("0")) {
//                deshabilitarPlan = true;
                estiloHorasC = "color:black;";
                estiloHorasL = "color:black;";
                estiloHorasT = "color:black;";
            }
        }
        if (selectedPlanestudio.getPesid() != 0) {
//            deshabilitarArea = false;
//            deshabilitarEtapa = false;
//            deshabilitarUA=true;
            estiloHorasC = "color:black;";
            estiloHorasL = "color:black;";
            estiloHorasT = "color:black;";
        } else {
            if (selectedPlanestudio.getPesid() == 0) {
//                deshabilitarArea = true;
//                deshabilitarEtapa = true;

                //Reiniciar los filtros de etapa y area conocimiento 
//                selectedAreaconocimiento.setAcoid(0);
//                selectedEtapa = 0;
                estiloHorasC = "color:black;";
                estiloHorasL = "color:black;";
                estiloHorasT = "color:black;";
                RequestContext.getCurrentInstance().update("form:etapas");
                RequestContext.getCurrentInstance().update("form:areas");
            }
        }
        if (selectedAreaconocimiento.getAcoid() != 0 && selectedEtapa != 0) {
            deshabilitarUA = false;
        } else {
            if (selectedAreaconocimiento.getAcoid() == 0 || selectedEtapa == 0) {
                deshabilitarUA = true;
                estiloHorasC = "color:black;";
                estiloHorasL = "color:black;";
                estiloHorasT = "color:black;";

            }
        }
    }

    public void onChangeEtapa(){
        deshabilitarComponentes();
        unidadesAprendizaje=getUnidadByArea();
    }
    //Ejecuta al cambiar programa educativo para deseleccionar plan de estudio
    public void onChangeProgramaEducativo() {
        deshabilitarComponentes();
        selectedPlanestudio.setPesid(0);
        selectedEtapa=0;
        planesestudio = ctDelegate.getPlanesByPrograma(Integer.parseInt(programaESeleccionado));
        areasConocimiento = getAreasByPlan();
        unidadesAprendizaje=getUnidadByArea();
        selectedPlanestudio.setPesid(0);
        obtenerPlanesEstudio();
    }

    public void onChangeAreaConocimiento() {
//        deshabilitarComponentes();
//        programaESeleccionado="";
//        selectedPlanestudio.setPesid(0);
//        
        //Arreglar toda la ruta para esta consilta
        //this.programasEducativos = catalogoCTBeanHelper.programasEducativosDeUsuario(loginBean.getUsuario().getUsuid());

        Unidadacademica aux = ctDelegate.findUnidadAcademica(loginBean.getLogueado().getUsuid()).get(0);

        List<Programaeducativo> programasEducativos = catalogoCTBeanHelper.programasEducativosDeAreaConocimiento(String.valueOf(selectedAreaconocimiento.getAcoid()));
        if (programasEducativos != null && programasEducativos.size() >= 1) {
            programaESeleccionado = programasEducativos.get(0).getPedid().toString();
        } else {

        }
        if(selectedPlanestudio.getPesid()==0){
          planesestudio= catalogoCTBeanHelper.getPlanesestudioByAreaConocimiento(String.valueOf(selectedAreaconocimiento.getAcoid()));
        if (planesestudio != null && planesestudio.size() >= 1) {
            selectedPlanestudio.setPesid(planesestudio.get(0).getPesid());
        } else {

        } 
        }
       
        unidadesAprendizaje=getUnidadByArea();
        obtenerPlanesEstudio();
    }

    public void onChangePlanEstudio() {
        deshabilitarComponentes();

        areasConocimiento = getAreasByPlan();
        selectedEtapa=0;
        unidadesAprendizaje=getUnidadByArea();
    }

    //******************************** Métodos del árbol *********************************************//
    public TreeNode getUnidades() {

        if (selectedUnidadaprendizaje != null && selectedUnidadaprendizaje.getUapclave() != 0) {
            root = new DefaultTreeNode();
            System.out.println("BUSQUEDA PARA UNIDAD DE APRENDIZAJE TIPO: " + tipoG);
            return root = catalogoCTBeanHelper.getNodos(clave, tipoG);
        } else {
            return root = new DefaultTreeNode();
        }
    }

    public List<Unidad> getUnidadesByUA() {
        if (selectedUnidadaprendizaje != null && selectedUnidadaprendizaje.getUapid() != null) {
//            unidades = catalogoCTBeanHelper.getUnidadesByUA(String.valueOf(selectedUnidadaprendizaje.getUapclave()));
            unidades = catalogoCTBeanHelper.getUnidadesByUA(String.valueOf(selectedUnidadaprendizaje.getUapid()));
            return unidades;
        } else {
            unidades = new ArrayList<Unidad>();
            return unidades;
        }
    }

    //sumico agrego inicio
    boolean grabaUni = false;

    //HE COMENTADO LOS CALCULO DE HORAS ANTERIORES Y AGREGADO NUEVAS VERSIONES
    public boolean calculoHorasUnidad() {
        boolean grabaUni = false;
        double hSol = formatoAHoras(buscar);
        double hRest = 0;
        double porcentUni = 0;
//validar si esta seleccionada una unidad de aprendisaje
        if (selectedUnidadaprendizaje.getUapid() != 0) {

            //Se asignan las horas totales de la clase a la variable hRest
            //Y despus se recorren cada una de las unidades restando las horas
            //que ya han sido asignadas.
            hRest = hc;
            for (TreeNode nodo : root.getChildren()) {

                hRest = hRest - formatoAHoras(horasAFormato(((NodoMultiClass) nodo.getData()).getHoras()));
            }
            // validacion para calcular el porcentaje de subtema
            if (hRest < 0 || formatoAHoras(horasAFormato((float)hRest)) >= formatoAHoras(horasAFormato((float)hSol))) {
                // calcular porcentaje horas unidad
                porcentUni = hSol * 100 / hc;
                setPorcentUni(porcentUni);
                grabaUni = true;

                RequestContext.getCurrentInstance().update("formDlg:tabView1:paU");

            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Horas solicitadas exceden horas disponibles. <br/><center>Horas disponibles = " + hRest + "</center>");
                RequestContext.getCurrentInstance().showMessageInDialog(message);

                grabaUni = false;
                buscar = "00:00";
                RequestContext.getCurrentInstance().update("formDlg:tabView1:horasUni");
                setPorcentUni(0);
            }

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Debes elegir una unidad de aprendizaje primero.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            grabaUni = false;
        }
        return grabaUni;
    }

    Boolean grabaTema = false;

    public boolean calculoHorasTema() {
        Boolean grabaTema = false;
        double hTCPUni = selectedUnidadaprendizaje.getUaphorasClase() * 16;
        double operacion;

        double hSolT = formatoAHoras(buscar);
        double hDisp = 0;
        double porcentTem = 0;
        double UniSel = selectedUnidad.getUnihoras();
        double hTem = selectedUnidad.getUnihoras();
        Integer uniSel = selectedUnidad.getUniid();
        Integer UASel = selectedUnidadaprendizaje.getUapclave();

//valiada si esta seleccionada una unidad de aprendisaje
        if (selectedUnidadaprendizaje.getUapid() != 0) {
            //valiada si esta seleccionada una unidad 
            if (selectedUnidad.getUniid() != 0) {
                //valiada si esta seleccionada un tema       
                //se manda a calcular la suma de horas de subtemas del tema seleccionado 
                double hSumaHorasTemUni = catalogoCTBeanHelper.sumaHorasTemUni(uniSel, UASel.toString());

                System.out.println("suma de horas" + hSumaHorasTemUni);

                BigDecimal bighSumaHorasTemUni = new BigDecimal(hSumaHorasTemUni);
                bighSumaHorasTemUni = bighSumaHorasTemUni.setScale(2, RoundingMode.HALF_UP);
                hSumaHorasTemUni = bighSumaHorasTemUni.doubleValue();
                // se promedia el porcentaje de horas subte,a
                //hDisp = temUniSel - hSumaHorasSubTUni;
                operacion = UniSel - hSumaHorasTemUni;

                BigDecimal bigoperacion = new BigDecimal(operacion);
                bigoperacion = bigoperacion.setScale(2, RoundingMode.HALF_UP);
                operacion = bigoperacion.doubleValue();

                for (TreeNode nodoUnidades : root.getChildren()) {
                    if (((NodoMultiClass) nodoUnidades.getData()).getId().equals(seleccionarUnidad)) {
                        hDisp = formatoAHoras(horasAFormato(((NodoMultiClass) nodoUnidades.getData()).getHoras()));
                        for (TreeNode nodoTemas : nodoUnidades.getChildren()) {
                            hDisp = hDisp - formatoAHoras(horasAFormato(((NodoMultiClass) nodoTemas.getData()).getHoras()));
                        }
                    }
                }

                sethDispT(hDisp);
                if (hDisp >= 0 && (formatoAHoras(horasAFormato((float) hDisp)) < (formatoAHoras(horasAFormato((float) hSolT))))) {

                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Horas solicitadas exceden horas disponibles. <br/><center>Horas disponibles = " + horasAFormato((float) hDisp) + "</center>");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                    buscar = "00:00";
                    RequestContext.getCurrentInstance().update("formDlg:tabView1:horasT");
                    System.out.print("no se agrega tema las horas solicitadas pasan el rango de las horas restantes");
                    setPorcentTem(0);
                    grabaTema = false;
                } else {
                    if (catalogoCTBeanHelper.minMayor(buscar) == true) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Formato de minutos incorrecto");
                        RequestContext.getCurrentInstance().showMessageInDialog(message);
                        setPorcentTem(0);
                        buscar = "00:00";
                        RequestContext.getCurrentInstance().update("formDlg:tabView1:horasT");
                    } else {
                        // calcular porcentaje horas subtemas
                        porcentTem = hSolT * 100 / hTCPUni;
                        porcentTem = Math.round(porcentTem * 100.0) / 100.0;
                        System.out.print("si se agrega tema");
                        grabaTema = true;
                        RequestContext.getCurrentInstance().update("formDlg:tabView1:paT");
                        this.porcentTem = porcentTem;
                        //return  graba;
                    }

                }

            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "selecciona Unidad");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                buscar = "00:00";
                RequestContext.getCurrentInstance().update("formDlg:tabView1:horasT");
                System.out.print("seleccionar Unidad");
                grabaTema = false;
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "seleccionar Unidad Aprendisaje = " + hDisp);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            System.out.print("seleccionar unidad");
            grabaTema = false;
        }
        return grabaTema;
    }

    Boolean grabaSubTema = false;

    public boolean calculoHorasSubTema() {
        Boolean grabaTema = false;
        double hTCPUni = selectedUnidadaprendizaje.getUaphorasClase() * 16;
        double operacion;
        //double hSolST = nuevoSubtemaunidad.getSuthoras();
        double hSolST = datoConvertido;
        double hDisp = 0;
        double porcentSub = 0;

//valiada si esta seleccionada una unidad de aprendisaje
        if (selectedUnidadaprendizaje.getUapid() != 0) {
            Integer UASel = selectedUnidadaprendizaje.getUapclave();
            //valiada si esta seleccionada una unidad 
            if (selectedUnidad2.getUniid() != null && selectedUnidad2.getUniid() != 0) {

                Integer uniSel = selectedUnidad2.getUniid();

                //valiada si esta seleccionada un tema 
                Integer temSel = 0;
                if (selectedTemaunidad != null) {
                    temSel = selectedTemaunidad.getTunid();
                }
                System.out.println("CALCULANDO HORAS DE SUBTEMAUNIDAD CON +++++++++++++++++:" + temSel + " //" + selectedTemaunidad.getTunid());
                if (temSel != 0) {
                    double temUniSel = selectedTemaunidad.getTunhoras();
                    double hTem = selectedTemaunidad.getTunhoras();
                    //se manda a calcular la suma de horas de subtemas del tema seleccionado 
                    double hSumaHorasSubTUni = catalogoCTBeanHelper.sumaHorasSubTUni(temSel, uniSel, UASel.toString());

                    BigDecimal bighSumaHorasSubTUni = new BigDecimal(hSumaHorasSubTUni);
                    bighSumaHorasSubTUni = bighSumaHorasSubTUni.setScale(2, RoundingMode.HALF_UP);
                    hSumaHorasSubTUni = bighSumaHorasSubTUni.doubleValue();
                    // se promedia el porcentaje de horas subte,a
                    //hDisp = temUniSel - hSumaHorasSubTUni;
                    operacion = temUniSel - hSumaHorasSubTUni;

                    BigDecimal bigoperacion = new BigDecimal(operacion);
                    bigoperacion = bigoperacion.setScale(2, RoundingMode.HALF_UP);
                    operacion = bigoperacion.doubleValue();

                    sethDispST(operacion);
                    System.out.println("OPERACION: " + operacion + " Horas SOLICITADAS: " + hSolST);
                    if ((formatoAHoras(horasAFormato((float) operacion))) < (formatoAHoras(horasAFormato((float) hSolST))) && operacion >= 0) {
//                        // calcular porcentaje horas subtemas
//                        porcentSub = hSolST * 100 / hTCPUni;
//                        System.out.print("si se agrega subtema");
//                        grabaTema = true;
//                        setPorcentSubTem(porcentSub);
//                        //return  graba;
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Horas solicitadas exceden horas disponibles. <br/><center>Horas disponibles = " + horasAFormato((float) operacion) + "</center>");
                        RequestContext.getCurrentInstance().showMessageInDialog(message);
                        buscar = "00:00";
                        RequestContext.getCurrentInstance().update("formDlg:tabView1:horasS");
                        System.out.print("no se agrega subtema las horas solicitadas pasan el rango de las horas restantes");
                        setPorcentSubTem(0);
                        grabaTema = false;
                    } else {
                        if (catalogoCTBeanHelper.minMayor(buscar) == true) {
                            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Formato de minutos incorrecto");
                            RequestContext.getCurrentInstance().showMessageInDialog(message);
                            setPorcentSubTem(0);
                            buscar = "00:00";
                            RequestContext.getCurrentInstance().update("formDlg:tabView1:horasS");
                        } else {
                            // calcular porcentaje horas subtemas
                            porcentSub = hSolST * 100 / hTCPUni;
                            porcentSub = Math.round(porcentSub * 100.0) / 100.0;
                            System.out.print("si se agrega subtema");
                            grabaTema = true;
                            setPorcentSubTem(porcentSub);
                            //return  graba;
                        }

                    }
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "selecciona tema");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                    //Limpiar campos de numero y nombre
                    nuevoSubtemaunidad.setSutnumero("");
                    nuevoSubtemaunidad.setSutnombre("");
                    //
                    buscar = "00:00";
                    RequestContext.getCurrentInstance().update("formDlg:tabView1:horasS");
                    System.out.print("seleccionar tema");
                    grabaTema = false;
                }

            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "selecciona Unidad");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                //Limpiar campos de numero y nombre
                nuevoSubtemaunidad.setSutnumero("");
                nuevoSubtemaunidad.setSutnombre("");
                //
                buscar = "00:00";
                RequestContext.getCurrentInstance().update("formDlg:tabView1:horasS");
                System.out.print("seleccionar Unidad");
                grabaTema = false;
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "seleccionar Unidad Aprendisaje = " + hDisp);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            System.out.print("seleccionar unidad");
            grabaTema = false;
        }
        return grabaTema;
    }

    //sumico agrego fin
    //sumico agrego inicio
    Boolean grabaPracLab = false;

    public boolean calculoPracticaLab() {
        Boolean grabaTema = false;
        double hTLPUni = selectedUnidadaprendizaje.getUaphorasLaboratorio() * 16;
        double operacion;
        //double hSolPracLb = nuevaPracticaL.getPrlhoras();
        double hSolPracLb = datoConvertido;
        double hTPrac = catalogoCTBeanHelper.gethTotPractL();

        BigDecimal bighTPrac = new BigDecimal(hTPrac);
        bighTPrac = bighTPrac.setScale(2, RoundingMode.HALF_UP);
        hTPrac = bighTPrac.doubleValue();

        double hDisp = 0;
        double porcentPractL = 0;
        //valiada si esta seleccionada una unidad de aprendisaje
        if (selectedUnidadaprendizaje.getUapid() != 0) {
            //validacion de horas disponibles
            // hDisp = hTLPUni - hTPrac;
            operacion = hTLPUni - hTPrac;
            BigDecimal bigoperacion = new BigDecimal(operacion);
            bigoperacion = bigoperacion.setScale(2, RoundingMode.HALF_UP);
            operacion = bigoperacion.doubleValue();
            sethDispPL(operacion);
            hDisp = hl;

            PA = (float) horas * 100 / hl;
            for (TreeNode nodo : root.getChildren()) {

                hDisp -= Float.parseFloat(((NodoMultiClass) nodo.getData()).getHoras());
            }

            if (hDisp < formatoAHoras(buscar) && hDisp >= 0) {
//                // calcular porcentaje horas Practica laboratorio
//                porcentPractL = hSolPracLb * 100 / hTLPUni;
//                System.out.print("si se agrega subtema");
//                grabaTema = true;
//                setPorcentPracL(porcentPractL);
//                //return  graba;
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Horas solicitadas exceden horas disponibles. <br/><center>Horas disponibles = " + horasAFormato((float) operacion) + "</center>");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                buscar = "00:00";
                RequestContext.getCurrentInstance().update("formDlg:horasPL");
                setPorcentPracL(0);
                grabaTema = false;
            } else {
                if (catalogoCTBeanHelper.minMayor(buscar) == true) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Formato de minutos incorrecto");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                    setPorcentPracT(0);
                    buscar = "00:00";
                    RequestContext.getCurrentInstance().update("formDlg:horasPL");
                } else {
                    // calcular porcentaje horas Practica laboratorio
                    porcentPractL = hSolPracLb * 100 / hTLPUni;
                    porcentPractL = Math.round(porcentPractL * 100.0) / 100.0;
                    System.out.print("si se agrega subtema");
                    grabaTema = true;
                    setPorcentPracL(porcentPractL);
                    RequestContext.getCurrentInstance().update("formDlg:tabView1:paPL");

                    //return  graba;
                }
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "selecciona UA");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            System.out.print("seleccionar tema");
            grabaTema = false;
        }

        return grabaTema;
    }

    //sumico agrego fin
    //sumico agrego inicio horas disponibles unidades
    public void horasDisUni() {
        double hDisp = 0;
        double hTCPorUni = selectedUnidadaprendizaje.getUaphorasClase() * 16;
        double hTUni = catalogoCTBeanHelper.gethTotUni();

        hDisp = hc;
        
        for (TreeNode nodo : root.getChildren()) {
            hDisp -= formatoAHoras(horasAFormato(((NodoMultiClass) nodo.getData()).getHoras()));
        }

        if (hDisp > 0) {
            sethDispUni(hDisp);
        } else {
            sethDispUni(0);
            System.out.print("suma mal de tema hecha lineas 757");
        }
    }

    //sumico agrego fin horas disponibles unidades
    //sumico agrego inicio horas disponibles tema
    String numeroTema = "";
    String numeroSubtema = "";

    public String getNumeroSubtema() {
        return numeroSubtema;
    }

    public void setNumeroSubtema(String numeroSubtema) {
        this.numeroSubtema = numeroSubtema;
    }

    public String getNumeroTema() {
        return numeroTema;
    }

    public void setNumeroTema(String numeroTema) {
        this.numeroTema = numeroTema;
    }

    public void horasDisTem() {
        // nota calcular las horas totates de los temas de la unidad en especifico
        String uniSelec = seleccionarUnidad;
        double hDisp = 0;

        if (seleccionarUnidad == "0") {
            numeroTema = "";
        }
        for (TreeNode nodoUnidades : root.getChildren()) {
            if (((NodoMultiClass) nodoUnidades.getData()).getId().equals(seleccionarUnidad)) {
                numeroTema = ((NodoMultiClass) nodoUnidades.getData()).getNumero() + ".";
                hDisp = Double.parseDouble(((NodoMultiClass) nodoUnidades.getData()).getHoras());
                hDisp=formatoAHoras(horasAFormato(String.valueOf(hDisp)));
                 
                for (TreeNode nodoTemas : nodoUnidades.getChildren()) {
                    hDisp = hDisp - 
                            formatoAHoras(horasAFormato(((NodoMultiClass) nodoTemas.getData()).getHoras()));
                }
            }
        }

        sethDispT(hDisp);
        RequestContext.getCurrentInstance().update("formDlg:tabView1:hdisT");

    }

    //sumico agrego fin horas disponibles tema
    //sumico agrego inicio horas disponibles subtema
    public double horasDisSubTe() {
        if (selectedUnidad2 != null && selectedUnidad2.getUniid() != 0 && !seleccionarTemaunidad.equals("0") && selectedTemaunidad != null && selectedTemaunidad.getTunhoras() != null) {
            Integer uniSel = selectedUnidad2.getUniid();
            Integer temSel = selectedTemaunidad.getTunid();
            double htemSel = selectedTemaunidad.getTunhoras();
            Integer UASel = selectedUnidadaprendizaje.getUapclave();
            double temUniSel = selectedTemaunidad.getTunhoras();
            double hSumaHorasSubTUni = catalogoCTBeanHelper.sumaHorasSubTUni(temSel, uniSel, UASel.toString());
            double hDispST = 0;

            //Asignar prenumero del subtema:
            numeroSubtema = selectedTemaunidad.getTunnumero() + ".";
            hDispST = formatoAHoras(horasAFormato((float)htemSel)) - hSumaHorasSubTUni;

            if (hDispST > 0) {
                sethDispST(hDispST);
            } else {
                sethDispST(0);
            }
            return hDispST;
        }
        numeroSubtema = "";
        sethDispST(0);
        return 0;
    }

    //sumico agrego fin horas disponibles subtema
    //sumico agrego inicio horas disponibles subtema
    public double horasDisLab() {

        if (selectedUnidadaprendizaje != null && selectedUnidadaprendizaje.getUapid() != 0) {
            sethDispPL(0.0);
            double operacion;
            //double hTTPUni = selectedUnidadaprendizaje.getUaphorasTaller() * 16;
            //double hTPrac = catalogoCTBeanHelper.gethTotPracT();

            double hSumaHorasLab = catalogoCTBeanHelper.gethTotPractL();

            double totHLab = selectedUnidadaprendizaje.getUaphorasLaboratorio() * 16;
            System.out.println("*************************totHLab" + totHLab);
            for (TreeNode nodoLaboratorio : root.getChildren()) {
                totHLab -= Float.parseFloat(((NodoMultiClass) nodoLaboratorio.getData()).getHoras());
            }
            BigDecimal bighSumaHorasLab = new BigDecimal(hSumaHorasLab);
            BigDecimal bigtotHLab = new BigDecimal(totHLab);

            bighSumaHorasLab = bighSumaHorasLab.setScale(2, RoundingMode.HALF_UP);
            System.out.println("odio esto2" + hSumaHorasLab + "ahora con big" + bighSumaHorasLab);
            hSumaHorasLab = bighSumaHorasLab.doubleValue();

            bigtotHLab = bigtotHLab.setScale(2, RoundingMode.HALF_UP);
            System.out.println("odio esto2" + hSumaHorasLab + "ahora con big" + bigtotHLab);
            totHLab = bigtotHLab.doubleValue();
            operacion = totHLab - hSumaHorasLab;

            BigDecimal bigoperacion = new BigDecimal(operacion);
            bigoperacion = bigoperacion.setScale(2, RoundingMode.HALF_UP);

            sethDispPL(totHLab);

            //sethDispPL(totHLab - hSumaHorasLab);
            System.out.println("TO RETURN DISPHL" + gethDispPL() + " TOTLAB" + totHLab);
            return totHLab;
        } else {
            return 0.0;
        }
    }
    //sumico agrego fin horas disponibles subtema

    public String agregarUnidades() {
        try {
            datoConvertido = catalogoCTBeanHelper.convercion(buscar);
            nuevaUnidad.setUnihoras((float) datoConvertido);
        } catch (Exception e) {
            nuevaUnidad.setUnihoras((float) 0);
        }
        if (calculoHorasUnidad()) {
            if (selectedUnidadaprendizaje.getUapid() != 0) {

                //validacion de campos vacios inicio
                String mensajeUni = "";

                if (nuevaUnidad.getUninumero() == null || nuevaUnidad.getUninumero() <= 0) {
                    mensajeUni = "Numero <br>";
                }
                if (nuevaUnidad.getUninombre().trim().equalsIgnoreCase("")) {
                    mensajeUni = mensajeUni + "Nombre <br>";
                }
                if (nuevaUnidad.getUnihoras() <= 0.0) {
                    mensajeUni = mensajeUni + "Horas <br>";
                }
                if (mensajeUni.equals("")) {
                    //validacion de campos vacios fin
                    System.out.println("Agregando unidad! n=" + nuevaUnidad.getUninombre() + " & uapid=" + selectedUnidadaprendizaje.getUapid());
                    nuevaUnidad.setUnidadaprendizaje(selectedUnidadaprendizaje);
                    nuevaUnidad.setUnivalorPorcentaje((float) porcentUni);

                    //Parte de comprobacion de campos repetidos
                    boolean banderaAgregar = true;
                    for (TreeNode node : root.getChildren()) {
                        if (((NodoMultiClass) node.getData()).getNombre().equalsIgnoreCase(nuevaUnidad.getUninombre())
                                || ((NodoMultiClass) node.getData()).getNumero().equalsIgnoreCase(nuevaUnidad.getUninumero().toString())) {
                            banderaAgregar = false;
                        }

                    }

                    //////////////
                    if (banderaAgregar) {
                        nuevaUnidad.setUnihorasCompletas(Boolean.TRUE);
                        catalogoCTBeanHelper.agregarUnidadCatalogo(nuevaUnidad);
                        nuevaUnidad = new Unidad();
                        catalogoCTBeanHelper.setUnidad(new Unidad());
                        porcentUni = 0;

                        //Se limpian los campos una vez agregado un tema
                        double horasDispRestantes = hDispUni - formatoAHoras(buscar);
                        limpiarCampos();
                        hDispUni = horasDispRestantes;
                        System.out.println("HORAS RESTANTES DE UNIDAD " + hDispUni);
                        RequestContext.getCurrentInstance().update("formDlg:tabView1:horasUni");
                        RequestContext.getCurrentInstance().update("formDlg:tabView1:paUnid");
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("", "Se guardó correctamente"));
                        validarHoras();
                    } else {
//                        limpiarCampos();
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Valor de nombre o número repetido");
                        RequestContext.getCurrentInstance().showMessageInDialog(message);
                    }
                    return "";
                } else //else de validacion de campos
                {
                    limpiarCampos();
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Campo oligatorio:<br>" + mensajeUni);
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
            } else //else de validacion Unidad de Aprendisaje
            {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Debes elegir una unidad de aprendizaje primero.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                return "";
            }
        }
        return "";
    }

    //Para obtener unidad y poder insertarla en tema
    public Unidad getUnidad(int id) {
        if (id == 0) {
            selectedUnidad.setUniid(0);
            return selectedUnidad;
        } else {
            selectedUnidad = catalogoCTBeanHelper.getUnidadById(id);
            return selectedUnidad;
        }
    }

    public String agregarTemas() {
        try {
            datoConvertido = catalogoCTBeanHelper.convercion(buscar);
            nuevoTemaunidad.setTunhoras((float) datoConvertido);
        } catch (Exception e) {
            nuevoTemaunidad.setTunhoras((float) 0);
        }
        if (selectedUnidadaprendizaje.getUapid() != 0) {
            //validacion de campos vacios inicio
            String mensajeT = "";
            if (seleccionarUnidad == "0") {
                mensajeT += "<br>Unidad";
            }
            if (nuevoTemaunidad.getTunnumero().equalsIgnoreCase("")) {
                mensajeT += "<br>Numero";
            }
            for (String temporal : nuevoTemaunidad.getTunnumero().split("\\.")) {
                if (!temporal.trim().isEmpty() && Double.parseDouble(temporal) == 0) {
                    mensajeT += "<br>Numero no puede contener 0";
                }
            }
            if (nuevoTemaunidad.getTunnombre().trim().equalsIgnoreCase("")) {
                mensajeT = mensajeT + "<br>Nombre";
            }
            if (nuevoTemaunidad.getTunhoras() <= 0) {
                mensajeT = mensajeT + "<br>Horas";
            }
            if (mensajeT.equals("")) {
            //validacion de campos vacios fin

                //Campos para validacion de excedente de horas
                double horasDisponibles = 0;

                //Validacion de campos repetidos
                boolean banderaAgregar = true;
                for (TreeNode nodo : root.getChildren()) {

                    if (((NodoMultiClass) nodo.getData()).getId().equals(seleccionarUnidad)) {
                        horasDisponibles = formatoAHoras(horasAFormato((((NodoMultiClass) nodo.getData()).getHoras())));
                        for (TreeNode nodo2 : nodo.getChildren()) {
                            horasDisponibles -= formatoAHoras(horasAFormato((((NodoMultiClass) nodo2.getData()).getHoras())));
                            if (((NodoMultiClass) nodo2.getData()).getNombre().equalsIgnoreCase(nuevoTemaunidad.getTunnombre())
                                    || ((NodoMultiClass) nodo2.getData()).getNumero().equalsIgnoreCase(numeroTema + nuevoTemaunidad.getTunnumero())) {
                                banderaAgregar = false;
                            }
                        }
                    }
                }
                //Validacion de campos repetidos

                //Validacion de excedente de horas
                System.out.println("HORAS PARA AGREGAR TEMA " + horasDisponibles + "INTENTANDO AGREGAR" + nuevoTemaunidad.getTunhoras());
                buscar = horasAFormato((float) horasDisponibles);
                if (horasDisponibles >= nuevoTemaunidad.getTunhoras()) {//fin validacion de excedente de horas
                    //Fin de validacion de excedente de horas

                    if (banderaAgregar) {
                        System.out.println("Agregando temas! n=" + nuevoTemaunidad.getTunnombre() + " & uniid=" + selectedUnidad.getUniid());
                        nuevoTemaunidad.setUnidad(selectedUnidad);
                        nuevoTemaunidad.setTunvalorPorcentaje((float) porcentTem);
                        nuevoTemaunidad.setTunhorasCompletas(Boolean.TRUE);
                        nuevoTemaunidad.setTunnumero(numeroTema + nuevoTemaunidad.getTunnumero());
                        catalogoCTBeanHelper.agregarTemaCatalogo(nuevoTemaunidad);
                        hDispT = hDispT - nuevoTemaunidad.getTunhoras();
                        nuevoTemaunidad = new Temaunidad();
                        catalogoCTBeanHelper.setTemaunidad(new Temaunidad());

                        porcentTem = 0;
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("", "Se guardó correctamente"));
                        limpiarAlAgregar();

                        validarHoras();
                        RequestContext.getCurrentInstance().update("formDlg:tabView1:hdisT");

                    } else {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Valor de nombre o número repetido");
                        RequestContext.getCurrentInstance().showMessageInDialog(message);
                    }
                    return "";
                } else //else de validacion Horas Disponibles
                {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Horas Disponibles Excedidas");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
            } else //else de validacion Campos vacios
            {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Campo boligatorio:" + mensajeT);
                RequestContext.getCurrentInstance().showMessageInDialog(message);

                return "";
            }
        } else //else de validacion Unidadd de Aprendisaje
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Debes elegir una unidad de aprendizaje primero.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "";
        }
        return "";
    }

    public Temaunidad getTemaunidad(int id) {
        if (id == 0) {
            selectedTemaunidad.setTunid(0);
            return selectedTemaunidad;
        } else {
            selectedTemaunidad = catalogoCTBeanHelper.getTemaunidadById(id);
            return selectedTemaunidad;
        }
    }

    public String agregarSubtemas() {
        try {
            datoConvertido = catalogoCTBeanHelper.convercion(buscar);
            nuevoSubtemaunidad.setSuthoras((float) datoConvertido);
        } catch (Exception e) {
            System.out.println("SE INTENTO PONER EN HROAS DE SUBTEMA: " + buscar);
            nuevoSubtemaunidad.setSuthoras((float) 0);
        }
        if (selectedUnidadaprendizaje.getUapid() != 0) {
            //validaion de campos vacios inicio
            String mensajeSubT = "";

            if (selectedUnidad2.getUniid() == 0) {
                mensajeSubT += "<br>Unidad";
            }
            if (seleccionarTemaunidad == "0") {
                mensajeSubT += "<br>Tema Unidad";
            }
            if (nuevoSubtemaunidad.getSutnumero().equalsIgnoreCase("")) {
                mensajeSubT += "<br>Numero";
            }
            for (String temporal : nuevoSubtemaunidad.getSutnumero().split("\\.")) {
                System.out.println("RESULTADO DE LA VALIDACION DE SUBTEMAS DEL SPLIT" + temporal);
                if (temporal.trim().isEmpty() || Double.parseDouble(temporal) == 0) {
                    if (!temporal.trim().isEmpty() && Double.parseDouble(temporal) == 0) {
                        mensajeSubT += "<br>Numero no puede ser 0";
                    }
                }
            }

            if (nuevoSubtemaunidad.getSutnombre().trim().equalsIgnoreCase("")) {
                mensajeSubT = mensajeSubT + "<br>Nombre";
            }
            //error
            if (nuevoSubtemaunidad.getSuthoras() <= 0) {
                mensajeSubT = mensajeSubT + "<br>Horas ";
            }

            if (mensajeSubT.equals("")) {
                //validacion de campos vacios fin

                //Validacion de campos repetidos
                boolean banderaAgregar = true;
                for (TreeNode nodo : root.getChildren()) {
                    if (((NodoMultiClass) nodo.getData()).getId().equals(selectedUnidad2.getUniid().toString())) {
                        for (TreeNode nodo2 : nodo.getChildren()) {
                            if (((NodoMultiClass) nodo2.getData()).getId().equals(seleccionarTemaunidad)) {
                                for (TreeNode nodo3 : nodo2.getChildren()) {
                                    if (((NodoMultiClass) nodo3.getData()).getNombre().equalsIgnoreCase(nuevoSubtemaunidad.getSutnombre())
                                            || ((NodoMultiClass) nodo3.getData()).getNumero().equalsIgnoreCase(numeroSubtema + nuevoSubtemaunidad.getSutnumero())) {
                                        banderaAgregar = false;
                                    }
                                }
                            }
                        }
                    }
                }
                //Validacion de campos repetidos
                if (banderaAgregar) {

                    nuevoSubtemaunidad.setSutid(0);
                    nuevoSubtemaunidad.setSuthoras((float) datoConvertido);
                    nuevoSubtemaunidad.setTemaunidad(selectedTemaunidad);
                    nuevoSubtemaunidad.setSutvalorPorcentaje((float) porcentSubTem);

                    //Agregar prenumero
                    nuevoSubtemaunidad.setSutnumero(numeroSubtema + nuevoSubtemaunidad.getSutnumero());
                    catalogoCTBeanHelper.agregarSubtemaCatalogo(nuevoSubtemaunidad);
                    hDispST -= nuevoSubtemaunidad.getSuthoras();
                    nuevoSubtemaunidad = new Subtemaunidad();
                    System.out.println("");
                    limpiarAlAgregar();
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Alta", "Se guardó correctamente"));
                    validarHoras();
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Valor de nombre o número repetido");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
                return "";
            } else //else de validacion de campos
            {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Campo Obligatorio: " + mensajeSubT);
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }

        } else // else validacion de Unidad de Aprendisaje
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Debes elegir una unidad de aprendizaje primero.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "";
        }
        return "";
    }

    public String agregarPracticaslaboratorio() {
        try {
            datoConvertido = catalogoCTBeanHelper.convercion(buscar);
            nuevaPracticaL.setPrlhoras((float) datoConvertido);
        } catch (Exception e) {
            nuevaPracticaL.setPrlhoras((float) 0);
        }
        if (selectedUnidadaprendizaje.getUapid() != 0) {
            //validacion de campos vacios inicio
            String mensaje = "";
            if (numeroPractica.isEmpty()) {
                nuevaPracticaL.setPrlnumero(0);
            } else {
                nuevaPracticaL.setPrlnumero(Integer.parseInt(numeroPractica));
            }
            if (nuevaPracticaL.getPrlnumero() <= 0) {
                mensaje += "<br>Numero";
            }
            if (nuevaPracticaL.getPrlnombre().trim().equalsIgnoreCase("")) {
                mensaje = mensaje + "<br>Nombre";
            }
            if (formatoAHoras(buscar) <= 0) {
                mensaje = mensaje + "<br>Horas";

            }
            if (mensaje.equalsIgnoreCase("")) {
                //validacion de campos vacios fin        
                boolean banderaAgregar = true;
                for (TreeNode node : root.getChildren()) {
                    if (((NodoMultiClass) node.getData()).getNombre().equalsIgnoreCase(nuevaPracticaL.getPrlnombre())
                            || ((NodoMultiClass) node.getData()).getNumero().equalsIgnoreCase(String.valueOf(nuevaPracticaL.getPrlnumero()))) {
                        banderaAgregar = false;
                    }

                }

                //////////////
                if (banderaAgregar) {
                    nuevaPracticaL.setUnidadaprendizaje(selectedUnidadaprendizaje);
                    nuevaPracticaL.setPrlvalorPorcentaje((float) porcentPracL);
                    catalogoCTBeanHelper.agregarPracticaLaboratorioCatalogo(nuevaPracticaL);
                    nuevaPracticaL = new Practicalaboratorio();
                    catalogoCTBeanHelper.setPracticasLab(new LinkedList<Practicalaboratorio>());
                    porcentPracL = 0;
                    limpiarCampos();
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Alta", "Se guardó correctamente"));
                    validarHoras();

                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Valor de nombre o número repetido");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);

                }
                return "";
            } else //else de validacion de campos
            {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo Obligatorio", "Campo obligatorio: " + mensaje);
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
        } else //else de validacion de unidad de aprendizaje
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Debes elegir una unidad de aprendizaje primero.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "";
        }
        return "";
    }

    public String agregarPracticastaller() {

        try {
            datoConvertido = catalogoCTBeanHelper.convercion(buscar);
            nuevaPracticaT.setPrthoras((float) datoConvertido);
        } catch (Exception e) {
            nuevaPracticaT.setPrthoras((float) 0);
        }

        if (selectedUnidadaprendizaje.getUapid() != 0 && nuevaPracticaT.getPrthoras() <= hDispPT
                && nuevaPracticaT.getPrthoras() != 0) {
            //validacion de campos vacios inicio
            String mensaje = "";

            if (numeroPractica.isEmpty()) {
                nuevaPracticaT.setPrtnumero(0);
            } else {
                nuevaPracticaT.setPrtnumero(Integer.parseInt(numeroPractica));
            }
            if (nuevaPracticaT.getPrtnumero() == 0) {
                mensaje += "<br>Numero";
            }
            if (nuevaPracticaT.getPrtnombre().trim().equals("")) {
                mensaje = mensaje + "<br>Nombre";
            }
            if (formatoAHoras(buscar) <= 0) {
                mensaje = mensaje + "<br>Horas";
            }
            if (mensaje.equals("")) {

                //validacion de campos vacios fin
                boolean banderaAgregar = true;
                for (TreeNode node : root.getChildren()) {
                    if (((NodoMultiClass) node.getData()).getNombre().equalsIgnoreCase(nuevaPracticaT.getPrtnombre())
                            || ((NodoMultiClass) node.getData()).getNumero().equalsIgnoreCase(String.valueOf(nuevaPracticaT.getPrtnumero()))) {
                        banderaAgregar = false;
                    }

                }
                //////////////
                if (banderaAgregar) {

                    System.out.println("Agregando practicasTaller! n=" + nuevaPracticaT.getPrtnombre() + " & uapid=" + selectedUnidadaprendizaje.getUapid());
                    nuevaPracticaT.setUnidadaprendizaje(selectedUnidadaprendizaje);
                    nuevaPracticaT.setPrtvalorPorcentaje((float) porcentPracT);

                    catalogoCTBeanHelper.agregarPracticaTallerCatalogo(nuevaPracticaT);
                    nuevaPracticaT = new Practicataller();
                    catalogoCTBeanHelper.setPracticasTall(new LinkedList<Practicataller>());
                    buscar = "00:00";
                    limpiarCampos();
                    RequestContext.getCurrentInstance().update("formDlg:horasPT");
                    porcentPracT = 0.0;
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Alta", "Se guardó correctamente"));
                    validarHoras();
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Valor de nombre o numero repetido");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
                return "";
            } else //else de validacion de campos
            {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Campo obligatorio " + mensaje);
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }

        } else // else de validacion de Unidad de Aprendizaje
        {
            if (selectedUnidadaprendizaje.getUapid() == 0) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Debes elegir una unidad de aprendizaje primero.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
            if (nuevaPracticaT.getPrthoras() > hDispPT && hDispPT >= 0) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Las horas solicitadas exceden las horas disponibles");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
            if (nuevaPracticaT.getPrthoras() == 0) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Las horas deben ser mayores que 0");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }

            return "";
        }
        return "";
    }
    private String mask = "";

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public void traerDatosDeInputMask() {
        String mask = "";

    }

    public String agregarPracticascampo() {
        if (selectedUnidadaprendizaje.getUapid() != 0) {
            nuevaPracticaC.setUnidadaprendizaje(selectedUnidadaprendizaje);
            catalogoCTBeanHelper.agregarPracticaCampoCatalogo(nuevaPracticaC);
            nuevaPracticaC = new Practicascampo();
            catalogoCTBeanHelper.setPracticasCampo(new LinkedList<Practicascampo>());
            return "";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Debes elegir una unidad de aprendizaje primero.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "";
        }
    }

    public void dialogosAgregar() {
        if (tipoG.trim().equalsIgnoreCase("C")) {
            horasDisUni();
            if (seleccionarUnidad != null) {
                horasDisTem();
            }
            horasDisSubTe();
            RequestContext.getCurrentInstance().update("formDlg:tabView1:paUnid");
            RequestContext.getCurrentInstance().update("formDlg:tabView1:hdisT");
            RequestContext.getCurrentInstance().update("formDlg:tabView1:hdiSU");
            RequestContext.getCurrentInstance().execute("dlgUnidades.show()");

        } else {
            if (tipoG.trim().equalsIgnoreCase("L")) {
                horasDisLab();
                RequestContext.getCurrentInstance().update("formDlg:tabView1:hdispPL");
                RequestContext.getCurrentInstance().execute("dlgPracticaslaboratorio.show()");
            } else {
                if (tipoG.trim().equalsIgnoreCase("T")) {
                    horasDisT();
                    RequestContext.getCurrentInstance().update("formDlg:tabView1:hdis");
                    RequestContext.getCurrentInstance().execute("dlgPracticastaller.show()");
                } else {
                    if (tipoG.trim().equalsIgnoreCase("PC")) {
                        RequestContext.getCurrentInstance().execute("dlgPracticascampo.show()");
                    } else {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Debes elegir una unidad de aprendizaje primero.");
                        RequestContext.getCurrentInstance().showMessageInDialog(message);
                    }
                }
            }
        }
    }

    /**
     * Método para verificar el tipo de nodo seleccionado. Se ejecuta en: ajax
     * de treetable. linea-141 *
     */
    String objUnidadSeleccionada = "";//Variable para guardar el id de la unidad seleccionada
    String objTemaSeleccionado = "";//Variable para guardar el id del tema seleccionado
    boolean bloquearTema = false;

    public void tipoNodoSeleccionado() {
        if (selectedNode != null) {
            System.out.println("Nodo seleccionado: " + selectedNode.getData().toString());
            NodoMultiClass nmc = (NodoMultiClass) selectedNode.getData();
            System.out.println("NMC - Tipo: " + nmc.getTipo());
            System.out.println("TipoNodo: " + nmc.getTipo());

            if (nmc.getTipo().equalsIgnoreCase("unidad")) {
                tipo = "esta Unidad";
                Unidad u = nmc.getUnidad();
                System.out.println("id:" + u.getUniid() + " Nombre:" + u.getUninombre());

                //Se guarda el ID de la unidad seleccionada
                objUnidadSeleccionada = ((NodoMultiClass) selectedNode.getData()).getId();

                //Se asigna en el value del SelectOneMenu de unidad
                seleccionarUnidad = objUnidadSeleccionada;
                selectedUnidad2.setUniid(Integer.parseInt(seleccionarUnidad));
                selectedUnidad.setUniid(Integer.parseInt(seleccionarUnidad));//Usado para validar horas

                if (seleccionarUnidad.equals("0")) {
                    numeroTema = "";
                }
                numeroTema = ((NodoMultiClass) selectedNode.getData()).getNumero() + ".";
                numeroSubtema = "";
                setSeleccionarTemaunidad("0");
                /**
                 * Faltaria asignarlo tambien al value del selectOneMenu de
                 * subtema y replicar una operacion parecida para el
                 * selectOneMenu de tema en la pestaña de Subtema
                 */
            }
            if (nmc.getTipo().equalsIgnoreCase("temaunidad")) {
                bloquearTema = true;
                tipo = "este Tema de unidad";
                Temaunidad tu = nmc.getTemaUnidad();

                objUnidadSeleccionada = ((NodoMultiClass) selectedNode.getParent().getData()).getId();

                //Se asigna en el value del SelectOneMenu de unidad
                seleccionarUnidad = objUnidadSeleccionada;
                selectedUnidad2.setUniid(Integer.parseInt(seleccionarUnidad));
                //Asignar prenumero del subtema:
                numeroTema = ((NodoMultiClass) selectedNode.getParent().getData()).getNumero() + ".";
                numeroSubtema = ((NodoMultiClass) selectedNode.getData()).getNumero() + ".";

                //Colocar tema seleccionado
                RequestContext.getCurrentInstance().update(":formDlg:tabView1:unidades2");

                RequestContext.getCurrentInstance().update(":formDlg:tabView1:temas");
                objTemaSeleccionado = ((NodoMultiClass) selectedNode.getData()).getId();
                getTemaunidad(Integer.parseInt(objTemaSeleccionado));
                System.out.println("" + objTemaSeleccionado + " ///" + ((NodoMultiClass) selectedNode.getData()).getNombre());
                seleccionarTemaunidad = objTemaSeleccionado;
                setSeleccionarTemaunidad(objTemaSeleccionado);
                RequestContext.getCurrentInstance().update(":formDlg:tabView1:temas");

            }
            if (nmc.getTipo().equalsIgnoreCase("subtemaunidad")) {
                tipo = "este Subtema de unidad";
                Subtemaunidad su = nmc.getSubTema();

                ///
                objUnidadSeleccionada = ((NodoMultiClass) selectedNode.getParent().getParent().getData()).getId();

                //Se asigna en el value del SelectOneMenu de unidad
                seleccionarUnidad = objUnidadSeleccionada;
                selectedUnidad2.setUniid(Integer.parseInt(seleccionarUnidad));

                //Colocar tema seleccionado
                RequestContext.getCurrentInstance().update(":formDlg:tabView1:unidades2");

                RequestContext.getCurrentInstance().update(":formDlg:tabView1:temas");
                objTemaSeleccionado = ((NodoMultiClass) selectedNode.getParent().getData()).getId();
                getTemaunidad(Integer.parseInt(objTemaSeleccionado));
                System.out.println("" + objTemaSeleccionado + " ///" + ((NodoMultiClass) selectedNode.getData()).getNombre());
                seleccionarTemaunidad = objTemaSeleccionado;
                setSeleccionarTemaunidad(objTemaSeleccionado);
                RequestContext.getCurrentInstance().update(":formDlg:tabView1:temas");

                //Asignar prenumero del subtema:
                numeroTema = ((NodoMultiClass) selectedNode.getParent().getData()).getNumero() + ".";
                numeroSubtema = ((NodoMultiClass) selectedNode.getData()).getNumero() + ".";

                ////
                System.out.println("id:" + su.getSutid() + " Nombre:" + su.getSutnombre());
                numeroTema = ((NodoMultiClass) selectedNode.getParent().getParent().getData()).getNumero() + ".";
                numeroSubtema = ((NodoMultiClass) selectedNode.getParent().getData()).getNumero() + ".";
            }
            if (nmc.getTipo().equalsIgnoreCase("practicalaboratorio")) {
                tipo = "esta Práctica de laboratorio";
                Practicalaboratorio pl = nmc.getPracticaL();
                System.out.println("id:" + pl.getPrlid() + " Nombre:" + pl.getPrlnombre());
            }
            if (nmc.getTipo().equalsIgnoreCase("practicataller")) {
                tipo = "esta Práctica de taller";
                Practicataller pt = nmc.getPracticaT();
                System.out.println("id:" + pt.getPrtid() + " Nombre:" + pt.getPrtnombre());
            }
            if (nmc.getTipo().equalsIgnoreCase("practicacampo")) {
                tipo = "esta Práctica de campo";
                Practicascampo pc = nmc.getPracticaC();
                System.out.println("id:" + pc.getPrcid() + " Nombre:" + pc.getPrcnombre());
            }
        } else {
        }
    }

    public void dialogoEliminar() {
        if (selectedNode != null) {
            NodoMultiClass nmc = (NodoMultiClass) selectedNode.getData();

            if (nmc.getTipo().equalsIgnoreCase("unidad")) {
                Unidad u = nmc.getUnidad();
                numero = String.valueOf(u.getUninumero());
                nombre = u.getUninombre();
                horas = u.getUnihoras();
                PA = u.getUnivalorPorcentaje();
            }
            if (nmc.getTipo().equalsIgnoreCase("temaunidad")) {
                Temaunidad tu = nmc.getTemaUnidad();
                numero = tu.getTunnumero();
                nombre = tu.getTunnombre();
                horas = tu.getTunhoras();
                PA = tu.getTunvalorPorcentaje();
            }
            if (nmc.getTipo().equalsIgnoreCase("subtemaunidad")) {
                Subtemaunidad su = nmc.getSubTema();
                numero = su.getSutnumero();
                nombre = su.getSutnombre();
                horas = su.getSuthoras();
                PA = su.getSutvalorPorcentaje();
            }
            if (nmc.getTipo().equalsIgnoreCase("practicalaboratorio")) {
                Practicalaboratorio pl = nmc.getPracticaL();
                numero = String.valueOf(pl.getPrlnumero());
                nombre = pl.getPrlnombre();
                horas = pl.getPrlhoras();
                PA = pl.getPrlvalorPorcentaje();
            }
            if (nmc.getTipo().equalsIgnoreCase("practicataller")) {
                Practicataller pt = nmc.getPracticaT();
                numero = String.valueOf(pt.getPrtnumero());
                nombre = pt.getPrtnombre();
                horas = pt.getPrthoras();
                PA = pt.getPrtvalorPorcentaje();
            }
            if (nmc.getTipo().equalsIgnoreCase("practicacampo")) {
                Practicascampo pc = nmc.getPracticaC();
                numero = String.valueOf(pc.getPrcnumero());
                nombre = pc.getPrcnombre();
                horas = pc.getPrchoras();
                PA = pc.getPrcvalorPorcentaje();
            }
            RequestContext.getCurrentInstance().execute("dlgEliminar.show()");
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Debes elegir una unidad primero.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    /**
     * Elimina el nodo que este seleccionado *
     */
    public void eliminarNodo() {
        if (selectedNode != null) {
            NodoMultiClass nmc = (NodoMultiClass) selectedNode.getData();

            if (nmc.getTipo().equalsIgnoreCase("unidad")) {
                Unidad u = nmc.getUnidad();
                catalogoCTBeanHelper.getCatalogoCTDelegate().eliminarUnidad(u);
                selectedNode = null;
                numero = "";
                nombre = "";
                horas = (float) 0.0;
                PA = (float) 0.0;
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("", "Se eliminó correctamente"));
            }
            if (nmc.getTipo().equalsIgnoreCase("temaunidad")) {
                Temaunidad tu = nmc.getTemaUnidad();
                catalogoCTBeanHelper.getCatalogoCTDelegate().eliminarTemaUnidad(tu);
                selectedNode = null;
                numero = "";
                nombre = "";
                horas = (float) 0.0;
                PA = (float) 0.0;
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("", "Se eliminó correctamente"));
            }
            if (nmc.getTipo().equalsIgnoreCase("subtemaunidad")) {
                Subtemaunidad su = nmc.getSubTema();
                catalogoCTBeanHelper.getCatalogoCTDelegate().eliminarSubtemaunidad(su);
                selectedNode = null;
                numero = "";
                nombre = "";
                horas = (float) 0.0;
                PA = (float) 0.0;
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("", "Se eliminó correctamente"));
            }
            if (nmc.getTipo().equalsIgnoreCase("practicalaboratorio")) {
                Practicalaboratorio pl = nmc.getPracticaL();
                catalogoCTBeanHelper.getCatalogoCTDelegate().eliminarPracticalaboratorio(pl);
                selectedNode = null;
                numero = "";
                nombre = "";
                horas = (float) 0.0;
                PA = (float) 0.0;
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("", "Se eliminó correctamente"));
            }
            if (nmc.getTipo().equalsIgnoreCase("practicataller")) {
                Practicataller pt = nmc.getPracticaT();
                catalogoCTBeanHelper.getCatalogoCTDelegate().eliminarPracticataller(pt);
                selectedNode = null;
                numero = "";
                nombre = "";
                horas = (float) 0.0;
                PA = (float) 0.0;
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("", "Se eliminó correctamente"));
            }
            if (nmc.getTipo().equalsIgnoreCase("practicacampo")) {
                Practicascampo pc = nmc.getPracticaC();
                catalogoCTBeanHelper.getCatalogoCTDelegate().eliminarPracticacampo(pc);
                selectedNode = null;
                numero = "";
                nombre = "";
                horas = (float) 0.0;
                PA = (float) 0.0;
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("", "Se eliminó correctamente"));
            }
            getUnidades();
            validarHoras();
            limpiarSeleccion();
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Debes elegir una unidad primero.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        //return "";
    }

    /**
     * Verifica si hay un nodo seleccionado para abrir el dialogo de actualizar
     * *
     */
    TreeNode nodoActualizacion;
    //Variable donde se define el InputMask
    String mascara = "99:99";

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public void dialogoActualizar() {
        banderaActualizar = true;
        if (selectedNode != null) {
            nodoActualizacion = selectedNode;
            NodoMultiClass nmc = (NodoMultiClass) selectedNode.getData();

            numeroTema = "";
            if (nmc.getTipo().equalsIgnoreCase("unidad")) {
                Unidad u = nmc.getUnidad();
                numero = String.valueOf(u.getUninumero());
                nombre = u.getUninombre();
                horas = u.getUnihoras();
                PA = u.getUnivalorPorcentaje();
                hDispA = hc;
                mascara = "99:00";
            }
            if (nmc.getTipo().equalsIgnoreCase("temaunidad")) {
                hDispA = Double.parseDouble(((NodoMultiClass) selectedNode.getParent().getData()).getHoras());
                String numero = (((NodoMultiClass) selectedNode.getData()).getNumero());
                Temaunidad tu = nmc.getTemaUnidad();
                numeroTema = numero.split("\\.")[0] + ".";
                this.numero = numero.split("\\.")[1];

                nombre = (((NodoMultiClass) selectedNode.getData()).getNombre());
                horas = Float.parseFloat((((NodoMultiClass) selectedNode.getParent().getData()).getHoras()));
                PA = Float.parseFloat((((NodoMultiClass) selectedNode.getParent().getData()).getPorcentajeAvance()));
                nuevoTemaunidad = new Temaunidad();
                nuevoTemaunidad.setTunhoras(horas);
                mascara = "99:99";

            }
            if (nmc.getTipo().equalsIgnoreCase("subtemaunidad")) {
                hDispA = Double.parseDouble(((NodoMultiClass) selectedNode.getParent().getData()).getHoras());

                Subtemaunidad su = nmc.getSubTema();
                String numero = (((NodoMultiClass) selectedNode.getData()).getNumero());

                numeroTema = numero.split("\\.")[0] + "." + numero.split("\\.")[1] + ".";
                this.numero = numero.split("\\.")[2];
                nombre = ((NodoMultiClass) selectedNode.getData()).getNombre();
                if (su.getSuthoras() != null) {
                    horas = Float.parseFloat(((NodoMultiClass) selectedNode.getParent().getData()).getHoras());
                } else {
                    horas = (float) 0.0;
                }
                PA = su.getSutvalorPorcentaje();
                nuevoSubtemaunidad = new Subtemaunidad();
                nuevoSubtemaunidad.setSuthoras(horas);
                mascara = "99:99";

            }
            if (nmc.getTipo().equalsIgnoreCase("practicalaboratorio")) {
                hDispA = hl;
                Practicalaboratorio pl = nmc.getPracticaL();
                numero = String.valueOf(pl.getPrlnumero());
                nombre = pl.getPrlnombre();
                horas = pl.getPrlhoras();
                PA = pl.getPrlvalorPorcentaje();
                mascara = "99:00";

            }
            if (nmc.getTipo().equalsIgnoreCase("practicataller")) {
                hDispA = ht;
                Practicataller pt = nmc.getPracticaT();
                numero = String.valueOf(pt.getPrtnumero());
                nombre = pt.getPrtnombre();
                horas = pt.getPrthoras();
                PA = pt.getPrtvalorPorcentaje();
                convertirDouble(horas);
                mascara = "99:00";

            }
            if (nmc.getTipo().equalsIgnoreCase("practicacampo")) {
                Practicascampo pc = nmc.getPracticaC();
                numero = String.valueOf(pc.getPrcnumero());
                nombre = pc.getPrcnombre();
                horas = pc.getPrchoras();
                PA = pc.getPrcvalorPorcentaje();
            }
            hDispA=(formatoAHoras(horasAFormato((float)hDispA)));
            for (TreeNode nodo : selectedNode.getParent().getChildren()) {
                hDispA -=(formatoAHoras(horasAFormato((((NodoMultiClass) nodo.getData()).getHoras()))));
            }
            hDispA += (formatoAHoras(horasAFormato(((NodoMultiClass) selectedNode.getData()).getHoras())));
            if (hDispA < 0) {
                hDispA = 0;
            }
            RequestContext.getCurrentInstance().execute("dlgActualizacion.show()");
            //calculoHorasRestantes();
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Debes elegir una unidad primero.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    /**
     * Método para modificar cualquier tipo de nodo *
     */
    public String convertirDouble(float horas) {
        String valor = "";
        String palabraCompleta = "";
        System.out.println(horas);
        valor = Double.toString(horas);
        double valor2 = 0;
        valor = valor.replace('.', ':');
        System.out.println("holis estoes el valor" + valor);
//      return valor;
        String[] parts = valor.split(":");
        String part1 = parts[0];
        String part2 = parts[1];
        int parte1Int = Integer.parseInt(part1);
        double parte2Int = Integer.parseInt(part2);

        System.out.println("ya me enfade imprime" + parte2Int);
        valor2 = (parte2Int / 100);
        //valor2=Math.rint(valor2*100)/100; 
        valor2 = valor2 * 60;

        //valor2=valor2/100;
        System.out.println("otra vez aqui" + valor2);
        String palabra1 = Integer.toString(parte1Int);
        String palabra2 = Double.toString(valor2);
        System.out.println("holis 3 esta es la ultima" + buscar);
        palabraCompleta = palabra1 + ":" + palabra2;
        setBuscar(palabraCompleta);
        return palabraCompleta;
    }

    public String actualizarNodo() {

        /**
         * Se sumaran las horas de los nodos hijos para que un nodo padre no
         * pueda resultar por una modificacion con menos horas de las que sus
         * hijos suman
         */
        float auxiliarHorasHijos = 0;

        for (TreeNode nodo : selectedNode.getChildren()) {
            auxiliarHorasHijos += Float.parseFloat(((NodoMultiClass) nodo.getData()).getHoras());
        }
        if (selectedNode.getChildCount() != 0 && formatoAHoras(horasAFormato(horas)) < formatoAHoras(horasAFormato(auxiliarHorasHijos))) {
            String tipoHijo = "";
            if (((NodoMultiClass) selectedNode.getData()).getTipo().equals("unidad")) {
                tipoHijo = "temas";
            }
            if (((NodoMultiClass) selectedNode.getData()).getTipo().equals("temaunidad")) {
                tipoHijo = "sub-temas";
            }

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Las horas resultantes no deben ser menores a <br>las horas colocadas en " + tipoHijo);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            if (banderaActualizar) {
                String mensaje = "";
                if (numero.trim().equals("")) {
                    mensaje += "<br>Numero";
                }
                for (String temporal : (numero + ".").split("\\.")) {
                    if (Double.parseDouble(temporal) == 0) {
                        mensaje += "<br>Número no puede ser 0";
                    }
                }
                if (nombre.trim().equals("")) {
                    mensaje += "<br>Nombre";
                }
                if (horas == 0) {
                    mensaje += "<br>Horas";
                }
                if (mensaje.equals("")) {
                    if (selectedNode != null) {

                        NodoMultiClass nmc = (NodoMultiClass) selectedNode.getData();

                        if (nmc.getTipo().equalsIgnoreCase("unidad")) {

                            boolean banderaAgregar = true;
                            for (TreeNode node : nodoActualizacion.getParent().getChildren()) {
                                if (((NodoMultiClass) node.getData()).getNombre().equals(nombre)
                                        || ((NodoMultiClass) node.getData()).getNumero().equalsIgnoreCase(numero.toString())) {
                                    if (!((NodoMultiClass) nodoActualizacion.getData()).getId().equalsIgnoreCase(((NodoMultiClass) node.getData()).getId())) {
                                        banderaAgregar = false;
                                    }
                                }

                            }
                            //////////////
                            if (banderaAgregar) {
                                Unidad u = nmc.getUnidad();
                                u.setUninumero(Integer.parseInt(numero));
                                u.setUninombre(nombre);
                                u.setUnihoras(horas);
                                u.setUnivalorPorcentaje(PA);
                                catalogoCTBeanHelper.agregarUnidadCatalogo(u);
                                numero = "";
                                nombre = "";
                                horas = (float) 0.0;
                                PA = (float) 0.0;
                                

                                
                                
                                FacesContext context = FacesContext.getCurrentInstance();
                                context.addMessage(null, new FacesMessage("", "Se modificó correctamente"));

                                
                                
                                
                            } else {

                                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Valor de nombre o número repetido");
                                RequestContext.getCurrentInstance().showMessageInDialog(message);
                            }
                        }
                        if (nmc.getTipo().equalsIgnoreCase("temaunidad")) {

                            boolean banderaAgregar = true;
                            for (TreeNode node : nodoActualizacion.getParent().getChildren()) {
                                if (((NodoMultiClass) node.getData()).getNombre().equals(nombre)
                                        || ((NodoMultiClass) node.getData()).getNumero().equalsIgnoreCase(numeroTema + numero.toString())) {
                                    if (!((NodoMultiClass) nodoActualizacion.getData()).getId().equalsIgnoreCase(((NodoMultiClass) node.getData()).getId())) {
                                        banderaAgregar = false;
                                    }
                                }

                            }
                            //////////////
                            if (banderaAgregar) {
                                Temaunidad tu = nmc.getTemaUnidad();

                                //Agregar prenumero
                                tu.setTunnumero(numeroTema + numero);
                                tu.setTunnombre(nombre);
                                tu.setTunhoras(horas);
                                tu.setTunvalorPorcentaje(PA);
                                tu.setTunhorasCompletas(Boolean.TRUE);
                                catalogoCTBeanHelper.agregarTemaCatalogo(tu);
                                numero = "";
                                nombre = "";
                                horas = (float) 0.0;
                                PA = (float) 0.0;
                                FacesContext context = FacesContext.getCurrentInstance();
                                context.addMessage(null, new FacesMessage("", "Se modificó correctamente"));
                            } else {

                                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Valor de nombre o número repetido");
                                RequestContext.getCurrentInstance().showMessageInDialog(message);
                            }
                        }
                        if (nmc.getTipo().equalsIgnoreCase("subtemaunidad")) {

                            boolean banderaAgregar = true;
                            for (TreeNode node : nodoActualizacion.getParent().getChildren()) {
                                if (((NodoMultiClass) node.getData()).getNombre().equals(nombre)
                                        || ((NodoMultiClass) node.getData()).getNumero().equalsIgnoreCase(numeroTema + numero.toString())) {
                                    if (!((NodoMultiClass) nodoActualizacion.getData()).getId().equalsIgnoreCase(((NodoMultiClass) node.getData()).getId())) {
                                        System.out.println("=========_________________IDS EN CONFLICTO: " + ((NodoMultiClass) node.getData()).getNumero() + "//" + numeroTema + numero.toString() + "//" + ((NodoMultiClass) node.getData()).getNombre() + "//" + nombre);
                                        banderaAgregar = false;
                                    }
                                }

                            }
                            //////////////
                            if (banderaAgregar) {

                                Subtemaunidad su = nmc.getSubTema();

                                //Agregar Prenumero
                                su.setSutnumero(numeroTema + numero);
                                su.setSutnombre(nombre);
                                su.setSuthoras(horas);
                                su.setSutvalorPorcentaje(PA);
                                catalogoCTBeanHelper.agregarSubtemaCatalogo(su);
                                numero = "";
                                nombre = "";
                                horas = (float) 0.0;
                                PA = (float) 0.0;
                                FacesContext context = FacesContext.getCurrentInstance();
                                context.addMessage(null, new FacesMessage("", "Se modificó correctamente"));
                            } else {

                                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Valor de nombre o número repetido");
                                RequestContext.getCurrentInstance().showMessageInDialog(message);
                            }
                        }
                        if (nmc.getTipo().equalsIgnoreCase("practicalaboratorio")) {

                            boolean banderaAgregar = true;
                            for (TreeNode node : nodoActualizacion.getParent().getChildren()) {
                                if (((NodoMultiClass) node.getData()).getNombre().equals(nombre)
                                        || ((NodoMultiClass) node.getData()).getNumero().equalsIgnoreCase(numero.toString())) {
                                    if (!((NodoMultiClass) nodoActualizacion.getData()).getId().equalsIgnoreCase(((NodoMultiClass) node.getData()).getId())) {
                                        banderaAgregar = false;
                                    }
                                }

                            }
                            //////////////
                            if (banderaAgregar) {

                                Practicalaboratorio pl = nmc.getPracticaL();
                                pl.setPrlnumero(Integer.parseInt(numero));
                                pl.setPrlnombre(nombre);
                                pl.setPrlhoras(horas);
                                pl.setPrlvalorPorcentaje(PA);
                                catalogoCTBeanHelper.agregarPracticaLaboratorioCatalogo(pl);
                                numero = "";
                                nombre = "";
                                horas = (float) 0.0;
                                PA = (float) 0.0;
                                FacesContext context = FacesContext.getCurrentInstance();
                                context.addMessage(null, new FacesMessage("", "Se modificó correctamente"));
                            } else {

                                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Valor de nombre o número repetido");
                                RequestContext.getCurrentInstance().showMessageInDialog(message);
                            }
                        }
                        if (nmc.getTipo().equalsIgnoreCase("practicataller")) {

                            boolean banderaAgregar = true;
                            for (TreeNode node : nodoActualizacion.getParent().getChildren()) {
                                if (((NodoMultiClass) node.getData()).getNombre().equals(nombre)
                                        || ((NodoMultiClass) node.getData()).getNumero().equalsIgnoreCase(numero.toString())) {
                                    if (!((NodoMultiClass) nodoActualizacion.getData()).getId().equalsIgnoreCase(((NodoMultiClass) node.getData()).getId())) {
                                        banderaAgregar = false;
                                    }
                                }

                            }
                            //////////////
                            if (banderaAgregar) {

                                Practicataller pt = nmc.getPracticaT();
                                pt.setPrtnumero(Integer.parseInt(numero));
                                pt.setPrtnombre(nombre);
                                pt.setPrthoras(horas);

                                pt.setPrtvalorPorcentaje(PA);
                                catalogoCTBeanHelper.agregarPracticaTallerCatalogo(pt);
                                numero = "";
                                nombre = "";
                                horas = (float) 0.0;
                                PA = (float) 0.0;
                                buscar = "";
                                FacesContext context = FacesContext.getCurrentInstance();
                                context.addMessage(null, new FacesMessage("", "Se modificó correctamente"));
                            } else {

                                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Valor de nombre o número repetido");
                                RequestContext.getCurrentInstance().showMessageInDialog(message);
                            }
                        }
                        if (nmc.getTipo().equalsIgnoreCase("practicacampo")) {

                            boolean banderaAgregar = true;
                            for (TreeNode node : nodoActualizacion.getParent().getChildren()) {
                                if (((NodoMultiClass) node.getData()).getNombre().equals(nombre)
                                        || ((NodoMultiClass) node.getData()).getNumero().equalsIgnoreCase(numero.toString())) {
                                    if (!((NodoMultiClass) nodoActualizacion.getData()).getId().equalsIgnoreCase(((NodoMultiClass) node.getData()).getId())) {
                                        banderaAgregar = false;
                                    }
                                }

                            }
                            //////////////
                            if (banderaAgregar) {
                                Practicascampo pc = nmc.getPracticaC();
                                pc.setPrcnumero(Integer.parseInt(numero));
                                pc.setPrcnombre(nombre);
                                pc.setPrchoras(horas);
                                pc.setPrcvalorPorcentaje(PA);
                                catalogoCTBeanHelper.agregarPracticaCampoCatalogo(pc);
                                numero = "";
                                nombre = "";
                                horas = (float) 0.0;
                                PA = (float) 0.0;
                                FacesContext context = FacesContext.getCurrentInstance();
                                context.addMessage(null, new FacesMessage("", "Se modificó correctamente"));
                            } else {

                                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Valor de nombre o número repetido");
                                RequestContext.getCurrentInstance().showMessageInDialog(message);
                            }
                        }
                    }

                    validarHoras();
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Campo Obligatorio: " + mensaje);
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Horas solicitadas exceden horas disponibles");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }

        }//IF DE HORAS DE PADRE MENORES A HORAS DE HIJO
        return "";
    }

    public void validarHr() {
        if (nuevaUnidad.getUnihoras() > 8) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Advertencia!", "Las horas ingresadas sobrepasan las horas disponibles."));
        } else {

        }
    }

    //sumico inicio
    private double hDispST;
    private double hDispT;
    private double hDispUni;
    private double porcentTem;

    private double porcentSubTem;
    private double porcentUni;
    private double porcentPrac;
    private double porcentPracT;
    private double porcentPracL;
    private double hDispPT;
    private double hDispPL;
    private double datoConvertido;

    public double getDatoConvertido() {
        return datoConvertido;
    }

    public void setDatoConvertido(double datoConvertido) {
        this.datoConvertido = datoConvertido;
    }

    public double getPorcentPracL() {
        return porcentPracL;
    }

    public void setPorcentPracL(double porcentPracL) {
        this.porcentPracL = porcentPracL;
    }

    public double gethDispPL() {
        return hDispPL;
    }

    public void sethDispPL(double hDispPL) {
        this.hDispPL = hDispPL;
    }

    public double gethDispST() {
        return hDispST;
    }

    public void sethDispST(double hDispST) {
        this.hDispST = hDispST;
    }

    public double gethDispT() {
        return hDispT;
    }

    public void sethDispT(double hDispT) {
        this.hDispT = hDispT;
    }

    public double gethDispUni() {
        return hDispUni;
    }

    public void sethDispUni(double hDispUni) {
        this.hDispUni = hDispUni;
    }

    public double gethDispPT() {
        return hDispPT;
    }

    public void sethDispPT(double hDispPT) {
        this.hDispPT = hDispPT;
    }

    public double getPorcentPracT() {
        return porcentPracT;
    }

    public void setPorcentPracT(double porcentPracT) {
        this.porcentPracT = porcentPracT;
    }

    public double getPorcentPrac() {
        return porcentPrac;
    }

    public void setPorcentPrac(double porcentPrac) {
        this.porcentPrac = porcentPrac;
    }

    public double getPorcentUni() {
        return porcentUni;
    }

    public void setPorcentUni(double porcentUni) {
        this.porcentUni = porcentUni;
    }

    public double getPorcentTem() {
        return porcentTem;
    }

    public void setPorcentTem(double porcentTem) {
        this.porcentTem = porcentTem;
    }

    public double getPorcentSubTem() {
        return porcentSubTem;
    }

    public void setPorcentSubTem(double porcentSubTem) {
        this.porcentSubTem = porcentSubTem;
    }

    Boolean grabaPracLabT = false;

    public double horasDisT() {

        if (selectedUnidadaprendizaje != null && selectedUnidadaprendizaje.getUapid() != 0) {
            sethDispPT(0.0);
            double operacion;
            double hTTPUni = selectedUnidadaprendizaje.getUaphorasTaller() * 16;
            System.out.println("odio esto" + hTTPUni);
            double hTPrac = catalogoCTBeanHelper.gethTotPracT();
            BigDecimal bighTPrac = new BigDecimal(hTPrac);
            BigDecimal bighTTPUni = new BigDecimal(hTTPUni);

            bighTPrac = bighTPrac.setScale(2, RoundingMode.HALF_UP);
            System.out.println("odio esto2" + hTPrac + "ahora con big" + bighTPrac);
            hTPrac = bighTPrac.doubleValue();

            bighTTPUni = bighTTPUni.setScale(2, RoundingMode.HALF_UP);
            System.out.println("odio esto2" + hTPrac + "ahora con big" + bighTTPUni);
            hTTPUni = bighTTPUni.doubleValue();
            operacion = hTTPUni - hTPrac;
//            sethDispPT(hTTPUni - hTPrac);
            BigDecimal bigoperacion = new BigDecimal(operacion);

            bigoperacion = bigoperacion.setScale(2, RoundingMode.HALF_UP);
            System.out.println("odio esto2" + hTPrac + "ahora con big" + bigoperacion);
            operacion = bigoperacion.doubleValue();
            sethDispPT(operacion);
            System.out.println("de nuevo aqui ya me enfade" + hDispPT);
            return gethDispPT();
        } else {
            return 0.0;
        }

    }

    public void valorcVonvertido(int i) {
        try {
            datoConvertido = catalogoCTBeanHelper.convercion(buscar);
            System.out.println(datoConvertido);

            if (i == 1) {
                calculoHorasUnidad();
            }
            if (i == 2) {
                calculoHorasTema();
            }
            if (i == 3) {
                System.out.println("CALCULO DE HORAS DE SUBTEMA");
                calculoHorasSubTema();
            }
            if (i == 4) {
                calculoPracticaLab();
                nuevaPracticaL.setPrlhoras((float) datoConvertido);
            }
            if (i == 5) {
                calculoPracticaT();
                nuevaPracticaT.setPrthoras((float) datoConvertido);
            }
            if (i == 6) {
            }
        } catch (Exception e) {
            datoConvertido = 0;
            System.out.println("EXCEPCION DURANTE LA REVISION DE HORAS");
            e.printStackTrace();

        }

    }

    public boolean calculoPracticaT() {
        Boolean grabaTema = true;
        double hTTPUni = selectedUnidadaprendizaje.getUaphorasTaller() * 16;
        double operacion;
        //double hSolPracT = nuevaPracticaT.getPrthoras();
        double hSolPracT = datoConvertido;

        double hTPrac = catalogoCTBeanHelper.gethTotPracT();

        BigDecimal bighTPrac = new BigDecimal(hTPrac);
        bighTPrac = bighTPrac.setScale(2, RoundingMode.HALF_UP);
        hTPrac = bighTPrac.doubleValue();

        //double hDisp = 0;
        double porcentPractT;
        //valiada si esta seleccionada una unidad de aprendisaje
        if (selectedUnidadaprendizaje.getUapid() != 0) {

            //validacion de horas disponibles
            // hDisp = hTTPUni - hTPrac;
            operacion = hTTPUni - hTPrac;
            BigDecimal bigoperacion = new BigDecimal(operacion);
            bigoperacion = bigoperacion.setScale(2, RoundingMode.HALF_UP);
            operacion = bigoperacion.doubleValue();

            sethDispPT(operacion);

            if (operacion < hSolPracT && operacion >= 0) {

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Horas solicitadas exceden horas disponibles. <br/><center>Horas disponibles = " + horasAFormato((float) operacion) + "</center>");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                setPorcentPracT(0);
                buscar = "00:00";
                RequestContext.getCurrentInstance().update("formDlg:horasPT");
                System.out.print("no se agrega subtema las horas solicitadas pasan el rango de las horas restantes");
                grabaTema = false;
            } else {
                if (catalogoCTBeanHelper.minMayor(buscar) == true) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Formato de minutos incorrecto");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                    setPorcentPracT(0);
                    buscar = "00:00";
                    RequestContext.getCurrentInstance().update("formDlg:horasPT");
                } else {
                    // calcular porcentaje horas Practica Taller
                    porcentPractT = hSolPracT * 100 / hTTPUni;
                    porcentPractT = Math.round(porcentPractT * 100.0) / 100.0;

                    System.out.print("si se agrega subtema");
                    grabaTema = true;
                    setPorcentPracT(porcentPractT);
                    //return  graba;
                }
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "selecciona unidad de aprendizaje");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            setPorcentPracT(0);
            System.out.print("seleccionar tema");
            grabaTema = false;
        }

        return grabaTema;
    }

    //Metodo que se ejecuta al hacer un cambio de TAB
    public void onTabChange(TabChangeEvent event) {
        System.out.println("EVENTO DE TAB CLOSE");
        limpiarCampos();
    }

    
    public void limpiarAlAgregar() {
        nuevaUnidad = new Unidad();
        nuevaUnidad.setUniid(0);
        buscar = "00:00";
        hDispUni = hc;
        numeroPractica = "";
        for (TreeNode nodo : root.getChildren()) {
            hDispUni -= Double.parseDouble(((NodoMultiClass) nodo.getData()).getHoras());
        }
        if (hDispUni < 0) {
            hDispUni = 0;
        }
        //Limpiar campo de porcentaje en unidad
        porcentUni = 0;

        //limpiar tema
        if (selectedNode == null && seleccionarUnidad != "0") {
            porcentTem = 0;
            nuevoTemaunidad = new Temaunidad();
            nuevoTemaunidad.setTunid(0);
        }
        //*
        //limpiar subtema
        buscar = "00:00";
        if (selectedNode == null) {
            
            nuevoSubtemaunidad = new Subtemaunidad();
            porcentSubTem = 0;
            //Las horas no se pierden al tener algo seleccionado
            porcentSubTem = 0;

        }
        nuevoSubtemaunidad.setSutnombre("");
        nuevoSubtemaunidad.setSutnumero("");
        //*
        nuevaPracticaC = new Practicascampo();
        nuevaPracticaL = new Practicalaboratorio();
        porcentPracL = 0;
        nuevaPracticaT = new Practicataller();
        porcentPracT = 0;

        RequestContext.getCurrentInstance().update("formDlg:tabView1:paU");
        RequestContext.getCurrentInstance().update("formDlg:tabView1:paUnid");
    }
    
    
    
    public void limpiarCampos() {
        nuevaUnidad = new Unidad();
        nuevaUnidad.setUniid(0);
        buscar = "00:00";
        hDispUni = hc;
        numeroPractica = "";
        for (TreeNode nodo : root.getChildren()) {
            System.out.println("############RESTANDO" + hDispUni + " - " + ((NodoMultiClass) nodo.getData()).getHoras());
            hDispUni -= Double.parseDouble(((NodoMultiClass) nodo.getData()).getHoras());
        }
        if (hDispUni < 0) {
            hDispUni = 0;
        }
        //Limpiar campo de porcentaje en unidad
        porcentUni = 0;

        //limpiar tema
        if (selectedNode == null && seleccionarUnidad != "0") {
            seleccionarUnidad = "0";
            hDispT = 0.0;
            porcentTem = 0;
            nuevoTemaunidad = new Temaunidad();
            nuevoTemaunidad.setTunid(0);
            selectedUnidad = new Unidad();
            selectedUnidad.setUniid(0);
        }

        //*
        //limpiar subtema
        buscar = "00:00";
        if (selectedNode == null) {
            seleccionarTemaunidad = "0";
            selectedUnidad2 = new Unidad();
            selectedUnidad2.setUniid(0);
            nuevoSubtemaunidad = new Subtemaunidad();
            porcentSubTem = 0;
            //Las horas no se pierden al tener algo seleccionado
            hDispST = 0.0;
            porcentSubTem = 0;

        }
        nuevoSubtemaunidad.setSutnombre("");
        nuevoSubtemaunidad.setSutnumero("");
        //*
        nuevaPracticaC = new Practicascampo();
        nuevaPracticaL = new Practicalaboratorio();
        porcentPracL = 0;
        nuevaPracticaT = new Practicataller();
        porcentPracT = 0;

        RequestContext.getCurrentInstance().update("formDlg:tabView1:paU");
        RequestContext.getCurrentInstance().update("formDlg:tabView1:paUnid");

    }
    //limpiar capos de formularios 
    //sumico fin
    //Metodos agregados por valentin
    //Variable que utiliza sus getter y setter para convertir de fromato hora a float en agregar unidad
    public String formatoDeHora = "0";

    public String getFormatoDeHora() {

        float horas = 0;
        if (selectedNode != null) {
            horas = Float.parseFloat(((NodoMultiClass) selectedNode.getData()).getHoras());
        }
//        int valorEntero=(int)horas;
//        float minutos=horas-valorEntero;
//        System.out.println("A/C HORAS"+valorEntero+"Minutos:"+minutos);
//        minutos=(float) (minutos*60/0.5);
//        System.out.println("D/C HORAS"+valorEntero+"Minutos:"+minutos);
//        formatoDeHora=String.valueOf(valorEntero+minutos);
        ///

        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        DecimalFormat formato = new DecimalFormat("##", simbolo);

        String formatoDeHora;
        int valorEntero = (int) horas;
        double minutos = horas - valorEntero;
        //Se convierten los decimales a minutos y se multiplica por 100 para poder despreciar la fraccion
        minutos = (double) (minutos * 30 / 0.5);

        String strM = String.valueOf(formato.format(minutos));
        String strH = String.valueOf(valorEntero);

        if (strM.length() < 2) {
            strM = "0" + strM;
        }
        if (strH.length() < 2) {
            strH = "0" + strH;
        }
        formatoDeHora = strH + strM;
        this.formatoDeHora = formatoDeHora;
        return this.formatoDeHora;
    }

    public String getFormatoDeHora(float horas) {
        //Mover variables a globarl y simbolo.setDecimalSeparator a CONSTRUCTOR
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        DecimalFormat formato = new DecimalFormat("##", simbolo);

        String formatoDeHora;
        int valorEntero = (int) horas;
        float minutos = horas - valorEntero;
        //Se convierten los decimales a minutos y se multiplica por 100 para poder despreciar la fraccion
        minutos = (float) (minutos * 30 / 0.5);

        String strM = String.valueOf(formato.format(minutos));
        String strH = String.valueOf(valorEntero);

        if (strM.length() < 2) {
            strM = "0" + strM;
        }
        if (strH.length() < 2) {
            strH = "0" + strH;
        }
        formatoDeHora = strH + strM;
        return formatoDeHora;
    }

    public void setFormatoDeHora(String formatoDeHora) {
        String[] formatoHora = formatoDeHora.split(":");
        float minutos = 0;
        float horas = 0;
        try {
            horas = Float.parseFloat(formatoHora[0]);
            minutos = Float.parseFloat(formatoHora[1]);
            minutos = (float) ((minutos * 0.5) / 30);
        } catch (Exception e) {
            horas = 0;
            minutos = 0;
        }
        nuevaUnidad.setUnihoras((horas + minutos));
        nuevaPracticaL.setPrlhoras(horas + minutos);
        nuevaPracticaC.setPrchoras(horas + minutos);
        nuevaPracticaT.setPrthoras(horas + minutos);
        //Actualizar tambien la variable global que se utiliza en el caso de modificacion.
        this.horas = (float) (horas + minutos);
        this.formatoDeHora = formatoDeHora;
    }

    //Recibe un numero flotante y lo convierte a formato 00:00
    public String horasAFormato(float horas) {
        //Mover variables a globarl y simbolo.setDecimalSeparator a CONSTRUCTOR
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        DecimalFormat formato = new DecimalFormat("##", simbolo);
        String formatoDeHora;
        int valorEntero = (int) horas;
        float minutos = horas - valorEntero;
        //Se convierten los decimales a minutos y se multiplica por 100 para poder despreciar la fraccion
        minutos = (float) (minutos * 30 / 0.5);
        //HUBO UN CASO DONDE EL RESULTADO MOSTRO 1HR60MIN, YA QUE DESPUES DE APLICAR EL FORMATO (REDONDEAR) 
        //VALIDABA QUE FUESEN MENOS DE 60 MIN.
        float minutosAHoras = Float.parseFloat(formato.format(minutos));
        if (minutosAHoras == 60) {
            minutos = 0;
            valorEntero += 1;
        }

        if (formato.format(minutos).length() < 2) {
            formatoDeHora = String.valueOf(valorEntero + ":0" + formato.format(minutos));
        } else {
            formatoDeHora = String.valueOf(valorEntero + ":" + formato.format(minutos));
        }

        return formatoDeHora;
    }

    public String horasAFormatoInputMask(float horas) {
        //Mover variables a globarl y simbolo.setDecimalSeparator a CONSTRUCTOR
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        DecimalFormat formato = new DecimalFormat("##", simbolo);
        String formatoDeHora;
        int valorEntero = (int) horas;
        float minutos = horas - valorEntero;
        //Se convierten los decimales a minutos y se multiplica por 100 para poder despreciar la fraccion
        minutos = (float) (minutos * 30 / 0.5);

        String strM = String.valueOf(formato.format(minutos));
        String strH = String.valueOf(valorEntero);

        if (strM.length() < 2) {
            strM = "0" + strM;
        }
        if (strH.length() < 2) {
            strH = "0" + strH;
        }
        formatoDeHora = strH + strM;
        return formatoDeHora;
    }

    //Recibe un formato de horas 00:00 y lo convierte a flotante
    public float formatoAHoras(String formato) {
        String[] formatoHora = formato.split(":");
        float minutos = 0;
        float horas = 0;
        try {
            horas = Float.parseFloat(formatoHora[0]);
            minutos = Float.parseFloat(formatoHora[1]);
            minutos = (float) ((minutos * 0.5) / 30);
            nuevaUnidad.setUnihoras(horas + minutos);
        } catch (Exception e) {
        }
        return horas + minutos;
    }

    public void valorcVonvertido() {
        try {
            datoConvertido = catalogoCTBeanHelper.convercion(buscar);
//            calculoHorasTema();
//            calculoHorasSubTema();
            System.out.println(datoConvertido);
            nuevaPracticaT.setPrthoras((float) datoConvertido);
            nuevaPracticaL.setPrlhoras((float) datoConvertido);
        } catch (Exception e) {
            datoConvertido = 0;
        }

    }

    //Caluclo de horas aplicable a todos los niveles
    boolean banderaActualizar = false;

    public String calculoHorasRestantes() {
        if (selectedNode != null) {
            NodoMultiClass nmc = (NodoMultiClass) selectedNode.getData();

            if (nmc.getTipo().equalsIgnoreCase("unidad")) {
                banderaActualizar = calculoHorasUnidadM();
            }
            if (nmc.getTipo().equalsIgnoreCase("temaunidad")) {
                banderaActualizar = calculoHorasTemaM();
            }
            if (nmc.getTipo().equalsIgnoreCase("subtemaunidad")) {
                banderaActualizar = calculoHorasSubTemaM();
            }
            if (nmc.getTipo().equalsIgnoreCase("practicalaboratorio")) {
                banderaActualizar = calculoPracticaLabM();
            }
            if (nmc.getTipo().equalsIgnoreCase("practicataller")) {
                banderaActualizar = calculoPracticaTM();
            }
            if (nmc.getTipo().equalsIgnoreCase("practicacampo")) {

            }
            RequestContext.getCurrentInstance().update("formDlg:paA");
        }
        return "";
    }

    private double hDispA = 0;

    public double gethDispA() {
        return hDispA;
    }

    public void sethDispA(double hDispA) {
        this.hDispA = hDispA;
    }

    public boolean calculoHorasUnidadM() {
        Boolean grabaUni = false;
        double hTCPorUni = selectedUnidadaprendizaje.getUaphorasClase() * 16;
        double hTUni = catalogoCTBeanHelper.gethTotUni();
        double hSol = nuevaUnidad.getUnihoras();
        double hRest = 0;
        double porcentUni = 0;
//validar si esta seleccionada una unidad de aprendisaje
        if (selectedUnidadaprendizaje.getUapid() != 0) {

            //validacion entre horas totales de clase por unidad con horas totales de unidad
//            if (hTCPorUni + hSol > hTUni) {
            hRest = hTCPorUni - hTUni;
            sethDispUni(hRest);
            // validacion para calcular el porcentaje de subtema
            float margen = Float.parseFloat(((NodoMultiClass) selectedNode.getData()).getHoras());

            //Calcular el porcentaje de Avance
            PA = (float) hSol * 100 / hc;

            if (hRest + margen >= hSol || hRest < 0) {
                hDispA = hRest + margen;
                // calcular porcentaje horas unidad
                porcentUni = hSol * 100 / hTCPorUni;
                System.out.print("si se agrega unidad");
                setPorcentUni(porcentUni);
                grabaUni = true;
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Horas solicitadas exceden horas disponibles. <br/><center>Horas disponibles = " + horasAFormato((float) hRest + margen) + "</center>");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                setFormatoDeHora("00:00");
                RequestContext.getCurrentInstance().update("formDlg:horasA");

                grabaUni = false;
                setPorcentUni(0);

            }
//            } else {
//                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "horas clase exede rango");
//                RequestContext.getCurrentInstance().showMessageInDialog(message);
//                System.out.print("las horas totales por unidad sobrepasan las horas totales de la c");
//                setFormatoDeHora("00:00");
//
//                grabaUni = false;
//
//            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Debes elegir una unidad de aprendizaje primero.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            grabaUni = false;
        }
        return grabaUni;
    }

    public boolean calculoHorasTemaM() {
        Boolean grabaTema = false;
        double hTCPorUni = selectedUnidadaprendizaje.getUaphorasClase();
        double hTUni = catalogoCTBeanHelper.gethTotUni();
        double hTotTem = catalogoCTBeanHelper.gethTotTem();
        double hSolTem = nuevoTemaunidad.getTunhoras();
        double hRest = 0;
        float hDisp = 0;
        double porcentUni = 0;
        double porcentTem = 0;

        //valida si esta seleccionala una unidad de aprendisaje
        if (selectedUnidadaprendizaje.getUapid() != 0) {

            float hUni = Float.parseFloat(((NodoMultiClass) selectedNode.getParent().getData()).getHoras());
            hUni = formatoAHoras(horasAFormato(hUni));
            hDisp = hUni;
            PA = (float) horas * 100 / hc;
            for (TreeNode nodo : selectedNode.getParent().getChildren()) {
                if (!((NodoMultiClass) nodo.getData()).getId().equals(((NodoMultiClass) selectedNode.getData()).getId())) {
                    hDisp -= formatoAHoras(horasAFormato(((NodoMultiClass) nodo.getData()).getHoras()));
                }
            }

            horas = formatoAHoras(horasAFormato(horas));
            hDisp = formatoAHoras(horasAFormato(hDisp));
            System.out.println("HORAS DISPONIBLES " + hDisp + " HORAS " + horas);
            if (hDisp >= horas && hDisp >= 0) {
                //promedia el porcent   aje de horas tema 
                if (catalogoCTBeanHelper.minMayor(horasAFormato(horas)) == true) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Formato de minutos incorrecto");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                    setPorcentSubTem(0);
                    formatoDeHora = "00:00";
                    RequestContext.getCurrentInstance().update("formDlg:dlgActualizar:horasA");
                } else {
                    porcentUni = hSolTem * 100 / hTCPorUni;
                    hDispA = hDisp;
                    porcentTem = hSolTem * 100 / hTCPorUni;
                    grabaTema = true;
                    setPorcentTem(porcentTem);
                }
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Horas solicitadas exceden horas disponibles. <br/><center>Horas disponibles = " + horasAFormato(hDisp) + "</center>");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                setFormatoDeHora("00:00");
                RequestContext.getCurrentInstance().update("formDlg:horasA");

                System.out.print("no se agrega tema las horas solicitadas pasan el rango de las horas restantes");
                setPorcentTem(0);
                grabaTema = false;
            }

        }
        return grabaTema;
    }

    //sumico agrego fin
    //sumico agrego inicio
    public boolean calculoHorasSubTemaM() {
        Boolean grabaTema = false;
        double hTCPUni = selectedUnidadaprendizaje.getUaphorasClase() * 16;
        double operacion;
        //double hSolST = nuevoSubtemaunidad.getSuthoras();
        double hSolST = datoConvertido;
        float hDisp = 0;
        double porcentSub = 0;

        if (selectedUnidadaprendizaje.getUapid() != 0) {

            float hUni = Float.parseFloat(((NodoMultiClass) selectedNode.getParent().getData()).getHoras());
            hDisp = hUni;
            PA = (float) horas * 100 / hc;
            for (TreeNode nodo : selectedNode.getParent().getChildren()) {
                if (!((NodoMultiClass) nodo.getData()).getId().equals(((NodoMultiClass) selectedNode.getData()).getId())) {
                    hDisp -= Float.parseFloat(((NodoMultiClass) nodo.getData()).getHoras());
                    System.out.println(hDisp + " - " + Float.parseFloat(((NodoMultiClass) nodo.getData()).getHoras()));
                }
            }
            hDisp = formatoAHoras(horasAFormato(hDisp));

            horas = formatoAHoras(horasAFormato(horas));
            System.out.println("///////////////////HORAS DISP" + hDisp + " HORAS AGREGAR " + horas);
            if (hDisp < horas && hDisp >= 0) {

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Horas solicitadas exceden horas disponibles. <br/><center>Horas disponibles = " + horasAFormato(hDisp) + "</center>");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                setFormatoDeHora("00:00");
                RequestContext.getCurrentInstance().update("formDlg:horasA");

                setPorcentSubTem(0);
                grabaTema = false;
            } else {
                if (catalogoCTBeanHelper.minMayor(horasAFormato(horas)) == true) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Formato de minutos incorrecto");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                    setPorcentSubTem(0);
                    formatoDeHora = "00:00";
                    RequestContext.getCurrentInstance().update("formDlg:dlgActualizar:horasA");
                } else {
                    hDispA = hDisp;
                    porcentSub = hSolST * 100 / hTCPUni;
                    porcentSub = Math.round(porcentSub * 100.0) / 100.0;
                    System.out.print("si se agrega subtema");
                    grabaTema = true;
                    setPorcentSubTem(porcentSub);
                }
            }

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "seleccionar Unidad Aprendisaje ");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            System.out.print("seleccionar unidad");
            grabaTema = false;
        }
        return grabaTema;
    }

    public boolean calculoPracticaLabM() {
        Boolean grabaTema = false;
        double hTLPUni = selectedUnidadaprendizaje.getUaphorasLaboratorio() * 16;
        double operacion;
        //double hSolPracLb = nuevaPracticaL.getPrlhoras();
        double hSolPracLb = datoConvertido;
        double hTPrac = catalogoCTBeanHelper.gethTotPractL();

        BigDecimal bighTPrac = new BigDecimal(hTPrac);
        bighTPrac = bighTPrac.setScale(2, RoundingMode.HALF_UP);
        hTPrac = bighTPrac.doubleValue();

        double hDisp = 0;
        double porcentPractL = 0;
        //valiada si esta seleccionada una unidad de aprendisaje
        if (selectedUnidadaprendizaje.getUapid() != 0) {
            //validacion de horas disponibles
            // hDisp = hTLPUni - hTPrac;

            float hUni = hl;
            hDisp = hUni;

            PA = (float) horas * 100 / hl;
            for (TreeNode nodo : selectedNode.getParent().getChildren()) {
                if (!((NodoMultiClass) nodo.getData()).getId().equals(((NodoMultiClass) selectedNode.getData()).getId())) {
                    hDisp -= Float.parseFloat(((NodoMultiClass) nodo.getData()).getHoras());
                    System.out.println(hDisp + " - " + Float.parseFloat(((NodoMultiClass) nodo.getData()).getHoras()));
                }
            }

            if (hDisp < horas && hDisp >= 0) {
//                // calcular porcentaje horas Practica laboratorio
//                porcentPractL = hSolPracLb * 100 / hTLPUni;
//                System.out.print("si se agrega subtema");
//                grabaTema = true;
//                setPorcentPracL(porcentPractL);
//                //return  graba;
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Horas solicitadas exceden horas disponibles. <br/><center>Horas disponibles = " + horasAFormato((float) hDisp) + "</center>");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                setFormatoDeHora("00:00");
                RequestContext.getCurrentInstance().update("formDlg:horasA");
                setPorcentPracL(0);
                grabaTema = false;
            } else {
                if (catalogoCTBeanHelper.minMayor(horasAFormato(horas)) == true) {

                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Formato de minutos incorrecto");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                    setPorcentPracT(0);
                    setFormatoDeHora("00:00");
                    RequestContext.getCurrentInstance().update("formDlg:horasA");
                } else {
                    // calcular porcentaje horas Practica laboratorio
                    porcentPractL = hSolPracLb * 100 / hTLPUni;
                    porcentPractL = Math.round(porcentPractL * 100.0) / 100.0;
                    System.out.print("si se agrega subtema");
                    hDispA = hDisp;

                    grabaTema = true;
                    setPorcentPracL(porcentPractL);
                    //return  graba;
                }
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "selecciona tema");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            System.out.print("seleccionar tema");
            grabaTema = false;
        }

        return grabaTema;
    }

    public boolean calculoPracticaTM() {
        Boolean grabaTema = true;
        double hTTPUni = selectedUnidadaprendizaje.getUaphorasTaller() * 16;
        double operacion;
        //double hSolPracT = nuevaPracticaT.getPrthoras();
        double hSolPracT = datoConvertido;

        double hTPrac = catalogoCTBeanHelper.gethTotPracT();
        System.out.println("nommes no salgo de aqui" + hTPrac);

        BigDecimal bighTPrac = new BigDecimal(hTPrac);
        bighTPrac = bighTPrac.setScale(2, RoundingMode.HALF_UP);
        hTPrac = bighTPrac.doubleValue();

        //double hDisp = 0;
        double porcentPractT = 0;
        //valiada si esta seleccionada una unidad de aprendisaje
        float hDisp;
        if (selectedUnidadaprendizaje.getUapid() != 0) {

            float hUni = ht;
            hDisp = hUni;
            PA = (float) horas * 100 / ht;
            for (TreeNode nodo : selectedNode.getParent().getChildren()) {
                if (!((NodoMultiClass) nodo.getData()).getId().equals(((NodoMultiClass) selectedNode.getData()).getId())) {
                    hDisp -= Float.parseFloat(((NodoMultiClass) nodo.getData()).getHoras());
                    System.out.println(hDisp + " - " + Float.parseFloat(((NodoMultiClass) nodo.getData()).getHoras()));
                }
            }

            if (hDisp < horas && hDisp >= 0) {

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Horas solicitadas exceden horas disponibles. <br/><center>Horas disponibles = " + horasAFormato(hDisp) + "</center>");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                setPorcentPracT(0);
                setFormatoDeHora("00:00");
                RequestContext.getCurrentInstance().update("formDlg:horasA");
                grabaTema = false;
            } else {
                if (catalogoCTBeanHelper.minMayor(horasAFormato(horas)) == true) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "Formato de minutos incorrecto");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                    setPorcentPracT(0);
                    setFormatoDeHora("00:00");
                    RequestContext.getCurrentInstance().update("formDlg:horasA");
                } else {
                    // calcular porcentaje horas Practica Taller
                    porcentPractT = hSolPracT * 100 / hTTPUni;
                    porcentPractT = Math.round(porcentPractT * 100.0) / 100.0;
                    hDispA = hDisp;

                    System.out.print("si se agrega subtema");
                    grabaTema = true;
                    setPorcentPracT(porcentPractT);
                    //return  graba;
                }
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de validación", "selecciona tema");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            setPorcentPracT(0);
            System.out.print("seleccionar tema");
            grabaTema = false;
        }

        return grabaTema;
    }

    public float dosDecimales(double completo) {
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        DecimalFormat formato = new DecimalFormat("##.##", simbolo);
        return Float.parseFloat(formato.format(completo));
    }

    //Metodo que define el texto de la cabecera de la primer columna del arbol
    public String cabeceraColumna() {
        if (seleccionarUA.trim().endsWith("T") || seleccionarUA.trim().endsWith("L")) {
            return "Nombre de la Practica";
        } else {
            return "Nombre de la Unidad";
        }
    }

    public void limpiarSeleccion() {
        selectedNode = null;
    }

    public void limpiarSeleccionYCampos() {
        limpiarSeleccion();
        seleccionarTemaunidad = "0";
        seleccionarUnidad = "0";
        selectedUnidad2 = null;

        limpiarCampos();
    }
    //Comprobar que horas esten completamente asignadas
    //Lista que se utiliza para revisar las horas posteriormente
    List<Unidadaprendizaje> unidadesAprendizaje = new ArrayList<Unidadaprendizaje>();

    String estiloHorasC;

    public String getEstiloHorasC() {
        return estiloHorasC;
    }

    public void setEstiloHorasC(String estiloHorasC) {
        this.estiloHorasC = estiloHorasC;
    }

    public String getEstiloHorasL() {
        return estiloHorasL;
    }

    public void setEstiloHorasL(String estiloHorasL) {
        this.estiloHorasL = estiloHorasL;
    }

    public String getEstiloHorasT() {
        return estiloHorasT;
    }

    public void setEstiloHorasT(String estiloHorasT) {
        this.estiloHorasT = estiloHorasT;
    }
    String estiloHorasL;
    String estiloHorasT;
    List<String> unidadesIncompletas = new ArrayList();
    List<String> temasIncompletos = new ArrayList();

    public String horasCompletasNodo(String tipo, String id) {
        if (tipo.equals("unidad")) {
            if (unidadesIncompletas.size() > 0) {
                for (String unidadId : unidadesIncompletas) {
                    if (unidadId.equals(id)) {
                        return "color:red;";
                    }
                }
            }
        }
        if (tipo.equals("temaunidad")) {
            if (temasIncompletos.size() > 0) {
                for (String temaId : temasIncompletos) {
                    if (temaId.equals(id)) {
                        return "color:red;";
                    }
                }
            }
        }
        return "color:black;";
    }

    public boolean validarHoras() {
        estiloHorasC = "color:green;";
        estiloHorasL = "color:green;";
        estiloHorasT = "color:green;";
        unidadesIncompletas = new ArrayList();
        temasIncompletos = new ArrayList();
        boolean horasCompletas = true;
        for (Unidadaprendizaje unidadaprendizaje2 : unidadesAprendizaje) {
            boolean horasClaseCompletas = true;
            boolean horasLaboratorioCompletas = true;
            boolean horasTallerCompletas = true;

            if (seleccionarUA.split(" -- ")[0].trim().equals(String.valueOf(unidadaprendizaje2.getUapclave()))) {
                if (unidadaprendizaje2.getUaphorasClase() != 0) {
                    float sumaHoras = 0;
                    //unidadaprendizaje2.setUaphorasClaseCompletas(true);
                    for (TreeNode nodo : catalogoCTBeanHelper.getNodos(clave, "C").getChildren()) {

                        sumaHoras += (formatoAHoras(horasAFormato((((NodoMultiClass) nodo.getData()).getHoras()))));
                        boolean horasCompletasUnidad = true;

                        if (nodo.getChildCount() > 0) {
                            float sumaTemas = 0;
                            for (TreeNode nodoTemas : nodo.getChildren()) {

                                boolean horasCompletasTema = true;
                                sumaTemas += (formatoAHoras(horasAFormato(Float.parseFloat(((NodoMultiClass) nodoTemas.getData()).getHoras()))));

                                if (nodoTemas.getChildCount() > 0) {
                                    float sumaSubtemas = 0;
                                    for (TreeNode nodoSubtemas : nodoTemas.getChildren()) {
                                        if (((NodoMultiClass) nodoSubtemas.getData()).getHoras() != null) {
                                            sumaSubtemas += (formatoAHoras(horasAFormato(((NodoMultiClass) nodoSubtemas.getData()).getHoras())));
                                        }
                                    }
                                    if (formatoAHoras(horasAFormato(sumaSubtemas)) < formatoAHoras(horasAFormato(Float.parseFloat(((NodoMultiClass) nodoTemas.getData()).getHoras())))) {
                                        if (tipoG.trim().equalsIgnoreCase("C")) {
                                            horasCompletas = false;
                                        }
                                        horasClaseCompletas = false;
                                        horasCompletasTema = false;
                                        horasCompletasUnidad = false;
                                        System.out.println("=======================NO SE ACOMPLETARON POR QUE HU: " + Float.parseFloat(((NodoMultiClass) nodoTemas.getData()).getHoras()) + " al agregar" + formatoAHoras(horasAFormato(sumaSubtemas)));
                                        temasIncompletos.add(((NodoMultiClass) nodoTemas.getData()).getId());
                                        unidadesIncompletas.add((((NodoMultiClass) nodo.getData()).getId()));
                                        estiloHorasC = "color:red;";
                                    }

                                }
                                if (horasCompletasTema != (((NodoMultiClass) nodoTemas.getData()).isHorasCompletas())) {
                                    catalogoCTBeanHelper.horasCompletastTemaunidad((((NodoMultiClass) nodoTemas.getData()).getId()));

                                }
                            }
                            if (formatoAHoras(horasAFormato(sumaTemas)) < formatoAHoras(horasAFormato(Float.parseFloat(((NodoMultiClass) nodo.getData()).getHoras())))) {
                                horasCompletasUnidad = false;
                                if (tipoG.trim().equalsIgnoreCase("C")) {
                                    horasCompletas = false;
                                }
                                horasClaseCompletas = false;
                                estiloHorasC = "color:red;";

                                unidadesIncompletas.add((((NodoMultiClass) nodo.getData()).getId()));
                            }
                        }

                        if (horasCompletasUnidad != ((NodoMultiClass) nodo.getData()).isHorasCompletas()) {
                            catalogoCTBeanHelper.horasCompletasUnidad(((NodoMultiClass) nodo.getData()).getId());
                        }
                    }

                    System.out.println("HORAS CLASE " + sumaHoras + " de " + hc);
                    if (formatoAHoras(horasAFormato(sumaHoras)) < formatoAHoras(horasAFormato(hc))) {
                        estiloHorasC = "color:red;";
                        // unidadaprendizaje2.setUaphorasClaseCompletas(false);
                        if (tipoG.trim().equalsIgnoreCase("C")) {
                            horasCompletas = false;
                        }
                        horasClaseCompletas = false;

                    }

                }

                if (unidadaprendizaje2.getUaphorasLaboratorio() != 0) {
                    float sumaHoras = 0;
                    // unidadaprendizaje2.setUaphorasLaboratorioCompletas(true);
                    for (TreeNode nodo : catalogoCTBeanHelper.getNodos(clave, "L").getChildren()) {
                        sumaHoras += Float.parseFloat(((NodoMultiClass) nodo.getData()).getHoras());
                    }
                    System.out.println("HORAS LABORATORIO " + sumaHoras + " de " + hl);
                    if (formatoAHoras(horasAFormato(sumaHoras)) < formatoAHoras(horasAFormato(hl))) {
                        estiloHorasL = "color:red;";
                        //unidadaprendizaje2.setUaphorasLaboratorioCompletas(false);
                        if (tipoG.trim().equalsIgnoreCase("L")) {
                            horasCompletas = false;
                        }
                        horasLaboratorioCompletas = false;
                    }
                }

                if (unidadaprendizaje2.getUaphorasTaller() != 0) {
                    float sumaHoras = 0;
                    //unidadaprendizaje2.setUaphorasTallerCompletas(true);
                    for (TreeNode nodo : catalogoCTBeanHelper.getNodos(clave, "T").getChildren()) {
                        sumaHoras += Float.parseFloat(((NodoMultiClass) nodo.getData()).getHoras());
                    }
                    System.out.println("HORAS TALLER " + sumaHoras + " de " + hDispPL);
                    if (formatoAHoras(horasAFormato(sumaHoras)) < formatoAHoras(horasAFormato(ht))) {
                        estiloHorasT = "color:red;";
                        // unidadaprendizaje2.setUaphorasTallerCompletas(false);
                        if (tipoG.trim().equalsIgnoreCase("T")) {
                            horasCompletas = false;
                        }
                        horasTallerCompletas = false;
                    }
                }

                System.out.println("/////////////////////////////horas laboratorio" + unidadaprendizaje2.getUaphorasLaboratorioCompletas() + "" + horasLaboratorioCompletas);
                if (unidadaprendizaje2.getUaphorasClaseCompletas() != horasClaseCompletas
                        || unidadaprendizaje2.getUaphorasLaboratorioCompletas() != horasLaboratorioCompletas
                        || unidadaprendizaje2.getUaphorasTallerCompletas() != horasTallerCompletas) {
                    unidadaprendizaje2.setUaphorasClaseCompletas(horasClaseCompletas);
                    unidadaprendizaje2.setUaphorasLaboratorioCompletas(horasLaboratorioCompletas);
                    unidadaprendizaje2.setUaphorasTallerCompletas(horasTallerCompletas);
                    catalogoCTBeanHelper.horasCompletasUnidadAprendizaje(unidadaprendizaje2, horasCompletas);
                    //unidadaprendizaje2.setUaphorasCompletas(horasCompletas);
                }
            }
        }

        if (horasCompletas) {

            FacesContext.getCurrentInstance().addMessage("horas", new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Las horas disponibles ya han sido completamente asignadas"));
            RequestContext.getCurrentInstance().update("form:messages");

        } else {
            FacesContext.getCurrentInstance().addMessage("horas", new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Las horas disponibles no han sido completamente asignadas"));
            RequestContext.getCurrentInstance().update("form:messages");
        }

        return horasCompletas;
    }

    public void cambioUA() {
        validarHoras();
        limpiarSeleccion();
        limpiarCampos();
        setDeCampos();
        obtenerPlanesEstudio();

//        obtenerProgramasEducativosDeUA();
        //Metodo que enviara mensaje si la unidad de aprendizaje se encuentra
        //en mas de un Programa educativo

//        areasConocimiento=getAreasByPlan();
//        getPlanesestudio();
//        List<Planestudio> planesestudio = catalogoCTBeanHelper.getPlanesestudioByUnidadAprendizaje(clave);
//        System.out.println("+++++++++++++++++++PLANES DE ESTUDIO");
//        if (planesestudio != null && planesestudio.size() >= 1) {
//            System.out.println("++++++++++++++++++++++++++PLANES DE ESTUDIO " + planesestudio.get(0).getPesid());
//            selectedPlanestudio.setPesid(planesestudio.get(0).getPesid());
//        }
    }

    //Metodo que enviara mensaje si la unidad de aprendizaje se encuentra
    //en mas de un Programa educativo
    public void obtenerProgramasEducativosDeUA() {
        //Programas educativos donde se encuentra la Unidad academica
        List<Programaeducativo> listaProgramasEducativos = new ArrayList<Programaeducativo>();

        //Variable utilizada para mostrar los nombres de los prgramas educativos en texto
        String textoProgramaeseducativos = "";
        listaProgramasEducativos = catalogoCTBeanHelper.obtenerProgramasEducativosDeUA(Integer.parseInt(clave.trim()));
        if (listaProgramasEducativos.size() > 1) {

//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Esta unidad de aprendizaje se imparte en: "));
            for (Programaeducativo programaeducativo : listaProgramasEducativos) {
                textoProgramaeseducativos += "</br>" + programaeducativo.getPednombre();
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "",textoProgramaeseducativos));
            }
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Esta unidad de aprendizaje se imparte en: "));
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Esta unidad de aprendizaje se imparte en: " + textoProgramaeseducativos);
            RequestContext.getCurrentInstance().showMessageInDialog(message);

        }

    }

    public void setDeCampos() {
//        programasEducativos=null;
//        planesestudio=null;
//        System.out.println(seleccionarUA.split(" -- ")[0]);
//        
//        
//        selectedPlanestudio.setPesid(1);
//        selectedAreaconocimiento.setAcoid(1);

        if (isAdmin) {
            String idUa = seleccionarUA.split(" -- ")[0];
            String etapaUa = seleccionarUA.split(" -- ")[3];
            System.out.println("etapaUA++++++++++++++++++: " + etapaUa);
            switch (etapaUa) {
                case "Básica":
                    selectedEtapa = 1;
                    break;
                case "Disciplinaria":
                    selectedEtapa = 2;
                    break;
                case "Terminal":
                    selectedEtapa = 3;
                    break;
                default:
                    break;
            }
            Unidadacademica uaAux = ctDelegate.findUnidadAcademica(loginBean.getLogueado().getUsuid()).get(0);
            programaESeleccionado = catalogoCTBeanHelper.programadeUnidadAprandizaje(Integer.parseInt(idUa)).getPedid().toString();

            selectedAreaconocimiento = catalogoCTBeanHelper.areaConocimientodeUnidadAprandizaje(idUa);
            System.out.println("EL PE SELECCIONADO ES :::::::::::" + selectedAreaconocimiento.getAcoid());
            //programasEducativos = catalogoCTBeanHelper.programasEducativosDeUnidadAcademica(uaAux.getUacid());

            selectedPlanestudio = catalogoCTBeanHelper.getPlanDeUnidadAprendizaje(Integer.parseInt(idUa));
        }
    }

    //Metodo creado para evitar excepcion en caso de que las Horas sean null,
    //ya que se detecta el parametro como String y no como float
    public String horasAFormato(String horasString) {
        //Mover variables a globarl y simbolo.setDecimalSeparator a CONSTRUCTOR
        String formatoDeHora = "0:00";
        try {
            float horas = Float.parseFloat(horasString);
            DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
            simbolo.setDecimalSeparator('.');
            DecimalFormat formato = new DecimalFormat("##", simbolo);

            int valorEntero = (int) horas;
            float minutos = horas - valorEntero;
            System.out.println("A/C HORAS" + valorEntero + "Minutos:" + minutos);
            //Se convierten los decimales a minutos y se multiplica por 100 para poder despreciar la fraccion
            minutos = (float) (minutos * 30 / 0.5);
            System.out.println("D/C HORAS" + valorEntero + "Minutos:" + minutos);

            if (String.valueOf((int) minutos).length() < 2) {
                formatoDeHora = String.valueOf(valorEntero + ":0" + formato.format(minutos));
            } else {
                formatoDeHora = String.valueOf(valorEntero + ":" + formato.format(minutos));
            }
        } catch (Exception e) {

        }
        return formatoDeHora;
    }

    String numeroPractica;

    public String getNumeroPractica() {
        return numeroPractica;
    }

    public void setNumeroPractica(String numeroPractica) {
        this.numeroPractica = numeroPractica;
    }

    //Si filtrar es igual a "algo" no regresa valor
    public String noMostrarAlgo(String filtrar) {
        if (filtrar.equals("algo")) {
            return "";
        } else {
            return filtrar;
        }
    }

    public String buscarUAporClave() {
        String resultado = "";
        Unidadaprendizaje auxiliarUA = new Unidadaprendizaje();
        resultado = auxiliarUA.getUapclave() + " -- " + auxiliarUA.getUapnombre();
        seleccionarUA = resultado;
        return resultado;
    }

    //--------------------------------------------------------------------------------------o
    String claveBuscar = "a";

    public String getClaveBuscar() {
        return claveBuscar;
    }

    public void setClaveBuscar(String claveBuscar) {
        this.claveBuscar = claveBuscar;
    }

    public void buscarPorClave() {
        UnidadAprendizajeDelegate unidadAprendizajeDelegate = new UnidadAprendizajeDelegate();
        ConsultaDelegate consultaDelegate = new ConsultaDelegate();
        Unidadaprendizaje auxUA = new Unidadaprendizaje();
        auxUA = unidadAprendizajeDelegate.findUAbyClave(claveBuscar);
        if (auxUA != null) {
            System.out.println("SE ENCONTRO UA:" + auxUA.getUapnombre() + " // " + claveBuscar + "");
            //auxUA.setAreaconocimiento(ac);
        } else {
            System.out.println("**********NO SE ENCONTRO UA CON CLAVE " + claveBuscar);

        }
    }

    
    
    public void seleccionarFiltro() {
        int filtros = 0;
        catalogoCTBeanHelper.agregarPracticaCampoCatalogo(nuevaPracticaC);
    }

}