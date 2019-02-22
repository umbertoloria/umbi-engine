package engine.structures;

import graphics.camera.Camera;
import phisics.Light;

public interface Renderable {

	void onRender(Camera camera, Light light);

}
