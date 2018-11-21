package utils;

import metrics.Vec2;

import static org.lwjgl.opengl.GL11.*;

public class Draw {

	public static void quad(double cx, double cy, double sx, double sy, Color c) {
		color(c);
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

	public static void translate(Vec2 v) {
		glTranslated(v.x(), v.y(), 0);
	}

	public static void scale(Vec2 v) {
		glScaled(v.x(), v.y(), 1);
	}

	public static void pushMatrix() {
		glPushMatrix();
	}

	public static void popMatrix() {
		glPopMatrix();
	}

	public static void color(Color c) {
		glColor4f(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
	}

}
