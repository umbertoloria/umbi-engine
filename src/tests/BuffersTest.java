package tests;

import objects.Object;
import objects.ObjectLoader;
import windows.GameWindow;

public class BuffersTest {

	public static void main(String[] args) {
		GameWindow gw = new GameWindow("Buffers Test");

		Object object = ObjectLoader.get("quad");
		object.setOrigin(10, 20);
		object.setMotoRettilineoUniforme(50);

		gw.add(object);
		gw.run();
	}

}
