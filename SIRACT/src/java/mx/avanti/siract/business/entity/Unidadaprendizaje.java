package mx.avanti.siract.business.entity;
// Generated 17-ago-2015 14:50:31 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Unidadaprendizaje generated by hbm2java
 */
public class Unidadaprendizaje  implements java.io.Serializable {


     private Integer uapid;
     private Cicloescolar cicloescolar;
     private Unidadaprendizaje unidadaprendizaje;
     private int uapclave;
     private String uapnombre;
     private String uapetapaFormacion;
     private int uapcreditos;
     private Integer uaphorasClase;
     private Integer uaphorasLaboratorio;
     private Integer uaphorasTaller;
     private Integer uaphorasClinica;
     private Integer uaphorasCampo;
     private Integer uaphorasExtraClase;
     private String uaptipoCaracter;
     private Boolean uaphorasClaseCompletas;
     private Boolean uaphorasLaboratorioCompletas;
     private Boolean uaphorasTallerCompletas;
     private Boolean uaphorasCampoCompletas;
     private Set unidadaprendizajeImparteProfesors = new HashSet(0);
     private Set practicatallers = new HashSet(0);
     private Set practicascampos = new HashSet(0);
     private Set practicalaboratorios = new HashSet(0);
     private Set practicasclinicas = new HashSet(0);
     private Set areaconocimientos = new HashSet(0);
     private Set unidadaprendizajes = new HashSet(0);
     private Set coordinadorareaadministrativas = new HashSet(0);
     private Set unidads = new HashSet(0);

    public Unidadaprendizaje() {
    }

	
    public Unidadaprendizaje(int uapclave, String uapnombre, String uapetapaFormacion, int uapcreditos, String uaptipoCaracter) {
        this.uapclave = uapclave;
        this.uapnombre = uapnombre;
        this.uapetapaFormacion = uapetapaFormacion;
        this.uapcreditos = uapcreditos;
        this.uaptipoCaracter = uaptipoCaracter;
    }
    public Unidadaprendizaje(Cicloescolar cicloescolar, Unidadaprendizaje unidadaprendizaje, int uapclave, String uapnombre, String uapetapaFormacion, int uapcreditos, Integer uaphorasClase, Integer uaphorasLaboratorio, Integer uaphorasTaller, Integer uaphorasClinica, Integer uaphorasCampo, Integer uaphorasExtraClase, String uaptipoCaracter, Boolean uaphorasClaseCompletas, Boolean uaphorasLaboratorioCompletas, Boolean uaphorasTallerCompletas, Boolean uaphorasCampoCompletas, Set unidadaprendizajeImparteProfesors, Set practicatallers, Set practicascampos, Set practicalaboratorios, Set practicasclinicas, Set areaconocimientos, Set unidadaprendizajes, Set coordinadorareaadministrativas, Set unidads) {
       this.cicloescolar = cicloescolar;
       this.unidadaprendizaje = unidadaprendizaje;
       this.uapclave = uapclave;
       this.uapnombre = uapnombre;
       this.uapetapaFormacion = uapetapaFormacion;
       this.uapcreditos = uapcreditos;
       this.uaphorasClase = uaphorasClase;
       this.uaphorasLaboratorio = uaphorasLaboratorio;
       this.uaphorasTaller = uaphorasTaller;
       this.uaphorasClinica = uaphorasClinica;
       this.uaphorasCampo = uaphorasCampo;
       this.uaphorasExtraClase = uaphorasExtraClase;
       this.uaptipoCaracter = uaptipoCaracter;
       this.uaphorasClaseCompletas = uaphorasClaseCompletas;
       this.uaphorasLaboratorioCompletas = uaphorasLaboratorioCompletas;
       this.uaphorasTallerCompletas = uaphorasTallerCompletas;
       this.uaphorasCampoCompletas = uaphorasCampoCompletas;
       this.unidadaprendizajeImparteProfesors = unidadaprendizajeImparteProfesors;
       this.practicatallers = practicatallers;
       this.practicascampos = practicascampos;
       this.practicalaboratorios = practicalaboratorios;
       this.practicasclinicas = practicasclinicas;
       this.areaconocimientos = areaconocimientos;
       this.unidadaprendizajes = unidadaprendizajes;
       this.coordinadorareaadministrativas = coordinadorareaadministrativas;
       this.unidads = unidads;
    }
   
    public Integer getUapid() {
        return this.uapid;
    }
    
    public void setUapid(Integer uapid) {
        this.uapid = uapid;
    }
    public Cicloescolar getCicloescolar() {
        return this.cicloescolar;
    }
    
    public void setCicloescolar(Cicloescolar cicloescolar) {
        this.cicloescolar = cicloescolar;
    }
    public Unidadaprendizaje getUnidadaprendizaje() {
        return this.unidadaprendizaje;
    }
    
    public void setUnidadaprendizaje(Unidadaprendizaje unidadaprendizaje) {
        this.unidadaprendizaje = unidadaprendizaje;
    }
    public int getUapclave() {
        return this.uapclave;
    }
    
    public void setUapclave(int uapclave) {
        this.uapclave = uapclave;
    }
    public String getUapnombre() {
        return this.uapnombre;
    }
    
