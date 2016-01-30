package robot.commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.Robot;

public class DriveToUltraDistance extends DriveToDistance {

	double uDistance; // Ultrasonic distance
	double velocity;
	double angleSetpoint;
	double pidOutputTurn;

	public DriveToUltraDistance(double angle, double distance, double speed) {
		super(distance, angle, speed);
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
