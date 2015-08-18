/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.application.helper;

import java.io.Serializable;
import org.primefaces.model.chart.MeterGaugeChartModel;

/**
 *
 * @author Ricardo
 */
public class SemaforoProgEd implements Serializable{
    MeterGaugeChartModel model1;
    String Programa;

    public SemaforoProgEd(MeterGaugeChartModel model1, String Programa) {
        this.model1 = model1;
        this.Programa = Programa;
    }
    
    public MeterGaugeChartModel getModel1() {
        return model1;
    }
    
    public String getPrograma() {
        return Programa;
    }
}