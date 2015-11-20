package mx.avanti.siract.ui;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mx.avanti.common.util.MyUtil;
import mx.avanti.siract.application.helper.LoginBeanHelper;
import mx.avanti.siract.business.PermisoDelegate;
import mx.avanti.siract.business.RolDelegate;
import mx.avanti.siract.business.SubPermisoDelegate;
import mx.avanti.siract.business.entity.Permiso;
import mx.avanti.siract.business.entity.Rol;
import mx.avanti.siract.business.entity.Subpermisos;
import mx.avanti.siract.business.entity.Usuario;
import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@ManagedBean
@SessionScoped

public class LoginBean implements Serializable {

    private Map<String, String> roles = new HashMap<String, String>();
    List<Rol> listaRol = new ArrayList();
    private String rolmenu;
    private String rol;
    private Usuario logueado;

    String seleccionado;

    boolean permisoAlta = false;
    boolean permisoBaja = false;
    boolean permisoModificar = false;
    boolean formano = true;
    //////boolean TienePermiso = false;
    boolean TienePermiso = true;
    boolean selectRol = true;
    private String header;
///////////////////////Usuarios
    private String deshabilitar;
    List<Rol> obtenerListaRoles;
    private String dialogo;
    private String boton = "true";
    private List<String> obtenerRoles;//aqui se guardan los roles que seleccione en la alta al usuario
    private List<Rol> rolesTipo;
    Usuario u = new Usuario();
    String rolSeleccionado = "0";
    RolDelegate rd = new RolDelegate();
    PermisoDelegate pd = new PermisoDelegate();
    SubPermisoDelegate spd = new SubPermisoDelegate();
    private LoginBeanHelper logBeanHelp = new LoginBeanHelper();
    private MenuModel model;
    LoginBean lb;
    List<Rol> list = null;

    ///Constructor
    public LoginBean() {
        super();

        if (this.logueado == null) {
            this.logueado = new Usuario();
        }
    }

    ////Get and set
    public String getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(String seleccionado) {
        this.seleccionado = seleccionado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isFormano() {
        return formano;
    }

    public void setFormano(boolean formano) {
        this.formano = formano;
    }

    public boolean isResetdatos() {
        return resetdatos;
    }

    public void setResetdatos(boolean resetdatos) {
        this.resetdatos = resetdatos;
    }

    boolean resetdatos = false;

    public boolean isPermisoAlta() {
        return permisoAlta;
    }

    public void setPermisoAlta(boolean permisoAlta) {
        this.permisoAlta = permisoAlta;
    }

    public boolean isPermisoBaja() {
        return permisoBaja;
    }

    public void setPermisoBaja(boolean permisoBaja) {
        this.permisoBaja = permisoBaja;
    }

    public boolean isPermisoModificar() {
        return permisoModificar;
    }

    public void setPermisoModificar(boolean permisoModificar) {
        this.permisoModificar = permisoModificar;
    }

    public boolean isTienePermiso() {
        if (TienePermiso == true) {

            formano = false;
            setFormano(false);
        }
        return TienePermiso;
    }

    public void setTienePermiso(boolean TienePermiso) {
        this.TienePermiso = TienePermiso;
    }

    public MenuModel getModel() {
        return model;
    }

    public boolean isSelectRol() {
        return selectRol;
    }

    public void setSelectRol(boolean selectRol) {
        this.selectRol = selectRol;
    }

    public Map<String, String> getRoles() {
        return roles;
    }

    public Usuario getLogueado() {
        return logueado;
    }

    public void setLogueado(Usuario logueado) {
        this.logueado = logueado;
    }

    public String getRolMenu() {
        return rolmenu;
    }

    public void setRolMenu(String rolmenu) {
        this.rolmenu = rolmenu;
    }

    public boolean noRol() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(true);
        String username = "";
        username = (String) sesion.getAttribute("user");
        logueado.setUsuusuario(username);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("logueado: " + logueado.getUsuusuario());
        logueado = logBeanHelp.getUsuDel().login(logueado);/////////Cambio de 
        LoginBean lb = new LoginBean();
        List<Rol> list = null;
        boolean bandera = true;
        String seleccionado;
        seleccionado = getRolMenu();
        list = lb.Obtenerrol(logueado.getUsuid());
        System.out.println(list.size());
        if (list.size() == 0) {
            System.out.println(" la lista de roles es igual a 0");

            bandera = false;
        }
        System.out.println(" la lista de roles es igual mas de 0");

        return bandera;
    }

