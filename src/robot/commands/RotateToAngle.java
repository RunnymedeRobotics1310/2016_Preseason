package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 *
 */
public class RotateToAngle extends Command {

	private final int target;
	
    public RotateToAngle(int angle) {
    	this.target = angle;
    	requires(Robot.chassisSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// Which way am I going to turn?
    	// Turn on the motors.
    	double seconds = 3.0;
    	this.setTimeout(seconds);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// If at the target then finish
    	//or
    	this.isTimedOut();
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	// Stop the motors.
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
