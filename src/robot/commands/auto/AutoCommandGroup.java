package robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.Field.Defense;
import robot.Field.Goal;
import robot.Field.Lane;
import robot.Field.Slot;
import robot.commands.RotateToAngle;

public class AutoCommandGroup extends CommandGroup {

	public AutoCommandGroup(Slot slot, Defense defense, Lane lane, Goal goal) {
		double speed = 0.5;
		double waitTime = 4.0;

		switch (defense) {
		case LOW_BAR:
			addSequential(new DriveToDistance(speed, 0, 192));
			break;
		case MOAT:
			addSequential(new DriveToDistance(speed, 0, 192));
			break;
		case RAMPARTS:
			addSequential(new DriveToDistance(speed, 0, 192));
			break;
		case ROCK_WALL:
			addSequential(new DriveToDistance(speed, 0, 192));
			break;
		case ROUGH_TERRAIN:
			addSequential(new DriveToDistance(speed, 0, 192));
			break;
		case PORTCULLIS:
			break;
		case CHEVAL_DE_FRISE:
			break;
		default:
			addSequential(new DriveToDistance(speed, 0, 192));
			break;

		}

		// If the far lane is selected, go for another 50 inches.
		if (lane == Lane.FAR) {
			addSequential(new DriveToDistance(speed, 0, 50));
		}

		// Rotate to 90 degrees, because that's what we always do.
		addSequential(new RotateToAngle(90, waitTime));

		addSequential(new WaitUntilPathClear(waitTime, slot));

		switch (goal) {
		case LEFT:
			addSequential(new DriveToUltraDistance(speed, 90, Slot.TWO.getDistanceToLeftWall()));
			break;
		case CENTER:
			addSequential(new DriveToUltraDistance(speed, 90,
					(Slot.THREE.getDistanceToLeftWall() + Slot.FOUR.getDistanceToLeftWall()) / 2));
			break;
		case RIGHT:
			addSequential(new DriveToUltraDistance(speed, 90, Slot.FIVE.getDistanceToLeftWall()));
			break;
		default:
			addSequential(new DriveToUltraDistance(speed, 90, Slot.TWO.getDistanceToLeftWall()));
			break;

		}

		// Rotate to 0 degrees, because that's what we always do.
		addSequential(new RotateToAngle(0, waitTime));

		addSequential(new DriveToProximity(speed, 0));

		if (goal != Goal.CENTER) {
			addSequential(new DriveToDistance(speed, 0, -20));
		}

		int rampAngle = 50;

		switch (goal) {
		case LEFT:
			addSequential(new RotateToAngle(rampAngle, waitTime));
			break;
		case CENTER:
			// do nothing.
			break;
		case RIGHT:
			addSequential(new RotateToAngle(360 - rampAngle, waitTime));
			break;
		default:
			addSequential(new DriveToUltraDistance(speed, 90, Slot.TWO.getDistanceToLeftWall()));
			break;

		}

		// TODO: Add more stuff.

	}
}
