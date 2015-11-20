package mx.avanti.siract.business.entity;
// Generated 17-ago-2015 14:50:31 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Grupo generated by hbm2java
 */
public class Grupo  implements java.io.Serializable {


     private Integer gpoid;
     private Planestudio planestudio;
     private int gponumero;
     private Set unidadaprendizajeImparteProfesors = new HashSet(0);

    public Grupo() {
    }

	
    public Grupo(int gponumero) {
        this.gponumero = gponumero;
    }
    public Grupo(Planestudio planestudio, int gponumero, Set unidadaprendizajeImparteProfesors) {
       this.planestudio = planestudio;
       this.gponumero = gponumero;
       this.unidadaprendizajeImparteProfesors = unidadaprendizajeImparteProfesors;
    }
   
    public Integer getGpoid() {
        return this.gpoid;
    }
    
    public void setGpoid(Integer gpoid) {
        this.gpoid = gpoid;
    }
    public Planestudio getPlanestudio() {
        return this.planestudio;
    }
    
    public void setPlanestudio(Planestudio planestudio) {
        this.planestudio = planestudio;
    }
    public int getGponumero() {
        return this.gponumero;
    }
    
    public void setGponumero(int gponumero) {
        this.gponumero = gponumero;
    }
    public Set getUnidadaprendizajeImparteProfesors() {
        return this.unidadaprendizajeImparteProfesors;
    }
    
    public void setUnidadaprendizajeImparteProfesors(Set unidadaprendizajeImparteProfesors) {
        this.unidadaprendizajeImparteProfesors = unidadaprendizajeImparteProfesors;
    }




}

