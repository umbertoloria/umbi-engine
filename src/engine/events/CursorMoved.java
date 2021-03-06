package engine.events;

public class CursorMoved extends Event {

	private double x, y;

	public CursorMoved(double x, double y) {
		super(Type.CURSOR_MOVED);
		this.x = x;
		this.y = y;
	}

	double getXMotion() {
		return x;
	}

	double getYMotion() {
		return y;
	}

}
