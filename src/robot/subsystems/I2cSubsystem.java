
package robot.subsystems;

import ca.team1310.ITG3200;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.R_Subsystem;

/**
 *
 */
public class I2cSubsystem extends R_Subsystem {

	//ITG3200 itg3200 = new ITG3200();
	
	I2C i2cGyro = new I2C(Port.kOnboard, (int) 0x68);
	byte [] i2cGyroBuffer = new byte[64];

	public void initDefaultCommand() {

	}

	@Override
	public void init() {
		byte FS_SEL = 0x18;
		byte LOW_PASS_FILTER_98HZ = 0x02;
		// Initialize the Gyro bits
		i2cGyro.write(22, FS_SEL + LOW_PASS_FILTER_98HZ);
		
		byte X_AXIS_ONLY = 0x18;
		i2cGyro.write(62, X_AXIS_ONLY);
		
	
	}
	
	public void updateDashboard() {

		byte [] buffer = new byte [4];
		for (int i=0; i<i2cGyroBuffer.length/4; i++) {
			i2cGyro.read(i*4, 4, buffer);
			for (int j=0; j<4; j++) {
				i2cGyroBuffer[i*4+j] = buffer[j];
			}
		}

		for (int i=0; i<i2cGyroBuffer.length; i++) {
			SmartDashboard.putString(
					"gyro[" + i + "]", Integer.toHexString(i2cGyroBuffer[i]));
		}
		
//		SmartDashboard.putNumber(
//				"z angle", itg3200.getZAngle());
		

	}

}
