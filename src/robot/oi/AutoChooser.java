package robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.commands.auto.DriveToDistance;
import robot.commands.auto.DriveToLimit;
import robot.commands.auto.DriveToUltraDistance;
import robot.commands.auto.SuperFastAuto;
import robot.commands.auto.TestAutoCommandGroup;

public class AutoChooser extends SendableChooser {

	public AutoChooser() {
		
        addDefault("Default Auto", new DriveToDistance(0.5, 0.0, 0.5));
        addObject("Drive To Distance", new DriveToDistance(0.5, 0.0, 180.0));
        addObject("Drive To Ultrasound", new DriveToUltraDistance(0.5, 0.0, 50));
        addObject("Drive To Proximity", new DriveToLimit(0.5, 0.0));
        addObject("Test Auto", new TestAutoCommandGroup());
        
        SmartDashboard.putData("Auto mode", this);
	}
	
	public Command getSelectedCommand() {
		return (Command) super.getSelected();
	}
}