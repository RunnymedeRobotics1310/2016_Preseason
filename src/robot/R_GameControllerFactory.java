package robot;

import edu.wpi.first.wpilibj.Joystick;

public class R_GameControllerFactory {
	public static R_GameController getGameController(int port) {
		Joystick newStick = new Joystick(port);
		
		switch (newStick.getName()){
			case "Xbox":
				return new R_Xbox360_GameController(newStick);
			case "F310":
				return new R_F310_GameController(newStick);
			default:
				return null;
				//TODO: figure out how to throw an error to the driverstation console.
		}
	}
}
