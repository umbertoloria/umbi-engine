package buffers;

import org.lwjgl.BufferUtils;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;

public class IBO {

	private int id, count;

	public IBO(int[] indices) {
		this.count = indices.length;
		this.id = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, storeDataInIntBuffer(indices), GL_STATIC_DRAW);
	}

	public int getCount() {
		return count;
	}

	private static IntBuffer storeDataInIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

	public void delete() {
		glDeleteBuffers(id);
	}

}
