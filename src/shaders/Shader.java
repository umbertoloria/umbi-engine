package shaders;

import maths.Mat;
import graphics.Color;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

	public static Shader basic_shader;

	public static void loadAll() {
		basic_shader = new Shader("shade.frag");
	}

	public static final int VERTEX_ATTRIB = 0;
	// public static final int TCOORD_ATTRIB = 1;

	private boolean enabled = false;

	private int ID;

	private Map<String, Integer> locationCache = new HashMap<>();

	private Shader(String shader) {
		ID = ShaderUtils.load(shader);
	}

	private int getUniform(String name) {
		if (locationCache.containsKey(name)) {
			return locationCache.get(name);
		}
		int result = glGetUniformLocation(ID, name);
		if (result == -1) {
			System.err.println("Could not find uniform variable '" + name + "'!");
		} else {
			locationCache.put(name, result);
		}
		return result;
	}

	/*public void setUniform1i(String name, int value) {
		if (!enabled) {
			enable();
		}
		glUniform1i(getUniform(name), value);
	}

	public void setUniform1f(String name, float value) {
		if (!enabled) {
			enable();
		}
		glUniform1f(getUniform(name), value);
	}

	public void setUniform2f(String name, float x, float y) {
		if (!enabled) {
			enable();
		}
		glUniform2f(getUniform(name), x, y);
	}

	public void setUniform3f(String name, float x, float y, float z) {
		if (!enabled) {
			enable();
		}
		glUniform3f(getUniform(name), x, y, z);
	}*/

	public void setUniformColor(String name, Color color) {
		if (!enabled) {
			enable();
		}
		glUniform4f(getUniform(name), color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}

	public void setUniformMat4f(String name, Mat matrix) {
		if (!enabled) {
			enable();
		}
		glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
	}

	public void enable() {
		glUseProgram(ID);
		enabled = true;
	}

	public void disable() {
		glUseProgram(0);
		enabled = false;
	}

}
