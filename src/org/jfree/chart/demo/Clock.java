package org.jfree.chart.demo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class Clock {

	public int sec = 0;
//	public int min = 0;
	public int mlsec = 0;
	Timer timer;
	boolean flag = false; 
	
	public Clock(){
		 

		timer = new javax.swing.Timer( 10, new ActionListener()
		  {
		      public void actionPerformed(ActionEvent e)
		      {
		    	  if(flag ==false)
		    		  inkr();			
		      }
		  } );		
	    timer.start();
	};
	
	 public void  starting(){
		 
		flag = false;
	}
	 
	  void  pause(){
		  
		flag = true;
	 }
	  
	  void inkr(){
			 
			 if (mlsec < 100){
				 mlsec++;
			 }else{
				 sec++;
				 mlsec = 0;
			 }
		}
}
