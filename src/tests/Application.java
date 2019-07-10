package tests;

import engine.GameEngine;
import tests.graph.Visual;
import tests.platform.Menu;

public class Application {

	public static void main(String[] args) {
		GameEngine ge = new GameEngine("umbi-engine");
		ge.add(new Visual());
		ge.add(new Menu());
		ge.play();
	}

}
