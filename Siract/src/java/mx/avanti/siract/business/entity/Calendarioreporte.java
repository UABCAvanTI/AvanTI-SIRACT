package mx.avanti.siract.business.entity;
// Generated 17-ago-2015 14:50:31 by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Calendarioreporte generated by hbm2java
 */
public class Calendarioreporte  implements java.io.Serializable {


     private Integer creid;
     private Date crefechaCorte;
     private Date crefechaLimite;
     private Set calendarioreporteTieneAlertas = new HashSet(0);
     private Set configuracions = new HashSet(0);

    public Calendarioreporte() {
    }

	
    public Calendarioreporte(Date crefechaCorte, Date crefechaLimite) {
        this.crefechaCorte = crefechaCorte;
        this.crefechaLimite = crefechaLimite;
    }
    public Calendarioreporte(Date crefechaCorte, Date crefechaLimite, Set calendarioreporteTieneAlertas, Set configuracions) {
       this.crefechaCorte = crefechaCorte;
       this.crefechaLimite = crefechaLimite;
       this.calendarioreporteTieneAlertas = calendarioreporteTieneAlertas;
       this.configuracions = configuracions;
    }
   
    public Integer getCreid() {
        return this.creid;
    }
    
    public void setCreid(Integer creid) {
        this.creid = creid;
    }
    public Date getCrefechaCorte() {
        return this.crefechaCorte;
    }
    
    public void setCrefechaCorte(Date crefechaCorte) {
        this.crefechaCorte = crefechaCorte;
    }
    public Date getCrefechaLimite() {
        return this.crefechaLimite;
    }
    
    public void setCrefechaLimite(Date crefechaLimite) {
        this.crefechaLimite = crefechaLimite;
    }
    public Set getCalendarioreporteTieneAlertas() {
        return this.calendarioreporteTieneAlertas;
    }
    
    public void setCalendarioreporteTieneAlertas(Set calendarioreporteTieneAlertas) {
        this.calendarioreporteTieneAlertas = calendarioreporteTieneAlertas;
    }
    public Set getConfiguracions() {
        return this.configuracions;
    }
    
    public void setConfiguracions(Set configuracions) {
        this.configuracions = configuracions;
    }




}


