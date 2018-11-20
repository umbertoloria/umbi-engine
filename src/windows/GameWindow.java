package windows;

import lwjgl.LWJGLWindow;
import projections.Camera;
import structures.Entity;
import structures.Renderable;
import structures.Updatable;

import java.util.ArrayList;

public class GameWindow extends LWJGLWindow {

	public static final int WIDTH = 1600;
	public static final double PROPS = 16 / 10d;
	public static final int HEIGHT = (int) (WIDTH / PROPS);
	private ArrayList<Updatable> updatables = new ArrayList<>();
	private ArrayList<Renderable> renderables = new ArrayList<>();

	public GameWindow(String title) {
		super(WIDTH, HEIGHT, title, new Camera(100));
	}

	public void add(Entity e) {
		updatables.add(e);
		renderables.add(e);
	}

	public void add(Updatable u) {
		updatables.add(u);
	}

	public void render() {
		for (Renderable renderable : renderables) {
			renderable.render();
		}
	}

	public void update(double delta) {
		for (Updatable updatable : updatables) {
			updatable.update(delta);
		}
	}

}
