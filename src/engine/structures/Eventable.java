package engine.structures;

import engine.events.Event;

public interface Eventable {

	boolean onEvent(Event event);

}
