package phisics;

import engine.Mesh;
import engine.shaders.Shader;
import engine.structures.Renderable;
import graphics.Color;
import graphics.maths.Mat;
import graphics.maths.Vec3;

public class En3 implements Renderable {

	private Mesh mesh;
	private Vec3 position = new Vec3(0, 0, 0);
	private Vec3 rotation = new Vec3(0, 0, 0);
	private Vec3 scale = new Vec3(1, 1, 1);
	private Color color;
	private Shader shader;

	public En3(Mesh mesh, Shader shader, Color color) {
		this.mesh = mesh;
		this.shader = shader;
		this.color = color;
	}

	public Mat getModelMatrix() {
		return Mat.model(
				position.x, position.y, position.z,
				rotation.x, rotation.y, rotation.z,
				scale.x, scale.y, scale.z
		);
	}

	public Mesh getMesh() {
		return mesh;
	}

	public final Vec3 getPosition() {
		return position;
	}

	public final void setPosition(float x, float y, float z) {
		position = new Vec3(x, y, z);
	}

	public final void setPosition(Vec3 position) {
		this.position = position;
	}

	public final Vec3 getRotation() {
		return rotation;
	}

	public final void setRotation(Vec3 rotation) {
		this.rotation = rotation;
	}

	public final void setScale(float value) {
		scale = new Vec3(value, value, value);
	}

	public Color getColor() {
		return color;
	}

	public Shader getShader() {
		return shader;
	}

}
