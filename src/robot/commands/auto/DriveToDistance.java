package robot.commands.auto;

import robot.Robot;

/**
 * Drives to a specified distance using encoder counts.
 */
public class DriveToDistance extends AutoGoStraightCommand {

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
	public DriveToDistance(double speed, double angle, double distance) {
		super(angle);
		this.speedSetpoint = speed;
		this.distanceSetpoint = distance;
	}

    // Called just before this Command runs the first time
    protected void initialize() {
	Robot.chassisSubsystem.resetEncoders();
	super.initialize();

	if (distanceSetpoint < 0) {
	    setSpeed(speedSetpoint, Direction.BACKWARDS);
	} else {
	    setSpeed(speedSetpoint, Direction.FORWARD);
	}
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
	return (Math.abs(Robot.chassisSubsystem.getEncoderDistance()) >= Math.abs(distanceSetpoint));
    }
}
