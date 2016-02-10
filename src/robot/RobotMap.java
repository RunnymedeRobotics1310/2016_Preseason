package robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    private final static boolean inverted = true;

    public enum MotorMap {
        LEFT_MOTOR (1, !RobotMap.inverted),
        RIGHT_MOTOR(0, RobotMap.inverted),
        INTAKE_MOTOR(2, !RobotMap.inverted),
        LEFT_SHOOTER_MOTOR(3, !RobotMap.inverted),
        RIGHT_SHOOTER_MOTOR(4, !RobotMap.inverted);

        public final int port;
        public final boolean inverted;

        MotorMap(int port, boolean invertedState) {
            this.port = port;
            this.inverted = invertedState;
        }
    }
    
    public enum ServoMap {
        SERVO_ONE (9);
        public final int port;
        ServoMap(int port) {
        	this.port = port;
        }
    }
    
    public enum SensorMap {
    	// Analog Ports
    	GYRO         (0), 
    	ULTRASONIC   (3),
    	
    	// Digital Ports
    	SHOOTER_LIMIT_SWITCH(6),
    	LEFT_PROXIMITY_SENSOR(7),
    	CENTER_PROXIMITY_SENSOR(8),
    	RIGHT_PROXIMITY_SENSOR(9);
    	
        public final int port;

        SensorMap(int port) {
            this.port = port;
        }
    }
    
    public enum EncoderMap {
    	LEFT (2, 3, 1800.0, 29.75),
    	RIGHT(0, 1, 1800.0, 29.75),
    	
    	//Counts per inch not accurate
    	LEFT_SHOOTER(4,5,1800.0,29.75),
    	RIGHT_SHOOTER(6,7,1800.0,29.75);
    	
    	public final int ch1;
    	public final int ch2;
    	public final double maxRate;
    	public final double countsPerInch;
    	
    	EncoderMap(int ch1, int ch2, double maxRate, double countsPerInch) {
    		this.ch1 = ch1;
    		this.ch2 = ch2;
    		this.maxRate = maxRate;
    		this.countsPerInch = countsPerInch;
    	}
    }    
}
