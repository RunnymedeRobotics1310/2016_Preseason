package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 *
 */
public class RotateToAngle extends Command {

	private double targetAngle;
	private double leftSpeed;
	private double rightSpeed;
	private double initSpeed = 0.5;
	
	public RotateToAngle(double targetAngle, double time) {
		requires(Robot.chassisSubsystem);
		this.targetAngle = targetAngle;
		this.setTimeout(time);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Which way am I going to turn? Need to be able to get angle from
		// chassis
		
		double angleDifference = Robot.chassisSubsystem.getAngleDifference(this.targetAngle);
		
		leftSpeed = (angleDifference > 0) ? initSpeed: -initSpeed;
		rightSpeed = -leftSpeed;
		
		Robot.chassisSubsystem.setSpeed(leftSpeed, rightSpeed);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Reduce the speed as you are getting close to the target.
		
		double angleDifference =  Robot.chassisSubsystem.getAngleDifference(this.targetAngle);
		
		double speed = initSpeed;
		
		if (Math.abs(angleDifference) < 20) {
			speed *= .8;
		} else if (Math.abs(angleDifference) < 10) {
			speed *= .5;
		} else if (Math.abs(angleDifference) < 5) {
			speed *= .3;
		}
		
		leftSpeed = (angleDifference > 0) ? speed: -speed;
		
		rightSpeed = -leftSpeed;
		
		Robot.chassisSubsystem.setSpeed(leftSpeed, rightSpeed);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		double angleDifference =  Robot.chassisSubsystem.getAngleDifference(this.targetAngle);
		
		if (Math.abs(angleDifference) < 3) {
			return true;
		} else {
			return this.isTimedOut();
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		// stop motors
		Robot.chassisSubsystem.setSpeed(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		
	}
}
