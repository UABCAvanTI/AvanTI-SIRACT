/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.List;
import mx.avanti.siract.business.RolDelegate;
import mx.avanti.siract.business.SubPermisoDelegate;
import mx.avanti.siract.business.entity.Rol;
import mx.avanti.siract.business.entity.Subpermisos;

/**
 *
 * @author Usagi
 */
public class NewClass {
    
    
    public static void main(String[] args) {
        ///SubPermisoDelegate spd=new SubPermisoDelegate();
        RolDelegate rdl=new RolDelegate();
        Rol r=new Rol();
        r.setRolprioridad(20);
        r.setRoltipo("nuevo");
        rdl.saveRol(r);
//        List<Rol> list=rdl.getRol();
//        for(Rol r:list){
//            System.out.println("Rol "+r.getRoltipo());
//        }
    }
}
