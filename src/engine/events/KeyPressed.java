package engine.events;

public class KeyPressed extends Event {

	private String key;

	public KeyPressed(String key) {
		super(Type.KEY_PRESSED);
		this.key = key;
	}

	String getKey() {
		return key;
	}

}
