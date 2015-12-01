package robot;

import edu.wpi.first.wpilibj.Joystick;

public class Xbox_Joystick{
	private Joystick DRIVE_STICK;

	Xbox_Joystick(int port) {
		DRIVE_STICK = new Joystick(port);
	}

	private enum Axis {
		X, 
		Y;
	}
	
	private enum Stick {
		LEFT,
		RIGHT;
	}

	private enum buttonMap {
		LEFT_STICK(9), 
		RIGHT_STICK(10), 
		LEFT_BUMPER(5), 
		RIGHT_BUMPER(6), 
		BACK(7),
		START(8),
		A(1), 
		B(2), 
		X(3), 
		Y(4);

		final int buttonNumber;

		buttonMap(int button) {
			this.buttonNumber = button;
		}
	}

	private enum triggerMap {
		LEFT(11),
		RIGHT(12);
		
		final int triggerNumber;

		triggerMap(int buttonNumber) {
			this.triggerNumber = buttonNumber;
		}
	}
	
	private enum axisMap {
		LEFT_X(1), 
		LEFT_Y(2), 
		RIGHT_X(4), 
		RIGHT_Y(5);

		final int axisNumber;

		axisMap(int axis) {
			this.axisNumber = axis;
		}
	}
	
	/**
    * Get the value of a button.
    *
    * @param button The button to be read. [buttonMap.LEFT_STICK, buttonMap.RIGHT, etc]
    * 
    * @return boolean The value of the button.
    */
	public boolean getButton(buttonMap button) {
		return DRIVE_STICK.getRawButton(button.buttonNumber);
	}
	
	/**
     * Get the value of a trigger.
     *
     * @param trigger The trigger to be read. [triggerMap.LEFT, triggerMap.RIGHT]
     * 
     * @return double The value of the trigger.
     */
	public double getTrigger(triggerMap trigger) {
		return DRIVE_STICK.getRawAxis(trigger.triggerNumber);
	}

	/**
     * Get the value of the axis.
     *
     * @param stick The side of the joystick to read. [Stick.LEFT, Stick.RIGHT]
     * @param axis The axis of the stick to be returned. [Axis.X, Axis.Y]
     * 
     * @return double The value of the axis.
     */
	public double getAxis(Stick stick, Axis axis) {
			switch (stick) {
				case LEFT:
					switch (axis) {
						case X:
							return DRIVE_STICK.getRawAxis(axisMap.LEFT_X.axisNumber);
						case Y:
							return DRIVE_STICK.getRawAxis(axisMap.LEFT_Y.axisNumber);
					}
				case RIGHT:
					switch (axis) {
						case X:
							return DRIVE_STICK.getRawAxis(axisMap.RIGHT_X.axisNumber);
						case Y:
							return DRIVE_STICK.getRawAxis(axisMap.RIGHT_Y.axisNumber);
					}
			}
			
			return 0.0;
		
	}
}
