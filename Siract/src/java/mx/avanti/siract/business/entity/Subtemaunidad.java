package mx.avanti.siract.business.entity;
// Generated 17-ago-2015 14:50:31 by Hibernate Tools 3.6.0



/**
 * Subtemaunidad generated by hbm2java
 */
public class Subtemaunidad  implements java.io.Serializable {


     private Integer sutid;
     private Temaunidad temaunidad;
     private String sutnumero;
     private String sutnombre;
     private float sutvalorPorcentaje;
     private Float suthoras;

    public Subtemaunidad() {
    }

	
    public Subtemaunidad(Temaunidad temaunidad, String sutnumero, String sutnombre, float sutvalorPorcentaje) {
        this.temaunidad = temaunidad;
        this.sutnumero = sutnumero;
        this.sutnombre = sutnombre;
        this.sutvalorPorcentaje = sutvalorPorcentaje;
    }
    public Subtemaunidad(Temaunidad temaunidad, String sutnumero, String sutnombre, float sutvalorPorcentaje, Float suthoras) {
       this.temaunidad = temaunidad;
       this.sutnumero = sutnumero;
       this.sutnombre = sutnombre;
       this.sutvalorPorcentaje = sutvalorPorcentaje;
       this.suthoras = suthoras;
    }
   
    public Integer getSutid() {
        return this.sutid;
    }
    
    public void setSutid(Integer sutid) {
        this.sutid = sutid;
    }
    public Temaunidad getTemaunidad() {
        return this.temaunidad;
    }
    
    public void setTemaunidad(Temaunidad temaunidad) {
        this.temaunidad = temaunidad;
    }
    public String getSutnumero() {
        return this.sutnumero;
    }
    
    public void setSutnumero(String sutnumero) {
        this.sutnumero = sutnumero;
    }
    public String getSutnombre() {
        return this.sutnombre;
    }
    
    public void setSutnombre(String sutnombre) {
        this.sutnombre = sutnombre;
    }
    public float getSutvalorPorcentaje() {
        return this.sutvalorPorcentaje;
    }
    
    public void setSutvalorPorcentaje(float sutvalorPorcentaje) {
        this.sutvalorPorcentaje = sutvalorPorcentaje;
    }
    public Float getSuthoras() {
        return this.suthoras;
    }
    
    public void setSuthoras(Float suthoras) {
        this.suthoras = suthoras;
    }




}


