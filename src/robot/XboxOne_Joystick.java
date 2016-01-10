package robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;

public class XboxOne_Joystick {
	
	public enum Axis {
		X, 
		Y;
	}

	public enum AxisMap {
		LEFT_AXIS_X(0), 
		LEFT_AXIS_Y(1), 
		RIGHT_AXIS_X(4), 
		RIGHT_AXIS_Y(5); 

		int axisNumber;

		AxisMap(int axisNumber) {
			this.axisNumber = axisNumber;
		}
		public int getAxis () {
			return axisNumber;
		}
	}

	public enum ButtonMap {
		LEFT_STICK(8), 
		RIGHT_STICK(9), 
		LEFT_BUMPER(4), 
		RIGHT_BUMPER(5),
		VIEW(6),
		MENU(7),
		A(0), 
		B(1), 
		X(2), 
		Y(3);

		int buttonNumber;

		ButtonMap(int buttonNumber) {
			this.buttonNumber = buttonNumber;
		}
		public int getButton() {
			return buttonNumber;
		}
	}

	public enum Stick {
		LEFT,
		RIGHT;
	}

	public enum TriggerMap{
		LEFT_TRIGGER(2),
		RIGHT_TRIGGER(3);

		int trigger;

		TriggerMap(int trigger) {
			this.trigger = trigger;
		}
		public int getTrigger() {
			return trigger;
		}
	}

	private Joystick joystick;

	public XboxOne_Joystick(int port) {
		joystick = new Joystick(port);
	}

	public XboxOne_Joystick(Joystick j) {
		joystick = j;
	}

	// checks if the specified button has been pressed, returns true or false
	//Sample call: joystick.getRawAxis(Side.LEFT, Axis.X);
	public double getAxis(Stick stick, Axis axis) {
		switch (stick) {
		case LEFT:
			switch (axis) {
			case X:
				return joystick.getRawAxis(AxisMap.LEFT_AXIS_X.getAxis());
			case Y:
				return joystick.getRawAxis(AxisMap.LEFT_AXIS_Y.getAxis());
			}
		case RIGHT:
			switch (axis) {
			case X:
				return joystick.getRawAxis(AxisMap.RIGHT_AXIS_X.getAxis());
			case Y:
				return joystick.getRawAxis(AxisMap.RIGHT_AXIS_Y.getAxis());
			}
		}
		return 0.0;
	}

	// checks if the specified button has been pressed, returns true or false
	public boolean getButton(ButtonMap button) {
		return joystick.getRawButton(button.getButton());
	}

	public int getPOVAngle(){
		return joystick.getPOV(0);
	}

	public double getTrigger(TriggerMap trigger){
		switch (trigger){
		case LEFT_TRIGGER:
			return joystick.getRawAxis(TriggerMap.LEFT_TRIGGER.getTrigger());
		case RIGHT_TRIGGER:
			return joystick.getRawAxis(TriggerMap.RIGHT_TRIGGER.getTrigger());
		}
		return 0.0;	
	}

	public void setRumble(RumbleType type, float value){
		joystick.setRumble(type, value);
	}
}
