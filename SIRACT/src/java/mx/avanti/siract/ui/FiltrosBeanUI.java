/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.ui;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import mx.avanti.siract.application.helper.FiltrosBeanHelper;
import mx.avanti.siract.application.helper.GeneralData;
import mx.avanti.siract.application.helper.ReporteAux;
import mx.avanti.siract.application.helper.ReporteAvanceAux;
import mx.avanti.siract.application.helper.SemaforoProgEd;
import mx.avanti.siract.business.CatalogoReportesDelegate;
import mx.avanti.siract.business.EsperadosDelegate;
import mx.avanti.siract.business.ProgramaEducativoDelegate;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Catalogoreportes;
import mx.avanti.siract.business.entity.Cicloescolar;
import mx.avanti.siract.business.entity.Grupo;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Rol;
import mx.avanti.siract.business.entity.Unidadacademica;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.business.entity.Usuario;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.jfree.chart.demo.LineChartDemo1;
import org.jfree.chart.demo.PieChartDemo1;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.PieChartModel;
//import test.ReporteAvanceAux3;

/**
 *
 * @author Owner
 */
@ManagedBean
@ViewScoped
public class FiltrosBeanUI implements Serializable{

    /**
     * Creates a new instance of ReporteAvanceCTBeanUI
     */
    
    //variables Rodrigo
    // modelos que nos traeremos del sistema
    private FiltrosBeanHelper filtrosBeanHelper;
    private Unidadacademica unidadacademica;
    private Programaeducativo programaeducativo;
    private Planestudio planestudio;
    private Areaconocimiento areaconocimiento;
    private Unidadaprendizaje unidadaprendizaje;
    private Cicloescolar cicloescolar;
    private List<String> selectCicloEscolarList;
    
    //Atributos/Objetos de gráficas
    private CartesianChartModel barModel;
    private CartesianChartModel lineModel;
    private PieChartModel pieModel;
    private MeterGaugeChartModel gaugeModel;
    
    private String title="Reportes de avance";
    private String xaxisLabel = "Ciclos";
    private String yaxisLabel = "Cantidad";
    private String min="0.0";
    private String max="0.0";
    
    //Atributos para Consultas
    private String opcion = "";
    private String ract = "";
    private String reporte = "";
    private String criterio = " ";
    private String tipografico = "";
    private boolean mostrarBar = false;
    private boolean mostrarLine = false;
    private boolean mostrarPie = false;
    private boolean mostrarGauge = true;
    
    private List<String> selectProgramEducativo;
    private ArrayList<String> selectProgramEducativo2;
    private List<String> selectPlanesEstudio;
    private ArrayList<String> selectPlanesEstudio2;
    private List<String> selectAreaConocimiento;
    private List<String> selectUnidadAprendisaje;
    private List<String> selectGrupo;
    private List<String> selectProfesor;
    private List<GeneralData> generalDataList;//Lista de objetos para tabla de reporte general
    private ArrayList<String> Plan;
    private ArrayList<Integer> Programa;
    private ArrayList<String> Ciclo;
    private ArrayList<String> RactEntEsp;//ArrayList de entregados y esperados para la grafica de interfaz
    private ArrayList<String> RactEsp;//ArrayList de entregados y esperados para la grafica de interfaz
    private MeterGaugeChartModel meterGaugeModel1;
    private List<SemaforoProgEd> semProgEd;
    
    private EsperadosDelegate esperadosDelegate;
    private ProgramaEducativoDelegate programaEducativoDelegate;
    //variables Rodrigo
    
    private String tipoMensajeGrowl = "";
    //private FacesMessage message;
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    
    private Usuario usuario;
    private Rol rolSeleccionado;
    
    public FiltrosBeanUI() {
        
        //variables constructor Rodrigo
        esperadosDelegate = new EsperadosDelegate();
        filtrosBeanHelper = new FiltrosBeanHelper();
        //si se iniciale la barras como nullo o con el metodo sin definir se da un problema
        barModel = initBarModel();
        lineModel = initLineModel();
        pieModel = initPieModel();
        
        //Inicializando objetos, con id en 0(cero).
        try{
            unidadacademica = new Unidadacademica();
            unidadacademica.setUacid(1);
            programaeducativo = new Programaeducativo();
            programaeducativo.setPedid(0);
            planestudio = new Planestudio();
            planestudio.setPesid(0);
            areaconocimiento = new Areaconocimiento();
            areaconocimiento.setAcoid(0);
            unidadaprendizaje = new Unidadaprendizaje();
            unidadaprendizaje.setUapid(0);
            cicloescolar = new Cicloescolar();
            cicloescolar.setCesid(0);
            selectCicloEscolarList = new ArrayList<String>();
            selectPlanesEstudio = new ArrayList<String>();
            selectPlanesEstudio2 = new ArrayList<String>();
            selectPlanesEstudio2.add(planestudio.getPesvigenciaPlan());
            selectProgramEducativo = new ArrayList<String>();
            //selectProgramEducativo2 = new ArrayList<String>();
            selectAreaConocimiento = new ArrayList<String>();
            selectUnidadAprendisaje = new ArrayList<String>();
            selectGrupo = new ArrayList<String>();
            selectProfesor = new ArrayList<String>();
            generalDataList = new ArrayList<GeneralData>();
            Plan = new ArrayList<String>();
            Programa = new ArrayList<Integer>();
            Ciclo = new ArrayList<String>();
            Plan.add("2006-2");
            Plan.add("2008-2");
            Plan.add("2009-2");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        //variables constructor Rodrigo        
        
        filtrosBeanHelper=new FiltrosBeanHelper();
        listaAux=new ArrayList<ReporteAvanceAux>();
        catalogoreportesDelegate= new CatalogoReportesDelegate();
        programaEducativoDelegate= new ProgramaEducativoDelegate();
        listaCatalogoReportes=null;
        usuario= new Usuario();
        rolSeleccionado= new Rol();
        criterio = new String();
        CTRnombre = new String();
        CTRnombre= "";
        botonCancelar=false;
        isPAGCTodosRacts=false;
        semProgEd = new ArrayList<SemaforoProgEd>();
        //message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se guardo el reporte debido a que existe una consulta guardada con el mismo nombre");
        initConsultasGuardadas();
    }
   
    @PostConstruct
    public void postConstructor() {
        //profesorBeanHelper.setRolSeleccionado(loginBean.getSeleccionado());
        usuario=loginBean.getUsuario();
        System.out.println("usuario(id): "+usuario.getUsuid());
        rolSeleccionado=loginBean.rolObj;
        System.out.println("rolSeleccionado(tipo): "+rolSeleccionado.getRoltipo());
        mostrarUsuario();
    }
    
    //variables metodos Jesus Ruelas
//    private FiltrosBeanHelper filtrosBeanHelper;
    private ArrayList<ReporteAvanceAux> listaAux;
    private CatalogoReportesDelegate catalogoreportesDelegate;
    private String op; 
    private String tipo;
    private int calnumeroReporte;
    private int numRact; 
    private String cescicloEscolar;
    private int acoclave;
    private int clavepe;
    private String pesvigencia;
    private int numProfUIPid;
    private Date fecha1;
    private int pronumeroEmpleado;
    private int gponumero;
    private int clave;
    private int uapclave;
    private int uacclave;
    private int creid;
    //aqui agregue Jesus Ruelas
    private String CTRnombre="";
    private String tipoReporteCTR;
    private List<Catalogoreportes> listaCatalogoReportes;
    private boolean botonCancelar;
    private boolean isPAGCTodosRacts;
    //aqui agregue Jesus Ruelas
    //variables metodos Jesus Ruelas
    private String tituloGraficas;
    private String tituloTabla;
    
    //codigo Rodrigo y Ricardo
    public List<GeneralData> getGeneralDataList() {
        return generalDataList;
    }
    
    public void CreateGeneralDataList() {
        ArrayList<String> entregados1=null;
        ArrayList<String> entregados2=null;
        ArrayList<String> entregados3=null;
        ArrayList<String> esperados=null;
        GeneralData data;
        //<editor-fold defaultstate="collapsed" desc="llenar Data">
        generalDataList.clear();
        if(criterio.equalsIgnoreCase("programa_educativo")){
            if(reporte.equalsIgnoreCase("entregados")||reporte.equalsIgnoreCase("noentregados")||reporte.equalsIgnoreCase("entregadosynoentregados")){
                entregados1 = esperadosDelegate.getProgramaEduEntregados(unidadacademica.getUacid(), Plan, Programa, Ciclo, "1");
                entregados2 = esperadosDelegate.getProgramaEduEntregados(unidadacademica.getUacid(), Plan, Programa, Ciclo, "2");
                entregados3 = esperadosDelegate.getProgramaEduEntregados(unidadacademica.getUacid(), Plan, Programa, Ciclo, "3");
            }
            if(reporte.equalsIgnoreCase("entregadosatiempo")){
                System.out.println("entro a entregados a tiempo");
                entregados1 = esperadosDelegate.getAtiempoProgramaEdu(unidadacademica.getUacid(), Plan, Programa, Ciclo, "1", "", "");
                entregados2 = esperadosDelegate.getAtiempoProgramaEdu(unidadacademica.getUacid(), Plan, Programa, Ciclo, "", "2", "");
                entregados3 = esperadosDelegate.getAtiempoProgramaEdu(unidadacademica.getUacid(), Plan, Programa, Ciclo, "", "", "3");
            }
            if(reporte.equalsIgnoreCase("entregadosenfechalimite")){
                System.out.println("entro a entregados en limite");
                entregados1 = esperadosDelegate.getEnLimiteProgramaEdu(unidadacademica.getUacid(), Plan, Programa, Ciclo, "1", "", "");
                entregados2 = esperadosDelegate.getEnLimiteProgramaEdu(unidadacademica.getUacid(), Plan, Programa, Ciclo, "", "2", "");
                entregados3 = esperadosDelegate.getEnLimiteProgramaEdu(unidadacademica.getUacid(), Plan, Programa, Ciclo, "", "", "3");
            }
            if(reporte.equalsIgnoreCase("entregadosdespueslimite")){
                System.out.println("entro a entregados despues de limite");
                entregados1 = esperadosDelegate.getDespuesLimiteProgramaEdu(unidadacademica.getUacid(), Plan, Programa, Ciclo, "1", "", "");
                entregados2 = esperadosDelegate.getDespuesLimiteProgramaEdu(unidadacademica.getUacid(), Plan, Programa, Ciclo, "", "2", "");
                entregados3 = esperadosDelegate.getDespuesLimiteProgramaEdu(unidadacademica.getUacid(), Plan, Programa, Ciclo, "", "", "3");
            }
            esperados = esperadosDelegate.getProgramaEduEsperados(unidadacademica.getUacid(), Plan, Programa, Ciclo);
        }
        int sumaEsperados = 0;
        int sumaEntregados = 0;
        for (String esperado1 : esperados) {
            String[] esperado = esperado1.split("-");
            sumaEsperados = sumaEsperados + Integer.parseInt(esperado[1]);
        }
        sumaEsperados=sumaEsperados/3;for (String Plan1 : Plan) {
                System.out.println(Plan1);
            }
        for (String entregados11 : entregados1) {
            String[] entregado = entregados11.split("-");
            sumaEntregados = sumaEntregados + Integer.parseInt(entregado[1]);
        }
        data = null;
        if(reporte.equals("entregados")){
            tituloTabla = "Reporte general de Entregados por Programa Educativo";
            data = new GeneralData("RACT 1",sumaEntregados, sumaEsperados);
        }
        if(reporte.equals("noentregados")){
            tituloTabla = "Reporte general de No entregados por Programa Educativo";
            data = new GeneralData("RACT 1",sumaEsperados-sumaEntregados, sumaEsperados);
        }
        if(reporte.equals("entregadosynoentregados")){
            tituloTabla = "Reporte general de Entregados y no entregados por Programa Educativo";
            data = new GeneralData("RACT 1",sumaEsperados, sumaEsperados);
        }
        if(reporte.equalsIgnoreCase("entregadosatiempo")){
            tituloTabla = "Reporte general de Entregados a tiempo por Programa Educativo";
            data = new GeneralData("RACT 1",sumaEntregados, sumaEsperados);
        }
        if(reporte.equalsIgnoreCase("entregadosenfechalimite")){
            tituloTabla = "Reporte general de Entregados en fecha limite por Programa Educativo";
            data = new GeneralData("RACT 1",sumaEntregados, sumaEsperados);
        }
        if(reporte.equalsIgnoreCase("entregadosdespueslimite")){
            tituloTabla = "Reporte general de Entregados despues de fecha limite por Programa Educativo";
            data = new GeneralData("RACT 1",sumaEntregados, sumaEsperados);
        }
        generalDataList.add(data);
        data = null;
        sumaEntregados =0;
        for (String entregados21 : entregados2) {
            String[] entregado = entregados21.split("-");
            sumaEntregados = sumaEntregados + Integer.parseInt(entregado[1]);
        }
        if(reporte.equals("noentregados"))
            data = new GeneralData("RACT 2",sumaEsperados-sumaEntregados, sumaEsperados);
        else
            data = new GeneralData("RACT 2",sumaEntregados, sumaEsperados);
        generalDataList.add(data);
        data = null;
        sumaEntregados =0;
        for (String entregados31 : entregados3) {
            String[] entregado = entregados31.split("-");
            sumaEntregados = sumaEntregados + Integer.parseInt(entregado[1]);
        }
        if(reporte.equals("noentregados"))
            data = new GeneralData("RACT 3",sumaEsperados-sumaEntregados, sumaEsperados);
        else
            data = new GeneralData("RACT 3",sumaEntregados, sumaEsperados);
        generalDataList.add(data);
        //</editor-fold>
    }

    public void setGeneralDataList(List<GeneralData> generalDataList) {
        this.generalDataList = generalDataList;
    }

    public FiltrosBeanHelper getFiltrosBeanHelper() {
        return filtrosBeanHelper;
    }
 
    //********************** Gráficas **************************
    public CartesianChartModel getBarModel() {
        return barModel;
    }
    
    public CartesianChartModel getLineModel() {
        return lineModel;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }
    
    private CartesianChartModel initBarModel() {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries entregados = new ChartSeries();
        entregados.setLabel("");
        entregados.set("", 0);
        this.title="";

        model.addSeries(entregados);                 
        return model;
    }
    
    private CartesianChartModel initLineModel() {
        CartesianChartModel model = new CartesianChartModel();
 
        LineChartSeries entregados = new LineChartSeries();
        entregados.setLabel("");
        entregados.set("", 0);
        this.title="";

        model.addSeries(entregados);                 
        return model;
    }
    
    private PieChartModel initPieModel(){
        PieChartModel model = new PieChartModel();
        this.title="Entregados";
        return model;
    }
    
    //Getters para los datos de la gráfica
    public String getTitle() {
        return title;
    }

    public String getXaxisLabel() {
        return xaxisLabel;
    }

    public String getYaxisLabel() {
        return yaxisLabel;
    }

    public String getMin() {
        return min;
    }

    public String getMax() {
        return max;
    }
    //***************************** Métodos para Consultas Comunes ******************************//
    public String getOpcion() {
        System.out.println("Este es el get");
        return opcion;
    }

    public void setOpcion(String opcion) {
        System.out.println("Este es el set");
        this.opcion = opcion;
    }
    
    public void tipoReporteComun(){
        System.out.println("Esta es la opcion: " + getOpcion());
    }
    
    public String getRact() {
        return ract;
    }

    public void setRact(String ract) {
        this.ract = ract;
    }
    
    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }
    
    public String getCriterio() {
        return criterio;
    }
    
    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }
    
    public String getTipoGrafico() {
        return tipografico;
    }

    public void setTipoGrafico(String tipografico) {
        this.tipografico = tipografico;
    }
    
    public List<String> getSelectProgramEducativo() {
        return selectProgramEducativo;
    }
    
    public void setSelectProgramEducativo(List<String> selectProgramEducativo) {
        this.selectProgramEducativo = selectProgramEducativo;
        Programa.clear();
        for(int x=0;x<selectProgramEducativo.size();x++){
            Programa.add(Integer.parseInt(selectProgramEducativo.get(x)));
        }
    }
    
    public List<String> getSelectAreaConocimiento() {
        return selectAreaConocimiento;
    }

    public void setSelectAreaConocimiento(List<String> selectAreaConocimiento) {
        this.selectAreaConocimiento = selectAreaConocimiento;
    }
    
    public List<String> getSelectPlanesEstudio() {
        return selectPlanesEstudio;
    }

    public void setSelectPlanesEstudio(List<String> selectPlanesEstudioL) {
        this.selectPlanesEstudio = selectPlanesEstudioL;
    }
    
    //selectUnidadAprendisaje
    public List<String> getSelectUnidadAprendisaje() {
        return selectUnidadAprendisaje;
    }

    public void setSelectUnidadAprendisaje(List<String> selectUnidadAprendisajeL) {
        this.selectUnidadAprendisaje = selectUnidadAprendisajeL;
    }
    
    //selectGrupo
    public List<String> getSelectGrupo() {
        return selectGrupo;
    }

    public void setSelectGrupo(List<String> selectGrupoL) {
        this.selectGrupo = selectGrupoL;
    }
    
    
    //selectProfesor
    public List<String> getSelectProfesor() {
        return selectProfesor;
    }

    public void setSelectProfesor(List<String> selectProfesorL) {
        this.selectProfesor = selectProfesorL;
    }
    
    //********************************* Metodos para listas actualizables *********************************
    public Unidadacademica getUnidadacademica() {
        return unidadacademica;
    }
    
    public Programaeducativo getProgramaeducativo() {
        return programaeducativo;
    }
    
    public Planestudio getPlanestudio() {
        return planestudio;
    }
    
    public Areaconocimiento getAreaconocimiento() {
        return areaconocimiento;
    }

    public Unidadaprendizaje getUnidadaprendizaje() {
        return unidadaprendizaje;
    }
    
    public boolean getMostrarBar() {
        return mostrarBar;
    }

    public boolean getMostrarLine() {
        return mostrarLine;
    }

    public boolean getMostrarPie() {
        return mostrarPie;
    }
    
    public Cicloescolar getCicloescolar(){
        return cicloescolar;
    }
    
    public Cicloescolar setCicloescolar(Cicloescolar cicloescolar){
        return this.cicloescolar = cicloescolar;
    }
    
    public List<String> getSelectCicloEscolar(){
        return selectCicloEscolarList;
    }
    
    public void setSelectCicloEscolar(List<String> selectCicloEscolarList){
        this.selectCicloEscolarList = selectCicloEscolarList;
        System.out.println("Ciclo escolar en array");
        Ciclo.clear();
        for(int x=0;x<selectCicloEscolarList.size();x++){
            Ciclo.add(selectCicloEscolarList.get(x));
            System.out.println(selectCicloEscolarList.get(x));
        }
    }
    //Metodo para traer la lista por filtro de Unidad Academica
    public List<Cicloescolar> getCiclosEscolares(){
        List<Cicloescolar> resultado = null;
        return resultado;
    }
    
    public List<Programaeducativo> getProgramasByUnidad(){
        if(unidadacademica.getUacid()!=0){
            //Estos sets son para cuando ya esta seleccionada una opcion y se selecciona otra opcion diferente, las listas se actualicen correctamente sin inconsistencias
            programaeducativo.setPedid(0);
            planestudio.setPesid(0);
            areaconocimiento.setAcoid(0);
            unidadaprendizaje.setUapid(0);
            return filtrosBeanHelper.getConsultaDelegate().getProgramaeducativoByUnidadacademica(unidadacademica.getUacid());
            
        }else{
            //Se utiliza este Set para que la lista no se quede con datos seleccionados
            //al cambiar el filtro anterior.
            programaeducativo.setPedid(0);
            return new ArrayList<Programaeducativo>();
        }
    }
    
    public List<Programaeducativo> getProgramasByUnidadClave(){
        if(unidadacademica.getUacid()!=0){
            //Estos sets son para cuando ya esta seleccionada una opcion y se selecciona otra opcion diferente, las listas se actualicen correctamente sin inconsistencias
            programaeducativo.setPedid(0);
            planestudio.setPesid(0);
            areaconocimiento.setAcoid(0);
            unidadaprendizaje.setUapid(0);
            
            System.out.println("Metodo Get By Clave");
            System.out.println("La cave a usar es:");
            System.out.println(unidadacademica.getUacclave());
            
            
            return filtrosBeanHelper.getConsultaDelegate().getProgramaeducativoByUnidadacademicaClave(unidadacademica.getUacclave());
            
        }else{
            //Se utiliza este Set para que la lista no se quede con datos seleccionados
            //al cambiar el filtro anterior.
            programaeducativo.setPedid(0);
            return new ArrayList<Programaeducativo>();
        }
    }
    
    //Metodo para traer la lista por filtro de Programa Educativo
    public List<Planestudio> getPlanesByPrograma(){        
        //programaeducativo.setPedid(Integer.parseInt(selectProgramEducativo.get(0)));
        if(programaeducativo.getPedid()!=0){
            planestudio.setPesid(0);
            areaconocimiento.setAcoid(0);
            unidadaprendizaje.setUapid(0);
            System.out.println("****primer if****");
            return filtrosBeanHelper.getConsultaDelegate().getPlanesByPrograma(programaeducativo.getPedid());
        }else{
            planestudio.setPesid(0);
            System.out.println("*****segundo if*****");
            return new ArrayList<Planestudio>();
        }
    }
    
    public List<Planestudio> getPlanesByProgramaClave(){        
        //programaeducativo.setPedid(Integer.parseInt(selectProgramEducativo.get(0)));
        if(programaeducativo.getPedclave()!=0){
            planestudio.setPesid(0);
            areaconocimiento.setAcoid(0);
            unidadaprendizaje.setUapid(0);
            
            System.out.println("PlanesProProgramaClave");
            System.out.println("Clave de Programa educativo:");
            System.out.println(programaeducativo.getPedclave());
            
            
            return filtrosBeanHelper.getConsultaDelegate().getPlanesByProgramaClave(programaeducativo.getPedclave());
        }else{
            planestudio.setPesid(0);
            System.out.println("*****segundo if*****");
            return new ArrayList<Planestudio>();
        }
    }
    
    public List<Planestudio> getPlanesByProgramasLista(){
        List<Planestudio> planeTempList = new ArrayList<Planestudio>();
        if(this.selectProgramEducativo!=null){
            if(selectProgramEducativo.size()> 0){
                System.out.println("Mas de 0");
                for(String ProgramasIDstr : selectProgramEducativo){
                    System.out.println(ProgramasIDstr);
                   planeTempList.addAll( filtrosBeanHelper.getConsultaDelegate().getPlanesByPrograma( Integer.parseInt(ProgramasIDstr) ));
                }
            }
            else{
                System.out.println("Vacio");
            }
        }
        else{
            System.out.println("Nullo");
        }
                
        return planeTempList;
    }
    
    public List<Areaconocimiento> getAreasByPlan(){
        if(planestudio.getPesid()!=0){
            areaconocimiento.setAcoid(0);
            unidadaprendizaje.setUapid(0);
            return filtrosBeanHelper.getConsultaDelegate().getAreasByPlan(planestudio.getPesid());
        }else{
            areaconocimiento.setAcoid(0);
            return new ArrayList<Areaconocimiento>();
        }
    }
    
    public List<Areaconocimiento> getAreasByPlanesEstudio(){
        List<Areaconocimiento> areasTempList;
        areasTempList = new ArrayList<Areaconocimiento>();
        System.out.println("Vemos SelectPlanes");
        if(selectPlanesEstudio!=null){
            if(selectPlanesEstudio.size()> 0){
                System.out.println("Mas de 0");
                for(String PlanesIDstr : selectPlanesEstudio){
                    System.out.println(PlanesIDstr);
                   areasTempList.addAll( filtrosBeanHelper.getConsultaDelegate().getAreasByPlan( Integer.parseInt(PlanesIDstr) ));
                }
            }
            else{
                System.out.println("Vacio");
            }
        }
        else{
            System.out.println("Nullo");
        }
                
        return areasTempList;
    }
    
    public List<Areaconocimiento> getAreasByPlanesEstudioClave(){
        List<Areaconocimiento> areasTempList;
        areasTempList = new ArrayList<Areaconocimiento>();
        System.out.println("Vemos SelectPlanes");
        if(selectPlanesEstudio!=null){
            if(selectPlanesEstudio.size()> 0){
                System.out.println("Mas de 0");
                for(String pesvigenciaPlan : selectPlanesEstudio){
                    System.out.println(pesvigenciaPlan);
                    //tenemos que enviar la clave del programa educativo y de que plan de estudios hablamos para traer los programas
                    //pesvigenciaPlan
                    areasTempList.addAll( filtrosBeanHelper.getConsultaDelegate().getAreasByPlanClave(  programaeducativo.getPedclave(), pesvigenciaPlan  ));
                }
            }
            else{
                System.out.println("Vacio");
            }
        }
        else{
            System.out.println("Nullo");
        }
                
        return areasTempList;
    }
    
    public List<Areaconocimiento> getAreasByPrograma(){
        List<Areaconocimiento> areasList = null;
        if(selectProgramEducativo!=null){
            if(selectProgramEducativo.size()>0){
            areasList = filtrosBeanHelper.getConsultaDelegate().getAreasByProgramaEducativoList(selectProgramEducativo);
            }
            else{
                areaconocimiento.setAcoid(0);
            }
        }
        else{
            areaconocimiento.setAcoid(0);
        }
        return areasList;
    }
    
    public List<Unidadaprendizaje> getUnidadByArea(){
        if(areaconocimiento.getAcoid()!=0){
            unidadaprendizaje.setUapid(0);
            return filtrosBeanHelper.getConsultaDelegate().getUnidadByArea(areaconocimiento.getAcoid());
        }else{
            unidadaprendizaje.setUapid(0);
            return new ArrayList<Unidadaprendizaje>();
        }
    }
    
    public List<Unidadaprendizaje> getUnidadesByAreaAconocimiento(){        
        List<Unidadaprendizaje> unidadesTempList;
        unidadesTempList = new ArrayList<Unidadaprendizaje>();
        System.out.println("Vemos SelectPlanes");
        if(selectAreaConocimiento!=null){
            if(selectAreaConocimiento.size()> 0){
                System.out.println("Mas de 0");
                for(String UnidadIDstr : selectAreaConocimiento){
                    System.out.println(UnidadIDstr);
                   unidadesTempList.addAll( filtrosBeanHelper.getConsultaDelegate().getUnidadByArea(Integer.parseInt(UnidadIDstr)) );       
                }
            }
            else{
                System.out.println("Vacio");
            }
        }
        else{
            System.out.println("Nullo");
        }       
        return unidadesTempList;
    }
    
    public List<Unidadaprendizaje> getUnidadesByAreaAconocimientoClave(){        
        List<Unidadaprendizaje> unidadesTempList;
        unidadesTempList = new ArrayList<Unidadaprendizaje>();
        System.out.println("Vemos SelectPlanes");
        if(selectAreaConocimiento!=null){
            if(selectAreaConocimiento.size()> 0){
                System.out.println("Mas de 0");
                for(String acoclaveStr : selectAreaConocimiento){
                    System.out.println(acoclaveStr);
                    unidadesTempList.addAll( filtrosBeanHelper.getConsultaDelegate().getUnidadByAreaClave(Integer.parseInt(acoclaveStr)) );       
                }
            }
            else{
                System.out.println("Vacio");
            }
        }
        else{
            System.out.println("Nullo");
        }    
        System.out.println("regresamos un tam de: ");
        System.out.println(unidadesTempList.size());
        return unidadesTempList;
    }
    
    public  List<Grupo> getGruposByUnidadAprendisajes (){
        List<Grupo> gruposTempList;
        gruposTempList = new ArrayList<Grupo>();
        if(selectUnidadAprendisaje!=null){
            if(selectUnidadAprendisaje.size()> 0){
                System.out.println("Mas de 0");
                for(String unidadAprenIDstr : selectUnidadAprendisaje){
                    System.out.println(unidadAprenIDstr);
                    gruposTempList.addAll( filtrosBeanHelper.getConsultaDelegate().getGrupoByUnidad(Integer.parseInt(unidadAprenIDstr)) );       
                }
            }
            else{
                System.out.println("Vacio");
            }
        }
        else{
            System.out.println("Nullo");
        }       
        return gruposTempList;
    }
    
    public  List<Grupo> getGruposByUnidadAprendisajesClave(){
        List<Grupo> gruposTempList;
        gruposTempList = new ArrayList<Grupo>();
        List<Grupo> gruposTempListAux;
        gruposTempListAux = new ArrayList<Grupo>();
        System.out.println("Entro a grupo pro unidad aprendisaje clave");
        if(selectUnidadAprendisaje!=null){
            if(selectUnidadAprendisaje.size()> 0){
                System.out.println("Mas de 0");
                System.out.println("parese que hay mas de 1");
                for(String uapclavestr : selectUnidadAprendisaje){
                    System.out.println(uapclavestr);
                    gruposTempListAux.addAll( filtrosBeanHelper.getConsultaDelegate().getGrupoByUnidadClave(Integer.parseInt(uapclavestr)) );       
                }
                //sorteamos los objetos y eliminamos repetidos
                int grp = 0;
                for(Grupo grupo :gruposTempListAux){
                    if(grupo.getGponumero()!=grp){
                        gruposTempList.add(grupo);
                        grp = grupo.getGponumero();
                    }
                }
            }
            else{
                System.out.println("Vacio");
            }
        }
        else{
            System.out.println("Nullo");
        }       
        return gruposTempList;
    }
    
    public List<Profesor> getProfesoresByUnidadAprendisaje(){
        List<Profesor> profesorTempList;
        profesorTempList = new ArrayList<Profesor>();
        System.out.println("Entro a get profesorbyunidadaprendisaje");
        if(selectUnidadAprendisaje!=null){
            if(selectUnidadAprendisaje.size()> 0){
                for(String uapclavestr : selectUnidadAprendisaje){
                    System.out.println(uapclavestr);
                    profesorTempList.addAll( filtrosBeanHelper.getConsultaDelegate().getProfesorByUnidadAprendisaje(Integer.parseInt(uapclavestr)) );       
                }
            }
            else{
                System.out.println("Vacio");
            }
        }
        else{
            System.out.println("Nullo");
        }   
        
        return profesorTempList;
    }
    
    public List<Profesor> getProfesoresByUnidadAprendisajeClave(){
        List<Profesor> profesorTempList;
        profesorTempList = new ArrayList<Profesor>();
        System.out.println("Entro a get profesorbyunidadaprendisaje");
        if(selectUnidadAprendisaje!=null){
            if(selectUnidadAprendisaje.size()> 0){
                for(String uapclavestr : selectUnidadAprendisaje){
                    System.out.println(uapclavestr);
                    profesorTempList.addAll( filtrosBeanHelper.getConsultaDelegate().getProfesorByUnidadAprendisajeClave(Integer.parseInt(uapclavestr)) );       
                }
            }
            else{
                System.out.println("Vacio");
            }
        }
        else{
            System.out.println("Nullo");
        }   
        
        return profesorTempList;
    }
    
