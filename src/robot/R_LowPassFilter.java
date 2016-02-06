package robot;

public class R_LowPassFilter {
    double cutOff;
    double sampleTime;
    double yPrevious = 0;
    double xPrevious = 0;
    
    double lastError = -1;

	/*
	 * y = [(x + xp) - (1 - {2t/T})yp]/[1 + (2t/T)]
	 * 
	 * where t = 1 / f-cutoff
	 * T = sample time
	 */

    /**
     * This class can be used to filter data through a low pass filter.
     * 
     * @param cutOffFrequency The frequency (in Hz) that should be used for cut off.
     * @param sampleTime The time (in seconds) that your data is sampled.
     */
    public R_LowPassFilter(double cutOffFrequency, double sampleTime) {
        this.cutOff = 1 / cutOffFrequency;
        this.sampleTime = sampleTime;
    }

    public void reset(double initVal) {
        yPrevious = xPrevious = initVal;
    }

    /**
     * Calculates/adjusts the output given the low pass filter.
     * 
     * @param x The input to pass to the filter
     * @return The adjusted output
     */
    public double calculate(double x) {
        if (Math.abs(x - xPrevious) < 10 || Math.abs(lastError - x) < 5) {
            double numerator = (x + xPrevious) - (1 - (2 * cutOff / sampleTime)) * yPrevious;
            double denominator = 1 + (2 * cutOff / sampleTime);
            double y = numerator / denominator;

            xPrevious = x;
            yPrevious = y;
            lastError = -1;
            
            return y;
        } else {
        	lastError = x;
        	System.out.println("Throwing out Ultrasonic value: " + x);
        }

        return yPrevious;
    }
}