package robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class R_Ultrasonic extends AnalogInput {

    int port;
    double distance;

    public R_Ultrasonic(int port) {
	super(port);
	this.port = port;
    }

    public double getDistance() {
	distance = super.getVoltage() * (256.0 / 5.0);
	return distance;
    }

}
