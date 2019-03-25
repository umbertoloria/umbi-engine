package engine.inputs;

import engine.events.KeyPressed;
import engine.events.KeyPressing;
import engine.events.KeyReleased;
import engine.Window;

import java.util.HashMap;

import static org.lwjgl.glfw.GLFW.*;

class Keyboard {

	private Window window;
	private HashMap<String, Integer> keymap = new HashMap<>();
	private boolean[] keys = new boolean[65536];

	Keyboard(Window window) {
		this.window = window;
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

	private String getCode(int key) {
		for (String s : keymap.keySet()) {
			if (keymap.get(s) == key) {
				return s;
			}
		}
		return "";
	}

	void invoke(int key, int action) {
		boolean newstate = action != GLFW_RELEASE;
		if (keys[key] != newstate) {
			// Cambia stato
			if (newstate) {
				// Premuto
				window.pushEvent(new KeyPressed(getCode(key)));
			} else {
				// Rilasciato
				window.pushEvent(new KeyReleased(getCode(key)));
			}
		}
		if (newstate) {
			// Continua ad essere premuto
			window.pushEvent(new KeyPressing(getCode(key)));
		}
		keys[key] = newstate;
	}

}