    public boolean unRol() {
        LoginBean lb = new LoginBean();
        List<Rol> list = null;
        boolean bandera = true;
        String seleccionado;

        list = lb.Obtenerrol(logueado.getUsuid());

        if (list.size() == 0) {
            bandera = false;

        } else {
            seleccionado = list.get(0).getRoltipo();
            this.seleccionado = seleccionado;
            RenderizarMenu(list, seleccionado);

        }
        System.out.println("ROL UNICO" + this.seleccionado);
        return bandera;
    }
    boolean asd = true;

    public boolean masRoles() {
        ////TienePermiso = false;
        TienePermiso=true;
        formano = false;
        ///formano = true;
        LoginBean lb = new LoginBean();
        List<Rol> list = null;
        String seleccionado;
        list = lb.Obtenerrol(logueado.getUsuid());
        System.out.println(" llegue a roles");
        if (list.size() > 1) {
            System.out.println(" la lista es mayor a 1");
            seleccionado = getRolMenu();
            System.out.println(getRolMenu() + " rol seleccionado directo del menu get");
            System.out.println("onclicksubtinpermiso");
            RenderizarMenu(list, seleccionado);
            this.seleccionado = seleccionado;
            System.out.println("UN ROL DE LOS MUCHOS" + this.seleccionado);
            return true;

        } else {

            return false;
        }

    }

    public boolean masRoles2() {
        LoginBean lb = new LoginBean();
        List<Rol> list = null;
        String seleccionado = "";
        System.out.println("Masroles 2");
        //// model = new DefaultMenuModel();
        ////dao.setTipo(Rol.class);
        list = lb.Obtenerrol(logueado.getUsuid());
        int may = -10000;
        if (list.size() > 1) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getRolprioridad() > may) {
                    may = list.get(i).getRolprioridad();
                    seleccionado = list.get(i).getRoltipo();
                    System.out.println("el mayor es " + list.get(i).getRolprioridad());
                }
            }

            RenderizarMenu(list, seleccionado);
            rolmenu = seleccionado;
            this.seleccionado = seleccionado;
            System.out.println("UN ROL DE LOS MUCHOS ----------EN 2" + this.seleccionado);
            return true;

        } else {
            return false;
        }

    }

    //------------------------------------------fin CapturaUsuarioBeanUI---------------------------
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!NUEVO LOGIN BEAN
    //Se modifico para que U nunca sea null
    @PostConstruct
    public void init() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(true);
        String username = "";
///  FacesContext context = FacesContext.getCurrentInstance();
        String ruta = "";
        username = (String) sesion.getAttribute("user");
        Usuario logueado = new Usuario();
        logueado.setUsuusuario(username);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("logueado: " + logueado.getUsuusuario());
        boolean loggedIn;
        logueado = logBeanHelp.getUsuDel().login(logueado);/////////Cambio de ruta

        if (logueado == null) {
            RequestContext context = RequestContext.getCurrentInstance();
            ruta = MyUtil.basepathlogin() + "denied.jsp";
            loggedIn = false;
            context.addCallbackParam("loggedIn", loggedIn);

            context.addCallbackParam("ruta", ruta);

        } else {

            List result = Obtenerrol(logueado.getUsuid());
            if (result == null) {
                roles = new HashMap<String, String>();
                roles.put("", "");
            } else {

                roles = new HashMap<String, String>();
                for (Object object : result) {
                    Rol dto = (Rol) object;
                    roles.put(dto.getRoltipo(), "");
                }
            }

        }

        obtenerRoles = new ArrayList();
    }

    ////Get and set
    public String getBoton() {
        return boton;
    }

    public void setBoton(String boton) {
        this.boton = boton;
    }

    boolean banderaUnavez = false;

    public void login() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(true);
        String username = "";
///  FacesContext context = FacesContext.getCurrentInstance();

        username = (String) sesion.getAttribute("user");

        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message;
        boolean loggedIn;
        boolean result;
        String ruta = "";
        logueado = new Usuario();
        logueado.setUsuusuario(username);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("Usuario: " + logueado.getUsuusuario());

        logueado = logBeanHelp.getUsuDel().login(logueado);/////////Cambio de ruta

