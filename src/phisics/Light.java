package phisics;

import engine.Mesh;
import engine.shaders.Shader;
import graphics.Color;
import graphics.maths.Vec3;

public class Light extends En3 {

	public Light(float x, float y, float z, Color color) {
		super(Mesh.sphere, Shader.light, color);
		setPosition(x, y, z);
	}

	private int direction = 1;

	public void update(float delta) {
		float px = getPosition().x;
		float py = getPosition().y;
		float pz = getPosition().z;
		setPosition(getPosition().add(new Vec3(0, 4 * direction * delta, 0)));
		if (py > 10 ^ py < 3) {
			direction = -direction;
			float acty = py;
			acty = Math.min(acty, 10);
			acty = Math.max(acty, 3);
			setPosition(px, acty, pz);
		}
	}

}
