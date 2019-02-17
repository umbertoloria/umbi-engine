package graphics.camera.projections;

import engine.GameEngine;
import graphics.maths.Mat;

public class OrthographicProjection extends Projection {

	public OrthographicProjection(float zoom) {
		super(Mat.orthographic(
				-zoom * GameEngine.PROPS / 2, zoom * GameEngine.PROPS / 2,
				-zoom / GameEngine.PROPS / 2, zoom / GameEngine.PROPS / 2,
				Projection.NEAR, Projection.FAR
		));
	}

}
