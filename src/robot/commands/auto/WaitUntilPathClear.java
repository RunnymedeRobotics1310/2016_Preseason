package robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import robot.Field.Slot;
import robot.Robot;

public class WaitUntilPathClear extends Command {

	private Slot slot;

	public WaitUntilPathClear(double waitTime, Slot slot) {
		this.setTimeout(waitTime);
		this.slot = slot;
		requires(Robot.chassisSubsystem);

	}

	@Override
	protected void initialize() {
		// Do nothing. At all.
	}

	@Override
	protected void execute() {
		Robot.chassisSubsystem.setSpeed(0, 0);
	}

	@Override
	protected void end() {

	}

	@Override
	protected boolean isFinished() {
		return isTimedOut() || Robot.chassisSubsystem.getUltrasonicDistance() >= slot.getDistanceToLeftWall() + 15;

	}

	@Override
	protected void interrupted() {
	}

}
