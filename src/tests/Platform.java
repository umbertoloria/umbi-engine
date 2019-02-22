package tests;

import engine.events.Event;
import engine.layer.Layer;
import graphics.camera.IdleCamera;
import graphics.textures.Animator;
import graphics.textures.SpriteSheet;
import phisics.En2;

public class Platform extends Layer {

	private En2 mario;
	private Animator anim1, anim2;

	public void onInit() {
		camera = new IdleCamera(0, 0, 20, 0, 0);

		anim1 = new Animator(new SpriteSheet("mariomaker", 25, 25), 90, 0);
		anim2 = new Animator(new SpriteSheet("mariomaker", 25, 25), 10, 1);

		mario = new En2(anim1);
		mario.setPosition(-18, 0);
		mario.setScale(4, 4);
		add(mario);

		En2 b = new En2(anim2);
		b.setPosition(10, 0);
		b.setScale(3, 3);
		add(b);
	}

	// 1 sinistra, 0 destra
	private int direction = 1;
	private static final float SPEED = 10;

	public void onUpdate(float delta) {
		mario.setRotation(0, direction * 180, 0);
		if (space) {
			anim1.animate("salto1");
		} else if (d ^ a) {
			if (d) {
				mario.setPosition(mario.getPosition().x() + SPEED * delta, 0);
			} else {
				mario.setPosition(mario.getPosition().x() - SPEED * delta, 0);
			}
			anim1.animate("cammina");
		} else {
			anim1.animate("alzato");
		}
	}

	private boolean a, d, space;

	public void onEvent(Event event) {
		event.onKeyNewState("D", (state) -> {
			if (state) {
				direction = 0;
			}
			d = state;
		});
		event.onKeyNewState("A", (state) -> {
			if (state) {
				direction = 1;
			}
			a = state;
		});
		event.onKeyNewState("SPACE", (state) -> space = state);
	}

}
