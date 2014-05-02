package org.jfree.chart.demo;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import org.jfree.data.xy.XYSeries;

public class Bottom_panel extends JPanel {
	private final Action action = new SwingAction();
	private Graphic graf;
	private ArrayList<XYSeries>serie;
	/**
	 * Create the panel.
	 */
	public Bottom_panel(ArrayList<Graphic> Grafs ,ArrayList<XYSeries>series, int x,int y , int width, int height) {
		
		graf = Grafs.get(8);
		serie = series;
		setBounds(x,y,width , height);
		setLayout(new GridLayout(0, 4, 2, 0));
		final JCheckBox chckbxNewCheckBox_5 = new JCheckBox("Graph 1");
		chckbxNewCheckBox_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 XYSeries sr = serie.get(0);
				
				if(chckbxNewCheckBox_5.isSelected() == true){
				
					 graf.change_data(serie.get(0), true);
				}
				else{
					 graf.change_data(serie.get(0), false);
				}
			}
		});
		chckbxNewCheckBox_5.setBackground(new Color(176,199,246));
		add(chckbxNewCheckBox_5);
		
		final JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Graph2");
		chckbxNewCheckBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 
				 XYSeries sr = serie.get(1);
				
				if(chckbxNewCheckBox_1.isSelected() == true){
				
					 graf.change_data(serie.get(1), true);
				}
				else{
					 graf.change_data(serie.get(1), false);
				}
			}

		});
		chckbxNewCheckBox_1.setBackground(new Color(176,199,246));
		add(chckbxNewCheckBox_1);
		
		final JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Graph 3");
		chckbxNewCheckBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				 XYSeries sr = serie.get(2);
				
				if(chckbxNewCheckBox_2.isSelected() == true){
				
					 graf.change_data(serie.get(2), true);
				}
				else{
					 graf.change_data(serie.get(2), false);
				}
			}

		});
		chckbxNewCheckBox_2.setBackground(new Color(176,199,246));
		add(chckbxNewCheckBox_2);
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("Graph 4");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
		
			 XYSeries sr = serie.get(3);
				
			if(chckbxNewCheckBox.isSelected() == true){
			
				 graf.change_data(serie.get(3), true);
			}
			else{
				 graf.change_data(serie.get(3), false);
			}
		}

		});
		chckbxNewCheckBox.setBackground(new Color(176,199,246));
		add(chckbxNewCheckBox);
		
		final JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Graph 5");
		chckbxNewCheckBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				 XYSeries sr = serie.get(4);
				
				if(chckbxNewCheckBox_3.isSelected() == true){
				
					 graf.change_data(serie.get(4), true);
				}
				else{
					 graf.change_data(serie.get(4), false);
				}
			}

		});
		chckbxNewCheckBox_3.setBackground(new Color(176,199,246));
		add(chckbxNewCheckBox_3);
		
		final JCheckBox chckbxNewCheckBox_4 = new JCheckBox("Graph 6");
		chckbxNewCheckBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				 XYSeries sr = serie.get(5);
				
				if(chckbxNewCheckBox_4.isSelected() == true){
				
					 graf.change_data(serie.get(5), true);
				}
				else{
					 graf.change_data(serie.get(5), false);
				}
			}
		});
		chckbxNewCheckBox_4.setBackground(new Color(176,199,246));
		add(chckbxNewCheckBox_4);
		
		final JCheckBox chckbxNewCheckBox_6 = new JCheckBox("Graph 7");
		chckbxNewCheckBox_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				 XYSeries sr = serie.get(6);
				
				if(chckbxNewCheckBox_6.isSelected() == true){
				
					 graf.change_data(serie.get(6), true);
				}
				else{
					 graf.change_data(serie.get(6), false);
				}
			}

		});
		chckbxNewCheckBox_6.setBackground(new Color(176,199,246));
		add(chckbxNewCheckBox_6);
		
		final JCheckBox chckbxNewCheckBox_7 = new JCheckBox("Graph 8");
		chckbxNewCheckBox_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 XYSeries sr = serie.get(7);
				
				if(chckbxNewCheckBox_6.isSelected() == true){
				
					 graf.change_data(serie.get(7), true);
				}
				else{
					 graf.change_data(serie.get(7), false);
				}
			}

		});
		chckbxNewCheckBox_7.setBackground(new Color(176,199,246));
		add(chckbxNewCheckBox_7);
		
		
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
