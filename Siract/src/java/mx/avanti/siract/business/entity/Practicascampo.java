package mx.avanti.siract.business.entity;
// Generated 17-ago-2015 14:50:31 by Hibernate Tools 3.6.0



/**
 * Practicascampo generated by hbm2java
 */
public class Practicascampo  implements java.io.Serializable {


     private Integer prcid;
     private Unidadaprendizaje unidadaprendizaje;
     private int prcnumero;
     private String prcnombre;
     private float prcvalorPorcentaje;
     private Float prchoras;

    public Practicascampo() {
    }

	
    public Practicascampo(Unidadaprendizaje unidadaprendizaje, int prcnumero, String prcnombre, float prcvalorPorcentaje) {
        this.unidadaprendizaje = unidadaprendizaje;
        this.prcnumero = prcnumero;
        this.prcnombre = prcnombre;
        this.prcvalorPorcentaje = prcvalorPorcentaje;
    }
    public Practicascampo(Unidadaprendizaje unidadaprendizaje, int prcnumero, String prcnombre, float prcvalorPorcentaje, Float prchoras) {
       this.unidadaprendizaje = unidadaprendizaje;
       this.prcnumero = prcnumero;
       this.prcnombre = prcnombre;
       this.prcvalorPorcentaje = prcvalorPorcentaje;
       this.prchoras = prchoras;
    }
   
    public Integer getPrcid() {
        return this.prcid;
    }
    
    public void setPrcid(Integer prcid) {
        this.prcid = prcid;
    }
    public Unidadaprendizaje getUnidadaprendizaje() {
        return this.unidadaprendizaje;
    }
    
    public void setUnidadaprendizaje(Unidadaprendizaje unidadaprendizaje) {
        this.unidadaprendizaje = unidadaprendizaje;
    }
    public int getPrcnumero() {
        return this.prcnumero;
    }
    
    public void setPrcnumero(int prcnumero) {
        this.prcnumero = prcnumero;
    }
    public String getPrcnombre() {
        return this.prcnombre;
    }
    
    public void setPrcnombre(String prcnombre) {
        this.prcnombre = prcnombre;
    }
    public float getPrcvalorPorcentaje() {
        return this.prcvalorPorcentaje;
    }
    
    public void setPrcvalorPorcentaje(float prcvalorPorcentaje) {
        this.prcvalorPorcentaje = prcvalorPorcentaje;
    }
    public Float getPrchoras() {
        return this.prchoras;
    }
    
    public void setPrchoras(Float prchoras) {
        this.prchoras = prchoras;
    }




}


