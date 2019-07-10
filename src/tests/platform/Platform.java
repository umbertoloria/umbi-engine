package tests.platform;

import engine.Renderer;
import engine.events.Event;
import engine.layer.Layer;
import graphics.camera.Camera;
import graphics.camera.FlatCamera;
import tests.platform.level.Background;
import tests.platform.level.Pavement;
import tests.platform.physics.PhysicsMachine;

import java.util.ArrayList;

public class Platform extends Layer {

	private Camera camera;
	private Background background;
	private ArrayList<Pavement> pavements = new ArrayList<>();
	private Mario mario;
	private Person luigi;
	private PhysicsMachine physicsMachine = new PhysicsMachine();

	public void onInit() {
		camera = new FlatCamera(0, 16, 0, 9, -1, 1);

		background = new Background();

		pavements.add(new Pavement(0, 0.25f, 16));

		mario = new Mario();
		mario.setPosition(2, 4);

		luigi = new Luigi();
		luigi.setPosition(5, 4);

		physicsMachine.add(mario, 2);
		physicsMachine.add(luigi, 1);
	}

	public void onUpdate(float delta) {
		mario.update(delta);
		physicsMachine.update(delta);
	}

	public void onEvent(Event event) {
		mario.event(event);
	}

	public void onRender(Renderer renderer) {
		renderer.use(camera);
		renderer.draw(background);
		for (Pavement pavement : pavements) {
			renderer.draw(pavement);
		}
		renderer.draw(mario);
		renderer.draw(luigi);
	}

}
