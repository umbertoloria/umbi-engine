package engine.inputs;

import engine.events.MousePressed;
import engine.events.MouseReleased;
import engine.Window;
import org.lwjgl.glfw.GLFW;

class Mouse {

	private Window window;

	Mouse(Window window) {
		this.window = window;
	}

	void invoke(int button, int action) {
		if (button == GLFW.GLFW_MOUSE_BUTTON_1 && action == GLFW.GLFW_PRESS) {
			window.pushEvent(new MousePressed("LEFT"));
		} else {
			window.pushEvent(new MouseReleased("LEFT"));
		}
		if (button == GLFW.GLFW_MOUSE_BUTTON_3 && action == GLFW.GLFW_PRESS) {
			window.pushEvent(new MousePressed("RIGHT"));
		} else {
			window.pushEvent(new MouseReleased("RIGHT"));
		}
	}

}
