package tests;

import camera.Camera;
import camera.PerspectiveCamera;
import engine.GameEngine;

public class Application {

	public static void main(String[] args) {
		Camera camera = new PerspectiveCamera();

		GameEngine gw = new GameEngine("umbi-engine", camera);

		SampleLayer sampleLayer = new SampleLayer();
		gw.add(sampleLayer);

		gw.play();
	}

}
