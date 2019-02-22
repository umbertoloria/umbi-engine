package phisics;

import engine.structures.Updatable;
import graphics.maths.Vec3;

import java.util.ArrayList;

public abstract class PuntoMateriale implements Updatable {

	protected Vec3 position = new Vec3(0, 0, 0);
	private float weight;
	private ArrayList<Forza> forze = new ArrayList<>();

	public PuntoMateriale(float weight) {
		this.weight = weight;
		forze.add(new Forza(9.81f, 0));
	}

	float getWeight() {
		return weight;
	}

	public final Vec3 getPosition() {
		return position;
	}

//	public void setMotoUniforme(float speed) {
//		setMotoUniformementeAccelerato(speed, 0);
//	}

//	public void setMotoUniformementeAccelerato(float speed, float acceleration) {
//		this.speed = speed;
//		this.acceleration = acceleration;
//	}

	public void onUpdate(float delta) {
		if (weight == 0) {
			return;
		}
		for (Forza forza : forze) {
			forza.apply(this, delta);
		}
//		speed += acceleration * delta;
//		position.move(direction, 0, speed * delta);
	}

}
