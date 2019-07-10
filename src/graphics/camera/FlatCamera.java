package graphics.camera;

import graphics.maths.Mat;

public class FlatCamera extends Camera {

	public FlatCamera(float left, float right, float bottom, float top, float near, float far) {
		super(Mat.orthographic(left, right, bottom, top, near, far));
	}

	public void update(float delta) {
	}

}
