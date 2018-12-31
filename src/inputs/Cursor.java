package inputs;

import engine.window.Window;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;

public class Cursor extends InputIsolator implements GLFWCursorPosCallbackI {

	private Window window;

	public Cursor(Window window) {
		this.window = window;
	}

	public void invoke(long window, double dx, double dy) {
		int x = (int) dx;
		int y = (int) dy;
		xmotion = (x - cx) * SENSIBILITY;
		ymotion = (y - cy) * SENSIBILITY;
		cx = x;
		cy = y;
		newInput();
	}

	private static final float SENSIBILITY = 5f;
	private int cx, cy;

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
