package engine.layer;

import engine.GameEngine;
import engine.Renderer;
import engine.structures.Renderable;
import graphics.camera.Camera;
import graphics.maths.Vec2;
import phisics.Light;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class View {

	private Vec2 start, finish;
	private int width, height;

	public View(int x, int y, int width, int height) {
		this.start = new Vec2(x, GameEngine.HEIGHT - height - y);
		this.finish = new Vec2(x + width, GameEngine.HEIGHT - y);
		this.width = width;
		this.height = height;
	}

	public void render(Renderer renderer, Light light) {
		glEnable(GL_SCISSOR_TEST);
		glScissor((int) start.x, (int) start.y, (int) finish.x, (int) finish.y);
		glViewport((int) start.x, (int) start.y, width, height);
		glDisable(GL_SCISSOR_TEST);
		onRender(renderer);
	}

	public void onRender(Renderer renderer) {
	}

}
