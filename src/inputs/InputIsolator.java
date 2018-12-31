package inputs;

public class InputIsolator {

	private boolean free = true;

	public void newInput() {
		free = true;
	}

	public boolean canTakeInput() {
		return free;
	}

	public void inputTaken(boolean taken) {
		if (taken) {
			free = false;
		}
	}

}
