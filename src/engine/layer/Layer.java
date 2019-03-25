package engine.layer;

import engine.Renderer;
import engine.events.Event;
import engine.structures.Updatable;

public abstract class Layer {

	public void onInit() {
	}

	public void onUpdate(float delta) {
	}

	public void onEvent(Event event) {
	}

	public void onRender(Renderer renderer) {
	}

}
