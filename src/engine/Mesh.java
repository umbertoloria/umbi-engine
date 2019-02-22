package engine;

import engine.shaders.Shader;
import graphics.models.Model;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Mesh {

	public static Mesh cube, sphere, cone, cylinder, monkey, quad;

	public static void loadAll() {
		cube = new Mesh(Model.cube);
		sphere = new Mesh(Model.sphere);
		cone = new Mesh(Model.cone);
		cylinder = new Mesh(Model.cylinder);
		monkey = new Mesh(Model.monkey);
		quad = new Mesh(Model.quad);
	}

	public static void cleanAll () {
		cube.destroy();
		sphere.destroy();
		cone.destroy();
		cylinder.destroy();
		monkey.destroy();
		quad.destroy();
	}

	private int vao, pbo, tbo, nbo, ibo, count;
	private boolean hasTextureCoordinates, hasNormals;

	private Mesh(Model model) {
		hasTextureCoordinates = model.hasTextureCoordinates();
		hasNormals = model.hasNormals();
		count = model.getIndicesCount();
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		pbo = createVBO(model.getPositionsArray(), Shader.POSITION_LOC, 3);
		if (hasTextureCoordinates) {
			tbo = createVBO(model.getTextureCoordinatesArray(), Shader.TEXTURE_COORDINATE_LOC, 2);
		}
		if (hasNormals) {
			nbo = createVBO(model.getNormalsArray(), Shader.NORMAL_LOC, 3);
		}
		ibo = createIBO(model.getIndicesArray());
		glBindVertexArray(0);
	}

	private int createVBO(float[] data, int index, int size) {
		int vertexBufferObject = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
		glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
		glVertexAttribPointer(index, 3, GL_FLOAT, false, size * Float.BYTES, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		return vertexBufferObject;
	}

	private int createIBO(int[] data) {
		int indicesBufferObject = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesBufferObject);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, data, GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		return indicesBufferObject;
	}

	public void render() {
		glBindVertexArray(vao);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glEnableVertexAttribArray(Shader.POSITION_LOC);
		if (hasTextureCoordinates) {
			glEnableVertexAttribArray(Shader.TEXTURE_COORDINATE_LOC);
		}
		if (hasNormals) {
			glEnableVertexAttribArray(Shader.NORMAL_LOC);
		}
		glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_INT, 0);
		if (hasNormals) {
			glDisableVertexAttribArray(Shader.NORMAL_LOC);
		}
		if (hasTextureCoordinates) {
			glDisableVertexAttribArray(Shader.TEXTURE_COORDINATE_LOC);
		}
		glDisableVertexAttribArray(Shader.POSITION_LOC);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}

	public void destroy() {
		glDisableVertexAttribArray(Shader.POSITION_LOC);
		if (hasNormals) {
			glDisableVertexAttribArray(Shader.NORMAL_LOC);
		}
		if (hasTextureCoordinates) {
			glDisableVertexAttribArray(Shader.TEXTURE_COORDINATE_LOC);
		}
//		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
//		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glDeleteBuffers(pbo);
		glDeleteBuffers(tbo);
		glDeleteBuffers(nbo);
		glDeleteBuffers(ibo);
		// Delete the VAO
//		glBindVertexArray(0);
		glDeleteVertexArrays(vao);
	}

}
