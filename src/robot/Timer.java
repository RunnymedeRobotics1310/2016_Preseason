package robot;

/**
 * This class implements a timer that can be used in the iterative part of the robot
 * <p>
 * It allows for timing of events.
 */
public class Timer {

	/**
	 * Start the timer for the specified number of seconds.
	 * <p>
	 * If the timer is already running, this call does nothing.
	 */
	public void start(double time) {
	}
	
	/**
	 * Stop the timer.
	 * <p>
	 * If the timer is stopped it is always expired.
	 */
	public void stop() {}
	
	/**
	 * Determine if the timer has expired.
	 * @return {@code true} if the timer has expired.  {@code false} otherwise
	 */
	public boolean isExpired() {
		return true;
	}

}
