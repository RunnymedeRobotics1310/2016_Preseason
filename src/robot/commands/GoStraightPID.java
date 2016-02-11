package robot.commands;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.R_PIDController;
import robot.R_PIDInput;
import robot.Robot;

public class GoStraightPID {

	/*
	 * ENCODER PID Controller
	 * 
	 * The encoder PID controller is declared as static so that they can be adjusted in the SmartDashboard
	 */
	private static R_PIDInput encoderPIDInput = new R_PIDInput() {
		@Override
		public double pidGet() {
			return -Robot.chassisSubsystem.getEncoderDifference();
		}
	};

	private static PIDOutput encoderPIDOutput = new PIDOutput() {
		@Override
		public void pidWrite(double output) {
			pidOutputValue = output;
		}
	};

	private static double pidOutputValue = 0.0;

	private static R_PIDController encoderPIDController = 
			new R_PIDController(1.0, 0.0, 0.0, 1.0, encoderPIDInput, encoderPIDOutput);
	
	public static void setEnabled(boolean enabled) {
		if (enabled) {
			encoderPIDController.enable();
		} else {
			encoderPIDController.reset();
		}
	}
	
	public static boolean isEnabled() {
		return encoderPIDController.isEnabled();
	}
	
	public static double getOutput() { 
		return pidOutputValue;
	}
	
	public static void setSetpoint() {
		Robot.chassisSubsystem.resetEncoders();
	}

	public static void periodic() {
		encoderPIDController.calculate();
	}
	
	public static void updateDashboard() {
		SmartDashboard.putData("EncoderPID", encoderPIDController);
	}

}
