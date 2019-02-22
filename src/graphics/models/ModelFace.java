package graphics.models;

public class ModelFace {

	private int positionIndex, textureCoordinateIndex, normalIndex;

	ModelFace(int positionIndex, int textureCoordinateIndex, int normalIndex) {
		this.positionIndex = positionIndex;
		this.textureCoordinateIndex = textureCoordinateIndex;
		this.normalIndex = normalIndex;
	}

	int getPositionIndex() {
		return positionIndex;
	}

	int getNormalIndex() {
		return normalIndex;
	}

	int getTextureCoordinateIndex() {
		return textureCoordinateIndex;
	}

}
