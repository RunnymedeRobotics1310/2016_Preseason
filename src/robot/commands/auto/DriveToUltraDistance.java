package robot.commands.auto;

import robot.Robot;

public class DriveToUltraDistance extends AutoGoStraightCommand {

	/**
	 * The distance to drive to.
	 */
	private double distanceSetpoint;

	private double speedSetpoint;

	/**
	 * The constructor for a new DriveToDistance command.
	 * 
	 * @param speed
	 *            The speed at which to drive.
	 * @param angle
	 *            The angle to drive at (in degrees).
	 * @param distance
	 *            The distance to drive to.
	 */
	public DriveToUltraDistance(double speed, double angle, double distance) {
		super(angle);
		this.speedSetpoint = speed;
		this.distanceSetpoint = distance;
	}

	protected void initialize() {
		super.initialize();
		Robot.chassisSubsystem.resetUltrasonic();
		if (distanceSetpoint - Robot.chassisSubsystem.getUltraSonicDistance() < 0) {
			setSpeed(-speedSetpoint, Direction.BACKWARDS);
		} else {
			setSpeed(speedSetpoint, Direction.FORWARD);
		}
		System.out.println("drive to ultra started");
	}

	/**
	 * Gets the distance set point.
	 * 
	 * @return the distance set point.
	 */
	public double getDistanceSetpoint() {
		return distanceSetpoint;
	}

	// Called once after isFinished returns true
	protected boolean isFinished() {
		// Stop 4in early because it takes the robot 4 inches to stop.
		System.out.println("Drive ultra finished");
		return (Math.abs(distanceSetpoint - Robot.chassisSubsystem.getUltraSonicDistance()) <= 4);

	}
}