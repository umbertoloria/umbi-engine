package phisics.steves;

import engine.mesh.IndexedMesh;
import graphics.cosmetics.Color;

import java.util.Random;

public class Light extends Entity {

	public Light(float x, float y, float z, Color color) {
		super(IndexedMesh.sphere, color);
		setPosition(x, y, z);
	}

	private int mov = 1;
	private float speed = 5;
	private Random r = new Random();

	public void onUpdate(float delta) {
		super.onUpdate(delta);
		position.y += speed * mov * delta;
		if (position.y > 10 || position.y < 3) {
			mov = -mov;
		}
		speed += r.nextFloat() * .3f * delta;
		speed = Math.max(speed, 3);
		speed = Math.min(speed, 10);
	}

}
