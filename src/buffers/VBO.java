package buffers;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

public class VBO {

	private int attributeNumber;
	private int vbo;
	private int vertexCount;

	public VBO(int attributeNumber, float[] data) {
		this.attributeNumber = attributeNumber;
		this.vertexCount = data.length / 3;
		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, storeDataInFloatBuffer(data), GL_STATIC_DRAW);
		glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public int getAttributeNumber() {
		return attributeNumber;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	private static FloatBuffer storeDataInFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

	public void delete() {
		glDeleteBuffers(vbo);
	}

}
