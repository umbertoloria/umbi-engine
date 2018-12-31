package graphics;

import buffers.Mesh;
import camera.Camera;
import inputs.Cursor;
import inputs.Keyboard;
import inputs.Mouse;
import maths.Mat;
import maths.Vec3;
import phisics.MaterialPoint;
import shaders.Shader;
import structures.Renderable;

public class Entity extends MaterialPoint implements Renderable {

	private Mesh mesh;
	private Shader shader;
	private Color color;
	private Vec3 rot = new Vec3(0, 0, 0);
	private float scale = 1;

	public Entity(Mesh mesh, Shader shader, Color color) {
		this.mesh = mesh;
		this.shader = shader;
		this.color = color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public void render(Camera c) {
		shader.enable();
		shader.setUniformMat4f("pr_matrix", c.getProjectionMatrix());
		shader.setUniformMat4f("vw_matrix", c.getViewMatrix());
		shader.setUniformMat4f("ml_matrix", getModelMatrix());
		shader.setUniformColor("cl", color);
		mesh.render();
		shader.disable();
	}

	public void update(float delta, Keyboard k, Cursor c, Mouse m) {
		super.update(delta, k, c, m);
		rot.add(delta, delta, delta);
	}

	private Mat getModelMatrix() {
		return Mat.model(getPosition(), rot, scale);
	}

}
