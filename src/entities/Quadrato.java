package entities;

import metrics.Vec2;
import structures.Entity;
import utils.Color;
import utils.Draw;

public class Quadrato extends Entity {

	private Vec2 size = new Vec2(10, 10);
	private Color cl = new Color(1, .4f, .1f);

	public Quadrato(double x, double y) {
		setOrigin(x, y);
		setDirection(270);
		setMotoUniformementeAccelerato(10, 10);
	}

	public void render() {
		Draw.quad(getPos().x(), getPos().y(), size.x(), size.y(), cl);
	}

	public void update(double delta) {
		super.update(delta);
	}

}
