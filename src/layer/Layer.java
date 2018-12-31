package layer;

import camera.Camera;
import graphics.Entity;
import inputs.Cursor;
import inputs.Keyboard;
import inputs.Mouse;
import structures.Renderable;
import structures.Updatable;

import java.util.ArrayList;

// TODO: Portare la camera nel layer.
public abstract class Layer implements Renderable, Updatable {

	private ArrayList<Updatable> updatables = new ArrayList<>();
	private ArrayList<Renderable> renderables = new ArrayList<>();

	public abstract void init();

	protected void add(Entity e) {
		updatables.add(e);
		renderables.add(e);
	}

	public void render(Camera c) {
		for (Renderable renderable : renderables) {
			renderable.render(c);
		}
	}

	public void update(float delta, Keyboard k, Cursor c, Mouse m) {
		for (Updatable updatable : updatables) {
			updatable.update(delta, k, c, m);
		}
	}

}
