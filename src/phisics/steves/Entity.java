package phisics.steves;

import engine.buffers.Mesh;
import engine.inputs.Cursor;
import engine.inputs.Keyboard;
import engine.inputs.Mouse;
import engine.shaders.Shader;
import engine.structures.Renderable;
import graphics.camera.Camera;
import graphics.maths.Mat;
import graphics.maths.Vec3;
import phisics.PuntoMateriale;

public class Entity extends PuntoMateriale implements Renderable {

	private Mesh mesh;
	private Shader shader;
	Vec3 rotation = new Vec3(0, 0, 0);
	private Vec3 scale = new Vec3(1, 1, 1);

	public Entity(Mesh mesh) {
		super(0);
		this.mesh = mesh;
		this.shader = Shader.basic_shader;
	}

	public void render(Camera c) {
		if (!c.canRender(this)) {
			return;
		}
		shader.enable();
		shader.setUniformMat4f("pr_matrix", c.getProjectionMatrix());
		shader.setUniformMat4f("vw_matrix", c.getViewMatrix());
		shader.setUniformMat4f("ml_matrix", getModelMatrix());
//		shader.setUniformColor("cl", color);
		mesh.render();
		shader.disable();
	}

	public void update(float delta, Keyboard k, Cursor c, Mouse m) {
		super.update(delta, k, c, m);
	}

	private Mat getModelMatrix() {
		return Mat.model(
				position.x, position.y, position.z,
				rotation.x, rotation.y, rotation.z,
				scale.x, scale.y, scale.z
		);
	}

	public void setPosition(float x, float y, float z) {
		position.place(x, y, z);
	}

	public void setScale(float value) {
		scale.place(value, value, value);
	}

	public float getPitch() {
		return rotation.x;
	}

	public float getYaw() {
		return rotation.y;
	}

}
