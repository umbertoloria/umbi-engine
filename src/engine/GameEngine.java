package engine;

import engine.layer.Layer;
import engine.layer.LayerStack;
import engine.mesh.Mesh;
import engine.shaders.Shader;
import engine.window.Window;
import engine.inputs.Cursor;
import engine.inputs.Keyboard;
import engine.inputs.Mouse;
import graphics.models.Model;

public class GameEngine {

	public static final float PROPS = 16f / 9;
	public static final int WIDTH = 2800;
	public static final int HEIGHT = (int) (WIDTH / PROPS);
	private static final boolean FULLSCREEN = false;
	private static final boolean VSYNC = true;
	private static final boolean SHOW_FPS = true;

	private Window window;
	private LayerStack layerStack = new LayerStack();
	private int ups, fps;
	private float lastFPScheck;

	public GameEngine(String title) {
		window = new Window(title, WIDTH, HEIGHT, FULLSCREEN, VSYNC);
	}

	public void add(Layer layer) {
		layerStack.add(layer);
	}

	public void play() {
		window.create();
		Model.loadAll();
		Mesh.loadAll();
		Shader.loadAll();
		layerStack.init();
		loop(window);
		window.terminate();
	}

	private void loop(Window window) {
		float delta, now, lastTime = 0;
		while (window.running() && window.getTime() <= 1) {
			window.flush();
		}
		lastFPScheck = window.getTime();
		while (window.running()) {
			now = window.getTime();
			delta = now - lastTime;
			lastTime = now;
			update(window, delta);
			render(window);
		}
	}

	private void update(Window window, float delta) {
		Keyboard k = window.getKeyboard();
		Cursor c = window.getCursor();
		Mouse m = window.getMouse();
		k.newInput();
		c.newInput();
		m.newInput();
		window.update(k);
		for (Layer layer : layerStack) {
			layer.update(delta, k, c, m);
		}
		ups++;
	}

	private void render(Window window) {
		window.clear();
		for (Layer layer : layerStack) {
			layer.render();
		}
		window.flush();
		fps++;
		if (lastFPScheck + 1 < window.getTime()) {
			lastFPScheck = window.getTime();
			if (SHOW_FPS) {
				System.out.println(ups + " ups, " + fps + " fps.");
			}
			ups = fps = 0;
		}
	}

//	private static final int FPS_MAX = 100;
//	private void the_cherno(Window window) {
//		long lastTime = System.nanoTime();
//		float delta = 0;
//		float ns = 1_000_000_000f / 60;
//		float fakeDeltaTime = 0.016666f;
//		long now;
//		while (window.running()) {
//			// Update
//			now = System.nanoTime();
//			delta += (now - lastTime) / ns;
//			lastTime = now;
//			if (delta >= 1.0) {
//				update(window, fakeDeltaTime);
//				delta--;
//			}
//			// Render
//			render(window);
//		}
//	}
//
//	private void semi_fixed_timestep(Window window) {
//		float dt = 1f / FPS_MAX;
//		float currentTime = window.getTime();
//		float newTime, frameTime;
//		while (window.running()) {
//			// Update
//			newTime = window.getTime();
//			frameTime = newTime - currentTime;
//			currentTime = newTime;
//			while (frameTime > 0.0) {
//				float deltaTime = Math.min(frameTime, dt);
//				update(window, deltaTime);
//				frameTime -= deltaTime;
//			}
//			// Render
//			render(window);
//		}
//	}
//
//	private void free_the_phisics(Window window) {
//		// Fonte: https://www.gafferongames.com/post/fix_your_timestep/
//		float dt = 1f / FPS_MAX;
//		float currentTime = window.getTime();
//		float accumulator = 0;
//		float newTime, frameTime;
//		while (window.running()) {
//			// Update
//			newTime = window.getTime();
//			frameTime = newTime - currentTime;
//			currentTime = newTime;
//			accumulator += frameTime;
//			while (accumulator >= dt) {
//				update(window, dt);
//				accumulator -= dt;
//			}
//			// Render
//			render(window);
//		}
//	}

}