package graphics.textures;

import graphics.maths.Vec2;

public class SpriteSheet {

	private String filename;
	private Texture texture;
	private Vec2 scale, offset;

	public SpriteSheet(String filename, int width, int height) {
		this.filename = filename;
		texture = new Texture(filename + ".png", true);
		scale = new Vec2((float) texture.getWidth() / width, (float) texture.getHeight() / height);
		offset = new Vec2(0, 0);
	}

	public Vec2 getScale() {
		return scale;
	}

	public void setOffset(float x, float y) {
		offset = new Vec2(x, y);
	}

	public Vec2 getOffset() {
		return offset;
	}

	public Texture getTexture() {
		return texture;
	}

	public String getFilename() {
		return filename;
	}

}
