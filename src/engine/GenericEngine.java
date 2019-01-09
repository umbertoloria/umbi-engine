package engine;

import engine.buffers.Mesh;
import engine.inputs.Cursor;
import engine.inputs.Keyboard;
import engine.inputs.Mouse;
import engine.layer.Layer;
import engine.layer.LayerStack;
import engine.shaders.Shader;
import engine.window.Window;
import graphics.models.Model;

public abstract class GenericEngine implements Runnable {

	public static final float PROPS = 16f / 9;
	public static final int WIDTH = 2800;
	public static final int HEIGHT = (int) (WIDTH / PROPS);

	private Thread th = new Thread(this);
	private Window window;
	private LayerStack layerStack = new LayerStack();

	public void add(Layer layer) {
		layerStack.add(layer);
	}

	void render() {
		for (Layer layer : layerStack) {
			layer.render();
		}
	}

	void update(float delta, Keyboard k, Cursor c, Mouse m) {
		window.update(k);
		for (Layer layer : layerStack) {
			layer.update(delta, k, c, m);
		}
	}

	GenericEngine(String title, boolean fullscreen, boolean vsync) {
		window = new Window(title, WIDTH, HEIGHT, fullscreen, vsync);
	}

	public void play() {
		if (System.getProperty("os.name").contains("Mac")) {
			th.run();
		} else {
			th.start();
		}
	}

	public void run() {
		window.create();
		// Init
		Model.loadAll();
		Mesh.loadAll();
		Shader.loadAll();
		layerStack.init();
		// Loop
		loop(window);
		// Close
		window.terminate();
	}

	protected abstract void loop(Window window);

}
