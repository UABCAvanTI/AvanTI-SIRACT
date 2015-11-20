/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import mx.avanti.siract.business.AreaConocimientoDelegate;
import mx.avanti.siract.business.CalendarioreporteDelegate;
import mx.avanti.siract.business.CalendarioreporteTieneAlertaDelegate;
import mx.avanti.siract.business.CicloEscolarDelegate;
import mx.avanti.siract.business.DelegateConfiguracionSistema;
import mx.avanti.siract.business.GrupoDelegate;
import mx.avanti.siract.business.PracticasCampoDelegate;
import mx.avanti.siract.business.PracticasLaboratorioDelegate;
import mx.avanti.siract.business.PracticasTallerDelegate;
import mx.avanti.siract.business.ProfesorDelegate;
import mx.avanti.siract.business.ProgramaEducativoDelegate;
import mx.avanti.siract.business.ReporteDelegate;
import mx.avanti.siract.business.ReporteavancecontenidotematicoDelegate;
import mx.avanti.siract.business.SubtemaunidadDelegate;
import mx.avanti.siract.business.TemaunidadDelegate;
import mx.avanti.siract.business.UnidadAprendizajeDelegate;
import mx.avanti.siract.business.UnidadDelegate;
import mx.avanti.siract.business.UnidadaprendizajeImparteProfesorDelegate;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Calendarioreporte;
import mx.avanti.siract.business.entity.CalendarioreporteTieneAlerta;
import mx.avanti.siract.business.entity.Cicloescolar;
import mx.avanti.siract.business.entity.Configuracion;
import mx.avanti.siract.business.entity.Grupo;
import mx.avanti.siract.business.entity.Practicalaboratorio;
import mx.avanti.siract.business.entity.Practicascampo;
import mx.avanti.siract.business.entity.Practicataller;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Reporte;
import mx.avanti.siract.business.entity.ReporteId;
import mx.avanti.siract.business.entity.Reporteavancecontenidotematico;
import mx.avanti.siract.business.entity.Subtemaunidad;
import mx.avanti.siract.business.entity.Temaunidad;
import mx.avanti.siract.business.entity.Unidad;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.business.entity.UnidadaprendizajeImparteProfesor;
import mx.avanti.siract.business.entity.Usuario;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Y
 */
public class RACTBeanHelper {

    //Aqui se accede a los delegate
    UnidadDelegate unidadDelegate;
    PracticasLaboratorioDelegate practicasLabDelegate;
    PracticasTallerDelegate practicasTallDelegate;
    ProfesorDelegate profesorDelegate;
    UnidadAprendizajeDelegate unidadAprendisajeDelegate;
    GrupoDelegate grupoDelegate;
    List<Unidadaprendizaje> listaUnidadesAprendisaje;
    List<Unidad> unidades;
    List<Practicalaboratorio> practicasLab;
    List<Practicataller> practicasTall;
    List<Practicascampo> practicasCampo;
    List<Profesor> profesores;
    List<Grupo> grupos;
    List<Programaeducativo> programaesEducativos;
    TreeNode root;
    private AreaConocimientoDelegate areaConocimientoDelegate;
    private Areaconocimiento areaConocimiento;
    private List<Areaconocimiento> areasConocimiento;
    Profesor profesor;
    ReporteavancecontenidotematicoDelegate reporteavancecontenidotematicodelegate;
    Reporteavancecontenidotematico auxReporteAvancecontenidotematico;
    Reporte auxReporte;
    String numeroReporte;
    boolean validarTiempo = true;
    CalendarioreporteDelegate calendarioreporteDelegate;
    CalendarioreporteTieneAlertaDelegate calendarioreporteTieneAlertaDelegate;
    List<TreeNode> listaSeleccionados;
    PracticasCampoDelegate practicasCampoDelegate;
    List<NodoMultiClass> listaPDF = new ArrayList();

    NodoMultiClass nodoM = new NodoMultiClass(); //auxiliar que se utiliza para marcar 
    CicloEscolarDelegate cicloescolarDelegate = new CicloEscolarDelegate();

    public Configuracion configuracionDeCicloEscolar(int id) {
        DelegateConfiguracionSistema configuracionDelegate = new DelegateConfiguracionSistema();
        return configuracionDelegate.buscarConfiguracion(id);
    }

    public Cicloescolar cicloescolarActual() {
        return cicloescolarDelegate.cicloescolarActual();
    }