//    public boolean disableBtnGenerarGrafico(){
//     
//        boolean disable=false;
//        
////        System.out.println("*****************Para checar disableBtnGenerarGrafico(1)***************************");
////        if(!(selectCicloEscolarList.isEmpty())){
////            System.out.println("ciclo escolar list no esta vacia");
////        }
////        if(unidadacademica.getUacclave()!=0){
////            System.out.println("unidad academica clave es diferente de cero");
////        }
////        if(!(ract.equalsIgnoreCase("Selecciona"))){
////            System.out.println("ract es igual a: "+ract);
////        }
////        if(programaeducativo.getPedclave()!=0){
////            System.out.println("clave Pe: "+programaeducativo.getPedclave());
////        }
////        System.out.println("*****************Para checar disableBtnGenerarGrafico(1)***************************");
//        
//        //if(((!selectProgramEducativo.isEmpty())||(programaeducativo.getPedclave()!=0))&&(!(selectCicloEscolarList.isEmpty()))&&unidadacademica.getUacclave()!=0&&(!(ract.equalsIgnoreCase("Selecciona")))){
//        
////      if(!(reporte==null)){  
////        if((!reporte.equalsIgnoreCase(""))&&((!selectProgramEducativo.isEmpty())&&((!(selectCicloEscolarList.isEmpty()))&&unidadacademica.getUacclave()!=0)&&(!(ract.equalsIgnoreCase("Selecciona"))))){    
////            disable=false;
////        }
////      }
////      
////      if(!(reporte==null)){  
////        if((!reporte.equalsIgnoreCase(""))&&(programaeducativo.getPedclave()!=0)&&(!(selectCicloEscolarList.isEmpty()))&&(!(selectPlanesEstudio.isEmpty()))&&(!(selectAreaConocimiento.isEmpty()))){    
////            //if((criterio.equalsIgnoreCase("area_conocimiento"))){
////            disable=false;
////            //}
////        }
////      }
////       
////      if(!(reporte==null)){  
////        if((!reporte.equalsIgnoreCase(""))&&(programaeducativo.getPedclave()!=0)&&(!(selectCicloEscolarList.isEmpty()))&&(!(selectPlanesEstudio.isEmpty()))&&(!(selectAreaConocimiento.isEmpty()))&&(!(selectUnidadAprendisaje.isEmpty()))&&(!(selectGrupo.isEmpty()))){    
////            //if((criterio.equalsIgnoreCase("unidad_aprendizaje"))){
////            disable=false;
////            //}
////        }
////      }
//      
//        //arreglo temporal:
//        //disable=false;
////      if(){
////       disable=true;   
////      }
//      
//        
//        return disable;
//    }
    
    public boolean disableBtnGenerarGrafico(){
     
        boolean disable=true;
        
//        System.out.println("*****************Para checar disableBtnGenerarGrafico(1)***************************");
//        if(!(selectCicloEscolarList.isEmpty())){
//            System.out.println("ciclo escolar list no esta vacia");
//        }
//        if(unidadacademica.getUacclave()!=0){
//            System.out.println("unidad academica clave es diferente de cero");
//        }
//        if(!(ract.equalsIgnoreCase("Selecciona"))){
//            System.out.println("ract es igual a: "+ract);
//        }
//        if(programaeducativo.getPedclave()!=0){
//            System.out.println("clave Pe: "+programaeducativo.getPedclave());
//        }
//        System.out.println("*****************Para checar disableBtnGenerarGrafico(1)***************************");
        
        //if(((!selectProgramEducativo.isEmpty())||(programaeducativo.getPedclave()!=0))&&(!(selectCicloEscolarList.isEmpty()))&&unidadacademica.getUacclave()!=0&&(!(ract.equalsIgnoreCase("Selecciona")))){
        
      if(!(reporte==null)){  
        if((!reporte.equalsIgnoreCase(""))&&((!selectProgramEducativo.isEmpty())&&((!(selectCicloEscolarList.isEmpty()))&&unidadacademica.getUacclave()!=0)&&(!(ract.equalsIgnoreCase("Selecciona"))))){    
            disable=false;
        }
      }
      
      if(!(reporte==null)){  
        if((!reporte.equalsIgnoreCase(""))&&(programaeducativo.getPedclave()!=0)&&(!(selectCicloEscolarList.isEmpty()))&&(!(selectPlanesEstudio.isEmpty()))&&(!(selectAreaConocimiento.isEmpty()))){    
            //if((criterio.equalsIgnoreCase("area_conocimiento"))){
            disable=false;
            //}
        }
      }
       
      if(!(reporte==null)){  
        if((!reporte.equalsIgnoreCase(""))&&(programaeducativo.getPedclave()!=0)&&(!(selectCicloEscolarList.isEmpty()))&&(!(selectPlanesEstudio.isEmpty()))&&(!(selectAreaConocimiento.isEmpty()))&&(!(selectUnidadAprendisaje.isEmpty()))&&(!(selectGrupo.isEmpty()))){    
            //if((criterio.equalsIgnoreCase("unidad_aprendizaje"))){
            disable=false;
            //}
        }
      }
        //arreglo temporal:
        //disable=false;
//      if(){
//       disable=true;   
//      }
      
        
        return disable;
    }
    
    public void mostrarUsuario(){
        usuario.getUsuid();
        usuario=filtrosBeanHelper.mostrarUsuario(usuario, rolSeleccionado);
        
        if(rolSeleccionado.getRoltipo().equalsIgnoreCase("Director")){            
            unidadacademica.setUacclave(140);
        }
    }
    
    public void arreglarRactUnico(){
        
        ArrayList<ReporteAvanceAux> listaAux5 = new ArrayList<ReporteAvanceAux>();
        
        if(numRact>0&&numRact<4&&(!(isPAGCTodosRacts))){
            for(ReporteAvanceAux report: listaAux){
                if(numRact==1){
                    report.fechaElaboracRact1=report.getReporteAvance().getRacfechaElaboracion();
                    report.porcentAvanceRact1=report.getReporteAvance().getRacavanceGlobal();
                    report.fechaLimiteRact1=report.fechaLimite;
                    report.statusRact1=report.getReporteAvance().getRacstatus();
                    //report.tipoReporteSelecRact1=;
                }
                if(numRact==2){
                    report.fechaElaboracRact2=report.getReporteAvance().getRacfechaElaboracion();
                    report.porcentAvanceRact2=report.getReporteAvance().getRacavanceGlobal();
                    report.fechaLimiteRact2=report.fechaLimite;
                    report.statusRact2=report.getReporteAvance().getRacstatus();
                    //report.tipoReporteSelecRact2=;
                }
                if(numRact==3){
                    report.fechaElaboracRact3=report.getReporteAvance().getRacfechaElaboracion();
                    report.porcentAvanceRact3=report.getReporteAvance().getRacavanceGlobal();
                    report.fechaLimiteRact3=report.fechaLimite;
                    report.statusRact3=report.getReporteAvance().getRacstatus();
                    //report.tipoReporteSelecRact3=;
                }
            }
            
        }
        
    }
    
    public void generarDatosListaAux(){
        // se usa un select y podemos definir de cual id del arreglo
        this.setNumRact(Integer.parseInt(ract));
        if(!(selectCicloEscolarList.isEmpty())){
        this.setCescicloEscolar(selectCicloEscolarList.get(0));
        }
        if(!(selectAreaConocimiento.isEmpty())){
        this.setAconClave(selectAreaConocimiento.get(0));
        }
        if(!(selectProgramEducativo.isEmpty())){
        this.setClavepe(selectProgramEducativo.get(0));
        }
        if(programaeducativo.getPedclave()!=0){
        this.setClavepe(programaeducativo.getPedclave());
        }
        if(!(selectPlanesEstudio.isEmpty())){
        this.setPesvigenciaR(selectPlanesEstudio.get(0));
        }
        if(!(selectGrupo.isEmpty())){
        this.setGrupoNumero(selectGrupo.get(0));
        }
        if(!(selectUnidadAprendisaje.isEmpty())){
        this.setUapclaveR(selectUnidadAprendisaje.get(0));
        }
        if(!(selectProfesor.isEmpty())){
        this.setNumeroEmpleado(selectProfesor.get(0));
        }
        botonCancelar=false;
    }
    
    public void enableBotonCancelar(){
        botonCancelar=true;
        
        reporte="";
        selectProgramEducativo.clear();
        selectCicloEscolarList.clear();
        unidadacademica.setUacclave(0);
        ract="Selecciona";
        programaeducativo.setPedclave(0);
        selectPlanesEstudio.clear();
        selectAreaConocimiento.clear();
        selectUnidadAprendisaje.clear();
        selectGrupo.clear();
        criterio=" ";
    }
    
    /*meotodos para el reporte grafico visual :D*/
        public void generarGrafico(){
        
        // se usa un select y podemos definir de cual id del arreglo
        this.setNumRact(Integer.parseInt(ract));
        if(!(selectCicloEscolarList.isEmpty())){
            this.setCescicloEscolar(selectCicloEscolarList.get(0));
        }
        if(!(selectAreaConocimiento.isEmpty())){
            this.setAconClave(selectAreaConocimiento.get(0));
        }
        if(!(selectProgramEducativo.isEmpty())){
            this.setClavepe(selectProgramEducativo.get(0));
        }
        if(programaeducativo.getPedclave()!=0){
            this.setClavepe(programaeducativo.getPedclave());
        }
        if(!(selectPlanesEstudio.isEmpty())){
            this.setPesvigenciaR(selectPlanesEstudio.get(0));
        }
        if(!(selectGrupo.isEmpty())){
            this.setGrupoNumero(selectGrupo.get(0));
        }
        if(!(selectUnidadAprendisaje.isEmpty())){
            this.setUapclaveR(selectUnidadAprendisaje.get(0));
        }
        if(!(selectProfesor.isEmpty())){
            this.setNumeroEmpleado(selectProfesor.get(0));
        }
        
        selectionReporteTipo();
        
        arreglarRactUnico();
                
        
        System.out.println("**************Variables actual(1):****************");
        System.out.println("numRact: "+numRact);
        System.out.println("cescicloEscolar: "+cescicloEscolar);
        System.out.println("acoclave: "+acoclave);
        System.out.println("clavepe: "+clavepe);
        //System.out.println("programaeducativo: "+programaeducativo.getPedclave());
        System.out.println("pesvigencia: "+pesvigencia);
        System.out.println("gponumero: "+gponumero);
        System.out.println("uapclave: "+uapclave);
        System.out.println("pronumeroempleado: "+pronumeroEmpleado);
        
        System.out.println("***************Variables anterior(2)*******************");
        System.out.println("**********Variables para ProgEd*********");
        System.out.println("ract: "+ract);
        int num=0;
        for(String ce: selectCicloEscolarList ){
            System.out.println("ciclo escolar"+num+": "+ce);
            num++;
        }
        num=0;
        for(String pe: selectProgramEducativo ){
            System.out.println("programa educativo"+num+": "+pe);
            num++;
        }
        System.out.println("programa educativo(no selectlist): "+programaeducativo.getPednombre());
        System.out.println("programa educativo(no selectlist) id: "+programaeducativo.getPedid());
        num=0;
        //no funciona - no implementado
        for(String pl: selectPlanesEstudio ){
            System.out.println("plan estudio"+num+": "+pl);
            num++;
        }
        System.out.println("planestudio vigencia: "+planestudio.getPesvigenciaPlan());
        System.out.println("planestudio id: "+planestudio.getPesid());
        //no funciona - no implementado
        System.out.println("unidad academica: "+unidadacademica.getUacnombre());
        System.out.println("unidad academica id: "+unidadacademica.getUacid());
        System.out.println("unidad academica clave: "+unidadacademica.getUacclave());
        System.out.println("**********Variables para ProgEd*********");
        System.out.println(" ");
        
        System.out.println("**********Variables para AreaCon*********");
        num=0;
        for(String ac: selectAreaConocimiento ){
            System.out.println("area conocimiento"+num+": "+ac);
            num++;
        }
        num=0;
        //no funciona - no implementado
        for(String pl: selectPlanesEstudio ){
            System.out.println("plan estudio"+num+": "+pl);
            num++;
        }
        //no funciona - no implementado
        System.out.println("**********Variables para AreaCon*********");
        
        mostrarBar=false;
        mostrarLine=false;
        mostrarPie=false;
        mostrarGauge = true;
        pieModel = initPieModel(); 
        if(reporte.equalsIgnoreCase("entregados")||
           reporte.equalsIgnoreCase("noentregados")||
           reporte.equalsIgnoreCase("entregadosynoentregados")){
            
            RactEntEsp = esperadosDelegate.getFullProgramaEdu(unidadacademica.getUacid(), Plan, Programa, Ciclo);
        }
        if(reporte.equalsIgnoreCase("entregadosatiempo")){
            RactEntEsp = esperadosDelegate.getAtiempoProgramaEdu(unidadacademica.getUacid(), Plan, Programa, Ciclo, "1", "2", "3");
            RactEsp = esperadosDelegate.getProgramaEduEsperados(unidadacademica.getUacid(), Plan, Programa, Plan);
        }
        if(reporte.equalsIgnoreCase("entregadosenfechalimite")){
            RactEntEsp = esperadosDelegate.getEnLimiteProgramaEdu(unidadacademica.getUacid(), Plan, Programa, Ciclo, "1", "2", "3");
            RactEsp = esperadosDelegate.getProgramaEduEsperados(unidadacademica.getUacid(), Plan, Programa, Plan);
        }
        if(reporte.equalsIgnoreCase("entregadosdespueslimite")){
            RactEntEsp = esperadosDelegate.getDespuesLimiteProgramaEdu(unidadacademica.getUacid(), Plan, Programa, Ciclo, "1", "2", "3");
            RactEsp = esperadosDelegate.getProgramaEduEsperados(unidadacademica.getUacid(), Plan, Programa, Plan);
        }
        CreateGeneralDataList();
        if(tipografico.equalsIgnoreCase("barras")){
            tituloGraficas = "Grafica de Barras";
            mostrarBar=true;
            mostrarPie=false;
            mostrarGauge=false;
            barModel = graficoDeacuerdoFiltrosBarras();
            lineModel = ClearCharts(1);
            gaugeModel = createMeterGaugeModels();
        }
        if(tipografico.equalsIgnoreCase("linea")){
            tituloGraficas = "Grafica de Lineas";
            mostrarLine=true;
            mostrarPie=false;
            lineModel = graficoDeacuerdoFiltrosLinear();
            barModel = ClearCharts(2);
        }
        if(tipografico.equalsIgnoreCase("pastel")){
            tituloGraficas = "Grafica de Pastel";
            mostrarPie=true;
            mostrarLine=false;
            mostrarBar=false;
            mostrarGauge=false;
            pieModel = graficoDeacuerdoFiltrosPastel();
            lineModel = ClearCharts(1);
            barModel = ClearCharts(2);
        } 
    }
    
    private CartesianChartModel ClearCharts(int op){
        CartesianChartModel model = new CartesianChartModel();
        if(op==1){
            LineChartSeries entregados = new LineChartSeries();
            entregados.setLabel("");
            entregados.set("", 0);
            this.title="";
            model.addSeries(entregados);
        }
        if(op==2){
            ChartSeries entregados = new ChartSeries();

            entregados.setLabel("");
            entregados.set("", 0);
            this.title="";
            model.addSeries(entregados);
        }
        return model;
    }
    
    private CartesianChartModel graficoDeacuerdoFiltrosBarras() {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries entregados = new ChartSeries();
        ChartSeries esperados = new ChartSeries();
        ChartSeries noentregados = new ChartSeries();
        //Reportes 
        if(criterio.equalsIgnoreCase("programa_educativo")){
            this.xaxisLabel = "Programas Educativos";
            if(reporte.compareTo("entregados")==0){
                tituloGraficas = tituloGraficas + " de Entregados por Programa Educativo";
                this.title = "Entregados";
                entregados.setLabel("Entregados");
            }
            if(reporte.compareTo("noentregados")==0){
                tituloGraficas = tituloGraficas + " de No entregados por Programa Educativo";
                this.title = "No entregados";
                entregados.setLabel("No entregados");
            }
            if(reporte.compareTo("entregadosynoentregados")==0){
                tituloGraficas = tituloGraficas + " de Entregados y no entregados por Programa Educativo";
                this.title = "Entregados y no entregados";
                entregados.setLabel("Entregados");
                noentregados.setLabel("No entregados");
            }
            if(reporte.compareTo("entregadosatiempo")==0){
                tituloGraficas = tituloGraficas + " de Entregados a tiempo por Programa Educativo";
                this.title = "Entregados a tiempo";
                entregados.setLabel("Entregados a tiempo");
            }
            if(reporte.compareTo("entregadosenfechalimite")==0){
                tituloGraficas = tituloGraficas + " de Entregados en limite por Programa Educativo";
                this.title = "Entregados en limite";
                entregados.setLabel("Entregados en limite");
            }
            if(reporte.compareTo("entregadosdespueslimite")==0){
                tituloGraficas = tituloGraficas + " de Entregados despues de limite por Programa Educativo";
                this.title = "Entregados despues de limite";
                entregados.setLabel("Entregados despues de limite");
            }
            esperados.setLabel("Esperados");
            // tengo que modificar este pars que me traiga los racs para la materia seleccionada
            //List<Programaeducativo> programasByUnidad = getProgramasByUnidad();
            selectProgramEducativo2 = new ArrayList<String>();
            int x=0;
            int max2=0;
            //for(Programaeducativo programa: programasByUnidad){
            //    if( selectProgramEducativo.contains(programa.getPedid().toString())  ) {
                    String[] aux;
                    String[] aux2 = null;
                    int num = -1;
                    int esperados2=0;
                    for (String RactEntEsp1 : RactEntEsp) {
                        aux = RactEntEsp1.split("-");
                        if(reporte.equalsIgnoreCase("entregadosatiempo")||reporte.equalsIgnoreCase("entregadosenfechalimite")||reporte.equalsIgnoreCase("entregadosdespueslimite")){
                            num++;
                            aux2 = RactEsp.get(num).split("-");
                        }
                //        if(aux[0].equalsIgnoreCase(programa.getPednombre())){
                            if(reporte.compareTo("entregados")==0 ||reporte.compareTo("entregadosynoentregados")==0||reporte.compareTo("entregadosatiempo")==0||reporte.compareTo("entregadosenfechalimite")==0||reporte.compareTo("entregadosdespueslimite")==0)
                                entregados.set(aux[0], Integer.parseInt(aux[1]));//se agrega el category y su valor
                            if(reporte.compareTo("noentregados")==0)
                                entregados.set(aux[0], Integer.parseInt(aux[2])-Integer.parseInt(aux[1]));
                            if(reporte.compareTo("entregadosynoentregados")==0)    
                                noentregados.set(aux[0], Integer.parseInt(aux[2])-Integer.parseInt(aux[1]));
                            if(reporte.equalsIgnoreCase("entregadosatiempo")||reporte.equalsIgnoreCase("entregadosenfechalimite")||reporte.equalsIgnoreCase("entregadosdespueslimite")){
                                esperados.set(aux[0], Integer.parseInt(aux2[1]));
                                esperados2 = Integer.parseInt(aux2[1]);
                            }else{
                                esperados.set(aux[0], Integer.parseInt(aux[2]));
                                esperados2 = Integer.parseInt(aux[2]); 
                            }
                            if(esperados2>max2){
                                max2=esperados2;
                            }
                            
                  //      }
                    }
                    //selectProgramEducativo2.add(programa.getPednombre());
              //  }
            //}
            max2 = max2+11;
            max = Integer.toString(max2);
            model.addSeries(entregados); 
            if(reporte.compareTo("entregadosynoentregados")==0)
                model.addSeries(noentregados);
            model.addSeries(esperados);
            System.out.println("Ver si hay valores seleccionados");
            for (String idprograma : selectProgramEducativo) {
                System.out.println("Existe el: "+idprograma );
            }
            if(selectProgramEducativo.isEmpty()){
                System.out.println("esta totalmente vacio");
            }
        }
        return model;
    }
    
    private CartesianChartModel graficoDeacuerdoFiltrosLinear(){
        CartesianChartModel model = new CartesianChartModel();
        LineChartSeries entregados = new LineChartSeries();
        LineChartSeries esperados = new LineChartSeries();
        LineChartSeries noentregados = new LineChartSeries();
        if(criterio.equalsIgnoreCase("programa_educativo")){
            this.xaxisLabel = "Programas Educativos";
            if(reporte.compareTo("entregados")==0){
                tituloGraficas = tituloGraficas + " de Entregados por Programa Educativo";
                this.title = "Entregados";
                entregados.setLabel("Entregados");
            }
            if(reporte.compareTo("noentregados")==0){
                tituloGraficas = tituloGraficas + " de No entregados por Programa Educativo";
                this.title = "No entregados";
                entregados.setLabel("No entregados");
            }
            if(reporte.compareTo("entregadosynoentregados")==0){
                tituloGraficas = tituloGraficas + " de Entregados y no entregados por Programa Educativo";
                this.title = "Entregados y no entregados";
                entregados.setLabel("Entregados");
                noentregados.setLabel("No entregados");
            }
            esperados.setLabel("Esperados");
            
            List<Programaeducativo> programasByUnidad = getProgramasByUnidad();
            selectProgramEducativo2 = new ArrayList<String>();
            int x=0;
            int max2=0;
            for(Programaeducativo programa: programasByUnidad){
                if( selectProgramEducativo.contains(programa.getPedid().toString())  ) {
                    String[] aux;
                    for (String RactEntEsp1 : RactEntEsp) {
                        aux = RactEntEsp1.split("-");
                        if(aux[0].equalsIgnoreCase(programa.getPednombre())){
                            if(reporte.compareTo("entregados")==0 ||reporte.compareTo("entregadosynoentregados")==0)
                                entregados.set(programa.getPednombre(), Integer.parseInt(aux[1]));//se agrega el category y su valor
                            if(reporte.compareTo("noentregados")==0)
                                entregados.set(programa.getPednombre(), Integer.parseInt(aux[2])-Integer.parseInt(aux[1]));
                            if(reporte.compareTo("entregadosynoentregados")==0)    
                                noentregados.set(programa.getPednombre(), Integer.parseInt(aux[2])-Integer.parseInt(aux[1]));
                            esperados.set(programa.getPednombre(), Integer.parseInt(aux[2]));
                            int esperados2 = Integer.parseInt(aux[2]);
                            if(esperados2>max2){
                                max2=esperados2;
                            }
                        }
                    }
                    selectProgramEducativo2.add(programa.getPednombre());
                }
            }
            max2 = max2+10;
            max = Integer.toString(max2);
            model.addSeries(entregados);
            if(reporte.compareTo("entregadosynoentregados")==0)
                model.addSeries(noentregados);
            model.addSeries(esperados);  
            System.out.println("Ver si hay valores seleccionados");
            for (String idprograma : selectProgramEducativo) {
                System.out.println("Existe el: "+idprograma );
            }
            if(selectProgramEducativo.isEmpty()){
                System.out.println("esta totalmente vacio");
            }
        }
        return model;
    }
    
    private PieChartModel graficoDeacuerdoFiltrosPastel() {     
        this.title = "Entregados";
        this.xaxisLabel = "Programas Educativos";
        tituloGraficas = tituloGraficas + " de Entregados por Programa Educativo";
        String[] aux;
        for (String RactEntEsp1 : RactEntEsp) {
            aux = RactEntEsp1.split("-");
            pieModel.set(aux[0], Integer.parseInt(aux[1])); 
        }
        return pieModel;
    }
    
    public List<SemaforoProgEd> getSemProgEd() {
        MeterProgEdu();
        return semProgEd;
    }

public MeterGaugeChartModel getGaugeModel() {
        return gaugeModel;
    }

//------------------------------------------------------------------------------
    private MeterGaugeChartModel createMeterGaugeModels() {
        List<Number> intervals = new ArrayList<Number>(){
            {//intervals indica los limites de los colores
                add(0);
                add(80);
                add(95);
                add(100); 
            }
        };
        List<Number> ticks = new ArrayList<Number>(){
            {//thicks maneja las labels de los indicadores
                add(0);
                add(10);
                add(20);
                add(30);
                add(40);
                add(50);
                add(60);
                add(70);
                add(80);
                add(90);
                add(100);
            }
        };
        MeterGaugeChartModel semaforo = new MeterGaugeChartModel();
        semaforo.setIntervals(intervals);
        semaforo.setTicks(ticks);
        //EsperadosDelegate delegate = new EsperadosDelegate();
        System.out.println("****************************************************\n************************************");
        //System.out.println("Valor de ua id= "+unidadacademica.getUacid());
        double val = esperadosDelegate.getSemaforo(unidadacademica.getUacid());
        System.out.println("Valor de val= "+val);
        semaforo.setValue(val);
        return semaforo;
    }
    
    public void MeterProgEdu(){
        //List<MeterGaugeChartModel> semaforosProgEd = new ArrayList<MeterGaugeChartModel>();
        ArrayList<String> progEd = esperadosDelegate.getSemadoroProgEd(unidadacademica.getUacid());
        List<Number> intervals = new ArrayList<Number>(){
            {//intervals indica los limites de los colores
                add(0);
                add(80);
                add(95);
                add(100); 
            }
        }; 
        List<Number> ticks = new ArrayList<Number>(){
                {//thicks maneja las labels de los indicadores
                    add(0);
                    add(10);
                    add(20);
                    add(30);
                    add(40);
                    add(50);
                    add(60);
                    add(70);
                    add(80);
                    add(90);
                    add(100);
                }
            };
        MeterGaugeChartModel semaforo;
        SemaforoProgEd data;
        for(String prog:progEd){
            String[] aux = prog.split("-");
            semaforo = new MeterGaugeChartModel();
            semaforo.setIntervals(intervals);
            semaforo.setTicks(ticks);
            semaforo.setValue(Double.parseDouble(aux[1]));
            data = new SemaforoProgEd(semaforo,aux[0]);
            semProgEd.add(data);
        }
    }
    
    public void mostrarProgEd(){
        mostrarBar=true;
    }
//------------------------------------------------------------------------------    
   
    public void setAconClave(String _AconClave)
    {
        acoclave = Integer.parseInt(_AconClave);
    }
    
    public void setClavepe(String _Clavepe)
    {
        clavepe = Integer.parseInt(_Clavepe);
    }
    
    public void setPesvigenciaR(String _Pesvigencia)
    {
        pesvigencia = _Pesvigencia;
    }
    
    public void setUapclaveR(String _uapclave)
    {
        uapclave = Integer.parseInt(_uapclave);
    }
     
    public void setGrupoNumero(String _gponumero)
    {
        gponumero = Integer.parseInt(_gponumero);
    }
     
    public void setNumeroEmpleado(String _pronumeroEmpleado)
    {
     pronumeroEmpleado = Integer.parseInt(_pronumeroEmpleado);
    }
    
    public List<Grupo> getGruposByProfesorUnidadAprendisaje(){
        List<Grupo> grupoTempList;
        List<Grupo> grupoTempList2;
        grupoTempList = new ArrayList<Grupo>();
        grupoTempList2 = new ArrayList<Grupo>();
        System.out.println("Entro a get profesorbyunidadaprendisaje");
        if(selectProfesor !=null){
            if(selectUnidadAprendisaje.size()> 0){
                for(String uapclavestr : selectUnidadAprendisaje){
                    for(String pronumeroEmpleado : selectProfesor){
                        grupoTempList2.addAll( filtrosBeanHelper.getConsultaDelegate().getGrupoByProfesorUnidadAprendisaje(Integer.parseInt(pronumeroEmpleado),Integer.parseInt(uapclavestr)) );       
                    }
                }
                int grupoNum = 0;
                for(Grupo grup :  grupoTempList2){
                    if(grupoNum!=grup.getGponumero()){
                        grupoTempList.add(grup);
                        grupoNum = grup.getGponumero();
                    }
                }   
            }
            else{
                System.out.println("Vacio");
            }
        }
        else{
            System.out.println("Nullo");
        }   
        
        return grupoTempList;
    }
    
   public void Export2ExcelSemaforoProgramasEd() throws Throwable {
       //variables listaux
        ReporteAux reporteUI=new ReporteAux(); 
       
        // objeto de hoja de excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        String nombreLibro = "";
        //guardamos imagen UABC en Documento
        URL location = FiltrosBeanUI.class.getProtectionDomain().getCodeSource().getLocation();
        String path = location.getFile();
        String replace = path.replace("FiltrosBeanUI.class", "uabclogo.png");
        InputStream uabc_image = new FileInputStream(replace);
        //InputStream uabc_image = new FileInputStream("C://decode/uabclogo.png");
        //InputStream uabc_image = new FileInputStream("/home/user/decode/uabclogo.png");
        byte[] bytes = IOUtils.toByteArray(uabc_image);
        int uabcLogo = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
        uabc_image.close();
        
        //definimos estilo a celda titulo de los encabezados con logo escuela
        HSSFCellStyle style = workbook.createCellStyle();        
        HSSFFont font = workbook.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        font.setFontHeightInPoints((short) 20);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.GREEN.index);
        style.setFont(font);
        
        
        
        //setCellStyle(style); 
        
        //definimos los bordes de celdas en tabla
        HSSFCellStyle borderstabla = workbook.createCellStyle();
        borderstabla.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        borderstabla.setBorderTop(HSSFCellStyle.BORDER_THIN);
        borderstabla.setBorderRight(HSSFCellStyle.BORDER_THIN);
        borderstabla.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        font = workbook.createFont();
        borderstabla.setFont(font);
        
        //definimos el estilo de las cabezeras de una tabla
        HSSFCellStyle headerTabla = workbook.createCellStyle();
        HSSFFont fontwit = workbook.createFont();
        fontwit.setColor(HSSFColor.WHITE.index);
        headerTabla.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerTabla.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerTabla.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerTabla.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerTabla.setFillForegroundColor(HSSFColor.GREEN.index);
        headerTabla.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headerTabla.setFont(fontwit);
        //aqui cambie
        headerTabla.setWrapText(true);
        //aqui cambie
       
        
        
        //if(criterio.equalsIgnoreCase("programa_educativo")){
            HSSFSheet sheet = workbook.createSheet("Programas Educativos");
            nombreLibro = "- Director"; 
            
          //  if(reporte.equalsIgnoreCase("entregados")){
                nombreLibro = "RACT General " + nombreLibro;
                
                //definimos encabezado
                sheet = cabezeraGeneralExcel(sheet,uabcLogo,style);
                
   
                HSSFRow row1 = sheet.createRow(7);
                row1.setHeight((short)600);
                HSSFCell cell = row1.createCell(1);
                cell.setCellValue("Concentrado de Reporte de Avance de Contenido Temático General  por Programa Educativo");
                cell.setCellStyle(style);
                
//                HSSFRow row1 = sheet.createRow(14);
//                row1.setHeightInPoints(56);
//                HSSFRow row2 = sheet.createRow(28);
//                row2.setHeightInPoints(56);
//                HSSFRow row3 = sheet.createRow(42);
//                row3.setHeightInPoints(56);
                
                
                //aqui cambie jesus ruelas
                //for(int i=0;i<28;i++){
