package robot;

import edu.wpi.first.wpilibj.Gyro;

public class R_Gyro extends Gyro {
	public R_Gyro(int port) {
		super(port);
	}

	@Override
	public double getAngle() {
		double angle = -super.getAngle() % 360;
		return (angle < 0) ? angle + 360 : angle;
	}
	
	public double getAngleDifference(double targetAngle) {
		double currentAngle = getAngle();
		double difference = targetAngle - currentAngle;
		
		if (difference > 180) {
			difference -= 360;
		} else if (difference < -180) {
			difference += 360;
		}
		
		return difference;
	}
}
