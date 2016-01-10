package robot;

import edu.wpi.first.wpilibj.Joystick;

public class Xbox_Joystick{
	public enum Axis {
		X, 
		Y;
	}

	public enum Stick {
		LEFT,
		RIGHT;
	}

	private enum axisMap {
		LEFT_X(0), 
		LEFT_Y(1), 
		RIGHT_X(4), 
		RIGHT_Y(5);

		final int axisNumber;

		axisMap(int axis) {
			this.axisNumber = axis;
		}
	}

	private enum Button {
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

		Button(int button) {
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

	private final Joystick joystick;

	Xbox_Joystick(int port) {
		joystick = new Joystick(port);
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
				return joystick.getRawAxis(axisMap.LEFT_X.axisNumber);
			case Y:
				return joystick.getRawAxis(axisMap.LEFT_Y.axisNumber);
			}
		case RIGHT:
			switch (axis) {
			case X:
				return joystick.getRawAxis(axisMap.RIGHT_X.axisNumber);
			case Y:
				return joystick.getRawAxis(axisMap.RIGHT_Y.axisNumber);
			}
		}

		return 0.0;

	}

	/**
	 * Get the value of a button.
	 *
	 * @param button The button to be read. [buttonMap.LEFT_STICK, buttonMap.RIGHT, etc]
	 * 
	 * @return boolean The value of the button.
	 */
	public boolean getButton(Button button) {
		return joystick.getRawButton(button.buttonNumber);
	}

	/**
	 * Get the value of a trigger.
	 *
	 * @param trigger The trigger to be read. [triggerMap.LEFT, triggerMap.RIGHT]
	 * 
	 * @return double The value of the trigger.
	 */
	public double getTrigger(triggerMap trigger) {
		return joystick.getRawAxis(trigger.triggerNumber);
	}
}
