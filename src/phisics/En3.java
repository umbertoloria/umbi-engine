package phisics;

import engine.Mesh;
import engine.shaders.Shader;
import graphics.Color;
import graphics.camera.Camera;
import graphics.maths.Mat;
import graphics.maths.Vec3;

public class En3 extends En {

	private Mesh mesh;
	private Shader shader = Shader.light;
	Vec3 rotation = new Vec3(0, 0, 0);
	private Vec3 scale = new Vec3(1, 1, 1);
	private Color color;

	public En3(Mesh mesh, Color color) {
		this.mesh = mesh;
		this.color = color;
	}

	public final void onRender(Camera camera, Light light) {
		if (camera.canRender(this)) {
			shader.enable();
			shader.setUniformMat4f("model", getModelMatrix());
			shader.setUniformMat4f("view", camera.getViewMatrix());
			shader.setUniformMat4f("projection", camera.getProjectionMatrix());
			shader.setUniformColor("object_color", color);
			if (light != null) {
				shader.setUniformVec3("light_position", light.getPosition());
				shader.setUniformOpaqueColor("light_color", light.getColor());
			}
			mesh.render();
			shader.disable();
		}
	}

	public Mat getModelMatrix() {
		return Mat.model(
				position.x(), position.y(), position.z(),
				rotation.x(), rotation.y(), rotation.z(),
				scale.x(), scale.y(), scale.z()
		);
	}

	public final void setPosition(float x, float y, float z) {
		position = new Vec3(x, y, z);
	}

	public final void setScale(float value) {
		scale = new Vec3(value, value, value);
	}

	public final float getPitch() {
		return rotation.x();
	}

	public final float getYaw() {
		return rotation.y();
	}

	public Color getColor() {
		return color;
	}

}
