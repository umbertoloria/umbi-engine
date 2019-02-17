package engine.mesh;

import engine.shaders.Shader;
import graphics.models.Model;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class LinearMesh extends Mesh {

	private int meshBuffer, count;

	LinearMesh(Model model) {
		count = model.getIndicesCount();
		meshBuffer = glGenVertexArrays();
		glBindVertexArray(meshBuffer);
		createVBO(model.getLinearPositionsArray(), Shader.POSITION_LOC);
		createVBO(model.getLinearNormalsArray(), Shader.NORMAL_LOC);
		glBindVertexArray(0);
	}

	void bind() {
		glBindVertexArray(meshBuffer);
		glEnableVertexAttribArray(Shader.POSITION_LOC);
		glEnableVertexAttribArray(Shader.NORMAL_LOC);
	}

	void draw() {
		glDrawArrays(GL_TRIANGLES, 0, count);
	}

	void unbind() {
		glDisableVertexAttribArray(Shader.NORMAL_LOC);
		glDisableVertexAttribArray(Shader.POSITION_LOC);
		glBindVertexArray(0);
	}

}
