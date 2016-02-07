package robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.commands.auto.DriveToDistance;
import robot.commands.auto.DriveToLimit;
import robot.commands.auto.DriveToUltraDistance;
import robot.commands.auto.TestAutoCommandGroup;

public class AutoChooser {

	SendableChooser oldChooser = new SendableChooser();
	SendableChooser slotChooser = new SendableChooser();
	
	public AutoChooser() {
		
        oldChooser.addDefault("Default Auto", new DriveToDistance(0.5, 0.0, 0.5));
        oldChooser.addObject("Drive To Distance Positive", new DriveToDistance(0.5, 0.0, 180.0));
        oldChooser.addObject("Drive To Distance Negative", new DriveToDistance(-0.5, 0.0, 180.0));
        oldChooser.addObject("Drive To Ultrasound", new DriveToUltraDistance(0.5, 0.0, 50));
        oldChooser.addObject("Drive To Proximity", new DriveToLimit(0.5, 0.0));
        oldChooser.addObject("Test Auto", new TestAutoCommandGroup());
        
        slotChooser.addObject("1",new Integer(1));
        slotChooser.addObject("2",new Integer(2));
        slotChooser.addObject("3",new Integer(3));
        slotChooser.addObject("4",new Integer(4));
        slotChooser.addObject("5",new Integer(5));
        
        
        
        SmartDashboard.putData("Auto mode", oldChooser);
        SmartDashboard.putData("Slot position", slotChooser);
        
	}
	
	public Command getSelectedCommand() {
		return (Command) oldChooser.getSelected();
	}
}