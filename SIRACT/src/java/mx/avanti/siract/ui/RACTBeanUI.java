/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.ui;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.avanti.siract.application.helper.NodoMultiClass;
import mx.avanti.siract.application.helper.RACTBeanHelper;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Cicloescolar;
import mx.avanti.siract.business.entity.Configuracion;
import mx.avanti.siract.business.entity.Grupo;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Subtemaunidad;
import mx.avanti.siract.business.entity.Temaunidad;
import mx.avanti.siract.business.entity.Unidad;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.business.entity.UnidadaprendizajeImparteProfesor;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Y
 */
@ManagedBean
@ViewScoped
public class RACTBeanUI implements Serializable {

//-------------------------------ATRIBUTOS----------------------------------------//
    RACTBeanHelper ractBeanHelper = new RACTBeanHelper();

    List<Unidad> unidades;
    List<Temaunidad> temas;
    List<Unidadaprendizaje> unidadesaprendisaje;
    List<Profesor> profesores;
    List<Grupo> grupos = new ArrayList();
    Profesor profesor;
    String claveUnidadAprendizajeSeleccionada = "0";
    List<String> as = new ArrayList();
    List<Programaeducativo> programasEducativos;
    String clave = "000000";
    String nombreProfesor = "@Profesor";
    String fechaActual = "DD/MM/AAAA";
    String grupo = "00";
    String nombreUnidadAprendisaje;
    List<Areaconocimiento> areasConocimiento;
    String programaEducativoSeleccionado = "0";
    TreeNode root;
    private TreeNode[] selectedNodes;
    boolean pintar = true;
    float porcentajeAvance;
    String numeroReporte = "0";
    String tipoUnidadAprendizaje = "N";
    String subGrupo = "00";
    Boolean botones = false;
    String unidadAprendizajeSeleccionada = "00";
    Boolean disable = true;
    Boolean disable2 = true;
    String nombreUnidadSeleccionado;
    String cicloEscolar;

    String tipo = "";
    String[] num;

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    boolean auxSelectedNodes = false;

    //Lista utilizada para llenar datatable y generarPDF
    List<NodoMultiClass> filaTabla;

    boolean excesoDeReportes = false;

    //////PARTE DE OBSEVACIONES - - FALTA DE OPTIMIZAR
    String observacion;

    public NodoMultiClass nodoObservacion = new NodoMultiClass();

    //Variable donde se guarda el % de avance de unidades ya guardadas
    private float porcentajeAvanceInicial = 0;

    //-------------------------------ATRIBUTOS----------------------------------------//
    //-------------------------------sets/gets----------------------------------------//
    public String getUnidadAprendizajeSeleccionada() {
        return unidadAprendizajeSeleccionada;
    }

    public void setUnidadAprendizajeSeleccionada(String seleccionado) {
        if (!unidadAprendizajeSeleccionada.equals(seleccionado) && !seleccionado.equals("00")) {
            unidadAprendizajeSeleccionada = seleccionado;
            int idUnidadAprendizajeSeleccionada = Integer.parseInt(unidadAprendizajeSeleccionada);

            for (Unidadaprendizaje u : unidadesaprendisaje) {
                if (u.getUapid() == idUnidadAprendizajeSeleccionada) {
                    if (seleccionado != null && !seleccionado.isEmpty() && !seleccionado.equals("00")) {
                        String[] valores = u.getUapnombre().split(" -- ");
                        this.claveUnidadAprendizajeSeleccionada = String.valueOf(u.getUapclave());
                        tipoUnidadAprendizaje = valores[3];
                        this.grupo = valores[1];
                        subGrupo = valores[2];
                        nombreUnidadSeleccionado = valores[0];
                    }
                }
            }
            pintar = true;
            auxSelectedNodes = false;
            System.out.println("@@@QUITANDO BLOQUEO DE SELECTEDNODES");
        } else {
            pintar = false;
            if (selectedNodes != null) {
//                for (TreeNode nodosSeleccionado : selectedNodesInitial) {
//                    nodosSeleccionado.setSelected(true);
//                }
            }
            System.out.println("@@@SE CONSERVA BLOQUEO DE NODOS SELECCIONADOS");
        }
//        
        if (seleccionado.equals("00")) {
            root = new DefaultTreeNode();
            selectedNodes = null;
            claveUnidadAprendizajeSeleccionada = "0";
            this.unidadAprendizajeSeleccionada = seleccionado;
        }
        setEnable2(true);
    }

    public String getClaveUnidadAprendizajeSeleccionada() {
        return claveUnidadAprendizajeSeleccionada;
    }

