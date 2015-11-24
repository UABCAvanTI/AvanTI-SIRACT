/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import mx.avanti.siract.application.helper.PlanEstudioBeanHelper;
import mx.avanti.siract.application.helper.ProgramaEducativoBeanHelper;
import javax.faces.context.FacesContext;
import mx.avanti.siract.application.helper.AsignarAreaAdministrativaBeanHelper;
import mx.avanti.siract.business.entity.Areaadministrativa;
import org.primefaces.context.RequestContext;
import mx.avanti.siract.business.entity.Grupo;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Coordinadorareaadministrativa;
import mx.avanti.siract.business.entity.CoordinadorareaadministrativaId;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.business.entity.UnidadaprendizajeImparteProfesor;
import mx.avanti.siract.integration.persistence.BaseDAO;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Gig@B COMPUTADORAS
 */
@ManagedBean
@ViewScoped
public class AsignarAreaAdministrativaBeanUI implements Serializable {
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    private AsignarAreaAdministrativaBeanHelper asignarAreaAdministrativaBeanHelper;
    private AsignarAreaAdministrativaBeanHelper AAAHelper;
    private List<Coordinadorareaadministrativa> listaFiltrada;
    private PlanEstudioBeanHelper planEstudioBeanHelper;
    private ProgramaEducativoBeanHelper programaEducativoBeanHelper;

    private List<Programaeducativo> listaPE;
    private List<Unidadaprendizaje> listaUA;
    private List<Areaconocimiento> listaAC;
    private List<Planestudio> listaPlan;
    private List<Profesor> listaProfesor;
    private List<Grupo> listaGrupo;

    public static String header;
    private String deshabilitar;
    private String busqueda = "";
    private String mensajeConfirm;
    private String deshabilitarBoton;
    private String deshabilitarSubgrupo;
    private String mensajeVal;

    boolean mostrarPE=false;
    
    public AsignarAreaAdministrativaBeanUI() {
        init();
    }
    
    @PostConstruct
    public void postConstructor(){
        AAAHelper.setUsuario(loginBean.getLogueado());
        AAAHelper.setRolSeleccionado(loginBean.getSeleccionado());
        System.out.println("rol " + loginBean.getSeleccionado());
        System.out.println("usuario id " + loginBean.getLogueado().getUsuid());
        if(loginBean.getSeleccionado().equalsIgnoreCase("Responsable de Programa Educativo")){
            mostrarPE=true;
        }
    }

    private void init() {
        asignarAreaAdministrativaBeanHelper = new AsignarAreaAdministrativaBeanHelper();
        AAAHelper = new AsignarAreaAdministrativaBeanHelper();
        planEstudioBeanHelper = new PlanEstudioBeanHelper();
        programaEducativoBeanHelper = new ProgramaEducativoBeanHelper();
    }
    /* getters y setters */

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public boolean isMostrarPE() {
        return mostrarPE;
    }

    public void setMostrarPE(boolean mostrarPE) {
        this.mostrarPE = mostrarPE;
    }
    
    public List<Coordinadorareaadministrativa> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Coordinadorareaadministrativa> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public AsignarAreaAdministrativaBeanHelper getAsignarAreaAdministrativaBeanHelper() {
        return asignarAreaAdministrativaBeanHelper;
    }

    public void setAsignarAreaAdministrativaBeanHelper(AsignarAreaAdministrativaBeanHelper asignarAreaAdministrativaBeanHelper) {
        this.asignarAreaAdministrativaBeanHelper = asignarAreaAdministrativaBeanHelper;
    }

    //getters y setter de Marco
    public AsignarAreaAdministrativaBeanHelper getAAAHelper() {
        return AAAHelper;
    }

    public void setAAAPHelper(AsignarAreaAdministrativaBeanHelper AAAHelper) {
        this.AAAHelper = AAAHelper;
    }

    public PlanEstudioBeanHelper getPlanEstudioBeanHelper() {
        return planEstudioBeanHelper;
    }

    public void setPlanEstudioBeanHelper(PlanEstudioBeanHelper planEstudioBeanHelper) {
        this.planEstudioBeanHelper = planEstudioBeanHelper;
    }

    public ProgramaEducativoBeanHelper getProgramaEducativoBeanHelper() {
        return programaEducativoBeanHelper;
    }

