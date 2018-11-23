package shaders;

import metrics.Matrix;

import java.util.Scanner;

import static org.lwjgl.opengl.GL40.*;

public abstract class Shader {

	private int program, vertexShader, fragmentShader;

	public Shader(String filename) {
		program = glCreateProgram();

		String[] src = getSources(filename + ".glsl");
		vertexShader = createShader(src[0], GL_VERTEX_SHADER);
		fragmentShader = createShader(src[1], GL_FRAGMENT_SHADER);

		glAttachShader(program, vertexShader);
		glAttachShader(program, fragmentShader);

		bindAttributes();

		glLinkProgram(program);
		if (glGetProgrami(program, GL_LINK_STATUS) != 1) {
			System.out.println("Unable to link the program.");
			System.exit(1);
		}
		glValidateProgram(program);
		if (glGetProgrami(program, GL_VALIDATE_STATUS) != 1) {
			System.out.println("Unable to validate the program.");
			System.exit(1);
		}
	}

	protected abstract void bindAttributes();

	void bindAttribute(int attribute, String varName) {
		glBindAttribLocation(program, attribute, varName);
	}

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

	protected int loadUniform(String name) {
		return glGetUniformLocation(program, name);
	}

	/*
		protected void loadFloat(int location, float value) {
			glUniform1f(location, value);
		}

		protected void loadVector(int location, Vec3 vec) {
			glUniform3d(location, vec.x(), vec.y(), vec.z());
		}

		protected void loadFloats(int location, float a, float b, float c) {
			glUniform3d(location, a, b, c);
		}

		protected void loadVector4f(int location, float a, float b, float c, float d) {
			glUniform4f(location, a, b, c, d);
		}

		protected void loadMatrix(int location, Matrix4f matrix) {
			/*matrix.store(matrixBuffer);
			matrixBuffer.flip();*
			float[] data = new float[]{
					1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
			};
			FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
			matrixBuffer.put(data);
			matrixBuffer.flip();
			System.out.println("put");
			glUniformMatrix4fv(location, false, matrixBuffer);
		}
	*/

	public void setInt(int uniform, int value) {
		glUniform1i(uniform, value);
	}

	protected void setFloat(int uniform, float value) {
		glUniform1f(uniform, value);
	}

	protected void setMatrix(int uniform, Matrix m) {
		glUniformMatrix4fv(uniform, false, m.toFloatBuffer());
	}

	private static int createShader(String source, int type) {
		int shader = glCreateShader(type);
		glShaderSource(shader, source);
		glCompileShader(shader);
		if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.out.println(glGetShaderInfoLog(shader, 500));
			System.exit(1);
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