    public void setClaveUnidadAprendizajeSeleccionada(String claveUnidadAprendizajeSeleccionada) {
        this.claveUnidadAprendizajeSeleccionada = claveUnidadAprendizajeSeleccionada;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public String getCicloEscolar() {
        return cicloEscolar;
    }

    public void setCicloEscolar(String cicloEscolar) {
        this.cicloEscolar = cicloEscolar;
    }

    public void setBotones(Boolean botones) {
        this.botones = botones;
    }

    public List<NodoMultiClass> getFilaTabla() {
        return filaTabla;
    }

    public void setFilaTabla(List<NodoMultiClass> filaTabla) {
        this.filaTabla = filaTabla;
    }

    public void setExcesoDeReportes(boolean excesoDeReportes) {
        this.excesoDeReportes = excesoDeReportes;
    }

    public void setNumeroReporte(String numeroReporte) {
        this.numeroReporte = numeroReporte;
    }

    public float getPorcentajeAvance() {
        System.out.println("CONSIGUIENDO PORCENTAJE AVANCE");
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(float porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public TreeNode[] getSelectedNodes() {
        System.out.println("OBTENIENDO NODOS");
        //Prueba de contenido
        if(selectedNodes!=null&&selectedNodes.length>=1){
        for(TreeNode nodo:selectedNodes){
           
        }
        }else{
            System.out.println("NO HAY SELECCION ");
        }
        //
        return selectedNodes;
    }

    public void setSelectedNodes(TreeNode[] selectedNodes) {
        System.out.println("SET SELECTED NODES TRUE");

        this.selectedNodes = selectedNodes;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public String getProgramaEducativoSeleccionado() {
        return programaEducativoSeleccionado;

    }

    public void setProgramaEducativoSeleccionado(String programaEducativoSeleccionado) {
        if (!programaEducativoSeleccionado.equals(this.programaEducativoSeleccionado)) {
            setUnidadAprendizajeSeleccionada("00");
        }
        this.programaEducativoSeleccionado = programaEducativoSeleccionado;
        if (programaEducativoSeleccionado.equals("00")) {
            System.out.println("PROGRAMA EDUCATIV0 SELECCIONADO ");
            claveUnidadAprendizajeSeleccionada = "0";
            unidadAprendizajeSeleccionada = "0";
            porcentajeAvance = 0;

        }
    }

    public List<Programaeducativo> getProgramasEducativos() {
        programasEducativos = ractBeanHelper.getProgramaesEducativos();
        return programasEducativos;
    }

    public void setProgramasEducativos(List<Programaeducativo> programasEducativos) {
        this.programasEducativos = programasEducativos;
    }

    public List<Areaconocimiento> getAreasConocimiento() {
        return areasConocimiento;
    }

    public void setAreasConocimiento(List<Areaconocimiento> areasConocimiento) {
        this.areasConocimiento = areasConocimiento;
    }

    public String getNombreUnidadAprendisaje() {
        return nombreUnidadAprendisaje;
    }

    public void setNombreUnidadAprendisaje(String nombreUnidadAprendisaje) {
        this.nombreUnidadAprendisaje = nombreUnidadAprendisaje;
    }

    public List<Grupo> getGrupos() {
        grupos = ractBeanHelper.getGrupos(claveUnidadAprendizajeSeleccionada);
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }

    public void setUnidadesaprendisaje(List<Unidadaprendizaje> unidadesaprendisaje) {
        this.unidadesaprendisaje = unidadesaprendisaje;
    }

    public List<Temaunidad> getTemas() {
        return temas;
    }

    public void setTemas(List<Temaunidad> temas) {
        this.temas = temas;
    }

    public List<Unidad> getUnidades() {
        return unidades;
    }

    public List<Unidadaprendizaje> getUnidadesaprendisajeConGrupo() {
        unidadesaprendisaje = ractBeanHelper.getUnidadesaprendisajeConGrupos(programaEducativoSeleccionado);

        return unidadesaprendisaje;
    }

    public void setUnidades(List<Unidad> unidades) {
        this.unidades = unidades;
    }

    public String getNumeroReporte() {
        return "Reporte #" + numeroReporte;
    }

    public NodoMultiClass getNodoObservacion() {
        return nodoObservacion;
    }

    public void setNodoObservacion(NodoMultiClass nodoObservacion) {
        this.nodoObservacion = nodoObservacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public boolean isDisable2() {
        return disable2;
    }

    public void setDisable2(boolean disable2) {
        this.disable2 = disable2;
    }

    public void setEnable(boolean enable) {
        if (programaEducativoSeleccionado.equalsIgnoreCase("00")) {
            enable = true;
            setEnable2(true);
        } else {
            enable = false;
            setEnable2(true);
        }
        setDisable(enable);
    }

    public void setEnable2(boolean enable2) {
        if (unidadAprendizajeSeleccionada.equalsIgnoreCase("00") || selectedNodes == null || selectedNodes.length <= 0) {
            enable2 = true;
        } else {
            enable2 = false;
        }
        //setDisable2(enable2);
//        disable2=enviado;
    }

    //-------------------------------sets/gets----------------------------------------//
    //-------------------------------CONSTRUCTORES------------------------------------//
    public RACTBeanUI() {
        root = new DefaultTreeNode();

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        fechaActual = sdf.format(date);
        programasEducativos = new ArrayList();

        //Obtener CicloEscolarActual
        Cicloescolar auxCicloEscolar;
        auxCicloEscolar = ractBeanHelper.cicloescolarActual();
        cicloEscolar = "N/A";
        if (auxCicloEscolar == null) {
        } else {
            Configuracion auxConfiguracion = ractBeanHelper.configuracionDeCicloEscolar(auxCicloEscolar.getCesid());
            Calendar auxCalendarioConfiguracion = Calendar.getInstance();
            auxCalendarioConfiguracion.setTime(auxConfiguracion.getConfechaInicioSemestre());
            auxCalendarioConfiguracion.add(Calendar.DATE, (auxConfiguracion.getConnumeroSemanas() * 7));
            if (date.before(auxCalendarioConfiguracion.getTime())) {
                cicloEscolar = auxCicloEscolar.getCescicloEscolar();
            }
        }
        //Obteniendo CicloEscolarActual

        numeroReporte = ractBeanHelper.obtenerReporteSiguiente();
        System.out.println("CREANDO BEANUI ");
    }

    @PostConstruct
    public void postConstructor() {
        if (numeroReporte.equals("0")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No hay fechas de envio", "Ya no hay fechas futuras de envio"));
        } else {
            if (loginBean == null && loginBean.getUsuario() != null) {
                System.out.println("No hay loginbean");
            } else {
                profesor = ractBeanHelper.getProfesorFromUsuario(loginBean.getUsuario());
                if (profesor == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario sin profesor", "Este usuario no tiene ligado ningun profesor"));

                } else {
                    nombreProfesor = profesor.getPronombre();
                    ractBeanHelper.setProfesor(profesor);
                }
            }
        }
    }

    //-------------------------------CONSTRUCTORES------------------------------------//  
    //--------------------------------METODOS----------------------------------------//
    public Boolean isBotones() {
        if (programaEducativoSeleccionado.equals("0")) {
            botones = false;
        } else {
            botones = true;
        }
        return botones;
    }

    public boolean isExcesoDeReportes() {
        return excesoDeReportes;
    }

    TreeNode[] selectedNodesInitial = new DefaultTreeNode[10];

    public void fillTree() {
        TreeNode root = new DefaultTreeNode();
        selectedNodes = null;
        auxSelectedNodes = false;
        root = ractBeanHelper.getNodos(claveUnidadAprendizajeSeleccionada, grupo, subGrupo, tipoUnidadAprendizaje);
        pintar = false;
        //PONER NODOS SELECCIONADOS
        if (auxSelectedNodes) {
            System.out.println("NO RECALCULAR SELECCIONES");
        } else {
            System.out.println("SELECCIONANDO NODOS DE BASE DE DATOS ");
            List<TreeNode> auxSelected = ractBeanHelper.getListaSeleccionados();
            selectedNodes = new TreeNode[auxSelected.size()];
            selectedNodesInitial = new TreeNode[auxSelected.size()];
            int x = 0;
            for (TreeNode node : auxSelected) {
                if (node != null) {
                    if (node.isSelected()) {
                        selectedNodes[x] = node;
                        selectedNodesInitial[x] = node;
                        x++;
                    }
                }
            }
            auxSelectedNodes = true;

        }
        //Deshabilitando nodos que ya han sido enviados
//        for (TreeNode nodo : root.getChildren()) {
//
//            for (TreeNode seleccionado : selectedNodes) {
//                if (((NodoMultiClass) seleccionado.getData())!=null&&((NodoMultiClass) seleccionado.getData()).getTipo().equals(((NodoMultiClass) nodo.getData()).getTipo()) && ((NodoMultiClass) seleccionado.getData()).getId().equals(((NodoMultiClass) nodo.getData()).getId())) {
//                    nodo.setSelectable(false);
//                    nodo.setSelected(true);
//                }
//            }
//
//            for (TreeNode nodo2 : nodo.getChildren()) {
//
//                for (TreeNode seleccionado : selectedNodes) {
//                    if (((NodoMultiClass) seleccionado.getData()).getTipo().equals(((NodoMultiClass) nodo2.getData()).getTipo()) && ((NodoMultiClass) seleccionado.getData()).getId().equals(((NodoMultiClass) nodo2.getData()).getId())) {
//                        nodo2.setSelectable(false);
//                        nodo2.setSelected(true);
//                    }
//                }
//
//                for (TreeNode nodo3 : nodo2.getChildren()) {
//
//                    for (TreeNode seleccionado : selectedNodes) {
//                        if (((NodoMultiClass) seleccionado.getData()).getTipo().equals(((NodoMultiClass) nodo3.getData()).getTipo()) && ((NodoMultiClass) seleccionado.getData()).getId().equals(((NodoMultiClass) nodo3.getData()).getId())) {
//                            nodo3.setSelectable(false);
//                            nodo3.setSelected(true);
//                        }
//                    }
//
//                }
//            }
//        }

        //Validar si el RACT de esta unidad de aprendizaje ya ha sido enviado
        validarEnviado();

        //FIN NODOS SELECCIONADOS
        //GUARDAR INFORMACION PARA DATATABLE DE IMPRESION PDF
        filaTabla = ractBeanHelper.getListaPDF();

        this.root = root;
        calculo();
    }

    public void deshabilitarEnviados() {
        //Deshabilitando nodos que ya han sido enviados
        selectedNodesInitial = new TreeNode[selectedNodes.length];
        for (int i=0;i<selectedNodes.length;i++) {
                selectedNodesInitial[i]=selectedNodes[i];
            }
        for (TreeNode nodo : root.getChildren()) {

            for (TreeNode seleccionado : selectedNodes) {
                if (((NodoMultiClass) seleccionado.getData()).getTipo().equals(((NodoMultiClass) nodo.getData()).getTipo()) && ((NodoMultiClass) seleccionado.getData()).getId().equals(((NodoMultiClass) nodo.getData()).getId())) {
                    nodo.setSelectable(false);
                    nodo.setSelected(true);
                }
            }

            for (TreeNode nodo2 : nodo.getChildren()) {

                for (TreeNode seleccionado : selectedNodes) {
                    if (((NodoMultiClass) seleccionado.getData()).getTipo().equals(((NodoMultiClass) nodo2.getData()).getTipo()) && ((NodoMultiClass) seleccionado.getData()).getId().equals(((NodoMultiClass) nodo2.getData()).getId())) {
                        nodo2.setSelectable(false);
                        nodo2.setSelected(true);
                    }
                }

                for (TreeNode nodo3 : nodo2.getChildren()) {

                    for (TreeNode seleccionado : selectedNodes) {
                        if (((NodoMultiClass) seleccionado.getData()).getTipo().equals(((NodoMultiClass) nodo3.getData()).getTipo()) && ((NodoMultiClass) seleccionado.getData()).getId().equals(((NodoMultiClass) nodo3.getData()).getId())) {
                            nodo3.setSelectable(false);
                            nodo3.setSelected(true);
                        }
                    }

                }
            }
        }
        
        //Esta tarea se ejecuta al llenar nuevamente el arbol cuando se envia un reporte
//        validarEnviado();
    }

    public TreeNode pintarArbol() {
        return root;
    }

    public String redirect() {
        return "seleccionAreaConocimiento.xhtml?facesRedirect=true";
    }

    public String obtenerGrupo(Unidadaprendizaje unidadAprendisaje) {
        ractBeanHelper.buscarGrupo(String.valueOf(unidadAprendisaje.getUapclave()));
        return "";
    }

    public List<Temaunidad> obtenerTemas(Unidad unidad) {
        List temasUnidad = new ArrayList<Temaunidad>(unidad.getTemaunidads());
        return temasUnidad;
    }

    public List<Subtemaunidad> obtenerSubTemas(Temaunidad tema) {
        List temasUnidad = new ArrayList<Subtemaunidad>(tema.getSubtemaunidads());
        return temasUnidad;
    }

    public Unidadaprendizaje obtenerUnidadesA(UnidadaprendizajeImparteProfesor p) {
        unidades = new ArrayList(p.getUnidadaprendizaje().getUnidads());
        if (claveUnidadAprendizajeSeleccionada.equals("0")) {
            claveUnidadAprendizajeSeleccionada = p.getUnidadaprendizaje().getUapid().toString();
        }
        nombreUnidadAprendisaje = p.getUnidadaprendizaje().getUapnombre();

        return p.getUnidadaprendizaje();
    }

    public void onNodeCollapse(NodeCollapseEvent event) {
        if (event != null && event.getTreeNode() != null) {
            event.getTreeNode().setExpanded(false);
        }
    }

    public void onNodeSelect(NodeSelectEvent event) {
        System.out.println("SELECCIONADO");
        calculo();
        if (selectedNodes == null || selectedNodes.length == 0) {
            disable2 = true;
        } else {
            disable2 = false;
        }
        if (enviado) {
            disable2 = enviado;
        }
        if (event != null && event.getTreeNode() != null) {
            event.getTreeNode().setSelected(true);
        }
    }

    public void onNodeUnselect(NodeUnselectEvent event) {
        System.out.println("DESSELECCIONADO");
        if (selectedNodes == null || selectedNodes.length == 0) {
            disable2 = true;
        }
        if (enviado) {
            disable2 = enviado;
        }
        
       
        if (event != null && event.getTreeNode() != null) {

           }
        calculo();

    }
    
    private boolean unselectNode(TreeNode child){
   System.out.println("==============SE INTENTA ELIMINAR UN NODO HIJO "+((NodoMultiClass) child.getData()).getTipo()+" "+((NodoMultiClass) child.getData()).getId()+
           "/"+selectedNodes.length);
      for(int i=0;i<selectedNodes.length;i++){
          System.out.println("==============SE COMPARA CON "+((NodoMultiClass) selectedNodes[i].getData()).getTipo()+" "+((NodoMultiClass) selectedNodes[i].getData()).getId()+
           "/"+i);
          if (((NodoMultiClass) selectedNodes[i].getData()).getTipo().equals(((NodoMultiClass) child.getData()).getTipo()) && ((NodoMultiClass) selectedNodes[i].getData()).getId().equals(((NodoMultiClass) child.getData()).getId())) {
              TreeNode[]auxSelected=new TreeNode[selectedNodes.length-1];
              for(int j=0;j<selectedNodes.length;j++){
                    if (j<i) {
                    auxSelected[j]=selectedNodes[j];
                    }else{
                    auxSelected[j]=selectedNodes[j+1]; 
                    }
              }
              System.out.println("SE ELIMINARA "+((NodoMultiClass) selectedNodes[i].getData()).getNombre());
              selectedNodes=auxSelected;
                         return true;

            }
      }
      return false;
    }

    private void expand(TreeNode treeNode) {
        if (treeNode.getParent() != null) {
            treeNode.getParent().setExpanded(true);
            expand(treeNode.getParent());
        }
    }

    public void guardarReporte() {
        pintar = true;
        auxSelectedNodes = true;
        if (selectedNodes == null || selectedNodes.length < 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reporte no guardado", "Seleccione al menos una unidad"));
        } else {
            excesoDeReportes = !ractBeanHelper.guardarReporteAvance(selectedNodes, porcentajeAvance, profesor.getProid(), Integer.parseInt(grupo), subGrupo, tipoUnidadAprendizaje, Integer.parseInt(claveUnidadAprendizajeSeleccionada), false);
            if (excesoDeReportes) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reporte no guardado", "Ya se han enviado los reportes correspondientes"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reporte guardado", "El reporte ha sido guardado"));
            }
//            deshabilitarEnviados();
//            validarEnviado();
        }
    }

    public void enviarReporte() {
        pintar = true;
        auxSelectedNodes = true;
        System.out.println("Metodo de enviar reporte ha sido activado");
        if (selectedNodes == null || selectedNodes.length < 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reporte no enviado", "Seleccione al menos una unidad"));
        } else {
            excesoDeReportes = !ractBeanHelper.guardarReporteAvance(selectedNodes, porcentajeAvance, profesor.getProid(), Integer.parseInt(grupo), subGrupo, tipoUnidadAprendizaje, Integer.parseInt(claveUnidadAprendizajeSeleccionada), true);
            if (excesoDeReportes) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reporte no enviado", "Ya se han enviado los reportes correspondientes"));
            } else {
            fillTree();
            }
            deshabilitarEnviados();
        }
        setEnable2(true);
    }

    public void sumarNodoaPorcentaje(TreeNode nodo) {
        float sumar = 0;
        if (nodo.isSelected()) {
            sumar = Float.parseFloat(nodo.getData().toString().split("-//-")[5]);
            System.out.println("SE SUMARA" + sumar);
            porcentajeAvance += sumar;
        } else {
            for (TreeNode node : nodo.getChildren()) {
                sumarNodoaPorcentaje(node);
            }
        }
    }

    public void calculo() {
        porcentajeAvance = 0;
        if ((selectedNodes != null && selectedNodes.length > 0) || (selectedNodesInitial != null && selectedNodesInitial.length > 0)) {
            for (TreeNode node : root.getChildren()) {
                sumarNodoaPorcentaje(node);
            }
            System.out.println("FIN DEL CALCULO DE PORCENTAJE DE AVANCE");
        } else {
            System.out.println("NO CALCULANDO AVANCE");
        }
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        DecimalFormat formato = new DecimalFormat("####.##", simbolo);

        porcentajeAvance = Float.parseFloat(formato.format(porcentajeAvance));

        //Actualizar etiqueta de porcentaje de avance
        RequestContext.getCurrentInstance().execute("avance(" + porcentajeAvance + ")");
    }

    public void cambio(TreeNode[] nodes) {
        if (nodes != null && nodes.length > 0) {
            for (TreeNode node : nodes) {
                String[] todo = node.getData().toString().split("-//-");
                porcentajeAvance += Float.parseFloat(todo[5]);
            }
        }
    }

    public void buscarUnidadAprendizajeImparteProfesor() {

    }

    public void ponerObservacion(String numero) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        observacion = request.getParameter(":formId:arbol:tabla");
        //PARA USAR OBSERVACIONES DE PRUEBA
        observacion = request.getParameter("formId:observacion");
    }

