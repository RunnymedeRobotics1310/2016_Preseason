
package robot.commands.auto;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.R_PIDController;
import robot.R_PIDInput;
import robot.Robot;

/**
 *
 */
public abstract class AutoGoStraightCommand extends Command {

	private static double pidAngleSetpoint;
	private static double pidOutputTurn;

	private final double angleSetpoint;
	private final double speedSetpoint;
	
	/*
	 * Angle PID Controller
	 * 
	 * These controllers are declared as static so that they can be adjusted in the console
	 */
	private static R_PIDInput gyroPIDInput = new R_PIDInput() {
		@Override
		public double pidGet() {
			return -Robot.chassisSubsystem.getAngleDifference(pidAngleSetpoint) / 180.0;
		}
	};

	private static PIDOutput gyroPIDOutput = new PIDOutput() {
		@Override
		public void pidWrite(double output) {
			pidOutputTurn = output;
		}
	};

	public static R_PIDController autoGyroPID = new R_PIDController(30.0, 3.0, 0.0, 1.0, gyroPIDInput, gyroPIDOutput);

	public AutoGoStraightCommand(double speed, double angle) {
		requires(Robot.chassisSubsystem);
		this.angleSetpoint = angle;
		this.speedSetpoint = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		autoGyroPID.reset();
		pidAngleSetpoint = angleSetpoint;
		autoGyroPID.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double speed = speedSetpoint;
		double leftSpeed;
		double rightSpeed;

		autoGyroPID.calculate();

		SmartDashboard.putNumber("Angle setpoint", angleSetpoint);
		SmartDashboard.putNumber("Angle difference", -Robot.chassisSubsystem.getAngleDifference(angleSetpoint));
		SmartDashboard.putNumber("GyroPIDOutput", pidOutputTurn);

		double turn = pidOutputTurn;

		if (speed < 0) {
			turn = -turn;
		}

		if (Math.abs(speed) < 0.03) {
			leftSpeed = turn * 0.25;
			rightSpeed = -turn * 0.25;
		} else {
			leftSpeed  = (turn < 0) ? speed * (1 + turn) : speed;
			rightSpeed = (turn < 0) ? speed              : speed * (1 - turn);
		}
		
		Robot.chassisSubsystem.setSpeed(leftSpeed, rightSpeed);

		SmartDashboard.putData("Gyro PID", autoGyroPID);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	public void end() {
	    Robot.chassisSubsystem.setSpeed(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
