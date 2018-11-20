package metrics;

public class Vec2 implements Cloneable {

	private double x, y;

	public Vec2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void add(double ax, double ay) {
		x += ax;
		y += ay;
	}

	public void move(double angle, double length) {
		x += Math.cos(Math.toRadians(angle)) * length;
		y -= Math.sin(Math.toRadians(angle)) * length;
	}

	public double x() {
		return x;
	}

	public double y() {
		return y;
	}

	public double distanceTo(Vec2 dot) {
		return Math.sqrt(Math.pow(x - dot.x, 2) + Math.pow(y - dot.y, 2));
	}

	public double angleTo(Vec2 dot) {
		return Math.toDegrees(Math.atan2(dot.y - y, dot.x - x)) % 360;
	}

	public void placeTo(Vec2 dot) {
		x = dot.x;
		y = dot.y;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	public String toString() {
		return getClass().getName() + "[x=" + x + ",y=" + y + "]";
	}

}