    public void setProgramaEducativoBeanHelper(ProgramaEducativoBeanHelper programaEducativoBeanHelper) {
        this.programaEducativoBeanHelper = programaEducativoBeanHelper;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDeshabilitar() {
        return deshabilitar;
    }

    public void setDeshabilitar(String deshabilitar) {
        this.deshabilitar = deshabilitar;
    }

    public String getMensajeConfirm() {
        return mensajeConfirm;
    }

    public void setMensajeConfirm() {
        if (deshabilitar.equals("true") && deshabilitarSubgrupo.equals("true")) {
            mensajeConfirm = "¿Está seguro de eliminar el registro?";
        } else {
            mensajeConfirm = "¿Está seguro de guardar los cambios?";
        }
        RequestContext.getCurrentInstance().update("confirmdlgId");
    }

    public String getDeshabilitarBoton() {
        return deshabilitarBoton;
    }

    public void setDeshabilitarBoton(String deshabilitarBoton) {
        this.deshabilitarBoton = deshabilitarBoton;
    }

    public String getDeshabilitarSubgrupo() {
        return deshabilitarSubgrupo;
    }

    public void setDeshabilitarSubgrupo(String deshabilitarSubgrupo) {
        this.deshabilitarSubgrupo = deshabilitarSubgrupo;
    }

    public String getMensajeVal() {
        return mensajeVal;
    }

    public void setMensajeVal(String mensajeVal) {
        this.mensajeVal = mensajeVal;
    }

    public List<Unidadaprendizaje> getListaUA() {
        return listaUA;
    }

    public List<Programaeducativo> getListaPE() {
        return listaPE;
    }

    public void setListaPE(List<Programaeducativo> listaPE) {
        this.listaPE = listaPE;
    }

    public void setListaUA(List<Unidadaprendizaje> listaUA) {
        this.listaUA = listaUA;
    }

    public List<Profesor> getListaProfesor() {
        return listaProfesor;
    }

    public void setListaProfesor(List<Profesor> listaProfesor) {
        this.listaProfesor = listaProfesor;
    }

    public List<Grupo> getListaGrupo() {
        return listaGrupo;
    }

    public void setListaGrupo(List<Grupo> listaGrupo) {
        this.listaGrupo = listaGrupo;
    }

    public List<Areaconocimiento> getListaAC() {
        return listaAC;
    }

    public void setListaAC(List<Areaconocimiento> listaAC) {
        this.listaAC = listaAC;
    }

    public List<Planestudio> getListaPlan() {
        return listaPlan;
    }

    public void setListaPlan(List<Planestudio> listaPlan) {
        this.listaPlan = listaPlan;
    }


    /* metodos */
    public void nuevo() {
        limpiarSeleccion();
        dlgCabecera(1);
            AAAHelper.setAGUAP(new UnidadaprendizajeImparteProfesor());
            //AAAHelper.setProgramaEducativo(new Programaeducativo());
            AAAHelper.setPlanEstudio(new Planestudio());
            AAAHelper.setAreaConocimiento(new Areaconocimiento());
            AAAHelper.setUnidadApren(new Unidadaprendizaje());
            AAAHelper.setProfesor(new Profesor());
            AAAHelper.setGrupo(new Grupo());
            if(loginBean.getSeleccionado().equalsIgnoreCase("Director")||loginBean.getSeleccionado().equalsIgnoreCase("Subdirector")||loginBean.getSeleccionado().equalsIgnoreCase("Administrador")){
                AAAHelper.setProgramaEducativo(new Programaeducativo());
            }
            cargarPE();
            cargarPlan();
            cargarAC();
            cargarUA();
            cargarGrupo();
            cargarProfesor();
            System.out.println(listaPlan.size()+" "+listaProfesor.size()+" ---1");
            if(AAAHelper.getProgramaEducativo().getPedclave()!=0){
                filtrarPlanYProfPorPE();
                System.out.println(listaPlan.size()+" "+listaProfesor.size());
            }
            
            
    }
    
    boolean botones=true;

    public boolean isBotones() {
        return botones;
    }

    public void setBotones(boolean botones) {
        this.botones = botones;
    }
    
    public void modificar() {
        AAAHelper.setUnidadesAp(new ArrayList<String>());
        dlgCabecera(3);
        cargarUA();
        cargarProfesor();
        cargarPlan();
        cargarPE();
        cargarAC();
        try {
           
                AAAHelper.setProfesor(getCAAseleccionado().getProfesor());
                AAAHelper.setArea(getCAAseleccionado().getAreaadministrativa());
                AAAHelper.getUnidadesAp().add(getCAAseleccionado().getUnidadaprendizaje().getUapid().toString());
                if(loginBean.getSeleccionado().equalsIgnoreCase("Director")||loginBean.getSeleccionado().equalsIgnoreCase("Subdirector")||loginBean.getSeleccionado().equalsIgnoreCase("Administrador")){
                    AAAHelper.setProgramaEducativo(programaEducativoBeanHelper.getProgramaEducativoDelegate().findProgramaEducativoById(getCAAseleccionado().getAreaadministrativa().getProgramaeducativo().getPedid()));
                }else{
                    AAAHelper.setProgramaEducativo(AAAHelper.getProgramaEducativo());
                }
                //
                Unidadaprendizaje u=getCAAseleccionado().getUnidadaprendizaje();
                String clave=""+getCAAseleccionado().getUnidadaprendizaje().getUapclave();
                AAAHelper.setAreaConocimiento(AAAHelper.getAreaConocimientoDelegate().getAreaByUnidad(clave).get(0));
                System.out.println(AAAHelper.getAreaConocimiento().getAconombre());
                AAAHelper.setPlanEstudio(AAAHelper.getPlanEstudioDelegate().findByPlanEstudioId(AAAHelper.getAreaConocimiento().getPlanestudio().getPesid()));
                filtrarListas();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("\n\n\n joijiuhhojooijij");
            AAAHelper.setAGUAP(new UnidadaprendizajeImparteProfesor());
            AAAHelper.setUnidadApren(new Unidadaprendizaje());
            AAAHelper.setProfesor(new Profesor());
            AAAHelper.setGrupo(new Grupo());
        }
    }
    Unidadaprendizaje u;

    public Unidadaprendizaje getU() {
        return u;
    }

    public void setU(Unidadaprendizaje u) {
        this.u = u;
    }
    
    
    public void eliminar() {
        dlgCabecera(3);
        cargarUA();
        cargarProfesor();
        cargarPlan();
        cargarPE();
        cargarAC();
        try {
           
                AAAHelper.setProfesor(getCAAseleccionado().getProfesor());
                AAAHelper.setArea(getCAAseleccionado().getAreaadministrativa());
                AAAHelper.getUnidadesAp().add(0, getCAAseleccionado().getUnidadaprendizaje().getUapnombre());
                AAAHelper.setProgramaEducativo(programaEducativoBeanHelper.getProgramaEducativoDelegate().findProgramaEducativoById(getCAAseleccionado().getAreaadministrativa().getProgramaeducativo().getPedid()));
                //
                u=getCAAseleccionado().getUnidadaprendizaje();
                String clave=""+getCAAseleccionado().getUnidadaprendizaje().getUapclave();
                AAAHelper.setAreaConocimiento(AAAHelper.getAreaConocimientoDelegate().getAreaByUnidad(clave).get(0));
                System.out.println(AAAHelper.getAreaConocimiento().getAconombre());
                AAAHelper.setPlanEstudio(AAAHelper.getPlanEstudioDelegate().findByPlanEstudioId(AAAHelper.getAreaConocimiento().getPlanestudio().getPesid()));
                filtrarListas();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("\n\n\n joijiuhhojooijij");
            AAAHelper.setAGUAP(new UnidadaprendizajeImparteProfesor());
            AAAHelper.setUnidadApren(new Unidadaprendizaje());
            AAAHelper.setProfesor(new Profesor());
            AAAHelper.setGrupo(new Grupo());
        }
    }

//--------------------------seleccion de la tabla
    
    Coordinadorareaadministrativa CAAseleccionado;

    public Coordinadorareaadministrativa getCAAseleccionado() {
        return CAAseleccionado;
    }

    public void setCAAseleccionado(Coordinadorareaadministrativa CAAseleccionado) {
        this.CAAseleccionado = CAAseleccionado;
    }
    
    public void seleccion(SelectEvent event){
        setBotones(false);
        Coordinadorareaadministrativa c=new Coordinadorareaadministrativa();
        c=(Coordinadorareaadministrativa)event.getObject();
//        c.setAreaadministrativa(AAAHelper.getAreaAdministrativaDelegate.findAreaAdById(c.getAreaadministrativa().getAadid()));
//        c.setProfesor(profesorDelegate.findProfesorById(c.getProfesor().getProid()));
//        c.setUnidadaprendizaje(unidadAprendizajeDelegate.findUAById(c.getUnidadaprendizaje().getUapid()));
        setCAAseleccionado(c);
    }
    //------eliminacion
    public void confirmacion(){
        RequestContext.getCurrentInstance().execute("confirmdlg.show()");
    }
    
    public void eliminarCAA(){
        Coordinadorareaadministrativa c=getCAAseleccionado();
        AAAHelper.getCareaAdministrativaDelegate().eliminarAsignacion(c);
        FacesContext context= FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("", "Se eliminó correctamente"));
        c=new Coordinadorareaadministrativa();
        setCAAseleccionado(new Coordinadorareaadministrativa());
        AAAHelper.consultarAreasAdministrativas();
        setBotones(true);
    }
    
