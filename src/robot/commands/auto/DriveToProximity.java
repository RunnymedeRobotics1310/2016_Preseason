package robot.commands.auto;

import robot.Robot;

public class DriveToProximity extends AutoGoStraightCommand {

    private double speedSetpoint;

    public DriveToProximity(double speed, double angle) {
	super(angle);
	this.speedSetpoint = speed;
    }

    protected void initialize() {
	super.initialize();
	setSpeed(speedSetpoint, Direction.FORWARD);
    }

    @Override
    protected boolean isFinished() {
	return Robot.chassisSubsystem.getFrontLimit();
    }
}
