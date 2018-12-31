package maths;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import utils.BufferUtils;

import java.nio.FloatBuffer;

public class Mat {

	private float[] elements = new float[16];

//	public static Mat orthographic(float left, float right, float bottom, float top, float near, float far) {
//		Matrix4f ortho = new Matrix4f();
//		ortho.ortho(left, right, bottom, top, near, far);
//		return new Mat(ortho);
//	}

	public static Mat perspective(float fov, float aspRatio, float near, float far) {
		Matrix4f m = new Matrix4f();
		m.perspective((float) Math.toRadians(fov), aspRatio, near, far);
		return new Mat(m);
	}

	public static Mat model(Vec3 translation, Vec3 rotation, float scale) {
		Matrix4f model = new Matrix4f();
		model.translate(-translation.x, translation.y, translation.z);
		model.rotate(rotation.x(), new Vector3f(1, 0, 0));
		model.rotate(rotation.y(), new Vector3f(0, 1, 0));
		model.rotate(rotation.z(), new Vector3f(0, 0, 1));
		model.scale(scale);
		return new Mat(model);
	}

	public static Mat view(Vec3 position, float pitch, float yaw) {
		Matrix4f m = new Matrix4f();
		m.identity();
		m.rotateX((float) Math.toRadians(90 - pitch));
		m.rotateY((float) Math.toRadians(yaw));
		m.translate(-position.x, -position.y, -position.z);
		return new Mat(m);
	}

	public FloatBuffer toFloatBuffer() {
		//return BufferUtils.createFloatBuffer(16).put(elements).flip();
		return BufferUtils.createFloatBuffer(elements);
	}

	private Mat(Matrix4f m) {
		m.get(elements);
	}

//	private Mat() {
//	}

//	public static Mat identity() {
//		Mat result = new Mat();
//		for (int i = 0; i < 16; i++) {
//			result.elements[i] = 0;
//		}
//		for (int i = 0; i < 4; i++) {
//			result.elements[i + i * 4] = 1;
//		}
//		return result;
//	}

//	private Mat multiply(Mat matrix) {
//		Mat result = new Mat();
//		for (int y = 0; y < 4; y++) {
//			for (int x = 0; x < 4; x++) {
//				float sum = 0;
//				for (int e = 0; e < 4; e++) {
//					sum += this.elements[x + e * 4] * matrix.elements[e + y * 4];
//				}
//				result.elements[x + y * 4] = sum;
//			}
//		}
//		return result;
//	}

}
