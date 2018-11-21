package utils;

public class Color {

	public static final Color RED = new Color(255, 0, 0);
	public static final Color GREEN = new Color(0, 255, 0);
	public static final Color BLUE = new Color(0, 0, 255);
	public static final Color BLACK = new Color(0, 0, 0);
	public static final Color WHITE = new Color(255, 255, 255);

	private float red, green, blue, alpha = 1f;

	public Color(float red, float green, float blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public Color(int red, int green, int blue) {
		this.red = red / 255f;
		this.green = green / 255f;
		this.blue = blue / 255f;
	}

	public Color(float red, float green, float blue, float alpha) {
		this(red, green, blue);
		this.alpha = alpha;
	}

	public Color(String hex) throws IllegalArgumentException {
		if (!hex.startsWith("#") || (hex.length() != 4 && hex.length() != 7)) {
			throw new IllegalArgumentException("Inconsistent color");
		}
		if (hex.length() == 4) {
			hex = "#" + hex.charAt(1) + hex.charAt(1) + hex.charAt(2) + hex.charAt(2) + hex.charAt(3) + hex.charAt(3);
		}
		int i = Integer.decode(hex);
		this.red = (i >> 16 & 0xFF) / 255f;
		this.green = (i >> 8 & 0xFF) / 255f;
		this.blue = (i & 0xFF) / 255f;
	}

	public float getRed() {
		return red;
	}

	public float getGreen() {
		return green;
	}

	public float getBlue() {
		return blue;
	}

	public float getAlpha() {
		return alpha;
	}

}
