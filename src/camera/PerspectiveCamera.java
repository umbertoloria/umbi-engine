package camera;

import engine.GenericEngine;
import inputs.Cursor;
import inputs.Keyboard;
import inputs.Mouse;
import maths.Mat;
import maths.Vec3;

public class PerspectiveCamera extends Camera {

	private Mat projectionMatrix = Mat.perspective(75, GenericEngine.PROPS, 0.001f, 1000);
	private Vec3 position = new Vec3(0, 0, 0);
	private float pitch, yaw;

	public Mat getProjectionMatrix() {
		return projectionMatrix;
	}

	public Mat getViewMatrix() {
		return Mat.view(position, pitch, yaw);
	}

	public void update(float delta, Keyboard k, Cursor c, Mouse m) {
		if (k.canTakeInput()) {
			k.inputTaken(keyboardInput(k, delta));
		}
		if (c.canTakeInput()) {
			c.inputTaken(cursorInput(c, delta));
		}
	}

	private static final float SENSIBILITY = 2;

	private boolean cursorInput(Cursor c, float delta) {
		float xmotion = c.getXMotion() * SENSIBILITY;
		float ymotion = c.getYMotion() * SENSIBILITY;
		if (xmotion == 0 && ymotion == 0) {
			return false;
		}
		// Giro verso destra
		yaw = (360 + yaw + xmotion * delta) % 360;
		// Giro verso l'alto
		float newPitch = pitch - ymotion * delta;
		if (0 <= newPitch && newPitch < 180) {
			pitch = (360 + newPitch) % 360;
		}
		return true;
	}

	private static final float SPEED = 40;

	private boolean keyboardInput(Keyboard k, float delta) {
		if (!k.isKeyDown(Keyboard.W) && !k.isKeyDown(Keyboard.A) &&
				!k.isKeyDown(Keyboard.S) && !k.isKeyDown(Keyboard.D) &&
				!k.isKeyDown(Keyboard.SHIFT) && !k.isKeyDown(Keyboard.SPACE)) {
			return false;
		}

		// Movimento in avanti
		float forward = 0;
		if (k.isKeyDown(Keyboard.W)) {
			forward += SPEED * delta;
		}
		if (k.isKeyDown(Keyboard.S)) {
			forward -= SPEED * delta;
		}
		position.applyYaw(yaw - 90, forward);

		// Movimento laterale
		float right = 0;
		if (k.isKeyDown(Keyboard.D)) {
			right += SPEED * delta;
		}
		if (k.isKeyDown(Keyboard.A)) {
			right -= SPEED * delta;
		}
		position.applyYaw(yaw, right);

		// Movimento in alto
		float stairs = 0;
		if (k.isKeyDown(Keyboard.SPACE)) {
			stairs += SPEED * delta;
		}
		if (k.isKeyDown(Keyboard.SHIFT)) {
			stairs -= SPEED * delta;
		}
		position.add(0, stairs, 0);
		return true;
	}

}
