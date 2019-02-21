package engine.events;

import engine.structures.Eventable;

public class EventDispatcher {

	private Event event;

	public EventDispatcher(Event event) {
		this.event = event;
	}

	public void dispatch(Event.Type type, Eventable handler) {
		if (!event.handled && event.type == type && handler.onEvent(event)) {
			event.handled = true;
		}
	}

}
