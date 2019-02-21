package engine.events;

public class CursorPlaced extends Event {

	private double x, y;

	public CursorPlaced(double x, double y) {
		super(Type.CURSOR_PLACED);
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
