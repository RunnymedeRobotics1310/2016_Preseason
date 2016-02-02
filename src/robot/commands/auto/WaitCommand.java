package robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 *
 */
public class WaitCommand extends Command {

    public WaitCommand(double waitTime) {
    	this.setTimeout(waitTime);
    	requires(Robot.chassisSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassisSubsystem.setSpeed(0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
