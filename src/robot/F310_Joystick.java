package robot;

import edu.wpi.first.wpilibj.Joystick;

public class F310_Joystick {

	public enum JoystickMode {
		X, 
		D
	};

	public enum Axis {
		X, 
		Y;
	}

	public enum Stick {
		LEFT,
		RIGHT;
	}

	// does not contain all available buttons on the F310
	public enum buttonMap {
		LEFT_STICK(8, 11), 
		RIGHT_STICK(9, 10), 
		LEFT_BUMPER(4, 4), 
		RIGHT_BUMPER(5, 5), 
		//RETURNS AXIS: (2,)
		LEFT_TRIGGER(7, 6),
		//RETURNS AXIS: (3,)
		RIGHT_TRIGGER(8, 7),
		START(7, 9),
		BACK(6, 8),

		A(0, 1), 
		B(1, 2), 
		X(2, 0), 
		Y(3, 3);

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
		RIGHT_AXIS_X(4, 2), 
		RIGHT_AXIS_Y(5, 3); 

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

	private Joystick joystick;

	public F310_Joystick(int port) {
		joystick = new Joystick(port);
	}

	public F310_Joystick(Joystick j) {
		joystick = j;
	}

	public JoystickMode getMode() {
		if (joystick.getButtonCount()>10) {
			return JoystickMode.D;
		}
		else {
			return JoystickMode.X;
		}
	}

	// checks if the specified button has been pressed, returns true or false
	public boolean getButton(buttonMap button) {
		return joystick.getRawButton(button.getPortNumber(getMode()));
	}

	// checks if the specified button has been pressed, returns true or false
	//Sample call: DRIVE_STICK.getRawAxis(Side.LEFT, Axis.X);
	public double getAxis(Stick side, Axis axis) {
		switch (side) {
		case LEFT:
			switch (axis) {
			case X:
				return joystick.getRawAxis(axisMap.LEFT_AXIS_X.getPortNumber(getMode()));
			case Y:
				return joystick.getRawAxis(axisMap.LEFT_AXIS_Y.getPortNumber(getMode()));
			}
		case RIGHT:
			switch (axis) {
			case X:
				return joystick.getRawAxis(axisMap.RIGHT_AXIS_X.getPortNumber(getMode()));
			case Y:
				return joystick.getRawAxis(axisMap.RIGHT_AXIS_Y.getPortNumber(getMode()));
			}
		}
		return 0.0;

	}
}
