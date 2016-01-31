package robot.commands.auto;

/**
 * Drives to a specified distance using encoder counts.
 */
public class DriveToDistance extends AutoGoStraightCommand {

	/**
	 * The distance to drive to.
	 */
	private double distanceSetpoint;

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
	public DriveToDistance(double speed, double angle, double distance) {
		super(speed, angle);
		System.out.println("Construct DriveToDistance");
		this.distanceSetpoint = distance;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("DriveToDistance init();");
		super.initialize();
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
		return false;
		/*System.out.println("DriveToDistance isFinished();");
		return (distanceSetpoint <= Robot.chassisSubsystem.getEncoderDistance());
	*/
	}
}
