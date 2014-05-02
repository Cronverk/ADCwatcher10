package org.jfree.chart.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;


import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class RightPanel extends JPanel {

	private JButton btnOpen;
	public Thread t;
	public boolean open  = true;
	private Comport comport = new Comport();
	JLabel lblName;
    public  boolean error_flag = false;
	public String errors = "";
	public JTextArea err =new JTextArea();
	/**
	 * Create the panel.
	 */
	int width ;
	int height;
	String Baude[];
	String Name[];
	String Name1[];
	String Data[] = new String[2];
	
	private JComboBox  comboBox;
	
	public RightPanel( int x, int y, int width, int height, String baude[] ,  String name[] ) {
	
	
		setBackground(new Color( 176,199,246));
		
		this.width = width;
		this.height = height;
		
		Data[0] ="8";
		Data[1]= "9";
		 
		Name  = name;
		Baude = baude;
		
		setLayout(new GridLayout(8, 1, 0, 0));
	
	    lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Arial" , 15 ,16));
		add(lblName);
		
		comboBox = new JComboBox(Name);
		comboBox.setFont(new Font("Arial" , 15 ,16));
		add(comboBox);
		
		JLabel lblBaude = new JLabel("Baude");
		lblBaude.setFont(new Font("Arial" , 15 ,16));
		lblBaude.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblBaude);
		
		final JComboBox comboBox_1 = new JComboBox(Baude);
		comboBox_1.setFont(new Font("Arial" , 15 ,16));
		add(comboBox_1);
		
		JLabel lblDataSize = new JLabel("Data size");
		lblDataSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataSize.setFont(new Font("Arial" , 15 ,16));
		add(lblDataSize);
		
		final JComboBox comboBox_2 = new JComboBox(Data);
		comboBox_2.setFont(new Font("Arial" , 15 ,16));
		add(comboBox_2);
		
		JSeparator separator = new JSeparator();
		add(separator);
		
		btnOpen = new JButton("Open");
		btnOpen.setFont(new Font("Arial" , 15 ,16));
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( btnOpen.getText() == "Open"){
					btnOpen.setText("Close");
					open = true;
					int baude = 9600, data =8;
					try{
					baude = Integer.parseInt((String)comboBox_1.getSelectedItem());
					data = Integer.parseInt((String)comboBox_2.getSelectedItem());
					}catch(Exception e){
						errors += "Enter correct Bauderate\n";	
						error_flag = true;
						
					}
				    comport = new Comport( (String)comboBox.getSelectedItem(),baude,data);
					comport.start();
				
				}
				else{
					comport.flag = false;
					btnOpen.setText("Open");
					open = false;
					
					comport.close();
				//	comport.stop();
					
					System.out.println("Comport = "+ comport.isAlive());
				System.out.println("Comport life  = "+ comport.get_life());
					
				}
			}
		});
		add(btnOpen);
		setBounds(x+50, y,width -40, height -20 );
		err.setBounds(x+50, y,50, 50);
		//add(err);

	}

	public void set_names( String[] names){
		Name = names;
		redraw();
		
	}
	
	public Comport get_comport(){
		return comport;
		
	}


	public void redraw(){
		// TODO Auto-generated method stub
			if( Arrays.equals(Name, Name1) != true){
					comboBox.removeAllItems();
					for( int i = 0 ; i < Name.length ;i++)
						comboBox.addItem(Name[i]);
			}	
		}

	public void close() {
		// TODO Auto-generated method stub
		comport.close();
	}

}
