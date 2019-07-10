package engine.gui;

import engine.Mesh;
import engine.Renderer;
import engine.events.Event;
import engine.layer.Layer;
import engine.shaders.Shader;
import graphics.camera.Camera;
import graphics.camera.FlatCamera;
import graphics.maths.Quad;
import graphics.maths.Vec2;

import java.util.ArrayList;

public abstract class GFrame extends Layer {

	private Quad quad;
	private Camera camera;
	private ArrayList<GComponent> components = new ArrayList<>();
	private MouseCursor mouseCursor;
	private boolean visible = true;

	public void onInit() {
		mouseCursor = new MouseCursor();
		onLoad();
	}

	public abstract void onLoad();

	public final void add(GComponent comp) {
		components.add(comp);
	}

	public void onUpdate(float delta) {
		for (GComponent component : components) {
			component.update(delta);
		}
	}

	// TODO: Make private and implement Listeners.
	public void onEvent(Event event) {
		if (visible) {
			event.onCursorMoved((xmotion, ymotion) -> mouseCursor.updatePosition((float) xmotion, (float) ymotion,
					quad.leftTop.x, quad.rightTop.x, quad.leftTop.y, quad.leftBottom.y));
			event.onMousePressed("LEFT", () -> mouseCursor.updateState(true));
			event.onMouseReleased("LEFT", () -> mouseCursor.updateState(false));
		}
	}

	public void onRender(Renderer renderer) {
		if (visible) {
			renderer.disableDepth();
			renderer.use(camera);
			for (GComponent component : components) {
				renderer.draw(component);
			}
			renderer.draw(Shader.basic, mouseCursor.getModelMatrix(), mouseCursor.getSpriteSheet(), Mesh.quad);
			renderer.enableDepth();
		}
	}

	protected void setSize(float width, float height) {
		quad = new Quad(new Vec2(0, 0), new Vec2(width, height));
		camera = new FlatCamera(0, width, height, 0, 0, 1);
	}

	protected void setVisible(boolean visible) {
		this.visible = visible;
	}

	protected boolean isVisible() {
		return visible;
	}

}
