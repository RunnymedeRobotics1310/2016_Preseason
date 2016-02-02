package robot.commands.auto;

import robot.Robot;

public class DriveToLimit extends AutoGoStraightCommand {	
    public DriveToLimit(double speed, double angle) {
        super(speed, angle);
    }

    @Override
    protected boolean isFinished() {
	System.out.println("isFinished driveToLimit : " + Robot.chassisSubsystem.getFrontLimit());
        return Robot.chassisSubsystem.getFrontLimit();
    }
}
