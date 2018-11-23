package metrics;

import org.lwjgl.BufferUtils;
import inputs.Camera;
import windows.GameWindow;

import java.nio.FloatBuffer;

public class Matrix {

	private double[][] m = new double[4][4];

	public void setIdentity() {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				m[i][j] = i == j ? 1 : 0;
			}
		}
	}

	public void translate(double x, double y, double z) {
		m[0][3] += m[0][0] * x + m[0][1] * y + m[0][2] * z;
		m[1][3] += m[1][0] * x + m[1][1] * y + m[1][2] * z;
		m[2][3] += m[2][0] * x + m[2][1] * y + m[2][2] * z;
		m[3][3] += m[3][0] * x + m[3][1] * y + m[3][2] * z;
	}

	public void rotate(double angle, Vec3 axis) {
		float c = (float) Math.cos(angle);
		float s = (float) Math.sin(angle);
		float oneminusc = 1.0f - c;
		double xy = axis.x() * axis.y();
		double yz = axis.y() * axis.z();
		double xz = axis.x() * axis.z();
		double xs = axis.x() * s;
		double ys = axis.y() * s;
		double zs = axis.z() * s;

		double f00 = axis.x() * axis.x() * oneminusc + c;
		double f10 = xy * oneminusc + zs;
		double f20 = xz * oneminusc - ys;
		// n[3] not used
		double f01 = xy * oneminusc - zs;
		double f11 = axis.y() * axis.y() * oneminusc + c;
		double f21 = yz * oneminusc + xs;
		// n[7] not used
		double f02 = xz * oneminusc + ys;
		double f12 = yz * oneminusc - xs;
		double f22 = axis.z() * axis.z() * oneminusc + c;

		double t00 = m[0][0] * f00 + m[0][1] * f01 + m[0][2] * f02;
		double t01 = m[1][0] * f00 + m[1][1] * f01 + m[1][2] * f02;
		double t02 = m[2][0] * f00 + m[2][1] * f01 + m[2][2] * f02;
		double t03 = m[3][0] * f00 + m[3][1] * f01 + m[3][2] * f02;
		double t10 = m[0][0] * f10 + m[0][1] * f11 + m[0][2] * f12;
		double t11 = m[1][0] * f10 + m[1][1] * f11 + m[1][2] * f12;
		double t12 = m[2][0] * f10 + m[2][1] * f11 + m[2][2] * f12;
		double t13 = m[3][0] * f10 + m[3][1] * f11 + m[3][2] * f12;
		m[0][2] = m[0][0] * f20 + m[0][1] * f21 + m[0][2] * f22;
		m[1][2] = m[1][0] * f20 + m[1][1] * f21 + m[1][2] * f22;
		m[2][2] = m[2][0] * f20 + m[2][1] * f21 + m[2][2] * f22;
		m[3][2] = m[3][0] * f20 + m[3][1] * f21 + m[3][2] * f22;
		m[0][0] = t00;
		m[1][0] = t01;
		m[2][0] = t02;
		m[3][0] = t03;
		m[0][1] = t10;
		m[1][1] = t11;
		m[2][1] = t12;
		m[3][1] = t13;
	}

	public void scale(Vec3 scaling) {
		m[0][0] *= scaling.x();
		m[1][0] *= scaling.x();
		m[2][0] *= scaling.x();
		m[3][0] *= scaling.x();
		m[0][1] *= scaling.y();
		m[1][1] *= scaling.y();
		m[2][1] *= scaling.y();
		m[3][1] *= scaling.y();
		m[0][2] *= scaling.z();
		m[1][2] *= scaling.z();
		m[2][2] *= scaling.z();
		m[3][2] *= scaling.z();
	}

	public FloatBuffer toFloatBuffer() {
		FloatBuffer buff = BufferUtils.createFloatBuffer(16);
		for (double[] row : m) {
			for (double cell : row) {
				buff.put((float) cell);
			}
		}
		buff.flip();
		return buff;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for (double[] row : m) {
			for (double v : row) {
				str.append("| ");
				str.append(v >= 0 ? " " + v : v);
				str.append(" ");
			}
			str.append("|\n");
		}
		return str.toString();
	}

	public static Matrix createTransformationMatrix(double tx, double ty, double tz, double rx, double ry, double rz,
	                                                double scale) {
		Matrix m = new Matrix();
		m.setIdentity();
		m.translate(tx, ty, tz);
		m.rotate((float) Math.toRadians(rx), new Vec3(1, 0, 0));
		m.rotate((float) Math.toRadians(ry), new Vec3(0, 1, 0));
		m.rotate((float) Math.toRadians(rz), new Vec3(0, 0, 1));
		m.scale(new Vec3(scale, scale, scale));
		return m;
	}

	public static Matrix createProjectionMatrix(double fov, double far, double near) {
		double aspectRatio = GameWindow.PROPS;
		double y_scale = (1d / Math.tan(Math.toRadians(fov / 2))) * aspectRatio;
		double frustum_length = far - near;
		Matrix matrix = new Matrix();
		matrix.m[0][0] = y_scale / aspectRatio;
		matrix.m[1][1] = y_scale;
		matrix.m[2][2] = -((far + near) / frustum_length);
		matrix.m[3][2] = -1;
		matrix.m[2][3] = -((2 * near * far) / frustum_length);
		matrix.m[3][3] = 0;
		return matrix;
	}

	public static Matrix createViewMatrix(Camera camera) {
		Matrix matrix = new Matrix();
		matrix.setIdentity();
		matrix.rotate(Math.toRadians(camera.getPitch()), new Vec3(1, 0, 0));
		matrix.rotate(Math.toRadians(camera.getYaw()), new Vec3(0, 1, 0));
		matrix.translate(-camera.getPosition().x(), -camera.getPosition().y(), -camera.getPosition().z());
		return matrix;
	}

}
