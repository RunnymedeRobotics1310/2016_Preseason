
package robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.R_Subsystem;
import robot.RobotMap;
import robot.commands.JoystickCommand;

/**
 *
 */
public class ChassisSubsystem extends R_Subsystem {

	Talon leftMotor = new Talon(RobotMap.MotorMap.LEFT_MOTOR.port);
	Talon rightMotor = new Talon(RobotMap.MotorMap.RIGHT_MOTOR.port);
	DigitalInput limitSwitch = new DigitalInput(RobotMap.sensorMap.LIMIT_SWITCH.port);

	public void initDefaultCommand() {

		setDefaultCommand(new JoystickCommand());
	}

	public void setSpeed(double leftSpeed, double rightSpeed) {
		if (!limitSwitch.get()) {
			leftSpeed = 0;
			rightSpeed = 0;
		}

		if (RobotMap.MotorMap.LEFT_MOTOR.inverted) {
			leftSpeed *= -1;
		}

		if (RobotMap.MotorMap.RIGHT_MOTOR.inverted) {
			rightSpeed *= -1;
		}

		leftMotor.set(leftSpeed);
		rightMotor.set(rightSpeed);
	}

	@Override
	public void updateDashboard() {
		SmartDashboard.putBoolean("limit switch", limitSwitch.get());
	}
}
