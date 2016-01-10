package robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.R_GameController.Axis;
import robot.R_GameController.Stick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	R_GameController driverStick = new R_F310_Joystick(0);
	//R_GameController driverStick = new R_Xbox360_Joystick(0);
    
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
	
	public void updateDashboard() {
		SmartDashboard.putString("Driver Controller", driverStick.toString());
	}
}

