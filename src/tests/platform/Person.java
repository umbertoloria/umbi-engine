package tests.platform;

import engine.shaders.Shader;
import graphics.textures.Animator;
import graphics.textures.SpriteSheet;
import phisics.En2;

class Person extends En2 {

	Person(int offsetInHeight) {
		super(Shader.basic, new Animator(new SpriteSheet("mariomaker", 25, 25), 90, offsetInHeight));
	}

}
