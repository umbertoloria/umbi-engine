package engine.mesh;

import graphics.models.Model;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public abstract class Mesh {

	public static Mesh cube, sphere, cone, cylinder, monkey;

	public static void loadAll() {
		cube = new IndexedMesh(Model.cube);
		sphere = new IndexedMesh(Model.sphere);
		cone = new IndexedMesh(Model.cone);
		cylinder = new IndexedMesh(Model.cylinder);
		monkey = new IndexedMesh(Model.monkey);
	}

	static void createVBO(float[] data, int index) {
		glBindBuffer(GL_ARRAY_BUFFER, glGenBuffers());
		glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
		glVertexAttribPointer(index, 3, GL_FLOAT, false, 3 * Float.BYTES, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	static int createVBO(int[] data) {
		int indicesBufferObject = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesBufferObject);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, data, GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		return indicesBufferObject;
	}

	abstract void bind();

	abstract void draw();

	abstract void unbind();

	public void render() {
		bind();
		draw();
		unbind();
	}

}
