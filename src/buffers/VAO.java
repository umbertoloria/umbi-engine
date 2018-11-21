package buffers;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL30.*;

public class VAO {

	private int vao;
	private ArrayList<VBO> vbos = new ArrayList<>();

	public VAO() {
		vbos.ensureCapacity(16);
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
	}

	public void add(float[] data) {
		vbos.add(new VBO(vbos.size(), data));
	}

	public void render() {
		glBindVertexArray(vao);
		for (VBO vbo : vbos) {
			glEnableVertexAttribArray(vbo.getAttributeNumber());
			glDrawArrays(GL_TRIANGLES, 0, vbo.getVertexCount());
			glDisableVertexAttribArray(0);
		}
		glBindVertexArray(0);
	}

}
