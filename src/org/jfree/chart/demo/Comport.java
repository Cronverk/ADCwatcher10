package org.jfree.chart.demo;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;


public class Comport extends Thread {
	
	
	static double data;
	private static SerialPort serialPort;
	private boolean islive = false; 
	private static Clock  clock = new Clock();
	private static Clock time_cur = new Clock();
	public static boolean flag = true;
	public  boolean error_flag = false;
	public String errors = "";
	public static byte[] inf;
	
	public Comport( ) {		
	}
	
	public Comport(String port_name , int baudRate ,int data_bits ) {		
		
		 try {
				serialPort = new SerialPort(port_name);
				serialPort.openPort();
				islive = serialPort.isOpened();
				serialPort.setParams(baudRate, data_bits, SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
				serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN);
				System.out.println("baudRate "+baudRate);
				serialPort.addEventListener(new EventListener(), SerialPort.MASK_RXCHAR);
				int mask = SerialPort.MASK_RXCHAR;
				serialPort.setEventsMask(mask);
				islive = true;
				flag = true;
			} catch (SerialPortException e) {
				islive = false;
				// TODO Auto-generated catch block
				System.out.println("port error "+e.toString());
				errors += e.toString()+" ;\n";
				flag = false;
				error_flag = true;
				
			}
		
	 }
	
	public void run() {

		while(flag ==true){}
	}
	
	public synchronized Clock get_time(){
		
		return time_cur;
	} 
	
	public void set_inf( byte[] inf){
		
		inf =  this.inf;
	}

	public boolean get_life(){
		return islive;
		
	} 
	
	String[] get_ports_names(){
		
		 return SerialPortList.getPortNames();
	}
	
    private static class EventListener implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR()){
            
                try {
                	String str = "";
                	inf = serialPort.readBytes(51);
                	for( int  i = 0 ; i < 20; i++){
                		 
                		str+= (char)inf[i];
                	}
                    time_cur.sec = clock.sec;
	                time_cur.mlsec = clock.mlsec;
                }
                catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
	 
	 public void close(){		
		 try {
			serialPort.closePort();
			islive = false;
			stop();
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			errors += e.toString()+" ;\n";
			error_flag = true;
		}
	 }
	 
}
