package tests;

public class Switch {

	private long now = 0;
	private long last = 0;
	private int delay;

	public Switch(int delay) {
		this.delay = delay;
	}

	public boolean timeout(float delta) {
//		long now = System.currentTimeMillis();
		now += delta * 1000;
		if (last + delay <= now) {
			last = now;
			return true;
		} else {
			return false;
		}
	}

}
