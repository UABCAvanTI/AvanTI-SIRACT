package mx.avanti.siract.business.entity;
// Generated 17-ago-2015 14:50:31 by Hibernate Tools 3.6.0



/**
 * Practicataller generated by hbm2java
 */
public class Practicataller  implements java.io.Serializable {


     private Integer prtid;
     private Unidadaprendizaje unidadaprendizaje;
     private int prtnumero;
     private String prtnombre;
     private float prtvalorPorcentaje;
     private Float prthoras;

    public Practicataller() {
    }

	
    public Practicataller(Unidadaprendizaje unidadaprendizaje, int prtnumero, String prtnombre, float prtvalorPorcentaje) {
        this.unidadaprendizaje = unidadaprendizaje;
        this.prtnumero = prtnumero;
        this.prtnombre = prtnombre;
        this.prtvalorPorcentaje = prtvalorPorcentaje;
    }
    public Practicataller(Unidadaprendizaje unidadaprendizaje, int prtnumero, String prtnombre, float prtvalorPorcentaje, Float prthoras) {
       this.unidadaprendizaje = unidadaprendizaje;
       this.prtnumero = prtnumero;
       this.prtnombre = prtnombre;
       this.prtvalorPorcentaje = prtvalorPorcentaje;
       this.prthoras = prthoras;
    }
   
    public Integer getPrtid() {
        return this.prtid;
    }
    
    public void setPrtid(Integer prtid) {
        this.prtid = prtid;
    }
    public Unidadaprendizaje getUnidadaprendizaje() {
        return this.unidadaprendizaje;
    }
    
    public void setUnidadaprendizaje(Unidadaprendizaje unidadaprendizaje) {
        this.unidadaprendizaje = unidadaprendizaje;
    }
    public int getPrtnumero() {
        return this.prtnumero;
    }
    
    public void setPrtnumero(int prtnumero) {
        this.prtnumero = prtnumero;
    }
    public String getPrtnombre() {
        return this.prtnombre;
    }
    
    public void setPrtnombre(String prtnombre) {
        this.prtnombre = prtnombre;
    }
    public float getPrtvalorPorcentaje() {
        return this.prtvalorPorcentaje;
    }
    
    public void setPrtvalorPorcentaje(float prtvalorPorcentaje) {
        this.prtvalorPorcentaje = prtvalorPorcentaje;
    }
    public Float getPrthoras() {
        return this.prthoras;
    }
    
    public void setPrthoras(Float prthoras) {
        this.prthoras = prthoras;
    }




}

