package robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.Robot;

public class DriveToUltraDistance extends DriveToDistance {

    double uDistance; // Ultrasonic distance
    double distance;
    double velocity;
    double angleSetpoint;
    double pidOutputTurn;

    public DriveToUltraDistance(double angle) {
	super(distance, angle, angle);
	requires(Robot.chassisSubsystem);
	this.angleSetpoint = angle;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
	
    }

    @Override
    protected void execute() {
	double leftSpeed;
	double rightSpeed;
	SmartDashboard.putNumber("Ultrasonic distance", uDistance);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void interrupted() {

    }

    @Override
    protected boolean isFinished() {

	return false;
    }

}
