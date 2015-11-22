package ca.team1310;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

/**
 * Implements the ITG3200 sensor for the NI roboRIO.
 * <p>
 * This sensor is a 3-axis Gyro.  This driver returns only the z-axis for the 
 * sensor.
 * <p>
 * The implementation uses an 8kHz interrupt on the roboRIO to sample the device.
 * <p>
 * @author Richard McMullin
 *
 */
public class ITG3200 {

	// Currently uses only the onboard port and the address 0x68
    private I2C i2c = new I2C(Port.kOnboard, 0x68);
	
    private int offsetX = 0, offsetY = 0, offsetZ = 0;
    
    GyroAxis x, y, z;
    
	// SampleTimer - used when there is no external interrupt used for 
	// sample timing.
	private final SampleTimer sampleTimer;

	public ITG3200() {
		initGyro();
		sampleTimer = new SampleTimer();
		sampleTimer.run();
	}

	private class GyroAxis {
		double rate = 0;
		double angle = 0;
	}
	
	private class SampleTimer implements Runnable {

		long loopStartTime;
		long loopEndTime;
		
		public void run() {
			loopStartTime = System.nanoTime();
			while (true) {
				loopEndTime = System.nanoTime();
				readGyro(loopEndTime - loopStartTime);
				loopStartTime = System.nanoTime();
				synchronized (sampleTimer) {
					try {
						sampleTimer.wait(1);  // Approx 800 Hz looping
					} catch (InterruptedException e) { } // Do nothing
				}
			}
		}
	}

	private void initGyro() {
	}
	
	private void readGyro(long loopTime) {
		
		double [] gyroRates = readGyroRates();
		double [] angleChanges = new double [3];
		
		angleChanges[0] = gyroRates[0] * loopTime;
		angleChanges[1] = gyroRates[1] * loopTime;
		angleChanges[2] = gyroRates[2] * loopTime;
		
		updateAxis(gyroRates, angleChanges);
	}
	
	private void updateAxis(double [] newRates, double [] angleChanges) {
		// Synchronize the axis values when updating;
		synchronized (x) {
			x.rate = newRates[0];
			x.angle = (x.angle + angleChanges[0]) % 360;
			if (x.angle < 0) {
				x.angle += 360;
			}
		}
		synchronized (y) {
			y.rate = newRates[0];
			y.angle = (y.angle + angleChanges[0]) % 360;
			if (y.angle < 0) {
				y.angle += 360;
			}
		}
		synchronized (z) {
			z.rate = newRates[0];
			z.angle = (z.angle + angleChanges[0]) % 360;
			if (z.angle < 0) {
				z.angle += 360;
			}
		}
	}
	
	private double [] readGyroRates() {
		byte [] gyroRateBuffer = new byte[6];
		i2c.read(29, gyroRateBuffer.length, gyroRateBuffer);
		double [] rates = new double [3];
		rates[0] = (gyroRateBuffer[0] << 8 | gyroRateBuffer[1] + offsetX) * 1.0;
		rates[1] = (gyroRateBuffer[2] << 8 | gyroRateBuffer[3] + offsetY) * 1.0;
		rates[2] = (gyroRateBuffer[4] << 8 | gyroRateBuffer[5] + offsetZ) * 1.0;
		return rates;
	}
	
	public double getZAngle() {
		synchronized (z) {
			return z.angle;
		}
	}

}