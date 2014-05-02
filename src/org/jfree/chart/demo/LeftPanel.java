package org.jfree.chart.demo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;


public class LeftPanel extends JPanel {

	
	public LeftPanel(ArrayList<ChartPanel> graphics , ArrayList<Graphic> Grafs , ArrayList<JFreeChart> charts, ArrayList<XYSeries>  Series ) {
		//setBackground(new Color(152 ,134, 202));
		//setBackground(new Color(234 ,247, 202));
		setSize(800, 530);
		setLayout(null);
		setBackground(new Color( 176,199,246));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(174 , 250 ,127));
		
		tabbedPane.setFont(new Font("Arial" , 15 ,16));
		tabbedPane.setOpaque(false);
		
		tabbedPane.setBounds(7, 6, 750, 505);
		tabbedPane.setAlignmentY(Component.TOP_ALIGNMENT);
		add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color( 176,199,246));
		panel.setToolTipText("Graphic 1");
		panel.add(graphics.get(0));
		panel.add(new bottom_slider(new Color( 176,199,246) , charts , Grafs.get(0),0));
		tabbedPane.addTab("Graphic 1", null, panel, null);
		panel.setLayout(null);
	
		JPanel panel_2 = new JPanel();
		panel_2.add(graphics.get(1));
		tabbedPane.addTab("Graphic 2", null, panel_2, null);
		panel_2.setLayout(null);
		panel_2.setBackground(new Color( 176,199,246));
		panel_2.add(new bottom_slider(new Color( 176,199,246) , charts, Grafs.get(1),1));
		
		JPanel panel_1 = new JPanel();
		panel_1.add(graphics.get(2));
		tabbedPane.addTab("Graphic 3", null, panel_1, null);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color( 176,199,246));
		panel_1.add(new bottom_slider(new Color( 176,199,246) , charts, Grafs.get(2),2));
		panel_1.setBackground(new Color( 176,199,246));
		
		
		JPanel panel_3 = new JPanel();
		panel_3.add(graphics.get(3));
		tabbedPane.addTab("Graphic 4", null, panel_3, null);
		panel_3.setLayout(null);
		panel_3.setBackground(new Color( 176,199,246));
		panel_3.add(new bottom_slider(new Color( 176,199,246) , charts, Grafs.get(3),3));
		
		JPanel panel_4 = new JPanel();
		panel_4.add(graphics.get(4));
		tabbedPane.addTab("Graphic 5 ", null, panel_4, null);
		panel_4.setLayout(null);
		panel_4.setBackground(new Color( 176,199,246));
		panel_4.add(new bottom_slider(new Color( 176,199,246) , charts, Grafs.get(4),4));
		
		JPanel panel_5 = new JPanel();
		panel_5.add(graphics.get(5));
		tabbedPane.addTab("Graphic 6", null, panel_5, null);
		panel_5.setLayout(null);
		panel_5.setBackground(new Color( 176,199,246));
		panel_5.add(new bottom_slider(new Color( 176,199,246) , charts, Grafs.get(5),5));
		
		JPanel panel_6 = new JPanel();
		panel_6.add(graphics.get(6));
		tabbedPane.addTab("Graphic 7", null, panel_6, null);
		panel_6.setLayout(null);
		panel_6.setBackground(new Color( 176,199,246));
		panel_6.add(new bottom_slider(new Color( 176,199,246) , charts, Grafs.get(6),6));
		
		JPanel panel_7 = new JPanel();
		panel_7.add(graphics.get(7));
		tabbedPane.addTab("Graphic 8", null, panel_7, null);
		panel_7.setLayout(null);
		panel_7.setBackground(new Color( 176,199,246));
		panel_7.add( new bottom_slider(new Color( 176,199,246) , charts, Grafs.get(7),7));
		
		JPanel panel_8 = new JPanel();
		panel_8.add(new Bottom_panel(Grafs,Series,5,390,735,50 ));
		graphics.set(8, Grafs.get(8).get_ChartPanel(740, 390));
		panel_8.add(graphics.get(8));
		
		tabbedPane.addTab("Graphic 9", null, panel_8, null);
		panel_8.setLayout(null);
		panel_8.setBackground(new Color( 176,199,246));

		JPanel panel_9 = new JPanel();
		panel_9.add(graphics.get(9));
		tabbedPane.addTab("Graphic 10", null, panel_9, null);
		panel_9.setLayout(null);
		panel_9.setBackground(new Color( 176,199,246));		


	}
}
