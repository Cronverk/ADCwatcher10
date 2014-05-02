package next_pacage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class Clock {

	int sec = 0;
	int min = 0;
	int mlsec = 0;
	Timer timer;
	boolean flag = false; 
	
	Clock(){
		 

		timer = new javax.swing.Timer( 1, new ActionListener()
		  {
		      public void actionPerformed(ActionEvent e)
		      {
		    	  if(flag ==false)
		    		  inkr();			
		      }
		  } );		
	    timer.start();
	};
	
	
	 void  starting(){
		flag = false;
	}
	 
	  void  pause(){
		flag = true;
	 
	 }
	 
	 
	  void inkr(){
			 
			 if (mlsec < 100){
				 mlsec++;
			 }else{
				 if(sec < 59 )
				 sec++;
				 else{
					 min++;
					 sec = 0;
				 }
				 mlsec = 0;
			 }
		}
}