    public List<NodoMultiClass> getListaPDF() {
        return listaPDF;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public void setListaPDF(List<NodoMultiClass> listaPDF) {
        this.listaPDF = listaPDF;
    }

    public List<TreeNode> getListaSeleccionados() {
        return listaSeleccionados;
    }

    public void setListaSeleccionados(List<TreeNode> listaSeleccionados) {
        this.listaSeleccionados = listaSeleccionados;
    }

    public RACTBeanHelper() {
        init();

    }

    private void init() {
        root = new DefaultTreeNode("raiz", null);
        profesorDelegate = new ProfesorDelegate();
        unidadDelegate = new UnidadDelegate();
        grupoDelegate = new GrupoDelegate();
        areaConocimientoDelegate = new AreaConocimientoDelegate();
        unidadAprendisajeDelegate = new UnidadAprendizajeDelegate();
        programaEducativoDelegate = new ProgramaEducativoDelegate();
        practicasLabDelegate = new PracticasLaboratorioDelegate();
        practicasTallDelegate = new PracticasTallerDelegate();
        calendarioreporteDelegate = new CalendarioreporteDelegate();
        calendarioreporteTieneAlertaDelegate = new CalendarioreporteTieneAlertaDelegate();
        listaSeleccionados = new ArrayList<TreeNode>();
        practicasCampoDelegate = new PracticasCampoDelegate();
        reporteavancecontenidotematicodelegate = new ReporteavancecontenidotematicoDelegate();
    }

    public List<Programaeducativo> getProgramaesEducativos() {
        if (profesor != null && profesor.getProid() != null) {
            programaesEducativos = programaEducativoDelegate.getListaUnidadAprendisajeFFWD(null, null, profesor.getProid().toString());
        }
        return programaesEducativos;
    }

    public void setProgramaesEducativos(List<Programaeducativo> programaesEducativos) {
        this.programaesEducativos = programaesEducativos;
    }
    ProgramaEducativoDelegate programaEducativoDelegate;

    public List<Grupo> getGrupos(String unidadAprendisajeSeleccionada) {
        grupos = grupoDelegate.buscarGrupos("unidadaprendizajeImparteProfesors", "unidadaprendizaje.uapclave", unidadAprendisajeSeleccionada);
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public Areaconocimiento buscarAreaConocimiento(Areaconocimiento area) {
        areaConocimiento = areaConocimientoDelegate.buscarAreaConocimiento(areaConocimiento);
        return areaConocimiento;
    }

    //Utilizado en pruebas para conseguir una lista de profesores
//    public List<Profesor> getProfesores() {
//        profesores = profesorDelegate.getProfesores();
//        return profesores;
//    }
    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }

    public String buscarGrupo(String unidadAprendisajeImparteProfesor) {
        grupoDelegate.buscarGrupos("unidadaprendizajeImparteProfesors", "unidadaprendizaje.uapclave", unidadAprendisajeImparteProfesor);
        return "";
    }

    public List<Unidadaprendizaje> getUnidadesaprendisaje() {
        listaUnidadesAprendisaje = unidadAprendisajeDelegate.getListaUnidadAprendizaje();
        return listaUnidadesAprendisaje;
    }

    public List<Unidadaprendizaje> getUnidadesaprendisajeConGrupos(String programaEducativoSeleccionado) {
        List<Unidadaprendizaje> nuevaLista = null;
        if (profesor != null && profesor.getProid() != null) {
            nuevaLista = new ArrayList();
            List<Grupo> gruposdeUnidad;
            List<UnidadaprendizajeImparteProfesor> uaips;
            Unidadaprendizaje auxiliar;
            UnidadaprendizajeImparteProfesorDelegate unidadAprendizajeImparteProfesorDelegate = new UnidadaprendizajeImparteProfesorDelegate();
//        UnidadaprendizajeImparteProfesor uaip = new UnidadaprendizajeImparteProfesor();

            listaUnidadesAprendisaje = unidadAprendisajeDelegate.getListaUnidadAprendisajeFFWT("unidadaprendizajeImparteProfesors b join a.areaconocimientos c", "a.profesor ", "proid", profesor.getProid().toString(), "c.planestudio.programaeducativo", "pedid", programaEducativoSeleccionado, "uapnombre");
            //Variable de que depende si la unidad de aprendizaje se muestra o no
            boolean horasCompletas = false;

            for (Iterator<Unidadaprendizaje> it = listaUnidadesAprendisaje.iterator(); it.hasNext();) {
                Unidadaprendizaje unidadaprendizaje = it.next();
                gruposdeUnidad = new ArrayList(grupoDelegate.buscarGrupos("unidadaprendizajeImparteProfesors", "unidadaprendizaje.uapclave", String.valueOf(unidadaprendizaje.getUapclave())));

                //ADAPTACION ESPECIAL DEL QUERY
                uaips = unidadAprendizajeImparteProfesorDelegate.consultaFFW("profesor", "proid", profesor.getProid().toString() + " AND a.unidadaprendizaje.uapid=" + unidadaprendizaje.getUapid(), "uipid");

                for (Iterator<UnidadaprendizajeImparteProfesor> it2 = uaips.iterator(); it2.hasNext();) {
                    UnidadaprendizajeImparteProfesor uaip = it2.next();
                    Grupo grupo = null;
                    do {
                        grupo = grupoDelegate.buscarGrupo(uaip.getGrupo().getGpoid());
                        if (grupo != null) {
                            //LOS VALORES QUE SE MUESTRAN EN LA SELECCION 
                            String nombre = unidadaprendizaje.getUapnombre() + " -- " + grupo.getGponumero() + " -- " + uaip.getUipsubgrupo() + " -- " + uaip.getUiptipoSubgrupo();

                            switch (uaip.getUiptipoSubgrupo()) {
                                case "C":
                                    horasCompletas = unidadaprendizaje.getUaphorasClaseCompletas();
                                    break;
                                case "L":
                                    horasCompletas = unidadaprendizaje.getUaphorasLaboratorioCompletas();
                                    break;
                                case "T":
                                    horasCompletas = unidadaprendizaje.getUaphorasTallerCompletas();
                                    break;
                                case "PC":
                                    horasCompletas = unidadaprendizaje.getUaphorasCampoCompletas();
                                    break;
                            }

                            //CREAR COPIA DE LA UNIDADDEAPRENDISAJE 
                            if (horasCompletas) {
                                auxiliar = new Unidadaprendizaje(unidadaprendizaje.getCicloescolar(),
                                        unidadaprendizaje.getUnidadaprendizaje(),
                                        unidadaprendizaje.getUapclave(),
                                        nombre,
                                        unidadaprendizaje.getUapetapaFormacion(),
                                        unidadaprendizaje.getUapcreditos(),
                                        unidadaprendizaje.getUaphorasClase(),
                                        unidadaprendizaje.getUaphorasLaboratorio(),
                                        unidadaprendizaje.getUaphorasTaller(),
                                        unidadaprendizaje.getUaphorasClinica(),
                                        unidadaprendizaje.getUaphorasCampo(),
                                        unidadaprendizaje.getUaphorasExtraClase(),
                                        unidadaprendizaje.getUaptipoCaracter(), true, true, true, true,
                                        unidadaprendizaje.getUnidadaprendizajeImparteProfesors(),
                                        unidadaprendizaje.getPracticatallers(),
                                        unidadaprendizaje.getPracticascampos(),
                                        unidadaprendizaje.getPracticalaboratorios(),
                                        unidadaprendizaje.getPracticasclinicas(),
                                        unidadaprendizaje.getAreaconocimientos(),
                                        unidadaprendizaje.getUnidadaprendizajes(),
                                        unidadaprendizaje.getCoordinadorareaadministrativas(),
                                        unidadaprendizaje.getUnidads());

                                //SE ASIGNA EL ID DE UNIDAD APRENDIZAJE IMPARTE PROFESOR PARA PODER IDENTIFICAR CADA SET DE PROFESOR/UNIDADAPRENDIZAJE/GRUPO
                                auxiliar.setUapid(uaip.getUipid());

                                //revisar que el tipo que de la unidad de aprendizaje tiene las horas copletas
                                nuevaLista.add(auxiliar);
                            }
                        }
                    } while (grupo == null);
                }

                //FIN DE CREAR COPIA DE LA UNIDADDEAPRENDISAJE
//            }
            }
        }
        return nuevaLista;
    }

    //!!!!!!!!!!!! CAMBIAR IF-ESLSE POR SWITCH-CASE
    public TreeNode getNodos(String seleccionado, String grupo, String subGrupo, String tipo) {
        if (tipo.equals("C")) {
            return traerUnidades("unidadaprendizaje", "uapclave", seleccionado, "uninumero", grupo, subGrupo);
        } else {
            if (tipo.equals("L")) {
                return traerPracticasLaboratorio("unidadaprendizaje", "uapclave", seleccionado, "prlnumero", grupo, subGrupo);
            } else {
                if (tipo.equals("T")) {
                    return traerPracticasTaller("unidadaprendizaje", "uapclave", seleccionado, "prtid", grupo, subGrupo);
                } else {
                    if (tipo.equals("CL")) {
                        return traerPracticasCampo("unidadaprendizaje", "uapclave", seleccionado, "prcid", grupo, subGrupo);
                    }
                }
            }
        }
        return root;
    }

    public TreeNode traerUnidades(String de, String campo, String criterio, String order, String num_grupo, String subGrupo) {
        auxReporteAvancecontenidotematico = null;
        unidades = unidadDelegate.getListaUnidad(de, campo, criterio + " and a.unidadaprendizaje.uaphorasClaseCompletas='1'", order);
        listaSeleccionados = new ArrayList();
        TemaunidadDelegate temaunidaDelegate = new TemaunidadDelegate();
        SubtemaunidadDelegate subtemaunidadDelegate = new SubtemaunidadDelegate();
        TreeNode nivel1 = new DefaultTreeNode();
        TreeNode nivel2;
        TreeNode nivel3;
        List<Temaunidad> temasUnidad;
        List<Subtemaunidad> subtemasTemaUnidad;
        root = new DefaultTreeNode(new NodoMultiClass(null), null);

        listaPDF = new ArrayList();// AGREGADO 1

        //AUXILIARES PARA GUARDAR NODOS PADRE
        int perteneceAUnidad = 0;
        int perteneceATema = 0;
        ReporteDelegate reporteDelegate = new ReporteDelegate();
numeroReporte=obtenerReporteSiguiente();
        
         auxReporteAvancecontenidotematico
                = reporteavancecontenidotematicodelegate.buscarReporteavancecontenidotematicoNumero(Integer.parseInt(criterio),numeroReporte, profesor.getProid(), Integer.parseInt(num_grupo), subGrupo);

        int reporteActual = 0;
        Reporte reporteAuxiliar = new Reporte();
        

        if (auxReporteAvancecontenidotematico != null) {
            reporteActual = auxReporteAvancecontenidotematico.getRacid();
            System.out.println("REPORTE ACTUAL _____________"+reporteActual);
        }        
//        listaPDF.add(nodoM);

        int IdReporteParaConsultarAnteriores=reporteActual;
        if(IdReporteParaConsultarAnteriores==0){
            try{
            IdReporteParaConsultarAnteriores=reporteavancecontenidotematicodelegate.buscarReporteavancecontenidotematico(Integer.parseInt(criterio), profesor.getProid(), Integer.parseInt(num_grupo), subGrupo).getRacid();
            }catch(NullPointerException e){
                System.out.println("CATCH: NULL POINTEREXCEPTION");
            }
        }

        //CREANDO LOS NODOS DEL ARBOL Y TEXTO PARA GENERACION DE PDF
        //NIVEL 1 <---------------
        for (Iterator<Unidad> it = unidades.iterator(); it.hasNext();) {

            Unidad unidad = it.next();
            nivel1 = new DefaultTreeNode(new NodoMultiClass(unidad), root);
            nodoM = new NodoMultiClass(unidad);//<== nuevo

            reporteAuxiliar = reporteDelegate.consultaCheckBox(unidad.getUniid(), "Unidad", reporteActual);
            if (reporteAuxiliar == null) {

            } else {
                if (reporteAuxiliar.getImpartido()) {
                    nodoM.impartido = true;// NUEVO 
                }
                ((NodoMultiClass) nivel1.getData()).setObservaciones(reporteAuxiliar.getRepobservacion());
            }
            listaPDF.add(nodoM);//NUEVO

            perteneceAUnidad = unidad.getUniid();
            temasUnidad = temaunidaDelegate.getListaTemaunidadsFromWhere("unidad", "uniid", unidad.getUniid().toString(), "tunnumero");

            //NIVEL 2 <-----
            for (Iterator<Temaunidad> it1 = temasUnidad.iterator(); it1.hasNext();) {
                Temaunidad temaunidad = it1.next();

                //listaPDF.add(new NodoMultiClass(temaunidad));              
                nivel2 = new DefaultTreeNode(new NodoMultiClass(temaunidad, perteneceAUnidad), nivel1);

                nodoM = new NodoMultiClass(temaunidad);//nuevo

                reporteAuxiliar = reporteDelegate.consultaCheckBox(temaunidad.getTunid(), "Tema", reporteActual);
                if (reporteAuxiliar == null) {
                } else {
                    if (reporteAuxiliar.getImpartido()) {
                        nodoM.impartido = true;//
                    }
                    ((NodoMultiClass) nivel2.getData()).setObservaciones(reporteAuxiliar.getRepobservacion());
                }
                listaPDF.add(nodoM);//
//            listaPDF.add(new NodoMultiClass(temasUnidad));

                perteneceATema = temaunidad.getTunid();
                subtemasTemaUnidad = subtemaunidadDelegate.listaTemasUnidadDe("temaunidad", "tunid", temaunidad.getTunid().toString(), "sutnumero");

                //NIVEL 3 <====================
                for (Iterator<Subtemaunidad> it2 = subtemasTemaUnidad.iterator(); it2.hasNext();) {
                    Subtemaunidad subtemaunidad = it2.next();
                    reporteAuxiliar = reporteDelegate.consultaCheckBox(subtemaunidad.getSutid(), "Subtema", reporteActual);
                    nivel3 = new DefaultTreeNode(new NodoMultiClass(subtemaunidad, perteneceAUnidad, perteneceATema), nivel2);

                    nodoM = new NodoMultiClass(subtemaunidad);//NUEVO

                    listaPDF.add(nodoM);//NUEVO

                    if (reporteAuxiliar == null) {
                        nivel3.setSelected(false);
                    } else {
                        nivel3.setSelected(reporteAuxiliar.getImpartido());
                        if (nivel3.isSelected()) {
                            nivel3.setSelectable(true);
                            ((NodoMultiClass) nivel3.getData()).impartido = true;
                            listaSeleccionados.add(nivel3);
                            nodoM.impartido = true;//NUEVO
                        } else {
                            nivel3.setSelectable(true);
                        }
                        ((NodoMultiClass) nivel3.getData()).setObservaciones(reporteAuxiliar.getRepobservacion());
                    }
                    //Consulta de reportes anteriores
                    UnidadaprendizajeImparteProfesorDelegate unidadAprendizajeImparteProfesorDelegate = new UnidadaprendizajeImparteProfesorDelegate();
                    List<UnidadaprendizajeImparteProfesor> uaips = unidadAprendizajeImparteProfesorDelegate.consultaFFW("reporteavancecontenidotematicos", "racid", String.valueOf(IdReporteParaConsultarAnteriores), "racid");
                    if (uaips != null) {
                        for (UnidadaprendizajeImparteProfesor uaipAuxiliar : uaips) {
                            reporteAuxiliar = reporteDelegate.consultaCheckBoxReportesAnteriores(subtemaunidad.getSutid(), "Subtema", reporteActual, uaipAuxiliar.getUipid());
                            if (reporteAuxiliar == null) {
//                                nivel3.setSelected(false);
                            } else {
                                nivel3.setSelected(reporteAuxiliar.getImpartido());
                                if (nivel3.isSelected()) {
                                    nivel3.setSelectable(false);
                                    ((NodoMultiClass) nivel3.getData()).deReporteAnterior=true;
                                    ((NodoMultiClass) nivel3.getData()).impartido = true;
                                    listaSeleccionados.add(nivel3);
                                }
                                ((NodoMultiClass) nivel3.getData()).setObservaciones(reporteAuxiliar.getRepobservacion());

                            }
                        }
                    }

                //Fin de consulta de anteriores
//            listaPDF.add(new NodoMultiClass(subtemaunidad));
                }

                reporteAuxiliar = reporteDelegate.consultaCheckBox(temaunidad.getTunid(), "Tema", reporteActual);
                if (reporteAuxiliar == null) {
                    nivel2.setSelected(false);
                } else {
                    nivel2.setSelected(reporteAuxiliar.getImpartido());
                    if (nivel2.isSelected()) {
                        nivel2.setSelectable(true);
                        ((NodoMultiClass) nivel2.getData()).impartido = true;
                        listaSeleccionados.add(nivel2);
                    } else {
                        nivel2.setSelectable(true);
                    }
                    ((NodoMultiClass) nivel2.getData()).setObservaciones(reporteAuxiliar.getRepobservacion());

                }

                //Consulta de reportes anteriores
                UnidadaprendizajeImparteProfesorDelegate unidadAprendizajeImparteProfesorDelegate = new UnidadaprendizajeImparteProfesorDelegate();
                List<UnidadaprendizajeImparteProfesor> uaips = unidadAprendizajeImparteProfesorDelegate.consultaFFW("reporteavancecontenidotematicos", "racid", String.valueOf(IdReporteParaConsultarAnteriores), "racid");
                if (uaips != null) {

                    for (UnidadaprendizajeImparteProfesor uaipAuxiliar : uaips) {
                        reporteAuxiliar = reporteDelegate.consultaCheckBoxReportesAnteriores(temaunidad.getTunid(), "Tema", reporteActual, uaipAuxiliar.getUipid());
                        if (reporteAuxiliar == null) {
//                            nivel2.setSelected(false);
                        } else {
                            nivel2.setSelected(reporteAuxiliar.getImpartido());
                            System.out.println("....................TEMA EN ESTADO: "+reporteAuxiliar.getImpartido());
                            if (nivel2.isSelected()) {
                                nivel2.setSelectable(false);
                                                    System.out.println(".....................SELECCIONADO E IMPARTIDO TEMA" + ((NodoMultiClass) nivel2.getData()).nombre);

                                ((NodoMultiClass) nivel2.getData()).deReporteAnterior=true;
//                                ((NodoMultiClass) nivel2.getData()).impartido = true;
                                listaSeleccionados.add(nivel2);
                            }
                            ((NodoMultiClass) nivel2.getData()).setObservaciones(reporteAuxiliar.getRepobservacion());

                        }
                    }
                }
                //Fin de consulta de anteriores

            }
            reporteAuxiliar = reporteDelegate.consultaCheckBox(unidad.getUniid(), "Unidad", reporteActual);
            if (reporteAuxiliar == null) {
                nivel1.setSelected(false);
            } else {
                nivel1.setSelected(reporteAuxiliar.getImpartido());
                if (nivel1.isSelected()) {
                    System.out.println(".....................SELECCIONADO E IMPARTIDO" + ((NodoMultiClass) nivel1.getData()).nombre);
                    nivel1.setSelectable(true);
                    ((NodoMultiClass) nivel1.getData()).impartido = true;
                    listaSeleccionados.add(nivel1);
                } else {
                    nivel1.setSelectable(true);
                }
                ((NodoMultiClass) nivel1.getData()).setObservaciones(reporteAuxiliar.getRepobservacion());
            }

            //Consulta de reportes anteriores
            UnidadaprendizajeImparteProfesorDelegate unidadAprendizajeImparteProfesorDelegate = new UnidadaprendizajeImparteProfesorDelegate();
            List<UnidadaprendizajeImparteProfesor> uaips = unidadAprendizajeImparteProfesorDelegate.consultaFFW("reporteavancecontenidotematicos", "racid", String.valueOf(IdReporteParaConsultarAnteriores), "racid");
            if (uaips != null) {
                for (UnidadaprendizajeImparteProfesor uaipAuxiliar : uaips) {
                                            System.out.println("___________SE BUSCA UAIP: " + uaipAuxiliar.getUipid());

                    reporteAuxiliar = reporteDelegate.consultaCheckBoxReportesAnteriores(unidad.getUniid(), "Unidad", reporteActual, uaipAuxiliar.getUipid());
                    if (reporteAuxiliar == null) {
                        System.out.println("NO SE ENCONTRO");

//                        nivel1.setSelected(false);
                    } else {
                        nivel1.setSelected(reporteAuxiliar.getImpartido());
                        if (nivel1.isSelected()) {
                            System.out.println("SI SE ENCONTRO");
                            ((NodoMultiClass) nivel1.getData()).deReporteAnterior=true;
                            nivel1.setSelectable(false);
                            System.out.println("Subtema No es seleccionable"+((NodoMultiClass) nivel1.getData()).getNombre());
                            ((NodoMultiClass) nivel1.getData()).impartido = true;
                            listaSeleccionados.add(nivel1);
                        }
                        ((NodoMultiClass) nivel1.getData()).setObservaciones(reporteAuxiliar.getRepobservacion());

                    }
                }
            } else {
                System.out.println("_____________NO HAY ENTRADA EN UAIP");
            }

                //Fin de consulta de anteriores
        }
        return root;

    }

    public Profesor getProfesorFromUsuario(Usuario usuario) {
        Profesor profesor = new Profesor();
        if (usuario != null && usuario.getUsuid() != null) {
            System.out.println("EL USUARIO DE ESTE PROFESOR ES" + usuario.getUsuid());
            profesor = profesorDelegate.findProfesorFromUser(usuario.getUsuid());
            if (profesor == null) {
                System.out.println("No hay profesor");
            }
        } else {
            return null;
        }
        return profesor;
    }

    public TreeNode traerPracticasTaller(String de, String campo, String criterio, String order, String num_grupo, String subGrupo) {
        auxReporteAvancecontenidotematico = null;
        practicasTall = practicasTallDelegate.getListaPracticasTall(de, campo, criterio + " and a.unidadaprendizaje.uaphorasTallerCompletas='1'", order);
        listaSeleccionados = new ArrayList<TreeNode>();
        TreeNode nivel1 = new DefaultTreeNode();

        listaPDF = new ArrayList();//AGREGADO      

        List<Practicataller> practicaTallL;

        root = new DefaultTreeNode(new NodoMultiClass(null), null);

        ReporteDelegate reporteDelegate = new ReporteDelegate();
numeroReporte=obtenerReporteSiguiente();
        
         auxReporteAvancecontenidotematico
                = reporteavancecontenidotematicodelegate.buscarReporteavancecontenidotematicoNumero(Integer.parseInt(criterio),numeroReporte, profesor.getProid(), Integer.parseInt(num_grupo), subGrupo);

        int reporteActual = 0;
        Reporte reporteAuxiliar = new Reporte();
        

        if (auxReporteAvancecontenidotematico != null) {
            reporteActual = auxReporteAvancecontenidotematico.getRacid();
            System.out.println("REPORTE ACTUAL _____________"+reporteActual);
        }        
        listaPDF.add(nodoM);

        int IdReporteParaConsultarAnteriores=reporteActual;
        if(IdReporteParaConsultarAnteriores==0){
            try{
            IdReporteParaConsultarAnteriores=reporteavancecontenidotematicodelegate.buscarReporteavancecontenidotematico(Integer.parseInt(criterio), profesor.getProid(), Integer.parseInt(num_grupo), subGrupo).getRacid();
            }catch(NullPointerException e){
                System.out.println("CATCH: NULL POINTEREXCEPTION");
            }
        }

        //NIVEL 1
        for (Iterator<Practicataller> it = practicasTall.iterator(); it.hasNext();) {
            Practicataller practica = it.next();

            //listaPDF.add(new NodoMultiClass(practica));//AGRAGADO<============
            nodoM = new NodoMultiClass(practica);//<===

            nivel1 = new DefaultTreeNode(new NodoMultiClass(practica), root);

            reporteAuxiliar = reporteDelegate.consultaCheckBox(practica.getPrtid(), "practicaTaller", reporteActual);
            if (reporteAuxiliar == null) {

            } else {
                if (reporteAuxiliar.getImpartido()) {
                    nodoM.impartido = true;//<-----
                }
                ((NodoMultiClass) nivel1.getData()).setObservaciones(reporteAuxiliar.getRepobservacion());
            }

            reporteAuxiliar = reporteDelegate.consultaCheckBox(practica.getPrtid(), "practicaTaller", reporteActual);
            if (reporteAuxiliar == null) {
                nivel1.setSelected(false);
            } else {
                nivel1.setSelected(reporteAuxiliar.getImpartido());
                if (nivel1.isSelected()) {
                    nivel1.setSelectable(true);
                    ((NodoMultiClass) nivel1.getData()).impartido = true;
                    listaSeleccionados.add(nivel1);

                }
                ((NodoMultiClass) nivel1.getData()).setObservaciones(reporteAuxiliar.getRepobservacion());

            }

            //Consulta de reportes anteriores
            UnidadaprendizajeImparteProfesorDelegate unidadAprendizajeImparteProfesorDelegate = new UnidadaprendizajeImparteProfesorDelegate();
            List<UnidadaprendizajeImparteProfesor> uaips =unidadAprendizajeImparteProfesorDelegate.consultaFFW("reporteavancecontenidotematicos", "racid", String.valueOf(IdReporteParaConsultarAnteriores), "racid");
            if (uaips != null) {
                for (UnidadaprendizajeImparteProfesor uaipAuxiliar : uaips) {
                    reporteAuxiliar = reporteDelegate.consultaCheckBoxReportesAnteriores(practica.getPrtid(), "practicaTaller", reporteActual, uaipAuxiliar.getUipid());
                    if (reporteAuxiliar == null) {
//                        nivel1.setSelected(false);
                    } else {
                        nivel1.setSelected(reporteAuxiliar.getImpartido());
                        if (nivel1.isSelected()) {
                            nivel1.setSelectable(false);
                            ((NodoMultiClass) nivel1.getData()).deReporteAnterior=true;
                            ((NodoMultiClass) nivel1.getData()).impartido = true;
                            listaSeleccionados.add(nivel1);
                        }
                        ((NodoMultiClass) nivel1.getData()).setObservaciones(reporteAuxiliar.getRepobservacion());

                    }
                }
            }

                //Fin de consulta de anteriores
        }
        return root;
    }

    public TreeNode traerPracticasCampo(String de, String campo, String criterio, String order, String num_grupo, String subGrupo) {
        auxReporteAvancecontenidotematico = null;
        listaSeleccionados = new ArrayList<TreeNode>();

        practicasCampo = practicasCampoDelegate.getListaPracticasCampo(de, campo, criterio + " and a.unidadaprendizaje.uaphorasCampoCompletas='1'", order);
        TreeNode nivel1 = new DefaultTreeNode();

        List<Practicascampo> practicasCampoL;
        listaPDF = new ArrayList();

        root = new DefaultTreeNode(new NodoMultiClass(null), null);

        ReporteDelegate reporteDelegate = new ReporteDelegate();
numeroReporte=obtenerReporteSiguiente();
        
         auxReporteAvancecontenidotematico
                = reporteavancecontenidotematicodelegate.buscarReporteavancecontenidotematicoNumero(Integer.parseInt(criterio),numeroReporte, profesor.getProid(), Integer.parseInt(num_grupo), subGrupo);

        int reporteActual = 0;
        Reporte reporteAuxiliar = new Reporte();
        

        if (auxReporteAvancecontenidotematico != null) {
            reporteActual = auxReporteAvancecontenidotematico.getRacid();
            System.out.println("REPORTE ACTUAL _____________"+reporteActual);
        }        
        
        listaPDF.add(nodoM);

        int IdReporteParaConsultarAnteriores=reporteActual;
        if(IdReporteParaConsultarAnteriores==0){
            try{
            IdReporteParaConsultarAnteriores=reporteavancecontenidotematicodelegate.buscarReporteavancecontenidotematico(Integer.parseInt(criterio), profesor.getProid(), Integer.parseInt(num_grupo), subGrupo).getRacid();
            }catch(NullPointerException e){
                System.out.println("CATCH: NULL POINTEREXCEPTION");
            }
        }

        for (Iterator<Practicascampo> it = practicasCampo.iterator(); it.hasNext();) {
            Practicascampo practica = it.next();
            nivel1 = new DefaultTreeNode(new NodoMultiClass(practica), root);

            //listaPDF.add(new NodoMultiClass(practica));
            nodoM = new NodoMultiClass(practica);

            reporteAuxiliar = reporteDelegate.consultaCheckBox(practica.getPrcid(), "PracticaCampo", reporteActual);
            if (reporteAuxiliar == null) {

            } else {
                if (reporteAuxiliar.getImpartido()) {
                    nodoM.impartido = true;
                }
                ((NodoMultiClass) nivel1.getData()).setObservaciones(reporteAuxiliar.getRepobservacion());
            }

            reporteAuxiliar = reporteDelegate.consultaCheckBox(practica.getPrcid(), "PracticaCampo", reporteActual);
            if (reporteAuxiliar == null) {
                nivel1.setSelected(false);
            } else {
                nivel1.setSelected(reporteAuxiliar.getImpartido());
                if (nivel1.isSelected()) {
                    nivel1.setSelectable(true);
                    ((NodoMultiClass) nivel1.getData()).impartido = true;
                    listaSeleccionados.add(nivel1);
                }
                ((NodoMultiClass) nivel1.getData()).setObservaciones(reporteAuxiliar.getRepobservacion());

            }

            //Consulta de reportes anteriores
            UnidadaprendizajeImparteProfesorDelegate unidadAprendizajeImparteProfesorDelegate = new UnidadaprendizajeImparteProfesorDelegate();
            List<UnidadaprendizajeImparteProfesor> uaips = unidadAprendizajeImparteProfesorDelegate.consultaFFW("reporteavancecontenidotematicos", "racid", String.valueOf(IdReporteParaConsultarAnteriores), "racid");
            if (uaips != null) {
                for (UnidadaprendizajeImparteProfesor uaipAuxiliar : uaips) {
                    reporteAuxiliar = reporteDelegate.consultaCheckBoxReportesAnteriores(practica.getPrcid(), "PracticaCampo", reporteActual, uaipAuxiliar.getUipid());
                    if (reporteAuxiliar == null) {
//                        nivel1.setSelected(false);
                    } else {
                        nivel1.setSelected(reporteAuxiliar.getImpartido());
                        if (nivel1.isSelected()) {
                            nivel1.setSelectable(false);
                            ((NodoMultiClass) nivel1.getData()).deReporteAnterior=true;
                            ((NodoMultiClass) nivel1.getData()).impartido = true;
                            listaSeleccionados.add(nivel1);
                        }
                        ((NodoMultiClass) nivel1.getData()).setObservaciones(reporteAuxiliar.getRepobservacion());

                    }
                }
            }

                //Fin de consulta de anteriores
        }
        return root;
    }

    public TreeNode traerPracticasLaboratorio(String de, String campo, String criterio, String order, String num_grupo, String subGrupo) {
        auxReporteAvancecontenidotematico = null;
        practicasLab = practicasLabDelegate.getListaPracticasLab(de, campo, criterio + " and a.unidadaprendizaje.uaphorasLaboratorioCompletas='1'", order);
        listaSeleccionados = new ArrayList<TreeNode>();

        TreeNode nivel1 = new DefaultTreeNode();

        List<Practicalaboratorio> practicaLabL;

        listaPDF = new ArrayList();

        root = new DefaultTreeNode(new NodoMultiClass(null), null);

        ReporteDelegate reporteDelegate = new ReporteDelegate();
        numeroReporte=obtenerReporteSiguiente();
        
         auxReporteAvancecontenidotematico
                = reporteavancecontenidotematicodelegate.buscarReporteavancecontenidotematicoNumero(Integer.parseInt(criterio),numeroReporte, profesor.getProid(), Integer.parseInt(num_grupo), subGrupo);

        int reporteActual = 0;
        Reporte reporteAuxiliar = new Reporte();
        
        listaPDF.add(nodoM);

        if (auxReporteAvancecontenidotematico != null) {
            reporteActual = auxReporteAvancecontenidotematico.getRacid();
            System.out.println("REPORTE ACTUAL _____________"+reporteActual);
        }
        int IdReporteParaConsultarAnteriores=reporteActual;
        if(IdReporteParaConsultarAnteriores==0){
            try{
            IdReporteParaConsultarAnteriores=reporteavancecontenidotematicodelegate.buscarReporteavancecontenidotematico(Integer.parseInt(criterio), profesor.getProid(), Integer.parseInt(num_grupo), subGrupo).getRacid();
            }catch(NullPointerException e){
                System.out.println("CATCH: NULL POINTEREXCEPTION");
            }
        }
        for (Iterator<Practicalaboratorio> it = practicasLab.iterator(); it.hasNext();) {
            Practicalaboratorio practica = it.next();
            nivel1 = new DefaultTreeNode(new NodoMultiClass(practica), root);

            //listaPDF.add(new NodoMultiClass(practica));
            nodoM = new NodoMultiClass(practica);//Agregado

            reporteAuxiliar = reporteDelegate.consultaCheckBox(practica.getPrlid(), "practicaLaboratorio", reporteActual);
            if (reporteAuxiliar == null) {

            } else {
                if (reporteAuxiliar.getImpartido()) {
                    nodoM.impartido = true;
                }
                ((NodoMultiClass) nivel1.getData()).setObservaciones(reporteAuxiliar.getRepobservacion());
            }

            reporteAuxiliar = reporteDelegate.consultaCheckBox(practica.getPrlid(), "practicaLaboratorio", reporteActual);
            if (reporteAuxiliar == null) {
                nivel1.setSelected(false);
            } else {
                nivel1.setSelected(reporteAuxiliar.getImpartido());
                if (nivel1.isSelected()) {
                    nivel1.setSelectable(true);
                    ((NodoMultiClass) nivel1.getData()).impartido = true;
                    listaSeleccionados.add(nivel1);
                }
                ((NodoMultiClass) nivel1.getData()).setObservaciones(reporteAuxiliar.getRepobservacion());

            }

            //Consulta de reportes anteriores

            UnidadaprendizajeImparteProfesorDelegate unidadAprendizajeImparteProfesorDelegate = new UnidadaprendizajeImparteProfesorDelegate();
            List<UnidadaprendizajeImparteProfesor> uaips = unidadAprendizajeImparteProfesorDelegate.consultaFFW("reporteavancecontenidotematicos", "racid", String.valueOf(IdReporteParaConsultarAnteriores), "racid");
            if (uaips != null) {
                System.out.println("SE ENCONTRARON "+uaips.size()+" UNIDAD _A_I_P para el reporte"+reporteActual);
                for (UnidadaprendizajeImparteProfesor uaipAuxiliar : uaips) {
                                System.out.println("INICIANDO CONSULTA DE REPORTES ANTERIORES____________");

                    reporteAuxiliar = reporteDelegate.consultaCheckBoxReportesAnteriores(practica.getPrlid(), "practicaLaboratorio", reporteActual, uaipAuxiliar.getUipid());
                    if (reporteAuxiliar == null) {
                       System.out.println("PRACTICA LAB: ES NULL");
//                        nivel1.setSelected(false);
                    } else {
                        nivel1.setSelected(reporteAuxiliar.getImpartido());
                        System.out.println("PRACTICA LAB: "+((NodoMultiClass) nivel1.getData()).getNombre()+" es "+reporteAuxiliar.getImpartido());
                        if (nivel1.isSelected()) {
                            nivel1.setSelectable(false);
                            System.out.println("PRACTICA LAB: "+((NodoMultiClass) nivel1.getData()).getNombre()+" Fue impartida");
                            ((NodoMultiClass) nivel1.getData()).deReporteAnterior=true;
                            ((NodoMultiClass) nivel1.getData()).impartido = true;
                            listaSeleccionados.add(nivel1);
                        }
                        ((NodoMultiClass) nivel1.getData()).setObservaciones(reporteAuxiliar.getRepobservacion());

                    }
                }
            }else{
                System.out.println("NO SE ENCONTRO UAIP!!!!!!!!!!!!!!!!!!![X]");
            }

                //Fin de consulta de anteriores
        }
        return root;
    }
    

    public void obtenerUnidadAprendizajeImparteProfesor(String id_unidadAprendizaje, String id_profesor, String id_grupo) {

    }

    public boolean guardarReporteAvance(TreeNode[] selectedNodes, float porcentajeAvance, int id_profesor, int id_grupo, String subGrupo, String tipo_grupo, int id_unidadAprendizajeImparteProfesor, boolean enviar) {

        if ((Integer.parseInt(obtenerNumeroReporte(id_profesor, id_grupo, id_unidadAprendizajeImparteProfesor, tipo_grupo, subGrupo))) <= 3) {
            ReporteDelegate reporteDelegate = new ReporteDelegate();
            UnidadaprendizajeImparteProfesorDelegate unidadAprendizajeImparteProfesorDelegate = new UnidadaprendizajeImparteProfesorDelegate();
            UnidadaprendizajeImparteProfesor auxUnidadAprendizajeImparteProfesor = new UnidadaprendizajeImparteProfesor();

            Reporteavancecontenidotematico auxReporteAvancecontenidotematico
                    = reporteavancecontenidotematicodelegate.buscarReporteavancecontenidotematico(id_unidadAprendizajeImparteProfesor, id_profesor, id_grupo, subGrupo);

            if (auxReporteAvancecontenidotematico == null) {
                System.out.println("SE CREARA UN NUEVO REPORTE");
                auxUnidadAprendizajeImparteProfesor = unidadAprendizajeImparteProfesorDelegate.buscarUnidadAprendizajeImparteProfesor(id_unidadAprendizajeImparteProfesor, id_profesor, id_grupo, tipo_grupo, subGrupo);
                if (auxUnidadAprendizajeImparteProfesor == null) {
                    System.out.println("---NO SE ENCONTRO REPORTE EN FASE DE CREACION");
                } else {
                    System.out.println("---SE ENCONTRO REPORTE UNIDAD APRENDIZAJE IMPARTE PROFESOR PARA AUXAPRENDIZAJE");
                }

                auxReporteAvancecontenidotematico = new Reporteavancecontenidotematico();
                auxReporteAvancecontenidotematico.setRacid(0);
                auxReporteAvancecontenidotematico = auxReporteAvancecontenidotematico;

                auxReporteAvancecontenidotematico.setUnidadaprendizajeImparteProfesor(auxUnidadAprendizajeImparteProfesor);
//RACID = AUTOINCREMENTABLE

                //SE ENCONTRO UN REPORTE
            } else {
                System.out.println("REPORTE #" + auxReporteAvancecontenidotematico.getRacnumero());

                Reporteavancecontenidotematico reporteInconcluso
                        = reporteavancecontenidotematicodelegate.buscarReporteavancecontenidotematicoIncompleto(id_unidadAprendizajeImparteProfesor, id_profesor, id_grupo);
                if (auxReporteAvancecontenidotematico.getRacstatus().equals("Parcial")) {
                    System.out.println("SE ENCONTRO UN REPORTE SIN TERMINAR");
                } else {
                    System.out.println("SE ENCONTRO UN REPORTE EN ESTADO DE ENVIADO");
                    auxUnidadAprendizajeImparteProfesor = unidadAprendizajeImparteProfesorDelegate.buscarUnidadAprendizajeImparteProfesor(id_unidadAprendizajeImparteProfesor, id_profesor, id_grupo, tipo_grupo, subGrupo);
                    auxReporteAvancecontenidotematico = new Reporteavancecontenidotematico();
                    auxReporteAvancecontenidotematico.setRacid(0);
                    auxReporteAvancecontenidotematico = auxReporteAvancecontenidotematico;
                    auxReporteAvancecontenidotematico.setUnidadaprendizajeImparteProfesor(auxUnidadAprendizajeImparteProfesor);
                }

            }
            auxReporteAvancecontenidotematico.setRacavanceGlobal(porcentajeAvance);
            auxReporteAvancecontenidotematico.setRacfechaElaboracion(new Date());
            auxReporteAvancecontenidotematico.setRacstatus("Parcial");
            auxReporteAvancecontenidotematico.setRacnumero(obtenerReporteEnFecha());
            if (auxReporteAvancecontenidotematico.getRacnumero().equals("0")) {
                auxReporteAvancecontenidotematico.setRacnumero(obtenerReporteSiguiente());
            }

            if (enviar) {
                auxReporteAvancecontenidotematico.setRacstatus("Enviado");
            }

            Calendarioreporte c = calendarioreporteDelegate.calendarioFechaActual();
            if (c == null && enviar) {
                Calendarioreporte calendario = calendarioreporteDelegate.siguienteReporte();

//                auxReporteAvancecontenidotematico.setRacnumero(
//                        obtenerNumeroReporte(id_profesor, id_grupo, id_unidadAprendizajeImparteProfesor, tipo_grupo, subGrupo)
//                );
//                if (auxReporteAvancecontenidotematico.getRacnumero().equals(obtenerReporteSiguiente())) {
                if (calendario != null) {
                    c = calendario;
                    auxReporteAvancecontenidotematico.setRacnumero(obtenerReporteSiguiente());
                } else {
                    System.out.println("YA NO HAY FECHAS PARA ENVIAR REPORTES");
                    return false;
                }
//                } else {
//                    System.out.println("NO SE PUEDE ENVIAR EL REPORTE, No hay calendario para la fecha");
//                    return false;
//                }
            }
            Date fecha = Calendar.getInstance().getTime();
            System.out.println(fecha);
            List<CalendarioreporteTieneAlerta> cal = new ArrayList();

            if (c != null) {
                cal = calendarioreporteTieneAlertaDelegate.getCalendariosFechaActual(String.valueOf(c.getCreid()));
            }

            Reporteavancecontenidotematico auxReporteavanceContenidotematico2
                    = reporteavancecontenidotematicodelegate.buscarReporteavancecontenidotematico(id_unidadAprendizajeImparteProfesor, id_profesor, id_grupo, subGrupo);
            if (enviar) {
                for (CalendarioreporteTieneAlerta cal2 : cal) {
                    System.out.println("NUMERO DEL REPORTE ACTUAL " + cal2.getCalnumeroReporte());

                    if (Integer.parseInt(auxReporteAvancecontenidotematico.getRacnumero()) <= cal2.getCalnumeroReporte()) {

                        if (auxReporteavanceContenidotematico2 == null
                                || !auxReporteavanceContenidotematico2.getRacstatus().equals("Enviado")) {
                            reporteavancecontenidotematicodelegate.agregarReporteavancecontenidotematico(auxReporteAvancecontenidotematico);
                        } else {
                            if (auxReporteavanceContenidotematico2 == null || auxReporteAvancecontenidotematico.getRacnumero().equals(auxReporteavanceContenidotematico2.getRacnumero())) {
                                System.out.println("Conflicto al comprobar datos previos al enviar");
                                return false;
                            } else {
                                reporteavancecontenidotematicodelegate.agregarReporteavancecontenidotematico(auxReporteAvancecontenidotematico);
                            }

                        }
                    } else {
                        System.out.println("NO SE PUEDE ENVIAR EL REPORTE, POR ESTAR FUERA DE TIEMPO");
                        return false;
                    }
                }
            } else {
                //ESTAS LINEA SE AGREGA PARA PODER GUARDAR REPORTES AUN ESTANDO FUERA DE TIEMPO
//                if (auxReporteavanceContenidotematico2 == null ||!auxReporteavanceContenidotematico2.getRacstatus().equals("Enviado")) {
                if (auxReporteavanceContenidotematico2 == null || !obtenerReporteSiguiente().equals(auxReporteavanceContenidotematico2.getRacnumero()) || !auxReporteavanceContenidotematico2.getRacstatus().equals("Enviado")) {
                    reporteavancecontenidotematicodelegate.agregarReporteavancecontenidotematico(auxReporteAvancecontenidotematico);
                } else {
                    System.out.println("Conflicto al comprobar datos previos al enviar");
                    return false;
                }

                //
            }

            do {
                auxReporteAvancecontenidotematico
                        = reporteavancecontenidotematicodelegate.buscarReporteavancecontenidotematico(id_unidadAprendizajeImparteProfesor, id_profesor, id_grupo, subGrupo);
                if (auxReporteAvancecontenidotematico == null) {
                    System.out.println("NO SE ENCONTRO REPORTE DESPUES DE AGREGAR");
                }
            } while (auxReporteAvancecontenidotematico == null);

            Reporteavancecontenidotematico auxReporteA = auxReporteAvancecontenidotematico;

            int racId = auxReporteAvancecontenidotematico.getRacid();

            //Eliminar reportes del reporteDeAvanceActual
            reporteDelegate.eliminaReportesDeReporteDeAvanceContenidoTematico(auxReporteAvancecontenidotematico.getRacid());
            if (selectedNodes != null && selectedNodes.length > 0) {
                List<Reporte> listaReportes = new ArrayList();
                for (TreeNode node : selectedNodes) {
                    if (node != null && node.isSelectable() == true) {
                        auxReporte = new Reporte();
                        auxReporte.setId(new ReporteId());
                        String[] todo = node.getData().toString().split("-//-");
                        if (auxReporte.getImpartido() == null) {
                            auxReporte.setImpartido(true);
                        }

                        auxReporte.setRepobservacion(todo[6].trim());

                        switch (todo[0]) {
                            case "unidad":
                                auxReporte.getId().setUniid(Integer.parseInt(todo[1]));
                                break;
                            case "temaunidad":
                                auxReporte.getId().setTunid(Integer.parseInt(todo[2]));
                                auxReporte.getId().setUniid(Integer.parseInt(todo[1]));
                                break;
                            case "subtemaunidad":
                                auxReporte.getId().setSutid(Integer.parseInt(todo[3]));
                                auxReporte.getId().setTunid(Integer.parseInt(todo[2]));
                                auxReporte.getId().setUniid(Integer.parseInt(todo[1]));
                                break;
                            case "practicalaboratorio":
                                auxReporte.getId().setPrlid(Integer.parseInt(todo[1]));
                                break;
                            case "practicataller":
                                auxReporte.getId().setPrtid(Integer.parseInt(todo[1]));
                                break;
                            case "practicaCampo":
                                auxReporte.getId().setPrcid(Integer.parseInt(todo[1]));
                                break;

                        }
                        auxReporte.getId().setRepid(racId);
                        auxReporte.setReporteavancecontenidotematico(auxReporteA);
                        reporteDelegate.agregarReporte(auxReporte);
                        listaReportes.add(auxReporte);
                    }
                }
                auxReporteA.setReportes(new HashSet(listaReportes));
            }

            return true;
        } else {
            System.out.println("EL REPORTE NO SERA ENVIADO");
            return false;
        }
    }

    public void guardarComentario(TreeNode selectedNodes, float porcentajeAvance, int id_profesor, int id_grupo, String subGrupo, String tipo_grupo, int id_unidadAprendizajeImparteProfesor) {
        ReporteDelegate reporteDelegate = new ReporteDelegate();
        UnidadaprendizajeImparteProfesorDelegate unidadAprendizajeImparteProfesorDelegate = new UnidadaprendizajeImparteProfesorDelegate();
        UnidadaprendizajeImparteProfesor auxUnidadAprendizajeImparteProfesor = new UnidadaprendizajeImparteProfesor();

        auxReporteAvancecontenidotematico
                = reporteavancecontenidotematicodelegate.buscarReporteavancecontenidotematico(id_unidadAprendizajeImparteProfesor, id_profesor, id_grupo, subGrupo);

        if (auxReporteAvancecontenidotematico == null) {
            System.out.println("SE CREARA UN NUEVO REPORTE");

            auxUnidadAprendizajeImparteProfesor = unidadAprendizajeImparteProfesorDelegate.buscarUnidadAprendizajeImparteProfesor(id_unidadAprendizajeImparteProfesor, id_profesor, id_grupo, tipo_grupo, subGrupo);
            if (auxUnidadAprendizajeImparteProfesor == null) {
                System.out.println("---NO SE ENCONTRO REPORTE EN FASE DE CREACION");
            } else {
                System.out.println("---SE ENCONTRO REPORTE UNIDAD APRENDIZAJE IMPARTE PROFESOR PARA AUXAPRENDIZAJE");
            }

            auxReporteAvancecontenidotematico = new Reporteavancecontenidotematico();
            auxReporteAvancecontenidotematico.setRacid(0);
            auxReporteAvancecontenidotematico = auxReporteAvancecontenidotematico;
            if (auxReporteAvancecontenidotematico == null) {
                System.out.println("DESAPARICION MISTERIOSA");
            }

            auxReporteAvancecontenidotematico.setUnidadaprendizajeImparteProfesor(auxUnidadAprendizajeImparteProfesor);
            auxReporteAvancecontenidotematico.setRacnumero(obtenerReporteSiguiente());
            auxReporteAvancecontenidotematico.setRacstatus("Parcial");
            if (Integer.parseInt(auxReporteAvancecontenidotematico.getRacnumero()) <= 3) {
                System.out.println("FIN DE FASE 1");
                auxReporteAvancecontenidotematico.setRacavanceGlobal(0);
                auxReporteAvancecontenidotematico.setRacfechaElaboracion(new Date());
                reporteavancecontenidotematicodelegate.agregarReporteavancecontenidotematico(auxReporteAvancecontenidotematico);
            }

        }

        if (auxReporteAvancecontenidotematico != null) {
        }

        Reporteavancecontenidotematico auxReporteA = auxReporteAvancecontenidotematico;

        int racId = auxReporteAvancecontenidotematico.getRacid();
//        List<UnidadaprendizajeImparteProfesor> listaunidadesaprenizajeimparteprofesors;
        List<Reporte> listaReportes = new ArrayList();

        if (selectedNodes != null) {
            auxReporte = new Reporte();
            auxReporte.setId(new ReporteId());
            String[] todo = selectedNodes.getData().toString().split("-//-");
            System.out.println("-------OBSERVACION!!!" + todo[6]);
            if (auxReporte.getImpartido() == null) {
                System.out.println((((NodoMultiClass) selectedNodes.getData()).isImpartido()));
                auxReporte.setImpartido((((NodoMultiClass) selectedNodes.getData()).isImpartido()));
            }
            auxReporte.setRepobservacion(todo[6]);
            Reporte reporteAuxiliar = new Reporte();
            switch (todo[0]) {
                case "unidad":
                    reporteAuxiliar = reporteDelegate.consultaCheckBox(Integer.parseInt(todo[1]), "Unidad", auxReporteA.getRacid());
                    auxReporte.getId().setUniid(Integer.parseInt(todo[1]));
                    break;
                case "temaunidad":
                    reporteAuxiliar = reporteDelegate.consultaCheckBox(Integer.parseInt(todo[2]), "Tema", auxReporteA.getRacid());

                    auxReporte.getId().setTunid(Integer.parseInt(todo[2]));
                    auxReporte.getId().setUniid(Integer.parseInt(todo[1]));
                    break;
                case "subtemaunidad":
                    reporteAuxiliar = reporteDelegate.consultaCheckBox(Integer.parseInt(todo[3]), "Subtema", auxReporteA.getRacid());

                    auxReporte.getId().setSutid(Integer.parseInt(todo[3]));
                    auxReporte.getId().setTunid(Integer.parseInt(todo[2]));
                    auxReporte.getId().setUniid(Integer.parseInt(todo[1]));
                    break;
                case "practicalaboratorio":
                    reporteAuxiliar = reporteDelegate.consultaCheckBox(Integer.parseInt(todo[1]), "practicaLaboratorio", auxReporteA.getRacid());
                    auxReporte.getId().setPrlid(Integer.parseInt(todo[1]));
                    break;
                case "practicataller":
                    reporteAuxiliar = reporteDelegate.consultaCheckBox(Integer.parseInt(todo[1]), "practicaTaller", auxReporteA.getRacid());
                    auxReporte.getId().setPrtid(Integer.parseInt(todo[1]));
                    break;
            }
            if (reporteAuxiliar == null) {
                auxReporte.setImpartido(false);
            } else {

                auxReporte.setImpartido(reporteAuxiliar.getImpartido());

            }

            auxReporte.getId().setRepid(racId);
            auxReporte.setReporteavancecontenidotematico(auxReporteA);
            reporteDelegate.agregarReporte(auxReporte);
            listaReportes.add(auxReporte);
        }
        auxReporteA.setReportes(new HashSet(listaReportes));
    }

    //Obtener numero de reporte correspondiente a fecha actual
    public String obtenerReporteEnFecha() {
        String numeroReporte = "0";
        Calendarioreporte calendario = calendarioreporteDelegate.calendarioFechaActual();
        if (calendario == null) {
            System.out.println("NO HAY UN NUMERO DE REPORTE PARA LA FECHA ACTUAL");
        } else {
            CalendarioreporteTieneAlerta calendarioReporteAlerta = calendarioreporteTieneAlertaDelegate.getCalendariosFechaActual(calendario.getCreid().toString()).get(0);
            numeroReporte = calendarioReporteAlerta.getCalnumeroReporte().toString();
            System.out.println("EL NUMERO DE REPORTE ES: " + numeroReporte);
        }
        return numeroReporte;
    }

    //Metodo para obtener el siguiente reporte a partir de la fecha actual
    public String obtenerReporteSiguiente() {
        String numeroReporte = "0";
        Calendarioreporte calendario = calendarioreporteDelegate.siguienteReporte();
        if (calendario == null) {
            System.out.println("NO HAY UN NUMERO DE REPORTE PARA LA FECHA ACTUAL");
        } else {
            CalendarioreporteTieneAlerta calendarioReporteAlerta = calendarioreporteTieneAlertaDelegate.getCalendariosFechaActual(calendario.getCreid().toString()).get(0);
            numeroReporte = calendarioReporteAlerta.getCalnumeroReporte().toString();
            System.out.println("EL NUMERO DE REPORTE ES: " + numeroReporte);
        }
        return numeroReporte;
    }

    //METODO PARA OBTENER EL NMERO DEL REPORTE ACTUAL BASADO EN EL ULTIMO REPORTE ENVIADO 
    public String obtenerNumeroReporte(int id_profesor, int id_grupo, int id_unidadAprendizajeImparteProfesor, String tipo, String subGrupo) {
        numeroReporte = "0";
        int auxNumReporte = 1;
        Reporteavancecontenidotematico reporteAuxiliarNumero
                = reporteavancecontenidotematicodelegate.buscarReporteavancecontenidotematicoUltimo(id_unidadAprendizajeImparteProfesor, id_profesor, id_grupo, tipo, subGrupo);
        if (reporteAuxiliarNumero == null) {
            System.out.println("===NO SE ENCONTRO REPORTE");
        } else {
            numeroReporte = reporteAuxiliarNumero.getRacnumero();
            if (reporteAuxiliarNumero.getRacstatus().equals("Enviado")) {
                auxNumReporte = Integer.parseInt(numeroReporte) + 1;
                System.out.println("AUMENTANDO EL NUMERO DE REPORTE");
            }
        }

        numeroReporte = String.valueOf(auxNumReporte);
        System.out.println("NUMERO DEREPORTE " + numeroReporte);
        return numeroReporte;
    }

    public boolean validarReporteEnviado(int id_profesor, int id_grupo, String subGrupo, int id_unidadAprendizajeImparteProfesor) {
        Reporteavancecontenidotematico auxReporteavanceContenidotematico2
                = reporteavancecontenidotematicodelegate.buscarReporteavancecontenidotematico(id_unidadAprendizajeImparteProfesor, id_profesor, id_grupo, subGrupo);

        if (auxReporteavanceContenidotematico2 == null || !obtenerReporteSiguiente().equals(auxReporteavanceContenidotematico2.getRacnumero()) || !auxReporteavanceContenidotematico2.getRacstatus().equals("Enviado")) {
            return false;

        } else {
            System.out.println("Conflicto al comprobar datos previos al enviar");
            return true;
        }
    }

}
