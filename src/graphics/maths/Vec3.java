package graphics.maths;

public class Vec3 {

	public float x, y, z;

	public Vec3(Vec3 save) {
		this(save.x, save.y, save.z);
	}

	public Vec3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void move(float no, float si, float distanza) {
		x -= distanza * Math.cos(Math.toRadians(no)) * Math.cos(Math.toRadians(si));
		y += distanza * Math.sin(Math.toRadians(si));
		z += distanza * Math.sin(Math.toRadians(no)) * Math.cos(Math.toRadians(si));
	}

	public void place(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

//	public float distanceTo(Vec3 dot) {
//		return (float) Math.sqrt(Math.pow(x - dot.x, 2) + Math.pow(y - dot.y, 2));
//	}

//	public float angleTo(Vec3 dot) {
//		return (float) Math.toDegrees(Math.atan2(dot.y - y, dot.x - x)) % 360;
//	}

	public String toString() {
		return String.format("X: %-3.3f; Y: %-3.3f; Z: %-3.3f", x, y, z);
	}

}
