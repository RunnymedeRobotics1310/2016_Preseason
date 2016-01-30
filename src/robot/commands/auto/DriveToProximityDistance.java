package robot.commands.auto;

import edu.wpi.first.wpilibj.DigitalInput;
import robot.Robot;
import robot.commands.GoStraightCommand;

/**
 *
 */
public class DriveToProximityDistance extends GoStraightCommand {
	DigitalInput limitSwitch1;
	DigitalInput limitSwitch2;
	
    public DriveToProximityDistance(int limit1Port, int limit2Port) {
        super(Robot.chassisSubsystem.getCurrentAngle());
        this.limitSwitch1 = new DigitalInput(limit1Port);
        this.limitSwitch2 = new DigitalInput(limit2Port);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return !this.limitSwitch1.get() || !this.limitSwitch2.get();
    }
}
