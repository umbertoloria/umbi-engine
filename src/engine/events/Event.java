package engine.events;

public class Event {

	public enum Type {
		KEY_PRESSED,
		KEY_PRESSING,
		KEY_RELEASED,
		CURSOR_PLACED,
		CURSOR_MOVED,
		MOUSE_PRESSED,
		MOUSE_RELEASED
	}

	Type type;
	boolean handled = false;

	public Event(Type type) {
		this.type = type;
	}

	public String toString() {
		return "Event: " + type;
	}

}
