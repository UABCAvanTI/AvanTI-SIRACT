/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.application.helper;

import java.io.Serializable;

/**
 *
 * @author Ricardo
 */
public class GeneralData implements Serializable{
    private final int entregados;
    private final int esperados;
    private final double porcentaje;
    private final String Ract;

    public GeneralData(String Ract,int entregados, int esperados) {
        this.Ract = Ract;
        this.entregados = entregados;
        this.esperados = esperados;
        System.out.println("Numero de esperados = "+esperados);
        this.porcentaje = (entregados*100)/esperados;
    }

    public String getRact() {
        return Ract;
    }
    
    public int getEntregados() {
        return entregados;
    }

    public int getEsperados() {
        return esperados;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    
}