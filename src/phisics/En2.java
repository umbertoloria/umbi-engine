package phisics;

import engine.Mesh;
import engine.shaders.Shader;
import graphics.camera.Camera;
import graphics.maths.Mat;
import graphics.maths.Vec2;
import graphics.maths.Vec3;
import graphics.textures.Animator;

public class En2 extends En {

	private Mesh mesh = Mesh.quad;
	private Shader shader;
	private Vec3 rotation = new Vec3(0, 0, 0);
	private Vec2 scale = new Vec2(1, 1);
	private Animator animator;

	public En2(Animator animator) {
		this.animator = animator;
		this.shader = Shader.basic;
	}

	public final void onRender(Camera camera, Light light) {
		if (camera.canRender(this)) {
			animator.getSpriteSheet().getTexture().bind();
			shader.enable();
			shader.setUniformMat4f("model", getModelMatrix());
			shader.setUniformMat4f("view", camera.getViewMatrix());
			shader.setUniformMat4f("projection", camera.getProjectionMatrix());
			shader.setUniformTexture("texture_channel", animator.getSpriteSheet().getTexture());
			shader.setUniformVec2("texture_scale", animator.getSpriteSheet().getScale());
			shader.setUniformVec2("texture_offset", animator.getOffset());
			mesh.render();
			shader.disable();
			animator.getSpriteSheet().getTexture().unbind();
		}
	}

	public Mat getModelMatrix() {
		return Mat.model(
				position.x(), position.y(), position.z(),
				rotation.x(), rotation.y(), rotation.z(),
				scale.x(), scale.y(), 0
		);
	}

	public final void setPosition(float x, float y) {
		position = new Vec3(x, y, 0);
	}

	public final void setScale(float sx, float sy) {
		scale = new Vec2(sx, sy);
	}

	public final void setRotation(float rx, float ry, float rz) {
		rotation = new Vec3(rx, ry, rz);
	}

}