//                 sheet.autoSizeColumn(1);
//                 sheet.autoSizeColumn(2);
//                 sheet.autoSizeColumn(3);
//                 sheet.autoSizeColumn(4);
                
                //merge cells de Total de RACT General de + ProgEd
                sheet.addMergedRegion(new CellRangeAddress(13,13,1,5));
                sheet.addMergedRegion(new CellRangeAddress(13,13,7,11));
                sheet.addMergedRegion(new CellRangeAddress(13,13,13,17));
                sheet.addMergedRegion(new CellRangeAddress(13,13,19,23));
                sheet.addMergedRegion(new CellRangeAddress(13,13,25,29));
                
                sheet.addMergedRegion(new CellRangeAddress(27,27,1,5));
                sheet.addMergedRegion(new CellRangeAddress(27,27,7,11));
                sheet.addMergedRegion(new CellRangeAddress(27,27,13,17));
                sheet.addMergedRegion(new CellRangeAddress(27,27,19,23));
                sheet.addMergedRegion(new CellRangeAddress(27,27,25,29));                
                
                sheet.addMergedRegion(new CellRangeAddress(41,41,1,5));
                sheet.addMergedRegion(new CellRangeAddress(41,41,7,11));
                sheet.addMergedRegion(new CellRangeAddress(41,41,13,17));
                sheet.addMergedRegion(new CellRangeAddress(41,41,19,23));
                sheet.addMergedRegion(new CellRangeAddress(41,41,25,29));
                
                sheet.addMergedRegion(new CellRangeAddress(55,55,1,5));
                sheet.addMergedRegion(new CellRangeAddress(55,55,7,11));
                sheet.addMergedRegion(new CellRangeAddress(55,55,13,17));
                sheet.addMergedRegion(new CellRangeAddress(55,55,19,23));
                sheet.addMergedRegion(new CellRangeAddress(55,55,25,29));
                
                sheet.addMergedRegion(new CellRangeAddress(69,69,1,5));
                sheet.addMergedRegion(new CellRangeAddress(69,69,7,11));
                sheet.addMergedRegion(new CellRangeAddress(69,69,13,17));
                sheet.addMergedRegion(new CellRangeAddress(69,69,19,23));
                sheet.addMergedRegion(new CellRangeAddress(69,69,25,29));
                
                //merge cells de programa educativo
                sheet.addMergedRegion(new CellRangeAddress(11,11,2,3));
                sheet.addMergedRegion(new CellRangeAddress(11,11,8,9));
                sheet.addMergedRegion(new CellRangeAddress(11,11,14,15));
                sheet.addMergedRegion(new CellRangeAddress(11,11,20,21));
                sheet.addMergedRegion(new CellRangeAddress(11,11,26,27));
                
                sheet.addMergedRegion(new CellRangeAddress(25,25,2,3));
                sheet.addMergedRegion(new CellRangeAddress(25,25,8,9));
                sheet.addMergedRegion(new CellRangeAddress(25,25,14,15));
                sheet.addMergedRegion(new CellRangeAddress(25,25,20,21));
                sheet.addMergedRegion(new CellRangeAddress(25,25,26,27));
                
                sheet.addMergedRegion(new CellRangeAddress(39,39,2,3));
                sheet.addMergedRegion(new CellRangeAddress(39,39,8,9));
                sheet.addMergedRegion(new CellRangeAddress(39,39,14,15));
                sheet.addMergedRegion(new CellRangeAddress(39,39,20,21));
                sheet.addMergedRegion(new CellRangeAddress(39,39,26,27));
                
                sheet.addMergedRegion(new CellRangeAddress(53,53,2,3));
                sheet.addMergedRegion(new CellRangeAddress(53,53,8,9));
                sheet.addMergedRegion(new CellRangeAddress(53,53,14,15));
                sheet.addMergedRegion(new CellRangeAddress(53,53,20,21));
                sheet.addMergedRegion(new CellRangeAddress(53,53,26,27));
                
                sheet.addMergedRegion(new CellRangeAddress(67,67,2,3));
                sheet.addMergedRegion(new CellRangeAddress(67,67,8,9));
                sheet.addMergedRegion(new CellRangeAddress(67,67,14,15));
                sheet.addMergedRegion(new CellRangeAddress(67,67,20,21));
                sheet.addMergedRegion(new CellRangeAddress(67,67,26,27));
                //}
                //aqui cambie jesus ruelas
                
                //mostraremos tabla con
                // Programas educativos //  Total Racs entregados // Total Esperados
                //preparamos informacion para insertar
                List<Programaeducativo> programasByUnidad = getProgramasByUnidad();
        List<Planestudio> planesByPrograma = getPlanesByPrograma();
                ArrayList<String> listaProgEdContar = esperadosDelegate.getSemadoroProgEdValor(1); 
                
                
                //setCellStyle(style); 
                
                //setExDat(sheet, 7, 1, "Concentrado de Reporte de Avance de Contenido Temático General  por Programa Educativo");
                
                //imrpiimiremos la tabla
                // Definimos los encabezados de la tabla
                
                int pos=0;
                
                int row=11;
                int col=1;
                
                Boolean bandPe=false;
                
                for (Programaeducativo pe : programasByUnidad) {
                  for (String peContar : listaProgEdContar) {

                   String[] contarEntEspProgEd;

                  if(listaProgEdContar.size()>=pos){ 
                   if (peContar.contains(pe.getPednombre())) {

                   contarEntEspProgEd = listaProgEdContar.get(pos).split("-");

                   setExDat(sheet, row, col, "Clave");
                   setExDat(sheet, row, col + 1, "Programa Educativo");
                   setExDat(sheet, row, col + 3, "Plan de Estudios");
                   setExDat(sheet, row, col + 4, "Responsable");

               //    if (contarEntEspProgEd[0].equalsIgnoreCase(programasByUnidad.get(0).getPednombre())) {

                       setExDat(sheet, row + 1, col, pe.getPedclave());
                       setExDat(sheet, row + 1, col + 1, pe.getPednombre());
                       if (!(planesByPrograma.isEmpty())) {
                           setExDat(sheet, row + 1, col + 2, planesByPrograma.get(0).getPesvigenciaPlan());
                       }
                       setExDat(sheet, row + 1, col + 3, "");

                //   }

                   setExDat(sheet, row + 2, col, "Total de RACT General de " + programasByUnidad.get(0).getPednombre());

                   setExDat(sheet, row + 3, col, "Resumen de totales \r\n por numero de RACT");
                   setExDat(sheet, row + 3, col + 1, "Total entregados");
                   setExDat(sheet, row + 3, col + 2, "%");
                   setExDat(sheet, row + 3, col + 3, "Total esperados");
                   setExDat(sheet, row + 3, col + 4, "%");

                   sheet.getRow(row+3).getCell(1);//aqui modifique jesus ruelas
                   
                   float porcentEnt = (Float.parseFloat(contarEntEspProgEd[1])) / (Float.parseFloat(contarEntEspProgEd[2])) * 100;

                   float porcentEsp = 100 - porcentEnt;

//                setExDat(sheet, 15, 1, "Todos los RACTS");
//                setExDat(sheet, 15, 2, contarEntEspProgEd[1]);
//                setExDat(sheet, 15, 3, "" + porcentEnt + "%");
//                setExDat(sheet, 15, 4, contarEntEspProgEd[2]);
//                setExDat(sheet, 15, 5, ""  + porcentEsp + "%");
                   //String[] contarEntEspProgEd;
                   String[] contarEntRact1;
                   String[] contarEntRact2;
                   String[] contarEntRact3;
                   int esperadosProgEdUnicoRact = 0;

                   contarEntRact1 = contarEntEspProgEd[3].split(":");
                   contarEntRact2 = contarEntEspProgEd[4].split(":");
                   contarEntRact3 = contarEntEspProgEd[5].split(":");

                   esperadosProgEdUnicoRact = (Integer.parseInt(contarEntEspProgEd[2])) / 3;

                   float contarPorcentEntRact1 = (Float.parseFloat(contarEntRact1[1])) / ((float) esperadosProgEdUnicoRact) * 100;
                   float contarPorcentEntRact2 = (Float.parseFloat(contarEntRact2[1])) / ((float) esperadosProgEdUnicoRact) * 100;
                   float contarPorcentEntRact3 = (Float.parseFloat(contarEntRact3[1])) / ((float) esperadosProgEdUnicoRact) * 100;

                   float contarPorcentEspRact1 = 100 - contarPorcentEntRact1;
                   float contarPorcentEspRact2 = 100 - contarPorcentEntRact2;
                   float contarPorcentEspRact3 = 100 - contarPorcentEntRact3;

                   setExDat(sheet, row + 4, col, "RACT 1");
                   setExDat(sheet, row + 4, col + 1, contarEntRact1[1]);
                   setExDat(sheet, row + 4, col + 2, "" + contarPorcentEntRact1 + "%");
                   setExDat(sheet, row + 4, col + 3, esperadosProgEdUnicoRact);
                   setExDat(sheet, row + 4, col + 4, "" + contarPorcentEspRact1 + "%");

                   setExDat(sheet, row + 5, col, "RACT 2");
                   setExDat(sheet, row + 5, col + 1, contarEntRact2[1]);
                   setExDat(sheet, row + 5, col + 2, "" + contarPorcentEntRact2 + "%");
                   setExDat(sheet, row + 5, col + 3, esperadosProgEdUnicoRact);
                   setExDat(sheet, row + 5, col + 4, "" + contarPorcentEspRact2 + "%");

                   setExDat(sheet, row + 6, col, "RACT 3");
                   setExDat(sheet, row + 6, col + 1, contarEntRact3[1]);
                   setExDat(sheet, row + 6, col + 2, "" + contarPorcentEntRact3 + "%");
                   setExDat(sheet, row + 6, col + 3, esperadosProgEdUnicoRact);
                   setExDat(sheet, row + 6, col + 4, "" + contarPorcentEspRact3 + "%");

                   setExDat(sheet, row + 7, col, "Todos los RACTS");
                   setExDat(sheet, row + 7, col + 1, contarEntEspProgEd[1]);
                   setExDat(sheet, row + 7, col + 2, "" + porcentEnt + "%");
                   setExDat(sheet, row + 7, col + 3, contarEntEspProgEd[2]);
                   setExDat(sheet, row + 7, col + 4, "" + porcentEsp + "%");

                   setStyleCell(sheet, headerTabla, row, col);
                   setStyleCell(sheet, headerTabla, row, col + 1);
                   setStyleCell(sheet, headerTabla, row, col + 2);
                   setStyleCell(sheet, headerTabla, row, col + 3);
                   setStyleCell(sheet, headerTabla, row, col + 4);

                   setStyleCell(sheet, headerTabla, row + 2, col);
                   setStyleCell(sheet, headerTabla, row + 2, col + 1);
                   setStyleCell(sheet, headerTabla, row + 2, col + 2);
                   setStyleCell(sheet, headerTabla, row + 2, col + 3);

                   setStyleCell(sheet, headerTabla, row + 3, col);
                   setStyleCell(sheet, headerTabla, row + 3, col + 1);
                   setStyleCell(sheet, headerTabla, row + 3, col + 2);
                   setStyleCell(sheet, headerTabla, row + 3, col + 3);
                   setStyleCell(sheet, headerTabla, row + 3, col + 4);

                   bandPe = true;
                   
                   pos++;
               }
               
              }
               if (bandPe == true) {
                   if (col >= 25) {
                       row = row + 14;
                       col = -5;
                   }
                   col = col + 6;
                   bandPe = false;
               }
           }
       }
                /*sheet.autoSizeColumn(2);
                sheet.autoSizeColumn(3);
                sheet.autoSizeColumn(4);*/
                
                //imprimimos la informacion en su lugar
                
                int prow = 14;
                 for(Programaeducativo programa: programasByUnidad){   
                       if( selectProgramEducativo.contains(programa.getPedid().toString())  ){
                           setExDat(sheet, prow, 17, programa.getPednombre());
                           setExDat(sheet, prow, 18, 75);
                           setExDat(sheet, prow, 19, 100);
                           prow++;
                       }                  
                }
          //  }
        //}
                 
        //finalizamos con
        //metodo para descargar el objeto
        System.out.println("Generando Excel");
        if(criterio!="" && reporte!=""){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.setResponseContentType("application/vnd.ms-excel");
            externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\""+nombreLibro+".xls\"");
            workbook.write(externalContext.getResponseOutputStream());
            facesContext.responseComplete();
        }
        else{
            System.out.println("No se Genero por: Criterio->"+criterio+" , Reporte->"+reporte);
        }
   } 
    
   public void Export2Excel() throws Throwable {
        //variables listaux
        ReporteAux reporteUI=new ReporteAux(); 
        
        // objeto de hoja de excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        String nombreLibro = "";
        //guardamos imagen UABC en Documento
        URL location = FiltrosBeanUI.class.getProtectionDomain().getCodeSource().getLocation();
        String path = location.getFile();
        String replace = path.replace("FiltrosBeanUI.class", "uabclogo.png");
        InputStream uabc_image = new FileInputStream(replace);
        //InputStream uabc_image = new FileInputStream("C://decode/uabclogo.png");
        //InputStream uabc_image = new FileInputStream("/home/user/decode/uabclogo.png");
        byte[] bytes = IOUtils.toByteArray(uabc_image);
        int uabcLogo = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
        uabc_image.close();
        
        //definimos estilo a celda titulo de los encabezados con logo escuela
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        font.setFontHeightInPoints((short) 20);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.GREEN.index);
        style.setFont(font);
        
        //definimos los bordes de celdas en tabla
        HSSFCellStyle borderstabla = workbook.createCellStyle();
        borderstabla.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        borderstabla.setBorderTop(HSSFCellStyle.BORDER_THIN);
        borderstabla.setBorderRight(HSSFCellStyle.BORDER_THIN);
        borderstabla.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        font = workbook.createFont();
        borderstabla.setFont(font);
        
        //definimos el estilo de las cabezeras de una tabla
        HSSFCellStyle headerTabla = workbook.createCellStyle();
        HSSFFont fontwit = workbook.createFont();
        fontwit.setColor(HSSFColor.WHITE.index);
        headerTabla.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerTabla.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerTabla.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerTabla.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerTabla.setFillForegroundColor(HSSFColor.GREEN.index);
        headerTabla.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headerTabla.setFont(fontwit);
       
        if(criterio.equalsIgnoreCase("programa_educativo")){
            HSSFSheet sheet = workbook.createSheet("Graficos");
            nombreLibro = "- Programa Educativo";
            
            if(reporte.equalsIgnoreCase("entregados")){
                nombreLibro = "Entregados " + nombreLibro;
                
                //definimos encabezado
                sheet = cabezeraGeneralExcel(sheet,uabcLogo,style);
                
                //mostraremos tabla con
                // Programas educativos //  Total Racs entregados // Total Esperados
                //preparamos informacion para insertar
                List<Programaeducativo> programasByUnidad = getProgramasByUnidad();
                
                //imrpiimiremos la tabla
                // Definimos los encabezados de la tabla
                setExDat(sheet, 13, 17, "Programa Educativo");
                setExDat(sheet, 13, 18, "Total de RACT Entregados");
                setExDat(sheet, 13, 19, "Total de RACT Esperados");
                setExDat(sheet, 13, 20, " ");
                
                setStyleCell(sheet, headerTabla, 13, 17);
                setStyleCell(sheet, headerTabla, 13, 18);
                setStyleCell(sheet, headerTabla, 13, 19);
                
                
                //imprimimos la informacion en su lugar
                
                int prow = 14;
                 for(Programaeducativo programa: programasByUnidad){   
                       if( selectProgramEducativo.contains(programa.getPedid().toString())  ){
                           setExDat(sheet, prow, 17, programa.getPednombre());
                           setExDat(sheet, prow, 18, 75);
                           setExDat(sheet, prow, 19, 100);
                           prow++;
                       }                  
                }
                //Se obtiene consulta con los parametros seleccionados
                ArrayList<String> entregados = esperadosDelegate.getFullProgramaEdu(unidadacademica.getUacid(), Plan, Programa, Ciclo);

                //agregamos programacion de grafico
                if(tipografico.equalsIgnoreCase("barras")){
                    BarChartCL demo1 = new BarChartCL("Estadísticas de Reportes Entregados por Programa Educativo",entregados,"ExcelPOIGrafica1");
                    demo1.addChartToExcel(demo1.createChart(demo1.createDataset()),workbook);
                }
                if(tipografico.equalsIgnoreCase("linea")){
                    LineChartDemo1 demo1 = new LineChartDemo1("Estadísticas de Reportes Entregados por Programa Educativo",entregados,"ExcelPOIGrafica1");
                    demo1.addChartToExcel(demo1.createChart(demo1.createDataset()),workbook);
                }
                if(tipografico.equalsIgnoreCase("pastel")){
                    PieChartDemo1 demo1 = new PieChartDemo1("Estadísticas de Reportes Entregados por Programa Educativo",entregados,"ExcelPOIGrafica1");
                    demo1.addChartToExcel(demo1.createChart(demo1.createDataset()),workbook);
                }
                 
                //Se acaba grafico
                //recorreremos todo el list aux para revisar los programas educativos que utiliza
                //aun que podemos usar el selectProgramaeducativo
                 
                /* Programacion de Hojas*/
                for(Programaeducativo programa: programasByUnidad){
                    if(selectProgramEducativo.contains( Integer.toString(programa.getPedclave()) )){
                    //aqui creamos la hoja para el programa
                    sheet = workbook.createSheet(programa.getPednombre());
                    //sheet = cabezeraGeneralExcel(sheet,uabcLogo,style); lo moveremos al final de la hoja para que no sea mdoficada la imagen
                    setExDat(sheet, 8, 3, "Concentrado de Reporte de Avance de Contenido Temático de Entregados por Programa Educativo");
                    
                    boolean autotam = true;
                    
                    //informacion de programa educativo tabla
                    setExDat(sheet, 14, 1, "Clave");
                    setExDat(sheet, 14, 2, "Programa Educativo");
                    setExDat(sheet, 14, 3, "Plan de Estudios");
                    setExDat(sheet, 14, 4, "Responsable");
                    
                    setStyleCell(sheet, headerTabla, 14, 1);
                    setStyleCell(sheet, headerTabla, 14, 2);
                    setStyleCell(sheet, headerTabla, 14, 3);
                    setStyleCell(sheet, headerTabla, 14, 4);
                                    
                    //llenado de informacion
                    // se tiene imprimir por cada plan de estudios???
                    
//                    Responsableprogramaeducativo responsable = filtrosBeanHelper.getConsultaDelegate().getResponsableProgramaEducativoByID(programa.getPedid());
                    
                    setExDat(sheet, 15, 1, programa.getPedclave());
                    setExDat(sheet, 15, 2, programa.getPednombre());
                    setExDat(sheet, 15, 3, "");      
                    setExDat(sheet, 15, 4, "");
                    setStyleCell(sheet, borderstabla, 15, 1);
                    setStyleCell(sheet, borderstabla, 15, 2);
                    setStyleCell(sheet, borderstabla, 15, 3);
                    setStyleCell(sheet, borderstabla, 15, 4);
                    
                    List<Planestudio> planes = filtrosBeanHelper.getConsultaDelegate().getPlanesByPrograma(programa.getPedid());
                    boolean uno = true;
                    int currow = 20;
                    
                    Planestudio planeact = new Planestudio();       
                    for(Planestudio planeactn : planes){
                        planeact = planeactn;
                        break;
                    }
                    
                    ReporteAvanceAux tempAux = new ReporteAvanceAux();       
                    for(ReporteAvanceAux aux : listaAux){
                        tempAux = aux;
                        break;
                    }
                       
                    //intentaremos usar un solo plan
                            setExDat(sheet, currow , 1, "Áreas de conocimiento");
                            setStyleCell(sheet, headerTabla, currow, 1);
                            currow++;
                            //cada plan de estudios tiene varias areas de conocimiento
                            //List<Areaconocimiento> areasConocimiento = filtrosBeanHelper.getConsultaDelegate().getAreasByPlan( tempAux.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesid() );
                            //List<Areaconocimiento> areasConocimiento = filtrosBeanHelper.getConsultaDelegate().getAreasByPlan( planeact.getPesid() );
                            List<Areaconocimiento> areasConocimiento = filtrosBeanHelper.getConsultaDelegate().getAreasByPlanClave(programa.getPedclave(),tempAux.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesvigenciaPlan());
                            
                            
                            for(Areaconocimiento areaCon : areasConocimiento){
                                //clave , area de conocimiento, responsable
                                setExDat(sheet, currow,1, "Clave" );
                                setExDat(sheet, currow,2, "Área de conocimiento" );
                                setExDat(sheet, currow,3, "Responsable" );
                                setStyleCell(sheet, headerTabla, currow, 1);
                                setStyleCell(sheet, headerTabla, currow, 2);
                                setStyleCell(sheet, headerTabla, currow, 3);
                                
                                currow++;
                                setExDat(sheet, currow,1, areaCon.getAcoclave() ); //clave
                                setExDat(sheet, currow,2, areaCon.getAconombre() ); //area conociminto
                                setExDat(sheet, currow,3, "" ); //responsable
                               
                                
                                currow+=2;
                                //obtenemos las unidades de aprendisake
                                setExDat(sheet, currow,1, "Clave unidad de aprendizaje" );
                                setExDat(sheet, currow,2, "Unidad de aprendizaje" );
                                setExDat(sheet, currow,3, "No. de empleado" );
                                setExDat(sheet, currow,4, "Nombre del profesor" );
                                setExDat(sheet, currow,5, "Grupo" );
                                setExDat(sheet, currow,6, "% Avance 1er reporte" );
                                setExDat(sheet, currow,7, "Fecha de elaboración 1er RACT" );
                                setExDat(sheet, currow,8, "% Avance 2do reporte" );
                                setExDat(sheet, currow,9, "Fecha de elaboración 2do RACT" );
                                setExDat(sheet, currow,10, "% Avance 3er reporte" );
                                setExDat(sheet, currow,11, "Fecha de elaboración 3er RACT" );
                                
                                //autosize para la columna
                                if(autotam){
                                    sheet.autoSizeColumn(2);
                                    sheet.autoSizeColumn(4);
                                    sheet.autoSizeColumn(11);
                                    autotam = false;
                                }
                                
                                
                                // para formatear toda la linea
                                for(int i=1;i<=11;i++){
                                    //tenemos qu aajustar texto
                                    //tenemos que centrar el texto
                                    //cambiar el color de fondo
                                  setStyleCell(sheet, headerTabla, currow, i);  
                                }
                                                             
                                currow++;
                                //List<Unidadaprendizaje> unidadesAprendisaje = filtrosBeanHelper.getConsultaDelegate().getUnidadByArea(areaCon.getAcoclave());
                                    
                                    //de ls listaAux separamos los los reportes que correspondan a la unidad de aprendisaje acutal
                                    ArrayList <ReporteAvanceAux> tempListAux = new ArrayList<ReporteAvanceAux>();        
                                    for(ReporteAvanceAux aux : listaAux){
                                        if( aux.getAreaConocimiento().getAcoclave() == areaCon.getAcoclave() && 
                                                programa.getPedclave() == aux.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getProgramaeducativo().getPedclave() 
                                                )
                                        {
                                            tempListAux.add(aux);
                                        }
                                    }
                                    
                                    //List<UnidadaprendizajeImparteProfesor> unidadesProfesor  = filtrosBeanHelper.getConsultaDelegate().getUnidadesAprendisajeImparteProf(unidadApren.getUapid());
                                    int uniprofeTemp = 0 ; 
                                    for(ReporteAvanceAux auxRacs :tempListAux){
                                            
                                        String nompreP = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getPronombre();
                                        String apellidoPP = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoPaterno();
                                        String apellidoPM = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoMaterno();
                                        int claveUnidadApren = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave();
                                        String nombreUnidad = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapnombre();

                                        uniprofeTemp = claveUnidadApren;
                                        setExDat(sheet, currow,1, claveUnidadApren ); //clave
                                        setExDat(sheet, currow,2, nombreUnidad ); //nombre unidad
                                        //reporteAvance.unidadaprendizajeImparteProfesor.profesor.pronumeroEmpleado
                                        setExDat(sheet, currow,3, auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getPronumeroEmpleado() ); //numero empleado
                                        setExDat(sheet, currow,4, apellidoPP+ " "+apellidoPM+ " "+nompreP ); //nombre maestro
                                        setExDat(sheet, currow,5, auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getGponumero() + "-" + auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUiptipoSubgrupo()  + "-"+ auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUipsubgrupo() ); //grupo numero

                                        //marcamos bordes
                                        for(int i=1;i<=5;i++){
                                            setStyleCell(sheet, borderstabla, currow, i);
                                        }
                                        //tienne que ser entregados/enviados
                                        if(auxRacs.getStatusRact1()!=null){
                                            if(auxRacs.getStatusRact1().equalsIgnoreCase("Enviado")){
                                                System.out.println("Entro a enviados 1");
                                                setExDat(sheet, currow,(4 + (1*2)), auxRacs.getPorcentAvanceRact1() ); //% avance 7**
                                                setExDat(sheet, currow,(4 + (1*2)+1), auxRacs.getFechaElaboracRact1().toString() ); //fecha elabora 8***
                                                setStyleCell(sheet, borderstabla, currow, (4 + (1*2)));
                                                setStyleCell(sheet, borderstabla, currow, (4 + (1*2)+1));
                                            }else{
                                                setExDat(sheet, currow,(4 + (1*2)), " " ); //% avance 7**
                                                setExDat(sheet, currow,(4 + (1*2)+1), " " ); //fecha elabora 8***
                                                setStyleCell(sheet, borderstabla, currow, (4 + (1*2)));
                                                setStyleCell(sheet, borderstabla, currow, (4 + (1*2)+1));
                                            }
                                        }
                                        else{
                                            setExDat(sheet, currow,(4 + (1*2)), " " ); //% avance 7**
                                            setExDat(sheet, currow,(4 + (1*2)+1), " " ); //fecha elabora 8***
                                            setStyleCell(sheet, borderstabla, currow, (4 + (1*2)));
                                            setStyleCell(sheet, borderstabla, currow, (4 + (1*2)+1));
                                        }
                                        
                                        
                                        if(auxRacs.getStatusRact2()!=null){
                                            if(auxRacs.getStatusRact2().equalsIgnoreCase("Enviado")){
                                                setExDat(sheet, currow,(4 + (2*2)), auxRacs.getPorcentAvanceRact2() ); //% avance 7**
                                                setExDat(sheet, currow,(4 + (2*2)+1), auxRacs.getFechaElaboracRact2().toString() ); //fecha elabora 8***
                                                setStyleCell(sheet, borderstabla, currow, (4 + (2*2)));
                                                setStyleCell(sheet, borderstabla, currow, (4 + (2*2)+1));
                                            }else{
                                                setExDat(sheet, currow,(4 + (2*2)), " " ); //% avance 7**
                                                setExDat(sheet, currow,(4 + (2*2)+1), " " ); //fecha elabora 8***
                                                setStyleCell(sheet, borderstabla, currow, (4 + (2*2)));
                                                setStyleCell(sheet, borderstabla, currow, (4 + (2*2)+1));
                                            }
                                        }else{
                                            setExDat(sheet, currow,(4 + (2*2)), " " ); //% avance 7**
                                            setExDat(sheet, currow,(4 + (2*2)+1), " " ); //fecha elabora 8***
                                            setStyleCell(sheet, borderstabla, currow, (4 + (2*2)));
                                            setStyleCell(sheet, borderstabla, currow, (4 + (2*2)+1));
                                        }
                                        
                                        if(auxRacs.getStatusRact3()!=null){
                                            if(auxRacs.getStatusRact3().equalsIgnoreCase("Enviado")){
                                                setExDat(sheet, currow,(4 + (3*2)), auxRacs.getPorcentAvanceRact3() ); //% avance 7**
                                                setExDat(sheet, currow,(4 + (3*2)+1), auxRacs.getFechaElaboracRact3().toString() ); //fecha elabora 8***
                                                setStyleCell(sheet, borderstabla, currow, (4 + (3*2)));
                                                setStyleCell(sheet, borderstabla, currow, (4 + (3*2)+1));
                                            }else{
                                                setExDat(sheet, currow,(4 + (3*2)), " "  ); //% avance 7**
                                                setExDat(sheet, currow,(4 + (3*2)+1), " " ); //fecha elabora 8***
                                                setStyleCell(sheet, borderstabla, currow, (4 + (3*2)));
                                                setStyleCell(sheet, borderstabla, currow, (4 + (3*2)+1));
                                            }
                                        }
                                        else{
                                            setExDat(sheet, currow,(4 + (3*2)), " "  ); //% avance 7**
                                            setExDat(sheet, currow,(4 + (3*2)+1), " " ); //fecha elabora 8***
                                            setStyleCell(sheet, borderstabla, currow, (4 + (3*2)));
                                            setStyleCell(sheet, borderstabla, currow, (4 + (3*2)+1));   
                                        }
                                        
                                        currow++;
                                    }
                                    
                                
                                
                                currow+=2;
                            }
                            
                                                 
                            sheet = cabezeraGeneralExcel(sheet,uabcLogo,style);
                    }// fin del la comparacon de plan
                }// fin del for por plan de estudios
                 
                 
            }// fin de si es por entregados if(entregados)
            
            if(reporte.equalsIgnoreCase("noentregados")){
                nombreLibro = "No Entregados " + nombreLibro;
                
                //definimos encabezado
                sheet = cabezeraGeneralExcel(sheet,uabcLogo,style);
                
                //mostraremos tabla con
                // Programas educativos //  Total Racs entregados // Total Esperados
                //preparamos informacion para insertar
                List<Programaeducativo> programasByUnidad = getProgramasByUnidad();
                
                //imrpiimiremos la tabla
                // Definimos los encabezados de la tabla
                setExDat(sheet, 13, 17, "Programa Educativo");
                setExDat(sheet, 13, 18, "Total de RACT Entregados");
                setExDat(sheet, 13, 19, "Total de RACT Esperados");
                setExDat(sheet, 13, 20, " ");
                
                setStyleCell(sheet, headerTabla, 13, 17);
                setStyleCell(sheet, headerTabla, 13, 18);
                setStyleCell(sheet, headerTabla, 13, 19);
                
                /*sheet.autoSizeColumn(2);
                sheet.autoSizeColumn(3);
                sheet.autoSizeColumn(4);*/
                
                //imprimimos la informacion en su lugar
                
                int prow = 14;
                 for(Programaeducativo programa: programasByUnidad){   
                       if( selectProgramEducativo.contains(programa.getPedid().toString())  ){
                           setExDat(sheet, prow, 17, programa.getPednombre());
                           setExDat(sheet, prow, 18, 75);
                           setExDat(sheet, prow, 19, 100);
                           prow++;
                       }                  
                }
                //Se obtiene consulta con los parametros seleccionados
                ArrayList<String> entregados = esperadosDelegate.getFullProgramaEdu(unidadacademica.getUacid(), Plan, Programa, Ciclo);

                //agregamos programacion de grafico
                if(tipografico.equalsIgnoreCase("barras")){
                    BarChartCL demo1 = new BarChartCL("Estadísticas de Reportes Entregados por Programa Educativo",entregados,"ExcelPOIGrafica1");
                    demo1.addChartToExcel(demo1.createChart(demo1.createDataset()),workbook);
                }
                if(tipografico.equalsIgnoreCase("linea")){
                    LineChartDemo1 demo1 = new LineChartDemo1("Estadísticas de Reportes Entregados por Programa Educativo",entregados,"ExcelPOIGrafica1");
                    demo1.addChartToExcel(demo1.createChart(demo1.createDataset()),workbook);
                }
                if(tipografico.equalsIgnoreCase("pastel")){
                    PieChartDemo1 demo1 = new PieChartDemo1("Estadísticas de Reportes Entregados por Programa Educativo",entregados,"ExcelPOIGrafica1");
                    demo1.addChartToExcel(demo1.createChart(demo1.createDataset()),workbook);
                }
                 
                //Se acaba grafico
                //recorreremos todo el list aux para revisar los programas educativos que utiliza
                //aun que podemos usar el selectProgramaeducativo
                 
                /* Programacion de Hojas*/
                for(Programaeducativo programa: programasByUnidad){
                    if(selectProgramEducativo.contains( Integer.toString(programa.getPedclave()) )){
                    sheet = workbook.createSheet(programa.getPednombre());
                    sheet = cabezeraGeneralExcel(sheet,uabcLogo,style);
                    setExDat(sheet, 8, 3, "Concentrado de Reporte de Avance de Contenido Temático de Entregados por Programa Educativo");
                    
                    boolean autotam = true;
                    //informacion de programa educativo tabla
                    setExDat(sheet, 14, 1, "Clave");
                    setExDat(sheet, 14, 2, "Programa Educativo");
                    setExDat(sheet, 14, 3, "Plan de Estudios");
                    setExDat(sheet, 14, 4, "Responsable");
                    
                    setStyleCell(sheet, headerTabla, 14, 1);
                    setStyleCell(sheet, headerTabla, 14, 2);
                    setStyleCell(sheet, headerTabla, 14, 3);
                    setStyleCell(sheet, headerTabla, 14, 4);
                                    
                    //llenado de informacion
                    // se tiene imprimir por cada plan de estudios???
                    
//                    Responsableprogramaeducativo responsable = filtrosBeanHelper.getConsultaDelegate().getResponsableProgramaEducativoByID(programa.getPedid());
                    
                    setExDat(sheet, 15, 1, programa.getPedclave());
                    setExDat(sheet, 15, 2, programa.getPednombre());
                    setExDat(sheet, 15, 3, "");      
                    setExDat(sheet, 15, 4, "");
                    setStyleCell(sheet, borderstabla, 15, 1);
                    setStyleCell(sheet, borderstabla, 15, 2);
                    setStyleCell(sheet, borderstabla, 15, 3);
                    setStyleCell(sheet, borderstabla, 15, 4);
                    
                    List<Planestudio> planes = filtrosBeanHelper.getConsultaDelegate().getPlanesByPrograma(programa.getPedid());
                    boolean uno = true;
                    int currow = 20;
                    
                    ReporteAvanceAux tempAux = new ReporteAvanceAux();       
                    for(ReporteAvanceAux aux : listaAux){
                        tempAux = aux;
                        break;
                    }
                       
                    //intentaremos usar un solo plan
                            setExDat(sheet, currow , 1, "Áreas de conocimiento");
                            setStyleCell(sheet, headerTabla, currow, 1);
                            currow++;
                            //cada plan de estudios tiene varias areas de conocimiento
                            //List<Areaconocimiento> areasConocimiento = filtrosBeanHelper.getConsultaDelegate().getAreasByPlan( tempAux.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesid() );
                            List<Areaconocimiento> areasConocimiento = filtrosBeanHelper.getConsultaDelegate().getAreasByPlanClave(programa.getPedclave(),tempAux.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesvigenciaPlan());
                            for(Areaconocimiento areaCon : areasConocimiento){
                                //clave , area de conocimiento, responsable
                                setExDat(sheet, currow,1, "Clave" );
                                setExDat(sheet, currow,2, "Área de conocimiento" );
                                setExDat(sheet, currow,3, "Responsable" );
                                setStyleCell(sheet, headerTabla, currow, 1);
                                setStyleCell(sheet, headerTabla, currow, 2);
                                setStyleCell(sheet, headerTabla, currow, 3);
                                
                                currow++;
                                setExDat(sheet, currow,1, areaCon.getAcoclave() ); //clave
                                setExDat(sheet, currow,2, areaCon.getAconombre() ); //area conociminto
                                setExDat(sheet, currow,3, "" ); //responsable
                               
                                
                                currow+=2;
                                //obtenemos las unidades de aprendisake
                                setExDat(sheet, currow,1, "Clave unidad de aprendizaje" );
                                setExDat(sheet, currow,2, "Unidad de aprendizaje" );
                                setExDat(sheet, currow,3, "No. de empleado" );
                                setExDat(sheet, currow,4, "Nombre del profesor" );
                                setExDat(sheet, currow,5, "Grupo" );
                                setExDat(sheet, currow,6, "% Avance 1er reporte" );
                                setExDat(sheet, currow,7, "Fecha de elaboración 1er RACT" );
                                setExDat(sheet, currow,8, "% Avance 2do reporte" );
                                setExDat(sheet, currow,9, "Fecha de elaboración 2do RACT" );
                                setExDat(sheet, currow,10, "% Avance 3er reporte" );
                                setExDat(sheet, currow,11, "Fecha de elaboración 3er RACT" );
                                //autosize para la columna
                                if(autotam){
                                    sheet.autoSizeColumn(2);
                                    sheet.autoSizeColumn(4);
                                    sheet.autoSizeColumn(11);
                                    autotam = false;
                                }
                                // para formatear toda la linea
                                for(int i=1;i<=11;i++){
                                    //tenemos qu aajustar texto
                                    //tenemos que centrar el texto
                                    //cambiar el color de fondo
                                  setStyleCell(sheet, headerTabla, currow, i);  
                                }
                                                             
                                currow++;
                                //List<Unidadaprendizaje> unidadesAprendisaje = filtrosBeanHelper.getConsultaDelegate().getUnidadByArea(areaCon.getAcoclave());
                                    
                                    //de ls listaAux separamos los los reportes que correspondan a la unidad de aprendisaje acutal
                                    ArrayList <ReporteAvanceAux> tempListAux = new ArrayList<ReporteAvanceAux>();        
                                    for(ReporteAvanceAux aux : listaAux){
                                        if(aux.getAreaConocimiento()!=null && aux.getAreaConocimiento().getAcoclave() == areaCon.getAcoclave() && 
                                                programa.getPedclave() == aux.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getProgramaeducativo().getPedclave() 
                                                )
                                        {
                                            tempListAux.add(aux);
                                        }
                                    }
                                    
                                    //List<UnidadaprendizajeImparteProfesor> unidadesProfesor  = filtrosBeanHelper.getConsultaDelegate().getUnidadesAprendisajeImparteProf(unidadApren.getUapid());
                                    int uniprofeTemp = 0 ; 
                                    for(ReporteAvanceAux auxRacs :tempListAux){
                                            
                                        String nompreP = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getPronombre();
                                        String apellidoPP = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoPaterno();
                                        String apellidoPM = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoMaterno();
                                        int claveUnidadApren = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave();
                                        String nombreUnidad = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapnombre();

                                        uniprofeTemp = claveUnidadApren;
                                        setExDat(sheet, currow,1, claveUnidadApren ); //clave
                                        setExDat(sheet, currow,2, nombreUnidad ); //nombre unidad
                                        //reporteAvance.unidadaprendizajeImparteProfesor.profesor.pronumeroEmpleado
                                        setExDat(sheet, currow,3, auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getPronumeroEmpleado() ); //numero empleado
                                        setExDat(sheet, currow,4, apellidoPP+ " "+apellidoPM+ " "+nompreP ); //nombre maestro
                                        setExDat(sheet, currow,5, auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getGponumero() + "-" + auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUiptipoSubgrupo()  + "-"+ auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUipsubgrupo() ); //grupo numero

                                        //marcamos bordes
                                        for(int i=6;i<=11;i++){
                                            setExDat(sheet, currow,i," ");
                                        }
                                        for(int i=1;i<=11;i++){
                                            setStyleCell(sheet, borderstabla, currow, i);
                                        }
                                        
                                        //tienne que ser entregados/enviados
                                        if(auxRacs.getStatusRact1()!=null){
                                            
                                            System.out.println("Entro a enviados 1");
                                            setExDat(sheet, currow,(4 + (1*2)), auxRacs.getPorcentAvanceRact1() ); //% avance 7**
                                            setExDat(sheet, currow,(4 + (1*2)+1), auxRacs.getFechaElaboracRact1().toString() ); //fecha elabora 8***
                                            setStyleCell(sheet, borderstabla, currow, (4 + (1*2)));
                                            setStyleCell(sheet, borderstabla, currow, (4 + (1*2)+1));
                                            
                                        }
                                        else{
                                            setExDat(sheet, currow,(4 + (1*2)), " " ); //% avance 7**
                                            setExDat(sheet, currow,(4 + (1*2)+1), " " ); //fecha elabora 8***
                                            setStyleCell(sheet, borderstabla, currow, (4 + (1*2)));
                                            setStyleCell(sheet, borderstabla, currow, (4 + (1*2)+1));
                                        }
                                        
                                        
                                        if(auxRacs.getStatusRact2()!=null){
                                           
                                            setExDat(sheet, currow,(4 + (2*2)), auxRacs.getPorcentAvanceRact2() ); //% avance 7**
                                            setExDat(sheet, currow,(4 + (2*2)+1), auxRacs.getFechaElaboracRact2().toString() ); //fecha elabora 8***
                                            setStyleCell(sheet, borderstabla, currow, (4 + (2*2)));
                                            setStyleCell(sheet, borderstabla, currow, (4 + (2*2)+1));
                                            
                                        }else{
                                            setExDat(sheet, currow,(4 + (2*2)), " " ); //% avance 7**
                                            setExDat(sheet, currow,(4 + (2*2)+1), " " ); //fecha elabora 8***
                                            setStyleCell(sheet, borderstabla, currow, (4 + (2*2)));
                                            setStyleCell(sheet, borderstabla, currow, (4 + (2*2)+1));
                                        }
                                        
                                        if(auxRacs.getStatusRact3()!=null){
                                            setExDat(sheet, currow,(4 + (3*2)), auxRacs.getPorcentAvanceRact3() ); //% avance 7**
                                            setExDat(sheet, currow,(4 + (3*2)+1), auxRacs.getFechaElaboracRact3().toString() ); //fecha elabora 8***
                                            setStyleCell(sheet, borderstabla, currow, (4 + (3*2)));
                                            setStyleCell(sheet, borderstabla, currow, (4 + (3*2)+1));
                                        }
                                        else{
                                            setExDat(sheet, currow,(4 + (3*2)), " "  ); //% avance 7**
                                            setExDat(sheet, currow,(4 + (3*2)+1), " " ); //fecha elabora 8***
                                            setStyleCell(sheet, borderstabla, currow, (4 + (3*2)));
                                            setStyleCell(sheet, borderstabla, currow, (4 + (3*2)+1));   
                                        }
                                        
                     
                                      
                                        currow++;
                                    }
                                    
                                
                                
                                currow+=2;
                            }
                            
                                                 

                    }// fin del la comparacon de plan
                }// fin del for por plan de estudios
                 
                 
            }// fin de si es por entregados if(entregados)
            
            if(reporte.equalsIgnoreCase("entregadosynoentregados")){
                nombreLibro = "Entregados y No Entregados " + nombreLibro;
                
                //definimos encabezado
                sheet = cabezeraGeneralExcel(sheet,uabcLogo,style);
                
                //mostraremos tabla con
                // Programas educativos //  Total Racs entregados // Total Esperados
                //preparamos informacion para insertar
                List<Programaeducativo> programasByUnidad = getProgramasByUnidad();
                
                //imrpiimiremos la tabla
                // Definimos los encabezados de la tabla
                setExDat(sheet, 13, 17, "Programa Educativo");
                setExDat(sheet, 13, 18, "Total de RACT Entregados");
                setExDat(sheet, 13, 19, "Total de RACT Esperados");
                setExDat(sheet, 13, 20, " ");
                
                setStyleCell(sheet, headerTabla, 13, 17);
                setStyleCell(sheet, headerTabla, 13, 18);
                setStyleCell(sheet, headerTabla, 13, 19);
                
                /*sheet.autoSizeColumn(2);
                sheet.autoSizeColumn(3);
                sheet.autoSizeColumn(4);*/
                
                //imprimimos la informacion en su lugar
                
                int prow = 14;
                 for(Programaeducativo programa: programasByUnidad){   
                       if( selectProgramEducativo.contains(programa.getPedid().toString())  ){
                           setExDat(sheet, prow, 17, programa.getPednombre());
                           setExDat(sheet, prow, 18, 75);
                           setExDat(sheet, prow, 19, 100);
                           prow++;
                       }                  
                }
                //Se obtiene consulta con los parametros seleccionados
                ArrayList<String> entregados = esperadosDelegate.getFullProgramaEdu(unidadacademica.getUacid(), Plan, Programa, Ciclo);

                //agregamos programacion de grafico
                if(tipografico.equalsIgnoreCase("barras")){
                    BarChartCL demo1 = new BarChartCL("Estadísticas de Reportes Entregados por Programa Educativo",entregados,"ExcelPOIGrafica1");
                    demo1.addChartToExcel(demo1.createChart(demo1.createDataset()),workbook);
                }
                if(tipografico.equalsIgnoreCase("linea")){
                    LineChartDemo1 demo1 = new LineChartDemo1("Estadísticas de Reportes Entregados por Programa Educativo",entregados,"ExcelPOIGrafica1");
                    demo1.addChartToExcel(demo1.createChart(demo1.createDataset()),workbook);
                }
                if(tipografico.equalsIgnoreCase("pastel")){
                    PieChartDemo1 demo1 = new PieChartDemo1("Estadísticas de Reportes Entregados por Programa Educativo",entregados,"ExcelPOIGrafica1");
                    demo1.addChartToExcel(demo1.createChart(demo1.createDataset()),workbook);
                }
                 
                //Se acaba grafico
                //recorreremos todo el list aux para revisar los programas educativos que utiliza
                //aun que podemos usar el selectProgramaeducativo
                 
                /* Programacion de Hojas*/
                for(Programaeducativo programa: programasByUnidad){
                    if(selectProgramEducativo.contains( Integer.toString(programa.getPedclave()) )){
                    sheet = workbook.createSheet(programa.getPednombre());
                    sheet = cabezeraGeneralExcel(sheet,uabcLogo,style);
                    setExDat(sheet, 8, 3, "Concentrado de Reporte de Avance de Contenido Temático de Entregados por Programa Educativo");
                    
                    boolean autotam = true;
                    //informacion de programa educativo tabla
                    setExDat(sheet, 14, 1, "Clave");
                    setExDat(sheet, 14, 2, "Programa Educativo");
                    setExDat(sheet, 14, 3, "Plan de Estudios");
                    setExDat(sheet, 14, 4, "Responsable");
                    
                    setStyleCell(sheet, headerTabla, 14, 1);
                    setStyleCell(sheet, headerTabla, 14, 2);
                    setStyleCell(sheet, headerTabla, 14, 3);
                    setStyleCell(sheet, headerTabla, 14, 4);
                                    
                    //llenado de informacion
                    // se tiene imprimir por cada plan de estudios???
                    
//                    Responsableprogramaeducativo responsable = filtrosBeanHelper.getConsultaDelegate().getResponsableProgramaEducativoByID(programa.getPedid());
                    
                    setExDat(sheet, 15, 1, programa.getPedclave());
                    setExDat(sheet, 15, 2, programa.getPednombre());
                    setExDat(sheet, 15, 3, "");      
                    setExDat(sheet, 15, 4, "");
                    setStyleCell(sheet, borderstabla, 15, 1);
                    setStyleCell(sheet, borderstabla, 15, 2);
                    setStyleCell(sheet, borderstabla, 15, 3);
                    setStyleCell(sheet, borderstabla, 15, 4);
                    
                    List<Planestudio> planes = filtrosBeanHelper.getConsultaDelegate().getPlanesByPrograma(programa.getPedid());
                    boolean uno = true;
                    int currow = 20;
                    
                    ReporteAvanceAux tempAux = new ReporteAvanceAux();       
                    for(ReporteAvanceAux aux : listaAux){
                        tempAux = aux;
                        break;
                    }
                       
                    //intentaremos usar un solo plan
                            setExDat(sheet, currow , 1, "Áreas de conocimiento");
                            setStyleCell(sheet, headerTabla, currow, 1);
                            currow++;
                            //cada plan de estudios tiene varias areas de conocimiento
                            //List<Areaconocimiento> areasConocimiento = filtrosBeanHelper.getConsultaDelegate().getAreasByPlan( tempAux.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesid() );
                            List<Areaconocimiento> areasConocimiento = filtrosBeanHelper.getConsultaDelegate().getAreasByPlanClave(programa.getPedclave(),tempAux.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesvigenciaPlan());
                            
                            for(Areaconocimiento areaCon : areasConocimiento){
                                //clave , area de conocimiento, responsable
                                setExDat(sheet, currow,1, "Clave" );
                                setExDat(sheet, currow,2, "Área de conocimiento" );
                                setExDat(sheet, currow,3, "Responsable" );
                                setStyleCell(sheet, headerTabla, currow, 1);
                                setStyleCell(sheet, headerTabla, currow, 2);
                                setStyleCell(sheet, headerTabla, currow, 3);
                                
                                currow++;
                                setExDat(sheet, currow,1, areaCon.getAcoclave() ); //clave
                                setExDat(sheet, currow,2, areaCon.getAconombre() ); //area conociminto
                                setExDat(sheet, currow,3, "" ); //responsable
                               
                                
                                currow+=2;
                                //obtenemos las unidades de aprendisake
                                setExDat(sheet, currow,1, "Clave unidad de aprendizaje" );
                                setExDat(sheet, currow,2, "Unidad de aprendizaje" );
                                setExDat(sheet, currow,3, "No. de empleado" );
                                setExDat(sheet, currow,4, "Nombre del profesor" );
                                setExDat(sheet, currow,5, "Grupo" );
                                setExDat(sheet, currow,6, "% Avance 1er reporte" );
                                setExDat(sheet, currow,7, "Fecha de elaboración 1er RACT" );
                                setExDat(sheet, currow,8, "% Avance 2do reporte" );
                                setExDat(sheet, currow,9, "Fecha de elaboración 2do RACT" );
                                setExDat(sheet, currow,10, "% Avance 3er reporte" );
                                setExDat(sheet, currow,11, "Fecha de elaboración 3er RACT" );
                                if(autotam){
                                    sheet.autoSizeColumn(2);
                                    sheet.autoSizeColumn(4);
                                    sheet.autoSizeColumn(11);
                                    autotam = false;
                                }
                                // para formatear toda la linea
                                for(int i=1;i<=11;i++){
                                    //tenemos qu aajustar texto
                                    //tenemos que centrar el texto
                                    //cambiar el color de fondo
                                  setStyleCell(sheet, headerTabla, currow, i);  
                                }
                                                             
                                currow++;
                                //List<Unidadaprendizaje> unidadesAprendisaje = filtrosBeanHelper.getConsultaDelegate().getUnidadByArea(areaCon.getAcoclave());
                                    
                                    //de ls listaAux separamos los los reportes que correspondan a la unidad de aprendisaje acutal
                                    ArrayList <ReporteAvanceAux> tempListAux = new ArrayList<ReporteAvanceAux>();        
                                    for(ReporteAvanceAux aux : listaAux){
                                        if(aux.getAreaConocimiento()!=null && aux.getAreaConocimiento().getAcoclave() == areaCon.getAcoclave() && 
                                                programa.getPedclave() == aux.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getProgramaeducativo().getPedclave() 
                                                )
                                        {
                                            tempListAux.add(aux);
                                        }
                                    }
                                    
                                    //List<UnidadaprendizajeImparteProfesor> unidadesProfesor  = filtrosBeanHelper.getConsultaDelegate().getUnidadesAprendisajeImparteProf(unidadApren.getUapid());
                                    int uniprofeTemp = 0 ; 
                                    for(ReporteAvanceAux auxRacs :tempListAux){
                                        
                                        //marcamos bordes
                                        for(int i=1;i<=11;i++){
                                            setExDat(sheet, currow,i," ");
                                        }
                                        for(int i=1;i<=11;i++){
                                            setStyleCell(sheet, borderstabla, currow, i);
                                        }
                                            
                                        String nompreP = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getPronombre();
                                        String apellidoPP = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoPaterno();
                                        String apellidoPM = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoMaterno();
                                        int claveUnidadApren = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave();
                                        String nombreUnidad = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapnombre();

                                        uniprofeTemp = claveUnidadApren;
                                        setExDat(sheet, currow,1, claveUnidadApren ); //clave
                                        setExDat(sheet, currow,2, nombreUnidad ); //nombre unidad
                                        //reporteAvance.unidadaprendizajeImparteProfesor.profesor.pronumeroEmpleado
                                        setExDat(sheet, currow,3, auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getPronumeroEmpleado() ); //numero empleado
                                        setExDat(sheet, currow,4, apellidoPP+ " "+apellidoPM+ " "+nompreP ); //nombre maestro
                                        setExDat(sheet, currow,5, auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getGponumero() + "-"+ auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUiptipoSubgrupo()  + "-"+ auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUipsubgrupo() ); //grupo numero

                                        //marcamos bordes
                                        for(int i=1;i<=5;i++){
                                            setStyleCell(sheet, borderstabla, currow, i);
                                        }
                                        //tienne que ser entregados/enviados

                                        
                                            setExDat(sheet, currow,(4 + (1*2)), auxRacs.getPorcentAvanceRact1() ); //% avance 7**
                                            if(auxRacs.getFechaElaboracRact1()!=null){
                                                setExDat(sheet, currow,(4 + (1*2)+1), auxRacs.getFechaElaboracRact1().toString() ); //fecha elabora 8***
                                            }
                                            setStyleCell(sheet, borderstabla, currow, (4 + (1*2)));
                                            setStyleCell(sheet, borderstabla, currow, (4 + (1*2)+1));
                                        
                                            setExDat(sheet, currow,(4 + (2*2)), auxRacs.getPorcentAvanceRact2() ); //% avance 7**
                                            if(auxRacs.getFechaElaboracRact2()!=null){
                                                setExDat(sheet, currow,(4 + (2*2)+1), auxRacs.getFechaElaboracRact2().toString() ); //fecha elabora 8***
                                            }
                                            setStyleCell(sheet, borderstabla, currow, (4 + (2*2)));
                                            setStyleCell(sheet, borderstabla, currow, (4 + (2*2)+1));
                                            
                                            setExDat(sheet, currow,(4 + (3*2)), auxRacs.getPorcentAvanceRact3() ); //% avance 7**
                                            if(auxRacs.getFechaElaboracRact3()!=null){
                                               setExDat(sheet, currow,(4 + (3*2)+1), auxRacs.getFechaElaboracRact3().toString() ); //fecha elabora 8*** 
                                            }
                                            setStyleCell(sheet, borderstabla, currow, (4 + (3*2)));
                                            setStyleCell(sheet, borderstabla, currow, (4 + (3*2)+1));
                                        
                                      
                                        currow++;
                                    }
                                    
                                
                                
                                currow+=2;
                            }
                            
                                                 

                    }// fin del la comparacon de plan
                }// fin del for por plan de estudios
                 
                 
            }// fin de si es por entregados y no entregados if(entregados)
            
            if(reporte.equalsIgnoreCase("entregadosatiempo") || reporte.equalsIgnoreCase("entregadosenfechalimite") || reporte.equalsIgnoreCase("entregadosdespueslimite") || reporte.equalsIgnoreCase("entregadosdespueslimite") || reporte.equalsIgnoreCase("entregadosatiempoenfechalimiteydespues"))
            {
                if(reporte.equalsIgnoreCase("entregadosatiempo")){
                    System.out.println("Entro a entregados a tiempo");
                    nombreLibro = "Entregados a Tiempo " + nombreLibro;
                }

                if(reporte.equalsIgnoreCase("entregadosenfechalimite")){
                    System.out.println("Entro a entregados en fecha");
                    nombreLibro = "Entregados en Fecha Limite " + nombreLibro;
                }

                if(reporte.equalsIgnoreCase("entregadosdespueslimite")){
                    System.out.println("Entro a entregados despues");
                    nombreLibro = "Entregados despues Fecha Limite" + nombreLibro;
                }

                if(reporte.equalsIgnoreCase("entregadosatiempoenfechalimiteydespues")){
                    System.out.println("Entro a entregados a tiempo");
                    nombreLibro = "Entregados a Tiempo-Limite-Despues de Fecha " + nombreLibro;
                }
                
                //mostraremos tabla con
                // Programas educativos //  Total Racs entregados // Total Esperados
                //preparamos informacion para insertar
                List<Programaeducativo> programasByUnidad = getProgramasByUnidad();
                
                //imrpiimiremos la tabla
                // Definimos los encabezados de la tabla
                setExDat(sheet, 13, 17, "Programa Educativo");
                setExDat(sheet, 13, 18, "Total de RACT Entregados");
                setExDat(sheet, 13, 19, "Total de RACT Esperados");
                setExDat(sheet, 13, 20, " ");
                
                setStyleCell(sheet, headerTabla, 13, 17);
                setStyleCell(sheet, headerTabla, 13, 18);
                setStyleCell(sheet, headerTabla, 13, 19);
                
                /*sheet.autoSizeColumn(2);
                sheet.autoSizeColumn(3);
                sheet.autoSizeColumn(4);*/
                
                //imprimimos la informacion en su lugar
                
                int prow = 14;
                 for(Programaeducativo programa: programasByUnidad){   
                       if( selectProgramEducativo.contains(programa.getPedid().toString())  ){
                           setExDat(sheet, prow, 17, programa.getPednombre());
                           setExDat(sheet, prow, 18, 75);
                           setExDat(sheet, prow, 19, 100);
                           prow++;
                       }                  
                }
                //Se obtiene consulta con los parametros seleccionados
                ArrayList<String> entregados = esperadosDelegate.getFullProgramaEdu(unidadacademica.getUacid(), Plan, Programa, Ciclo);

                //agregamos programacion de grafico
                if(tipografico.equalsIgnoreCase("barras")){
                    BarChartCL demo1 = new BarChartCL("Estadísticas de Reportes Entregados por Programa Educativo",entregados,"ExcelPOIGrafica1");
                    demo1.addChartToExcel(demo1.createChart(demo1.createDataset()),workbook);
                }
                if(tipografico.equalsIgnoreCase("linea")){
                    LineChartDemo1 demo1 = new LineChartDemo1("Estadísticas de Reportes Entregados por Programa Educativo",entregados,"ExcelPOIGrafica1");
                    demo1.addChartToExcel(demo1.createChart(demo1.createDataset()),workbook);
                }
                if(tipografico.equalsIgnoreCase("pastel")){
                    PieChartDemo1 demo1 = new PieChartDemo1("Estadísticas de Reportes Entregados por Programa Educativo",entregados,"ExcelPOIGrafica1");
                    demo1.addChartToExcel(demo1.createChart(demo1.createDataset()),workbook);
                }
                 
                //Se acaba grafico
                //recorreremos todo el list aux para revisar los programas educativos que utiliza
                //aun que podemos usar el selectProgramaeducativo
                 
                /* Programacion de Hojas*/
                for(Programaeducativo programa: programasByUnidad){
                    if(selectProgramEducativo.contains( Integer.toString(programa.getPedclave()) )){
                        
                    sheet = workbook.createSheet(programa.getPednombre());
                    sheet = cabezeraGeneralExcel(sheet,uabcLogo,style);
                    setExDat(sheet, 8, 3, "Concentrado de Reporte de Avance de Contenido Temático de Entregados por Programa Educativo");
                    
                    boolean autotam = true;
                    //informacion de programa educativo tabla
                    setExDat(sheet, 14, 1, "Clave");
                    setExDat(sheet, 14, 2, "Programa Educativo");
                    setExDat(sheet, 14, 3, "Plan de Estudios");
                    setExDat(sheet, 14, 4, "Responsable");
                    
                    setStyleCell(sheet, headerTabla, 14, 1);
                    setStyleCell(sheet, headerTabla, 14, 2);
                    setStyleCell(sheet, headerTabla, 14, 3);
                    setStyleCell(sheet, headerTabla, 14, 4);
                                    
                    //llenado de informacion
                    // se tiene imprimir por cada plan de estudios???
                    
//                    Responsableprogramaeducativo responsable = filtrosBeanHelper.getConsultaDelegate().getResponsableProgramaEducativoByID(programa.getPedid());
                    
                    setExDat(sheet, 15, 1, programa.getPedclave());
                    setExDat(sheet, 15, 2, programa.getPednombre());
                    setExDat(sheet, 15, 3, "");      
                    setExDat(sheet, 15, 4, "");
                    setStyleCell(sheet, borderstabla, 15, 1);
                    setStyleCell(sheet, borderstabla, 15, 2);
                    setStyleCell(sheet, borderstabla, 15, 3);
                    setStyleCell(sheet, borderstabla, 15, 4);
                    
                    List<Planestudio> planes = filtrosBeanHelper.getConsultaDelegate().getPlanesByPrograma(programa.getPedid());
                    boolean uno = true;
                    int currow = 20;
                    
                    ReporteAvanceAux tempAux = new ReporteAvanceAux();       
                    for(ReporteAvanceAux aux : listaAux){
                        tempAux = aux;
                        break;
                    }
                       
                    //intentaremos usar un solo plan
                            setExDat(sheet, currow , 1, "Áreas de conocimiento");
                            setStyleCell(sheet, headerTabla, currow, 1);
                            currow++;
                            //cada plan de estudios tiene varias areas de conocimiento
                            //List<Areaconocimiento> areasConocimiento = filtrosBeanHelper.getConsultaDelegate().getAreasByPlan( tempAux.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesid() );
                            List<Areaconocimiento> areasConocimiento = filtrosBeanHelper.getConsultaDelegate().getAreasByPlanClave(programa.getPedclave(),tempAux.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesvigenciaPlan());
                            
                            for(Areaconocimiento areaCon : areasConocimiento){
                                //clave , area de conocimiento, responsable
                                setExDat(sheet, currow,1, "Clave" );
                                setExDat(sheet, currow,2, "Área de conocimiento" );
                                setExDat(sheet, currow,3, "Responsable" );
                                setStyleCell(sheet, headerTabla, currow, 1);
                                setStyleCell(sheet, headerTabla, currow, 2);
                                setStyleCell(sheet, headerTabla, currow, 3);
                                
                                currow++;
                                setExDat(sheet, currow,1, areaCon.getAcoclave() ); //clave
                                setExDat(sheet, currow,2, areaCon.getAconombre() ); //area conociminto
                                setExDat(sheet, currow,3, "" ); //responsable
                               
                                
                                currow+=2;
                                //obtenemos las unidades de aprendisake
                                setExDat(sheet, currow,1, "Clave unidad de aprendizaje" );
                                setExDat(sheet, currow,2, "Unidad de aprendizaje" );
                                setExDat(sheet, currow,3, "No. de empleado" );
                                setExDat(sheet, currow,4, "Nombre del profesor" );
                                setExDat(sheet, currow,5, "Grupo" );
                                setExDat(sheet, currow,6, "% Avance 1er reporte" );
                                setExDat(sheet, currow,7, "Fecha de elaboración 1er RACT" );
                                setExDat(sheet, currow,8, "% Avance 2do reporte" );
                                setExDat(sheet, currow,9, "Fecha de elaboración 2do RACT" );
                                setExDat(sheet, currow,10, "% Avance 3er reporte" );
                                setExDat(sheet, currow,11, "Fecha de elaboración 3er RACT" );
                                if(autotam){
                                    sheet.autoSizeColumn(2);
                                    sheet.autoSizeColumn(4);
                                    sheet.autoSizeColumn(11);
                                    autotam = false;
                                }
                                // para formatear toda la linea
                                for(int i=1;i<=11;i++){
                                    //tenemos qu aajustar texto
                                    //tenemos que centrar el texto
                                    //cambiar el color de fondo
                                  setStyleCell(sheet, headerTabla, currow, i);  
                                }
                                                             
                                currow++;
                                //List<Unidadaprendizaje> unidadesAprendisaje = filtrosBeanHelper.getConsultaDelegate().getUnidadByArea(areaCon.getAcoclave());
                                    
                                    //de ls listaAux separamos los los reportes que correspondan a la unidad de aprendisaje acutal
                                    ArrayList <ReporteAvanceAux> tempListAux = new ArrayList<ReporteAvanceAux>();        
                                    for(ReporteAvanceAux aux : listaAux){
                                        if(aux.getAreaConocimiento()!=null && aux.getAreaConocimiento().getAcoclave() == areaCon.getAcoclave() && 
                                                programa.getPedclave() == aux.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getProgramaeducativo().getPedclave() 
                                                )
                                        {
                                            tempListAux.add(aux);
                                        }
                                    }
                                    
                                    //List<UnidadaprendizajeImparteProfesor> unidadesProfesor  = filtrosBeanHelper.getConsultaDelegate().getUnidadesAprendisajeImparteProf(unidadApren.getUapid());
                                    int uniprofeTemp = 0 ; 
                                    for(ReporteAvanceAux auxRacs :tempListAux){
                                        
                                        //marcamos bordes
                                        for(int i=1;i<=11;i++){
                                            setExDat(sheet, currow,i," ");
                                        }
                                        for(int i=1;i<=11;i++){
                                            setStyleCell(sheet, borderstabla, currow, i);
                                        }
                                            
                                        String nompreP = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getPronombre();
                                        String apellidoPP = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoPaterno();
                                        String apellidoPM = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoMaterno();
                                        int claveUnidadApren = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave();
                                        String nombreUnidad = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapnombre();

                                        uniprofeTemp = claveUnidadApren;
                                        setExDat(sheet, currow,1, claveUnidadApren ); //clave
                                        setExDat(sheet, currow,2, nombreUnidad ); //nombre unidad
                                        //reporteAvance.unidadaprendizajeImparteProfesor.profesor.pronumeroEmpleado
                                        setExDat(sheet, currow,3, auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getPronumeroEmpleado() ); //numero empleado
                                        setExDat(sheet, currow,4, apellidoPP+ " "+apellidoPM+ " "+nompreP ); //nombre maestro
                                        setExDat(sheet, currow,5, auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getGponumero() + "-"+ auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUiptipoSubgrupo()  + "-"+ auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUipsubgrupo() ); //grupo numero

                                        //marcamos bordes
                                        for(int i=1;i<=5;i++){
                                            setStyleCell(sheet, borderstabla, currow, i);
                                        }
                                        //tienne que ser entregados/enviados

                                        
                                            setExDat(sheet, currow,(4 + (1*2)), auxRacs.getPorcentAvanceRact1() ); //% avance 7**
                                            if(auxRacs.getFechaElaboracRact1()!=null){
                                                setExDat(sheet, currow,(4 + (1*2)+1), auxRacs.getFechaElaboracRact1().toString() ); //fecha elabora 8***
                                            }
                                            setStyleCell(sheet, borderstabla, currow, (4 + (1*2)));
                                            setStyleCell(sheet, borderstabla, currow, (4 + (1*2)+1));
                                        
                                            setExDat(sheet, currow,(4 + (2*2)), auxRacs.getPorcentAvanceRact2() ); //% avance 7**
                                            if(auxRacs.getFechaElaboracRact2()!=null){
                                                setExDat(sheet, currow,(4 + (2*2)+1), auxRacs.getFechaElaboracRact2().toString() ); //fecha elabora 8***
                                            }
                                            setStyleCell(sheet, borderstabla, currow, (4 + (2*2)));
                                            setStyleCell(sheet, borderstabla, currow, (4 + (2*2)+1));
                                            
                                            setExDat(sheet, currow,(4 + (3*2)), auxRacs.getPorcentAvanceRact3() ); //% avance 7**
                                            if(auxRacs.getFechaElaboracRact3()!=null){
                                               setExDat(sheet, currow,(4 + (3*2)+1), auxRacs.getFechaElaboracRact3().toString() ); //fecha elabora 8*** 
                                            }
                                            setStyleCell(sheet, borderstabla, currow, (4 + (3*2)));
                                            setStyleCell(sheet, borderstabla, currow, (4 + (3*2)+1));
                                        
                                      
                                        currow++;
                                    }
                                    
                                
                                
                                currow+=2;
                            }
                            
                                                 

                    }// fin del la comparacon de plan
                }// fin del for por plan de estudios
                 
                //definimos encabezado
                sheet = cabezeraGeneralExcel(sheet,uabcLogo,style);
            }// fin de si es por entregados a tiempoif(entregados)
            
            //Porcentaje de Avance Global Incompleto
            if(reporte.equalsIgnoreCase("Porcentaje de Avance Global Completo")|| 
                    reporte.equalsIgnoreCase("Porcentaje de Avance Global Incompleto")||
                    reporte.equalsIgnoreCase("Porcentaje de Avance Global Completo e Incompleto") 
                    ){
                nombreLibro = reporte + nombreLibro;
                
                //definimos encabezado
                sheet = cabezeraGeneralExcel(sheet,uabcLogo,style);
                
                //mostraremos tabla con
                // Programas educativos //  Total Racs entregados // Total Esperados
                //preparamos informacion para insertar
                List<Programaeducativo> programasByUnidad = getProgramasByUnidad();
                
                //imrpiimiremos la tabla
                // Definimos los encabezados de la tabla
                setExDat(sheet, 13, 17, "Programa Educativo");
                setExDat(sheet, 13, 18, "Total de RACT Entregados");
                setExDat(sheet, 13, 19, "Total de RACT Esperados");
                setExDat(sheet, 13, 20, " ");
                
                setStyleCell(sheet, headerTabla, 13, 17);
                setStyleCell(sheet, headerTabla, 13, 18);
                setStyleCell(sheet, headerTabla, 13, 19);
                
                /*sheet.autoSizeColumn(2);
                sheet.autoSizeColumn(3);
                sheet.autoSizeColumn(4);*/
                
                //imprimimos la informacion en su lugar
                
                int prow = 14;
                 for(Programaeducativo programa: programasByUnidad){   
                       if( selectProgramEducativo.contains(programa.getPedid().toString())  ){
                           setExDat(sheet, prow, 17, programa.getPednombre());
                           setExDat(sheet, prow, 18, 75);
                           setExDat(sheet, prow, 19, 100);
                           prow++;
                       }                  
                }
                //Se obtiene consulta con los parametros seleccionados
                ArrayList<String> entregados = esperadosDelegate.getFullProgramaEdu(unidadacademica.getUacid(), Plan, Programa, Ciclo);

                //agregamos programacion de grafico
                if(tipografico.equalsIgnoreCase("barras")){
                    BarChartCL demo1 = new BarChartCL("Estadísticas de Reportes Entregados por Programa Educativo",entregados,"ExcelPOIGrafica1");
                    demo1.addChartToExcel(demo1.createChart(demo1.createDataset()),workbook);
                }
                if(tipografico.equalsIgnoreCase("linea")){
                    LineChartDemo1 demo1 = new LineChartDemo1("Estadísticas de Reportes Entregados por Programa Educativo",entregados,"ExcelPOIGrafica1");
                    demo1.addChartToExcel(demo1.createChart(demo1.createDataset()),workbook);
                }
                if(tipografico.equalsIgnoreCase("pastel")){
                    PieChartDemo1 demo1 = new PieChartDemo1("Estadísticas de Reportes Entregados por Programa Educativo",entregados,"ExcelPOIGrafica1");
                    demo1.addChartToExcel(demo1.createChart(demo1.createDataset()),workbook);
                }
                 
                //Se acaba grafico
                //recorreremos todo el list aux para revisar los programas educativos que utiliza
                //aun que podemos usar el selectProgramaeducativo
                 
                /* Programacion de Hojas*/
                for(Programaeducativo programa: programasByUnidad){
                    if(selectProgramEducativo.contains( Integer.toString(programa.getPedclave()) )){
                    //sheet = cabezeraGeneralExcel(sheet,uabcLogo,style); lo moveremos al final de la hoja para que no sea mdoficada la imagen
                    sheet = workbook.createSheet(programa.getPednombre());
                    sheet = cabezeraGeneralExcel(sheet,uabcLogo,style);
                    //setExDat(sheet, 8, 3, "Concentrado de Reporte de Avance de Contenido Temático de Entregados por Programa Educativo");
                    
                    
                    boolean autotam = true;
                    
                    //informacion de programa educativo tabla
                    setExDat(sheet, 14, 1, "Clave");
                    setExDat(sheet, 14, 2, "Programa Educativo");
                    setExDat(sheet, 14, 3, "Plan de Estudios");
                    setExDat(sheet, 14, 4, "Responsable");
                    
                    setStyleCell(sheet, headerTabla, 14, 1);
                    setStyleCell(sheet, headerTabla, 14, 2);
                    setStyleCell(sheet, headerTabla, 14, 3);
                    setStyleCell(sheet, headerTabla, 14, 4);
                                    
                    //llenado de informacion
                    // se tiene imprimir por cada plan de estudios???
                    
//                    Responsableprogramaeducativo responsable = filtrosBeanHelper.getConsultaDelegate().getResponsableProgramaEducativoByID(programa.getPedid());
                    
                    setExDat(sheet, 15, 1, programa.getPedclave());
                    setExDat(sheet, 15, 2, programa.getPednombre());
                    setExDat(sheet, 15, 3, "");      
                    setExDat(sheet, 15, 4, "");
                    setStyleCell(sheet, borderstabla, 15, 1);
                    setStyleCell(sheet, borderstabla, 15, 2);
                    setStyleCell(sheet, borderstabla, 15, 3);
                    setStyleCell(sheet, borderstabla, 15, 4);
                    
                    List<Planestudio> planes = filtrosBeanHelper.getConsultaDelegate().getPlanesByPrograma(programa.getPedid());
                    boolean uno = true;
                    int currow = 20;
                    
                    Planestudio planeact = new Planestudio();       
                    for(Planestudio planeactn : planes){
                        planeact = planeactn;
                        break;
                    }
                    
                    ReporteAvanceAux tempAux = new ReporteAvanceAux();       
                    for(ReporteAvanceAux aux : listaAux){
                        tempAux = aux;
                        break;
                    }
                       
                    //intentaremos usar un solo plan
                            setExDat(sheet, currow , 1, "Áreas de conocimiento");
                            setStyleCell(sheet, headerTabla, currow, 1);
                            currow++;
                            //cada plan de estudios tiene varias areas de conocimiento
                            //List<Areaconocimiento> areasConocimiento = filtrosBeanHelper.getConsultaDelegate().getAreasByPlan( tempAux.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesid() );
                            //List<Areaconocimiento> areasConocimiento = filtrosBeanHelper.getConsultaDelegate().getAreasByPlan( planeact.getPesid() );
                            List<Areaconocimiento> areasConocimiento = filtrosBeanHelper.getConsultaDelegate().getAreasByPlanClave(programa.getPedclave(),tempAux.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getPesvigenciaPlan());
                            
                            
                            for(Areaconocimiento areaCon : areasConocimiento){
                                //clave , area de conocimiento, responsable
                                setExDat(sheet, currow,1, "Clave" );
                                setExDat(sheet, currow,2, "Área de conocimiento" );
                                setExDat(sheet, currow,3, "Responsable" );
                                setStyleCell(sheet, headerTabla, currow, 1);
                                setStyleCell(sheet, headerTabla, currow, 2);
                                setStyleCell(sheet, headerTabla, currow, 3);
                                
                                currow++;
                                setExDat(sheet, currow,1, areaCon.getAcoclave() ); //clave
                                setExDat(sheet, currow,2, areaCon.getAconombre() ); //area conociminto
                                setExDat(sheet, currow,3, "" ); //responsable
                               
                                
                                currow+=2;
                                //obtenemos las unidades de aprendisake
                                setExDat(sheet, currow,1, "Clave unidad de aprendizaje" );
                                setExDat(sheet, currow,2, "Unidad de aprendizaje" );
                                setExDat(sheet, currow,3, "No. de empleado" );
                                setExDat(sheet, currow,4, "Nombre del profesor" );
                                setExDat(sheet, currow,5, "Grupo" );
                                setExDat(sheet, currow,6, "% Avance 1er reporte" );
                                setExDat(sheet, currow,7, "Fecha de elaboración 1er RACT" );
                                setExDat(sheet, currow,8, "% Avance 2do reporte" );
                                setExDat(sheet, currow,9, "Fecha de elaboración 2do RACT" );
                                setExDat(sheet, currow,10, "% Avance 3er reporte" );
                                setExDat(sheet, currow,11, "Fecha de elaboración 3er RACT" );
                                
                                //autosize para la columna
                                if(autotam){
                                    sheet.autoSizeColumn(2);
                                    sheet.autoSizeColumn(4);
                                    sheet.autoSizeColumn(11);
                                    autotam = false;
                                }
                                
                                
                                // para formatear toda la linea
                                for(int i=1;i<=11;i++){
                                    //tenemos qu aajustar texto
                                    //tenemos que centrar el texto
                                    //cambiar el color de fondo
                                  setStyleCell(sheet, headerTabla, currow, i);  
                                }
                                                             
                                currow++;
                                //List<Unidadaprendizaje> unidadesAprendisaje = filtrosBeanHelper.getConsultaDelegate().getUnidadByArea(areaCon.getAcoclave());
                                    
                                    //de ls listaAux separamos los los reportes que correspondan a la unidad de aprendisaje acutal
                                    ArrayList <ReporteAvanceAux> tempListAux = new ArrayList<ReporteAvanceAux>();        
                                    for(ReporteAvanceAux aux : listaAux){
                                        if( aux.getAreaConocimiento().getAcoclave() == areaCon.getAcoclave() && 
                                                programa.getPedclave() == aux.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getPlanestudio().getProgramaeducativo().getPedclave() 
                                                )
                                        {
                                            tempListAux.add(aux);
                                        }
                                    }
                                    
                                    //List<UnidadaprendizajeImparteProfesor> unidadesProfesor  = filtrosBeanHelper.getConsultaDelegate().getUnidadesAprendisajeImparteProf(unidadApren.getUapid());
                                    int uniprofeTemp = 0 ; 
                                    for(ReporteAvanceAux auxRacs :tempListAux){
                                            
                                        String nompreP = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getPronombre();
                                        String apellidoPP = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoPaterno();
                                        String apellidoPM = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoMaterno();
                                        int claveUnidadApren = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave();
                                        String nombreUnidad = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapnombre();

                                        uniprofeTemp = claveUnidadApren;
                                        setExDat(sheet, currow,1, claveUnidadApren ); //clave
                                        setExDat(sheet, currow,2, nombreUnidad ); //nombre unidad
                                        //reporteAvance.unidadaprendizajeImparteProfesor.profesor.pronumeroEmpleado
                                        setExDat(sheet, currow,3, auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getPronumeroEmpleado() ); //numero empleado
                                        setExDat(sheet, currow,4, apellidoPP+ " "+apellidoPM+ " "+nompreP ); //nombre maestro
                                        setExDat(sheet, currow,5, auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getGponumero() + "-"+ auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUiptipoSubgrupo()  + "-" + auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUipsubgrupo() ); //grupo numero

                                        //marcamos bordes
                                        for(int i=1;i<=5;i++){
                                            setStyleCell(sheet, borderstabla, currow, i);
                                        }
                                        float totalpor = 0;
                                        if(auxRacs.getStatusRact1()!=null && auxRacs.getStatusRact2()!=null && auxRacs.getStatusRact3()!=null){
                                            totalpor = auxRacs.getPorcentAvanceRact1() + auxRacs.getPorcentAvanceRact2() + auxRacs.getPorcentAvanceRact3();
                                        }
                                        
                                            
                                            //tienne que ser entregados/enviados
                                            if(auxRacs.getStatusRact1()!=null){
                                               
                                                    System.out.println("Entro a enviados 1");
                                                    setExDat(sheet, currow,(4 + (1*2)), auxRacs.getPorcentAvanceRact1() ); //% avance 7**
                                                    
                                                    if(auxRacs.getFechaElaboracRact1()!=null){
                                                        setExDat(sheet, currow,(4 + (1*2)+1), auxRacs.getFechaElaboracRact1().toString() ); //fecha elabora 8***
                                                    }
                                                    else{
                                                        setExDat(sheet, currow,(4 + (1*2)+1), " " ); //fecha elabora 8***
                                                    }
                                                    setStyleCell(sheet, borderstabla, currow, (4 + (1*2)));
                                                    setStyleCell(sheet, borderstabla, currow, (4 + (1*2)+1));
                                                
                                            }
                                            else{
                                                setExDat(sheet, currow,(4 + (1*2)), " " ); //% avance 7**
                                                setExDat(sheet, currow,(4 + (1*2)+1), " " ); //fecha elabora 8***
                                                setStyleCell(sheet, borderstabla, currow, (4 + (1*2)));
                                                setStyleCell(sheet, borderstabla, currow, (4 + (1*2)+1));
                                            }


                                            if(auxRacs.getStatusRact2()!=null){
                                                
                                                    setExDat(sheet, currow,(4 + (2*2)), auxRacs.getPorcentAvanceRact2() ); //% avance 7**
                                                    
                                                    if(auxRacs.getFechaElaboracRact2()!=null){
                                                        setExDat(sheet, currow,(4 + (2*2)+1), auxRacs.getFechaElaboracRact2().toString() ); //fecha elabora 8***
                                                    }
                                                    else{
                                                        setExDat(sheet, currow,(4 + (2*2)+1)," " ); //fecha elabora 8***
                                                    }
                                                    setStyleCell(sheet, borderstabla, currow, (4 + (2*2)));
                                                    setStyleCell(sheet, borderstabla, currow, (4 + (2*2)+1));
                                               
                                            }else{
                                                setExDat(sheet, currow,(4 + (2*2)), " " ); //% avance 7**
                                                setExDat(sheet, currow,(4 + (2*2)+1), " " ); //fecha elabora 8***
                                                setStyleCell(sheet, borderstabla, currow, (4 + (2*2)));
                                                setStyleCell(sheet, borderstabla, currow, (4 + (2*2)+1));
                                            }

                                            if(auxRacs.getStatusRact3()!=null){
                                                
                                                    setExDat(sheet, currow,(4 + (3*2)), auxRacs.getPorcentAvanceRact3() ); //% avance 7**
                                                    if(auxRacs.getFechaElaboracRact3()!=null){
                                                        setExDat(sheet, currow,(4 + (3*2)+1), auxRacs.getFechaElaboracRact3().toString() ); //fecha elabora 8***
                                                    }
                                                    else{
                                                        setExDat(sheet, currow,(4 + (3*2)+1), " " ); //fecha elabora 8***
                                                    }
                                                    setStyleCell(sheet, borderstabla, currow, (4 + (3*2)));
                                                    setStyleCell(sheet, borderstabla, currow, (4 + (3*2)+1));
                                                
                                            }
                                            else{
                                                setExDat(sheet, currow,(4 + (3*2)), " "  ); //% avance 7**
                                                setExDat(sheet, currow,(4 + (3*2)+1), " " ); //fecha elabora 8***
                                                setStyleCell(sheet, borderstabla, currow, (4 + (3*2)));
                                                setStyleCell(sheet, borderstabla, currow, (4 + (3*2)+1));   
                                            }
                                    
                                       
                                      
                                        currow++;
                                    }
                                    
                                
                                
                                currow+=2;
                            }
                            
                        //cabeceran excel                         
                        //sheet = cabezeraGeneralExcel(sheet,uabcLogo,style);
                    }// fin del la comparacon de plan
                }// fin del for por plan de estudios
                 
                 
            }// fin de si es por entregados if(entregados)
               
            
        }//fin de programas educativos
        
        if(criterio.equalsIgnoreCase("area_conocimiento")){
           HSSFSheet sheet = workbook.createSheet("Graficos");
           nombreLibro = "- Area de conocimiento";

           if(reporte.equalsIgnoreCase("entregados") || reporte.equalsIgnoreCase("noentregados") || reporte.equalsIgnoreCase("entregadosynoentregados")){
               
               if(reporte.equalsIgnoreCase("entregados"))
                nombreLibro = "Entregados " + nombreLibro;
               
               if(reporte.equalsIgnoreCase("noentregados"))
                nombreLibro = "No Entregados " + nombreLibro;
               
               if(reporte.equalsIgnoreCase("entregadosynoentregados"))
                nombreLibro = "Entregados Y No Entregados " + nombreLibro;
                
                //definimos encabezado
                sheet = cabezeraGeneralExcel(sheet,uabcLogo,style);
                
                //mostraremos tabla con
                // Programas educativos //  Total Racs entregados // Total Esperados
                //preparamos informacion para insertar
                List<Programaeducativo> programasByUnidad = getProgramasByUnidad();
                List<Areaconocimiento> areasByUnidad = getAreasByPlanesEstudioClave();

                /* Programacion de Hojas*/
                for(Areaconocimiento areaConocimiento: areasByUnidad){
                    if( selectAreaConocimiento.contains( Integer.toString( areaConocimiento.getAcoclave() ) ) ){
                        //aqui creamos la hoja para el programa
                        sheet = workbook.createSheet(areaConocimiento.getAconombre());
                        //sheet = cabezeraGeneralExcel(sheet,uabcLogo,style); lo moveremos al final de la hoja para que no sea mdoficada la imagen
                        setExDat(sheet, 8, 3, "Concentrado de Reporte de Avance de Contenido Temático de Entregados por Area de conocimiento");

                        boolean autotam = true;

                        //informacion de programa educativo tabla
                        setExDat(sheet, 14, 1, "Clave");
                        setExDat(sheet, 14, 2, "Programa Educativo");
                        setExDat(sheet, 14, 3, "Plan de Estudios");
                        setExDat(sheet, 14, 4, "Responsable");

                        setStyleCell(sheet, headerTabla, 14, 1);
                        setStyleCell(sheet, headerTabla, 14, 2);
                        setStyleCell(sheet, headerTabla, 14, 3);
                        setStyleCell(sheet, headerTabla, 14, 4);
                        
                        ArrayList <ReporteAvanceAux> tempListAux = new ArrayList<ReporteAvanceAux>();        
                        for(ReporteAvanceAux aux : listaAux){
                            if( aux.getAreaConocimiento().getAcoclave() == areaConocimiento.getAcoclave() )
                            {
                                tempListAux.add(aux);
                            }
                        }
                        
                        int currow = 22;
                        setExDat(sheet, currow,1, "Clave unidad de aprendizaje" );
                        setExDat(sheet, currow,2, "Unidad de aprendizaje" );
                        setExDat(sheet, currow,3, "No. de empleado" );
                        setExDat(sheet, currow,4, "Nombre del profesor" );
                        setExDat(sheet, currow,5, "Grupo" );
                        setExDat(sheet, currow,6, "% Avance 1er reporte" );
                        setExDat(sheet, currow,7, "Fecha de elaboración 1er RACT" );
                        setExDat(sheet, currow,8, "% Avance 2do reporte" );
                        setExDat(sheet, currow,9, "Fecha de elaboración 2do RACT" );
                        setExDat(sheet, currow,10, "% Avance 3er reporte" );
                        setExDat(sheet, currow,11, "Fecha de elaboración 3er RACT" );

                        //autosize para la columna
                        if(autotam){
                            sheet.autoSizeColumn(2);
                            sheet.autoSizeColumn(4);
                            sheet.autoSizeColumn(11);
                            autotam = false;
                        }
                        // para formatear toda la linea
                        for(int i=1;i<=11;i++){
                          setStyleCell(sheet, headerTabla, currow, i);  
                        }
                        currow++;
                        
                        
                        int uniprofeTemp = 0 ; 
                        for(ReporteAvanceAux auxRacs :tempListAux){
                            
                            //marcamos bordes
                            for(int i=1;i<=11;i++){
                                setExDat(sheet, currow,i," ");
                            }
                            for(int i=1;i<=11;i++){
                                setStyleCell(sheet, borderstabla, currow, i);
                            }

                            String nompreP = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getPronombre();
                            String apellidoPP = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoPaterno();
                            String apellidoPM = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getProapellidoMaterno();
                            int claveUnidadApren = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapclave();
                            String nombreUnidad = auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUnidadaprendizaje().getUapnombre();

                            uniprofeTemp = claveUnidadApren;
                            setExDat(sheet, currow,1, claveUnidadApren ); //clave
                            setExDat(sheet, currow,2, nombreUnidad ); //nombre unidad
                            //reporteAvance.unidadaprendizajeImparteProfesor.profesor.pronumeroEmpleado
                            setExDat(sheet, currow,3, auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getProfesor().getPronumeroEmpleado() ); //numero empleado
                            setExDat(sheet, currow,4, apellidoPP+ " "+apellidoPM+ " "+nompreP ); //nombre maestro
                            setExDat(sheet, currow,5, auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getGrupo().getGponumero() + "-"+ auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUiptipoSubgrupo()  + "-"+ auxRacs.getReporteAvance().getUnidadaprendizajeImparteProfesor().getUipsubgrupo() ); //grupo numero

                            //marcamos bordes
                            for(int i=1;i<=5;i++){
                                setStyleCell(sheet, borderstabla, currow, i);
                            }
                            //tienne que ser entregados/enviados
                            if(auxRacs.getStatusRact1()!=null){

                                    System.out.println("Entro a enviados 1");
                                    setExDat(sheet, currow,(4 + (1*2)), auxRacs.getPorcentAvanceRact1() ); //% avance 7**

                                    if(auxRacs.getFechaElaboracRact1()!=null){
                                        setExDat(sheet, currow,(4 + (1*2)+1), auxRacs.getFechaElaboracRact1().toString() ); //fecha elabora 8***
                                    }
                                    else{
                                        setExDat(sheet, currow,(4 + (1*2)+1), " " ); //fecha elabora 8***
                                    }
                                    setStyleCell(sheet, borderstabla, currow, (4 + (1*2)));
                                    setStyleCell(sheet, borderstabla, currow, (4 + (1*2)+1));

                            }
                            else{
                                setExDat(sheet, currow,(4 + (1*2)), " " ); //% avance 7**
                                setExDat(sheet, currow,(4 + (1*2)+1), " " ); //fecha elabora 8***
                                setStyleCell(sheet, borderstabla, currow, (4 + (1*2)));
                                setStyleCell(sheet, borderstabla, currow, (4 + (1*2)+1));
                            }


                            if(auxRacs.getStatusRact2()!=null){

                                    setExDat(sheet, currow,(4 + (2*2)), auxRacs.getPorcentAvanceRact2() ); //% avance 7**

                                    if(auxRacs.getFechaElaboracRact2()!=null){
                                        setExDat(sheet, currow,(4 + (2*2)+1), auxRacs.getFechaElaboracRact2().toString() ); //fecha elabora 8***
                                    }
                                    else{
                                        setExDat(sheet, currow,(4 + (2*2)+1)," " ); //fecha elabora 8***
                                    }
                                    setStyleCell(sheet, borderstabla, currow, (4 + (2*2)));
                                    setStyleCell(sheet, borderstabla, currow, (4 + (2*2)+1));

                            }else{
                                setExDat(sheet, currow,(4 + (2*2)), " " ); //% avance 7**
                                setExDat(sheet, currow,(4 + (2*2)+1), " " ); //fecha elabora 8***
                                setStyleCell(sheet, borderstabla, currow, (4 + (2*2)));
                                setStyleCell(sheet, borderstabla, currow, (4 + (2*2)+1));
                            }

                            if(auxRacs.getStatusRact3()!=null){

                                    setExDat(sheet, currow,(4 + (3*2)), auxRacs.getPorcentAvanceRact3() ); //% avance 7**
                                    if(auxRacs.getFechaElaboracRact3()!=null){
                                        setExDat(sheet, currow,(4 + (3*2)+1), auxRacs.getFechaElaboracRact3().toString() ); //fecha elabora 8***
                                    }
                                    else{
                                        setExDat(sheet, currow,(4 + (3*2)+1), " " ); //fecha elabora 8***
                                    }
                                    setStyleCell(sheet, borderstabla, currow, (4 + (3*2)));
                                    setStyleCell(sheet, borderstabla, currow, (4 + (3*2)+1));

                            }
                            else{
                                setExDat(sheet, currow,(4 + (3*2)), " "  ); //% avance 7**
                                setExDat(sheet, currow,(4 + (3*2)+1), " " ); //fecha elabora 8***
                                setStyleCell(sheet, borderstabla, currow, (4 + (3*2)));
                                setStyleCell(sheet, borderstabla, currow, (4 + (3*2)+1));   
                            }


                            currow++;
                        }
                        
                        
                        
                        //fin de codigo e imrpesion de cabezera
                        sheet = cabezeraGeneralExcel(sheet,uabcLogo,style);
                    }
                                                
                }// fin del for por plan de estudios     
            }// fin de si es por entregados if(entregados)
        }//fin de areas de conocimiento
        
        
        //finalizamos con
        //metodo para descargar el objeto
        System.out.println("Generando Excel");
        if(criterio!="" && reporte!=""){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.setResponseContentType("application/vnd.ms-excel");
            externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\""+nombreLibro+".xls\"");
            workbook.write(externalContext.getResponseOutputStream());
            facesContext.responseComplete();
        }
        else{
            System.out.println("No se Genero por: Criterio->"+criterio+" , Reporte->"+reporte);
        }
    }
   
    public HSSFSheet cabezeraGeneralExcel(HSSFSheet sheet,int logouabc,HSSFCellStyle style){
        /* Create the drawing container */
        HSSFPatriarch drawing = sheet.createDrawingPatriarch();
        /* Create an anchor point */
        ClientAnchor my_anchor = new HSSFClientAnchor();
        /* Define top left corner, and we can resize picture suitable from there */
        my_anchor.setCol1(1);
        my_anchor.setRow1(1);           
        /* Invoke createPicture and pass the anchor point and ID */
        HSSFPicture  my_picture = drawing.createPicture(my_anchor, logouabc);
        /* Call resize method, which resizes the image */
        double escalaRes = 1;
        my_picture.resize(escalaRes);
        //definiremos el estilo para estas Celdas
        //Definiremos el nombre de la escuela
        HSSFRow row = sheet.createRow(2);
        row.setHeight((short)600);
        HSSFCell cell = row.createCell(3);
        cell.setCellValue("Universidad Autónoma de Baja California");
        cell.setCellStyle(style); 
        row = sheet.createRow(3);
        row.setHeight((short)600);
        cell = row.createCell(3);
        cell.setCellValue("Facultad de Ingeniería");
        cell.setCellStyle(style);
        row = sheet.createRow(4);
        row.setHeight((short)600);
        cell = row.createCell(3);
        cell.setCellValue("Mexicali");
        cell.setCellStyle(style);
        return sheet;
    }
    
    public HSSFSheet setExDat(HSSFSheet sheet, int prow, int pcol, String valor){
        if(sheet.getRow(prow)!=null){
           HSSFRow row = sheet.getRow(prow);
            HSSFCell cell = row.createCell(pcol);
            cell.setCellValue(valor);
        }
        else{
           HSSFRow row = sheet.createRow(prow);
            HSSFCell cell = row.createCell(pcol);
            cell.setCellValue(valor);
        }
        return sheet;
    }
    
    public HSSFSheet setExDat(HSSFSheet sheet, int prow, int pcol, int valor){
        if(sheet.getRow(prow)!=null){
           HSSFRow row = sheet.getRow(prow);
            HSSFCell cell = row.createCell(pcol);
            cell.setCellValue(valor);
        }
        else{
           HSSFRow row = sheet.createRow(prow);
            HSSFCell cell = row.createCell(pcol);
            cell.setCellValue(valor);
        }
        return sheet;
    }
    
    public HSSFSheet setExDat(HSSFSheet sheet, int prow, int pcol, float valor){
        if(sheet.getRow(prow)!=null){
           HSSFRow row = sheet.getRow(prow);
            HSSFCell cell = row.createCell(pcol);
            cell.setCellValue(valor);
        }
        else{
           HSSFRow row = sheet.createRow(prow);
            HSSFCell cell = row.createCell(pcol);
            cell.setCellValue(valor);
        }
        return sheet;
    }
    
    public HSSFSheet setStyleCell(HSSFSheet sheet,HSSFCellStyle style, int prow, int pcol){
        if(sheet.getRow(prow)!=null){
           HSSFRow row = sheet.getRow(prow);
           if(row.getCell(pcol)!=null){
               HSSFCell cell = row.getCell(pcol);
               cell.setCellStyle(style);
           }
        }
        return sheet;
    }
    //codigo Rodrigo y Ricardo
    
    //codigo metodos Jesus Ruelas
    private void initRact(){
       filtrosBeanHelper=new FiltrosBeanHelper();
       listaAux=new ArrayList<ReporteAvanceAux>();
       catalogoreportesDelegate= new CatalogoReportesDelegate();
       op=null;
       tipo=null;
       calnumeroReporte=0;
       numRact=0;
       cescicloEscolar=null;
       acoclave=0;
       clavepe=0;
       pesvigencia=null;
       numProfUIPid=0;
       fecha1=null;
       pronumeroEmpleado=0;
       gponumero=0;
       clave=0;
       uapclave=0;
       uacclave=0;
       creid=0;
       //aqui agregue Jesus Ruelas
       CTRnombre = null;
       tipoReporteCTR = null;
       listaCatalogoReportes=null;       
       //aqui agregue Jesus Ruelas
    }
    
    private void initConsultasGuardadas(){
        todasConsultasGuardadas();
    }
    
    public void mostrarMensajeGrowl(){
        FacesContext context = FacesContext.getCurrentInstance();
        //guardarConsultaSeleccionar
        if(tipoMensajeGrowl.equalsIgnoreCase("guardarConsulta")){
          context.addMessage(null, new FacesMessage("Se guardó correctamente",  "") );
        }
        if(tipoMensajeGrowl.equalsIgnoreCase("guardarConsultaSeleccionar")){
          context.addMessage(null, new FacesMessage("Indique un nombre a la consulta a guardar",  "") );
        }
        if(tipoMensajeGrowl.equalsIgnoreCase("consultaDuplicada")){
          context.addMessage(null, new FacesMessage("No se guardó el reporte debido a que existe una consulta guardada con el mismo nombre",  "") );
          FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "No se guardó el reporte debido a que existe una consulta guardada con el mismo nombre");
          RequestContext.getCurrentInstance().showMessageInDialog(message);
          
          //FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_INFO, "message *****le", "message body");
          //RequestContext.getCurrentInstance().showMessageInDialog(message2);
          
          //RequestContext.getCurrentInstance().openDialog("Info1");
          //FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Title", "Message body");
          //RequestContext.getCurrentInstance().showMessageInDialog(message2);
        }
        if(tipoMensajeGrowl.equalsIgnoreCase("modificarConsulta")){
          context.addMessage(null, new FacesMessage("Se guardó correctamente",  "") );
        }
        if(tipoMensajeGrowl.equalsIgnoreCase("modificarConsultaSeleccionar")){
          context.addMessage(null, new FacesMessage("Seleccione un nombre de consulta a modificar",  "") );
        }
        if(tipoMensajeGrowl.equalsIgnoreCase("eliminarConsulta")){
          context.addMessage(null, new FacesMessage("Se eliminó correctamente",  "") );
        }
        if(tipoMensajeGrowl.equalsIgnoreCase("eliminarConsultaSeleccionar")){
          context.addMessage(null, new FacesMessage("Seleccione un nombre de consulta a eliminar",  "") );
        }
        if(tipoMensajeGrowl.equalsIgnoreCase("ejecutarQueryConsultaGuardada")){
          context.addMessage(null, new FacesMessage("Operación realizada correctamente",  "") );
        }
        if(tipoMensajeGrowl.equalsIgnoreCase("ejecutarQueryConsultaGuardadaSeleccionar")){
          context.addMessage(null, new FacesMessage("Favor de seleccionar una consulta",  "") );
        }
        //context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
        
    }

    //metodo para guardar consulta(tipo de reporte de consulta) del usuario tomando 
    //los atributos que seleccione el usuario como predefinidos en esta consulta
    //guardada lo guarda en la base de datos en un atributo tipo String y
    //tiene otro atributo un nombre de la consulta guardada definido por el
    //usuario
    public void guardarConsulta(){
    
        initConsultasGuardadas();
        
        //en este atrubuto guarda el nombre del metodo al que llama
        //o es propio de la consulta guardada(uno de los 64 metodos 
        //de consulta
        String tipoReporteUI;
                
        //**Estas son las opciones de los metodos de este beanUI(64 metodos) para 
        //todas las consultas, por lo que se copia el nombre de esta lista en
        //el atributo tipoReporteUI(tipo String)**:
        
        //**Opciones tipoReporteUI:
        //ATiempoAreaCon
        //ATiempoAreaConTodosRacts
        //ATiempoProfGrupo
        //ATiempoProfGrupoTodosRacts
        //ATiempoProgEd
        //ATiempoProgEdTodosRacts
        //ATiempoUAGrupo
        //ATiempoUAGrupoTodosRacts
        //ATiempoYNoYEnLimiteAreaCon
        //ATiempoYNoYEnLimiteAreaConTodosRacts
        //ATiempoYNoYEnLimiteProfGrupo
        //ATiempoYNoYEnLimiteProfGrupoTodosRacts
        //ATiempoYNoYEnLimiteProgEd
        //ATiempoYNoYEnLimiteProgEdTodosRacts
        //ATiempoYNoYEnLimiteUAGrupo
        //ATiempoYNoYEnLimiteUAGrupoTodosRacts
        //PAGCAreaCon
        //PAGCAreaConTodosRacts
        //PAGCCompletoAreaCon
        //PAGCompletoEIncompletoAreaCon
        //PAGCompletoEIncompletoProfGrupo
        //PAGCompletoEIncompletoProgEd
        //PAGCompletoEIncompletoUAGrupo
        //PAGCompletoProfGrupo
        //PAGCompletoProgEd
        //PAGCompletoUAGrupo
        //PAGCompletosYNoAreaConTodosRacts
        //PAGCompletosYNoProfGrupoTodosRacts
        //PAGCompletosYNoProgEdTodosRacts
        //PAGCompletosYNoUAGrupoTodosRacts
        //PAGCProfGrupo
        //PAGCProfGrupoTodosRacts
        //PAGCProgEd
        //PAGCProgEdTodosRacts
        //PAGCUAGrupo
        //PAGCUAGrupoTodosRacts
        //enFechaLimiteTiempoAreaCon
        //enFechaLimiteTiempoProfGrupo
        //enFechaLimiteTiempoProgEd
        //enFechaLimiteTiempoUAGrupo
        //entregadosAreaCon
        //entregadosAreaConTodosRacts
        //entregadosProfGrupo
        //entregadosProfGrupoTodosRacts
        //entregadosProgEd
        //entregadosProgEdTodosRacts
        //entregadosUAGrupo
        //entregadosUAGrupoTodosRacts
        //entregadosYNoAreaConTodosRacts
        //entregadosYNoEntregadosAreaCon
        //entregadosYNoEntregadosProfGrupo
        //entregadosYNoEntregadosProgEd
        //entregadosYNoEntregadosUAGrupo
        //entregadosYNoProfGrupoTodosRacts
        //entregadosYNoProgEdTodosRacts
        //entregadosYNoUAGrupoTodosRacts
        //fueraTiempoAreaCon
        //fueraTiempoProfGrupo
        //fueraTiempoProgEd
        //fueraTiempoUAGrupo
        //noEntregadosAreaCon
        //noEntregadosProfGrupo
        //noEntregadosProgEd
        //noEntregadosUAGrupo
        //Opciones tipoReporteUI**
        
        //inicializa el objeto donde se guardaran los atributos que
        //se necesitan por la consulta guardada se ocupa inicializar 
        //para que los que no se ocupan sean ceros o nulos y los
        //ignore los métodos del DAO(pasando por las capas)
        ReporteAux reporteUI = new ReporteAux();        
        
        setNumRact(Integer.parseInt(ract));
        setCescicloEscolar(selectCicloEscolarList.get(0));        
        //setCescicloEscolar("2009-2");
        //setClavepe(Integer.parseInt(selectProgramEducativo.get(0)));
        //setClavepe(Integer.parseInt("14006"));
        //setPesvigencia("2009-2");
        
        //atributos necesarios para entregados en el objeto que se manda al
        //delegate
        reporteUI.setNumRact(numRact);
        reporteUI.setCescicloEscolar(cescicloEscolar);
        reporteUI.setAcoclave(acoclave);
        reporteUI.setClavepe(clavepe);
        reporteUI.setPesvigencia(pesvigencia);
        reporteUI.setPronumeroEmpleado(pronumeroEmpleado);
        reporteUI.setGponumero(gponumero);
        reporteUI.setClave(clavepe);
        reporteUI.setUapclave(uapclave); 
        
        //aqui
        ArrayList<ReporteAux> listaReporte = new ArrayList<ReporteAux>();
      if(botonCancelar==false){
        if(criterio.equalsIgnoreCase("programa_educativo")){
        listaReporte=filtrosBeanHelper.prepararAtribGuardarConsultaProgEd(reporteUI,selectCicloEscolarList, selectProgramEducativo);
        }     
        if(criterio.equalsIgnoreCase("area_conocimiento")){
        listaReporte=filtrosBeanHelper.prepararAtribGuardarConsultaAreaCon(reporteUI,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
        }
        if(criterio.equalsIgnoreCase("unidad_aprendizaje")){
        listaReporte=filtrosBeanHelper.prepararAtribGuardarConsultaUAprend(reporteUI,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
        }
        if(criterio.equalsIgnoreCase("profesor")){
        listaReporte=filtrosBeanHelper.prepararAtribGuardarConsultaProfesor(reporteUI,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
        }
      
        //en CTRnombre se guarda el nombre que definio el usuario para la
        //consulta(el nombre que identifica la consulta guardada)
 //       CTRnombre="Septimo reporte";
        String CTRConsultaQuery="";
        
        //manda reporteUI, tipoReporteUI y CTRnombre y se devuelve el formato
        //de tratamiento de cadenas con separadores "#" y ":" para el formato
        //que contiene el nombre del metodos y los atributos con los mismos 
        //separadores
        
       // ArrayList<> 
        
        List<Catalogoreportes> listaReportes = filtrosBeanHelper.todasConsultasGuardadasNormal();        
     
        tipoMensajeGrowl="guardarConsulta";
        Boolean bandera=false;
        
        
        for (Catalogoreportes cr : listaReportes) {
            if (cr.getCtrnombre().equalsIgnoreCase(CTRnombre)) {
                tipoMensajeGrowl="consultaDuplicada";
                bandera=true;
            }
        }
        
        String CTRnombreTemp=CTRnombre.trim();
     
        if ((bandera == false)&&!(CTRnombreTemp.equalsIgnoreCase(""))) {
            for (ReporteAux report : listaReporte) {
                CTRConsultaQuery = filtrosBeanHelper.guardarConsulta(report, tipoReporteCTR, CTRnombre);
            }
        }
    
        if(CTRnombre==null||CTRnombreTemp.equalsIgnoreCase("")){
          //if(CTRnombre.equalsIgnoreCase("")){
              tipoMensajeGrowl="guardarConsultaSeleccionar";
              //mostrarMensajeGrowl();
          //}
        }
        
        //return CTRConsultaQuery;        
        mostrarMensajeGrowl();
        todasConsultasGuardadas();
      }
    }
    
    public void modificarConsulta(){
    
        initConsultasGuardadas();
        
        ReporteAux reporteUI = new ReporteAux();
        
        setNumRact(Integer.parseInt(ract));
        setCescicloEscolar(selectCicloEscolarList.get(0));        
        //setCescicloEscolar("2009-2");
        //setClavepe(Integer.parseInt(selectProgramEducativo.get(0)));
        //setClavepe(Integer.parseInt("14006"));
        //setPesvigencia("2009-2");
        
        reporteUI.setNumRact(numRact);
        reporteUI.setCescicloEscolar(cescicloEscolar);
        reporteUI.setAcoclave(acoclave);
        reporteUI.setClavepe(clavepe);
        reporteUI.setPesvigencia(pesvigencia);
        reporteUI.setPronumeroEmpleado(pronumeroEmpleado);
        reporteUI.setGponumero(gponumero);
        reporteUI.setClave(clavepe);
        reporteUI.setUapclave(uapclave);   
        
        //aqui
        ArrayList<ReporteAux> listaReporte = new ArrayList<ReporteAux>();
       if(botonCancelar==false){
        if(criterio.equalsIgnoreCase("programa_educativo")){
        listaReporte=filtrosBeanHelper.prepararAtribGuardarConsultaProgEd(reporteUI,selectCicloEscolarList , selectProgramEducativo);
        }     
        if(criterio.equalsIgnoreCase("area_conocimiento")){
        listaReporte=filtrosBeanHelper.prepararAtribGuardarConsultaAreaCon(reporteUI,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
        }
        if(criterio.equalsIgnoreCase("unidad_aprendizaje")){
        listaReporte=filtrosBeanHelper.prepararAtribGuardarConsultaUAprend(reporteUI,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
        }
        if(criterio.equalsIgnoreCase("profesor")){
        listaReporte=filtrosBeanHelper.prepararAtribGuardarConsultaProfesor(reporteUI,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
        }
        
        //filtrosBeanHelper.guardarConsulta(reporteUI, tipoReporteCTR, CTRnombre);
        //filtrosBeanHelper.modificarConsulta(reporteUI, tipoReporteCTR, CTRnombre);        
                
//      List<Catalogoreportes> listaConsGuardadas=filtrosBeanHelper.todasConsultasGuardadasNormal();
        
        ArrayList<Integer> listaIdCTR = new ArrayList<Integer>();
        
//        for(Catalogoreportes cr: listaConsGuardadas){
//            if(cr.getCtrnombre().equalsIgnoreCase(CTRnombre)){
//                listaIdCTR.add(cr.getCtrid());
//            }
//        }
        //if(botonCancelar==false){
            filtrosBeanHelper.eliminarConsulta(tipoReporteCTR, CTRnombre);
        //}
        
        int cont=0;
        
        String CTRnombreTemp="";
        
        if(!(CTRnombre==null)){
        CTRnombreTemp=CTRnombre.trim();
        }
        
         //if (listaReporte.size() == listaIdCTR.size()) {
         if(!listaReporte.isEmpty()&&!(CTRnombreTemp.equalsIgnoreCase(""))){
            for (ReporteAux report : listaReporte) {
                //filtrosBeanHelper.modificarConsulta(report, tipoReporteCTR, CTRnombre, listaIdCTR.get(cont));
                filtrosBeanHelper.guardarConsulta(report, tipoReporteCTR, CTRnombre);
                cont++;
            }
         }
        
        
        tipoMensajeGrowl="modificarConsulta";
        
        if(CTRnombre==null||CTRnombreTemp.equalsIgnoreCase("")){
        //if(CTRnombre==null){
          //if(CTRnombre.equalsIgnoreCase("")){
              tipoMensajeGrowl="modificarConsultaSeleccionar"; 
              //mostrarMensajeGrowl();
          //}
         }
        
        mostrarMensajeGrowl();
        todasConsultasGuardadas();
       }
    }
    
    public void eliminarConsulta(){
        
        initConsultasGuardadas();
        
//        ReporteAux reporteUI = new ReporteAux();
//        
//        setNumRact(Integer.parseInt(ract));
//        setCescicloEscolar(selectCicloEscolarList.get(0));        
//        //setCescicloEscolar("2009-2");
//        //setClavepe(Integer.parseInt(selectProgramEducativo.get(0)));
//        //setClavepe(Integer.parseInt("14006"));
//        //setPesvigencia("2009-2");
//        
//        reporteUI.setNumRact(numRact);
//        reporteUI.setCescicloEscolar(cescicloEscolar);
//        reporteUI.setAcoclave(acoclave);
//        reporteUI.setClavepe(clavepe);
//        reporteUI.setPesvigencia(pesvigencia);
//        reporteUI.setPronumeroEmpleado(pronumeroEmpleado);
//        reporteUI.setGponumero(gponumero);
//        reporteUI.setClave(clavepe);
//        reporteUI.setUapclave(uapclave);  
//        
//        //aqui
//        ArrayList<ReporteAux> listaReporte = new ArrayList<ReporteAux>();
//        listaReporte=filtrosBeanHelper.prepararAtribGuardarConsulta(reporteUI, selectProgramEducativo);
        
        //filtrosBeanHelper.guardarConsulta(reporteUI, tipoReporteCTR, CTRnombre);
        //filtrosBeanHelper.eliminarConsulta(reporteUI, tipoReporteCTR, CTRnombre);
        
//        for(ReporteAux report: listaReporte){
//           filtrosBeanHelper.eliminarConsulta(report, tipoReporteCTR, CTRnombre);
//        }
        
        filtrosBeanHelper.eliminarConsulta(tipoReporteCTR, CTRnombre);
        
        tipoMensajeGrowl="eliminarConsulta";
        
        String CTRnombreTemp="";
        
        if(!(CTRnombre==null)){
        CTRnombreTemp=CTRnombre.trim();
        }
        
        if(CTRnombre==null||CTRnombreTemp.equalsIgnoreCase("")){
          //if(CTRnombre.equalsIgnoreCase("")){
              tipoMensajeGrowl="eliminarConsultaSeleccionar";
              //mostrarMensajeGrowl();
          //}
        } 
        
        mostrarMensajeGrowl();
        todasConsultasGuardadas();
    }
    
    public void todasConsultasGuardadas(){
        listaCatalogoReportes=filtrosBeanHelper.todasConsultasGuardadas();
    }
    
    //metodo que ejecuta el query guardado en la tabla Catalogoreportes
    //tomando como referencia el CTRnombre de esta misma tabla y pasa el id
    //que pertence al beanHelper que ejecutara el metodo indicado en el query
    //y regresa la lista con los valores de la consulta que se haría de forma
    //normal la presentación como siempre
    public void ejecutarQueryConsultaGuardada(){
      
      if((!(CTRnombre==null))){
          if(!(CTRnombre.equalsIgnoreCase(""))){
        initConsultasGuardadas();
                
        //consulta general de la tabla Catalogoreportes
        //List<Catalogoreportes> listaCatalogoReportes = catalogoreportesDelegate.getCatalogoreportes();
        List<Catalogoreportes> listaCatalogoReportes = filtrosBeanHelper.todasConsultasGuardadasNormal();
        
        //este es el nombre del criteria del query guardado que se buscará
        
   //CTRnombre = "Septimo reporte";
        int idCTR=0;
   //String tipoReporteCTR;
        
        ArrayList<Integer> idCTRlist = new ArrayList<Integer>();
        
        Catalogoreportes catReportDatosSelec = new Catalogoreportes();

        ArrayList<Catalogoreportes> catReportDatosSelec2 = new ArrayList<Catalogoreportes>();
        
        //hace una búsqueda dentro de la tabla Catalogoreportes el 
        //nombre que coincida y guarda su id del criteria de la misma tabla
        for(Catalogoreportes catalogoRep :listaCatalogoReportes){
            if(catalogoRep.getCtrnombre().equalsIgnoreCase(CTRnombre)){
                idCTR = catalogoRep.getCtrid();
                idCTRlist.add(idCTR);
                catReportDatosSelec=catalogoRep;
                catReportDatosSelec2.add(catalogoRep);
            }
        }
        
        int pos=0;
        
        for(Integer idCTR2: idCTRlist){
        System.out.println("***********************************************");
        System.out.println("idCTRlist(ejecutar query consulta guardada beanUI): "+idCTR2+" en la pos: "+pos);
        System.out.println("***********************************************");
        pos++;
        }
        
        //manda el id del criteraia guardado al beanHelper que ejecutará la
        //consulta y regresa una lista con todos los valores como se presentan
        //en todos los metodos de siempre en la vista(con el metodo especifico
        //guardado)
        tipoReporteCTR=filtrosBeanHelper.nombreTipoEjecutarQueryConsultaGuardada(idCTR);
        listaAux=filtrosBeanHelper.ejecutarQueryConsultaGuardada(idCTRlist);
        
        tipoMensajeGrowl="ejecutarQueryConsultaGuardada";
    
        
        if(catReportDatosSelec2.get(0).getCtrconsultaQuery().contains("PAGC")){
            isPAGCTodosRacts = true;
        }else{
            isPAGCTodosRacts = false;
        }
        
        todasConsultasGuardadas();
      //if(botonCancelar==false){  
        if(tipoReporteCTR.contains("ProgEd")){
            fijarConsultaReporteGuardadoActualProgEd(catReportDatosSelec2);
        }
        if(tipoReporteCTR.contains("AreaCon")){
            fijarConsultaReporteGuardadoActualAreaCon(catReportDatosSelec2);
        }
        if(tipoReporteCTR.contains("UAGrupo")){
            fijarConsultaReporteGuardadoActualUAprend(catReportDatosSelec2);
        }
        if(tipoReporteCTR.contains("ProfGrupo")){
            fijarConsultaReporteGuardadoActualProfesor(catReportDatosSelec2);
        }
      //}
      
        arreglarRactUnico();
       }
      }
      
      String CTRnombreTemp="";
      
      if(!(CTRnombre==null)){
      CTRnombreTemp=CTRnombre.trim();
      }
        
        if(CTRnombre==null||CTRnombre.equalsIgnoreCase("")){
          //if(CTRnombre.equalsIgnoreCase("")){
              tipoMensajeGrowl="ejecutarQueryConsultaGuardadaSeleccionar";
              //mostrarMensajeGrowl();
          //}
        }
        
        mostrarMensajeGrowl();
    }
    
    public void fijarConsultaReporteGuardadoActualProgEd(ArrayList<Catalogoreportes> catReportDatosSelec2){
        ArrayList<String> selectProgramaEducativo2 = new ArrayList<String>();
        ArrayList<String> selectCicloEscolarList2 = new ArrayList<String>();
        Boolean bandera=false;
        //List<Programaeducativo> todosProgEd = programaEducativoDelegate.getListaProgramaeducativo();

        selectCicloEscolarList.clear();
        selectAreaConocimiento.clear();
        selectProgramEducativo.clear();
        selectPlanesEstudio.clear();
        selectProfesor.clear();
        selectGrupo.clear();
        selectUnidadAprendisaje.clear();
        
        for (Catalogoreportes cr : catReportDatosSelec2) {

            String CTRConsultaQuery = cr.getCtrconsultaQuery();

            String CTRConsultaQueryArray[] = CTRConsultaQuery.split("#");

        //muestra cada atributo correctamente guardado
            String tipoReporteSTRArray[] = CTRConsultaQueryArray[0].split(":");

            //toma el valor para tipoReporteUI
            String tipoReporteUI = tipoReporteSTRArray[1];
            
            fijarReporteYCriterio(tipoReporteUI);
            
            
            String numRactSTRArray[] = CTRConsultaQueryArray[1].split(":");

    //toma el valor para numRact
            //reporteUI.setNumRact(Integer.parseInt(numRactSTRArray[1]));
            setRact(numRactSTRArray[1]);
            setNumRact(Integer.parseInt(numRactSTRArray[1]));

            String cescicloEscolarSTRArray[] = CTRConsultaQueryArray[2].split(":");

    //toma el valor para cescicloEscolar
            //reporteUI.setCescicloEscolar(cescicloEscolarSTRArray[1]);            
    
            bandera=false;
            
            for(String selCicloEs :selectCicloEscolarList){
                if(selCicloEs.equalsIgnoreCase(cescicloEscolarSTRArray[1])){
                    bandera=true;
                }
            }
            if(!bandera){
            selectCicloEscolarList2.add(cescicloEscolarSTRArray[1]);
            setSelectCicloEscolar(selectCicloEscolarList2);
            }
            
            String acoclaveSTRArray[] = CTRConsultaQueryArray[3].split(":");

    //toma el valor para acoclave
//    reporteUI.setAcoclave(Integer.parseInt(acoclaveSTRArray[1]));
            String clavepeSTRArray[] = CTRConsultaQueryArray[4].split(":");

    //toma el valor para clavepe
            //reporteUI.setClavepe(Integer.parseInt(clavepeSTRArray[1]));
            //setSelectProgramaEducativo(Integer.parseInt(clavepeSTRArray[1]));    
//            int clavePe = -1;
//            for (Programaeducativo pe : todosProgEd) {
//                if (pe.getPedclave() == (Integer.parseInt(clavepeSTRArray[1]))) {
//                    clavePe = pe.getPedclave();
//                }
//            }
            
            bandera=false;
            
            for(String selPE :selectProgramEducativo){
                if(selPE.equalsIgnoreCase(clavepeSTRArray[1])){
                    bandera=true;
                }
            }
            if(!bandera){
            int clavePe=Integer.parseInt(clavepeSTRArray[1]);
            selectProgramaEducativo2.add(String.valueOf(clavePe));
            setSelectProgramEducativo(selectProgramaEducativo2);
            programaeducativo.setPedclave(clavePe);
            }
            System.out.println("**************************(1)*******************************");
            System.out.println("programaeducativo clavePe(fijarEjecutarQuery): "+programaeducativo.getPedclave());
            System.out.println("**************************(1)*******************************");

            String pesvigenciaSTRArray[] = CTRConsultaQueryArray[5].split(":");

    //toma el valor para pesvigencia
//    reporteUI.setPesvigencia(pesvigenciaSTRArray[1]);
            String pronumeroEmpleadoSTRArray[] = CTRConsultaQueryArray[6].split(":");

    //toma el valor para pronumeroEmpleado
//    reporteUI.setPronumeroEmpleado(Integer.parseInt(pronumeroEmpleadoSTRArray[1]));
            String gponumeroSTRArray[] = CTRConsultaQueryArray[7].split(":");

    //toma el valor para pronumeroEmpleado
//    reporteUI.setGponumero(Integer.parseInt(gponumeroSTRArray[1]));
            String claveSTRArray[] = CTRConsultaQueryArray[8].split(":");

    //toma el valor para clave(clavepe)
//    reporteUI.setClave(Integer.parseInt(claveSTRArray[1]));
            String uapclaveSTRArray[] = CTRConsultaQueryArray[9].split(":");

    //toma el valor para uapclave
//    reporteUI.setUapclave(Integer.parseInt(uapclaveSTRArray[1]));
            System.out.println(" ");

//    CTRConsultaQuery="tipoReporte:"+tipoReporte+"#"+"numRact:"+reporte.getNumRact()+"#"+
//             "cescicloEscolar:"+reporte.getCescicloEscolar()+"#"+"acoclave:"+reporte.getAcoclave()+"#"+
//             "clavepe:"+reporte.getClavepe()+"#"+"pesvigencia:"+reporte.getPesvigencia()+"#"+
//             "pronumeroEmpleado:"+reporte.getPronumeroEmpleado()+"#"+"gponumero:"+reporte.getGponumero()+"#"+
//             "clave:"+reporte.getClavepe()+"#"+"uapclave:"+reporte.getUapclave();
            //hasta aqui codigo de test
        }
    }
    
    public void fijarConsultaReporteGuardadoActualAreaCon(ArrayList<Catalogoreportes> catReportDatosSelec2){
        ArrayList<String> selectProgramaEducativo2 = new ArrayList<String>();
        ArrayList<String> selectCicloEscolarList2 = new ArrayList<String>();
        ArrayList<String> selectAreaConocimientoList2 = new ArrayList<String>();
        ArrayList<String> selectPlanesEstudioList2 = new ArrayList<String>();
        //List<Programaeducativo> todosProgEd = programaEducativoDelegate.getListaProgramaeducativo();

        boolean bandera=false;
        
        selectCicloEscolarList.clear();
        selectAreaConocimiento.clear();
        selectProgramEducativo.clear();
        selectPlanesEstudio.clear();
        selectProfesor.clear();
        selectGrupo.clear();
        selectUnidadAprendisaje.clear();
        
        for (Catalogoreportes cr : catReportDatosSelec2) {

            String CTRConsultaQuery = cr.getCtrconsultaQuery();

            String CTRConsultaQueryArray[] = CTRConsultaQuery.split("#");

        //muestra cada atributo correctamente guardado
            String tipoReporteSTRArray[] = CTRConsultaQueryArray[0].split(":");

            //toma el valor para tipoReporteUI
            String tipoReporteUI = tipoReporteSTRArray[1];
            
            fijarReporteYCriterio(tipoReporteUI);
            
            
            String numRactSTRArray[] = CTRConsultaQueryArray[1].split(":");

    //toma el valor para numRact
            //reporteUI.setNumRact(Integer.parseInt(numRactSTRArray[1]));
            setRact(numRactSTRArray[1]);
            setNumRact(Integer.parseInt(numRactSTRArray[1]));

            String cescicloEscolarSTRArray[] = CTRConsultaQueryArray[2].split(":");

    //toma el valor para cescicloEscolar
            //reporteUI.setCescicloEscolar(cescicloEscolarSTRArray[1]); 
            bandera=false;
            
            for(String selCicloEs :selectCicloEscolarList){
                if(selCicloEs.equalsIgnoreCase(cescicloEscolarSTRArray[1])){
                    bandera=true;
                }
            }
            if(!bandera){
            selectCicloEscolarList2.add(cescicloEscolarSTRArray[1]);
            setSelectCicloEscolar(selectCicloEscolarList2);
            }
            
            String acoclaveSTRArray[] = CTRConsultaQueryArray[3].split(":");

    //toma el valor para acoclave
//    reporteUI.setAcoclave(Integer.parseInt(acoclaveSTRArray[1]));
            
            bandera=false;
            
            for(String selAreaCon :selectAreaConocimiento){
                if(selAreaCon.equalsIgnoreCase(acoclaveSTRArray[1])){
                    bandera=true;
                }
            }            
            if(!bandera){
            selectAreaConocimientoList2.add(acoclaveSTRArray[1]);
            setSelectAreaConocimiento(selectAreaConocimientoList2);
            }
            
            String clavepeSTRArray[] = CTRConsultaQueryArray[4].split(":");

    //toma el valor para clavepe
            //reporteUI.setClavepe(Integer.parseInt(clavepeSTRArray[1]));
            //setSelectProgramaEducativo(Integer.parseInt(clavepeSTRArray[1]));    
//            int clavePe = -1;
//            for (Programaeducativo pe : todosProgEd) {
//                if (pe.getPedclave() == (Integer.parseInt(clavepeSTRArray[1]))) {
//                    clavePe = pe.getPedclave();
//                }
//            }
            bandera=false;
            
            //for(String selPE :selectProgramEducativo){
            //    if(selPE.equalsIgnoreCase(clavepeSTRArray[1])){
            //        bandera=true;
            //    }
            //}
            if(!bandera){
            int clavePe=Integer.parseInt(clavepeSTRArray[1]);
            selectProgramaEducativo2.add(String.valueOf(clavePe));
            setSelectProgramEducativo(selectProgramaEducativo2);
            programaeducativo.setPedclave(clavePe);
            }
            System.out.println("**************************(1)*******************************");
            System.out.println("programaeducativo clavePe(fijarEjecutarQuery): "+programaeducativo.getPedclave());
            System.out.println("**************************(1)*******************************");

            String pesvigenciaSTRArray[] = CTRConsultaQueryArray[5].split(":");

    //toma el valor para pesvigencia
//    reporteUI.setPesvigencia(pesvigenciaSTRArray[1]);
             
            bandera=false;
            
            for(String selPlanE :selectPlanesEstudio){
                if(selPlanE.equalsIgnoreCase(pesvigenciaSTRArray[1])){
                    bandera=true;
                }
            }
            if(!bandera){
            selectPlanesEstudioList2.add(pesvigenciaSTRArray[1]);
            setSelectPlanesEstudio(selectPlanesEstudioList2);
            }
            
            String pronumeroEmpleadoSTRArray[] = CTRConsultaQueryArray[6].split(":");

    //toma el valor para pronumeroEmpleado
//    reporteUI.setPronumeroEmpleado(Integer.parseInt(pronumeroEmpleadoSTRArray[1]));
            String gponumeroSTRArray[] = CTRConsultaQueryArray[7].split(":");

    //toma el valor para pronumeroEmpleado
//    reporteUI.setGponumero(Integer.parseInt(gponumeroSTRArray[1]));
            String claveSTRArray[] = CTRConsultaQueryArray[8].split(":");

    //toma el valor para clave(clavepe)
//    reporteUI.setClave(Integer.parseInt(claveSTRArray[1]));
            String uapclaveSTRArray[] = CTRConsultaQueryArray[9].split(":");

    //toma el valor para uapclave
//    reporteUI.setUapclave(Integer.parseInt(uapclaveSTRArray[1]));
            System.out.println(" ");

//    CTRConsultaQuery="tipoReporte:"+tipoReporte+"#"+"numRact:"+reporte.getNumRact()+"#"+
//             "cescicloEscolar:"+reporte.getCescicloEscolar()+"#"+"acoclave:"+reporte.getAcoclave()+"#"+
//             "clavepe:"+reporte.getClavepe()+"#"+"pesvigencia:"+reporte.getPesvigencia()+"#"+
//             "pronumeroEmpleado:"+reporte.getPronumeroEmpleado()+"#"+"gponumero:"+reporte.getGponumero()+"#"+
//             "clave:"+reporte.getClavepe()+"#"+"uapclave:"+reporte.getUapclave();
            //hasta aqui codigo de test
        }
        
//        String areaConTemp;
//        int cont=0;
//        
//        ArrayList<String> selectAreaConTemp = new ArrayList<String>();
//        
//        selectAreaConTemp.addAll(selectAreaConocimiento);
//        
//        for(String selAcon:selectAreaConTemp){
//            cont=0;
//            for(String selAcon2:selectAreaConTemp){
//                if(selAcon.equalsIgnoreCase(selAcon2)){
//                    if(cont>=2){
//                    areaConTemp=selAcon;    
//                    selectAreaConocimiento.remove(areaConTemp);                    
//                    }
//                    cont++;
//                }                
//            }
//        }
        
    }
    
    public void fijarConsultaReporteGuardadoActualUAprend(ArrayList<Catalogoreportes> catReportDatosSelec2){
        ArrayList<String> selectProgramaEducativo2 = new ArrayList<String>();
        ArrayList<String> selectCicloEscolarList2 = new ArrayList<String>();
        ArrayList<String> selectAreaConocimientoList2 = new ArrayList<String>();
        ArrayList<String> selectPlanesEstudioList2 = new ArrayList<String>();
        ArrayList<String> selectGponumeroList2 = new ArrayList<String>();
        ArrayList<String> selectUapclaveList2 = new ArrayList<String>();
        Boolean bandera=false;
        //List<Programaeducativo> todosProgEd = programaEducativoDelegate.getListaProgramaeducativo();

        selectCicloEscolarList.clear();
        selectAreaConocimiento.clear();
        selectProgramEducativo.clear();
        selectPlanesEstudio.clear();
        selectProfesor.clear();
        selectGrupo.clear();
        selectUnidadAprendisaje.clear();
        
        for (Catalogoreportes cr : catReportDatosSelec2) {

            String CTRConsultaQuery = cr.getCtrconsultaQuery();

            String CTRConsultaQueryArray[] = CTRConsultaQuery.split("#");

        //muestra cada atributo correctamente guardado
            String tipoReporteSTRArray[] = CTRConsultaQueryArray[0].split(":");

            //toma el valor para tipoReporteUI
            String tipoReporteUI = tipoReporteSTRArray[1];
            
            fijarReporteYCriterio(tipoReporteUI);
            
            
            String numRactSTRArray[] = CTRConsultaQueryArray[1].split(":");

    //toma el valor para numRact
            //reporteUI.setNumRact(Integer.parseInt(numRactSTRArray[1]));
            setRact(numRactSTRArray[1]);
            setNumRact(Integer.parseInt(numRactSTRArray[1]));

            String cescicloEscolarSTRArray[] = CTRConsultaQueryArray[2].split(":");

    //toma el valor para cescicloEscolar
            //reporteUI.setCescicloEscolar(cescicloEscolarSTRArray[1]);            
            
            bandera=false;
            
            for(String selCicloEs :selectCicloEscolarList){
                if(selCicloEs.equalsIgnoreCase(cescicloEscolarSTRArray[1])){
                    bandera=true;
                }
            }
            if(!bandera){            
            selectCicloEscolarList2.add(cescicloEscolarSTRArray[1]);
            setSelectCicloEscolar(selectCicloEscolarList2);
            }
            
            String acoclaveSTRArray[] = CTRConsultaQueryArray[3].split(":");

    //toma el valor para acoclave
//    reporteUI.setAcoclave(Integer.parseInt(acoclaveSTRArray[1]));            
            
            bandera=false;
            
            for(String selAreaCon :selectAreaConocimiento){
                if(selAreaCon.equalsIgnoreCase(acoclaveSTRArray[1])){
                    bandera=true;
                }
            }            
            if(!bandera){            
            selectAreaConocimientoList2.add(acoclaveSTRArray[1]);
            setSelectAreaConocimiento(selectAreaConocimientoList2);
            }
            
            String clavepeSTRArray[] = CTRConsultaQueryArray[4].split(":");

    //toma el valor para clavepe
            //reporteUI.setClavepe(Integer.parseInt(clavepeSTRArray[1]));
            //setSelectProgramaEducativo(Integer.parseInt(clavepeSTRArray[1]));    
//            int clavePe = -1;
//            for (Programaeducativo pe : todosProgEd) {
//                if (pe.getPedclave() == (Integer.parseInt(clavepeSTRArray[1]))) {
//                    clavePe = pe.getPedclave();
//                }
//            }
            
            bandera=false;
            
            //for(String selPE :selectProgramEducativo){
            //    if(selPE.equalsIgnoreCase(clavepeSTRArray[1])){
            //        bandera=true;
            //    }
            //}
            if(!bandera){            
            int clavePe=Integer.parseInt(clavepeSTRArray[1]);
            selectProgramaEducativo2.add(String.valueOf(clavePe));
            setSelectProgramEducativo(selectProgramaEducativo2);
            programaeducativo.setPedclave(clavePe);
            }
            System.out.println("**************************(1)*******************************");
            System.out.println("programaeducativo clavePe(fijarEjecutarQuery): "+programaeducativo.getPedclave());
            System.out.println("**************************(1)*******************************");

            String pesvigenciaSTRArray[] = CTRConsultaQueryArray[5].split(":");

    //toma el valor para pesvigencia
//    reporteUI.setPesvigencia(pesvigenciaSTRArray[1]);
                        
            bandera=false;
            
            for(String selPlanE :selectPlanesEstudio){
                if(selPlanE.equalsIgnoreCase(pesvigenciaSTRArray[1])){
                    bandera=true;
                }
            }
            if(!bandera){
            selectPlanesEstudioList2.add(pesvigenciaSTRArray[1]);
            setSelectPlanesEstudio(selectPlanesEstudioList2);
            }
            
            String pronumeroEmpleadoSTRArray[] = CTRConsultaQueryArray[6].split(":");

    //toma el valor para pronumeroEmpleado
//    reporteUI.setPronumeroEmpleado(Integer.parseInt(pronumeroEmpleadoSTRArray[1]));
            String gponumeroSTRArray[] = CTRConsultaQueryArray[7].split(":");

    //toma el valor para pronumeroEmpleado
//    reporteUI.setGponumero(Integer.parseInt(gponumeroSTRArray[1]));
             
            bandera=false;
            
            for(String selGrupo :selectGrupo){
                if(selGrupo.equalsIgnoreCase(gponumeroSTRArray[1])){
                    bandera=true;
                }
            }
            if(!bandera){            
            selectGponumeroList2.add(gponumeroSTRArray[1]);
            setSelectGrupo(selectGponumeroList2);
            }
            
            String claveSTRArray[] = CTRConsultaQueryArray[8].split(":");

    //toma el valor para clave(clavepe)
//    reporteUI.setClave(Integer.parseInt(claveSTRArray[1]));
            String uapclaveSTRArray[] = CTRConsultaQueryArray[9].split(":");

    //toma el valor para uapclave
//    reporteUI.setUapclave(Integer.parseInt(uapclaveSTRArray[1]));
                        
            bandera=false;
            
            for(String selUAprend :selectUnidadAprendisaje){
                if(selUAprend.equalsIgnoreCase(uapclaveSTRArray[1])){
                    bandera=true;
                }
            }
            if(!bandera){                        
            selectUapclaveList2.add(uapclaveSTRArray[1]);
            setSelectUnidadAprendisaje(selectUapclaveList2);
            }
            
            System.out.println(" ");

//    CTRConsultaQuery="tipoReporte:"+tipoReporte+"#"+"numRact:"+reporte.getNumRact()+"#"+
//             "cescicloEscolar:"+reporte.getCescicloEscolar()+"#"+"acoclave:"+reporte.getAcoclave()+"#"+
//             "clavepe:"+reporte.getClavepe()+"#"+"pesvigencia:"+reporte.getPesvigencia()+"#"+
//             "pronumeroEmpleado:"+reporte.getPronumeroEmpleado()+"#"+"gponumero:"+reporte.getGponumero()+"#"+
//             "clave:"+reporte.getClavepe()+"#"+"uapclave:"+reporte.getUapclave();
            //hasta aqui codigo de test
        } 
    }
    
    public void fijarConsultaReporteGuardadoActualProfesor(ArrayList<Catalogoreportes> catReportDatosSelec2){
        ArrayList<String> selectProgramaEducativo2 = new ArrayList<String>();
        ArrayList<String> selectCicloEscolarList2 = new ArrayList<String>();
        ArrayList<String> selectAreaConocimientoList2 = new ArrayList<String>();
        ArrayList<String> selectPlanesEstudioList2 = new ArrayList<String>();
        ArrayList<String> selectGponumeroList2 = new ArrayList<String>();
        ArrayList<String> selectUapclaveList2 = new ArrayList<String>();
        ArrayList<String> selectProfclaveList2 = new ArrayList<String>();
        //List<Programaeducativo> todosProgEd = programaEducativoDelegate.getListaProgramaeducativo();
        Boolean bandera=false;
        
        selectCicloEscolarList.clear();
        selectAreaConocimiento.clear();
        selectProgramEducativo.clear();
        selectPlanesEstudio.clear();
        selectProfesor.clear();
        selectGrupo.clear();
        selectUnidadAprendisaje.clear();
        
        for (Catalogoreportes cr : catReportDatosSelec2) {

            String CTRConsultaQuery = cr.getCtrconsultaQuery();

            String CTRConsultaQueryArray[] = CTRConsultaQuery.split("#");

        //muestra cada atributo correctamente guardado
            String tipoReporteSTRArray[] = CTRConsultaQueryArray[0].split(":");

            //toma el valor para tipoReporteUI
            String tipoReporteUI = tipoReporteSTRArray[1];
            
            fijarReporteYCriterio(tipoReporteUI);
            
            
            String numRactSTRArray[] = CTRConsultaQueryArray[1].split(":");

    //toma el valor para numRact
            //reporteUI.setNumRact(Integer.parseInt(numRactSTRArray[1]));
            setRact(numRactSTRArray[1]);
            setNumRact(Integer.parseInt(numRactSTRArray[1]));

            String cescicloEscolarSTRArray[] = CTRConsultaQueryArray[2].split(":");

    //toma el valor para cescicloEscolar
            //reporteUI.setCescicloEscolar(cescicloEscolarSTRArray[1]);            
            bandera=false;
            
            for(String selCicloEs :selectCicloEscolarList){
                if(selCicloEs.equalsIgnoreCase(cescicloEscolarSTRArray[1])){
                    bandera=true;
                }
            }
            if(!bandera){            
            selectCicloEscolarList2.add(cescicloEscolarSTRArray[1]);
            setSelectCicloEscolar(selectCicloEscolarList2);
            }

            String acoclaveSTRArray[] = CTRConsultaQueryArray[3].split(":");

    //toma el valor para acoclave
//    reporteUI.setAcoclave(Integer.parseInt(acoclaveSTRArray[1]));            
            
            bandera=false;
            
            for(String selAreaCon :selectAreaConocimiento){
                if(selAreaCon.equalsIgnoreCase(acoclaveSTRArray[1])){
                    bandera=true;
                }
            }            
            if(!bandera){            
            selectAreaConocimientoList2.add(acoclaveSTRArray[1]);
            setSelectAreaConocimiento(selectAreaConocimientoList2);
            }
            
            String clavepeSTRArray[] = CTRConsultaQueryArray[4].split(":");

    //toma el valor para clavepe
            //reporteUI.setClavepe(Integer.parseInt(clavepeSTRArray[1]));
            //setSelectProgramaEducativo(Integer.parseInt(clavepeSTRArray[1]));    
//            int clavePe = -1;
//            for (Programaeducativo pe : todosProgEd) {
//                if (pe.getPedclave() == (Integer.parseInt(clavepeSTRArray[1]))) {
//                    clavePe = pe.getPedclave();
//                }
//            }
            
            bandera=false;
            
            //for(String selPE :selectProgramEducativo){
            //    if(selPE.equalsIgnoreCase(clavepeSTRArray[1])){
            //        bandera=true;
            //    }
            //}
            if(!bandera){            
            int clavePe=Integer.parseInt(clavepeSTRArray[1]);
            selectProgramaEducativo2.add(String.valueOf(clavePe));
            setSelectProgramEducativo(selectProgramaEducativo2);
            programaeducativo.setPedclave(clavePe);
            }
            System.out.println("**************************(1)*******************************");
            System.out.println("programaeducativo clavePe(fijarEjecutarQuery): "+programaeducativo.getPedclave());
            System.out.println("**************************(1)*******************************");

            String pesvigenciaSTRArray[] = CTRConsultaQueryArray[5].split(":");

    //toma el valor para pesvigencia
//    reporteUI.setPesvigencia(pesvigenciaSTRArray[1]);
                        
            bandera=false;
            
            for(String selPlanE :selectPlanesEstudio){
                if(selPlanE.equalsIgnoreCase(pesvigenciaSTRArray[1])){
                    bandera=true;
                }
            }
            if(!bandera){
            selectPlanesEstudioList2.add(pesvigenciaSTRArray[1]);
            setSelectPlanesEstudio(selectPlanesEstudioList2);
            }
            
            String pronumeroEmpleadoSTRArray[] = CTRConsultaQueryArray[6].split(":");

    //toma el valor para pronumeroEmpleado
//    reporteUI.setPronumeroEmpleado(Integer.parseInt(pronumeroEmpleadoSTRArray[1]));
            
            bandera=false;
            
            for(String selProf :selectProfesor){
                if(selProf.equalsIgnoreCase(pronumeroEmpleadoSTRArray[1])){
                    bandera=true;
                }
            }
            if(!bandera){            
            selectProfclaveList2.add(pronumeroEmpleadoSTRArray[1]);
            setSelectProfesor(selectProfclaveList2);
            }
            
            String gponumeroSTRArray[] = CTRConsultaQueryArray[7].split(":");

    //toma el valor para pronumeroEmpleado
//    reporteUI.setGponumero(Integer.parseInt(gponumeroSTRArray[1]));
                        
            bandera=false;
            
            for(String selGrupo :selectGrupo){
                if(selGrupo.equalsIgnoreCase(gponumeroSTRArray[1])){
                    bandera=true;
                }
            }
            if(!bandera){            
            selectGponumeroList2.add(gponumeroSTRArray[1]);
            setSelectGrupo(selectGponumeroList2);
            }
            
            String claveSTRArray[] = CTRConsultaQueryArray[8].split(":");

    //toma el valor para clave(clavepe)
//    reporteUI.setClave(Integer.parseInt(claveSTRArray[1]));
            String uapclaveSTRArray[] = CTRConsultaQueryArray[9].split(":");

    //toma el valor para uapclave
//    reporteUI.setUapclave(Integer.parseInt(uapclaveSTRArray[1]));
                        
            bandera=false;
            
            for(String selUAprend :selectUnidadAprendisaje){
                if(selUAprend.equalsIgnoreCase(uapclaveSTRArray[1])){
                    bandera=true;
                }
            }
            if(!bandera){                        
            selectUapclaveList2.add(uapclaveSTRArray[1]);
            setSelectUnidadAprendisaje(selectUapclaveList2);
            }
            
            System.out.println(" ");

//    CTRConsultaQuery="tipoReporte:"+tipoReporte+"#"+"numRact:"+reporte.getNumRact()+"#"+
//             "cescicloEscolar:"+reporte.getCescicloEscolar()+"#"+"acoclave:"+reporte.getAcoclave()+"#"+
//             "clavepe:"+reporte.getClavepe()+"#"+"pesvigencia:"+reporte.getPesvigencia()+"#"+
//             "pronumeroEmpleado:"+reporte.getPronumeroEmpleado()+"#"+"gponumero:"+reporte.getGponumero()+"#"+
//             "clave:"+reporte.getClavepe()+"#"+"uapclave:"+reporte.getUapclave();
            //hasta aqui codigo de test
        }
    }
    
    public void fijarReporteYCriterio(String tipoReporteUI){
        
//            if(tipoReporteUI.contains("entregados")){
//                setReporte("entregados");
//            }
//            if(tipoReporteUI.contains("noentregados")){
//                setReporte("noentregados");
//            }
//            if(tipoReporteUI.contains("entregadosyno")||tipoReporteUI.contains("entregadosynoentregados")){
//                setReporte("entregadosynoentregados");
//            }
//            if(tipoReporteUI.contains("atiempo")){
//                setReporte("entregadosatiempo");
//            }
//            if(tipoReporteUI.contains("enfechalimite")){
//                setReporte("entregadosatiempo");
//            }
//            
//            if(tipoReporteUI.contains("proged")){
//                setCriterio("programa_educativo");
//            }
//            if(tipoReporteUI.contains("areacon")){
//                setCriterio("area_conocimiento");
//            }
//            if(tipoReporteUI.contains("uagrupo")){
//                setCriterio("unidad_aprendizaje");
//            }
//            if(tipoReporteUI.contains("profgrupo")){
//                setCriterio("profesor");
//            }
        
        //Opciones tipoReporteUI:
        
        //ATiempoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("ATiempoAreaCon")){                         
            setReporte("entregadosatiempo");            
            setCriterio("area_conocimiento");            
        }
        
        //ATiempoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoAreaConTodosRacts")){
            setReporte("entregadosatiempo");            
            setCriterio("area_conocimiento");            
        }
        
        //fueraTiempoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoAreaConTodosRacts")){            
            setReporte("entregadosdespueslimite");            
            setCriterio("area_conocimiento");            
        }
        
        //ATiempoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("ATiempoProfGrupo")){
            setReporte("entregadosatiempo");            
            setCriterio("profesor");            
        }
        
        //ATiempoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoProfGrupoTodosRacts")){
            setReporte("entregadosatiempo");            
            setCriterio("profesor");
        }
        
        //fueraTiempoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoProfGrupoTodosRacts")){            
            setReporte("entregadosdespueslimite");            
            setCriterio("profesor");
        }
        
        //ATiempoProgEd
        if(tipoReporteUI.equalsIgnoreCase("ATiempoProgEd")){
            setReporte("entregadosatiempo");            
            setCriterio("programa_educativo");            
        }
        
        //ATiempoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoProgEdTodosRacts")){
            setReporte("entregadosatiempo");            
            setCriterio("programa_educativo");            
        }
        
        //fueraTiempoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoProgEdTodosRacts")){            
            setReporte("entregadosdespueslimite");            
            setCriterio("programa_educativo");            
        }
        
        //ATiempoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("ATiempoUAGrupo")){
            setReporte("entregadosatiempo");            
            setCriterio("unidad_aprendizaje");            
        }
        
        //ATiempoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoUAGrupoTodosRacts")){
            setReporte("entregadosatiempo");            
            setCriterio("unidad_aprendizaje");            
        }
        
        //fueraTiempoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoUAGrupoTodosRacts")){            
            setReporte("entregadosdespueslimite");            
            setCriterio("unidad_aprendizaje");            
        }
        
        //ATiempoYNoYEnLimiteAreaCon
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteAreaCon")){                         
            setReporte("entregadosatiempoenfechalimiteydespues");            
            setCriterio("area_conocimiento");            
        }
        
        //ATiempoYNoYEnLimiteAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteAreaConTodosRacts")){                         
            setReporte("entregadosatiempoenfechalimiteydespues");
            setCriterio("area_conocimiento");            
        }
        
        //ATiempoYNoYEnLimiteProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProfGrupo")){            
            setReporte("entregadosatiempoenfechalimiteydespues");
            setCriterio("profesor");
        }
        
        //ATiempoYNoYEnLimiteProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProfGrupoTodosRacts")){            
            setReporte("entregadosatiempoenfechalimiteydespues");
            setCriterio("profesor");
        }
        
        //ATiempoYNoYEnLimiteProgEd
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProgEd")){            
            setReporte("entregadosatiempoenfechalimiteydespues");
            setCriterio("programa_educativo");            
        }
        
        //ATiempoYNoYEnLimiteProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteProgEdTodosRacts")){            
            setReporte("entregadosatiempoenfechalimiteydespues");
            setCriterio("programa_educativo");            
        }
        
        //ATiempoYNoYEnLimiteUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteUAGrupo")){            
            setReporte("entregadosatiempoenfechalimiteydespues");
            setCriterio("unidad_aprendizaje");            
        }
        
        //ATiempoYNoYEnLimiteUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("ATiempoYNoYEnLimiteUAGrupoTodosRacts")){            
            setReporte("entregadosatiempoenfechalimiteydespues");
            setCriterio("unidad_aprendizaje");            
        }
        
        //PAGCAreaCon
        if(tipoReporteUI.equalsIgnoreCase("PAGCAreaCon")){                         
            setReporte("Porcentaje de Avance Global Incompleto");            
            setCriterio("area_conocimiento");            
        }
        
        //PAGCAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCAreaConTodosRacts")){                         
            setReporte("Porcentaje de Avance Global Incompleto");            
            setCriterio("area_conocimiento");            
        }
        
        //PAGCCompletoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoAreaConTodosRacts")){             
            setReporte("Porcentaje de Avance Global Completo");            
            setCriterio("area_conocimiento");            
        }
        
        //PAGCCompletoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoAreaCon")){             
            setReporte("Porcentaje de Avance Global Completo");            
            setCriterio("area_conocimiento");            
        }
        
        //PAGCompletoEIncompletoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoAreaCon")){                        
            setReporte("Porcentaje de Avance Global Completo e Incompleto");
            setCriterio("area_conocimiento");            
        }
        
        //PAGCompletoEIncompletoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoProfGrupo")){            
            setReporte("Porcentaje de Avance Global Completo e Incompleto");
            setCriterio("profesor");
        }
        
        //PAGCompletoEIncompletoProgEd
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoProgEd")){            
            setReporte("Porcentaje de Avance Global Completo e Incompleto");
            setCriterio("programa_educativo");            
        }
        
        //PAGCompletoEIncompletoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoEIncompletoUAGrupo")){            
            setReporte("Porcentaje de Avance Global Completo e Incompleto");
            setCriterio("unidad_aprendizaje");            
        }
        
        //PAGCompletoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoProfGrupo")){
            setReporte("Porcentaje de Avance Global Completo");            
            setCriterio("profesor");
        }
        
        //PAGCompletoProgEd
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoProgEd")){
            setReporte("Porcentaje de Avance Global Completo");            
            setCriterio("programa_educativo");            
        }
        
        //PAGCompletoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletoUAGrupo")){
            setReporte("Porcentaje de Avance Global Completo");            
            setCriterio("unidad_aprendizaje");            
        }
        
        //PAGCompletosYNoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoAreaConTodosRacts")){                         
            setReporte("Porcentaje de Avance Global Completo e Incompleto");
            setCriterio("area_conocimiento");            
        }
        
        //PAGCompletosYNoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoProfGrupoTodosRacts")){            
            setReporte("Porcentaje de Avance Global Completo e Incompleto");
            setCriterio("profesor");
        }
        
        //PAGCompletosYNoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoProgEdTodosRacts")){            
            setReporte("Porcentaje de Avance Global Completo e Incompleto");
            setCriterio("programa_educativo");                        
        }
        
        //PAGCompletosYNoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCompletosYNoUAGrupoTodosRacts")){            
            setReporte("Porcentaje de Avance Global Completo e Incompleto");
            setCriterio("unidad_aprendizaje");                        
        }
        
        //PAGCProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCProfGrupo")){            
            setReporte("Porcentaje de Avance Global Incompleto");            
            setCriterio("profesor");
        }
        
        //PAGCProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCProfGrupoTodosRacts")){            
            setReporte("Porcentaje de Avance Global Incompleto");            
            setCriterio("profesor");
        }
        
        //PAGCCompletoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoProfGrupoTodosRacts")){
            setReporte("Porcentaje de Avance Global Completo");            
            setCriterio("profesor");
        }
        
        //PAGCProgEd
        if(tipoReporteUI.equalsIgnoreCase("PAGCProgEd")){            
            setReporte("Porcentaje de Avance Global Incompleto");            
            setCriterio("programa_educativo");                        
        }
        
        //PAGCProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCProgEdTodosRacts")){            
            setReporte("Porcentaje de Avance Global Incompleto");            
            setCriterio("programa_educativo");                        
        }
        
        //PAGCCompletoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoProgEdTodosRacts")){
            setReporte("Porcentaje de Avance Global Completo");            
            setCriterio("programa_educativo");                        
        }
        
        //PAGCUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("PAGCUAGrupo")){            
            setReporte("Porcentaje de Avance Global Incompleto");            
            setCriterio("unidad_aprendizaje");                        
        }
        
        //PAGCUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCUAGrupoTodosRacts")){            
            setReporte("Porcentaje de Avance Global Incompleto");            
            setCriterio("unidad_aprendizaje");                        
        }
        
        //PAGCCompletoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("PAGCCompletoUAGrupoTodosRacts")){
            setReporte("Porcentaje de Avance Global Completo");            
            setCriterio("unidad_aprendizaje");                        
        }
        
        //enFechaLimiteTiempoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoAreaCon")){                         
            setReporte("entregadosenfechalimite");            
            setCriterio("area_conocimiento");                                    
        }
        
        //enFechaLimiteTiempoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoAreaConTodosRacts")){             
            setReporte("entregadosenfechalimite");
            setCriterio("area_conocimiento");                                    
        }
        
        //enFechaLimiteTiempoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProfGrupo")){            
            setReporte("entregadosenfechalimite");            
            setCriterio("profesor");
        }
        
        //enFechaLimiteTiempoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProfGrupoTodosRacts")){            
            setReporte("entregadosenfechalimite");            
            setCriterio("profesor");
        }
        
        //enFechaLimiteTiempoProgEd
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProgEd")){            
            setReporte("entregadosenfechalimite");            
            setCriterio("programa_educativo");                        
        }
        
        //enFechaLimiteTiempoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoProgEdTodosRacts")){            
            setReporte("entregadosenfechalimite");            
            setCriterio("programa_educativo");                        
        }
        
        //enFechaLimiteTiempoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoUAGrupo")){            
            setReporte("entregadosenfechalimite");            
            setCriterio("unidad_aprendizaje");                        
        }
        
        //enFechaLimiteTiempoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("enFechaLimiteTiempoUAGrupoTodosRacts")){            
            setReporte("entregadosenfechalimite");            
            setCriterio("unidad_aprendizaje");                        
        }
        
        //entregadosAreaCon
        if(tipoReporteUI.equalsIgnoreCase("entregadosAreaCon")){             
            setReporte("entregados");            
            setCriterio("area_conocimiento");                                    
        }
        
        //entregadosAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosAreaConTodosRacts")){             
            setReporte("entregados");            
            setCriterio("area_conocimiento");                                    
        }
        
        //entregadosProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("entregadosProfGrupo")){
            setReporte("entregados");            
            setCriterio("profesor");
        }
        
        //entregadosProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosProfGrupoTodosRacts")){
            setReporte("entregados");            
            setCriterio("profesor");
        }
        
        //entregadosProgEd
        if(tipoReporteUI.equalsIgnoreCase("entregadosProgEd")){
            setReporte("entregados");            
            setCriterio("programa_educativo");                        
        }
        
        //entregadosProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosProgEdTodosRacts")){
            setReporte("entregados");            
            setCriterio("programa_educativo");                        
        }
        
        //entregadosUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("entregadosUAGrupo")){
            setReporte("entregados");            
            setCriterio("unidad_aprendizaje");                        
        }
        
        //entregadosUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosUAGrupoTodosRacts")){
            setReporte("entregados");            
            setCriterio("unidad_aprendizaje");                       
        }
        
        //entregadosYNoAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoAreaConTodosRacts")){                         
            setReporte("entregadosynoentregados");
            setCriterio("area_conocimiento");                                    
        }
        
        //entregadosYNoEntregadosAreaCon
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosAreaCon")){                         
            setReporte("entregadosynoentregados");
            setCriterio("area_conocimiento");                                    
        }
        
        //entregadosYNoEntregadosProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosProfGrupo")){            
            setReporte("entregadosynoentregados");
            setCriterio("profesor");
        }
        
        //entregadosYNoEntregadosProgEd
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosProgEd")){            
            setReporte("entregadosynoentregados");
            setCriterio("programa_educativo");                        
        }
        
        //entregadosYNoEntregadosUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoEntregadosUAGrupo")){            
            setReporte("entregadosynoentregados");
            setCriterio("unidad_aprendizaje");                        
        }
        
        //entregadosYNoProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoProfGrupoTodosRacts")){            
            setReporte("entregadosynoentregados");
            setCriterio("profesor");
        }
        
        //entregadosYNoProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoProgEdTodosRacts")){            
            setReporte("entregadosynoentregados");
            setCriterio("programa_educativo");                        
        }
        
        //entregadosYNoUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("entregadosYNoUAGrupoTodosRacts")){            
            setReporte("entregadosynoentregados");
            setCriterio("unidad_aprendizaje");                        
        }
        
        //fueraTiempoAreaCon
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoAreaCon")){                         
            setReporte("entregadosdespueslimite");            
            setCriterio("area_conocimiento");                                    
        }
        
        //fueraTiempoProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoProfGrupo")){            
            setReporte("entregadosdespueslimite");            
            setCriterio("profesor");
        }
        
        //fueraTiempoProgEd
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoProgEd")){            
            setReporte("entregadosdespueslimite");            
            setCriterio("programa_educativo");                        
        }
        
        //fueraTiempoUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("fueraTiempoUAGrupo")){            
            setReporte("entregadosdespueslimite");            
            setCriterio("unidad_aprendizaje");                        
        }
        
        //noEntregadosAreaCon
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosAreaCon")){                         
            setReporte("noentregados");            
            setCriterio("area_conocimiento");                                    
        }
        
        //noEntregadosAreaConTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosAreaConTodosRacts")){                         
            setReporte("noentregados");            
            setCriterio("area_conocimiento");                                    
        }
        
        //noEntregadosProfGrupo
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosProfGrupo")){            
            setReporte("noentregados");            
            setCriterio("profesor");
        }
        
        //noEntregadosProfGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosProfGrupoTodosRacts")){            
            setReporte("noentregados");            
            setCriterio("profesor");
        }
        
        //noEntregadosProgEd
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosProgEd")){            
            setReporte("noentregados");            
            setCriterio("programa_educativo");                        
        }
        
        //noEntregadosProgEdTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosProgEdTodosRacts")){            
            setReporte("noentregados");            
            setCriterio("programa_educativo");                        
        }
        
        //noEntregadosUAGrupo
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosUAGrupo")){            
            setReporte("noentregados");            
            setCriterio("unidad_aprendizaje");                        
        }
        
        //noEntregadosUAGrupoTodosRacts
        if(tipoReporteUI.equalsIgnoreCase("noEntregadosUAGrupoTodosRacts")){            
            setReporte("noentregados");           
            setCriterio("unidad_aprendizaje");                        
        }
            
    }
    
    public void selectionReporteTipo(){
        
        int nRact=Integer.parseInt(ract);
        
        isPAGCTodosRacts=false;
            //Opciones tipoReporteUI:        
                
//            //ATiempoProgEd
//            if (reporte.equalsIgnoreCase("ATiempoProgEd")&&criterio.equalsIgnoreCase("programa_educativo")) {
//                ATiempoProgEd();
//            }

            //ATiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosatiempo")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact==4||nRact==0)) {
                ATiempoProgEdTodosRacts();
            }
            
            //ATiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosatiempo")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact>0&&nRact<4)) {
                ATiempoProgEd();
            }

