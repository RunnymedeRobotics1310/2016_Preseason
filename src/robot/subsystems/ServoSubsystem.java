
package robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.R_Subsystem;
import robot.RobotMap;
import robot.commands.ServoCommand;

/**
 *
 */
public class ServoSubsystem extends R_Subsystem {

	private Servo servo = new Servo(RobotMap.ServoMap.SERVO_ONE.port); 

	public void initDefaultCommand() {
		setDefaultCommand(new ServoCommand());
	}


	public void moveToPosition(double position) { 
		servo.setPosition(position); 
	}
	
	public void stop() {
		servo.setPosition(servo.getPosition());
	}
	
	@Override
	public void updateDashboard() {
		SmartDashboard.putData("Servo", servo);
	}
}
