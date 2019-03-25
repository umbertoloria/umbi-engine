package engine.layer;

import engine.Renderer;
import engine.events.Event;

import java.util.ArrayList;

public class LayerStack {

	private ArrayList<Layer> layers = new ArrayList<>();

	public void add(Layer layer) {
		layers.add(layer);
	}

	public void init() {
		for (Layer layer : layers) {
			layer.onInit();
		}
	}

	public void update(float delta) {
		for (Layer layer : layers) {
			layer.onUpdate(delta);
		}
	}

	public void render(Renderer renderer) {
		for (Layer layer : layers) {
			layer.onRender(renderer);
		}
	}

	public void pushEvent(Event event) {
		for (int i = layers.size() - 1; i >= 0; i--) {
			layers.get(i).onEvent(event);
		}
	}

}
