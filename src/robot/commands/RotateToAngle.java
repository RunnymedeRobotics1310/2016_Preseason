package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import robot.Robot;

/**
 *
 */
public class RotateToAngle extends Command {

	private int target;
	double currentAngle;
	double leftSpeed;
	double rightSpeed;
	double initSpeed = 0.5;
	boolean rotateClockwise;
	int time;
	
	public RotateToAngle(int angle, int time) {
		requires(Robot.chassisSubsystem);
		this.target = angle;
		this.time = time;
		this.setTimeout(time);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Which way am I going to turn? Need to be able to get angle from
		// chassis
		// Turn on motors
		
		this.currentAngle = Robot.chassisSubsystem.getCurrentAngle();

		double relativeTarget = target - this.currentAngle;

		if ((relativeTarget < 0 && relativeTarget > -180) || (relativeTarget < 180)) { 
			// Anticlockwise
			this.leftSpeed = -initSpeed;
			this.rightSpeed = initSpeed;
			this.rotateClockwise = false;
		} else { // clockwise!
			this.leftSpeed = initSpeed;
			this.rightSpeed = -initSpeed;
			this.rotateClockwise = true;
		}

		Robot.chassisSubsystem.setSpeed(leftSpeed, rightSpeed);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		this.currentAngle = Robot.chassisSubsystem.getCurrentAngle();

		if ((Math.abs(this.currentAngle - this.target) < 5) || (Math.abs(this.target - this.currentAngle) < 5)) {
			this.leftSpeed = (!rotateClockwise) ? -initSpeed * .3 : initSpeed * .3;
			this.rightSpeed = (!rotateClockwise) ? -initSpeed * .3 : initSpeed * .3;
		} else if ((Math.abs(this.currentAngle - this.target) < 10)
				|| (Math.abs(this.target - this.currentAngle) < 10)) {
			this.leftSpeed = (!rotateClockwise) ? -initSpeed * .5 : initSpeed * .5;
			this.rightSpeed = (!rotateClockwise) ? -initSpeed * .5 : initSpeed * .5;
		} else if ((Math.abs(this.currentAngle - this.target) < 20)
				|| (Math.abs(this.target - this.currentAngle) < 20)) {
			this.leftSpeed = (!rotateClockwise) ? -initSpeed * .8 : initSpeed * .8;
			this.rightSpeed = (!rotateClockwise) ? -initSpeed * .8 : initSpeed * .8;
		}

		Robot.chassisSubsystem.setSpeed(leftSpeed, rightSpeed);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (this.currentAngle == target) {
			return true;
		} else if (this.currentAngle > target && this.rotateClockwise && this.time - 1 > 0) {
			Scheduler.getInstance().add(new RotateToAngle(this.target, this.time - 1));
			return true;
		} else if (this.currentAngle > target && this.rotateClockwise && this.time -1 > 0) {
			Scheduler.getInstance().add(new RotateToAngle(this.target, this.time - 1));
			return true;
		} else {
			return (this.isTimedOut()) ? true : false;
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
