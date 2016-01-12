package robot;

import edu.wpi.first.wpilibj.PIDInterface;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

/**
 * Class implements a PID Control Loop.
 *
 * Creates a separate thread which reads the given PIDSource and takes care of
 * the integral calculations, as well as writing the given PIDOutput
 */
public class R_PIDController implements PIDInterface, LiveWindowSendable {

	public static final double kDefaultPeriod = .05;
	private double m_P; // factor for "proportional" control
	private double m_I; // factor for "integral" control
	private double m_D; // factor for "derivative" control
	private double m_F; // factor for feedforward term
	private double m_maximumOutput = 1.0; // |maximum output|
	private double m_minimumOutput = -1.0; // |minimum output|
	private double m_maximumInput = 0.0; // maximum input - limit setpoint to this
	private double m_minimumInput = 0.0; // minimum input - limit setpoint to this
	private boolean m_enabled = false; // is the pid controller enabled
	private double m_prevError = 0.0; // the prior error (used to compute
	// velocity)
	private double m_totalError = 0.0; // the sum of the errors for use in the
	// integral calc
	
	private double m_setpoint = 0.0;
	private double m_result = 0.0;
	
	protected PIDSource m_pidInput;
	protected PIDOutput m_pidOutput;

	/**
	 * Allocate a PID object with the given constants for P, I, D, and F
	 *$
	 * @param Kp the proportional coefficient
	 * @param Ki the integral coefficient
	 * @param Kd the derivative coefficient
	 * @param Kf the feed forward term
	 * @param source The PIDSource object that is used to get values
	 * @param output The PIDOutput object that is set to the output percentage
	 * @param period the loop time for doing calculations. This particularly
	 *        effects calculations of the integral and differential terms. The
	 *        default is 50ms.
	 */
	public R_PIDController(double Kp, double Ki, double Kd, double Kf, PIDSource input,
			PIDOutput output) {

		if (input == null) {
			throw new NullPointerException("Null PIDSource was given");
		}
		if (output == null) {
			throw new NullPointerException("Null PIDOutput was given");
		}

		m_P = Kp;
		m_I = Ki;
		m_D = Kd;
		m_F = Kf;

		m_pidInput = input;
		m_pidOutput = output;
	}

	/**
	 * Read the input, calculate the output accordingly, and write to the output.
	 * This should only be called by the PIDTask and is created during
	 * initialization.
	 */
	public void calculate() {
		boolean enabled;

		synchronized (this) {
			if (m_pidInput == null) {
				return;
			}
			if (m_pidOutput == null) {
				return;
			}
			enabled = m_enabled; // take snapshot of these values...
		}

		if (enabled) {
			double result;
			double error;
			PIDOutput pidOutput = null;
			synchronized (this) {

				error = getError();

				if (m_I != 0) {
					double potentialIGain = (m_totalError + error) * m_I;
					if (potentialIGain < m_maximumOutput) {
						if (potentialIGain > m_minimumOutput) {
							m_totalError += error;
						} else {
							m_totalError = m_minimumOutput / m_I;
						}
					} else {
						m_totalError = m_maximumOutput / m_I;
					}
				}

				m_result = m_P * error + m_I * m_totalError +
						m_D * (error - m_prevError) + m_F * getSetpoint();

				m_prevError = error;

				if (m_result > m_maximumOutput) {
					m_result = m_maximumOutput;
				} else if (m_result < m_minimumOutput) {
					m_result = m_minimumOutput;
				}
				pidOutput = m_pidOutput;
				result = m_result;
				pidOutput.pidWrite(result);
			}

		}
		
		if (table != null) {
			table.putNumber("error", getError());
			table.putNumber("output", Math.round(get()*1000.0)/1000.0);
		}

	}

	/**
	 * Set the PID Controller gain parameters. Set the proportional, integral, and
	 * differential coefficients.
	 *$
	 * @param p Proportional coefficient
	 * @param i Integral coefficient
	 * @param d Differential coefficient
	 */
	public synchronized void setPID(double p, double i, double d) {
		m_P = p;
		m_I = i;
		m_D = d;

		if (table != null) {
			table.putNumber("p", p);
			table.putNumber("i", i);
			table.putNumber("d", d);
		}
	}

	/**
	 * Set the PID Controller gain parameters. Set the proportional, integral, and
	 * differential coefficients.
	 *$
	 * @param p Proportional coefficient
	 * @param i Integral coefficient
	 * @param d Differential coefficient
	 * @param f Feed forward coefficient
	 */
	public synchronized void setPID(double p, double i, double d, double f) {
		m_P = p;
		m_I = i;
		m_D = d;
		m_F = f;

		if (table != null) {
			table.putNumber("p", p);
			table.putNumber("i", i);
			table.putNumber("d", d);
			table.putNumber("f", f);
		}
	}

	/**
	 * Get the Proportional coefficient
	 *$
	 * @return proportional coefficient
	 */
	public synchronized double getP() {
		return m_P;
	}

