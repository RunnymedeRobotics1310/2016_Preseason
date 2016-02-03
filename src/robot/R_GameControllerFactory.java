package robot;

import edu.wpi.first.wpilibj.Joystick;

public class R_GameControllerFactory {
	public static R_GameController getGameController(int port) {
		Joystick newStick = new Joystick(port);
		
		switch (newStick.getName()){
			case "Controller (XBOX 360 For Windows)":
				return new R_Xbox360_GameController(newStick);
			case "Logitech Extreme 3D":
				return new R_Extreme3DPro_GameController(newStick);
			case "Logitech F310":
				return new R_F310_GameController(newStick);
			default:
				return new R_Xbox360_GameController(newStick);
		}
	}
}
