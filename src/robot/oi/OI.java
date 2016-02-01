package robot.oi;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.R_GameController;
import robot.R_GameController.Axis;
import robot.R_GameController.Stick;
import robot.R_Xbox360_GameController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	// R_GameController driverStick = new R_F310_GameController(0);
	R_GameController driverStick = new R_Xbox360_GameController(0);
	
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
