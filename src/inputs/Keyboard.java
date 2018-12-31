package inputs;

import engine.window.Window;
import org.lwjgl.glfw.GLFWKeyCallbackI;

import static org.lwjgl.glfw.GLFW.*;

public class Keyboard extends InputIsolator implements GLFWKeyCallbackI {

	private Window window;

	public Keyboard(Window window) {
		this.window = window;
	}

	private boolean[] keys = new boolean[65536];

	public void invoke(long window_id, int key, int scancode, int action, int mods) {
		if (key < 0) {
			return;
		}
		// TODO: Gestire questo evento su 'Window'.
		if (key == GLFW_KEY_ESCAPE) {
			window.close();
		}
		keys[key] = action != GLFW_RELEASE;
	}

	public boolean isKeyDown(int keycode) {
		return keys[keycode];
	}

	public static final int SPACE = GLFW_KEY_SPACE;
	public static final int SHIFT = GLFW_KEY_LEFT_SHIFT;
	public static final int W = GLFW_KEY_W;
	public static final int A = GLFW_KEY_A;
	public static final int S = GLFW_KEY_S;
	public static final int D = GLFW_KEY_D;

}
