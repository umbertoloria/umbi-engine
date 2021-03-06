package phisics;

import engine.Mesh;
import engine.shaders.Shader;
import engine.structures.Renderable;
import graphics.maths.Mat;
import graphics.maths.Vec2;
import graphics.maths.Vec3;
import graphics.textures.Animator;

public class En2 implements Renderable {

	private Mesh mesh = Mesh.quad;
	private Vec3 position = new Vec3(0, 0, 0);
	private Vec3 rotation = new Vec3(0, 0, 0);
	private Vec2 scale = new Vec2(1, 1);
	private Animator animator;
	private Shader shader;

	public En2(Shader shader, Animator animator) {
		this.shader = shader;
		this.animator = animator;
	}

//	public final void render(Camera camera, Light light) {
//		if (camera.canRender(this)) {
//			animator.getSpriteSheet().getTexture().bind();
//			shader.enable();
//			shader.setUniformMat4f("model", getModelMatrix());
//			shader.setUniformMat4f("view", camera.getViewMatrix());
//			shader.setUniformMat4f("projection", camera.getProjectionMatrix());
//			shader.setUniformTexture("texture_channel", animator.getSpriteSheet().getTexture());
//			shader.setUniformVec2("texture_scale", animator.getSpriteSheet().getScale());
//			shader.setUniformVec2("texture_offset", animator.getOffset());
//			mesh.render();
//			shader.disable();
//			animator.getSpriteSheet().getTexture().unbind();
//		}
//	}

	public Mat getModelMatrix() {
		return Mat.model(
				position.x, position.y, position.z,
				rotation.x, rotation.y, rotation.z,
				scale.x, scale.y, 0
		);
	}

	public Animator getAnimator() {
		return animator;
	}

	public Mesh getMesh() {
		return mesh;
	}

	public final Vec2 getPosition() {
		return new Vec2(position.x, position.y);
	}

	public final void setPosition(float x, float y) {
		position = new Vec3(x, y, 0);
	}

	public final void setPosition(Vec2 pos) {
		position = new Vec3(pos.x, pos.y, 0);
	}

	public final void setScale(float sx, float sy) {
		scale = new Vec2(sx, sy);
	}

	public final void setRotation(float rx, float ry, float rz) {
		rotation = new Vec3(rx, ry, rz);
	}

	public Shader getShader() {
		return shader;
	}

}
