package engine.shaders;

import graphics.Color;
import graphics.maths.Mat;
import graphics.maths.Vec2;
import graphics.maths.Vec3;
import graphics.textures.Texture;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public abstract class Shader {

	public static final int POSITION_LOC = 0;
	public static final int TEXTURE_COORDINATE_LOC = 1;
	public static final int NORMAL_LOC = 2;

	public static Shader basic, light, gbutton;

	public static void loadAll() {
		basic = new BasicShader();
		light = new LightShader();
		gbutton = new GButtonShader();
	}

	public static void cleanAll() {
		basic.destroy();
		light.destroy();
	}

	private int program;

	private Map<String, Integer> locationCache = new HashMap<>();

	Shader(String shader) {
		program = ShaderUtils.load(shader);
	}

	private int getUniform(String name) {
		if (locationCache.containsKey(name)) {
			return locationCache.get(name);
		}
		int result = glGetUniformLocation(program, name);
		if (result == -1) {
			System.err.println("Could not find uniform variable '" + name + "'!");
		} else {
			locationCache.put(name, result);
		}
		return result;
	}

	public void setUniformColor(String name, Color color) {
		glUniform4f(getUniform(name), color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}

	public void setUniformMat4f(String name, Mat matrix) {
		glUniformMatrix4fv(getUniform(name), false, matrix.toArray());
	}

	public void setUniformVec2(String name, Vec2 v) {
		glUniform2f(getUniform(name), v.x, v.y);
	}

	public void setUniformVec3(String name, Vec3 v) {
		glUniform3f(getUniform(name), v.x, v.y, v.z);
	}

	public void setUniformTexture(String name, Texture texture) {
		glUniform1i(getUniform(name), texture.getSlot());
	}

	public void setUniformInt(String name, int v) {
		glUniform1i(getUniform(name), v);
	}

	public void enable() {
		glUseProgram(program);
	}

	public void disable() {
		glUseProgram(0);
	}

	private void destroy() {
		glDeleteProgram(program);
	}

}
