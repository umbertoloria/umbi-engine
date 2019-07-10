package engine;

import engine.gui.GComponent;
import engine.shaders.Shader;
import graphics.Color;
import graphics.camera.Camera;
import graphics.maths.Mat;
import graphics.maths.Vec2;
import graphics.textures.Animator;
import graphics.textures.SpriteSheet;
import graphics.textures.Texture;
import phisics.En2;
import phisics.En3;
import phisics.Light;
import tests.platform.level.Image;

public class Renderer {

	private Window window;
	private Camera camera;
	private Light light;

	Renderer(Window window) {
		this.window = window;
	}

	/* Enables */

	public void use(Camera camera) {
		this.camera = camera;
	}

	public void use(Light light) {
		this.light = light;
	}

	/* Drawers */

	public void draw(En3 obj) {
		if (camera.canRender(obj)) {
			draw(obj.getShader(), obj.getModelMatrix(), obj.getColor(), obj.getMesh());
		}
	}

	public void draw(En2 obj) {
		if (camera.canRender(obj)) {
			draw(obj.getShader(), obj.getModelMatrix(), obj.getAnimator(), obj.getMesh());
		}
	}

	public void draw(GComponent comp) {
		comp.render(camera);
	}

	public void draw(Image image) {
		draw(image.getShader(), image.getModelMatrix(), image.getTexture(), image.getMesh());
	}

	// Draw colored mesh.
	public void draw(Shader shader, Mat modelMatrix, Color color, Mesh mesh) {
		shader.enable();
		shader.setUniformMat4f("model", modelMatrix);
		shader.setUniformMat4f("projectionView", camera.getProjectionViewMatrix());
		shader.setUniformColor("object_color", color);
		if (light != null) {
			shader.setUniformVec3("light_position", light.getPosition());
			shader.setUniformColor("light_color", light.getColor());
		}
		mesh.render();
	}

	// Draw animator-based mesh.
	private void draw(Shader shader, Mat modelMatrix, Animator animator, Mesh mesh) {
		animator.getSpriteSheet().getTexture().bind();
		shader.enable();
		shader.setUniformMat4f("model", modelMatrix);
		shader.setUniformMat4f("view", camera.getViewMatrix());
		shader.setUniformMat4f("projection", camera.getProjectionMatrix());
		shader.setUniformTexture("texture_channel", animator.getSpriteSheet().getTexture());
		shader.setUniformVec2("texture_scale", animator.getSpriteSheet().getScale());
		shader.setUniformVec2("texture_offset", animator.getOffset());
		mesh.render();
		animator.getSpriteSheet().getTexture().unbind();
	}

	// Draw spritesheet-based mesh.
	public void draw(Shader shader, Mat modelMatrix, SpriteSheet spriteSheet, Mesh mesh) {
		Texture texture = spriteSheet.getTexture();
		texture.bind();
		shader.enable();
		shader.setUniformMat4f("model", modelMatrix);
		shader.setUniformMat4f("view", camera.getViewMatrix());
		shader.setUniformMat4f("projection", camera.getProjectionMatrix());
		shader.setUniformTexture("texture_channel", texture);
		shader.setUniformVec2("texture_scale", spriteSheet.getScale());
		shader.setUniformVec2("texture_offset", spriteSheet.getOffset());
		mesh.render();
		texture.unbind();
	}

	// Draw texture-based mesh.
	public void draw(Shader shader, Mat modelMatrix, Texture texture, Mesh mesh) {
		texture.bind();
		shader.enable();
		shader.setUniformMat4f("model", modelMatrix);
		shader.setUniformMat4f("view", camera.getViewMatrix());
		shader.setUniformMat4f("projection", camera.getProjectionMatrix());
		shader.setUniformTexture("texture_channel", texture);
		shader.setUniformVec2("texture_scale", new Vec2(1, 1));
		shader.setUniformVec2("texture_offset", new Vec2(0, 0));
		mesh.render();
		texture.unbind();
	}

	/* OPTIONS */

	public void enableDepth() {
		window.enableDepth();
	}

	public void disableDepth() {
		window.disableDepth();
	}

}
