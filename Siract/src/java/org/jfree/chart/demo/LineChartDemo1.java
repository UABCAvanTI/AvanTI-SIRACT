/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * -------------------
 * LineChartDemo1.java
 * -------------------
 * (C) Copyright 2002-2004, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: LineChartDemo1.java,v 1.27 2004/05/27 09:10:42 mungady Exp $
 *
 * Changes
 * -------
 * 08-Apr-2002 : Version 1 (DG);
 * 30-May-2002 : Modified to display values on the chart (DG);
 * 25-Jun-2002 : Removed redundant import (DG);
 * 11-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 *
 */

package org.jfree.chart.demo;

import java.awt.BasicStroke;
import java.awt.Color;
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
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * A simple demonstration application showing how to create a line chart using data from a
 * {@link CategoryDataset}.
 */
public class LineChartDemo1{
    private static final int HIGH = 350;
    private static final int WIDTH = 600;
    private static final String SHEET_NAME = "Graficos";
    private static final int POSITION_X = 1;
    private static final int POSITION_Y = 13;
    private static final int CHART_WIDTH = 14 + POSITION_X; //numero de columnas
    private static final int CHART_HIGH = 23 + POSITION_Y; //numero de filas
    ArrayList<String> Entregados;
    String title;
    String excel;
    double min=0,max;
    
    public LineChartDemo1(String title,ArrayList<String> Entregados,String excel) {
        this.Entregados = Entregados;
        this.excel = excel;
        this.title = title;
    }

    /**
     * Creates a sample dataset.
     * 
     * @return The dataset.
     */
    public CategoryDataset createDataset(){     
        // row keys...
        final String series1 = "No entregados";
        final String series2 = "Esperados";

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String[] aux;
        for(String programa: Entregados){
            aux = programa.split("-");
            dataset.addValue(Integer.parseInt(aux[1]), series1,aux[0]);
            dataset.addValue(Integer.parseInt(aux[2]), series2,aux[0]);
        }

        return dataset;
                
    }
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  a dataset.
     * 
     * @return The chart.
     */
    public JFreeChart createChart(final CategoryDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createLineChart(
            title,       // chart title
            "Programa educativo",                    // domain axis label
            "Racts",                   // range axis label
            dataset,                   // data
            PlotOrientation.VERTICAL,  // orientation
            true,                      // include legend
            true,                      // tooltips
            false                      // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
//        final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setDisplaySeriesShapes(true);
    //    legend.setShapeScaleX(1.5);
      //  legend.setShapeScaleY(1.5);
        //legend.setDisplaySeriesLines(true);

        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinePaint(Color.black);
      

        // customise the range axis...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);

        // ****************************************************************************
        // * JFREECHART DEVELOPER GUIDE                                               *
        // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
        // * to purchase from Object Refinery Limited:                                *
        // *                                                                          *
        // * http://www.object-refinery.com/jfreechart/guide.html                     *
        // *                                                                          *
        // * Sales are used to provide funding for the JFreeChart project - please    * 
        // * support us so that we can continue developing free software.             *
        // ****************************************************************************
        
        // customise the renderer...
        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
//        renderer.setDrawShapes(true);

        renderer.setSeriesStroke(
            0, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {10.0f, 6.0f}, 0.0f
            )
        );
        renderer.setSeriesStroke(
            1, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {6.0f, 6.0f}, 0.0f
            )
        );
        renderer.setSeriesStroke(
            2, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {2.0f, 6.0f}, 0.0f
            )
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        
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
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {
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
//        final LineChartDemo1 demo = new LineChartDemo1("Line Chart Demo",noEntregados);
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);

    }

}