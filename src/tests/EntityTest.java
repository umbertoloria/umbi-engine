package tests;

import entities.Quadrato;
import windows.GameWindow;

public class EntityTest {

	public static void main(String[] args) {
		GameWindow gw = new GameWindow("Entity Test");
		Quadrato p = new Quadrato(50, 50);
		gw.add(p);
		gw.run();
	}

}
