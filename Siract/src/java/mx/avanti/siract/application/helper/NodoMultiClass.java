/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.Objects;
import mx.avanti.siract.business.entity.Practicalaboratorio;
import mx.avanti.siract.business.entity.Practicascampo;
import mx.avanti.siract.business.entity.Practicataller;
import mx.avanti.siract.business.entity.Subtemaunidad;
import mx.avanti.siract.business.entity.Temaunidad;
import mx.avanti.siract.business.entity.Unidad;

/**
 *
 * @author Y
 */
public class NodoMultiClass implements Serializable, Comparable<NodoMultiClass> {

    Unidad unidad;
    Temaunidad temaUnidad;
    Subtemaunidad subTema;
    String nombre;
    String subtemaId;
    String numero;
    String horas;
    String unidadId;
    String temaId;
    String id;
    Boolean horasCompletas=true;
    Boolean deReporteAnterior=false;

    public Boolean isDeReporteAnterior() {
        return deReporteAnterior;
    }

    public void setDeReporteAnterior(Boolean deReporteAnterior) {
        this.deReporteAnterior = deReporteAnterior;
    }

    public Boolean isHorasCompletas() {
        return horasCompletas;
    }

    public void setHorasCompletas(Boolean horasCompletas) {
        this.horasCompletas = horasCompletas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Practicalaboratorio getPracticaL() {
        return practicaL;
    }

    public void setPracticaL(Practicalaboratorio practicaL) {
        this.practicaL = practicaL;
    }

    public Practicataller getPracticaT() {
        return practicaT;
    }

    public void setPracticaT(Practicataller practicaT) {
        this.practicaT = practicaT;
    }

    public Practicascampo getPracticaC() {
        return practicaC;
    }

    public void setPracticaC(Practicascampo practicaC) {
        this.practicaC = practicaC;
    }
    String tipo = "/";
    Practicalaboratorio practicaL;
    Practicataller practicaT;
    Practicascampo practicaC;
    
    String observaciones = "";
    String porcentajeAvance = "0";
    String sumar = "0";
    boolean impartido=false;

    public boolean isImpartido() {
        return impartido;
    }

    public void setImpartido(boolean impartido) {
        this.impartido = impartido;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getSubtemaId() {
        return subtemaId;
    }

    public void setSubtemaId(String id) {
        this.subtemaId = id;
    }

    public String getHoras() {
        return horas;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(String porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public NodoMultiClass() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public Temaunidad getTemaUnidad() {
        return temaUnidad;
    }

    public void setTemaUnidad(Temaunidad temaUnidad) {
        this.temaUnidad = temaUnidad;
    }

    public Subtemaunidad getSubTema() {
        return subTema;
    }

    public void setSubTema(Subtemaunidad subTema) {
        this.subTema = subTema;
    }

    public NodoMultiClass(Object obj) {
        definirNodo(obj);
    }

    public NodoMultiClass(Object obj, int perteneceAUnidad) {
        this.unidadId = String.valueOf(perteneceAUnidad);
        definirNodo(obj);
    }

    public NodoMultiClass(Object obj, int perteneceAUnidad, int perteneceATema) {
        this.unidadId = String.valueOf(perteneceAUnidad);
        this.temaId = String.valueOf(perteneceATema);
        definirNodo(obj);
    }

    public void definirNodo(Object obj) {
        if (obj instanceof Unidad) {
            unidad = (Unidad) obj;
            nombre = unidad.getUninombre();
            horas = String.valueOf(unidad.getUnihoras());
            porcentajeAvance = String.valueOf(unidad.getUnivalorPorcentaje());
            unidadId = String.valueOf(unidad.getUniid());
            numero = String.valueOf(unidad.getUninumero());
            sumar = String.valueOf(unidad.getUnivalorPorcentaje());
            tipo = "unidad";
             id = String.valueOf(unidad.getUniid());
             horasCompletas=unidad.getUnihorasCompletas();
        }
        if (obj instanceof Temaunidad) {
            temaUnidad = (Temaunidad) obj;
            nombre = temaUnidad.getTunnombre();
            horas = String.valueOf(temaUnidad.getTunhoras());
            porcentajeAvance = String.valueOf(temaUnidad.getTunvalorPorcentaje());
            temaId = String.valueOf(temaUnidad.getTunid());
            numero = String.valueOf(temaUnidad.getTunnumero());
            sumar = String.valueOf(temaUnidad.getTunvalorPorcentaje());
             id = String.valueOf(temaUnidad.getTunid());
            tipo = "temaunidad";
            horasCompletas=temaUnidad.getTunhorasCompletas();
        }
        if (obj instanceof Subtemaunidad) {
            subTema = (Subtemaunidad) obj;
            nombre = subTema.getSutnombre();
            horas = String.valueOf(subTema.getSuthoras());
            porcentajeAvance = String.valueOf(subTema.getSutvalorPorcentaje());
            subtemaId = String.valueOf(subTema.getSutid());
            numero = String.valueOf(subTema.getSutnumero());
            sumar = String.valueOf(subTema.getSutvalorPorcentaje());
             id = String.valueOf(subTema.getSutid());
            tipo = "subtemaunidad";

        }
        if (obj instanceof Practicalaboratorio) {

            practicaL = (Practicalaboratorio) obj;
            nombre = practicaL.getPrlnombre();
            horas = String.valueOf(practicaL.getPrlhoras());
            porcentajeAvance = String.valueOf(practicaL.getPrlvalorPorcentaje());
            unidadId = String.valueOf(practicaL.getPrlid());
            numero = String.valueOf(practicaL.getPrlnumero());
            sumar = String.valueOf(practicaL.getPrlvalorPorcentaje());
            id = String.valueOf(practicaL.getPrlid());
            tipo = "practicalaboratorio";

        }
        if (obj instanceof Practicataller) {

            practicaT = (Practicataller) obj;
            nombre = practicaT.getPrtnombre();
            horas = String.valueOf(practicaT.getPrthoras());
            porcentajeAvance = String.valueOf(practicaT.getPrtvalorPorcentaje());
            unidadId = String.valueOf(practicaT.getPrtid());
            numero = String.valueOf(practicaT.getPrtnumero());
            sumar = String.valueOf(practicaT.getPrtvalorPorcentaje());
            id = String.valueOf(practicaT.getPrtid());
            tipo = "practicataller";

        }
           if (obj instanceof Practicascampo) {
            practicaC = (Practicascampo) obj;
            nombre = practicaC.getPrcnombre();
            horas = String.valueOf(practicaC.getPrchoras());
            porcentajeAvance = String.valueOf(practicaC.getPrcvalorPorcentaje());
            unidadId = String.valueOf(practicaC.getPrcid());
            numero = String.valueOf(practicaC.getPrcnumero());
            sumar = String.valueOf(practicaC.getPrcvalorPorcentaje());
            id = String.valueOf(practicaC.getPrcid());
            tipo = "practicaCampo";
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.unidad);
        hash = 37 * hash + Objects.hashCode(this.temaUnidad);
        hash = 37 * hash + Objects.hashCode(this.subTema);
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.subtemaId);
        hash = 37 * hash + Objects.hashCode(this.numero);
        hash = 37 * hash + Objects.hashCode(this.horas);
        hash = 37 * hash + Objects.hashCode(this.unidadId);
        hash = 37 * hash + Objects.hashCode(this.temaId);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.practicaL);
        hash = 37 * hash + Objects.hashCode(this.practicaT);
        hash = 37 * hash + Objects.hashCode(this.practicaC);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.porcentajeAvance);
        hash = 37 * hash + Objects.hashCode(this.sumar);
        hash = 37 * hash + (this.impartido ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NodoMultiClass other = (NodoMultiClass) obj;
        if (!Objects.equals(this.unidad, other.unidad)) {
            return false;
        }
        if (!Objects.equals(this.temaUnidad, other.temaUnidad)) {
            return false;
        }
        if (!Objects.equals(this.subTema, other.subTema)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.subtemaId, other.subtemaId)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        if (!Objects.equals(this.horas, other.horas)) {
            return false;
        }
        if (!Objects.equals(this.unidadId, other.unidadId)) {
            return false;
        }
        if (!Objects.equals(this.temaId, other.temaId)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.practicaL, other.practicaL)) {
            return false;
        }
        if (!Objects.equals(this.practicaT, other.practicaT)) {
            return false;
        }
        if (!Objects.equals(this.practicaC, other.practicaC)) {
            return false;
        }
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        if (!Objects.equals(this.porcentajeAvance, other.porcentajeAvance)) {
            return false;
        }
        if (!Objects.equals(this.sumar, other.sumar)) {
            return false;
        }
        if (this.impartido != other.impartido) {
            return false;
        }
        return true;
    }

   


 
    @Override
    public String toString() {
        if (observaciones.isEmpty() || observaciones == null) {
            observaciones = " ";
        }

        return tipo + "-//-" + unidadId + "-//-" + temaId + "-//-" + subtemaId + "-//-" + nombre + "-//-" + sumar + "-//-" + observaciones;
    }

    @Override
    public int compareTo(NodoMultiClass document) {
        return this.getNombre().compareTo(document.getNombre());
    }

}
