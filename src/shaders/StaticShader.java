package shaders;

import metrics.Matrix;
import inputs.Camera;

public class StaticShader extends Shader {

	private int transformationMatrix_location;
	private int projectionMatrix_location;
	private int viewMatrix_location;

	public StaticShader(String filename) {
		super(filename);
		transformationMatrix_location = loadUniform("transformationMatrix");
		projectionMatrix_location = loadUniform("projectionMatrix");
		viewMatrix_location = loadUniform("viewMatrix");
	}

	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

	public void loadTransformationMatrix(Matrix matrix) {
		super.setMatrix(transformationMatrix_location, matrix);
	}

	public void loadProjectionMatrix(Camera camera) {
		super.setMatrix(projectionMatrix_location, camera.getProjectionMatrix());
	}

	public void loadViewMatrix(Camera camera) {
		super.setMatrix(viewMatrix_location, Matrix.createViewMatrix(camera));
	}

}
