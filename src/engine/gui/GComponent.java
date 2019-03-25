package engine.gui;

import engine.Mesh;
import engine.shaders.Shader;
import engine.structures.Renderable;
import engine.structures.Updatable;
import graphics.camera.Camera;
import graphics.maths.Mat;
import graphics.maths.Quad;

public abstract class GComponent implements Updatable, Renderable {

	protected Quad quad;
	protected Shader shader;

	public GComponent(Quad quad, Shader shader) {
		this.quad = quad;
		this.shader = shader;
	}

	public Mat getModelMatrix() {
		return Mat.model(
				quad.center.x, quad.center.y, 0,
				0, 0, 0,
				quad.size.x, quad.size.y, 0
		);
	}

	public abstract void render(Camera camera);

}
