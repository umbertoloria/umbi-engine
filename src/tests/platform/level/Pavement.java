package tests.platform.level;

import graphics.maths.Vec2;
import graphics.maths.Vec3;
import graphics.textures.Texture;

public class Pavement extends Image {

	public Pavement(float x, float y, float width) {
		super(Texture.pavement, new Vec3(width / 2 + x, y, -.25f), new Vec2(width, .5f));
	}

}
