package engine.window;

import engine.GameEngine;
import engine.inputs.Input;
import engine.events.Event;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

	private long window;
	private GameEngine gameEngine;
	private String title;
	private int width, height;
	private boolean fullscreen, vsync;
	private Input input;

	public Window(GameEngine gameEngine, String title, int width, int height, boolean fullscreen, boolean vsync) {
		this.gameEngine = gameEngine;
		this.title = title;
		this.width = width;
		this.height = height;
		this.fullscreen = fullscreen;
		this.vsync = vsync;
		this.input = new Input(this);
	}

	// Create
	public void create() {
		GLFWErrorCallback.createPrint(System.err).set();
		if (!glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}
		glfwDefaultWindowHints();
		// Finestra
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		// Antialiasing
		glfwWindowHint(GLFW_STENCIL_BITS, 8);
		glfwWindowHint(GLFW_SAMPLES, 8);
		// Compatibilità OSX
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
		if (fullscreen) {
			window = glfwCreateWindow(width, height, title, glfwGetPrimaryMonitor(), NULL);
		} else {
			window = glfwCreateWindow(width, height, title, NULL, NULL);
		}
		if (window == NULL) {
			throw new RuntimeException("Failed to create the GLFW window");
		}
		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);
			glfwGetWindowSize(window, pWidth, pHeight);
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			if (vidmode == null) {
				throw new RuntimeException("Failed to get the Video Mode");
			}
			glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
		}

		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> input.newKey(key, action));
		glfwSetCursorPosCallback(window, (window, xpos, ypos) -> input.newPos(xpos, ypos));
		glfwSetMouseButtonCallback(window, (window, button, action, mods) -> input.newMouse(button, action));

		glfwMakeContextCurrent(window);
		glfwSwapInterval(vsync ? 1 : 0);
		glfwShowWindow(window);
		GL.createCapabilities();

		glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

		glEnable(GL_DEPTH_TEST);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
//		glEnable(GL_LIGHT0);
//		glActiveTexture(GL_TEXTURE1);
//		glCullFace(GL_BACK);
//		glEnable(GL_CULL_FACE);
		glClearColor(.3f, .3f, .3f, 1f);
	}

	// Loop
	public boolean running() {
		return !glfwWindowShouldClose(window);
	}

	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public float getTime() {
		return (float) glfwGetTime();
	}

	public void flush() {
		glfwSwapBuffers(window);
		glfwPollEvents();
	}

	public void close() {
		glfwSetWindowShouldClose(window, true);
	}

	public void terminate() {
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		glfwTerminate();
	}

	public void pushEvent(Event event) {
		gameEngine.pushEvent(event);
	}

	public void enableInputs() {
		input.enable();
	}
}
