package tests.platform;

import engine.events.Event;

class Mario extends Person {

	Mario() {
		super(0);
	}

	private boolean a, d, space;

	void event(Event event) {
		event.onKeyNewState("D", (state) -> {
			d = state;
			if (a ^ d) {
				if (a) {
					direction = 180;
				} else {
					direction = 0;
				}
			}
		});
		event.onKeyNewState("A", (state) -> {
			a = state;
			if (a ^ d) {
				if (a) {
					direction = 180;
				} else {
					direction = 0;
				}
			}
		});
		event.onKeyNewState("SPACE", (state) -> space = state);
	}

	// 1 sinistra, 0 destra
	private int direction = 0;
	private static final float SPEED = 4;

	public void update(float delta) {
		setRotation(0, direction, 0);
		if (space) {
			getAnimator().animate("salto1");
		} else if (d ^ a) {
			if (d) {
				setPosition(getPosition().x + SPEED * delta, getPosition().y);
			} else {
				setPosition(getPosition().x - SPEED * delta, getPosition().y);
			}
			getAnimator().animate("cammina");
		} else {
			getAnimator().animate("alzato");
		}
	}


}
