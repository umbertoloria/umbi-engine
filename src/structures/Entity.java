package structures;

import metrics.Vec2;

public abstract class Entity implements Renderable, Updatable {

	private Vec2 pos;
	private double speed = 1;
	private double accelation = 0;
	private double direction = 0;
	private double radius = 0;

	protected void setOrigin(double x, double y) {
		pos = new Vec2(x, y);
	}

	protected void setDirection(double direction) {
		this.direction = direction;
	}

	protected Vec2 getPos() {
		return pos;
	}

	public void setMotoRettilineoUniforme(double speed) {
		setMotoUniformementeAccelerato(speed, 0);
	}

	public void setMotoUniformementeAccelerato(double speed, double accelerato) {
		this.speed = speed;
		this.accelation = accelerato;
	}

	public void setMotoCircolareUniforme(double speed, double radius) {
		setMotoCircolareUniformementeAccelerato(speed, radius, 0);
	}

	public void setMotoCircolareUniformementeAccelerato(double speed, double radius, double acceleration) {
		this.speed = speed;
		this.radius = radius;
		this.accelation = acceleration;
	}

	public void update(double delta) {
		speed += accelation * delta;
		// direction += 360 / (2 * Math.PI * radius / speed);
		pos.move(direction, speed * delta);
	}

}
