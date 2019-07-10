package graphics.maths;

public final class Vec3 {

	public final float x, y, z;

	public Vec3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vec3 move(float no, float si, float distanza) {
		float newx = x - (float) (distanza * Math.cos(Math.toRadians(no)) * Math.cos(Math.toRadians(si)));
		float newy = y + (float) (distanza * Math.sin(Math.toRadians(si)));
		float newz = z + (float) (distanza * Math.sin(Math.toRadians(no)) * Math.cos(Math.toRadians(si)));
		return new Vec3(newx, newy, newz);
	}

//	public float distanceTo(Vec3 dot) {
//		return (float) Math.sqrt(Math.pow(x - dot.x, 2) + Math.pow(y - dot.y, 2));
//	}

//	public float angleTo(Vec3 dot) {
//		return (float) Math.toDegrees(Math.atan2(dot.y - y, dot.x - x)) % 360;
//	}

	public Vec3 add(Vec3 add) {
		return new Vec3(x + add.x, y + add.y, z + add.z);
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vec3 vec3 = (Vec3) o;
		return vec3.x == x && vec3.y == y && vec3.z == z;
	}

	public String toString() {
		return String.format("X: %-3.3f; Y: %-3.3f; Z: %-3.3f", x, y, z);
	}

}
