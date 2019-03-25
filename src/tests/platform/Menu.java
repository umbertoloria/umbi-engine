package tests.platform;

import engine.events.Event;
import engine.gui.GButton;
import engine.gui.GFrame;

public class Menu extends GFrame {

	public void onLoad() {
		GButton btn1 = new GButton(350, 250, 300, 200);
		add(btn1);
		GButton btn2 = new GButton(350, 550, 300, 200);
		add(btn2);
		setSize(1000, 1000);
		setVisible(false);
	}

	private long lastEscape = System.currentTimeMillis();
	private int delayEscape = 100;

	public void onEvent(Event event) {
		super.onEvent(event);
		event.onKeyNewState("ESCAPE", state -> {
			if (state && lastEscape + delayEscape <= System.currentTimeMillis()) {
				setVisible(!isVisible());
			}
		});
		if (isVisible()) {
			event.stopPropagation();
		}
	}

}
