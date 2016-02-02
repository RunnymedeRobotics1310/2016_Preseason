package robot.commands;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.R_PIDController;
import robot.R_PIDInput;
import robot.Robot;

public class GoStraightPID {

	/*
	 * Angle PID Controller
	 * 
	 * These controllers are declared as static so that they can be adjusted in the console
	 */
	private static R_PIDInput gyroPIDInput = new R_PIDInput() {
		@Override
		public double pidGet() {
			return -Robot.chassisSubsystem.getAngleDifference(pidSetpoint) / 180.0;
		}
	};

	private static PIDOutput gyroPIDOutput = new PIDOutput() {
		@Override
		public void pidWrite(double output) {
			pidOutputValue = output;
		}
	};
	
	private static double pidSetpoint = 0.0;
	private static double pidOutputValue = 0.0;

	private static R_PIDController anglePIDController = new R_PIDController(30.0, 3.0, 0.0, 1.0, gyroPIDInput, gyroPIDOutput);

	//*********************************************************************************************
	
	public static void setEnabled(boolean enabled) {
		if (enabled) {
			anglePIDController.enable();
		} else {
			anglePIDController.disable();
		}
	}
	
	public static boolean isEnabled() {
		return anglePIDController.isEnabled();
	}
	
	public static double getOutput() { 
		return pidOutputValue;
	}
	
	public static void setSetpoint(double setpoint) {
		anglePIDController.setSetpoint(setpoint);
		pidSetpoint = setpoint;
	}

	public static void periodic() {
		anglePIDController.calculate();
	}
	
	public static void updateDashboard() {
		SmartDashboard.putData("AnglePID", anglePIDController);
	}

}