////            usuario2 = usuario;
        if (logueado != null) {
            loggedIn = true;
            init();

            if (!noRol()) {
                setSelectRol(false);
                model = new DefaultMenuModel();

                ruta = MyUtil.basepathlogin() + "index.xhtml";
                System.out.println("entre a esta seccion por eso fui redericeonado de todas foprmas");
                loggedIn = false;

                if (this.logueado == null) {
                    this.logueado = new Usuario();
                }

                ruta = MyUtil.basepathlogin() + "denied.jsp";
                System.out.println("usuario no existe");
                loggedIn = false;
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario no tiene asignado rol", "Usuario no tiene asignado rol");
                FacesContext.getCurrentInstance().addMessage(null, message);
                context.addCallbackParam("loggedIn", loggedIn);

                context.addCallbackParam("ruta", ruta);

            } else {
                System.out.println("si estoy imprimiendo esto significa que ");

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("logueado", this.logueado.getUsuusuario());
                ////message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.logueado.getUsuusuario());
                lb = new LoginBean();
                list = null;

                list = lb.Obtenerrol(logueado.getUsuid());
                if (banderaUnavez == false) {
                    if (list.size() == 1) {
                        setSelectRol(false);
                        ruta = MyUtil.basepathlogin() + "index.xhtml";
                        permisoAlta = false;
                        permisoModificar = false;
                        permisoBaja = false;

                        onClickSubmitPermiso();
                        banderaUnavez = true;
                    } else {
                        banderaUnavez = true;
                        permisoAlta = false;
                        permisoModificar = false;
                        permisoBaja = false;

                        ruta = MyUtil.basepathlogin() + "index.xhtml";
                        onClickSubmitPermiso();

                        //----------------------------------------fin---------------------------------------------------------
                    }
                }
            }

            ////FacesContext.getCurrentInstance().addMessage(null, message);
            context.addCallbackParam("loggedIn", loggedIn);

            context.addCallbackParam("ruta", ruta);
        }
        if (logueado == null) {
            ruta = MyUtil.basepathlogin() + "denied.jsp";
            System.out.println("usuario no existe");
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario o Contraseña incorrecta", "Usuario o Contraseña incorrecta");
            if (this.logueado == null) {
                this.logueado = new Usuario();
            }

            FacesContext.getCurrentInstance().addMessage(null, message);
            context.addCallbackParam("loggedIn", loggedIn);

            context.addCallbackParam("ruta", ruta);

        }

    }

