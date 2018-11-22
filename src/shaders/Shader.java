package shaders;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.Scanner;

import static org.lwjgl.opengl.GL20.*;

public abstract class Shader {

	private int program, vertexShader, fragmentShader;

	//private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

	public Shader(String filename) {
		String[] src = getSources(filename + ".glsl");
		vertexShader = loadShader(src[0], GL_VERTEX_SHADER);
		fragmentShader = loadShader(src[1], GL_FRAGMENT_SHADER);
		program = glCreateProgram();
		glAttachShader(program, vertexShader);
		glAttachShader(program, fragmentShader);
		bindAttributes();
		glLinkProgram(program);
		glValidateProgram(program);
		getAllUniformLocations();
	}

	void bindAttribute(int attribute, String varName) {
		glBindAttribLocation(program, attribute, varName);
	}

	protected abstract void getAllUniformLocations();

	protected abstract void bindAttributes();

	public void bind() {
		glUseProgram(program);
	}

	public void unbind() {
		glUseProgram(0);
	}

	public void delete() {
		unbind();
		glDetachShader(program, vertexShader);
		glDetachShader(program, fragmentShader);
		glDeleteShader(vertexShader);
		glDeleteShader(fragmentShader);
		glDeleteProgram(program);
	}

	/*protected int getUniformLocation(String uniformName) {
		return glGetUniformLocation(program, uniformName);
	}

	/*protected void loadFloat(int location, float value) {
		glUniform1f(location, value);
	}

	protected void loadVector(int location, Vec2 vec) {
		glUniform3d(location, vec.x(), vec.y(), 0);
	}

	/*protected void loadMatrix(int location, Matrix4f matrix) {
		/*matrix.storeOn(matrixBuffer);
		FloatBuffer b = BufferUtils.createFloatBuffer(16);
		b.put(new float[]{0, 0, 0, 1});
		b.put(new float[]{0, 0, 1, 0});
		b.put(new float[]{0, 1, 0, 0});
		b.put(new float[]{1, 0, 0, 0});
		b.flip();*
		glUniformMatrix4fv(location, false, matrix.toFloatBuffer());
	}*/

	private static int loadShader(String source, int type) {
		int shader = glCreateShader(type);
		glShaderSource(shader, source);
		glCompileShader(shader);
		if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.out.println(glGetShaderInfoLog(shader, 500));
			System.err.println("Could not compile shader!");
			System.exit(-1);
		}
		return shader;
	}

	private static String[] getSources(String filename) {
		String[] sources = new String[2];
		StringBuilder src = new StringBuilder();
		Scanner sc = new Scanner(Shader.class.getResourceAsStream(filename));
		String line;


		while (sc.hasNextLine()) {
			line = sc.nextLine();
			if (line.equalsIgnoreCase("// ---___---___--- //")) {
				break;
			}
			src.append(line);
			src.append("\n");
		}
		sources[0] = src.toString();
		src.delete(0, src.length());
		while (sc.hasNextLine()) {
			src.append(sc.nextLine());
			src.append("\n");
		}
		sources[1] = src.toString();
		return sources;
	}

}
