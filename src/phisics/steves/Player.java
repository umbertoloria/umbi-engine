package phisics.steves;

import engine.events.*;
import engine.mesh.LinearMesh;
import graphics.cosmetics.Color;

public class Player extends Entity {

	public Player() {
		super(LinearMesh.monkey, Color.blue);
	}

	public void onEvent(Event event) {
		EventDispatcher ed = new EventDispatcher(event);
		ed.dispatch(Event.Type.KEY_PRESSED, (e) -> keyPressed((KeyPressed) e));
		ed.dispatch(Event.Type.KEY_RELEASED, (e) -> keyReleased((KeyReleased) e));
		ed.dispatch(Event.Type.CURSOR_MOVED, (e) -> cursorMoved((CursorMoved) e));
	}

	private boolean wBtn, aBtn, sBtn, dBtn;
	private float xmotion, ymotion;

	private boolean keyPressed(KeyPressed e) {
		if (e.getKey().equals("W")) {
			wBtn = true;
		}
		if (e.getKey().equals("A")) {
			aBtn = true;
		}
		if (e.getKey().equals("S")) {
			sBtn = true;
		}
		if (e.getKey().equals("D")) {
			dBtn = true;
		}
		return e.getKey().equals("W") || e.getKey().equals("A") || e.getKey().equals("S") || e.getKey().equals("D");
	}

	private boolean keyReleased(KeyReleased e) {
		if (e.getKey().equals("W")) {
			wBtn = false;
		}
		if (e.getKey().equals("A")) {
			aBtn = false;
		}
		if (e.getKey().equals("S")) {
			sBtn = false;
		}
		if (e.getKey().equals("D")) {
			dBtn = false;
		}
		return e.getKey().equals("W") || e.getKey().equals("A") || e.getKey().equals("S") || e.getKey().equals("D");
	}

	private boolean cursorMoved(CursorMoved e) {
		xmotion += e.getXMotion();
		ymotion += e.getYMotion();
		return true;
	}

	public void onUpdate(float delta) {
		if (xmotion != 0) {
			rotation.y -= xmotion * delta;
			xmotion = 0;
		}
		if (ymotion != 0) {
			rotation.x -= ymotion * delta;
			ymotion = 0;
		}
		if (wBtn ^ sBtn) {
			float forward = 0;
			if (wBtn) {
				forward++;
			}
			if (sBtn) {
				forward--;
			}

			float right = 0;
			if (aBtn) {
				right--;
			}
			if (dBtn) {
				right++;
			}

			if (forward != 0) {
				float distance = 15 * delta;
				float angle;
				if (forward > 0) {
					angle = -90;
				} else {
					angle = 90;
				}
				int direction = (int) -angle / 90;
				if (right < 0) {
					angle += direction * 45;
				} else if (right > 0) {
					angle -= direction * 45;
				}
				position.move(rotation.y + angle, direction * rotation.x, distance);
			}
		}
	}

}
