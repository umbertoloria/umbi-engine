package objects;

import buffers.VAO;
import metrics.Matrix;
import metrics.Vec2;
import inputs.Camera;
import shaders.StaticShader;
import structures.Entity;
import utils.Color;

public class Object extends Entity {

	private VAO vao;
	private StaticShader shader;
	private Vec2 size;
	private Color color;
	private Camera camera;

	public void setVao(VAO vao) {
		this.vao = vao;
	}

	public void setShader(StaticShader shader) {
		this.shader = shader;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
		shader.bind();
		shader.loadProjectionMatrix(camera);
		shader.unbind();
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setSize(double width, double height) {
		this.size = new Vec2(width, height);
	}

	public void render() {
		shader.bind();
		Matrix tm = Matrix.createTransformationMatrix(getPos().x(), getPos().z(), getPos().y(),
				0, 0, 0, 1);
		shader.loadTransformationMatrix(tm);
		shader.loadViewMatrix(camera);
		vao.render();
		shader.unbind();
	}

	public void delete() {
		vao.delete();
		shader.delete();
	}

}
