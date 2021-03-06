package mx.avanti.siract.business.entity;
// Generated 17-ago-2015 14:50:31 by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Configuracion generated by hbm2java
 */
public class Configuracion  implements java.io.Serializable {


     private Integer conid;
     private Cicloescolar cicloescolar;
     private Alerta alerta;
     private Date confechaInicioSemestre;
     private int connumeroSemanas;
     private Set calendarioreportes = new HashSet(0);

    public Configuracion() {
    }

	
    public Configuracion(Date confechaInicioSemestre, int connumeroSemanas) {
        this.confechaInicioSemestre = confechaInicioSemestre;
        this.connumeroSemanas = connumeroSemanas;
    }
    public Configuracion(Cicloescolar cicloescolar, Alerta alerta, Date confechaInicioSemestre, int connumeroSemanas, Set calendarioreportes) {
       this.cicloescolar = cicloescolar;
       this.alerta = alerta;
       this.confechaInicioSemestre = confechaInicioSemestre;
       this.connumeroSemanas = connumeroSemanas;
       this.calendarioreportes = calendarioreportes;
    }
   
    public Integer getConid() {
        return this.conid;
    }
    
    public void setConid(Integer conid) {
        this.conid = conid;
    }
    public Cicloescolar getCicloescolar() {
        return this.cicloescolar;
    }
    
    public void setCicloescolar(Cicloescolar cicloescolar) {
        this.cicloescolar = cicloescolar;
    }
    public Alerta getAlerta() {
        return this.alerta;
    }
    
    public void setAlerta(Alerta alerta) {
        this.alerta = alerta;
    }
    public Date getConfechaInicioSemestre() {
        return this.confechaInicioSemestre;
    }
    
    public void setConfechaInicioSemestre(Date confechaInicioSemestre) {
        this.confechaInicioSemestre = confechaInicioSemestre;
    }
    public int getConnumeroSemanas() {
        return this.connumeroSemanas;
    }
    
    public void setConnumeroSemanas(int connumeroSemanas) {
        this.connumeroSemanas = connumeroSemanas;
    }
    public Set getCalendarioreportes() {
        return this.calendarioreportes;
    }
    
    public void setCalendarioreportes(Set calendarioreportes) {
        this.calendarioreportes = calendarioreportes;
    }




}