    //falta mucho por terminar no hay que llamar a este metodo aun
    public void onClickSubmit() {
        if(validarCamposVacios()){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Favor de llenar todos los campos vacios");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }else{
        if(AsignarAreaAdministrativaBeanUI.header.equalsIgnoreCase("Asignar Coordinación Área Administrativa")){
            Coordinadorareaadministrativa c=new Coordinadorareaadministrativa();
            Unidadaprendizaje u=new Unidadaprendizaje();
            CoordinadorareaadministrativaId ci;
            System.out.println(AAAHelper.getUnidadesAp().size());
            //---validacion
            boolean bandera=true;
            List<Coordinadorareaadministrativa> cs=AAAHelper.getCs();
//            for(int q=0; q<cs.size(); q++){//asi :v
//                if(cs.get(q).getAreaadministrativa().getProgramaeducativo().getPedid()==AAAHelper.getProgramaEducativo().getPedid()&&cs.get(q).getAreaadministrativa().getAadid()==AAAHelper.getArea().getAadid()){
//                    if(cs.get(q).getProfesor().getProid()==AAAHelper.getProfesor().getProid()&&cs.get(q).getAreaadministrativa().getAadid()==AAAHelper.getArea().getAadid()){
//                    
//                    }else{
//                        bandera=false;
//                    }
//                }
//            }
            //
            BaseDAO baseDAO=new BaseDAO();
            baseDAO.setTipo(Profesor.class);
            List<Profesor> list=baseDAO.findFromWhere("coordinadorareaadministrativas", "areaadministrativa.aadid", AAAHelper.getArea().getAadid().toString());
            baseDAO.setTipo(Areaadministrativa.class);
            List<Areaadministrativa> list2=baseDAO.findFromWhere("coordinadorareaadministrativas", "profesor.proid", AAAHelper.getProfesor().getProid().toString());
            if(list.isEmpty() || list2.isEmpty()){
                for(int x=0; x<cs.size(); x++){
                    if(cs.get(x).getProfesor().getProid()==AAAHelper.getProfesor().getProid()){
                        bandera=false;
                    }
                    if(cs.get(x).getAreaadministrativa().getAadid()==AAAHelper.getArea().getAadid()){
                        bandera=false;
                    }
                }
            }else{
                for(int i=0; i<list.size(); i++){
                    if(list.get(i).getProid().compareTo(AAAHelper.getProfesor().getProid())==0&&list2.get(i).getAadid().compareTo(AAAHelper.getArea().getAadid())==0){
                        
                    }else{
                        bandera=false;
                    }
                }
            }
            //
            if(bandera==false){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Un profesor no puede coordinar más de un área");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }else{
            for(int q=0; q<AAAHelper.getUnidadesAp().size(); q++){//asi :v
                boolean bandera2=true;
                try{
                    System.out.println(q);
                    for(int i=0;i<cs.size(); i++){
//                        if(Integer.parseInt(AAAHelper.getUnidadesAp().get(q))==cs.get(i).getUnidadaprendizaje().getUapid()){
//                            bandera2=false;
//                        }else{
//                            
//                        }
                        if(AAAHelper.getProgramaEducativo().getPedid()==cs.get(i).getAreaadministrativa().getProgramaeducativo().getPedid()){
                            
                        if(Integer.parseInt(AAAHelper.getUnidadesAp().get(q))==cs.get(i).getUnidadaprendizaje().getUapid()){
                            bandera2=false;
                        }else{
                            
                        }
                        }else{
                            
                        }
                    }
                    if(bandera2){
                        u=AAAHelper.getUnidadAprendizajeDelegate().findUAById(Integer.parseInt(AAAHelper.getUnidadesAp().get(q)));
                        ci=new CoordinadorareaadministrativaId(AAAHelper.getProfesor().getProid(), u.getUapid());
                        c.setId(ci);
                        c.setAreaadministrativa(AAAHelper.getArea());
                        AAAHelper.getCareaAdministrativaDelegate().asignarAreaAdministrativa(c);
                        FacesContext context= FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("", "Se guardó correctamente"));
                    }else{
                        FacesContext context= FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("", "La unidad ya se encuentra asignada"+" ("+AAAHelper.getUnidadAprendizajeDelegate().findUAById(Integer.parseInt(AAAHelper.getUnidadesAp().get(q))).getUapnombre()+")"));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "El registro esta repetido");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
                ci=new CoordinadorareaadministrativaId();
                c=new Coordinadorareaadministrativa();
                u=new Unidadaprendizaje();
            }
            }
            AAAHelper.setArea(new Areaadministrativa());
            AAAHelper.setUnidadesAp(new ArrayList<String>());
            AAAHelper.setProfesor(new Profesor());
            AAAHelper.consultarAreasAdministrativas();
        }else if (AsignarAreaAdministrativaBeanUI.header.equalsIgnoreCase("Modificar Asignación Área Administrativa")) {
            try{
                boolean bandera=true;
                List<Coordinadorareaadministrativa> cs=AAAHelper.getCs();
                BaseDAO baseDAO=new BaseDAO();
                baseDAO.setTipo(Profesor.class);
                List<Profesor> list=baseDAO.findFromWhere("coordinadorareaadministrativas", "areaadministrativa.aadid", AAAHelper.getArea().getAadid().toString());
                baseDAO.setTipo(Areaadministrativa.class);
                List<Areaadministrativa> list2=baseDAO.findFromWhere("coordinadorareaadministrativas", "profesor.proid", AAAHelper.getProfesor().getProid().toString());
                if(list.isEmpty() && list2.isEmpty()){
                    System.out.println("ambas listas estan vacias");
                }else{
                    for(int i=0; i<list.size(); i++){
                        if(list.get(i).getProid().compareTo(AAAHelper.getProfesor().getProid())==0&&list2.get(i).getAadid().compareTo(AAAHelper.getArea().getAadid())==0){
                            System.out.println("tercer if");
                        }else{
                            System.out.println("else tercer if");
                            bandera=false;
                        }
                    }
                }
                if (bandera==false) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Un profesor no puede coordinar más de un área");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }else{
                    if(AAAHelper.getUnidadesAp().size()>1){
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se han seleccionado varias materias");
                        RequestContext.getCurrentInstance().showMessageInDialog(message);
                    }else{
                        System.out.println("Llegue a modificar");
                        Coordinadorareaadministrativa c=getCAAseleccionado();
                        CoordinadorareaadministrativaId ci=getCAAseleccionado().getId();
                        System.out.println(getCAAseleccionado().getProfesor().getPronombre()+"-----------------------");
                        AAAHelper.getCareaAdministrativaDelegate().eliminarAsignacion(getCAAseleccionado());//quitar si no sirve
                        ci.setProfesorProid(AAAHelper.getProfesor().getProid());
                        ci.setUnidadAprendizajeUapid(AAAHelper.getUnidadAprendizajeDelegate().findUAById(Integer.parseInt(AAAHelper.getUnidadesAp().get(0))).getUapid());//quitar su no sirve
                        c.setId(ci);
                        setCAAseleccionado(c);
                        AAAHelper.getCareaAdministrativaDelegate().asignarAreaAdministrativa(getCAAseleccionado());
                        FacesContext context= FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("", "Se guardó correctamente"));
                        setBotones(true);
                        System.out.println(getCAAseleccionado().getProfesor().getPronombre()+"-----------------------");
                        limpiarSeleccion();
                        c=new Coordinadorareaadministrativa();
                        AAAHelper.consultarAreasAdministrativas();
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        }
    }

    //aun falta por terminar
    public void confirmacionAceptada() {
        if (deshabilitar.equals("true")) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Eliminando", "se eliminó correctamente"));

            //AAAHelper.eliminarDeLista(AAAHelper.getAGUAP().getUipid());
            AAAHelper.getAGUAPDelegate().eliminarUniAprenImparteProfe(AAAHelper.getAGUAP());
            AAAHelper.setSelecAGUAP(new UnidadaprendizajeImparteProfesor());
            AAAHelper.setAGUAP(new UnidadaprendizajeImparteProfesor());
            //AGUAPHelper.setSelecAGUAP(new UnidadaprendizajeImparteProfesor());
            RequestContext.getCurrentInstance().execute("confirmdlg.hide()");

            if (AAAHelper.getListaAGUAPSeleccionada().size() >= 1) {
                AAAHelper.setAGUAP(AAAHelper.getListaAGUAPSeleccionada().get(0));
                AAAHelper.setGrupo(AAAHelper.getListaAGUAPSeleccionada().get(0).getGrupo());
                AAAHelper.setProfesor(AAAHelper.getListaAGUAPSeleccionada().get(0).getProfesor());
                AAAHelper.setUnidadApren(AAAHelper.getListaAGUAPSeleccionada().get(0).getUnidadaprendizaje());
                RequestContext.getCurrentInstance().execute("dlg.show();");

            }
            filtro();
        }
    }

