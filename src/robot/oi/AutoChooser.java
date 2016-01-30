package robot.oi;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import robot.commands.auto.DriveToDistance;
import robot.commands.auto.DriveToLimit;
import robot.commands.auto.DriveToUltraDistance;

public class AutoChooser extends SendableChooser {

	public AutoChooser() {
        addDefault("Default Auto", new DriveToDistance(0.5, 0.0, 0.5));
        addObject("Drive To Distance", new DriveToDistance(0.5, 0.0, 0.5));
        addObject("Drive To Ultrasound", new DriveToUltraDistance(0.5, 0.0, 50));
        addObject("Drive To Proximity", new DriveToLimit(0.5, 0.0));
	}
}
