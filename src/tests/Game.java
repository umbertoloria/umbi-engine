package tests;

import inputs.Camera;
import objects.Object;
import objects.ObjectLoader;
import windows.GameWindow;

public class Game {

	public static void main(String[] args) {
		GameWindow gw = new GameWindow("Game");

		Camera cam = new Camera();

		gw.add(cam);

		Object object = ObjectLoader.get("quad");
		object.setCamera(cam);
		object.setOrigin(0, -20, 0);
		//object.setDirection(270);
		//object.setMotoRettilineoUniforme(1);

		gw.add(object);
		gw.run();

		object.delete();
	}

}
