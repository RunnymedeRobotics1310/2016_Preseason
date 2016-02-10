package robot;

public class Toggle {

	boolean state, previousState;
	long time = System.nanoTime();

	public Toggle(boolean initialState) {
		this.state = initialState;
	}

	public boolean update(boolean pressed) {
		if (pressed && !previousState) {
			state = !state;
			time = System.nanoTime();
		}

		previousState = pressed;
		return state;
	}

	public boolean toggle() {
		state = !state;
		return state;
	}

	public boolean getState() {
		return state;
	}

	public boolean setState(boolean state) {
		this.state = state;
		return this.state;
	}

	public long getLastStateChangeTime() {
		return time;
	}
}