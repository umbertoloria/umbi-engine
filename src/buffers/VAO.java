package buffers;

import static org.lwjgl.opengl.GL30.*;

public class VAO {

	private int vao;
	private VBO vbo;
	private IBO ibo;

	public VAO(float[] vertices, int[] indices) {
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		vbo = new VBO(vertices);
		ibo = new IBO(indices);
	}

	public void render() {
		glBindVertexArray(vao);
		glEnableVertexAttribArray(0);
		//glDrawArrays(GL_TRIANGLES, 0, vbo.getVertexCount());
		glDrawElements(GL_TRIANGLES, ibo.getCount(), GL_UNSIGNED_INT, 0);
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
	}

	public void delete() {
		vbo.delete();
		ibo.delete();
	}

}
