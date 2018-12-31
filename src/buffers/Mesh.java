package buffers;


import models.Model;
import utils.BufferUtils;
import shaders.Shader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Mesh {

	public static Mesh cube, sphere, cone;

	public static void loadAll() {
		cube = new Mesh(Model.cube);
		sphere = new Mesh(Model.sphere);
		cone = new Mesh(Model.cone);
	}

	private int vao, ibo, count;

	// TODO: Textures e Normals
	private Mesh(Model model) {
		this(model.getVertices(), model.getIndices());
	}

	private Mesh(float[] vertices, int[] indices) {
		count = indices.length;

		vao = glGenVertexArrays();
		glBindVertexArray(vao);

		int vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);
		glVertexAttribPointer(Shader.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(Shader.VERTEX_ATTRIB);

		/*tbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, tbo);
		glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(textureCoordinates), GL_STATIC_DRAW);
		glVertexAttribPointer(Shader.TCOORD_ATTRIB, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(Shader.TCOORD_ATTRIB);*/

		ibo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createIntBuffer(indices), GL_STATIC_DRAW);

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}

	private void bind() {
		glBindVertexArray(vao);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
	}

	private void draw() {
		glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_INT, 0);
		// glDrawArrays(GL_TRIANGLES, 0, count);
	}

	public void render() {
		bind();
		draw();
	}

}
