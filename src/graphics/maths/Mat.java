package graphics.maths;

import org.joml.Matrix4f;

public class Mat {

	private float[] elements = new float[16];

	private Mat(Matrix4f m) {
		m.get(elements);
	}

	public static Mat orthographic(float left, float right, float bottom, float top, float near, float far) {
		Matrix4f result = new Matrix4f();
		result = result.ortho(left, right, bottom, top, near, far);
		return new Mat(result);
	}

	public static Mat perspective(float fov, float aspRatio, float near, float far) {
		Matrix4f result = new Matrix4f();
		result = result.perspective((float) Math.toRadians(fov), aspRatio, near, far);
		return new Mat(result);
	}

	public static Mat model(float tx, float ty, float tz, float rx, float ry, float rz, float sx, float sy, float sz) {
		Matrix4f model = new Matrix4f();
		model.translate(tx, ty, tz);
		model.rotateY((float) Math.toRadians(ry));
		model.rotateX((float) Math.toRadians(rx));
		model.rotateZ((float) Math.toRadians(rz));
		model.scale(sx, sy, sz);
		return new Mat(model);
	}

	public static Mat view(float tx, float ty, float tz, float rx, float ry, float rz) {
		Matrix4f m = new Matrix4f();
		m.rotateX((float) Math.toRadians(-rx));
		m.rotateY((float) Math.toRadians(-ry));
		m.rotateZ((float) Math.toRadians(-rz));
		m.translate(-tx, -ty, -tz);
		return new Mat(m);
	}

	public float[] toArray() {
		return elements.clone();
	}

	public String toString() {
		StringBuilder res = new StringBuilder();
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (elements[x + y * 4] < 0) {
					res.append(String.format("| -%3.3f ", -elements[x + y * 4]));
				} else {
					res.append(String.format("|  %3.3f ", elements[x + y * 4]));
				}
			}
			res.append("|\n");
		}
		return res.toString();
	}

}
