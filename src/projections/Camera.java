package projections;

import metrics.Vec2;
import utils.Draw;
import windows.GameWindow;

public class Camera {

	private Vec2 scaling;
	private Vec2 translation;

	public Camera(double zoom) {
		double sx = 2d / (zoom * GameWindow.PROPS);
		double sy = -2d / zoom;
		scaling = new Vec2(sx, sy);
		translation = new Vec2(-zoom / 2);
	}

	public void enable() {
		Draw.pushMatrix();
		Draw.scale(scaling);
		Draw.translate(translation);
	}

	public void disable() {
		Draw.popMatrix();
	}

}
