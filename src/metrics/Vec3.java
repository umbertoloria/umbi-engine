package metrics;

public class Vec3 implements Cloneable {

	/*public double length() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	public void normalize() {
		scale(1 / length());
	}

	public void scale(double scalar) {
		this.x *= scalar;
		this.y *= scalar;
		this.z *= scalar;
	}*/

	private double x, y, z;

	public Vec3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double x() {
		return x;
	}

	public double y() {
		return y;
	}

	public double z() {
		return z;
	}

	public void add(double ax, double ay, double az) {
		x += ax;
		y += ay;
		z += az;
	}

	public void move(double angle, double length) {
		// FIXME
		x += Math.cos(Math.toRadians(angle)) * length;
		y -= Math.sin(Math.toRadians(angle)) * length;
	}

	public void placeTo(Vec3 dot) {
		x = dot.x;
		y = dot.y;
		z = dot.z;
	}

	public double distanceTo(Vec3 dot) {
		// FIXME
		return Math.sqrt(Math.pow(x - dot.x, 2) + Math.pow(y - dot.y, 2));
	}

	public double angleTo(Vec3 dot) {
		// FIXME
		return Math.toDegrees(Math.atan2(dot.y - y, dot.x - x)) % 360;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	public String toString() {
		return getClass().getName() + "[x=" + x + ",y=" + y + ",z=" + z + "]";
	}

}
