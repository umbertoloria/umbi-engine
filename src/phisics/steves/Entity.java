package phisics.steves;

import engine.mesh.Mesh;
import engine.shaders.Shader;
import engine.structures.Renderable;
import graphics.camera.Camera;
import graphics.cosmetics.Color;
import graphics.maths.Mat;
import graphics.maths.Vec3;
import phisics.PuntoMateriale;

public class Entity extends PuntoMateriale implements Renderable {

	private Mesh mesh;
	private Shader shader;
	Vec3 rotation = new Vec3(0, 0, 0);
	private Vec3 scale = new Vec3(1, 1, 1);
	private Color color;

	public Entity(Mesh mesh, Color color) {
		super(0);
		this.mesh = mesh;
		this.color = color;
		this.shader = Shader.basic_shader;
	}

	public final void onRender(Camera c, Light light) {
		if (!c.canRender(this)) {
			return;
		}
		shader.enable();
		shader.setUniformMat4f("model", getModelMatrix());
		shader.setUniformMat4f("view", c.getViewMatrix());
		shader.setUniformMat4f("projection", c.getProjectionMatrix());
		shader.setUniformColor("object_color", color);
		if (light != null) {
			shader.setUniformVec3("light_position", light.position);
			shader.setUniformOpaqueColor("light_color", ((Entity) light).color);
		}
		mesh.render();
		shader.disable();
	}

	private Mat getModelMatrix() {
		return Mat.model(
				position.x, position.y, position.z,
				rotation.x, rotation.y, rotation.z,
				scale.x, scale.y, scale.z
		);
	}

	public final void setPosition(float x, float y, float z) {
		position.place(x, y, z);
	}

	public final void setScale(float value) {
		scale.place(value, value, value);
	}

	public final float getPitch() {
		return rotation.x;
	}

	public final float getYaw() {
		return rotation.y;
	}

}
