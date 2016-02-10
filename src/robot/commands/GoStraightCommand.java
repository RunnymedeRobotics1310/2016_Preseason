package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.Robot;

/**
 *
 */
public class GoStraightCommand extends Command {

	double angleSetpoint;

	public GoStraightCommand(double angle) {
		requires(Robot.chassisSubsystem);
		this.angleSetpoint = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		GoStraightPID.setEnabled(false);
		GoStraightPID.setSetpoint(angleSetpoint);
		GoStraightPID.setEnabled(true);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double speed = Robot.oi.getSpeed();
		double leftSpeed;
		double rightSpeed;

		SmartDashboard.putNumber("Angle setpoint", angleSetpoint);
		SmartDashboard.putNumber("Angle difference", -Robot.chassisSubsystem.getAngleDifference(angleSetpoint));
		SmartDashboard.putNumber("AnglePIDOutput", GoStraightPID.getOutput());

		double turn = GoStraightPID.getOutput();

		// Reverse the direction of the turn when going backwards
		if (speed < 0) { turn = -turn; }

		// If the speed is zero, then just pivot in place
		// The speed of the turn is set to 1/4 of the full value for all pivots.
		if (Math.abs(speed) < 0.03) {
			leftSpeed  =  turn * 0.25;
			rightSpeed = -turn * 0.25;
		} else {
			
			// If the speed is more than zero, then slow down one side of the robot
			leftSpeed  = (turn < 0) ? speed * (1 + turn) : speed;
			rightSpeed = (turn < 0) ? speed              : speed * (1 - turn);
		}

		Robot.chassisSubsystem.setSpeed(leftSpeed, rightSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		double turn = Robot.oi.getTurn();
		if (Math.abs(turn) > 0.03) {
			return true;
		}
		return false;

	}

	// Called once after isFinished returns true
	protected void end() {
		GoStraightPID.setEnabled(false);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
