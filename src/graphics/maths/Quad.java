package graphics.maths;

public final class Quad {

	public final Vec2 leftTop, rightTop;
	public final Vec2 leftBottom, rightBottom;
	public final Vec2 center, size;

	public Quad(Vec2 leftTop, Vec2 size) {
		this.leftTop = leftTop;
		rightTop = leftTop.add(new Vec2(size.x, 0));
		leftBottom = leftTop.add(new Vec2(0, size.y));
		rightBottom = leftTop.add(size);
		center = leftTop.add(size.multiply(.5f));
		this.size = size;
	}

}
