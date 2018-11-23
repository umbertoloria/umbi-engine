package inputs;

import metrics.Matrix;
import metrics.Vec3;
import org.lwjgl.glfw.GLFW;
import structures.Inputable;

public class Camera implements Inputable {

	private Vec3 position = new Vec3(0, 0, 0);
	private double pitch, yaw, roll;
	private Matrix projectionMatrix = Matrix.createProjectionMatrix(70, 0.1, 1000);

	public Vec3 getPosition() {
		return position;
	}

	public double getPitch() {
		return pitch;
	}

	public double getYaw() {
		return yaw;
	}

	public double getRoll() {
		return roll;
	}

	public Matrix getProjectionMatrix() {
		return projectionMatrix;
	}

	public void keyboardInput(int key, int action) {
		if (key != GLFW.GLFW_KEY_W && key != GLFW.GLFW_KEY_A && key != GLFW.GLFW_KEY_S && key != GLFW.GLFW_KEY_D) {
			return;
		}
		int x = 0;
		if (key == GLFW.GLFW_KEY_D) {
			x++;
		}
		if (key == GLFW.GLFW_KEY_A) {
			x--;
		}
		int y = 0;
		if (key == GLFW.GLFW_KEY_W) {
			y++;
		}
		if (key == GLFW.GLFW_KEY_S) {
			y--;
		}
		position.add(x * KEY_SENSIBILITY, 0, y * KEY_SENSIBILITY);
	}

	private static final double KEY_SENSIBILITY = 0.05;
	private static final double MOUSE_SENSIBILITY = 0.05;

	public void mouseInput(double xpos, double ypos) {
		yaw -= xpos * MOUSE_SENSIBILITY;
		pitch += ypos * MOUSE_SENSIBILITY;
	}

	/*private Vec2 scaling;
	private Vec2 translation;

	public Camera(double zoom) {
		double sx = 2d / (zoom * GameWindow.PROPS);
		double sy = -2d / zoom;
		scaling = new Vec2(sx, sy);
		translation = new Vec2(-zoom / 2);
	}

	public void enable() {
		Draw.pushMatrix();
		Draw.scale(scaling);
		Draw.translate(translation);
	}

	public void disable() {
		Draw.popMatrix();
	}*/

}
