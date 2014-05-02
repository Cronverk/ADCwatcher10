package org.jfree.chart.demo;

import java.awt.Color;
import java.awt.Font;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.TextAnchor;

public class Graphic {
	
	public XYSeries series;
	private XYSeriesCollection dataset;
	private String title;
	private JFreeChart chart;
	private XYPlot plot ;
	public ChartPanel chr;
	public ArrayList<Double> mas_x = new ArrayList();// Хранит значение Х
	public ArrayList<Double> mas_y = new ArrayList();//Хранит значение Y
	
	public Graphic( String title ) {
	
		this.title = title;     
		
		mas_x.add((double) 0);
		mas_y.add((double) 0);
	
	    dataset = new XYSeriesCollection();
	    chart = ChartFactory.createXYLineChart(title,"T - время ","U -напряжение ", dataset, PlotOrientation.VERTICAL, true, true, false );  
	    XYPlot plot = chart.getXYPlot();
	 
	    plot.setDomainCrosshairLockedOnData(true);
	    plot.setDomainCrosshairVisible(true);
	    plot.setRangeCrosshairVisible(true);
	    plot.setRangeCrosshairLockedOnData(true);
	  
    }
	
	void init_dataset( ArrayList<XYSeries> Series , int num){
		
		dataset.removeAllSeries();
	
		dataset.addSeries((Series.get(num)));
		
	}
	void init_dataset( ArrayList<XYSeries> Series , int num[]){
		
		dataset.removeAllSeries();
	for( int i = 0 ; i < 8;i++){
		if( num[i] != 0){
		dataset.addSeries((Series.get(i)));
		}
	}
		
	}

	void init_dataset( ArrayList<XYSeries> Series){
		
	
		dataset.removeAllSeries();
		for( int i = 0 ; i < Series.size() ; i++)
			dataset.addSeries(Series.get(i));
		
	}

	public JFreeChart get_JFreeChart(){
		
		return chart;
	}

	public JFreeChart get_chart(){
		
		return chart;
	}
	
	
	public ChartPanel get_ChartPanel(int width , int height){
		chr = new ChartPanel(chart);
		chr.setBackground(Color.blue);
	
		chr.setBounds(5, 5, width -5, height -10);
		chr.setVisible(true);
		chr.setMouseWheelEnabled(true);
		chr.addChartMouseListener(new ChartMouseListener() {

        public void chartMouseMoved(ChartMouseEvent chartmouseevent) {

        }   
        public void chartMouseClicked(ChartMouseEvent chartmouseevent) {

           SwingUtilities.invokeLater(new Runnable() {
              public void run() {
                 XYPlot xyplot = (XYPlot) chr.getChart().getPlot();
                 xyplot.clearAnnotations();
                 double x, y;
                 x = new BigDecimal( xyplot.getDomainCrosshairValue()).setScale(3, RoundingMode.UP).doubleValue();
                 
                 y = new BigDecimal(xyplot.getRangeCrosshairValue()).setScale(3, RoundingMode.UP).doubleValue();
                 XYTextAnnotation annotation = new XYTextAnnotation("("+ x + ", " + y + ")",new BigDecimal(x).setScale(3, RoundingMode.UP).doubleValue() ,y);
                 annotation.setFont(new Font("serif", Font.BOLD, 15));
                 annotation.setTextAnchor(TextAnchor.BOTTOM_CENTER);
                 xyplot.addAnnotation(annotation);
              }
           });
        }
     });
		
		return chr;   
    }
	
	
	public void add_to_mas( double x ,double y){
			
		mas_x.add(x);
		mas_y.add(y);
		
	}
	
	public void add(double x , double y){
		
		series.add(x, y);	
		//System.out.println("====="+ x);
	}

	public void change_data(XYSeries series2, boolean flag) {
		// TODO Auto-generated method stub
		
		if(flag == true){
		//	System.out.println("hi11 = "+series.getItemCount());
			dataset.addSeries(series2);
		}
		else{
			dataset.removeSeries(series2);
		}
	}
			
		
		
		
	}