    boolean bolSelPED = false;
    public boolean getBolSelPed(){
        return bolSelPED;
    }
    public void setBolSelPED(boolean  bolSelPED){
        this.bolSelPED=bolSelPED;
    }
    public void filtro() {
        if(loginBean.getSeleccionado().equalsIgnoreCase("Director")||loginBean.getSeleccionado().equalsIgnoreCase("Subdirector")||loginBean.getSeleccionado().equalsIgnoreCase("Administrador")){
            bolSelPED=true;
        }
        listaFiltrada = AAAHelper.filtrado(busqueda);
    }

    public String toolTip(int i) {
        if (AAAHelper.getListaAGUAPSeleccionada() == null || AAAHelper.getListaAGUAPSeleccionada().size() < 1) {
            return "Seleccione un registro de la tabla";
        } else {
            if (i == 1) {
                return "Eliminar";
            } else if (i == 2) {
                return "Modificar";
            }
        }
        return "";
    }

    public String botonesModElim() {
        if (AAAHelper.getCaaSeleccionada()== null) {
            return "true";
        } else {
            return "false";
        }
    }

    public String dlgCabecera(int i) {
        if (i == 1) {
            header = "Asignar Coordinación Área Administrativa";
            deshabilitar = "false";
            deshabilitarSubgrupo = "false";
        }
        if (i == 2) {
            header = "Eliminar Grupo, Unidad Aprendizaje y Profesor";
            deshabilitar = "true";
            deshabilitarSubgrupo = "true";
        }
        if (i == 3) {
            header = "Modificar Asignación Área Administrativa";
            deshabilitar = "false";
            deshabilitarSubgrupo = "false";
        }
        return header;
    }

