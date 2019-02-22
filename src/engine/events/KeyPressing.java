package engine.events;

public class KeyPressing extends Event {

	private String key;

	public KeyPressing(String key) {
		super(Type.KEY_PRESSING);
		this.key = key;
	}

	String getKey() {
		return key;
	}

}
