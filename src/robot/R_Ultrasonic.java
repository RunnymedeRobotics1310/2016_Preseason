package robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class R_Ultrasonic extends AnalogInput {

    private double distance;

    public R_Ultrasonic(int port) {
	super(port);
    }

    /**
     * Gets the distance from the ultrasonic sensor.
     * @return Distance in inches
     */
    public double getDistance() {
	//y = mx + b           v = 0.0094d + 0.01
	//(y - b)/m = x
	distance = (super.getVoltage() - 0.01) / 0.0094;

	return distance;
    }
}