    public List<Programaeducativo> cargarPE() {
        listaPE = AAAHelper.getAGUAPDelegate().getListaProgramaEducativo();
        return listaPE;
    }

    public List<Planestudio> cargarPlan() {
        listaPlan = AAAHelper.getAGUAPDelegate().getListaPlanEstudio();
        return listaPlan;
    }

    public List<Areaconocimiento> cargarAC() {
        listaAC = AAAHelper.getAGUAPDelegate().getListaAreaConocimiento();
        return listaAC;
    }

    public List<Unidadaprendizaje> cargarUA() {
        listaUA = AAAHelper.getUnidadAprendizajeDelegate().getListaUnidadAprendizaje();
        return listaUA;
    }

    public List<Profesor> cargarProfesor() {
        listaProfesor = AAAHelper.getAGUAPDelegate().getListaProfesor();
        return listaProfesor;
    }

    public List<Grupo> cargarGrupo() {
        listaGrupo = AAAHelper.getAGUAPDelegate().getListaGrupo();
        return listaGrupo;
    }

    public boolean mostrarSeleccionAGUAP() {
        return AAAHelper.getListaAGUAPSeleccionada() != null && AAAHelper.getListaAGUAPSeleccionada().size() > 1;
    }

    public void filtrarPlanYProfPorPE() {
        System.out.println(AAAHelper.getProgramaEducativo().getPedid()+"filtro sout 1");
        listaPlan = AAAHelper.getPlanEstudioDelegate().buscarPlanEstudio(AAAHelper.getProgramaEducativo().getPedid());
        System.out.println(AAAHelper.getProgramaEducativo().getPedid()+"filtro sout 2");
        listaProfesor = AAAHelper.getProfesorDelegate().getProfPE(AAAHelper.getProgramaEducativo().getPedid());
        System.out.println(AAAHelper.getProgramaEducativo().getPedid()+"filtro sout 3");
        AAAHelper.consultarAreas();
        System.out.println(AAAHelper.getProgramaEducativo().getPedid()+"filtro sout 4");
    }

