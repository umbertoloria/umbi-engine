package tests;

import engine.events.Event;
import engine.events.EventDispatcher;
import engine.events.MousePressed;
import engine.layer.Layer;
import engine.mesh.Mesh;
import graphics.camera.HatCamera;
import graphics.cosmetics.Color;
import phisics.steves.Entity;
import phisics.steves.Light;
import phisics.steves.Player;

import java.util.Random;

public class SimpleMap extends Layer {

	private Random r = new Random();
	private Player scimmia;

	public void onInit() {

		float size = 2;
		// Player
		scimmia = new Player();
		scimmia.setPosition(0, size * 2, -size * 5);
		scimmia.setScale(2);
		add(scimmia);

		// Camere
		camera = new HatCamera(scimmia);
		add(camera);

		// Light
		light = new Light(0, 5, -10, Color.white);
		add(light);

		// Generazione paesaggio per 3D
		Color c;
		for (int z = -1; z >= -10; z--) {
			for (int x = 0; x < 50; x++) {
				c = new Color(
						x / 50f,
						x / 50f * (-(z + 1) / 9f) * .8f + .2f * r.nextFloat(),
						-(z + 1) / 9f
				);
				Entity e = new Entity(Mesh.cube, c);
				e.setPosition(x * size, 0, z * size);
				e.setScale(size);
				add(e);
			}
		}
	}

	public void onUpdate(float delta) {
		while (sps > 0) {
			Mesh mmm = r.nextBoolean() ? Mesh.cone : Mesh.sphere;
			Entity a = new Entity(mmm, new Color());
			a.setPosition(r.nextInt(100), 0, -r.nextInt(40));
			a.setScale(2);
			add(a);
			sps--;
		}
	}

	public void onEvent(Event event) {
		scimmia.onEvent(event);
		EventDispatcher ed = new EventDispatcher(event);
		ed.dispatch(Event.Type.MOUSE_PRESSED, (e) -> mousePressed((MousePressed) e));
	}

	private int sps = 0;

	private boolean mousePressed(MousePressed e) {
		if (e.getButton().equals("LEFT")) {
			sps++;
		}
		return e.getButton().equals("LEFT");
	}

}
