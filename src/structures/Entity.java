package structures;

import metrics.Vec3;

public abstract class Entity implements Renderable, Updatable {

	private Vec3 pos;
	private double speed, acceleration, direction, radius;

	public void setOrigin(double x, double y, double z) {
		pos = new Vec3(x, y, z);
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public Vec3 getPos() {
		return pos;
	}

	public void setMotoRettilineoUniforme(double speed) {
		setMotoUniformementeAccelerato(speed, 0);
	}

	public void setMotoUniformementeAccelerato(double speed, double acceleration) {
		this.speed = speed;
		this.acceleration = acceleration;
	}

	public void setMotoCircolareUniforme(double speed, double radius) {
		setMotoCircolareUniformementeAccelerato(speed, radius, 0);
	}

	public void setMotoCircolareUniformementeAccelerato(double speed, double radius, double acceleration) {
		this.speed = speed;
		this.radius = radius;
		this.acceleration = acceleration;
	}

	public void update(double delta) {
		speed += acceleration * delta;
		// direction += 360 / (2 * Math.PI * radius / speed);
		pos.move(direction, speed * delta);
	}

}