    public void filtrarAreaYGpoPorPlan() {
        listaAC = AAAHelper.getAreaConocimientoDelegate().getAreaMismoPlan(AAAHelper.getPlanEstudio().getPesid());
        //listaGrupo = AAAHelper.getGrupoDelegate().getGpoMismoPlan(AAAHelper.getPlanEstudio().getPesid());
    }

    public void filtrarUAPorArea() {
        listaUA = AAAHelper.getUnidadAprendizajeDelegate().getUAMismaArea(AAAHelper.getAreaConocimiento().getAcoid());
    }

    public void cargaDeListas() {
        cargarPE();
        cargarPlan();
        cargarAC();
        cargarUA();
        cargarProfesor();
    }

    public void filtrarListas() {
        filtrarPlanYProfPorPE();
        filtrarAreaYGpoPorPlan();
        filtrarUAPorArea();
    }

//    public void limpiarSeleccion() {
//        AAAHelper.setListaAGUAPSeleccionada(null);
//        AAAHelper.setAGUAP(new UnidadaprendizajeImparteProfesor());
//        AAAHelper.setUnidadApren(new Unidadaprendizaje());
//        AAAHelper.setProfesor(new Profesor());
//        AAAHelper.setGrupo(new Grupo());
//        mostrarSeleccionAGUAP();
//        botonesModElim();
//    }
    public void limpiarSeleccion() {
        AAAHelper.setCaaSeleccionado(new Coordinadorareaadministrativa());
        AAAHelper.setUnidadesAp(new ArrayList<String>());
        AAAHelper.setProfesor(new Profesor());
        AAAHelper.setArea(new Areaadministrativa());
        //
        //AAAHelper.setProgramaEducativo(new Programaeducativo());
        AAAHelper.setPlanEstudio(new Planestudio());
        AAAHelper.setAreaConocimiento(new Areaconocimiento());
        //mostrarSeleccionAGUAP();
        //botonesModElim();
    }

