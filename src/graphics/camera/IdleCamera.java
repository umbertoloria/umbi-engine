package graphics.camera;

import engine.inputs.Keyboard;
import engine.structures.Renderable;
import graphics.camera.projections.PerspectiveProjection;

public class IdleCamera extends Camera {

	public IdleCamera(float x, float y, float z, float pitch, float yaw) {
		super(x, y, z, pitch, yaw, new PerspectiveProjection(60));
	}

	public boolean canRender(Renderable r) {
		return true;
	}

	private static final float ROT_SPEED = 100f;
	private static final float MOV_SPEED = 10f;

	public void manageCamera(Keyboard k, float delta) {
		if (!k.isKeyDown("UP") && !k.isKeyDown("DOWN") &&
				!k.isKeyDown("LEFT") && !k.isKeyDown("RIGHT") &&
				!k.isKeyDown("SPACE") && !k.isKeyDown("SHIFT")) {
			return;
		}
		// Pitch
		if (k.isKeyDown("UP")) {
			rx += delta * ROT_SPEED;
		}
		if (k.isKeyDown("DOWN")) {
			rx -= delta * ROT_SPEED;
		}
		// Yaw
		if (k.isKeyDown("LEFT")) {
			ry += delta * ROT_SPEED;
		}
		if (k.isKeyDown("RIGHT")) {
			ry -= delta * ROT_SPEED;
		}
		// Y-axis
		if (k.isKeyDown("SPACE")) {
			ty += delta * MOV_SPEED;
		}
		if (k.isKeyDown("SHIFT")) {
			ty -= delta * MOV_SPEED;
		}
	}

}
