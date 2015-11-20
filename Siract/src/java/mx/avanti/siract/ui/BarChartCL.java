package mx.avanti.siract.ui;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.util.DefaultShadowGenerator;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * A simple demonstration application showing how to create a bar chart.
 *
 */
public class BarChartCL{
    private static final int HIGH = 350;
    private static final int WIDTH = 600;
    private static final String SHEET_NAME = "Graficos";
    private static final int POSITION_X = 1;
    private static final int POSITION_Y = 13;
    private static final int CHART_WIDTH = 14 + POSITION_X; //numero de columnas
    private static final int CHART_HIGH = 23 + POSITION_Y; //numero de filas
    ArrayList<String> entregados;
    String title;
    String excel;
    double min=0,max;
    
    public BarChartCL(final String title,ArrayList<String> entregados,String excel) {
        this.title = title;
        this.excel = excel;
        this.entregados = entregados;
        for(int x=0;x<entregados.size();x++){
            System.out.println("Se muestran entregados");
            System.out.println(entregados.get(x));
        }
       // final CategoryDataset dataset = createDataset();
      // final JFreeChart chart = createChart(dataset);
       // this.entregados = entregados;
        
    }

    /**
     * Returns a sample dataset.
     * 
     * @return The dataset.
     */
    public CategoryDataset createDataset() {
        System.out.println("dataset");
        // row keys...
        final String series1 = "Total de RACT Entregados";
        final String series2 = "Total de RACT Esperados";
        
        // column keys...
        ArrayList<String> category = new ArrayList<String>();
        ArrayList<Integer> Ent = new ArrayList<Integer>();
        ArrayList<Integer> Esp = new ArrayList<Integer>();
       // ArrayList<Double> porc = new ArrayList<Double>();
        for (int x=0;x<entregados.size();x++){
            String entregado = entregados.get(x);
            String[] aux = entregado.split("-");
            category.add(aux[0]);
            Ent.add(Integer.parseInt(aux[1]));
            Esp.add(Integer.parseInt(aux[2]));
        }
        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for(int x=0;x<category.size();x++){
            System.out.println(x);
            dataset.addValue(Ent.get(x), series1, category.get(x));
            dataset.addValue(Esp.get(x), series2, category.get(x));
            if(max<Esp.get(x)){
                max = Esp.get(x);
            }
        }
        max=max+10;
        return dataset;
    }
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return The chart.
     */
    public JFreeChart createChart(final CategoryDataset dataset) {
        System.out.println("createChart");
        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            title,         // chart title
            "ProgramaEducativo",               // domain axis label
            "Cantidad de RACT",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.BLUE);
        plot.setRangeGridlinePaint(Color.BLACK);
        

       //style customisation
        ((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setItemMargin(0.035);
        BarRenderer.setDefaultShadowsVisible(true);
        System.setProperty("green", "0X009000");
        Color myColor1 = Color.getColor("green");
        System.setProperty("blue", "0X0000BE");
        Color myColor2 = Color.getColor("green");
        System.setProperty("shadow", "0X848484");
        Color myshadow = Color.getColor("shadow");
        renderer.setSeriesPaint(0, Color.getColor("green"));
        renderer.setSeriesPaint(1, Color.getColor("blue"));
        plot.setShadowGenerator(new DefaultShadowGenerator(10,myshadow,(float)0.45,10,10));
        
        //font customisation
        final Font titleFont = new Font("calibri", 0, 20);
        chart.getTitle().setFont(titleFont);
        CategoryAxis axis = plot.getDomainAxis();
        ValueAxis axis2 = plot.getRangeAxis();
        Font font = new Font("calibri", 0, 15);
        axis.setTickLabelFont(font);//Font de category
        Font font2 = new Font("calibri", 0, 15);
        axis2.setTickLabelFont(font2);
        Font font3 = new Font("calibri", 0, 15); 
        plot.getDomainAxis().setLabelFont(font3);
        plot.getRangeAxis().setLabelFont(font3);
        LegendTitle legend = chart.getLegend();
        legend.setBorder(0, 0, 0, 0);
        DecimalFormat decimalformat1 = new DecimalFormat("##,###");
        renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
        renderer.setItemLabelsVisible(true);
        chart.getCategoryPlot().setRenderer(renderer); 
        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)//inclinacion de las categorias
        );
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(min, max);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
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
    
    public void runGrap(HSSFWorkbook wb) {
        ArrayList<String> Plan = new ArrayList<String>();
        ArrayList<String> Programa = new ArrayList<String>();
        ArrayList<String> Ciclo = new ArrayList<String>();
        ArrayList<String> AC = new ArrayList<String>();
        ArrayList<String> UnidadApren = new ArrayList<String>();
        ArrayList<String> entregados;
        //----------------------------------------------------------------------
        String UnidadA = "Ingeniería";
        Plan.add("2009-2");
        AC.add("Administrativa");
        AC.add("Programacion e Ingeniera de Software");
        AC.add("Contable");
        AC.add("Tratamiento de la Informacion");
        AC.add("Redes y Arquitectura de Computadoras");
        AC.add("Matematicas");
        Programa.add("Licenciado en Sistemas Computacionales");
        Programa.add("Ingeniería Civil");
        Ciclo.add("2009-2");
        Ciclo.add("2013-5");
        Ciclo.add("2013-2");
        Ciclo.add("2013-1");
        Ciclo.add("2012-5");
        Ciclo.add("2012-2");
        Ciclo.add("2012-1");
        Ciclo.add("2011-5");
        Ciclo.add("2011-2");
        Ciclo.add("2011-1");
        UnidadApren.add("Introducción a los sistemas computacionales");
        UnidadApren.add("Contabilidad");
        UnidadApren.add("Introducción a la programación");
        UnidadApren.add("Programación estructurada");
        int op = 1;
        /*switch(op){
            case 1: entregados = ed.getFullProgramaEdu(UnidadA, Plan, Programa, Ciclo);
                    BarChartCL demo1 = new BarChartCL("Estadísticas de Reportes Entregados por Programa Educativo",entregados,"ExcelPOIGrafica1");
                    demo1.addChartToExcel(demo1.createChart(demo1.createDataset()),wb);
                break;
            case 2: entregados = ed.getFullAreaConocimiento(UnidadA, AC, Plan, Programa, Ciclo);
                    BarChartCL demo2 = new BarChartCL("Estadísticas de Reportes Entregados por Area de conocimientos",entregados,"ExcelPOIGrafica2");
                    demo2.addChartToExcel(demo2.createChart(demo2.createDataset()),wb);
                break;
            case 3: entregados = ed.getFullProfesor(UnidadA, AC, Plan, Programa, Ciclo,UnidadApren);
                    BarChartCL demo3 = new BarChartCL("Estadísticas de Reportes Entregados por Profesor",entregados,"ExcelPOIGrafica3");
                    demo3.addChartToExcel(demo3.createChart(demo3.createDataset()),wb);
                break;
            case 4: entregados = ed.getFullUnidadAprend(UnidadA, AC, Plan, Programa, Ciclo,UnidadApren);
                    BarChartCL demo4 = new BarChartCL("Estadísticas de Reportes Entregados por Unidad de Aprendizaje",entregados,"ExcelPOIGrafica4");
                    demo4.addChartToExcel(demo4.createChart(demo4.createDataset()),wb);
                break;
            default: System.out.println("Valor no valido");
        }*/
    }
}