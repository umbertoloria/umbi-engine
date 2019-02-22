package graphics.camera;

import engine.events.Event;
import graphics.camera.projections.PerspectiveProjection;

public class IdleCamera extends Camera {

	public IdleCamera(float x, float y, float z, float pitch, float yaw) {
		super(x, y, z, pitch, yaw, new PerspectiveProjection(60));
	}

	private static final float ROT_SPEED = 100f;
	private static final float MOV_SPEED = 10f;

	public void manageCamera(float delta) {
		if (upBtn || downBtn || leftBtn || rightBtn || spaceBtn || shiftBtn) {
			// Precessione
			if (upBtn) {
				rx += delta * ROT_SPEED;
			}
			if (downBtn) {
				rx -= delta * ROT_SPEED;
			}
			// Nutazione
			if (leftBtn) {
				ry += delta * ROT_SPEED;
			}
			if (rightBtn) {
				ry -= delta * ROT_SPEED;
			}
			// Traslazione ordinate
			if (spaceBtn) {
				ty += delta * MOV_SPEED;
			}
			if (shiftBtn) {
				ty -= delta * MOV_SPEED;
			}
		}
	}

	private boolean upBtn, downBtn, leftBtn, rightBtn, spaceBtn, shiftBtn;

	public void onEvent(Event event) {
		event.onKeyNewState("UP", (state) -> upBtn = state);
		event.onKeyNewState("DOWN", (state) -> downBtn = state);
		event.onKeyNewState("LEFT", (state) -> leftBtn = state);
		event.onKeyNewState("RIGHT", (state) -> rightBtn = state);
		event.onKeyNewState("SPACE", (state) -> spaceBtn = state);
		event.onKeyNewState("SHIFT", (state) -> shiftBtn = state);
	}

	public void onUpdate(float delta) {
	}

}
