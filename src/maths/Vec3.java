package maths;

import org.joml.Vector3f;

public class Vec3 extends Vector3f {

	public Vec3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void applyYaw(float angle, float length) {
		x += Math.cos(Math.toRadians(angle)) * length;
		z += Math.sin(Math.toRadians(angle)) * length;
	}

//	public void moveSky(float angle, float length) {
//		y -= Math.cos(Math.toRadians(angle)) * length;
//		z -= Math.sin(Math.toRadians(angle)) * length;
//	}

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
		return String.format("X: %-3.3f; Y: %-3.3f; Z: %-3.3f;", x, y, z);
	}

}
