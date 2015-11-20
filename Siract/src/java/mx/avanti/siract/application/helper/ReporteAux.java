/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.application.helper;

import java.util.Date;

/**
 *
 * @author Owner
 */
public class ReporteAux {
    public String op; 
    public String tipo;
    public int calnumeroReporte;
    public int numRact; 
    public String cescicloEscolar;
    public int acoclave;
    public int clavepe;
    public String pesvigencia;
    public int numProfUIPid;
    public Date fecha1;
    public int pronumeroEmpleado;
    public int gponumero;
    public int clave;
    public int uapclave;
    public int uacclave;
    public int creid;
    public int aadid;
    //public int calnumeroReporte;

    public ReporteAux() {
        this.op = null;
        this.tipo = null;
        this.calnumeroReporte = 0;
        this.numRact = 0;
        this.cescicloEscolar = null;
        this.acoclave = 0;
        this.clavepe = 0;
        this.pesvigencia = null;
        this.numProfUIPid = 0;
        this.fecha1 = null;
        this.pronumeroEmpleado = 0;
        this.gponumero = 0;
        this.clave = 0;
        this.uapclave = 0;
        this.uacclave = 0;
        this.creid = 0;
        this.aadid = 0;
    }
    
    public ReporteAux(String op, String tipo, int calnumeroReporte, int numRact, String cescicloEscolar, int acoclave, int clavepe, String pesvigencia, int numProfUIPid, Date fecha1, int pronumeroEmpleado, int gponumero, int clave, int uapclave, int uacclave, int creid, int aadid) {
        this.op = op;
        this.tipo = tipo;
        this.calnumeroReporte = calnumeroReporte;
        this.numRact = numRact;
        this.cescicloEscolar = cescicloEscolar;
        this.acoclave = acoclave;
        this.clavepe = clavepe;
        this.pesvigencia = pesvigencia;
        this.numProfUIPid = numProfUIPid;
        this.fecha1 = fecha1;
        this.pronumeroEmpleado = pronumeroEmpleado;
        this.gponumero = gponumero;
        this.clave = clave;
        this.uapclave = uapclave;
        this.uacclave = uacclave;
        this.creid = creid;
        this.aadid = aadid;
    }

    public int getAadid() {
        return aadid;
    }

    public void setAadid(int aadid) {
        this.aadid = aadid;
    }

    public int getCreid() {
        return creid;
    }

    public void setCreid(int creid) {
        this.creid = creid;
    }
        
    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCalnumeroReporte() {
        return calnumeroReporte;
    }

    public void setCalnumeroReporte(int calnumeroReporte) {
        this.calnumeroReporte = calnumeroReporte;
    }

    public int getNumRact() {
        return numRact;
    }

    public void setNumRact(int numRact) {
        this.numRact = numRact;
    }

    public String getCescicloEscolar() {
        return cescicloEscolar;
    }

    public void setCescicloEscolar(String cescicloEscolar) {
        this.cescicloEscolar = cescicloEscolar;
    }

    public int getAcoclave() {
        return acoclave;
    }

    public void setAcoclave(int acoclave) {
        this.acoclave = acoclave;
    }

    public int getClavepe() {
        return clavepe;
    }

    public void setClavepe(int clavepe) {
        this.clavepe = clavepe;
    }

    public String getPesvigencia() {
        return pesvigencia;
    }

    public void setPesvigencia(String pesvigencia) {
        this.pesvigencia = pesvigencia;
    }

    public int getNumProfUIPid() {
        return numProfUIPid;
    }

    public void setNumProfUIPid(int numProfUIPid) {
        this.numProfUIPid = numProfUIPid;
    }

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public int getPronumeroEmpleado() {
        return pronumeroEmpleado;
    }

    public void setPronumeroEmpleado(int pronumeroEmpleado) {
        this.pronumeroEmpleado = pronumeroEmpleado;
    }

    public int getGponumero() {
        return gponumero;
    }

    public void setGponumero(int gponumero) {
        this.gponumero = gponumero;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public int getUapclave() {
        return uapclave;
    }

    public void setUapclave(int uapclave) {
        this.uapclave = uapclave;
    }

    public int getUacclave() {
        return uacclave;
    }

    public void setUacclave(int uacclave) {
        this.uacclave = uacclave;
    }
    
    
    
}
