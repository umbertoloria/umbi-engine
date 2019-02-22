package engine.layer;

import engine.GameEngine;
import engine.structures.Renderable;
import graphics.camera.Camera;
import graphics.maths.Vec2;
import phisics.Light;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class View implements Renderable {

	private Vec2 start, finish;
	private int width, height;

	protected Camera camera;
	private ArrayList<Renderable> renderables = new ArrayList<>();

	public View(int x, int y, int width, int height) {
		this.start = new Vec2(x, GameEngine.HEIGHT - height - y);
		this.finish = new Vec2(x + width, GameEngine.HEIGHT - y);
		this.width = width;
		this.height = height;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public void add(Renderable r) {
		renderables.add(r);
	}

	public void onRender(Camera ignoroCamera, Light light) {
		glEnable(GL_SCISSOR_TEST);
		glScissor((int) start.x(), (int) start.y(), (int) finish.x(), (int) finish.y());
		glViewport((int) start.x(), (int) start.y(), width, height);
		glDisable(GL_SCISSOR_TEST);
		for (Renderable renderable : renderables) {
			renderable.onRender(camera, light);
		}
	}

}
