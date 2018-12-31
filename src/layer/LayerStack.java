package layer;

import java.util.ArrayList;
import java.util.Iterator;

public class LayerStack implements Iterable<Layer> {

	private ArrayList<Layer> layers = new ArrayList<>();

	public void init () {
		for (Layer layer : layers) {
			layer.init();
		}
	}

	public void pushLayer(Layer layer) {
		layers.add(layer);
	}

	public void popLayer(Layer layer) {
		layers.remove(layer);
	}

	public Iterator<Layer> iterator() {
		return layers.iterator();
	}

}
