package robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.commands.RotateToAngle;

/**
 *
 */
public class TestAutoCommandGroup extends CommandGroup {
    
    public  TestAutoCommandGroup() {
    	addSequential(new DriveToDistance(.5, 0.0, 192));
    	addSequential(new RotateToAngle(90.0, 2.0));
    	addSequential(new DriveToUltraDistance(0.5, 90.0, 154.0));
    	addSequential(new RotateToAngle(0.0,4.0));
    	addSequential(new DriveToLimit(0.5, 0.0));
    }
}
