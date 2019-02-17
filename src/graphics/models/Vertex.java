package graphics.models;

import graphics.maths.Vec3;

class Vertex {

	private Vec3 position = new Vec3(0, 0, 0);
	private Vec3 normal = new Vec3(0, 0, 0);

	Vertex(Vec3 position, Vec3 normal) {
		this.position.place(position);
		this.normal.place(normal);
	}

	Vec3 getPosition() {
		return position;
	}

	Vec3 getNormal() {
		return normal;
	}

	boolean equals(Vertex o) {
		return position.equals(o.position) && normal.equals(o.normal);
	}

}
