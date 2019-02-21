package engine.inputs;

import engine.window.Window;

public class Input {

	private Keyboard keyboard;
	private Cursor cursor;
	private Mouse mouse;

	public Input(Window window) {
		keyboard = new Keyboard(window);
		cursor = new Cursor(window);
		mouse = new Mouse(window);
	}

	private boolean enable = false;

	public void enable() {
		enable = true;
	}

	public void newKey(int key, int action) {
		if (enable && key >= 0) {
			keyboard.invoke(key, action);
		}
	}

	public void newPos(double xpos, double ypos) {
		if (enable) {
			cursor.invoke(xpos, ypos);
		}
	}

	public void newMouse(int button, int action) {
		if (enable) {
			mouse.invoke(button, action);
		}
	}

}
