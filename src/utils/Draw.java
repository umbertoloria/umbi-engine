package utils;

import static org.lwjgl.opengl.GL11.*;

public class Draw {

	public static void quad(double cx, double cy, double sx, double sy, Color c) {
		c.apply();
		quad(cx - sx / 2, cy - sy / 2, sx, sy);
	}

	private static void quad(double x, double y, double width, double height) {
		glBegin(GL_QUADS);
		glVertex2d(x, y);
		glVertex2d(x + width, y);
		glVertex2d(x + width, y + height);
		glVertex2d(x, y + height);
		glEnd();
	}

}
