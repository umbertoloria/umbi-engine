package windows;

import lwjgl.LWJGLWindow;
import structures.Entity;
import structures.Inputable;
import structures.Renderable;
import structures.Updatable;

import java.util.ArrayList;

public class GameWindow extends LWJGLWindow {

	public static final double PROPS = 16 / 10d;
	private static final int WIDTH = 2800;
	private static final int HEIGHT = (int) (WIDTH / PROPS);
	private ArrayList<Updatable> updatables = new ArrayList<>();
	private ArrayList<Renderable> renderables = new ArrayList<>();
	private ArrayList<Inputable> inputables = new ArrayList<>();

	public GameWindow(String title) {
		super(WIDTH, HEIGHT, title);
	}

	public void add(Entity e) {
		updatables.add(e);
		renderables.add(e);
	}

	public void add(Inputable i) {
		inputables.add(i);
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

	public void keyboardInput(int key, int action) {
		for (Inputable inputable : inputables) {
			inputable.keyboardInput(key, action);
		}
	}

	private double lx = -1, ly = -1;

	public void mouseInput(double xpos, double ypos) {
		if (lx == -1) {
			lx = xpos;
		}
		if (ly == -1) {
			ly = ypos;
		}
		for (Inputable inputable : inputables) {
			inputable.mouseInput(xpos - lx, ypos - ly);
		}
		lx = xpos;
		ly = ypos;
	}
}
