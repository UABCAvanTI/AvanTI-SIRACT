package mx.avanti.siract.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import mx.avanti.siract.application.helper.RolesBeanHelper;
import mx.avanti.siract.business.entity.Permiso;
import mx.avanti.siract.business.entity.Rol;
import mx.avanti.siract.business.entity.RolHasPermiso;
import mx.avanti.siract.business.entity.RolHasPermisoId;
import mx.avanti.siract.business.entity.Subpermisos;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean
@SessionScoped
public class RolesBeanUI implements Serializable {

    private List<String> listPermisos = new ArrayList();
    private List<String> listaPermisosSelected = new ArrayList();
    Rol rolObj = new Rol();
    
    private RolesBeanHelper rolBeanHelp = new RolesBeanHelper();
    private String boton;
    private String deshabilitar = "false";
    private String header;
    private String ModEl = "true";
    private String nombreRol;

    public void init() {
        listPermisos = new ArrayList();
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
    Rol rolAux = new Rol();

    public Rol getRolAux() {
        return rolAux;
    }

    public void setRolAux(Rol rolAux) {
        this.rolAux = rolAux;
    }

    public String onRowSelect(SelectEvent event) {
        rolAux = (Rol) event.getObject();
        ModEl = "false";

        return "";
    }

    public String noRowUnselect(UnselectEvent event) {
        ModEl = "true";

        return "";
    }

    public List<String> getListPermisos() {
        listPermisos = new ArrayList();

        for (Permiso perm : rolBeanHelp.getPermisoDelegete().getPermiso()) {
            for (Subpermisos subperm : rolBeanHelp.getSpDel().getPermiso()) {
                Permiso aux = new Permiso();
                if (!perm.getPertipo().equals("Actualizar porcentaje de contenido temático") || !subperm.getSpertipo().equals("Eliminación")) {
                    listPermisos.add(perm.getPertipo() + " - " + subperm.getSpertipo());
                }
            }
        }

        return listPermisos;
    }

    public void setListPermisos(List<String> listPermisos) {
        this.listPermisos = listPermisos;
    }

    public List<String> getListaPermisosSelected() {
        listaPermisosSelected = new ArrayList();
        if (rolObj != null) {
            listaPermisosSelected = rolBeanHelp.getListaPerm(rolObj);
        }
        return listaPermisosSelected;
    }

    public void setListaPermisosSelected(List<String> listaPermisosSelected) {
        this.listaPermisosSelected = listaPermisosSelected;
    }

    public Rol getRolObj() {
        if (rolObj == null) {
            rolObj = new Rol();
        }
        return rolObj;
    }

    public void setRolObj(Rol rolObj) {
        this.rolObj = rolObj;
    }
    
    public void refrescarForma() {
        ////   u = null;
        
        rolObj=new Rol();
        setRolObj(null);
        
        
        
    }


    public String getBoton() {
        return boton;
    }

    public void setBoton(String boton) {
        this.boton = boton;
    }

    public String permStrList(Rol rolPerm) {
        String rolsPermEsp = "";
        // System.out.println("El rol es: " + rolPerm.getRoltipo());
        rolsPermEsp = "";
        List<String> lista = null;
        lista = (List<String>) rolBeanHelp.getListaPerm(rolPerm);
        if (lista.isEmpty()) {
            boton = "visibility: hidden";

            return "No tiene permisos asignados.";

        } else {
            boton = "visibility: visible";

            return "Consultar los permisos del rol: ";

        }

    }

    boolean ban = false;

    public void eliminarConfirm() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>eliminarConfirm" + rolEntero);
        if (rolEntero != -1) {
            ban = true;
            onClick();

        }
    }

