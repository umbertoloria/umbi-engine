package engine.structures;

import engine.inputs.Cursor;
import engine.inputs.Keyboard;
import engine.inputs.Mouse;

public interface Updatable {

	void update(float delta, Keyboard k, Cursor c, Mouse m);

}
