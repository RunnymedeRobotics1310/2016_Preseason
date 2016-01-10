
package robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import robot.subsystems.ChassisSubsystem;
import robot.subsystems.ServoSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ChassisSubsystem chassisSubsystem = new ChassisSubsystem();
	public static final ServoSubsystem servoSubsystem = new ServoSubsystem(); 
	public static OI oi;

	public static List<R_Subsystem> subsystemList = new ArrayList<R_Subsystem>();
	
    Command autonomousCommand;

    public void autonomousInit() {
        // schedule the autonomous command
        if (autonomousCommand != null) autonomousCommand.start();
    }
	
	/**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        // instantiate the command used for the autonomous period
        autonomousCommand = null; //FIXME: add the auto command
        // Add all the subsystems to the subsystem list.
        subsystemList.add(chassisSubsystem);
        subsystemList.add(servoSubsystem);
        
        for (R_Subsystem s: subsystemList) {
        	s.init();
        }
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        for (R_Subsystem r: subsystemList) {
        	r.periodic();
        	r.updateDashboard();
        }
        oi.updateDashboard();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
