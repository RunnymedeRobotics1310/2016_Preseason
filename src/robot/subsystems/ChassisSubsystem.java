
package robot.subsystems;

import robot.R_Subsystem;
import robot.commands.JoystickCommand;

/**
 *
 */
public class ChassisSubsystem extends R_Subsystem {
    

    public void initDefaultCommand() {
    	
    	setDefaultCommand(new JoystickCommand());
    }
}

