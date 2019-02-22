package engine.events;

public class MousePressed extends Event {

	private String button;

	public MousePressed(String button) {
		super(Type.MOUSE_PRESSED);
		this.button = button;
	}

	String getButton() {
		return button;
	}

}
