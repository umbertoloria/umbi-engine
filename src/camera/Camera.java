package camera;

import inputs.Cursor;
import inputs.Keyboard;
import inputs.Mouse;
import maths.Mat;

public abstract class Camera {

	public abstract Mat getProjectionMatrix();

	public abstract Mat getViewMatrix();

	public abstract void update(float delta, Keyboard k, Cursor c, Mouse m);

}
