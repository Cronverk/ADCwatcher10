package org.jfree.chart.demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class SocketThread extends Thread {

	private boolean Islive = true;
    private DataInputStream in = null;
    private DataOutputStream out = null;
	public String data = "0 0 0 0 0 0 0 0 0 ";
    private int port;
    public  boolean error_flag = false;
	public String errors = "";
	
	public SocketThread( int port) {
		// TODO Auto-generated constructor stub
		this.port = port;
		}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
		ServerSocket ss = new ServerSocket(4444); 
		Socket socket = null;
	        while( Islive ){
				System.out.println("Started :) ");
		        socket = ss.accept(); /* Ждем клента*/
		        System.out.println("Got a client :) ");
		      
		        InputStream sin = socket.getInputStream();
		        OutputStream sout = socket.getOutputStream();
		
		        in = new DataInputStream(sin);
		        out = new DataOutputStream(sout);
			
			String line = null;
			
				while(true){
					line = in.readUTF(); /* Строка о готовности приема*/
					System.out.println( "line start sending  = " + line );
					out.writeUTF(data); 
					out.flush(); 
					line = in.readUTF();
			    	System.out.println(line);
			    	if(line == "finish") break;
				    	else{
				    	out.writeUTF("ok"); 
						out.flush();
				    	}
				}
			} 
	        out.writeUTF("finish"); 
	        out.flush();

	        in.close();
			out.close();
	    	socket.close();
		}catch(Exception x) { 
			
			errors += x.toString()+" ;\n";
			error_flag = true;
		}
	
	}
	public void close(){
		
			 Islive = false;

		}

}
