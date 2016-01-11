package robot;

import edu.wpi.first.wpilibj.Joystick;

public class R_F310_Joystick extends R_GameController {

	private enum JoystickMode {
		X, 
		D
	}

	private final Joystick joystick;

	public R_F310_Joystick(int port) {
		joystick = new Joystick(port);
	}

	public R_F310_Joystick(Joystick j) {
		joystick = j;
	}

	@Override
	public double getAxis(Stick stick, Axis axis) {

		double axisValue = 0.0;
		
		// The axis map changes based on the joystick mode
		switch (getMode()) {

		case D:
			switch (stick) {
			case LEFT:
				switch (axis) {
				case X:  axisValue = joystick.getRawAxis(0);  break;
				case Y:  axisValue = joystick.getRawAxis(1);  break;
				}
				break;
				
			case RIGHT:
				switch (axis) {
				case X:  axisValue = joystick.getRawAxis(2);  break;
				case Y:  axisValue = joystick.getRawAxis(3);  break;
				}
				break;
			}
			break;

		case X:
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
			break;
		}

		// Round the axis value to 2 decimal places
		return Math.round(axisValue*100) / 100.0;
	}

	@Override
	public boolean getButton(Button button) {

		// The button map changes based on the joystick mode.
		switch(getMode()) {

		// D Mode
		case D:

			switch (button) {
			case A:             return joystick.getRawButton(2);
			case B:				return joystick.getRawButton(3);
			case X:				return joystick.getRawButton(1);
			case Y:				return joystick.getRawButton(4);
			case LEFT_BUMPER:	return joystick.getRawButton(5);
			case RIGHT_BUMPER:	return joystick.getRawButton(6);
			case LEFT_STICK:	return joystick.getRawButton(11);
			case RIGHT_STICK:	return joystick.getRawButton(12);
			case BACK:			return joystick.getRawButton(9);
			case START:			return joystick.getRawButton(10);
			default:            return false;
			}

			// X Mode
		case X:

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


		// Unknown joystick mode.  There is no button pressed.	
		default: return false;
		}
	}

	@Override
	public int getPOVAngle() { return joystick.getPOV(); }

	@Override
	public Joystick getRawJoystick() { return joystick; }

	@Override
	public double getTrigger(Trigger trigger) {

		double triggerValue = 0.0;
		
		// The trigger map changes based on the joystick mode
		switch (getMode()) {
		
		case D:
			switch (trigger) {
			// In D mode, the triggers are buttons - return 0 or 1.
			case LEFT:  return joystick.getRawButton(7) ? 1.0 : 0.0;
			case RIGHT: return joystick.getRawButton(8) ? 1.0 : 0.0;
			}
			break;
			
		case X:
			switch (trigger) {
			case LEFT:  triggerValue = joystick.getRawAxis(2); break;
			case RIGHT: triggerValue = joystick.getRawAxis(3); break;
			}
			break;
		}
		
		// Round the trigger value to 2 decimal places
		return Math.round(triggerValue*100.0) / 100.0;
	}


	private JoystickMode getMode() {

		if (joystick.getButtonCount()>10) {
			return JoystickMode.D;
		}

		return JoystickMode.X;
	}

}