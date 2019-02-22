package engine.inputs;

import engine.events.CursorMoved;
import engine.events.CursorPlaced;
import engine.Window;
import graphics.maths.Vec2;

class Cursor {

	void invoke(double dx, double dy) {
		window.pushEvent(new CursorPlaced(dx, dy));
		Vec2 now = new Vec2(dx, dy);
		if (pos != null) {
			Vec2 tmp = now.add(pos.multiply(-1)).multiply(SENSIBILITY);
			window.pushEvent(new CursorMoved(tmp.x(), tmp.y()));
		}
		pos = now;
	}

	private Window window;
	private static final float SENSIBILITY = 4f;
	private Vec2 pos;

	Cursor(Window window) {
		this.window = window;
	}

}
