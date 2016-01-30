package robot.commands.auto;

import robot.Robot;

public class DriveToUltraDistance extends AutoGoStraightCommand {


	/**
	 * The distance to drive to.
	 */
	private double distanceSetpoint;

	/**
	 * The constructor for a new DriveToDistance command.
	 * 
	 * @param distance
	 *            The distance to drive to.
	 * @param angle
	 *            The angle to drive at (in degrees).
	 * @param speed
	 *            The speed at which to drive.
	 */
	public DriveToUltraDistance(double speed, double angle, double distance) {
		super(speed, angle);
		this.distanceSetpoint = distance;
	}

	/**
	 * Gets the distance set point.
	 * 
	 * @return the distance set point.
	 */
	public double getDistance() {
		return distanceSetpoint;
	}

	// Called once after isFinished returns true
	protected boolean isFinished() {
		return (distanceSetpoint <= Robot.chassisSubsystem.getUltraSonicDistance());
	}

}
