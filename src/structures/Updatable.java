package structures;

import inputs.Cursor;
import inputs.Keyboard;
import inputs.Mouse;

public interface Updatable {

	void update(float delta, Keyboard k, Cursor c, Mouse m);

}