    public void onClick() {
        if (deshabilitar.equals("true")) {
            RequestContext.getCurrentInstance().execute("statusDialog.hide()");
            RequestContext.getCurrentInstance().execute("confirmdlg.show();");
            if (ban) {
                ban = false;
                rolBeanHelp.getRolHasPermisoDel().deleteRolHasPermiso("RolHasPermiso", "rol.rolid", String.valueOf(rolEntero));
                Rol aux = rolBeanHelp.getRolDelegate().getRolUnico(rolEntero);
                rolBeanHelp.getRolDelegate().eliminarRol(aux);

                nombreRol = null;
                deshabilitar = "false";
                rolEntero = -1;
                rolObj = new Rol();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Se eliminó correctamente.", "Se eliminó correctamente."));
            }
            rolObj = new Rol();
            listaFiltrada = rolBeanHelp.getRolDelegate().getRol();
        } else {
            boolean guardar = true;
            for (Rol rolDel : rolBeanHelp.getRolDelegate().getRol()) {
                if (rolDel.getRoltipo().equalsIgnoreCase(rolObj.getRoltipo())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Rol existente");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                    RequestContext.getCurrentInstance().execute("statusDialog.hide()");
                    guardar = false;
                }
            }
            if (rolObj.getRoltipo().isEmpty()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Llenar campo Rol");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                RequestContext.getCurrentInstance().execute("statusDialog.hide()");
            } else {
                for (Rol rolDel : rolBeanHelp.getRolDelegate().getRol()) {
                    if (rolDel.getRoltipo().equals(nombreRol)) {
                        rolObj.setRolid(rolDel.getRolid());
                        rolObj.setRolprioridad(rolDel.getRolprioridad());
                        rolObj.setRolHasPermisos(rolDel.getRolHasPermisos());
                        System.out.println("Entre en nuevo" + rolObj.getRolid());
                    }
                }
                if (guardar) {
                    rolBeanHelp.getRolDelegate().saveRol(rolObj);
                }
                for (Rol rolDel : rolBeanHelp.getRolDelegate().getRol()) {
                    if (rolDel.getRoltipo().equals(nombreRol)) {
                        rolObj.setRolid(rolDel.getRolid());
                        rolObj.setRolprioridad(rolDel.getRolprioridad());
                        rolObj.setRolHasPermisos(rolDel.getRolHasPermisos());
                        System.out.println("Entre en nuevo" + rolObj.getRolid());
                    }
                }

                //Se eliminan permisos previos del rol
                rolBeanHelp.getRolHasPermisoDel().deleteRolHasPermiso("RolHasPermiso", "rol.rolid", String.valueOf(rolObj.getRolid()));
                RolHasPermisoId rolHasPerID = new RolHasPermisoId();
                int sumaPermisos = 0;
                for (String permisosList : listaPermisosSelected) {
                    String[] auxArray = permisosList.split(" - ");
                    for (Permiso auxPerm : rolBeanHelp.getPermisoDelegete().getPermiso()) {
                        if (auxPerm.getPertipo().equals(auxArray[0])) {
                            rolHasPerID.setPermisoPerid(auxPerm.getPerid());
                            sumaPermisos += auxPerm.getPervalor();
                        }
                    }
                    for (Subpermisos auxSperm : rolBeanHelp.getSpDel().getPermiso()) {
                        if (auxSperm.getSpertipo().equals(auxArray[1])) {
                            rolHasPerID.setSubpermisosSperid(auxSperm.getSperid());
                            sumaPermisos += auxSperm.getSpervalor();
                        }
                    }

                    rolHasPerID.setRolRolid(rolObj.getRolid());
                    RolHasPermiso rolHasPer = new RolHasPermiso();
                    rolHasPer.setId(rolHasPerID);//se añaden permisos y subpermisos al rol
                    rolBeanHelp.getRolHasPermisoDel().saveRolHasPermiso(rolHasPer);//se guardan los permisos y subperidos

                }
                rolObj.setRolprioridad(sumaPermisos);//se le asigna prioridad

                if (guardar) {
                    rolBeanHelp.getRolDelegate().saveRol(rolObj);
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Se guardó correctamente.", "Se guardó correctamente."));
                    rolObj = new Rol();
                    RequestContext.getCurrentInstance().execute("window.location.replace(window.location.href='Roles.xhtml');");
                }

            }

        }
        rolObj = null;
        listaFiltrada = rolBeanHelp.getRolDelegate().getRol();
    }

    public String nuevo() {
        rolObj = null;
        listPermisos = new ArrayList();
        listaPermisosSelected = new ArrayList();
        header = "Capturar Rol";
        deshabilitar = "false";

        return "";
    }

    public String eliminar() {
        String result3 = "UsuarioEliminado";
        header = "Eliminar Rol";
        deshabilitar = "true";
        nombreRol = rolObj.getRoltipo();
        rolEntero = rolObj.getRolid();

        return result3;
    }
    int rolEntero = -1;

    public String modificar() {
        header = "Modificar Rol";
        deshabilitar = "false";

//        rolEntero = rolObj.getRolid();
        nombreRol = rolObj.getRoltipo();
        if (rolEntero != 0) {
            rolBeanHelp.getRolDelegate().saveRol(rolObj);
        }
        return "";
    }

    private TreeNode root;

    public TreeNode getRoot() {
        this.root = new DefaultTreeNode("Root Node", null);
        int auxList = rolBeanHelp.getRolDelegate().getRol().size();

        TreeNode permisosNod = null;
        TreeNode subpermisosNod = null;
        if (rolAux != null && rolAux.getRolid() != null) {
            List<Permiso> lista = rolBeanHelp.getPermisoDelegete().getPermisoUser(rolAux.getRolid());
            if (!lista.isEmpty()) {
                for (Permiso perm : lista) {
                    permisosNod = new DefaultTreeNode(perm.getPertipo(), this.root);
                    for (Subpermisos subp : rolBeanHelp.getSpDel().getPermisoID(rolAux.getRolid(), perm.getPerid())) {
                        subpermisosNod = new DefaultTreeNode(subp.getSpertipo(), permisosNod);
                    }

                }
            }
        }
        RequestContext.getCurrentInstance().execute("statusDialog.hide()");
        rolObj = new Rol();
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    private String busqueda;
    private List<Rol> listaFiltrada = rolBeanHelp.getRolDelegate().getRol();

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public void filtrado() {
        System.out.println("Si entra [" + busqueda + "]");
        listaFiltrada = rolBeanHelp.filtrado(busqueda);
        if (listaFiltrada.isEmpty()) {
            listaFiltrada = new ArrayList();
        }
        System.out.println("Llego al final del método");
    }

    public List<Rol> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Rol> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public RolesBeanHelper getRolBeanHelp() {
        return rolBeanHelp;
    }

    public void setRolBeanHelp(RolesBeanHelper rolBeanHelp) {
        this.rolBeanHelp = rolBeanHelp;
    }

    public void resetForm() {
        rolObj = null;
        rolObj = new Rol();
        listaPermisosSelected = new ArrayList();
        ModEl = "true";
    }

    public String getModEl() {
        return ModEl;
    }

    public void setModEl(String ModEl) {
        this.ModEl = ModEl;
    }

}
