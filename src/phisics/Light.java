package phisics;

import engine.Mesh;
import graphics.Color;
import graphics.maths.Vec3;

public class Light extends En3 {

	public Light(float x, float y, float z, Color color) {
		super(Mesh.sphere, color);
		setPosition(x, y, z);
	}

	private int direction = 1;

	public void update(float delta) {
		position = position.add(new Vec3(0, 4 * direction * delta, 0));
		if (position.y > 10 ^ position.y < 3) {
			direction = -direction;
			float acty = position.y;
			acty = Math.min(acty, 10);
			acty = Math.max(acty, 3);
			position = new Vec3(position.x, acty, position.z);
		}
	}

}
