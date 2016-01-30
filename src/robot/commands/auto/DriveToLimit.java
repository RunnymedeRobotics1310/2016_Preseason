package robot.commands.auto;

import robot.Robot;
import robot.commands.GoStraightCommand;

public class DriveToLimit extends AutoGoStraightCommand {	
    public DriveToLimit(double speed, double angle) {
        super(speed, angle);
    }

    @Override
    protected boolean isFinished() {
        return Robot.chassisSubsystem.getFrontLimit();
    }
}
