package robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.Field.Defence;
import robot.Field.Slot;
import robot.R_GameController;
import robot.R_GameController.Axis;
import robot.R_GameController.Button;
import robot.R_GameController.Stick;
import robot.R_GameControllerFactory;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private R_GameController driverStick = R_GameControllerFactory.getGameController(0);
	private AutoChooser autoChooser = new AutoChooser();
	
	public double getSpeed() {
		double joystickValue = driverStick.getAxis(Stick.LEFT, Axis.Y);
		return -Math.round(joystickValue * Math.abs(joystickValue) * 100) / 100.0;
	}

	public double getTurn() {
		double joystickValue = driverStick.getAxis(Stick.RIGHT, Axis.X);
		return Math.round(joystickValue * Math.abs(joystickValue) * 100) / 100.0;
	}

	public int getPOVAngle() {
		return driverStick.getPOVAngle();
	}
	
	public boolean getGyroReset() {
		return driverStick.getButton(Button.BACK);
	}
	
	public Defence getDefense() {
		return Defence.toEnum(autoChooser.getSelectedDefence());
	}
	
	public Slot getSlot() {
		return Slot.toEnum(autoChooser.getSelectedSlot());
	}
	
	public Distance getDistance() {
		return Distance.toEnum(autoChooser.getSelectedDistance());
	}
	
	public Goal getGoal() {
		return Goal.toEnum(autoChooser.getSelectedGoal());
	}
	
	public Command getAutoCommand() {
		return (Command) autoChooser.oldChooser.getSelected();
	}

	/**
	 * Update the periodic running elements of the dashboard
	 * <p>
	 * i.e. all toggle buttons
	 */
	public void periodic() {}
	
	/**
	 * Put any items on the dashboard
	 */
	public void updateDashboard() {
		SmartDashboard.putString("Driver Controller", driverStick.toString());
	}
}
