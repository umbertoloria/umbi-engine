package engine;

import camera.Camera;
import engine.window.Window;
import inputs.Cursor;
import inputs.Keyboard;
import inputs.Mouse;

public class GameEngine extends GenericEngine {

	private static boolean FULLSCREEN = false;
	private static boolean VSYNC = true;
	private static boolean SHOW_FPS = false;

	public GameEngine(String title, Camera camera) {
		super(title, camera, FULLSCREEN, VSYNC);
	}

	protected void loop(Window window) {
		// Aspetto un secondo per far assestare il motore
		while (window.running() && window.getTime() <= 1) {
			window.flush();
		}
		// Comincia il Application Loop
		basic_loop(window);
	}

	private void basic_loop(Window window) {
		float lastTime = 0;
		float delta;
		float now;
		while (window.running()) {
			// Update
			now = window.getTime();
			delta = now - lastTime;
			lastTime = now;
			//System.out.println(delta);
			updateAll(window, delta);
			// Render
			renderAll(window);
		}
	}

	private void the_cherno(Window window) {
		long lastTime = System.nanoTime();
		float delta = 0;
		float ns = 1_000_000_000f / 60;
		float fakeDeltaTime = 0.016666f;
		long now;
		while (window.running()) {
			// Update
			now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1.0) {
				updateAll(window, fakeDeltaTime);
				delta--;
			}
			// Render
			renderAll(window);
		}
	}

	private void semi_fixed_timestep(Window window) {
		float dt = 1f / FPS_MAX;
		float currentTime = window.getTime();
		float newTime, frameTime;
		while (window.running()) {
			// Update
			newTime = window.getTime();
			frameTime = newTime - currentTime;
			currentTime = newTime;
			while (frameTime > 0.0) {
				float deltaTime = Math.min(frameTime, dt);
				updateAll(window, deltaTime);
				frameTime -= deltaTime;
			}
			// Render
			renderAll(window);
		}
	}

	private void free_the_phisics(Window window) {
		// Fonte: https://www.gafferongames.com/post/fix_your_timestep/
		float dt = 1f / FPS_MAX;
		float currentTime = window.getTime();
		float accumulator = 0;
		float newTime, frameTime;
		while (window.running()) {
			// Update
			newTime = window.getTime();
			frameTime = newTime - currentTime;
			currentTime = newTime;
			accumulator += frameTime;
			while (accumulator >= dt) {
				updateAll(window, dt);
				accumulator -= dt;
			}
			// Render
			renderAll(window);
		}
	}

	private static final int FPS_MAX = 100;
	private int ups, fps;
	private float lastFPScheck;

	private void updateAll(Window window, float delta) {
		Keyboard k = window.getKeyboard();
		k.newInput();
		Cursor c = window.getCursor();
		c.newInput();
		Mouse m = window.getMouse();
		c.newInput();
		update(delta, k, c, m);
		ups++;
	}

	private void renderAll(Window window) {
		window.clear();
		render();
		window.flush();
		fps++;
		if (lastFPScheck + 1 < window.getTime()) {
			lastFPScheck = window.getTime();
			if (SHOW_FPS) {
				System.out.println(ups + " ups, " + fps + " fps.");
			}
			ups = 0;
			fps = 0;
		}
	}

}