////cambios para renderizar el menu automaticamente al tener un solo rol
    public String onClickSubmitPermiso() {
        String ruta = "inicioP";

        model = new DefaultMenuModel();

        if (roles.size() == 1) {
            unRol();

        } else {
            if (roles.size() > 1) {
                masRoles2();
                setSelectRol(true);
            }
        }

        return ruta;
    }

    public String onClickSubmitPermiso2() {
        String ruta = "inicioP";
        System.out.println("onclicksubtinpermiso2");
        model = new DefaultMenuModel();
                                permisoAlta = false;
                                permisoModificar = false;
                                permisoBaja = false;

        if (roles.size() > 1) {
            masRoles();
            setSelectRol(true);
        }
        return ruta;
    }

    public boolean RenderizarMenu(List<Rol> list, String seleccionado) {
        DefaultSubMenu MenuUsuarios = new DefaultSubMenu("Usuarios");
//        DefaultSubMenu MenuAvanceContenidoTematico = new DefaultSubMenu("Avance contenido tematico");
        DefaultMenuItem MenuAvanceContenidoTematico = new DefaultMenuItem("Avance contenido temático");
        MenuAvanceContenidoTematico.setOutcome("ract");

//        DefaultSubMenu MenuConfiguracion = new DefaultSubMenu("Configuración del sistema");
        DefaultMenuItem MenuConfiguracion = new DefaultMenuItem("Configuración del sistema");
        MenuConfiguracion.setOutcome("config");

        DefaultSubMenu MenuCatalogos = new DefaultSubMenu("Mantenimiento a catálogos");

//        DefaultSubMenu MenuReportes = new DefaultSubMenu("Reportes");
        DefaultMenuItem MenuReportes = new DefaultMenuItem("Reportes");
        MenuReportes.setOutcome("reporte");

        MenuReportes.setRendered(false);
        MenuUsuarios.setRendered(false);
        MenuAvanceContenidoTematico.setRendered(false);
        MenuCatalogos.setRendered(false);
        MenuConfiguracion.setRendered(false);

        MenuReportes.setIcon("btnReportes");
        MenuCatalogos.setIcon("btnMantenimiento");
        MenuConfiguracion.setIcon("btnConfiguracion");
        MenuAvanceContenidoTematico.setIcon("btnAvance");
        MenuUsuarios.setIcon("btnUserconfig");

        DefaultMenuItem OpcReportes = new DefaultMenuItem("Generador de reportes");
        DefaultMenuItem OpcUsuarios = new DefaultMenuItem("Usuarios");
        DefaultMenuItem OpcRoles = new DefaultMenuItem("Roles");
        OpcRoles.setOnclick("statusDialog.show()");

        DefaultMenuItem OpcRealizar = new DefaultMenuItem("Realizar");
        DefaultMenuItem OpcConfiguracion = new DefaultMenuItem("Configuración");
        DefaultMenuItem OpcAdmonUnidadAcademica = new DefaultMenuItem("Unidad académica");
        DefaultMenuItem OpcAdmonProgramaE = new DefaultMenuItem("Programa educativo");
        DefaultMenuItem OpcAdmonAreaC = new DefaultMenuItem("Área de conocimiento");
        DefaultMenuItem OpcAdmonUnidadAprendizaje = new DefaultMenuItem("Unidad de aprendizaje");
        DefaultMenuItem OpcAdmonProfesores = new DefaultMenuItem("Profesores");
        DefaultMenuItem OpcAdmonContenidoT = new DefaultMenuItem("Contenido temático");
        DefaultMenuItem OpcAdmonGrupo = new DefaultMenuItem("Grupo");
        DefaultMenuItem OpcAsignarGrupo = new DefaultMenuItem("Asignar grupo");
        DefaultMenuItem OpcAdmonCampus = new DefaultMenuItem("Campus");
        DefaultMenuItem OpcAdmonCiclo = new DefaultMenuItem("Ciclo escolar");
        DefaultMenuItem OpcAdmonPlanEstudios = new DefaultMenuItem("Plan de estudios");

        //Posiblemente no necesario
        //Asignar valores de los enlaces
        OpcUsuarios.setOutcome("UsuariosMenu");
        OpcConfiguracion.setOutcome("config");
        OpcRoles.setOutcome("RolesMenu");
        OpcRealizar.setOutcome("ract");

        OpcReportes.setOutcome("reporte");
        OpcAdmonUnidadAcademica.setOutcome("UnidadAcademicaMenu");
        OpcAdmonProgramaE.setOutcome("ProgramaEducativoMenu");
        OpcAdmonAreaC.setOutcome("AreadeConocimientoMenu");
        
        //Linea comentada para quitar funcionamiento a la opcion de unidad de aprendizaje
        //OpcAdmonUnidadAprendizaje.setOutcome("AprendizajeMenu");
        OpcAdmonProfesores.setOutcome("ProfesorMenu");
        OpcAdmonContenidoT.setOutcome("ContenidoTematicoMenu");
        OpcAdmonGrupo.setOutcome("GruposMenu");
        OpcAsignarGrupo.setOutcome("AsigGruposMenu");
        OpcAdmonCampus.setOutcome("CampusMenu");
        OpcAdmonCiclo.setOutcome("cem");
        OpcAdmonPlanEstudios.setOutcome("PlanMenu");

        OpcReportes.setRendered(false);
        OpcUsuarios.setRendered(false);
        OpcRoles.setRendered(false);
        OpcRealizar.setRendered(false);
        OpcConfiguracion.setRendered(false);
        OpcAdmonUnidadAcademica.setRendered(false);
        OpcAdmonProgramaE.setRendered(false);
        OpcAdmonAreaC.setRendered(false);
        OpcAdmonUnidadAprendizaje.setRendered(false);
        OpcAdmonProfesores.setRendered(false);
        OpcAdmonContenidoT.setRendered(false);
        OpcAdmonGrupo.setRendered(false);
        OpcAsignarGrupo.setRendered(false);
        OpcAdmonCampus.setRendered(false);
        OpcAdmonCiclo.setRendered(false);
        OpcAdmonPlanEstudios.setRendered(false);

//        MenuReportes.addElement(OpcReportes);
        MenuUsuarios.addElement(OpcUsuarios);
        MenuUsuarios.addElement(OpcRoles);
//        MenuAvanceContenidoTematico.addElement(OpcRealizar);
//        MenuConfiguracion.addElement(OpcConfiguracion);
        MenuCatalogos.addElement(OpcAdmonUnidadAcademica);
        MenuCatalogos.addElement(OpcAdmonProgramaE);
        MenuCatalogos.addElement(OpcAdmonAreaC);
        MenuCatalogos.addElement(OpcAdmonUnidadAprendizaje);
        MenuCatalogos.addElement(OpcAdmonProfesores);
        MenuCatalogos.addElement(OpcAdmonContenidoT);
        MenuCatalogos.addElement(OpcAdmonGrupo);
        MenuCatalogos.addElement(OpcAsignarGrupo);
        MenuCatalogos.addElement(OpcAdmonCampus);
        MenuCatalogos.addElement(OpcAdmonCiclo);
        MenuCatalogos.addElement(OpcAdmonPlanEstudios);

        model.addElement(MenuAvanceContenidoTematico);
        model.addElement(MenuCatalogos);
        model.addElement(MenuConfiguracion);
        model.addElement(MenuReportes);
        model.addElement(MenuUsuarios);

        
        
        //Agregado a 18-08-2015 para el funcionamiento de areas de conocimiento administrativas
        DefaultMenuItem OpcAreaConAdm = new DefaultMenuItem("Área administrativa");
        DefaultMenuItem OpcCoorAreaConAdm = new DefaultMenuItem("Coordinador área administrativa");
        OpcAreaConAdm.setOutcome("AreaAdministrativa");
        OpcAreaConAdm.setRendered(false);
        MenuCatalogos.addElement(OpcAreaConAdm);

        OpcCoorAreaConAdm.setOutcome("AreaConocimientoAdministrativa");
        OpcCoorAreaConAdm.setRendered(false);
        MenuCatalogos.addElement(OpcCoorAreaConAdm);

        DefaultMenuItem OpcResProgEd = new DefaultMenuItem("Responsable de programa educativo");
        OpcResProgEd.setOutcome("responsableprogramaeducativo");
        OpcResProgEd.setRendered(false);
        MenuCatalogos.addElement(OpcResProgEd);

        //
        LoginBean lb2 = new LoginBean();
        for (Rol rol : list) {
            if (rol.getRoltipo().equals(seleccionado)) {
                System.out.println(seleccionado+"########################################################");
                List<Permiso> list2 = null;
                list2 = lb.ObtenerPermiso(rol.getRolid());
                for (Permiso per : list2) {
                System.out.println(per.getPertipo()+"########################################################");
                    
                if (per.getPertipo().equals("Generar reporte")) {
                        
                        MenuReportes.setRendered(true);
                        OpcReportes.setRendered(true);
                        
                    }
                    if (per.getPertipo().equals("Configurar sistema")) {
                        
                        MenuConfiguracion.setRendered(true);
                        OpcConfiguracion.setRendered(true);
                        
                    }
                    if (per.getPertipo().equals("Administración de rol")) {
                        MenuUsuarios.setRendered(true);
                        OpcRoles.setRendered(true);
                        
                    }
                    if (per.getPertipo().equals("Administración de cuenta de usuario")) {
                        MenuUsuarios.setRendered(true);
                        OpcUsuarios.setRendered(true);
                        
                    }
                    if (per.getPertipo().equals("Administración de unidad académica")) {
                        MenuCatalogos.setRendered(true);
                        OpcAdmonUnidadAcademica.setRendered(true);
                        
                    }
                    if (per.getPertipo().equals("Administración de programa educativo")) {
                        
                        MenuCatalogos.setRendered(true);
                        OpcAdmonProgramaE.setRendered(true);
                        
                    }
                    if (per.getPertipo().equals("Administración de área de conocimiento")) {
                        
                        MenuCatalogos.setRendered(true);
                        OpcAdmonAreaC.setRendered(true);
                        
                    }
                    if (per.getPertipo().equals("Administración de unidad de aprendizaje")) {
                       
                        MenuCatalogos.setRendered(true);
                        OpcAdmonUnidadAprendizaje.setRendered(true);
                       
                    }
                    if (per.getPertipo().equals("Administración de profesores")) {
                       
                        MenuCatalogos.setRendered(true);
                        OpcAdmonProfesores.setRendered(true);
                       
                    }
                    if (per.getPertipo().equals("Administración de contenido temático")) {
                        
                        MenuCatalogos.setRendered(true);
                        OpcAdmonContenidoT.setRendered(true);
                        
                    }
                    if (per.getPertipo().equals("Administración grupo")) {
                        
                        MenuCatalogos.setRendered(true);
                        OpcAdmonGrupo.setRendered(true);
                        

                    }
                    if (per.getPertipo().equals("Asignar grupo")) {
                       
                        MenuCatalogos.setRendered(true);
                        OpcAsignarGrupo.setRendered(true);
                       
                    }
                    if (per.getPertipo().equals("Actualizar porcentaje de contenido temático")) {
                       
                        MenuAvanceContenidoTematico.setRendered(true);
                        OpcRealizar.setRendered(true);
                       
                    }
                    if (per.getPertipo().equals("Administración de campus")) {
                        
                        MenuCatalogos.setRendered(true);
                        OpcAdmonCampus.setRendered(true);

                    }
                    if (per.getPertipo().equals("Administración de ciclo escolar")) {
                        
                        MenuCatalogos.setRendered(true);
                        OpcAdmonCiclo.setRendered(true);
                        
                    }
                    if (per.getPertipo().equals("Administración de plan de estudios")) {
                       
                        MenuCatalogos.setRendered(true);
                        OpcAdmonPlanEstudios.setRendered(true);
                        
                    }
                    if (per.getPertipo().equals("Responsable de programa educativo")) {
                        
                        MenuCatalogos.setRendered(true);
                        OpcResProgEd.setRendered(true);
                        
                    }
                    if (per.getPertipo().equals("Administración de área administrativa")) {
                        
                        MenuCatalogos.setRendered(true);
                        OpcAreaConAdm.setRendered(true);
                        
                    }
                    if (per.getPertipo().equals("Administración de coordinador área")) {
                        
                        MenuCatalogos.setRendered(true);
                        OpcCoorAreaConAdm.setRendered(true);
                        
                    }
                }
            }
        }

        return true;
    }
    
    public void TienePermiso(List<Rol> list, String seleccionado,String catalogo){
        permisoModificar=false;
        permisoAlta=false;
        permisoBaja=false;
        formano = true;
        TienePermiso = false;
        for (Rol rol : list) {
            if (rol.getRoltipo().equals(seleccionado)) {
                System.out.println(seleccionado+"########################################################");
                List<Permiso> list2 = null;
                list2 = lb.ObtenerPermiso(rol.getRolid());
                for (Permiso per : list2) {
                
                    
                if (per.getPertipo().equals(catalogo)) {
                    System.out.println(catalogo+"tengo este permiso");
                        TienePermiso=true;
                        formano=false;
                        
                        List<Subpermisos> s;
                        s = logBeanHelp.getSp().getPermisoID(rol.getRolid(), per.getPerid());
                        for (Subpermisos sub : s) {
                            if (sub.getSpertipo().equals("Modificación")) {
                                permisoModificar = true;

                            }
                            if (sub.getSpertipo().equals("Eliminación")) {
                                permisoBaja = true;

                            }
                            if (sub.getSpertipo().equals("Altas")) {
                                permisoAlta = true;

                            }
                        }
                    }
        
                }
            }
        }
        
    }
    

    public List<Rol> Obtenerrol(int rol) {
        List<Rol> listarol = null;
        listarol = rd.getRolUser(rol);
        return listarol;

    }

    public List<Permiso> ObtenerPermiso(int permiso) {
        List<Permiso> listapermiso = null;
        listapermiso = pd.getPermisoUser(permiso);
        return listapermiso;

    }

    public boolean selectedRol() {
        boolean b;
        b = true;
        boolean ver = b;
        return ver;

    }

    public String doActionSalir() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        final HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        request.getSession().setAttribute("user", null);
        request.getSession(false).invalidate();

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("https://llave.uabc.edu.mx/auth/logout");
            ////   FacesContext.getCurrentInstance().getExternalContext().redirect("https://mail.google.com/mail/logout?hl=es");

        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public String index() {
        String ruta = "inicioP";
        return ruta;
    }

    public String getUsuariose() {

        return usuariose;
    }

    public void setUsuariose(String usuariose) {
        this.usuariose = usuariose;
    }
    String usuariose;

    int idUsu = -1;

    Rol rolObj = new Rol();

//-------------------------------------------Copia de lo que hay en CapturaUsuarioBeanUI------------------------------------
    private String listStrRoles = "No tiene roles";

    public LoginBeanHelper getLogBeanHelp() {
        return logBeanHelp;
    }

}
