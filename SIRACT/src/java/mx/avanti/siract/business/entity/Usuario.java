package mx.avanti.siract.business.entity;
// Generated 17-ago-2015 14:50:31 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private Integer usuid;
     private String usuusuario;
     private String usucontrasenia;
     private Set rols = new HashSet(0);
     private Set catalogoreporteses = new HashSet(0);
     private Set profesors = new HashSet(0);

    public Usuario() {
    }

	
    public Usuario(String usuusuario) {
        this.usuusuario = usuusuario;
    }
    public Usuario(String usuusuario, String usucontrasenia, Set rols, Set catalogoreporteses, Set profesors) {
       this.usuusuario = usuusuario;
       this.usucontrasenia = usucontrasenia;
       this.rols = rols;
       this.catalogoreporteses = catalogoreporteses;
       this.profesors = profesors;
    }
   
    public Integer getUsuid() {
        return this.usuid;
    }
    
    public void setUsuid(Integer usuid) {
        this.usuid = usuid;
    }
    public String getUsuusuario() {
        return this.usuusuario;
    }
    
    public void setUsuusuario(String usuusuario) {
        this.usuusuario = usuusuario;
    }
    public String getUsucontrasenia() {
        return this.usucontrasenia;
    }
    
    public void setUsucontrasenia(String usucontrasenia) {
        this.usucontrasenia = usucontrasenia;
    }
    public Set getRols() {
        return this.rols;
    }
    
    public void setRols(Set rols) {
        this.rols = rols;
    }
    public Set getCatalogoreporteses() {
        return this.catalogoreporteses;
    }
    
    public void setCatalogoreporteses(Set catalogoreporteses) {
        this.catalogoreporteses = catalogoreporteses;
    }
    public Set getProfesors() {
        return this.profesors;
    }
    
    public void setProfesors(Set profesors) {
        this.profesors = profesors;
    }




}


