package engine.layer;

import engine.events.Event;
import engine.structures.Renderable;
import engine.structures.Updatable;
import graphics.camera.Camera;
import phisics.En;
import phisics.Light;

import java.util.ArrayList;

public abstract class Layer implements Updatable {

	protected Camera camera;
	protected Light light;
	private ArrayList<Updatable> updatables = new ArrayList<>();
	private ArrayList<Renderable> renderables = new ArrayList<>();

	final void render() {
		for (Renderable renderable : renderables) {
			renderable.onRender(camera, light);
		}
	}

	final void update(float delta) {
		camera.onUpdate(delta);
		for (Updatable updatable : updatables) {
			updatable.onUpdate(delta);
		}
		onUpdate(delta);
	}

	protected final void add(En e) {
		updatables.add(e);
		renderables.add(e);
	}

	protected final void add(Updatable u) {
		updatables.add(u);
	}

	protected final void add(Renderable r) {
		renderables.add(r);
	}

	public void onInit() {
	}

	public void onUpdate(float delta) {
	}

	public void onEvent(Event event) {
	}

}
