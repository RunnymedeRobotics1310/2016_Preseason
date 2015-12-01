
package robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
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
	Encoder leftEncoder = new Encoder(RobotMap.EncoderMap.LEFT.ch1, RobotMap.EncoderMap.LEFT.ch2);
	Encoder rightEncoder = new Encoder(RobotMap.EncoderMap.RIGHT.ch1, RobotMap.EncoderMap.RIGHT.ch2);

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

	public void updateDashboard() {
		SmartDashboard.putData("Left Motor", leftMotor);
		SmartDashboard.putData("Right Motor", rightMotor);
		SmartDashboard.putData("Limit Switch", limitSwitch);
		SmartDashboard.putData("Left Encoder", leftEncoder);
		SmartDashboard.putData("Right Encoder", rightEncoder);
	}
}
