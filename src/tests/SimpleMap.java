package tests;

import engine.GameEngine;
import engine.mesh.IndexedMesh;
import engine.inputs.Cursor;
import engine.inputs.Keyboard;
import engine.inputs.Mouse;
import engine.layer.Layer;
import engine.layer.View;
import engine.structures.Updatable;
import graphics.camera.HatCamera;
import graphics.camera.IdleCamera;
import graphics.cosmetics.Color;
import phisics.steves.Entity;
import phisics.steves.Light;
import phisics.steves.Player;

import java.util.Random;

public class SimpleMap extends Layer {

	private IdleCamera ic;

	private View esterna, interna;

	public void init() {

		// Camere per View
		esterna = new View(0, 0, GameEngine.WIDTH / 2, GameEngine.HEIGHT);
		interna = new View(GameEngine.WIDTH / 2, 0, GameEngine.WIDTH / 2, GameEngine.HEIGHT);
		add(esterna);
		add(interna);

		// Light
		light = new Light(0, 5, -10, Color.white);
		adda(light);

		// Generazione paesaggio per 3D
		Random r = new Random();
		Color c;
		float size = 2;
		for (int z = -1; z >= -10; z--) {
			for (int x = 0; x < 50; x++) {
				c = new Color(
						x / 50f,
						x / 50f * (-(z + 1) / 9f) * .8f + .2f * r.nextFloat(),
						-(z + 1) / 9f
				);
				Entity e = new Entity(IndexedMesh.cube, c);
				e.setPosition(x * size, 0, z * size);
				e.setScale(size * .95f);
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
