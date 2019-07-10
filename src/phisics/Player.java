package phisics;

import engine.Mesh;
import engine.events.Event;
import engine.shaders.Shader;
import graphics.Color;
import graphics.maths.Vec3;

public class Player extends En3 {

	public Player() {
		super(Mesh.monkey, Shader.light, Color.blue);
	}

	private boolean wBtn, aBtn, sBtn, dBtn;
	private float xmotion, ymotion;
	private static float SENSIBILITY = 4;

	public void onEvent(Event event) {
		event.onKeyNewState("W", (state) -> wBtn = state);
		event.onKeyNewState("A", (state) -> aBtn = state);
		event.onKeyNewState("S", (state) -> sBtn = state);
		event.onKeyNewState("D", (state) -> dBtn = state);
		event.onCursorMoved((xmo, ymo) -> {
			xmotion += xmo * SENSIBILITY;
			ymotion += ymo * SENSIBILITY;
		});
	}

	public void update(float delta) {
		if (xmotion != 0) {
			setRotation(getRotation().add(new Vec3(0, -xmotion * delta, 0)));
			xmotion = 0;
		}
		if (ymotion != 0) {
			setRotation(getRotation().add(new Vec3(-ymotion * delta, 0, 0)));
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
				setPosition(getPosition().move(getRotation().y + angle, direction * getRotation().x, distance));
			}
		}
	}

}