//            //ATiempoYNoYEnLimiteProgEd
//            if (reporte.equalsIgnoreCase("ATiempoYNoYEnLimiteProgEd")&&criterio.equalsIgnoreCase("programa_educativo")) {
//                ATiempoYNoYEnLimiteProgEd();
//            }

            //ATiempoYNoYEnLimiteProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosatiempoenfechalimiteydespues")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact==4||nRact==0)) {
                ATiempoYNoYEnLimiteProgEdTodosRacts();
            }
            
            //ATiempoYNoYEnLimiteProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosatiempoenfechalimiteydespues")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact>0&&nRact<4)) {
                ATiempoYNoYEnLimiteProgEd();
            }

//            //PAGCompletoEIncompletoProgEd
//            if (reporte.equalsIgnoreCase("PAGCompletoEIncompletoProgEd")&&criterio.equalsIgnoreCase("programa_educativo")) {
//               PAGCCompletoEIncompletoProgEd();
//            }

//            //PAGCompletoProgEd
//            if (reporte.equalsIgnoreCase("PAGCompletoProgEd")&&criterio.equalsIgnoreCase("programa_educativo")) {
//                PAGCCompletoProgEd();
//            }

            //PAGCompletosYNoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Completo e Incompleto")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact==4||nRact==0)) {
                PAGCCompletosYNoProgEdTodosRacts();
                isPAGCTodosRacts=true;
            }
            
            //PAGCompletosYNoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Completo e Incompleto")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact>0&&nRact<4)) {
                //PAGCCompletoEIncompletoProgEd();
                PAGCCompletosYNoProgEdTodosRacts();
                isPAGCTodosRacts=true;
            }

