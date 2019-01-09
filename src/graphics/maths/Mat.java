package graphics.maths;

import engine.buffers.utils.BufferUtils;
import org.joml.Matrix4f;

import java.nio.FloatBuffer;

public class Mat {

	private float[] elements = new float[16];

	public static Mat orthographic (float left, float right, float bottom, float top, float near, float far) {
		Matrix4f m = new Matrix4f();
		m.ortho(left, right, bottom, top, near, far);
		return new Mat(m);
	}

	public static Mat perspective(float fov, float aspRatio, float near, float far) {
//		float y_scale = (float) ((1f / Math.tan(Math.toRadians(fov / 2f))) * aspRatio);
//		float x_scale = y_scale / aspRatio;
//		float frustum_length = far - near;
//		Matrix4f projectionMatrix = new Matrix4f();
//		projectionMatrix._m00(x_scale);
//		projectionMatrix._m11(y_scale);
//		projectionMatrix._m22(-((far + near) / frustum_length));
//		projectionMatrix._m23(-1);
//		projectionMatrix._m32(-((2 * near * far) / frustum_length));
//		projectionMatrix._m33(0);
//		return new Mat(projectionMatrix);
		Matrix4f m = new Matrix4f();
		m.perspective((float) Math.toRadians(fov), aspRatio, near, far);
		return new Mat(m);
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

	public FloatBuffer toFloatBuffer() {
		return BufferUtils.createFloatBuffer(elements);
	}

	private Mat(Matrix4f m) {
		m.get(elements);
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
