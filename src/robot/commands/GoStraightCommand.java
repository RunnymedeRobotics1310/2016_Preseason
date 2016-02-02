package robot.commands;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.R_PIDController;
import robot.R_PIDInput;
import robot.Robot;

/**
 *
 */
public class GoStraightCommand extends Command {

	double angleSetpoint;

	double pidOutputTurn;
	/*
	 * Motor PID Controllers
	 */
	R_PIDInput gyroPIDInput = new R_PIDInput() {
		@Override
		public double pidGet() {
			return -Robot.chassisSubsystem.getAngleDifference(angleSetpoint) / 180.0;
		}
	};

	PIDOutput gyroPIDOutput = new PIDOutput() {
		@Override
		public void pidWrite(double output) {
			pidOutputTurn = output;
		}
	};

	R_PIDController gyroPID = new R_PIDController(30.0, 3.0, 0.0, 1.0, gyroPIDInput, gyroPIDOutput);

	List<R_PIDController> pidControllers = new ArrayList<R_PIDController>();

	public GoStraightCommand(double angle) {
		requires(Robot.chassisSubsystem);
		this.angleSetpoint = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		gyroPID.reset();
		gyroPID.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		double speed = Robot.oi.getSpeed();
		double leftSpeed;
		double rightSpeed;

		gyroPID.calculate();

		SmartDashboard.putNumber("Angle setpoint", angleSetpoint);
		SmartDashboard.putNumber("Angle difference", -Robot.chassisSubsystem.getAngleDifference(angleSetpoint));
		SmartDashboard.putNumber("GyroPIDOutput", pidOutputTurn);

		double turn = pidOutputTurn;

		if (speed < 0) {
			turn = -turn;
		}

		if (Math.abs(speed) < 0.03) {
			leftSpeed = turn * 0.25;
			rightSpeed = -turn * 0.25;
		} else {
			leftSpeed = (turn < 0) ? speed * (1 + turn) : speed;
			rightSpeed = (turn > 0) ? speed * (1 - turn) : speed;
		}

		Robot.chassisSubsystem.setSpeed(leftSpeed, rightSpeed);

		SmartDashboard.putData("Gyro PID", gyroPID);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		double turn = Robot.oi.getTurn();
		if (Math.abs(turn) > 0.03) {
			gyroPID.disable();
			SmartDashboard.putData("Gyro PID", gyroPID);
			return true;
		}
		return false;

	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
