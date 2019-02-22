package graphics.camera;

import engine.structures.Renderable;
import graphics.camera.projections.PerspectiveProjection;
import phisics.En3;

public class HatCamera extends Camera {

	private En3 e;

	public HatCamera(En3 e) {
		super(0, 0, 0, 0, 0, new PerspectiveProjection(100));
		this.e = e;
	}

	public boolean canRender(Renderable r) {
		return r != e;
	}

	public void onUpdate(float delta) {
		tx = e.getPosition().x();
		ty = e.getPosition().y();
		tz = e.getPosition().z();
		rx = e.getPitch();
		ry = e.getYaw();
	}

}
