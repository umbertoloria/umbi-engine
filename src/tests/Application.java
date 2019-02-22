package tests;

import engine.GameEngine;

public class Application {

	public static void main(String[] args) {
		GameEngine ge = new GameEngine("umbi-engine");
		ge.add(new Platform());
		ge.play();
	}

}