//            //PAGCProgEd
//            if (reporte.equalsIgnoreCase("PAGCProgEd")&&criterio.equalsIgnoreCase("programa_educativo")) {
//               PAGCProgEd();
//            }

            //PAGCProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Incompleto")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact==4||nRact==0)) {
                PAGCProgEdTodosRacts();
                isPAGCTodosRacts=true;
            }
            
            //PAGCProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Incompleto")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact>0&&nRact<4)) {
                //PAGCProgEd();
                PAGCProgEdTodosRacts();
                isPAGCTodosRacts=true;
            }

            //PAGCCompletoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Completo")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact==4||nRact==0)) {
                PAGCCompletoProgEdTodosRacts();
                isPAGCTodosRacts=true;
            }
            
            //PAGCCompletoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Completo")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact>0&&nRact<4)) {
                //PAGCCompletoProgEd();
                PAGCCompletoProgEdTodosRacts();
                isPAGCTodosRacts=true;
            }

//            //enFechaLimiteTiempoProgEd
//            if (reporte.equalsIgnoreCase("enFechaLimiteTiempoProgEd")&&criterio.equalsIgnoreCase("programa_educativo")) {
//                enFechaLimiteTiempoProgEd();
//            }

            //enFechaLimiteTiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosenfechalimite")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact==4||nRact==0)) {
                enFechaLimiteTiempoProgEdTodosRacts();
            }
            
            //enFechaLimiteTiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosenfechalimite")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact>0&&nRact<4)) {
                enFechaLimiteTiempoProgEd();
            }

