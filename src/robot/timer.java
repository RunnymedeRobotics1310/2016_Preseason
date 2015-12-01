package timer;

import java.util.Timer;
import java.util.TimerTask;


//TODO , something definitely

public class timer {
	private long startTime;
	private static Timer timer;
	private static int interval;
	
	//Start the timer
		public void start(){
			startTime = System.nanoTime();		
		}
	
	//see how much time has passed
		public double deltaTime(){
	        long currentTime = System.nanoTime();
	        return (currentTime - startTime) / 1000000000.0;	// Difference is converted to seconds		
		}
		
		
		
		public void countDown(int amountTime){
			
			int delay = 0; // delay of schedule starting
			int period = 500; // frequency of display in Milliseconds
			
			interval = amountTime;
			
			timer = new Timer();
			// start task, repeat at the schedule below
		    timer.scheduleAtFixedRate(new TimerTask() {
		    	// Do this when the interval has occured
		        public void run() {
		            System.out.println(setInterval());

		        }
		    }, delay, period ); // this is the schedule which it repeats the task
			
		}
		
		private static final int setInterval() {
		    if (interval == 1)
		        timer.cancel();
		    return --interval;
		}
		
		public void pause(){
			
			// a comment
			
		}

}
