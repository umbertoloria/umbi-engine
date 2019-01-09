package tests;

import engine.GameEngine;
import engine.buffers.Mesh;
import engine.inputs.Cursor;
import engine.inputs.Keyboard;
import engine.inputs.Mouse;
import engine.layer.Layer;
import engine.layer.View;
import engine.structures.Updatable;
import graphics.camera.HatCamera;
import graphics.camera.IdleCamera;
import phisics.steves.Entity;
import phisics.steves.Player;

public class SimpleMap extends Layer {

	private IdleCamera ic;

	private View esterna, interna;

	public void init() {

		esterna = new View(0, 0, GameEngine.WIDTH / 2, GameEngine.HEIGHT);
		interna = new View(GameEngine.WIDTH / 2, 0, GameEngine.WIDTH / 2, GameEngine.HEIGHT);

		add(esterna);
		add(interna);

		// Generazione paesaggio per 3D
		float size = 2;
		for (int z = -1; z >= -10; z--) {
			for (int x = 0; x < 50; x++) {
				Entity e = new Entity(Mesh.cube);
				e.setPosition(x * size, 0, z * size);
				e.setScale(size);
				adda(e);
			}
		}

		// Player
		Player scimmia = new Player();
		scimmia.setPosition(0, size * 2, -size * 5);
		scimmia.setScale(2);
		adda(scimmia);

		// Camere
		ic = new IdleCamera(0, size * 2, 0, -10, -20);
		esterna.setCamera(ic);
		HatCamera hc = new HatCamera(scimmia);
		interna.setCamera(hc);
		add(hc);

	}

	private void adda(Entity e) {
		esterna.add(e);
		interna.add(e);
		add((Updatable) e);
	}

	public void update(float delta, Keyboard k, Cursor c, Mouse m) {
		super.update(delta, k, c, m);
		ic.manageCamera(k, delta);
	}

}
