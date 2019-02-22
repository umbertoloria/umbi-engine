package phisics;

import engine.Mesh;
import engine.events.*;
import graphics.Color;
import graphics.maths.Vec3;

public class Player extends En3 {

	public Player() {
		super(Mesh.monkey, Color.blue);
	}

	private boolean wBtn, aBtn, sBtn, dBtn;
	private float xmotion, ymotion;

	public void onEvent(Event event) {
		event.onKeyNewState("W", (state) -> wBtn = state);
		event.onKeyNewState("A", (state) -> aBtn = state);
		event.onKeyNewState("S", (state) -> sBtn = state);
		event.onKeyNewState("D", (state) -> dBtn = state);
		event.onCursorMoved((xmo, ymo) -> {
			xmotion += xmo;
			ymotion += ymo;
		});
	}

	public void onUpdate(float delta) {
		if (xmotion != 0) {
			rotation = rotation.add(new Vec3(0, -xmotion * delta, 0));
			xmotion = 0;
		}
		if (ymotion != 0) {
			rotation = rotation.add(new Vec3(-ymotion * delta, 0, 0));
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
				position = position.move(rotation.y() + angle, direction * rotation.x(), distance);
			}
		}
	}

}
