package graphics.camera.projections;

import graphics.maths.Mat;

public abstract class Projection {

	static final float NEAR = 0.0001f;
	static final float FAR = 1000;
	private Mat projectionMatrix;

	Projection(Mat projectionMatrix) {
		this.projectionMatrix = projectionMatrix;
	}

	public Mat getProjectionMatrix() {
		return projectionMatrix;
	}

}