    public String observacionVacia(NodoMultiClass nodo) {
        String tipo = nodo.toString().split("-//-")[0];
        String unidad = nodo.toString().split("-//-")[1];
        String tema = nodo.toString().split("-//-")[2];
        String subtema = nodo.toString().split("-//-")[3];
        for (TreeNode nod : root.getChildren()) {
            if (tipo.equals("unidad") || tipo.equals("practicalaboratorio") || tipo.equals("practicataller") || tipo.equals("practicaCampo")) {
                if (nod.getData().toString().split("-//-")[1].equals(unidad)) {
                    observacion = ((NodoMultiClass) nod.getData()).getObservaciones();
                }
            } else {
                for (TreeNode nod2 : nod.getChildren()) {
                    if (tipo.equals("temaunidad")) {
                        if (nod2.getData().toString().split("-//-")[2].equals(tema)) {
                            observacion = ((NodoMultiClass) nod2.getData()).getObservaciones();

                        }
                    } else {
                        for (TreeNode nod3 : nod2.getChildren()) {
                            if (tipo.equals("subtemaunidad")) {
                                if (nod3.getData().toString().split("-//-")[3].equals(subtema)) {
                                    observacion = ((NodoMultiClass) nod3.getData()).getObservaciones();
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!observacion.trim().isEmpty()) {

            return "ui-icon-NoteBlank";
        } else {
            return "ui-icon-NoteWrite";

        }
    }

    public void prepararObservacion(NodoMultiClass nodoMultiClass) {
        setNodoObservacion(nodoMultiClass);
        verObservacion();
    }

    public void verObservacion() {
        NodoMultiClass nodo = nodoObservacion;
        String tipo = nodo.toString().split("-//-")[0];
        String unidad = nodo.toString().split("-//-")[1];
        String tema = nodo.toString().split("-//-")[2];
        String subtema = nodo.toString().split("-//-")[3];
        for (TreeNode nod : root.getChildren()) {
            if (tipo.equals("unidad") || tipo.equals("practicalaboratorio") || tipo.equals("practicataller") || tipo.equals("practicaCampo")) {
                if (nod.getData().toString().split("-//-")[1].equals(unidad)) {
                    observacion = ((NodoMultiClass) nod.getData()).getObservaciones();
                }
            } else {
                for (TreeNode nod2 : nod.getChildren()) {
                    if (tipo.equals("temaunidad")) {
                        if (nod2.getData().toString().split("-//-")[2].equals(tema)) {
                            observacion = ((NodoMultiClass) nod2.getData()).getObservaciones();

                        }
                    } else {
                        for (TreeNode nod3 : nod2.getChildren()) {
                            if (tipo.equals("subtemaunidad")) {
                                if (nod3.getData().toString().split("-//-")[3].equals(subtema)) {
                                    observacion = ((NodoMultiClass) nod3.getData()).getObservaciones();
                                }
                            }
                        }
                    }
                }
            }
        }
        observacion = observacion.trim();
    }

    public void agregarObservacion() {
        pintar = false;
        NodoMultiClass nodo = nodoObservacion;
        if (observacion != null) {
            nodo.setObservaciones(observacion);

            String tipo = nodo.toString().split("-//-")[0];
            String unidad = nodo.toString().split("-//-")[1];
            String tema = nodo.toString().split("-//-")[2];
            String subtema = nodo.toString().split("-//-")[3];
            if (tema == null) {
                tema = " ";
            }
            if (subtema == null) {
                subtema = " ";
            }
            for (TreeNode nod : root.getChildren()) {
                if (tipo.equals("unidad") || tipo.equals("practicalaboratorio") || tipo.equals("practicataller")) {
                    if (nod.getData().toString().split("-//-")[1].equals(unidad)) {
                        ((NodoMultiClass) nod.getData()).setObservaciones(observacion);
                        ((NodoMultiClass) nod.getData()).setImpartido(nod.isSelected());

                        ractBeanHelper.guardarComentario(nod, porcentajeAvance, profesor.getProid(), Integer.parseInt(grupo), subGrupo, tipoUnidadAprendizaje, Integer.parseInt(claveUnidadAprendizajeSeleccionada));
                    }
                } else {
                    List<TreeNode> nodos2 = nod.getChildren();
                    if (!nodos2.isEmpty() && nodos2 != null) {
                        for (TreeNode nod2 : nodos2) {
                            if (tipo.equals("temaunidad")) {
                                if (nod2.getData().toString().split("-//-")[2].equals(tema)) {
                                    ((NodoMultiClass) nod2.getData()).setObservaciones(observacion);
                                    ((NodoMultiClass) nod2.getData()).setImpartido(nod2.isSelected());    
                                    ractBeanHelper.guardarComentario(nod2, porcentajeAvance, profesor.getProid(), Integer.parseInt(grupo), subGrupo, tipoUnidadAprendizaje, Integer.parseInt(claveUnidadAprendizajeSeleccionada));
                                }
                            } else {
                                for (TreeNode nod3 : nod2.getChildren()) {
                                    if (tipo.equals("subtemaunidad")) {
                                        if (nod3.getData().toString().split("-//-")[3].equals(subtema)) {
                                            ((NodoMultiClass) nod3.getData()).setObservaciones(observacion);
                                            ((NodoMultiClass) nod3.getData()).setImpartido(nod3.isSelected());
                                            ractBeanHelper.guardarComentario(nod3, porcentajeAvance, profesor.getProid(), Integer.parseInt(grupo), subGrupo, tipoUnidadAprendizaje, Integer.parseInt(claveUnidadAprendizajeSeleccionada));
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
        //Volver true la bandera de actualizacion para el arbol de contenido tematico
        // pintar = true;
    }

    //CREAR DE PDF
    //Es necesario el JAR de PDFBox 1.8.6
    public List<NodoMultiClass> crearPDF() {
        System.out.println("CREANDO PDF");
        return ractBeanHelper.getListaPDF();
    }

    public void preProcessPDF(Object document) throws IOException, DocumentException {

        final Document pdf = (Document) document;

        pdf.setPageSize(PageSize.A4.rotate());
    }

    //Impresion de archivo PDF
    public void postProcessPDF(Object document) throws IOException, DocumentException {
        final Document pdf = (Document) document;
        TreeNode[] selectedNodesPDF = selectedNodes;

        if (selectedNodes != null) {
            for (TreeNode nodo : selectedNodes) {
                System.out.println("*********************" + ((NodoMultiClass) nodo.getData()).getNombre());
            }
        }
        pdf.setPageSize(PageSize.A4.rotate());
        pdf.open();
        String nombrep = profesor.getPronombre() + " " + profesor.getProapellidoPaterno() + " " + profesor.getProapellidoMaterno();
        String numEmpPro = Integer.toString(profesor.getPronumeroEmpleado());
        String porAv = Float.toString(porcentajeAvance);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        //Obtener el nombre del programa educativo seleccionado
        String nombreProgEdu = "";
        int programaEducativoSeleccionado2 = Integer.parseInt(programaEducativoSeleccionado);
        for (int x = 0; x < programasEducativos.size(); x++) {
            if (programasEducativos.get(x).getPedid() == programaEducativoSeleccionado2) {
                nombreProgEdu = programasEducativos.get(x).getPednombre();
            }
        }
        //----------------------------------------------------------------------------------

        //Obtener el nombre de la unidad de aprendizaje y clave seleccionada
        String nombreUniApr = "";
        String nombreclave = "";
        int uniAprselec2 = Integer.parseInt(unidadAprendizajeSeleccionada);
        for (int x = 0; x < unidadesaprendisaje.size(); x++) {
            if (unidadesaprendisaje.get(x).getUapclave() == uniAprselec2) {
                nombreUniApr = unidadesaprendisaje.get(x).getUapnombre();
                nombreclave = Integer.toString(unidadesaprendisaje.get(x).getUapclave());
            }
        }
        //----------------------------------------------------------------------

        // rutaImagen es la ruta para acceder a la imagen guardada en el folder resources del proyecto
        try {
//            Image imagenLogo = Image.getInstance("C:\\Users\\Y\\Desktop\\RACT 22-01-2015\\RACT\\build\\web\\resources\\imagenes\\logo.jpg");
            Image imagenLogo = Image.getInstance(RACTBeanUI.class.getResource("imagenes/logo.jpg"));

            //Posicion de imagen (Horizontal, Vertiacal)
            imagenLogo.setAbsolutePosition(120f, 460f);

            //Tamaño de imagen (Ancho, largo)
            imagenLogo.scaleAbsolute(90, 120);
            //imagenLogo.scaleAbsoluteWidth(100f);
            //imagenLogo.scaleAbsoluteHeight(150f);
            //imagenLogo.scaleToFit(100f, 120f); Esta es la buena
            //Image imagenLogo = Image.getInstance("http://ed.uabc.mx/sed/images/logo.jpg");
            //imagenLogo.scaleAbsolute(70f, 90f);
            pdf.add(imagenLogo);
        } catch (Exception exception) {
            System.out.println("****NO SE ENCONTRO LA RUTA DE IMAGEN ESPECIFICADA");
        }
        //Tabla con UABC y Nombre del profesor(a)
        PdfPTable pdfTabletitulo = new PdfPTable(2);
        pdfTabletitulo.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        PdfPTable pdfTabletitulo2 = new PdfPTable(2);
        pdfTabletitulo2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        Paragraph UABC = new Paragraph("Universidad Autónoma de Baja California", FontFactory.getFont(FontFactory.TIMES, 22, Font.BOLD, new Color(0, 113, 65)));
        UABC.setAlignment(Element.ALIGN_CENTER);
        Paragraph esp = new Paragraph(" ");
        pdf.add(UABC);
        pdf.add(esp);
        pdf.add(esp);
        pdf.add(esp);
        pdf.add(esp);
        pdfTabletitulo.addCell(new Paragraph(""));
        pdfTabletitulo.addCell(new Paragraph(""));
        pdfTabletitulo.addCell(new Paragraph(""));
        pdfTabletitulo.addCell(new Paragraph(""));
        pdfTabletitulo.addCell(new Paragraph(""));
        pdfTabletitulo.addCell(new Paragraph(""));
        pdfTabletitulo.addCell(new Paragraph(""));
        pdfTabletitulo.addCell(new Paragraph(""));
        pdfTabletitulo.addCell(new Paragraph(""));
        pdfTabletitulo.addCell(new Paragraph(""));

        pdfTabletitulo.addCell(new Paragraph("Profesor(a): ", FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new Color(0, 0, 0))));
        pdfTabletitulo.addCell(new Paragraph(nombrep));

        pdfTabletitulo2.addCell(new Paragraph("Num. de Empleado: ", FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new Color(0, 0, 0))));
        pdfTabletitulo2.addCell(new Paragraph(numEmpPro));

        pdfTabletitulo.setHorizontalAlignment(25);
        //pdfTabletitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
        //document.add(table);
        float[] columnWidthsss = new float[]{4f, 28};
        pdfTabletitulo.setWidths(columnWidthsss);
        pdf.add(pdfTabletitulo);

        float[] columnWidthss = new float[]{10f, 38};
        pdfTabletitulo2.setWidths(columnWidthss);
        pdf.add(pdfTabletitulo2);

        pdf.add(new Phrase(" "));

        //----------------------------------------------------------------------
        //Tabla Cabezera
        PdfPTable pdftablecabezera = new PdfPTable(4);
        pdftablecabezera.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        PdfPTable pdftablecabezera2 = new PdfPTable(5);
        pdftablecabezera2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        pdftablecabezera.addCell(new Paragraph("Programa educativo: ", FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new Color(0, 0, 0))));
        pdftablecabezera.addCell(new Paragraph("Fecha: ", FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new Color(0, 0, 0))));
        pdftablecabezera.addCell(new Paragraph("Ciclo Escolar: ", FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new Color(0, 0, 0))));
        pdftablecabezera.addCell(new Paragraph("Avance Global: ", FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new Color(0, 0, 0))));
        pdftablecabezera.addCell(new Paragraph(nombreProgEdu));
        pdftablecabezera.addCell(new Paragraph(formato.format(new Date())));
        pdftablecabezera.addCell(new Paragraph("        " + cicloEscolar));
        pdftablecabezera.addCell(new Paragraph("        " + porAv));

        pdftablecabezera.addCell(new Paragraph(" "));
        pdftablecabezera.addCell(new Paragraph(" "));
        pdftablecabezera.addCell(new Paragraph(" "));
        pdftablecabezera.addCell(new Paragraph(" "));

        pdftablecabezera2.addCell(new Paragraph("Unidad de Aprendizaje: ", FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new Color(0, 0, 0))));
        pdftablecabezera2.addCell(new Paragraph("Clave: ", FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new Color(0, 0, 0))));
        pdftablecabezera2.addCell(new Paragraph("Grupo: ", FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new Color(0, 0, 0))));
        pdftablecabezera2.addCell(new Paragraph("Subgrupo: ", FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new Color(0, 0, 0))));
        pdftablecabezera2.addCell(new Paragraph("Tipo Grupo: ", FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new Color(0, 0, 0))));
        pdftablecabezera2.addCell(new Paragraph(nombreUnidadSeleccionado));
        pdftablecabezera2.addCell(new Paragraph(claveUnidadAprendizajeSeleccionada));
        pdftablecabezera2.addCell(new Paragraph("   " + grupo));
        pdftablecabezera2.addCell(new Paragraph("       " + subGrupo));
        String tipoGrupo = "";
        switch (this.tipoUnidadAprendizaje) {
            case "C":
                tipoGrupo = "Clase";
                break;
            case "L":
                tipoGrupo = "Laboratorio";
                break;
            case "T":
                tipoGrupo = "Taller";
                break;
            case "P":
                tipoGrupo = "Practica";
                break;
            case "CL":
                tipoGrupo = "Practicas Clinica";
                break;
        }
        pdftablecabezera2.addCell(new Paragraph("       " + tipoGrupo));
        pdftablecabezera2.addCell(new Paragraph(" "));

        pdftablecabezera.setHorizontalAlignment(25);
        float[] columnWidths = new float[]{38f, 10f, 14f, 18f};
        pdftablecabezera.setWidths(columnWidths);
        pdf.add(pdftablecabezera);

        pdftablecabezera2.setHorizontalAlignment(25);
        float[] columnWidthss2 = new float[]{38f, 10f, 8f, 11f, 14f};
        pdftablecabezera2.setWidths(columnWidthss2);
        pdf.add(pdftablecabezera2);

        pdf.add(new Phrase(" "));

        //----------------------------------------------------------------------
        Paragraph numrep = new Paragraph("Reporte # " + numeroReporte, FontFactory.getFont(FontFactory.TIMES, 16, Font.BOLD, new Color(0, 0, 0)));
        numrep.setAlignment(Element.ALIGN_CENTER);
        pdf.add(numrep);
        pdf.add(new Paragraph(" "));
        //Tabla con Datos 
        PdfPTable pdfTable = new PdfPTable(3);
        pdfTable.addCell(new Phrase("Nombre", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, new Color(0, 0, 0))));
        pdfTable.addCell(new Phrase("Porcentaje", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, new Color(0, 0, 0))));
        pdfTable.addCell(new Phrase("Observaciones", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, new Color(0, 0, 0))));

        //Evitar Null pointer exception por no tener nodoes en selectedNodes
        //Se utiliza un auxiliar de selectedNodes por que hay posibilidad de que selectedNodes se modifique durante el proceso de generacion del PDF
        if (selectedNodesPDF != null && selectedNodesPDF.length > 0) {

            //Regresar a lo basicoString
            int nns = selectedNodesPDF.length;
            String[] ns = new String[nns];
            for (int x = 0; x < selectedNodesPDF.length; x++) {
                ns[x] = ((NodoMultiClass) selectedNodesPDF[x].getData()).getNumero() + "--" + ((NodoMultiClass) selectedNodes[x].getData()).getNombre() + "--" + ((NodoMultiClass) selectedNodes[x].getData()).getPorcentajeAvance() + "--" + ((NodoMultiClass) selectedNodes[x].getData()).getObservaciones() + " -- ";
            }
            String[] filas = new String[4];
            String[] nstr = new String[3];

            String[] filas2 = new String[4];
            String[] nstr2 = new String[3];

            for (int x = 1; x < ns.length; x++) {
                for (int y = 0; y < ns.length - x; y++) {
                    //Obtengo los valores para hacer las comparaciones
                    int[] n = new int[3];
                    int[] n2 = new int[3];
                    filas = ns[y].split("--");
                    nstr = filas[0].split("\\.");
                    for (int z = 0; z < nstr.length; z++) {
                        if (nstr == null) {
                            n[z] = 0;
                        } else {
                            n[z] = Integer.parseInt(nstr[z]);
                        }
                    }
                    filas2 = ns[y + 1].split("--");
                    nstr2 = filas2[0].split("\\.");
                    for (int z = 0; z < nstr2.length; z++) {
                        if (nstr2 == null) {
                            n2[z] = 0;
                        } else {
                            n2[z] = Integer.parseInt(nstr2[z]);
                        }
                    }
                    //-------------------------------------------------comparacion
                    if (n[0] == n2[0]) {
                        if (n[1] == n2[1]) {
                            if (n[2] > n2[2]) {
                                String aux = ns[y];
                                ns[y] = ns[y + 1];
                                ns[y + 1] = aux;
                            }
                        } else {
                            if (n[1] > n2[1]) {
                                String aux = ns[y];
                                ns[y] = ns[y + 1];
                                ns[y + 1] = aux;
                            }
                        }
                    } else {
                        if (n[0] > n2[0]) {
                            String aux = ns[y];
                            ns[y] = ns[y + 1];
                            ns[y + 1] = aux;
                        }
                    }
                    //-------------------------------------------------------------
                }
            }

            String[] auxNum = new String[4];
            for (int x = 0; x < ns.length; x++) {

                filas = ns[x].split("--");
                num = filas[0].split("\\.");

                //Auxiliar para agregar padre de tema/subtema
                int auxMargen = 1;
                //Agregar unidad a la que pertenece el tema/subtema

                String[] auxNum2 = auxNum;
                int tamanoArbol = root.getChildCount();
                //AGREGAR A TEMA
                if (Integer.parseInt(num[0]) > tamanoArbol) {
                    auxMargen = Integer.parseInt(num[0]) - tamanoArbol;
                }
                if (num.length >= 2 && auxNum2[0] != null && !auxNum2[0].equals(num[0])) {
                    if (!num[1].equals("0")) {
                        while (!num[0].equals(auxNum2[0]) && auxMargen > 0) {
                            System.out.println("XXXXXXIteracion  para encontrar padre XXXXXXX 0" + auxMargen);
                            auxNum2 = (((NodoMultiClass) root.getChildren().get(Integer.parseInt(num[0]) - auxMargen).getData()).getNumero()).split("\\.");
                            //if(auxNum[0]!=null&&Integer.parseInt(num[0])>Integer.parseInt(auxNum[0])){
                            if (num[0].equals(auxNum2[0])) {

                            } else {
                                auxMargen--;
                            }

                        }
                        System.out.println("***Unidad Padre" + ((NodoMultiClass) root.getChildren().get(Integer.parseInt(num[0]) - auxMargen).getData()).getNombre());
                        NodoMultiClass auxNodo = ((NodoMultiClass) root.getChildren().get(Integer.parseInt(num[0]) - auxMargen).getData());
                        //UTIL PARA PONER TEXTO TACHADO
//                     Chunk strikethrough = new Chunk("Strikethrough.");
//                      strikethrough.setUnderline(0.1f, 3f); //0.1 thick, 2 y-location
//                     document.add(strikethrough);
                        pdfTable.addCell(new Phrase(auxNodo.getNumero() + ".- " + auxNodo.getNombre(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, new Color(154, 151, 151))));
                        pdfTable.addCell(new Phrase(auxNodo.getPorcentajeAvance(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, new Color(154, 151, 151))));
                        pdfTable.addCell(auxNodo.getObservaciones());

                    }
                } else {
                    if (auxNum2[0] == null && num.length >= 2) {
                        auxNum2 = (((NodoMultiClass) root.getChildren().get(Integer.parseInt(num[0]) - auxMargen).getData()).getNumero()).split("\\.");
                        while (!num[0].equals(auxNum2[0]) && auxMargen > 0) {
                            System.out.println("XXXXXXIteracion  para encontrar padre XXXXXXX 0" + auxMargen);
                            auxNum2 = (((NodoMultiClass) root.getChildren().get(Integer.parseInt(num[0]) - auxMargen).getData()).getNumero()).split("\\.");
                            //if(auxNum[0]!=null&&Integer.parseInt(num[0])>Integer.parseInt(auxNum[0])){
                            if (num[0].equals(auxNum2[0])) {

                            } else {
                                auxMargen--;
                            }
                        }
                        System.out.println("***Unidad Padre" + ((NodoMultiClass) root.getChildren().get(Integer.parseInt(num[0]) - auxMargen).getData()).getNombre());
                        NodoMultiClass auxNodo = ((NodoMultiClass) root.getChildren().get(Integer.parseInt(num[0]) - auxMargen).getData());
                        //UTIL PARA PONER TEXTO TACHADO
//                     Chunk strikethrough = new Chunk("Strikethrough.");
//                      strikethrough.setUnderline(0.1f, 3f); //0.1 thick, 2 y-location
//                     document.add(strikethrough);
                        pdfTable.addCell(new Phrase(auxNodo.getNumero() + ".- " + auxNodo.getNombre(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, new Color(154, 151, 151))));
                        pdfTable.addCell(new Phrase(auxNodo.getPorcentajeAvance(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, new Color(154, 151, 151))));
                        pdfTable.addCell(auxNodo.getObservaciones());

                    }
                }

                //AGREGAR A SUBTEMA
                String[] auxNumeroUnidad = new String[4];
                int auxMargen2 = 1;
                if (num.length == 3 && auxNum[1] != null && !auxNum[1].equals(num[1])) {
                    if (!num[2].equals("0")) {
                        while (!num[0].equals(auxNum[0]) && auxMargen >= 0) {
                            NodoMultiClass auxNodoUnidad = ((NodoMultiClass) root.getChildren().get(Integer.parseInt(num[0]) - auxMargen).getData());
                            auxNumeroUnidad = auxNodoUnidad.getNumero().split("\\.");
                            tamanoArbol = root.getChildren().get(Integer.parseInt(num[0])).getChildCount();
                            if (Integer.parseInt(num[1]) > tamanoArbol) {
                                auxMargen2 = Integer.parseInt(num[1]) - tamanoArbol;
                            }

                            while (auxNumeroUnidad[0].equals(num[0]) && !num[1].equals(auxNum[1]) && auxMargen > 0) {
                                System.out.println("XXXXXXIteracion  para encontrar padre XXXXXXX 0" + auxMargen);
                                auxNum = (((NodoMultiClass) root.getChildren().get(Integer.parseInt(num[0]) - auxMargen).getChildren().get(Integer.parseInt(num[1]) - auxMargen2).getData()).getNumero()).split("\\.");
                                //if(auxNum[0]!=null&&Integer.parseInt(num[0])>Integer.parseInt(auxNum[0])){
                                if (num[1].equals(auxNum[1])) {

                                } else {
                                    auxMargen2--;
                                }

                            }
//                            auxNum = (((NodoMultiClass) root.getChildren().get(Integer.parseInt(num[1]) - auxMargen).getData()).getNumero()).split("\\.");
                            if (num[0].equals(auxNum[0])) {
                            } else {
                                auxMargen--;
                            }
                        }

                        System.out.println("*****Unidad Padre(SUBTEMA)" + ((NodoMultiClass) root.getChildren().get(Integer.parseInt(num[0]) - auxMargen).getChildren().get(Integer.parseInt(num[1]) - auxMargen2).getData()).getNombre());
                        NodoMultiClass auxNodo = ((NodoMultiClass) root.getChildren().get(Integer.parseInt(num[0]) - auxMargen).getChildren().get(Integer.parseInt(num[1]) - auxMargen2).getData());
                        //UTIL PARA PONER TEXTO TACHADO
//                     Chunk strikethrough = new Chunk("Strikethrough.");
//                      strikethrough.setUnderline(0.1f, 3f); //0.1 thick, 2 y-location
//                     document.add(strikethrough);
                        pdfTable.addCell(new Phrase("   " + auxNodo.getNumero() + ".- " + auxNodo.getNombre(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, new Color(154, 151, 151))));
                        pdfTable.addCell(new Phrase(auxNodo.getPorcentajeAvance(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, new Color(154, 151, 151))));
                        pdfTable.addCell(auxNodo.getObservaciones());

                    }
                } else {
                    if (auxNum[0] == null && num.length == 3) {
                        auxNum = (((NodoMultiClass) root.getChildren().get(Integer.parseInt(num[0]) - auxMargen).getData()).getNumero()).split("\\.");
                        while (!num[0].equals(auxNum[0]) && auxMargen > 0) {
                            NodoMultiClass auxNodoUnidad = ((NodoMultiClass) root.getChildren().get(Integer.parseInt(num[0]) - auxMargen).getData());
                            auxNumeroUnidad = auxNodoUnidad.getNumero().split("\\.");
                            tamanoArbol = root.getChildren().get(Integer.parseInt(num[0])).getChildCount();
                            if (Integer.parseInt(num[1]) > tamanoArbol) {
                                auxMargen2 = Integer.parseInt(num[1]) - tamanoArbol;
                            }
                            while (auxNumeroUnidad[0].equals(num[0]) && !num[1].equals(auxNum[1]) && auxMargen > 0) {
                                System.out.println("Iteracion  para encontrar padre XXXXXXX 0" + auxMargen);
                                auxNum = (((NodoMultiClass) root.getChildren().get(Integer.parseInt(num[0]) - auxMargen).getChildren().get(Integer.parseInt(num[1]) - auxMargen2).getData()).getNumero()).split("\\.");
                                //if(auxNum[0]!=null&&Integer.parseInt(num[0])>Integer.parseInt(auxNum[0])){
                                if (num[1].equals(auxNum[1])) {

                                } else {
                                    auxMargen2--;
                                }
                            }
//                            auxNum = (((NodoMultiClass) root.getChildren().get(Integer.parseInt(num[1]) - auxMargen).getData()).getNumero()).split("\\.");
                            if (num[0].equals(auxNum[0])) {
                            } else {
                                auxMargen--;
                            }
                        }

                        System.out.println("***Unidad Padre(SUBTEMA)" + ((NodoMultiClass) root.getChildren().get(Integer.parseInt(num[0]) - auxMargen).getChildren().get(Integer.parseInt(num[1]) - auxMargen2).getData()).getNombre());
                        NodoMultiClass auxNodo = ((NodoMultiClass) root.getChildren().get(Integer.parseInt(num[0]) - auxMargen).getChildren().get(Integer.parseInt(num[1]) - auxMargen2).getData());
                        //UTIL PARA PONER TEXTO TACHADO
//                     Chunk strikethrough = new Chunk("Strikethrough.");
//                      strikethrough.setUnderline(0.1f, 3f); //0.1 thick, 2 y-location
//                     document.add(strikethrough);
                        pdfTable.addCell(new Phrase("   " + auxNodo.getNumero() + ".- " + auxNodo.getNombre(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, new Color(154, 151, 151))));
                        pdfTable.addCell(new Phrase(auxNodo.getPorcentajeAvance(), FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, new Color(154, 151, 151))));
                        pdfTable.addCell(auxNodo.getObservaciones());

                    }
                }

                auxNum = num;
                //FIN AGREGAR PADRE DE TEMA/SUBTEMA 
                boolean banpor = true;
                if (num.length == 1) {
                    pdfTable.addCell(new Phrase(filas[0] + ".- " + filas[1], FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, new Color(0, 0, 0))));
                    pdfTable.addCell(new Phrase(String.valueOf(dosDecimales(Float.parseFloat(filas[2]))), FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, new Color(0, 0, 0))));
                    banpor = false;
                }
                if (num.length == 2) {
                    pdfTable.addCell(new Phrase("   " + filas[0] + ".- " + filas[1]));
                }
                if (num.length == 3) {
                    pdfTable.addCell(new Phrase("       " + filas[0] + ".- " + filas[1], FontFactory.getFont(FontFactory.TIMES_ITALIC, 12)));
                    pdfTable.addCell(new Phrase(String.valueOf(dosDecimales(Float.parseFloat(filas[2]))), FontFactory.getFont(FontFactory.TIMES_ITALIC, 12)));
                    banpor = false;
                }
                if (banpor) {
                    pdfTable.addCell(String.valueOf(dosDecimales(Float.parseFloat(filas[2]))));
                    pdfTable.addCell(filas[3]);
                } else {
                    pdfTable.addCell(filas[3]);
                }
            }

        } else {
            pdfTable.addCell("No hay nada seleccionado");
            pdfTable.addCell("");
            pdfTable.addCell("");
        }

        pdfTable.setHorizontalAlignment(15);
        float[] columnWidths2 = new float[]{32f, 8f, 14f};
        pdfTable.setWidths(columnWidths2);
        pdf.add(pdfTable);
        //----------------------------------------------------------------------
    }

    //activar y desactivar el boton
    public boolean isDisable() {
        return disable;
    }

    public void onSelectUA() {
        setEnable2(false);
        fillTree();
    }

    public void onSelectPE() {

        fillTree();
        enviado = true;
        setEnable(false);
    }

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

        if (String.valueOf((int) minutos).length() < 2) {
            formatoDeHora = String.valueOf(valorEntero + ":0" + formato.format(minutos));
        } else {
            formatoDeHora = String.valueOf(valorEntero + ":" + formato.format(minutos));
        }
        return formatoDeHora;
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

    public boolean enviado = false;

    public void validarEnviado() {
        if (ractBeanHelper.validarReporteEnviado(profesor.getProid(), Integer.parseInt(grupo), subGrupo, Integer.parseInt(claveUnidadAprendizajeSeleccionada))) {
            System.out.println("AGREGANDO MENSAJE DE ENVIADOS^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            FacesContext.getCurrentInstance().addMessage("validarEnviado", new FacesMessage(FacesMessage.SEVERITY_WARN, "El reporte ya ha sido enviado", ""));
            enviado = true;
            disable2 = true;
            RequestContext.getCurrentInstance().update("formId:message");
        } else {
            enviado = false;
            System.out.println("NO AGREGAR MENSAJE DE ENVIADOS^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            RequestContext.getCurrentInstance().update("formId:message");
        }
        //Los nodos se deshabilitan si el reporte ya esta enviado
        if (enviado) {
            for (TreeNode nodo : root.getChildren()) {
                nodo.setSelectable(false);
                for (TreeNode nodo2 : nodo.getChildren()) {
                    nodo2.setSelectable(false);
                    for (TreeNode nodo3 : nodo2.getChildren()) {
                        nodo3.setSelectable(false);
                    }
                }
            }
        }
    }

    public String unselectOnEnviado() {
        if (enviado) {
            return "";
        } else {
            return "checkbox";
        }
    }

    //trunca un valor a dos decimales 
    public float dosDecimales(float dosDecimales) {
        float convertido = 0;
        try {
            DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
            simbolo.setDecimalSeparator('.');
            DecimalFormat formato = new DecimalFormat("####.##", simbolo);
            convertido = Float.parseFloat(formato.format(dosDecimales));
        } catch (Exception e) {

        }
        return convertido;
    }

    //returna none para usar en selectioMode
    public String selectableToImage(NodoMultiClass nodo) {
//        if (selectedNodesInitial != null) {
//            for (TreeNode seleccion : selectedNodesInitial) {
//                if (nodo.getTipo().equals(((NodoMultiClass) seleccion.getData()).getTipo()) && nodo.getId().equals(((NodoMultiClass) seleccion.getData()).getId())) {
//                    return "true";
//                }
//            }
//        }
//        return "false";
       if(nodo.isDeReporteAnterior()){
           return "true";
       }else{
           if((nodo.isImpartido()&&enviado)){
               return "true";
           }
           return "false";
       }
    }
}