//            //entregadosProgEd
//            if (reporte.equalsIgnoreCase("entregadosProgEd")&&criterio.equalsIgnoreCase("programa_educativo")) {
//                entregadosProgEd();
//            }

            //entregadosProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregados")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact==4||nRact==0)) {                
                entregadosProgEdTodosRacts();                
            }
            
            //entregadosProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregados")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact>0&&nRact<4)) {                
                entregadosProgEd();                
            }

            //noEntregadosProgEdTodosRacts
            if (reporte.equalsIgnoreCase("noentregados")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact==4||nRact==0)) {
                noEntregadosProgEdTodosRacts();
            }
            
            //noEntregadosProgEdTodosRacts
            if (reporte.equalsIgnoreCase("noentregados")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact>0&&nRact<4)) {
                noEntregadosProgEd();
            }

//            //entregadosYNoEntregadosProgEd
//            if (reporte.equalsIgnoreCase("entregadosYNoEntregadosProgEd")&&criterio.equalsIgnoreCase("programa_educativo")) {
//                entregadosYNoEntregadosProgEd();
//            }

            //entregadosYNoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosynoentregados")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact==4||nRact==0)) {
                entregadosYNoProgEdTodosRacts();
            }
            
            //entregadosYNoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosynoentregados")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact>0&&nRact<4)) {
                entregadosYNoEntregadosProgEd();
            }

