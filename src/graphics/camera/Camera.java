package graphics.camera;

import engine.structures.Renderable;
import graphics.camera.projections.Projection;
import graphics.maths.Mat;

public abstract class Camera {

	private Projection projection;

	float tx, ty, tz;
	float rx, ry;

	Camera(float tx, float ty, float tz, float rx, float ry, Projection projection) {
		this.tx = tx;
		this.ty = ty;
		this.tz = tz;
		this.rx = rx;
		this.ry = ry;
		this.projection = projection;
	}

	public Mat getProjectionMatrix() {
		return projection.getProjectionMatrix();
	}

	public Mat getViewMatrix() {
//		return Mat.view(-tx, -ty, -tz, rx, -(ry - 90), rz);
//		return Mat.view(tx, ty, tz, rx, (ry - 90), rz);
		return Mat.view(tx, ty, tz, rx, ry, 0);
	}

	public String toString() {
		return String.format("POS: TX: %3.1f TY: %3.1f TZ: %3.1f;", tx, ty, tz) +
				String.format("ROT: RX: %3.1f RY: %3.1f", rx, ry);
	}

	public abstract boolean canRender(Renderable r);

}