    public void setUapnombre(String uapnombre) {
        this.uapnombre = uapnombre;
    }
    public String getUapetapaFormacion() {
        return this.uapetapaFormacion;
    }
    
    public void setUapetapaFormacion(String uapetapaFormacion) {
        this.uapetapaFormacion = uapetapaFormacion;
    }
    public int getUapcreditos() {
        return this.uapcreditos;
    }
    
    public void setUapcreditos(int uapcreditos) {
        this.uapcreditos = uapcreditos;
    }
    public Integer getUaphorasClase() {
        return this.uaphorasClase;
    }
    
    public void setUaphorasClase(Integer uaphorasClase) {
        this.uaphorasClase = uaphorasClase;
    }
    public Integer getUaphorasLaboratorio() {
        return this.uaphorasLaboratorio;
    }
    
    public void setUaphorasLaboratorio(Integer uaphorasLaboratorio) {
        this.uaphorasLaboratorio = uaphorasLaboratorio;
    }
    public Integer getUaphorasTaller() {
        return this.uaphorasTaller;
    }
    
    public void setUaphorasTaller(Integer uaphorasTaller) {
        this.uaphorasTaller = uaphorasTaller;
    }
    public Integer getUaphorasClinica() {
        return this.uaphorasClinica;
    }
    
    public void setUaphorasClinica(Integer uaphorasClinica) {
        this.uaphorasClinica = uaphorasClinica;
    }
    public Integer getUaphorasCampo() {
        return this.uaphorasCampo;
    }
    
    public void setUaphorasCampo(Integer uaphorasCampo) {
        this.uaphorasCampo = uaphorasCampo;
    }
    public Integer getUaphorasExtraClase() {
        return this.uaphorasExtraClase;
    }
    
    public void setUaphorasExtraClase(Integer uaphorasExtraClase) {
        this.uaphorasExtraClase = uaphorasExtraClase;
    }
    public String getUaptipoCaracter() {
        return this.uaptipoCaracter;
    }
    
    public void setUaptipoCaracter(String uaptipoCaracter) {
        this.uaptipoCaracter = uaptipoCaracter;
    }
    public Boolean getUaphorasClaseCompletas() {
        return this.uaphorasClaseCompletas;
    }
    
    public void setUaphorasClaseCompletas(Boolean uaphorasClaseCompletas) {
        this.uaphorasClaseCompletas = uaphorasClaseCompletas;
    }
    public Boolean getUaphorasLaboratorioCompletas() {
        return this.uaphorasLaboratorioCompletas;
    }
    
    public void setUaphorasLaboratorioCompletas(Boolean uaphorasLaboratorioCompletas) {
        this.uaphorasLaboratorioCompletas = uaphorasLaboratorioCompletas;
    }
    public Boolean getUaphorasTallerCompletas() {
        return this.uaphorasTallerCompletas;
    }
    
    public void setUaphorasTallerCompletas(Boolean uaphorasTallerCompletas) {
        this.uaphorasTallerCompletas = uaphorasTallerCompletas;
    }
    public Boolean getUaphorasCampoCompletas() {
        return this.uaphorasCampoCompletas;
    }
    
    public void setUaphorasCampoCompletas(Boolean uaphorasCampoCompletas) {
        this.uaphorasCampoCompletas = uaphorasCampoCompletas;
    }
    public Set getUnidadaprendizajeImparteProfesors() {
        return this.unidadaprendizajeImparteProfesors;
    }
    
    public void setUnidadaprendizajeImparteProfesors(Set unidadaprendizajeImparteProfesors) {
        this.unidadaprendizajeImparteProfesors = unidadaprendizajeImparteProfesors;
    }
    public Set getPracticatallers() {
        return this.practicatallers;
    }
    
    public void setPracticatallers(Set practicatallers) {
        this.practicatallers = practicatallers;
    }
    public Set getPracticascampos() {
        return this.practicascampos;
    }
    
    public void setPracticascampos(Set practicascampos) {
        this.practicascampos = practicascampos;
    }
    public Set getPracticalaboratorios() {
        return this.practicalaboratorios;
    }
    
    public void setPracticalaboratorios(Set practicalaboratorios) {
        this.practicalaboratorios = practicalaboratorios;
    }
    public Set getPracticasclinicas() {
        return this.practicasclinicas;
    }
    
    public void setPracticasclinicas(Set practicasclinicas) {
        this.practicasclinicas = practicasclinicas;
    }
    public Set getAreaconocimientos() {
        return this.areaconocimientos;
    }
    
    public void setAreaconocimientos(Set areaconocimientos) {
        this.areaconocimientos = areaconocimientos;
    }
    public Set getUnidadaprendizajes() {
        return this.unidadaprendizajes;
    }
    
    public void setUnidadaprendizajes(Set unidadaprendizajes) {
        this.unidadaprendizajes = unidadaprendizajes;
    }
    public Set getCoordinadorareaadministrativas() {
        return this.coordinadorareaadministrativas;
    }
    
    public void setCoordinadorareaadministrativas(Set coordinadorareaadministrativas) {
        this.coordinadorareaadministrativas = coordinadorareaadministrativas;
    }
    public Set getUnidads() {
        return this.unidads;
    }
    
    public void setUnidads(Set unidads) {
        this.unidads = unidads;
    }




}


