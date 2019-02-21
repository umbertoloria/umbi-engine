package engine.events;

public class KeyReleased extends Event {

	private String key;

	public KeyReleased(String key) {
		super(Type.KEY_RELEASED);
		this.key = key;
	}

	public String getKey() {
		return key;
	}

}
