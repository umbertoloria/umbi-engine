package engine.inputs;

import engine.events.CursorMoved;
import engine.events.CursorPlaced;
import engine.window.Window;
import graphics.maths.Vec2;

class Cursor {

	void invoke(double dx, double dy) {
		window.pushEvent(new CursorPlaced(dx, dy));
		Vec2 now = new Vec2(dx, dy);
		if (pos == null) {
			pos = new Vec2(now);
		} else {
			window.pushEvent(new CursorMoved((now.x - pos.x) * SENSIBILITY, (now.y - pos.y) * SENSIBILITY));
		}
		pos.place(now);
	}

	private Window window;
	private static final float SENSIBILITY = 4f;
	private Vec2 pos;

	Cursor(Window window) {
		this.window = window;
	}

}
