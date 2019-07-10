package engine.shaders;

import graphics.Color;
import graphics.maths.Mat;
import graphics.maths.Vec2;
import graphics.maths.Vec3;
import graphics.textures.Texture;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

	public static final int POSITION_LOC = 0;
	public static final int TEXTURE_COORDINATE_LOC = 1;
	public static final int NORMAL_LOC = 2;

	public static Shader basic, light, gbutton;

	public static void loadAll() {
		basic = Shader.load("texture.frag");
		light = Shader.load("light.frag");
		gbutton = Shader.load("gbutton.frag");
	}

	public static void cleanAll() {
		basic.destroy();
		light.destroy();
	}

	private int program;

	private Map<String, Integer> locationCache = new HashMap<>();

	private static Shader load(String file) {
		Scanner s = new Scanner(Shader.class.getResourceAsStream(file));
		String line;

		StringBuilder vert = new StringBuilder();
		while (s.hasNextLine()) {
			line = s.nextLine();
			if (line.equals("// ---___---___--- //")) {
				break;
			}
			vert.append(line);
			vert.append('\n');
		}

		StringBuilder frag = new StringBuilder();
		while (s.hasNextLine()) {
			frag.append(s.nextLine());
			frag.append('\n');
		}

		return new Shader(vert.toString(), frag.toString());
	}

	public Shader(String vertexShader, String fragmentShader) {
		int program = glCreateProgram();
		int vertID = glCreateShader(GL_VERTEX_SHADER);
		int fragID = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(vertID, vertexShader);
		glShaderSource(fragID, fragmentShader);
		glCompileShader(vertID);
		if (glGetShaderi(vertID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("ODDEOOOOOO");
			throw new IllegalStateException("Invalid vertex shader: " + glGetShaderInfoLog(vertID));
		}
		glCompileShader(fragID);
		if (glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("ODDEOOOOOO");
			throw new IllegalStateException("Invalid fragment shader: " + glGetShaderInfoLog(fragID));
		}
		glAttachShader(program, vertID);
		glAttachShader(program, fragID);
		glLinkProgram(program);
		glValidateProgram(program);
		glDeleteShader(vertID);
		glDeleteShader(fragID);
		this.program = program;
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

	public void enable() {
		glUseProgram(program);
	}

	private void destroy() {
		glDeleteProgram(program);
	}

}
