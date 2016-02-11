package robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.R_PIDController;
import robot.R_PIDInput;
import robot.R_Subsystem;
import robot.R_Talon;
import robot.RobotMap;
import robot.commands.JoystickCommand;

/**
 *
 */
public class ChassisSubsystem extends R_Subsystem {

	Talon leftMotor = new R_Talon(RobotMap.MotorMap.LEFT_MOTOR);
	Talon rightMotor = new R_Talon(RobotMap.MotorMap.RIGHT_MOTOR);
	DigitalInput leftLimitSwitch = new DigitalInput(RobotMap.SensorMap.LEFT_LIMIT_SWITCH.port);
	DigitalInput centerLimitSwitch = new DigitalInput(RobotMap.SensorMap.CENTER_LIMIT_SWITCH.port);
	DigitalInput rightLimitSwitch = new DigitalInput(RobotMap.SensorMap.RIGHT_LIMIT_SWITCH.port);
	Encoder leftEncoder = new Encoder(RobotMap.EncoderMap.LEFT.ch1, RobotMap.EncoderMap.LEFT.ch2);
	Encoder rightEncoder = new Encoder(RobotMap.EncoderMap.RIGHT.ch1, RobotMap.EncoderMap.RIGHT.ch2);

	/*
	 * Motor PID Controllers
	 */
	R_PIDInput leftPIDInput = new R_PIDInput() {
		@Override
		public double pidGet() {
			return leftEncoder.getRate() / RobotMap.EncoderMap.LEFT.maxRate;
		}
	};

	R_PIDInput rightPIDInput = new R_PIDInput() {
		@Override
		public double pidGet() {
			return -rightEncoder.getRate() / RobotMap.EncoderMap.RIGHT.maxRate;
		}
	};

	R_PIDController leftMotorPID = new R_PIDController(1.0, 0.0, 0.0, 1.0, leftPIDInput, leftMotor);

	R_PIDController rightMotorPID = new R_PIDController(1.5, 0.0, 0.0, 1.0, rightPIDInput, rightMotor);

	List<R_PIDController> pidControllers = new ArrayList<R_PIDController>();

	public void init() {
		pidControllers.add(leftMotorPID);
		pidControllers.add(rightMotorPID);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new JoystickCommand());
	}

	public void setSpeed(double leftSpeed, double rightSpeed) {
		SmartDashboard.putNumber("LeftMotorSpeed", leftSpeed);
		SmartDashboard.putNumber("RightMotorSpeed", rightSpeed);

		leftMotorPID.setSetpoint(leftSpeed);
		rightMotorPID.setSetpoint(rightSpeed);

		if (!leftMotorPID.isEnabled()) {
			leftMotorPID.enable();
		}
		if (!rightMotorPID.isEnabled()) {
			rightMotorPID.enable();
		}
	}
	
	public double getEncoderDifference() {
		return rightEncoder.getRaw() - leftEncoder.getRaw();
	}
	
	/**
	 * Gets the approximate distance using encoder counts by averaging the two
	 * encoder distances.
	 * 
	 * @return the approximate distance.
	 */
	public double getEncoderDistance() {
		return (this.leftEncoder.getDistance() - this.rightEncoder.getDistance()) / 2.0 / RobotMap.EncoderMap.LEFT.countsPerInch;
	}

	public void resetEncoders() {
		this.leftEncoder.reset();
		this.rightEncoder.reset();
	}

	public boolean getFrontLimit() {
	    boolean frontLimit = !rightLimitSwitch.get() || !leftLimitSwitch.get() || !centerLimitSwitch.get();
	    SmartDashboard.putBoolean("Front Limit", frontLimit);
	    return frontLimit;
	}



	@Override
	public void periodic() {
		// Update all of the PIDs every loop
		for (R_PIDController pid : pidControllers) {
			pid.calculate();
		}
	}
	
	@Override
	public void updateDashboard() {
		SmartDashboard.putData("Left Motor", leftMotor);
		SmartDashboard.putData("Right Motor", rightMotor);
		SmartDashboard.putData("Left Limit Switch", leftLimitSwitch);
		SmartDashboard.putData("Center Limit Switch", centerLimitSwitch);
		SmartDashboard.putData("Right Limit Switch", rightLimitSwitch);
		SmartDashboard.putData("Left Encoder", leftEncoder);
		SmartDashboard.putData("Right Encoder", rightEncoder);
		SmartDashboard.putData("Left Motor PID", leftMotorPID);
		SmartDashboard.putData("Right Motor PID", rightMotorPID);
	}
}
