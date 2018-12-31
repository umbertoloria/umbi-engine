package shaders;

import utils.FileUtils;

import java.util.Scanner;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

class ShaderUtils {

	static int load(String path) {
		String shaderSource = FileUtils.loadAsString(Shader.class, path);
		Scanner s = new Scanner(shaderSource);
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

		return create(vert.toString(), frag.toString());
	}

	private static int create(String vert, String frag) {
		int program = glCreateProgram();
		int vertID = glCreateShader(GL_VERTEX_SHADER);
		int fragID = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(vertID, vert);
		glShaderSource(fragID, frag);
		glCompileShader(vertID);
		if (glGetShaderi(vertID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Failed to compile vertex shader!");
			System.err.println(glGetShaderInfoLog(vertID));
			return -1;
		}
		glCompileShader(fragID);
		if (glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Failed to compile fragment shader!");
			System.err.println(glGetShaderInfoLog(fragID));
			System.exit(1);
			return -1;
		}
		glAttachShader(program, vertID);
		glAttachShader(program, fragID);
		glLinkProgram(program);
		glValidateProgram(program);
		glDeleteShader(vertID);
		glDeleteShader(fragID);
		return program;
	}

}
