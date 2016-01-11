package robot;

import edu.wpi.first.wpilibj.Joystick;

public class R_Xbox360_GameController extends R_GameController {

	private final Joystick joystick;

	public R_Xbox360_GameController(int port) {
		joystick = new Joystick(port);
	}

	public R_Xbox360_GameController(Joystick j) {
		joystick = j;
	}

	@Override
	public double getAxis(Stick stick, Axis axis) {

		double axisValue = 0.0;

		switch (stick) {
		case LEFT:
			switch (axis) {
			case X:  axisValue = joystick.getRawAxis(0);  break;
			case Y:  axisValue = joystick.getRawAxis(1);  break;
			}
			break;

		case RIGHT:
			switch (axis) {
			case X:  axisValue = joystick.getRawAxis(4);  break;
			case Y:  axisValue = joystick.getRawAxis(5);  break;
			}
			break;
		}

		// Round the axis value to 2 decimal places
		return Math.round(axisValue*100) / 100.0;
	}

	@Override
	public boolean getButton(Button button) {

		switch (button) {
		case A:             return joystick.getRawButton(1);
		case B:				return joystick.getRawButton(2);
		case X:				return joystick.getRawButton(3);
		case Y:				return joystick.getRawButton(4);
		case LEFT_BUMPER:	return joystick.getRawButton(5);
		case RIGHT_BUMPER:	return joystick.getRawButton(6);
		case BACK:			return joystick.getRawButton(7);
		case START:			return joystick.getRawButton(8);
		case LEFT_STICK:	return joystick.getRawButton(9);
		case RIGHT_STICK:	return joystick.getRawButton(10);
		default:            return false;
		}

	}

	@Override
	public int getPOVAngle() { return joystick.getPOV(); }

	@Override
	public Joystick getRawJoystick() { return joystick; }

	@Override
	public double getTrigger(Trigger trigger) {

		double triggerValue = 0.0;

		switch (trigger) {
		case LEFT:  triggerValue = joystick.getRawAxis(11); break;
		case RIGHT: triggerValue = joystick.getRawAxis(12); break;
		}

		// Round the trigger value to 2 decimal places
		return Math.round(triggerValue*100.0) / 100.0;

	}
	
}