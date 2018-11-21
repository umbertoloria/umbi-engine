package objects;

import buffers.VAO;
import metrics.Vec2;
import structures.Entity;
import utils.Color;
import utils.Draw;

public class Object extends Entity {

	private VAO vao;
	private Vec2 size;
	private Color color;

	public void setVao(VAO vao) {
		this.vao = vao;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setSize(double width, double height) {
		this.size = new Vec2(width, height);
	}

	public void render() {
		Draw.pushMatrix();
		Draw.translate(getPos());
		Draw.scale(size);
		Draw.color(color);
		vao.render();
		Draw.popMatrix();
	}

}
