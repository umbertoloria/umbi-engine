package tests.graph;

import graphics.maths.Mat;
import graphics.maths.Vec3;

public class Point {

	private Vec3 position;

	Point(Vec3 position) {
		this.position = position;
	}

	public Vec3 getPosition() {
		return position;
	}

	public Mat getModelMatrix() {
		return Mat.model(position.x, position.y, position.z, 0, 0, 0, .1f, .1f, .1f);
	}

}