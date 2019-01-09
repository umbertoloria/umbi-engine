package engine;

import engine.window.Window;
import engine.inputs.Cursor;
import engine.inputs.Keyboard;
import engine.inputs.Mouse;

public class GameEngine extends GenericEngine {

	private static final boolean FULLSCREEN = false;
	private static final boolean VSYNC = true;

	private int ups, fps;
	private float lastFPScheck;
	private boolean showFps;

	public GameEngine(String title) {
		super(title, FULLSCREEN, VSYNC);
		showFps = false;
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
			updateAll(window, delta);
			// Render
			renderAll(window);
		}
	}

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
			if (showFps) {
				System.out.println(ups + " ups, " + fps + " fps.");
			}
			ups = 0;
			fps = 0;
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
//				updateAll(window, fakeDeltaTime);
//				delta--;
//			}
//			// Render
//			renderAll(window);
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
//				updateAll(window, deltaTime);
//				frameTime -= deltaTime;
//			}
//			// Render
//			renderAll(window);
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
//				updateAll(window, dt);
//				accumulator -= dt;
//			}
//			// Render
//			renderAll(window);
//		}
//	}

}