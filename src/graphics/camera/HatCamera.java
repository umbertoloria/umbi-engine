package graphics.camera;

import engine.GameEngine;
import engine.structures.Renderable;
import graphics.maths.Mat;
import phisics.En3;

public class HatCamera extends Camera {

	private En3 e;

	public HatCamera(En3 e) {
		super(Mat.perspective(55, GameEngine.PROPS, Camera.NEAR, Camera.FAR));
		this.e = e;
	}

	public boolean canRender(Renderable r) {
		return r != e;
	}

	public void update(float delta) {
		setPosition(e.getPosition());
		setRotation(e.getRotation());
	}

}