	/**
	 * Get the Integral coefficient
	 *$
	 * @return integral coefficient
	 */
	public synchronized double getI() {
		return m_I;
	}

	/**
	 * Get the Differential coefficient
	 *$
	 * @return differential coefficient
	 */
	public synchronized double getD() {
		return m_D;
	}

	/**
	 * Get the Feed forward coefficient
	 *$
	 * @return feed forward coefficient
	 */
	public synchronized double getF() {
		return m_F;
	}

	/**
	 * Return the current PID result This is always centered on zero and
	 * constrained the the max and min outs
	 *$
	 * @return the latest calculated output
	 */
	public synchronized double get() {
		return m_result;
	}

	/**
	 * Set the setpoint for the PIDController
	 * Clears the queue for GetAvgError().
	 *$
	 * @param setpoint the desired setpoint
	 */
	public synchronized void setSetpoint(double setpoint) {
		if (m_maximumInput > m_minimumInput) {
			if (setpoint > m_maximumInput) {
				m_setpoint = m_maximumInput;
			} else if (setpoint < m_minimumInput) {
				m_setpoint = m_minimumInput;
			} else {
				m_setpoint = setpoint;
			}
		} else {
			m_setpoint = setpoint;
		}

		if (table != null)
			table.putNumber("setpoint", m_setpoint);
	}

	/**
	 * Returns the current setpoint of the PIDController
	 *$
	 * @return the current setpoint
	 */
	public synchronized double getSetpoint() {
		return m_setpoint;
	}

	/**
	 * Returns the current difference of the input from the setpoint
	 *$
	 * @return the current error
	 */
	public synchronized double getError() {
		// return m_error;
		return getSetpoint() - m_pidInput.pidGet();
	}

	/**
	 * Sets what type of input the PID controller will use
	 *$
	 * @param pidSource the type of input
	 */
	void setPIDSourceType(PIDSourceType pidSource) {
		m_pidInput.setPIDSourceType(pidSource);
	}

	/**
	 * Returns the type of input the PID controller is using
	 *$
	 * @return the PID controller input type
	 */
	PIDSourceType getPIDSourceType() {
		return m_pidInput.getPIDSourceType();
	}

	/**
	 * Begin running the PIDController
	 */
	@Override
	public synchronized void enable() {
		m_enabled = true;

		if (table != null) {
			table.putBoolean("enabled", true);
		}
	}

	/**
	 * Stop running the PIDController, this sets the output to zero before
	 * stopping.
	 */
	@Override
	public synchronized void disable() {
		m_pidOutput.pidWrite(0);
		m_enabled = false;

		if (table != null) {
			table.putBoolean("enabled", false);
		}
	}

	/**
	 * Return true if PIDController is enabled.
	 *
	 * @deprecated Call {@link #isEnabled()} instead.
	 */
	@Deprecated
	public synchronized boolean isEnable() {
		return isEnabled();
	}

	/**
	 * Return true if PIDController is enabled.
	 */
	public boolean isEnabled() {
		return m_enabled;
	}

	/**
	 * Reset the previous error,, the integral term, and disable the controller.
	 */
	public synchronized void reset() {
		disable();
		m_prevError = 0;
		m_totalError = 0;
		m_result = 0;
	}

	@Override
	public String getSmartDashboardType() {
		return "PIDController";
	}

	private final ITableListener listener = new ITableListener() {
		@Override
		public void valueChanged(ITable table, String key, Object value, boolean isNew) {
			if (key.equals("p") || key.equals("i") || key.equals("d") || key.equals("f")) {
				if (getP() != table.getNumber("p", 0.0) || getI() != table.getNumber("i", 0.0)
						|| getD() != table.getNumber("d", 0.0) || getF() != table.getNumber("f", 0.0))
					setPID(table.getNumber("p", 0.0), table.getNumber("i", 0.0), table.getNumber("d", 0.0),
							table.getNumber("f", 0.0));
			} else if (key.equals("setpoint")) {
				if (getSetpoint() != ((Double) value).doubleValue())
					setSetpoint(((Double) value).doubleValue());
			} else if (key.equals("enabled")) {
				if (isEnabled() != ((Boolean) value).booleanValue()) {
					if (((Boolean) value).booleanValue()) {
						enable();
					} else {
						disable();
					}
				}
			}
		}
	};
	private ITable table;

	@Override
	public void initTable(ITable table) {
		if (this.table != null)
			this.table.removeTableListener(listener);
		this.table = table;
		if (table != null) {
			table.putNumber("p", getP());
			table.putNumber("i", getI());
			table.putNumber("d", getD());
			table.putNumber("f", getF());
			table.putNumber("setpoint", getSetpoint());
			table.putBoolean("enabled", isEnabled());
			table.putNumber("error", getError());
			table.putNumber("output", Math.round(get()*1000.0)/1000.0);
			table.addTableListener(listener, false);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ITable getTable() {
		return table;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateTable() {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startLiveWindowMode() {
		disable();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stopLiveWindowMode() {}
}

