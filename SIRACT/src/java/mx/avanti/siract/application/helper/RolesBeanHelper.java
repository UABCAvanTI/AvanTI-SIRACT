/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import mx.avanti.siract.business.PermisoDelegate;
import mx.avanti.siract.business.ProfesorDelegate;
import mx.avanti.siract.business.RolDelegate;
import mx.avanti.siract.business.RolHasPermisoDelegate;
import mx.avanti.siract.business.SubPermisoDelegate;
import mx.avanti.siract.business.UsuarioDelegate;
import mx.avanti.siract.business.entity.Permiso;
import mx.avanti.siract.business.entity.Rol;
import mx.avanti.siract.business.entity.Subpermisos;
import mx.avanti.siract.business.entity.Usuario;

/**
 *
 * @author Usagi
 */
public class RolesBeanHelper implements Serializable {

    Rol rol;
    Usuario usuario;
    RolDelegate rolDelegate = new RolDelegate();
    private List<String> listarol = new ArrayList();
    List<Rol> rolesID = new ArrayList();
    UsuarioDelegate usuDel = new UsuarioDelegate();
    ProfesorDelegate profDel = new ProfesorDelegate();
    PermisoDelegate permisoDelegete = new PermisoDelegate();
    SubPermisoDelegate spDel = new SubPermisoDelegate();
    RolHasPermisoDelegate rolHasPermisoDel = new RolHasPermisoDelegate();

    public List<String> getListaPerm(Rol rolSelected) {
        List<Permiso> recibe = null;//Creacion de recibe 
        listarol = new ArrayList();
        if (rolSelected.getRolid() != null) {
            System.out.println("El Rol es: " + rolSelected.getRoltipo());
            recibe = permisoDelegete.getPermisoUser(rolSelected.getRolid());
            if (!recibe.isEmpty()) {
                for (Permiso perm : recibe) {
                    for (Subpermisos subp : spDel.getPermisoID(rolSelected.getRolid(), perm.getPerid())) {
                        listarol.add(perm.getPertipo() + " - " + subp.getSpertipo());
                    }
                }
            }
        } else {
            recibe = permisoDelegete.getPermiso();

            for (Permiso perm : recibe) {
                listarol.add(perm.getPertipo());
            }
        }
        return listarol;

    }

    public List<String> getListaSubperm(Rol rolSelected) {
        List<Subpermisos> recibe = null;//Creacion de recibe 
        // LoginBean logBean = new LoginBean();
        listarol = new ArrayList();
        if (rolSelected.getRolid() != null) {
            System.out.println("El Rol es: " + rolSelected.getRoltipo());
//            recibe = permisoDelegete.getPermisoUser(rolSelected.getRolid());
            recibe = spDel.getPermisoUser(rolSelected.getRolid());
            for (Subpermisos subp : recibe) {
//                System.out.println("Estos son los permisos del rol? " + subp.getSpertipo());
                listarol.add(subp.getSpertipo());
            }

        } else {
            recibe = spDel.getPermiso();

            for (Subpermisos subp : recibe) {
//                System.out.println("Estos son los permisos del rol? " + subp.getSpertipo());
                listarol.add(subp.getSpertipo());
            }
        }
        return listarol;

    }
    List<Rol> listaFiltrada;

    public List<Rol> filtrado(String busqueda) {
        busqueda = busqueda.toLowerCase();
        listaFiltrada = rolDelegate.getRol();

        if (busqueda.trim().length() > 0) {
            listaFiltrada.clear();
            for (Rol rolB : rolDelegate.getRol()) {
                String rolTipo = rolB.getRoltipo().toLowerCase();
                if (rolTipo.startsWith(busqueda)) {
                    System.out.println(">>>>>>>>>>>>>>>Rol:" + rolB.getRoltipo());
                    if (!listaFiltrada.contains(rolB)) {
                        listaFiltrada.add(rolB);
                    }
                }
//                for (Permiso permRolB : permisoDelegete.getPermisoUser(rolB.getRolid())) {
//                    String permipo = permRolB.getPertipo().toLowerCase();
//                    if (permipo.startsWith(busqueda)) {
////                        System.out.println("Rol para comparar" + rolUsB.getRoltipo());
//                        System.out.println(">>>>>>>>>>>>>>>Perm:" + rolTipo);
//                        if (!listaFiltrada.contains(rolB)) {
//                            listaFiltrada.add(rolB);
//                        }
//                    }
//                }

            }

        }
        return listaFiltrada;
    }

    public PermisoDelegate getPermisoDelegete() {
        return permisoDelegete;
    }

    public void setPermisoDelegete(PermisoDelegate permisoDelegete) {
        this.permisoDelegete = permisoDelegete;
    }

    public SubPermisoDelegate getSpDel() {
        return spDel;
    }

    public void setSpDel(SubPermisoDelegate spDel) {
        this.spDel = spDel;
    }
    
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public RolDelegate getRolDelegate() {
        return rolDelegate;
    }

    public void setRolDelegate(RolDelegate rolDelegate) {
        this.rolDelegate = rolDelegate;
    }

    public List<String> getListarol() {
        return listarol;
    }

    public void setListarol(List<String> listarol) {
        this.listarol = listarol;
    }

    public List<Rol> getRolesID() {
        return rolesID;
    }

    public void setRolesID(List<Rol> rolesID) {
        this.rolesID = rolesID;
    }

    public UsuarioDelegate getUsuDel() {
        return usuDel;
    }

    public void setUsuDel(UsuarioDelegate usuDel) {
        this.usuDel = usuDel;
    }

    public ProfesorDelegate getProfDel() {
        return profDel;
    }

    public void setProfDel(ProfesorDelegate profDel) {
        this.profDel = profDel;
    }

    public RolHasPermisoDelegate getRolHasPermisoDel() {
        return rolHasPermisoDel;
    }

    public void setRolHasPermisoDel(RolHasPermisoDelegate rolHasPermisoDel) {
        this.rolHasPermisoDel = rolHasPermisoDel;
    }

}
