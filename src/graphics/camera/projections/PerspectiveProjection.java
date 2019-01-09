package graphics.camera.projections;

import engine.GenericEngine;
import graphics.maths.Mat;

public class PerspectiveProjection extends Projection {

	public PerspectiveProjection(float fov) {
		super(
				Mat.perspective(fov, GenericEngine.PROPS, Projection.NEAR, Projection.FAR)
		);
	}

}
