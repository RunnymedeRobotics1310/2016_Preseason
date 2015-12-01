package robot;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class R_Subsystem extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public void periodic() {
		
	}
	
	public void init() {}
	
	/**
	 * Update the dashboard every cycle.
	 */
	public abstract void updateDashboard();
}
