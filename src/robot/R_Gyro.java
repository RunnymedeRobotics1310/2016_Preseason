package robot;

import edu.wpi.first.wpilibj.Gyro;

public class MyGyro extends Gyro {
	public MyGyro(int port) {
		super(port);
	}

	@Override
	public double getAngle() {
		double angle = -super.getAngle() % 360;
		return (angle < 0) ? angle + 360 : angle;
	}
}
