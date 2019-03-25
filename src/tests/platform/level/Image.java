package tests.platform.level;

import engine.Mesh;
import graphics.maths.Mat;
import graphics.maths.Vec2;
import graphics.maths.Vec3;
import graphics.textures.Texture;

public class Image {

	private Vec3 pos;
	private Vec2 scale;
	private Mesh mesh = Mesh.quad;
	private Texture texture;

	public Image(Texture texture, Vec3 pos, Vec2 scale) {
		this.texture = texture;
		this.pos = pos;
		this.scale = scale;
	}

	public Mat getModelMatrix() {
		return Mat.model(pos.x, pos.y, pos.z, 0, 0, 0, scale.x, scale.y, 1);
	}

	public Texture getTexture() {
		return texture;
	}

	public Mesh getMesh() {
		return mesh;
	}

}
