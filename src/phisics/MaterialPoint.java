package phisics;

import inputs.Cursor;
import inputs.Keyboard;
import inputs.Mouse;
import maths.Vec3;
import structures.Updatable;

public abstract class MaterialPoint implements Updatable {

	private Vec3 position = new Vec3(0, 0, 0);
	private float speed, acceleration, direction;

	public void setPosition(float x, float y, float z) {
		position.place(x, y, z);
	}

	public void setDirection(float direction) {
		this.direction = direction;
	}

	public Vec3 getPosition() {
		return position;
	}

	public void setMotoUniforme(float speed) {
		setMotoUniformementeAccelerato(speed, 0);
	}

	public void setMotoUniformementeAccelerato(float speed, float acceleration) {
		this.speed = speed;
		this.acceleration = acceleration;
	}

	public void update(float delta, Keyboard k, Cursor c, Mouse m) {
		speed += acceleration * delta;
		position.applyYaw(direction, speed * delta);
	}

}
