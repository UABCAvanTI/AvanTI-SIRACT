/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.Date;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Reporteavancecontenidotematico;

/**
 *
 * @author Owner
 */
public class ReporteAvanceAux implements Serializable{
    public Reporteavancecontenidotematico reporteAvance;
    public Areaconocimiento areaConocimiento;
    public int contUipid;
    public int contResultCriteria;
    public float auxPAvance;
    public Date fechaCorte;
    public Date fechaLimite;
    public String tipoReporteSelec;
    public float porcentAvanceRact1;
    public float porcentAvanceRact2;
    public float porcentAvanceRact3;
    public String statusRact1;
    public String statusRact2;
    public String statusRact3;
    public Date fechaElaboracRact1;
    public Date fechaElaboracRact2;
    public Date fechaElaboracRact3;
    public Date fechaLimiteRact1;
    public Date fechaLimiteRact2;
    public Date fechaLimiteRact3;
    public String tipoReporteSelecRact1;
    public String tipoReporteSelecRact2;
    public String tipoReporteSelecRact3;

    public ReporteAvanceAux(Reporteavancecontenidotematico reporteAvance,Areaconocimiento areaConocimiento, int contUipid, int contResultCriteria, float auxPAvance) {
        this.reporteAvance = reporteAvance;
        this.areaConocimiento = areaConocimiento;
        this.contUipid = contUipid;
        this.contResultCriteria = contResultCriteria;
        this.auxPAvance = auxPAvance;
        this.fechaCorte = null;
        this.fechaLimite = null;
    }
    
    public ReporteAvanceAux(Reporteavancecontenidotematico reporteAvance,Areaconocimiento areaConocimiento, int contResultCriteria, float auxPAvance) {
        this.reporteAvance = reporteAvance;
        this.areaConocimiento = areaConocimiento;
        this.contUipid = 0;
        this.contResultCriteria = contResultCriteria;
        this.auxPAvance = auxPAvance;
        this.fechaCorte = null;
        this.fechaLimite = null;
    }

    public ReporteAvanceAux(Reporteavancecontenidotematico reporteAvance,Areaconocimiento areaConocimiento, int contUipid, int contResultCriteria, float auxPAvance, Date fechaCorte, Date fechaLimite) {
        this.reporteAvance = reporteAvance;
        this.areaConocimiento = areaConocimiento;
        this.contUipid = contUipid;
        this.contResultCriteria = contResultCriteria;
        this.auxPAvance = auxPAvance;
        this.fechaCorte = fechaCorte;
        this.fechaLimite = fechaLimite;
    }
    
    public ReporteAvanceAux(Reporteavancecontenidotematico reporteAvance,Areaconocimiento areaConocimiento, int contResultCriteria, float auxPAvance, Date fechaCorte, Date fechaLimite) {
        this.reporteAvance = reporteAvance;
        this.areaConocimiento = areaConocimiento;
        this.contUipid = 0;
        this.contResultCriteria = contResultCriteria;
        this.auxPAvance = auxPAvance;
        this.fechaCorte = fechaCorte;
        this.fechaLimite = fechaLimite;
    }

    public ReporteAvanceAux(Reporteavancecontenidotematico reporteAvance,Areaconocimiento areaConocimiento, int contUipid, int contResultCriteria, float auxPAvance, Date fechaCorte, Date fechaLimite, String tipoReporteSelec) {
        this.reporteAvance = reporteAvance;
        this.areaConocimiento = areaConocimiento;
        this.contUipid = contUipid;
        this.contResultCriteria = contResultCriteria;
        this.auxPAvance = auxPAvance;
        this.fechaCorte = fechaCorte;
        this.fechaLimite = fechaLimite;
        this.tipoReporteSelec = tipoReporteSelec;
    }
    
    public ReporteAvanceAux() {
        this.reporteAvance = null;
        this.areaConocimiento = null;
        this.contUipid = 0;
        this.contResultCriteria = 0;
        this.auxPAvance = 0;
        this.fechaCorte = null;
        this.fechaLimite = null;
        this.tipoReporteSelec = null;
        this.porcentAvanceRact1 = 0;
        this.porcentAvanceRact2 = 0;
        this.porcentAvanceRact3 = 0;
        this.statusRact1 = null;
        this.statusRact2 = null;
        this.statusRact3 = null;
        this.fechaElaboracRact1 = null;
        this.fechaElaboracRact2 = null;
        this.fechaElaboracRact3 = null;
        this.fechaLimiteRact1 = null;
        this.fechaLimiteRact2 = null;
        this.fechaLimiteRact3 = null;
        this.tipoReporteSelecRact1 = null;
        this.tipoReporteSelecRact2 = null;
        this.tipoReporteSelecRact3 = null;
    }
    
    
    
