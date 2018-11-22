package objects;

import buffers.VAO;
import metrics.Vec2;
import shaders.Shader;
import structures.Entity;
import utils.Color;

public class Object extends Entity {

	private VAO vao;
	private Shader shader;
	private Vec2 size;
	private Color color;

	public void setVao(VAO vao) {
		this.vao = vao;
	}

	public void setShader(Shader shader) {
		this.shader = shader;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setSize(double width, double height) {
		this.size = new Vec2(width, height);
	}

	public void render() {
		/*Draw.pushMatrix();
		Draw.translate(getPos());
		Draw.scale(size);
		Draw.color(color);*/
		shader.bind();
		vao.render();
		shader.unbind();
		/*Draw.popMatrix();*/
	}

	public void delete() {
		vao.delete();
		shader.delete();
	}

}
