package robot.commands.auto;

import robot.Robot;
import robot.commands.GoStraightCommand;

/**
 *
 */
public class DriveToLimit extends GoStraightCommand {	
    public DriveToLimit() {
        super(Robot.chassisSubsystem.getCurrentAngle());
    }

    @Override
    protected boolean isFinished() {
        return Robot.chassisSubsystem.getFrontLimit();
    }
}
