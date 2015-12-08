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
        LEFT_MOTOR (0,  RobotMap.inverted),
        RIGHT_MOTOR(1, !RobotMap.inverted);

        public final int port;
        public final boolean inverted;

        MotorMap(int port, boolean invertedState) {
            this.port = port;
            this.inverted = invertedState;
        }
    }

    public enum sensorMap {
    	LIMIT_SWITCH(9);
    	
        public final int port;

        sensorMap(int port) {
            this.port = port;
        }
    }
    
    public enum EncoderMap {
    	LEFT(0, 1),
    	RIGHT(2, 3);
    	
    	public final int ch1;
    	public final int ch2;
    	
    	EncoderMap(int ch1, int ch2) {
    		this.ch1 = ch1;
    		this.ch2 = ch2;
    	}
    }
}
