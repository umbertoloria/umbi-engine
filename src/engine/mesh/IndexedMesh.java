package engine.mesh;

import engine.shaders.Shader;
import graphics.models.Model;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;

public class IndexedMesh extends Mesh {

	private int meshBuffer, indicesBuffer, count;

	IndexedMesh(Model model) {
		count = model.getIndicesCount();
		meshBuffer = glGenVertexArrays();
		glBindVertexArray(meshBuffer);
		createVBO(model.getPositionsArray(), Shader.POSITION_LOC);
		createVBO(model.getNormalsArray(), Shader.NORMAL_LOC);
		indicesBuffer = createVBO(model.getIndicesArray());
		glBindVertexArray(0);
	}

	void bind() {
		glBindVertexArray(meshBuffer);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer);
		glEnableVertexAttribArray(Shader.POSITION_LOC);
		glEnableVertexAttribArray(Shader.NORMAL_LOC);
	}

	void draw() {
		glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_INT, 0);
	}

	void unbind() {
		glDisableVertexAttribArray(Shader.NORMAL_LOC);
		glDisableVertexAttribArray(Shader.POSITION_LOC);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}

}
