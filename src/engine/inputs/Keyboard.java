package engine.inputs;

import org.lwjgl.glfw.GLFWKeyCallbackI;

import java.util.HashMap;

import static org.lwjgl.glfw.GLFW.*;

public class Keyboard extends InputIsolator implements GLFWKeyCallbackI {

	private HashMap<String, Integer> keymap = new HashMap<>();
	private boolean[] keys = new boolean[65536];

//	public static final int SPACE = GLFW_KEY_SPACE;
//	public static final int SHIFT = GLFW_KEY_LEFT_SHIFT;
//	public static final int W = GLFW_KEY_W;
//	public static final int A = GLFW_KEY_A;
//	public static final int S = GLFW_KEY_S;
//	public static final int D = GLFW_KEY_D;
//	public static final int UP = GLFW_KEY_UP;
//	public static final int DOWN = GLFW_KEY_DOWN;
//	public static final int LEFT = GLFW_KEY_LEFT;
//	public static final int RIGHT = GLFW_KEY_RIGHT;
//	public static final int ESCAPE = GLFW_KEY_ESCAPE;

	public Keyboard() {
		keymap.put("SPACE", GLFW_KEY_SPACE);
		keymap.put("SHIFT", GLFW_KEY_LEFT_SHIFT);
		keymap.put("W", GLFW_KEY_W);
		keymap.put("A", GLFW_KEY_A);
		keymap.put("S", GLFW_KEY_S);
		keymap.put("D", GLFW_KEY_D);
		keymap.put("UP", GLFW_KEY_UP);
		keymap.put("DOWN", GLFW_KEY_DOWN);
		keymap.put("LEFT", GLFW_KEY_LEFT);
		keymap.put("RIGHT", GLFW_KEY_RIGHT);
		keymap.put("ESCAPE", GLFW_KEY_ESCAPE);

	}

	public void invoke(long window_id, int key, int scancode, int action, int mods) {
		if (key < 0) {
			return;
		}
		keys[key] = action != GLFW_RELEASE;
	}

	public boolean isKeyDown(String key) {
		return keys[keymap.get(key)];
	}

}