    public void tipoTieneSubgrupo() {
        if (AAAHelper.getAGUAP().getUiptipoSubgrupo().equals("C")) {
            AAAHelper.getAGUAP().setUipsubgrupo("0");
            deshabilitarSubgrupo = "true";
        } else {
            AAAHelper.getAGUAP().setUipsubgrupo("");
            deshabilitarSubgrupo = "false";
        }
    }

    public void subgrupoTieneTipo() {
        if (AAAHelper.getAGUAP().getUipsubgrupo().equals("0")) {
            AAAHelper.getAGUAP().setUiptipoSubgrupo("C");
        }
    }

//    public boolean validarCamposVacios() {
//        if (AAAHelper.getUnidadApren().getUapid() == 0
//                || AAAHelper.getProfesor().getProid() == 0
//                || AAAHelper.getGrupo().getGpoid() == 0
//                || AAAHelper.getAGUAP().getUipsubgrupo().isEmpty()
//                || AAAHelper.getAGUAP().getUiptipoSubgrupo().isEmpty()) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    
    public boolean validarCamposVacios() {
        if (AAAHelper.getUnidadesAp().isEmpty()
                || AAAHelper.getProfesor().getProid() == 0
                || AAAHelper.getArea().getAadid() == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public void validacion(){
        BaseDAO baseDAO=new BaseDAO();
        baseDAO.setTipo(Profesor.class);
        List<Profesor> list=baseDAO.findFromWhere("coordinadorareaadministrativas", "areaadministrativa.aadid", "4");
        for (Profesor p:list) {
            System.out.println(p.getPronombre());
        }
        baseDAO.setTipo(Areaadministrativa.class);
        List<Areaadministrativa> list2=baseDAO.findFromWhere("coordinadorareaadministrativas", "profesor.proid", "11");
        for (Areaadministrativa p:list2) {
            System.out.println(p.getAadnombre());
        }
    }
}
