package engine.gui;

import engine.Mesh;
import engine.shaders.Shader;
import graphics.Color;
import graphics.camera.Camera;
import graphics.maths.Mat;
import graphics.maths.Quad;
import graphics.maths.Vec2;

public class GButton extends GComponent {

	protected Color border = Color.dark_red;
	protected Color color = Color.red;
	private Mesh mesh = Mesh.quad;

	public GButton(float x, float y, float width, float height) {
		super(new Quad(new Vec2(x, y), new Vec2(width, height)), Shader.gbutton);
	}

	public Mat getInnerModelMatrix() {
		return Mat.model(
				quad.center.x, quad.center.y, 0,
				0, 0, 0,
				quad.size.x - 20, quad.size.y - 20, 0
		);
	}

	public void render(Camera camera) {
		shader.enable();
		shader.setUniformMat4f("model", getModelMatrix());
		shader.setUniformMat4f("view", camera.getViewMatrix());
		shader.setUniformMat4f("projection", camera.getProjectionMatrix());
		shader.setUniformColor("color", border);
		mesh.render();
		shader.setUniformMat4f("model", getInnerModelMatrix());
		shader.setUniformColor("color", color);
		mesh.render();
		shader.disable();
	}

	public void update(float delta) {
	}

	public Color getColor() {
		return color;
	}

}
