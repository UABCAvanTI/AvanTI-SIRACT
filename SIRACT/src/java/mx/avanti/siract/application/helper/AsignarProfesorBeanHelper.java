/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mx.avanti.siract.business.AreaConocimientoDelegate;
import mx.avanti.siract.business.AsignarProfesorDelegate;
import mx.avanti.siract.business.PlanEstudioDelegate;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Programaeducativo;

/**
 *
 * @author Samanta Rdgz
 */
public class AsignarProfesorBeanHelper implements Serializable {

    private AsignarProfesorDelegate asignarProfesorDeleagate;
    private Profesor selecProfesor;
    private Areaconocimiento areac;
    private Profesor[] profesoresSeleccionados;
    private Areaconocimiento[] areasSeleccionados;
    //CRITERIA
    private Planestudio planestudiocriteria;
    private Areaconocimiento areaconocimientocriteria;
    private Programaeducativo programaeducativocriteria;
    private Profesor profesorcriteria;

    public AsignarProfesorBeanHelper() {
        try {
            this.asignarProfesorDeleagate = new AsignarProfesorDelegate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        selecProfesor = new Profesor();
        areac = new Areaconocimiento();
        
        //CRITERIA
        planestudiocriteria = new Planestudio();
        planestudiocriteria.setPesid(0);
        areaconocimientocriteria = new Areaconocimiento();
        areaconocimientocriteria.setAcoid(0);
        programaeducativocriteria = new Programaeducativo();
        programaeducativocriteria.setPedid(0);
        profesorcriteria = new Profesor();
        profesorcriteria.setProid(0);
    }
        
    public AsignarProfesorDelegate getAsignarProfesorDeleagate() {
        return asignarProfesorDeleagate;
    }

//    public void setAsignarProfesorDeleagate(AsignarProfesorDelegate asignarProfesorDeleagate) {
//        this.asignarProfesorDeleagate = asignarProfesorDeleagate;
//    }
    public Profesor getSelecProfesor() {
        return selecProfesor;
    }

    public void setSelecProfesor(Profesor selecProfesor) {
        this.selecProfesor = selecProfesor;
    }

    public Areaconocimiento getAreac() {
        return areac;
    }

    public void setAreac(Areaconocimiento areac) {
        this.areac = areac;
    }

    public Profesor[] getProfesoresSeleccionados() {
        return profesoresSeleccionados;
    }

    public void setProfesoresSeleccionados(Profesor[] profesoresSeleccionados) {
        this.profesoresSeleccionados = profesoresSeleccionados;
    }

    public Areaconocimiento[] getAreasSeleccionados() {
        return areasSeleccionados;
    }

    public void setAreasSeleccionados(Areaconocimiento[] areasSeleccionados) {
        this.areasSeleccionados = areasSeleccionados;
    }

    public Profesor[] asignar() {
        Profesor prof[] = getProfesoresSeleccionados();
        Areaconocimiento areacon[] = getAreasSeleccionados();
        for (int x = 0; x < prof.length; x++) {
            System.out.println("profesor: " + prof[x].getPronombre());
  //prof[x].getAreaconocimientos().addAll(Arrays.asList(areacon));//es lo mismo que el for de la sig. linea
             
            /*for (int i = 0; i < areacon.length; i++) {
             prof[x].getAreaconocimientos().add(areacon[i]);
             }*/
        }
        for (int x = 0; x < prof.length; x++) {
            try {
                asignarProfesorDeleagate.agregarProfesor(prof[x]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return prof;
    }

    //CRITERIA
    public Planestudio getPlanestudiocriteria() {
        return planestudiocriteria;
    }

    public void setPlanestudiocriteria(Planestudio planestudiocriteria) {
        this.planestudiocriteria = planestudiocriteria;
    }

    public Areaconocimiento getAreaconocimientocriteria() {
        return areaconocimientocriteria;
    }

    public void setAreaconocimientocriteria(Areaconocimiento areaconocimientocriteria) {
        this.areaconocimientocriteria = areaconocimientocriteria;
    }

    public Programaeducativo getProgramaeducativocriteria() {
        return programaeducativocriteria;
    }

    public void setProgramaeducativocriteria(Programaeducativo programaeducativocriteria) {
        this.programaeducativocriteria = programaeducativocriteria;
    }

    public Profesor getProfesorcriteria() {
        return profesorcriteria;
    }

    public void setProfesorcriteria(Profesor profesorcriteria) {
        this.profesorcriteria = profesorcriteria;
    }

    public List<Planestudio> getPlanesByPrograma() {
        if (programaeducativocriteria.getPedid() != 0) {
            planestudiocriteria.setPesid(0);
            areaconocimientocriteria.setAcoid(0);
            return getAsignarProfesorDeleagate().getPlanesByPrograma(programaeducativocriteria.getPedid());
        } else {
            planestudiocriteria.setPesid(0);
            return new ArrayList<Planestudio>();
        }
    }

//    public List<Areaconocimiento> getAreasByPlan() {
//        if (planestudiocriteria.getPesid() != 0) {
//            areaconocimientocriteria.setAcoid(0);
//            return getAsignarProfesorDeleagate().getAreasByPlan(planestudiocriteria.getPesid());
//        } else {
//            areaconocimientocriteria.setAcoid(0);
//            return new ArrayList<Areaconocimiento>();
//        }
//    }
    
//    public List<Profesor> getProfesorByPrograma() {
//        if (programaeducativocriteria.getPedid() != 0) {
//            profesorcriteria.setProid(0);
//            return getAsignarProfesorDeleagate().getProfesoresByPrograma(programaeducativocriteria.getPedid());
//        } else {
//            planestudiocriteria.setPesid(0);
//            return new ArrayList<Profesor>();
//        }
//    }
    
    public List<Profesor> getProfesor(){
        if(programaeducativocriteria.getPedid() != 0){
            profesorcriteria.setProid(0);
            return getAsignarProfesorDeleagate().getProfesor(programaeducativocriteria.getPedid());
        } else {
            profesorcriteria.setProid(0);
            return new ArrayList<Profesor>();
        }
    }
}
