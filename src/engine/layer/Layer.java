package engine.layer;

import engine.inputs.Cursor;
import engine.inputs.Keyboard;
import engine.inputs.Mouse;
import engine.structures.Renderable;
import engine.structures.Updatable;
import graphics.camera.IdleCamera;
import phisics.steves.Entity;
import phisics.steves.Light;

import java.util.ArrayList;

public abstract class Layer {

	protected IdleCamera camera;
	protected Light light;
	private ArrayList<Updatable> updatables = new ArrayList<>();
	private ArrayList<Renderable> renderables = new ArrayList<>();

	public abstract void init();

	// protected <T extends Renderable & Updatable> void add(T e) {
	protected void add(Entity e) {
		updatables.add(e);
		renderables.add(e);
	}

	protected void add(Updatable u) {
		updatables.add(u);
	}

	protected void add(Renderable r) {
		renderables.add(r);
	}

	public void render() {
		for (Renderable renderable : renderables) {
			renderable.render(camera, light);
		}
	}

	public void update(float delta, Keyboard k, Cursor c, Mouse m) {
		for (Updatable updatable : updatables) {
			updatable.update(delta, k, c, m);
		}
	}

}
