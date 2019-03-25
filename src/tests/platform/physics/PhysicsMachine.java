package tests.platform.physics;

import graphics.maths.Vec2;
import phisics.En2;

import java.util.ArrayList;

public class PhysicsMachine {

	private static final float GRAVITY = .3f;
	private ArrayList<En2> ens = new ArrayList<>();
	private ArrayList<Float> weights = new ArrayList<>();

	public void add(En2 en, float weight) {
		ens.add(en);
		weights.add(weight);
	}

	public void update(float delta) {
		for (int i = 0; i < ens.size(); i++) {
			En2 en = ens.get(i);
			en.setPosition(simulation(en.getPosition(), weights.get(i), delta));
			// TODO: Implement collisions.
		}
	}

	private Vec2 simulation(Vec2 position, float weight, float delta) {
		return position.add(new Vec2(0, -weight * GRAVITY * delta));
	}


}
