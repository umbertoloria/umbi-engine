package tests;

import engine.GameEngine;
import phisics.Light;
import tests.platform.Menu;
import tests.platform.Platform;

public class Application {

	public static void main(String[] args) {
		GameEngine ge = new GameEngine("umbi-engine");
		ge.add(new Platform());
		ge.add(new Menu());
		ge.play();
	}

}
