
package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 *
 */
public class ServoCommand extends Command {

	long startTime = 0;
	boolean direction = false;
	
    public ServoCommand() {
        requires(Robot.servoSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = System.nanoTime();
    	Robot.servoSubsystem.moveToPosition(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	long endTime = System.nanoTime();
    	boolean expired = false;
    	if (endTime - startTime > 3000000000L) {
    		expired = true;
    	}
    	
    	if (!expired) { return; }
    	
    	direction = !direction;
    	
    	if (direction == true) {
    		Robot.servoSubsystem.moveToPosition(1);
    		
    	} else {
    		Robot.servoSubsystem.moveToPosition(0);
    	}
    	startTime = endTime;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
