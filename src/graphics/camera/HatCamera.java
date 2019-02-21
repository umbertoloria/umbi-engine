package graphics.camera;

import engine.structures.Renderable;
import engine.structures.Updatable;
import graphics.camera.projections.PerspectiveProjection;
import graphics.maths.Vec3;
import phisics.steves.Entity;

public class HatCamera extends Camera {

	private Entity e;

	public HatCamera(Entity e) {
		super(0, 0, 0, 0, 0, new PerspectiveProjection(100));
		this.e = e;
	}

	public boolean canRender(Renderable r) {
		return r != e;
	}

	public void onUpdate(float delta) {
		Vec3 pos = e.getPosition();
		tx = pos.x;
		ty = pos.y;
		tz = pos.z;
		rx = e.getPitch();
		ry = e.getYaw();
	}

}
