package engine.window;

import engine.inputs.Cursor;
import engine.inputs.Keyboard;
import engine.inputs.Mouse;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
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
	private String title;
	private int width, height;
	private boolean fullscreen, vsync;
	private Keyboard keyboard;
	private Cursor cursor;
	private Mouse mouse;

	public Window(String title, int width, int height, boolean fullscreen, boolean vsync) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.fullscreen = fullscreen;
		this.vsync = vsync;
		this.keyboard = new Keyboard();
		this.mouse = new Mouse();
		this.cursor = new Cursor();
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
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
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

		glfwSetKeyCallback(window, keyboard);
		glfwSetCursorPosCallback(window, cursor);
		glfwSetMouseButtonCallback(window, mouse);

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

		glfwSetWindowSizeCallback(window, new GLFWWindowSizeCallback() {
			public void invoke(long window, int width, int height) {
				// FIXME: Favorire il resize.
				glViewport(0, 0, width, height);
				System.out.println(width);
				System.out.println(height);
				System.out.println();
			}
		});

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

	// Closure
	public void close() {
		glfwSetWindowShouldClose(window, true);
	}

	public void terminate() {
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		glfwTerminate();
	}

	// Inputs
	public Keyboard getKeyboard() {
		return keyboard;
	}

	public Cursor getCursor() {
		return cursor;
	}

	public Mouse getMouse() {
		return mouse;
	}

	public void update(Keyboard k) {
		if (k.isKeyDown("ESCAPE")) {
			close();
		}
	}

}
