package robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

//TODO Add automatic mode detection

public class F310_Joystick{
    public Joystick joystick;
    public enum Mode{DirectInput, XInput}
    public enum Axis{X, Y, Z}
    public enum ButtonMap
    {
	A(1, 2), 
	B(2, 3),
	X(3, 1),
	Y(4, 4), 
	LB(5, 5),
	RB(6, 6),
	LT(7, 7),
	RT(8, 8),
	BACK(9, 9),
	START(10, 10),
	LEFTSTICK_BUTTON(11, 11),
	RIGHTSTICK_BUTTON(12, 12);
	int dButtonNumber;
	int xButtonNumber;
	//The constructor should never be called from outside this class.
	private ButtonMap(int dButtonNumber, int xButtonNumber) {
	    this.dButtonNumber = dButtonNumber;
	    this.xButtonNumber = xButtonNumber;
	}
    }
    public enum AxisMap{
	LS_XAXIS (0,0),
	LS_YAXIS (1,1),
	LS_ZAXIS (-1,2),
	RS_XAXIS (2,4),
	RS_YAXIS (3,5),
	RS_ZAXIS (-1,3);
	int dNumber;
	int xNumber;
	private AxisMap(int dNumber, int xNumber) {
	    this.dNumber = dNumber;
	    this.xNumber = xNumber;
	}
    }
    public F310_Joystick(int port) {
	joystick = new Joystick(port);
    }
    //The whole point is to get the integer value of the given button.
    //Example: for "A" return 2 if in XInput mode, else return 1.
    public int getButton(ButtonMap button, Mode mode) {
	for (ButtonMap b : ButtonMap.values())
	    if (mode == Mode.XInput) {
		return b.xButtonNumber;
	    }
	    else {
		return b.dButtonNumber;
	    }
	return -1;
    }
    public int getAxis(AxisMap axis, Mode mode){
	for (AxisMap a : AxisMap.values()){
	    if (mode == Mode.XInput){
		return a.xNumber;
	    }
	    else{
		return a.dNumber;
	    }
	}
	return -1;
    }
    //Just a random JoystickButton test
    JoystickButton button = new JoystickButton(joystick, getButton(ButtonMap.A, Mode.XInput));
}
