package tests.platform.level;

import graphics.maths.Vec2;
import graphics.maths.Vec3;
import graphics.textures.Texture;

public class Background extends Image {

	public Background() {
		super(Texture.background, new Vec3(0, 0, -.5f), new Vec2(32, 18));
	}

}
