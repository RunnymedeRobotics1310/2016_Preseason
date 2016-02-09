package robot;

import edu.wpi.first.wpilibj.Victor;
import robot.RobotMap.MotorMap;

public class R_Victor extends Victor {
	
	public R_Victor(MotorMap motor) {
		super(motor.port);
		this.setInverted(motor.inverted);
	}
}
