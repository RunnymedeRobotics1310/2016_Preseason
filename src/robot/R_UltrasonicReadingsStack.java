package robot;

import java.util.stream.DoubleStream;

public class R_UltrasonicReadingsStack {
	double[] readings;
	int setPOS = 0;
	int n;

	public R_UltrasonicReadingsStack(int n, double initDistance) {
		this.n = n;
		this.readings = new double[n];

		for (int i = 0; i < n; i++) {
			this.readings[i] = initDistance;
		}
	}

	public void add(double data) {
		if (setPOS + 1 > n) {
			setPOS = 0;
		}
		readings[setPOS++] = data;
	}

	public double getMeanDistance() {		
		return DoubleStream.of(readings).sum() / (n / 1.0) ;
	}
}