    public String getTipoReporteSelecRact1() {
        return tipoReporteSelecRact1;
    }

    public void setTipoReporteSelecRact1(String tipoReporteSelecRact1) {
        this.tipoReporteSelecRact1 = tipoReporteSelecRact1;
    }

    public String getTipoReporteSelecRact2() {
        return tipoReporteSelecRact2;
    }

    public void setTipoReporteSelecRact2(String tipoReporteSelecRact2) {
        this.tipoReporteSelecRact2 = tipoReporteSelecRact2;
    }

    public String getTipoReporteSelecRact3() {
        return tipoReporteSelecRact3;
    }

    public void setTipoReporteSelecRact3(String tipoReporteSelecRact3) {
        this.tipoReporteSelecRact3 = tipoReporteSelecRact3;
    }    
    
    public float getPorcentAvanceRact1() {
        return porcentAvanceRact1;
    }

    public void setPorcentAvanceRact1(float porcentAvanceRact1) {
        this.porcentAvanceRact1 = porcentAvanceRact1;
    }

    public float getPorcentAvanceRact2() {
        return porcentAvanceRact2;
    }

    public void setPorcentAvanceRact2(float porcentAvanceRact2) {
        this.porcentAvanceRact2 = porcentAvanceRact2;
    }

    public float getPorcentAvanceRact3() {
        return porcentAvanceRact3;
    }

    public void setPorcentAvanceRact3(float porcentAvanceRact3) {
        this.porcentAvanceRact3 = porcentAvanceRact3;
    }

    public String getStatusRact1() {
        return statusRact1;
    }

    public void setStatusRact1(String statusRact1) {
        this.statusRact1 = statusRact1;
    }

    public String getStatusRact2() {
        return statusRact2;
    }

    public void setStatusRact2(String statusRact2) {
        this.statusRact2 = statusRact2;
    }

    public String getStatusRact3() {
        return statusRact3;
    }

    public void setStatusRact3(String statusRact3) {
        this.statusRact3 = statusRact3;
    }

    public Date getFechaElaboracRact1() {
        return fechaElaboracRact1;
    }

    public void setFechaElaboracRact1(Date fechaElaboracRact1) {
        this.fechaElaboracRact1 = fechaElaboracRact1;
    }

    public Date getFechaElaboracRact2() {
        return fechaElaboracRact2;
    }

    public void setFechaElaboracRact2(Date fechaElaboracRact2) {
        this.fechaElaboracRact2 = fechaElaboracRact2;
    }

    public Date getFechaElaboracRact3() {
        return fechaElaboracRact3;
    }

    public void setFechaElaboracRact3(Date fechaElaboracRact3) {
        this.fechaElaboracRact3 = fechaElaboracRact3;
    }

    public Date getFechaLimiteRact1() {
        return fechaLimiteRact1;
    }

    public void setFechaLimiteRact1(Date fechaLimiteRact1) {
        this.fechaLimiteRact1 = fechaLimiteRact1;
    }

    public Date getFechaLimiteRact2() {
        return fechaLimiteRact2;
    }

    public void setFechaLimiteRact2(Date fechaLimiteRact2) {
        this.fechaLimiteRact2 = fechaLimiteRact2;
    }

    public Date getFechaLimiteRact3() {
        return fechaLimiteRact3;
    }

    public void setFechaLimiteRact3(Date fechaLimiteRact3) {
        this.fechaLimiteRact3 = fechaLimiteRact3;
    } 
        
    public String getTipoReporteSelec() {
        return tipoReporteSelec;
    }

    public void setTipoReporteSelec(String tipoReporteSelec) {
        this.tipoReporteSelec = tipoReporteSelec;
    }   
    
    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
    
       
        
    public Reporteavancecontenidotematico getReporteAvance() {
        return reporteAvance;
    }

    public void setReporteAvance(Reporteavancecontenidotematico reporteAvance) {
        this.reporteAvance = reporteAvance;
    }

    public int getContUipid() {
        return contUipid;
    }

    public void setContUipid(int contUipid) {
        this.contUipid = contUipid;
    }

    public int getContResultCriteria() {
        return contResultCriteria;
    }

    public void setContResultCriteria(int contResultCriteria) {
        this.contResultCriteria = contResultCriteria;
    }

    public float getAuxPAvance() {
        return auxPAvance;
    }

    public void setAuxPAvance(float auxPAvance) {
        this.auxPAvance = auxPAvance;
    }

    public Areaconocimiento getAreaConocimiento() {
        return areaConocimiento;
    }

    public void setAreaConocimiento(Areaconocimiento areaConocimiento) {
        this.areaConocimiento = areaConocimiento;
    }
    
    
}
