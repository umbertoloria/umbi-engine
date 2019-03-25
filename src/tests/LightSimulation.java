package tests;

import engine.Mesh;
import engine.Renderer;
import engine.events.Event;
import engine.layer.Layer;
import engine.shaders.Shader;
import graphics.Color;
import graphics.camera.HatCamera;
import phisics.En3;
import phisics.Light;
import phisics.Player;

import java.util.ArrayList;
import java.util.Random;

public class LightSimulation extends Layer {

	private Random r = new Random();
	private Player scimmia;
	private HatCamera camera;
	private Light light;
	private ArrayList<En3> ents = new ArrayList<>();

	public void onInit() {
		float size = 2;
		// Player
		scimmia = new Player();
		scimmia.setPosition(0, size * 2, -size * 5);
		scimmia.setScale(2);

		// Camera
		camera = new HatCamera(scimmia);

		// Light
		light = new Light(0, 5, -10, Color.white);

		// Generazione paesaggio per 3D
		Color c;
		for (int z = -1; z >= -10; z--) {
			for (int x = 0; x < 50; x++) {
				c = new Color(
						x / 50f,
						x / 50f * (-(z + 1) / 9f) * .8f + .2f * r.nextFloat(),
						-(z + 1) / 9f
				);
				En3 e = new En3(Mesh.cube, c);
				e.setPosition(x * size, 0, z * size);
				e.setScale(size);
				ents.add(e);
			}
		}
	}

	public void onUpdate(float delta) {
		scimmia.update(delta);
		camera.update(delta);
		light.update(delta);
		while (sps > 0) {
			Mesh mmm = r.nextBoolean() ? Mesh.cone : Mesh.sphere;
			En3 a = new En3(mmm, new Color());
			a.setPosition(r.nextInt(100), 0, -r.nextInt(40));
			a.setScale(2);
			ents.add(a);
			sps--;
		}
	}

	public void onEvent(Event event) {
		scimmia.onEvent(event);
		event.onMousePressed("LEFT", () -> sps++);
	}

	private int sps = 0;

	public void onRender(Renderer renderer) {
		renderer.use(camera);
		renderer.use(light);
		renderer.use(Shader.light);
		renderer.draw(scimmia);
		renderer.draw(light);
		for (En3 ent : ents) {
			renderer.draw(ent);
		}
	}

}
