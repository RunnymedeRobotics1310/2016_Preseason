package robot.commands.auto;

import robot.commands.GoStraightCommand;

/**
 * Drives to a specified distance using encoder counts.
 */
public class DriveToDistance extends GoStraightCommand {

	double distance;
	double speed;

	public DriveToDistance(double distance, double angle, double speed) {
		super(angle);
		this.distance = distance;
		this.speed = speed;
	}

	// Called once after isFinished returns true
	protected void end() {
		
	}

}
