package robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class R_Ultrasonic extends AnalogInput {
	R_UltrasonicReadingsStack distanceStack = new R_UltrasonicReadingsStack(10, 0);
	
	public R_Ultrasonic(int port) {
		super(port);
	}

	/**
	 * Gets the distance from the ultrasonic sensor.
	 * 
	 * @return Distance in inches
	 */
	public double getRawDistance() {
		// y = mx + b v = 0.0094d + 0.01
		// (y - b)/m = x
		return (super.getVoltage() - 0.01) / 0.0094;
	}
	
	public double getFilteredDistance() {
		distanceStack.add(getRawDistance());		
		return distanceStack.getMeanDistance();
	}
}
