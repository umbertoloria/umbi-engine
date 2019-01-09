package phisics.steves;

import engine.buffers.Mesh;
import engine.inputs.Cursor;
import engine.inputs.Keyboard;
import engine.inputs.Mouse;

public class Player extends Entity {

	public Player() {
		super(Mesh.monkey);
	}

	public void update(float delta, Keyboard k, Cursor c, Mouse m) {
		if (k.canTakeInput()) {
			k.inputTaken(keyboardInput(k, delta));
		}
		if (c.canTakeInput()) {
			c.inputTaken(cursorInput(c, delta));
		}
	}

	private boolean cursorInput(Cursor c, float delta) {
		float xmotion = c.getXMotion();
		float ymotion = c.getYMotion();
		if (xmotion == 0 && ymotion == 0) {
			return false;
		}
		if (xmotion != 0) {
			rotation.y -= xmotion * delta;
		}
		if (ymotion != 0) {
			rotation.x -= ymotion * delta;
		}
		return true;
	}

	private static final float SPEED = 20;

	private boolean keyboardInput(Keyboard k, float delta) {
		if (!k.isKeyDown("W") && !k.isKeyDown("S") &&
				!k.isKeyDown("A") && !k.isKeyDown("D")) {
			return false;
		}

		float forward = 0;
		if (k.isKeyDown("W")) {
			forward++;
		}
		if (k.isKeyDown("S")) {
			forward--;
		}

		float right = 0;
		if (k.isKeyDown("A")) {
			right--;
		}
		if (k.isKeyDown("D")) {
			right++;
		}

		float distance = SPEED * delta;

		float angle = Float.NaN;

		if (forward > 0) {
			angle = -90;
			if (right < 0) {
				angle -= 45;
			} else if (right > 0) {
				angle += 45;
			}
		} else if (forward < 0) {
			angle = 90;
			if (right < 0) {
				angle += 45;
			} else if (right > 0) {
				angle -= 45;
			}
		}

		if (!Float.isNaN(angle)) {
			position.move(rotation.y + angle, rotation.x, distance);
		}
		return true;
	}

}
