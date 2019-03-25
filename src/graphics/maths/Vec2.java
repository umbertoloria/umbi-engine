package graphics.maths;

public final class Vec2 {

	public final float x, y;

	public Vec2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vec2(double x, double y) {
		this.x = (float) x;
		this.y = (float) y;
	}

//	public void move(float angle, float length) {
//		x += Math.cos(Math.toRadians(angle)) * length;
//		y += Math.sin(Math.toRadians(angle)) * length;
//	}

	public Vec2 add(Vec2 add) {
		return new Vec2(x + add.x, y + add.y);
	}

	public Vec2 multiply(float scalar) {
		return new Vec2(x * scalar, y * scalar);
	}

//	public float x() {
//		return x;
//	}
//
//	public float y() {
//		return y;
//	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vec2 vec2 = (Vec2) o;
		return vec2.x == x && vec2.y == y;
	}

	public String toString() {
		return String.format("X: %-3.3f; Y: %-3.3f", x, y);
	}

}
