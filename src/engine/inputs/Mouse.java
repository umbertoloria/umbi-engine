package engine.inputs;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;

public class Mouse extends InputIsolator implements GLFWMouseButtonCallbackI {

	private boolean left, right;

	public void invoke(long window, int button, int action, int mods) {
		left = button == GLFW.GLFW_MOUSE_BUTTON_1 && action == GLFW.GLFW_PRESS;
		right = button == GLFW.GLFW_MOUSE_BUTTON_3 && action == GLFW.GLFW_PRESS;
		newInput();
	}

	public boolean leftClicked() {
		return left;
	}

	public boolean rightClicked() {
		return right;
	}

}
