package engine.events;

public class MouseReleased extends Event {

	private String button;

	public MouseReleased(String button) {
		super(Type.MOUSE_RELEASED);
		this.button = button;
	}

	public String getButton() {
		return button;
	}

}
