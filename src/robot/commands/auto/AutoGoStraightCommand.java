
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
public class AutoGoStraightCommand extends Command {

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

	private static R_PIDController gyroPID = new R_PIDController(30.0, 3.0, 0.0, 1.0, gyroPIDInput, gyroPIDOutput);

	public AutoGoStraightCommand(double speed, double angle) {
		requires(Robot.chassisSubsystem);
		this.angleSetpoint = angle;
		this.speedSetpoint = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("AutoGoStraightCommand init();");
		gyroPID.reset();
		pidAngleSetpoint = angleSetpoint;
		gyroPID.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("AutoGoStraightCommand execute();");
		
		double speed = speedSetpoint;
		double leftSpeed;
		double rightSpeed;

		gyroPID.calculate();

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
			leftSpeed = (turn < 0) ? speed * (1 + turn) : speed;
			rightSpeed = (turn > 0) ? speed * (1 - turn) : speed;
		}

		Robot.chassisSubsystem.setSpeed(leftSpeed, rightSpeed);

		SmartDashboard.putData("Gyro PID", gyroPID);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		System.out.println("AutoGoStraightCommand doesn't finish!");
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
