package graphics.camera;

import engine.structures.Renderable;
import engine.structures.Updatable;
import graphics.maths.Mat;
import graphics.maths.Vec3;

public abstract class Camera implements Updatable {

	public static final float NEAR = 0.0001f;
	public static final float FAR = 1000;

	private float px, py, pz, rx, ry, rz;
	private Mat viewMatrix, projectionMatrix, projectionViewMatrix;

	public Camera(Mat projectionMatrix) {
		this.projectionMatrix = projectionMatrix;
		updateMatrices();
	}

	private void updateMatrices() {
		viewMatrix = Mat.view(px, py, pz, rx, ry, rz);
		projectionViewMatrix = projectionMatrix.multiply(viewMatrix);
	}

	public Mat getProjectionMatrix() {
		return projectionMatrix;
	}

	public Mat getViewMatrix() {
		return viewMatrix;
	}

	public Mat getProjectionViewMatrix() {
		return projectionViewMatrix;
	}

	public boolean canRender(Renderable r) {
		return true;
	}

	public String toString() {
		return String.format("POS: TX: %3.1f TY: %3.1f TZ: %3.1f;", px, py, pz) +
				String.format(" ROT: RX: %3.1f RY: %3.1f RZ: %3.1f", rx, ry, rz);
	}

	// UTILS

	public void setPosition(float x, float y, float z) {
		px = x;
		py = y;
		pz = z;
		updateMatrices();
	}

	public void setPosition(Vec3 p) {
		setPosition(p.x, p.y, p.z);
	}

	public void setRotation(float x, float y, float z) {
		rx = x;
		ry = y;
		rz = z;
		updateMatrices();
	}

	public void setRotation(Vec3 r) {
		setRotation(r.x, r.y, r.z);
	}

	public float getPx() {
		return px;
	}

	public void setPx(float px) {
		this.px = px;
	}

	public float getPy() {
		return py;
	}

	public void setPy(float py) {
		this.py = py;
	}

	public float getPz() {
		return pz;
	}

	public void setPz(float pz) {
		this.pz = pz;
	}

	public float getRx() {
		return rx;
	}

	public void setRx(float rx) {
		this.rx = rx;
	}

	public float getRy() {
		return ry;
	}

	public void setRy(float ry) {
		this.ry = ry;
	}

	public float getRz() {
		return rz;
	}

	public void setRz(float rz) {
		this.rz = rz;
	}

}
