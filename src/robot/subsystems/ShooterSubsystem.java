package robot.subsystems;

import java.util.ArrayList;
import java.util.List;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.R_PIDController;
import robot.R_PIDInput;
import robot.R_Subsystem;
import robot.R_Victor;
import robot.RobotMap;
import robot.commands.JoystickCommand;

/**
 *
 */
public class ShooterSubsystem extends R_Subsystem {

	//Need to create new enums for ports, etc.
	Victor intakeMotor = new R_Victor(RobotMap.MotorMap.INTAKE_MOTOR);
	Victor leftShooterMotor = new R_Victor(RobotMap.MotorMap.LEFT_SHOOTER_MOTOR);
	Victor rightShooterMotor = new R_Victor(RobotMap.MotorMap.RIGHT_SHOOTER_MOTOR);
	DigitalInput shooterLimitSwitch = new DigitalInput(RobotMap.SensorMap.SHOOTER_LIMIT_SWITCH.port);
	
	//Third parameter is a boolean that reverses the encoders
	Encoder leftShooterEncoder = new Encoder(RobotMap.EncoderMap.LEFT_SHOOTER.ch1, RobotMap.EncoderMap.LEFT_SHOOTER.ch2, 0);
	Encoder rightShooterEncoder = new Encoder(RobotMap.EncoderMap.RIGHT_SHOOTER.ch1, RobotMap.EncoderMap.RIGHT_SHOOTER.ch2, 0);

	/*
	 * Motor PID Controllers
	 */
	R_PIDInput leftShooterPIDInput = new R_PIDInput() {
		@Override
		public double pidGet() {
			return leftShooterEncoder.getRate() / RobotMap.EncoderMap.LEFT_SHOOTER.maxRate;
		}
	};

	R_PIDInput rightShooterPIDInput = new R_PIDInput() {
		@Override
		public double pidGet() {
			return -rightShooterEncoder.getRate() / RobotMap.EncoderMap.RIGHT_SHOOTER.maxRate;
		}
	};

	R_PIDController leftShooterMotorPID = new R_PIDController(1.0, 0.0, 0.0, 1.0, leftShooterPIDInput, leftShooterMotor);

	R_PIDController rightShooterMotorPID = new R_PIDController(1.0, 0.0, 0.0, 1.0, rightShooterPIDInput, rightShooterMotor);

	List<R_PIDController> pidControllers = new ArrayList<R_PIDController>();

	public void init() {
		pidControllers.add(leftShooterMotorPID);
		pidControllers.add(rightShooterMotorPID);
	}

	//This will need to be CHANGED!
	//!!!!!!!!!!!!!!!!!!!
	public void initDefaultCommand() {
		setDefaultCommand(new JoystickCommand());
	}

	public void shooterWheelSpeed(double leftSpeed, double rightSpeed) {
		SmartDashboard.putNumber("LeftShooterMotorSpeed", leftSpeed);
		SmartDashboard.putNumber("RightShooterMotorSpeed", rightSpeed);

		leftShooterMotorPID.setSetpoint(leftSpeed);
		rightShooterMotorPID.setSetpoint(rightSpeed);

		if (!leftShooterMotorPID.isEnabled()) {
			leftShooterMotorPID.enable();
		}
		if (!rightShooterMotorPID.isEnabled()) {
			rightShooterMotorPID.enable();
		}
	}
	
	public void intakeToLimit() {
		//Normally ON/true proximity sensors
		if(shooterLimitSwitch.get()) {
			intakeMotor.set(1.0);
		}
		else {
			intakeMotor.set(0);
		}
	}
	
	public void intakeReverse() {
		intakeMotor.set(-1.0);
	}
	
	public void intakeOff() {
		intakeMotor.set(0);
	}

	@Override
	public void periodic() {
		// Update all of the PIDs every loop
		for (R_PIDController pid : pidControllers) {
			pid.calculate();
		}
	}

	/**
	 * Resets the encoders.
	 */
	public void resetEncoders() {
		this.leftShooterEncoder.reset();
		this.rightShooterEncoder.reset();
	}
	
	@Override
	public void updateDashboard() {
		SmartDashboard.putData("Left Shooter Motor", leftShooterMotor);
		SmartDashboard.putData("Right Shooter Motor", rightShooterMotor);
		SmartDashboard.putData("Left Shooter Encoder", leftShooterEncoder);
		SmartDashboard.putData("Right Shooter Encoder", rightShooterEncoder);
		SmartDashboard.putData("Left Shooter Motor PID", leftShooterMotorPID);
		SmartDashboard.putData("Right Shooter Motor PID", rightShooterMotorPID);
	}
}
