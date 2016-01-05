package robot;

import robot.F310_Joystick.Axis;
import robot.F310_Joystick.Stick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	F310_Joystick driverStick = new F310_Joystick(0);
    //Xbox_Joystick operatorStick = new Xbox_Joystick(0);
    
    public double getSpeed() {
    	double joystickValue = driverStick.getAxis(Stick.LEFT, Axis.Y);
    	return Math.round(joystickValue * Math.abs(joystickValue) * 1000) / 1000.0;
    }
    
    public double getTurn() {
    	double joystickValue = driverStick.getAxis(Stick.RIGHT, Axis.X);
    	return Math.round(joystickValue * Math.abs(joystickValue) * 1000) / 1000.0;
    }

	public int getPOV() {
		return driverStick.getPOV();
	}
}

