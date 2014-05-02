package org.jfree.chart.demo;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;


public class bottom_slider extends JPanel {
	private JTable table;
	private JSlider slider_2 ;
	private JSlider slider_3; 
	private ArrayList<JFreeChart> charts;
	private Marker distanceTiers1;
	private Marker distanceTiers2;
	private double X1 = 0,X2= 0,Y1 =0,Y2 =0;
	private XYPlot plot ;
	Object colum =new Object[][] {
				{"Slider1", null, null, null,null},
				{"Slider2", null, null, null,null},
			};
	Object stolb = new String[] {
			"Slider", "X", "Y", "X1-X2","Y1-Y2" 
		};

	/**
	 * Create the panel.
	 */
		
	public bottom_slider(Color color,ArrayList<JFreeChart> chart , final Graphic graph , int num) {
		setBounds(5,380,740,100);
		setLayout(null);
		charts = chart;
		plot = charts.get(num).getXYPlot();

		table = new JTable(new Object[][] {
				{"Slider", "X", "Y", "X1-X2","Y1-Y2"},
				{"Slider1", null, null, null,null},
				{"Slider2", null, null, null,null}
			}, new String[] {
					"Slider", "X", "Y", "X1-X2","Y1-Y2" 
			});
		setBackground(color);
		table.setBounds(255, 22, 485, 48);
			add(table);
			
			slider_2 = new JSlider();
			slider_2.setBounds(5, 22, 250, 20);
			slider_2.setBackground(color);
			slider_2.addChangeListener(new ChangeListener() {
	 
				@Override
	            public void stateChanged(ChangeEvent e) {
					try{
	            	 int value = slider_2.getValue();   
	                
	                 ValueAxis domainAxis = plot.getDomainAxis();   
	                 Range range = domainAxis.getRange();
	                 
	                 X1 = domainAxis.getLowerBound() + (value / 100.0) * range.getLength();   
	              
	             	int i = 0;
	             	for( ;i <  graph.mas_x.size(); i++){
	             	//	System.out.println("X"+  Arrays.toString(graph.mas_x.toArray()));
					   if( graph.mas_x.get(i).doubleValue() <= X1 ){}
					   else{
						  break;
					   } 
				   }
					 Y1 = graph.mas_y.get(i ).doubleValue();
					}catch(Exception e1){	
						if( e.toString() =="java.lang.IndexOutOfBoundsException")
							 Y1 = graph.mas_y.get(graph.mas_y.size() -1 ).doubleValue();						
	             	}
	                 plot.removeDomainMarker(distanceTiers2);
	                 distanceTiers2 = new ValueMarker(X1);
	                 distanceTiers2.setPaint(Color.BLACK);
	                 plot.addDomainMarker(distanceTiers2);
	                 
	                 table.setValueAt(""+new BigDecimal(X1).setScale(3, RoundingMode.UP).doubleValue(), 1, 1);
	                 table.setValueAt(""+new BigDecimal(Y1).setScale(3, RoundingMode.UP).doubleValue(), 1, 2);
	                 table.setValueAt(""+new BigDecimal(X2).setScale(3, RoundingMode.UP).doubleValue(), 2,1);
	                 table.setValueAt(""+new BigDecimal(Y2).setScale(3, RoundingMode.UP).doubleValue(), 2, 2);
	                 
	             	 table.setValueAt(""+ new BigDecimal(Math.abs(X2-X1)).setScale(3, RoundingMode.UP).doubleValue(), 2, 3);
	             	 table.setValueAt(""+ new BigDecimal(Math.abs(X2-X1)).setScale(3, RoundingMode.UP).doubleValue(), 1, 3);
	             	 table.setValueAt(""+ new BigDecimal(Math.abs(Y2-Y1)).setScale(3, RoundingMode.UP).doubleValue(), 2, 4);
	               	 table.setValueAt(""+ new BigDecimal(Math.abs(Y2-Y1)).setScale(3, RoundingMode.UP).doubleValue(), 1, 4);
	            }
				
	        });
			add(slider_2);

			slider_3 = new JSlider();
			slider_3.setBounds(5, 55, 250, 20);
			slider_3.setBackground(color);
			slider_3.addChangeListener(new ChangeListener() {
	            @Override
	            public void stateChanged(ChangeEvent e) {
	            	try{
	            	 int value = slider_3.getValue();   
	             
	            	  
	                 ValueAxis domainAxis = plot.getDomainAxis();   
	                 Range range = domainAxis.getRange();   
	                
	                 X2 = domainAxis.getLowerBound() + (value / 100.0) * range.getLength();   
	                
	                 plot.removeDomainMarker(distanceTiers1);
	                 distanceTiers1 = new ValueMarker(X2); 
	                 distanceTiers1.setPaint(Color.BLACK);

	                 plot.addDomainMarker(distanceTiers1);
	                 int i=0;
	        		 for( ;i <  graph.mas_x.size(); i++){
						 
						 if( graph.mas_x.get(i).doubleValue() <= X2 ){}
						 else{
							 break;
						 }
						 
					 }
						
					 Y2 = graph.mas_y.get(i).doubleValue();
	                 
		        	}catch(Exception e1){	
						if( e.toString() =="java.lang.IndexOutOfBoundsException")
							 Y1 = graph.mas_y.get(graph.mas_y.size() -1 ).doubleValue();						
	             	}
	                 table.setValueAt(""+new BigDecimal(X1).setScale(3, RoundingMode.UP).doubleValue(), 1, 1);
	                 table.setValueAt(""+new BigDecimal(Y1).setScale(3, RoundingMode.UP).doubleValue(), 1, 2);
	                 table.setValueAt(""+new BigDecimal(X2).setScale(3, RoundingMode.UP).doubleValue(), 2,1);
	                 table.setValueAt(""+new BigDecimal(Y2).setScale(3, RoundingMode.UP).doubleValue(), 2, 2);
	                 
	             	 table.setValueAt(""+ new BigDecimal(Math.abs(X2-X1)).setScale(3, RoundingMode.UP).doubleValue(), 2, 3);
	             	 table.setValueAt(""+ new BigDecimal(Math.abs(X2-X1)).setScale(3, RoundingMode.UP).doubleValue(), 1, 3);
	             	 table.setValueAt(""+ new BigDecimal(Math.abs(Y2-Y1)).setScale(3, RoundingMode.UP).doubleValue(), 2, 4);
	               	 table.setValueAt(""+ new BigDecimal(Math.abs(Y2-Y1)).setScale(3, RoundingMode.UP).doubleValue(), 1, 4);
	            }
	        });
			add(slider_3);
	}
	
}
