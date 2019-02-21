package graphics.camera;

import engine.events.Event;
import engine.events.EventDispatcher;
import engine.events.KeyPressed;
import engine.events.KeyReleased;
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

	public void onEvent(Event event) {
		EventDispatcher ed = new EventDispatcher(event);
		ed.dispatch(Event.Type.KEY_RELEASED, (e) -> keyReleased((KeyReleased) e));
		ed.dispatch(Event.Type.KEY_PRESSED, (e) -> keyPressed((KeyPressed) e));
	}

	private boolean upBtn, downBtn, leftBtn, rightBtn, spaceBtn, shiftBtn;

	private boolean keyPressed(KeyPressed e) {
		if (e.getKey().equals("UP")) upBtn = true;
		if (e.getKey().equals("DOWN")) downBtn = true;
		if (e.getKey().equals("LEFT")) leftBtn = true;
		if (e.getKey().equals("RIGHT")) rightBtn = true;
		if (e.getKey().equals("SPACE")) spaceBtn = true;
		if (e.getKey().equals("SHIFT")) shiftBtn = true;
		return e.getKey().equals("UP") || e.getKey().equals("DOWN") || e.getKey().equals("LEFT") ||
				e.getKey().equals("RIGHT") || e.getKey().equals("SPACE") || e.getKey().equals("SHIFT");
	}

	private boolean keyReleased(KeyReleased e) {
		if (e.getKey().equals("UP")) upBtn = false;
		if (e.getKey().equals("DOWN")) downBtn = false;
		if (e.getKey().equals("LEFT")) leftBtn = false;
		if (e.getKey().equals("RIGHT")) rightBtn = false;
		if (e.getKey().equals("SPACE")) spaceBtn = false;
		if (e.getKey().equals("SHIFT")) shiftBtn = false;
		return e.getKey().equals("RIGHT") || e.getKey().equals("SPACE") || e.getKey().equals("SHIFT") ||
				e.getKey().equals("UP") || e.getKey().equals("DOWN") || e.getKey().equals("LEFT");
	}

	public void onUpdate(float delta) {
	}

}
