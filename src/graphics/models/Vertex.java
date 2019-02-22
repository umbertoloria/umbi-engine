package graphics.models;

import graphics.maths.Vec2;
import graphics.maths.Vec3;

import java.util.Objects;

class Vertex {

	private Vec3 position, normal;
	private Vec2 textureCoordinate;

	Vertex(Vec3 position, Vec2 textureCoordinate, Vec3 normal) {
		this.position = position;
		this.textureCoordinate = textureCoordinate;
		this.normal = normal;
	}

	Vertex(Vec3 position, Vec3 normal) {
		this.position = position;
		this.normal = normal;
	}

	Vertex(Vec3 position, Vec2 textureCoordinate) {
		this.position = position;
		this.textureCoordinate = textureCoordinate;
	}

	Vertex(Vec3 position) {
		this.position = position;
	}

	Vec3 getPosition() {
		return position;
	}

	Vec3 getNormal() {
		return normal;
	}

	Vec2 getTextureCoordinate() {
		return textureCoordinate;
	}

	boolean equals(Vertex o) {
		return Objects.equals(position, o.position) &&
				Objects.equals(position, o.position) &&
				Objects.equals(position, o.position);
	}

}
