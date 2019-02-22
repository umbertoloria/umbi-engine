package graphics.camera;

import graphics.camera.projections.OrthographicProjection;

public class FlatCamera extends Camera {

	public FlatCamera(float x, float y, float z, float rx, float ry, float zoom) {
		super(x, y, z, rx, ry, new OrthographicProjection(zoom));
	}

	public void onUpdate(float delta) {

	}

}
