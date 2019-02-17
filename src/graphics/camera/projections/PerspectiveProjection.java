package graphics.camera.projections;

import engine.GameEngine;
import graphics.maths.Mat;

public class PerspectiveProjection extends Projection {

	public PerspectiveProjection(float fov) {
		super(Mat.perspective(fov, GameEngine.PROPS, Projection.NEAR, Projection.FAR));
	}

}
