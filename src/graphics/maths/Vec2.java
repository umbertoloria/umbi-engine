package graphics.maths;

public class Vec2 {

	public float x, y;

	public Vec2(Vec2 save) {
		place(save);
	}

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

	public void place(Vec2 save) {
		x = save.x;
		y = save.y;
	}

	public String toString() {
		return String.format("X: %-3.3f; Y: %-3.3f", x, y);
	}

}
