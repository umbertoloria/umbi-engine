package graphics.cosmetics;

import java.util.Random;

public class Color {

	public static final Color RED = new Color(1, 0, 0);
	public static final Color GREEN = new Color(0, 1, 0);
	public static final Color BLUE = new Color(0, 0, 1);
	public static final Color BLACK = new Color(0, 0, 0);
	public static final Color WHITE = new Color(1, 1, 1);
	public static final Color GRAY = new Color(.5f, .5f, .5f);

	private float red, green, blue, alpha = 1f;

	public Color() {
		Random r = new Random();
		this.red = r.nextFloat();
		this.green = r.nextFloat();
		this.blue = r.nextFloat();
	}

	public Color(float red, float green, float blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public Color(float red, float green, float blue, float alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
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

	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Color oth = (Color) obj;
		return this.red == oth.red && this.green == oth.green && this.blue == oth.blue;
	}

	public String toString() {
		return "color: [red:" + red + ", green:" + green + ", blue:" + blue + ", alpha:" + alpha + "]";
	}

}
