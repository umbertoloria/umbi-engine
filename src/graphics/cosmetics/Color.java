package graphics.cosmetics;

import java.util.Random;

public class Color {

	public static final Color red = new Color(1, 0, 0);
	public static final Color green = new Color(0, 1, 0);
	public static final Color blue = new Color(0, 0, 1);
	public static final Color black = new Color(0, 0, 0);
	public static final Color white = new Color(1, 1, 1);
	public static final Color gray = new Color(.5f, .5f, .5f);

	private float r, g, b, alpha = 1f;

	public Color() {
		Random r = new Random();
		this.r = r.nextFloat();
		this.g = r.nextFloat();
		this.b = r.nextFloat();
	}

	public Color(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public Color(float r, float g, float b, float alpha) {
		this.r = r;
		this.g = g;
		this.b = b;
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
		this.r = (i >> 16 & 0xFF) / 255f;
		this.g = (i >> 8 & 0xFF) / 255f;
		this.b = (i & 0xFF) / 255f;
	}

	public float getRed() {
		return r;
	}

	public float getGreen() {
		return g;
	}

	public float getBlue() {
		return b;
	}

	public float getAlpha() {
		return alpha;
	}

	public String toString() {
		return "color: [r:" + r + ", g:" + g + ", b:" + b + ", alpha:" + alpha + "]";
	}

}
