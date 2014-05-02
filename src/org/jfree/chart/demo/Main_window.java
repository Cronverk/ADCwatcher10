package org.jfree.chart.demo;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;

public class Main_window extends JFrame {

	
	private JPanel panel;

	private String names[];
	private int width = 950;
	private int height = 550;
	double cur =  0;
	public ArrayList<ChartPanel> graphics;
	private ArrayList<Graphic> Grafs;
	public ArrayList<JFreeChart> Charts = new ArrayList<>();
	private int port;
	private int[] mas = new int[10];
	private Clock time;
	private SocketThread socket = new SocketThread(4444);
	private JTextArea errors = new JTextArea("Errors");
	private ArrayList<XYSeries> series  = new ArrayList<XYSeries>();
	
	private JTabbedPane tabs;
	private String[] bode = { "NULL","300","600","1200","4800","9600","12000","14400","19200","38400","128000"};
	
	private void parser(byte[] inf){
		String str = "";
		int j = 0 ; 
		for(int i = 0 ;  i < 51 ;i++){
			if( (char)inf[i] == ' ' ){
				mas[j] = Integer.parseInt(str);
				j++;
				str = "";
			}
			else{
				str+= (char)inf[i];
			}
		}	
	}
	
	public String packetform( int mas[] , double time){
		String str = "";
		for( int i = 0 ; i < 8;i++){
			str += mas[i]*5.0/1025 + " ";			
		}
		str += time + " ";
		return str;
	}
	private Comport comport = new Comport();
	private JPanel leftpanel = new JPanel( );
	private LeftPanel lp ;
	private RightPanel rightpanel = new RightPanel( 700+20,25,width-740,height/2, bode, comport.get_ports_names() );
	
	 

	private Timer timer = new Timer(200, new ActionListener() {
	
		
		private byte[] inf = null;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int i = 0 ; 
			
			rightpanel.set_names(comport.get_ports_names());
			panel.repaint();
			comport = rightpanel.get_comport();
			String str = "Errors:\n";
			if( rightpanel.error_flag == true){
				str+=rightpanel.errors;
				rightpanel.error_flag = false;
				}
			if( comport.error_flag == true){
				str+=comport.errors;
				comport.error_flag = false;
				}
			if( socket.error_flag == true){
				str+=socket.errors;
				socket.error_flag = false;
				}
			errors.setText(str);
			
			if(comport.get_life() == true){
				comport.set_inf(inf);
				inf = comport.inf;
				if( inf!= null){
					parser(inf);
					Clock clock = comport.get_time();	
					double time = clock.sec + clock.mlsec/100;
					socket.data = new String(packetform(mas,time));
					for(int i1 = 0;i1 < Grafs.size() - 2 ;i1++, i++){					
						series.get(i1).add(time ,mas[i]*5.0/1025);
						Grafs.get(i1).init_dataset(series , i1);
						Grafs.get(i1).add_to_mas(time, mas[i]*5.0/1025);
						Grafs.get(i1).init_dataset(series , i1);
					}
				}
			}
			errors.setText("Errors\n"+"Comport:\n"+comport.errors+"Com config\n"+rightpanel.errors+"Tcp "+socket.errors);
			rightpanel.err = errors;
			rightpanel.redraw();
		}
	});

	
	
	Main_window(){
		this.setBackground(new Color(47 ,79, 79));
		errors.setBounds(500, 700, 50, 50);
		errors.setVisible(true);
	//	errors.setFont(new Font(arg0, arg1, arg2));
		
		leftpanel.setBackground(new Color(47 ,79, 79));
		
		panel = new JPanel( new BorderLayout());
		
		Grafs = new ArrayList<>();
		tabs = new JTabbedPane();
		
		leftpanel.setBounds( 0, 0, 700, height );
		leftpanel.setBackground(Color.blue);
		
		for( int  i = 0  ; i < 10 ; i++){
			series.add(new XYSeries("Graph "+ i));
			
		}
		
		arrays_init();
		jframe_init( );
		jpanel_int();
		init_graphics( 10 );
	
		lp = new LeftPanel(graphics,Grafs,Charts,series);
		panel.add(rightpanel);
		panel.add(lp);
		panel.repaint();
		
		timer.start();
		System.out.println("Socket!!");
		socket.start();
		
	}

	void arrays_init(){
		
		graphics = new ArrayList<>();
	}

	void jframe_init( ){
		setBounds(50, 50, width , height );
		setResizable(false);
		setVisible(true);
		
		
		
		
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			                    System.exit(0);
			                    socket.close();
			                    if(socket.isAlive()!=true){
			                    comport.close();
			                    timer.stop();
			                    rightpanel.close();
			                    }
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(errors);
		add(panel);
		
	}
	
	void jpanel_int(){
	    panel.setBounds(getX()+ (int)(3/4)*getWidth(), getY() ,(int)(1/4)*getWidth() , getHeight() );
	    panel.setBackground(Color.LIGHT_GRAY);
	    panel.setLayout(new BorderLayout());
	}

	void init_graphics( int n  ){
		for(int i = 0  ; i <n  ;i++){
			Graphic graf = new Graphic("U от T");
			Grafs.add(graf);			
			graphics.add( graf.get_ChartPanel( 740 , 390) );
			Charts.add(graf.get_chart());
		}
			
	}
	
	public static void main(String[] args) {
				 try {
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
			// SeaGlassLNFTest window = new SeaGlassLNFTest();
			 
			
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Main_window window =new Main_window();
		
		try {
			
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
