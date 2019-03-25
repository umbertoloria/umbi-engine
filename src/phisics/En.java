package phisics;

import engine.structures.Renderable;
import graphics.maths.Mat;
import graphics.maths.Vec3;

public abstract class En implements Renderable {

	protected Vec3 position = new Vec3(0, 0, 0);

	public abstract Mat getModelMatrix();

}
