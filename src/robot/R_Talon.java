package robot;

import edu.wpi.first.wpilibj.Talon;
import robot.RobotMap.MotorMap;

public class R_Talon extends Talon {
	
	public R_Talon(MotorMap motor) {
		super(motor.port);
		this.setInverted(motor.inverted);
	}
}
