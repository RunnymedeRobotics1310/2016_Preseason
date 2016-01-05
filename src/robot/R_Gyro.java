package robot;

import edu.wpi.first.wpilibj.Gyro;

public class R_Gyro extends Gyro {

	public R_Gyro(int port) {
		super(port);
	}

	// Invert the Gyro angle because the gyro is 
	// mounted upside down.
	public double getAngle() {
		return - super.getAngle();
	}
}
