package engine.gui;

import graphics.maths.Mat;
import graphics.maths.Vec2;
import graphics.textures.SpriteSheet;

class MouseCursor {

	private SpriteSheet spriteSheet = new SpriteSheet("cursor", 25, 28);
	private Vec2 pos = new Vec2(0, 0);
	private Vec2 scale = new Vec2(50, 50);

	void updatePosition(float xmotion, float ymotion, float minX, float maxX, float minY, float maxY) {
		pos = pos.add(new Vec2(xmotion, ymotion));
		if (pos.x < minX) {
			pos = new Vec2(minX, pos.y);
		} else if (pos.x > maxX) {
			pos = new Vec2(maxX, pos.y);
		}
		if (pos.y < minY) {
			pos = new Vec2(pos.x, minY);
		} else if (pos.y > maxY) {
			pos = new Vec2(pos.x, maxY);
		}
	}

	void updateState(boolean pressed) {
		if (pressed) {
			spriteSheet.setOffset(1, 0);
		} else {
			spriteSheet.setOffset(0, 0);
		}
	}

	SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}

	Mat getModelMatrix() {
		return Mat.model(pos.x + scale.x / 2, pos.y + scale.y / 2, 0, 0, 0, 0, scale.x, scale.y, 0);
	}

}
