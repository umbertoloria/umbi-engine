package graphics.textures;

final class SpriteGroup {


	enum Trend {
		NULL, LINEARE, ALTERNATA;
	}

	private final String name;

	private final int begin, end;
	private final Trend trend;

	public SpriteGroup(String name, int begin, int end) {
		if (end < begin + 1 || begin + 2 < end) {
			throw new RuntimeException("SpriteGroup inconsistente");
		}
		this.name = name;
		trend = Trend.NULL;
		this.begin = begin;
		this.end = end;
	}

	public SpriteGroup(String name, int begin, int end, Trend trend) {
		if (begin + 2 > end) {
			throw new RuntimeException("SpriteGroup inconsistente");
		}
		this.name = name;
		this.trend = trend;
		this.begin = begin;
		this.end = end;
	}

	boolean contains(int x) {
		return begin <= x && x < end;
	}

	int getFirst() {
		return begin;
	}

	int getLast() {
		return end - 1;
	}

	Trend getTrend() {
		return trend;
	}

	String getName() {
		return name;
	}

	int[] animateMyOffset(int offset, int direction) {
		if (trend == Trend.LINEARE) {
			int newoffset = offset + direction;
			if (newoffset > end - 1) {
				newoffset = begin;
			}
			return new int[] {newoffset, direction};
		} else if (trend == Trend.ALTERNATA) {
			int newoffset = offset + direction;
			if (end <= newoffset) {
				newoffset = end - 2;
				direction = -direction;
			} else if (newoffset < begin) {
				newoffset = begin + 1;
				direction = -direction;
			}
			return new int[] {newoffset, direction};
		}
		return null;
	}

}
