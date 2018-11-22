package shaders;

public class StaticShader extends Shader {

	private int location_transformationMatrix;

	public StaticShader(String filename) {
		super(filename);
		//Matrix m = Maths.createTransformationMatrix(new Vec3(-10, -10, -10), 0, 0, 0, 500);
		//Matrix4f m = Matrix4f.orthographic(-1, 1, 1, -1, 0, 0);
		//loadTransformationMatrix(m);
	}

	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

	protected void getAllUniformLocations() {
		//location_transformationMatrix = super.getUniformLocation("transformationMatrix");
	}

	/*public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}*/

}
