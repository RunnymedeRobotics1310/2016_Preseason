
package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import robot.Robot;

/**
 *
 */
public class JoystickCommand extends Command {

    public JoystickCommand() {
        requires(Robot.chassisSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double speed = Robot.oi.getSpeed();
    	double turn = Robot.oi.getTurn();
    	double leftSpeed;
    	double rightSpeed;
    	
    	/*
    	// If the user is not turning, then follow the encoders using the GoStraight command.
    	if (Math.abs(turn) < 0.03) {
    		Scheduler.getInstance().add(new GoStraightCommand(Robot.chassisSubsystem.getCurrentAngle()));
    		return;
    	}
    	*/
    	
    	//NOTE: at the moment, the gostraightcommand isn't used!
    	
    	if (Math.abs(speed) < 0.03) {
    		leftSpeed = turn;
    		rightSpeed = -turn;
    	} else {
    		leftSpeed = (turn < 0) ? speed * (1 + turn) : speed;
    		rightSpeed = (turn > 0) ? speed * (1 - turn) : speed;
    	}
    	
    	Robot.chassisSubsystem.setSpeed(leftSpeed, rightSpeed);
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
