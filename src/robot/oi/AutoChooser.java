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
	SendableChooser defenceChooser = new SendableChooser();
	SendableChooser distanceChooser = new SendableChooser();
	SendableChooser goalChooser = new SendableChooser();
	
	public AutoChooser() {
		
        oldChooser.addDefault("Default Auto", new DriveToDistance(0.5, 0.0, 0.5));
        oldChooser.addObject("Drive To Distance Positive", new DriveToDistance(0.5, 0.0, 180.0));
        oldChooser.addObject("Drive To Distance Negative", new DriveToDistance(0.5, 0.0, -180.0));
        oldChooser.addObject("Drive To Ultrasound", new DriveToUltraDistance(0.5, 0.0, 90));
        oldChooser.addObject("Drive To Limit", new DriveToLimit(0.5, 0.0));
        oldChooser.addObject("Test Auto", new TestAutoCommandGroup());
        
        slotChooser.addObject("1",new Integer(1));
        slotChooser.addObject("2",new Integer(2));
        slotChooser.addObject("3",new Integer(3));
        slotChooser.addObject("4",new Integer(4));
        slotChooser.addObject("5",new Integer(5));
        
        defenceChooser.addObject("Low Bar", new String("Low Bar"));
        defenceChooser.addObject("Ramparts", new String("Ramparts"));
        defenceChooser.addObject("Moat", new String("Moat"));
        defenceChooser.addObject("Rock Wall", new String("Rock Wall"));
        defenceChooser.addObject("Rough Terrain", new String("Rough Terrain"));
        defenceChooser.addObject("Portcullis", new String("Portcullis"));
        defenceChooser.addObject("Cheval de Frise", new String("Cheval de Frise"));
        
        distanceChooser.addObject("Close", new String("Close"));
        distanceChooser.addObject("Far", new String("Far"));
        
        goalChooser.addObject("Left", new String("Left"));
        goalChooser.addObject("Center", new String("Center"));
        goalChooser.addObject("Right", new String("Right"));
        
        
        SmartDashboard.putData("Auto mode", oldChooser);
        SmartDashboard.putData("Slot position", slotChooser);
        SmartDashboard.putData("Defences",defenceChooser);
        SmartDashboard.putData("Distance",distanceChooser);
        SmartDashboard.putData("Goal",goalChooser);
        
	}

	public Command getSelectedCommand() {
	    System.out.println(oldChooser.getSelected());
		return (Command) oldChooser.getSelected();
	}
	
	public Integer getSelectedSlot() {
		
		Integer selectedValue = (Integer) slotChooser.getSelected();
		return selectedValue;
	}
	
	public String getSelectedDefence() {
		String selectedDefence = (String) defenceChooser.getSelected();
		return selectedDefence;
	}
	
	public String getSelectedDistance() {
		String selectedDistance = (String) distanceChooser.getSelected();
		return selectedDistance;
	}
	
	public String getSelectedGoal() {
		String selectedGoal = (String) goalChooser.getSelected();
		return selectedGoal;
	}
}