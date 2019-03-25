package engine.events;

import java.security.Key;

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

	private Type type;
	private boolean handled;

	public Event(Type type) {
		this.type = type;
	}

	public void onKeyNewState(String key, KeyNewStateEventHandler handler) {
		if (!handled) {
			if (this instanceof KeyPressed && ((KeyPressed) this).getKey().equals(key)) {
				handler.response(true);
				handled = true;
			}
			if (this instanceof KeyReleased && ((KeyReleased) this).getKey().equals(key)) {
				handler.response(false);
				handled = true;
			}
		}
	}

	public void onKeyPressing(String key, GeneralEventHandler handler) {
		if (!handled) {
			if (this instanceof KeyPressing && ((KeyPressing) this).getKey().equals(key)) {
				handler.response();
				handled = true;
			}
		}
	}

	public void onMousePressed(String button, GeneralEventHandler handler) {
		if (!handled && this instanceof MousePressed && ((MousePressed) this).getButton().equals(button)) {
			handler.response();
			handled = true;
		}
	}

	public void onMouseReleased(String button, GeneralEventHandler handler) {
		if (!handled && this instanceof MouseReleased && ((MouseReleased) this).getButton().equals(button)) {
			handler.response();
			handled = true;
		}
	}

	public void onCursorMoved(CursorMovedEventHandler handler) {
		if (!handled && this instanceof CursorMoved) {
			CursorMoved smartEvent = ((CursorMoved) this);
			handler.response(smartEvent.getXMotion(), smartEvent.getYMotion());
			handled = true;
		}
	}

	public void stopPropagation() {
		handled = true;
	}

	public String toString() {
		return "Event: " + type;
	}

}
