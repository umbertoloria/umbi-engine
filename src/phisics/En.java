package phisics;

import engine.structures.Renderable;
import graphics.maths.Mat;

public abstract class En extends PuntoMateriale implements Renderable {

	En() {
		super(0);
	}

	public abstract Mat getModelMatrix();

}