//            //fueraTiempoProgEd
//            if (reporte.equalsIgnoreCase("fueraTiempoProgEd")&&criterio.equalsIgnoreCase("programa_educativo")) {
//                fueraTiempoProgEd();
//            }

            //fueraTiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosdespueslimite")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact==4||nRact==0)) {
                fueraTiempoProgEdTodosRacts();
            }
            
            //fueraTiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosdespueslimite")&&criterio.equalsIgnoreCase("programa_educativo")&&(nRact>0&&nRact<4)) {
                fueraTiempoProgEd();
            }

//            //noEntregadosProgEd
//            if (reporte.equalsIgnoreCase("noEntregadosProgEd")&&criterio.equalsIgnoreCase("programa_educativo")) {
//                noEntregadosProgEd();
//            }
            
            
            //area_conocimiento
            
            //Opciones tipoReporteUI:        
                
//            //ATiempoProgEd
//            if (reporte.equalsIgnoreCase("ATiempoProgEd")&&criterio.equalsIgnoreCase("area_conocimiento")) {
//                ATiempoProgEd();
//            }

            //ATiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosatiempo")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact==4||nRact==0)) {
                ATiempoAreaConTodosRacts(); 
            }
            
            if (reporte.equalsIgnoreCase("entregadosatiempo")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact>0&&nRact<4)) {
                ATiempoAreaCon();
            }

//            //ATiempoYNoYEnLimiteProgEd
//            if (reporte.equalsIgnoreCase("ATiempoYNoYEnLimiteProgEd")&&criterio.equalsIgnoreCase("area_conocimiento")) {
//                ATiempoYNoYEnLimiteProgEd();
//            }

            //ATiempoYNoYEnLimiteProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosatiempoenfechalimiteydespues")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact==4||nRact==0)) {
                ATiempoYNoYEnLimiteAreaConTodosRacts();
            }
            
            //ATiempoYNoYEnLimiteProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosatiempoenfechalimiteydespues")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact>0&&nRact<4)) {
                ATiempoYNoYEnLimiteAreaCon();
            }

//            //PAGCompletoEIncompletoProgEd
//            if (reporte.equalsIgnoreCase("PAGCompletoEIncompletoProgEd")&&criterio.equalsIgnoreCase("area_conocimiento")) {
//               PAGCCompletoEIncompletoProgEd();
//            }

//            //PAGCompletoProgEd
//            if (reporte.equalsIgnoreCase("PAGCompletoProgEd")&&criterio.equalsIgnoreCase("area_conocimiento")) {
//                PAGCCompletoProgEd();
//            }

            //PAGCompletosYNoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Completo e Incompleto")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact==4||nRact==0)) {
                PAGCCompletosYNoAreaConTodosRacts();
                isPAGCTodosRacts=true;
            }
            
            //PAGCompletosYNoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Completo e Incompleto")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact>0&&nRact<4)) {
                //PAGCCompletoEIncompletoAreaCon();
                PAGCCompletosYNoAreaConTodosRacts();
                isPAGCTodosRacts=true;
            }

//            //PAGCProgEd
//            if (reporte.equalsIgnoreCase("PAGCProgEd")&&criterio.equalsIgnoreCase("area_conocimiento")) {
//               PAGCProgEd();
//            }

            //PAGCProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Incompleto")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact==4||nRact==0)) {
                PAGCAreaConTodosRacts(); 
                isPAGCTodosRacts=true;
            }
            
            //PAGCProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Incompleto")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact>0&&nRact<4)) {
                //PAGCAreaCon(); 
                PAGCAreaConTodosRacts(); 
                isPAGCTodosRacts=true;
            }

            //PAGCCompletoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Completo")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact==4||nRact==0)) {
                PAGCCompletoAreaConTodosRacts(); 
                isPAGCTodosRacts=true;
            }
            
            //PAGCCompletoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Completo")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact>0&&nRact<4)) {
                //PAGCCompletoAreaCon();
                PAGCCompletoAreaConTodosRacts(); 
                isPAGCTodosRacts=true;
            }

//            //enFechaLimiteTiempoProgEd
//            if (reporte.equalsIgnoreCase("enFechaLimiteTiempoProgEd")&&criterio.equalsIgnoreCase("area_conocimiento")) {
//                enFechaLimiteTiempoProgEd();
//            }

            //enFechaLimiteTiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosenfechalimite")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact==4||nRact==0)) {
                enFechaLimiteTiempoAreaConTodosRacts();
            }
            
            //enFechaLimiteTiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosenfechalimite")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact>0&&nRact<4)) {
                enFechaLimiteTiempoAreaCon();
            }

//            //entregadosProgEd
//            if (reporte.equalsIgnoreCase("entregadosProgEd")&&criterio.equalsIgnoreCase("area_conocimiento")) {
//                entregadosProgEd();
//            }

            //entregadosProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregados")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact==4||nRact==0)) {                
                entregadosAreaConTodosRacts();                
            }
            
            //entregadosProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregados")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact>0&&nRact<4)) {                
                entregadosAreaCon();                
            }

            //noEntregadosProgEdTodosRacts
            if (reporte.equalsIgnoreCase("noentregados")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact==4||nRact==0)) {
                noEntregadosAreaConTodosRacts();
            }
            
            //noEntregadosProgEdTodosRacts
            if (reporte.equalsIgnoreCase("noentregados")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact>0&&nRact<4)) {
                noEntregadosAreaCon();
            }

//            //entregadosYNoEntregadosProgEd
//            if (reporte.equalsIgnoreCase("entregadosYNoEntregadosProgEd")&&criterio.equalsIgnoreCase("area_conocimiento")) {
//                entregadosYNoEntregadosProgEd();
//            }

            //entregadosYNoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosynoentregados")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact==4||nRact==0)) {
                entregadosYNoAreaConTodosRacts();
            }
            
            //entregadosYNoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosynoentregados")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact>0&&nRact<4)) {
                entregadosYNoEntregadosAreaCon();
            }

//            //fueraTiempoProgEd
//            if (reporte.equalsIgnoreCase("fueraTiempoProgEd")&&criterio.equalsIgnoreCase("area_conocimiento")) {
//                fueraTiempoProgEd();
//            }

            //fueraTiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosdespueslimite")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact==4||nRact==0)) {
                fueraTiempoAreaConTodosRacts();
            }
            
            //fueraTiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosdespueslimite")&&criterio.equalsIgnoreCase("area_conocimiento")&&(nRact>0&&nRact<4)) {
                fueraTiempoAreaCon();
            }

//            //noEntregadosProgEd
//            if (reporte.equalsIgnoreCase("noEntregadosProgEd")&&criterio.equalsIgnoreCase("area_conocimiento")) {
//                noEntregadosProgEd();
//            }
            
            
            
            //unidad_aprendizaje
            
            //Opciones tipoReporteUI:        
                
//            //ATiempoProgEd
//            if (reporte.equalsIgnoreCase("ATiempoProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//                ATiempoProgEd();
//            }

            //ATiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosatiempo")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact==4||nRact==0)) {
                ATiempoUAGrupoTodosRacts();
            }
            
            //ATiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosatiempo")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact>0&&nRact<4)) {
                ATiempoUAGrupo();
            }

//            //ATiempoYNoYEnLimiteProgEd
//            if (reporte.equalsIgnoreCase("ATiempoYNoYEnLimiteProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//                ATiempoYNoYEnLimiteProgEd();
//            }

            //ATiempoYNoYEnLimiteProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosatiempoenfechalimiteydespues")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact==4||nRact==0)) {
                ATiempoYNoYEnLimiteUAGrupoTodosRacts();
            }
            
            //ATiempoYNoYEnLimiteProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosatiempoenfechalimiteydespues")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact>0&&nRact<4)) {
                ATiempoYNoYEnLimiteUAGrupo();
            }

//            //PAGCompletoEIncompletoProgEd
//            if (reporte.equalsIgnoreCase("PAGCompletoEIncompletoProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//               PAGCCompletoEIncompletoProgEd();
//            }

//            //PAGCompletoProgEd
//            if (reporte.equalsIgnoreCase("PAGCompletoProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//                PAGCCompletoProgEd();
//            }

            //PAGCompletosYNoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Completo e Incompleto")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact==4||nRact==0)) {
                PAGCCompletosYNoUAGrupoTodosRacts();
                isPAGCTodosRacts=true;
            }
            
            //PAGCompletosYNoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Completo e Incompleto")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact>0&&nRact<4)) {
                //PAGCCompletoEIncompletoUAGrupo();
                PAGCCompletosYNoUAGrupoTodosRacts();
                isPAGCTodosRacts=true;
            }

//            //PAGCProgEd
//            if (reporte.equalsIgnoreCase("PAGCProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//               PAGCProgEd();
//            }

            //PAGCProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Incompleto")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact==4||nRact==0)) {
                PAGCUAGrupoTodosRacts();
                isPAGCTodosRacts=true;
            }
            
            //PAGCProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Incompleto")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact>0&&nRact<4)) {
                //PAGCUAGrupo();
                PAGCUAGrupoTodosRacts();
                isPAGCTodosRacts=true;
            }

            //PAGCCompletoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Completo")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact==4||nRact==0)) {
                PAGCCompletoUAGrupoTodosRacts();
                isPAGCTodosRacts=true;
            }
            
            //PAGCCompletoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Completo")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact>0&&nRact<4)) {
                //PAGCCompletoUAGrupo();
                PAGCCompletoUAGrupoTodosRacts();
                isPAGCTodosRacts=true;
            }

//            //enFechaLimiteTiempoProgEd
//            if (reporte.equalsIgnoreCase("enFechaLimiteTiempoProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//                enFechaLimiteTiempoProgEd();
//            }

            //enFechaLimiteTiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosenfechalimite")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact==4||nRact==0)) {
                enFechaLimiteTiempoUAGrupoTodosRacts();
            }
            
            //enFechaLimiteTiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosenfechalimite")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact>0&&nRact<4)) {
                enFechaLimiteTiempoUAGrupo();
            }

//            //entregadosProgEd
//            if (reporte.equalsIgnoreCase("entregadosProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//                entregadosProgEd();
//            }

            //entregadosProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregados")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact==4||nRact==0)) {                
                entregadosUAGrupoTodosRacts();                
            }
            
            //entregadosProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregados")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact>0&&nRact<4)) {                
                entregadosUAGrupo();                
            }

            //noEntregadosProgEdTodosRacts
            if (reporte.equalsIgnoreCase("noentregados")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact==4||nRact==0)) {
                noEntregadosUAGrupoTodosRacts();
            }
            
            //noEntregadosProgEdTodosRacts
            if (reporte.equalsIgnoreCase("noentregados")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact>0&&nRact<4)) {
                noEntregadosUAGrupo();
            }

//            //entregadosYNoEntregadosProgEd
//            if (reporte.equalsIgnoreCase("entregadosYNoEntregadosProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//                entregadosYNoEntregadosProgEd();
//            }

            //entregadosYNoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosynoentregados")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact==4||nRact==0)) {
                entregadosYNoUAGrupoTodosRacts();
            }
            
            //entregadosYNoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosynoentregados")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact>0&&nRact<4)) {
                entregadosYNoEntregadosUAGrupo();
            }

//            //fueraTiempoProgEd
//            if (reporte.equalsIgnoreCase("fueraTiempoProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//                fueraTiempoProgEd();
//            }

            //fueraTiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosdespueslimite")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact==4||nRact==0)) {
                fueraTiempoUAGrupoTodosRacts();
            }
            
            //fueraTiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosdespueslimite")&&criterio.equalsIgnoreCase("unidad_aprendizaje")&&(nRact>0&&nRact<4)) {
                fueraTiempoUAGrupo();
            }

//            //noEntregadosProgEd
//            if (reporte.equalsIgnoreCase("noEntregadosProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//                noEntregadosProgEd();
//            }
            
            
            
            //profesor
            
            //Opciones tipoReporteUI:        
                
//            //ATiempoProgEd
//            if (reporte.equalsIgnoreCase("ATiempoProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//                ATiempoProgEd();
//            }

            //ATiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosatiempo")&&criterio.equalsIgnoreCase("profesor")&&(nRact==4||nRact==0)) {
                ATiempoProfGrupoTodosRacts();
            }
            
            //ATiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosatiempo")&&criterio.equalsIgnoreCase("profesor")&&(nRact>0&&nRact<4)) {
                ATiempoProfGrupo();
            }

//            //ATiempoYNoYEnLimiteProgEd
//            if (reporte.equalsIgnoreCase("ATiempoYNoYEnLimiteProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//                ATiempoYNoYEnLimiteProgEd();
//            }

            //ATiempoYNoYEnLimiteProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosatiempoenfechalimiteydespues")&&criterio.equalsIgnoreCase("profesor")&&(nRact==4||nRact==0)) {
                ATiempoYNoYEnLimiteProfGrupoTodosRacts();
            }
            
            //ATiempoYNoYEnLimiteProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosatiempoenfechalimiteydespues")&&criterio.equalsIgnoreCase("profesor")&&(nRact>0&&nRact<4)) {
                ATiempoYNoYEnLimiteProfGrupo();
            }

//            //PAGCompletoEIncompletoProgEd
//            if (reporte.equalsIgnoreCase("PAGCompletoEIncompletoProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//               PAGCCompletoEIncompletoProgEd();
//            }

//            //PAGCompletoProgEd
//            if (reporte.equalsIgnoreCase("PAGCompletoProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//                PAGCCompletoProgEd();
//            }

            //PAGCompletosYNoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Completo e Incompleto")&&criterio.equalsIgnoreCase("profesor")&&(nRact==4||nRact==0)) {
                PAGCCompletosYNoProfGrupoTodosRacts();
                isPAGCTodosRacts=true;
            }
            
            //PAGCompletosYNoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Completo e Incompleto")&&criterio.equalsIgnoreCase("profesor")&&(nRact>0&&nRact<4)) {
                //PAGCCompletoEIncompletoProfGrupo();
                PAGCCompletosYNoProfGrupoTodosRacts();
                isPAGCTodosRacts=true;
            }

//            //PAGCProgEd
//            if (reporte.equalsIgnoreCase("PAGCProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//               PAGCProgEd();
//            }

            //PAGCProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Incompleto")&&criterio.equalsIgnoreCase("profesor")&&(nRact==4||nRact==0)) {
                PAGCProfGrupoTodosRacts();
                isPAGCTodosRacts=true;
            }
            
            //PAGCProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Incompleto")&&criterio.equalsIgnoreCase("profesor")&&(nRact>0&&nRact<4)) {
                //PAGCProfGrupo();
                PAGCProfGrupoTodosRacts();
                isPAGCTodosRacts=true;
            }

            //PAGCCompletoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Completo")&&criterio.equalsIgnoreCase("profesor")&&(nRact==4||nRact==0)) {
                PAGCCompletoProfGrupoTodosRacts();
                isPAGCTodosRacts=true;
            }
            
            //PAGCCompletoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("Porcentaje de Avance Global Completo")&&criterio.equalsIgnoreCase("profesor")&&(nRact>0&&nRact<4)) {
                //PAGCCompletoProfGrupo();
                PAGCCompletoProfGrupoTodosRacts();
                isPAGCTodosRacts=true;
            }

//            //enFechaLimiteTiempoProgEd
//            if (reporte.equalsIgnoreCase("enFechaLimiteTiempoProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//                enFechaLimiteTiempoProgEd();
//            }

            //enFechaLimiteTiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosenfechalimite")&&criterio.equalsIgnoreCase("profesor")&&(nRact==4||nRact==0)) {
                enFechaLimiteTiempoProfGrupoTodosRacts();
            }
            
            //enFechaLimiteTiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosenfechalimite")&&criterio.equalsIgnoreCase("profesor")&&(nRact>0&&nRact<4)) {
                enFechaLimiteTiempoProfGrupo();
            }

