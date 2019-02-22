package graphics.textures;

import graphics.maths.Vec2;

public class SpriteSheet {

	private Texture texture;
	private SpriteMap spriteMap;
	private Vec2 scale;

	public SpriteSheet(String filegroup, int width, int height) {
		texture = new Texture(filegroup + ".png");
		spriteMap = new SpriteMap(filegroup + ".map");
//		rowCount = texture.getHeight() / height;
//		colCount = texture.getWidth() / width;
		scale = new Vec2((float) texture.getWidth() / width, (float) texture.getHeight() / height);
	}

	public Vec2 getScale() {
		return scale;
	}

	public Texture getTexture() {
		return texture;
	}

//	public void nextCol() {
//		offset = new Vec2(spriteMap.nextFrom((int) offset.x()), offset.y());
//	}
//
//	public void nextRow() {
////		offset.y = (rowCount + offset.y + 1) % rowCount;
//		offset = new Vec2(offset.x(), offset.y() + 1);
//	}

	SpriteMap getSpriteMap() {
		return spriteMap;
	}

}
