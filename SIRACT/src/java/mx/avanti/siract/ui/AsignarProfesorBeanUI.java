/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.ui;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.avanti.siract.application.helper.AsignarProfesorBeanHelper;

/**
 *
 * @author Samanta Rdgz
 */
@ManagedBean
@ViewScoped
public class AsignarProfesorBeanUI implements Serializable {

    private AsignarProfesorBeanHelper asignarProfesorBeanHelper;

    public AsignarProfesorBeanUI() {
        init();
    }

    private void init() {
        asignarProfesorBeanHelper = new AsignarProfesorBeanHelper();
    }

    public AsignarProfesorBeanHelper getAsignarProfesorBeanHelper() {
        return asignarProfesorBeanHelper;
    }

    public void setAsignarProfesorBeanHelper(AsignarProfesorBeanHelper asignarProfesorBeanHelper) {
        this.asignarProfesorBeanHelper = asignarProfesorBeanHelper;
    }

    public String onClickSubmit() {
        String resultado = "";
        asignarProfesorBeanHelper.asignar();
        init();
        return resultado;
    }

}
