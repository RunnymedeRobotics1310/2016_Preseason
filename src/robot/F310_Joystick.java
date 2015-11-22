package robot;

import edu.wpi.first.wpilibj.Joystick;

public class F310_Joystick {

	public enum JoystickMode {
		X, 
		D
	};

	public enum Axis {
		X, 
		Y, 
		Z;
	}
	
	public enum Side {
		LEFT,
		RIGHT;
	}

	// does not contain all available buttons on the F310
	public enum buttonMap {
		LEFT_STICK(12, 10), 
		RIGHT_STICK(11, 9), 
		LEFT_BUMPER(5, 5), 
		RIGHT_BUMPER(6, 6), 
		LEFT_TRIGGER(7,-1), 
		RIGHT_TRIGGER(8, -2), 
		A(2, 1), 
		B(3, 2), 
		X(1, 3), 
		Y(4, 4);

		int xModeNumber, dModeNumber;

		buttonMap(int xModeNumber, int dModeNumber) {
			this.xModeNumber = xModeNumber;
			this.dModeNumber = dModeNumber;
		}
		
		public int getPortNumber(JoystickMode mode) {
			if (mode == JoystickMode.X) {
				return xModeNumber;
			}
			else {
				return dModeNumber;
			}
		}
	}

	public enum axisMap {
		LEFT_AXIS_X(0, 0), 
		LEFT_AXIS_Y(1, 1), 
		LEFT_AXIS_Z(2, -1),
		RIGHT_AXIS_X(4, 2), 
		RIGHT_AXIS_Y(5, 3), 
		RIGHT_AXIS_Z(3, -1);

		int xModeNumber, dModeNumber;

		axisMap(int xModeNumber, int dModeNumber) {
			this.xModeNumber = xModeNumber;
			this.dModeNumber = dModeNumber;
		}
		
		public int getPortNumber (JoystickMode mode) {
			if (mode == JoystickMode.X) {
				return xModeNumber;
			}
			else {
				return dModeNumber;
			}
		}
	}

	// gets the buttonMap representation of the specified number based on the
	// current joystick mode
	public buttonMap getButton(int feedNumber, JoystickMode mode) {
		for (buttonMap button : buttonMap.values())
			if (button.getPortNumber(getMode()) == feedNumber) {
				return button;
			}
		return null;
	}

	public Joystick DRIVE_STICK;

	public F310_Joystick(int port) {
		DRIVE_STICK = new Joystick(port);
	}
	
	public JoystickMode getMode() {
		//THIS MAY BE BACKWARDS, TOOK A GUESS
		if (DRIVE_STICK.getButtonCount()>10) {
			return JoystickMode.X;
		}
		else {
			return JoystickMode.D;
		}
	}

	// checks if the specified button has been pressed, returns true or false
	public boolean getRawButton(buttonMap button) {
			return DRIVE_STICK.getRawButton(button.getPortNumber(getMode()));
	}

	// checks if the specified button has been pressed, returns true or false
	//Sample call: DRIVE_STICK.getRawAxis(Side.LEFT, Axis.X);
	public double getRawAxis(Side side, Axis axis) {
			switch (side) {
			case LEFT:
				switch (axis) {
				case X:
					return DRIVE_STICK.getRawAxis(axisMap.LEFT_AXIS_X.getPortNumber(getMode()));
				case Y:
					return DRIVE_STICK.getRawAxis(axisMap.LEFT_AXIS_Y.getPortNumber(getMode()));
				case Z:
					return DRIVE_STICK.getRawAxis(axisMap.LEFT_AXIS_Z.getPortNumber(getMode()));
				}
			case RIGHT:
				switch (axis) {
				case X:
					return DRIVE_STICK.getRawAxis(axisMap.RIGHT_AXIS_X.getPortNumber(getMode()));
				case Y:
					return DRIVE_STICK.getRawAxis(axisMap.RIGHT_AXIS_Y.getPortNumber(getMode()));
				case Z:
					return DRIVE_STICK.getRawAxis(axisMap.RIGHT_AXIS_Z.getPortNumber(getMode()));
				}
			}
			return 0.0;
		
	}
}
