package robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.commands.auto.DriveToDistance;
import robot.commands.auto.DriveToProximity;
import robot.commands.auto.DriveToUltraDistance;
import robot.commands.auto.TestAutoCommandGroup;

public class AutoChooser {

	SendableChooser autoModeChooser = new SendableChooser();
	SendableChooser slotChooser = new SendableChooser();
	SendableChooser defenceChooser = new SendableChooser();
	SendableChooser distanceChooser = new SendableChooser();
	SendableChooser goalChooser = new SendableChooser();

	public AutoChooser() {
		autoModeChooser.addDefault("Drive To Distance Positive", new DriveToDistance(0.5, 0.0, 180.0));
		autoModeChooser.addObject("Drive To Distance Negative", new DriveToDistance(0.5, 0.0, -180.0));
		autoModeChooser.addObject("Drive To Ultrasound", new DriveToUltraDistance(0.5, 0.0, 90));
		autoModeChooser.addObject("Drive To Proximity", new DriveToProximity(0.5, 0.0));
		autoModeChooser.addObject("Test Auto", new TestAutoCommandGroup());

		slotChooser.addObject("1", new Integer(1));
		slotChooser.addObject("2", new Integer(2));
		slotChooser.addObject("3", new Integer(3));
		slotChooser.addObject("4", new Integer(4));
		slotChooser.addObject("5", new Integer(5));

		defenceChooser.addObject("Low Bar", new String("Low Bar"));
		defenceChooser.addObject("Ramparts", new String("Ramparts"));
		defenceChooser.addObject("Moat", new String("Moat"));
		defenceChooser.addObject("Rock Wall", new String("Rock Wall"));
		defenceChooser.addObject("Rough Terrain", new String("Rough Terrain"));
		defenceChooser.addObject("Portcullis", new String("Portcullis"));
		defenceChooser.addObject("Cheval de Frise", new String("Cheval de Frise"));

		distanceChooser.addObject("Close", new String("Close"));
		distanceChooser.addObject("Far", new String("Far"));

		goalChooser.addObject("Left", new String("Left"));
		goalChooser.addObject("Center", new String("Center"));
		goalChooser.addObject("Right", new String("Right"));

		SmartDashboard.putData("Auto mode", autoModeChooser);
		SmartDashboard.putData("Slot position", slotChooser);
		SmartDashboard.putData("Defences", defenceChooser);
		SmartDashboard.putData("Distance", distanceChooser);
		SmartDashboard.putData("Goal", goalChooser);
	}

	/**
	 * 
	 * @return The selected command, as a ready-to-use command
	 */
	public Command getSelectedCommand() {
		return (Command) autoModeChooser.getSelected();
	}

	/**
	 * 
	 * @return The selected slot, as an integer
	 */
	public int getSelectedSlot() {
		return (int) slotChooser.getSelected();
	}

	/**
	 * 
	 * @return The selected defense, as a string
	 */
	public String getSelectedDefence() {
		return (String) defenceChooser.getSelected();
	}

	/**
	 * 
	 * @return The selected distance, as a string
	 */
	public String getSelectedDistance() {
		return (String) distanceChooser.getSelected();
	}

	/**
	 * 
	 * @return The selected goal, as a String
	 */
	public String getSelectedGoal() {
		return (String) goalChooser.getSelected();
	}
}