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
	distance = super.getVoltage() * (256.0 / 5.0);
	return distance;
    }

}
