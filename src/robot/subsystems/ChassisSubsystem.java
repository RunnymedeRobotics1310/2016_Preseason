
package robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
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
	DigitalInput limitSwitch = new DigitalInput(RobotMap.SensorMap.LIMIT_SWITCH.port);
	Encoder leftEncoder = new Encoder(RobotMap.EncoderMap.LEFT.ch1, RobotMap.EncoderMap.LEFT.ch2);
	Encoder rightEncoder = new Encoder(RobotMap.EncoderMap.RIGHT.ch1, RobotMap.EncoderMap.RIGHT.ch2);

	PIDController leftMotorPID = new PIDController(0.5, 0.0, 0.0, 1.0, 
			new PIDSource() {
				@Override
				public double pidGet() {
					return leftEncoder.getRate()/1800.0;
				}},  
			leftMotor);
	
	PIDController rightMotorPID = new PIDController(0.5, 0.0, 0.0, 1.0, 
			new PIDSource() {
				@Override
				public double pidGet() {
					return rightEncoder.getRate()/1800.0;
				}}, 
			rightMotor);

	// Gyro
	Gyro gyro = new Gyro(RobotMap.SensorMap.GYRO.port);
	
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

		leftMotorPID.setSetpoint(leftSpeed);
		rightMotorPID.setSetpoint(rightSpeed);
		if (!leftMotorPID.isEnable()) {
			leftMotorPID.enable();
		}
		if (!rightMotorPID.isEnable()) {
			rightMotorPID.enable();
		}
	}

	@Override
	public void updateDashboard() {
		SmartDashboard.putData("Left Motor", leftMotor);
		SmartDashboard.putData("Right Motor", rightMotor);
		SmartDashboard.putData("Limit Switch", limitSwitch);
		SmartDashboard.putData("Left Encoder", leftEncoder);
		SmartDashboard.putData("Right Encoder", rightEncoder);
		SmartDashboard.putData("Left Motor PID", leftMotorPID);
		SmartDashboard.putData("Right Motor PID", rightMotorPID);
		SmartDashboard.putData("Gyro", gyro);
	}
}
