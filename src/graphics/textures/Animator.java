package graphics.textures;

import graphics.maths.Vec2;

public class Animator {

	private SpriteSheet spriteSheet;
	private SpriteMap spriteMap;
	private SpriteGroup currentGroup;

	private int currentOffset;
	private int direction = 1;

	private int row;
	private long last;
	private int delay;

	public Animator(SpriteSheet spriteSheet, int delay, int row) {
		this.spriteSheet = spriteSheet;
		this.spriteMap = new SpriteMap(spriteSheet.getFilename() + ".map");
		this.delay = delay;
		this.row = row;
	}

	public void animate(String name) {
		if (last + delay >= System.currentTimeMillis()) {
			return;
		}
		last = System.currentTimeMillis();
		if (currentGroup == null || !currentGroup.getName().equals(name)) {

			direction = 1;
			SpriteGroup newGroup = getGroupByName(name);
			if (newGroup != null) {
				currentGroup = newGroup;
				currentOffset = newGroup.getFirst();
			} else {
				currentGroup = null;
				currentOffset = 0;
			}

		} else {

			int[] newDispositions = currentGroup.animateMyOffset(currentOffset, direction);
			if (newDispositions != null) {
				currentOffset = newDispositions[0];
				direction = newDispositions[1];
			}

		}
	}

	private SpriteGroup getGroupByName(String name) {
		for (SpriteGroup group : spriteMap.getGroups()) {
			if (group.getName().equals(name)) {
				return group;
			}
		}
		return null;
	}

	public SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}

	public Vec2 getOffset() {
		return new Vec2(currentOffset, row);
	}

}
