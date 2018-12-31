package engine;

import buffers.Mesh;
import camera.Camera;
import engine.window.Window;
import inputs.Cursor;
import inputs.Keyboard;
import inputs.Mouse;
import layer.Layer;
import layer.LayerStack;
import models.Model;
import shaders.Shader;

public abstract class GenericEngine implements Runnable {

	public static final float PROPS = 16f / 9;
	private static final int WIDTH = 2800;
	private static final int HEIGHT = (int) (WIDTH / PROPS);

	private Thread th = new Thread(this);
	private Window window;
	private Camera camera;
	private LayerStack layerStack = new LayerStack();

	public void add(Layer layer) {
		layerStack.pushLayer(layer);
	}

	void render() {
		for (Layer layer : layerStack) {
			layer.render(camera);
		}
	}

	void update(float delta, Keyboard k, Cursor c, Mouse m) {
		camera.update(delta, k, c, m);
		for (Layer layer : layerStack) {
			layer.update(delta, k, c, m);
		}
	}

	GenericEngine(String title, Camera camera, boolean fullscreen, boolean vsync) {
		window = new Window(title, WIDTH, HEIGHT, fullscreen, vsync);
		this.camera = camera;
	}

	public void play() {
		// fixme: il fatto del Mac.
		th.start();
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
