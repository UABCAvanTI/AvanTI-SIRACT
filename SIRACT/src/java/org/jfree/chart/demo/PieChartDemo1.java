/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2005, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * ------------------
 * PieChartDemo1.java
 * ------------------
 * (C) Copyright 2003-2005, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   ;
 *
 * $Id: PieChartDemo1.java,v 1.2 2005/03/28 19:38:45 mungady Exp $
 *
 * Changes
 * -------
 * 09-Mar-2005 : Version 1, copied from the demo collection that ships with
 *               the JFreeChart Developer Guide (DG);
 *
 */

package org.jfree.chart.demo;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import mx.avanti.siract.business.EsperadosDelegate;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * A simple demonstration application showing how to create a pie chart using 
 * data from a {@link DefaultPieDataset}.
 */
public class PieChartDemo1{
    private static final int HIGH = 350;
    private static final int WIDTH = 600;
    private static final String SHEET_NAME = "Graficos";
    private static final int POSITION_X = 1;
    private static final int POSITION_Y = 13;
    private static final int CHART_WIDTH = 14 + POSITION_X; //numero de columnas
    private static final int CHART_HIGH = 23 + POSITION_Y; //numero de filas
    ArrayList<String> noEntregados;
    String title;
    String excel;
    double min=0,max;
    
    public PieChartDemo1(String title,ArrayList<String> noEntregados,String excel) {
        this.title = title;
        this.noEntregados = noEntregados;
        this.excel = excel;
    }

    /**
     * Creates a sample dataset.
     * 
     * @return A sample dataset.
     */
    public PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        String[] aux;
        for(String programa: noEntregados){
            aux = programa.split("-");
            dataset.setValue(aux[0], Integer.parseInt(aux[1]));
        }
        return dataset;        
    }
    
    /**
     * Creates a chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return A chart.
     */
    public JFreeChart createChart(PieDataset dataset) {
        
        JFreeChart chart = ChartFactory.createPieChart(
            "No entregados",  // chart title
            dataset,             // data
            true,               // include legend
            true,
            false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        return chart;
    }
    
    public void addChartToExcel(JFreeChart chart,HSSFWorkbook wb ) throws Exception{
    	final BufferedImage buffer = chart.createBufferedImage(WIDTH, HIGH);
    	//final FileOutputStream file = new FileOutputStream(excel+".xls");
        
    	ByteArrayOutputStream img_bytes = new ByteArrayOutputStream();
    	ImageIO.write( buffer, "png",img_bytes );
    	img_bytes.flush();
        /*Esta es la parte para agregar todo lo gneraod al excel*/
         /*Esta es la parte para agregar todo lo gneraod al excel*/
         /*Esta es la parte para agregar todo lo gneraod al excel*/
    	//HSSFWorkbook wb = new HSSFWorkbook();
    	//wb.createSheet(SHEET_NAME);
    	HSSFClientAnchor anchor = new HSSFClientAnchor(0,0,0,0,(short)POSITION_X,POSITION_Y,(short)CHART_WIDTH,CHART_HIGH);
    	int index = wb.addPicture(img_bytes.toByteArray(),HSSFWorkbook.PICTURE_TYPE_PNG);
    	HSSFSheet sheet = wb.getSheet(SHEET_NAME);
    	HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
    	patriarch.createPicture(anchor,index);
    	//wb.write(file);
    	//file.close();
    }
    
    public static void main(String[] args) {
        ArrayList<String> Plan = new ArrayList<String>();
        ArrayList<Integer> Programa = new ArrayList<Integer>();
        ArrayList<String> Ciclo = new ArrayList<String>();
        //----------------------------------------------------------------------
        int UnidadA = 1;
        Plan.add("2009-2");
        Plan.add("2006-2");
        Plan.add("2008-2");
        Programa.add(1);
        Programa.add(4);
        Programa.add(9);
        Ciclo.add("2009-2");
        EsperadosDelegate delegate = new EsperadosDelegate();
        ArrayList<String> noEntregados = delegate.getFullProgramaEdu(UnidadA, Plan, Programa, Ciclo);
//        PieChartDemo1 demo = new PieChartDemo1("Pie Chart Demo 1",noEntregados);
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);

    }

}