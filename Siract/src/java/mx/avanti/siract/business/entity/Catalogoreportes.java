package mx.avanti.siract.business.entity;
// Generated 17-ago-2015 14:50:31 by Hibernate Tools 3.6.0



/**
 * Catalogoreportes generated by hbm2java
 */
public class Catalogoreportes  implements java.io.Serializable {


     private Integer ctrid;
     private Usuario usuario;
     private String ctrnombre;
     private String ctrconsultaQuery;

    public Catalogoreportes() {
    }

	
    public Catalogoreportes(Usuario usuario, String ctrnombre) {
        this.usuario = usuario;
        this.ctrnombre = ctrnombre;
    }
    public Catalogoreportes(Usuario usuario, String ctrnombre, String ctrconsultaQuery) {
       this.usuario = usuario;
       this.ctrnombre = ctrnombre;
       this.ctrconsultaQuery = ctrconsultaQuery;
    }
   
    public Integer getCtrid() {
        return this.ctrid;
    }
    
    public void setCtrid(Integer ctrid) {
        this.ctrid = ctrid;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getCtrnombre() {
        return this.ctrnombre;
    }
    
    public void setCtrnombre(String ctrnombre) {
        this.ctrnombre = ctrnombre;
    }
    public String getCtrconsultaQuery() {
        return this.ctrconsultaQuery;
    }
    
    public void setCtrconsultaQuery(String ctrconsultaQuery) {
        this.ctrconsultaQuery = ctrconsultaQuery;
    }




}


