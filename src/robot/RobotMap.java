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
        LEFT_MOTOR(0, inverted),
        RIGHT_MOTOR(1, !inverted);

        public final int port;
        public final boolean inverted;

        MotorMap(int port, boolean invertedState) {
            this.port = port;
            this.inverted = invertedState;
        }
    }

    public enum sensorMap {
        LEFT_ENCODER(0),
        RIGHT_ENCDOER(1);

        public final int port;

        sensorMap(int port) {
            this.port = port;
        }
    }
}
