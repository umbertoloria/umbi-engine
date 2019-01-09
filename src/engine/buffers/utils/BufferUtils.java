package engine.buffers.utils;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class BufferUtils {

	public static FloatBuffer createFloatBuffer(float[] array) {
		FloatBuffer result = MemoryUtil.memAllocFloat(array.length);
		result.put(array).flip();
		return result;
	}

	public static IntBuffer createIntBuffer(int[] array) {
		IntBuffer result = MemoryUtil.memAllocInt(array.length);
		result.put(array).flip();
		return result;
	}

}
