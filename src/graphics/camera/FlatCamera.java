package graphics.camera;

import graphics.camera.projections.OrthographicProjection;

public class FlatCamera extends Camera {

//	public FlatCamera(float x, float y, float z, float rx, float ry, float zoom) {
//		super(x, y, z, rx, ry, new OrthographicProjection(zoom));
//	}

	public FlatCamera(float left, float right, float bottom, float top, float near, float far) {
		super(0, 0, 0, 0, 0, new OrthographicProjection(left, right, bottom, top, near, far));
	}

	public void update(float delta) {
	}

}
