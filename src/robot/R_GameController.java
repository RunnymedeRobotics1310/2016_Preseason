package robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;

/**
 * This interface is the interface for all GameControllers.  A GameController typically has two
 * joysticks, and a POV on the top, and two bumpers and triggers on the front side.
 * 
 * Each type of GameController has different button mappings.  The GameControllerFactory will 
 * return a GameController of the correct type.
 * 
 */
public abstract class R_GameController {

	public enum Axis {
		X, 
		Y;
	};

	public enum Stick {
		LEFT,
		RIGHT;
	}

	public enum Trigger {
		LEFT,
		RIGHT;
	}

	public enum Button {
		A, 
		B, 
		X, 
		Y,
		LEFT_STICK, 
		RIGHT_STICK, 
		LEFT_BUMPER, 
		RIGHT_BUMPER,
		START,
		BACK;
	}
	
	private double curLeftRumble = 0.0;
	private double curRightRumble = 0.0;

	/**
	 * Get the value of the given axis for this stick on the game controller.
	 * 
	 * @param stick - the LEFT or RIGHT stick on the controller
	 * @param axis - the X or Y axis.  By convention, the Y axis returns a negative
	 * value when pushed away from the user (inverted value).  
	 * @return - a double from -1.0 to 1.0 indicating the value of the axis.  A value 
	 * of 0 indicates the joystick is centered.  Sometimes the joystick will not center
	 * at 0.0 and a deadband may need to be introduced.
	 */
	public abstract double getAxis(Stick stick, Axis axis);

	/**
	 * Get the value of the given trigger.
	 * 
	 * @param trigger - the LEFT or RIGHT trigger on the controller.
	 * @return - a double from 0.0 to 1.0 indicating the value of the trigger.  A value 
	 * of 0 indicates the trigger was not pressed.  To return the value of the trigger as a 
	 * button (boolean) use the {@link #getButton(Trigger)} method.
	 */
	public abstract double getTrigger(Trigger trigger);

	/**
	 * Get the value of the given button.
	 * <p>
	 * If the GameController does not have the requested button, the GameController must return
	 * false.
	 * <p>
	 * 
	 * @param trigger - the LEFT or RIGHT trigger on the controller.
	 * @return - boolean - {@code true} if the button was pressed, {@false otherwise}
	 */
	public abstract boolean getButton(Button button);

	/**
	 * Get the value of the given trigger as a button.
	 * 
	 * @param trigger - the LEFT or RIGHT trigger on the controller.
	 * @return - boolean - {@code true} if the trigger is pressed {@code false} otherwise.  The 
	 * trigger value must be greater than .5 to be deemed pressed.  This prevents accidentally detecting
	 * a pressed value when the operator's finger is resting on the trigger.
	 */
	public boolean getButton(Trigger trigger) {

		if (getTrigger(trigger) > 0.5) { return true; }

		return false;
	};

	/**
	 * Get the value of the POV.
	 * 
	 * @return - boolean - {@value -1} if the POV is not pressed.  A value from 0-259 degrees indicating
	 * the clockwise angle of the POV indicated by the user input.  Different POVs can return different
	 * intermediate angle values.
	 */
	public abstract int getPOVAngle();

	/**
	 * Set the Rumble value on the joystick.
	 * <p>
	 * Some joysticks have a rumble feature.  This routines sets the joystick 
	 * rumble on the left and right channels independently
	 *
	 * @param leftRumble - left rumble value between 0 and 1.0
	 * @param rightRumble - right rumble value between 0 and 1.0
	 */
	public void setRumble(double leftRumble, double rightRumble) {
		Joystick joystick = getRawJoystick();
		joystick.setRumble(RumbleType.kLeftRumble,  (float) leftRumble);
		joystick.setRumble(RumbleType.kRightRumble, (float) rightRumble);
		
		curLeftRumble = leftRumble;
		curRightRumble = rightRumble;
	};

	/**
	 * Set the Rumble value on the joystick.
	 * <p>
	 * Some joysticks have a single rumble feature.  Both rumble values setable in the 
	 * driverstation are set by this routine.
	 * 
	 * @param rumble - rumble value between 0 and 1.0
	 */
	public void setRumble(double rumble) {
		setRumble(rumble, rumble);
	};

	public abstract Joystick getRawJoystick();	
	
	@Override
	public String toString() {
		
		String buttonString = "";
		boolean first = true;
		for (Button button : Button.values()) {
			if (getButton(button)) {
				if (!first) {
					buttonString += ",";
				}
				buttonString += button;
			}
		}
		
		String povString = "";
		if (getPOVAngle() >= 0) { 
			povString += getPOVAngle() + " ";
		}
		
		String rumbleString = "";
		if (curLeftRumble > 0.0 || curRightRumble > 0.0) {
			rumbleString += "R(" + curLeftRumble + "," + curRightRumble + ") ";
		}
		
		return  getRawJoystick().getButtonCount()     
				+ " (" + getAxis(Stick.LEFT,  Axis.X) + "," + getAxis(Stick.LEFT,  Axis.Y) + ")"
				+ " (" + getAxis(Stick.RIGHT, Axis.X) + "," + getAxis(Stick.RIGHT, Axis.Y) + ")"
				+ " (" + getTrigger(Trigger.LEFT)     + "," + getTrigger(Trigger.RIGHT) + ") "
				+ povString + buttonString + rumbleString;
	}

}