//            //entregadosProgEd
//            if (reporte.equalsIgnoreCase("entregadosProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//                entregadosProgEd();
//            }

            //entregadosProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregados")&&criterio.equalsIgnoreCase("profesor")&&(nRact==4||nRact==0)) {                
                entregadosProfGrupoTodosRacts();                
            }
            
            //entregadosProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregados")&&criterio.equalsIgnoreCase("profesor")&&(nRact>0&&nRact<4)) {                
                entregadosProfGrupo();                
            }

            //noEntregadosProgEdTodosRacts
            if (reporte.equalsIgnoreCase("noentregados")&&criterio.equalsIgnoreCase("profesor")&&(nRact==4||nRact==0)) {
                noEntregadosProfGrupoTodosRacts();
            }
            
            //noEntregadosProgEdTodosRacts
            if (reporte.equalsIgnoreCase("noentregados")&&criterio.equalsIgnoreCase("profesor")&&(nRact>0&&nRact<4)) {
                noEntregadosProfGrupo();
            }

//            //entregadosYNoEntregadosProgEd
//            if (reporte.equalsIgnoreCase("entregadosYNoEntregadosProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//                entregadosYNoEntregadosProgEd();
//            }

            //entregadosYNoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosynoentregados")&&criterio.equalsIgnoreCase("profesor")&&(nRact==4||nRact==0)) {
                entregadosYNoProfGrupoTodosRacts();
            }
            
            //entregadosYNoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosynoentregados")&&criterio.equalsIgnoreCase("profesor")&&(nRact>0&&nRact<4)) {
                entregadosYNoEntregadosProfGrupo();
            }

//            //fueraTiempoProgEd
//            if (reporte.equalsIgnoreCase("fueraTiempoProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//                fueraTiempoProgEd();
//            }

            //fueraTiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosdespueslimite")&&criterio.equalsIgnoreCase("profesor")&&(nRact==4||nRact==0)) {
                fueraTiempoProfGrupoTodosRacts();
            }
            
            //fueraTiempoProgEdTodosRacts
            if (reporte.equalsIgnoreCase("entregadosdespueslimite")&&criterio.equalsIgnoreCase("profesor")&&(nRact>0&&nRact<4)) {
                fueraTiempoProfGrupo();
            }

//            //noEntregadosProgEd
//            if (reporte.equalsIgnoreCase("noEntregadosProgEd")&&criterio.equalsIgnoreCase("unidad_aprendizaje")) {
//                noEntregadosProgEd();
//            }
            
    }

    public ReporteAux fijarAtributosReporteUI(ReporteAux reporteUI,String tipoReporte){
        
        if (tipoReporte.equalsIgnoreCase("ProgEd")) {
            //pasa los atributos que necesita por tipo de consulta
            //o reporte para los atributos del objeto del criteria
            reporteUI.setNumRact(numRact);
            reporteUI.setCescicloEscolar(cescicloEscolar);
            reporteUI.setClavepe(clavepe);
            reporteUI.setPesvigencia(pesvigencia);
        }
        if (tipoReporte.equalsIgnoreCase("AreaCon")) {
            //pasa los atributos que necesita por tipo de consulta
            //o reporte para los atributos del objeto del criteria
            reporteUI.setNumRact(numRact);
            reporteUI.setCescicloEscolar(cescicloEscolar);
            reporteUI.setAcoclave(acoclave);
            reporteUI.setClavepe(clavepe);
            reporteUI.setPesvigencia(pesvigencia);
        }
        if (tipoReporte.equalsIgnoreCase("UAGrupo")) {
            //pasa los atributos que necesita por tipo de consulta
            //o reporte para los atributos del objeto del criteria
            reporteUI.setNumRact(numRact);
            reporteUI.setCescicloEscolar(cescicloEscolar);
            reporteUI.setAcoclave(acoclave);
            reporteUI.setClavepe(clavepe);
            reporteUI.setPesvigencia(pesvigencia);
            reporteUI.setGponumero(gponumero);
            reporteUI.setUapclave(uapclave);
        }
        if (tipoReporte.equalsIgnoreCase("ProfGrupo")) {
            //pasa los atributos que necesita por tipo de consulta
            //o reporte para los atributos del objeto del criteria
            reporteUI.setNumRact(numRact);
            reporteUI.setCescicloEscolar(cescicloEscolar);
            reporteUI.setAcoclave(acoclave);
            reporteUI.setClavepe(clavepe);
            reporteUI.setPesvigencia(pesvigencia);
            reporteUI.setGponumero(gponumero);
            reporteUI.setUapclave(uapclave);
            reporteUI.setPronumeroEmpleado(pronumeroEmpleado);
        }

        return reporteUI;
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo de
    //solamente entregados
    public void entregadosProgEdTodosRacts(){
                
        ReporteAux reporteUI=new ReporteAux();
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos 
        //de la vista   
        
        setNumRact(Integer.parseInt(ract));
        setCescicloEscolar(selectCicloEscolarList.get(0));        
        //setCescicloEscolar("2009-2");
        //setClavepe(Integer.parseInt(selectProgramEducativo.get(0)));
        //setClavepe(Integer.parseInt("14006"));
        //setPesvigencia("2009-2");
        
        tipoReporteCTR="entregadosProgEdTodosRacts";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
        
     //   listaAux=filtrosBeanHelper.entregadosProgEdTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo de
    //solamente no entregados
    public void noEntregadosProgEdTodosRacts(){
                
        ReporteAux reporteUI=new ReporteAux();
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos 
        //de la vista   
        
        setNumRact(Integer.parseInt(ract));
        setCescicloEscolar(selectCicloEscolarList.get(0));        
        //setCescicloEscolar("2009-2");
        //setClavepe(Integer.parseInt(selectProgramEducativo.get(0)));
        //setClavepe(Integer.parseInt("14006"));
        //setPesvigencia("2009-2");
        
        tipoReporteCTR="noEntregadosProgEdTodosRacts";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
               
        //listaAux=filtrosBeanHelper.noEntregadosProgEdTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo de
    //la union de entregados y no entregados
    public void entregadosYNoProgEdTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="entregadosYNoProgEdTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
        
        setNumRact(Integer.parseInt(ract));
        setCescicloEscolar(selectCicloEscolarList.get(0));        
        //setCescicloEscolar("2009-2");
        //setClavepe(Integer.parseInt(selectProgramEducativo.get(0)));
        //setClavepe(Integer.parseInt("14006"));
        //setPesvigencia("2009-2");

        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
        
        //listaAux=filtrosBeanHelper.entregadosYNoProgEdTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area de conocimiento de
    //solamente entregados
    public void entregadosAreaConTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="entregadosAreaConTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
                
        //listaAux=filtrosBeanHelper.entregadosAreaConTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area de conocimiento de
    //solamente no entregados
    public void noEntregadosAreaConTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="noEntregadosAreaConTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
                
        //listaAux=filtrosBeanHelper.noEntregadosAreaConTodosRacts(reporteUI);
        
        //listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area de conocimiento de
    //la union de entregados y no entregados
    public void entregadosYNoAreaConTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="entregadosYNoAreaConTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y

        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
                
        //listaAux=filtrosBeanHelper.entregadosYNoAreaConTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad de aprendizaje 
    //con Grupo de solamente entregados
    public void entregadosUAGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="entregadosUAGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y

        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
                
        //listaAux=filtrosBeanHelper.entregadosUAGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad de aprendizaje 
    //con Grupo de solamente no entregados
    public void noEntregadosUAGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="noEntregadosUAGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y

        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
                
        //listaAux=filtrosBeanHelper.noEntregadosUAGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad de aprendizaje con
    //con Grupo de la union de entregados y no entregados
    public void entregadosYNoUAGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="entregadosYNoUAGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
                
        //listaAux=filtrosBeanHelper.entregadosYNoUAGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor con Grupo 
    //de solamente entregados
    public void entregadosProfGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="entregadosProfGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y

        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
        
        //listaAux=filtrosBeanHelper.entregadosProfGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor con Grupo 
    //de solamente no entregados
    public void noEntregadosProfGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="noEntregadosProfGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y

        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
                
        //listaAux=filtrosBeanHelper.noEntregadosProfGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor con Grupo
    //de la union de entregados y no entregados
    public void entregadosYNoProfGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="entregadosYNoProfGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y

        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
                
        //listaAux=filtrosBeanHelper.entregadosYNoProfGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo
    //de solamente Porcentaje de Avance General *Incompleto*
    public void PAGCProgEdTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCProgEdTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
        
        setNumRact(Integer.parseInt(ract));
        setCescicloEscolar(selectCicloEscolarList.get(0));        
        //setCescicloEscolar("2009-2");
        //setClavepe(Integer.parseInt(selectProgramEducativo.get(0)));
        //setClavepe(Integer.parseInt("14006"));
        //setPesvigencia("2009-2");
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
                
        //listaAux=filtrosBeanHelper.PAGCProgEdTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo
    //de solamente Porcentaje de Avance General *Completo*
    public void PAGCCompletoProgEdTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCCompletoProgEdTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
        
        setNumRact(Integer.parseInt(ract));
        setCescicloEscolar(selectCicloEscolarList.get(0));        
        //setCescicloEscolar("2009-2");
        //setClavepe(Integer.parseInt(selectProgramEducativo.get(0)));
        //setClavepe(Integer.parseInt("14006"));
        //setPesvigencia("2009-2");
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
               
        //listaAux=filtrosBeanHelper.PAGCCompletoProgEdTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo
    //de ambos Porcentaje de Avance General Completo e *Incompleto*
    public void PAGCCompletosYNoProgEdTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCompletosYNoProgEdTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
        
        setNumRact(Integer.parseInt(ract));
        setCescicloEscolar(selectCicloEscolarList.get(0));        
        //setCescicloEscolar("2009-2");
        //setClavepe(Integer.parseInt(selectProgramEducativo.get(0)));
        //setClavepe(Integer.parseInt("14006"));
        //setPesvigencia("2009-2");
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
              
        //listaAux=filtrosBeanHelper.PAGCCompletosYNoProgEdTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area de conocimiento
    //de solamente Porcentaje de Avance General *Incompleto*
    public void PAGCAreaConTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCAreaConTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y

        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
                
        //listaAux=filtrosBeanHelper.PAGCAreaConTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area de conocimiento
    //de solamente Porcentaje de Avance General *Completo*
    public void PAGCCompletoAreaConTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCCompletoAreaConTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y

        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
                
        //listaAux=filtrosBeanHelper.PAGCCompletoAreaConTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area de conocimiento
    //de ambos Porcentaje de Avance General Completo e *Incompleto*
    public void PAGCCompletosYNoAreaConTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCompletosYNoAreaConTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
            
        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
               
        //listaAux=filtrosBeanHelper.PAGCCompletosYNoAreaConTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad aprendizaje
    //con Grupo de solamente Porcentaje de Avance General *Incompleto*
    public void PAGCUAGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCUAGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
                
        //listaAux=filtrosBeanHelper.PAGCUAGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad aprendizaje
    //con Grupo de solamente Porcentaje de Avance General *Completo*
    public void PAGCCompletoUAGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCCompletoUAGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
         
        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
                
        //listaAux=filtrosBeanHelper.PAGCCompletoUAGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad aprendizaje
    //con Grupo de ambos Porcentaje de Avance General Completo e *Incompleto*
    public void PAGCCompletosYNoUAGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCompletosYNoUAGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
         
        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
                
        //listaAux=filtrosBeanHelper.PAGCCompletosYNoUAGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor
    //con Grupo de solamente Porcentaje de Avance General *Incompleto*
    public void PAGCProfGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCProfGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
         
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
                
        //listaAux=filtrosBeanHelper.PAGCProfGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor
    //con Grupo de solamente Porcentaje de Avance General *Completo*
    public void PAGCCompletoProfGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCCompletoProfGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
                
        //listaAux=filtrosBeanHelper.PAGCCompletoProfGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor
    //con Grupo de ambos Porcentaje de Avance General Completo e *Incompleto*
    public void PAGCCompletosYNoProfGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCompletosYNoProfGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y

        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
                
        //listaAux=filtrosBeanHelper.PAGCCompletosYNoProfGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo
    //para solamente A Tiempo(comparando la fecha limite)
    public void ATiempoProgEdTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="ATiempoProgEdTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
            
        setNumRact(Integer.parseInt(ract));
        setCescicloEscolar(selectCicloEscolarList.get(0));         
        //setCescicloEscolar("2009-2");
        //setClavepe(Integer.parseInt(selectProgramEducativo.get(0)));
        //setClavepe(Integer.parseInt("14006"));
        //setPesvigencia("2009-2");
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
                
        //listaAux=filtrosBeanHelper.ATiempoProgEdTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo
    //para solamente Despues de fecha Limite Tiempo(comparando la fecha limite)
    public void fueraTiempoProgEdTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="fueraTiempoProgEdTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
                
        setNumRact(Integer.parseInt(ract));
        setCescicloEscolar(selectCicloEscolarList.get(0));        
        //setCescicloEscolar("2009-2");
        //setClavepe(Integer.parseInt(selectProgramEducativo.get(0)));
        //setClavepe(Integer.parseInt("14006"));
        //setPesvigencia("2009-2");
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
               
        //listaAux=filtrosBeanHelper.FueraTiempoProgEdTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo
    //para solamente En fecha Limite Tiempo(comparando la fecha limite)
    public void enFechaLimiteTiempoProgEdTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="enFechaLimiteTiempoProgEdTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
        
        setNumRact(Integer.parseInt(ract));
        setCescicloEscolar(selectCicloEscolarList.get(0));        
        //setCescicloEscolar("2009-2");
        //setClavepe(Integer.parseInt(selectProgramEducativo.get(0)));
        //setClavepe(Integer.parseInt("14006"));
        //setPesvigencia("2009-2");
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
               
        //listaAux=filtrosBeanHelper.EnFechaLimiteTiempoProgEdTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Programa educativo
    //para todos A Tiempo, En Fecha Limite y Despues de Fecha Limite(comparando la fecha limite)
    public void ATiempoYNoYEnLimiteProgEdTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="ATiempoYNoYEnLimiteProgEdTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
        
        setNumRact(Integer.parseInt(ract));
        setCescicloEscolar(selectCicloEscolarList.get(0));        
        //setCescicloEscolar("2009-2");
        //setClavepe(Integer.parseInt(selectProgramEducativo.get(0)));
        //setClavepe(Integer.parseInt("14006"));
        //setPesvigencia("2009-2");
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
                
        //listaAux=filtrosBeanHelper.ATiempoYNoYEnLimiteProgEdTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area de conocimiento
    //para solamente A Tiempo(comparando la fecha limite)
    public void ATiempoAreaConTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="ATiempoAreaConTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
         
        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
                
        //listaAux=filtrosBeanHelper.ATiempoAreaConTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area de conocimiento
    //para solamente Despues de fecha Limite Tiempo(comparando la fecha limite)
    public void fueraTiempoAreaConTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="fueraTiempoAreaConTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
         
        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
               
        //listaAux=filtrosBeanHelper.FueraTiempoAreaConTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area de conocimiento
    //para solamente En fecha limite Tiempo(comparando la fecha limite)
    public void enFechaLimiteTiempoAreaConTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="enFechaLimiteTiempoAreaConTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
         
        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
               
        //listaAux=filtrosBeanHelper.EnFechaLimiteTiempoAreaConTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Area conocimiento
    //para todos A Tiempo, En Fecha Limite y Despues de Fecha Limite(comparando la fecha limite)
    public void ATiempoYNoYEnLimiteAreaConTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="ATiempoYNoYEnLimiteAreaConTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
         
        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
               
        //listaAux=filtrosBeanHelper.ATiempoYNoYEnLimiteAreaConTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad aprendizaje 
    //con Grupo para solamente A Tiempo(comparando la fecha limite)
    public void ATiempoUAGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="ATiempoUAGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
         
        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
                
        //listaAux=filtrosBeanHelper.ATiempoUAGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad aprendizaje 
    //con Grupo para solamente Despues de fecha Limite Tiempo(comparando la fecha limite)
    public void fueraTiempoUAGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="fueraTiempoUAGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
                
        //listaAux=filtrosBeanHelper.FueraTiempoUAGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad aprendizaje 
    //con Grupo para solamente En fecha Limite Tiempo(comparando la fecha limite)
    public void enFechaLimiteTiempoUAGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="enFechaLimiteTiempoUAGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
         
        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
                
        //listaAux=filtrosBeanHelper.EnFechaLimiteTiempoUAGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Unidad aprendizaje 
    //con Grupo para todos A Tiempo, En Fecha Limite y Despues de Fecha Limite(comparando la fecha limite)
    public void ATiempoYNoYEnLimiteUAGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="ATiempoYNoYEnLimiteUAGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
                
        //listaAux=filtrosBeanHelper.ATiempoYNoYEnLimiteUAGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor 
    //con Grupo para solamente A Tiempo(comparando la fecha limite)
    public void ATiempoProfGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="ATiempoProfGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
               
        //listaAux=filtrosBeanHelper.ATiempoProfGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor 
    //con Grupo para solamente Despues de fecha Limite Tiempo(comparando la fecha limite)
    public void fueraTiempoProfGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="fueraTiempoProfGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
                
        //listaAux=filtrosBeanHelper.FueraTiempoProfGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }

    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor 
    //con Grupo para solamente En fecha Limite Tiempo(comparando la fecha limite)
    public void enFechaLimiteTiempoProfGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="enFechaLimiteTiempoProfGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
         
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
              
        //listaAux=filtrosBeanHelper.EnFechaLimiteTiempoProfGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }
    
    //metodo para obtener en una linea los 3 ract de la union de un mismo profesor con misma unidad de
    //aprendizaje y mismo grupo(un solo registro en una sola linea) para Profesor
    //con Grupo para todos A Tiempo, En Fecha Limite y Despues de Fecha Limite(comparando la fecha limite)
    public void ATiempoYNoYEnLimiteProfGrupoTodosRacts(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="ATiempoYNoYEnLimiteProfGrupoTodosRacts";
        
        //init(); esto creo que no se ocupa ya(comentado) por que en realidad toma los atributos y
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
        
        //listaAux=filtrosBeanHelper.ATiempoYNoYEnLimiteProfGrupoTodosRacts(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para los entregados por Programa educativo segun los atributos de consulta
    //de la especificación(Todo esto por un solo numero de Ract)
    public void entregadosProgEd(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="entregadosProgEd";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
        
        //listaAux=filtrosBeanHelper.entregadosProgEd(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para los entregados por Area de conocimiento segun los atributos de consulta
    //de la especificación(Todo esto por un solo numero de Ract)
    public void entregadosAreaCon(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="entregadosAreaCon";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
        
        //listaAux=filtrosBeanHelper.entregadosAreaCon(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para los entregados por Unidad aprendizaje con Grupo segun los atributos de consulta
    //de la especificación(Todo esto por un solo numero de Ract)
    public void entregadosUAGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="entregadosUAGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
        
        //listaAux=filtrosBeanHelper.entregadosUAGrupo(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para los entregados por Profesor con Grupo segun los atributos de consulta
    //de la especificación(Todo esto por un solo numero de Ract)
    public void entregadosProfGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="entregadosProfGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
        
        //listaAux=filtrosBeanHelper.entregadosProfGrupo(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }

    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para los no entregados por Programa educativo segun los atributos de consulta
    //de la especificación(Todo esto por un solo numero de Ract)
    public void noEntregadosProgEd(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="noEntregadosProgEd";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
        
        //listaAux=filtrosBeanHelper.noEntregadosProgEd(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para los no entregados por Area Conocimiento segun los atributos de consulta
    //de la especificación(Todo esto por un solo numero de Ract)
    public void noEntregadosAreaCon(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="noEntregadosAreaCon";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
        
        //listaAux=filtrosBeanHelper.noEntregadosAreaCon(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para los no entregados por Unidad Aprendizaje con Grupo segun los atributos de consulta
    //de la especificación(Todo esto por un solo numero de Ract)
    public void noEntregadosUAGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="noEntregadosUAGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
        
        //listaAux=filtrosBeanHelper.noEntregadosUAGrupo(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para los no entregados por Profesor con Grupo segun los atributos de consulta
    //de la especificación(Todo esto por un solo numero de Ract)
    public void noEntregadosProfGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="noEntregadosProfGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
        
        //listaAux=filtrosBeanHelper.noEntregadosProfGrupo(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }
   
    //metodo que une los entregados y no entregados por un solo numero de
    //ract *no por una sola linea los tres racts de un mismo id de 
    //unidadaprendizajeimparteprofesor por Programa Educativo
    public void entregadosYNoEntregadosProgEd(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="entregadosYNoEntregadosProgEd";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
                
        //listaAux=filtrosBeanHelper.entregadosYNoEntregadosProgEd(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);
    }
    
    //metodo que une los entregados y no entregados por un solo numero de
    //ract *no por una sola linea los tres racts de un mismo id de 
    //unidadaprendizajeimparteprofesor por Area Conocimiento
    public void entregadosYNoEntregadosAreaCon(){        
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="entregadosYNoEntregadosAreaCon";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
        
        //listaAux=filtrosBeanHelper.entregadosYNoEntregadosAreaCon(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //metodo que une los entregados y no entregados por un solo numero de
    //ract *no por una sola linea los tres racts de un mismo id de 
    //unidadaprendizajeimparteprofesor por Unidad Aprendizaje con Grupo
    public void entregadosYNoEntregadosUAGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="entregadosYNoEntregadosUAGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
        
        //listaAux=filtrosBeanHelper.entregadosYNoEntregadosUAGrupo(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }

    //metodo que une los entregados y no entregados por un solo numero de
    //ract *no por una sola linea los tres racts de un mismo id de 
    //unidadaprendizajeimparteprofesor por Profesor con Grupo
    public void entregadosYNoEntregadosProfGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="entregadosYNoEntregadosProfGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
        
        //listaAux=filtrosBeanHelper.entregadosYNoEntregadosProfGrupo(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para Porcentaje Avance General *Incompleto* por Programa educativo
    //segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void PAGCProgEd(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();  
        
        tipoReporteCTR="PAGCProgEd";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
        
        //listaAux=filtrosBeanHelper.PAGCProgEd(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para Porcentaje Avance General *Incompleto* por Area de conocimiento
    //segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void PAGCAreaCon(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCAreaCon";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
        
        //listaAux=filtrosBeanHelper.PAGCAreaCon(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para Porcentaje Avance General *Incompleto* por Unidad Aprendizaje con Grupo
    //segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void PAGCUAGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCUAGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
        
        //listaAux=filtrosBeanHelper.PAGCUAGrupo(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para Porcentaje Avance General *Incompleto* por Profesor con Grupo
    //segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void PAGCProfGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCProfGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
        
        //listaAux=filtrosBeanHelper.PAGCProfGrupo(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para Porcentaje Avance General Completo por Programa educativo
    //segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void PAGCCompletoProgEd(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCompletoProgEd";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
        
        //listaAux=filtrosBeanHelper.PAGCCompletoProgEd(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para Porcentaje Avance General Completo por Area Conocimiento
    //segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void PAGCCompletoAreaCon(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCCompletoAreaCon";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
        
        //listaAux=filtrosBeanHelper.PAGCCompletoAreaCon(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para Porcentaje Avance General Completo por Unidad Aprendizaje con Grupo
    //segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void PAGCCompletoUAGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCCompletoUAGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
        
        //listaAux=filtrosBeanHelper.PAGCCompletoUAGrupo(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para Porcentaje Avance General Completo por Profesor con Grupo
    //segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void PAGCCompletoProfGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCompletoProfGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
        
        //listaAux=filtrosBeanHelper.PAGCCompletoProfGrupo(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }
    
    //este metodo une los Porcentaje Avance Global Completo con Incompleto
    //por un solo numero de ract, *no en una sola linea los tres ract por 
    //id de unidadaprendizajeimparteprofesor por Programa Educativo
    public void PAGCCompletoEIncompletoProgEd(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCompletoEIncompletoProgEd";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
        
        //listaAux=filtrosBeanHelper.PAGCCompletoEIncompletoProgEd(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);     
    }
    
    //este metodo une los Porcentaje Avance Global Completo con Incompleto
    //por un solo numero de ract, *no en una sola linea los tres ract por 
    //id de unidadaprendizajeimparteprofesor por Area Conocimiento
    public void PAGCCompletoEIncompletoAreaCon(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCompletoEIncompletoAreaCon";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
        
        //listaAux=filtrosBeanHelper.PAGCCompletoEIncompletoAreaCon(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //este metodo une los Porcentaje Avance Global Completo con Incompleto
    //por un solo numero de ract, *no en una sola linea los tres ract por 
    //id de unidadaprendizajeimparteprofesor por Unidad Aprendizaje con Grupo
    public void PAGCCompletoEIncompletoUAGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCompletoEIncompletoUAGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
        
        //listaAux=filtrosBeanHelper.PAGCCompletoEIncompletoUAGrupo(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }
    
    //este metodo une los Porcentaje Avance Global Completo con Incompleto
    //por un solo numero de ract, *no en una sola linea los tres ract por 
    //id de unidadaprendizajeimparteprofesor por Profesor con Grupo
    public void PAGCCompletoEIncompletoProfGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="PAGCompletoEIncompletoProfGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
       
        //listaAux=filtrosBeanHelper.PAGCCompletoEIncompletoProfGrupo(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para A Tiempo(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Programa educativo segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void ATiempoProgEd(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="ATiempoProgEd";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
        
        //listaAux=filtrosBeanHelper.ATiempoProgEd(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para A Tiempo(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Area Conocimiento segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void ATiempoAreaCon(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR = "ATiempoAreaCon";        
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
        
        //listaAux=filtrosBeanHelper.ATiempoAreaCon(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para A Tiempo(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Unidad Aprendizaje con Grupo segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void ATiempoUAGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="ATiempoUAGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
        
        //listaAux=filtrosBeanHelper.ATiempoUAGrupo(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para A Tiempo(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Profesor con Grupo segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void ATiempoProfGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="ATiempoProfGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
        
        //listaAux=filtrosBeanHelper.ATiempoProfGrupo(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para "Despues de fecha límite"(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Programa educativo segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void fueraTiempoProgEd(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="fueraTiempoProgEd";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
        
        //listaAux=filtrosBeanHelper.FueraTiempoProgEd(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para "Despues de fecha límite"(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Area Conocimiento segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void fueraTiempoAreaCon(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="fueraTiempoAreaCon";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
        
        //listaAux=filtrosBeanHelper.FueraTiempoAreaCon(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para "Despues de fecha límite"(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Unidad Aprendizaje con Grupo segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void fueraTiempoUAGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="fueraTiempoUAGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
        
        //listaAux=filtrosBeanHelper.FueraTiempoUAGrupo(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para "Despues de fecha límite"(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Profesor con Grupo segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void fueraTiempoProfGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="fueraTiempoProfGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
        
        //listaAux=filtrosBeanHelper.FueraTiempoProfGrupo(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para "En fecha límite"(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Programa educativo segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void enFechaLimiteTiempoProgEd(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="enFechaLimiteTiempoProgEd";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
        
        //listaAux=filtrosBeanHelper.EnFechaLimiteTiempoProgEd(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para "En fecha límite"(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Area Conocimiento segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void enFechaLimiteTiempoAreaCon(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="enFechaLimiteTiempoAreaCon";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
        
        //listaAux=filtrosBeanHelper.EnFechaLimiteTiempoAreaCon(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para "En fecha límite"(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Unidad Aprendizaje con Grupo segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void enFechaLimiteTiempoUAGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="enFechaLimiteTiempoUAGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
        
        //listaAux=filtrosBeanHelper.EnFechaLimiteTiempoUAGrupo(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }
    
    //metodo para hacer la consulta o join(criteria del dao por todas las capas) 
    //para "En fecha límite"(comparando la fecha límite por numero de ract y ciclo escolar) 
    //por Profesor con Grupo segun los atributos de consulta de la especificación
    //(Todo esto por un solo numero de Ract)
    public void enFechaLimiteTiempoProfGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="enFechaLimiteTiempoProfGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
        
        //listaAux=filtrosBeanHelper.EnFechaLimiteTiempoProfGrupo(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }
    
    //metodo auxiliar para obtener por cada ract(*no en una sola linea) 
    //de todos los registros en ract(no por unico profesor, unidad
    //de aprendizaje y grupo)
    //para Programa educativo de todos A Tiempo, En Fecha Limite, 
    // Despues de Fecha Limite(comparando la fecha Limite)
    public void ATiempoYNoYEnLimiteProgEd(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="ATiempoYNoYEnLimiteProgEd";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProgEd");
        
        //listaAux=filtrosBeanHelper.ATiempoYNoYEnLimiteProgEd(reporteUI);        
        
        listaAux=filtrosBeanHelper.selectionProgEd(reporteUI,tipoReporteCTR,selectProgramEducativo,selectCicloEscolarList);
    }
    
    //metodo auxiliar para obtener por cada ract(*no en una sola linea) 
    //de todos los registros en ract(no por unico profesor, unidad
    //de aprendizaje y grupo)
    //para Area Conocimiento de todos A Tiempo, En Fecha Limite, 
    // Despues de Fecha Limite(comparando la fecha Limite)
    public void ATiempoYNoYEnLimiteAreaCon(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="ATiempoYNoYEnLimiteAreaCon";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"AreaCon");
        
        //listaAux=filtrosBeanHelper.ATiempoYNoYEnLimiteAreaCon(reporteUI);        
        
        listaAux=filtrosBeanHelper.selectionAreaCon(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento);
    }
    
    //metodo auxiliar para obtener por cada ract(*no en una sola linea) 
    //de todos los registros en ract(no por unico profesor, unidad
    //de aprendizaje y grupo)
    //para Unidad Aprendizaje con Grupo de todos A Tiempo, En Fecha Limite, 
    // Despues de Fecha Limite(comparando la fecha Limite)
    public void ATiempoYNoYEnLimiteUAGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="ATiempoYNoYEnLimiteUAGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"UAGrupo");
        
        //listaAux=filtrosBeanHelper.ATiempoYNoYEnLimiteUAGrupo(reporteUI);                
        
        listaAux=filtrosBeanHelper.selectionUAGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo);
    }
    
    //metodo auxiliar para obtener por cada ract(*no en una sola linea) 
    //de todos los registros en ract(no por unico profesor, unidad
    //de aprendizaje y grupo)
    //para Profesor con Grupo de todos A Tiempo, En Fecha Limite, 
    // Despues de Fecha Limite(comparando la fecha Limite)
    public void ATiempoYNoYEnLimiteProfGrupo(){
        //objeto para inicializar a ceros y nulos los
        //atributos que no se necesitan en la consulta
        //de criteria
        ReporteAux reporteUI=new ReporteAux();
        
        tipoReporteCTR="ATiempoYNoYEnLimiteProfGrupo";
        
        reporteUI = fijarAtributosReporteUI(reporteUI,"ProfGrupo");
                       
        //listaAux=filtrosBeanHelper.ATiempoYNoYEnLimiteProfGrupo(reporteUI);
        
        listaAux=filtrosBeanHelper.selectionProfGrupo(reporteUI,tipoReporteCTR,selectCicloEscolarList,selectPlanesEstudio,selectAreaConocimiento,selectUnidadAprendisaje,selectGrupo,selectProfesor);
    }
    
    public ArrayList<ReporteAvanceAux> getListaAux() {
        return listaAux;
    }

    public void setListaAux(ArrayList<ReporteAvanceAux> listaAux) {
        this.listaAux = listaAux;
    }
    
    
  /*
    public FiltrosBeanHelper getFiltrosBeanHelper() {
        return filtrosBeanHelper;
    }

    public void setFiltrosBeanHelper(FiltrosBeanHelper filtrosBeanHelper) {
        this.filtrosBeanHelper = filtrosBeanHelper;
    }
 */
    
    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCalnumeroReporte() {
        return calnumeroReporte;
    }

    public void setCalnumeroReporte(int calnumeroReporte) {
        this.calnumeroReporte = calnumeroReporte;
    }

    public int getNumRact() {
        return numRact;
    }

    public void setNumRact(int numRact) {
        this.numRact = numRact;
    }

    public String getCescicloEscolar() {
        return cescicloEscolar;
    }

    public void setCescicloEscolar(String cescicloEscolar) {
        this.cescicloEscolar = cescicloEscolar;
    }

    public int getAcoclave() {
        return acoclave;
    }

    public void setAcoclave(int acoclave) {
        this.acoclave = acoclave;
    }

    public int getClavepe() {
        return clavepe;
    }

    public void setClavepe(int clavepe) {
        this.clavepe = clavepe;
    }

    public String getPesvigencia() {
        return pesvigencia;
    }

    public void setPesvigencia(String pesvigencia) {
        this.pesvigencia = pesvigencia;
    }

    public int getNumProfUIPid() {
        return numProfUIPid;
    }

    public void setNumProfUIPid(int numProfUIPid) {
        this.numProfUIPid = numProfUIPid;
    }

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public int getPronumeroEmpleado() {
        return pronumeroEmpleado;
    }

    public void setPronumeroEmpleado(int pronumeroEmpleado) {
        this.pronumeroEmpleado = pronumeroEmpleado;
    }

    public int getGponumero() {
        return gponumero;
    }

    public void setGponumero(int gponumero) {
        this.gponumero = gponumero;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public int getUapclave() {
        return uapclave;
    }

    public void setUapclave(int uapclave) {
        this.uapclave = uapclave;
    }

    public int getUacclave() {
        return uacclave;
    }

    public void setUacclave(int uacclave) {
        this.uacclave = uacclave;
    }

    public int getCreid() {
        return creid;
    }

    public void setCreid(int creid) {
        this.creid = creid;
    }    

    //aqui agregue Jesus Ruelas
    public String getCTRnombre() {
        return CTRnombre;
    }

    public void setCTRnombre(String CTRnombre) {
        this.CTRnombre = CTRnombre;
    }           

    public String getTipoReporteCTR() {
        return tipoReporteCTR;
    }

    public void setTipoReporteCTR(String tipoReporteCTR) {
        this.tipoReporteCTR = tipoReporteCTR;
    }
    
    public List<Catalogoreportes> getListaCatalogoReportes() {
        return listaCatalogoReportes;
    }

    public void setListaCatalogoReportes(List<Catalogoreportes> listaCatalogoReportes) {
        this.listaCatalogoReportes = listaCatalogoReportes;
    }    

    //aqui agregue Jesus Ruelas
    //codigo metodos Jesus Ruelas   
    public String getTituloGraficas() {
        return tituloGraficas;
    }

    public String getTituloTabla() {
        return tituloTabla;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }  

//    public LoginBean getLoginBean() {
//        return loginBean;
//    }
//
//    public void setLoginBean(LoginBean loginBean) {
//        this.loginBean = loginBean;
//    }

    public Rol getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(Rol rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }        
    
}