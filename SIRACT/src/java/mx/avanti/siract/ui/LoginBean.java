package mx.avanti.siract.ui;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import mx.avanti.common.util.MyUtil;
import mx.avanti.siract.application.helper.LoginBeanHelper;
import mx.avanti.siract.business.PermisoDelegate;
import mx.avanti.siract.business.RolDelegate;
import mx.avanti.siract.business.SubPermisoDelegate;
import mx.avanti.siract.business.entity.Permiso;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Rol;
import mx.avanti.siract.business.entity.Subpermisos;
import mx.avanti.siract.business.entity.Usuario;
import mx.avanti.siract.integration.persistence.BaseDAO;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;
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
    private  Usuario usuario;
    private Usuario usuario2;
    String seleccionado;

    public String getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(String seleccionado) {
        this.seleccionado = seleccionado;
    }
    ///Busqueda///
    private String busqueda;
    ///Busqueda///
    boolean permisoAlta = false;
    boolean permisoBaja = false;
    boolean permisoModificar = false;
    boolean formano = true;
    boolean TienePermiso = false;
    boolean selectRol = true;
    private String header;
///////////////////////Usuarios
    private String deshabilitar;
    List<Rol> obtenerListaRoles;
    private String dialogo;
    private String boton="true";
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

        if (this.usuario == null) {
            this.usuario = new Usuario();
        }

    }

    ////Get and set
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    ;
    
    public Usuario getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(Usuario usuario2) {
        this.usuario2 = usuario2;
    }
    

    public String getRolMenu() {
        return rolmenu;
    }

    public void setRolMenu(String rolmenu) {
        this.rolmenu = rolmenu;
    }


    public boolean noRol() {
        LoginBean lb = new LoginBean();
        List<Rol> list = null;
        boolean bandera = true;
        String seleccionado;
        seleccionado = getRolMenu();
        list = lb.Obtenerrol(usuario.getUsuid());
        System.out.println(list.size());
        if (list.size() == 0) {

            bandera = false;
        }

        return bandera;
    }

    public boolean unRol() {
        LoginBean lb = new LoginBean();
        List<Rol> list = null;
        boolean bandera = true;
        String seleccionado;

        list = lb.Obtenerrol(usuario.getUsuid());

        if (list.size() == 0) {
            bandera = false;

        } else {
            seleccionado = list.get(0).getRoltipo();
            this.seleccionado=seleccionado;
            RenderizarMenu(list, seleccionado);

        }
        System.out.println("ROL UNICO"+this.seleccionado);
        return bandera;
    }
    boolean asd = true;

    public boolean masRoles() {
        TienePermiso = false;
        formano = true;
        LoginBean lb = new LoginBean();
        List<Rol> list = null;
        String seleccionado;
        list = lb.Obtenerrol(usuario.getUsuid());
        System.out.println(" llegue a roles");
        if (list.size() > 1) {
            System.out.println(" la lista es mayor a 1");
            seleccionado = getRolMenu();
            System.out.println("onclicksubtinpermiso");
            RenderizarMenu(list, seleccionado);
            this.seleccionado=seleccionado;
            System.out.println("UN ROL DE LOS MUCHOS"+this.seleccionado);
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
        list = lb.Obtenerrol(usuario.getUsuid());
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
            this.seleccionado=seleccionado;
            System.out.println("UN ROL DE LOS MUCHOS ----------EN 2"+this.seleccionado);
            return true;

        } else {
            return false;
        }

    }
    
    //------------------------------------------fin CapturaUsuarioBeanUI---------------------------
    
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!NUEVO LOGIN BEAN
        boolean eliminaa = false;

        
    public boolean isEliminaa() {
        return eliminaa;
    }
    public void banderaeliminar(){
        eliminaa=true;
        onClickSubmit();
    }

    public void setEliminaa(boolean eliminaa) {
        this.eliminaa = eliminaa;
    }

    public boolean isEliminarono() {
        if (eliminaa == true) {

            eliminarono = true;
            setEliminarono(true);
        }
        
        return eliminarono;
    }

    public void setEliminarono(boolean eliminarono) {
        this.eliminarono = eliminarono;
    }
    boolean eliminarono=false;
   
    boolean multiSeleccion = true;


    //Se modifico para que U nunca sea null
    @PostConstruct
    public void init() {
        if (usuario.getUsuid() != null) {
            List result = Obtenerrol(this.usuario.getUsuid());
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
    public boolean isMultiSeleccion() {
        return multiSeleccion;
    }

    public void setMultiSeleccion(boolean multiSeleccion) {
        this.multiSeleccion = multiSeleccion;
    }

   
///Método para logueo y deslogueo

    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message;
        boolean loggedIn;
        boolean result;
        String ruta = "";
        System.out.println("Usuario: " + usuario.getUsuusuario());
        System.out.println("Contra: " + usuario.getUsucontrasenia());

        usuario = logBeanHelp.getUsuDel().login(usuario);/////////Cambio de ruta
        if(usuario!=null) 
        idLogeado=usuario.getUsuid();
        usuario2=usuario;

        if (usuario != null) {
            loggedIn = true;
            init();

            if (!noRol()) {
                ruta = MyUtil.basepathlogin() + "login.xhtml";
                loggedIn = false;

                if (this.usuario == null) {
                    this.usuario = new Usuario();
                }
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario no tiene asignado rol", "Usuario no tiene asignado rol");

            } else {

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario.getUsuusuario());
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.usuario.getUsuusuario());
                lb = new LoginBean();
                list = null;

                list = lb.Obtenerrol(usuario.getUsuid());
                if (list.size() == 1) {
                    setSelectRol(false);
                    ruta = MyUtil.basepathlogin() + "index.xhtml";
                    onClickSubmitPermiso();
                } else {
                    ruta = MyUtil.basepathlogin() + "index.xhtml";
                    onClickSubmitPermiso();

                    //----------------------------------------fin---------------------------------------------------------
                }
            }
             FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);

        context.addCallbackParam("ruta", ruta);
            
        } if(usuario==null) {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario o Contraseña incorrecta", "Usuario o Contraseña incorrecta");
            if (this.usuario == null) {
                this.usuario = new Usuario();
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
        
        //SE MODIFICARON LOS BOTONES PARA QUE NO DIGAN ADMINISTRACON
//        DefaultMenuItem OpcAdmonUnidadAcademica = new DefaultMenuItem("Administración de unidad académica");
//        DefaultMenuItem OpcAdmonProgramaE = new DefaultMenuItem("Administración de programa educativo");
//        DefaultMenuItem OpcAdmonAreaC = new DefaultMenuItem("Administración de área de conocimiento");
//        DefaultMenuItem OpcAdmonUnidadAprendizaje = new DefaultMenuItem("Administración de unidad de aprendizaje");
//        DefaultMenuItem OpcAdmonProfesores = new DefaultMenuItem("Administración de profesores");
//        DefaultMenuItem OpcAdmonContenidoT = new DefaultMenuItem("Administración de contenido temático");
//        DefaultMenuItem OpcAdmonGrupo = new DefaultMenuItem("Administración grupo");
//        DefaultMenuItem OpcAsignarGrupo = new DefaultMenuItem("Asignar grupo");
//        DefaultMenuItem OpcAdmonCampus = new DefaultMenuItem("Administración de campus");
//        DefaultMenuItem OpcAdmonCiclo = new DefaultMenuItem("Administración de ciclo escolar");
//        DefaultMenuItem OpcAdmonPlanEstudios = new DefaultMenuItem("Administración de plan de estudios");
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

            //no necesario
//          DefaultMenuItem OpcPuestos = new DefaultMenuItem("Puestos");
//          OpcPuestos.setOutcome("PuestoMenu");
//          OpcPuestos.setRendered(true);
//          MenuCatalogos.addElement(OpcPuestos);
          
        //Asignar valores de los enlaces
        OpcUsuarios.setOutcome("UsuariosMenu");
        OpcConfiguracion.setOutcome("config");
        OpcRoles.setOutcome("RolesMenu");
        OpcRealizar.setOutcome("ract");
        
        
         OpcReportes.setOutcome("reporte");
         OpcAdmonUnidadAcademica .setOutcome("UnidadAcademicaMenu");
         OpcAdmonProgramaE.setOutcome("ProgramaEducativoMenu");
         OpcAdmonAreaC.setOutcome("AreadeConocimientoMenu");
         OpcAdmonUnidadAprendizaje.setOutcome("AprendizajeMenu");
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

        LoginBean lb2 = new LoginBean();
        for (Rol rol : list) {
            if (rol.getRoltipo().equals(seleccionado)) {
                List<Permiso> list2 = null;
                list2 = lb.ObtenerPermiso(rol.getRolid());
                for (Permiso per : list2) {
                    if (per.getPertipo().equals("Generar reporte")) {
                        TienePermiso = true;
                        MenuReportes.setRendered(true);
                        OpcReportes.setRendered(true);
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
                    if (per.getPertipo().equals("Configurar sistema")) {
                        TienePermiso = true;
                        MenuConfiguracion.setRendered(true);
                        OpcConfiguracion.setRendered(true);
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
                    if (per.getPertipo().equals("Administración de rol")) {
                        MenuUsuarios.setRendered(true);
                        OpcRoles.setRendered(true);
                        TienePermiso = true;
                        formano = false;
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
                    if (per.getPertipo().equals("Administración de cuenta de usuario")) {
                        MenuUsuarios.setRendered(true);
                        OpcUsuarios.setRendered(true);
                        TienePermiso = true;
                        formano = false;
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
                    if (per.getPertipo().equals("Administración de unidad académica")) {
                        MenuCatalogos.setRendered(true);
                        OpcAdmonUnidadAcademica.setRendered(true);
                        TienePermiso = true;
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
                    if (per.getPertipo().equals("Administración de programa educativo")) {
                        TienePermiso = true;
                        MenuCatalogos.setRendered(true);
                        OpcAdmonProgramaE.setRendered(true);
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
                    if (per.getPertipo().equals("Administración de área de conocimiento")) {
                        TienePermiso = true;
                        MenuCatalogos.setRendered(true);
                        OpcAdmonAreaC.setRendered(true);
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
                    if (per.getPertipo().equals("Administración de unidad de aprendizaje")) {
                        TienePermiso = true;
                        MenuCatalogos.setRendered(true);
                        OpcAdmonUnidadAprendizaje.setRendered(true);
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
                    if (per.getPertipo().equals("Administración de profesores")) {
                        TienePermiso = true;
                        MenuCatalogos.setRendered(true);
                        OpcAdmonProfesores.setRendered(true);
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
                    if (per.getPertipo().equals("Administración de contenido temático")) {
                        TienePermiso = true;
                        MenuCatalogos.setRendered(true);
                        OpcAdmonContenidoT.setRendered(true);
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
                    if (per.getPertipo().equals("Administración grupo")) {
                        TienePermiso = true;
                        MenuCatalogos.setRendered(true);
                        OpcAdmonGrupo.setRendered(true);
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
                    if (per.getPertipo().equals("Asignar grupo")) {
                        TienePermiso = true;
                        MenuCatalogos.setRendered(true);
                        OpcAsignarGrupo.setRendered(true);
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
                    if (per.getPertipo().equals("Actualizar porcentaje de contenido temático")) {
                        TienePermiso = true;
                        MenuAvanceContenidoTematico.setRendered(true);
                        OpcRealizar.setRendered(true);
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
                    if (per.getPertipo().equals("Administración de campus")) {
                        TienePermiso = true;
                        MenuCatalogos.setRendered(true);
                        OpcAdmonCampus.setRendered(true);
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
                    if (per.getPertipo().equals("Administración de ciclo escolar")) {
                        TienePermiso = true;
                        MenuCatalogos.setRendered(true);
                        OpcAdmonCiclo.setRendered(true);
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
                    if (per.getPertipo().equals("Administración de plan de estudios")) {
                        TienePermiso = true;
                        MenuCatalogos.setRendered(true);
                        OpcAdmonPlanEstudios.setRendered(true);
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

        return true;
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

    public void logout() {
        String ruta = MyUtil.basepathlogin() + "login.xhtml";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(false);
        sesion.invalidate();

        context.addCallbackParam("loggerOut", true);
        context.addCallbackParam("ruta", ruta);
    }

    public String cambioUsuario2() {
        String ruta = "permiso";

        return ruta;
    }

    public boolean cambioUsuario() {
        String ruta;

        if (isProfesor()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isProfesor() {
        BaseDAO dao = new BaseDAO();
        List<Rol> list = null;
        dao.setTipo(Rol.class);
        list = dao.findFromWhere("usuarios", "usuid", String.valueOf(this.usuario.getUsuid()));

        return false;
    }

    public String index() {
        String ruta = "inicioP";
        return ruta;
    }

    public String onClickSubmitPermisoR() {
        String ruta = "inicioP";

        LoginBean lb = new LoginBean();
        List<Rol> list = null;
        String seleccionado;
        list = lb.Obtenerrol(usuario.getUsuid());

        seleccionado = list.get(0).getRoltipo();
        RenderizarMenu(list, seleccionado);

        return ruta;
    }

    public String getUsuariose() {

        return usuariose;
    }

    public void setUsuariose(String usuariose) {
        this.usuariose = usuariose;
    }
    String usuariose;

    public Usuario getU() {
        System.out.println(u.getUsucontrasenia() + "contraseñaaaa");
        return u;
    }

    public void setU(Usuario u) {
        this.u = u;
    }
    int idUsu = -1;

    public int getIdLogeado() {
        return idLogeado;
    }

    public void setIdLogeado(int idLogeado) {
        this.idLogeado = idLogeado;
    }
    int idLogeado=0;
    Rol rolObj = new Rol();

    public String onRowSelect(SelectEvent event) {
        if(usuario.getUsuid()==0){
            usuario.setUsuid(idLogeado);
        }
        boton = "false";
        u = new Usuario();
        u = selectedUsuario;
        u = (Usuario) event.getObject();
        usuariose = u.getUsuusuario();
        idUsu = u.getUsuid();
            
        System.out.println(usuario.getUsuid()+"______________________-modificar");
        

        setUsuariose(usuariose);
        
        return usuariose;
    }

    public void onRowUnselect(UnselectEvent event) {
        if(usuario.getUsuid()==0){
            usuario.setUsuid(idLogeado);
        }
       if(selectedUsuarios.size()>0){
        boton = "false";
            System.out.println("+++++++++++++");
        }else{
        boton="true";
            System.out.println("------------");}
        u = new Usuario();
            System.out.println(usuario.getUsuusuario()+"______________________-modificar");
        System.out.println(usuario.getUsuid()+"______________________-modificar");
    }
    public String onToggleselect(ToggleSelectEvent event) {
        if(usuario.getUsuid()==0){
            usuario.setUsuid(idLogeado);
        }
        if (boton == "false" && selectedUsuarios.size()==0) {
            boton = "true";
            return boton;
        }
        if (boton == "true" && selectedUsuarios.size()>0) {
            boton = "false";
            return boton;
        }
        return boton;

    }

//-------------------------------------------Copia de lo que hay en CapturaUsuarioBeanUI------------------------------------
    private String listStrRoles = "No tiene roles";

    private Usuario selectedUsuario;
    private List<Usuario> selectedUsuarios;

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {

        this.selectedUsuario = selectedUsuario;
    }


    public List<Usuario> getSelectedUsuarios() {
        return selectedUsuarios;
    }

    public void setSelectedUsuarios(List<Usuario> selectedUsuarios) {

        this.selectedUsuarios = selectedUsuarios;
    }

    public void selectMultipleUsuario() {
        

        System.out.println("Usuario selected en el select " + usuario.getUsuid());
        selectedUsuario = logBeanHelp.getUsuDel().usuUnic(usuario.getUsuid());
        idUsu = selectedUsuario.getUsuid();
        u = selectedUsuario;

    }

    public void eliminarComfirm() {
        u = logBeanHelp.getUsuDel().usuUnic(usuario.getUsuid());
        List<Usuario> l = selectedUsuarios;
        System.out.println(l.size() + "tamaño de la lista ____________");
        if (l.size() == 1) {
            for (Usuario us : l) {
                
                u = logBeanHelp.getUsuDel().usuUnic(us.getUsuid());
                idUsu = u.getUsuid();
                Usuario usMod = logBeanHelp.getUsuDel().usuUnic(idUsu);
                System.out.println("Borrando usuario: " + usMod.getUsuusuario());
                logBeanHelp.getUsuDel().eliminarUsuario(usMod);

                u = null; //limpiar la variable cada vez que se modifica
                u = new Usuario();// se crea una vacia para llenar posteriormente y evitar nullpointer

                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Se eliminó correctamente.", ""));
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxenestaparte se esta eliminando 1 usuario");
    
            }

        } else {
            u = logBeanHelp.getUsuDel().usuUnic(usuario.getUsuid());
            idUsu = u.getUsuid();
            Usuario usMod = logBeanHelp.getUsuDel().usuUnic(idUsu);
            System.out.println("Borrando usuario: " + usMod.getUsuusuario());
            logBeanHelp.getUsuDel().eliminarUsuario(usMod);
            for(int user=0;user<selectedUsuarios.size();user++){
            if(selectedUsuarios.get(user).getUsuid()==usMod.getUsuid()){
                selectedUsuarios.remove(user);
                
            }
        }
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

            
            u = null; //limpiar la variable cada vez que se modifica
            u = new Usuario();// se crea una vacia para llenar posteriormente y evitar nullpointer
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Se eliminó correctamente.", ""));
            

        }
        String ruta = "inicioP";

        if (selectedUsuarios.size() == 1) {
            boton = "true";
            u = new Usuario();
            setBoton(boton);
            getBoton();
        }
        
        
        u = new Usuario();
        

    }

    public void onClickSubmit() {
        String result = "UsuarioGuardado";
        if(usuario.getUsuid()==0){
            usuario.setUsuid(idLogeado);
        }
        HashSet setRols = new HashSet();
        if (deshabilitar.equals("true")) {
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

            if (isEliminarono() == false) {
                RequestContext.getCurrentInstance().execute("confirmdlg.show();");

            }

            if (isEliminarono()) {
                eliminaa = false;
                setEliminarono(false);
                FacesContext context = FacesContext.getCurrentInstance();
                eliminarComfirm();
            

                if (selectedUsuarios.size() == 0) {
                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    RequestContext.getCurrentInstance().execute("dlg.hide();");

                }

            }

        } //modificar/alta
        else {
            try {

                String mensaj = "";
                if (u.getUsuusuario().isEmpty() || u.getUsucontrasenia().isEmpty()) {
                    if (u.getUsucontrasenia().isEmpty()) {
                        mensaj = "Contraseña";
                    }
                    if (u.getUsuusuario().isEmpty()) {
                        mensaj = "Usuario";
                    }
                    if (u.getUsuusuario().isEmpty() && u.getUsucontrasenia().isEmpty()) {
                        mensaj = "Usuario y contraseña";
                    }
                    
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Llenar campo "+ mensaj,"" ));
                    

                } else {

                    setRols = new HashSet();
                    logBeanHelp = new LoginBeanHelper();

                    for (String rolesDelUsuario : obtenerRoles) {

                        for (Rol listRolAll : obtenerListaRoles) {
                            if (listRolAll.getRoltipo().equalsIgnoreCase(rolesDelUsuario)) {
                                System.out.println("ID's de los roles que se van a asignar:" + listRolAll.getRolid());
                                setRols.add(listRolAll);
                            }
                        }
                    }

                    if (idUsu != -1) {
                        System.out.println("OnClick se activó");
                        Usuario usMod = logBeanHelp.getUsuDel().usuUnic(idUsu);
                        System.out.println("NuevoUsuario: " + usMod.getUsuusuario());
                        usMod.setRols(setRols);
                        usMod.setUsuusuario(u.getUsuusuario());
                        if (!u.getUsucontrasenia().equalsIgnoreCase(usMod.getUsucontrasenia())) {
                            usMod.setUsucontrasenia(DigestUtils.md5Hex(u.getUsucontrasenia()));

                        }
                        logBeanHelp.getUsuDel().saveUsuario(usMod);
        List<Usuario> L;
        L=selectedUsuarios;
        System.out.println(usMod.getUsuusuario()+"_______________________________"+usMod.getUsuid());
        for(int user=0;user<selectedUsuarios.size();user++){
            if(selectedUsuarios.get(user).getUsuid()== usMod.getUsuid()){
                System.out.println("se cumplio la condicion");
                selectedUsuarios.remove(user);
                selectedUsuarios.add(usMod);
            }
            
        }
                               
                        
                        
                        FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Se guardó correctamente.", ""));
            
            if (selectedUsuarios.size() == 1) {
                    RequestContext.getCurrentInstance().execute("dlg.hide();");

                }
                    } else {
                        u.setRols(setRols);
                        u.setUsucontrasenia(DigestUtils.md5Hex(u.getUsucontrasenia()));
                        
                        logBeanHelp.getUsuDel().saveUsuario(u);
                        FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Se guardó correctamente.", ""));
            u = new Usuario();
            
                    }
                    idUsu = -1;
                }

            } catch (org.hibernate.AssertionFailure e) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Usuario ya existente");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                u = new Usuario();
            }
        }
        listaFiltrada = logBeanHelp.getUsuDel().getUsuario();
        
        /////if(selectedUsuarios.size()==1)setSelectedUsuarios(null);
            
            
            
            
        
        
        ////
    }

    public void nuevo() {
        multiSeleccion = false;
        idUsu = -1;
//        capturaUsuarioBeanHelper.setUsuario(u);
        u = new Usuario();
        obtenerRoles = new ArrayList();
        selectedUsuarios = new ArrayList();
        selectedUsuario = new Usuario();
        deshabilitar = "false";
        // logBeanHelp.getUsuDel().saveUsuario(u);
        cabecera(1);
    }

    public String eliminar() {
        
        if (selectedUsuarios.size() > 1) {
            multiSeleccion = true;
        } else {
            multiSeleccion = false;
        }
        String result3 = "UsuarioEliminado";
        cabecera(2);
        dialogo = "confirmation.show()";
        setEliminarono(false);
        eliminarono = false;
        setEliminaa(false);
        eliminaa = false;
        if(selectedUsuarios.size()>1){
        u=new Usuario();
        }
        
        setBusqueda("");
        return result3;
    }
  

    public void modificar() {
        Usuario n2=new Usuario();
        System.out.println(usuario.getUsuusuario()+"______________________-modificar");
        System.out.println(usuario.getUsuid()+"______________________-modificar");
        if(usuario.getUsuid()==0){
            usuario.setUsuid(idLogeado);
        }

        if (selectedUsuarios.size() > 1) {
            multiSeleccion = true;
        } else {
            multiSeleccion = false;
        }

        if (selectedUsuarios.size() == 1) {
            n2 = selectedUsuarios.get(0);
            System.out.println(u.getUsuid()+ "<><><><>>><>>>>>><><>><>>");
        }
        cabecera(3);
        dialogo = "confirmation.show()";
        idUsu = u.getUsuid();
        List<Usuario> l;
        l = selectedUsuarios;

        if (l.size() == 1) {
            idUsu = l.get(0).getUsuid();
        }
        
        if(selectedUsuarios.size()>1){
        u=new Usuario();
        }
        System.out.println(usuario.getUsuusuario()+"______________________-modificar");
        System.out.println(usuario.getUsuid()+"______________________-modificar");
        setBusqueda("");

    }

    public String getDeshabilitar() {
        return deshabilitar;
    }

    public void setDeshabilitar(String deshabilitar) {
        this.deshabilitar = deshabilitar;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String cabecera(int i) {
        if (i == 1) {
            header = "Agregar Usuario";
            deshabilitar = "false";
        }
        if (i == 2) {
            header = "Eliminar Usuario";
            deshabilitar = "true";
        }
        if (i == 3) {
            header = "Modificar Usuario";
            deshabilitar = "false";
        }
        return header;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public LoginBeanHelper getLogBeanHelp() {
        return logBeanHelp;
    }

    //Se obtienen los roles del helper, si U no es nulo va a y consulta especificamente del objeto guardad
    //en u pero si u es nulo consulta todos los roles guardados en la base de datos.
    public List<String> getObtenerRoles() {
        logBeanHelp = new LoginBeanHelper(u);

        if (u != null && u.getUsuid() != null) {
            obtenerRoles = logBeanHelp.getListarol(u);
            return obtenerRoles;
        } else {

            for (Rol roles : logBeanHelp.consultaroles()) {
                obtenerRoles.add(roles.getRoltipo());
            }
            return obtenerRoles;
        }
    }

    public void setObtenerRoles(List<String> obtenerRoles) {
        this.obtenerRoles = obtenerRoles;
    }

    public List<Rol> getObtenerListaRoles() {
        obtenerListaRoles = logBeanHelp.getListaRol();
        return obtenerListaRoles;
    }

    public void refrescarForma() {
        ////   u = null;
        u = new Usuario();
        setSelectedUsuarios(null);
        obtenerRoles = null;
        System.out.println("U setter null");
        boton="true";
        
        
    }
    public void refrescarForma2() {
        ////   u = null;
        if(selectedUsuarios.size()==1)
        boton="true";    
        
        
        
        
    }

    public String getDialogo() {
        return dialogo;
    }

    public void setDialogo(String dialogo) {
        this.dialogo = dialogo;
    }

    public String rolesStrList(Usuario usuRol) {
        String rolsUsuEsp = "";
        System.out.println("El usuario: " + usuRol.getUsuusuario());
        rolsUsuEsp = "";
        List<String> lista = null;
        lista = logBeanHelp.getListarol(usuRol);
        if (lista.isEmpty()) {
            return "No tiene roles asignados.";
        } else {
            for (int i = 0; i < lista.size(); i++) {
                if (i == (lista.size()) - 1) {
                    System.out.println("Profesor: " + usuRol.getUsuusuario() + " Roles: " + lista.get(i) + "<<<<<<<<");
                    rolsUsuEsp += lista.get(i) + ".";
                } else {
                    System.out.println("Profesor: " + usuRol.getUsuusuario() + " Roles: " + lista.get(i) + "<<<<<<<<");
                    rolsUsuEsp += lista.get(i) + ", ";
                }
            }
            return rolsUsuEsp;

        }

    }

    public String profDeUsu(int usuProf) {
        Profesor usProfesor = logBeanHelp.getProfDel().profIDUs(usuProf);
        if (usProfesor != null) {
            return usProfesor.getPronumeroEmpleado() + " - " + usProfesor.getPronombre() + " " + usProfesor.getProapellidoPaterno() + " " + usProfesor.getProapellidoMaterno() + ".";

        } else {
            return "No tiene un profesor asignado.";
        }
    }

    private List<Usuario> listaFiltrada = logBeanHelp.getUsuDel().getUsuario();

    public void filtrado() {
        System.out.println("Si entra<<<<<<<<<<<<<<<");
        listaFiltrada = logBeanHelp.filtrado(busqueda);
        if (listaFiltrada.isEmpty()) {
            listaFiltrada = new ArrayList();
        }
    }

    public List<Usuario> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Usuario> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    //------------------------------------------fin CapturaUsuarioBeanUI---------------------------
}
