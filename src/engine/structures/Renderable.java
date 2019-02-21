package engine.structures;

import graphics.camera.Camera;
import phisics.steves.Light;

public interface Renderable {

	void onRender(Camera c, Light l);

}
