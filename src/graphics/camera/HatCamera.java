package graphics.camera;

import engine.inputs.Cursor;
import engine.inputs.Keyboard;
import engine.inputs.Mouse;
import engine.structures.Renderable;
import engine.structures.Updatable;
import phisics.steves.Entity;
import graphics.camera.projections.PerspectiveProjection;
import graphics.maths.Vec3;

public class HatCamera extends Camera implements Updatable {

	private Entity e;

	public HatCamera(Entity e) {
		super(0, 0, 0, 0, 0, new PerspectiveProjection(60));
		this.e = e;
	}

	public void update(float delta, Keyboard k, Cursor c, Mouse m) {
		Vec3 pos = e.getPosition();
		tx = pos.x;
		ty = pos.y;
		tz = pos.z;
		rx = e.getPitch();
		ry = e.getYaw();
	}

	public boolean canRender(Renderable r) {
		return r != e;
	}

}
