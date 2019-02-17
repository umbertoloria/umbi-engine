package engine.inputs;

import graphics.maths.Vec2;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;

public class Cursor extends InputDispatcher implements GLFWCursorPosCallbackI {

	public void invoke(long window, double dx, double dy) {
		Vec2 now = new Vec2(dx, dy);
		if (pos == null) {
			pos = new Vec2(now);
		} else {
			xmotion = (now.x - pos.x) * SENSIBILITY;
			ymotion = (now.y - pos.y) * SENSIBILITY;
		}
		pos.place(now);
		newInput();
	}

	private static final float SENSIBILITY = 5f;
	private Vec2 pos;

	private float xmotion, ymotion;

	public float getXMotion() {
		float val = xmotion;
		xmotion = 0;
		return val;
	}

	public float getYMotion() {
		float val = ymotion;
		ymotion = 0;
		return val;
	}

}
