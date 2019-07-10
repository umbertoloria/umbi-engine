package graphics.camera;

import engine.GameEngine;
import engine.events.Event;
import graphics.maths.Mat;

public class IdleCamera extends Camera {

	public IdleCamera(float x, float y, float z, float pitch, float yaw) {
		super(Mat.perspective(60, GameEngine.PROPS, Camera.NEAR, Camera.FAR));
		setPosition(x, y, z);
		setRotation(pitch, yaw, 0);
	}

	private static final float ROT_SPEED = 100f;
	private static final float MOV_SPEED = 10f;

	public void manageCamera(float delta) {
		if (upBtn || downBtn || leftBtn || rightBtn || spaceBtn || shiftBtn) {
			// Precessione
			if (upBtn) {
				setRx(getRx() + delta * ROT_SPEED);
			}
			if (downBtn) {
				setRx(getRx() - delta * ROT_SPEED);
			}
			// Nutazione
			if (leftBtn) {
				setRy(getRy() + delta * ROT_SPEED);
			}
			if (rightBtn) {
				setRy(getRy() - delta * ROT_SPEED);
			}
			// Traslazione ordinate
			if (spaceBtn) {
				setPy(getPy() + delta * MOV_SPEED);
			}
			if (shiftBtn) {
				setPy(getPy() - delta * MOV_SPEED);
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

	public void update(float delta) {
	}

}
